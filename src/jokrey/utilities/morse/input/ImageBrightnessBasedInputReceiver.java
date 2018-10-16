package jokrey.utilities.morse.input;

import jokrey.utilities.animation.util.AEColor;
import jokrey.utilities.animation.util.AEImage;

import java.util.ArrayList;
import java.util.List;

import static jokrey.utilities.morse.BinarySignalUnit.getPercentageRatioBetween;

/**
 * Input Receiver receiving raw rgb images of any size(as an AEImage, to be most platform independent).
 * AEImages added are either assumed to be placed there at a fixed time (pure) or in real time.
 *    For both options there are specific subclasses.
 *
 * TODO: Explanation of how the algorithm is supposed to work.
 */
public abstract class ImageBrightnessBasedInputReceiver extends InputReceiver {
//Theoretically adjustable Parameters - actually should be adjustable through UI
    public double upTick_Threshold = 0.8;
    public double downTick_Threshold = 0.2;
    public int amountOfAnalResultsIgnored_fromStart = 0;
    public int amountOfAnalResultsIgnored_fromEnd = 0;


    private ArrayList<Event> events = new ArrayList<>();
    @Override public Event[] getEvents() {
        if(getBrightnessEventsForCalculation().isEmpty())return new Event[]{};

        analyseBrightnessHistory();

        return events.toArray(new Event[0]);
    }




    private List<InputBrightnessEvent> inputBrightnessEvent = new ArrayList<>();
    private List<InputBrightnessEvent> getBrightnessEventsForCalculation() {
        List<InputBrightnessEvent> inputBrightnessEvent_filtered = new ArrayList<>();
        for(int i = amountOfAnalResultsIgnored_fromStart; i< inputBrightnessEvent.size() - amountOfAnalResultsIgnored_fromEnd; i++) {
            inputBrightnessEvent_filtered.add(inputBrightnessEvent.get(i));
        }
        return inputBrightnessEvent_filtered;
    }

    public abstract void addNewInput(AEImage newImage);

    protected void addNewInput(AEImage newImage, long time_marker) {
        long overallBrightness = 0;

//        long analysisStart_timestamp = System.nanoTime();

        for(int x = 0;x<newImage.getWidth();x+=4) {
            for(int y = 0;y<newImage.getHeight();y+=4) {
                AEColor pixel_clr = newImage.getColorAt(x,y);
                int red = pixel_clr.getRed();
                int green = pixel_clr.getGreen();
                int blue = pixel_clr.getBlue();

                int pixelBrightness = red + green + blue; //this is at least a weird way to do it.......
                overallBrightness+=pixelBrightness;
            }
        }

        long analysisEnd_timestamp = System.nanoTime();
//        double analysisTook = (analysisEnd_timestamp-analysisStart_timestamp) / 1e9;

        inputBrightnessEvent.add(new InputBrightnessEvent(analysisEnd_timestamp, -1, overallBrightness/*, brightestSpot_x, brightestSpot_y, highestBrightness*/));
    }


    protected long analyseBrightnessHistory() {
        List<InputBrightnessEvent> filteredResults = getBrightnessEventsForCalculation();

        if(filteredResults.size()<2)return -1;

        //RUNTIME VARIABLES - used for normalization
        double lowestOverallBrightnessEver = Double.MAX_VALUE;
        double highestOverallBrightnessEver = Double.MIN_VALUE;
        double highestChange = Double.MIN_VALUE;
        for(int i=0;i<filteredResults.size()-1;i++) {
            InputBrightnessEvent result_i = filteredResults.get(i);
            InputBrightnessEvent result_ip1 = filteredResults.get(i + 1);

            double changeToNext = result_i.overallBrightness - result_ip1.overallBrightness;
            if(changeToNext>highestChange)
                highestChange=changeToNext;
            if(result_i.overallBrightness> highestOverallBrightnessEver)
                highestOverallBrightnessEver =result_i.overallBrightness;
            if(result_i.overallBrightness< lowestOverallBrightnessEver)
                lowestOverallBrightnessEver =result_i.overallBrightness;
        }

        events.clear();

        if (filteredResults.size() >= 2) {
            InputBrightnessEvent firstResult = filteredResults.get(0);

            long lastUpOrDown = firstResult.timestamp;
            int lastWas = 0;//-1 for signal, 1 for pause
            for(int i=1;i<filteredResults.size();i++) {
                double height_ratio = 1 - getPercentageRatioBetween(filteredResults.get(i).overallBrightness, lowestOverallBrightnessEver, highestOverallBrightnessEver);

                long current = filteredResults.get(i).timestamp;
                if(height_ratio < downTick_Threshold  && (lastWas == 0 || lastWas == 1)) {
                    events.add(new Event((current - lastUpOrDown) / 1e9, false));
                    lastWas = -1;
                    lastUpOrDown = current;
                } else if(height_ratio > upTick_Threshold  && (lastWas==-1)) {
                    events.add(new Event((current - lastUpOrDown) / 1e9, true));
                    lastWas = 1;
                    lastUpOrDown = current;
                }
            }
            return lastUpOrDown;
        }
        return -1;
    }


    private class InputBrightnessEvent {
        public final long timestamp;
        public final double analysisTook;
        public final double overallBrightness;

        InputBrightnessEvent(long timestamp, double analysisTook, double overallBrightness) {
            this.timestamp=timestamp;
            this.analysisTook=analysisTook;
            this.overallBrightness=overallBrightness;
        }

        public String toString() {
            return "at "+(timestamp/1e9)+" - analysisTook "+analysisTook+" - ovBrightness "+overallBrightness+")";
        }
    }
}