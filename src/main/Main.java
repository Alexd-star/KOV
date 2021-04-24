package main;

import javax.swing.JFrame;

import ui.GameFrame;

public class Main {

	public static void main(String[] args) {
		JFrame jf=new JFrame("KOV                                    By 罗成");
		jf.setResizable(false);
		jf.setSize(854, 466);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame g=new GameFrame();
		jf.add(g);
		jf.pack();
		jf.setVisible(true);
		jf.addKeyListener(g.getZl());
		jf.addMouseListener(g);
	}  

}
