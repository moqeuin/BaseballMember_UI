package panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.SingleDao;
import dto.Human;
import dto.Pitcher;
import mainview.MainView;
import subview.InsertView;

public class PitcherPanel extends JPanel implements ActionListener{
	
	JLabel infoLabel[] = new JLabel[6];
	JTextField textField[] = new JTextField[6];
	JButton[] btn = new JButton[2];
	
	InsertView insertView;
	
	public PitcherPanel(InsertView insertView) {
		// 메뉴로 전환할 때 isertView를 종료하기 위해서 저장.
		this.insertView= insertView;
		
		setLayout(null);
		// 텍스트필드의 이름.		
		String labelName[] = {"이름","나이","신장","승수", "패전수", "방어율"};
		
		for (int i = 0; i < infoLabel.length; i++) {
			infoLabel[i] =new JLabel(labelName[i]);	
			infoLabel[i].setHorizontalAlignment(JLabel.CENTER);
			infoLabel[i].setBounds(20, 10+(i*40), 60, 25);
			infoLabel[i].setOpaque(true);
			infoLabel[i].setBackground(Color.white);	
			add(infoLabel[i]);
		}
		// 데이터 입력란.
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(10);
			textField[i].setBounds(110, 10+(i*40), 190, 25);
			add(textField[i]);
		}
		// 추가, 메뉴 버튼	
		String[] btn_title = {"추가", "메뉴"};
		for (int i = 0; i < btn_title.length; i++) {
			btn[i] = new JButton(btn_title[i]);
			btn[i].setBounds(30+(i*140), 250, 120, 30);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			add(btn[i]);
		}		
		setBounds(110, 80, 320, 300);
		setBackground(new Color(135, 206, 250));			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		// 입력된 데이터를 리스트에 저장하기 위해서 불러옴.
		SingleDao sd = SingleDao.getInstance();
		
		if(obj == btn[0]) {
			// 데이터를 입력해서 객체를 생성하기 위한 객체변수.
			Human h=null;
			// 텍스트필드에 빈 칸이 있는 지 확인.
			for (int i = 0; i < textField.length; i++) {
				if(textField[i].getText().equals("")) {	
					JOptionPane.showMessageDialog(null, "입력란에 빈 칸이 있습니다.");
					return;
				}
			}
			// 텍스트필드에 입력된 문자열을 불러와서 변수에 저장.
			String name = textField[0].getText();			
			String age = textField[1].getText();			
			String height = textField[2].getText();
			String win = textField[3].getText();	
			String lose = textField[4].getText();
			String defence = textField[5].getText();
			// 변수의 저장한 데이터를 기반으로 객체 생성.
			h = new Pitcher(0, name, Integer.parseInt(age), 
							Double.parseDouble(height), Integer.parseInt(win),
							Integer.parseInt(lose), Double.parseDouble(defence));
			// 추가가 성공적으로 됐는지 확인.
			boolean b =sd.insert(h);
			
			if(b) JOptionPane.showMessageDialog(null, "성공적으로 추가되었습니다.");	
			else JOptionPane.showMessageDialog(null, "추가를 실패했습니다");
			// 작업을 마치면 텍스트 필드란를 비움.
			for (int i = 0; i < textField.length; i++) {
				textField[i].setText("");
			}		
		}
		// 메뉴 화면으로 전환.
		if(obj == btn[1]) {
			insertView.dispose();
			new MainView();		
		}	
	}
}