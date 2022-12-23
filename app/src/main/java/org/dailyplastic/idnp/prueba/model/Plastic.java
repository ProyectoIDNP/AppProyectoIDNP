package org.dailyplastic.idnp.prueba.model;

public class Plastic {

    private Integer id;
    //Cambiar por las clases a category y presentation
    private Category category;
    private Presentation presentation;
    private String name;
    private Integer decomposition_time;
    private Integer unit_weight;

    public Plastic() {
    }

    public Plastic(Integer id, Category category, Presentation presentation, String name, Integer decompositionTime, Integer unitWeight) {
        this.id = id;
        this.category = category;
        this.presentation = presentation;
        this.name = name;
        this.decomposition_time = decompositionTime;
        this.unit_weight = unitWeight;

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
        return decomposition_time;
    }

    public void setDecompositionTime(Integer decomposition_time) {
        this.decomposition_time = decomposition_time;
    }

    public Integer getUnitWeight() {
        return unit_weight;
    }

    public void setUnitWeight(Integer unit_weight) {
        this.unit_weight = unit_weight;
    }

    @Override
    public String toString() {
        return name;
    }
}
