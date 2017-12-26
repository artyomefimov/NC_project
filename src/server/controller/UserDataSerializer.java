package server.controller;

import server.model.Journal;

import java.io.*;
import java.util.Map;

public class UserDataSerializer {

    public void writeData(Map<String, String> userData, String path) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(path));
            objectOutputStream.writeObject(userData);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Map<String, String> readData(String path) throws IOException {
        Map<String, String> userData;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path))) {
            userData = (Map<String, String>) objectInputStream.readObject();
        } catch (EOFException e) {
            return null;
        } catch (ClassNotFoundException e1) {
            return null;
        }
        return userData;
    }
}
