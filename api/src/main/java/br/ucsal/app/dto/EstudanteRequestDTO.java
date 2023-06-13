package br.ucsal.app.dto;

// CREATE TABLE est_estudante (
//     est_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//     est_matricula VARCHAR(20) NOT NULL,
//     est_nomecompleto VARCHAR(100) NOT NULL,
//     est_email VARCHAR(100) NOT NULL,
//     est_anoegresso INTEGER NOT NULL
// );

public record EstudanteRequestDTO(String matricula, String nomeCompleto, String email, int anoEgresso) {
  
}
