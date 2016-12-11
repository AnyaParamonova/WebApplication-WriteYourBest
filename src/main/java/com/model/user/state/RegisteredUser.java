package com.model.user.state;

import java.sql.Timestamp;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class RegisteredUser {

    private int id;
    private String nickname;
    private String email;
    private String type;
    private Timestamp creationDate;

    public RegisteredUser(int id, String nickname, String email, String type, Timestamp creationDate){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(email == null)
            throw new NullPointerException("email");
        if(type == null)
            throw new NullPointerException("type");
        if(creationDate == null)
            throw new NullPointerException("creationDate");

        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.type = type;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public Timestamp getCreationDate(){
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisteredUser)) return false;

        RegisteredUser that = (RegisteredUser) o;

        if (id != that.id) return false;
        if (!nickname.equals(that.nickname)) return false;
        if (!email.equals(that.email)) return false;
        if (!type.equals(that.type)) return false;
        return creationDate.equals(that.creationDate);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nickname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }
}
