/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius.response;

import com.george.radius.RadiusPacket;
import com.george.radius.response.RadiusResponse;

/**
 *
 * @author home
 */
public class AccessReject extends RadiusResponse{
    
   public AccessReject(RadiusPacket requestPacket)
    {
        super(TYPE_ACCESS_REJECT, requestPacket);
    }

    @Override
    public void parseAttributes(byte[] data, int index) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
   
   
    
}
