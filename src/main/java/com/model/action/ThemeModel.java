package com.model.action;

import com.dao.action.ThemeDao;
import com.dao.exception.DaoException;
import com.model.composition.Theme;
import com.model.error.ErrorList;
import com.model.user.state.AuthorizedUser;

import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class ThemeModel {

    private static String THEME_TEMPLATE =
            "<form id='comp_$ID' method='post' class='row'>\n" +
                    "<div class='form-group' style='border: 2px solid #2b542c; padding: 20px; border-radius: 5px'>\n" +
                    "<h5 style='color: #2b542c'>Creation date: $DATE</h5>\n" +
                    "<p  style='overflow: auto' style='color: #737373'>$BODY</p>\n" +
                    " </div>\n" +
            "</form>";

    private AuthorizedUser user;
    private String errorMessage;

    public ThemeModel(AuthorizedUser user){
        if(user == null)
            throw new NullPointerException("user");

        this.user = user;
    }

    public boolean addNewTheme(String theme){
        ThemeDao dao = new ThemeDao();
        try{
            dao.saveTheme(user.getId(), theme);
            return true;
        }
        catch (DaoException e){
            errorMessage = ErrorList.SERVER_ERROR;
            return false;
        }
    }

    public String generateHtmlThemeList(){
        ThemeDao dao = new ThemeDao();
        try{
            ArrayList<Theme> themes = dao.extractAllThemes();
            StringBuilder builder = new StringBuilder();
            for(Theme theme : themes){
                String id = String.valueOf(theme.getId());
                String date = theme.getCreationDate().toString();
                date = date.substring(0, date.length()-2);

                String compositionForm = THEME_TEMPLATE;
                compositionForm = compositionForm.replace("$ID", id);
                compositionForm = compositionForm.replace("$DATE", date);
                compositionForm = compositionForm.replace("$BODY", theme.getBody());

                builder.append(compositionForm);
            }

            return builder.toString();
        }
        catch (DaoException e){
            errorMessage = ErrorList.SERVER_ERROR;
            return "";
        }
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
