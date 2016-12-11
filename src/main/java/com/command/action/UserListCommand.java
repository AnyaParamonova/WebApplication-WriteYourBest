package com.command.action;

import com.command.attribute.AttributeHandler;
import com.command.factory.ActionCommand;
import com.model.action.UserModel;
import com.model.user.state.AuthorizedUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class UserListCommand implements ActionCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AuthorizedUser admin = AttributeHandler.readUserAttribute(request.getSession());
        UserModel model = new UserModel(admin);

        String userList = model.generateHtmlUserList();
        request.setAttribute("users", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/UserListPage.jsp");
        dispatcher.forward(request, response);
    }
}
