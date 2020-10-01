package com.test.repository;

import com.test.domain.ContratAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ContratAuditRepository extends JpaRepository<ContratAudit, Long> {
 @Query("select ca from ContratAudit ca where ca.id = ("
         + "select max(ca1.id) from ContratAudit ca1 where ca1.idContrat = :idContrat)")
 ContratAudit findByMaxIdContrat(@Param("idContrat") Long idContrat);

 }
