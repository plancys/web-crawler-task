package com.web.crawler.normalizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlNormalizer implements Normalizer {

    private static final String NORMALIZER_REGEX = "http[s]?://[w\\\\.]*([\\w-]+\\.(\\w{2,3}))";

    @Override
    public String normalize(String url) {

        Matcher m = Pattern.compile(NORMALIZER_REGEX).matcher(url);
        m.find();

        return "http://www." + m.group(1);
    }
}
