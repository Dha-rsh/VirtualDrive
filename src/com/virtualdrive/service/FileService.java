package com.virtualdrive.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.Part;
import com.virtualdrive.model.FileEntity;
import com.virtualdrive.model.Folder;
import com.virtualdrive.repository.FileDao;
import com.virtualdrive.repository.FolderDao;

public class FileService {
    private static final  String path="c:/VirtualDriveStorage/";
    private FileDao fd=new FileDao();
    private FolderDao fol=new FolderDao();
    public boolean uploadFile(Part filepart,String fileName,Integer folderId,int userId) 
   throws IOException {
    Folder f=fol.getFolder(folderId);
    String folderPath;
    if(folderId==0){
        folderPath=path+"user_"+userId;
    }else{
        folderPath=f.getPath();
    }
        File userDir = new File(folderPath);
        
        if(!userDir.exists())
        {
            userDir.mkdirs();
        }
    

        File file=new File(userDir,fileName);

        filepart.write(file.getAbsolutePath());
        FileEntity entity=new FileEntity(fileName,file.getAbsolutePath(),filepart.getSize(),folderId,userId);
        return fd.saveFile(entity);

    }

    public List<FileEntity> getFiles(int userId,Integer parentId)
    {
        return fd.getFiles(userId,parentId);
    }
    public FileEntity getFile(int id)
    {
        return fd.getFile(id);
    }


    public boolean deleteFile(int fileId)
    {
        return fd.deleteFile(fileId);
    }

   
     
    

}
