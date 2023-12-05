import java.util.*;

public class State 
{
	private int f, h, g;
	private boolean d;
	private State father;
	private int totalTime;
	private ArrayList<State> children;
	private ArrayList<Integer> left;
    private ArrayList<Integer> right;
	
	public State() 
	{
		this.f = 0;
		this.h = 0;
		this.g = 0;
		this.father = null;
		this.totalTime = 0;
		this.children = new ArrayList<State>();
		this.right = new ArrayList<Integer>();
		this.left = new ArrayList<Integer>();

	}
	
	public State(State s)
	{
		this.children = new ArrayList<State>();
		this.right = new ArrayList<Integer>();
		this.left = new ArrayList<Integer>();
		this.father = s;
		
	}

	public void setLeft(int t)
	{
		this.left.add(t);
	}

	public void setGo(boolean d){
		this.d = d;
	}

	public boolean getGo()
	{
		return this.d;
	}	

	public void setLeft(ArrayList<Integer> t)
	{
		while(!t.isEmpty()){
			this.left.add(t.get(0));
			t.remove(0);
		}
		
	}

	public void setRight(ArrayList<Integer> t)
	{
		this.right=t ;
	}

	public ArrayList<Integer> getLeft()
	{
		return this.left;
	}

	public ArrayList<Integer> getRight()
	{
		return this.right;
	}
	
	public void setChildren(State ch){
		this.children.add(ch);
	}

	public int getF() 
	{
		return this.f;
	}
	
	public int getG() 
	{
		return this.g;
	}
	
	public int getH() 
	{
		return this.h;
	}
	
	public State getFather()
	{
		return this.father;
	}
	
	public void setF(int f)
	{
		this.f = f;
	}
	
	public void setG(int g)
	{
		this.g = g;
	}
	
	public void setH(int h)
	{
		this.h = h;
	}
	
	
	public int getTotalTime() 
	{
		return this.totalTime;
	}
	
	public void setTotalTime(int time)
	{
		this.totalTime = time;
	}
	
	public void evaluate() 
	{
		this.f = this.h + this.g;
	}
		
	public ArrayList<State> getChildren() {return this.children;}
	
}
