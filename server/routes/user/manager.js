let conn = require('../../DBConnection');
let AES256 = require('nodejs-aes256');
const key = 'this_is_key';

let manager = {}

//회원가입
manager.registe = function (id, password, name, phone, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('insert into account values(?,?,?,?);', [id, password, name, phone], function (err, result) {
        console.log(err);
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;

        callback(JSON.stringify(response));
    });
}

//로그인
manager.login = function (id, password, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('select * from account where id=? and password=?', [id, password], function (err, rows) {
        console.log(rows.length);
        if (err) response.error = true;
        else if (rows.length == 1) response.success = true;

        callback(response);
    });
}

//아이디 중복 체크
manager.checkId = function (id, callback) {
    let response = {
        error: false,
        overlap: false
    };
    conn.query('select * from account where id=?', id, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.overlap = true;

        callback(JSON.stringify(response));
    });
}

//아이디 중복 체크
manager.checkName = function (name, callback) {
    let response = {
        error: false,
        overlap: false
    };
    conn.query('select * from account where name=?', name, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.overlap = true;

        callback(JSON.stringify(response));
    });
}

//아이디 중복 체크
manager.checkPhone = function (phoneid, callback) {
    let response = {
        error: false,
        overlap: false
    };
    conn.query('select * from account where phone=?', phone, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.overlap = true;

        callback(JSON.stringify(response));
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

        callback(JSON.stringify(response));
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

        callback(JSON.stringify(response));
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
    });
}
module.exports = manager;