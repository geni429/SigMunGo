let express = require('express');
let router = express.Router();
let manager = require('./manager');
let random = require('../../support/random');

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

router.route('/good/:comtentId').get(function (req, res) {
    let contentId = req.params.contentId;

    manager.getGood(contentId, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

router.route('/').get(function(req, res){
    
});

router.route('/restaurantInfo').get(function (req, res) {
    manager.getContentId(function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

router.route('/restaurant').post(function (req, res) {
    let contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let manu = JSON.parse(req.body.manu);
    let bool = true;

    while (bool) {
        contentId = random.randomString();
        bool = manager.checkId(contentId);
    }

    manager.addRestaurant(contentId, name, place, phone, manu, function (JSONResponse) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSONResponse);
        res.end();
    });
});

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

router.route('/restaurant/:contentId').put(function (req, res) {
    let contentId = req.params.contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let manu = req.body.manu;


});

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

router.route('/restaurant/:areaCode').get(function (req, res) {
    let areaCode = req.params.areaCode;


});

router.route('/post/:contentId').post(function (req, res) {

});

router.route('/post/:contentId').get(function (req, res) {

});

router.route('/post/:contentId').put(function (req, res) {

});

router.route('/post/:contentId').delete(function (req, res) {

});

module.exports = router;