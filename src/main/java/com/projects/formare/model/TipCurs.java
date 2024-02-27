package com.projects.formare.model;

public enum TipCurs {
    TIP1("competente comune"),
    TIP2("perfectionare"),
    TIP3("specializare"),
    TIP4("initiere"),
    T5("calificare");
    private String label;

    private TipCurs(String label) {
        this.label = label;
    }

}
