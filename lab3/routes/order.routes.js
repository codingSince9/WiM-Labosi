var express = require('express');
var router = express.Router();
const db = require('../db');

router.get('/', async function(req, res, next) {
    const cat = await db.query('SELECT * FROM categories');
    const it = await db.query('SELECT * FROM inventory');
    res.render('order', {
        title: 'Order',
        linkActive: 'order',
        categories: cat.rows,
        items: it.rows
    });
});

module.exports = router;