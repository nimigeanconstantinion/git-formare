package com.projects.formare.model;

import java.util.HashMap;
import java.util.Map;

public enum TipCurs {
    COMPETENTE_COMUNE("competente comune"),
    PERFECTIONARE("perfectionare"),
    SPECIALIZARE("specializare"),
    INITIERE("initiere"),
    CALIFICARE("calificare");
    private String label;

    private TipCurs(String label) {
        this.label = label;
    }

    public static TipCurs getByLabel(String lbl) {
        if (lbl == null) {
            return null;
        }
        Map<String, TipCurs> map = new HashMap<>();
        for (TipCurs value : values()) {
            map.put(value.label, value);
        }
        return map.get(lbl);
    }

    public static String getLabel(TipCurs n) {
        return n.label;
    }
}
