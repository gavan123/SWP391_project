package com;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Account;
import utility.Email;

public class ContentDelivery {
    private static final String senderName = "PMarket";

    public static void sendProductKey(String toAddress, ArrayList<String> productNames, ArrayList<String> productKeys) {
        String subject = "Your product key is ready!";

        String content = "Congratulations on your purchase(s). You are now ready to activate your product(s) on Steam.\n\n";
        for (int i = 0; i < productNames.size(); i++) {
            content += "Product name: " + productNames.get(i)
                    + "\nProduct key: " + productKeys.get(i) + "\n\n";
        }
        content += "Thank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }

    public static void sendPasswordResetRequest(String toAddress, Account account, String code) {
        String subject = "Password reset request for account " + account.getUsername();

        String content = "Hi " + account.getDisplayName() + ",\n\n";
        content += "We have received a request to reset the password for your account " + account.getUsername() + "\n";
        content += "To reset your password, use the code below:\n\n";
        content += code;
        content += "\n\nThank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }

    public static void sendPasswordResetRequest(String toAddress, String username, String displayName, String code) {
        String subject = "Password reset request for account " + username;

        String content = "Hi " + displayName + ",\n\n";
        content += "We have received a request to reset the password for your account " + username + "\n";
        content += "To reset your password, use the code below:\n\n";
        content += code;
        content += "\n\nThank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }
    
    public static void sendSecurityAlert(String toAddress, String username, String displayName) {
        String subject = "Security alert for account " + username;

        String content = "Hi " + displayName + ",\n\n";
        content += "The password for your account " + username + " has recently been updated.\n\n";
        content += "Thank you for shopping with PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }
    
    public static void sendEmailVerificationCode(String toAddress, String username, String displayName, String code) {
        String subject = "Email verification for account " + username;

        String content = "Hi " + displayName + ",\n\n";
        content += "Thank you for verifying your email account with PMarket. You can use the code below to verify your email address:\n\n";
        content += code + "\n\n";
        content += "We look forward to shopping with you at PMarket! \n\nSent at: " + LocalDateTime.now();

        Email.sendMail(senderName, toAddress, subject, content);
    }
}
