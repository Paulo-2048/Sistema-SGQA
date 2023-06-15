package br.ucsal.app.entity;

import br.ucsal.app.dto.ProfessorRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// -- Tabela pro_professor
// CREATE TABLE pro_professor (
//     pro_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//     pro_matricula VARCHAR(20) NOT NULL,
//     pro_nomecompleto VARCHAR(100) NOT NULL,
//     pro_email VARCHAR(100) NOT NULL,
//     pro_anoegresso INTEGER NOT NULL
// );

@Table(name = "pro_professor")
@Entity(name = "Professor")
@Getter
@Setter
public class ProfessorEntity {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pro_id", nullable = false)
  private Integer id;

  @Column(name = "pro_matricula", nullable = false)
  private String matricula;

  @Column(name = "pro_nomecompleto", nullable = false)
  private String nomeCompleto;

  @Column(name = "pro_email", nullable = false)
  private String email;

  @Column(name = "pro_anoegresso", nullable = false)
  private Integer anoEgresso;


  public ProfessorEntity(ProfessorRequestDTO data) {
    this.matricula = data.matricula();
    this.nomeCompleto = data.nomeCompleto();
    this.email = data.email();
    this.anoEgresso = data.anoEgresso();
  }

  public ProfessorEntity() {
  }
}
