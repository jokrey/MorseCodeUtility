package jokrey.utilities.morse.input.interpret;

import jokrey.utilities.morse.SignalCharacterAlphabet;
import jokrey.utilities.morse.input.Event;
import jokrey.utilities.morse.input.InputReceiver;

import java.util.ArrayList;
import java.util.Collections;

public abstract class EventInterpreter {
    protected final ArrayList<Event> captured_events = new ArrayList<>();

    protected final SignalCharacterAlphabet alphabet;
    public EventInterpreter(SignalCharacterAlphabet alphabet) {
        this.alphabet=alphabet;
    }


    public Event[] getCapturedEvents() {
        return captured_events.toArray(new Event[0]);
    }
    public void setEventsFrom(InputReceiver receiver) {
        setEvents(receiver.getEvents());
    }
    public void setEvents(Event... events) {
        captured_events.clear();
        Collections.addAll(captured_events, events);
    }

    public abstract String getAnalysisResult();
}