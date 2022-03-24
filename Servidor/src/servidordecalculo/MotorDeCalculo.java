/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidordecalculo;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class MotorDeCalculo {

    public int  Multiplicador(int a, int b) {
        return a*b;
    }
    
    public int Sumador(int a, int b) {
    	try {
			Thread.sleep(1000);
			return a+b;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
    
    public int SumadorLista(int[] listaNumeros)
    {
        System.out.println("---------");
        System.out.println("Sumando");
        System.out.println("---------");
        int result = 0;
        for (int i = 0; i < listaNumeros.length ; i += 1) {
            result = Sumador(listaNumeros[i], result);
            System.out.println("Suma: " + result);
        }
        return result;
    }
}
