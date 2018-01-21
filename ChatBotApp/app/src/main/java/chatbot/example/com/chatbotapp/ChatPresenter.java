package chatbot.example.com.chatbotapp;

import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class ChatPresenter implements PresenterInterface {

    ViewInterface view;
    NetworkManager networkManager;
    ResponseModel response;
    Context context;

    public ChatPresenter(ViewInterface view){
        this.view = view;
    }

    @Override
    public void chatWithBot(String message) {
        chatWithBot(message, Constants.API_KEY, Constants.CHAT_BOT_ID, Constants.EXTERNAL_ID);
    }

    @Override
    public void chatWithBot(String message, String apiKey, String chatBotId, String externalId) {
        networkManager = new NetworkManager(new GenericCallback() {
            @Override
            public void onError(String error) {
                view.onAPIErrorResponse(error);
            }

            @Override
            public void onResponse(JSONObject data) {
                response = new Gson().fromJson(data.toString(), ResponseModel.class);
                view.onAPISuccessResponse(response);
            }
        });

        networkManager.fetchData(Constants.BASSE_URL, Request.Method.GET, null, populateQueryParams(message, apiKey, chatBotId, externalId));

    }

    private HashMap<String, String> populateQueryParams(String message, String apiKey, String chatBotId, String externalId){
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(Constants.QUERY_API_KEY, apiKey);
        queryMap.put(Constants.QUERY_MESSAGE_KEY, message);
        queryMap.put(Constants.QUERY_CHAT_BOT_ID, chatBotId);
        queryMap.put(Constants.QUERY_EXTERNAL_ID, externalId);
        return queryMap;
    }

}
