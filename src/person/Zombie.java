package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;

public class Zombie extends BaseEnemy{

	private Random random=new Random();
	private int speed=1;
	
	public Zombie(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		super.setZombie(true);
		this.walk_leftImgs=new Image[18];
		this.walk_rightImgs=new Image[18];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/Zombie/站立_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/Zombie/行走_右 ("+(i+1)+").png");
		}
	} 

	
	private void hit(){
		super.HP-=super.getZl().attackHP;
		if(super.isRight())
			x-=30;
		else
			x+=30;
		if(super.HP<=0)super.setIslive(false);
	}
	
	private void walk(){	
		if(super.isRight())
			super.walk(x-55, y-130, 120, 40, x-50, y-20);
		else
			super.walk(x-100, y-130, 120, 40, x-50, y-20);
		if(x<super.getUi().getBgx()+180)left=false;
		if(x>super.getUi().getBgx()+1907-200)right=false;
		if(y<=320)up=false;
		if(y>=super.getUi().getHeight()-35)down=false;
		if(left)x-=speed;
		if(right)x+=speed;
		if(up)y-=speed/2;
		if(down)y+=speed/2;
	}
	
	public void draw(Graphics g){
		//g.fillRect(x, y, 10, 10);
		walk();
		super.draw(g);
		if(super.getZl().attackRect().intersects(getRect()))
			hit();
	}
	
	public void run(){
		while(true){
			if(random.nextBoolean()){
				left=true;
				right=false;
			}		
			else{
				right=true;
				left=false;
			}
			
			if(random.nextBoolean()){
				up=true;
				down=false;
			}		
			else{
				down=true;
				up=false;
			}
			if(x>super.getZl().getX()+400)left=true;
			if(x<super.getZl().getX()-400)right=true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
