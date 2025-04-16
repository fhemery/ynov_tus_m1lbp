package org.katas.tripservice.trip;

import org.katas.tripservice.user.User;

import java.util.List;

public class TripFacade implements ITripFacade {
    @Override
    public List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
