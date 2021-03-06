package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private Long objectID;
    private String requestServiceId;
    private Date requestTime;
    private String requestBody;
    private String originalTransId;
}
