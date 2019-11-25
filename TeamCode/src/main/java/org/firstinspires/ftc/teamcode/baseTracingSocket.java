package org.firstinspires.ftc.teamcode;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class baseTracingSocket {
    private ServerSocket server;
    private int port = 9876;
    boolean socketIsOpen = false;
    OutputStream output;

    public void Start() throws IOException
    {
        server = new ServerSocket(port);
        Socket socket = server.accept();
        output = socket.getOutputStream();
        socketIsOpen = true;
    }

    public void stop() throws IOException
    {
        socketIsOpen = false;
        output.close();
        server.close();
    }
}


