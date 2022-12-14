package org.dailyplastic.idnp.prueba.model;

public class Consumption {
    private Integer id;
    private String plastic;
    private String user;
    private String origin;
    private String image;
    private String description;
    private Integer units;
    private String created;
    private String updated;

    public Consumption() {
    }

    public Consumption(Integer id, String plastic, String user, String origin, String image, String description, Integer units, String created, String updated) {
        this.id = id;
        this.plastic = plastic;
        this.user = user;
        this.origin = origin;
        this.image = image;
        this.description = description;
        this.units = units;
        this.created = created;
        this.updated = updated;
    }

    public String getPlastic() {
        return plastic;
    }

    public void setPlastic(String plastic) {
        this.plastic = plastic;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", plastic='" + plastic + '\'' +
                ", user='" + user + '\'' +
                ", origin='" + origin + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", units=" + units +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
