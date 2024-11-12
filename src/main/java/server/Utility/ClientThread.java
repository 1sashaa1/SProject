package server.Utility;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Hibernate;
import server.Enums.ResponseStatus;
import server.Models.DTO.ClientDTO;
import server.Models.Entities.Client;
import server.Models.Entities.Deposit;
import server.Models.Entities.Notifications;
import server.Models.Entities.User;
import server.Models.TCP.Response;
import server.Models.TCP.Request;
import server.Services.ClientService;
import server.Services.DepositService;
import server.Services.NotificationService;
import server.Services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private server.Models.TCP.Request request;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;

    private UserService userService = new UserService();
    private ClientService clientService = new ClientService();
    private DepositService depositService = new DepositService();
    private NotificationService notificationService = new NotificationService();
    // добавить другие

    public ClientThread(Socket clientSocket) throws IOException {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                String message = in.readLine();

                if (message == null) {
                    // Клиент закрыл соединение
                    break;
                }

                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                        //.excludeFieldsWithoutExposeAnnotation()
                        .create();


                Request request = gson.fromJson(message, Request.class);
                System.out.println("Запрос: " + request.getRequestType());
                switch (request.getRequestType()) {
                    case REGISTER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        System.out.println("Полученный пользователь:" + request.getRequestMessage());

                        if (userService.findAllEntities().stream().noneMatch(x -> x.getLogin().equalsIgnoreCase(user.getLogin()))) {
                            clientService.saveEntity(user.getClient());
                            userService.saveEntity(user);
                            userService.findAllEntities();
                            User returnUser;
                            returnUser = userService.findEntity(user.getId());
                            System.out.println("ID: " + user.getId());
                            response = new Response(ResponseStatus.OK, "User registered successfully.", gson.toJson(returnUser));
                            System.out.println("Response object: " + response);
                            System.out.println("Полученный пользователь" + user);

                        } else {
                            response = new Response(ResponseStatus.ERROR, "User already exists.");
                        }
                        break;
                    }
                    case LOGIN: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        System.out.println("Полученный пользователь: " + request.getRequestMessage());

                        // Найдите пользователя с соответствующим логином и паролем
                        Optional<User> optionalUser = userService.findAllEntities().stream()
                                .filter(x -> x.getLogin().equalsIgnoreCase(user.getLogin()) && x.getPassword().equals(user.getPassword()))
                                .findFirst();

                        if (optionalUser.isPresent()) {
                            User returnUser = optionalUser.get();  // Получение найденного пользователя
                            userService.findAllEntities();
                            User returnUserId;
                            returnUserId = userService.findEntity(returnUser.getId());
                            System.out.println("returnUser: " + returnUserId);
                            response = new Response(ResponseStatus.OK, "User login successfully.", gson.toJson(returnUserId));
                            System.out.println("ID: " + returnUserId.getId());
                            System.out.println("Response object: " + response);
                            System.out.println("Полученный пользователь: " + returnUserId);
                        } else {
                            response = new Response(ResponseStatus.ERROR, "User not found or incorrect credentials.");
                            System.out.println("Пользователь не найден или неверные учетные данные.");
                        }
                        break;
                    }
                    case ADDDEPOSIT: {
                        Deposit deposit = gson.fromJson(request.getRequestMessage(), Deposit.class);
                        System.out.println("Полученный вклад:" + request.getRequestMessage());

                        if (depositService.findAllEntities().stream().noneMatch(x -> x.getName().equalsIgnoreCase(deposit.getName()))) {
                            depositService.saveEntity(deposit);
                            depositService.findAllEntities();
                            Deposit returnDeposit;
                            returnDeposit = depositService.findEntity(deposit.getId());
                            System.out.println("ID: " + deposit.getId());
                            response = new Response(ResponseStatus.OK, "Deposit added successfully.", gson.toJson(returnDeposit));
                            System.out.println("Response object: " + response);
                            System.out.println("Полученный депозит: " + deposit);

                        } else {
                            response = new Response(ResponseStatus.ERROR, "Deposit already exists.");
                        }
                        break;
                    }
                    case GETDEPOSITS: {
                        List<Deposit> allDeposits = depositService.findAllEntities();

                        if (allDeposits != null && !allDeposits.isEmpty()) {

                            String depositsJson = gson.toJson(allDeposits);

                            response = new Response(ResponseStatus.OK, "Deposits retrieved successfully.", depositsJson);
                        } else {
                            response = new Response(ResponseStatus.OK, "No deposits found.", "[]");
                        }

                        System.out.println("Response object for GETDEPOSIT: " + response);
                        break;
                    }
                    case DELETEDEPOSIT: {
                        int depositId = gson.fromJson(request.getRequestMessage(), Integer.class);
                        Deposit depositToDelete = depositService.findEntity(depositId);

                        if (depositToDelete != null) {
                            depositService.deleteEntity(depositToDelete);
                            depositService.findAllEntities();
                            response = new Response(ResponseStatus.OK, "Deposit deleted successfully.", gson.toJson(depositToDelete));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Deposit not found.");
                        }
                        break;
                    }
                    case UPDATEDEPOSIT: {
                        Deposit deposit = gson.fromJson(request.getRequestMessage(), Deposit.class);
                        System.out.println("Полученный вклад:" + request.getRequestMessage());

                        if (depositService.findAllEntities().stream().anyMatch(x -> x.getId() == deposit.getId())) {
                            depositService.updateEntity(deposit);
                            depositService.findAllEntities();
                            Deposit returnDeposit;
                            returnDeposit = depositService.findEntity(deposit.getId());
                            System.out.println("ID: " + deposit.getId());
                            response = new Response(ResponseStatus.OK, "Deposit updated successfully.", gson.toJson(returnDeposit));
                            System.out.println("Response object: " + response);
                            System.out.println("Полученный депозит: " + deposit);

                        } else {
                            response = new Response(ResponseStatus.ERROR, "Deposit doesnt exists.");
                        }
                        break;
                    }
                    case GETCLIENTS: {
                        List<Client> allClients = clientService.findAllEntities();
                        List<ClientDTO> clientDTOs = allClients.stream()
                                .map(ClientDTO::new)
                                .collect(Collectors.toList());

                        String clientsJson = gson.toJson(clientDTOs);
                        System.out.println("клиент json: " + clientsJson);

                        response = new Response(ResponseStatus.OK, "Clients retrieved successfully.", clientsJson);
                        break;
                    }


                    case SENDNOTIFICATION: {
                        Notifications notification = gson.fromJson(request.getRequestMessage(), Notifications.class);
                        System.out.println("Полученная рассылка:" + request.getRequestMessage());

                        notificationService.saveEntity(notification);
                        notificationService.findAllEntities();
                        Notifications returnNotification;
                        returnNotification = notificationService.findEntity(notification.getId());
                        System.out.println("ID: " + notification.getId());
                        response = new Response(ResponseStatus.OK, "Notification added successfully.", gson.toJson(returnNotification));
                        System.out.println("Response object: " + response);
                        System.out.println("Полученная рассылка: " + notification);
                        break;
                    }

                    default:
                        response = new Response(ResponseStatus.ERROR, "Unknown request type.");
                        break;
                }

                // Отправка ответа клиенту
                String jsonResponse = new Gson().toJson(response);
                System.out.println(gson.toJson(response));
                System.out.println("Sending response: " + jsonResponse);
                out.println(gson.toJson(response));

                out.flush();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}