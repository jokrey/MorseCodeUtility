package jokrey.utilities.morse.input.interpret;

import jokrey.utilities.morse.SignalCharacterAlphabet;
import jokrey.utilities.morse.input.Event;
import jokrey.utilities.morse.input.InputReceiver;

import java.util.ArrayList;
import java.util.Arrays;
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



    public Event[] normalizeOutliers_distanceFromAverage(Event[] orig_events,
                                                         double normalization_factor_of_found_outliers_signal,
                                                         double normalization_factor_of_found_outliers_pause) {
//        double average_signal_length = Event.getAverageSignalLength(orig_events);
//        double average_pause_length = Event.getAveragePauseLength(orig_events);
//        return normalizeOutliers_distanceFromAverage(orig_events, average_signal_length, average_pause_length,
//                normalization_factor_of_found_outliers_signal,
//                normalization_factor_of_found_outliers_pause);
        double average_length = Event.getAverageLength(orig_events);
        return normalizeOutliers_distanceFromAverage(orig_events, average_length, average_length,
                normalization_factor_of_found_outliers_signal,
                normalization_factor_of_found_outliers_pause);
    }
    public Event[] normalizeOutliers_distanceFromAverage(Event[] orig_events,
                                                         double average_signal_used,
                                                         double average_pause_used,
                                                         double normalization_factor_of_found_outliers_signal,
                                                         double normalization_factor_of_found_outliers_pause) {
        ArrayList<Event> events = new ArrayList<>(orig_events.length);
        for (Event orig_event : orig_events) {
            if (orig_event.isASignal()) {
                if (orig_event.tookInSeconds <= average_signal_used * normalization_factor_of_found_outliers_signal)
//                if (Math.abs(average_signal_used - orig_event.tookInSeconds) <= average_signal_used * normalization_factor_of_found_outliers_signal)
                    events.add(orig_event);
                else
                    events.add(new Event(average_signal_used * normalization_factor_of_found_outliers_signal, orig_event.isASignal()));
            } else {
                if (orig_event.tookInSeconds <= average_pause_used * normalization_factor_of_found_outliers_pause)
//                if (Math.abs(average_pause_used - orig_event.tookInSeconds) <= average_pause_used * normalization_factor_of_found_outliers_pause)
                    events.add(orig_event);
                else
                    events.add(new Event(average_pause_used * normalization_factor_of_found_outliers_pause, orig_event.isASignal()));
            }
        }

        return events.toArray(new Event[0]);
    }
}