package com.web.crawler.download;

import com.web.crawler.download.namegenerator.Generator;
import com.web.crawler.model.PageSnapshot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Downloader implements PageDownloader {


    @Override
    public void downloadPage(PageSnapshot pageSnapshot, File outputDirectory) {

        saveFile(pageSnapshot, outputDirectory);
        pageSnapshot.getLinks()
                .forEach(link -> downloadPage(link, outputDirectory));
    }

    private void saveFile(PageSnapshot pageSnapshot, File outputDirectory) {

        byte[] data = pageSnapshot.getPage().getBody().getBytes();
        Path p = Paths.get(outputDirectory.getAbsolutePath() + "\\" + Generator.generateName(pageSnapshot));

        OutputStream out = null;
        try {
            Files.createDirectories(p.getParent());
            out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND));
            out.write(data);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}