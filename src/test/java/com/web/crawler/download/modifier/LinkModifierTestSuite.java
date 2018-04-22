package com.web.crawler.download.modifier;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkModifierTestSuite {
    private Modifier modifier;

    @Before
    public void setUp() throws Exception {
        modifier = new LinkModifier();
    }

    @Test
    public void shouldModifyLinks() {
        //Given
        String source = "<link rel=\"stylesheet\" media=\"screen\" href=\"/_css/2015.1/screen.css\"/>\n" +
                "<script type=\"text/javascript\" src=\"/_js/2013.1/jquery.js\"></script>\n" +
                "<li><a href=\"/domains/root\">Root Zone Management</a></l\n" +
                "are provided by <a href=\"http://pti.icann.org\">Public Technical Identifiers</a>,";


        String expected = "<link rel=\"stylesheet\" media=\"screen\" href=\"_css/2015.1/screen.css\"/>\n" +
                "<script type=\"text/javascript\" src=\"_js/2013.1/jquery.js\"></script>\n" +
                "<li><a href=\"domains/root\">Root Zone Management</a></l\n" +
                "are provided by <a href=\"http://pti.icann.org\">Public Technical Identifiers</a>,";

        //When
        String refactoredLinks = modifier.ModifyLinks(source);

        //Then
        assertEquals(expected, refactoredLinks);
    }
}