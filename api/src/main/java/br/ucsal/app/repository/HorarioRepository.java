package br.ucsal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ucsal.app.entity.HorarioEntity;

import java.util.List;

public interface HorarioRepository extends JpaRepository<HorarioEntity, Integer> {

  @Query("""
    SELECT codigoDisciplina, dis.nome, diaSemana, horarioInicio, horarioFinal
    FROM Horario hor
    INNER JOIN Disciplina dis ON dis.Id = hor.disciplinaId
    INNER JOIN Matricula mat ON mat.estudanteId = dis.Id
    WHERE mat.estudanteId = :estudantId
  """)
  public List<Object> buscarHorarioByEstudanteId(@Param("estudantId") Integer estudanteId);

  @Query("""
    SELECT codigoDisciplina, dis.nome, diaSemana, horarioInicio, horarioFinal
    FROM Horario hor
    INNER JOIN Disciplina dis ON dis.Id = hor.disciplinaId
    WHERE dis.professorId = :professorId
  """)
  public List<Object> buscarHorarioByProfessorId(@Param("professorId") Integer estudanteId);
  
}
