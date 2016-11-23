package classes.dao.exceptions;

import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class DaoException extends Exception {

    private SQLException hiddenException;
    private String errorMessage;

    public DaoException(String errorMessage, SQLException hiddenException){
        super(errorMessage);
        this.hiddenException = hiddenException;
        this.errorMessage = errorMessage;
    }

    public DaoException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return errorMessage + "\n[" + hiddenException.toString()+"]";
    }
}
