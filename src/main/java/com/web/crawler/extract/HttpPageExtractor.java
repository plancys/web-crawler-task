package com.web.crawler.extract;

import com.web.crawler.model.Page;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpPageExtractor implements PageExtractor {
//TODO check if this code can be refactored
    @Override
    public Page extractPage(String link) {
        URL url;
        InputStream is = null;
        BufferedReader br = null;
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            url = new URL(link);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return new Page(link, sb.toString());
    }
//TODO this is proof of concept for downloading images
    public static void main(String[] args) throws IOException {
        HttpPageExtractor httpPageExtractor = new HttpPageExtractor();
        Page page = httpPageExtractor.extractPage("http://www.obrazki.org/upload/ob_0_33793200_1306404375.JPEG");
       // new BufferedWriter(new FileWriter(new File("C:\\Users\\Jaras\\Desktop\\Temporary\\obrazek.jpeg")))
        //Files.write(Paths.get("C:\\Users\\Jaras\\Desktop\\Temporary\\obraze.png"), page.getBody().getBytes());
        try (InputStream in = URI.create("https://demotywatory.pl/uploads/201008/1282322197_by_MACTEP_600.jpg").toURL().openStream()) {
            Files.copy(in, Paths.get("C:\\Users\\Jaras\\Desktop\\Temporary\\obrazek1.png"));
        }
    }
}
