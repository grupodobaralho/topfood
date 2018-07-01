package grupodobaralho.topfood_android.data.db.uiModels.userInteractor;

import grupodobaralho.topfood_android.data.db.model.User;

public interface IUserInteractor {

    User getProfile(String token);
}
