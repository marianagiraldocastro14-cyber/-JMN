package co.edu.cesde.ga.model;

public class User_roles {

    private Long userId;
    private Long rolesId;

    public User_roles() {
    }

    public User_roles(Long userId, Long roleId) {
        this.userId = userId;
        this.rolesId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return rolesId;
    }

    public void setRoleId(Long roleId) {
        this.rolesId = roleId;
    }
}