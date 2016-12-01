package com.model.user.state;

/**
 * Created by Anastasia_Paramonova on 25.11.2016.
 */
public class SelectedUser {

    private int id;
    private String nickname;
    private String password;

    public SelectedUser(int id, String nickname, String password){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(password == null)
            throw new NullPointerException("password");

        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectedUser)) return false;

        SelectedUser that = (SelectedUser) o;

        if (id != that.id) return false;
        if (!nickname.equals(that.nickname)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + nickname.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
