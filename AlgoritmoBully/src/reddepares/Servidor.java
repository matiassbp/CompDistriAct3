package reddepares;

import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Servidor {

    public String conexion() throws ClassNotFoundException, InterruptedException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        String peticion = null;
        String id = "6";
        String[] ips = {"26.187.118.118", "26.157.199.222", "26.25.191.84", "26.157.200.26"};
        String[] ipMayor = {"26.187.118.118"};
        String ip = "";
        int numeroAzar = (int) (Math.random() * 100 + 1);
        int numeroAzar2 = (int) (Math.random() * 100 + 1);
        Hilo Hilo = new Hilo("Algo", ips);
        Hilo.start();

        try {
            System.out.println("Escuchando por el puerto 8000");
            serverSocket = new ServerSocket(8000, 300);
        } catch (IOException e) {
            System.out.println("java.io.IOException generada");
            e.printStackTrace();
        }

        System.out.println("Esperando a que los clientes se conecten... ");
        while (true) {
            if (Hilo.buscarCoordinador == false) {
                try {
                    socket = serverSocket.accept();
                    String ipRecivida = "" + socket.getInetAddress();

                    if (ipRecivida.equals("/26.157.199.222")) {
                        System.out.println("Se conecto un cliente: " + "MatiasPC");
                    }
                    if (ipRecivida.equals("/26.157.200.26")) {
                        System.out.println("Se conecto un cliente: " + "DiegoPC");
                    }
                    if (ipRecivida.equals("/26.187.118.118")) {
                        System.out.println("Se conecto un cliente: " + "DiegoVM");
                    }
                    if (ipRecivida.equals("/26.25.191.84")) {
                        System.out.println("Se conecto un cliente: " + "MatiasVM");
                    }
                    //System.out.println("Se conecto un cliente: " + socket.getInetAddress().getHostName());
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    peticion = dis.readUTF();
                    if (!peticion.equals("Algo")) {
                        Hilo.buscarCoordinador = true;
                        if (peticion.equals("Esta vivo?")) {
                            System.out.println("ME PREGUNTARON " + peticion + " Se comienza la eleccion del coordinador");

                        } else {
                            if (peticion.split(": ")[0].equals("El coordinador es")) {
                                System.out.println("------------" + peticion + "------------" + "Ha finalizado la busqueda de coordinador");
                                Hilo.buscarCoordinador = false;
                            }
                        }
                    } else {
                        System.out.println("El mensaje que me envió el cliente es: " + peticion);
                    }
                    dis.close();
                    socket = null;
                } catch (IOException e) {
                    System.out.println("java.io.IOException generada");
                    e.printStackTrace();
                }
            } else {
                if (ipMayor.length == 0) {
                    Hilo.enviarMensaje("El coordinador es: " + id);
                } else {
                    Hilo[] clienteMayor = new Hilo[ipMayor.length];
                    for (int i = 0; i < clienteMayor.length; i++) {
                        clienteMayor[i] = new Hilo(ipMayor[i]);
                        clienteMayor[i].start();
                    }
                    boolean encontrado = false;

                    for (int i = 0; i < clienteMayor.length; i++) {
                        clienteMayor[i].join();
                        if (clienteMayor[i].Encontrado) {
                            encontrado = true;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Ningun servidor mayor respondió " + "Soy el nuevo coordinador " + " Ha finalizado la busqueda de coordinador");
                        Hilo.enviarMensaje("El coordinador es: " + id);
                    } else {
                        System.out.println("Esperando que termine la eleccion del coordinador");
                        Thread.sleep(5000);

                    }
                }
                Hilo.buscarCoordinador = false;
            }

        }

    }
}
