class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];

        long curr = 0;

        // Step 1: Calculate string length after each operation
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                curr++;
            } 
            else if (ch == '*') {
                curr = Math.max(0, curr - 1);
            } 
            else if (ch == '#') {
                curr *= 2;
            }

            len[i] = curr;
        }

        // Step 2: Check whether k is valid
        if (k >= curr) {
            return '.';
        }

        // Step 3: Traverse backwards
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '*') {
                curr = (i == 0) ? 0 : len[i - 1];
            } 
            else if (ch == '#') {
                curr /= 2;

                if (curr > 0) {
                    k %= curr;
                }
            } 
            else if (ch == '%') {
                k = curr - 1 - k;
            } 
            else {
                if (k == curr - 1) {
                    return ch;
                }

                curr--;
            }
        }

        return '.';
    }
}