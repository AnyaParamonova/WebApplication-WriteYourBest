package com.model.users;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class UnauthorizedUser {

    private long id;
    private String nickname;
    private String password;

    public UnauthorizedUser(long id, String nickname, String password){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(password == null)
            throw new NullPointerException("password");

        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnauthorizedUser)) return false;

        UnauthorizedUser that = (UnauthorizedUser) o;

        if (id != that.id) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
