package jokrey.utilities.morse.input.interpret.morse;

import jokrey.utilities.morse.input.interpret.EventInterpreter;
import jokrey.utilities.morse.input.interpret.EventInterpreterTest;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EventInterpreter_Morse_DistanceFromAverageTest {
    @Test
    public void pure_test() {
        EventInterpreterTest.run_sure_universal_tests(new EventInterpreter_Morse_DistanceFromAverage());

        //up to the interpretation of the interpreter - funny.
        EventInterpreterTest.pure_test(new EventInterpreter_Morse_DistanceFromAverage(), Arrays.asList(1.0), "e");
        EventInterpreterTest.pure_test(new EventInterpreter_Morse_DistanceFromAverage(), Arrays.asList(1.0, 1.0), "t");
    }

    @Test
    public void other() {
        EventInterpreterTest.active_input_short_test(new EventInterpreter_Morse_DistanceFromAverage(), 10, 40);
        try {
            EventInterpreterTest.active_input_short_test(new EventInterpreter_Morse_DistanceFromAverage(), 10, 400);
            fail("WOAH. THIS WORKS NOW???");
        } catch(ComparisonFailure ignore) {} //expected. Just to showcase that something does not work in DistanceFromAverage that does work with MinMaxRatio
    }
}