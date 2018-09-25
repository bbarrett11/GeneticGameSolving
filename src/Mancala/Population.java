package Mancala;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Population{
	
	private Candidate[] cand;
	private int pop,totalSize;
	private String ideal;
	
	public Population(int size, String goal)
	{
		ideal = goal;
		totalSize = size;
		cand = new Candidate[size];
		pop = 0;
	}
	
	public void addCandidate(Candidate c)
	{
		if(pop<totalSize)
		{
			cand[pop] = c;
		}
		pop++;
	}
	public boolean isFull()
	{
		return pop >= totalSize;
	}
	public void Train()
	{
		for(int i = 0; i< totalSize;i++)
		{
			cand[i]
					.getFit(ideal);
		}
	}
	
	public Candidate getMaxFit()
	{
		Train();
		Candidate best= cand[0];
		for(Candidate c : cand)
		{
			if(best.compareTo(c)>0)
				best = c;
		}
		return best;
	}
	
	public int getSize()
	{
		return totalSize;
	}
	
	public Candidate getTourneyResult()
	{
		Arrays.sort(cand);
		double odds = 70;
		Random r = new Random();
		for(Candidate c : cand)
		{
			if(r.nextDouble()*100 < odds)
				return c;
			odds-=odds/4;
		}
		return cand[totalSize-1];
	}
}
