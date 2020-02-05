package com.example.demo.service;

import com.example.demo.model.request.RequestDTO;
import com.example.demo.model.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEWalletService {
    CommonResponse approvedApi(List<RequestDTO> requestDTOList);
}
