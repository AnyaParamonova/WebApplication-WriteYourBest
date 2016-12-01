package com.dao;

import com.dao.action.CompositionDao;
import com.dao.exception.DaoException;
import com.model.composition.Composition;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 29.11.2016.
 */
public class CompositonDaoTests {

    @Test
    @Ignore
    public void saveComposition_WhenInputDataCorrect_ShouldReturnTrue() throws DaoException {
        //arrange
        CompositionDao underTest = new CompositionDao();
        String valueToTest = "4th composition";

        //act
        boolean actual = underTest.saveComposition(2, valueToTest);

        //assert
        Assert.assertTrue(actual);
    }

    @Test(expected = NullPointerException.class)
    public void saveComposition_WhenInputDataIncorrect_ShouldThrowException() throws DaoException {
        //arrange
        CompositionDao underTest = new CompositionDao();
        String valueToTest = null;

        //act
        boolean actual = underTest.saveComposition(1, valueToTest);
    }

    @Test
    public void getCompositionList_WhenCanReturnAskCount_ReturnAskNumberOfCompositions() throws DaoException {
        //arrange
        CompositionDao underTest = new CompositionDao();
        int offset = 0;
        int count = 2;

        //act
        ArrayList<Composition> result = underTest.getCompositionList(1, offset, count);

        //assert
        int expected = 2;
        int actual = result.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCompositionList_WhenOffsetMoreThanCompositionAmount_ReturnEmptyList() throws DaoException {
        //arrange
        CompositionDao underTest = new CompositionDao();
        int offset = 1000;
        int count = 2;

        //act
        ArrayList<Composition> result = underTest.getCompositionList(1, offset, count);

        //assert
        int expected = 0;
        int actual = result.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCompositionList_WhenCompositionAmountLessThanAsk_ReturnListWithAmountCompositions() throws DaoException {
        //arrange
        CompositionDao underTest = new CompositionDao();
        int offset = 0;
        int count = 2000;

        //act
        ArrayList<Composition> result = underTest.getCompositionList(1, offset, count);

        //assert
        int expected = 2;
        int actual = result.size();

        Assert.assertEquals(expected, actual);
    }

}
