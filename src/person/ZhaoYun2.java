package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class ZhaoYun2 extends BaseEnemy {

	private Random random=new Random();
	private int speed=3;
	
	public ZhaoYun2(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[24];
		this.stand_rightImgs=new Image[24];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[8];
		this.walk_rightImgs=new Image[8];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[12];
		this.down_rightImgs=new Image[12];
		
		this.attack_leftImgs=new Image[20];
		this.attack_rightImgs=new Image[20];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[17];
		this.attack1_rightImgs=new Image[17];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[42];
		this.attack2_rightImgs=new Image[42];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun2/攻击2_右 ("+(i+1)+").png");
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
		super.setName("赵  云");
	} 
	private void attackMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhaoYun2/白虹贯日.mp3").play();
 			}
 		}.start();
 		
 	}
	
	
	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhaoYun2/来将通名.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhaoYun2/笑.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void stand(){
		if(super.isRight())
			super.stand(x-98, y-80, 120, 40, x-50, y-20);
		else
			super.stand(x-98, y-80, 120, 40, x-50, y-20);
	}
	
	private void attack(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackMusic();
			isMusic=true;
		}
		if(stateflag)
			super.attack(x-70, y-192, 0,0,x-50, y-20, 120, 40, x+40, y-20, new int[]{9,10,11,12,13,14,15,16,17},8);
		else
			super.attack(x-200, y-192, 0,0, x-50, y-20, 120, 40, x-150, y-20, new int[]{9,10,11,12,13,14,15,16,17},8);
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			isMusic=false;
			if(stateflag)
				x+=70;
			else x-=70;
		}
	}

	private void attack1(){
		if(!super.isAttack1())stateflag=super.isRight();
		if(!isMusic) {
			attack1Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack1(x-120, y-200, 0, 0,x-60, y-25, 120, 50, x+100, y-25, new int[]{11,12,13},5);
		else
			super.attack1(x-270, y-200, 0, 0, x-60, y-25, 120, 50, x-220, y-25, new int[]{11,12,13},5);
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			super.setState(0);
			super.setAttack1(false);
			if(super.isIshit())super.getZl().markadd();
			super.setIshit(false);	
			isMusic=false;
			if(stateflag)
				x+=120;
			else x-=120;
		}
	}
	
	private void attack2(){
		if(!super.isAttack2())stateflag=super.isRight();
		if(!isMusic) {
			attack2Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack2(x-200, y-180, 0, 0,x-60, y-25, 340, 50, x-170, y-25, new int[]{10,12,15,17,19,21,22,23,25,27,29,31,33,35,37,39},20);
		else
			super.attack2(x-200, y-180, 0, 0, x-60, y-25, 340, 50, x-170, y-25, new int[]{10,12,15,17,19,21,22,23,25,27,29,31,33,35,37,39},20);
		if(super.getImgIndex()==attack2_leftImgs.length-1){
			super.setState(0);
			super.setAttack2(false);
			isMusic=false;
		}
	}
	
	private void hit(){
		if(!super.isIshit()) {
     		HP-=super.getZl().getAttackHP();
			if(HP<=0)super.setIslive(false);
			super.setIshit(true);
			super.getZl().addkillNum(10);
		}
		super.setState(3);
	}
	

	
	private void walk(){
		if(super.isRight())
			super.walk(x-75, y-80, 120, 40, x-50, y-20);
		else
			super.walk(x-110, y-80, 120, 40, x-50, y-20);
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
		}
		
	}
}
