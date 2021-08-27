package com.projetoG5RedeSocial.repository;

import java.util.List;

import com.projetoG5RedeSocial.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long>{

	public List<Tema>findAllByDescricaoContainingIgnoreCase(String descricao);
}
