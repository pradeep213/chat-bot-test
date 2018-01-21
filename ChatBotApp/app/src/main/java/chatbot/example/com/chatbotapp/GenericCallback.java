package chatbot.example.com.chatbotapp;

import org.json.JSONObject;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public interface GenericCallback {

    void onError(String error);

    void onResponse(JSONObject data);
}
