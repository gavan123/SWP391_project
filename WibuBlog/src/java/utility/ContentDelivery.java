
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import utility.Email;

/**
 *
 * @author admin
 */
public class ContentDelivery {

    public static void sendVerificationCode(String from, String to, String code) {
        Email.sendMail(from, to, "Verification code", code);

    }

    public static void sendVerificationCode(String to, String code) {
        Email.sendMail("Verification code", to, "Verification code", code);

    }

    public static void sendNewPassword(String to, String code) {
        Email.sendMail("Verification code", to, "New password", code);

    }
}
