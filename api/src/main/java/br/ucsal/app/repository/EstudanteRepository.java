package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ucsal.app.entity.EstudanteEntity;

public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Integer>{
  
  public EstudanteEntity findByMatricula(String matricula);
}
