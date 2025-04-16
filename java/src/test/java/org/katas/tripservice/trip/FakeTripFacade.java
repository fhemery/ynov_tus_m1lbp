package org.katas.tripservice.trip;

import org.katas.tripservice.user.User;

import java.util.ArrayList;
import java.util.List;

public class FakeTripFacade implements ITripFacade {
    List<Trip> trips = new ArrayList<>();

    @Override
    public List<Trip> findTripsByUser(User user) {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
