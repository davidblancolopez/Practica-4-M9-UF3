
package practica.pkg4.m9.uf3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;


public class TimeClient {
    
    //Declarem el HOST
    static String HOST = "localhost";
    //Declarem el puerto
    static int PORT = 8745;
    //Declarem el DataOutput
    DataOutputStream outToServer;
    //Declarem el BufferedReader
    BufferedReader bf;
    //Declarem el Socket
    Socket sServer;
    /**
     * Constructor de la clase Client.
     * Li arriba per parametre un host i el port, un cop feta la conexio, demanem dia, mes i any i enviem aquestes dades a el metode
     * que enviará tot al Server.
     * @param host
     * @param port
     * @throws IOException 
     */
    public TimeClient(String host, int port) throws IOException {
        //Inicialitzem el Socket
        sServer = new Socket(host, port);
        //Inicialitzem el outToServer
        outToServer = new DataOutputStream(sServer.getOutputStream());
        //Inicialitzem BufferedReader
        bf = new BufferedReader(new InputStreamReader(sServer.getInputStream()));
        //Demanem dia, mes, any
        String dia = JOptionPane.showInputDialog(null, "Diu el numero del dia: ", "Entrando", 3);
        String mes = JOptionPane.showInputDialog(null, "Diu el mes: ", "Entrando", 3);
        String any = JOptionPane.showInputDialog(null, "Diu l'any: ", "Entrando", 3);
        //Pasem com int les dades i les enviem a el metode enviarAServer
        enviarAServer(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(any));

    }
    /**
     * Metode que li arriba dia, mes i any com int, el construeix com String i ho envia al Servidor
     * @param dia
     * @param mes
     * @param any
     * @throws IOException 
     */
    public void enviarAServer(int dia, int mes, int any) throws IOException {
        //Enviem el dia, mes i any, separat per espais en forma de string
        outToServer.writeBytes(dia + " " + mes + " " + any);
        //Tanquem els recursos
        bf.close();
        outToServer.close();
        sServer.close();
    }
    /**
     * Metode main que crida a la clase amb un Host i un port.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        new TimeClient(HOST, PORT);
    }
}
