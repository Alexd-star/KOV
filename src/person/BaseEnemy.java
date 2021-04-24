package person;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;


public class BaseEnemy extends BasePerson implements Runnable{

	protected Image[] hit_leftImgs;
	protected Image[] hit_rightImgs;
	
	
	private GameFrame ui;
	private ZhangLiao zl;
	private int state=0; 
	protected boolean left=false;
	protected boolean right=false;
	protected boolean up=false;
	protected boolean down=false;
	private boolean ishit=false;
	private Random random=new Random();
	protected int hitIndex=0;
	protected boolean stateflag=false;
    private String name;
	
	
    
	public void setName(String name) {
		this.name = name;
	}

	public boolean isIshit() {
		return ishit;
	}

	public void setIshit(boolean ishit) {
		this.ishit = ishit;
	}

	public GameFrame getUi() {
		return ui;
	}

	public ZhangLiao getZl() {
		return zl;
	}

	public int getState() {
		return state;
	}
	
	public void setState(int s) {
		state=s;
	}
    
	
	public void move(boolean isright){
		if(isright)x+=zl.getSpeed();
		else x-=zl.getSpeed();
	}
	
	public BaseEnemy(GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(HP);
        super.setBoss(boss);
		this.ui=ui;
		this.zl=zl;
		new Thread(this).start();
		state=4;
	}
	
	
	
	public void setImgs(Image[][]imgs){
		super.setImgs(new Image[][]{imgs[0],imgs[1],imgs[2],imgs[3],imgs[4],imgs[5],imgs[6],imgs[7],imgs[8],imgs[9],imgs[10],imgs[11]});
		hit_leftImgs=imgs[12];
		hit_rightImgs=imgs[13];
	}
	
	public void hit(int imgx,int imgy){
			super.imgx=imgx;
			super.imgy=imgy;
			super.width=0;
			super.height=0;
			super.atWidth=0;
			super.atHeight=0;
			super.setAttackFlag(false);
			super.setAttack(false);
			super.setAttack1(false);
			super.setAttack2(false);
		if(!ishit) {
			if(super.isRight())super.setStateImgs(hit_rightImgs);
			else super.setStateImgs(hit_leftImgs);
			HP-=zl.getAttackHP();
			zl.addkillNum(10);
			ishit=true;
		}
	}
	
	
	
	
	private int HPflag=HP;
	private double uiHP=HPflag;
	public void drawHP(Graphics g){
		
		if(uiHP>HP)uiHP--;
		g.setColor(Color.GREEN);
		if(super.getBoss()==1) {
			int index1=HPflag/700;
			for(int i=1;i<=index1;i++) {
				g.drawRect(100,400+i*13, 700, 8);
				if(i==index1) {
					g.drawRect(100,400+(i+1)*13, HPflag-700*index1, 8);
				}
			}
			int index2;
			if(HP>700) {
				index2=HP/700;
				for(int i=1;i<=index2;i++) {
					g.fillRect(100,400+i*13, 700, 8);
					if(i==index2) {
						g.fillRect(100,400+(i+1)*13, (int)uiHP-700*index2, 8);
					}
				}
			}else g.fillRect(100,413, (int)uiHP, 8);
			g.setColor(Color.WHITE);
			g.setFont(new Font("黑体",20,20));
			g.drawString(name, 30, 430);
		}else if(super.getBoss()==2) {
			int index1=HPflag/700;
			for(int i=1;i<=index1;i++){
				g.drawRect(100,426+i*13, 700, 8);
				if(i==index1) {
					g.drawRect(100,426+(i+1)*13, HPflag-700*index1, 8);
				}
			}
			int index2;
			if(HP>700) {
				index2=HP/700;
				for(int i=1;i<=index2;i++) {
					g.fillRect(100,426+i*13, 700, 8);
					if(i==index2) {
						g.fillRect(100,426+(i+1)*13,(int) uiHP-700*index2, 8);
					}
				}
			}else g.fillRect(100,439, (int)uiHP, 8);
			g.setColor(Color.WHITE);
			g.setFont(new Font("黑体",20,20));
			g.drawString(name, 30, 458);
		}else {
			g.setColor(Color.GREEN);
			g.drawRect(x-35,y-120, 80, 5);
			g.fillRect(x-35, y-120, (int)( 80*uiHP/(HPflag)), 5);
		}
	}
	
	public void draw(Graphics g){
		super.drawself(g);
		if(x>zl.getX())super.setRight(false);
		else super.setRight(true);
	}

	
	
	@Override
	public void run() {
		while(true){
			if(state==0||state==1){
				int flag=0;  //敌方攻击欲望
				switch(ui.getChooseResult()) {
				case 1:
					flag=1;
					break;
				case 2:
					flag=5;
					break;
				case 3:
					flag=3;
					break;
				case 4:
					flag=8;
					break;
				default:
				    flag=10;
				    break;
				}
				
				if(random.nextInt(20)<flag) {
					state=random.nextInt(3)+2;
					super.setImgIndex(0);
					
				}
				else {
					state=random.nextInt(2);
				}
				if(x>zl.getX()+400){
					left=true;
					state=1;
				}
				if(x<zl.getX()-400){
					right=true;
					state=1;
				}
				if(state==1){
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
					if(x>zl.getX()+400)left=true;
					if(x<zl.getX()-400)right=true;
                    if(y<=320)down=true;
                    if(y>=ui.getHeight()-10)up=true;
				}else{
					left=false;
					right=false;
					up=false;
					down=false;
				}
				
			}
				try {
						Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}	
}
