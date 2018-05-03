package com.web.crawler.download.modifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkModifier implements Modifier {
//TODO need to test it and improve those REGEX`s in case they dont work in some conditions
    private static final String LINKS_TO_DELETE_SLASH = "((src|href)=\\\")/";
    private static final String LINKS_TO_ADD_EXTENSION = "(href=\")([\\w\\.-]+/)([\\w]+\")";
//TODO need to implement an algorithm that will recognize which link modifying strategy to use
    @Override
    public String ModifyLinks(String websiteSource) {

        Pattern p = Pattern.compile(LINKS_TO_DELETE_SLASH);
        Matcher m = p.matcher(websiteSource);

        StringBuffer sb = new StringBuffer(websiteSource.length());

        while (m.find()) {
            String text = m.group(1);
            m.appendReplacement(sb, Matcher.quoteReplacement(text));
        }
        m.appendTail(sb);

        return process(sb.toString());
    }
    private String process(String websiteSource) {

        Pattern p = Pattern.compile(LINKS_TO_ADD_EXTENSION);
        Matcher m = p.matcher(websiteSource);

        StringBuffer sb = new StringBuffer(websiteSource.length());

        while (m.find()) {
            m.appendReplacement(sb, Matcher.quoteReplacement(m.group(1) + m.group(2) + processTheGroup(m.group(3))));
        }
        m.appendTail(sb);

        return sb.toString();

    }

    private String processTheGroup(String group) {

        return group.substring(0, group.length() - 1) + ".html\"";
    }
}
