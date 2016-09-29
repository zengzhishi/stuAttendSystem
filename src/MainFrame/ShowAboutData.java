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
				String makerImfo = "�༶:\t"+"14��0403\t�������\n"+"������Ա:\t"+"����\tѧ��\n"
						+ "\t����ʦ\t2014220403004\n"
						+ "\t�μ���\t2014220403005\n"
						+ "\t�����\t2014220403006\n";
				setTitle("Maker information");
				textArea.setFont(new Font("����",Font.PLAIN,20));
				textArea.setEditable(false);
				textArea.setText(makerImfo);
			}
			else if(type == 2){
				String helpImfo = "�����Excel���Ҫ��:\n"+"\t��ʽҪ��:\t.xls\n"+"\t���ڸ�ʽ:\n"
						+"\t\t����\tѧ��(��ѡ)\t\t������¼(0/1)\n"
						+"\t��\t����\t2014220403003\t1\n"
						+"\t\t...\n"+"\t\t������Ŀ��ѧ��\n"
						+"\t˵������ 0��1 �ֱ��ʾ δ�����ѵ�";
				setTitle("Help information");
				textArea.setFont(new Font("����",Font.PLAIN,13));
				textArea.setEditable(false);
				textArea.setText(helpImfo);
			}
			else if(type == 3){
				String warming = "�����Excel�ļ���������";
				setTitle("warming");
				setBounds(100,100,260,100);
				textArea.setFont(new Font("����",Font.PLAIN,20));
				textArea.setForeground(Color.red);
				textArea.setEditable(false);
				textArea.setText(warming);
			}
		}
	}

}
