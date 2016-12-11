package com.command.action;

import com.command.attribute.AttributeList;
import com.command.attribute.AttributeHandler;
import com.command.factory.ActionCommand;
import com.model.action.CompositionWallModel;
import com.model.composition.Composition;
import com.model.user.state.AuthorizedUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 30.11.2016.
 */

public class CreateWallCommand implements ActionCommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        AuthorizedUser user = AttributeHandler.readUserAttribute(session);

        CompositionWallModel model = new CompositionWallModel(user);
        String currentTheme = model.getCurrentDateTheme();
        request.setAttribute("theme", currentTheme);

        ArrayList<Composition> compositions = model.getNextCompositionPortion(0);
        session.setAttribute(AttributeList.OFFSET_ATTRIBUTE, compositions.size());
        String htmlCompositionList = model.generateHtmlCompositionList(compositions);

        request.setAttribute("compositions", htmlCompositionList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CompositionWallPage.jsp");
        dispatcher.forward(request, response);
    }
}
