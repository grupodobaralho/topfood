package grupodobaralho.topfood_android;

import android.app.Application;
import android.content.Context;

/*
    Classe utilizada para acessar o contexto da aplicacao.
*/
public class TopfoodApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getTopfoodApplicationContext(){
        return TopfoodApplication.context;
    }

}