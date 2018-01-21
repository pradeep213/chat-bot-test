package chatbot.example.com.chatbotapp;

import android.app.Application;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class App extends Application{

    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getAppContext(){
        return instance;
    }
}
