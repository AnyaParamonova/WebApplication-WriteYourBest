package com.model;

import com.dao.SignUpDao;
import com.dao.exceptions.DaoException;
import com.model.errors.ErrorList;
import com.model.users.AuthorizedUser;
import com.model.users.UnregisteredUser;

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
            if(signUpDao.nicknameIsBusy(unregisteredUser.getNickname())){
                errorMessage = ErrorList.BUSY_NICKNAME_ERROR;
                return null;
            }

            return signUpDao.signUpNewUser(unregisteredUser);
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
