package jokrey.utilities.morse.input;

import java.util.ArrayList;

/**
 * For example usable as a touch/click receiver. Can interpret in real time.
 */
public class ActiveInputsOverTimeInputReceiver extends InputReceiver {
    private long lastSignal_timestamp = System.nanoTime();
    private boolean signalOngoing = false;

    public void restart_clock() {
        lastSignal_timestamp = System.nanoTime();
    }

    private ArrayList<Event> events = new ArrayList<>();
    public void addInput_active() {
        events.add(new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastSignal_timestamp) / 1e9), false)); //adding a pause event
        signalOngoing =true;
        lastSignal_timestamp = System.nanoTime();
    }
    public void addInput_inactive() {
        events.add(new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastSignal_timestamp) / 1e9), true)); //adding a signal event
        signalOngoing =false;
        lastSignal_timestamp = System.nanoTime();
    }

    @Override public Event[] getEvents() {
        if(lastSignal_timestamp ==-1) return new Event[]{};
        Event[] events_arr = new Event[events.size()+1];
        for(int i=0;i<events.size();i++) {
            events_arr[i]=events.get(i);
        }
        events_arr[events_arr.length-1] = new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastSignal_timestamp) / 1e9), signalOngoing);
        return events_arr;
    }
}
