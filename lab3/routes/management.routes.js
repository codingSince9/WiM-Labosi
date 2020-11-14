var express = require('express');
var router = express.Router();
const db = require('../db');
const {
    check,
    body,
    validationResult
} = require('express-validator');

router.get('/', async function (req, res, next) {
    res.render('management', {
        title: 'Management',
        linkActive: 'management',
    });
});

router.get('/updateitem', async function (req, res, next) {

    const [categories, items] = (await Promise.all([
        db.query('SELECT * FROM categories'),
        db.query('SELECT * FROM inventory'),
    ])).map(result => result.rows);

    //const categories = await db.query('SELECT * FROM categories');
    //const items = await db.query('SELECT * FROM inventory');

    console.log(items.rows)
    console.log(categories.rows)
    res.render('vas_zadatak', {
        title: "Update item",
        linkActive: 'updateitem',

        itemsSelect: {
            name: "item",
            list: items.map(item => ({
                name: item.name,
                value: item.id,
            })),
            selected: ""
        },
        categoriesSelect: {
            name: "category",
            list: categories.map(category => ({
                name: category.name,
                value: category.id,
            })),
            selected: ""
        }
    });
})

router.post('/updateitem', [
    body('name').not().isEmpty().trim().isLength({
        min: 3,
        max: 20
    }),
    body('price').not().isEmpty().trim().toInt()
    .custom(value => {
        return value >= 0 && value <= 99999
    }), //.withMessage('Error invalid value occured on parameter price'),
    body('url').not().isEmpty().isURL(),

], async function (req, res, next) {
    const errors = validationResult(req);

    if (!errors.isEmpty()) {
        res.render('error', {
            title: 'Update Item',
            linkActive: 'management',
            errors: errors.array(),
        });
    } else {
        try {
            let item = req.body.item;
            let itemName = req.body.name;
            let itemPrice = req.body.price;
            let itemUrl = req.body.url;
            let changeCat = req.body.category;
            //let catId = await db.query('SELECT id FROM categories where name=' + changeCat);
            //let names = await db.query('SELECT name FROM inventory where');
            /*if (name.isEmpty() || description.isEmpty()){
                throw 'Parameter must not be empty';
            }*/
            console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            console.log(itemName);
            console.log(itemPrice);
            console.log(itemUrl);
            console.log(item);
            console.log(changeCat + "  aaa");

            /*for (let name of names.rows) {
                if (itemName == name) {
                    throw "duplicate key value violates unique constraint 'inventory_name_key'";
                }
            }*/

            await db.query(
                'UPDATE inventory SET name = $1, price = $2, categoryid = $3, imageurl = $4 WHERE id = $5',
                [
                    req.body.name,
                    req.body.price,
                    req.body.category,
                    req.body.url,
                    req.body.item,
                ],
            );
            //let upit = "UPDATE inventory (id, name, price, categoryid, imageurl) SET" + "id=" + catId + "', 'name=" + name + "', 'price=" + price + "', 'url=" + url + "WHERE name=" + pickitem;
            //let upit = "UPDATE inventory (id, name, price, categoryid, imageurl) SET id=${catid}, name=${itemName}, price=${itemPrice}, url=${itemUrl} WHERE name=${pickitem}"
            //await db.query(upit);
            res.redirect('/management');
        } catch (err) {
            /*router.get('/', async function(req, res, next) {
                res.render('management', {
                    title: 'Management',
                    linkActive: 'management',
                });
            });*/
            res.render('error', {
                title: "Error update item",
                linkActive: "error",
                errDB: err,
                errors: JSON.stringify(err)
            });
        }
    }
});

module.exports = router;