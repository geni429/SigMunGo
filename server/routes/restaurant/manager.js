let conn = require('../../DBConnection');

let manager = {}

manager.addRestaurant = function (contentId, name, place, phone, manu, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('insert into restaurant value(?,?,?,?);', [contentId, name, place, phone], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) {
            for (let i = 0; i < manu.length; i++) {
                conn.query('insert into manu value(?,?)', [contentId, manu[i]], function (err, result) {
                    if (err) response.error = true;
                    else if (result.affectedRows) response.success = true;
                });
            }
        }
        callback(JSON.stringify(response));
    });
}

manager.getRestaurant = function (contentId, callback) {

}

manager.updateRestaurant = function (contentId, name, place, phone, manu, callback) {

}

manager.deleteRestaurant = function (contentId, callback) {

}

module.exports = manager;