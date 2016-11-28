package com.model.user.state;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */
public class UnregisteredUser {
    
    private String nickname;
    private String email;
    private String password;

    public UnregisteredUser(String nickname, String email, String password){

        if(nickname == null)
            throw new NullPointerException("nickname");
        if(email == null)
            throw new NullPointerException("email");
        if(password == null)
            throw new NullPointerException("password");

        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnregisteredUser)) return false;

        UnregisteredUser that = (UnregisteredUser) o;

        if (!nickname.equals(that.nickname)) return false;
        if (!email.equals(that.email)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = nickname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
