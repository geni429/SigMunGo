"use strict";
let express = require('express');
let router = express.Router();
let manager = require('./manager');

//유저 정보
router.route('/userinfo/:id').get(function (req, res) {
    let id = req.params.id;
    console.log(id);
    manager.getUserInfo(id, function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

//불만 단 음식점
router.route('/mypage/postlist/:id').get(function (req, res) {
    let id= req.params.id;

    manager.getPostList(id, function(stateCode, response){
        res.writeHead(stateCode, {
            'Content-Type': 'application/json'
        });
        if(!!response.restaurant) res.write(JSON.stringify(response));
        res.end();
    })    
});

//프로필 수정
router.route('/mypage/profile/:id/').put(function (req, res) {
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
});

//프로필 삭제
router.route('/mypage/profile/:id').delete(function (req, res) {
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