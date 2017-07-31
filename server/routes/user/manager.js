"use strict";
let conn = require('../../DBConnection');
let AES256 = require('nodejs-aes256');
const key = 'this_is_key';

let manager = {}

//회원가입
manager.signup = function (id, password, name, phone, callback) {
    let response = {
        error: false,
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
        error: false
    };

    conn.query('select * from account where id=?', id, function (err, rows) {
        if (err) {
            response.error = true;
            callback(response);
        }
        else if (rows.length == 1) {
            conn.query('select * from account where id=? and password=?;', [id, password], function (err, rows1) {
                if (err) {
                    response.error = true;
                    callback(response);
                } else if (rows1.length == 1) {
                    response.success = true;
                    callback(response);
                } else if (rows1.length) {
                    response.message = 'worngPassword';
                    callback(response);
                }
            });
        } else {
            response.message = 'nonexistentId';
            callback(response);
        }
    });
}

//아이디 중복 체크
manager.idCheck = function (id, callback) {
    let response = {
        error: false,
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
        error: false,
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
        error: false,
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
        error: false,
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
        error: false,
        id: null
    };

    conn.query('select id from account where name=? and phone=?;', [name, phone], function (err, rows) {
        console.log(rows);
        if (err) response.error = true;
        else if (rows.length == 1) response.id = rows[0].id;

        callback(response);
    });
}

//사용자가 누른 좋아요 갯수
manager.goodCounts = function (id, callback) {
    let response = {
        error: false,
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