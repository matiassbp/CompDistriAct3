package servidordecalculo;

import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Servidor {

    public String conexion() throws ClassNotFoundException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        MotorDeCalculo sumar = new MotorDeCalculo();
        int[] lista = null;
        int resultado = 0;

        try {
            System.out.println("Escuchando por el puerto 8000");
            serverSocket = new ServerSocket(8000, 300);
        } catch (IOException e) {
            System.out.println("java.io.IOException generada");
            e.printStackTrace();
        }

        System.out.println("Esperando Conexiones...");
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Cliente Conectado: " + socket.getInetAddress().getHostName());
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                lista = (int[]) dis.readObject();
                System.out.println("Esclavo: El mensaje recibido por el coordinador = " + "\n" + Arrays.toString(lista));
                
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
                if (lista != null) {
                    resultado = sumar.SumadorLista(lista);
                }
                
                System.out.println("Esclavo: Se envia la suma de el arreglo correspondiente = " +"\n" + Arrays.toString(lista) +"\n" + "Suma: " + resultado);
                dos.writeInt(resultado);
                dos.close();
                dis.close();
                socket = null;
            } catch (IOException e) {
                System.out.println("java.io.IOException generada");
                e.printStackTrace();
            }
        }

    }
}