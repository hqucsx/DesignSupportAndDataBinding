package com.hqucsx.databindinganddesignsupport;

import java.io.Serializable;

/**
 * Created by csx on 15/6/7.
 */
public class Data implements Serializable{
    private String title;
    private String description;
    private int poster;

    public Data(String title, String description, int poster) {
        this.title = title;
        this.description = description;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

}
