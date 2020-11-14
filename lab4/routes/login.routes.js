const express = require('express');
const router = express.Router();
const User = require('../models/UserModel')

//ZADATAK: vrati login stranicu
router.get('/', function (req, res, next) {
    res.render('login', {
        title: 'Login',
        linkActive: 'login',
        user: req.session.user,
        err: req.session.err
    })
});

//ZADATAK: postupak prijave korisnika
router.post('/', async function (req, res, next) {

    if(req.session.user !== undefined) {
        res.render('login', {
            title: 'login',
            linkActive: 'login',
            user: req.session.user,
            err: "Please log out first."
        });
        return
    }

    let user = await User.fetchByUsername(req.body.user);
    if(user.isPersisted() && user.checkPassword(req.body.password)) {
        req.session.user = user
        res.redirect('/');
    } else {
        res.render('login', {
            title: 'login',
            linkActive: 'login',
            user: req.session.user,
            err: "Incorrect username or password."
        });
    }
});

module.exports = router;