package org.springframework.sbm.websocket;

public class HelloMessageModel {

  private String name;

  public HelloMessageModel() {
  }

  public HelloMessageModel(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}