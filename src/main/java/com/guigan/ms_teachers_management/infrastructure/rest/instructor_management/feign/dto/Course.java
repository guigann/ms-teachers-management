package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Course   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;
}
