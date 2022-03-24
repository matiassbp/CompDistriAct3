/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calc;

/**
 *
 * @author mati
 */
public class RellenarLista extends Thread {
    private int valor1 = 0;
    private int valor2 = 0;
    private int total = 0;
    private int asdad = 0;
    
    public RellenarLista(int valorUNO, int valorDOS){
        this.valor1 = valorUNO;
        this.valor2 = valorDOS;
        this.total =  valorDOS - valorUNO + 1;
        this.asdad = this.total/3;
    }
  
    public int[] ListaNumeros(int[] lista){
        for(int i = 0; i < lista.length; i++) {
          lista[i] = this.total - 1;
          this.total--;
        }
        return lista;
    }
}
