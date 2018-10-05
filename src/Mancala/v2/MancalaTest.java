package Mancala.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class MancalaTest {
	private Mancala game;
	
	@Before
	public void setUp() throws InterruptedException
	{
		game = new Mancala();
	}
	
	@Test
	public void  InitializeBoard()
	{
		int[] cUpper = {4,4,4,4,4,4};
		int[] cLower = {4,4,4,4,4,4};
		int lScore = 0;
		int uScore = 0;
		boolean lTurn = true;
		String msg = correctState(game,cUpper,cLower,uScore,lScore,lTurn);
		assertEquals(msg,msg,"");
	}
	@Test
	public void getInputLowerFirstMoveTest() throws InterruptedException
	{
		int[] cUpper = {4,4,4,5,5,5};
		int[] cLower = {4,4,4,4,4,0};
		int lScore = 1;
		int uScore = 0;
		boolean lTurn = false;
		game.GetInputLower(6);
		String msg = correctState(game,cUpper,cLower,uScore,lScore,lTurn);
		assertEquals(msg,msg,"");
	}
	@Test
	public void getInputLowerRepeatMoveTest() throws InterruptedException
	{
		int[] cUpper = {4,4,4,4,4,4};
		int[] cLower = {4,4,0,5,5,5};
		int lScore = 1;
		int uScore = 0;
		boolean lTurn = true;
		game.GetInputLower(3);
		String msg = correctState(game,cUpper,cLower,uScore,lScore,lTurn);
		assertEquals(msg,msg,"");
	}
	
	public String correctState(Mancala g, int[] upper, int[] lower, int Sup, int Slw,boolean lTurn)
	{
		String error = "";
		for(int i = 0; i < upper.length;i++)
		{
			if(g.getLowerPlayer()[i] != lower[i])
			{
				error+="Error lower index: "+i+"\n";
			}
			if(g.getUpperPlayer()[i]!= upper[i])
			{
				error+="Error upper index: "+i+"\n";
			}
		}
		if(g.getUpperBin() != Sup)
			error+="Error upper bin\n";
		if(g.getLowerBin() != Slw)
			error+="Error upper bin\n";
		if(g.getLowerTurn() != lTurn)
			error+="Error turn\n";

		return  error;
	}
}
