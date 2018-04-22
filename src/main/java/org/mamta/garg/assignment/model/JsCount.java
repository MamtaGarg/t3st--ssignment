package org.mamta.garg.assignment.model;

public class JsCount{
    long count;
    String jsLib;

    public JsCount(long count, String jsLib) {
        this.count = count;
        this.jsLib = jsLib;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getJsLib() {
        return jsLib;
    }

    public void setJsLib(String jsLib) {
        this.jsLib = jsLib;
    }
}