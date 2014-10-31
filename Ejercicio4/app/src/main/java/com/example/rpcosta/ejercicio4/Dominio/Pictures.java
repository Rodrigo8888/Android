package com.example.rpcosta.ejercicio4.Dominio;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Created by rpcosta on 20/10/14.
 */
@DatabaseTable(tableName = "Images")
public class Pictures implements Serializable {
    @DatabaseField(canBeNull = true, generatedId = true)
    int _id;
    @DatabaseField (foreign = true)
    private String url;
    private String id;
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
