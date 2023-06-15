package br.ucsal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.ucsal.app.repository.EstudanteRepository;
import br.ucsal.app.entity.EstudanteEntity;
import br.ucsal.app.dto.ApiResponse;
import br.ucsal.app.dto.EstudanteRequestDTO;
import br.ucsal.app.dto.ResponseFail;
import br.ucsal.app.dto.ResponseSuccess;

@RestController
@RequestMapping("estudante")
public class EstudanteController {
  
  @Autowired
  private EstudanteRepository repository;
  

  @GetMapping
  public ResponseEntity<ApiResponse> findAll() {
    try{
      List<EstudanteEntity> estudantes = repository.findAll();

      if (estudantes.isEmpty()) {
        throw new Exception("Nenhum estudante encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Estudantes listados com sucesso", estudantes);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar estudantes");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
    try{
      EstudanteEntity estudante = repository.findById(id).orElse(null);

      if (estudante == null) {
        throw new Exception("Estudante não encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Estudante listado com sucesso", estudante);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar estudante");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @GetMapping("/matricula/{matricula}")
  public ResponseEntity<ApiResponse> findByMatricula(@PathVariable String matricula) {
    try{
      EstudanteEntity estudante = repository.findByMatricula(matricula);

      if (estudante == null) {
        throw new Exception("Estudante não encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Estudante listado com sucesso", estudante);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar estudante");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(@RequestBody EstudanteRequestDTO data) {
    try{
      EstudanteEntity estudante = new EstudanteEntity(data);
      EstudanteEntity estudanteResponse = repository.save(estudante);

      ResponseSuccess response = new ResponseSuccess("Estudante criado com sucesso", estudanteResponse);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao criar estudante");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody EstudanteRequestDTO data) {
    try {
      EstudanteEntity estudante = repository.findById(id).orElse(null);

      if (estudante == null) {
        throw new Exception("Estudante não encontrado");
      }

      estudante.setMatricula(data.matricula());
      estudante.setNomeCompleto(data.nomeCompleto());
      estudante.setEmail(data.email());
      estudante.setAnoEgresso(data.anoEgresso());

      EstudanteEntity estudanteResponse = repository.save(estudante);

      ResponseSuccess response = new ResponseSuccess("Estudante atualizado com sucesso", estudanteResponse);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return ResponseEntity.badRequest().build();
    } 
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
    try{
      repository.deleteById(id);

      ResponseSuccess response = new ResponseSuccess("Estudante removido com sucesso");
      return ResponseEntity.ok().body(response.OnlyMessage());
    } catch(Exception e){
      System.out.println(e.getMessage());
      ResponseFail error = new ResponseFail("Erro ao remover estudante");
      return ResponseEntity.badRequest().body(error);
    }
    

  }
}
