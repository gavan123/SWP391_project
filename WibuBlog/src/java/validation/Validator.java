/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

/**
 *
 * @author mindc
 */
public class Validator {
    public static boolean passwordRegex(String password){
        String pattern = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d)[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,50}$";
        if (password.matches(pattern)){
            return true;
        }
        return false;
    }
    
    public static boolean usernameRegex(String username){
        String pattern = "^[a-zA-Z0-9]{4,20}$";
        if (username.matches(pattern)){
            return true;
        }
        return false;
    }
    
    public static boolean emailRegex(String email){
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email.matches(pattern)){
            return true;
        }
        return false;
    }
    
  
}
