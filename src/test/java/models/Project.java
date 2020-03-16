package models;

public class Project {
    String name;
    String description;
    String domain;

    public Project(String name, String description, String domain) {
        this.name = name;
        this.description = description;
        this.domain = domain;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
