package com.alibubu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
    public static void main(String[] args) throws IOException
    {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket socket = new DatagramSocket(1234);
        byte[] buffer = new byte[65535];

        // these 2 packets can be the same, no need to be 2 separate objects
        DatagramPacket receivePacket = null;
        DatagramPacket replyPacket = null;

        while (true)
        {

            // Step 2 : create a DatgramPacket to receive the data.
            receivePacket = new DatagramPacket(buffer, buffer.length);

            // Step 3 : revieve the data in byte buffer.
            socket.receive(receivePacket);

            System.out.println("Client:-" + data(buffer));

            // Exit the server if the client sends "bye"
            if (data(buffer).toString().equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            buffer = "AUTO REPLY!".getBytes();
            replyPacket = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(replyPacket);

            // Clear the buffer after every message.
            buffer = new byte[65535];
        }
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
