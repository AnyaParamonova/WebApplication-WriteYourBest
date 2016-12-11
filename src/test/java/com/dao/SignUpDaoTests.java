package com.dao;

import com.dao.action.SignUpDao;
import com.dao.exception.DaoException;
import com.model.encrypt.Encrypt;
import com.model.encrypt.HashEncrypt;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.UnregisteredUser;
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
        Encrypt encrypt = new HashEncrypt();
        UnregisteredUser valueToTest = new UnregisteredUser("Roman", "rakkatakka@gmail.com", encrypt.encryptString("123456"));

        //act
        AuthorizedUser actual = underTest.signUpUser(valueToTest);

        //assert
        AuthorizedUser expected = new AuthorizedUser(3, valueToTest.getNickname(), "rakkatakka@gmail.com", "regular");

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void signUp_WhenUserExists_ShouldThrowException() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        Encrypt encrypt = new HashEncrypt();
        UnregisteredUser valueToTest = new UnregisteredUser("Anastasia", "anastasia@gmail.com", encrypt.encryptString("qwerty"));

        //act
        AuthorizedUser result = underTest.signUpUser(valueToTest);
    }

    @Test(expected = DaoException.class)
    public void signUp_IncorrectInput_ShouldThrowException() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        UnregisteredUser valueToTest = null;

        //act
        AuthorizedUser result = underTest.signUpUser(valueToTest);
    }

    @Test
    public void checkNicknameBusy_WhenNicknameBusy_ShouldReturnTrue() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "Anastasia";

        //act
        boolean result = underTest.nicknameIsOccupied(valueToTest);

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void checkNicknameBusy_WhenNicknameNotBusy_ShouldReturnFalse() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "Avtandil";

        //act
        boolean result = underTest.nicknameIsOccupied(valueToTest);

        //assert
        Assert.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void checkNicknameBusy_IncorrectInput_ShouldThrowException() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = null;

        //act
        boolean result = underTest.nicknameIsOccupied(valueToTest);

        //assert
        Assert.assertFalse(result);
    }

    @Test
    public void checkEmailBusy_WhenEmailBusy_ShouldReturnTrue() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "anastasia@gmail.com";

        //act
        boolean result = underTest.emailIsOccupied(valueToTest);

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void checkEmailBusy_WhenEmailNotBusy_ShouldReturnFalse() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = "paramanastasia@gmail.com";

        //act
        boolean result = underTest.emailIsOccupied(valueToTest);

        //assert
        Assert.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void checkEmailBusy_IncorrectInput_ShouldThrowException() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = null;

        //act
        boolean result = underTest.emailIsOccupied(valueToTest);

        //assert
        Assert.assertFalse(result);
    }
}
