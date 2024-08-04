package com.example.ecommerce.service.algorithm;

import org.springframework.stereotype.Service;

import java.util.List;

public class Similarity {
    /**
     * calculate similarity between two string
     * @param s1
     * @param s2
     * @return
     */
    public static double similarity(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int maxLen = Math.max(len1, len2);
        if(maxLen == 0) return 1.0;
        int[][] dp = new int[len1 + 1][len2 + 2];
        for(int i = 0; i <= s1.length(); i++) {
            dp[0][i] = i;
        }
        for(int i = 0; i <= s2.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }
        return (1 - (double)(dp[len1][len2]/maxLen));
    }

}
