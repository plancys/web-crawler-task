package com.web.crawler.download;

import com.web.crawler.model.PageSnapshot;

import java.io.File;

public interface PageDownloader {

    void downloadPage(PageSnapshot pageSnapshot, File outputDirectory);
}
