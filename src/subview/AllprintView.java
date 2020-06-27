package subview;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import dao.SingleDao;
import dto.Human;
import mainview.MainView;

public class AllprintView extends JFrame implements ActionListener{
		
	JLabel printLabel;
	JTextArea ta;
	JScrollPane js;
	JButton btn[] = new JButton[2];
	
	public AllprintView() {
		
		super("선수데이터 출력");
		
		Container c = getContentPane();
		c.setLayout(null);
		// 뷰의 제목라벨.
		printLabel = new JLabel("Player AllPrint");
		printLabel.setHorizontalAlignment(JLabel.CENTER);
		printLabel.setBounds(170, 10, 180, 30);
		printLabel.setOpaque(true);
		printLabel.setBackground(new Color(224, 210, 255));	
		c.add(printLabel);
		// 출력받을 textarea를 스크롤바에 부착 시켜 컨탠트팬에 부착.
		ta = new JTextArea(30,20);
		js = new JScrollPane(ta);	
		js.setBounds(100, 50, 320, 300);
		c.add(js);
		// 출력, 메뉴 버튼	
		String btn_title[] = {"출력", "메뉴"};	
		
		for (int i = 0; i < btn_title.length; i++) {			
			btn[i] = new JButton(btn_title[i]);
			btn[i].setBounds(135+(i*130), 360, 120, 30);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			c.add(btn[i]);
		}
		
		c.setBackground(new Color(245, 245, 220));
		setBounds(600, 270, 550, 450);	
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		// singledao에서 선수데이터가 저장된 리스트를 리턴 받아서 저장.
		SingleDao sd = SingleDao.getInstance();
		ArrayList<Human> list = new ArrayList<Human>();	
		list = sd.getList();
		// 수정버튼
		if(obj == btn[0]) {
			// 리스트에서 모든 데이터를 반복해서 출력.
			for (int i = 0; i < list.size(); i++) {
				Human h = list.get(i);
				ta.append(h.toString()+"\n");
			}
		}
		// 메인메뉴 전환.
		else if(obj == btn[1]) {
			new MainView();
			this.dispose();
		}			
	}
}