package com.web.crawler.download.modifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkModifier implements Modifier {

    private static final String LINKS_TO_MODIFY_REGEX = "((src|href)=\\\")/";

    @Override
    public String ModifyLinks(String websiteSource) {

        Pattern p = Pattern.compile(LINKS_TO_MODIFY_REGEX);
        Matcher m = p.matcher(websiteSource);

        StringBuffer sb = new StringBuffer(websiteSource.length());

        while (m.find()) {
            String text = m.group(1);
            m.appendReplacement(sb, Matcher.quoteReplacement(text));
        }
        m.appendTail(sb);

        return sb.toString();
    }
}
