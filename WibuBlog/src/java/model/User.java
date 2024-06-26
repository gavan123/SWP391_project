/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
import java.time.LocalDateTime;

public class User {

    private int userId;
    private String username;
    private String passwordHash;
    private int roleId;
    private int point;
    private String status;
    private String email;
    private String fullName;
    private int rankId;
    private String profilePhoto;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;
    private LocalDateTime creationDate;
    private String bio;
    

    public User() {
    }

    public User(int userId, String username, String passwordHash, int roleId, int point, String status, String email, String fullName, int rankId, String profilePhoto, String phoneNumber, LocalDateTime dateOfBirth, LocalDateTime creationDate, String bio) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.point = point;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.rankId = rankId;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.bio = bio;
    }

    
    public User(int userId, String username, String passwordHash, int roleId, int point, String status, String email, String fullName, int rankId, String profilePhoto, String phoneNumber, LocalDateTime dateOfBirth, LocalDateTime creationDate) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.point = point;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.rankId = rankId;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
    }

        public User(int userId, String username, String passwordHash, int roleId, int point, String status, String email, String fullName, int rankId, String profilePhoto, String phoneNumber, LocalDateTime creationDate) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.point = point;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.rankId = rankId;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
    }

    public User(int userId, String username, String fullName, String profilePhoto, String phoneNumber, LocalDateTime dateOfBirth) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String username, String passwordHash, int roleId, int point, String status, String email, String fullName, int rankId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.point = point;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.rankId = rankId;
    }

    public User(int userId, String username, String passwordHash, int roleId, int point, String status, String email, String fullName, int rankId) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.point = point;
        this.status = status;
        this.email = email;
        this.fullName = fullName;
        this.rankId = rankId;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean verifyPassword(String hash) {
        return passwordHash.equals(hash);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    

}
