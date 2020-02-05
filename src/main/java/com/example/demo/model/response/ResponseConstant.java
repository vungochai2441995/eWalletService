package com.example.demo.model.response;

import com.example.demo.ulti.Constant;

public class ResponseConstant {
    public static CommonResponse responseOK(EWalletResponse detailSuccess, String methodName){return new CommonResponse(Constant.MessageApi.SUCCESS, Constant.ErrorCodeApi.SUCCESS, detailSuccess, methodName);}
}
