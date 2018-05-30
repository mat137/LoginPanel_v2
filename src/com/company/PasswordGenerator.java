package com.company;


import java.util.*;

public class PasswordGenerator {

    private static String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String NUMERIC = "0123456789";
    private static String SPECIAL = "!@#$%&_?.";
    private static int howManyFilters = 4; // LOWER + UPPER + NUMERIC + SPECIAL = 4

    public static String generatePassword(int theLength) {
        StringBuilder randomPassword = new StringBuilder();
        Random r = new Random();
        int unUsed = howManyFilters;

        Map<String, Integer> genericsList = new HashMap<String, Integer>();
        genericsList.put(LOWER, 0);
        genericsList.put(UPPER, 0);
        genericsList.put(NUMERIC, 0);
        genericsList.put(SPECIAL, 0);

        for (int i = 0; i < theLength; i++) {
            Object[] genericListKeys = genericsList.keySet().toArray();
            Object theKey = genericListKeys[r.nextInt(genericListKeys.length)];
            String stringKey = String.valueOf(theKey);
            String[] tmp = stringKey.split("");

            if(genericsList.get(stringKey) == 0) unUsed--;

            if(theLength - unUsed - i+1 > 1) {
                randomPassword.append(tmp[r.nextInt(tmp.length)]);
                genericsList.put(stringKey, genericsList.get(stringKey) + 1);
                if(!stringKey.equals(LOWER)) {
                    genericsList.remove(stringKey);
                }
            } else {
                genericsList.remove(stringKey);
                --i;
            }

        }


        return String.valueOf(randomPassword);
    }

}
