package com.virtualdrive.controller;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.virtualdrive.model.User;
import com.virtualdrive.service.UserService;

import jakarta.servlet.*;

public class LoginServlet extends HttpServlet {
    private UserService us=new UserService();
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
        String email=req.getParameter("email");
        String pass=req.getParameter("pass");
        User u=us.login(email,pass);
        if(u!=null)
        {
            HttpSession ses=req.getSession();
            ses.setAttribute("user",u);
            res.sendRedirect("folder?parentId=0");
        }else
        {
            res.sendRedirect("login.html");
        }
        
    }
}
