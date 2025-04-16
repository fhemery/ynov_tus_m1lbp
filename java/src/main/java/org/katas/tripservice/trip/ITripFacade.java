package org.katas.tripservice.trip;

import org.katas.tripservice.user.User;

import java.util.List;

public interface ITripFacade {
    List<Trip> findTripsByUser(User user);
}
