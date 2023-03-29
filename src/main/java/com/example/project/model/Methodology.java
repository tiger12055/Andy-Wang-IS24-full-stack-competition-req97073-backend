package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * An enumeration that represents the different methodologies that can be used for a project.
 * */
public enum Methodology {
    AGILE,
    WATERFALL;

    @JsonCreator
    public static Methodology fromString(String value) {
        return Methodology.valueOf(value.toUpperCase());
    }
}