package HelloWorld;

public class driver {
	
	public static final String GOAL = "Hello World!";
	public static void main(String[] args)
	{
		int numTests = 100;
		int TotalGen = 0;
		
		int popSize = 60;
		for(int mRate = 90; mRate<101;mRate++)
		{	for(int i = 0; i < numTests;i++)
			{
				TotalGen+=runTest(popSize, GOAL, mRate);
			}
		TotalGen/=numTests;
		System.out.println("Average Generation of "+numTests+" Tests: "+TotalGen+" For a mRate of: "+mRate+"% "+ " totalOpRate = "+(TotalGen*popSize));
		//Best Solution for Hello World!, popSize = 30 is 136 w/ 1Mutant, mRate = 95
		//Best Solution for Hello World!, popSize = 60 is 70 w/ 1Mutant, mRate = 100
		//Best Solution for Hello World!, popSize = 120 is 43 w/ 1Mutant, mRate = 96
		//Best Solution for Hello World!, popSize = 240 is 29 w/ 1Mutant, mRate = 100

		//Best TotalOpRate = 3950

		//
		}
	}
	
	public static int runTest(int popSize, String goal,int mRate)
	{
		Population pop = new Population(popSize,goal);
		while(!pop.isFull())
		{
			String temp = "";
			while(temp.length() < goal.length())
			{
				temp+=(char)(int)(Math.random()*(0x7e-0x20)+0x20)+"";
			}
			Candidate c = new Candidate(temp);
			//System.out.println(temp);
			pop.addCandidate(c);
		}
		int gen = 0;
		while(pop.getMaxFit().getFitness()< goal.length())
		{
			//System.out.println("Generation: "+(gen++)+" Max Fitness: "+ pop.getMaxFit().getFitness() +"--"+pop.getMaxFit().getSeq());
			gen++;
			pop = newPopTourneySubstring1Mutant(pop,goal,mRate);
		}
		//System.out.println("Final Generation: "+(gen++)+" Max Fitness: "+ pop.getMaxFit().getFitness());
		return gen;
	}
	
	public static Population newPopRandom()
	{
		Population newPop = new Population(30,GOAL);
		while(!newPop.isFull())
		{
			String temp = "";
			while(temp.length() < GOAL.length())
			{
				if(Math.random()<0.5)
					temp+="0";
				else
					temp+="1";
			}
			Candidate c = new Candidate(temp);
			newPop.addCandidate(c);
		}
		return newPop;
	}
/**
 * @brief Random Additions to random characters
 * @param cPop The current population to work with
 * @param goal String that the population is trying to emulate or find
 * @note 	Optimal Mutation rate seems to be about 90-100% 
 * 			Optimal Addition range seems to be ~[0,36)
 * 			As popSize decreases, the optimal Mutation rate seems to decrease as well
 * @return Number of generations to find goal
 */
	public static Population newPopTourneySubstring1Mutant(Population cPop,String goal,int MuteRate)
	{
		Population newPop = new Population(cPop.getSize(),goal);
		while(!newPop.isFull())
		{	
			Candidate c = cPop.getTourneyResult();
			Candidate d = cPop.getTourneyResult();
			String s = c.getSeq().substring(0, goal.length()/2)+d.getSeq().substring(goal.length()/2,goal.length());
			if(Math.random()*100 <MuteRate)
			{
				char[] chars = s.toCharArray();
				int index = (int)(Math.random()*chars.length);
				chars[index] += (int)(Math.random()*36);
				chars[index]%=0x7e;
				if(chars[index]<0x20)
					chars[index]+=0x20;
				s = new String(chars);
			}
			newPop.addCandidate(new Candidate(s));
		}
		return newPop;
	}
	
	/**
	 * @brief Random additions to 2 random characters when creating next population
	 * @param cPop 
	 * @param goal
	 * @note Increased number of generations as probability of Mutation increases
	 * @return
	 */
		public static Population newPopTourneySubstring2Mutants(Population cPop,String goal,int MuteRate)
		{
			Population newPop = new Population(cPop.getSize(),goal);
			while(!newPop.isFull())
			{	
				Candidate c = cPop.getTourneyResult();
				Candidate d = cPop.getTourneyResult();
				String s = c.getSeq().substring(0, goal.length()/2)+d.getSeq().substring(goal.length()/2,goal.length());
				if(Math.random()*100 <MuteRate)
				{
					char[] chars = s.toCharArray();
					int index = (int)(Math.random()*chars.length);
					chars[index] += (int)(Math.random()*37);
					chars[index]%=0x7e;
					if(chars[index]<0x20)
						chars[index]+=0x20;
					int index2 = (int)(Math.random()*chars.length);
					chars[index2] += (int)(Math.random()*53);
					chars[index2]%=0x7e;
					if(chars[index2]<0x20)
						chars[index2]+=0x20;
					s = new String(chars);
				}
				newPop.addCandidate(new Candidate(s));
			}
			return newPop;
		}
	
}
