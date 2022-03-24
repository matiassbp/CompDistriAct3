package com.mycompany.gui;

import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Cliente {

    public int main(int[] lista, String ip) {
        Socket socket = null;
        int respuesta = 0;
        try {
            socket = new Socket(ip, 7800);
            ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
            dos.writeObject(lista);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            respuesta = dis.readInt();
            dos.close();
            dis.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("java.io.IOException generada");
            e.printStackTrace();
        }
        return respuesta;
    }
}