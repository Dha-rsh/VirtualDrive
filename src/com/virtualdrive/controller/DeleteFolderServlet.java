package com.virtualdrive.controller;

import java.io.IOException;

import com.virtualdrive.model.User;
import com.virtualdrive.service.FileService;
import com.virtualdrive.service.FolderService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class DeleteFolderServlet extends HttpServlet {
    FolderService fol=new FolderService();
    FileService fs=new FileService();
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException
    {
        doPost(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
        Integer folderId=Integer.parseInt(req.getParameter("parentId"));
        HttpSession ses=req.getSession(false);
        User u=(User)ses.getAttribute("user");
        int userId=u.getId();
        fol.delFolder(userId,folderId);
        res.sendRedirect("folder?parentId="+0);
    }
}

    
