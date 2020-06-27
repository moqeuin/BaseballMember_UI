package subview;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mainview.MainView;
import panel.BatterPanel;
import panel.PitcherPanel;

public class InsertView extends JFrame implements ActionListener{
	
	JButton[] btn = new JButton[2];	
	JLabel insertLabel;
	
	JPanel panel;
	PitcherPanel pit= new PitcherPanel(this);
	BatterPanel bat = new BatterPanel(this);
	
	Container c;
	
	public InsertView() {
		
		super("선수데이터 추가");
		
		setLayout(null);
		c =  getContentPane();
		// 버튼과 라벨을 부착할 패널.	
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 550, 80);
		panel.setBackground(new Color(245, 245, 220));
		// 뷰의 제목라벨.
		insertLabel = new JLabel("Player Insert");	
		insertLabel.setHorizontalAlignment(JLabel.CENTER);
		insertLabel.setBounds(180, 5, 180, 30);
		insertLabel.setOpaque(true);
		insertLabel.setBackground(new Color(224, 210, 255));	
		panel.add(insertLabel);
		// 투수, 타자의 데이터 입력란 패널을 전환할 버튼.
		String[] btn_title = {"투수", "타자"};
		
		for (int i = 0; i < btn_title.length; i++) {
			btn[i] = new JButton(btn_title[i]);
			btn[i].setBounds(125+(i*170), 42, 120, 30);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			panel.add(btn[i]);
		}
		// 버튼, 라벨를 포함한 패널을 컨탠트팬에 부착.		
		c.add(panel);
		// 투수패널을 처음에 컨탠트팬에 부착.
		c.add(pit);	
		
		c.setBackground(new Color(245, 245, 220));		
		setBounds(600, 270, 550, 450);			
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();	
		// 부착된 투수 또는 타자 패널을 제거.
		c = getContentPane();
		c.remove(1);
		// 투수버튼
		if(obj == btn[0]) {			
			c.add(pit);			
		}
		// 타자버튼
		else if(obj == btn[1]) {		
			c.add(bat);				
		}
		//메인화면 전환.
		else if(obj == btn[2]) {
			new MainView();
			this.dispose();			
		}
		// 패널이 제거된 것을 jframe에게 알려서 새로고침한다.
		repaint();
	}
}