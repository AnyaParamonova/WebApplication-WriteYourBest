package com.command.action;

import com.command.attribute.AttributeHandler;
import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;
import com.model.action.ThemeModel;
import com.model.user.state.AuthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class AddThemeCommand implements ActionCommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        AuthorizedUser admin = AttributeHandler.readUserAttribute(session);

        ThemeModel model = new ThemeModel(admin);
        String theme = request.getParameter(AttributeList.THEME_ATTRIBUTE);
        if(!model.addNewTheme(theme)){
            out.write(model.getErrorMessage());
            return;
        }

        out.write("");
    }
}
