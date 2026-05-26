package co.edu.cesde.ga.model;

public class UserRoles {

    private Long userId;
    private Long rolesId;

    public UserRoles() {
    }

    public UserRoles(Long userId, Long roleId) {
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

    public Long getRolesId() {
        return rolesId;
    }

    public void setRoleId(Long roleId) {
        this.rolesId = roleId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public String toString() {
        return  "UserId= " + getUserId() + '\n' +
                "RoleId= " + getRoleId() + '\n' +
                "-----------------------------";
    }
}
