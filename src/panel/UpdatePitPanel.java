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
import dto.Batter;
import dto.Human;
import dto.Pitcher;
import mainview.MainView;
import subview.UpdateView;

public class UpdatePitPanel extends JPanel implements ActionListener{
	
	JTextField textField[] = new JTextField[3];
	JLabel infoLabel[] = new JLabel[3];
	JLabel nameLabel;
	JButton[] btn = new JButton[2];
	JTextField jf;
	
	UpdateView updateView;
	
	public UpdatePitPanel(UpdateView updateView) {
		// 메뉴로 전환할 때 updateView를 종료하기 위해서 저장.
		this.updateView = updateView;
		
		setLayout(null);
		// 텍스트필드의 이름.
		String labelName[] = {"승수", "패전수", "방어율"};
		for (int i = 0; i < infoLabel.length; i++) {
			infoLabel[i] =new JLabel(labelName[i]);	
			infoLabel[i].setHorizontalAlignment(JLabel.CENTER);
			infoLabel[i].setBounds(20, 15+(i*60), 60, 30);
			infoLabel[i].setOpaque(true);
			infoLabel[i].setBackground(Color.white);	
			add(infoLabel[i]);
		}
		// 데이터 입력란.
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(10);
			textField[i].setBounds(110, 15+(i*60), 190, 30);
			add(textField[i]);
		}
		// 수정, 메뉴 버튼
		String[] btn_title = {"수정", "메뉴"};
		for (int i = 0; i < btn_title.length; i++) {
			btn[i] = new JButton(btn_title[i]);
			btn[i].setBounds(30+(i*140), 250, 120, 30);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			add(btn[i]);
		}
		// 입력할 텍스트필드 이름.
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(60, 195, 50, 30);
		nameLabel.setBackground(Color.white);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setOpaque(true);
		add(nameLabel);
		// 수정할 이름을 입력할 텍스트필드.
		jf = new JTextField(5);
		jf.setBounds(140,195,120,30);
		add(jf);
		
		setBounds(110, 80, 320, 300);
		setBackground(new Color(135, 206, 250));	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		// 이름으로 검색하기위한 search()불러오기 위해 리턴.
		SingleDao sd = SingleDao.getInstance();
		
		if(obj == btn[0]) {
			
			if(jf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력란에 빈 칸이 있습니다.");
				return;
			}		
			// 입력란이 빈 칸인지 확인.
			for (int i = 0; i < textField.length; i++) {
				if(textField[i].getText().equals("")) {	
					JOptionPane.showMessageDialog(null, "입력란에 빈 칸이 있습니다.");
					return;
				}
			}		
			// 수정하기 위해 입력한 이름을 가져옴.
			String name = jf.getText();
			// 이름을 통해서 리스트에서 데이터를 찾는다.
			Human h = sd.search(name);
			// 리스트에 데이터가 없을 때.
			if(h==null) {
				JOptionPane.showMessageDialog(null,"수정할 선수의 이름이 없습니다.");
				return;
			}	
			// 선택한 항목이 수정할 선수의 타입과 다르면 리턴.
			if(h instanceof Batter) {
				JOptionPane.showMessageDialog(null,"이름의 선수는 투수 타입입니다.");
				return;
			}
			// 텍스트 필드에서 데이터를 가져와서 변수에 저장.
			String win = textField[0].getText();	
			String lose = textField[1].getText();
			String defence = textField[2].getText();
			// 투수 객체로 변환해서 투수만 가지고 있는 데이터를 저장.
			Pitcher p = (Pitcher)h;
			p.setWin(Integer.parseInt(win));
			p.setLose(Integer.parseInt(lose));
			p.setDefence(Double.parseDouble(defence));
			
			JOptionPane.showMessageDialog(null,"선수 수정 완료");
			// 작업이 끝나면 입력란을 빈 칸으로 비운다.
			for (int i = 0; i < textField.length; i++) {
				textField[i].setText("");
			}
		}
		// 메인화면으로 전환.
		else if(obj == btn[1]) {
			updateView.dispose();
			new MainView();
		}
	}
}