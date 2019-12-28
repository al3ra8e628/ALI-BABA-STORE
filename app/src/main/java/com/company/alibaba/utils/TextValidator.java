package com.company.alibaba.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._]+@[A-Z0-9._]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_ADDRESS_REGEX = Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#&_]).{1,20})$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[A-Za-z0-9]{1,10}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(String passStr) {
        Matcher matcher = VALID_PASSWORD_ADDRESS_REGEX.matcher(passStr);
        return matcher.find();
    }

    public static boolean validateUsername(String username) {
        Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
        return matcher.find();
    }

	    /*
	        ^                # start-of-string
	       (?=.*[0-9])       # a digit must occur at least once
	       (?=.*[a-z])       # a lower case letter must occur at least once
	       (?=.*[A-Z])       # an upper case letter must occur at least once
	       (?=.*[@#$%^&+=])  # a special character must occur at least once
	       (?=\S+$)          # no whitespace allowed in the entire string
	       .{8,}             # anything, at least eight places though
	       $                 # end-of-string
	       * */

}
