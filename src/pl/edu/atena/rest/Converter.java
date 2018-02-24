package pl.edu.atena.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

import org.vertx.java.core.json.impl.Json;


@Convert
public class Converter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null) {
            return null;
        } else {
            return Json.encode(attribute);
        }
    }
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return new ArrayList<>();
        } else {
            return Json.decodeValue(dbData, List.class);
        }
    }
}
