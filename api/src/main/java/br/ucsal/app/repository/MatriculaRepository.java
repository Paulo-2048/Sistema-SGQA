package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ucsal.app.entity.MatriculaEntity;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Integer> {
  
}
