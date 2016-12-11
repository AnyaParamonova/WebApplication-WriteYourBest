package com.model.action;

import com.dao.action.LogInDao;
import com.dao.action.SignUpDao;
import com.dao.action.UserDao;
import com.dao.exception.DaoException;
import com.model.error.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.SelectedUser;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class UserModel {

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

}
