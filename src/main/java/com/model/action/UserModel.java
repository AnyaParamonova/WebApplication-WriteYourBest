package com.model.action;

import com.dao.action.LogInDao;
import com.dao.action.SignUpDao;
import com.dao.action.UserDao;
import com.dao.exception.DaoException;
import com.model.error.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.RegisteredUser;
import com.model.user.state.SelectedUser;

import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class UserModel {

    private static String USER_TEMPLATE =
            "<form id='comp_$ID' method='post' class='row'>\n" +
                "<div class='form-group' style='border: 2px solid #2b542c; padding: 20px; border-radius: 5px'>\n" +
                "<h5 style='color: #2b542c'>Creation date: $DATE</h5>\n" +
                    "<h5 style='color: #2b542c'>Nickname: $NICKNAME</h5>\n" +
                    "<h5 style='color: #2b542c'>Email: $EMAIL</h5>\n" +
                    "<h5 style='color: #2b542c'>Type: $TYPE</h5>\n" +
                " </div>\n" +
            "</form>";

    private AuthorizedUser user;
    private String errorMessage;

    public UserModel(AuthorizedUser user){
       if(user == null)
           throw new NullPointerException("user");

        this.user = user;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public AuthorizedUser updateUserNickname(String newNickname){
        SignUpDao signUpDao = new SignUpDao();
        UserDao userDao = new UserDao();
        try{
            String oldNickname = user.getNickname();

            if(oldNickname.equals(newNickname)){
                return user;
            }

            if(signUpDao.nicknameIsOccupied(newNickname)){
                errorMessage = ErrorList.BUSY_NICKNAME_ERROR;
                return null;
            }

            userDao.changeUserNickname(user.getId(), newNickname);

            return new AuthorizedUser(user.getId(), newNickname, user.getEmail(), user.getType());

        }
        catch (DaoException e){
            System.out.println(e.toString());
            errorMessage = ErrorList.SERVER_ERROR;
            return null;
        }
    }

    public AuthorizedUser updateUserEmail(String newEmail){
        SignUpDao signUpDao = new SignUpDao();
        UserDao userDao = new UserDao();
        try{

            String oldEmail = user.getEmail();

            if(oldEmail.equals(newEmail)){
                return user;
            }

            if(signUpDao.emailIsOccupied(newEmail)){
                errorMessage = ErrorList.BUSY_EMAIL_ERROR;
                return null;
            }

            userDao.changeUserEmail(user.getId(), newEmail);

            return new AuthorizedUser(user.getId(), user.getNickname(), newEmail, user.getType());
        }
        catch (DaoException e){
            System.out.println(e.toString());
            errorMessage = ErrorList.SERVER_ERROR;
            return null;
        }
    }

    public AuthorizedUser updateUserPassword(String oldPassword, String newPassword){
        LogInDao logInDao = new LogInDao();
        UserDao userDao = new UserDao();
        try{
            SelectedUser selectedUser = logInDao.findUser(user.getNickname());
            if(!selectedUser.getPassword().equals(oldPassword)){
                errorMessage = ErrorList.INCORRECT_PASSWORD;
                return null;
            }

            userDao.changeUserPassword(user.getId(), newPassword);

            return user;
        }
        catch (DaoException e){
            System.out.println(e.toString());
            errorMessage = ErrorList.SERVER_ERROR;
            return null;
        }
    }

    public String generateHtmlUserList(){
        UserDao dao = new UserDao();
        try{
            ArrayList<RegisteredUser> users = dao.extractAllUsers();
            StringBuilder builder = new StringBuilder();
            for(RegisteredUser user : users){
                String id = String.valueOf(user.getId());
                String date = user.getCreationDate().toString();
                date = date.substring(0, date.length()-2);

                String compositionForm = USER_TEMPLATE;
                compositionForm = compositionForm.replace("$ID", id);
                compositionForm = compositionForm.replace("$DATE", date);
                compositionForm = compositionForm.replace("$NICKNAME", user.getNickname());
                compositionForm = compositionForm.replace("$EMAIL", user.getEmail());
                compositionForm = compositionForm.replace("$TYPE", user.getType());

                builder.append(compositionForm);
            }

            return builder.toString();
        }
        catch (DaoException e){
            errorMessage = ErrorList.SERVER_ERROR;
            return "";
        }
    }

}
