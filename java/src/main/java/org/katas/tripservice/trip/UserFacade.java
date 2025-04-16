package org.katas.tripservice.trip;

import org.katas.tripservice.user.User;
import org.katas.tripservice.user.UserSession;

public class UserFacade implements IUserFacade {

    @Override
    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
