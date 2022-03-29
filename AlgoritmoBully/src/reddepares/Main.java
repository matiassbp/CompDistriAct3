package reddepares;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mati
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        Hilo hilito = new Hilo();
        Servidor server = new Servidor();
        server.conexion();
    }
}
