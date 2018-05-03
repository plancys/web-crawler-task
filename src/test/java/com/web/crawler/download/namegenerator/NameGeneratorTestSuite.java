package com.web.crawler.download.namegenerator;

import com.web.crawler.model.Page;
import com.web.crawler.model.PageSnapshot;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class NameGeneratorTestSuite {

    private Generator generator;

    @Before
    public void setUp() throws Exception {
        generator = new NameGenerator();
    }

    @Test
    public void ShouldGenerateName() {
        //Given
        PageSnapshot testCase1 = new PageSnapshot(new Page("http://www.iana.org", "exampleBody"), new HashSet<>());
        PageSnapshot testCase2 = new PageSnapshot(new Page("http://www.iana.org/domains/reserved/", "exampleBody"), new HashSet<>());
        PageSnapshot testCase3 = new PageSnapshot(new Page("www.iana.org/domains/reserved/", "exampleBody"), new HashSet<>());
        PageSnapshot testCase4 = new PageSnapshot(new Page("iana.org/domains/reserved/", "exampleBody"), new HashSet<>());

        //When
        String generatedName1 = generator.generateName(testCase1);
        String generatedName2 = generator.generateName(testCase2);
        String generatedName3 = generator.generateName(testCase3);
        String generatedName4 = generator.generateName(testCase4);


        //Then
        assertEquals("iana.org\\index.html", generatedName1);
        assertEquals("iana.org\\domains\\reserved.html", generatedName2);
        assertEquals("iana.org\\domains\\reserved.html", generatedName3);
        assertEquals("iana.org\\domains\\reserved.html", generatedName4);
    }
}