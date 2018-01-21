package chatbot.example.com.chatbotapp;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public interface NetworkInterface {

    void fetchData(String url, int requestMethod, JSONObject params, Map<String, String> queryParams);
}
