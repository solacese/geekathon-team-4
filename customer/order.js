var ShoppingCart = (function ($) {
    "use strict";

    // Cahce necesarry DOM Elements
    var productsEl = document.querySelector(".products"),
        cartEl = document.querySelector(".shopping-cart-list"),
        productQuantityEl = document.querySelector(".product-quantity"),
        emptyCartEl = document.querySelector(".empty-cart-btn"),
        cartCheckoutEl = document.querySelector(".cart-checkout"),
        totalPriceEl = document.querySelector(".total-price");

    // init json product-db
    var productsInCart = [];
    //var products = [{"productId":"100000000","text":"Pullover","category":"Clothing","size":"S","color":"Red","unit":"UnitType.PC","currency":"USD","price":"25.00","popularity":"3"},{"productId":"100000001","text":"Pullover","category":"Clothing","size":"M","color":"Green","unit":"UnitType.PC","currency":"USD","price":"25.00","popularity":"2"},{"productId":"100000002","text":"Pullover","category":"Clothing","size":"L","color":"Blue","unit":"UnitType.PC","currency":"USD","price":"25.00","popularity":"4"},{"productId":"100000003","text":"Pullover","category":"Clothing","size":"XL","color":"Black","unit":"UnitType.PC","currency":"USD","price":"25.00","popularity":"5"},{"productId":"100000004","text":"Pullover","category":"Clothing","size":"XXL","color":"White","unit":"UnitType.PC","currency":"USD","price":"25.00","popularity":"3"},{"productId":"100000005","text":"Tshirt","category":"Clothing","size":"S","color":"Red","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"5"},{"productId":"100000006","text":"Tshirt","category":"Clothing","size":"M","color":"Green","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"5"},{"productId":"100000007","text":"Tshirt","category":"Clothing","size":"L","color":"Blue","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"2"},{"productId":"100000008","text":"Tshirt","category":"Clothing","size":"XL","color":"Black","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"3"},{"productId":"100000009","text":"Tshirt","category":"Clothing","size":"XXL","color":"White","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"5"},{"productId":"100000010","text":"Pants Long","category":"Clothing","size":"S","color":"Blue","unit":"UnitType.PC","currency":"USD","price":"34.00","popularity":"1"},{"productId":"100000011","text":"Pants Long","category":"Clothing","size":"M","color":"Blue","unit":"UnitType.PC","currency":"USD","price":"45.00","popularity":"3"},{"productId":"100000012","text":"Pants Long","category":"Clothing","size":"L","color":"Blue","unit":"UnitType.PC","currency":"USD","price":"34.00","popularity":"2"},{"productId":"100000013","text":"Pants Long","category":"Clothing","size":"XL","color":"Black","unit":"UnitType.PC","currency":"USD","price":"56.00","popularity":"1"},{"productId":"100000014","text":"Pants Long","category":"Clothing","size":"XXL","color":"Black","unit":"UnitType.PC","currency":"USD","price":"67.00","popularity":"4"},{"productId":"100000015","text":"Socks","category":"Clothing","size":"S","color":"Black","unit":"UnitType.PC","currency":"USD","price":"9.50","popularity":"4"},{"productId":"100000016","text":"Socks","category":"Clothing","size":"M","color":"Black","unit":"UnitType.PC","currency":"USD","price":"9.50","popularity":"1"},{"productId":"100000017","text":"Socks","category":"Clothing","size":"L","color":"Black","unit":"UnitType.PC","currency":"USD","price":"9.50","popularity":"3"},{"productId":"100000018","text":"Socks","category":"Clothing","size":"XL","color":"Black","unit":"UnitType.PC","currency":"USD","price":"9.50","popularity":"2"},{"productId":"100000019","text":"Socks","category":"Clothing","size":"XXL","color":"Black","unit":"UnitType.PC","currency":"USD","price":"9.50","popularity":"2"},{"productId":"100000020","text":"Knife","category":"Kitchen","other":"Vegetables","unit":"UnitType.PC","currency":"USD","price":"3.00","popularity":"4"},{"productId":"100000021","text":"Knife","category":"Kitchen","other":"Steak","unit":"UnitType.PC","currency":"USD","price":"4.00","popularity":"1"},{"productId":"100000022","text":"Knife","category":"Kitchen","size":"S","unit":"UnitType.PC","currency":"USD","price":"23.00","popularity":"5"},{"productId":"100000023","text":"Knife","category":"Kitchen","size":"M","unit":"UnitType.PC","currency":"USD","price":"34.00","popularity":"3"},{"productId":"100000024","text":"Knife","category":"Kitchen","size":"L","unit":"UnitType.PC","currency":"USD","price":"44.00","popularity":"3"},{"productId":"100000025","text":"Fork","category":"Kitchen","size":"S","unit":"UnitType.PC","currency":"USD","price":"10.00","popularity":"4"},{"productId":"100000026","text":"Fork","category":"Kitchen","size":"M","unit":"UnitType.PC","currency":"USD","price":"11.00","popularity":"1"},{"productId":"100000027","text":"Fork","category":"Kitchen","size":"L","unit":"UnitType.PC","currency":"USD","price":"12.00","popularity":"4"},{"productId":"100000028","text":"Spoon","category":"Kitchen","size":"S","unit":"UnitType.PC","currency":"USD","price":"10.00","popularity":"1"},{"productId":"100000029","text":"Spoon","category":"Kitchen","size":"M","unit":"UnitType.PC","currency":"USD","price":"11.00","popularity":"5"},{"productId":"100000030","text":"Spoon","category":"Kitchen","size":"L","unit":"UnitType.PC","currency":"USD","price":"12.00","popularity":"1"},{"productId":"100000031","text":"Plate","category":"Kitchen","size":"S","color":"White","unit":"UnitType.PC","currency":"USD","price":"6.00","popularity":"5"},{"productId":"100000032","text":"Plate","category":"Kitchen","size":"M","color":"White","unit":"UnitType.PC","currency":"USD","price":"8.00","popularity":"5"},{"productId":"100000033","text":"Plate","category":"Kitchen","size":"L","color":"White","unit":"UnitType.PC","currency":"USD","price":"10.00","popularity":"2"},{"productId":"100000034","text":"Glass","category":"Kitchen","color":"Water","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"3"},{"productId":"100000035","text":"Glass","category":"Kitchen","other":"White Wine","unit":"UnitType.PC","currency":"USD","price":"9.00","popularity":"4"},{"productId":"100000036","text":"Glass","category":"Kitchen","other":"Read Wine","unit":"UnitType.PC","currency":"USD","price":"11.00","popularity":"5"},{"productId":"100000037","text":"Notebook","category":"Office","size":"S","other":"Lines","unit":"UnitType.PC","currency":"USD","price":"4.00","popularity":"4"},{"productId":"100000038","text":"Notebook","category":"Office","size":"M","other":"Plain","unit":"UnitType.PC","currency":"USD","price":"6.00","popularity":"1"},{"productId":"100000039","text":"Notebook","category":"Office","size":"L","unit":"UnitType.PC","currency":"USD","price":"8.00","popularity":"3"},{"productId":"100000040","text":"Scissors","category":"Office","size":"S","other":"Stainless","unit":"UnitType.PC","currency":"USD","price":"23.00","popularity":"5"},{"productId":"100000041","text":"Scissors","category":"Office","size":"M","other":"Stainless","unit":"UnitType.PC","currency":"USD","price":"27.00","popularity":"1"},{"productId":"100000042","text":"Scissors","category":"Office","size":"L","other":"Stainless","unit":"UnitType.PC","currency":"USD","price":"31.00","popularity":"5"},{"productId":"100000043","text":"Cable Mains","category":"Doityourself","size":"5m","unit":"UnitType.PC","currency":"USD","price":"3.00","popularity":"2"},{"productId":"100000044","text":"Cable Mains","category":"Doityourself","size":"10m","unit":"UnitType.PC","currency":"USD","price":"5.00","popularity":"5"},{"productId":"100000045","text":"Cable Mains","category":"Doityourself","size":"20m","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"4"},{"productId":"100000046","text":"Tube Alu","category":"Doityourself","size":"15mm x 10m","unit":"UnitType.PC","currency":"USD","price":"23.00","popularity":"1"},{"productId":"100000047","text":"Tube Alu","category":"Doityourself","size":"30mm x 10m","unit":"UnitType.PC","currency":"USD","price":"28.00","popularity":"3"},{"productId":"100000048","text":"Tube Alu","category":"Doityourself","size":"50mm x 10m","unit":"UnitType.PC","currency":"USD","price":"32.00","popularity":"2"},{"productId":"100000049","text":"Board","category":"Doityourself","size":"40x60cm","other":"Wood","unit":"UnitType.PC","currency":"USD","price":"12.00","popularity":"4"},{"productId":"100000050","text":"Board","category":"Doityourself","size":"40x80cm","other":"Wood","unit":"UnitType.PC","currency":"USD","price":"15.00","popularity":"1"},{"productId":"100000051","text":"Board","category":"Doityourself","size":"40x100cm","other":"Wood","unit":"UnitType.PC","currency":"USD","price":"18.00","popularity":"5"}]
    var products = [{"productId":"100000020","text":"Knife","category":"Kitchen","other":"Vegetables","unit":"UnitType.PC","currency":"USD","price":"3.00","popularity":"4"},{"productId":"100000021","text":"Knife","category":"Kitchen","other":"Steak","unit":"UnitType.PC","currency":"USD","price":"4.00","popularity":"1"},{"productId":"100000022","text":"Knife","category":"Kitchen","size":"S","unit":"UnitType.PC","currency":"USD","price":"23.00","popularity":"5"},{"productId":"100000023","text":"Knife","category":"Kitchen","size":"M","unit":"UnitType.PC","currency":"USD","price":"34.00","popularity":"3"},{"productId":"100000024","text":"Knife","category":"Kitchen","size":"L","unit":"UnitType.PC","currency":"USD","price":"44.00","popularity":"3"},{"productId":"100000025","text":"Fork","category":"Kitchen","size":"S","unit":"UnitType.PC","currency":"USD","price":"10.00","popularity":"4"},{"productId":"100000026","text":"Fork","category":"Kitchen","size":"M","unit":"UnitType.PC","currency":"USD","price":"11.00","popularity":"1"},{"productId":"100000027","text":"Fork","category":"Kitchen","size":"L","unit":"UnitType.PC","currency":"USD","price":"12.00","popularity":"4"},{"productId":"100000028","text":"Spoon","category":"Kitchen","size":"S","unit":"UnitType.PC","currency":"USD","price":"10.00","popularity":"1"},{"productId":"100000029","text":"Spoon","category":"Kitchen","size":"M","unit":"UnitType.PC","currency":"USD","price":"11.00","popularity":"5"},{"productId":"100000030","text":"Spoon","category":"Kitchen","size":"L","unit":"UnitType.PC","currency":"USD","price":"12.00","popularity":"1"},{"productId":"100000031","text":"Plate","category":"Kitchen","size":"S","color":"White","unit":"UnitType.PC","currency":"USD","price":"6.00","popularity":"5"},{"productId":"100000032","text":"Plate","category":"Kitchen","size":"M","color":"White","unit":"UnitType.PC","currency":"USD","price":"8.00","popularity":"5"},{"productId":"100000033","text":"Plate","category":"Kitchen","size":"L","color":"White","unit":"UnitType.PC","currency":"USD","price":"10.00","popularity":"2"},{"productId":"100000034","text":"Glass","category":"Kitchen","color":"Water","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"3"},{"productId":"100000035","text":"Glass","category":"Kitchen","other":"White Wine","unit":"UnitType.PC","currency":"USD","price":"9.00","popularity":"4"},{"productId":"100000036","text":"Glass","category":"Kitchen","other":"Read Wine","unit":"UnitType.PC","currency":"USD","price":"11.00","popularity":"5"},{"productId":"100000037","text":"Notebook","category":"Office","size":"S","other":"Lines","unit":"UnitType.PC","currency":"USD","price":"4.00","popularity":"4"},{"productId":"100000038","text":"Notebook","category":"Office","size":"M","other":"Plain","unit":"UnitType.PC","currency":"USD","price":"6.00","popularity":"1"},{"productId":"100000039","text":"Notebook","category":"Office","size":"L","unit":"UnitType.PC","currency":"USD","price":"8.00","popularity":"3"},{"productId":"100000040","text":"Scissors","category":"Office","size":"S","other":"Stainless","unit":"UnitType.PC","currency":"USD","price":"23.00","popularity":"5"},{"productId":"100000041","text":"Scissors","category":"Office","size":"M","other":"Stainless","unit":"UnitType.PC","currency":"USD","price":"27.00","popularity":"1"},{"productId":"100000042","text":"Scissors","category":"Office","size":"L","other":"Stainless","unit":"UnitType.PC","currency":"USD","price":"31.00","popularity":"5"},{"productId":"100000043","text":"Cable Mains","category":"Doityourself","size":"5m","unit":"UnitType.PC","currency":"USD","price":"3.00","popularity":"2"},{"productId":"100000044","text":"Cable Mains","category":"Doityourself","size":"10m","unit":"UnitType.PC","currency":"USD","price":"5.00","popularity":"5"},{"productId":"100000045","text":"Cable Mains","category":"Doityourself","size":"20m","unit":"UnitType.PC","currency":"USD","price":"7.00","popularity":"4"},{"productId":"100000046","text":"Tube Alu","category":"Doityourself","size":"15mm x 10m","unit":"UnitType.PC","currency":"USD","price":"23.00","popularity":"1"},{"productId":"100000047","text":"Tube Alu","category":"Doityourself","size":"30mm x 10m","unit":"UnitType.PC","currency":"USD","price":"28.00","popularity":"3"},{"productId":"100000048","text":"Tube Alu","category":"Doityourself","size":"50mm x 10m","unit":"UnitType.PC","currency":"USD","price":"32.00","popularity":"2"},{"productId":"100000049","text":"Board","category":"Doityourself","size":"40x60cm","other":"Wood","unit":"UnitType.PC","currency":"USD","price":"12.00","popularity":"4"},{"productId":"100000050","text":"Board","category":"Doityourself","size":"40x80cm","other":"Wood","unit":"UnitType.PC","currency":"USD","price":"15.00","popularity":"1"},{"productId":"100000051","text":"Board","category":"Doityourself","size":"40x100cm","other":"Wood","unit":"UnitType.PC","currency":"USD","price":"18.00","popularity":"5"}]
    
    // Pretty much self explanatory function. NOTE: Here I have used template strings (ES6 Feature)
    var generateProductList = function () {
        while (productsEl.firstChild) {
            productsEl.removeChild(productsEl.firstChild);
        }
        products.forEach(function (item) {
            var productEl = document.createElement("div");
            productEl.className = "product";
            productEl.innerHTML = `
            ${item.category} : ${item.text} : ${item.size} : ${item.currency} ${item.price} : 
            <a href="#0" class="button add-to-cart" data-id=${item.productId}>[ + ]</a>
        </div>
        `;

            productsEl.appendChild(productEl);
        });
    }

    // Like one before and I have also used ES6 template strings
    var generateCartList = function () {

        cartEl.innerHTML = "";

        productsInCart.forEach(function (item) {
            var li = document.createElement("li");
            li.innerHTML = `${item.quantity} ${item.product.category}/${item.product.text}/${item.product.size} - ${item.product.currency} ${item.product.price * item.quantity}`;
            cartEl.appendChild(li);
        });

        productQuantityEl.innerHTML = productsInCart.length;

        generateCartButtons()
    }


    // Function that generates Empty Cart and Checkout buttons based on condition that checks if productsInCart array is empty
    var generateCartButtons = function () {
        if (productsInCart.length > 0) {
            emptyCartEl.style.display = "block";
            cartCheckoutEl.style.display = "block"
            totalPriceEl.innerHTML = "$ " + calculateTotalPrice();
        } else {
            emptyCartEl.style.display = "none";
            cartCheckoutEl.style.display = "none";
        }
    }

    // Setting up listeners for click event on all products and Empty Cart button as well
    var setupListeners = function () {
        productsEl.addEventListener("click", function (event) {
            var el = event.target;
            if (el.classList.contains("add-to-cart")) {
                var elId = el.dataset.id;
                addToCart(elId);
            }
        });

        cartCheckoutEl.addEventListener("click", function (event) {
            // checkout();
            // console.log("shop: " + document.getElementById("shopID").value);
            // console.log("cust: " + document.getElementById("custID").value);
            checkout(
                document.getElementById("shopID").value,
                document.getElementById("custID").value
                );
        });

        emptyCartEl.addEventListener("click", function (event) {
            if (confirm("Are you sure?")) {
                productsInCart = [];
            }
            generateCartList();
        });
    }

    // Adds new items or updates existing one in productsInCart array
    var addToCart = function (id) {
        var obj = findElement(products, 'productId', id);
        if (productsInCart.length === 0 || productFound(obj.productId) === undefined) {
            productsInCart.push({ product: obj, quantity: 1 });
            console.log('adding  ' + obj.productId + ':' + obj.text);
        } else {
            productsInCart.forEach(function (item) {
                if (item.product.productId === obj.productId) {
                    item.quantity++;
                    console.log('adding another ' + obj.productId  + ':' + obj.text);
                }
            });
        }
        generateCartList();
    }

    // this is a poor hack, I know, help me :)
    function findElement(arr, propName, propValue) {
        for (var i = 0; i < arr.length; i++)
            if (arr[i][propName] == propValue)
                return arr[i];
        // will return undefined if not found; you could return a default instead
    }

    // This function checks if project is already in productsInCart array
    
    var productFound = function (productId) {
        return productsInCart.find(function (item) {
            return item.product.productId === productId;
        });
    }

    var calculateTotalPrice = function () {
        return productsInCart.reduce(function (total, item) {
            return total + (item.product.price * item.quantity);
        }, 0);
    }

    var checkout = function(shopID, custID) {
        sendOrder(buildOrderJSON(shopID, custID));
        confirm('Your order has been sent. Stunning!');
        productsInCart = [];
        generateCartList();
    }

    // var buildOrderJSON = function() {
    //     buildOrderJSON("123456789", "200000000");
    // }

    var buildOrderJSON = function(shopID, custID) {
        var utcDateTime = new Date();
        var locDateTime = utcDateTime;
        var order = {  "utcDateTime": utcDateTime,  "locDateTime": locDateTime,  "referenceId": "list-id", 
            "sender": {    "id": custID,    "name": "sendername",    "street": "senderstreet",    "city": "sendercity",    "zip": "senderzip",    "country": "sendercountry"  },
            "receiver": {    "id": shopID,    "name": "receivername",    "street": "receiverstreet",    "city": "receivercity",    "zip": "receiverzip",    "country": "receivercountry"  },
            "items": []
        }

        productsInCart.forEach(function (item) {
            var orderItem = {
                "productId": `${item.product.productId}`,
                "text": `${item.product.text}`,
                "quantity": `${item.quantity}`,
                "currency": `${item.product.currency}`,
                "price": `${item.product.price}`
            };
            order.items.push(orderItem);
        });
        return JSON.stringify(order);
    }

    // This functon starts the whole application
    var init = function () {
        generateProductList();
        setupListeners();
        generateCartButtons();
    }

    // Exposes just init function to public, everything else is private
    return {
        init: init,
        products: products,
        generateProductList: generateProductList
    };
})();

ShoppingCart.init();