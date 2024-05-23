/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.model;

/**
 *
 * @author ekire
 */
public class MessageModel {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MessageModel() {
    }
    
    
}
