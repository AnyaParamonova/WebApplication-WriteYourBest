package com.web;

import com.model.Encryptor.HashEncrypt;
import com.model.Encryptor.IEncrypt;
import com.model.LogInModel;
import com.model.errors.ErrorList;
import com.model.users.AuthorizedUser;
import com.model.users.UnauthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anastasia_Paramonova on 24.11.2016.
 */

@WebServlet("/LogIn.do")
public class LogInController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        if(nickname.equals("") || password.equals("")){
            out.write(ErrorList.EMPTY_FIELDS_ERROR);
            return;
        }

        IEncrypt encrypt = new HashEncrypt();
        UnauthorizedUser unauthorizedUser = new UnauthorizedUser(nickname, encrypt.encryptString(password));
        LogInModel model = new LogInModel(unauthorizedUser);
        AuthorizedUser authorizedUser = model.performLogIn();
        if(authorizedUser == null){
            out.write(model.getErrorMessage());
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", authorizedUser);
        out.write("");
    }
}
