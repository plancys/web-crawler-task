package com.web.crawler.download.namegenerator;

import com.web.crawler.model.PageSnapshot;

public interface Generator {

    static String generateName(PageSnapshot pageSnapshot) {

        String name = pageSnapshot.getPage().getAddress().replaceAll(":|\\.|(https)|(http)", "");
        name = name.replace("/", "\\");

        if (name.substring(name.length() - 3, name.length()).equals("css"))
            return name.substring(0, name.length() -3) + ".css";

        return name + ".html";
    }
}
