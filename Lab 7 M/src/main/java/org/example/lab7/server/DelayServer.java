package org.example.lab7.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DelayServer {

    private static final int PORT = 12345;

    public static void main(String[] args) {

        System.out.println(
                "Сервер запущен на порту " + PORT
        );

        try (
                ServerSocket serverSocket =
                        new ServerSocket(PORT)
        ) {

            while (true) {

                Socket clientSocket =
                        serverSocket.accept();

                System.out.println(
                        "Подключился клиент: "
                                + clientSocket.getInetAddress()
                );

                handleClient(clientSocket);
            }

        } catch (IOException e) {

            System.err.println(
                    "Ошибка сервера: "
                            + e.getMessage()
            );
        }
    }

    private static void handleClient(
            Socket clientSocket
    ) {

        try (
                clientSocket;
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(
                                        clientSocket.getInputStream()
                                )
                        );

                PrintWriter out =
                        new PrintWriter(
                                clientSocket.getOutputStream(),
                                true
                        )
        ) {

            String inputLine;

            while (
                    (inputLine = in.readLine())
                            != null
            ) {

                System.out.println(
                        "Получено: "
                                + inputLine
                );

                Thread.sleep(21000);

                out.println(inputLine);
            }

        } catch (Exception e) {

            System.err.println(
                    "Ошибка клиента: "
                            + e.getMessage()
            );
        }
    }
}