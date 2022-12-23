package org.dailyplastic.idnp.prueba.model;

public class Consumption {
    private Integer id;
    private String user;
    private Plastic plastic;
    private Origin origin;
    private String image;
    private String description;
    private Integer units;
    private String updated;


    public Consumption() {
    }

    public Consumption(Integer id, String user, Plastic plastic, Origin origin, String image, String description, Integer units, String updated) {
        this.id = id;
        this.user = user;
        this.plastic = plastic;
        this.origin = origin;
        this.image = image;
        this.description = description;
        this.units = units;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Plastic getPlastic() {
        return plastic;
    }

    public void setPlastic(Plastic plastic) {
        this.plastic = plastic;
    }

    public Plastic getPresentation() {
        return plastic;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getTotalUnitsWeight() {
        return units*plastic.getUnitWeight();
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", plastic=" + plastic +
                ", origin=" + origin +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", units=" + units +
                ", updated='" + updated + '\'' +
                '}';
    }
}