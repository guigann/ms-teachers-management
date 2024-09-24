package com.guigan.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Salary {
  @JsonProperty("amount")
  private Float amount = null;

  @JsonProperty("currency")
  private String currency = null;
}
