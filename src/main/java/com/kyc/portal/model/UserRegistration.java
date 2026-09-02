package com.kyc.portal.model;

public class UserRegistration {
    private final int registrationId;
    private final String fullName;
    private final String dateOfBirth;
    private final int age;
    private final String email;
    private final String phoneNumber;
    private final String governmentId;
    private final String address;

    public UserRegistration(int registrationId, String fullName, String dateOfBirth, int age,
                            String email, String phoneNumber, String governmentId, String address) {
        this.registrationId = registrationId;
        this.fullName = fullName;
        this.dob = dateOfBirth;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.governmentId = governmentId;
        this.address = address;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Registration ID: " + registrationId + System.lineSeparator()
                + "Full Name: " + fullName + System.lineSeparator()
                + "DoBirth: " + dateOfBirth + System.lineSeparator()
                + "Age: " + age + System.lineSeparator()
                + "Email Address: " + email + System.lineSeparator()
                + "Phone Number: " + phoneNumber + System.lineSeparator()
                + "Government ID: " + governmentId + System.lineSeparator()
                + "Address: " + address;
    }
}
