package com.virtualdrive.model;

public class Folder {
    private int id;
    private String name;
    private Integer parentId;
    private int userId;
    private String path;


     public Folder(int id,String name, Integer parentId, int userId,String path) {
       this.id=id;
        this.name = name;
        this.parentId = parentId;
        this.userId = userId;
        this.path=path;
    }
     public Folder(String name, Integer parentId, int userId,String path) {
        this.name = name;
        this.parentId = parentId;
        this.userId = userId;
        this.path=path;
    }
     public Folder(String name, Integer parentId, int userId) {
        this.name = name;
        this.parentId = parentId;
        this.userId = userId;
    }

    public void setId(int id)
    {
        this.id=id;
    }
     public void setName(String name)
    {
        this.name=name;
    }
     public void setParent(Integer parentId)
    {
        this.parentId=parentId;
    }
     public void setUserId(int userId)
    {
        this.userId=userId;
    }
    public void setpath(String path)
    {
        this.path=path;
    }

    public int getId()
    {
        return id;
    }
     public String getName()
    {
        return name;
    }
     public Integer getParent()
    {
         return parentId;
    }
     public int getUserId()
    {
        return userId;
    }
    public String getPath()
    {
        return path;
    }


}
