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
public abstract class RadiusAttribute {
    
    public static final int TYPE_USERNAME=1;
     public static final int TYPE_PASSWORD=2;
    
    
    protected int type;
    protected int length;
    protected byte[] value;
    
    protected RadiusPacket radiusPacket;
    
    public RadiusAttribute(RadiusPacket radiusPacket,int type)
    {
        this.type=type;
        this.radiusPacket=radiusPacket;
    }
    
    public abstract void parse(byte[] data,int index)  throws Exception;

    public int getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public byte[] getValue() {
        return value;
    }
    
    
}
