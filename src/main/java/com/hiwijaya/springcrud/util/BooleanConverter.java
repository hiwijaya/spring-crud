package com.hiwijaya.springcrud.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Happy Indra Wijaya
 */
@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean property) {    // object --> database
        if(property != null){
            return property ? "Y" : "N";
        }
        return null;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {     // database --> object
        if(dbData != null){
            return dbData.equals("Y");
        }
        return null;
    }

}
