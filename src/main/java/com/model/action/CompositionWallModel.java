package com.model.action;

import com.dao.action.CompositionDao;
import com.dao.action.ThemeDao;
import com.dao.exception.DaoException;
import com.model.composition.Composition;
import com.model.user.state.AuthorizedUser;

import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 29.11.2016.
 */
public class CompositionWallModel {

    private static int DEFAULT_PORTION_SIZE = 5;
    private static String COMPOSITION_TEMPLATE =
            "<form id='comp_$ID' action='all_messages.php' method='post' class='row'>\n" +
                    "<div class='form-group' style='border: 2px solid #2b542c; padding: 20px; border-radius: 5px'>\n" +
                    "<button value='$ID' class='close' type='button'>&times</button>\n" +
                    "<h3 style='color: #2b542c'>$THEME</h3>\n" +
                    "<hr>\n" +
                    "<h5 style='color: #2b542c'>Creation date: $DATE</h5>\n" +
                    "<p  style='overflow: auto' style='color: #737373'>$BODY</p>\n" +
                    " </div>\n" +
             "</form>";

    private AuthorizedUser user;

    public CompositionWallModel(AuthorizedUser user){

        if(user == null)
            throw new NullPointerException("user");

        this.user = user;
    }

    public String getCurrentDateTheme(){
        try{
            ThemeDao dao = new ThemeDao();
            String theme = dao.extractCurrentDateTheme();
            return theme;
        }
        catch (DaoException e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    public ArrayList<Composition> getNextCompositionPortion(int offset){
            return getNextCompositionPortion(offset, DEFAULT_PORTION_SIZE);
    }

    public ArrayList<Composition> getNextCompositionPortion(int offset, int portionSize){
        try {
            CompositionDao dao = new CompositionDao();
            return dao.getCompositionList(user.getId(), offset, portionSize);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Composition>();
        }
    }

    public String generateHtmlCompositionList(ArrayList<Composition> compositions){
        StringBuilder builder = new StringBuilder();
        for(Composition composition : compositions){
            String date = composition.getCreationDate().toString();
            date = date.substring(0, date.length()-2);
            String id = String.valueOf(composition.getId());

            String compositionForm = COMPOSITION_TEMPLATE;
            compositionForm = compositionForm.replace("$ID", id);
            compositionForm = compositionForm.replace("$THEME", composition.getTheme());
            compositionForm = compositionForm.replace("$DATE", date);
            compositionForm = compositionForm.replace("$BODY", composition.getBody());

            builder.append(compositionForm);
        }

        return builder.toString();
    }

    public void saveComposition(String body){
        CompositionDao dao = new CompositionDao();
        try {
            dao.saveComposition(user.getId(), body);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteComposition(int compositionId){
        CompositionDao dao = new CompositionDao();
        try {
            dao.deleteCompostion(compositionId);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }
}
