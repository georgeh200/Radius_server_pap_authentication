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
public class Utils {
    
    
    public static String hexString(byte[] data)
    {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for(byte b: data)
           sb.append(String.format("%02x", b));
        return sb.toString();
    }
    
}
