package person;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class ZhangLiao extends BasePerson implements KeyListener{

   private boolean right=false;
   private boolean left=false;
   private boolean up=false;
   private boolean down=false;
   private boolean iswalk=false;
   private boolean isattack=false;
   private boolean isattack1=false;
   private boolean isattack2=false;
   private boolean isdown=false;
   private GameFrame ui;
   private int speed=5;
   private int killNum=5;
   private int killNumSch=100;
   private int mark=0;
   private int liveNum=10;
   private List<BasePerson>enemys;
   
 	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhangLiao/翻雷滚天.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/ZhangLiao/风卷残云.mp3").play();
 			}
 		}.start();
 		
 	}
   
   public void restart() {
	   x=200; 
	   y=400;
	   super.setRight(true);
	   killNum=5;
	   killNumSch=100;
	   mark=0;
	   liveNum=1;
	   HP=100;
	   uiHP=100;
	   uiKill=100;
	   super.setIslive(true);
	   isdown=false;
   }
   
   public void markadd() {
	   mark+=100;
	   if(mark==5000||mark==8000||mark==15000||mark==20000||mark==30000||mark==40000)liveNum++;
   }
   
	public void setEnemys(List<BasePerson>enemys) {
		this.enemys = enemys;
	}

	public int getSpeed() {
		return speed;
	}

	public ZhangLiao(int x,int y,GameFrame m,int HP){
		super(HP);
		this.x=x;
		this.y=y;
		ui=m;
		super.setZhang(true);
		super.setHitDown(false);
		this.stand_leftImgs=new Image[11];
		this.stand_rightImgs=new Image[11];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[7];
		this.walk_rightImgs=new Image[7];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[9];
		this.down_rightImgs=new Image[9];
		for(int i=0;i<down_leftImgs.length;i++){
			this.down_leftImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/倒地_左 ("+(i+1)+").png");
			this.down_rightImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/倒地_右 ("+(i+1)+").png");
		}
		
		this.attack_leftImgs=new Image[9];
		this.attack_rightImgs=new Image[9];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[40];
		this.attack1_rightImgs=new Image[40];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[26];
		this.attack2_rightImgs=new Image[26];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/ZhangLiao/攻击2_右 ("+(i+1)+").png");
		}
		
		Image[][]imgs={stand_leftImgs,stand_rightImgs,
					   walk_leftImgs,walk_rightImgs,
					   down_leftImgs,down_rightImgs,
					   attack_leftImgs,attack_rightImgs,
					   attack1_leftImgs,attack1_rightImgs,
					   attack2_leftImgs,attack2_rightImgs};
		super.setImgs(imgs);
		stand();
	}
	
	
	private int uiKill=killNumSch;
	public void addkillNum(int count) {
		
		killNumSch+=count;
		if(killNumSch>100) {
			if(killNum<5)killNumSch=0;
			else killNumSch=100;
		}
		
		if(uiKill<killNumSch)uiKill++;
		if(uiKill>killNumSch) {
			uiKill++;
			if(uiKill>100) {
				uiKill=0;
				killNum++;
			}
		}
		
		
		
		
	}
	
	private void isdown(){
		for(BasePerson p:enemys)
			if(p.attackRect().intersects(getRect())&&!p.isZhang()){
				isdown=true;
				HP-=p.getAttackHP();
				this.addkillNum(p.getAttackHP());
			}
	}
	
	private void down(){
		left=false;
		right=false;
		up=false;
		down=false;
		isattack=false;
		if(super.isRight())
			super.down(x-135, y-127);
		else super.down(x-110, y-127);
		if(HP<=0&&super.getImgIndex()==6&&liveNum==0)super.setIslive(false);
		if(super.getImgIndex()==down_leftImgs.length-1){
			isdown=false;
			stand();
			if(HP<=0&&liveNum>0) {
				liveNum--;
				isattack2=true;
				HP=100;
				killNum=5;
				killNumSch=100;
			}
			if(super.isRight()){
				x-=70;
			    if(x<130)x=130;
			}
			else {
				x+=70;
				 if(x>730)x=730;
			}
		}
	}
	private void attack1(){
		left=false;
		right=false;
		up=false;
		down=false;
		isattack=false;
		if(!isMusic) {
			attack1Music();
			isMusic=true;
		}
		if(super.isRight())
			super.attack1(x-113, y-136, 0, 0,x-50,y-20,320,40,x-20,y-20, new int[]{16,17,18,19,20,21,22,23,24,25,26,27,28},80);
		else super.attack1(x-322, y-136, 0, 0,x-50,y-20,320,40,x-300,y-20, new int[]{16,17,18,19,20,21,22,23,24,25,26,27,28},80);
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			isattack1=false;
			stand();
			isMusic=false;
		}
	}
	
	private void attack2(){
		left=false;
		right=false;
		up=false;
		down=false;
		isattack=false;
		if(!isMusic) {
			attack2Music();
			isMusic=true;
		}
		if(super.isRight())
			super.attack2(x-76, y-170, 0, 0,x-50,y-20,320,40,x-20,y-20, new int[]{16,17,18,19,20},200);
		else super.attack2(x-370, y-170, 0, 0,x-50,y-20,320,40,x-300,y-20, new int[]{16,17,18,19,20},200);
		if(super.getImgIndex()==attack2_leftImgs.length-1){
			isattack2=false;
			stand();
			isMusic=false;
		}
	}
	
	private void stand(){
		super.stand(x-60, y-80, 110, 40,x-50,y-20);
	}
	
	private void walk(){
		super.walk(x-60, y-86, 110, 40,x-50,y-20);
		move();
	}
	
	private void attack(){
		if(super.isRight())
			super.attack(x-95, y-112, 110, 40,x-50,y-20,130,40,x-20,y-20, new int[]{3,4,7,8},20);
		else
			super.attack(x-140, y-112, 110, 40,x-50,y-20,130,40,x-100,y-20, new int[]{3,4,7,8},20);
	}
	
	private void move() {	
		if(up&&y>315)y-=speed/2;
		if(down&&y<ui.getHeight()-10)y+=speed/2;
		if(x<=400||x>=500){
			if(left&&x>130)x-=speed;
			if(right&&x<730)x+=speed;
		}
		if(x>400&&x<500){
			ui.moveBg(left, right);
		    if(ui.getBgx()>0||ui.getBgx()<-1035){
		    	if(left)x-=speed;
				if(right)x+=speed;
		    }
		}
		if(!left&&!right&&!up&&!down)stand();
	}

	private int uiHP=100;
	public void drawHP(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("宋体",20,20));
		g.drawString("罗冲 X "+liveNum, 40, 75);
		g.setFont(new Font("宋体",20,17));
		g.drawString("分数： "+mark, 80, 30);
		g.setFont(new Font("宋体",20,15));
		g.drawString("怒气值",145, 55);
		g.setColor(Color.GREEN);
		if(uiHP>HP)uiHP--;
		if(uiHP<HP)uiHP++;
		g.drawRect(50, 85, 200, 15);
		g.fillRect(50, 85,uiHP*2, 15);
		g.setColor(Color.GRAY);
		g.fillRect(145, 60, 185, 20);
		g.setColor(Color.YELLOW);
		g.drawRect(150, 65, 100, 10);
		this.addkillNum(0);
		g.fillRect(150, 65, uiKill, 10);
		for(int i=0;i<5;i++)g.drawOval(255+i*15,65, 10, 10);
		for(int i=0;i<killNum;i++)g.fillOval(255+i*15,65, 10, 10);
		
	}
	
	
	Image head=ImageUtil.getImage("/imgs/ZhangLiao/头像.jpg");
	public void draw(Graphics g){
			//drawHP(g);
			isdown();
			if(iswalk&&!isattack)walk();
			if(isdown)down();
			if(isattack)attack();
			if(isattack1)attack1();
			if(isattack2)attack2();
			super.drawself(g);
			g.drawImage(head, 30,8,50,50,null);
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(!ui.isStart()) {
			ui.setStart(true);
			ui.setIschoose(true);
		}else if((!super.isIslive()||(ui.getEnemyAllCount()==0&&ui.getGameLevel()==3))&&e.getKeyCode()==KeyEvent.VK_ENTER){
			if(ui.getEnemyAllCount()==0&&ui.getGameLevel()==3&&e.getKeyCode()==KeyEvent.VK_ENTER&&ui.getChooseResult()==5) {
				ui.setGameLevel(1);
				ui.addChooseResult(1);
				ui.setEnemyAllCount(4);
				x=200;
				y=400;
				ui.setBgx(0);
			}else {
				ui.restart();
				this.restart();
			}
		}else
		if(!isdown&&!isattack1&&!isattack2)
		switch(e.getKeyCode()){
		case KeyEvent.VK_A:
			right=false;
			super.setRight(false);
			left=true;
			iswalk=true;
			break;
		case KeyEvent.VK_D:
			left=false;
			super.setRight(true);
			right=true;
			iswalk=true;
			break;
		case KeyEvent.VK_W:
			up=true;
			iswalk=true;
			if(ui.isIschoose()) {
				if(ui.getChooseResult()==1) {
					ui.addChooseResult(3);
				}else ui.addChooseResult(-1);
			}
			break;	
		case KeyEvent.VK_S:
			down=true;
			iswalk=true;
			if(ui.isIschoose()) {
				if(ui.getChooseResult()==4) {
					ui.addChooseResult(-3);
				}else ui.addChooseResult(1);
			}
			break;
		case KeyEvent.VK_J:
			if(ui.isIschoose()) {
				ui.setIschoose(false);
				ui.setEnemyAllCount(13);
			}
			else isattack=true;
			break;
		case KeyEvent.VK_K:
			if(ui.isIschoose()&&ui.getChooseResult()==4) {
				ui.setIschoose(false);
				ui.setEnemyAllCount(4);
				ui.addChooseResult(1);
			}
			break;
		case KeyEvent.VK_U:
			if(killNum>=1&&!isdown){
				isattack1=true;
			    killNum--;
			}
			break;	
		case KeyEvent.VK_I:
			if(killNum>=2&&!isdown){
				isattack2=true;
			 	killNum-=2;
			}
			break;
		case KeyEvent.VK_ENTER:
			if(ui.getEnemyAllCount()==0&&ui.getGameLevel()!=3) {
				x=200;
				y=400;
				ui.setBgx(0);
				ui.addEnemycount();
			}
			
			break;
		case KeyEvent.VK_SPACE:
			if(ui.isStart()&&!ui.isIschoose()) {
				ui.pause();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_A:
			left=false;
			break;
		case KeyEvent.VK_D:
			right=false;
			break;
		case KeyEvent.VK_W:
			up=false;
			break;	
		case KeyEvent.VK_S:
			down=false;
			break; 
		case KeyEvent.VK_J: 
			isattack=false;  
			stand(); 
			break; 
		
		} 
	} 


	 
	public void keyTyped(KeyEvent e) {}

	@Override
	public void move(boolean isright){}

} 
 