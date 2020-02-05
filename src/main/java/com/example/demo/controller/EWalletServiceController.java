package com.example.demo.controller;

import com.example.demo.annotation.Loggable;
import com.example.demo.model.request.RequestDTO;
import com.example.demo.model.response.CommonResponse;
import com.example.demo.service.IEWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/e-wallet-service",consumes = "application/json", produces = "application/json")
@Validated
public class EWalletServiceController {
    @Autowired
    IEWalletService IEWalletService;

    @Loggable
    @PostMapping()
    public ResponseEntity<CommonResponse> EWalletService(@RequestBody(required = true) List<RequestDTO> requestDTOList) {
        CommonResponse commonResponse = IEWalletService.approvedApi(requestDTOList);
        return  ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
