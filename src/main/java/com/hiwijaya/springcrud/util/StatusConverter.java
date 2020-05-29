package com.hiwijaya.springcrud.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Happy Indra Wijaya
 */
@Converter
public class StatusConverter implements AttributeConverter<RentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RentStatus rentStatus) {
        if(rentStatus == null){
            return null;
        }

        return rentStatus.getStatus();

    }

    @Override
    public RentStatus convertToEntityAttribute(Integer dbData) {
        return null;
    }

}
