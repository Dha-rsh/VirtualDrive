package com.virtualdrive.controller;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.virtualdrive.model.User;
import com.virtualdrive.service.UserService;

import jakarta.servlet.*;

public class RegisterServlet extends HttpServlet
{
    private UserService us=new UserService();
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String pass=req.getParameter("pass");
        String cpass=req.getParameter("cpass");
        User u=new User(name,email,pass);
            if(us.register(u,cpass))
            {
                res.sendRedirect("login.html");
            }else
            {
                res.sendRedirect("register.html");
            }
        
        
    }
} 