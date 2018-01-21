package chatbot.example.com.chatbotapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class VolleyNetworkClient {

    private RequestQueue requestQueue;
    private static VolleyNetworkClient instance;

    private VolleyNetworkClient() {
        requestQueue = Volley.newRequestQueue(App.getAppContext());

    }

    public static VolleyNetworkClient getInstance() {

        if (instance == null) {
            instance = new VolleyNetworkClient();
        }

        return instance;
    }

    public RequestQueue getRequestQueue(){

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(App.getAppContext());
        }

        return requestQueue;

    }

}
