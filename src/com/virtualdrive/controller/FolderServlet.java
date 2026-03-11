package com.virtualdrive.controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import com.virtualdrive.model.FileEntity;
import com.virtualdrive.model.Folder;
import com.virtualdrive.model.User;
import com.virtualdrive.service.FileService;
import com.virtualdrive.service.FolderService;

public class FolderServlet extends HttpServlet {
    private FolderService fs=new FolderService();
    private FileService fileser=new FileService();
    protected void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
        User u=(User) req.getSession().getAttribute("user");
        if(u==null)
        {
             res.sendRedirect("login.html");
            return;
        }
        String parent =req.getParameter("parentId");
        int parentId=0;
        if(parent!=null)
        {
            parentId=Integer.parseInt(parent);
        }
        List<Folder> folders=fs.getFolders(u.getId(),parentId);
        List<FileEntity> files=fileser.getFiles(u.getId(),parentId);
         List<Folder> folder=fs.getAll(parentId);
        req.setAttribute("folders",folders);
        req.setAttribute("files",files);
        req.setAttribute("cpId",parentId);  
        req.setAttribute("nav",folder);
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
    rd.forward(req, res);

    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
          String folderName=req.getParameter("name");
          User u=(User) req.getSession().getAttribute("user");
          if(u==null)
          {
           
            res.sendRedirect("login.html");
            return;
          }
          String parent =req.getParameter("parentId");
        int parentId=0;
        if(parent!=null)
        {
            parentId=Integer.parseInt(parent);
        }
        System.out.println(parentId+"from folder");
          Folder f=new Folder(folderName,parentId,u.getId());
          String path =fs.createFolder(f);
          f.setpath(path);
          System.out.println(path);
         
         res.sendRedirect("folder?parentId="+parentId);
        
    }
    
}
