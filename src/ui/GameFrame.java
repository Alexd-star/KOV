package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import person.BaShen;
import person.BasePerson;
import person.CaoCao;
import person.HuangZhong;
import person.LvBu;
import person.SiMayi;
import person.XuChu;
import person.ZhangFei;
import person.ZhangLiao;
import person.ZhaoYun1;
import person.ZhaoYun2;
import person.Zombie;
import person.ZuoCi;
import util.ImageUtil;
import util.Mp3Player;



@SuppressWarnings("serial")
public class GameFrame extends JPanel implements Runnable,MouseListener{
   
	private Random random=new Random();	
	private int bgx=0;
	private ZhangLiao zl;
	private int speed;
	private Image bg1=ImageUtil.getImage("/imgs/bg.png");
	private Image bg2=ImageUtil.getImage("/imgs/bg2.png");
	private Image bg3=ImageUtil.getImage("/imgs/bg3.png");
	private Image start=ImageUtil.getImage("/imgs/start.png");
	private Image choose=ImageUtil.getImage("/imgs/choose.png");
	private Image simple=ImageUtil.getImage("/imgs/简单.png");
	private Image common=ImageUtil.getImage("/imgs/普通.png");
	private Image difficult=ImageUtil.getImage("/imgs/困难.png");
	private Image superdifficult=ImageUtil.getImage("/imgs/噩梦.png");
	private Image pause=ImageUtil.getImage("/imgs/pause.png");
	private Image over=ImageUtil.getImage("/imgs/over.png");
	private Image victory=ImageUtil.getImage("/imgs/victory.png");
	private List<BasePerson> pers=new ArrayList<BasePerson>();
	private int enemyAllCount=0;
	private int enemycount=0;   //场上敌人数量
	private int gameLevel=1;
	private boolean boss=false;
    private boolean isStart=false;
    private boolean ischoose=false;
	private int chooseResult=2;  // （剧情模式）1简单 2普通 3困难 4噩梦        （武将争霸） 5一周目 6二周目
	private boolean isPause=false;
	private boolean ispaint=true;
	
	
	
