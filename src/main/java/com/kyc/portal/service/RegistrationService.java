package com.kyc.portal.service;

import com.kyc.portal.model.UserRegistration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private final List<UserRegistration> registrations = new ArrayList<>();
    private int nextRegistrationId = 1;

    public UserRegistration registerUser(String fullName, String dateOfBirth, int age,
                                        String email, String phoneNumber, String governmentId,
                                        String address) {
        UserRegistration registration = new UserRegistration(
                nextRegistrationId++,
                fullName,
                dateOfBirth,
                age,
                email,
                phoneNumber,
                governmentId,
                address
        );
        registrations.add(registration);
        return registration;
    }

    public List<UserRegistration> getAllRegistrations() {
        return registrations;
    }

    public UserRegistration findByRegistrationId(int registrationId) {
        for (UserRegistration registration : registrations) {
            if (registration.getRegistrationId() == registrationId) {
                return registration;
            }
        }
        return null;
    }
}
