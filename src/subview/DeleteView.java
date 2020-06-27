package subview;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dao.SingleDao;
import mainview.MainView;

public class DeleteView extends JFrame implements ActionListener{
	
	JLabel deleteLabel;
	JLabel nameLabel;
	JTextField jf;
	JButton btn[] = new JButton[2];
	
	public DeleteView() {
		
		super("선수데이터 삭제");
		setLayout(null);
		
		Container c = getContentPane();
		c.setLayout(null);
		// 뷰의 제목라벨.
		deleteLabel = new JLabel("Player Delete");	
		deleteLabel.setHorizontalAlignment(JLabel.CENTER);
		deleteLabel.setBounds(65, 15, 180, 40);
		deleteLabel.setOpaque(true);
		deleteLabel.setBackground(new Color(224, 210, 255));	
		c.add(deleteLabel);
		// 텍스트필드 이름.
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(60, 70, 30, 35);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setBackground(new Color(245, 245, 220));
		nameLabel.setOpaque(true);
		c.add(nameLabel);
		// 삭제할 데이터를 입력할 텍스트필드.
		jf = new JTextField(5);
		jf.setBounds(110,70,150,35);
		c.add(jf);
		
		// 삭제, 메뉴 버튼
		String[] btn_title = {"삭제", "메뉴"};
		
		for (int i = 0; i < btn_title.length; i++) {
			btn[i] = new JButton(btn_title[i]);
			btn[i].setBounds(30+(i*130), 130, 120, 30);
			btn[i].setOpaque(true);
			btn[i].addActionListener(this);
			c.add(btn[i]);
		}
		c.setBackground(new Color(245, 245, 220));	
		setBounds(730, 350, 330, 230);		
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		// singledao에서 이름을 받아서 삭제하기 위해 리턴.
		SingleDao sd = SingleDao.getInstance();		

		if(obj == btn[0]) {
			
			if(jf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력란에 빈 칸이 있습니다.");
				return;
			}
			// 입력한 이름을 받아서 singledao에게 넘겨서 데이터를 삭제했는지 유무를 출력.
			String name = jf.getText();
			Boolean complete= sd.delete(name);
			
			if(complete){
				JOptionPane.showMessageDialog(null,"선수의 데이터를 성공적으로 삭제했습니다.");
			}
			else {
				JOptionPane.showMessageDialog(null,"삭제할 선수의 이름이 없습니다.");
			}
			// 작업이 끝나면 비움.	
			jf.setText("");
		
		}
		// 메인메뉴 전환.
		else if(obj == btn[1]) {
			new MainView();
			this.dispose();
		}
	}
}