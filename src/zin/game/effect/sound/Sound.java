package zin.game.effect.sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/*
 * Sound Ŭ����
 * 
 * �Ҹ��� ����� �� �ִ� Ŭ����
 * 
 */

public class Sound {
	private static final float MAX_VALUE = 0f;	// ���� �ִ�ġ
	private static final float RANGE = 80f;	// ��������
	
	public static void Play(SoundType type, float value, boolean Loop) {
		Clip clip;
		String src = type.getSRC();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(src)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float gain = (RANGE * value) - RANGE + MAX_VALUE;
			volume.setValue(gain);
			clip.start();
			if (Loop) {
				clip.loop(-1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
