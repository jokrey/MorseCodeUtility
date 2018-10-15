package jokrey.utilities.morse.input;

import jokrey.utilities.animation.util.AEImage;

/**
 * Fixed interval implementation of the ImageBrightnessBasedInputReceiver.
 * Mostly supposed to be used for testing.
 * TODO. Write test.
 */
public class ImageBrightnessFixedIntervalInputReceiver extends ImageBrightnessBasedInputReceiver {
    private long current_tick = 0;

    public void addNewInput(AEImage newImage) {
        current_tick++;
        super.addNewInput(newImage, current_tick);
    }

    @Override public Event[] getEvents() {
        return super.getEvents();
    }
}
