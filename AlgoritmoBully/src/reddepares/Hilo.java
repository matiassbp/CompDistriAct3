/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reddepares;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class Hilo extends Thread {
    
    boolean buscarCoordinador;
    boolean Encontrado;
    String mensaje;
    String[] ips;
    String ipMayor;
    int i;
    
    public Hilo(){
        
    }
    
    public Hilo(String mensaje, String[] ips) {
        this.mensaje = mensaje;
        this.ips = ips;
        i=0;
        buscarCoordinador= false;
        Encontrado = false;
        
    }
    
    public Hilo(String mensaje, String[] ips , boolean buscarCoor){
        this.mensaje = mensaje;
        this.ips = ips;
        i=0;
        buscarCoordinador = buscarCoor;
        Encontrado = false;
    }
    
    public Hilo(String ipMayor){
        mensaje = "";
        i=0;
        buscarCoordinador= true;
        Encontrado=false;
        this.ipMayor = ipMayor;
    }

    public void run() {
       if(buscarCoordinador == false){
           try{
               Thread.sleep(3000);              
           }catch(InterruptedException ex){
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
           }
           main(mensaje);
       }else{
           buscar(ipMayor);
       }
    }
    
    public void main(String mensaje){
        Socket socket = null;
        String peticion = mensaje;
        try
        {
            while(buscarCoordinador == false){
                socket = new Socket(ips[i],8000);
                DataOutputStream data = new DataOutputStream(socket.getOutputStream());
                data.writeUTF(peticion);
                data.close();
                socket.close();
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException ex){
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE,null,ex);
                }
            
            }
            
        }catch(IOException ex){
            System.out.println("EL coordinador murió, hay que buscar otro");
            buscarCoordinador=true;
            i++;
        }
        
    }
    
    public void enviarMensaje(String mensaje){
        for (int i = this.i; i < ips.length; i++) {
            try{
                String peticion = mensaje;
                Socket socket = new Socket(ips[i],8000);
                DataOutputStream data = new DataOutputStream(socket.getOutputStream());
                data.writeUTF(peticion);
                data.close();
                socket.close();
                
            }catch(IOException ex){
                
            }
            
            try{
                Thread.sleep(1000);    
            }catch(InterruptedException ex){
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE,null,ex);

            }
        }
    }
    
    public void buscar(String ip){
        try{
            String peticion = "Esta vivo?";
            Socket socket = new Socket(ip,8000);
            DataOutputStream data = new DataOutputStream(socket.getOutputStream());
            data.writeUTF(peticion);
            data.close();
            socket.close();
            String m = "El servidor " + ip + " esta vivo";
            Encontrado = true;
        }catch(IOException ex){
            
        }
    }    
}
