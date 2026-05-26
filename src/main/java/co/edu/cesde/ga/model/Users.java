package co.edu.cesde.ga.model;

public class Users {

    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String status;
    private String createdAt;

    public Users() {
    }

    public Users(String username, String email, String passwordHash, String status, String createdAt) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Users(Long userId, String username, String email, String passwordHash, String status, String createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return  "UserId= " + getUserId() + '\n' +
                "Username= " + getUsername() + '\n' +
                "Email= " + getEmail() + '\n' +
                "PasswordHash= " + getPasswordHash() + '\n' +
                "Status= " + getStatus() + '\n' +
                "CreatedAt= " + getCreatedAt() + '\n' +
                "-----------------------------";
    }
}
