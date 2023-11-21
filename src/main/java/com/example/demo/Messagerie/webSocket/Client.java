package com.example.demo.Messagerie.webSocket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import com.example.demo.Messagerie.Users.User;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEvrything(socket, bufferedReader, bufferedWriter);
        }
    }
    public void sendMesaage(){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner= new Scanner(System.in);
            while (socket.isConnected()){
                String messageToSend=scanner.nextLine();
                bufferedWriter.write(username+":"+messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (IOException e){
            closeEvrything(socket,bufferedReader,bufferedWriter);
        }
    }
    public void ListenForMessage(){
            new Thread(() -> {
                String messageFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        messageFromGroupChat = bufferedReader.readLine();
                        System.out.println(messageFromGroupChat);
                    } catch (IOException e) {
                        closeEvrything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }).start();
        }

    public void closeEvrything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){

        try {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
            if(socket!=null){
                socket.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your username for chat");
        String username=scanner.nextLine();
        Socket socket=new Socket("localhost",880);
        Client client=new Client(socket,username);
        client.ListenForMessage();
        client.sendMesaage();


    }

}