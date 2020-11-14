const express = require('express');
const router = express.Router();
const cart = require('../models/CartModel')
const db = require('../db');


//ZADATAK prikaz košarice uz pomoć cart.ejs
router.get('/', async function (req, res, next) {
    if(req.session.cart === undefined){
        req.session.cart=cart.createCart();
        req.session.cart.invalid=false;
    }
    if(req.session.cart.invalid == true){
        req.session.cart=cart.createCart();
    }
    res.render('cart', {
        title: 'Cart',
        linkActive: 'cart',
        cart: req.session.cart,
        user: req.session.user
    })
});

//ZADATAK: dodavanje jednog artikla u košaricu
router.get('/add/:id', async function (req, res, next) {
    if (req.session.cart.invalid == true){
        req.session.cart=cart.createCart();
    }
    await cart.addItemToCart(req.session.cart, req.params.id, 1);
    res.end()
});

//ZADATAK: brisanje jednog artikla iz košarice
router.get('/remove/:id', async function (req, res, next) {
    await cart.removeItemFromCart(req.session.cart, req.params.id, 1);
    res.end()
});

//brisanje određenog item-a iz košarice
router.get('/removeall/:id', async function (req, res, next) {
    await cart.removeAllSelectedFromCart(req.session.cart, req.params.id);
    res.end();
});

module.exports = router;