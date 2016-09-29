package MainFrame;

import java.io.File;

import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlAction {
	Workbook book;
	WritableWorkbook wb;
	WritableSheet sheet;
	Cell cell;
	Label label;
	int nameCol, idCol, times, stuNum;
	
	
	JxlAction(File f) throws Exception{//初始化获取操作的xls文件
			book = Workbook.getWorkbook(f);
			if(f.exists()){
				this.book = Workbook.getWorkbook(f);
				wb = Workbook.createWorkbook(f, book);
			}
			else{
				wb = Workbook.createWorkbook(f);
			}
			
			sheet = wb.getSheet(0);
			
			updateBasicData();
			System.out.println(nameCol+"   "+idCol+"    "+times+"    "+stuNum);
	}
	
	void updateBasicData(){//更新excel学生名单的信息
		nameCol = getNameCol();//获取姓名所在的列数
		idCol = getIDCol();//获取学号所在的列数
		if(nameCol == -1){//-1表示不含指定列
			ShowAboutData warming = new ShowAboutData(3);
			warming.setVisible(true);
		}else{//当学号列存在与不存在两种情况
			if(nameCol != -1 && idCol != -1){
				times = sheet.getColumns()-2;
			}
			else{
				times = sheet.getColumns()-1; 
			}
		}
		stuNum = sheet.getRows()-1;
	}
	
	int getNameCol(){//获取姓名列数
		cell = sheet.getCell(0,0);
		String content = cell.getContents();
		for(int i = 0; i < sheet.getColumns(); i++){
			cell = sheet.getCell(i,0);
			content = cell.getContents();
			if(content.equals("姓名")) return i;
		}
		return -1;
	}
	
	int getIDCol(){//获取学号的列数
		cell = sheet.getCell(0,0);
		String content = cell.getContents();
		for(int i = 0; i < sheet.getColumns(); i++){
			cell = sheet.getCell(i,0);
			content = cell.getContents();
			if(content.equals("学号")) return i;
		}
		return -1;
	}
	
	String getName(int num){//获取当前行的姓名
		Cell cell = sheet.getCell(nameCol,num);
		return cell.getContents();
	}
	
	String getID(int num){//获取点名表中当前行的学号
		Cell cell = sheet.getCell(idCol,num);
		return cell.getContents();
	}
	
	String getPresentData(int num){//获取点名情况
		int present = 0;
		String presentData = getName(num);//(列，行）存储显示信息，初始值为其名字
		int[] presentTime = new int[times];
		for(int i = 0; i < sheet.getColumns(); i++){
			Cell cell = sheet.getCell(i,num);
			if(i != nameCol && i != idCol){
				try {
					if(!cell.getContents().isEmpty()){
						presentTime[present] = Integer.parseInt(cell.getContents());
						if(presentTime[present] == 0)//将出勤信息存储到presentData中
							presentData = presentData+"\n第"+(present+1)+"次点名:"+"false";
						else
							presentData = presentData+"\n第"+(present+1)+"次点名:"+"true";	
					}
					else{
						presentData = presentData+"\n第"+(present+1)+"次点名:"+"unknown";
					}
					present++;
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		return presentData;
	}
	
	String checkData(String name){//通过名字进行点名情况的查看
		Cell cell;
		String content = null;
		int i;
		for(i = 1; i < sheet.getRows(); i++){
			cell = sheet.getCell(nameCol,i);
			content = cell.getContents();
			if(content.equals(name)){
				return content = getPresentData(i);
			}
		}
		return "Not Fount "+name;
		
	}
	
	void insertNewPresentTime(){//返回新增的点名记录列所在的列数
		try{
			Label label = new Label( sheet.getColumns(), 0, "第"+(times+1)+"次点名");
			sheet.addCell(label);			
		}catch(Exception e){
			System.out.println(e);
		}
		updateBasicData();
	}
	
	void updatePresentTime(int num, int updateCol, String updateData){//修改点名情况
		try{
			Label label = new Label(updateCol, num, updateData);
			sheet.addCell(label);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	void closeWorkbook(){//关闭打开的文件,并将修改的内容协会到excel表中
		try{
			wb.write();
			wb.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}