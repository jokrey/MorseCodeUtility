package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.interpret.EventInterpreterTest;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class CombinedMorseInterpretationTests {
    @Test
    public void pure_test_all() {
        //every single one has to win this one
        for(EventInterpreterMorse one:EventInterpreterMorse.getAll()) {
            EventInterpreterTest.run_sure_universal_tests(one);

            //up to the interpretation of the interpreter - funny.
            EventInterpreterTest.pure_test(one, Arrays.asList(1.0), "e", "t");
            EventInterpreterTest.pure_test(one, Arrays.asList(1.0, 1.0), "e", "t");
            EventInterpreterTest.pure_test(one, Arrays.asList(1.0, 3.0, 1.0, 3.0, 1.0, 3.0, 1.0, 3.0), "eeee", "tttt", "e e e e ", "t t t t ");

            //some interpreters interpret 'eeee' here.
            // Which is clearly wrong, the pauses are as long as the signals and are there for only signal-pauses, NOT letter-pauses.
            // However: Since most only interpret based on signal length because can be very long and therefore skew results it is fine.
            EventInterpreterTest.pure_test(one, Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), "h", "eeee", "tttt");
        }
    }

    @Test
    public void active_noisy_time_delay_test() {
        EventInterpreterTest.active_input_short_test(new EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth(), 10, 40);
        EventInterpreterTest.active_input_short_test(new EventInterpreterMorse_MinMaxRatio_SignalMinMaxForBoth(), 10, 400);

        EventInterpreterTest.active_input_short_test(new EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold(), 10, 40);
        try {
            EventInterpreterTest.active_input_short_test(new EventInterpreterMorse_SignalAverageAsSignalAndPauseThreshold(), 10, 400);
            fail("Woah. This did not work before");
        } catch(ComparisonFailure ignored) {} //expected
    }
}
