package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ucsal.app.entity.HorarioEntity;

public interface HorarioRepository extends JpaRepository<HorarioEntity, Integer> {
  
}
