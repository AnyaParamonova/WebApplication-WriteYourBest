package com.command.attribute;

import com.model.user.state.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class AttributeHandler {
    public static AuthorizedUser readUserAttribute(HttpSession session){
        String userNickname = (String)session.getAttribute(AttributeList.NICKNAME_ATTRIBUTE);
        String userType = (String)session.getAttribute(AttributeList.TYPE_ATTRIBUTE);
        String userEmail = (String)session.getAttribute(AttributeList.EMAIL_ATTRIBUTE);
        int userId = (Integer)session.getAttribute(AttributeList.ID_ATTRIBUTE);

        return new AuthorizedUser(userId, userNickname, userEmail, userType);
    }
}
