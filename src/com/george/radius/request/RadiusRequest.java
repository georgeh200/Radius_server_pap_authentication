/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius.request;

import com.george.radius.RadiusPacket;
import com.george.radius.attributes.PasswordAttribute;
import com.george.radius.attributes.RadiusAttribute;
import com.george.radius.attributes.UserNameAttribute;

/**
 *
 * @author home
 */
public abstract class RadiusRequest extends RadiusPacket{
    
    public RadiusRequest(int type)
    {
        super(type);
    }
    
     @Override
    
     public  void parse(byte[] data)  throws Exception
    {
        this.type=data[0];
        this.identifier=data[1];
        this.length=(data[2] << 8) + data[3];
        for (int j = 4; j < 20; j++) {
              this.authenticator[j-4]=data[j];
        }
        
        int index=20;
        this.parseAttributes(data, index);
        
    }
    
}
