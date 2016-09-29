package MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;

public class JxlFrame extends JFrame implements ActionListener{

	JxlAction jAction;
	ShowAboutData aboutDataShow;
	private JPanel contentPane,panel_3;
	private JTextField textField;
	Font nameFont = new Font("����",Font.PLAIN,150),
			presentDataFont = new Font("����",Font.PLAIN,18),
			warmingFont = new Font("����",Font.PLAIN,60),
			warmingFont2 = new Font("����",Font.PLAIN,30);
	boolean isOpen = false,newCheck = false,isCheck = false,isUsable = true;
	File file;
	String name;
	int[] storage;//�洢����˳��Ķ�ջ
	int top;//��ջ����ʶ����Ϊ�������
	JMenuBar menuBar;
	JMenu mnFile,mnAbout;
	JMenuItem mntmOpen,mntmExit,mntmMaker,mntmHelp;
	FileDialog openDia;
	ButtonGroup bg;
	JRadioButton jr1,jr2;
	JTextArea textArea;
	JButton btnNewButton_3,btnNewButton_2,btnNewButton_1,btnNewButton,btnNewButton_4;

	/**
	 * Create the frame.
	 */
	public JxlFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("ѧ������ϵͳ");
		setBounds(100, 100, 647, 465);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
	
		mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		openDia = new FileDialog(this,"�ҵĴ�",FileDialog.LOAD);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mntmMaker = new JMenuItem("Maker");
		mnAbout.add(mntmMaker);
		
