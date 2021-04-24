package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class CaoCao extends BaseEnemy {

	private Random random=new Random();
	private int speed=2;
	
	public CaoCao(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[12];
		this.stand_rightImgs=new Image[12];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/CaoCao/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/CaoCao/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[8];
		this.walk_rightImgs=new Image[8];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/CaoCao/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/CaoCao/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[10];
		this.down_rightImgs=new Image[10];
		
		this.attack_leftImgs=new Image[6];
		this.attack_rightImgs=new Image[6];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/CaoCao/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/CaoCao/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[11];
		this.attack1_rightImgs=new Image[11];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/CaoCao/攻击3_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/CaoCao/攻击3_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[42];
		this.attack2_rightImgs=new Image[42];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/CaoCao/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/CaoCao/攻击2_右 ("+(i+1)+").png");
		}
		
		this.hit_leftImgs=new Image[8];
		this.hit_rightImgs=new Image[8];
		for(int i=0;i<hit_leftImgs.length;i++){
			this.hit_leftImgs[i]=ImageUtil.getImage("/imgs/CaoCao/挨打_左 ("+(i+1)+").png");
			this.hit_rightImgs[i]=ImageUtil.getImage("/imgs/CaoCao/挨打_右 ("+(i+1)+").png");
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
		super.setName("曹  操");
	} 
	
	
	private void hitMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/CaoCao/挨打.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attackMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/CaoCao/呀.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/CaoCao/闪.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/CaoCao/六面斩.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attack3Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/CaoCao/哪里走.mp3").play();
 			}
 		}.start();
 		
 	}
	private void stand(){
		if(super.isRight())
			
			super.stand(x-70, y-148, 140, 50, x-60, y-25);
		else
			super.stand(x-140, y-148, 140, 50, x-60, y-25);
	}
	
	private void attack(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackMusic();
			isMusic=true;
		}
		if(stateflag)
			super.attack(x-100, y-165, 140, 50,x-60, y-25, 180, 50, x-10, y-25, new int[]{4,5},10);
		else
			super.attack(x-200, y-165, 140, 50, x-60, y-25, 180, 50, x-150, y-25, new int[]{3,4},10);
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			isMusic=false;
		}
	}

	private Random r=new Random();
	boolean flag=false;
	private void attack1(){
		if(!super.isAttack1()) {
			stateflag=super.isRight();
			flag=true;
		}
		if(!isMusic&&super.getImgIndex()==0) {
			attack1Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack1(x-50, y-100, 0, 0,x-60, y-25, 0, 0, x-10, y-25, new int[]{12},0);
		else
			super.attack1(x-100, y-100, 0, 0, x-60, y-25, 0, 0, x-150, y-25, new int[]{12},0);
		
		if(super.getImgIndex()==7&&flag){
			x=r.nextInt(800);
			y=random.nextInt(143)+315;
			flag=false;
		}
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			if(r.nextInt(8)<7) {
				super.setState(3);
			}else {
				super.setState(r.nextInt(3)+2);
			}
			isMusic=false;
			super.setAttack1(false);
		}
	}
	
	private boolean flag1=false;
	private void attack2(){
		if(!super.isAttack2())stateflag=super.isRight();
		if(!isMusic) {
			attack2Music();
			isMusic=true;
			flag1=false;
		}
		if(stateflag)
			super.attack2(x-202, y-200, 0, 0,x-60, y-25, 320, 100, x-160, y-50, new int[]{31,32,36,37},25);
		else
			super.attack2(x-238, y-200, 0, 0, x-60, y-25, 320, 100, x-160, y-50, new int[]{31,32,36,37},25);
		
		if(super.getImgIndex()==17){
			x=super.getZl().getX();
			y=super.getZl().getY();
		}		
		if(super.getImgIndex()==25){
			if(!flag1) {
				attack3Music();
				flag1=true;
			}
		}
		if(super.getImgIndex()==attack2_leftImgs.length-1){
			super.setState(3);
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
			super.hit(x-100,y-148);
		else
			super.hit(x-100,y-148);
		if(HP<=0)super.setIslive(false);
		if(super.getImgIndex()==hit_leftImgs.length-1){
			super.setState(0);
			super.setIshit(false);
			super.getZl().markadd();
			isMusic=false;
			if(++hitIndex==3){
				super.setState(3);
				super.setImgIndex(0);
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
			super.walk(x-100, y-150, 140, 50, x-60, y-25);
		else
			super.walk(x-140, y-150, 140, 50, x-60, y-25);
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
