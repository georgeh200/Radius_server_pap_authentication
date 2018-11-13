/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius.attributes;

import com.george.radius.RadiusPacket;

/**
 *
 * @author home
 */
public class UserNameAttribute extends RadiusAttribute{
    
    
    private String username;
    
    public UserNameAttribute(RadiusPacket radiusPacket)
    {
        super(radiusPacket,TYPE_USERNAME);
    }

    @Override
    public void parse(byte[] data, int index) throws Exception{
       this.type=TYPE_USERNAME;
       this.length=data[index+1];
       this.value=new byte[this.length-2];
       System.arraycopy(data, index+2, this.value, 0, this.length-2);
      this.username=new String(data, index + 2, this.length-2, "UTF-8");
    
       
    }

    public String getUsername() {
        return username;
    }
    
    
    
}
