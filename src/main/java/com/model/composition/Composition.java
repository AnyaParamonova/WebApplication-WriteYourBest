package com.model.composition;

import java.sql.Timestamp;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class Composition {

    private int id;
    private String theme;
    private String body;
    private Timestamp creationDate;

    public Composition(int id, String theme, String composition, Timestamp creationDate){

        if(theme == null)
            throw new NullPointerException("theme");
        if(composition == null)
            throw new NullPointerException("body");
        if(creationDate == null)
            throw new NullPointerException("creationDate");

        this.id = id;
        this.theme = theme;
        this.body = composition;
        this.creationDate = creationDate;
    }

    public int getId(){
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composition)) return false;

        Composition that = (Composition) o;

        if (id != that.id) return false;
        if (!theme.equals(that.theme)) return false;
        if (!body.equals(that.body)) return false;
        return creationDate.equals(that.creationDate);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + theme.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }
}
