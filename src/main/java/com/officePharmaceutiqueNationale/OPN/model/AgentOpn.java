package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Agent")
@Getter @Setter
public class AgentOpn extends Utilisateur {

    private String matriculeAgent;
    private String nomAgent;
    private String prenomAgent;

}
