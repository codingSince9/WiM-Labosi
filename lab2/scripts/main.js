/*if (typeof(sessionStorage.numOfClicks) == "undefined") {
    document.getElementById('cart-items').innerHTML = 0;
    sessionStorage.numOfClicks = 0;
} else {
    document.getElementById('cart-items').innerHTML = sessionStorage.numOfClicks;
}*/

if (sessionStorage.getItem("itemsInCart") === null) {
    sessionStorage.setItem("itemsInCart", 0);
    document.getElementById('cart-items').innerHTML = 0;
} else {
    document.getElementById('cart-items').innerHTML = sessionStorage.getItem("itemsInCart");
}