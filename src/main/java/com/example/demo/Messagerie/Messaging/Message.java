package com.example.demo.Messagerie.Messaging;
import java.util.Date;

public class Message {
    private String content;         // Contenu du message
    private Date timeStamp;         // Date et heure du message
    private String messageType;     // Type de message (texte, audio, vidéo, etc.)
    private long userId;            // Clé étrangère vers la table users
    private long conversationId;    // Clé étrangère vers la table conversation

    // Constructeur
    public Message(String content, Date timeStamp, String messageType, long userId, long conversationId) {
        this.content = content;
        this.timeStamp = timeStamp;
        this.messageType = messageType;
        this.userId = userId;
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }
}