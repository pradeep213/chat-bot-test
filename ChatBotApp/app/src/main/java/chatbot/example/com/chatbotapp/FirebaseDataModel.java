package chatbot.example.com.chatbotapp;

import java.util.ArrayList;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class FirebaseDataModel {

    ArrayList<ResponseModel> messageDataList;

    public ArrayList<ResponseModel> getMessageDataList() {
        return messageDataList;
    }

    public void setMessageDataList(ArrayList<ResponseModel> messageDataList) {
        this.messageDataList = messageDataList;
    }
}
