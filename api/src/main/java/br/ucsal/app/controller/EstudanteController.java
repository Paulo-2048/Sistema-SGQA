package br.ucsal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.ucsal.app.repository.EstudanteRepository;
import br.ucsal.app.entity.Estudante;
import br.ucsal.app.dto.EstudanteRequestDTO;

@RestController
@RequestMapping("estudante")
public class EstudanteController {
  
  @Autowired
  private EstudanteRepository repository;
  

  @GetMapping
  public List<Estudante> findAll() {
    List<Estudante> estudantes = repository.findAll();

    return estudantes;
  }

  @GetMapping("/{id}")
  public Estudante findById(@PathVariable Integer id) {
    Estudante estudante = repository.findById(id).orElse(null);

    return estudante;
  }

  @GetMapping("/matricula/{matricula}")
  public Estudante findByMatricula(@PathVariable String matricula) {
    Estudante estudante = repository.findByMatricula(matricula);

    return estudante;
  }

  @PostMapping
  public Estudante create(EstudanteRequestDTO data) {
    Estudante estudante = new Estudante();
    estudante.setMatricula(data.matricula());
    estudante.setNomeCompleto(data.nomeCompleto());
    estudante.setEmail(data.email());
    estudante.setAnoEgresso(data.anoEgresso());

    return repository.save(estudante);
  }

  @PutMapping("/{id}")
  public Estudante update(@PathVariable Integer id, EstudanteRequestDTO data) {
    Estudante estudante = repository.findById(id).orElse(null);

    if (estudante == null) {
      return null;
    }

    estudante.setMatricula(data.matricula());
    estudante.setNomeCompleto(data.nomeCompleto());
    estudante.setEmail(data.email());
    estudante.setAnoEgresso(data.anoEgresso());

    return repository.save(estudante);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Integer id) {
    repository.deleteById(id);

    return "Estudante deletado com sucesso!";
  }
}
