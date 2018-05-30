package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {


    @Test
    public void generatePasswordTest() {
        int t = 100000;
        boolean flag = true;

        while(flag) {

            String pass = PasswordGenerator.generatePassword(8);
            String testString = Validator.validatePassword(pass);
            String result = testString.equals("")? " is correct." : " is not correct: " + testString;
            System.out.println("Password: " + pass + result);

            assertEquals("", testString);

            if(t-- == 0) flag = false;
        }
    }
}