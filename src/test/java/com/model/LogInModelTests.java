package com.model;

import com.model.users.AuthorizedUser;
import com.model.users.UnauthorizedUser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Anastasia_Paramonova on 25.11.2016.
 */
public class LogInModelTests {

    @Test
    public void logIn_WhenUserDataCorrect_ShouldReturnAuthorizedUser(){
        //arrange
        UnauthorizedUser valueToTest = new UnauthorizedUser("Anastasia", "123456");
        LogInModel underTest = new LogInModel(valueToTest);

        //act
        AuthorizedUser actual = underTest.performLogIn();

        //assert
        AuthorizedUser expected = new AuthorizedUser(1, "Anastasia");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logIn_WhenUserNicknameIncorrect_ShouldReturnNull(){
        //arrange
        UnauthorizedUser valueToTest = new UnauthorizedUser("Anastasi", "123456");
        LogInModel underTest = new LogInModel(valueToTest);

        //act
        AuthorizedUser actual = underTest.performLogIn();

        //assert
        AuthorizedUser expected = null;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logIn_WhenUserPasswordIncorrect_ShouldReturnNull(){
        //arrange
        UnauthorizedUser valueToTest = new UnauthorizedUser("Anastasia", "1234567");
        LogInModel underTest = new LogInModel(valueToTest);

        //act
        AuthorizedUser actual = underTest.performLogIn();

        //assert
        AuthorizedUser expected = null;

        Assert.assertEquals(expected, actual);
    }
}
