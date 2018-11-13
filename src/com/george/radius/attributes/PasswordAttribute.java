/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius.attributes;

import com.george.radius.Main;
import com.george.radius.RadiusPacket;
import java.security.MessageDigest;

/**
 *
 * @author home
 */
public class PasswordAttribute extends RadiusAttribute{
    
    private String password;
    
    public PasswordAttribute(RadiusPacket radiusPacket)
    {
        super(radiusPacket, TYPE_PASSWORD);
    }

    @Override
    public void parse(byte[] data, int index) throws Exception{
       this.type=TYPE_USERNAME;
       this.length=data[index+1];
       this.value=new byte[this.length-2];
       System.arraycopy(data, index+2, this.value, 0, this.length-2);
       this.decodePassword();
     
       
    }

    public String getPassword() {
        return password;
    }
    
    private  void decodePassword() throws Exception{

        byte[] authBytes=this.radiusPacket.getAuthenticator();
        byte[] sharedSecretBytes=Main.SHARED_SECRET.getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] lastBlock = new byte[16];

            for (int i = 0; i < this.value.length; i += 16) {
                md5.reset();
                md5.update(sharedSecretBytes);
                md5.update(i == 0 ? authBytes : lastBlock);
                byte result[] = md5.digest();
                System.arraycopy(this.value, i, lastBlock, 0, 16);
                
                for (int j = 0; j < 16; j++) {
                    this.value[i + j] = (byte) (result[j] ^ this.value[i + j]);
                }
            }

            // remove trailing zeros 
            int len = this.value.length;
            while (len > 0 && this.value[len - 1] == 0) {
                len--;
            }
            byte[] passtrunc = new byte[len];
            System.arraycopy(this.value, 0, passtrunc, 0, len);

            
            this.password= new String(passtrunc, "UTF-8");
          
      
       
    }
    
    
    
    
    
}
