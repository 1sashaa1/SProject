package server;

import server.Utility.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private static final int PORT_NUMBER = 5555;
    private static ServerSocket serverSocket;

    private static ClientThread clientHandler;
    private static Thread thread;
    private static List<Socket> currentSockets = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            while (true) {
                // Обрабатываем текущие подключённые сокеты
                Iterator<Socket> iterator = currentSockets.iterator();
                while (iterator.hasNext()) {
                    Socket socket = iterator.next();
                    if (socket.isClosed()) {
                        iterator.remove();
                        continue;
                    }
                    String socketInfo = "Клиент " + socket.getInetAddress() + ":" + socket.getPort() + " подключён";
                    System.out.println(socketInfo);
                }

                // Принимаем новое подключение
                try {
                    Socket socket = serverSocket.accept();//дискриптор
                    currentSockets.add(socket);

                    // Создаём и запускаем поток для обработки клиента
                    clientHandler = new ClientThread(socket);//для каждого сокета свой обрботчик
                    thread = new Thread(clientHandler);//создаём поток
                    thread.start();
                } catch (IOException e) {
                    System.err.println("Ошибка при подключении клиента: " + e.getMessage());
                }
            }
        } finally {
            shutdown(); // Очищаем ресурсы при завершении работы
        }
    }

    public static void shutdown() throws IOException {
        for (Socket socket : currentSockets) {
            if (!socket.isClosed()) {
                socket.close();
            }
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}