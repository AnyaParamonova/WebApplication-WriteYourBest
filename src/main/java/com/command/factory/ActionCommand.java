package com.command.factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public interface ActionCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
