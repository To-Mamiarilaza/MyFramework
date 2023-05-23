/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.framework.util;

/**
 *
 * @author to
 */
public class FileUpload {
    // Attributs du classe File Upload
    String name;
    String path;
    byte[] bytes;

    // Constructeur du class FileUpload
    public FileUpload() {
    }

    public FileUpload(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    // Getter et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    
    
}
