/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calc;

/**
 *
 * @author mati
 */
public class Hilo extends Thread {
    private int respuesta;
    private String ipRecibida = null;
    int[] numeros = null;
    
    public Hilo(String ip, int[] numeros) {
        this.ipRecibida = ip;
        this.numeros = numeros;
    }
    
    public void run() {
        Cliente Escucha = new Cliente();
        respuesta = Escucha.main(numeros, ipRecibida);
        System.out.println("--------------------------------------------------------");
        System.out.println("Coordinador: " + ipRecibida + " envia la siguiente respuesta: " + respuesta);
    }
    
    public int ObtenerSuma() {
        return respuesta;
    }
}
