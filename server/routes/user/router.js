let express = require('express');
let router = express.Router();
let manager = require('./manager');
let AES256 = require('nodejs-aes256');
let SHA256 = require('sha256');
let random = require('../../support/random');
const key='this_is_key';

//회원가입
router.route('/account/registe').post(function (req, res) {
    let id = AES256.encrypt(key,req.body.id);
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

//로그인
router.route('/account/login').post(function (req, res) {
    let id = AES256.encrypt(key,req.body.id);
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

//로그아웃
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

//아이디 중복 체크
router.route('/account/checkId').post(function (req, res) {
    let id = AES256.encrypt(key,req.body.id);

    manager.checkId(id, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//아이디 찾기
router.route('/account/id').post(function (req, res) {
    let name = req.body.name;
    let phone = req.body.phone;

    manager.getId(name, phone, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//비밀번호 변경
router.route('/account/password').put(function (req, res) {
    let id = AES256.encrypt(key,req.params.id);
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