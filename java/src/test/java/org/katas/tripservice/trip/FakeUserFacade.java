package org.katas.tripservice.trip;

import org.katas.tripservice.user.User;

public class FakeUserFacade implements IUserFacade {
    private User user = null;

    @Override
    public User getLoggedUser() {
        return user;
    }

    public void setLoggedUser(User user) {
        this.user = user;
    }
}
