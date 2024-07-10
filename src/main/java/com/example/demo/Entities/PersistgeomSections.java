package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersistgeomSections {
    private Long idprovince;
    private String geometry;
    private Long section;
}
