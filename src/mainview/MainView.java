package mainview;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import dao.SingleDao;
import subview.AllprintView;
import subview.DeleteView;
import subview.InsertView;
import subview.SelectView;
import subview.UpdateView;

public class MainView extends JFrame implements ActionListener{
	
	JButton[] btn = new JButton[6];
	JLabel menuLabel;
		
	public MainView() {
		// 메인메뉴 화면
		super("선수 관리 시스템");
		// 메뉴화면을 들어가면 싱글턴 클래스를 호출해서 데이터를 로드.
		SingleDao sd = SingleDao.getInstance();
		
		setLayout(null);
		
		Container c = getContentPane();
		c.setBackground(new Color(245, 245, 220));
		c.setLayout(null);
		
		// 메뉴 타이틀 라벨
		menuLabel = new JLabel("BaseBall Player Edit System");	
		menuLabel.setHorizontalAlignment(JLabel.CENTER);
		menuLabel.setBounds(174, 6, 180, 50);
		menuLabel.setOpaque(true);
		menuLabel.setBackground(new Color(224, 210, 255));
		c.add(menuLabel);
		
		// 메뉴 버튼
		String btnTitle[] = {"추가", "삭제", "검색", "수정", "출력", "저장"};
		for (int i = 0; i < btnTitle.length; i++) {
			btn[i] = new JButton(btnTitle[i]);
			btn[i].setBounds(160, 65+(i*55), 210, 40);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			c.add(btn[i]);
		}				
		setBounds(600, 270, 550, 450);	
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		// 데이터를 저장하기 위해서 호출.
		SingleDao sd = SingleDao.getInstance();
		// 선수 추가
		if(obj == btn[0]) {
			new InsertView();
			this.dispose();
		}
		// 선수 삭제
		else if(obj == btn[1]) {
			new DeleteView();		
			this.dispose();
		}
		// 선수 검색
		if(obj == btn[2]) {
			new SelectView();	
			this.dispose();	
		}
		// 선수 수정
		if(obj == btn[3]) {
			new UpdateView();	
			this.dispose();
		}
		// 선수 출력
		if(obj == btn[4]) {
			new AllprintView();	
			this.dispose();
		}
		// 데이터 저장
		if(obj == btn[5]) {
			sd.data_save();
			JOptionPane.showMessageDialog(null,"파일 저장을 완료했습니다.");
		}		
	}
}