package com.web.crawler.extract;

import com.web.crawler.model.Page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpPageExtractor implements PageExtractor {

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
}
