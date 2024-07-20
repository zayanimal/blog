package ru.rabbit.app.service.impl;

import com.ibm.icu.text.Transliterator;
import org.springframework.stereotype.Service;
import ru.rabbit.app.service.LinkService;

import java.util.regex.Pattern;

@Service
public class LinkServiceImpl implements LinkService {

    private final Transliterator TRANSLITERATOR = Transliterator.getInstance("Russian-Latin/BGN");

    private final Pattern WHITE_SPACE = Pattern.compile("\\s");

    private final Pattern ANY_SYMBOL = Pattern.compile("[^\\w\\-]");

    @Override
    public String transliterate(String str) {
        return TRANSLITERATOR.transliterate(str)
            .toLowerCase()
            .replaceAll(WHITE_SPACE.pattern(), "-")
            .replaceAll(ANY_SYMBOL.pattern(), "");
    }
}
