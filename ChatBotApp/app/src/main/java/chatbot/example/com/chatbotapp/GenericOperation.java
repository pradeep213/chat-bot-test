package chatbot.example.com.chatbotapp;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class GenericOperation {

    String url;
    int requestMethod;
    JSONObject params;
    Map<String, String> queryParams;
    String finalUrl;

    public GenericOperation(String url, int requestMethod, JSONObject params, Map<String, String> queryParams){
        this.url = url;
        this.requestMethod = requestMethod;
        this.params = params;
        this.queryParams = queryParams;
    }

    public void callApi(final GenericCallback callback){

        VolleyNetworkClient client = VolleyNetworkClient.getInstance();
        if (queryParams != null && queryParams.size() > 0)
            finalUrl = append(url, queryParams);
        else
            finalUrl = url;

        JsonObjectRequest request = new JsonObjectRequest(requestMethod, finalUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorResponse = new String(error.networkResponse.data);
                callback.onError(errorResponse);
            }
        });

        client.getRequestQueue().add(request);

    }

    private String append(String url, Map<String, String> queryParams){
        String returnUrl = url;

        if (queryParams != null && queryParams.size() > 0) {
            returnUrl = returnUrl.concat("?");
            try {
                for (Map.Entry<String, String> tempEntry : queryParams.entrySet()) {
                    if (returnUrl.endsWith("?")) {
                        returnUrl = returnUrl + tempEntry.getKey() + "=" + URLEncoder.encode(tempEntry.getValue(), "UTF-8");
                    } else {
                        returnUrl = returnUrl + "&" + tempEntry.getKey() + "=" + URLEncoder.encode(tempEntry.getValue(), "UTF-8");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return returnUrl;
    }
}
