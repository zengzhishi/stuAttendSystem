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
	
	
	JxlAction(File f) throws Exception{//��ʼ����ȡ������xls�ļ�
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
	
	void updateBasicData(){//����excelѧ����������Ϣ
		nameCol = getNameCol();//��ȡ�������ڵ�����
		idCol = getIDCol();//��ȡѧ�����ڵ�����
		if(nameCol == -1){//-1��ʾ����ָ����
			ShowAboutData warming = new ShowAboutData(3);
			warming.setVisible(true);
		}else{//��ѧ���д����벻�����������
			if(nameCol != -1 && idCol != -1){
				times = sheet.getColumns()-2;
			}
			else{
				times = sheet.getColumns()-1; 
			}
		}
		stuNum = sheet.getRows()-1;
	}
	
	int getNameCol(){//��ȡ��������
		cell = sheet.getCell(0,0);
		String content = cell.getContents();
		for(int i = 0; i < sheet.getColumns(); i++){
			cell = sheet.getCell(i,0);
			content = cell.getContents();
			if(content.equals("����")) return i;
		}
		return -1;
	}
	
	int getIDCol(){//��ȡѧ�ŵ�����
		cell = sheet.getCell(0,0);
		String content = cell.getContents();
		for(int i = 0; i < sheet.getColumns(); i++){
			cell = sheet.getCell(i,0);
			content = cell.getContents();
			if(content.equals("ѧ��")) return i;
		}
		return -1;
	}
	
	String getName(int num){//��ȡ��ǰ�е�����
		Cell cell = sheet.getCell(nameCol,num);
		return cell.getContents();
	}
	
	String getID(int num){//��ȡ�������е�ǰ�е�ѧ��
		Cell cell = sheet.getCell(idCol,num);
		return cell.getContents();
	}
	
	String getPresentData(int num){//��ȡ�������
		int present = 0;
		String presentData = getName(num);//(�У��У��洢��ʾ��Ϣ����ʼֵΪ������
		int[] presentTime = new int[times];
		for(int i = 0; i < sheet.getColumns(); i++){
			Cell cell = sheet.getCell(i,num);
			if(i != nameCol && i != idCol){
				try {
					if(!cell.getContents().isEmpty()){
						presentTime[present] = Integer.parseInt(cell.getContents());
						if(presentTime[present] == 0)//��������Ϣ�洢��presentData��
							presentData = presentData+"\n��"+(present+1)+"�ε���:"+"false";
						else
							presentData = presentData+"\n��"+(present+1)+"�ε���:"+"true";	
					}
					else{
						presentData = presentData+"\n��"+(present+1)+"�ε���:"+"unknown";
					}
					present++;
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
			}
		}
		return presentData;
	}
	
	String checkData(String name){//ͨ�����ֽ��е�������Ĳ鿴
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
	
	void insertNewPresentTime(){//���������ĵ�����¼�����ڵ�����
		try{
			Label label = new Label( sheet.getColumns(), 0, "��"+(times+1)+"�ε���");
			sheet.addCell(label);			
		}catch(Exception e){
			System.out.println(e);
		}
		updateBasicData();
	}
	
	void updatePresentTime(int num, int updateCol, String updateData){//�޸ĵ������
		try{
			Label label = new Label(updateCol, num, updateData);
			sheet.addCell(label);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	void closeWorkbook(){//�رմ򿪵��ļ�,�����޸ĵ�����Э�ᵽexcel����
		try{
			wb.write();
			wb.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}