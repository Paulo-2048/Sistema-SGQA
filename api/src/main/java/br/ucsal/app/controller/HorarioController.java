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

import br.ucsal.app.repository.HorarioRepository;
import br.ucsal.app.entity.HorarioEntity;
import br.ucsal.app.dto.ApiResponse;
import br.ucsal.app.dto.DiasSemanaEnum;
import br.ucsal.app.dto.HorarioRequestDTO;
import br.ucsal.app.dto.ResponseFail;
import br.ucsal.app.dto.ResponseSuccess;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("horario")
public class HorarioController {
  
  @Autowired
  private HorarioRepository repository;
  

  @GetMapping
  public ResponseEntity<ApiResponse> findAll() {
    try{
      List<HorarioEntity> horarios = repository.findAll();

      if (horarios.isEmpty()) {
        throw new Exception("Nenhum horario encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Horarios listados com sucesso", horarios);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar horarios");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
    try{
      HorarioEntity horario = repository.findById(id).orElse(null);

      if (horario == null) {
        throw new Exception("Horario não encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Horario listado com sucesso", horario);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar horario");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @GetMapping("/estudante/{id}")
  public ResponseEntity<ApiResponse> findByEstudanteId(@PathVariable Integer id) {
    try{
      List<HorarioEntity> horarios = repository.buscarHorarioByEstudanteId(id);

      if (horarios.isEmpty()) {
        throw new Exception("Nenhum horario encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Horarios listados com sucesso", horarios);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar horarios");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

  @GetMapping("/professor/{id}")
  public ResponseEntity<ApiResponse> findByProfessorId(@PathVariable Integer id) {
    try{
      List<HorarioEntity> horarios = repository.buscarHorarioByProfessorId(id);

      if (horarios.isEmpty()) {
        throw new Exception("Nenhum horario encontrado");
      }

      ResponseSuccess response = new ResponseSuccess("Horarios listados com sucesso", horarios);
      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao listar horarios");
      return ResponseEntity.badRequest().body(errorResponse);
    }
  }


  @PostMapping
  public ResponseEntity<ApiResponse> create(@RequestBody HorarioRequestDTO data) {
    try{
      HorarioEntity horario = new HorarioEntity(data);
      HorarioEntity horarioResponse = repository.save(horario);

      ResponseSuccess response = new ResponseSuccess("Horario criado com sucesso", horarioResponse);

      return ResponseEntity.ok().body(response);
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao criar horario");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(@PathVariable Integer id, HorarioRequestDTO data) {
    try {
      HorarioEntity horario = repository.findById(id).orElse(null);

      if (horario == null) {
        throw new Exception("Horario não encontrado");
      }

      horario.setDisciplinaId(data.disciplinaId());
      horario.setDataInicio(data.dataInicio());
      horario.setDataFinal(data.dataFinal());
      horario.setHorarioInicio(data.horarioInicio());
      horario.setHorarioFinal(data.horarioFinal());
      horario.setDiaSemana(DiasSemanaEnum.fromValue(data.diaDaSemana()));

      HorarioEntity horarioResponse = repository.save(horario);

      ResponseSuccess response = new ResponseSuccess("Horario atualizado com sucesso", horarioResponse);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao atualizar horario");
      return ResponseEntity.badRequest().body(errorResponse);
    } 
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
    try{
      repository.deleteById(id);

      ResponseSuccess response = new ResponseSuccess("Horario removido com sucesso");
      return ResponseEntity.ok().body(response.OnlyMessage());
    } catch(Exception e){
      System.out.println(e.getMessage());

      ResponseFail errorResponse = new ResponseFail("Erro ao remover horario");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    

  }
}
