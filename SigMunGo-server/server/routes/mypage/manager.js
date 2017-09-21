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
    conn.query('select * from post where id=?', id, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {}
    });
    conn.query('select * from restaurant ', null, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (var i = 0; i < 50; i++) {
                conn.query('select * from post where contentid=?', rows[i].contentid, function (err, rows2) {
                    let restaurant = {
                        contentid: rows[i].contentid,
                        img: rows[i].img,
                        name: rows[i].name,
                        place: rows[i].place,
                        sympathy: rows[i].good,
                        improved: rows[i].improved,
                        discontent: rows2[i].length
                    }
                    response.restaurant.push(restaurant);
                });
            }
        }
        callback(response);
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