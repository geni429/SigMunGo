let express = require('express');
let router = express.Router();
let manager = require('./manager');
let random = require('../../support/random');

//좋아요 +1
router.route('/good/:contentId').post(function (req, res) {
    let contentId = req.params.contentId;

    manager.addGood(contentId, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//좋아요 get
router.route('/good/:contentId').get(function (req, res) {
    let contentId = req.params.contentId;
    console.log(contentId);

    console.log(contentId);
    manager.getGood(contentId, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//좋아요 많은 5개 음식점 
router.route('/MostOfRestaurant').get(function (req, res) {
    manager.getMostOfRestaurant(function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//처음 시작할 때
router.route('/restaurantInfo').get(function (req, res) {
    manager.getContentId(function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//음식점 생성
router.route('/restaurant').post(function (req, res) {
    let contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let img = req.body.img;
    let manu = JSON.parse(req.body.manu);
    let bool = true;

    while (bool) {
        contentId = random.randomString();
        bool = manager.checkId(contentId);
    }

    manager.addRestaurant(contentId, name, place, phone, manu, img, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//음식점 디테일 정보
router.route('/restaurant/:contentId').get(function (req, res) {
    let contentId = req.params.contentId;

    console.log(contentId);
    manager.getDetailRestaurant(contentId, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//음식점 업데이트
router.route('/restaurant/:contentId').put(function (req, res) {
    let contentId = req.params.contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let manu = req.body.manu;
    let img = req.body.img;


});

//음식점 삭제
router.route('/restaurant/:contentId').delete(function (req, res) {
    let contentId = req.params.contentId;

    manager.deleteRestaurant(contentId, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

//gps로 음식점 정보
router.route('/restaurant/:areaCode').get(function (req, res) {
    let areaCode = req.params.areaCode;


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