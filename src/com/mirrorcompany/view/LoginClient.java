package com.mirrorcompany.view;

import com.mirrorcompany.model.User;
import com.mirrorcompany.service.UserService;
import java.rmi.Naming;

public class LoginClient {
    private static final String RMI_SERVER = "rmi://localhost:1099/UserService";

    public static void main(String[] args) {
        try {
            // Lookup the remote UserService object
            UserService userService = (UserService) Naming.lookup(RMI_SERVER);

            // Get user credentials (you can replace this with a proper UI or input mechanism)
            String email = "ezechiel@gmail.com";
            String password = "12345";

            // Create a User object with the provided credentials
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            // Call the remote method to verify user credentials
            boolean isAuthenticated = userService.verifyUserCredentials(user);

            if (isAuthenticated) {
                System.out.println("Login successful!");
                // Proceed with further actions or navigate to the main application
            } else {
                System.out.println("Invalid credentials. Login failed.");
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
