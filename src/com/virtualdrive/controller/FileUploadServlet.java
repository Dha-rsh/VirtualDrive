package com.virtualdrive.controller;

import java.io.IOException;

import com.virtualdrive.model.User;
import com.virtualdrive.service.FileService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
@MultipartConfig
public class FileUploadServlet extends HttpServlet{
    private FileService fs=new FileService();
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
        HttpSession ses=req.getSession(false);
        User u=(User) ses.getAttribute("user");

         if (u == null) {
            res.sendRedirect("login.html");
            return;
        }
        
        Part filePart =req.getPart("file");
        String fileName=filePart.getSubmittedFileName();
        String parent =req.getParameter("folderId");
        System.out.println("parent String"+parent);
        int parentId=0;
        if(parent!=null)
        {
            parentId=Integer.parseInt(parent);
              System.out.println("parentId"+parentId);
        }
        fs.uploadFile(filePart,fileName,parentId,u.getId());
       res.sendRedirect("folder?parentId="+parentId);

    }
}
