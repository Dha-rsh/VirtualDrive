package com.virtualdrive.model;

public class FileEntity {

    private int id;
    private String name;
    private String filePath;
    private long size;
    private Integer folderId;
    private int userId;

     public FileEntity(int id,String name, String filePath, long size,
                      Integer folderId, int userId) {
        this.id=id;
        this.name = name;
        this.filePath = filePath;
        this.size = size;
        this.folderId = folderId;
        this.userId = userId;
    }
    public FileEntity(String name, String filePath, long size,
                      Integer folderId, int userId) {
        this.name = name;
        this.filePath = filePath;
        this.size = size;
        this.folderId = folderId;
        this.userId = userId;
    }

    public FileEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}