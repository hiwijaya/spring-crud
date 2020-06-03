package com.hiwijaya.springcrud.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Happy Indra Wijaya
 */
@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {

        if(gender == null){
            return null;
        }

        return gender.getSymbol();

    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        if("M".equals(dbData)){
            return Gender.MALE;
        }
        else if("F".equals(dbData)){
            return Gender.FEMALE;
        }

        return null;
    }
}
