package com.command.action;

import com.command.attribute.AttributeHandler;
import com.command.factory.ActionCommand;
import com.model.action.ThemeModel;
import com.model.user.state.AuthorizedUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class ThemeListCommand implements ActionCommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AuthorizedUser admin = AttributeHandler.readUserAttribute(request.getSession());
        ThemeModel model = new ThemeModel(admin);

        String themeList = model.generateHtmlThemeList();
        request.setAttribute("themes", themeList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ThemeListPage.jsp");
        dispatcher.forward(request, response);
    }
}
