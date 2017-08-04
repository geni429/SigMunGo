"use strict";
let express = require('express');
let router = express.Router();
let manager = require('./manager');

router.route('/userinfo').get(function (req, res) {
    manager.sessionCheck(req, res);
    let id = req.session.user.id;
    manager.getUserInfo(id, function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });

});

router.route('/mypage/postlist').get(function (req, res) {

});

router.route('/mypage/profile').put(function (req, res) {
    manager.sessionCheck(req, res);

    let id = req.session.user.id;

    manager.deleteProfile(id, profile, function (response) {
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

router.route('/mypage/profile').delete(function (req, res) {
    manager.sessionCheck(req, res);

    let id = req.session.user.id;

    manager.deleteProfile(id, function (response) {
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

module.exports = router;

//이름 id 불만 작성수 공감단 횟수 프로필 이미지