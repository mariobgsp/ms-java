package com.example.msorder.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.msorder.config.properties.AppProperties;
import com.example.msorder.model.logs.ServiceLog;
import com.example.msorder.model.rqrs.request.RequestInfo;
import com.example.msorder.model.rqrs.response.ResponseInfo;
import com.example.msorder.service.KafkaServices;
import com.example.msorder.utils.CommonUtils;
import com.example.msorder.utils.LogsUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BaseUsecase {

    @Autowired
    private AppProperties appProperties;
    @Autowired
    private KafkaServices kafkaServices;

    public void publish(RequestInfo requestInfo, ResponseInfo<Object> responseInfo){

        // construct logs
        try{
            ServiceLog serviceLog = LogsUtils.construcInsertLogs(requestInfo, responseInfo);
            log.info("Publish log {}", appProperties.isPUBLISH_LOG_KAFKA());
            if(appProperties.isPUBLISH_LOG_KAFKA()){
                kafkaServices.publish(CommonUtils.gson.toJson(serviceLog));
            }
        }catch (Exception e){
            log.error("{}",e.getMessage());
        }
    }


    
}
