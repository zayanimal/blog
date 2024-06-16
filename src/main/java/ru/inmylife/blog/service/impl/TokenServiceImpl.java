package ru.inmylife.blog.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.entity.Token;
import ru.inmylife.blog.repository.TokenJpaRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements PersistentTokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        val username = token.getUsername();
        tokenJpaRepository.deleteAllByUsername(username);
        tokenJpaRepository.save(new Token()
            .setId(token.getSeries())
            .setUsername(username)
            .setToken(token.getTokenValue())
            .setLastUsed(token.getDate()));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        tokenJpaRepository.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        val optionalToken = tokenJpaRepository.findById(seriesId);
        if (optionalToken.isPresent()) {
            val token = optionalToken.get();
            return new PersistentRememberMeToken(
                token.getUsername(),
                token.getId(),
                token.getToken(),
                token.getLastUsed());
        }

        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        tokenJpaRepository.deleteAllByUsername(username);
    }
}
