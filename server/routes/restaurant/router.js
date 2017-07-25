let express = require('express');
let router = express.Router();
let manager = require('./manager');

router.route('/restaurant/:contentId').post(function(req,res){
    let contentId=req.param.contentId;
    let name=req.body.name;
    let phone=req.body.phone;
    let place=req.body.place;
    let manu=req.body.manu;

});

router.route('/restaurant/:contentId').get(function(req,res){
    let contentId=req.param.contentId;

});

router.route('/restaurant/:contentId').put(function(req,res){
    let contentId=req.param.contentId;
    let name=req.body.name;
    let phone=req.body.phone;
    let place=req.body.place;
    let manu=req.body.manu;

});

router.route('/restaurant/:contentId').delete(function(req,res){
    let contentId=req.param.contentId;

});

router.route('/restaurant/:areaCode').get(function(req,res){
    let areaCode=req.param.areaCode;
    http://jojoldu.tistory.com/163
});