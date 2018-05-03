package com.web.crawler.download;

import com.web.crawler.download.modifier.Modifier;
import com.web.crawler.download.namegenerator.Generator;
import com.web.crawler.model.PageSnapshot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Downloader implements PageDownloader {

    private static final String SLASH = "\\";

    private Generator generator;
    private Modifier modifier;

    public Downloader(Generator generator, Modifier modifier) {
        this.generator = generator;
        this.modifier = modifier;
    }

    @Override
    public void downloadPage(PageSnapshot pageSnapshot, File outputDirectory) {

        saveFile(pageSnapshot, outputDirectory);
        pageSnapshot.getLinks()
                .forEach(link -> downloadPage(link, outputDirectory));
    }

    private void saveFile(PageSnapshot pageSnapshot, File outputDirectory) {

        byte[] data = modifier.ModifyLinks(pageSnapshot.getPage().getBody()).getBytes();
        Path p = Paths.get(outputDirectory.getAbsolutePath() + SLASH + generator.generateName(pageSnapshot));
//TODO refactor this try to try-with-resources
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