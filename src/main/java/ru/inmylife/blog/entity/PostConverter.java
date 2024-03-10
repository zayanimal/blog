package ru.inmylife.blog.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.inmylife.blog.dto.block.PostData;

@Converter
@RequiredArgsConstructor
public class PostConverter implements AttributeConverter<PostData, String> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(PostData postData) {
        return objectMapper.writer().writeValueAsString(postData);
    }

    @Override
    @SneakyThrows
    public PostData convertToEntityAttribute(String s) {
        return objectMapper.readerFor(PostData.class).readValue(s);
    }
}
