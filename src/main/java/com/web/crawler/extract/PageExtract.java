package com.web.crawler.extract;

import com.web.crawler.model.Page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PageExtract implements PageExtractor {

    @Override
    public Page extractPage(String spec) {
        URL urll;
        InputStream is = null;
        BufferedReader br = null;
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            urll = new URL(spec);
            is = urll.openStream();  // throws an IOException
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
                if(br != null) {
                    br.close();
                }
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return new Page(spec, sb.toString());
    }

    public static void main(String[] args) {
        PageExtract pageExtract = new PageExtract();
        Page page = pageExtract.extractPage("http://example.com/");
        System.out.println(page.toString());
    }

}
