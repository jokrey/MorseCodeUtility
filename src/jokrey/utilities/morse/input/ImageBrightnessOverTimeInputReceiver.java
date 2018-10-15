package jokrey.utilities.morse.input;

import jokrey.utilities.animation.util.AEImage;

/**
 * Real time implementation of the ImageBrightnessBasedInputReceiver.
 */
public class ImageBrightnessOverTimeInputReceiver extends ImageBrightnessBasedInputReceiver {
    private long lastEventTimestamp = 0;

    public void addNewInput(AEImage newImage) {
        super.addNewInput(newImage, System.nanoTime());
    }

    @Override public Event[] getEvents() {
        Event[] raw_events = super.getEvents(); //also updates lastEventTimestamp, by calling analyseBrightnessHistory
        Event[] events = new Event[raw_events.length+1];

        System.arraycopy(raw_events, 0, events, 0, raw_events.length);

        events[events.length-1] = new Event(Math.min(REAL_TIME_MAX_GAP, (System.nanoTime() - lastEventTimestamp) / 1e9), false);
        return events;
    }

    @Override protected long analyseBrightnessHistory() {
        long lastUpOrDown = super.analyseBrightnessHistory();
        if(lastUpOrDown!=-1)
            lastEventTimestamp=lastUpOrDown;
        return lastUpOrDown;
    }
}
