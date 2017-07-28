"use strict";
let http = require('http');
let express = require('express');
let path = require('path');
let bodyParser = require('body-parser');
let cookieParser = require('cookie-parser');
let errorHandler = require('errorhandler');
let expressSession = require('express-session');
let app = express();

let mysql = require('mysql');

var user = require('./routes/user/router');
var restaurant=require('./routes/restaurant/router');

var port = '3434';

app.set('port', port);

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');


app.use(expressSession({
    secret: 'my key',
    resave: true,
    saveUninitialized: true
}));

app.post('/account/login',function (req, res) {
    console.log(req.body.id);
    console.log('로그인');
    let id = req.body.id;
    let password = SHA256(req.body.password);

    console.log(id, password);
    manager.login(id, password, function (response) {
        console.log(response.success);
        if (response.success) {
            req.session.user = {
                id: id,
                authorized: true
            };
        }

        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());

app.use('/', user);
app.use('/', restaurant);


http.createServer(app).listen(app.get('port'), function () {
    console.log('Server started on ' + app.get('port') + 'port');
});
