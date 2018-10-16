package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;

/**
 * Same as direct upper class, but with normalization.
 */
public class EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth_Normalize extends EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth {
    public double normalization_factor_of_found_outliers_signal = 3.1; //too count them as long signal's at least.
    public double normalization_factor_of_found_outliers_pause = 6.5; //too allow word pauses

    @Override public Event[] getPreProcessedEvents() {
        return normalizeOutliers_distanceFromAverage(getCapturedEvents(), normalization_factor_of_found_outliers_signal, normalization_factor_of_found_outliers_pause);
    }
}