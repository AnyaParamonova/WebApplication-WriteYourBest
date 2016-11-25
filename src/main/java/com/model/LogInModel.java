package com.model;

import com.dao.LogInDao;
import com.dao.exceptions.DaoException;
import com.model.errors.ErrorList;
import com.model.users.AuthorizedUser;
import com.model.users.SelectedUser;
import com.model.users.UnauthorizedUser;

/**
 * Created by Anastasia_Paramonova on 25.11.2016.
 */
public class LogInModel {

    private LogInDao logInDao;
    private String errorMessage;
    private UnauthorizedUser unauthorizedUser;

    public LogInModel(UnauthorizedUser unauthorizedUser){
        if(unauthorizedUser == null)
            throw new NullPointerException("unauthorizedUser");

        this.unauthorizedUser = unauthorizedUser;
        logInDao = new LogInDao();
    }

    public AuthorizedUser performLogIn(){
        try {
            SelectedUser selectedUser = logInDao.findUser(unauthorizedUser.getNickname());
            if(selectedUser == null){
                errorMessage = ErrorList.INCORRECT_DATA;
                return null;
            }
            String expectedPassword = selectedUser.getPassword();
            String actualPassword = unauthorizedUser.getPassword();
            if(!expectedPassword.equals(actualPassword)){
                errorMessage = ErrorList.INCORRECT_DATA;
                return null;
            }

            return new AuthorizedUser(selectedUser.getId(), selectedUser.getNickname());
        } catch (DaoException e) {
            System.out.println(e.toString());
            errorMessage = ErrorList.SERVER_ERROR;
            return null;
        }
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
