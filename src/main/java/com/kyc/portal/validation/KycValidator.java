package com.kyc.portal.validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class KycValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern GOVERNMENT_ID_PATTERN = Pattern.compile("^[A-Za-z0-9]{6,20}$");

    public String validateFullName(String fullName) {
        if (fullName == null || fullName.trim().length() < 3) {
            return "Full name must be at least 3 characters long.";
        }
        return null;
    }

    public String validateDateOfBirth(String dateOfBirth) {
        try {
            LocalDate dob = LocalDate.parse(dateOfBirth);
            if (dob.isAfter(LocalDate.now())) {
                return "Date of birth cannot be in the future.";
            }
            if (Period.between(dob, LocalDate.now()).getYears() < 18) {
                return "User must be at least 18 years old for KYC registration.";
            }
            return null;
        } catch (DateTimeParseException exception) {
            return "Date of birth must be in YYYY-MM-DD format.";
        }
    }

    public int calculateAge(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public String validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return "Enter a valid email address.";
        }
        return null;
    }

    public String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !PHONE_PATTERN.matcher(phoneNumber.trim()).matches()) {
            return "Phone number must contain exactly 10 digits.";
        }
        return null;
    }

    public String validateGovernmentId(String governmentId) {
        if (governmentId == null || !GOVERNMENT_ID_PATTERN.matcher(governmentId.trim()).matches()) {
            return "Government ID must be 6 to 20 alphanumeric characters.";
        }
        return null;
    }

    public String validateAddress(String address) {
        if (address == null || address.trim().length() < 10) {
            return "Address must be at least 10 characters long.";
        }
        return null;
    }
}
