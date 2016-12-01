package com.command.action;

import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;
import com.model.action.CompositionWallModel;
import com.model.user.state.AuthorizedUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Anastasia_Paramonova on 30.11.2016.
 */
public class SaveCompositionCommand implements ActionCommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userNickname = (String)session.getAttribute(AttributeList.NICKNAME_ATTRIBUTE);
        int userId = (Integer)session.getAttribute(AttributeList.ID_ATTRIBUTE);
        String body = request.getParameter(AttributeList.BODY_ATTRIBUTE);

        AuthorizedUser user = new AuthorizedUser(userId, userNickname);
        CompositionWallModel model = new CompositionWallModel(user);
        model.saveComposition(body);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WriteYourBest.do?action=CREATEWALL");
        dispatcher.forward(request, response);

    }

}
