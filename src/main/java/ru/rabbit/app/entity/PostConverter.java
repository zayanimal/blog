package ru.rabbit.app.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.rabbit.app.dto.block.Block;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class PostConverter implements AttributeConverter<List<Block>, String> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(List<Block> postData) {
        return objectMapper.writer().writeValueAsString(postData);
    }

    @Override
    @SneakyThrows
    public List<Block> convertToEntityAttribute(String s) {
        return objectMapper.readerFor(new TypeReference<List<Block>>() {}).readValue(s);
    }
}
