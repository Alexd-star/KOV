package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class BaShen extends BaseEnemy {

	private Random random=new Random();
	private int speed=3;
	
	public BaShen(int x,GameFrame ui,ZhangLiao zl,int HP ,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[8];
		this.stand_rightImgs=new Image[8];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[10];
		this.walk_rightImgs=new Image[10];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[22];
		this.down_rightImgs=new Image[22];
		for(int i=0;i<down_leftImgs.length;i++){
			this.down_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/击倒_左 ("+(i+1)+").png");
			this.down_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/击倒_右 ("+(i+1)+").png");
		}
		
		this.attack_leftImgs=new Image[29];
		this.attack_rightImgs=new Image[29];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[38];
		this.attack1_rightImgs=new Image[38];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[58];
		this.attack2_rightImgs=new Image[58];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/攻击2_右 ("+(i+1)+").png");
		}
		
		this.hit_leftImgs=new Image[6];
		this.hit_rightImgs=new Image[6];
		for(int i=0;i<hit_leftImgs.length;i++){
			this.hit_leftImgs[i]=ImageUtil.getImage("/imgs/BaShen/挨打_左 ("+(i+1)+").png");
			this.hit_rightImgs[i]=ImageUtil.getImage("/imgs/BaShen/挨打_右 ("+(i+1)+").png");
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
		super.setName("章 鹏");
	} 
	
	private void attackMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/BaShen/升龙.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void hitMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/BaShen/挨打.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/BaShen/八酒杯.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/BaShen/八稚女.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void stand(){
		if(super.isRight())
			super.stand(x-20, y-80, 60, 40, x-30, y-20);
		else
			super.stand(x-40, y-80, 60, 40, x-30, y-20);
	}
	
	private void attack(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackMusic();
			isMusic=true;
		}
		if(stateflag)
			super.attack(x-50, y-200, 0, 0,x-50, y-20, 100, 40, x, y-20, new int[]{10,11,12,13,14,15,16},12);
		else
			super.attack(x-110, y-200, 0, 0, x-50, y-20, 100, 40, x-100, y-20, new int[]{10,11,12,13,14,15,16},12);
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			if(stateflag)
				x+=45;
			else x-=45;
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
			super.attack1(x-75, y-195, 0, 0, x-50, y-20, 150, 40, x, y-20, new int[]{26,27,28,29},8);
		else
			super.attack1(x-190, y-195, 0, 0, x-50, y-20, 150, 40, x-150, y-20, new int[]{26,27,28,29},8);
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			super.setState(0);
			super.setAttack1(false);
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
			super.attack2(x-55, y-190, 0, 0,x-60, y-25, 100, 50, x+50, y-25, new int[]{15,16,17,18,19,20,21,29,41},10);
		else
			super.attack2(x-170, y-190, 0, 0, x-60, y-25, 100, 50, x-150, y-25, new int[]{15,16,17,18,19,20,21,29,41},10);
		if(super.getImgIndex()==10){
			if(stateflag)
			   x=super.getZl().getX()-90;
			else x=super.getZl().getX()+90;
			y=super.getZl().getY();
		}
		if(super.getImgIndex()==attack2_leftImgs.length-1){
			super.setState(0);
			super.setAttack2(false);
			isMusic=false;
			if(stateflag)
				x+=50;
			else x-=50;
		}
	}
	
	private void hit(){
		if(!super.isHit())stateflag=super.isRight();
		if(!isMusic) {
			hitMusic();
			isMusic=true;
		}
		if(stateflag)
			super.hit(x-80,y-100);
		else
			super.hit(x-40,y-100);
		if(HP<=0)super.setState(6);
		if(super.getImgIndex()==hit_leftImgs.length-1){
			super.setIshit(false);
			super.setState(0);
			super.setHit(false);
			isMusic=false;
			super.getZl().markadd();
			if(++hitIndex==3){
			super.setState(6);
			hitIndex=0;
			}
		}
		
	}
	private void down(){
		if(!super.isHitDown())stateflag=super.isRight();
		if(stateflag)
			super.down(x-150,y-150);
		else
			super.down(x-30,y-150);
		if(HP<=0&&super.getImgIndex()==7)super.setIslive(false);
		if(super.getImgIndex()==down_leftImgs.length-1){
			if(random.nextBoolean())super.setState(0);
			else super.setState(2);
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
			super.walk(x-30, y-100, 60, 40, x-30, y-20);
		else
			super.walk(x-30, y-100, 60, 40, x-30, y-20);
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
