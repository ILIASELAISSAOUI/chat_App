package com.example.demo.Messagerie.Users;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private long user_id;
    private String username;
    private String email;
    private String password;


    public User(Socket socket,long user_id, String username, String email, String password) {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.user_id = user_id;
            this.username = username;
            this.email = email;
            this.password = password;
        }catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


    }


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sendMessage(){
        try {

            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void ListenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgeFromGroupChat;
                while (socket.isConnected()){
                    try {
                        msgeFromGroupChat=bufferedReader.readLine();
                        System.out.println(msgeFromGroupChat);

                    }catch (IOException e){
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        System.out.println("mochkila");
                    }
                }
                System.out.println("deconicti");
            }
        }).start();
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){

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

    public static void main(String[] arg) throws IOException{
        Socket socket=new Socket("localhost",880);
        Scanner scanner=new Scanner(System.in);
        System.out.println("identifiant :");
        long id = scanner.nextLong();
        String username= scanner.nextLine();
        User user=new User(socket,id, username, "iliasaisssa@gmail.com","ilias2002");
        System.out.println("username :"+ user.getUsername() );
        user.ListenForMessage();
        user.sendMessage();
    }

}