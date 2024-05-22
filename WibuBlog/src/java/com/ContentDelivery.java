/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import utility.Email;

/**
 *
 * @author admin
 */
public class ContentDelivery {
    public void sendVerificationCode(String from, String to, String code) {
        Email.sendMail(from, to, "Verification code", code);
    }
}
