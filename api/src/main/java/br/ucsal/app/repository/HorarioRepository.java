package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ucsal.app.entity.HorarioEntity;

import java.util.List;

public interface HorarioRepository extends JpaRepository<HorarioEntity, Integer> {

  @Query("""
          SELECT dis_codigodisciplina, dis_nome, hor_dia_semana, hor_horario_inicio, hor_horario_final FROM hor_horario
          INNER JOIN dis_disciplina ON dis_id = hor_dis_id
          INNER JOIN mat_matricula ON mat_dis_id = dis_id
          WHERE mat_est_id = :estudantId
  """)
  public List<HorarioEntity> findByEstudantId(@Param("estudantId") Integer estudanteId);
  
}
