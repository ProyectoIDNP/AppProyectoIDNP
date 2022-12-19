package org.dailyplastic.idnp.prueba.model;

public class Plastic {

    private Integer id;
    //Cambiar por las clases a category y presentation
    private Category category;
    private Presentation presentation;
    private String name;
    private Integer decompositionTime;
    private Integer unitWeight;
    private String created;
    private String updated;


    public Plastic() {
    }

    public Plastic(Integer id, Category category, Presentation presentation, String name, Integer decompositionTime, Integer unitWeight, String created, String updated) {
        this.id = id;
        this.category = category;
        this.presentation = presentation;
        this.name = name;
        this.decompositionTime = decompositionTime;
        this.unitWeight = unitWeight;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDecompositionTime() {
        return decompositionTime;
    }

    public void setDecompositionTime(Integer decompositionTime) {
        this.decompositionTime = decompositionTime;
    }

    public Integer getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(Integer unitWeight) {
        this.unitWeight = unitWeight;
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

    @Override
    public String toString() {
        return "Plastic{" +
                "id=" + id +
                ", category=" + category +
                ", presentation=" + presentation +
                ", name='" + name + '\'' +
                ", decompositionTime=" + decompositionTime +
                ", unitWeight=" + unitWeight +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
