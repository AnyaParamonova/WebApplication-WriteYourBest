package classes.model.users;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class UnAuthorizedUser {

    private long id;
    private String nickname;
    private String password;

    public UnAuthorizedUser(long id, String nickname, String password){
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
        if (!(o instanceof UnAuthorizedUser)) return false;

        UnAuthorizedUser that = (UnAuthorizedUser) o;

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
