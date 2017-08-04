"use strict";
let conn = require('../../DBConnection');

let manager={};
//세션 체크
manager.sessionCheck = function (req, res) {
    console.log(req.session);
    if (!req.session.user) {
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.write(JSON.stringify({
            session: false
        }));
        res.end();
        return;
    } else {
        return;
    }
};

manager.getUserInfo = function (id, callback) {
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
                    conn.query('select * from post where id=?;', id, function (err, rows) {
                        if (err) response.error = true;
                        else if (rows.length >= 0) response.discontents = rows.length;
                        callback(response);
                    });
                }
            });
        }
    }); 
};

manager.getPostList = function (id, callback) {
    let response = {
        restaurant: []
    };
    conn.query('select * from post where id=?',id,function(err,rows){
        if(err) response.error=true;
        else if(rows.length>=0){
        } 
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

manager.putProfile = function (id, profile, callback) {
    let response = {
        success: false
    };

    conn.query('update accout set profile=? where id=?', [profile, id], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows == 1) success = true;

        callback(response);
    });
};

manager.deleteProfile = function (id, callback) {
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