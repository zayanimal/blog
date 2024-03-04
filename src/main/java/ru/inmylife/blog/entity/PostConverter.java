package ru.inmylife.blog.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.inmylife.blog.dto.block.Post;

@Converter
@RequiredArgsConstructor
public class PostConverter implements AttributeConverter<Post, String> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(Post post) {
        return objectMapper.writer().writeValueAsString(post);
    }

    @Override
    @SneakyThrows
    public Post convertToEntityAttribute(String s) {
        return objectMapper.readerFor(Post.class).readValue(s);
    }
}
