let crypto = require('crypto');

let SHA256 = {};
SHA256.encrypt = function (text) {
    let hash = crypto.createHash('sha256').update(text).digest('base64');
    return hash;
}
module.exports = SHA256;