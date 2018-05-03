package com.web.crawler.download.namegenerator;

import com.web.crawler.model.PageSnapshot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameGenerator implements Generator {
//TODO check if better REGEX`s can be implemented. Need to think of better naming alghoritm for different types of files
    private static final String MAIN_LINK_REGEX = "http[s]*://www\\.[\\w-]+\\.\\w+";
    private static final String GENERATE_NAME_REGEX = "\\w+\\..*\\w+";
    private static final String CSS_JS_REGEX = "(\\.css|\\.js)";

    public String generateName(PageSnapshot pageSnapshot) {

        String url = pageSnapshot.getPage().getAddress();
        Matcher mainLink = Pattern.compile(MAIN_LINK_REGEX).matcher(url);
        Matcher cssJs = Pattern.compile(CSS_JS_REGEX).matcher(url);


        if (mainLink.matches()) {
            return generateIndexHtml(url);
        }
        else if (cssJs.find()) {
            return generateCssJs(url);
        }

        return generate(url) + ".html";
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

        url = generate(url);

        return url + "\\index.html";
    }

    private String generateCssJs (String url) {

        url = generate(url);

        return url;
    }
}
