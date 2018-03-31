package com.web.crawler.crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexCrawler {

    private static final String LINK_REGEX = "href=\\\"(.*)\\\"";
    private static final String CHECK_LINK_REGEX = "www(.*)|https(.*)|http(.*)";
    private static final String MAIN_LINK_REGEX = "(.*)\\.pl|(.*)\\.org|(.*)\\.com";

    public List<String> find(String url, String websiteSource) {

        List<String> list = new ArrayList<>();

        Pattern p = Pattern.compile(LINK_REGEX);

        Matcher m = p.matcher(websiteSource);

        while (m.find()) {
                list.add(m.group(1));
        }
        return list.stream()
                .map(link -> checkLinks(url, link))
                .collect(Collectors.toList());
    }

    private String checkLinks(String url, String link){

        Pattern p = Pattern.compile(CHECK_LINK_REGEX);
        Matcher m = p.matcher(link);
        url = extractMainLink(url);

        if(url.endsWith("/"))
            url = url.substring(0, url.length() -1);

        if (m.find()) {
            return link;
        }

        return url + link;
    }
    private String extractMainLink(String url) {

        Pattern p = Pattern.compile(MAIN_LINK_REGEX);
        Matcher m = p.matcher(url);

        if(m.find())
        return m.group(0);

        return url;
    }
}
