package ru.inmylife.blog.service.impl;

import lombok.val;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements PersistentTokenRepository {

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        val tok = "";
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        val token = "";
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        val token = "";
    }
}
