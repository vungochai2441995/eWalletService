package com.example.demo.service.impl;

import com.example.demo.entities.RequestToService;
import com.example.demo.model.dto.ResultCheckerDTO;
import com.example.demo.model.request.RequestDTO;
import com.example.demo.model.response.CommonResponse;
import com.example.demo.model.response.EWalletResponse;
import com.example.demo.model.response.ResponseConstant;
import com.example.demo.repository.EWallServiceRepository;
import com.example.demo.service.IEWalletService;
import com.example.demo.ulti.Constant;
import com.example.demo.ulti.mapper.EWalletServiceMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

@Component
public class EWalletService implements IEWalletService {
    @Autowired
    private EWallServiceRepository eWallServiceRepository;

    @Override
    public CommonResponse approvedApi(List<RequestDTO> requestDTOList) {
        List<ResultCheckerDTO> successList = new CopyOnWriteArrayList<>();
        List<ResultCheckerDTO> failList = new CopyOnWriteArrayList<>();
        String nameOfCurrMethod = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        String methodName = this.getClass().getSimpleName() + "." + nameOfCurrMethod;
        int status = 1;
        if (requestDTOList != null && !requestDTOList.isEmpty()) {
            for (RequestDTO request : requestDTOList) {
                if (request.getObjectID() % 2 == 0) {
                    successList.add(new ResultCheckerDTO(request.getOriginalTransId(), request.getObjectID()));
                    RequestToService requestToService = EWalletServiceMapper.requestDtoToRequestToServiceEntity(request, Constant.ErrorCodeApi.SUCCESS.toString(),
                            Constant.MessageApi.SUCCESS, methodName, "123");
                    RequestToService result = saveData(requestToService);
                } else {
                    failList.add(new ResultCheckerDTO(request.getOriginalTransId(), request.getObjectID()));
                    RequestToService requestToService = EWalletServiceMapper.requestDtoToRequestToServiceEntity(request, Constant.ErrorCodeApi.INVALID_INPUT.toString(),
                            Constant.MessageApi.FAIL, methodName, "123");
                    RequestToService result = saveData(requestToService);
                }
            }

            if (!successList.isEmpty()) {
                if (failList.isEmpty()) status = 1;
                else status = 2;
            } else status = 3;
        }
        EWalletResponse eWalletResponse = new EWalletResponse(successList, failList, status);
        return ResponseConstant.responseOK(eWalletResponse, methodName);
    }

    public RequestToService saveData(RequestToService requestToService) {
        try {
            RequestToService result = eWallServiceRepository.save(requestToService);
            return result;
        } catch (RuntimeException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
    }
}
