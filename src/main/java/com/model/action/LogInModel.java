package com.model.action;

import com.dao.action.LogInDao;
import com.dao.exception.DaoException;
import com.model.error.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.SelectedUser;
import com.model.user.state.UnauthorizedUser;

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

            return new AuthorizedUser(selectedUser.getId(), selectedUser.getNickname(), selectedUser.getEmail(), selectedUser.getType());
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
