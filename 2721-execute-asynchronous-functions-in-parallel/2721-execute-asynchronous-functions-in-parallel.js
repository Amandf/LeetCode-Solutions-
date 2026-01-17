/**
 * @param {Array<Function>} functions
 * @return {Promise<any>}
 */
var promiseAll = async function(functions) {
    return new Promise((resolve, reject) => {
        const results = [];
        let completed = 0;

        functions.forEach((fn, index) => {
            fn()
                .then(value => {
                    results[index] = value;
                    completed++;

                    if (completed === functions.length) {
                        resolve(results);
                    }
                })
                .catch(reject);
        });

        if (functions.length === 0) {
            resolve([]);
        }
    });
};

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */