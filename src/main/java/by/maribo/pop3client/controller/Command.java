package by.maribo.pop3client.controller;

import by.maribo.pop3client.model.POP3Client;

public interface Command {
	String execute(String parameters, POP3Client client) throws POP3ClientException;
}
