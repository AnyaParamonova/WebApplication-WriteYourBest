package com.model.users;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class AuthorizedUser {

    private long id;
    private String nickname;

    public AuthorizedUser(long id, String nickname){

        if(nickname == null)
            throw new NullPointerException("nickname");

        this.id = id;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorizedUser)) return false;

        AuthorizedUser that = (AuthorizedUser) o;

        if (id != that.id) return false;
        return nickname != null ? nickname.equals(that.nickname) : that.nickname == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }
}
