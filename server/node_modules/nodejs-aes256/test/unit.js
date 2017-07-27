var aes256 = require('../nodejs-aes256'),
    should = require('should');

describe('nodejs-aes256', function() {
    var key = 'Heisenberg';
    var plaintext = 'Hello World!';
    var ciphertext;

    it('can encrypt', function() {
        ciphertext = aes256.encrypt(key, plaintext);
        ciphertext.should.not.equal(plaintext);
    });

    it('can decrypt', function() {
        var decrypted = aes256.decrypt(key, ciphertext);
        decrypted.should.equal(plaintext);
    });

    it('same key and plaintext will not return same ciphertext', function() {
        var ciphertext2 = aes256.encrypt(key, plaintext);
        ciphertext2.should.not.equal(ciphertext);
    });
});
