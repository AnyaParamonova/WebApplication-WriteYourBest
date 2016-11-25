package com.dao;

import com.dao.exceptions.DaoException;
import com.model.users.AuthorizedUser;
import com.model.users.UnregisteredUser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class SignUpDaoTests {

    @Test
    @Ignore
    public void signUp_WhenUserNotExists_ShouldReturnRegisteredUserObject() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        UnregisteredUser valueToTest = new UnregisteredUser("Antonio", "antonio@yandex.by", "qwerty");

        //act
        AuthorizedUser actual = underTest.signUpNewUser(valueToTest);

        //assert
        AuthorizedUser expected = new AuthorizedUser(2, valueToTest.getNickname());

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void signUp_WhenUserExists_ShouldThrowException() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        UnregisteredUser valueToTest = new UnregisteredUser("Anastasia", "anastasia@gmail.com", "qwerty");

        //act
        AuthorizedUser result = underTest.signUpNewUser(valueToTest);
    }

    @Test(expected = DaoException.class)
    public void signUp_IncorrectInput_ShouldThrowException() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        UnregisteredUser valueToTest = null;

        //act
        AuthorizedUser result = underTest.signUpNewUser(valueToTest);
    }

    @Test
    public void checkNicknameBusy_WhenNicknameBusy_ShouldReturnTrue() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "Anastasia";

        //act
        boolean result = underTest.nicknameIsBusy(valueToTest);

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void checkNicknameBusy_WhenNicknameNotBusy_ShouldReturnFalse() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "Avtandil";

        //act
        boolean result = underTest.nicknameIsBusy(valueToTest);

        //assert
        Assert.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void checkNicknameBusy_IncorrectInput_ShouldThrowException() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = null;

        //act
        boolean result = underTest.nicknameIsBusy(valueToTest);

        //assert
        Assert.assertFalse(result);
    }

    @Test
    public void checkEmailBusy_WhenEmailBusy_ShouldReturnTrue() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "anya9182561@gmail.com";

        //act
        boolean result = underTest.emailIsBusy(valueToTest);

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void checkEmailBusy_WhenEmailNotBusy_ShouldReturnFalse() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "paramanastasia@gmail.com";

        //act
        boolean result = underTest.emailIsBusy(valueToTest);

        //assert
        Assert.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void checkEmailBusy_IncorrectInput_ShouldThrowException() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = null;

        //act
        boolean result = underTest.emailIsBusy(valueToTest);

        //assert
        Assert.assertFalse(result);
    }
}
