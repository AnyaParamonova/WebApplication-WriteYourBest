package com.command.factory;

import com.command.commands.LogInCommand;
import com.command.commands.LogOutCommand;
import com.command.commands.SignUpCommand;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */

public enum CommandEnum{
    LOGIN{
        {
            this.command = new LogInCommand();
        }
    },
    SIGNUP{
        {
            this.command = new SignUpCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogOutCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getActionCommand(){
        return command;
    }
}