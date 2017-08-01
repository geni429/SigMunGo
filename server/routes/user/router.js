"use strict";

let express = require('express');
let router = express.Router();
let manager = require('./manager');
let AES256 = require('nodejs-aes256');
let SHA256 = require('sha256');
let random = require('../../support/random');
const key = 'this_is_key';

//회원가입
router.route('/account/signup').post(function (req, res) {
    let id = req.body.id;
    let name = req.body.name;
    let password = SHA256(req.body.password);
    let phone = req.body.phone;
    console.log(id, name, password, phone);

    manager.signup(id, password, name, phone, function (response) {
        if (response.success) {
            res.writeHead(201, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.end();
    });
});

//로그인
router.route('/account/signin').post(function (req, res) {
    let id = req.body.id;
    let password = SHA256(req.body.password);

    manager.signin(id, password, function (response, message) {
        if (response.success) {
            req.session.user = {
                id: id,
                authorized: true
            };
        }
        console.log(response, message);

        if (response.success) {
            res.writeHead(201, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(message));
        res.end();
    });
});

//로그아웃
router.route('/account/signout').delete(function (req, res) {
    let response = {
        success: false
    };

    req.session.destroy();

    if (req.session) {
        res.writeHead(400, {
            'Content-Type': 'application/json'
        });
        res.end();
    } else {
        response.success = true;

        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.end();
    }
});

//아이디 중복 체크
router.route('/account/idcheck').post(function (req, res) {
    let id = req.body.id;

    manager.checkId(id, function (response) {
        if (response.overlap) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.end();
    });
});

//이름 중복 체크
router.route('/account/nameCheck').post(function (req, res) {
    let name = req.body.name;

    manager.checkName(name, function (response) {
        if (response.overlap) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.end();
    });
});

//전화번호 중복 체크
router.route('/account/phonecheck').post(function (req, res) {
    let phone = req.body.phone;

    manager.checkPhone(phone, function (response) {
        if (response.overlap) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.end();
    });
});

//아이디 찾기
router.route('/account/id').get(function (req, res) {
    let name = req.body.name;
    let phone = req.body.phone;

    manager.getId(name, phone, function (response) {
        if (!!response.id) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }

        res.write(JSON.stringify(response));
        res.end();
    });
});

//비밀번호 변경
router.route('/account/password').put(function (req, res) {
    let id = req.params.id;
    let password = SHA256(req.body.password);

    manager.updatePassword(id, password, function (response) {
        if (response.success) {
            res.writeHead(201, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.end();
    });
});

//사용자가 누른 좋아요 개수
router.route('/account/sympathy').get(function (req, res) {
    manager.sessionCheck(req, res);
    let id = req.session.user.id;

    manager.sympathyCounts(id, function (response) {
        if (!!response.counts) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end();
    });
});

module.exports = router;