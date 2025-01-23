package server;

import server.Models.Entities.ClientsDeposits;
import server.Services.ClientsDepositsService;
import server.Services.EmployeeService;
import server.Utility.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.concurrent.*;


//Паттерн производитель-потребитель
public class Main {
    private static final int PORT_NUMBER = 5555;
    private static final int THREAD_POOL_SIZE = 10; // Количество потоков в пуле
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    private static BlockingQueue<Socket> taskQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            System.out.println("Сервер запущен на порту " + PORT_NUMBER);

            // Поток для обработки очереди задач
            Runnable taskProcessor = () -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Socket socket = taskQueue.take();
                        threadPool.submit(new ClientThread(socket));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            new Thread(taskProcessor).start();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключён клиент: " + socket.getInetAddress());
                taskQueue.put(socket);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
