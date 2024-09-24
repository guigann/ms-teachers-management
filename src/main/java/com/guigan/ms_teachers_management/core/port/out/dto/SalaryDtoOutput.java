package com.guigan.ms_teachers_management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDtoOutput {
    @JsonProperty("amount")
    private Float amount = null;

    @JsonProperty("currency")
    private String currency = null;

}
