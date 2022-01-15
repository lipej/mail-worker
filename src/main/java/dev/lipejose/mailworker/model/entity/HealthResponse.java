package dev.lipejose.mailworker.model.entity;

public class HealthResponse {
  private Boolean success;
  private String message;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean status) {
    this.success = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
