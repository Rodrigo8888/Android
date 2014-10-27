package com.example.rpcosta.ejercicio4.Dominio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown=true)
/**
 * Created by rpcosta on 23/10/14.
 */
public class APIResults {

    private String site_id;
    private String query;
    private ArrayList<Item> results;

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ArrayList<Item> getResults() {
        return results;
    }

    public void setResults(ArrayList<Item> results) {
        this.results = results;
    }

    public APIResults() {

    }
}
