package classes.model.user;

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


    
}
