class ArrayWrapper {
    constructor(nums) {
        this.nums = nums;
    }

    valueOf() {
        return this.nums.reduce((sum, n) => sum + n, 0);
    }

    toString() {
        return `[${this.nums.join(',')}]`;
    }
}
