package com.test.repository;

import com.test.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

}
