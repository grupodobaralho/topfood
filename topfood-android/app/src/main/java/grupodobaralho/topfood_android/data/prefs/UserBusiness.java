package grupodobaralho.topfood_android.data.prefs;

public class UserBusiness {

    private String accessToken;
    private String userid;

    private static final String prefix = "Bearer ";

    private static UserBusiness instance;

    private UserBusiness() {}

    public static synchronized UserBusiness getInstance() {
        if(instance == null)
            instance = new UserBusiness();

        return instance;
    }

    public void updateAccessToken(String accessToken, String userid) {
        this.accessToken = prefix + accessToken;
        this.userid = userid;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.ACCESS_TOKEN, this.accessToken);
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.USERID, this.userid);
    }

    public String getAccessToken() {
        if (accessToken == null) {
            accessToken = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        }
        return accessToken;
    }

    public String getUserId() {
        if (userid == null) {
            userid = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.USERID);
        }
        return userid;
    }

    public void removeAccessToken() {
        SharedPreferencesOperations.removeFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        SharedPreferencesOperations.removeFromPrefs(SharedPreferencesOperations.USERID);
        accessToken = null;
        userid = null;
    }

    public boolean isLogged() {
        if(getAccessToken() == null)
            return false;
        else
            return true;
    }
}