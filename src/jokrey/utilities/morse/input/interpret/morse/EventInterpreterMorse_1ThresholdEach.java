package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;

/**
 * Have an arbitrary threshold. Above threshold entails long signal, below entails short signal.
 * Obviously works perfectly for pure data. Does not work when the
 */
public class EventInterpreterMorse_1ThresholdEach extends EventInterpreterMorse {
    //Theoretically adjustable Algorithm Parameters
    public void setThreshold(double newThreshold) {
        belowIsShortSignal_aboveIsLongSignal_threshold = newThreshold;
    }
    protected double belowIsShortSignal_aboveIsLongSignal_threshold = 1.5;
    protected double aboveIsLetterPause_threshold = 1.5;
    public double arbitrary_letterPause_threshold_factor_for_word_pause = 5;

    @Override protected void preCalculateRuntimeVariables(Event[] preprocessed_events) {}
    @Override public boolean isShortSignal(Event event) {
        return event.tookInSeconds < belowIsShortSignal_aboveIsLongSignal_threshold;
    }
    @Override public boolean isLongSignal(Event event) {
        return event.tookInSeconds >= belowIsShortSignal_aboveIsLongSignal_threshold;
    }
    @Override public boolean isLetterPause(Event event) {
        return event.tookInSeconds >= aboveIsLetterPause_threshold;
    }
    @Override public boolean isWordPause(Event event) {
//        System.out.println("isWordPause - t: "+event.tookInSeconds+", abLP: "+aboveIsLetterPause_threshold+", word factor: "+arbitrary_letterPause_threshold_factor_for_word_pause+", times: "+(aboveIsLetterPause_threshold * arbitrary_letterPause_threshold_factor_for_word_pause));
        return event.tookInSeconds > aboveIsLetterPause_threshold * arbitrary_letterPause_threshold_factor_for_word_pause;
    }
    @Override public Event[] getPreProcessedEvents() {
        return getCapturedEvents();
    }
}