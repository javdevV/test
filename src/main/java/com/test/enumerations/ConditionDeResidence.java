package com.test.enumerations;

public enum ConditionDeResidence {
    SDE("sans date d''effet"), ADE("avec date d''effet");

    String value;

    private ConditionDeResidence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}