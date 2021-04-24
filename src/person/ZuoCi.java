package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class ZuoCi extends BaseEnemy {

	private Random random=new Random();
	private int speed=2;
	
	public ZuoCi(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[8];
		this.stand_rightImgs=new Image[8];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[8];
		this.walk_rightImgs=new Image[8];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/站立_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/站立_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[10];
		this.down_rightImgs=new Image[10];
		
		this.attack_leftImgs=new Image[10];
		this.attack_rightImgs=new Image[10];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[46];
		this.attack1_rightImgs=new Image[46];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[60];
		this.attack2_rightImgs=new Image[60];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/攻击2_右 ("+(i+1)+").png");
		}
		
		this.hit_leftImgs=new Image[6];
		this.hit_rightImgs=new Image[6];
		for(int i=0;i<hit_leftImgs.length;i++){
			this.hit_leftImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/挨打_左 ("+(i+1)+").png");
			this.hit_rightImgs[i]=ImageUtil.getImage("/imgs/ZuoCi/挨打_右 ("+(i+1)+").png");
		}
		
		Image[][]imgs={stand_leftImgs,stand_rightImgs,
					   walk_leftImgs,walk_rightImgs,
					   down_leftImgs,down_rightImgs,
					   attack_leftImgs,attack_rightImgs,
					   attack1_leftImgs,attack1_rightImgs,
					   attack2_leftImgs,attack2_rightImgs,
					   hit_leftImgs,hit_rightImgs};
		stand();
		super.setImgs(imgs);
		super.setName("左  慈");
	} 
	
	private void attackMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZuoCi/冲.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void hitMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZuoCi/挨打.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZuoCi/看招.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZuoCi/笑.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void stand(){
		if(super.isRight())
			
			super.stand(x-115, y-195, 140, 50, x-60, y-25);
		else
			super.stand(x-120, y-195, 140, 50, x-60, y-25);
	}
	
	private void attack2(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackMusic();
			isMusic=true;
		}
		if(stateflag)
			super.attack(x-140, y-190, 0, 0,x-60, y-25, 180, 50, x-10, y-25, new int[]{5,6,7},10);
		else
			super.attack(x-230, y-190, 0, 0, x-60, y-25, 180, 50, x-150, y-25, new int[]{5,6,7},10);
		if(super.getImgIndex()>=5&&super.getImgIndex()<=8)
			if(stateflag)
				x+=20;
			else x-=20;
		if(x<super.getUi().getBgx()+180)x=super.getUi().getBgx()+180;
		if(x>super.getUi().getBgx()+1907-200)x=super.getUi().getBgx()+1907-200;
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			isMusic=false;
			if(stateflag)
				x+=50;
			else x-=50;
		}
	}

	private void attack1(){
		if(!super.isAttack1())stateflag=super.isRight();
		if(!isMusic) {
			attack1Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack1(x-120, y-195, 0, 0,x-60, y-25, 140, 50, x+110, y-25, new int[]{27},15);
		else
			super.attack1(x-280, y-195, 0, 0, x-60, y-25, 140, 50, x-250, y-25, new int[]{27},15);
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			super.setState(0);
			super.setAttack1(false);
			isMusic=false;
		}
	}
	
	private void attack(){
		if(!super.isAttack2())stateflag=super.isRight();
		if(!isMusic) {
			attack2Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack2(x-120, y-208, 0, 0,x-60, y-25, 200, 100, x, y-50, new int[]{40},20);
		else
			super.attack2(x-210, y-208, 0, 0, x-60, y-25, 200, 100, x-200, y-50, new int[]{40},20);
		if(super.getImgIndex()==attack2_leftImgs.length-1){
			super.setState(0);
			super.setAttack2(false);
			isMusic=false;
		}
	}
	
	private void hit(){
		if(!super.isHit())stateflag=super.isRight();
		if(!isMusic) {
			hitMusic();
			isMusic=true;
		}
		if(stateflag)
			super.hit(x-150,y-143);
		else
			super.hit(x-75,y-143);
		if(HP<=0)super.setIslive(false);
		if(super.getImgIndex()==hit_leftImgs.length-1){
			super.setState(0);
			super.setIshit(false);
			super.getZl().markadd();
			isMusic=false;
			if(++hitIndex==3){
				super.setState(4);
				hitIndex=0;
			}
		}
	}
	private void down(){
		if(!super.isHitDown())stateflag=super.isRight();
		if(stateflag)
			super.down(x-200,y-175);
		else
			super.down(x-100,y-175);
		if(HP<=0&&super.getImgIndex()==7)super.setIslive(false);
		if(super.getImgIndex()==down_leftImgs.length-1){
			super.setState(0);
			super.setIshit(false);
			super.setHitDown(false);
			if(stateflag){
				x-=78;
				if(x<super.getUi().getBgx()+180)x=180;
			}
			else{
				x+=78;
				if(x>super.getUi().getBgx()+1907-200)x=super.getUi().getBgx()+1907-200;
			}
		}
	}
	
	private void walk(){
		if(super.isRight())
			super.walk(x-115, y-195, 140, 50, x-60, y-25);
		else
			super.walk(x-120, y-195, 140, 50, x-60, y-25);
		if(x<super.getUi().getBgx()+180)left=false;
		if(x>super.getUi().getBgx()+1907-200)right=false;
		if(y<=320)up=false;
		if(y>=super.getUi().getHeight()-10)down=false;
		if(left)x-=speed;
		if(right)x+=speed;
		if(up)y-=speed/2;
		if(down)y+=speed/2;
	}
	
	public void draw(Graphics g){
		//g.fillRect(x, y, 10, 10);
		super.draw(g);
		if(super.getZl().attackRect().intersects(getRect())){
			super.setState(5);
		}
		switch(super.getState()){
		case 0:
			stand();
			break;
		case 1:
			walk();
			break;
		case 2:
			attack();
			break;
		case 3:
			attack1();
			break;
		case 4:
			attack2();
			break;
		case 5:
			hit();
			break;
		case 6:
			down();
			break;
			
		}
		
	}
}
