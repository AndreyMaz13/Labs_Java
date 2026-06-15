package org.example.lab7.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final String HOST =
            "localhost";

    private static final int PORT =
            12345;

    public static void main(String[] args) {

        try (
                Socket socket =
                        new Socket(
                                HOST,
                                PORT
                        );

                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()
                                )
                        );

                PrintWriter out =
                        new PrintWriter(
                                socket.getOutputStream(),
                                true
                        );

                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(
                                        System.in
                                )
                        )
        ) {

            System.out.println(
                    "Подключено к серверу."
            );

            String userInput;

            while (
                    (userInput = stdIn.readLine())
                            != null
            ) {

                out.println(userInput);

                String response =
                        in.readLine();

                System.out.println(
                        "Ответ сервера: "
                                + response
                );
            }

        } catch (UnknownHostException e) {

            System.err.println(
                    "Неизвестный хост"
            );

        } catch (IOException e) {

            System.err.println(
                    "Ошибка: "
                            + e.getMessage()
            );
        }
    }
}