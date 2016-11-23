package classes.dao;

import classes.dao.exceptions.DaoException;
import classes.model.user.RegisteredUser;
import classes.model.user.UnregisteredUser;
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
        UnregisteredUser valueToTest = new UnregisteredUser("Igor", "igor@mail.ru", "qwerty");

        //act
        RegisteredUser result = underTest.signUpNewUser(valueToTest);

        //assert
        long expectedId = 2, actualId = result.getId();
        String expectedNickname = "Igor", actualNickName = "Igor";

        Assert.assertTrue(expectedId == actualId && expectedNickname.equals(actualNickName));
    }

    @Test(expected = DaoException.class)
    public void signUp_WhenUserExists_ShouldThrowException() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        UnregisteredUser valueToTest = new UnregisteredUser("Anastasia", "anastasia@gmail.com", "qwerty");

        //act
        RegisteredUser result = underTest.signUpNewUser(valueToTest);
    }

    @Test(expected = DaoException.class)
    public void signUp_IncorrectInput_ShouldThrowException() throws DaoException {
        //arrange
        SignUpDao underTest = new SignUpDao();
        UnregisteredUser valueToTest = null;

        //act
        RegisteredUser result = underTest.signUpNewUser(valueToTest);
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

    @Test
    public void checkNicknameBusy_IncorrectInput_ShouldReturnFalse() throws DaoException{
        //arrange
        SignUpDao underTest = new SignUpDao();
        String valueToTest = null;

        //act
        boolean result = underTest.nicknameIsBusy(valueToTest);

        //assert
        Assert.assertFalse(result);
    }
}
