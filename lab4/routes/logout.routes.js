const express = require('express');
const router = express.Router();

//ZADATAK:
// - obrisati sadržaj košarice
// - odjaviti registriranog korisnika iz sustava
// - napraviti redirect na osnovnu stranicu
router.get('/', function (req, res, next) {
    req.session.cart.invalid=true;
    req.session.user=undefined;
    res.redirect('/');
});

module.exports = router;