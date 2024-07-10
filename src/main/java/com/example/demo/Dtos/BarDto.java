package com.example.demo.Dtos;

import com.example.demo.Entities.Situation;
import com.example.demo.Entities.Situation_r;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarDto {

    Situation situation;
    Situation_r situation_r;
}
