package com.example.demo.Dtos;

import com.example.demo.Entities.Lot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LotDto {


    private Lot myLot;

    private  Double avReel;
    private Double avPgt;
    List<MarcheDto> marcheDtoList;

    private  String  impact ;

    private  Double Reel;
    private Double Pgt;
    private Double montant;

}
