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

import br.ucsal.app.repository.DisciplinaRepository;
import br.ucsal.app.entity.DisciplinaEntity;
import br.ucsal.app.dto.ApiResponse;
import br.ucsal.app.dto.DisciplinaRequestDTO;
import br.ucsal.app.dto.ResponseFail;
import br.ucsal.app.dto.ResponseSuccess;

@RestController
@RequestMapping("disciplina")
public class DisciplinaController {
  
  @Autowired
  private DisciplinaRepository repository;
  

  @GetMapping
  public ResponseEntity<ApiResponse> findAll() {
    try{
      List<DisciplinaEntity> disciplinas = repository.findAll();

      if (disciplinas.isEmpty()) {
        throw new Exception("Nenhuma disciplina encontrada");
      }

      ResponseSuccess response = new ResponseSuccess("Disciplinas listadas com sucesso", disciplinas);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar disciplinas");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
    try{
      DisciplinaEntity disciplina = repository.findById(id).orElse(null);

      if (disciplina == null) {
        throw new Exception("Disciplina não encontrada");
      }

      ResponseSuccess response = new ResponseSuccess("Disciplina listada com sucesso", disciplina);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar disciplina");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(@RequestBody DisciplinaRequestDTO data) {
    try{
      DisciplinaEntity disciplina = new DisciplinaEntity(data);
      DisciplinaEntity disciplinaResponse = repository.save(disciplina);

      ResponseSuccess response = new ResponseSuccess("Disciplina criada com sucesso", disciplinaResponse);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao criar disciplina");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(@PathVariable Integer id, DisciplinaRequestDTO data) {
    try {
      DisciplinaEntity disciplina = repository.findById(id).orElse(null);

      if (disciplina == null) {
        throw new Exception("Disciplina não encontrado");
      }

      disciplina.setCodigoDisciplina(data.codigoDisciplina());
      disciplina.setNome(data.nome());
      disciplina.setProfessorId(data.professorId());

      DisciplinaEntity disciplinaResponse = repository.save(disciplina);

      ResponseSuccess response = new ResponseSuccess("Disciplina atualizada com sucesso", disciplinaResponse);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao atualizar disciplina");
      return ResponseEntity.badRequest().body(errorResponse);
    } 
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
    try{
      repository.deleteById(id);

      ResponseSuccess response = new ResponseSuccess("Disciplina removida com sucesso");

      return ResponseEntity.ok().body(response.OnlyMessage());
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao remover disciplina");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    

  }
}
