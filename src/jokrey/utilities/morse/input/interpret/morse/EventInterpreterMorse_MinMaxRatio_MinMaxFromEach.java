package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;

import static jokrey.utilities.morse.BinarySignalUnit.getPercentageRatioBetween;

/**
 * Should allow one to use thresholds in percent instead of absolute..
 *
 * This algorithm typically calibrates with only one long and one short press anywhere in the sequence. So very fast.
 * It however is not great with outliers. Normalization helps, but not forever.
 */
public class EventInterpreterMorse_MinMaxRatio_MinMaxFromEach extends EventInterpreterMorse {
    //Theoretically adjustable Algorithm Parameters
    public double shortLongRatioToTriggerLongSignal = 0.6;
    public double shortLongRatioToTriggerLetterPause = 0.2; //1 will be a word pause or something more insane.
    public double shortLongRatioToTriggerWordPause = 0.8;

    //runtime vars
    protected double shortest_signal;
    protected double longest_signal;
    protected double shortest_pause;
    protected double longest_pause;
    @Override protected void preCalculateRuntimeVariables(Event[] preprocessed_events) {
        double[] sl_s = Event.getShortestAndLongestSignal(preprocessed_events);
        double[] sl_p = Event.getShortestAndLongestPause(preprocessed_events);
        shortest_signal=sl_s[0];
        longest_signal=sl_s[1];
        shortest_pause=sl_p[0];
        longest_pause=sl_p[1];
    }
    
    @Override public boolean isShortSignal(Event event) {
        double time_ratio = getPercentageRatioBetween(event.tookInSeconds, shortest_signal, longest_signal);
        return time_ratio < shortLongRatioToTriggerLongSignal;
    }
    @Override public boolean isLongSignal(Event event) {
        double time_ratio = getPercentageRatioBetween(event.tookInSeconds, shortest_signal, longest_signal);
        return time_ratio >= shortLongRatioToTriggerLongSignal;
    }
    @Override public boolean isLetterPause(Event event) {
        double time_ratio = getPercentageRatioBetween(event.tookInSeconds, shortest_pause, longest_pause);
//        System.out.println("isLetterPause - t: "+event.tookInSeconds+", sp: "+shortest_pause+", lp: "+longest_pause+", tr: "+time_ratio);
        return time_ratio >= shortLongRatioToTriggerLetterPause;
    }
    @Override public boolean isWordPause(Event event) {
        double time_ratio = getPercentageRatioBetween(event.tookInSeconds, shortest_pause, longest_pause);
//        System.out.println("isWordPause - t: "+event.tookInSeconds+", sp: "+shortest_pause+", lp: "+longest_pause+", tr: "+time_ratio);
        return time_ratio > shortLongRatioToTriggerWordPause;
    }
    @Override public Event[] getPreProcessedEvents() {
        return getCapturedEvents();
    }
}