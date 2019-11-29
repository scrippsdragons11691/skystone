package org.firstinspires.ftc.teamcode;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class baseTracingSocket {

    boolean socketIsOpen = false;
    OutputStream output;
    Socket clientSocket;

    public void Start() throws IOException
    {
        clientSocket = new Socket(GlobalSettings11691.dataServerHostIP, GlobalSettings11691.dataServerHostPort);
        output = clientSocket.getOutputStream();
        socketIsOpen = true;
    }

    public void stop() throws IOException
    {
        socketIsOpen = false;
        if(output != null)
            output.close();

        if(clientSocket != null)
            clientSocket.close();
    }
}


