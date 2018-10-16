package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.Event;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Takes the average of signals and pauses multiplies it with some factor and uses it directly as the threshold.
 *
 * Takes a while to calibrate,
 */
public class EventInterpreterMorse_SignalAverageAsSignalThresholdAndPauseAverageAsPauseThreshold extends EventInterpreterMorse_1ThresholdEach {
    public EventInterpreterMorse_SignalAverageAsSignalThresholdAndPauseAverageAsPauseThreshold() {
        arbitrary_letterPause_threshold_factor_for_word_pause = 3;
    }

    //Theoretically adjustable Algorithm Parameters
    public double arbitrary_av_factor_to_make_it_work = 1.9; //range to pass pure test is: [1.21 , 2.01] || range for active_noisy_time_delay_test: [1.6 , >2.01]

    @Override public void setThreshold(double newThreshold) {
        throw new NotImplementedException(); // "Setting the threshold manually is not possible with this subclass"
    }

    @Override protected void preCalculateRuntimeVariables(Event[] preprocessed_events) {
        belowIsShortSignal_aboveIsLongSignal_threshold = Event.getAverageSignalLength(preprocessed_events) * arbitrary_av_factor_to_make_it_work;
        aboveIsLetterPause_threshold = Event.getAveragePauseLength(preprocessed_events) * arbitrary_av_factor_to_make_it_work;
    }
}
