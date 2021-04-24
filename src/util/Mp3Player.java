package util;

import java.io.BufferedInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3Player {

	Player player; //音乐播放器
	
	
	public Mp3Player(String path) {
		BufferedInputStream ins=
				new BufferedInputStream(Mp3Player.class.getResourceAsStream(path));
		try {
			player=new Player(ins);
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			player.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
