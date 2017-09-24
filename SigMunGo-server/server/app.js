"use strict";
let http = require('http');
let express = require('express');
let path = require('path');
let bodyParser = require('body-parser');
let cookieParser = require('cookie-parser');
let errorHandler = require('errorhandler');
let expressSession = require('express-session');
let app = express();
var restaurant = require('./routes/restaurant/router');
var mypage= require('./routes/mypage/router');
var user=require('./routes/user/router');
var port = '5429';

app.set('port', port);

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');


app.use(expressSession({
    secret: 'my key',
    resave: true,
    saveUninitialized: true
}));

app.use('/static', express.static(__dirname + '/public'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(cookieParser());

app.use('/', user);
app.use('/', restaurant);
app.use('/',mypage);

http.createServer(app).listen(app.get('port'), function () {
    console.log('Server started on ' + app.get('port') + 'port');
});