package com.virtualdrive.controller;

import java.io.IOException;

import com.virtualdrive.service.FileService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class DeleteServlet extends HttpServlet {
    FileService fs=new FileService();
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException
    {
        doPost(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
        int fileId=Integer.parseInt(req.getParameter("id"));
        Integer folderId=Integer.parseInt(req.getParameter("folderId"));
        if(fs.deleteFile(fileId)){

        res.sendRedirect("folder?parentId="+folderId);
        }
    }
}
