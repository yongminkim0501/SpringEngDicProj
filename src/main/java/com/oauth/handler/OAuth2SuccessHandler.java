package com.oauth.handler;

import com.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // OAuth2User에서 email 추출
        String email = (String) oAuth2User.getAttributes().get("email");

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(email);

        // 응답 헤더에 JWT 토큰 추가
        response.addHeader("Authorization", "Bearer " + token);

        // 프론트엔드로 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, "/oauth2/redirect?token=" + token);
    }
}