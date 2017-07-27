let express = require('express');
let router = express.Router();
let manager = require('./manager');
let AES256 = require('nodejs-aes256');
let SHA256 = require('sha256');
let random = require('../../support/random');

router.route('/account/registe').post(function (req, res) {
    let id = AES256.encrypt(req.body.id);
    let name = req.body.name;
    let password = SHA256.encrypt(req.body.password);
    let phone = req.body.phone;

    console.log(id, password);

    manager.registe(id, password, name, phone, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

router.route('/account/login').post(function (req, res) {
    let id = AES256.encrypt(req.body.id);
    let password = SHA256.encrypt(req.body.password);

    manager.login(id, password, function (response) {
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

router.route('/account/logout').delete(function (req, res) {
    let response={
        success:false
    };

    req.session.destroy();

    if(req.session.user){
        res.writeHead(400, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    }else{
        response.success=true;

        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    }
});

router.route('/account/checkId').post(function (req, res) {
    let id = AES256.encrypt(req.body.id);

    manager.checkId(id, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

router.route('/account/id/:name/:phone').get(function (req, res) {
    let name = req.params.name;
    let phone = req.params.phone;

    manager.getId(name, phone, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

router.route('/account/password/:id').put(function (req, res) {
    let id = AES256.encrypt(req.params.id);
    let password = SHA256.encrypt(req.body.password);

    manager.updatePassword(id, password, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

module.exports = router;