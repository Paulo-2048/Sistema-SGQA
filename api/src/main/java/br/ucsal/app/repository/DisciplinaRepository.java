package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ucsal.app.entity.DisciplinaEntity;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Integer> {
  
}
