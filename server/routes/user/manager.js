let conn = require('../../DBConnection');

let manager = {}

manager.registe = function (id, password, name, phone, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('insert into account values(?,?,?,?);', [id, password, name, phone], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;

        callback(JSON.stringify(response));
    });
}

manager.login = function (id, password, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('select * from account where id=? and password=?', [id, password], function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.success = true;

        callback(response);
    });
}

manager.checkId = function (id, callback) {
    let response = {
        error: false,
        overlap: false
    };
    conn.query('select * from accont where id=?', id, function (err, result) {
        if (err) response.error = true;
        else if (reslt.affectedRows) response.overlap = true;

        callback(JSON.stringify(response));
    })
}

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

manager.getId = function (name, phone, callback) {
    let response = {
        error: true,
        id: null
    };

    conn.query('select id from account where name=? and phone=?;', [name, phone], function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.id = rows[0].id;

        callback(JSON.stringify(response));
    });
}

module.exports = manager;