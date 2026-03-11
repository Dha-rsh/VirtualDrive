package com.virtualdrive.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.virtualdrive.db.DBconnection;
import com.virtualdrive.model.FileEntity;

public class FileDao {
    public boolean saveFile(FileEntity f)
    {
        String sql="insert into files(name,file_path,size,folder_id,user_id) values (?,?,?,?,?)";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,f.getName());
            ps.setString(2,f.getFilePath());
            ps.setLong(3,f.getSize());
            if(f.getFolderId()==null)
            {
                ps.setNull(4,java.sql.Types.INTEGER);
            }else{
                ps.setInt(4,f.getFolderId());
            }

            ps.setInt(5,f.getUserId());
            return ps.executeUpdate()>0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public List<FileEntity> getFiles(int userId, Integer parentId) {
       List<FileEntity>files = new ArrayList<>();
       String sql="select * from files where user_id = ? and folder_id";
       if(parentId==null)
       {
        sql+="is null";
       }else{
        sql+="=?";
       }
       try(Connection con=DBconnection.getConnection())
       {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,userId);
        if(parentId!=null)
        {
            ps.setInt(2,parentId);
        }
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            FileEntity f=new FileEntity(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("file_path"),
                rs.getLong("size"),
                rs.getInt("folder_id"),
                rs.getInt("user_id")
            );
            files.add(f);
        }
        
       }catch(Exception e)
       {
        e.printStackTrace();
       }
       return files;

    }
    public FileEntity getFile(int id)
    {
        FileEntity f=null;
        String sql="select * from files where id=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               f=new FileEntity(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("file_path"),
                rs.getLong("size"),
                rs.getInt("folder_id"),
                rs.getInt("user_id")
            );
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteFile(int id)
    {
        String sql="delete from files where id=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
           
            return  ps.executeUpdate()>0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void delFromFolder(int folderId)
    {
        String sql="delete from files where folder_id=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,folderId);
           
             ps.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}
