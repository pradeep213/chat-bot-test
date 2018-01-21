package chatbot.example.com.chatbotapp;

import android.content.Context;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class NetworkManager implements NetworkInterface{

    GenericCallback callback;
    GenericOperation networkOperation;

    public NetworkManager(GenericCallback callback){
        this.callback = callback;
    }

    @Override
    public void fetchData(String url, int requestMethod, JSONObject params, Map<String, String> queryParams) {
        networkOperation = new GenericOperation(url, requestMethod, params, queryParams);
        networkOperation.callApi(callback);
    }
}
