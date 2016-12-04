package com.command.factory;

import com.command.action.*;

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
    },
    CREATEWALL{
        {
            this.command = new CreateWallCommand();
        }
    },
    LOADCOMPOSITIONS{
        {
            this.command = new LoadCompositionsCommand();
        }
    },
    LOADCREATEPAGE{
        {
            this.command = new LoadCreatePageCommand();
        }
    },
    SAVECOMPOSITION{
        {
            this.command = new SaveCompositionCommand();
        }
    },
    DELETECOMPOSITION{
        {
            this.command = new DeleteCompositionCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getActionCommand(){
        return command;
    }
}