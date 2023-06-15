package br.ucsal.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioRequestDTO(Integer disciplinaId, String diaDaSemana, LocalDate  dataInicio, LocalDate dataFinal,  LocalTime horarioInicio, LocalTime horarioFinal){
  
}
