package br.ucsal.app.dto;

public enum DiasSemanaEnum {
  SEGUNDA(1),
  TERCA(2),
  QUARTA(3),
  QUINTA(4),
  SEXTA(5),
  SABADO(6),
  DOMINGO(7);

  private final Integer value;

  DiasSemanaEnum(Integer value) {
    this.value = value;
  }

  public Integer value() {
    return value;
  }

  public static DiasSemanaEnum fromValue(Integer value) {
    for (DiasSemanaEnum dia : DiasSemanaEnum.values()) {
      if (dia.value.equals(value)) {
        return dia;
      }
    }
    return null;
  }
}
