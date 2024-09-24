package com.guigan.ms_teachers_management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {
    @JsonProperty("amount")
    private Float amount;

    @JsonProperty("currency")
    private String currency;
}
