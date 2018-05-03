package com.web.crawler.normalizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlNormalizer implements Normalizer {
//TODO Need to test this normalizer strategy, check if https protocol can be applied to all websites
    private static final String NORMALIZER_REGEX = "(http(s)?://)?(www\\.)?([-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*))";

    @Override
    public String normalize(String url) {

        Matcher m = Pattern.compile(NORMALIZER_REGEX).matcher(url);
        m.find();

        if (m.group(4).endsWith("/")) {
            return "https://www." + m.group(4).substring(0, m.group(4).length() - 1);
        }
        return "https://www." + m.group(4);
    }
}
