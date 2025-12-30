/**
 * @return {Function}
 */

/**
 * const f = createHelloWorld();
 * f(); // "Hello World"
 */

 function createHelloWorld() {
  return function(...args) {
    return "Hello World";
  };
}

