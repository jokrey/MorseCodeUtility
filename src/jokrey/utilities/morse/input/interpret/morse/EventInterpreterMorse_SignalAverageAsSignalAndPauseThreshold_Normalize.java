package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;

/**
 * Same as direct upper class, but with normalization.
 */
public class EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold_Normalize extends EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold {
    public EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold_Normalize() {
        arbitrary_letterPause_threshold_factor_for_word_pause = 3;
    }

    public double normalization_factor_of_found_outliers_signal = 3.1; //too count them as long signal's at least.
    public double normalization_factor_of_found_outliers_pause = 6.5; //too allow word pauses

    @Override public Event[] getPreProcessedEvents() {
        return normalizeOutliers_distanceFromAverage(getCapturedEvents(), normalization_factor_of_found_outliers_signal, normalization_factor_of_found_outliers_pause);
    }
}