package org.katas.tripservice.trip;

import org.katas.tripservice.exception.UserNotLoggedInException;
import org.katas.tripservice.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    TripService tripService;
    FakeTripFacade tripFacade;
    FakeUserFacade userFacade;

    @BeforeEach
    public void setup() {
        tripFacade = new FakeTripFacade();
        userFacade = new FakeUserFacade();
        tripService = new TripService(userFacade, tripFacade);
    }


    @Test
    public void shouldThrow_whenLoggedUserIsNull() {
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null));
    }

    @Test
    public void shouldReturnEmptyList_whenUserHasNoFriends() {
        User alice = new User();
        User bob = new User();
        userFacade.setLoggedUser(alice);

        List<Trip> result = tripService.getTripsByUser(bob);

        assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnEmptyList_whenUserDoesNotHaveLoggedUserAsFriend() {
        User alice = new User();
        User bob = new User();
        User carol = new User();
        bob.addFriend(carol);

        userFacade.setLoggedUser(alice);

        List<Trip> result = tripService.getTripsByUser(bob);

        assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnTripList_whenUserHasLoggedUserAsFriend() {
        User alice = new User();
        User bob = new User();
        User carol = new User();
        bob.addFriend(carol);
        bob.addFriend(alice);

        userFacade.setLoggedUser(alice);
        List<Trip> userTrips = new ArrayList<>();
        userTrips.add(new Trip());
        tripFacade.setTrips(userTrips);

        List<Trip> result = tripService.getTripsByUser(bob);

        assertEquals(1, result.size());
        assertEquals(userTrips, result);
    }
}
