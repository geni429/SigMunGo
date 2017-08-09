"use strict";
const express = require('express');
const router = express.Router();
const manager = require('./manager');

router.route('/userinfo/:id').get(function (req, res) {
    let id = req.params.id;

    manager.getUserInfo(id, function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.send(JSON.stringify(response));
    });
});

router.route('/mypage/postlist').get(function (req, res) {

});

router.route('/mypage/profile/:id').put(function (req, res) {
    let id = req.params.id;
    let profile = req.body.profile;

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
}).delete(function (req, res) {
    let id = req.params.id;

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