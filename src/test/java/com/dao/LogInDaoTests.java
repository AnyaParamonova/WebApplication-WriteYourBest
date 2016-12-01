package com.dao;

import com.dao.action.LogInDao;
import com.dao.exception.DaoException;
import com.model.encrypt.Encrypt;
import com.model.encrypt.HashEncrypt;
import com.model.user.state.SelectedUser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class LogInDaoTests {

    @Test
    public void authorize_WhenUserExists_ShouldReturnUnauthorizedUserObject() throws DaoException {
        //arrange
        LogInDao underTest = new LogInDao();
        String valueToTest = "Anastasia";
        Encrypt encrypt = new HashEncrypt();

        //act
        SelectedUser actual = underTest.findUser(valueToTest);

        //assert
        SelectedUser expected = new SelectedUser(1, "Anastasia", encrypt.encryptString("qwerty"));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void authorize_WhenUserNotExists_ShouldReturnNull() throws DaoException {
        //arrange
        LogInDao underTest = new LogInDao();
        String valueToTest = "Avtandil";

        //act
        SelectedUser actual = underTest.findUser(valueToTest);

        //assert
        Assert.assertNull(actual);

    }

    @Test(expected = DaoException.class)
    public void authorize_WhenInputDataIncorrect_ShouldThrowException() throws DaoException {
        //arrange
        LogInDao underTest = new LogInDao();
        String valueToTest =  null;

        //act
        SelectedUser actual = underTest.findUser(valueToTest);
    }

}
