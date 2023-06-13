package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ucsal.app.entity.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Integer>{
  
  public Estudante findByMatricula(String matricula);
}
