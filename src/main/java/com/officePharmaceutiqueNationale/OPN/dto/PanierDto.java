package com.officePharmaceutiqueNationale.OPN.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PanierDto {


    private String id;

    @NotNull(message = "Le client ne peut pas être nul")
    private ClientDto clientDto;



}
