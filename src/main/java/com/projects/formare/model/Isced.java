package com.projects.formare.model;

public enum Isced {
    ISCED0("Educatie timpurie"),
    ISCED1("Invatamant primar"),
    ISCED2("Invatamant gimnazial"),
    ISCED3("Invatamant liceal"),
    ISCED4("Invatamant postliceal"),
    ISCED5("Invatamant superior de scurta durata"),
    ISCED6("Licenta sau nivel echivalent"),
    ISCED7("Master sau nivel echivalent"),
    ISCED8("Doctorat sau nivel echivalent");
    private String label;
    private Isced(String label) {
        this.label = label;
    }

}
