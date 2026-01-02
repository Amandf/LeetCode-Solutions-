/**
 * @param {integer} init
 * @return { increment: Function, decrement: Function, reset: Function }
 */
var createCounter = function(init) {
    let value = init;

    return {
        increment: () => ++value,
        decrement: () => --value,
        reset: () =>  value  = init
    };
    
};
