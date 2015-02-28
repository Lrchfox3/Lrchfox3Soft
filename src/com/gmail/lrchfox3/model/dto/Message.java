/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.model.dto;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;

/**
 *
 * @author lchinchilla
 */
public class Message  extends Base {
    
     protected Campo messageCode = null; //1
    protected Campo type = null;         //2
    protected Campo title = null;       //3
    protected Campo message = null;      //4
    protected Campo icon = null;         //5
    protected Campo buttonType = null;   //6
    
    public Message(){
        messageCode = new Campo("message_code", "Código Mensaje", SqlTipos.INTEGER, true);
        type = new Campo("type", "Tipo Mensaje", SqlTipos.VARCHAR);
        title = new Campo("title", "Título Mensaje", SqlTipos.VARCHAR);
        message = new Campo("message", "Mensaje", SqlTipos.VARCHAR);
        icon = new Campo("icon", "Icono", SqlTipos.VARCHAR);
        buttonType = new Campo("button_type", "Tipo Botón", SqlTipos.INTEGER);        
        setTabla("MESSAGE");
        setTitulo("Mensajes");
    }
    
    
    public Campo MessageCode() {
        return messageCode;
    }
    
    public Campo Type() {
        return type;
    }
    
    public Campo Title() {
        return title;
    }
    
    public Campo Message() {
        return message;
    }
    
    public Campo Icon() {
        return icon;
    }
    
    public Campo ButtonType() {
        return buttonType;
    }

    public int getMessageCode() {
        return messageCode.getIntValue();
    }

    public void setMessageCode(int _messageCode) {
        this.messageCode.setValue(_messageCode);
    }

    public String getType() {
        return type.getStringValue();
    }

    public void setType(String _type) {
        this.type.setValue(type);
    }

    public String getTitle() {
        return title.getStringValue();
    }

    public void setTitle(String _title) {
        this.title.setValue( title);
    }

    public String getMessage() {
        return message.getStringValue();
    }

    public void setMessage(String _message) {
        this.message.setValue(message);
    }

    public String getIcon() {
        return icon.getStringValue();
    }

    public void setIcon(String _icon) {
        this.icon.setValue(icon);
    }

    public int getButtonType() {
        return buttonType.getIntValue();
    }

    public void setButtonType(int _buttonType) {
        this.buttonType.setValue(buttonType);
    }
    
    @Override
    public String toString(){
        return "Código Mensaje: " +this.getMessageCode();
    }
    
    
}
