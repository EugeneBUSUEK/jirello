package com.tinkoffworkshop.jirello.support.converter;

import com.tinkoffworkshop.jirello.model.enums.RoleType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {
    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getRole();
    }

    @Override
    public RoleType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(RoleType.values())
                .filter(roleType -> roleType.getRole().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
