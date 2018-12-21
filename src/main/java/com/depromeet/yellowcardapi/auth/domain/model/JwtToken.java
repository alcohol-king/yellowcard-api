package com.depromeet.yellowcardapi.auth.domain.model;

import com.depromeet.yellowcardapi.auth.exception.InvalidAccessTokenException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JwtToken {

    public static final String PREFIX_BEARER_HEADER = "Bearer ";

    private static final Pattern PREFIX_BEARER_PATTERN =
            Pattern.compile("^" + PREFIX_BEARER_HEADER + " *([^ ]+) *$", Pattern.CASE_INSENSITIVE);

    private String value;

    public static JwtToken parse(String header) {
        if (StringUtils.isBlank(header)) {
            throw new InvalidAccessTokenException();
        }

        Matcher matcher = PREFIX_BEARER_PATTERN.matcher(header);
        if (!matcher.matches()) {
            throw new InvalidAccessTokenException();
        }

        return new JwtToken(matcher.group(1));
    }
}
