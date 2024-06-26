
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class ContentDelivery {
    private static String from = "Wibu Forum";
    
    public static void sendEmailVerification(String to, String username, String code) {
        String subject = "Confirm registration for account " + username;

        String content = "Hi " + username + ",\n\n";
        content += "Thank you for creating an account with Wibu Forum. Please use the code below to verify your email address and complete your account registration:\n\n";
        content += code + "\n\n";
        content += "We look forward to seeing you at Wibu Blog!\n";
        content += "- Wibu Forum Team\n\n";
        content += "Sent at: " + LocalDateTime.now() + "\n";
        content += "(This message is automated. Please do not respond to this email. Wibu Forum and the team will never ask for your password or this verifivation code.)\n";
        
        Email.sendMail(from, to, subject, content);
    }
    
    public static void sendPasswordResetVerification(String to, String username, String code) {
        String subject = "Comfirm password reset request for account " + username;

        String content = "Hi " + username + ",\n\n";
        content += "We have received a request to reset the password for your account " + username + "\n";
        content += "To reset your password, please verify your identity using the code below:\n\n";
        content += code + "\n\n";
        content += "If you didn't make this request, you can safely ignore this email.\n";
        content += "- Wibu Forum Team\n\n";
        content += "Sent at: " + LocalDateTime.now() + "\n";
        content += "(This message is automated. Please do not respond to this email. Wibu Forum and the team will never ask for your password or this verifivation code.)\n";
        
        Email.sendMail(from, to, subject, content);
    }
    
    public static void sendSecurityAlert(String to, String username) {
        String subject = "Security alert for account " + username;

        String content = "Hi " + username + ",\n\n";
        content += "The password for your account " + username + " has recently been updated.\n\n";
        content += "Thank you being a member of Wibu Forum!\n";
        content += "- Wibu Forum Team\n\n";
        content += "Sent at: " + LocalDateTime.now() + "\n";
        content += "(This message is automated. Please do not respond to this email. Wibu Blog and the team will never ask for your password or this verifivation code.)\n";

        Email.sendMail(from, to, subject, content);
    }
    
     public static void sendEmailChangeVerification(String to, String username, String code) {
        String subject = "Comfirm new email address for account " + username;

        String content = "Hi " + username + ",\n\n";
        content += "We have received a request to set " + to + " as the new email address for your account " + username + "\n";
        content += "To confirm this action, please verify your identity using the code below:\n\n";
        content += code + "\n\n";
        content += "If you didn't make this request, you can safely ignore this email.\n";
        content += "- Wibu Forum Team\n\n";
        content += "Sent at: " + LocalDateTime.now() + "\n";
        content += "(This message is automated. Please do not respond to this email. Wibu Forum and the team will never ask for your password or this verifivation code.)\n";
        
        Email.sendMail(from, to, subject, content);
    }
     
      public static void sendEmailChangeAlert(String to, String username) {
        String subject = "Security alert for account " + username;

        String content = "Hi " + username + ",\n\n";
        content += "The email address " + to + " has recently been set as the email address for your account: " + username + ".";
        content += "Thank you being a member of Wibu Blog!\n";
        content += "- Wibu Forum Team\n\n";
        content += "Sent at: " + LocalDateTime.now() + "\n";
        content += "(This message is automated. Please do not respond to this email. Wibu Blog and the team will never ask for your password.)\n";

        Email.sendMail(from, to, subject, content);
    }
    
}
