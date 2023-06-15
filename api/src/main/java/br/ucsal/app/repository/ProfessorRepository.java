package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ucsal.app.entity.ProfessorEntity;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Integer> {
  
  public ProfessorEntity findByMatricula(String matricula);

}
