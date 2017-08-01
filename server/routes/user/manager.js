"use strict";
let conn = require('../../DBConnection');
let AES256 = require('nodejs-aes256');
const key = 'this_is_key';

let manager = {}

//회원가입
manager.signup = function (id, password, name, phone, callback) {
    let response = {
        success: false
    };

    conn.query('insert into account values(?,?,?,?);', [id, password, name, phone], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;

        callback(response);
    });
}

//로그인
manager.signin = function (id, password, callback) {
    let response = {
        success: false
    };
    let message = {};

    conn.query('select * from account where id=?', id, function (err, rows) {
        if (err) {
            response.error = true;
            callback(response, message);
        } else if (rows.length == 1) {
            conn.query('select * from account where id=? and password=?;', [id, password], function (err, rows1) {
                if (err) {
                    callback(response, message);
                } else if (rows1.length == 1) {
                    response.success = true;
                    callback(response, message);
                } else if (rows1.length == 0) {
                    message.message = 'worngPassword';
                    callback(response, message);
                }
            });
        }
        callback(response, message)
    });
}

//아이디 중복 체크
manager.idCheck = function (id, callback) {
    let response = {
        overlap: false
    };
    conn.query('select * from account where id=?', id, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.overlap = true;

        callback(response);
    });
}

//이름 중복 체크
manager.nameCheck = function (name, callback) {
    let response = {
        overlap: false
    };
    conn.query('select * from account where name=?', name, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.overlap = true;

        callback(response);
    });
}

//전화번호 중복 체크
manager.phonecheck = function (phone, callback) {
    let response = {
        overlap: false
    };
    conn.query('select * from account where phone=?', phone, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.overlap = true;

        callback(response);
    });
}

//비밀번호 변경
manager.updatePassword = function (id, callback) {
    let response = {
        success: false
    };

    conn.query('update account set password=? where id=?;', [id, password], function (err, result) {
        if (err) response.error = true;
        else if (reslt.affectedRows) response.success = true;

        callback(response);
    });
}

//아이디 찾기
manager.getId = function (name, phone, callback) {
    let response = {
        id: null
    };

    conn.query('select id from account where name=? and phone=?;', [name, phone], function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.id = rows[0].id;

        callback(response);
    });
}

//사용자가 누른 좋아요 갯수
manager.goodCounts = function (id, callback) {
    let response = {
        counts: null
    };

    conn.query('select * from good where id=?', id, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length >= 0) response.counts = rows.length;
        callback(response);
    });
}

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
module.exports = manager;