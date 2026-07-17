package com.kyc.portal;

import com.kyc.portal.model.UserRegistration;
import com.kyc.portal.service.RegistrationService;
import com.kyc.portal.validation.KycValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final RegistrationService registrationService = new RegistrationService();
    private final KycValidator validator = new KycValidator();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    listRegistrations();
                    break;
                case "3":
                    viewRegistrationById();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting KYC registration portal.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== KYC User Registration Portal ===");
        System.out.println("1. Register user");
        System.out.println("2. List registrations");
        System.out.println("3. View registration by ID");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void registerUser() {
        System.out.println();
        System.out.println("--- Register New User ---");

        String fullName = promptUntilValid("Full name: ", validator::validateFullName);
        String dateOfBirth = promptUntilValid("Date of birth (YYYY-MM-DD): ", validator::validateDateOfBirth);
        int age = validator.calculateAge(dateOfBirth);
        String email = promptUntilValid("Email: ", validator::validateEmail);
        String phoneNumber = promptUntilValid("Phone number (10 digits): ", validator::validatePhoneNumber);
        String governmentId = promptUntilValid("Government ID: ", validator::validateGovernmentId);
        String address = promptUntilValid("Address: ", validator::validateAddress);

        UserRegistration registration = registrationService.registerUser(
                fullName.trim(),
                dateOfBirth.trim(),
                age,
                email.trim(),
                phoneNumber.trim(),
                governmentId.trim(),
                address.trim()
        );

        System.out.println();
        System.out.println("Registration completed successfully.");
        System.out.println(registration);
    }

    private void listRegistrations() {
        System.out.println();
        System.out.println("--- Registered Users ---");
        List<UserRegistration> registrations = registrationService.getAllRegistrations();

        if (registrations.isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }

        for (UserRegistration registration : registrations) {
            System.out.println(registration.getRegistrationId() + " - "
                    + registration.getFullName() + " - "
                    + registration.getEmail());
        }
    }

    private void viewRegistrationById() {
        System.out.println();
        System.out.print("Enter registration ID: ");
        String input = scanner.nextLine().trim();

        try {
            int registrationId = Integer.parseInt(input);
            UserRegistration registration = registrationService.findByRegistrationId(registrationId);
            if (registration == null) {
                System.out.println("Registration not found.");
                return;
            }
            System.out.println();
            System.out.println(registration);
        } catch (NumberFormatException exception) {
            System.out.println("Registration ID must be a number.");
        }
    }

    private String promptUntilValid(String prompt, ValidationFunction validationFunction) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            String validationMessage = validationFunction.validate(input);
            if (validationMessage == null) {
                return input;
            }
            System.out.println(validationMessage);
        }
    }

    @FunctionalInterface
    private interface ValidationFunction {
        String validate(String input);
    }
}
