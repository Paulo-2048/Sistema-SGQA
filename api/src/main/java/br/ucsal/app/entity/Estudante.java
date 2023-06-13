package br.ucsal.app.entity;

import br.ucsal.app.dto.EstudanteRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// CREATE TABLE est_estudante (
//     est_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//     est_matricula VARCHAR(20) NOT NULL,
//     est_nomecompleto VARCHAR(100) NOT NULL,
//     est_email VARCHAR(100) NOT NULL,
//     est_anoegresso INTEGER NOT NULL
// );

@Table(name = "est_estudante")
@Entity(name = "Estudante")
@Getter
@Setter
public class Estudante {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "est_id", nullable = false)
  private Integer id;

  @Column(name = "est_matricula", nullable = false)
  private String matricula;

  @Column(name = "est_nomecompleto", nullable = false)
  private String nomeCompleto;

  @Column(name = "est_email", nullable = false)
  private String email;

  @Column(name = "est_anoegresso", nullable = false)
  private Integer anoEgresso;


  public Estudante(EstudanteRequestDTO data) {
    this.matricula = data.matricula();
    this.nomeCompleto = data.nomeCompleto();
    this.email = data.email();
    this.anoEgresso = data.anoEgresso();
  }

  public Estudante() {
  }
}

