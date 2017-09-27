"use strict";
let conn = require('../../DBConnection');

let manager = {};

//유저정보
manager.getUserInfo = (id, callback) => {
    let response = {
        name: null,
        id: id,
        discontents: null,
        sympathy: null,
        profile: null
    };

    conn.query('select * from account where id=?', id, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) {
            response.name = rows[0].name;
            response.profile = rows[0].profile;
            conn.query('select * from good where id=?;', id, function (err, rows) {
                if (err) response.error = true;
                else if (rows.length >= 0) {
                    response.sympathy = rows.length;
                    conn.query('select * from post where id=?;', id, function (err, rows) {});
                    response.discontents = 0;
                    callback(response);
                }
            });
        }
    });
};

//유저가 불만을 단 음식점
manager.getPostList = (id, callback) => {
    let response = {
        restaurant: []
    };

    let stateCode;
    conn.query('select * from post where id=?', id, function (err, rows) {
        if (err) stateCode = 500;
        else if (rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                (function(i){
                    conn.query('select * from restaurant where contentid=?', rows[i].contentid, function (err, rows2) {
                    if (err) stateCode = 500;
                    else if (rows2.length > 0) {
                        let restaurant = {
                            contentid: rows2[0].contentid,
                            img: rows2[0].img,
                            name: rows2[0].name,
                            place: rows2[0].place,
                            sympathy: rows2[0].good,
                            improved: rows2[0].improved,
                            discontent: rows2[0].length
                        }
                        response.restaurant.push(restaurant);
                        if (i == rows.length+1) {
                            stateCode = 200;
                            console.log(response);
                            callback(stateCode, response);
                        }
                    }
                })
            })(i);
            }
        } else if (rows.length == 0) {
            stateCode = 200;
            callback(stateCode, response);
        }
    });
};

//프로필 수정
manager.putProfile = (id, profile, callback) => {
    let response = {
        success: false
    };

    conn.query('update accout set profile=? where id=?', [profile, id], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows == 1) success = true;

        callback(response);
    });
};

//프로필 삭제
manager.deleteProfile = (id, callback) => {
    let response = {
        success: false
    };

    conn.query('update accout set profile=null where id=?', id, function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows == 1) success = true;

        callback(response);
    });
};

module.exports = manager;
