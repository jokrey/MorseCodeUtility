package jokrey.utilities.morse.input;

import java.util.ArrayList;

/**
 * When input is clear. For example to test EventInterpreters.
 */
public class PureInputReceiver extends InputReceiver {
    private ArrayList<Event> events = new ArrayList<>();
    public void addNewInput_signal(double took_in_seconds) {
        events.add(new Event(took_in_seconds, true));
    }
    public void addNewInput_pause(double took_in_seconds) {
        events.add(new Event(took_in_seconds, false));
    }

    @Override public Event[] getEvents() {
        return events.toArray(new Event[0]);
    }
}
