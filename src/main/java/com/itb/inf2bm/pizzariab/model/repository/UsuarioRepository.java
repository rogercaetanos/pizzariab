package com.itb.inf2bm.pizzariab.model.repository;


import com.itb.inf2bm.pizzariab.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
