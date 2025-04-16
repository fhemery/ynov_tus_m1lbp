package org.katas.birthdays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BirthdayNotebookTest {

    BirthdayNotebook notebook;

    @BeforeEach
    void setUp () {
        notebook = new BirthdayNotebook();
    }

    @Test
    void shouldReturnEmptyListWhenNoBirthdayIsSet() {
        Date today = new Date();
        List<String> birthdays = notebook.getBirthdays(today);
        assertEquals(0, birthdays.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoBirthdayIsSetForToday() {
        notebook.addBirthday("Bob", new Date(2025, 3, 10));

        List<String> birthdays = notebook.getBirthdays(new Date(2025, 3, 11));
        assertEquals(0, birthdays.size());
    }

    @Test
    void shouldReturnElementsWhenDateIsCorrect() {
        notebook.addBirthday("Bob", new Date(2025, 3, 10));
        notebook.addBirthday("Alice", new Date(2025, 3, 10));
        notebook.addBirthday("Carol", new Date(2025, 3, 11));

        List<String> birthdays = notebook.getBirthdays(new Date(2025, 3, 10));
        assertEquals(2, birthdays.size());

        // BOITE BLANCHE
        assertEquals("Bob", birthdays.get(0));
        assertEquals("Alice", birthdays.get(1));

        // BOITE NOIRE <== À privilégier !
        assertTrue(birthdays.contains("Bob"));
        assertTrue(birthdays.contains("Alice"));
    }
}