	public void pause() {
		if(isPause) {
			isPause=false;
			ispaint=true;
		}
		else {
			isPause=true;
		}
	}

	
	
	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}



	public void setEnemyAllCount(int enemyAllCount) {
		this.enemyAllCount = enemyAllCount;
	}



	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isIschoose() {
		return ischoose;
	}

	public void setIschoose(boolean ischoose) {
		this.ischoose = ischoose;
	}

	public int getChooseResult() {
		return chooseResult;
	}

	public void addChooseResult(int chooseResult) {
		this.chooseResult+=chooseResult;
	}

	public int getGameLevel() {
		return gameLevel;
	}

	public int getEnemyAllCount() {
		return enemyAllCount;
	}

	public void addEnemycount() {
		zl.setRight(true);
		boss=false;
		BasePerson b=null;
		for(BasePerson bp:pers)
			if(bp.isZhang())b=bp;
		pers.clear();
		pers.add(b);
		switch(++this.gameLevel) {
		case 2:
			this.enemyAllCount=23;
			if(chooseResult==5)this.enemyAllCount=4;
			if(chooseResult==6)this.enemyAllCount=4;
			break;
		case 3:
			this.enemyAllCount=32;
			if(chooseResult==5)this.enemyAllCount=4;
			if(chooseResult==6)this.enemyAllCount=4;
			break;
		}
	}

	public ZhangLiao getZl() {
		return zl;
	}

	public int getSpeed() {
		return speed;
	}

	public int getBgx() {
		return bgx;
	}
	
	

	public void setBgx(int bgx) {
		this.bgx = bgx;
	}

	public void restart() {
		isPause=false;
		ispaint=true;
		isStart=false;
		chooseResult=2;
		addEnemycount();
		gameLevel=1;
		enemyAllCount=13;
		zl.restart();
		bgx=0;
	}
	
	public void moveBg(boolean left,boolean right) {
			if(right&&bgx>=-1035){
				bgx-=zl.getSpeed();
				for(BasePerson p:pers){
					p.move(false);
				}
			}
			if(left&&bgx<=0){
				bgx+=zl.getSpeed();
				for(BasePerson p:pers){
					p.move(true);
				}
			}
	}

	public GameFrame() {	
		zl=new ZhangLiao(200,400,this,100);
		zl.setEnemys(pers);
		speed=zl.getSpeed();
		pers.add(zl);
		startMusic();	
		setPreferredSize(new Dimension(854, bg1.getHeight(null)));
		new Thread(){
			public void run() {
				while(true){
					if(ispaint)
						repaint();					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	    new Thread(this).start();
	}
	
	//启动背景音乐
	private void startMusic() {
		new Thread() {
			public void run() {
				while(true) {
					new Mp3Player("/music/三国杀背景.mp3").play();
				}
			}
		}.start();
		
	}



	private void drawPerson(Graphics g){
		
		Collections.sort(pers);
		for(int i=0;i<pers.size();i++)
			pers.get(i).draw(g);	
		for(int i=0;i<pers.size();i++)
			pers.get(i).drawHP(g);
	}
	public void paint(Graphics g){
		switch(gameLevel) {
		case 1:
			g.drawImage(bg1,bgx,0,null);
			break;
		case 2:
			g.drawImage(bg2,bgx,0,null);
			break;
		case 3:
			g.drawImage(bg3,bgx,0,null);
			break;
		}
		if(!isStart) {
			g.drawImage(start, 0, 0,getWidth(),getHeight(), null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("黑体",20,25));
			g.drawString("按任意键开始舍人！",280,450);
		}else if(ischoose) {
			g.drawImage(choose, 0, 0,getWidth(),getHeight(), null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("黑体",20,20));
			g.drawString("WS上下选择难度",50,100);
			g.drawString("按J键开启剧情模式",650,100);
			g.drawString("噩梦难度下可按K键",650,150);
			g.drawString("开启武将争霸模式",650,180);
			g.setFont(new Font("黑体",20,30));
			switch(chooseResult){
			case 1:
				g.drawImage(simple, 236, 65,382,80, null) ;
				g.drawImage(common, 265, 170,319,66, null) ;
				g.drawImage(difficult, 265,270,319,66, null);
				g.drawImage(superdifficult,265,370,319,66, null);
				g.drawString("简单：固定小兵，敌人攻击欲望低",200,40);
				break;
			case 2:
				g.drawImage(simple, 265, 70,319,66, null) ;
				g.drawImage(common, 236, 165,382,80, null) ;
				g.drawImage(difficult, 265, 270,319,66, null);
				g.drawImage(superdifficult, 265, 370,319,66, null);
				g.drawString("普通：固定小兵，敌人攻击欲望高",200,40);
				break;
			case 3:
				g.drawImage(simple, 265, 70,319,66, null) ;
				g.drawImage(common, 265, 170,319,66, null) ;
				g.drawImage(difficult,236,265,382,80, null);
				g.drawImage(superdifficult, 265, 370,319,66, null);
				g.drawString("困难：随机小兵，敌人攻击欲望中",200,40);
				break;
			case 4:
				g.drawImage(simple, 265, 70,319,66, null) ;
				g.drawImage(common, 265, 170,319,66, null) ;
				g.drawImage(difficult,265,270,319,66, null);
				g.drawImage(superdifficult, 236,365,382,80, null);
				g.drawString("噩梦：随机小兵，敌人攻击欲望很高",200,40);
				break;
			}
		}
		else {
	    drawPerson(g);
	    g.setColor(Color.RED);
	    g.setFont(new Font("宋体",20,20));
	    if(!boss)g.drawString("剩余小兵数量："+(enemyAllCount-4), 400,80);
	    switch(chooseResult) {
	    case 1:
	    	g.drawString("当前模式：简单难度剧情模式", 350,140);
	    	break;
	    case 2:
	    	g.drawString("当前模式：普通难度剧情模式", 350,140);
	    	break;
	    case 3:
	    	g.drawString("当前模式：困难难度剧情模式", 350,140);
	    	break;
	    case 4:
	    	g.drawString("当前模式：噩梦难度剧情模式", 350,140);
	    	break;
	    case 5:
	    	g.drawString("当前模式：噩梦难度武将争霸一周目", 350,140);
	    	break;
	    case 6:
	    	g.drawString("当前模式：噩梦难度武将争霸二周目", 350,140);
	    	break;
	    }
	    g.setColor(Color.WHITE);
	   if(enemyAllCount!=0)g.drawString("当前为第"+gameLevel+"关，"+"当前场上有"+enemycount+"个敌人，击杀僵尸回血后可进入下一关", 250,110);
	   else {
		   if(gameLevel!=3)g.drawString("第"+gameLevel+"关已过，按回车键进入下一关 ", 350,110);
		   else if(chooseResult<=4)g.drawString("您已通关", 350,110);
		   else g.drawString("第"+gameLevel+"关已过，按回车键进入下一关 ", 350,110);
	   	}
	   g.drawString("按空格键可暂停，暂停后可以重新开始游戏", 280,170);
	     if(isPause) {
	    	 g.drawImage(pause, 250, 50,null);
	    	 ispaint=false;
	     }
	     if(!zl.isIslive()) {
	    	 g.drawImage(over, 0, 0,getWidth() ,getHeight(),null);
	 		g.drawString("游戏结束，按回车键重新开始游戏",280, 350);
	     }
	     if(gameLevel==3&&enemyAllCount==0&&(chooseResult<=4||(chooseResult==6&&gameLevel==3))) {
	    	 g.setColor(Color.BLACK);
			 g.fillRect(0, 0, 1000, 1000);
			 g.drawImage(victory, 300, 100,null);
		   	g.setColor(Color.WHITE);
		   	g.drawString("您已通关，按回车键重新开始游戏",250, 350);
	     }
	   }
		
   	 
   	
 
	}
	

	private void enemyAppera(int temp) {
		switch(temp) {
		case 0:
			pers.add(new ZhangFei(700,this,zl,50,0));
			break;
		case 1:
			pers.add(new XuChu(700,this,zl,80,0));
			break;
		case 2:
			pers.add(new HuangZhong(700,this,zl,100,0));
			break;
		case 3:
			pers.add(new BaShen(700,this,zl,100,0));
			break;
		case 4:
			pers.add(new ZhaoYun1(700,this,zl,80,0));
			break;
		case 5:
			pers.add(new ZhaoYun2(700,this,zl,80,0));
			break;
		case 6:
			pers.add(new LvBu(700,this,zl,100,0));
			break;
		case 7:
			pers.add(new ZuoCi(700,this,zl,120,0));
			break;
		case 8:
			pers.add(new CaoCao(700,this,zl,150,0));
			break;
		case 9:
			pers.add(new SiMayi(700,this,zl,120,0));
			break;
		}
	}
	
	private void addEnemy(int level) {
		if(chooseResult==5) {
			boss=true;
			if(enemycount==0&&enemyAllCount==4) {
				switch(level) {
				case 1:
					pers.add(new HuangZhong(700,this,zl,1200,1));
					pers.add(new ZhangFei(700,this,zl,1200,2));
					break;
				case 2:
					pers.add(new BaShen(700,this,zl,1200,1));
					pers.add(new XuChu(700,this,zl,1200,2));
					break;
				case 3:
					pers.add(new SiMayi(700,this,zl,1200,1));
					pers.add(new LvBu(700,this,zl,1200,2));
					break;
				}
			}
		}
		if(chooseResult==6) {
			boss=true;
			if(enemycount==0&&enemyAllCount==4) {
				switch(level) {
				case 1:	
					pers.add(new ZhaoYun1(700,this,zl,1200,1));
					pers.add(new ZhaoYun2(700,this,zl,1200,2));	
					break;
				case 2:
					pers.add(new ZuoCi(700,this,zl,1200,1));
					pers.add(new BaShen(700,this,zl,1200,2));
					break;
				case 3:
					pers.add(new LvBu(700,this,zl,1200,1));
					pers.add(new CaoCao(700,this,zl,1200,2));
					break;
				}
			}
		}
		if(chooseResult<=4)
		switch(level) {
		case 1:	
			if(!boss) {
				if(enemycount==0) {
					int temp=enemyAllCount-4;
					if(temp>3)temp=3;
					if(temp>0) {
						for(int i=0;i<temp;i++) {			
							if(chooseResult<=2)
								enemyAppera(random.nextInt(2));
							else enemyAppera(random.nextInt(8)+2);
						}
					}
					else{
						pers.add(new HuangZhong(700,this,zl,1000,1));
						pers.add(new BaShen(700,this,zl,800,2));
						boss=true;
					}
				}
			}else if(enemycount<=2) {
				if(chooseResult<=2)
					enemyAppera(random.nextInt(2));
					else enemyAppera(random.nextInt(8)+2);
			}
		break;
		case 2:
			if(!boss) {
				if(enemycount==0) {
					int temp=enemyAllCount-4;
					if(temp>5)temp=5;
					if(temp>0)
						for(int i=0;i<temp;i++) {
							if(chooseResult<=2)
								enemyAppera(random.nextInt(6));
							else enemyAppera(random.nextInt(8)+2);
						}
					else{
						pers.add(new LvBu(700,this,zl,800,1));
						pers.add(new ZuoCi(700,this,zl,900,2));
						boss=true;
					}
				}
			}else if(enemycount<=3) {
				if(chooseResult<=2)
					enemyAppera(random.nextInt(6));
				else enemyAppera(random.nextInt(8)+2);
			}
			break;	
		case 3:
			if(!boss) {
				if(enemycount==0) {
					int temp=enemyAllCount-4;
					if(temp>7)temp=7;
					if(temp>0)
						for(int i=0;i<temp;i++) {
							if(chooseResult<=2)
								enemyAppera(random.nextInt(6)+2);
						else enemyAppera(random.nextInt(8)+2);
							
						}
					else{
						pers.add(new CaoCao(700,this,zl,1400,1));
						pers.add(new SiMayi(700,this,zl,1200,2));
						boss=true;
					}
				}
			}else if(enemycount<=4) {
				if(chooseResult<=2)
					enemyAppera(random.nextInt(6)+2);
			    else enemyAppera(random.nextInt(8)+2);
			}
			break;	
		}
    enemycount=pers.size()-1;
	}
	
	@Override
	public void run() {
		while(true) {
			if(isStart&&!ischoose) {
			  addEnemy(gameLevel);
			}
			if(boss&&enemyAllCount==0) {
				for(int i=0;i<pers.size();i++) {
					if(!pers.get(i).isZhang())
						pers.get(i).setIslive(false);
				}
			}
			
			BasePerson e=null;
			for(int i=0;i<pers.size();i++) {
				if(!pers.get(i).isIslive()&&!pers.get(i).isZhang())
					e=pers.get(i);
			}
			
			if(e!=null) {
				pers.remove(e);
					if(!boss)enemyAllCount--;
					else {
						if(e.getBoss()!=0) {
							enemyAllCount--;
							pers.add(new Zombie(700,this,zl,300,0));
						}
						if(e.isZombie()) {
							enemyAllCount--;
							zl.HPadd();
						}
					
				}
				
			}
		try { 
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		}
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		if(isStart&&!ischoose&&isPause&&e.getX()>=316&&e.getX()<=567&&e.getY()>=390&&e.getY()<=413) {
			restart();
		}
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}




