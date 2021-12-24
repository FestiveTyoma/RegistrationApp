package com.myprojects.registrationapp.pojo;

import java.util.List;

public class Albums {

    @com.squareup.moshi.Json(name = "resultCount")
    private Integer resultCount;
    @com.squareup.moshi.Json(name = "results")
    private List<Result> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}

