"use strict";
const express = require('express');
const router = express.Router();
const manager = require('./manager');
const random = require('../../support/random');

//좋아요 +1
router.route('/restaurant/sympathy/:contentId').post(function (req, res) {
    let contentId = req.params.contentId;
    let id = req.body.id;

    manager.addSympathy(contentId, id, function (response) {
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

//좋아요 많은 5개 음식점 
router.route('/restaurant/mostofsympathy').get(function (req, res) {
    manager.getMostOfRestaurant(function (response) {
        if (!!response) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.send(JSON.stringify(response));
    });
});

//처음 시작할 때
router.route('/restaurant/base').get(function (req, res) {
    manager.getContentId(function (response) {
        if (!!response.contentId || !!response.name) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.send(JSON.stringify(response));
    });
});

//음식점 생성
router.route('/restaurant').post(function (req, res) {
    let contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let img = req.body.img;
    let bool = true;

    while (bool) {
        contentId = random.randomString(10);
        bool = manager.checkId(contentId);
    }

    manager.addRestaurant(contentId, name, place, phone, img, function (response) {
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

//음식점 대략 정보 받아오기
router.route('/restaurant').get(function (req, res) {
    manager.getRestaurant(function (response) {
        if (!!response.restaurant) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.send(JSON.stringify(response));
    });
});

//음식점 디테일 정보
router.route('/restaurant/detail/:contentId').get(function (req, res) {
    let contentId = req.params.contentId;

    manager.getDetailRestaurant(contentId, function (response) {
        if (asdf) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.send(JSON.stringify(response));
    });
});

//음식점 업데이트
router.route('/restaurant/:contentId').put(function (req, res) {
    let contentId = req.params.contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let img = req.body.img;


});

//음식점 삭제
router.route('/restaurant/:contentId').delete(function (req, res) {
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
        res.end();
    });
});

// //gps로 음식점 정보
// router.route('/restaurant/:areaCode').get(function (req, res) {
//     let areaCode = req.params.areaCode;


// });

//음식점 메뉴 추가
router.route('/restaurant/menu/:contentid').post(function (req, res) {
    let contentId = req.params.contentId;
    let menu = req.body.menu;

    manager.addMenu(contentId, menu, function (response) {
        if (response.success) {
            res.writeHead(200, {
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

//음식점 메뉴 요청
router.route('/restaurant/menu/:contentid').get(function (req, res) {
    let contentId = req.params.contentId;

    manager.getMenu(contentId, menu, function (response) {
        if (!!response.menu) {
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
        } else {
            res.writeHead(204, {
                'Content-Type': 'application/json'
            });
        }
        res.send(JSON.stringify(response));
    });
});

//음식점 메뉴 삭제
router.route('/restaurant/menu/:contentid').delete(function (req, res) {
    let contentId = req.params.contentId;
    let menu = req.body.menu;

    manager.deleteMenu(contentId, menu, function (response) {
        if (response.success) {
            res.writeHead(200, {
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