package co.edu.cesde.ga.model;

public class Roles {

    private Long rolesId;
    private String name;
    private String description;

    public Roles() {
    }

    public Roles(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Roles(Long roleId, String name, String description) {
        this.rolesId = roleId;
        this.name = name;
        this.description = description;
    }

    public Long getRolesId() {
        return rolesId;
    }

    public Long getRoleId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    public void setRoleId(Long roleId) {
        this.rolesId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "RoleId= " + getRolesId() + '\n' +
                "Name= " + getName() + '\n' +
                "Description= " + getDescription() + '\n' +
                "-----------------------------";
    }
}
