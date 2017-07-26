let express = require('express');
let router = express.Router();
let manager = require('./manager');
let AES256 = require('../../secure/AES256');
let random = require('../../support/random');

/* GET home page. */
router.route('/account/registe').post(function (req, res) {
    let id = req.body.id;
    let name = req.body.name;
    let password = req.body.password;
    let phone = req.body.phone;

    manager.registe(id, password, name, phone, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

router.route('/account/login').post(function (req, res) {
    let id = req.body.id;
    let password = req.body.password;

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

router.route('/account/checkId').post(function (req, res) {
    let id = req.body.id;

    manager.checkId(id, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

// router.route('/account/id/') 아이디 찾기

router.route('/account/password/:id').put(function (req, res) {
    let id = req.params.id;
    let password = req.body.password;

    manager.updatePassword(id, password, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

module.exports = router;