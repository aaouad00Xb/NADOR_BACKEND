package com.example.demo.Dtos;

import com.example.demo.Entities.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ValidateSituationDto {



    private Situation situation;
     private Long sec_id;
     private String content;

}
