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

        if(dbData.equals("M")){
            return Gender.MALE;
        }
        else if(dbData.equals("F")){
            return Gender.FEMALE;
        }

        return null;
    }
}
