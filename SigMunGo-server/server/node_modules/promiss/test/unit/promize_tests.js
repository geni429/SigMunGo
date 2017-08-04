'use strict';

const test = require('ava');

const promize = require('../../lib/promize');

test('promize returns results for promises passed', t => {
  const async1 = () => new Promise(resolve => resolve('result1'));
  const async2 = () => new Promise(resolve => resolve('result2'));
  const async3 = () => new Promise(resolve => resolve('result3'));

  return promize([async1(), async2(), async3()]).then(result => {
    t.is(result.length, 3);
  });
});

test('promize returns results for time taking promises', t => {
  const async1 = () => new Promise(resolve => {
    setTimeout(() => {
      return resolve('result 1');
    }, 1000);
  });
  const async2 = () => new Promise((resolve, reject) => {
    setTimeout(() => {
      return resolve('result 2');
    }, 2000);
  });
  const async3 = () => new Promise(resolve => {
    setTimeout(() => {
      return resolve('result 3');
    }, 3000);
  });

  return promize([async1(), async2(), async3()]).then(result => {
    t.is(result.length, 3);
  });
});

test('promize returns on the first error found', t => {
  const async1 = () => new Promise(resolve => resolve('result1'));
  const async2 = () => new Promise((resolve, reject) => reject(new Error('first error')));
  const async3 = () => new Promise(resolve => resolve('result3'));

  return promize([async1(), async2(), async3()]).catch(err => {
    t.is(err.message, 'first error');
  });
});

test('promize returns first error for time taking methods', t => {
  const async1 = () => new Promise(resolve => {
    setTimeout(() => {
      return resolve('result 1');
    }, 1000);
  });
  const async2 = () => new Promise((resolve, reject) => {
    setTimeout(() => {
      return reject(new Error('first error'));
    }, 2000);
  });
  const async3 = () => new Promise(resolve => {
    setTimeout(() => {
      return resolve('result 3');
    }, 3000);
  });

  return promize([async1(), async2(), async3()]).catch(err => {
    t.is(err.message, 'first error');
  });
});

test('promize collects errors and results', t => {
  const async1 = () => new Promise(resolve => resolve('result1'));
  const async2 = () => new Promise((resolve, reject) => reject(new Error('first error')));
  const async3 = () => new Promise(resolve => resolve('result3'));

  return promize([async1(), async2(), async3()], { errors: false }).then(results => {
    t.is(results.length, 3);
    t.is(results[1].error.message, 'first error');
  });
});

test('promize collects errors and results', t => {
  const async1 = () => new Promise(resolve => {
    setTimeout(() => {
      return resolve('result 1');
    }, 1000);
  });
  const async2 = () => new Promise((resolve, reject) => {
    setTimeout(() => {
      return reject(new Error('first error'));
    }, 2000);
  });
  const async3 = () => new Promise(resolve => {
    setTimeout(() => {
      return resolve('result 3');
    }, 3000);
  });

  return promize([async1(), async2(), async3()], { errors: false }).then(results => {
    t.is(results.length, 3);
    t.is(results[1].error.message, 'first error');
  });
});
