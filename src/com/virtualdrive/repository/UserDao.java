package com.virtualdrive.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.virtualdrive.db.DBconnection;
import com.virtualdrive.model.User;

public class UserDao
{
    public boolean saveUser(User u)
    {
        String sql="insert into users(name,email,pass) values (?,?,?)";
        try(Connection con=DBconnection.getConnection())
        {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,u.getName());
            ps.setString(2,u.getEmail());
            ps.setString(3,u.getPass());
           int row= ps.executeUpdate();
           if(row>0)
            return true;
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String email)
    {
        User u=null;
        String sql="select * from users where email=?";
        try(Connection con=DBconnection.getConnection()) {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
             u=new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("pass"));

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}