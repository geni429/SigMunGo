## Implementation
`Promize` will be a simple `Promises` sequence runner which will be used to execute promise based `functions` in sequence and manage the way errors are handled.  

### Methods
- `run(array[, options])`: takes an `array` of promise and optional `options` object with `errors` with a `boolean` value to determine how to handle errors. Default value is `true` and it will throw the first instance of error it finds. A `false` value will collect all results including errors and return an array of objects with three keys. `name`, `result` and `error`. Values of `result` and `error` will be `null` if there promise doesn't return a result and/or there is no error.

## Related topics
- [Promise](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise): The Promise object is used for asynchronous computations. A Promise represents a value which may be available now, or in the future, or never.  