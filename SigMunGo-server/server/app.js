"use strict";
let express = require('express');
let app = express();

let path = require('path');
let bodyParser = require('body-parser');
let cookieParser = require('cookie-parser');
let errorHandler = require('errorhandler');
let expressSession = require('express-session');

var restaurant = require('./routes/restaurant/router');
var mypage = require('./routes/mypage/router');
var user = require('./routes/user/router');
var port = '5429';

app.set('port', port);

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

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
    console.log('Server started on ' + app.get('port') + 'port');
});
