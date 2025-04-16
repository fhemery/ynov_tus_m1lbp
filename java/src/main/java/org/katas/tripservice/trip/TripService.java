package org.katas.tripservice.trip;

import org.katas.tripservice.exception.UserNotLoggedInException;
import org.katas.tripservice.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	IUserFacade userFacade;
	ITripFacade tripFacade;

	TripService() {
		userFacade = new UserFacade();
		tripFacade = new TripFacade();
	}

	TripService(IUserFacade userFacade, ITripFacade tripFacade) {
		this.userFacade = userFacade;
		this.tripFacade = tripFacade;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = userFacade.getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
				return this.tripFacade.findTripsByUser(user);
            }
        }
        return new ArrayList<Trip>();
    }
}
