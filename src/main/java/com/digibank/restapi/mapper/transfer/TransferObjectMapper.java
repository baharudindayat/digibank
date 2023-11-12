package com.digibank.restapi.mapper.transfer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransferObjectMapper extends ObjectMapper {

    public TransferObjectMapper(){
        this.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN,true);
    }
}
