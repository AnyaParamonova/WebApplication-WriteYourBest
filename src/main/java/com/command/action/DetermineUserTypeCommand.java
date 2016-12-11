package com.command.action;

import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class DetermineUserTypeCommand implements ActionCommand {

    private static String REGULAR_USER = "regular";
    private static String ADMIN = "admin";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userType = (String) request.getSession().getAttribute(AttributeList.TYPE_ATTRIBUTE);

        if(userType.equals(REGULAR_USER)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WriteYourBest.do?action=CREATEWALL");
            dispatcher.forward(request, response);
            return;
        }

        if(userType.equals(ADMIN)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/AddThemePage.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ErrorPage.jsp");
        dispatcher.forward(request, response);

    }
}
