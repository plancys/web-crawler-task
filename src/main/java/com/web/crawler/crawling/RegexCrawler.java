package com.web.crawler.crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCrawler {

    private static final String LINK_REGEX = "href=\\\"(.*)\\\"";

    public List<String> find(String source) {

        List<String> list = new ArrayList<>();

        Pattern p = Pattern.compile(LINK_REGEX);

        Matcher m = p.matcher(source);

        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }
}
