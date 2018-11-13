/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.george.radius;

import com.george.radius.request.AccessRequest;
import com.george.radius.response.AccessAccept;
import com.george.radius.response.AccessReject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author home
 */
public class Main {

    public static final String SHARED_SECRET="george";
    
 

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1812);
            byte[] receiveData = new byte[4096];
            byte[] sendData =null;
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                byte[] data = receivePacket.getData();

                int code = data[0]; // packet type
                
                switch (code) {
                    case RadiusPacket.TYPE_ACCESS_REQEUST:
                        AccessRequest accessRequest = new AccessRequest();
                        try{
                        accessRequest.parse(data);
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                            continue;
                        }
                       if((accessRequest.getUserName()!=null&&accessRequest.getUserName().equals("frans1")
                               &&accessRequest.getPassword()!=null&&accessRequest.getPassword().equals("fran123!"))
                               ||(accessRequest.getUserName()!=null&&accessRequest.getUserName().equals("frans2")
                               &&accessRequest.getPassword()!=null&&accessRequest.getPassword().equals("fran123!")))
                       {
                           try{
                              AccessAccept accessAccept=new AccessAccept(accessRequest);
                             sendData=accessAccept.encode();
                           }catch(Exception e)
                        {
                            e.printStackTrace();
                            continue;
                        }
                          
                           
                       }
                       else
                           
                           try{
                               AccessReject accessReject=new AccessReject(accessRequest);
                          sendData=accessReject.encode();
                           }catch(Exception e)
                        {
                            e.printStackTrace();
                            continue;
                        }
                        

                        break;
                }
                
                
                if(sendData!=null)
                {
                    
              
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                DatagramPacket sendPacket
                        = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
                }
                sendData=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
