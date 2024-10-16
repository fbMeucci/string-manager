package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String frase;
            String operazione;
            String stringaTrasformata = "";

            do {
                // RICEVO DAL CLIENT IL TIPO DI OPERAZIONE
                operazione = in.readLine();
                if (operazione.equals("!")) {
                    System.out.println("Il client vuole chiudere");
                    s.close();
                    break;
                }
                // RICEVO DAL CLIENT LA FRASE
                frase = in.readLine();
                switch (operazione) {
                    case "1":
                        stringaTrasformata = frase.toUpperCase();
                        System.out.println(
                                "Stringa ricevuta sul thread" + Thread.currentThread().getName() + ": "
                                        + frase);
                        break;
                    case "2":
                        stringaTrasformata = frase.toLowerCase();
                        System.out.println(
                                "Stringa ricevuta sul thread" + Thread.currentThread().getName() + ": "
                                        + frase);
                        break;
                    case "3":
                        StringBuilder app = new StringBuilder();
                        app.append(frase);
                        app.reverse();
                        stringaTrasformata = app.toString();
                        System.out.println(
                                "Stringa ricevuta sul thread" + Thread.currentThread().getName() + ": "
                                        + frase);
                        break;
                    case "4":
                        stringaTrasformata = "" + frase.length();
                        System.out.println(
                                "Stringa ricevuta sul thread" + Thread.currentThread().getName() + ": "
                                        + frase);
                        break;
                }
                out.writeBytes(stringaTrasformata + "\n");
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
