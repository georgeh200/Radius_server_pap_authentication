/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius.request;

import com.george.radius.attributes.PasswordAttribute;
import com.george.radius.attributes.RadiusAttribute;
import com.george.radius.attributes.UserNameAttribute;
import com.george.radius.request.RadiusRequest;

/**
 *
 * @author home
 */
public class AccessRequest extends RadiusRequest{
    
    
    private UserNameAttribute userNameAttribute;
    private PasswordAttribute passwordAttribute;
    public AccessRequest()
    {
        super(TYPE_ACCESS_REQEUST);
    }

    @Override
    public void parseAttributes(byte[] data, int index)  throws Exception {
       int attributeType=0;
        RadiusAttribute attribute=null;
         while (index < this.length) {
              attributeType = data[index];
              switch(attributeType)
              {
                  case RadiusAttribute.TYPE_USERNAME:
                      attribute=new UserNameAttribute(this);
                      attribute.parse(data,index);
                      this.userNameAttribute=(UserNameAttribute)attribute;
                   break;
                     case RadiusAttribute.TYPE_PASSWORD:
                      attribute=new PasswordAttribute(this);
                      attribute.parse(data,index);
                      this.passwordAttribute=(PasswordAttribute)attribute;
                   break;
              }
              index+=attribute.getLength();
         }
    }
    
    
    
    public String getUserName()
    {
        if(this.userNameAttribute==null)
            return null;
        else return this.userNameAttribute.getUsername();
    }
    
    
    
     public String getPassword()
    {
        if(this.passwordAttribute==null)
            return null;
        else return this.passwordAttribute.getPassword();
    }
    

   
    
}
