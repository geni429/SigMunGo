"use strict";
const express = require('express');
const app = express();
app.set('port', 5429);
app.set('view engine', 'jade');

const path = require('path');
app.set('views', path.join(__dirname, 'views'));

const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const errorHandler = require('errorhandler');
const expressSession = require('express-session');

const restaurant = require('./routes/restaurant/router');
const mypage = require('./routes/mypage/router');
const user = require('./routes/user/router');

app.use(expressSession({
    secret: 'my key',
    resave: true,
    saveUninitialized: true
}));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(cookieParser('@k,.q!!.asAzka!'));

app.use('/', user);
app.use('/', restaurant);
app.use('/', mypage);

app.listen(app.get('port'), function () {
    console.log('Server is listening on : ' + app.get('port'));
});
