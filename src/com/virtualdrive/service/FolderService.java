package com.virtualdrive.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.virtualdrive.model.Folder;
import com.virtualdrive.repository.FileDao;
import com.virtualdrive.repository.FolderDao;

public class FolderService {
    String path="c:/VirtualDriveStorage/";
    FolderDao dao=new FolderDao();
    FileDao fd=new FileDao();
    public String createFolder(Folder f)
    {
        if(f==null)
        {
            return "";
        }
        int done=dao.saveFolder(f);
        if(done==-1)
        {
            return "";
        }
        f.setId(done);

        String userPath=path+"user_"+f.getUserId();
        File userDir=new File(userPath);
        if(!userDir.exists())
        {
            userDir.mkdirs();
        }
        String folderPath=userPath;
        if(f.getParent()!=0)
        {
            Folder parent=dao.getFolder(f.getParent());
            if(parent != null)
    {
        folderPath = parent.getPath();
    }
        }
        
        File newFolder = new File(folderPath,f.getName());
        if(!newFolder.exists())
        {
            newFolder.mkdirs();
        }
        String finalPath=newFolder.getAbsolutePath();
        dao.updatePath(f.getId(),finalPath);
        return finalPath;



    }

    public List<Folder> getFolders(int userId,Integer parentId)
    {
        return dao.getFolders(userId,parentId);
    }

     public void delFolder(int userId,int folderId)
    {
         fd.delFromFolder(folderId);
         List<Folder> lis=dao.getFolders(userId,folderId);
         for(Folder f:lis)
         {
            delFolder(userId,f.getId());
         }
         String path=dao.getPath(folderId);
        if(path!=null){
         File file=new File(path);
         if(file.exists())
         {
            file.delete();
         }
        }
         dao.delete(folderId);
         
    }
    
    public List<Folder> getAll(int folderId)
    {
        List<Folder> lis=new ArrayList<>();
        while(folderId!=0)
        {
            Folder f=dao.getFolder(folderId);
            lis.add(0,f);
            folderId=f.getParent();
        }
        return lis;
    }
}
