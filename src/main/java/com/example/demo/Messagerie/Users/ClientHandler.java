package com.example.demo.Messagerie.Users;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    public ClientHandler(Socket socket){
        try {
            this.socket=socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername=bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("server:" +clientUsername+"has entred the chat");

        }
        catch (IOException e){
            closeEvrything(socket , bufferedReader,bufferedWriter);
        }

    }



    @Override
    public void run(){
        String messageFromClient;
        while (socket.isConnected()){
            try {
                messageFromClient=bufferedReader.readLine();
                System.out.println(messageFromClient);
                broadcastMessage(messageFromClient);

            }catch (IOException e){
                closeEvrything(socket, bufferedReader,bufferedWriter);
                break;
            }
        }



    }
    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler:clientHandlers){

            try {
                //if(!clientHandler.clientUsername.equals(clientUsername)){
                    System.out.println("sift");
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                //}
            }catch (IOException e){
                closeEvrything(socket,bufferedReader,bufferedWriter);
            }
        }
    }
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("server"+clientUsername+"has left the chat");

    }
    public void closeEvrything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();
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

}