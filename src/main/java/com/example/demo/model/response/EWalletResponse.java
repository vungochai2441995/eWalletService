package com.example.demo.model.response;

import com.example.demo.model.dto.ResultCheckerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EWalletResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ResultCheckerDTO> successList;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ResultCheckerDTO> failList;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer status;
}
