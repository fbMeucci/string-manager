package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    // LATO CLIENT
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String frase, operazione;
        try {
            System.out.println("Client avviato!");
            Socket s = new Socket("localhost", 3000);
            System.out.println("Client connesso!");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            do {
                // MENU CLIENT
                System.out.println("1 maiuscolo, 2 minuscolo, 3 inverti, 4 conta caratteri, 0 FINE:");
                System.out.println("Scegli operazione: ");
                operazione = scan.nextLine();
                // SE FINE INVIO AL CLIENT CARATTERE ! PER FINIRE
                if (operazione.equals("0")) {
                    System.out.println("Il client sta terminando");
                    out.writeBytes("!" + "\n");
                    break;
                }
                System.out.println("inserisci la frase: ");
                frase = scan.nextLine();
                // INVIO AL SERVER SIA IL TIPO DI OPERAZIONE
                // CHE LA FRASE DA MANIPOLARE
                out.writeBytes(operazione + "\n");
                out.writeBytes(frase + "\n");
                // RESTO IN ATTESA DELLA RISPOSTA DEL SERVER
                frase = in.readLine();
                System.out.println("Il server ha risposto con " + frase);
            } while (true);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scan.close();
    }
}
