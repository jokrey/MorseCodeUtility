package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;

/**
 * Have two arbitrary thresholds. Above checks long signals, below checks short signals
 * Pretty stupid. Having a single threshold makes way more sense.
 */
public class EventInterpreterMorse_2ThresholdsForSignal extends EventInterpreterMorse {
    //Theoretically adjustable Algorithm Parameters
    public double belowIsShortSignal_threshold = 1.2;
    public double aboveIsLongSignal_threshold = 2.2;

    public double aboveIsLetterPause_threshold = 2.2;
    public double aboveIsWordPause_threshold = 6;


    @Override protected void preCalculateRuntimeVariables(Event[] preprocessed_events) {}
    @Override public boolean isShortSignal(Event event) {
        return event.tookInSeconds < belowIsShortSignal_threshold;
    }
    @Override public boolean isLongSignal(Event event) {
        return event.tookInSeconds > aboveIsLongSignal_threshold;
    }

    @Override public boolean isLetterPause(Event event) {
        return event.tookInSeconds > aboveIsLetterPause_threshold;
    }
    @Override public boolean isWordPause(Event event) {
        return event.tookInSeconds > aboveIsWordPause_threshold;
    }
    @Override public Event[] getPreProcessedEvents() {
        return getCapturedEvents();
    }
}