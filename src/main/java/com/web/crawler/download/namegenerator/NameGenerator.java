package com.web.crawler.download.namegenerator;

import com.web.crawler.model.PageSnapshot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameGenerator implements Generator {

    private static final String MAIN_LINK_REGEX = "(.+)\\.\\w+";
    private static final String GENERATE_NAME_REGEX = "\\w+\\..*\\w+";

    public String generateName(PageSnapshot pageSnapshot) {

        String url = pageSnapshot.getPage().getAddress();
        Matcher mainLink = Pattern.compile(MAIN_LINK_REGEX).matcher(url);

        if (mainLink.matches()) {
            return generateIndexHtml(url);
        }

        return generate(url);
    }

    private String generate(String url) {

        Matcher m = Pattern.compile(GENERATE_NAME_REGEX).matcher(url);
        m.find();

        String generatedUrl = m.group(0);
        generatedUrl = generatedUrl.replaceAll("www\\.", "");
        generatedUrl = generatedUrl.replace("/", "\\");

        return generatedUrl;
    }

    private String generateIndexHtml(String url) {

        return generate(url) + "\\index.html";
    }
}
