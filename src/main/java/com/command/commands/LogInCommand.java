package com.command.commands;

import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;
import com.model.LogInModel;
import com.model.encrypt.Encrypt;
import com.model.encrypt.HashEncrypt;
import com.model.errors.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.UnauthorizedUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class LogInCommand implements ActionCommand {

    private Encrypt encrypt;

    public LogInCommand(){
        this.encrypt = new HashEncrypt();
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String nickname = request.getParameter(AttributeList.NICKNAME_ATTRIBUTE);
        String password = request.getParameter(AttributeList.PASSWORD_ATTRIBUTE);

        if(nickname.equals("") || password.equals("")){
            out.write(ErrorList.EMPTY_FIELDS_ERROR);
            return;
        }

        UnauthorizedUser unauthorizedUser = new UnauthorizedUser(nickname, encrypt.encryptString(password));
        LogInModel model = new LogInModel(unauthorizedUser);
        AuthorizedUser authorizedUser = model.performLogIn();
        if(authorizedUser == null){
            out.write(model.getErrorMessage());
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute(AttributeList.NICKNAME_ATTRIBUTE, authorizedUser.getNickname());
        session.setAttribute(AttributeList.ID_ATTRIBUTE, authorizedUser.getId());
        out.write("");
    }

}
