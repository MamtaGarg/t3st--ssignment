package org.mamta.garg.assignment.service;

import org.junit.Test;
import org.mamta.garg.assignment.service.LibCountService;
import org.mamta.garg.assignment.service.LibCountServiceImpl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;

public class LibCountServiceTest {

    LibCountService libCountService = new LibCountServiceImpl();

    @Test
    public void getLibsCount() throws InterruptedException, ExecutionException, IOException {

        String googleSearchResponse = "{\"items\": [\n" +
                "  {\n" +
                "   \"link\": \"https://www.facebook.com\"\n" +
                "  }," +
                "  {\n" +
                "   \"link\": \"https://www.blackrock.com/\"\n" +
                "  }" +
                "]" +
                "}";

        assertTrue(libCountService.getLibsCount(googleSearchResponse).size() > 0);
    }

}
