package com.example.sharded_saga_wallet.services.saga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class SagaContext {

    private Map<String, Object> data;

    public SagaContext(Map<String, Object> data) {
        this.data = data != null ? data : new HashMap<>();
    }

    public void putData(String key, Object value){
        data.put(key,value);
    }
    public Object getData(String key){
        return data.get(key);
    }

    public Long getLong(String key){
        Object value = getData(key);
        if(value instanceof Number){
            return ((Number) value).longValue();
        }
        return null;
    }


    public BigDecimal getBigDecimal(String key){
        Object value = getData(key);
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        return null;
    }
}
