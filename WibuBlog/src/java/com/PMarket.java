/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import com.ContentDelivery;
import java.time.LocalDateTime;
import model.Account;
import security.Hash;
import utility.KeyGenerator;

/**
 *
 * @author admin
 */
public class PMarket {
    public static void main(String[] args) {
        String toAddress = "conboquangdeptrai@gmail.com";     
        
        String[] productNames = {"10$ Steam Wallet code", "20$ Steam Wallet code", "50$ Steam Wallet code"};
        String[] productKeys = {"NVQG8-YCYMX-60Z7S", "TN0DZ-45NPE-B2M41", "QY0M8-0MHIB-4P21K"};
        
        Account a = new Account(1, "fats", "Ho Nguyen Phat", "conboquangdeptrai@gmail.com", false, 0);
        
        // ContentDelivery.sendProductKey(toAddress, productNames, productKeys);     
        // ContentDelivery.sendPasswordResetRequest(toAddress, a, "177013");
        
        // System.out.println(Hash.getHash("password"));
        
        // System.out.println(KeyGenerator.generateKey());
        
        System.out.println(LocalDateTime.now().toLocalDate());
    }
}
