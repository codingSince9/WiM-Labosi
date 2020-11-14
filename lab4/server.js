//uvoz modula
const express = require('express');
const app = express();
const path = require('path');
const pg = require('pg')
const db = require('./db')
const session = require('express-session')
const pgSession = require('connect-pg-simple')(session)

//uvoz modula s definiranom funkcionalnosti ruta
const homeRouter = require('./routes/home.routes');
const orderRouter = require('./routes/order.routes');
const loginRoute = require('./routes/login.routes');
const logoutRoute = require('./routes/logout.routes');
const signupRoute = require('./routes/signup.routes');
const cartRoute = require('./routes/cart.routes');
const userRoute = require('./routes/user.routes');
const checkoutRoute = require('./routes/checkout.routes');

//middleware - predlošci (ejs)
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

//middleware - statički resursi
app.use(express.static(path.join(__dirname, 'public')));

//middleware - dekodiranje parametara
app.use(express.urlencoded({ extended: true }));

//middleware - upravljanje sjednicama (+ trajne sjednice u bazi podataka)
//ZADATAK: postaviti express-sessions midlleware, trajnost sjedničkog kolačića 1 dan,
//pohrana sjednica u postgres bazu korštenjem connect-pg-simple modula
app.use(session({
    secret: 'wim-lab4',
    resave: false,
    cookie: {maxAge: 1000*60*60*24},
    store: new pgSession({
        pool: db.pool,
        tableName: 'session'
    }),
    saveUninitialized: true
  }))
  

//definicija ruta
app.use('/', homeRouter);
app.use('/order', orderRouter);
app.use('/login', loginRoute)
app.use('/logout', logoutRoute)
app.use('/signup', signupRoute)
app.use('/cart', cartRoute)
app.use('/user', userRoute)
app.use('/checkout', checkoutRoute)

//pokretanje poslužitelja na portu 7100
app.listen(7100);