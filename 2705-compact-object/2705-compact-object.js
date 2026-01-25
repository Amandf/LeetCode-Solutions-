/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {
    if (obj === null) return null;

    if (Array.isArray(obj)) {
        return obj
            .map(compactObject)
            .filter(Boolean);
    }

    if (typeof obj === "object") {
        const result = {};
        for (let key in obj) {
            const value = compactObject(obj[key]);
            if (Boolean(value)) {
                result[key] = value;
            }
        }
        return result;
    }

    return obj;
};
