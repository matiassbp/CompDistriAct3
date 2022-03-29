package reddepares;

import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Servidor {

    public String conexion() throws ClassNotFoundException, InterruptedException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        String peticion = null;
        String id = "1";
        String[] ips = {"26.184.155.107","26.253.20.203","26.155.242.221","26.155.231.77","26.167.160.219"};
        String[] ipMayor = {"26.167.160.219","26.155.231.77","26.155.242.221","26.253.20.203","26.184.155.107"};
        String ip = "";
        Hilo Hilo = new Hilo("Algo",ips);  
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
                    System.out.println("Se conecto un cliente: " + socket.getInetAddress().getHostName());
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