"use strict";
let conn = require('../../DBConnection');
var Promise = require('promise');

let manager = {};

//좋아요 +1
manager.addSympathy = (contentId, id, callback) => {
    let response = {
        success: false
    }

    conn.query('insert into good values(?,?,1)', [contentId, id], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows == 1) response.success = true;
        callback(response);
    });
}

//좋아요 높은 5개 음식점 
manager.getMostOfRestaurant = (callback) => {
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
manager.getContentId = (callback) => {
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
manager.checkId = (contentId) => {
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
manager.getDetailRestaurant = (contentId, callback) => {
    let response = {
        name: null,
        place: null,
        phone: null,
        sympathy: null,
        img: null,
        menu: []
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

            conn.query('select * from menu where contentId=?', contentId, function (err, rows) {
                if (err) response.error = true;
                else if (rows.length >= 1) {
                    for (let i = 0; i < rows.length; i++) {
                        response.menu[i] = rows[i].menu;
                    }
                }
            });
        }
        callback(response);
    });
}

//음식점 정보 업데이트
manager.updateRestaurant = (contentId, name, place, phone, menu, callback) => {

}

//음식점 정보 삭제
manager.deleteRestaurant = (contentId, callback) => {
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
manager.getRestaurant = (callback) => {
    let response = {
        restaurant: []
    };

    conn.query('select * from restaurant', null, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (var i = 0; i < 50; i++) {
                conn.query('select * from post where contentid=?', rows[i].contentid, function (err, rows2) {});
                let restaurant = {
                    contentid: rows[i].contentid,
                    img: rows[i].img,
                    name: rows[i].name,
                    place: rows[i].place,
                    sympathy: rows[i].good,
                    improved: rows[i].improved,
                    discontent: 0
                }
                response.restaurant.push(restaurant);
                if (i == 49) {
                    console.log(response);
                    callback(response);
                }
            }
        }
    });

}

//음식점 메뉴 추가
manager.addMenu = (contentId, menu, callback) => {
    let response = {
        success: false
    };

    conn.query('insert into menu value(?,?);', [contentId, menu], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;
        callback(response);
    });
}

//음식점 메뉴 요청
manager.getMenu = (contentId, callback) => {
    let response = {
        menu: []
    };

    conn.query('select * from menu where contentid=?;', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (var i = 0; i < rows.length; i++) {
                response.menu.push(rows[i].menu);
            }
        }

        callback(response);
    });
}

//음식점 메뉴 삭제
manager.deleteMenu = (contentId, menu) => {
    let response = {
        success: false
    };

    conn.query('delete from menu where contentid=? and menu=?;', [contentId, menu], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;
        callback(response);
    });
}

//불만 작성
manager.addPost = (contentId, post, callback) => {
    let addPostLogic = (post) => {
        return new Promise(function (resolve, reject) {
            let stateCode;
            conn.query('insert into post values (?,?);', [contentId, post], function (err, result) {
                if (err) stateCode = 400;
                else if (result.affectedRows) stateCode = 200;
                resolve(stateCode);
            });
        });
    }

    let addPostPromise=addPostLogic(post);
    addPostPromise.then(function (stateCode) {
        callback(stateCode);
    })
}

//불만 개수
manager.getCountOfPost = (contentId, callback) => {
    let count = 0;
    let getCountOfPostLogic = (contentId) => {
        return new Promise(function (resolve, reject) {
            conn.query('select count(*) count from post where contentid=?;', [contentId], function (err, rows) {
                if (err) stateCode = 500;
                else if (rows.length == 1) {
                    stateCode = 201;
                    count = rows[0].count;
                } else stateCode = 204;
                resolve(stateCode, count);
            });
        });
    }
    let getCountOfPost=getCountOfPostLogic(contentId);
    getCountOfPost.then(function (stateCode, count) {
        callback(stateCode, count);
    });
}

//불만 수정
manager.updatePost = (callback) => {

}

//불만 삭제
manager.deletePost = (contentId, callback) => {

}

manager.getRestaurantImg = (contentid, callback) => {
    let images = [];

    let getImagesLogic = (contentId) => {
        return new Promise(function (resolve, reject) {
            let stateCode;
            conn.query('select * from restaurant where contentid=?;', [contentId], function (err, rows) {
                if (err) stateCode = 500;
                else if (rows.length >= 1) stateCode = 201;
                else stateCode = 204;
                resolve(stateCode, rows);
            });
        });
    }

    let getImages=getImagesLogic();
    getImages.then(function (stateCode, rows) {
        if (rows >= 1) for(let i=0; i<rows.length;i++) images.push(rows[i]);
        callback(stateCode, images);
    });

}

//음식점 검색
manager.restaurantSearch = (search_word, callback) => {
    let response = {
        restaurant: []
    };
    conn.query("select * from restaurant where name like '%" + search_word + "%';", null, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) {
            for (var i = 0; i < rows.length; i++) {
                let restaurant = {
                    name: rows[i].name,
                    contentid: rows[i].contentid
                };
                response.restaurant.push(restaurant);
            }
        }
        callback(response);
    });
}

module.exports = manager;