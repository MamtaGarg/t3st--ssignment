package org.mamta.garg.assignment.service;

import org.mamta.garg.assignment.model.JsCount;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * We can move our implementation to use new Lib other than JSoup,
 * but the contract should be same that
 * this service should get a json
 * and output JS File counts.

 */
public interface LibCountService {

    List<JsCount> getLibsCount(String googleResponseJson) throws IOException, ExecutionException, InterruptedException;
}
