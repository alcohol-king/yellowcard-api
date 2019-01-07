package com.depromeet.yellowcardapi.config;

import com.depromeet.yellowcardapi.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Profile("production")
@Component
@RequiredArgsConstructor
public class JwtUserIdHandlerMethodArgumentResolver extends UserIdHandlerMethodArgumentResolver {

    private final JwtTokenProvider tokenProvider;

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String token = tokenProvider.resolveToken(webRequest.getNativeRequest(HttpServletRequest.class));
        return tokenProvider.getUserId(token);
    }
}
