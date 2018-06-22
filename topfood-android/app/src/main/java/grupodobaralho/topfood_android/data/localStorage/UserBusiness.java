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
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isLogged(){
//        || getAccessToken().equals("Bearer anonymous")
        if(getAccessToken() == null)
            return false;
        else
            return true;
    }
}