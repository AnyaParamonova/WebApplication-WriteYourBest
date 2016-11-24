package com.web;

import com.model.errors.ErrorList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
@WebServlet("/SignUp.do")
public class SignUpController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRep = request.getParameter("passwordRep");

        if(nickname.equals("") || email.equals("") || password.equals("") || passwordRep.equals("")){
            out.write(ErrorList.EMPTY_FIELDS_ERROR);
            return;
        }

        if(!password.equals(passwordRep)){
            out.write(ErrorList.PASSWORDS_NOT_EQUAL_ERROR);
            return;
        }
/*
        UnregisteredUser unregisteredUser = new UnregisteredUser(nickname, email, encryptPassword(password));
        SignUpModel model = new SignUpModel(unregisteredUser);
        AuthorizedUser authorizedUser = model.performSingUp();
        if(authorizedUser == null){
            out.write(model.getErrorMessage());
            return;
        }*/
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/userProfilePage.jsp");
        requestDispatcher.forward(request, response);


    }

    private String encryptPassword(String password){
        return String.valueOf(password.hashCode());
    }

}
