package br.ucsal.app.dto;

import java.util.Optional;

public class ResponseSuccess extends ApiResponse {

  private Optional<Object> data;
  
  public ResponseSuccess(String message) {
    super(message);
  }

  public ResponseSuccess(String message, Object data) {
    super(message);
    this.data = Optional.ofNullable(data);
  }
  
  public Optional<Object> getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = Optional.ofNullable(data);
  }

  public ApiResponse OnlyMessage() {
    return new ApiResponse(getMessage());
  }
}
