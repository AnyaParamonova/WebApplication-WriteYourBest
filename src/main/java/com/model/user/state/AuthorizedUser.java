package com.model.user.state;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class AuthorizedUser {

    private int id;
    private String nickname;
    private String email;
    private String type;

    public AuthorizedUser(int id, String nickname, String email, String type){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(email == null)
            throw new NullPointerException("email");
        if(type == null)
            throw new NullPointerException("type");

        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.type = type;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorizedUser)) return false;

        AuthorizedUser that = (AuthorizedUser) o;

        if (id != that.id) return false;
        if (!nickname.equals(that.nickname)) return false;
        if (!email.equals(that.email)) return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nickname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
