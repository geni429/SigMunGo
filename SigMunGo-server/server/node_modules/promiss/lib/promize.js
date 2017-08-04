'use strict';

const async = require('async');

function handleDefaultOperation (memo, prom, callback) {
  prom.then(res => {
    memo.push(res);
    return callback(null, memo);
  }).catch(err => {
    return callback(err);
  });
}

function handleCollectOperation (memo, prom, callback) {
  const result = { index: memo.length };

  prom.then(res => {
    result.res = res;
    result.error = null;

    memo.push(result);

    return callback(null, memo);
  }).catch(err => {
    result.res = null;
    result.error = err;

    memo.push(result);

    return callback(null, memo);
  });
}

function promize (promises, config) {
  return new Promise((resolve, reject) => {
    async.reduce(promises, [], (memo, prom, callback) => {
      if (config && config.errors === false) {
        return handleCollectOperation(memo, prom, callback);
      }
      return handleDefaultOperation(memo, prom, callback);
    }, (err, results) => {
      if (err) {
        return reject(err);
      }
      return resolve(results);
    });
  });
}

module.exports = promize;
