package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.AgentOpn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restAgentOpn")
public interface AgentOpnRepository extends JpaRepository<AgentOpn, String> {
}
