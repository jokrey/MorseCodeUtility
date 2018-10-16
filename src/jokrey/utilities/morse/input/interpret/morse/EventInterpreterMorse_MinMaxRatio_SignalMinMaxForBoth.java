package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;

/**
 * Same as EventInterpreterMorse_MinMaxRatio_MinMaxFromEach, but use signal min max for pause calculation also.
 * Can help when there are long pauses without input.
 */
public class EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth extends EventInterpreterMorse_MinMaxRatio_MinMaxFromEach {
    public EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth() {
        shortLongRatioToTriggerLetterPause = 0.6; //1 will be a word pause or something more insane.
        shortLongRatioToTriggerWordPause = 3;
    }

    @Override protected void preCalculateRuntimeVariables(Event[] preprocessed_events) {
        double[] sl_s = Event.getShortestAndLongestSignal(preprocessed_events);
        shortest_signal=sl_s[0];
        longest_signal=sl_s[1];
        shortest_pause=sl_s[0];
        longest_pause=sl_s[1];
    }
}