package com.dao;

import com.dao.action.ThemeDao;
import com.dao.exception.DaoException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class ThemeDaoTests {

    @Test
    public void findTheme_ShouldReturnLastTheme() throws DaoException {
        //arrange
        ThemeDao underTest = new ThemeDao();

        //act
        String actual = underTest.extractCurrentDateTheme();

        //assert
        String expected = "The sunset of my life";
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void saveTheme_WhenInputDataIncorrect_ShouldThrowException() throws DaoException {
        //arrange
        ThemeDao underTest = new ThemeDao();
        String valueToTest = null;

        //act
        boolean actual = underTest.saveTheme(1, valueToTest);
    }

    @Test
    @Ignore
    public void saveTheme_WhenInputDataCorrect_ShouldReturnTrue() throws DaoException {
        //arrange
        ThemeDao underTest = new ThemeDao();
        String valueToTest = "How can I help myself?";

        //act
        boolean actual = underTest.saveTheme(1, valueToTest);

        //assert
        Assert.assertTrue(actual);
    }

}
