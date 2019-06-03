// hardcoded for now
//var port = document.getElementById("portInput").value;
// var hostname = 'mrq8m7h73wx2t.messaging.solace.cloud';
var hostname = 'mr-q8m7h73wxpl.messaging.solace.cloud';
// var port = '8000';
// var path = '/ws'
// var tls = false;
var port = '8443';
var path = '/wss'
var tls = true;

var qos = 1;
var retain = false;
var timeout = 60;
var keepAlive = 120;
var cleanSession = true;

var topicProductInfo = "us1/prodinfo/#"
var topicPub = ""
var topicSub = "";
var clientId = "" + (Math.random() + 1).toString(36).substring(2, 10);  // 8 "random" [0-9a-z]
var connected = false;
setFormEnabledState(false);

// called when the client connects
function onConnect(context) {
  // Once a connection has been made, make a subscription and send a message.
  var connectionString = context.invocationContext.host + ":" + context.invocationContext.port + context.invocationContext.path;
  logMessage("INFO", "Connection Success ", "[URI: ", connectionString, ", ID: ", context.invocationContext.clientId, "]");
  var statusSpan = document.getElementById("connectionStatus");
  statusSpan.innerHTML = "Connected to Solace PubSub+: " + connectionString + " with Client ID: " + context.invocationContext.clientId;
  connected = true;
  setFormEnabledState(true);

  // sub to catalog updates
  client.subscribe(topicProductInfo);
  logMessage("INFO", "Subscribed to " + topicProductInfo);
  // sub to my delivery status
  client.subscribe(topicSub);
  logMessage("INFO", "Subscribed to " + topicSub);
}


function onConnected(reconnect, uri) {
  // Once a connection has been made, make a subscription and send a message.
  logMessage("INFO", "Client Has now connected: [Reconnected: ", reconnect, ", URI: ", uri, "]");
  connected = true;
}

function onFail(context) {
  logMessage("ERROR", "Failed to connect. [Error Message: ", context.errorMessage, "]");
  var statusSpan = document.getElementById("connectionStatus");
  statusSpan.innerHTML = "Failed to connect: " + context.errorMessage;
  connected = false;
  setFormEnabledState(false);
}

// called when the client loses its connection
function onConnectionLost(responseObject) {
  if (responseObject.errorCode !== 0) {
    logMessage("INFO", "Connection Lost. [Error Message: ", responseObject.errorMessage, "]");
  }
  connected = false;
}

// called when a message arrives
function onMessageArrived(message) {
  // got a catalog update
  if (message.destinationName.startsWith(topicProductInfo.replace('#', ''))) {
    var prodInfo = JSON.parse(message.payloadString);
    prodInfo.items.forEach(item => {
      console.log('Received item: ' + JSON.stringify(item));
      if (item.status === 'NEW') {
        ShoppingCart.products.push(item);
        ShoppingCart.generateProductList();
      }
      else if (item.status === 'UPD') {
        pushToArray(ShoppingCart.products, item);
        ShoppingCart.generateProductList();
      }
      else {
        console.log('Found unknown product info with status: ' + item.status);
      }

    });
  }
  else { // delivery status
    var deliveryMsg = JSON.parse(message.payloadString);
    logMessage("INFO", "TOPIC='" + message.destinationName + "',&nbsp;&nbsp;&nbsp;PAYLOAD='" + message.payloadString + "'");
    var table = document.getElementById("incomingMessageTable").getElementsByTagName("tbody")[0];
    var row = table.insertRow(0);
    row.insertCell(0).innerHTML = new Date();

    row.insertCell(1).innerHTML = safeTagsRegex(
      ": Received order confirmation from shop: " + deliveryMsg.sender.id
      + " for " + deliveryMsg.items.length + " product(s)."
      );
  }
}

function pushToArray(arr, obj) {
  const index = arr.findIndex((e) => e.productId === obj.productId);
  if (index === -1) {
    arr.push(obj);
  } else {
    // console.log(obj.productId + ": BEFORE : " + JSON.stringify(arr[index]));
    arr[index] = obj;
    // console.log(obj.productId + ": AFTER : " + JSON.stringify(obj));
  }
}

function sendOrder(order) {
  var message = new Paho.MQTT.Message(order);
  message.destinationName = topicPub;
  message.qos = qos;
  message.retained = retain;
  client.send(message);
  console.log('order sent');
}

function logMessage(type, ...content) {
  var consolePre = document.getElementById("consolePre");
  var date = new Date();
  var timeString = date.toUTCString();
  var logMessage = timeString + " - " + type + " - " + content.join("");
  consolePre.innerHTML += logMessage + "\n";
  if (type === "INFO") {
    console.info(logMessage);
  } else if (type === "ERROR") {
    console.error(logMessage);
  } else {
    console.log(logMessage);
  }
}

// Just in case someone sends html
function safeTagsRegex(str) {
  return str.replace(/&/g, "&amp;").replace(/</g, "&lt;").
    replace(/>/g, "&gt;");
}



function connectionToggle() {
  if (connected) {
    disconnect();
  } else {
    connect();
  }
}

function connect() {
  // var hostname = document.getElementById("hostInput").value;
  var user = document.getElementById("userInput").value;
  var pass = document.getElementById("passInput").value;
  var shopID = document.getElementById("shopID").value;
  var custID = document.getElementById("custID").value;
  topicPub = "us1/order/cust/" + custID + "/shop/" + shopID;
  topicSub = "us1/delivery/shop/+/cust/" + custID;

  client = new Paho.MQTT.Client(hostname, Number(port), path, clientId);
  logMessage("INFO", "Connecting to Server: [Host: ", hostname, ", Port: ", port, ", Path: ", client.path, ", ID: ", clientId, "]");

  // set callback handlers
  client.onConnectionLost = onConnectionLost;
  client.onMessageArrived = onMessageArrived;
  client.onConnected = onConnected;

  var options = {
    invocationContext: { host: hostname, port: port, path: client.path, clientId: clientId },
    timeout: timeout,
    keepAliveInterval: keepAlive,
    cleanSession: cleanSession,
    useSSL: tls,
    onSuccess: onConnect,
    onFailure: onFail
  };

  if (user.length > 0) {
    options.userName = user;
  }

  if (pass.length > 0) {
    options.password = pass;
  }

  // connect the client
  client.connect(options);
  var statusSpan = document.getElementById("connectionStatus");
  statusSpan.innerHTML = "Connecting...";
}

function disconnect() {
  logMessage("INFO", "Disconnecting from Server.");
  client.disconnect();
  var statusSpan = document.getElementById("connectionStatus");
  statusSpan.innerHTML = "Connection - Disconnected.";
  connected = false;
  setFormEnabledState(false);

}

// Sets various form controls to either enabled or disabled
function setFormEnabledState(enabled) {
  // Connection Panel Elements
  if (enabled) {
    document.getElementById("clientConnectButton").innerHTML = "Disconnect";
    document.getElementById("order").style.display = "block";
  } else {
    document.getElementById("clientConnectButton").innerHTML = "Connect";
    document.getElementById("order").style.display = "none";
  }
  // document.getElementById("hostInput").disabled = enabled;
  // document.getElementById("portInput").disabled = enabled;
  document.getElementById("userInput").disabled = enabled;
  document.getElementById("passInput").disabled = enabled;
  document.getElementById("shopID").disabled = enabled;
  document.getElementById("custID").disabled = enabled;

}