package com.web.crawler.downlaod;

import com.web.crawler.PageSnapshotCreator;
import com.web.crawler.crawling.Crawler;
import com.web.crawler.extract.HttpPageExtractor;
import com.web.crawler.model.PageSnapshot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Downloader implements PageDownloader {


    @Override
    public void downloadPage(PageSnapshot pageSnapshot, File outputDirectory) {

        //String path = "C:\\Users\\Jaras\\Desktop\\Temporary\\";
        String name = pageSnapshot.getPage().getAddress().replaceAll("[^A-z]|(http)|(https)", "") + ".html";

        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory.getAbsolutePath() + "\\" + name))
        ){
                writer.write(pageSnapshot.getPage().getBody());
                pageSnapshot.getLinks().stream()
                        .flatMap(n -> n.getLinks().stream())
                        .forEach(n -> downloadPage(n, new File("dsa")));

        }
        catch (IOException e){

        } finally {

        }
    }
}
