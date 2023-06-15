package br.ucsal.app.entity;

import br.ucsal.app.dto.DisciplinaRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// -- Tabela dis_disciplina
// CREATE TABLE dis_disciplina (
//  	  dis_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//     dis_codigodisciplina VARCHAR(10) NOT NULL,
//     dis_nome VARCHAR(100) NOT NULL,
//     dis_pro_id INTEGER REFERENCES pro_professor(pro_id) ON DELETE CASCADE
// );

@Table(name = "dis_disciplina")
@Entity(name = "Disciplina")
@Getter
@Setter
public class DisciplinaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "dis_id", nullable = false)
  private Integer id;

  @Column(name = "dis_codigodisciplina", nullable = false)
  private String codigoDisciplina;

  public void setCodigoDisciplina(String codigoDisciplina) {
    this.codigoDisciplina = codigoDisciplina.toUpperCase();
  }

  @Column(name = "dis_nome", nullable = false)
  private String nome;

  @Column(name = "dis_pro_id", nullable = false)
  private Integer professorId;

  public DisciplinaEntity(DisciplinaRequestDTO data) {
    this.codigoDisciplina = data.codigoDisciplina();
    this.nome = data.nome();
    this.professorId = data.professorId();
  }
}
