package com.model.user.state;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class UnauthorizedUser {

    private String nickname;
    private String password;

    public UnauthorizedUser(String nickname, String password){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(password == null)
            throw new NullPointerException("password");

        this.nickname = nickname;
        this.password = password;
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

        if (!nickname.equals(that.nickname)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = nickname.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
