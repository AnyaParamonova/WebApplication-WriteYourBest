package classes.model.user;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class RegisteredUser {

    private long id;
    private String nickname;

    public RegisteredUser(long id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
