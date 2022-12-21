package org.dailyplastic.idnp.prueba.model;

public class Plastic {

    private Integer id;
    //Cambiar por las clases a category y presentation
    private Category category;
    private Presentation presentation;
    private String name;
    private Integer decompositionTime;
    private Integer unitWeight;

    public Plastic() {
    }

    public Plastic(Integer id, Category category, Presentation presentation, String name, Integer decompositionTime, Integer unitWeight) {
        this.id = id;
        this.category = category;
        this.presentation = presentation;
        this.name = name;
        this.decompositionTime = decompositionTime;
        this.unitWeight = unitWeight;

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

    @Override
    public String toString() {
        return "Plastic{" +
                "id=" + id +
                ", category=" + category +
                ", presentation=" + presentation +
                ", name='" + name + '\'' +
                ", decompositionTime=" + decompositionTime +
                ", unitWeight=" + unitWeight +
                '}';
    }
}
