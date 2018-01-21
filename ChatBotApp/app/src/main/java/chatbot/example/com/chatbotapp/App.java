package chatbot.example.com.chatbotapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class App extends Application{

    public static App instance;
    public static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
    }

    public static App getAppContext(){
        return instance;
    }

    public static FirebaseDatabase getFirebaseDatabase(){
        return database;
    }
}
