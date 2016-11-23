package classes.model.users;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */
public class UnregisteredUser {
    
    private String nickname;
    private String email;
    private String password;

    public UnregisteredUser(String nickname, String email, String password){
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

        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
