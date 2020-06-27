package subview;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dao.SingleDao;
import dto.Human;
import mainview.MainView;

public class SelectView extends JFrame implements ActionListener {
	
	JLabel selectLabel;
	JLabel nameLabel;
	
	JTextField jf;
	JTextArea ta;
	JScrollPane js;
	
	JButton btn[] = new JButton[2];
	
	public SelectView() {
		
		super("선수데이터 검색");
		
		Container c = getContentPane();
		c.setLayout(null);
		// 뷰의 제목라벨.
		selectLabel = new JLabel("Player Select");	
		selectLabel.setHorizontalAlignment(JLabel.CENTER);
		selectLabel.setBounds(185, 15, 180, 30);
		selectLabel.setOpaque(true);
		selectLabel.setBackground(new Color(224, 210, 255));	
		c.add(selectLabel);
		// 검색한 선수의 데이터를 출력할 textarea에 스크롤바를 부착시켜 컨탠트팬에 부착.
		ta = new JTextArea(30,20);
		js = new JScrollPane(ta);	
		js.setBounds(100, 60, 350, 100);
		c.add(js);
		// 입력란의 이름.
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(150, 170, 60, 30);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setBackground(new Color(245, 245, 220));
		nameLabel.setOpaque(true);
		c.add(nameLabel);
		// 검색할 선수에 입력란.
		jf = new JTextField(5);
		jf.setBounds(210,170,160,30);
		c.add(jf);
		// 검색, 메뉴 버튼.
		String[] btn_title = {"검색", "메뉴"};
		
		for (int i = 0; i < btn_title.length; i++) {
			btn[i] = new JButton(btn_title[i]);
			btn[i].setBounds(120+(i*170), 210, 120, 30);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			c.add(btn[i]);
		}
		
		c.setBackground(new Color(245, 245, 220));		
		setBounds(600, 320, 550, 300);	
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		// 입력한 이름을 통해서 리스트에서 데이터를 찾기 위해 리턴.
		SingleDao sd = SingleDao.getInstance();
			
		if(obj == btn[0]) {
			
			Human h=null;
			
			if(jf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력란에 빈 칸이 있습니다.");
				return;
			}
			// 입력란에서 텍스트를 가져와 리스트에서 데이터를 찾는다.
			String name = jf.getText();
			
			h= sd.search(name);
			// 데이터가 없으면 리턴.
			if(h==null) {
				JOptionPane.showMessageDialog(null,"검색한 이름이 없습니다.");
				return;
			}
			else {
			// 검색한 데이터를 개행문자와 함께 출력하고 다음 검색한 데이터와 계속이어준다.
			ta.append(h.toString()+"\n");
			}
			// 작업이 끝나면 입력란을 비움.
			jf.setText("");			
		}	
		// 메인 메뉴전환.
		else if(obj == btn[1]) {
			new MainView();
			this.dispose();
		}
	}
}