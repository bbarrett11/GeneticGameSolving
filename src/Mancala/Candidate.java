package Mancala;

public class Candidate implements Comparable{
	private String sequence;
	private int fitness;
	
	public Candidate(String s)
	{
		sequence = s;
		fitness = 0;
	}
	
	public int getFit(String s)
	{
		//System.out.println("Training: ");

		int f=0;
		for(int i = 0; i < s.length(); i++)
		{
			if(sequence.charAt(i) == s.charAt(i))
			{
				f++;
			}
		}
		fitness = f;
		return f;
	}

	public String getSeq()
	{
		return sequence;
	}
	
	public int getFitness()
	{
		return fitness;
	}

	@Override
	public int compareTo(Object o) {
		if(fitness>((Candidate)(o)).getFitness())		
			return -1;
		else if(fitness<((Candidate)(o)).getFitness())
			return 1;
		else
			return 0;
	}
}
