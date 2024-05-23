/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author user
 */
//doi tuong de truy cap du lieu
public class UserDAOImpl extends DBContext implements UserDAO {

    @Override
    public boolean insertUser(String userName, String password, String email, String fullName) {
        try {
            Connection conn = getConnection();
            ///Hash password using SHA-256
            String hashedPassword = hashPassword(password);
            String sql = "insert into User (userName, password, email, fullName) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, hashedPassword);
                pstmt.setString(3, email);
                pstmt.setString(4, fullName);

                //execute insert statement
                int rowsAffected = pstmt.executeUpdate();
                closeConnection(conn, pstmt, null);
                //check if the insertion was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = md.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private void closeConnection(Connection conn, PreparedStatement pstmt, Object object) {
        throw new UnsupportedOperationException();
    }
}
