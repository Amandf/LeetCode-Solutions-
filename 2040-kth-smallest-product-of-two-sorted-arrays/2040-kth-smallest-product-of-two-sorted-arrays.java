public class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long lo = -10000000000L - 1;
        long hi = 10000000000L + 1;
        long ans = 0;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (countLE(nums1, nums2, mid) >= k) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private long countLE(int[] A, int[] B, long x) {
        long cnt = 0;
        for (long a : A) {
            if (a > 0) {
                int l = 0, r = B.length - 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (a * B[mid] <= x) l = mid + 1;
                    else r = mid - 1;
                }
                cnt += l;
            } else if (a < 0) {
                int l = 0, r = B.length - 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (a * B[mid] <= x) r = mid - 1;
                    else l = mid + 1;
                }
                cnt += B.length - l;
            } else if (x >= 0) {
                cnt += B.length;
            }
        }
        return cnt;
    }
}
