package com.command.commands;

import com.command.attribute.AttributeList;
import com.command.factory.ActionCommand;
import com.model.SignUpModel;
import com.model.encrypt.Encrypt;
import com.model.encrypt.HashEncrypt;
import com.model.errors.ErrorList;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.UnregisteredUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class SignUpCommand implements ActionCommand{

    private Encrypt encrypt;

    public SignUpCommand(){
        this.encrypt = new HashEncrypt();
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String nickname = request.getParameter(AttributeList.NICKNAME_ATTRIBUTE);
        String email = request.getParameter(AttributeList.EMAIL_ATTRIBUTE);
        String password = request.getParameter(AttributeList.PASSWORD_ATTRIBUTE);
        String passwordRep = request.getParameter(AttributeList.REPPASSWORD_ATTRIBUTE);

        if(nickname.equals("") || email.equals("") || password.equals("") || passwordRep.equals("")){
            out.write(ErrorList.EMPTY_FIELDS_ERROR);
            return;
        }

        if(password.length() < 6){
            out.write(ErrorList.PASSWORD_TOO_SMALL);
            return;
        }

        if(!password.equals(passwordRep)){
            out.write(ErrorList.PASSWORDS_NOT_EQUAL_ERROR);
            return;
        }

        UnregisteredUser unregisteredUser = new UnregisteredUser(nickname, email, encrypt.encryptString(password));
        SignUpModel model = new SignUpModel(unregisteredUser);
        AuthorizedUser authorizedUser = model.performSingUp();
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
