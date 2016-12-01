package com.model.action;

import com.dao.action.SignUpDao;
import com.dao.exception.DaoException;
import com.model.error.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.UnregisteredUser;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class SignUpModel {

    private SignUpDao signUpDao;
    private String errorMessage;
    private UnregisteredUser unregisteredUser;

    public SignUpModel(UnregisteredUser unregisteredUser){

        if(unregisteredUser == null)
            throw new NullPointerException("unregisteredUser");

        this.unregisteredUser = unregisteredUser;
        signUpDao = new SignUpDao();
    }

    public AuthorizedUser performSingUp(){
        try{
            if(signUpDao.nicknameIsOccupied(unregisteredUser.getNickname())){
                errorMessage = ErrorList.BUSY_NICKNAME_ERROR;
                return null;
            }

            if(signUpDao.emailIsOccupied(unregisteredUser.getEmail())){
                errorMessage = ErrorList.BUSY_EMAIL_ERROR;
                return null;
            }

            return signUpDao.signUpUser(unregisteredUser);
        }
        catch (DaoException e){
            System.out.println(e.toString());
            errorMessage = ErrorList.SERVER_ERROR;
            return null;
        }
    }

    public String getErrorMessage(){
        return errorMessage;
    }

}
