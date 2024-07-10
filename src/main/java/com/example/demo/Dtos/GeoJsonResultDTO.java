package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GeoJsonResultDTO {
    private String type;
    private Map<String, Object> properties;
    private Object geometry;

    // Constructors, getters, and setters
}
