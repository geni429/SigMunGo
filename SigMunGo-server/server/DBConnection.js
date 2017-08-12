const mysql = require('mysql');

const conn = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "hy980615",
    database: "sigmungo"
});

module.exports = conn;
