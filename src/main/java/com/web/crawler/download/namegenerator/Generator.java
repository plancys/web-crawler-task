package com.web.crawler.download.namegenerator;

import com.web.crawler.model.PageSnapshot;

public interface Generator {

    String generateName(PageSnapshot pageSnapshot);
}