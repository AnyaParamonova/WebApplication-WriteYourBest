package com.command.action;

import com.command.attribute.AttributeHandler;
import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;
import com.model.action.UserModel;
import com.model.error.ErrorList;
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
public class UpdateDataCommand implements ActionCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        AuthorizedUser user = AttributeHandler.readUserAttribute(session);

        String newNickname = request.getParameter(AttributeList.NICKNAME_ATTRIBUTE);
        String newEmail = request.getParameter(AttributeList.EMAIL_ATTRIBUTE);

        if(newNickname.equals("") || newEmail.equals("")){
            out.write(ErrorList.EMPTY_FIELDS_ERROR);
            return;
        }

        UserModel model = new UserModel(user);
        AuthorizedUser updatedUser = model.updateUserEmail(newEmail);
        if(updatedUser == null){
            out.write(model.getErrorMessage());
            return;
        }
        else{
            session.setAttribute(AttributeList.EMAIL_ATTRIBUTE, updatedUser.getEmail());
        }

        updatedUser = model.updateUserNickname(newNickname);
        if(updatedUser == null){
            out.write(model.getErrorMessage());
            return;
        }
        else{
            session.setAttribute(AttributeList.NICKNAME_ATTRIBUTE, updatedUser.getNickname());
        }

        out.write("");
    }
}
