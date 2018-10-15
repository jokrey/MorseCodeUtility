package jokrey.utilities.morse.output.example;

import jokrey.utilities.animation.engine.AnimationEngine;
import jokrey.utilities.animation.pipeline.AnimationDrawer;
import jokrey.utilities.animation.pipeline.AnimationPipeline;
import jokrey.utilities.animation.util.AEColor;
import jokrey.utilities.animation.util.AERect;
import jokrey.utilities.morse.BinarySignalSequenceOutputEngine;

/**
 * Animation Engine Pipeline to display the current step in the BinarySignalSequenceOutputEngine.
 */
public class VisualSignalOutputPipeline extends AnimationPipeline {
    public VisualSignalOutputPipeline(AnimationDrawer drawer) {
        super(drawer);
    }

    //to disable dragging the screen...
    @Override public boolean useUserMidOV() { return false; }

    @Override protected void drawBackground(AERect drawBounds, AnimationEngine engine_uncast) {
        BinarySignalSequenceOutputEngine engine = (BinarySignalSequenceOutputEngine) engine_uncast;
        if(engine.isSignalCurrentlyOn())
            getDrawer().fillRect(AEColor.WHITE, new AERect(0,0,getPixelSize().getWidth(), getPixelSize().getHeight()));
        else
            getDrawer().fillRect(AEColor.BLACK, new AERect(0,0,getPixelSize().getWidth(), getPixelSize().getHeight()));
        if(!engine.isCurrentlyPausing()) {
//            AERect textBounds = new AERect(getPixelSize().getWidth()/2 - getPixelSize().getWidth()/6/2,
//                    getPixelSize().getHeight()/2 - getPixelSize().getHeight()/6/2, getPixelSize().getWidth()/6, getPixelSize().getHeight()/6);
//            getDrawer().drawString(AEColor.RED, String.valueOf(engine.getCurrentlyDisplayedChar()), textBounds);
            getDrawer().drawString(AEColor.RED, 50, String.valueOf(engine.getCurrentlyDisplayedChar()), getPixelSize().getWidth()/2, getPixelSize().getHeight()/2);
        }
    }
}
