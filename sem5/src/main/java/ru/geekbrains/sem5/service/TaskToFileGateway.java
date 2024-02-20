package ru.geekbrains.sem5.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface TaskToFileGateway {
    void writeTaskToFile(@Header(FileHeaders.FILENAME) String filename, String data);
}
