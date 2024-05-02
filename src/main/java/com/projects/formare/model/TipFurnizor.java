package com.projects.formare.model;

import java.util.HashMap;
import java.util.Map;

public enum TipFurnizor {
    AJOFM("AJOFM"),
    CRFPA("CRFPA"),
    FURNIZOR("FURNIZOR AUTORIZAT");

    private String label;

    TipFurnizor(String label) {
        this.label = label;
    }

    public static TipFurnizor getByLabel(String lbl) {
        if (lbl == null) {
            return null;
        }
        Map<String, TipFurnizor> map = new HashMap<>();
        for (TipFurnizor value : values()) {
            map.put(value.label, value);
        }
        return map.get(lbl);
    }
}

