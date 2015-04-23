package com.the_jester41.GameFramework.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.the_jester41.GameFramework.framework.Audio;
import com.the_jester41.GameFramework.framework.Music;
import com.the_jester41.GameFramework.framework.Sound;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity)
	{
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	@Override
	public Music newMusic(String fileName) {
		try {
			AssetFileDescriptor assetDescriptor=assets.openFd(fileName);
			return new AndroidMusic(assetDescriptor);
		} catch(IOException e) {
			throw new RuntimeException("Unable to load music '"+ fileName + "'");
		}
	}

	@Override
	public Sound newSound(String fileName) {
		 try
		 {
			 AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			 int soundId = soundPool.load(assetDescriptor, 0);
			 return new AndroidSound(soundPool, soundId);
	     } catch(IOException e) {
			throw new RuntimeException("Unable to load sound '"+ fileName + "'");
		 }
	}
}
