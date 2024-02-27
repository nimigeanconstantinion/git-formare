package com.projects.formare.model;

public enum TipFurnizor {
    AJOFM("intern"),
    CRFPA("CRFPA"),
    FURNIZOR("Furnizor privat");

    private String label;

    TipFurnizor(String label){
        this.label=label;
    }
}

