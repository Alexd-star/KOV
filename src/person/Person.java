package person;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Person{

	void draw(Graphics g);
	void move(boolean isright);
	Rectangle getRect();
	Rectangle attackRect();
	boolean isZombie();
}
