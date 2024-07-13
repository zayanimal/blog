package ru.inmylife.blog.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.entity.Token;
import ru.inmylife.blog.repository.TokenJpaRepository;

import java.util.Date;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements PersistentTokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    private final HttpServletRequest request;

    private final Pattern BRACES = Pattern.compile("\\((.*?)\\)");

    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        val device = getDevice();
        val username = token.getUsername();

        log.debug("Пришёл запрос на создание токена, пользователь: {}, устройство: {}", username, device);

        tokenJpaRepository.save(new Token()
            .setId(token.getSeries())
            .setUsername(username)
            .setToken(token.getTokenValue())
            .setLastUsed(token.getDate())
            .setDevice(device));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        log.debug("Пришёл запрос на обновление токена");
        tokenJpaRepository.updateToken(series, tokenValue, lastUsed, getDevice());
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        val optionalToken = tokenJpaRepository.findById(seriesId);
        if (optionalToken.isPresent()) {
            val token = optionalToken.get();
            log.debug("Пришёл запрос на получение токена, пользователь: {}, устройство: {}", token.getUsername(), getDevice());
            return new PersistentRememberMeToken(
                token.getUsername(),
                token.getId(),
                token.getToken(),
                token.getLastUsed());
        }

        return null;
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        log.debug("Пришёл запрос на удаление токена, пользователь: {}, устройство: {}", username, getDevice());
        tokenJpaRepository.deleteAllByUsername(username);
    }

    private String getDevice() {
        return BRACES.matcher(request.getHeader("user-agent"))
            .results()
            .findFirst()
            .map(MatchResult::group)
            .map(v -> v.replaceAll("\\(|\\)", ""))
            .orElse("");
    }
}
