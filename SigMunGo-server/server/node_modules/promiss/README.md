[![Build Status](https://travis-ci.org/samtes/promiss.svg?branch=master)](https://travis-ci.org/samtes/promiss)
# Promiss
This is a promises sequence runner. It will be used to run multiple promise 
based functions in sequence.

## Usage
### Installation
```javascript
npm install --save promiss
```

### Usage
`promiss` takes two arguments.

1. `array` of `promises`
2. [`options`](#Options) to customize response

```javascript
const promiss = require('promiss');

const foo = () => {
  return Promise((resolve, reject) => {
    setTimeout((){
      resolve('hello');
    }, 3000)
  });
};
const bar = () => {
  return Promise((resolve, reject) => {
    setTimeout((){
      reject(new Error('My heart broke'));
    }, 5000)
  });
};
const zoo = () => {
  return Promise((resolve, reject) => {
    setTimeout((){
      resolve('adios');
    }, 2000)
  });
};

// throws the first error rejected
// same as passing { errors: true } as the second argument
return promiss([foo(), bar(), zoo()]).catch(err => {
  // err.message = 'My heart broke'
  // catches the first error
});

// collects all errors and resolves at the end with results
return promiss([foo, bar, zoo], { error: false }).then(results => {
  // result will look like this
  [
    {
      index: 0,
      result: 'hello',
      error: null
    },
    {
      index: 1,
      result: null,
      error: Error('My heart broke')
    },
    {
      index: 3,
      result: 'adios',
      error: null
    }
  ]
});
```
### Options
- `errors` takes `boolean` value:
  - `true`: throws the first instance of error (the default behavior)
  - `false`: collects all the errors and returns array of objects with `index`, 
  `result` and `error`
      - `index`: index of the method in the array passed
      - `result`: result if there is result and `null` if it throws an error
      - `error`: error if there is error and `null` if there is no error
