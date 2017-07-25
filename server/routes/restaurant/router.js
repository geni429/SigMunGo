let express = require('express');
let router = express.Router();
let manager = require('./manager');
let randomString = require('../../support/randomString');

router.route('/restaurant').post(function (req, res) {
    let contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let manu = req.body.manu;
    let bool = true;
    
    while (bool) {
        contentId = randomString.random();
        if (manager.checkId(contentId)) bool = false;
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
    let contentId = req.param.contentId;

});

router.route('/restaurant/:contentId').put(function (req, res) {
    let contentId = req.param.contentId;
    let name = req.body.name;
    let place = req.body.place;
    let phone = req.body.phone;
    let manu = req.body.manu;

});

router.route('/restaurant/:contentId').delete(function (req, res) {
    let contentId = req.param.contentId;

});

router.route('/restaurant/:areaCode').get(function (req, res) {
    let areaCode = req.param.areaCode;

});