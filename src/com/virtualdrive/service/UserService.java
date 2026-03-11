package com.virtualdrive.service;

import com.virtualdrive.model.User;
import com.virtualdrive.repository.UserDao;

public class UserService {
    private UserDao ud=new UserDao();

    public boolean register(User u,String cpass)
    {
        if(!u.getPass().equals(cpass))
        {
            return false;
        }
        if(ud.getUser(u.getEmail())!=null)
        {
            return false;
        }
        return ud.saveUser(u);
    }

    public User login(String mail,String pass)
    {
        User u=ud.getUser(mail);
        if(u==null)
        {
            return null;
        }
        if(!u.getPass().equals(pass))
        {
            return null;
        }
        return u;
    }
}
