package com.projects.formare.utils;

import java.util.List;

public class MatchString {
    private static final int INSERTION_COST = 1;
    private static final int DELETION_COST = 1;
    private static final int SUBSTITUTION_COST = 1;

    public static float similarity(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost;
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = SUBSTITUTION_COST;
                }

                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + INSERTION_COST, dp[i][j - 1] + DELETION_COST), dp[i - 1][j - 1] + cost);
//                dp[i][j] = Math.min(
//                        dp[i - 1][j] + INSERTION_COST,
//                        dp[i][j - 1] + DELETION_COST,
//                        dp[i - 1][j - 1] + cost);
            }
        }

        float similarityScore = 1 - (float) dp[len1][len2] / Math.max(len1, len2);
        return similarityScore;
    }

    public static String removeDiacritics(String text) {
        String[] diacritics = {"ă", "â", "î", "ș", "ț", "Ă", "Â", "Î", "Ș", "Ț"};
        String[] replacements = {"a", "a", "i", "s", "t", "A", "A", "I", "S", "T"};

        for (int i = 0; i < diacritics.length; i++) {
            text = text.replace(diacritics[i], replacements[i]);
        }

        return text;
    }


    public static List<String> getWords(String sir) {
        String regex = "[\\s.,_\\-/]+"; // Regex care se potrivește cu spații, puncte, virgule, liniuțe de subliniere și bare
        String[] words = sir.split(regex);

        return List.of(words);
    }
}
