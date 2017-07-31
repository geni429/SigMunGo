"use strict";
let express = require('express');
let router = express.Router();
let manager = require('./manager');
let random = require('../../support/random');

//좋아요 +1
router.route('/good/:contentId').post(function (req, res) {
    manager.sessionCheck(req,res);
    let contentId = req.params.contentId;
    let id = req.session.user.id;

    manager.addGood(contentId,id, function (response) {
        if (response.success) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end();
    });
});

//좋아요 get
router.route('/good/:contentId').get(function (req, res) {
    manager.sessionCheck(req,res);
    let contentId = req.params.contentId;
    let id = req.session.user.id;

    manager.getGood(contentId,id, function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

//좋아요 많은 5개 음식점 
router.route('/MostOfRestaurant').get(function (req, res) {
    manager.getMostOfRestaurant(function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

//처음 시작할 때
router.route('/restaurantInfo').get(function (req, res) {
    manager.getContentId(function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

//음식점 생성
router.route('/restaurant').post(function (req, res) {
    manager.sessionCheck(req,res);

    let contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let img = req.body.img;
    let bool = true;

    while (bool) {
        contentId = random.randomString();
        bool = manager.checkId(contentId);
    }

    manager.addRestaurant(contentId, name, place, phone, img, function (response) {
        if (response.success) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end();
    });
});

router.route('/restaurant').get(function(req,res){
    manager.getRestaurant(function(response){
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

//음식점 디테일 정보
router.route('/restaurant/detail/:contentId').get(function (req, res) {
    let contentId = req.params.contentId;

    console.log(contentId);
    manager.getDetailRestaurant(contentId, function (response) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify(response));
        res.end();
    });
});

//음식점 업데이트
router.route('/restaurant/:contentId').put(function (req, res) {
    manager.sessionCheck(req);
    let contentId = req.params.contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let img = req.body.img;


});

//음식점 삭제
router.route('/restaurant/:contentId').delete(function (req, res) {
    manager.sessionCheck(req);

    let contentId = req.params.contentId;

    manager.deleteRestaurant(contentId, function (response) {
        if (response.success) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end();
    });
});

// //gps로 음식점 정보
// router.route('/restaurant/:areaCode').get(function (req, res) {
//     let areaCode = req.params.areaCode;


// });

//음식점 메뉴 추가
router.route('/restaurant/manu/:contentid').post(function(req, res){
    let contentId = req.params.contentId;
    let manu=req.body.manu;

    manager.addManu(contentId, manu, function(response){
       if (response.success) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end(); 
    });
});

//음식점 메뉴 요청
router.route('/restaurant/manu/:contentid').get(function(req, res){
    let contentId = req.params.contentId;

    manager.getManu(contentId, manu, function(response){
        res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        res.write(JSON.stringify(response));
        res.end(); 
    });
});

//음식점 메뉴 삭제
router.route('/restaurant/manu/:contentid').delete(function(req, res){
    let contentId = req.params.contentId;
    let manu=req.body.manu;

    manager.deleteManu(contentId, manu, function(response){
        if (response.success) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end(); 
    });
});

//음식점 메뉴 수정
router.route('/restaurant/manu/:contentid').put(function(req, res){
    let contentId = req.params.contentId;

    manager.updateManu(contentId, manu, function(response){
        if (response.success) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(400, {
                'Content-Type': 'application/json'
            });
        }
        res.write(JSON.stringify(response));
        res.end(); 
    }); 
});

//불만 작성
router.route('/post/:contentId').post(function (req, res) {

});

//불만 개수 
router.route('/post/:contentId').get(function (req, res) {

});

//불만 수정
router.route('/post/:contentId').put(function (req, res) {

});

//불만 삭제
router.route('/post/:contentId').delete(function (req, res) {

});

module.exports = router;