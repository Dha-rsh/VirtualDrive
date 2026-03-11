package com.virtualdrive.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.virtualdrive.db.DBconnection;
import com.virtualdrive.model.Folder;

public class FolderDao {
    public int saveFolder(Folder f)
    {
        String sql="insert into folders(name,parent_id,user_id) values (?,?,?)";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,f.getName());
            if(f.getParent()!=null){
            ps.setInt(2,f.getParent());
            }else
            {
                ps.setNull(2,java.sql.Types.INTEGER);
            }
            ps.setInt(3,f.getUserId());
           int row= ps.executeUpdate();
           if(row>0)
           {
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
            {
                return rs.getInt(1);
            }
           }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Folder> getFolders(int userId,Integer parentId)
    {
        List<Folder> lis=new ArrayList<>();
        String sql="select * from folders where user_id=? and parent_id ";
       if(parentId==null)
        {
            sql+="is null";
        }else{
            sql+="= ?";
        }
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,userId);
            if(parentId!=null)
            {
                ps.setInt(2,parentId);
            }
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Folder f=new Folder(rs.getInt("id"),rs.getString("name"),(Integer)rs.getObject("parent_id"),rs.getInt("user_id"),rs.getString("path"));
                lis.add(f);
            }
           
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return lis;
    }
    public Folder getFolder(int id)
    {
        Folder f=null;
        String sql="select * from folders where id=? ";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                 f=new Folder(rs.getInt("id"),rs.getString("name"),(Integer)rs.getObject("parent_id"),rs.getInt("user_id"),rs.getString("path"));
            }
           
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    public String getPath(int folderId)
    {
        String sql="select path from folders where id=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,folderId);
           ResultSet rs=ps.executeQuery();
           if(rs.next())
           {
            return rs.getString("path");
           }

        }catch(Exception e)
        {
            e.printStackTrace(); 
        }
        return null;
    }

   public void delete(int id)
    {
        String sql="delete from folders where id=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

        }catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }

    public void updatePath(int id,String path)
    {
        String sql="update folders set path=? where id=?";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,path);
            ps.setInt(2,id);
            ps.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
}
