package br.ucsal.app.entity;

import br.ucsal.app.dto.MatriculaRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


// -- Tabela mat_matricula
// CREATE TABLE mat_matricula (
//     mat_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//     mat_dis_id INTEGER REFERENCES dis_disciplina(dis_id) ON DELETE CASCADE,
//     mat_est_id INTEGER REFERENCES est_estudante(est_id) ON DELETE CASCADE
// );

@Table(name = "mat_matricula")
@Entity(name = "Matricula")
@Getter
@Setter
public class MatriculaEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mat_id", nullable = false)
  private Integer id;

  @Column(name = "mat_dis_id", nullable = false)
  private Integer disciplinaId;


  @Column(name = "mat_est_id", nullable = false)
  private Integer estudanteId;

  public MatriculaEntity(MatriculaRequestDTO data) {
    this.disciplinaId = data.disciplinaId();
    this.estudanteId = data.estudanteId();
  }

  public MatriculaEntity() {
  }
}
