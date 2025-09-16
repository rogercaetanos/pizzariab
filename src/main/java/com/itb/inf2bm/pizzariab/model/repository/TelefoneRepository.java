package com.itb.inf2bm.pizzariab.model.repository;

import com.itb.inf2bm.pizzariab.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
