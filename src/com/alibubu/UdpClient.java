package com.alibubu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("192.168.0.107");    //IP address of server
        byte buf[] = null;

        // loop while user not enters "bye"
        while (true)
        {
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            buf = inp.getBytes();

            // Step 2 : Create the datagramPacket for sending
            // the data.
            DatagramPacket packet =
                    new DatagramPacket(buf, buf.length, ip, 1234);

            // Step 3 : invoke the send call to actually send
            // the data.
            ds.send(packet);

            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;

            packet = new DatagramPacket(buf, buf.length);
            ds.receive(packet);
            String replyMsg = new String(
                    packet.getData(), 0, packet.getLength());

            System.out.println("Server:-" + replyMsg);
        }
    }
}