		mntmHelp = new JMenuItem("Help");
		mnAbout.add(mntmHelp);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		bg = new ButtonGroup(); 
		jr1 = new JRadioButton("˳�����",true);
		jr2 = new JRadioButton("�������");
		bg.add(jr1);
		bg.add(jr2);
		panel.add(jr1);
		panel.add(jr2);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		
		btnNewButton_4 = new JButton("��ʼ");
		panel_4.add(btnNewButton_4);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 3, 0, 0));
		
		btnNewButton_2 = new JButton("Yes");
		btnNewButton_2.setMnemonic(KeyEvent.VK_F1);
		panel_1.add(btnNewButton_2);
		
		btnNewButton_1 = new JButton("No");
		btnNewButton_1.setMnemonic(KeyEvent.VK_F2);
		panel_1.add(btnNewButton_1);
		
		btnNewButton = new JButton("Reset");
		btnNewButton.setMnemonic(KeyEvent.VK_F3);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("����Ҫ��ѯ��ѧ������");
		panel_2.add(label);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(20);
		
		btnNewButton_3 = new JButton("ȷ��");
		
		panel_2.add(btnNewButton_3);
		
		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		panel_3.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel_3.add(scrollPane);
		scrollPane.setBounds(29, 10, 490, 304); 	
		myEvent();
		setVisible(true);
	}
	
	private void myEvent(){
		mntmOpen.addActionListener(new ActionListener() {//���ļ�����ȡѧ��������
			public void actionPerformed(ActionEvent e) {
				textArea.setFont(presentDataFont);	
				textArea.setForeground(Color.BLACK);
				if(isOpen && isUsable)
					jAction.closeWorkbook();//��ֹ��;�ٴ������ļ�ʱ����д�뵼�����ݶ�ʧ
				openDia.setVisible(true);  
				String dirPath = openDia.getDirectory();//��ȡ�ļ�·��  
				String fileName = openDia.getFile();//��ȡ�ļ�����  
				if(dirPath == null || fileName == null)
					return;
				file = new File(dirPath,fileName);  //��ȡ���ļ���·�����ļ��������ں����ĵ�������
				try{					
					jAction = MainProgram.jxlAction(jAction, file);
					textArea.setText("�ļ����سɹ�");
					isUsable = true;
				}catch(Exception e1){
					textArea.setText("���ܲ������ļ�\n�������ƶ�");
					isUsable = false;
				}
				isOpen = true;
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {//��ʼ����
			public void actionPerformed(ActionEvent e){
				if(isOpen && isUsable){
					if(newCheck){
						textArea.setText("�Ѿ�������һ�ε���\n�Ƿ�Ҫ�ٴ�����һ�ε�����\n����Yes/No����ȷ��");
						textArea.setFont(warmingFont2);
						textArea.setForeground(Color.red);
						isCheck = true;
					}
					else{
						if(jr1.isSelected()){//ѡ��˳������ķ�ʽ
							top = 0;
							jAction.insertNewPresentTime();
							name = MainProgram.getStuOneByOne(jAction,top);
							textArea.setText(name);
						}else if(jr2.isSelected()){
							storage = new int[jAction.stuNum];
							top = 0;
							jAction.insertNewPresentTime();
							name = MainProgram.getStuByRandom(jAction,storage,top);
							textArea.setText(name);
						}						
						textArea.setFont(nameFont);
						textArea.setForeground(Color.BLACK);
					}
					newCheck = true;
				}
				else{
					textArea.setText("δ���ҵ��ļ�");
					textArea.setFont(warmingFont);
					textArea.setForeground(Color.red);
				}
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {//�˳�����
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				if(arg.equals("Exit")){
					if(isOpen && isUsable){
						jAction.closeWorkbook();						
					}
					setVisible(false);
					dispose();	
					System.exit(0);
				}
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {//ȷ����ѯ
			public void actionPerformed(ActionEvent e) {
				String stuName = textField.getText();
				if(!isCheck){
					if(!stuName.isEmpty()){
						if(isOpen && isUsable){			
							textArea.setText(jAction.checkData(stuName));
							textArea.setFont(presentDataFont);	
							textArea.setForeground(Color.BLACK);
						}
						else{
							textArea.setText("δ���ҵ��ļ�");
							textArea.setFont(warmingFont);
							textArea.setForeground(Color.red);
						}
					}else{
						textArea.setText("����Ϊ��");
						textArea.setFont(warmingFont);
						textArea.setForeground(Color.red);
					}					
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {//����-ȷ�ϵ���
			public void actionPerformed(ActionEvent arg0) {
				if(isOpen && newCheck && isUsable){
					if(!isCheck){
						if(top != jAction.stuNum){
							if(jr1.isSelected()){//˳�����
								jAction.updatePresentTime(top+1, jAction.sheet.getColumns()-1, "1");
								name = MainProgram.getStuOneByOne(jAction,++top);
								if(top == jAction.stuNum) {
									textArea.setText("����ѧ���Ѿ��������");
									textArea.setFont(warmingFont);
								}
								else {
									textArea.setText(name);
									textArea.setFont(nameFont);
								}
							}else if(jr2.isSelected()){
								jAction.updatePresentTime(storage[top]+1, jAction.sheet.getColumns()-1, "1");
								if((top+1) != jAction.stuNum)
									name = MainProgram.getStuByRandom(jAction,storage,++top);
								else
									top++;
								System.out.println(top);
								if(top == jAction.stuNum) {
									textArea.setText("����ѧ���Ѿ��������");
									textArea.setFont(warmingFont);
								}
								else {
									textArea.setText(name);
									textArea.setFont(nameFont);
								}
							}
						}else {
							textArea.setText("����ѧ���Ѿ��������");
							textArea.setFont(warmingFont);
						}
						textArea.setForeground(Color.BLACK);
					}else{
						if(jr1.isSelected()){//ѡ��˳������ķ�ʽ
							top = 0;
							jAction.insertNewPresentTime();
							name = MainProgram.getStuOneByOne(jAction,top);
							textArea.setText(name);
						}else if(jr2.isSelected()){
							storage = new int[jAction.stuNum];
							top = 0;
							jAction.insertNewPresentTime();
							name = MainProgram.getStuByRandom(jAction,storage,top);
							textArea.setText(name);
						}						
						textArea.setFont(nameFont);
						textArea.setForeground(Color.BLACK);
						isCheck = false;
					}
				}
				else{
					textArea.setText("δ���ҵ��ļ�");
					textArea.setFont(warmingFont);
					textArea.setForeground(Color.red);
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {//����-ѧ��δ��
			public void actionPerformed(ActionEvent e) {
				if(isOpen && newCheck && isUsable){
					if(!isCheck){
						if(top != jAction.stuNum){
							if(jr1.isSelected()){
								jAction.updatePresentTime(top+1, jAction.sheet.getColumns()-1, "0");
								name = MainProgram.getStuOneByOne(jAction,++top);
								if(top == jAction.stuNum) {
									textArea.setText("����ѧ���Ѿ��������");
									textArea.setFont(warmingFont);
								}
								else {
									textArea.setText(name);
									textArea.setFont(nameFont);
								}
							}else if(jr2.isSelected()){
								jAction.updatePresentTime(storage[top]+1, jAction.sheet.getColumns()-1, "0");
								if((top+1) != jAction.stuNum)
									name = MainProgram.getStuByRandom(jAction,storage,++top);
								else
									top++;
								if(top == jAction.stuNum) {
									textArea.setText("����ѧ���Ѿ��������");
									textArea.setFont(warmingFont);
								}
								else {
									textArea.setText(name);
									textArea.setFont(nameFont);
								}
							}
							
						}else {
							textArea.setText("����ѧ���Ѿ��������");
							textArea.setFont(warmingFont);
						}
						textArea.setForeground(Color.BLACK);
						textArea.setFont(nameFont);
					}else{
						textArea.setText(name);
						textArea.setForeground(Color.BLACK);
						textArea.setFont(nameFont);
						isCheck = false;
					}
				}
				else{
					textArea.setText("δ���ҵ��ļ�");
					textArea.setFont(warmingFont);
					textArea.setForeground(Color.red);
				}
			}
		});
		

		btnNewButton.addActionListener(new ActionListener() {//reset������һ��ѧ��
			public void actionPerformed(ActionEvent e) {
				
				if(isOpen && newCheck && isUsable){
					if(!isCheck){
						if(top >= 1){
							if(jr1.isSelected()){
								top--;
								name = MainProgram.getStuOneByOne(jAction,top);
								textArea.setText(name);
							}else if(jr2.isSelected()){
								top--;
								name = MainProgram.getStuByRandom(jAction,storage,top);
								textArea.setText(name);
							}
							textArea.setFont(nameFont);	
							textArea.setForeground(Color.BLACK);
						}						
					}		}
				else{
					textArea.setText("δ���ҵ��ļ�");
					textArea.setFont(warmingFont);
					textArea.setForeground(Color.red);
				}
			}
		});
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(isOpen && isUsable){
					jAction.closeWorkbook();					
				}
			}
		});

		mntmMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutDataShow = new ShowAboutData(1);
				aboutDataShow.setVisible(true);
			}
		});
		

		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aboutDataShow = new ShowAboutData(2);
				aboutDataShow.setVisible(true);
			}
		});
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
