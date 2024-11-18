package server.Utility;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import server.DataAccessObjects.ClientsDepositsDAO;
import server.DataAccessObjects.NotificationsDAO;
import server.Enums.ResponseStatus;
import server.Models.DTO.*;
import server.Models.Entities.*;
import server.Models.TCP.Response;
import server.Models.TCP.Request;
import server.Services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
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
    private ClientsDepositsService clientsDepositsService = new ClientsDepositsService();
    NotificationsDAO notificationsDAO = new NotificationsDAO();
    NotificationService notificationService1 = new NotificationService(notificationsDAO);
    // добавить другие

    public ClientThread(Socket clientSocket) throws IOException {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    private Client convertDtoToClient(ClientDTO clientDTO) {
        return new Client(clientDTO.getId());
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
                        List<DepositDTO> depositDTOs = allDeposits.stream()
                                .map(DepositDTO::new)
                                .collect(Collectors.toList());

                        if (depositDTOs != null && !depositDTOs.isEmpty()) {

                            String depositsJson = gson.toJson(depositDTOs);

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
                        List<User> allUsers = userService.findAllEntities();
                        List<UserDTO> clientDTOs = allUsers.stream()
                                .map(UserDTO::new)
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
                    case GETNOTIFICATIONS: {

                        int clientId = gson.fromJson(request.getRequestMessage(), Integer.class);

                        List<Notifications> clientNotifications = notificationService1.findNotificationsByClientId(clientId);

                        System.out.println("Список: " + clientNotifications);

                        List<NotificationDTO> notificationDTOs = clientNotifications.stream()
                                .map(notification -> new NotificationDTO(notification.getId(), notification.getMessage(), notification.isRead()))
                                .collect(Collectors.toList());

                        String notificationsJson = gson.toJson(notificationDTOs);
                        System.out.println("Уведомления клиента JSON: " + notificationsJson);

                        response = new Response(ResponseStatus.OK, "Notifications retrieved successfully.", notificationsJson);
                        break;
                    }
                    case EOPENDEPOSIT: {
                        try {
                            JsonObject jsonObject = gson.fromJson(request.getRequestMessage(), JsonObject.class);
                            System.out.println("Полученные данные: " + jsonObject);

                            String nameDeposit = jsonObject.get("nameDeposit").getAsString();
                            double firstAmount = jsonObject.get("firstAmount").getAsDouble();
                            String openingDateStr = jsonObject.has("openingDate") && !jsonObject.get("openingDate").isJsonNull()
                                    ? jsonObject.get("openingDate").getAsString()
                                    : null;

                            boolean isOpen = jsonObject.get("isOpen").getAsBoolean();
                            int idDeposit = jsonObject.get("idDeposit").getAsInt();

                            Date openingDate = null;

                            if (openingDateStr != null) {
                                try {
                                    Map<String, String> monthTranslations = new HashMap<>();
                                    monthTranslations.put("янв.", "Jan");
                                    monthTranslations.put("февр.", "Feb");
                                    monthTranslations.put("март", "Mar");
                                    monthTranslations.put("апр.", "Apr");
                                    monthTranslations.put("май", "May");
                                    monthTranslations.put("июнь", "Jun");
                                    monthTranslations.put("июль", "Jul");
                                    monthTranslations.put("авг.", "Aug");
                                    monthTranslations.put("сент.", "Sep");
                                    monthTranslations.put("окт.", "Oct");
                                    monthTranslations.put("нояб.", "Nov");
                                    monthTranslations.put("дек.", "Dec");

                                    // Заменяем русские месяцы на английские
                                    for (Map.Entry<String, String> entry : monthTranslations.entrySet()) {
                                        openingDateStr = openingDateStr.replace(entry.getKey(), entry.getValue());
                                    }

                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
                                    LocalDate date = LocalDate.parse(openingDateStr, formatter);
                                    openingDate = Date.valueOf(date); // Сохраняем преобразованную дату
                                } catch (DateTimeParseException e) {
                                    throw new IllegalArgumentException("Неверный формат даты: " + openingDateStr, e);
                                }
                            }

                            System.out.println("Запрос на изменение данных депозита: " +
                                    "Имя депозита: " + nameDeposit +
                                    ", Сумма: " + firstAmount +
                                    ", Дата открытия: " + openingDate +
                                    ", Открыт: " + isOpen +
                                    ", idDeposit: " + idDeposit);

                            // Проверяем существование депозита
                            ClientsDeposits existingDeposit = clientsDepositsService.findByIdDeposit(idDeposit, nameDeposit);
                            if (existingDeposit == null) {
                                throw new IllegalArgumentException("Депозит с именем \"" + nameDeposit + "\" для клиента с ID " + idDeposit + " не найден.");
                            }

                            // Обновляем поля
                            existingDeposit.setOpen(isOpen);
                            existingDeposit.setFirstAmount(firstAmount);
                            existingDeposit.setOpeningDate(openingDate);

                            // Сохраняем изменения
                            clientsDepositsService.updateEntity(existingDeposit);

                            response = new Response(
                                    ResponseStatus.OK,
                                    "Данные депозита успешно обновлены!",
                                    gson.toJson(existingDeposit)
                            );
                            System.out.println("Данные депозита успешно обновлены: " + existingDeposit);

                        } catch (IllegalArgumentException e) {
                            response = new Response(ResponseStatus.ERROR, "Ошибка: " + e.getMessage());
                            System.out.println("Ошибка: " + e.getMessage());
                        } catch (Exception e) {
                            response = new Response(ResponseStatus.ERROR, "Ошибка сервера при обработке запроса.");
                            System.err.println("Серверная ошибка: " + e.getMessage());
                        }
                        break;
                    }



                    case ECLOSEDEPOSIT: {
                        JsonObject jsonObject = gson.fromJson(request.getRequestMessage(), JsonObject.class);
                        System.out.println("Полученные данные: " + jsonObject);

                        String nameDeposit = jsonObject.get("nameDeposit").getAsString();
                        int idDeposit = jsonObject.get("idDeposit").getAsInt();

                        System.out.println("Запрос на закрытие депозита с ID: " + idDeposit);

                        try {
                            ClientsDeposits existingRequest = clientsDepositsService.findByIdDeposit(idDeposit, nameDeposit);
                            if (existingRequest == null) {
                                throw new IllegalArgumentException("Связь депозита и клиента не найдена для idDeposit: " + idDeposit + " и nameDeposit: " + nameDeposit);
                            }

                            existingRequest.setOpen(false);
                            existingRequest.setOpeningDate(null);
                            existingRequest.setFirstAmount(0);

                            clientsDepositsService.updateEntity(existingRequest);

                            response = new Response(
                                    ResponseStatus.OK,
                                    "Депозит успешно закрыт!",
                                    gson.toJson(existingRequest)
                            );
                            System.out.println("Депозит успешно закрыт: " + existingRequest);

                        } catch (IllegalArgumentException e) {
                            response = new Response(ResponseStatus.ERROR, "Ошибка: " + e.getMessage());
                            System.out.println("Ошибка: " + e.getMessage());
                        } catch (Exception e) {
                            response = new Response(ResponseStatus.ERROR, "Ошибка сервера при обработке запроса.");
                            System.err.println("Серверная ошибка: " + e.getMessage());
                        }

                        break;
                    }

                    case GETCLIENTSDEPOSITS: {
                        List<JoinClientsDepositsDTO> combinedData = clientsDepositsService.getCombinedData();
                        System.out.println("Combined Data: " + combinedData);


                        if (combinedData != null && !combinedData.isEmpty()) {
                            String depositsJson = gson.toJson(combinedData);

                            response = new Response(ResponseStatus.OK, "Deposits retrieved successfully.", depositsJson);
                        } else {
                            response = new Response(ResponseStatus.OK, "No deposits found.", "[]");
                        }

                        System.out.println("Response object for GETCLIENTSDEPOSIT: " + response);
                        break;
                    }

                    default:
                        response = new Response(ResponseStatus.ERROR, "Unknown request type.");
                        break;
                }

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