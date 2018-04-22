package org.mamta.garg.assignment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mamta.garg.assignment.model.JsCount;
import org.mamta.garg.assignment.task.IdentifyJsLibTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class LibCountServiceImpl implements LibCountService {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * Gets the scripts map.
     *
     * @param googleResponseJson
     *            the google response json
     * @return the scripts map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<JsCount> getLibsCount(String googleResponseJson) throws IOException, ExecutionException, InterruptedException {

        JsonNode node = new ObjectMapper().readTree(googleResponseJson);

        List<String> scriptsList = getLibsFromSearchResults(node);

        return toScriptCountList(scriptsList);
    }

    // Get all JS files name
    private List<String> getLibsFromSearchResults(JsonNode node) throws InterruptedException, ExecutionException {
        List<String> scriptsList = new ArrayList<>(1);
        for (JsonNode item : node.get("items")) {
            IdentifyJsLibTask identifyJsLibTask = new IdentifyJsLibTask(item);
            Future<List<String>> future = executor.submit(identifyJsLibTask);
            scriptsList.addAll(future.get());
        }
        return scriptsList;
    }

    // Get list of count and name of all JS files
    private List<JsCount> toScriptCountList(List<String> scriptsList) {
        return scriptsList
                .stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(5)
                .map(entry -> new JsCount(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }
}
