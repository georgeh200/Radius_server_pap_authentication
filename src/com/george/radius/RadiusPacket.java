/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius;



/**
 *
 * @author home
 */
public abstract class RadiusPacket {
    
    
    public static final int TYPE_ACCESS_REQEUST=1;
    public static final int TYPE_ACCESS_ACCEPT=2;
    public static final int TYPE_ACCESS_REJECT=3;
    
    protected int type; //code in the protocol
    protected int identifier;
    protected int length;
    protected byte[] authenticator;
    

    
    
    public RadiusPacket(int type)
    {
        this.type=type;
       
        this.authenticator=new byte[16];
    }
    
    
    

    public int getType() {
        return type;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getLength() {
        return length;
    }

    public byte[] getAuthenticator() {
        return authenticator;
    }
    
    
    
    
    
    public abstract void parse(byte[] data)  throws Exception;
    
    public abstract void parseAttributes(byte[] data,int index)  throws Exception;
}
