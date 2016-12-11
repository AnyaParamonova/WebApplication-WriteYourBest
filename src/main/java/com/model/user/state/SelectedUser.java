package com.model.user.state;

/**
 * Created by Anastasia_Paramonova on 25.11.2016.
 */
public class SelectedUser {

    private int id;
    private String nickname;
    private String type;
    private String email;
    private String password;

    public SelectedUser(int id, String nickname, String password, String email, String type){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(password == null)
            throw new NullPointerException("password");
        if(email == null)
            throw new NullPointerException("email");
        if(type == null)
            throw new NullPointerException("type");


        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectedUser)) return false;

        SelectedUser that = (SelectedUser) o;

        if (id != that.id) return false;
        if (!nickname.equals(that.nickname)) return false;
        if (!type.equals(that.type)) return false;
        if (!email.equals(that.email)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nickname.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
