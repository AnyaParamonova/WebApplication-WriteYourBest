package com.model;

import com.model.action.SignUpModel;
import com.model.error.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.UnregisteredUser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Anastasia_Paramonova on 24.11.2016.
 */
public class SignUpModelTests {

    @Test
    @Ignore
    public void signUp_WhenNicknameNotBusy_ShouldReturnAuthorizedUser(){
        //arrange
        UnregisteredUser valueToTest = new UnregisteredUser("Egor", "egor@gmail.com", "123456");
        SignUpModel underTest =  new SignUpModel(valueToTest);

        //act
        AuthorizedUser actual = underTest.performSingUp();

        //assert
        AuthorizedUser expected = new AuthorizedUser(1, "Egor", "egor@gmail.com", "regular");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void signUp_WhenNicknameBusy_ShouldReturnNull(){
        //arrange
        UnregisteredUser valueToTest = new UnregisteredUser("Egor", "egor@gmail.com", "123456");
        SignUpModel underTest =  new SignUpModel(valueToTest);

        //act
        AuthorizedUser actual = underTest.performSingUp();

        //assert
        AuthorizedUser expected = null;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void modelConstructor_WhenParametersIncorrect_ShouldThrowException(){
        //act
        SignUpModel underTest =  new SignUpModel(null);
    }

    @Test
    public void getErrorMessage_WhenNicknameIsBusy_ShouldReturnNicknameBusyError(){
        //arrange
        UnregisteredUser valueToTest = new UnregisteredUser("Egor", "egor@gmail.com", "123456");
        SignUpModel underTest =  new SignUpModel(valueToTest);

        //act
        AuthorizedUser result = underTest.performSingUp();

        //assert
        String expected = ErrorList.BUSY_NICKNAME_ERROR;
        String actual = underTest.getErrorMessage();

        Assert.assertEquals(expected, actual);
    }
}
