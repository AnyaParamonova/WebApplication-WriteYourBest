package com.model.composition;

import java.sql.Timestamp;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class Theme {
    private int id;
    private String body;
    private Timestamp creationDate;

    public Theme(int id, String theme, Timestamp creationDate){
        if(theme == null)
            throw new NullPointerException("body");
        if(creationDate == null)
            throw new NullPointerException("creationDate");

        this.body = theme;
        this.creationDate = creationDate;
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Theme)) return false;

        Theme theme1 = (Theme) o;

        if (!body.equals(theme1.body)) return false;
        return creationDate.equals(theme1.creationDate);

    }

    @Override
    public int hashCode() {
        int result = body.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }
}
