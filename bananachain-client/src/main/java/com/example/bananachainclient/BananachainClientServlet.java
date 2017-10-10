package com.example.bananachainclient;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import com.vaadin.server.VaadinServlet;

@WebServlet(
    asyncSupported=false,
    urlPatterns={"/*","/VAADIN/*"},
    initParams={
        @WebInitParam(name="ui", value="com.example.bananachainclient.BananachainClientUI")
    })
public class BananachainClientServlet extends VaadinServlet { }
