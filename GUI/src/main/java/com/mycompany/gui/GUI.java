/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.gui;

import java.io.DataOutputStream;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class GUI {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        System.out.print("Seleccione un Rango (1,100)" + "\n");
        Thread.sleep(500);
        System.out.println("Numero Menor:");
        int a = scan.nextInt();
        System.out.println("Numero Mayor:");
        int b = scan.nextInt();
        int[] ListaFinal = {a, b};
        Hilo hiloFinal;
        hiloFinal = new Hilo("26.157.200.26", ListaFinal);
        hiloFinal.start();
        hiloFinal.ObtenerSuma();
    }
}
