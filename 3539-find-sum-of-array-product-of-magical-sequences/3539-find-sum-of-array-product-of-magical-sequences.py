from typing import List
MOD = 10**9 + 7

class Solution:
    def magicalSum(self, m: int, k: int, nums: List[int]) -> int:
        n = len(nums)
        # Precompute factorials and inverse factorials up to m
        maxN = m
        fact = [1] * (maxN + 1)
        for i in range(1, maxN + 1):
            fact[i] = fact[i-1] * i % MOD
        invfact = [1] * (maxN + 1)
        invfact[maxN] = pow(fact[maxN], MOD-2, MOD)
        for i in range(maxN, 0, -1):
            invfact[i-1] = invfact[i] * i % MOD

        def comb(a, b):
            if b < 0 or b > a: 
                return 0
            return fact[a] * invfact[b] % MOD * invfact[a-b] % MOD

        # Precompute powNums[i][t] = nums[i]^t % MOD for t in 0..m
        powNums = [[1] * (m+1) for _ in range(n)]
        for i in range(n):
            for t in range(1, m+1):
                powNums[i][t] = powNums[i][t-1] * nums[i] % MOD

        # dp[t][carry][ones] using 3D lists
        # initialize dp for i = -1 (no indices processed): 0 picks, carry 0, ones 0 -> value 1
        dp = [[[0] * (m+1) for _ in range(m+1)] for __ in range(m+1)]
        dp[0][0][0] = 1

        # process each index i (bit position)
        for i in range(n):
            # next DP array
            nxt = [[[0] * (m+1) for _ in range(m+1)] for __ in range(m+1)]
            # iterate current states
            for t in range(0, m+1):
                for carry in range(0, m+1):
                    row = dp[t][carry]
                    # small micro-optim: skip entire row if all zeros
                    # but checking every element costs too; we'll check by scanning ones
                    any_nonzero = False
                    for ones in range(0, m+1):
                        if row[ones]:
                            any_nonzero = True
                            break
                    if not any_nonzero:
                        continue

                    for ones in range(0, m+1):
                        curVal = row[ones]
                        if curVal == 0:
                            continue
                        # choose freq picks of index i, from 0 to remaining
                        maxFreq = m - t
                        # we will incrementally update t2
                        for freq in range(0, maxFreq + 1):
                            t2 = t + freq
                            total = carry + freq
                            bit = total & 1
                            carry2 = total >> 1
                            ones2 = ones + bit
                            if ones2 > m: 
                                break
                            # multiplier: nums[i]^freq * C(t + freq, freq)
                            ways = comb(t + freq, freq)
                            prod = powNums[i][freq]
                            add = curVal * ways % MOD * prod % MOD
                            nxt[t2][carry2][ones2] = (nxt[t2][carry2][ones2] + add) % MOD

            dp = nxt

        # collect final results: t must be m
        ans = 0
        for carry in range(0, m+1):
            pc = carry.bit_count()
            # we require ones + pc == k
            for ones in range(0, m+1):
                if ones + pc == k:
                    ans = (ans + dp[m][carry][ones]) % MOD

        return ans
