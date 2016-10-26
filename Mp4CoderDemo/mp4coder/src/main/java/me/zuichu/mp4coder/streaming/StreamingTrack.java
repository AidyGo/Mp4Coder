package me.zuichu.mp4coder.streaming;


import java.io.Closeable;

import me.zuichu.mp4coder.boxes.iso14496.part12.SampleDescriptionBox;
import me.zuichu.mp4coder.streaming.output.SampleSink;

public interface StreamingTrack extends Closeable {
    /**
     * Gets the time scale of the track. Typically called by the SampleSink.
     * Might throw IllegalStateException if called before the first sample has been pushed into the SampleSink.
     *
     * @return the track's time scale
     */
    long getTimescale();

    String getHandler();

    String getLanguage();

    /**
     * All implementing classes must make sure the all generated samples are pushed to the sampleSink.
     * When a sample is pushed all methods must yield valid results.
     *
     * @param sampleSink the sink for all generated samples.
     */
    void setSampleSink(SampleSink sampleSink);

    SampleDescriptionBox getSampleDescriptionBox();

    <T extends TrackExtension> T getTrackExtension(Class<T> clazz);

    void addTrackExtension(TrackExtension trackExtension);

    void removeTrackExtension(Class<? extends TrackExtension> clazz);

}
