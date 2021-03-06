package com.command.action;

import com.command.attribute.AttributeList;
import com.command.attribute.AttributeHandler;
import com.command.factory.ActionCommand;
import com.model.action.CompositionWallModel;
import com.model.composition.Composition;
import com.model.user.state.AuthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 29.11.2016.
 */
public class LoadCompositionsCommand implements ActionCommand{

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        AuthorizedUser user = AttributeHandler.readUserAttribute(session);

        int offset = session.getAttribute(AttributeList.OFFSET_ATTRIBUTE) == null
                ? 0
                : (Integer)session.getAttribute(AttributeList.OFFSET_ATTRIBUTE);
        int portionSize = Integer.parseInt(request.getParameter(AttributeList.PORTION_SIZE));

        CompositionWallModel model = new CompositionWallModel(user);
        ArrayList<Composition> compositions = model.getNextCompositionPortion(offset, portionSize);
        offset += compositions.size();
        session.setAttribute(AttributeList.OFFSET_ATTRIBUTE, offset);
        String htmlCompositionList = model.generateHtmlCompositionList(compositions);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.write(htmlCompositionList);
    }

}
