class TimeLimitedCache {
    constructor() {
        this.cache = new Map();
    }

    set(key, value, duration) {
        const now = Date.now();
        const exists =
            this.cache.has(key) && this.cache.get(key).expire > now;

        this.cache.set(key, {
            value,
            expire: now + duration
        });

        return exists;
    }

    get(key) {
        if (!this.cache.has(key)) return -1;

        const entry = this.cache.get(key);
        if (Date.now() > entry.expire) {
            this.cache.delete(key);
            return -1;
        }

        return entry.value;
    }

    count() {
        const now = Date.now();
        let cnt = 0;

        for (const [key, entry] of this.cache) {
            if (entry.expire > now) {
                cnt++;
            } else {
                this.cache.delete(key);
            }
        }

        return cnt;
    }
}
