package com.web.crawler.crawling;

import com.web.crawler.model.Page;
import com.web.crawler.normalizer.UrlNormalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexLinkCrawler {
//TODO implement better regex`s
    private static final String LINK_REGEX = "(href|src)=\\\"([\\w\\d-_:\\./]+)\\\"";
    private static final String CHECK_LINK_REGEX = "www(.*)|https(.*)|http(.*)";
    private static final String MAIN_LINK_REGEX = "(.*)\\.pl|(.*)\\.org|(.*)\\.com";

    public List<String> find(Page page) {

        List<String> list = new ArrayList<>();

        Pattern p = Pattern.compile(LINK_REGEX);

        Matcher m = p.matcher(page.getBody());

        while (m.find()) {
                list.add(m.group(2));
        }
        return list.stream()
                .map(link -> checkLinks(page.getAddress(), link))
                .collect(Collectors.toList());
    }
//TODO check if bellow methods are still needed or can be refactored
    private String checkLinks(String url, String link){

        Pattern p = Pattern.compile(CHECK_LINK_REGEX);
        Matcher m = p.matcher(link);
        url = extractMainLink(url);

        if (m.find()) {
            return new UrlNormalizer().normalize(extractMainLink(link));
        }

        return validateLinkEnd(url) + validateLinkStart(link);
    }
    private String extractMainLink(String url) {

        Pattern p = Pattern.compile(MAIN_LINK_REGEX);
        Matcher m = p.matcher(url);

        if(m.find())
        return m.group(0);

        return url;
    }

    private String validateLinkStart(String url) {

        if (!url.startsWith("/")) {
            return validateLinkEnd("/" + url);
        }
        return validateLinkEnd(url);
    }

    private String validateLinkEnd(String url) {

        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }
}
