package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class ZhaoYun1 extends BaseEnemy {

	private Random random=new Random();
	private int speed=3;
	
	public ZhaoYun1(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[12];
		this.stand_rightImgs=new Image[12];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[8];
		this.walk_rightImgs=new Image[8];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[12];
		this.down_rightImgs=new Image[12];
		
		this.attack_leftImgs=new Image[15];
		this.attack_rightImgs=new Image[15];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[21];
		this.attack1_rightImgs=new Image[21];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[39];
		this.attack2_rightImgs=new Image[39];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/ZhaoYun1/攻击2_右 ("+(i+1)+").png");
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
 					new Mp3Player("/music/ZhaoYun1/大鹏展翅.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void hitMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhaoYun1/挨打.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhaoYun1/白鹤亮翅.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhaoYun1/倒转昆仑.mp3").play();
 			}
 		}.start();
 		
 	}
	private void stand(){
		if(super.isRight())
			super.stand(x-67, y-85, 120, 40, x-50, y-20);
		else
			super.stand(x-113, y-85, 120, 40, x-50, y-20);
	}
	
	private void attack(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackMusic();
			isMusic=true;
		}
		if(stateflag)
			super.attack(x-145, y-192, 0, 0,x-50, y-20, 120, 40, x+20, y-20, new int[]{3,8,9},8);
		else
			super.attack(x-155, y-192, 0, 0, x-50, y-20, 120, 40, x-130, y-20, new int[]{3,8,9},8);
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			isMusic=false;
		}
	}

	private void attack1(){
		if(!super.isAttack1())stateflag=super.isRight();
		if(!isMusic) {
			attack1Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack1(x-115, y-125, 0, 0,x-60, y-25, 120, 50, x-10, y-25, new int[]{14,15,16,17},5);
		else
			super.attack1(x-120, y-125, 0, 0, x-60, y-25, 120, 50, x-130, y-25, new int[]{14,15,16,17},5);
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			super.setState(0);
			super.setAttack1(false);
			if(super.isIshit())super.getZl().markadd();
			super.setIshit(false);	
			isMusic=false;
		}
	}
	
	private void attack2(){
		if(!super.isAttack2())stateflag=super.isRight();
		if(!isMusic) {
			attack2Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack2(x-110, y-115, 0, 0,x-60, y-25, 340, 50, x, y-25, new int[]{30,31,32,33,34,35,36,37},20);
		else
			super.attack2(x-340, y-115, 0, 0, x-60, y-25, 340, 50, x-340, y-25, new int[]{30,31,32,33,34,35,36,37},20);
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
			super.walk(x-55, y-85, 120, 40, x-50, y-20);
		else
			super.walk(x-110, y-85, 120, 40, x-50, y-20);
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
