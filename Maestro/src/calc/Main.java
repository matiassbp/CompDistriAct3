/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author 173751680
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        int valor1 = 0;
        int valor2 = 0;
        ServerSocket serverSocket = null;
        Socket socket = null;
        Funcion sumar = new Funcion();
        int[] lista = null;

        try {
            System.out.println("Escuchando por el puerto 7800");
            serverSocket = new ServerSocket(7800, 300);
        } catch (IOException e) {
            System.out.println("java.io.IOException generada");
            e.printStackTrace();
        }

        System.out.println("Esperando a que los clientes se conecten...");
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Se conecto un cliente: " + socket.getInetAddress().getHostName());
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
                lista = (int[]) dis.readObject();
                System.out.println("El mensaje que me envio el cliente es: " + Arrays.toString(lista));

                DataOutputStream dos2 = new DataOutputStream(socket.getOutputStream());

                if (lista != null) {
                    sumar.SumadorLista(lista);
                    valor1 = sumar.a;
                    valor2 = sumar.b;
                    int resto = valor2 + 1 - valor1;
                    int div = resto / 4;
                    int resultado1 = 0;
                    int resultado2 = 0;
                    int resultado3 = 0;
                    int resultado4 = 0;
                    Hilo uno, dos, tres, cuatro, hiloFinal;
                    int[] numeros1 = new int[div];
                    int[] numeros2 = new int[div];
                    int[] numeros3 = new int[div];
                    int[] numeros4 = new int[div];
                    
                    System.out.println("Primer Valor: " + valor1 + " Segundo Valor: " + valor2);
                    RellenarLista Lista = new RellenarLista(valor1, valor2 + 1);
                    numeros1 = Lista.ListaNumeros(numeros1);
                    RellenarLista Lista2 = new RellenarLista(valor1, valor2 - div + 1);
                    numeros2 = Lista2.ListaNumeros(numeros2);
                    RellenarLista Lista3 = new RellenarLista(valor1, valor2 - (2 * div) + 1);
                    numeros3 = Lista3.ListaNumeros(numeros3);
                    RellenarLista Lista4 = new RellenarLista(valor1, valor2 - (3 * div) + 1);
                    numeros4 = Lista4.ListaNumeros(numeros4);
                    
                    uno = new Hilo("26.157.200.26", numeros1);
                    dos = new Hilo("26.157.200.26", numeros2);
                    tres = new Hilo("26.157.200.26", numeros3);
                    cuatro = new Hilo("26.157.200.26", numeros4);
                    uno.start();
                    dos.start();
                    tres.start();
                    cuatro.start();
                    
                    try {
                        uno.join();
                        dos.join();
                        tres.join();
                        cuatro.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    resultado1 = uno.ObtenerSuma();
                    resultado2 = dos.ObtenerSuma();
                    resultado3 = tres.ObtenerSuma();
                    resultado4 = cuatro.ObtenerSuma();
                    int[] ListaFinal = {resultado1, resultado2, resultado3, resultado4};
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Suma Esclavos Finalizada, Comienza Suma Final");
                    System.out.println("--------------------------------------------------------");
                    
                    hiloFinal = new Hilo("26.157.200.26", ListaFinal);
                    hiloFinal.start();
                    try {
                        hiloFinal.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dos2.writeInt(hiloFinal.ObtenerSuma());
                }
                dos2.close();
                dis.close();
                socket = null;
            } catch (IOException e) {
                System.out.println("java.io.IOException generada");
                e.printStackTrace();
            }
        }
    }

}
