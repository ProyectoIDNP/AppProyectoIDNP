package org.dailyplastic.idnp.prueba.dto;

public class ConsumptionDto {
    Integer id;
    Integer user;
    Integer plastic;
    Integer origin;
    String image;
    String description;
    Integer unit;
    String updated;

    public ConsumptionDto() {
    }

    public ConsumptionDto(Integer id, Integer user, Integer plastic, Integer origin, String image, String description, Integer unit, String updated) {
        this.id = id;
        this.user = user;
        this.plastic = plastic;
        this.origin = origin;
        this.image = image;
        this.description = description;
        this.unit = unit;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPlastic() {
        return plastic;
    }

    public void setPlastic(Integer plastic) {
        this.plastic = plastic;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "ConsumptionDto{" +
                "id=" + id +
                ", user=" + user +
                ", plastic=" + plastic +
                ", origin=" + origin +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", unit=" + unit +
                ", updated='" + updated + '\'' +
                '}';
    }
}
