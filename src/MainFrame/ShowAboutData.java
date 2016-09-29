package MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;

public class ShowAboutData extends JDialog {

	/**
	 * Create the dialog.
	 */
	public ShowAboutData(int type) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		JTextArea textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.CENTER);
		{
			if(type == 1){
				String makerImfo = "班级:\t"+"14级0403\t大机三班\n"+"制作成员:\t"+"姓名\t学号\n"
						+ "\t曾智师\t2014220403004\n"
						+ "\t何嘉欣\t2014220403005\n"
						+ "\t黎寰宇\t2014220403006\n";
				setTitle("Maker information");
				textArea.setFont(new Font("宋体",Font.PLAIN,20));
				textArea.setEditable(false);
				textArea.setText(makerImfo);
			}
			else if(type == 2){
				String helpImfo = "输入的Excel表格要求:\n"+"\t格式要求:\t.xls\n"+"\t表单内格式:\n"
						+"\t\t姓名\t学号(可选)\t\t点名记录(0/1)\n"
						+"\t如\t李四\t2014220403003\t1\n"
						+"\t\t...\n"+"\t\t任意数目个学生\n"
						+"\t说明：用 0和1 分别表示 未到和已到";
				setTitle("Help information");
				textArea.setFont(new Font("宋体",Font.PLAIN,13));
				textArea.setEditable(false);
				textArea.setText(helpImfo);
			}
			else if(type == 3){
				String warming = "输入的Excel文件无名字列";
				setTitle("warming");
				setBounds(100,100,260,100);
				textArea.setFont(new Font("宋体",Font.PLAIN,20));
				textArea.setForeground(Color.red);
				textArea.setEditable(false);
				textArea.setText(warming);
			}
		}
	}

}
