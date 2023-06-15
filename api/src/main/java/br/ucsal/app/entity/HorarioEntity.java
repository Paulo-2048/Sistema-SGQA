package br.ucsal.app.entity;

import br.ucsal.app.dto.DiasSemanaEnum;
import br.ucsal.app.dto.HorarioRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

// -- Tabela hor_horario
// CREATE TABLE hor_horario (
//     hor_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//     hor_dis_id INTEGER REFERENCES dis_disciplina(dis_id) ON DELETE CASCADE,
//     hor_data_inicio DATE NOT NULL,
//     hor_data_final DATE NOT NULL,
//     hor_dia_semana VARCHAR(20) NOT NULL,
//     hor_horario_inicio TIME NOT NULL,
//     hor_horario_final TIME NOT NULL
// );

@Table(name = "hor_horario")
@Entity(name = "Horario")
@Getter
@Setter
public class HorarioEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hor_id", nullable = false)
  private Integer id;

  @Column(name = "hor_dis_id", nullable = false)
  private Integer disciplinaId;

  @Column(name = "hor_data_inicio", nullable = false)
  private LocalDate dataInicio;

  @Column(name = "hor_data_final", nullable = false)
  private LocalDate dataFinal;

  @Column(name = "hor_dia_semana", nullable = false)
  @Enumerated(EnumType.STRING)
  private DiasSemanaEnum diaSemana;

  @Column(name = "hor_horario_inicio", nullable = false)
  private LocalTime horarioInicio;

  @Column(name = "hor_horario_final", nullable = false)
  private LocalTime horarioFinal;

  public HorarioEntity(HorarioRequestDTO data) {
    this.disciplinaId = data.disciplinaId();
    this.dataInicio = data.dataInicio();
    this.dataFinal = data.dataFinal();
    this.diaSemana = DiasSemanaEnum.fromValue(data.diaDaSemana());
    this.horarioInicio = data.horarioInicio();
    this.horarioFinal = data.horarioFinal();
  }

  public HorarioEntity() {
  }

}
