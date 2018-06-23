package grupodobaralho.topfood_android.data.localStorage;

public class UserBusiness {

    private String accessToken;

    private static final String prefix = "Bearer ";
//    private static final String anonymousToken = "Bearer anonymous";

    private static UserBusiness instance;

    private UserBusiness() {}

    public static synchronized UserBusiness getInstance() {
        if(instance == null)
            instance = new UserBusiness();

        return instance;
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = prefix + accessToken;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.ACCESS_TOKEN, this.accessToken);
    }

    public String getAccessToken() {
        if (accessToken == null) {
            accessToken = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.ACCESS_TOKEN, accessToken);
    }

    public boolean isLogged(){
//        || getAccessToken().equals("Bearer anonymous")
        if(getAccessToken() == null)
            return false;
        else
            return true;
    }
}