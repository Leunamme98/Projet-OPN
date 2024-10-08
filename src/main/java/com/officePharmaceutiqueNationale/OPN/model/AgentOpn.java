package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("Agent")
@Getter @Setter
public class AgentOpn extends Utilisateur {

    private String matriculeAgent;

}
