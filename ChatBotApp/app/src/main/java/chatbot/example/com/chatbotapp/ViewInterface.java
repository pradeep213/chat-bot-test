package chatbot.example.com.chatbotapp;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public interface ViewInterface {

    void onAPISuccessResponse(ResponseModel data);

    void onAPIErrorResponse(String errorMessage);
}
