package main.java;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static final int PORT = 3232;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
    }
}
