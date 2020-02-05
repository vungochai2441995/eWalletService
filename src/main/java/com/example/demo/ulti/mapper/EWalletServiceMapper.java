package com.example.demo.ulti.mapper;

import com.example.demo.entities.RequestToService;
import com.example.demo.model.dto.ResultCheckerDTO;
import com.example.demo.model.request.RequestDTO;
import com.example.demo.model.response.CommonResponse;
import com.example.demo.model.response.EWalletResponse;
import com.example.demo.ulti.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class EWalletServiceMapper {
    public static RequestToService requestDtoToRequestToServiceEntity(RequestDTO requestDTO, String responseCode
            , String responseMsg, String methodName, String responseId) {

        String requestBody = objectToJsonString(requestDTO);
        ResultCheckerDTO resultCheckerDTO = new ResultCheckerDTO(requestDTO.getOriginalTransId(),requestDTO.getObjectID());
        List<ResultCheckerDTO> resultCheckerDTOList = new CopyOnWriteArrayList<>();
        resultCheckerDTOList.add(resultCheckerDTO);
        EWalletResponse eWalletResponse = new EWalletResponse();
        if (responseCode.equals(Constant.ErrorCodeApi.SUCCESS)) {
            eWalletResponse.setSuccessList(resultCheckerDTOList);
            eWalletResponse.setFailList(null);
            eWalletResponse.setStatus(null);
        }else {
            eWalletResponse.setSuccessList(null);
            eWalletResponse.setFailList(resultCheckerDTOList);
            eWalletResponse.setStatus(null);
        }

        CommonResponse commonResponse = new CommonResponse(responseCode,responseMsg,eWalletResponse,methodName);

        String responseBody = objectToJsonString(commonResponse);

        RequestToService requestToService = new RequestToService();
        requestToService.setObjectId(requestDTO.getObjectID());
        requestToService.setRequestBody(requestBody);
        requestToService.setRequestServiceId(requestDTO.getRequestServiceId());
        requestToService.setResponseCode(responseCode);
        requestToService.setResponseMsg(responseMsg);
        requestToService.setRequestTime(requestDTO.getRequestTime());
        requestToService.setResponseTime(new Date());
        requestToService.setResponseServiceId(responseId);
        requestToService.setResponseBody(responseBody);
        return requestToService;
    }

    public static String objectToJsonString(Object objectParsed) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = new String();
        try {
            jsonString = objectMapper.writeValueAsString(objectParsed);
            return jsonString;
        }catch (JsonProcessingException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
    }
}
