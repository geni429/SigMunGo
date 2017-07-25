let conn = require('../../DBConnection');

let manager = {}

manager.registe = function (id, password, name, phone, callback) {
    let response = {
        error: false,
        success: false
    };
    conn.query("insert into account values(?,?,?,?);", [id, password, name, phone], function (err, result) {
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

module.exports = manager;