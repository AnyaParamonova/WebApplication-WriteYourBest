package com.command.action;

import com.command.attribute.AttributeHandler;
import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;
import com.model.action.UserModel;
import com.model.encrypt.Encrypt;
import com.model.encrypt.HashEncrypt;
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
public class ChangePasswordCommand implements ActionCommand {

    private Encrypt encrypt;

    public ChangePasswordCommand(){
        this.encrypt = new HashEncrypt();
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        AuthorizedUser user = AttributeHandler.readUserAttribute(session);

        String oldPassword = request.getParameter(AttributeList.OLD_PASSWORD);
        String newPassword = request.getParameter(AttributeList.NEW_PASSWORD);
        String newPasswordRep = request.getParameter(AttributeList.NEW_PASSWORD_REP);

        if(oldPassword.equals("") || newPassword.equals("") || newPasswordRep.equals("")){
            out.write(ErrorList.EMPTY_FIELDS_ERROR);
            return;
        }

        if(newPassword.length() < 6){
            out.write(ErrorList.PASSWORD_TOO_SMALL);
            return;
        }

        if(!newPassword.equals(newPasswordRep)){
            out.write(ErrorList.PASSWORDS_NOT_EQUAL_ERROR);
            return;
        }

        UserModel model = new UserModel(user);
        AuthorizedUser updatedUser = model.updateUserPassword(encrypt.encryptString(oldPassword), encrypt.encryptString(newPassword));
        if(updatedUser == null){
            out.write(model.getErrorMessage());
            return;
        }

        out.write("");
    }
}
