package com.guigan.ms_teachers_management.infrastructure.rest.instructor_management.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wage   {
  @JsonProperty("total")
  private Float total = null;

  @JsonProperty("currency")
  private String currency = null;

}
