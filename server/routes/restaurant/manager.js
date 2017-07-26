let conn = require('../../DBConnection');

let manager = {}

manager.addGood = function (contentId, callback) {
    let response = {
        error: false,
        success: false
    }

    conn.query('select * from restaurant where contentid=?', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) {
            let good = rows[0].good;
            good += 1;

            conn.query('update restaurant set good=? where contentid=?', [good, contentId], function (err, result) {
                if (err) response.error = true;
                else if (result.affectedRows) response.success = true;
            });
        }

        callback(JSON.stringify(response));
    });
}

manager.getGood = function (contentId, callback) {
    let response = {
        error: false,
        good: null
    };

    conn.query('select * from restaurant where contentid=?', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) response.good = rows[0].good;
        callback(JSON.stringify(response));
    });
}

manager.getContentId = function (callback) {
    let response = {
        error: false,
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
        callback(JSON.stringify(response));
    });
}

manager.checkId = function (contentId) {
    conn.query('select * from restaurant where contentid=?;', contentId, function (err, rows) {
        if (rows.length == 1) return true;
        else if (rows.length == 0) return false;
    });
}

manager.addRestaurant = function (contentId, name, place, phone, manu, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('insert into restaurant value(?,?,?,?);', [contentId, name, place, phone], function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) {
            for (let i = 0; i < manu.length; i++) {

                conn.query('insert into manu value(?,?)', [contentId, manu.restaurant[i].manu], function (err, result) {
                    if (err) response.error = true;
                    else if (result.affectedRows) response.success = true;
                });
            }
        }
        callback(JSON.stringify(response));
    });
}


manager.getDetailRestaurant = function (contentId, callback) {
    let response = {
        error: false,
        name: null,
        place: null,
        phone: null,
        manu: []
    };

    conn.query('select * from restaurant where contentId=?', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) {
            response.name = rows[0].name;
            response.place = rows[0].place;
            response.phone = rows[0].phone;

            conn.query('select * from manu where contentId=?', contentId, function (err, rows) {
                if (err) response.error = true;
                else if (rows.length >= 1) {
                    for (let i = 0; i < rows.length; i++) {
                        response.manu[i] = rows[i].manu;
                    }
                }
            });
        }
        callback(JSON.stringify(response));
    });
}

manager.updateRestaurant = function (contentId, name, place, phone, manu, callback) {

}

manager.deleteRestaurant = function (contentId, callback) {
    let response = {
        error: false,
        success: false
    };

    conn.query('delete from restaurant where contentid=?', contentId, function (err, result) {
        if (err) response.error = true;
        else if (result.affectedRows) response.success = true;

        callback(JSON.stringify(response));
    });
}

manager.getRestaurant = function (contentId, callback) {
    let response = {
        error: false,
        img: null,
        name: null,
        phone: null,
        place: null
    };

    conn.query('select * from restaurant where contentid=?', contentId, function (err, rows) {
        if (err) response.error = true;
        else if (rows.length == 1) {
            response.img = rows[0].img;
            response.name = rows[0].name;
            response.phone = rows[0].phone;
            response.place = rows[0].place;
        }

        callback(JSON.stringify(response));
    });
}

manager.addDiscontent = function () {

}

manager.getCountOfDiscontent = function (contentId, callback) {

}

manager.updateDiscontent = function (contentId) {

}

manager.deleteDiscontent = function (contentId, callback) {

}

module.exports = manager;