package com.anthem.juju;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutocompleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        res.setContentType("application/json; charset=utf-8");

        PrintWriter out = res.getWriter();
        String json = "[{value:\"val 1\",label:\"label 1\"},{value:\"val 2\",label:\"label 2\"}]";
        res.getWriter().write(json);
        out.close();
    }
}
