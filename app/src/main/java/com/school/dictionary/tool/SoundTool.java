package com.school.dictionary.tool;

import android.media.MediaPlayer;

import com.school.dictionary.DictionaryApplication;

public class SoundTool {

    private static SoundTool soundTool;

    private SoundTool() {

    }

    public static SoundTool getInstance() {
        if (soundTool == null) {
            synchronized (SoundTool.class) {
                if (soundTool == null) {
                    soundTool = new SoundTool();
                }
            }
        }
        return soundTool;
    }

    public static void destroy() {
        soundTool = null;
    }

    /**
     * 播放语音
     */
    public void playSound(String soundID) {
        MediaPlayer mMediaPlayer = MediaPlayer.create(DictionaryApplication.getContext(),
                ResourceUtil.getRaw(DictionaryApplication.getContext(), soundID));
        mMediaPlayer.start();
    }

}
