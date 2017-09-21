"use strict";
let conn = require('../../DBConnection');
let AES256 = require('nodejs-aes256');
let Promise = require('promise');
const key = 'this_is_key';

let manager = {}

//회원가입
manager.signup = (id, password, name, phone, callback) => {
    let signupPromise = (id, password, name, phone) => {
        return new Promise(function (resolve, reject) {
            let stateCode;
            conn.query('insert into account (id, password, name, phone) values(?,?,?,?);', [id, password, name, phone], function (err, result) {
                if (err) stateCode = 400;
                else if (result.affectedRows) stateCode = 200;
                resolve(stateCode);
            });
        });
    }
    signupPromise.then(function (stateCode) {
        callback(stateCode);
    })
}

//로그인
manager.signin = (id, password, callback) => {
    let message = {};

    let loginPromise = () => {
        return new Promise(function (resolve, reject) {
            conn.query('select * from account where id=?', id, function (err, rows) {
                let stateCode;
                if (err) stateCode = 500;
                else if (rows.length == 1) resolve(stateCode, reject);
                stateCode = 400;
                message.message = 'nonexistentId';
                callback(message, stateCode);
            });
        });
    }

    loginPromise.then(function (message, stateCode) {
        conn.query('select * from account where id=? and password=?;', [id, password], function (err, rows1) {
            if (err) stateCode = 500;
            else if (rows1.length == 1) stateCode = 201;
            else if (rows1.length == 0) {
                stateCode = 400;
                message.message = 'wrongPassword';
            }
            callback(stateCode, message);
        });
    })
}

//아이디 중복 체크
manager.idCheck = (id, callback) => {
    let idCheck = () => {
        return new Promise(function (resolve, reject) {
            let stateCode;
            conn.query('select * from account where id=?', id, function (err, rows) {
                if (err) stateCode = 500;
                else if (rows.length == 1) stateCode = 200;
                else if (rows.length == 0) stateCode = 204;
                resolve(stateCode);
            });
        });
    }

    idCheck.then(function (stateCode) {
        callback(stateCode);
    });
}

//전화번호 중복 체크
manager.phonecheck = (phone, callback) => {
    let poneCheck = () => {
        return new Promise(function (resolve, reject) {
            let stateCode;
            conn.query('select * from account where phone=?', phone, function (err, rows) {
                if (err) stateCode = 500;
                else if (rows.length == 1) stateCode = 200;
                else if (rows.length == 0) stateCode = 204;
                resolve(stateCode);
            });
        });
    }

    phoneCheck.then(function (stateCode) {
        callback(stateCode);
    });
}

//비밀번호 변경
manager.updatePassword = (id, callback) => {
    let modifyPassword = () => {
        return new Promise(function (resolve, reject) {
            let stateCode;
            conn.query('update account set password=? where id=?;', [id, password], function (err, result) {
                if (err) stateCode = 500;
                else if (reslt.affectedRows) stateCode = 201;
                else stateCode = 204;
                resolve(stateCode);
            });
        });
    }
    modifyPassword.then(function (stateCode) {
        callback(stateCode);
    });
}

//아이디 찾기
manager.getId = (name, phone, callback) => {
    let id = null;

    let findId = () => {
        return new Promise(function (resolve, reject) {
            conn.query('select id from account where name=? and phone=?;', [name, phone], function (err, rows) {
                if (err) stateCode = 500;
                else if (rows.length == 1) {
                    stateCode = 201;
                    id = rows[0].id;
                } else stateCode = 204;
                resolve(stateCode, id);
            });
        });
    }

    findId.then(function (stateCode, id) {
        callback(stateCode, id);
    });
}

module.exports = manager;