package com.virtualdrive.controller;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

import com.virtualdrive.model.FileEntity;
import com.virtualdrive.service.FileService;
public class DownloadServlet extends HttpServlet {
    FileService fs=new FileService();
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
        int id=Integer.parseInt(req.getParameter("id"));
        FileEntity files=fs.getFile(id);
        String path=files.getFilePath();
            File f=new File(path);
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition",
        "attachment;filename=\""+files.getName()+"\"");
        BufferedInputStream bis=new BufferedInputStream(
            new FileInputStream(f)
        );

         BufferedOutputStream bos=new BufferedOutputStream(
           res.getOutputStream()
        );
        int data;
        while((data=bis.read())!=-1)
        {
            bos.write(data);
        }
        bis.close();
        bos.close(); 

        
    }
}
