package com.command.action;

import com.command.attribute.AttributeList;
import com.command.attribute.AttributeHandler;
import com.command.factory.ActionCommand;
import com.model.action.CompositionWallModel;
import com.model.user.state.AuthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Anastasia_Paramonova on 04.12.2016.
 */
public class DeleteCompositionCommand implements ActionCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AuthorizedUser user = AttributeHandler.readUserAttribute(session);

        int compositionId = Integer.parseInt(request.getParameter(AttributeList.COMPOSITION_ID));

        CompositionWallModel model = new CompositionWallModel(user);
        model.deleteComposition(compositionId);
    }
}
