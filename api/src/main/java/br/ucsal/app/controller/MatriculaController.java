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

import br.ucsal.app.repository.MatriculaRepository;
import br.ucsal.app.entity.MatriculaEntity;
import br.ucsal.app.dto.ApiResponse;
import br.ucsal.app.dto.MatriculaRequestDTO;
import br.ucsal.app.dto.ResponseFail;
import br.ucsal.app.dto.ResponseSuccess;

@RestController
@RequestMapping("matricula")
public class MatriculaController {
  
  @Autowired
  private MatriculaRepository repository;
  

  @GetMapping
  public ResponseEntity<ApiResponse> findAll() {
    try{
      List<MatriculaEntity> matriculas = repository.findAll();

      if (matriculas.isEmpty()) {
        throw new Exception("Nenhuma matricula encontrada");
      }

      ResponseSuccess response = new ResponseSuccess("Matriculas listadas com sucesso", matriculas);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar matriculas");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
    try{
      MatriculaEntity matricula = repository.findById(id).orElse(null);

      if (matricula == null) {
        throw new Exception("Matricula não encontrada");
      }

      ResponseSuccess response = new ResponseSuccess("Matricula listada com sucesso", matricula);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar matricula");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(@RequestBody MatriculaRequestDTO data) {
    try{
      MatriculaEntity matricula = new MatriculaEntity(data);
      MatriculaEntity matriculaResponse = repository.save(matricula);

      ResponseSuccess response = new ResponseSuccess("Matricula criada com sucesso", matriculaResponse);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao criar matricula");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(@PathVariable Integer id, MatriculaRequestDTO data) {
    try {
      MatriculaEntity matricula = repository.findById(id).orElse(null);

      if (matricula == null) {
        throw new Exception("Matricula não encontrado");
      }

      matricula.setDisciplinaId(data.disciplinaId());
      matricula.setEstudanteId(data.estudanteId());

      MatriculaEntity matriculaResponse = repository.save(matricula);

      ResponseSuccess response = new ResponseSuccess("Matricula atualizada com sucesso", matriculaResponse);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao atualizar matricula");
      return ResponseEntity.badRequest().body(errorResponse);
    } 
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
    try{
      repository.deleteById(id);

      ResponseSuccess response = new ResponseSuccess("Matricula removida com sucesso");
      return ResponseEntity.ok().body(response.OnlyMessage());
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao remover matricula");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    

  }
}
