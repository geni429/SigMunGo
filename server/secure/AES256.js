let crypto = require("crypto");
let AES256 = {};

AES256.encrypt = function (text, key) {
    let cipher = crypto.createCipher('aes-256-cbc', key);
    let encipheredContent = cipher.update(text, 'utf8', 'hex');
    encipheredContent += cipher.final('hex');

    return encipheredContent;
}

AES256.decrypt = function (text, key) {
    let decipher = crypto.createDecipher('aes-256-cbc', key);
    let decipheredPlaintext = decipher.update(text, 'hex', 'utf8');
    decipheredPlaintext += decipher.final('utf8');

    return decipheredPlaintext;
}

module.exports = AES256;