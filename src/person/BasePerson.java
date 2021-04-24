package person;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BasePerson implements Comparable<BasePerson>,Person{

	protected Image[] stand_leftImgs;   
	protected Image[] stand_rightImgs;  
	
	protected Image[] walk_leftImgs;   
	protected Image[] walk_rightImgs; 
	
	protected Image[] down_leftImgs; 
	protected Image[] down_rightImgs; 
	
	protected Image[] attack_leftImgs;  
	protected Image[] attack_rightImgs;  
	protected Image[] attack1_leftImgs;
	protected Image[] attack1_rightImgs;
	protected Image[] attack2_leftImgs;  
	protected Image[] attack2_rightImgs; 
	
	protected boolean isMusic=false;
	
	private boolean isattack=false;
	private Image[]stateImgs;
	private int imgIndex=0;
	private int drawIndex=6;
	private boolean attackFlag=false;
	protected int attackHP=0;
	protected int HP=300;
	protected int[]attackIndex;
	protected int imgx,imgy,hitx,hity,width,height,attx,atty,atWidth,atHeight;
	//           
	protected int x,y;
	private boolean right=true;
	private boolean islive=true;
	private boolean isZombie=false;
	private boolean isZhang=false;
    private boolean hitDown=false;
	private boolean isHit=false;
	private boolean attack=false;
	private boolean attack1=false;
	private boolean attack2=false;
	private int isBoss=0;
 
    
	
	public Image[] getStateImgs() {
		return stateImgs;
	}
	public void setDrawIndex(int drawIndex) {
		this.drawIndex = drawIndex;
	}
	public int getBoss() {
		return isBoss;
	}
	public void setBoss(int isBoss) {
		this.isBoss = isBoss;
	}
	public boolean isHit() {
		return isHit;
	}
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	public boolean isAttack() {
		return attack;
	}
	public void setAttack(boolean attack) {
		this.attack = attack;
	}
	public boolean isAttack1() {
		return attack1;
	}
	public void setAttack1(boolean attack1) {
		this.attack1 = attack1;
	}
	public boolean isAttack2() {
		return attack2;
	}
	public void setAttack2(boolean attack2) {
		this.attack2 = attack2;
	}
	public boolean isHitDown() {
		return hitDown;
	}
	public void setHitDown(boolean isdown) {
		this.hitDown = isdown;
	}
	public void setZhang(boolean isZhang) {
		this.isZhang = isZhang;
	}
	public boolean isZhang() {
		return isZhang;
	}
	public boolean isIslive() {
		return islive;
	}
	public void setIslive(boolean islive) {
		this.islive = islive;
	}
	public BasePerson(int HP){
		this.HP=HP;
	}
	public void setAttackHP(int attackHP) {
		this.attackHP = attackHP;
	}
	public int getAttackHP() {
		return attackHP;
	}
	public void setAttackFlag(boolean attackFlag) {
		this.attackFlag = attackFlag;
	}
	public int getImgIndex() {
		return imgIndex/drawIndex;
	}
	public void setImgIndex(int imgIndex) {
		this.imgIndex = imgIndex;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setStateImgs(Image[] stateImgs) {
		this.stateImgs = stateImgs;
	}
	public void setRight(boolean right) {
		this.right = right;
	}	
	public boolean isRight() {
		return right;
	}
	public void HPadd(){
		this.HP+=30;
		if(HP>100)HP=100;
	}
	
	public void setImgs(Image[][] imgs){
		stand_leftImgs=imgs[0];
		stand_rightImgs=imgs[1];
		walk_leftImgs=imgs[2];
		walk_rightImgs=imgs[3];
		down_leftImgs=imgs[4];
		down_rightImgs=imgs[5];
		attack_leftImgs=imgs[6];
		attack_rightImgs=imgs[7];
		attack1_leftImgs=imgs[8];
		attack1_rightImgs=imgs[9];
		attack2_leftImgs=imgs[10];
		attack2_rightImgs=imgs[11];
	}
	
	public void setImgs(Image[]walkleft,Image[]walkright){
		walk_leftImgs=walkleft;
		walk_rightImgs=walkright;
	}
	
	private void judje(){
		if(attackFlag){
			for(int i:attackIndex){
				if(i==imgIndex/drawIndex) {
					isattack=true;
				}
				else isattack=false;
			}
		}
	}
	
    public void drawHP(Graphics g) {
    	
    }
	
	private Image[] temp;
	public void drawself(Graphics g){
		if(islive){
			if(stateImgs!=temp)imgIndex=0;  
			g.drawImage(stateImgs[imgIndex/drawIndex], imgx, imgy, null);
			judje();
			if(++imgIndex>=stateImgs.length*drawIndex)imgIndex=0;
			temp=stateImgs;
			
			//g.setColor(Color.RED);
	    	//g.drawRect(attx,atty, atWidth, atHeight);
			//g.setColor(Color.BLACK);
			//g.drawRect(hitx, hity, width, height);
			//g.fillRect(x, y,10, 10);
		}
	}
	
	//վ��
	public void stand(int imgx,int imgy,int width,int height,int hitx,int hity){
		if(right)stateImgs=stand_rightImgs;
		else stateImgs=stand_leftImgs;
		this.imgx=imgx;
		this.imgy=imgy;
		this.width=width;
		this.height=height;
		this.atWidth=0;
		this.atHeight=0;
		this.hitx=hitx;
		this.hity=hity;
		this.attackFlag=false;
		this.setAttack(false);
		this.setAttack1(false);
		this.setAttack2(false);
	}
	
	//��
	public void walk(int imgx,int imgy,int width,int height,int hitx,int hity){
		if(right)stateImgs=walk_rightImgs;
		else stateImgs=walk_leftImgs;
		this.imgx=imgx;
		this.imgy=imgy;
		this.width=width;
		this.height=height;
		this.atWidth=0;
		this.atHeight=0;
		this.hitx=hitx;
		this.hity=hity;
		this.attackFlag=false;
		this.setAttack(false);
		this.setAttack1(false);
		this.setAttack2(false);
	}
	
	//����
	public void down(int imgx,int imgy){
		if(!hitDown)
		    if(right)stateImgs=down_rightImgs;
			else stateImgs=down_leftImgs;
		this.imgx=imgx;
		this.imgy=imgy;
		this.width=0;
		this.height=0;
		this.atWidth=0;
		this.atHeight=0;
		this.attackFlag=false;
		if(!isZhang())
		this.hitDown=true;
	}
	
	
	//��ͨ����
	public void attack(int imgx,int imgy,int width,int height,int hitx,int hity,int atwidth,int atheight,int attx,int atty,int []index,int attackHP){
		if(!attack)
			if(right)stateImgs=attack_rightImgs;
		    else stateImgs=attack_leftImgs;
		this.imgx=imgx;
		this.imgy=imgy;
		this.width=width;
		this.height=height;
		this.atWidth=atwidth;
		this.atHeight=atheight;
		this.hitx=hitx;
		this.hity=hity;
		this.attx=attx;
		this.atty=atty;
		this.attackIndex=index;
		this.attackFlag=true;
		this.attackHP=attackHP;
		if(!isZhang)
			attack=true;
		this.setAttack1(false);
		this.setAttack2(false);
	}
	
	//����1
	public void attack1(int imgx,int imgy,int width,int height,int hitx,int hity,int atwidth,int atheight,int attx,int atty,int []index,int attackHP){
		if(!attack1)
			if(this.isRight())this.setStateImgs(attack1_rightImgs);
			else this.setStateImgs(attack1_leftImgs);
		this.imgx=imgx;
		this.imgy=imgy;
		this.width=width;
		this.height=height;
		this.atWidth=atwidth;
		this.atHeight=atheight;
		this.hitx=hitx;
		this.hity=hity;
		this.attx=attx;
		this.atty=atty;
		this.attackIndex=index;
		this.setAttackFlag(true);
		this.setAttackHP(attackHP);
		if(!isZhang)
			attack1=true;
		this.setAttack(false);
		this.setAttack2(false);
	}
	
	//����2
	public void attack2(int imgx,int imgy,int width,int height,int hitx,int hity,int atwidth,int atheight,int attx,int atty,int []index,int attackHP){
		if(!attack2)
			if(this.isRight())this.setStateImgs(attack2_rightImgs);
			   else this.setStateImgs(attack2_leftImgs);
		this.imgx=imgx;
		this.imgy=imgy;
		this.width=width;
		this.height=height;
		this.atWidth=atwidth;
		this.atHeight=atheight;
		this.hitx=hitx;
		this.hity=hity;
		this.attx=attx;
		this.atty=atty;
		this.attackIndex=index;
		this.setAttackFlag(true);
		this.setAttackHP(attackHP);
		if(!isZhang)
			attack2=true;
		this.setAttack(false);
		this.setAttack1(false);	
	}
	
	public Rectangle getRect(){
		return new Rectangle(hitx,hity,width,height);
	}
	
	public Rectangle attackRect(){
		if(isattack)
		return new Rectangle(attx,atty,atWidth,atHeight);
		else return new Rectangle(0,0,0,0);
	}
	@Override
	//��������
	public int compareTo(BasePerson o) {
		if(this.y>o.y)return 1;
		if(this.y<o.y)return -1;
		return 0;
	}

	@Override
	public void move(boolean isright) {}
	@Override
	public void draw(Graphics g){}
	@Override
	public boolean isZombie() {
		return isZombie;
	}
	public void setZombie(boolean isZombie) {
		this.isZombie = isZombie;
	}

}
