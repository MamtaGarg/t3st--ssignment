package org.mamta.garg.assignment.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class GoogleSearchResultsService {

    /** The Constant API_KEY. */
    private static final String API_KEY = "AIzaSyAgKfJN9y2HfiU90iqxXrcUrQvUiT0OBdI";

    /** The Constant GOOGLE_ENGINE_KEY. */
    private static final String GOOGLE_ENGINE_KEY = "009799914807125141122:xrli-b5fgfm";

    /** The Constant GOOGLE_SEARCH_URL. */
    private static final String GOOGLE_SEARCH_URL = "https://www.googleapis.com/customsearch/v1";

    /** The Constant API_KEY_QUERY_PARAM. */
    private static final String API_KEY_QUERY_PARAM = "?key=%s";

    /** The Constant ENGINE_QUERY_PARAM. */
    private static final String ENGINE_QUERY_PARAM = "&cx=%s";

    /** The Constant SEARCH_QUERY_PARAM. */
    private static final String SEARCH_QUERY_PARAM = "&q=%s";

    /** The Constant SEARCH_URL. */
    private static final String SEARCH_URL = GOOGLE_SEARCH_URL + API_KEY_QUERY_PARAM + ENGINE_QUERY_PARAM
            + SEARCH_QUERY_PARAM;

    /**
     * Gets the google search json.
     *
     * @param searchKeyword
     *            the search keyword
     * @return the google search json
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String getGoogleSearchJson(String searchKeyword) throws IOException {
        InputStream doc = new URL(String.format(SEARCH_URL, API_KEY, GOOGLE_ENGINE_KEY, searchKeyword)).openStream();
        return readAll(new InputStreamReader(doc));
    }

    /**
     * Read all.
     *
     * @param rd
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
