package br.ucsal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.ucsal.app.repository.ProfessorRepository;
import br.ucsal.app.entity.ProfessorEntity;
import br.ucsal.app.dto.ProfessorRequestDTO;
import br.ucsal.app.dto.ApiResponse;
import br.ucsal.app.dto.ResponseFail;
import br.ucsal.app.dto.ResponseSuccess;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("professor")
public class ProfessorController {
  
  @Autowired
  private ProfessorRepository repository;
  

  @GetMapping
  public ResponseEntity<ApiResponse> findAll() {
    try{
      List<ProfessorEntity> professores = repository.findAll();

      if (professores.isEmpty()) {
        throw new Exception("Nenhum professor encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Professores listados com sucesso", professores);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar professores");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
    try{
      ProfessorEntity professor = repository.findById(id).orElse(null);

      if (professor == null) {
        throw new Exception("Professor não encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Professor listado com sucesso", professor);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar professor");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @GetMapping("/matricula/{matricula}")
  public ResponseEntity<ApiResponse> findByMatricula(@PathVariable String matricula) {
    try{
      ProfessorEntity professor = repository.findByMatricula(matricula);

      if (professor == null) {
        throw new Exception("Professor não encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Professor listado com sucesso", professor);

      return ResponseEntity.ok().body(response);

    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar professor");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(@RequestBody ProfessorRequestDTO data) {
    try{
      ProfessorEntity professor = new ProfessorEntity(data);
      ProfessorEntity professorResponse = repository.save(professor);

      ResponseSuccess response = new ResponseSuccess("Professor criado com sucesso", professorResponse);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao criar professor");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(@PathVariable Integer id, ProfessorRequestDTO data) {
    try {
      ProfessorEntity professor = repository.findById(id).orElse(null);

      if (professor == null) {
        throw new Exception("Professor não encontrado");
      }

      professor.setMatricula(data.matricula());
      professor.setNomeCompleto(data.nomeCompleto());
      professor.setEmail(data.email());
      professor.setAnoEgresso(data.anoEgresso());

      ProfessorEntity professorResponse = repository.save(professor);

      ResponseSuccess response = new ResponseSuccess("Professor atualizado com sucesso", professorResponse);

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao atualizar professor");
      return ResponseEntity.badRequest().body(errorResponse);
    } 
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
    try{
      repository.deleteById(id);

      ResponseSuccess response = new ResponseSuccess("Professor removido com sucesso");
      return ResponseEntity.ok().body(response.OnlyMessage());
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao remover professor");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }
}
