package com.example.rpcosta.ejercicio2;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
/**
 * Created by rpcosta on 20/10/14.
 */
public class Pictures{

        private String id;
        private String url;
        private String secure_url;
        private String size;
        private String max_size;
        private String quality;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
