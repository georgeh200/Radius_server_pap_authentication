/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius.response;

import com.george.radius.Main;
import com.george.radius.RadiusPacket;
import java.security.MessageDigest;

/**
 *
 * @author home
 */
public abstract class RadiusResponse extends RadiusPacket{
    
    
    private RadiusPacket requestPacket;
    
    public RadiusResponse(int type,RadiusPacket requestPacket)
    {
        super(type);
        this.requestPacket=requestPacket;
    }
    
      @Override
    public void parse(byte[] data) {
        throw new UnsupportedOperationException("Not supported ."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public byte[] encode() throws Exception
    {
        
        this.length=20; // no attributes for now
        byte[] data=new byte[this.length];
        this.identifier=this.requestPacket.getIdentifier();
        data[0]=(byte)this.type;
        data[1]=(byte)this.identifier;
        
        data[2] = (byte) (this.length  >> 8);
        data[3] = (byte) (this.length& 0xFF);
          
        System.arraycopy(this.requestPacket.getAuthenticator(), 0, data, 4, 16);
        byte[] sharedSecretBytes=Main.SHARED_SECRET.getBytes("UTF-8");
        
         MessageDigest md5 = MessageDigest.getInstance("MD5");
         md5.update(data);
         md5.update(sharedSecretBytes);
         byte result[] = md5.digest();
          System.arraycopy(result, 0, data, 4, 16);
        return data;
        
        
        
        
    }
    
}
