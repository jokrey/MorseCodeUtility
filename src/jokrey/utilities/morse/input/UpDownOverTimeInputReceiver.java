package jokrey.utilities.morse.input;

import java.util.ArrayList;

/**
 * InputReceiver for real time receiving of clear up(signal) and down(pause) input. For example a wave(ish) form with clear up and down thresholds.
 */
public class UpDownOverTimeInputReceiver extends InputReceiver {
    private long lastInput_timestamp = -1;


    private ArrayList<Event> events = new ArrayList<>();
    public void addNewInput_signal() {
        if(lastInput_timestamp!=-1) {//ignore a very first input....
            events.add(new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastInput_timestamp) / 1e9), true)); //adding a signal event
        }
        lastInput_timestamp=System.nanoTime();
    }
    public void addNewInput_pause() {
        if(lastInput_timestamp!=-1) {//ignore a very first input....
            events.add(new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastInput_timestamp) / 1e9), false)); //adding a pause event
        }
        lastInput_timestamp=System.nanoTime();
    }

    @Override public Event[] getEvents() {
        if(lastInput_timestamp ==-1) return new Event[]{};
        Event[] events_arr = new Event[events.size()+1];
        for(int i=0;i<events.size();i++) {
            events_arr[i]=events.get(i);
        }
        events_arr[events_arr.length-1] = new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastInput_timestamp) / 1e9), false);
        return events_arr;
    }
}
