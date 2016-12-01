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

    private static int PORTION_SIZE = 5;
    private String COMPOSITION_TEMPLATE =
            "<form action='all_messages.php' method='post' class='row'>\n" +
                    "<div class='form-group' style='border: 2px solid #2b542c; padding: 20px; border-radius: 5px'>\n" +
                    "<button name='comp_$ID' class='close' type='submit'>&times</button>\n" +
                    "<h3 style='color: #2b542c'>$THEME</h3>\n" +
                    "<hr>\n" +
                    "<h5 style='color: #2b542c'>Creation date: $DATE</h5>\n" +
                    "<p  style='overflow: auto' style='color: #737373'>$BODY</p>\n" +
                    " </div>\n" +
             "</form>";

    private AuthorizedUser user;

    public CompositionWallModel(AuthorizedUser user){

        if(user == null)
            throw  new NullPointerException("user");

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
        try {
            CompositionDao dao = new CompositionDao();
            return dao.getCompositionList(user.getId(), offset, PORTION_SIZE);
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
}
