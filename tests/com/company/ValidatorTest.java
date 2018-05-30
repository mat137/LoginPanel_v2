package com.company;

import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class ValidatorTest {


    String[] testMailSuccess = { "\"Chuck Norris\"@example.com", "Chuck Norris <gmail@chucknorris.com>", "webmaster@norris.us", "chuck@40.47.122.114" };
    String[] testMailFail = { "user@.invalid.com", "chucknorris.com" };

    String testNameFail = "ba";
    String testNameSuccess = "bartek";

    String testPhoneNumberFail = "555 888 777";
    String testPhoneNumberSuccess = "555111888";
    private static UserStore userStore = new UserStore();

    // E-mail
    @Test
    public void testValidateMailOnFail(){

        // should fail
        for (String email: testMailFail) {
            assertEquals(false, Validator.validateMail(email));
        }

    }
    @Test
    public void testValidateMailOnSuccess(){

        // should success
        for (String email: testMailSuccess) {
            assertEquals(true, Validator.validateMail(email));
        }
    }

    // Name
    @Test
    public void testValidateNameOnFail(){

        // should fail
        boolean resultF = Validator.validateName(testNameFail, userStore);
        assertEquals(false, resultF);

    }
    @Test
    public void testValidateNameOnSuccess(){

        // should success
        boolean resultS = Validator.validateName(testNameSuccess, userStore);
        assertEquals(true, resultS);
    }

    // phone number
    @Test
    public void testValidatePhoneNumberOnFail(){

        // should fail
        boolean resultF = Validator.validatePhoneNumber(testPhoneNumberFail);
        assertEquals(false, resultF);

    }
    @Test
    public void testValidatePhoneNumberOnSuccess(){

        // should success
        boolean resultS = Validator.validatePhoneNumber(testPhoneNumberSuccess);
        assertEquals(true, resultS);
    }

}