"use strict";
let conn = require('../../DBConnection');

let manager = {}

//세션 체크
manager.sessionCheck = function (req, res) {
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
}

//좋아요 +1
manager.addSympathy = function (contentId, id, callback) {
    let response = {
        success: false
    }

    conn.query('insert into good values(?,?,1)', [contentId, id], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows == 1) response.success = true;
        callback(response);
    });
}

//좋아요 받아오기
manager.getSympathy = function (contentId, id, callback) {
    let response = {
        sympathy: null
    };

    conn.query('select * from good where contentid=? and id=?;', [contentId, id], function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) response.sympathy = rows.length;
        callback(response);
    });
}

//좋아요 높은 5개 음식점 
manager.getMostOfRestaurant = function (callback) {
    let response = {
        0: {
            img: null,
            name: null,
            place: null,
            sympathy: null,
        },
        1: {
            img: null,
            name: null,
            place: null,
            sympathy: null
        },
        2: {
            img: null,
            name: null,
            place: null,
            sympathy: null
        },
        3: {
            img: null,
            name: null,
            place: null,
            sympathy: null
        },
        4: {
            img: null,
            name: null,
            place: null,
            sympathy: null
        }
    };

    conn.query('select * from restaurant where improved=1 order by good', null, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            console.log(rows.length);
            for (let i = 0; i < 5; i++) {
                response[i].img = rows[i] ? rows[i].img : null;
                response[i].name = rows[i] ? rows[i].name : null;
                response[i].place = rows[i] ? rows[i].place : null;
                response[i].sympathy = rows[i] ? rows[i].good : null;
            }
        }
        callback(response);
    });
}

//앱 시작할 때, contentid, name
manager.getContentId = function (callback) {
    let response = {
        contentId: [],
        name: []
    };

    conn.query('select contentid,name from restaurant;', null, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (let i = 0; i < rows.length; i++) {
                response.contentId.push(rows[i].contentid);
                response.name.push(rows[i].name);
            }
        }
        callback(response);
    });
}

//contentid 중복 체크
manager.checkId = function (contentId) {
    conn.query('select * from restaurant where contentid=?;', contentId, function (err, rows) {
        if (rows.length == 1) return true;
        else if (rows.length == 0) return false;
    });
}

//음식점 추가 
manager.addRestaurant = function (contentId, name, place, phone, img, callback) {
    let response = {
        success: false
    };

    conn.query('insert into restaurant value(?,?,?,?,?);', [contentId, name, place, phone, img], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;
        callback(response);
    });
}

//음식점 디테일 정보
manager.getDetailRestaurant = function (contentId, callback) {
    let response = {
        name: null,
        place: null,
        phone: null,
        sympathy: null,
        img: null,
        manu: []
    };

    conn.query('select * from restaurant where contentId=?', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) {
            response.name = rows[0].name;
            response.place = rows[0].place;
            response.phone = rows[0].phone;
            response.img = rows[0].img;

            conn.query('select * from good where contentid=?;', contentId, function (err, rows) {
                if (err) response.error = true;
                else if (rows.length >= 0) response.sympathy = rows.length;
            });

            conn.query('select * from manu where contentId=?', contentId, function (err, rows) {
                if (err) response.error = true;
                else if (rows.length >= 1) {
                    for (let i = 0; i < rows.length; i++) {
                        response.manu[i] = rows[i].manu;
                    }
                }
            });
        }
        callback(response);
    });
}

//음식점 정보 업데이트
manager.updateRestaurant = function (contentId, name, place, phone, manu, callback) {

}

//음식점 정보 삭제
manager.deleteRestaurant = function (contentId, callback) {
    let response = {
        success: false
    };

    conn.query('delete from restaurant where contentid=?', contentId, function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows == 1) response.success = true;

        callback(response);
    });
}

//음식점 대략 정보
manager.getRestaurant = function (callback) {
    let response = {
        restaurant: []
    };

    conn.query('select * from restaurant', null, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (var i = 0; i < 50; i++) {
                let restaurant = {
                    contentid: rows[i].contentid,
                    img: rows[i].img,
                    name: rows[i].name,
                    place: rows[i].place,
                    sympathy: rows[i].good,
                    improved: rows[i].improved
                }
                response.restaurant.push(restaurant);
            }
        }
        callback(response);
    });
}

//음식점 메뉴 추가
manager.addManu = function (contentId, manu, callback) {
    let response = {
        success: false
    };

    conn.query('insert into manu value(?,?);', [contentId, manu], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;
        callback(response);
    });
}

//음식점 메뉴 요청
manager.getManu = function (contentId, callback) {
    let response = {
        manu: []
    };

    conn.query('select * from manu where contentid=?;', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (var i = 0; i < rows.length; i++) {
                response.manu.push(rows[i].manu);
            }
        }

        callback(response);
    });
}

//음식점 메뉴 삭제
manager.deleteManu = function (contentId, manu) {
    let response = {
        success: false
    };

    conn.query('delete from manu where contentid=? and manu=?;', [contentId, manu], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;
        callback(response);
    });
}

//음식점 메뉴 수정
manager.updateManu = function () {

}

//불만 작성
manager.addPost = function () {

}

//불만 개수
manager.getCountOfPost = function (contentId, callback) {

}

//불만 수정
manager.updatePost = function (callback) {

}

manager.deletePost = function (contentId, callback) {

}

module.exports = manager;