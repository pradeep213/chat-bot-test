package chatbot.example.com.chatbotapp;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public interface PresenterInterface {

    void chatWithBot(String message);

    void chatWithBot(String message, String apiKey, String chatBotId, String externalId);
}
