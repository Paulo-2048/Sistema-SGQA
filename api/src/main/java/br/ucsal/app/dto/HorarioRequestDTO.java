package br.ucsal.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioRequestDTO(Integer disciplinaId, DiasSemanaEnum diaDaSemana, LocalDate  dataInicio, LocalDate dataFinal,  LocalTime horarioInicio, LocalTime horarioFinal){
  
}
