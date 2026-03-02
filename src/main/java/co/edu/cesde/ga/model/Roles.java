package co.edu.cesde.ga.model;

public class Roles {

    private Long rolesId;
    private String name;
    private String description;

    public Roles() {
    }

    public Roles(Long roleId, String name, String description) {
        this.rolesId = roleId;
        this.name = name;
        this.description = description;
    }

    public Long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
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
}