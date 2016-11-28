package com.command.factory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class ActionFactory {

    private static String ACTION_PARAM = "action";

    public static ActionCommand defineCommand(HttpServletRequest request){
        String action = request.getParameter(ACTION_PARAM);
        CommandEnum commandEnum = CommandEnum.valueOf(action);
        return commandEnum.getActionCommand();
    }
}
