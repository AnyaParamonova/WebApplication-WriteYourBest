package classes.dao;

import classes.dao.exceptions.DaoException;
import classes.model.users.UnAuthorizedUser;
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

        //act
        UnAuthorizedUser actual = underTest.findUser(valueToTest);

        //assert
        UnAuthorizedUser expected = new UnAuthorizedUser(1, "Anastasia", "123456");
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void authorize_WhenUserNotExists_ShouldReturnNull() throws DaoException {
        //arrange
        LogInDao underTest = new LogInDao();
        String valueToTest = "Avtandil";

        //act
        UnAuthorizedUser actual = underTest.findUser(valueToTest);

        //assert
        UnAuthorizedUser expected = null;
        Assert.assertEquals(expected, actual);

    }

    @Test(expected = DaoException.class)
    public void authorize_WhenInputDataIncorrect_ShouldThrowException() throws DaoException {
        //arrange
        LogInDao underTest = new LogInDao();
        String valueToTest =  null;

        //act
        UnAuthorizedUser actual = underTest.findUser(valueToTest);
    }

}
