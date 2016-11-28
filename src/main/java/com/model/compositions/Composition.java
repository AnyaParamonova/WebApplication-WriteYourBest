package com.model.compositions;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class Composition {

    private String theme;
    private String body;

    public Composition(String theme, String body){
        if(theme == null)
            throw new NullPointerException("theme");
        if(body == null)
            throw new NullPointerException("body");

        this.theme = theme;
        this.body = body;
    }

    public String getTheme() {
        return theme;
    }

    public String getBody() {
        return body;
    }

}
