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
import panel.UpdateBatPanel;
import panel.UpdatePitPanel;

public class UpdateView extends JFrame implements ActionListener{
	
	JLabel updateLabel;
	JLabel infoLabel[] = new JLabel[3];
	JTextField jf;
	JButton[] btn = new JButton[2];
	
	JPanel panel;	
	UpdatePitPanel up = new UpdatePitPanel(this);
	UpdateBatPanel ub = new UpdateBatPanel(this);

	Container c;
		
	MainView mainView;
	public UpdateView() {
		
		super("선수데이터 수정");
		
		c = getContentPane();
		c.setLayout(null);
		// 버튼과 라벨을 부착할 패널.
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 550, 80);
		panel.setBackground(new Color(245, 245, 220));
		// 뷰의 제목라벨.
		updateLabel = new JLabel("Player Update");	
		updateLabel.setHorizontalAlignment(JLabel.CENTER);
		updateLabel.setBounds(180, 5, 180, 30);
		updateLabel.setOpaque(true);
		updateLabel.setBackground(new Color(224, 210, 255));	
		panel.add(updateLabel);
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
		// 업데이트투수 패널을 처음에 컨탠트팬에 부착.
		c.add(up);		
		
		c.setBackground(new Color(245, 245, 220));		
		setBounds(600, 270, 550, 450);	
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		c =getContentPane();
		// 부착된 투수 또는 타자 패널을 제거.
		c.remove(1);
		
		// 투수 버튼
		if(obj == btn[0]) {
			c.add(new UpdatePitPanel(this));
		}
		// 타자 버튼
		else if(obj == btn[1]) {
			c.add(new UpdateBatPanel(this));
		}
		// 패널이 제거된 것을 jframe에게 알려서 새로고침한다.
		repaint();
	}
}