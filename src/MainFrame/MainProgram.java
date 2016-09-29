package MainFrame;

import java.io.File;
import java.util.Random;
import jxl.read.biff.*;

public class MainProgram {

	public static void main(String[] args) {	
		JxlFrame f = new JxlFrame();
	}
	
	public static JxlAction jxlAction(JxlAction jAction,File file) throws Exception{
		jAction = new JxlAction(file);			
		return jAction;
		
	}
	
	public static void jxlClose(JxlAction jAction){
		jAction.closeWorkbook();
	}
	
	public static String getStuOneByOne(JxlAction jAction,int top){
		String name = jAction.getName(top+1);
		return name;
	}
	
	public static String getStuByRandom(JxlAction jAction,int[] storage,int top){//top = 37
		Random rand = new Random();
		while(true){
			int randNum = rand.nextInt(jAction.stuNum);//0-37
			int i;
			for(i = 0; i < top; ++i){
				if(randNum == storage[i]) break;	
			}
			if((i == top) && (randNum != storage[i])){
				storage[top] = randNum;
				return jAction.getName(randNum+1);					
			}else if(i == jAction.stuNum-1 && randNum == storage[i]){
				return jAction.getName(randNum+1);
			}
		}
	}
	
	
	
}
