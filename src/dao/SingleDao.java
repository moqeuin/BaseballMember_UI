package dao;

import java.util.ArrayList;

import dto.Batter;
import dto.Human;
import dto.Pitcher;
import file.FileProc;

public class SingleDao {
	
	private static SingleDao sd = null;
	private ArrayList<Human> list = new ArrayList<Human>();
	private int memberNumber;
	private FileProc fc = new FileProc("baseball");
	
	private SingleDao() {
		// FileProc를 호출해서 파일에 저장된 데이터를 불러옴.
		fc.createFile();
		data_load();
		// 파일에 저장된 데이터가 없으면 등록선수 번호에 1000을 대입.
		if(list.isEmpty()) {
		memberNumber = 1000;
		}
		else {
			// 저장된 데이터에서 가장 마지막 선수의 등록번호를 가져옴.
			memberNumber = list.get(list.size() - 1).getNumber();
			// 타자면 -1000
			if(memberNumber >= 2000) {
				memberNumber = memberNumber - 1000;			
			}
		}
		// 선수를 추가할 때 +1된 등록번호를 준다.
		memberNumber = memberNumber + 1;	
	}
	
	public static SingleDao getInstance() {
		if(sd == null) {
			sd = new SingleDao();
		}
		// 인스턴스를 한 번 생성하고 다음부터 호출하면 계속해서 리턴.
		return sd;
	}
	
	// 추가
	public boolean insert(Human h) {
		// 투수면 저장된 등록번호 저장.
		if(h instanceof Pitcher) {
			h.setNumber(memberNumber);
		}
		else {
		// 타자면 저장된 등록번호에 1000을 더해서 저장.
			h.setNumber(memberNumber+1000);
		}
		
		// 리스트에 저장이 됐는지 확인.
		boolean b = list.add(h);
		// 다음 등록번호를 위해 증가.
		memberNumber++;
		// 저장이 성공됐는지 메세지박스로 출력.
		return b;
	}
	// 삭제
	public Boolean delete(String name) {
		Human h =null;
		Boolean complete = false;
		
		for (int i = 0; i < list.size(); i++) {
			h = list.get(i);
			// 입력한 이름으로 리스트에서 검색해서 삭제할 데이터를 찾는다.
			if(h.getName().equals(name)) {
				complete =true;
				list.remove(i);
				break;
			}
		}
		// 삭제할 선수가 없다면 false, 삭제가 성공하면 true를 반환.
		return complete;
	}
	// 검색
	public Human search(String name) {
		Human h =null;
		for (int i = 0; i < list.size(); i++) {
			// 이름을 통해서 리스트에서 데이터를 검색.
			if(list.get(i).getName().equals(name)) {
				h=list.get(i);
				break;
			}
		}
		// 검색한 객체를 리턴, 없으면 null을 리턴.
		return h;		
	}
	
	public ArrayList<Human> getList(){
		// AllPrintView에서 선수 데이터를 모두 출력시키기 위해서 데이터를 저장한 리스트를 리턴.
		return list;
	}
		
	public void data_save() {
		//한 선수의 데이터를 배열의 하나씩 저장하기 위해서 list의 크기만큼 배열할당.
			String datas[] = new String[list.size()];
					
			for (int i = 0; i < datas.length; i++) {
				Human h = list.get(i);
				// 1001-홍길동-24-178.1-10-3-0.12와 같은 형식으로 데이터를 저장.
				datas[i] = h.toString();
			}	
			fc.File_save(datas);	
	}
	
	public void data_load() {
		// 파일에서 저장된 데이터를 배열에 저장
		// 저장된 데이터의 형식 : 1000 - 홍길동 - 24 - 179 .....
		String datas[] = fc.File_load();
			
		for (int i = 0; i < datas.length; i++) {
			// 토큰을 기준으로 문자열을 하나씩 배열 한 개에 저장.
			String data[] = datas[i].split("-");
			
			int title = Integer.parseInt(data[0]);			
			Human human;
			if(title < 2000) {		// 투수				
				human = new Pitcher(	Integer.parseInt(data[0]), 
										data[1], 
										Integer.parseInt(data[2]), 
										Double.parseDouble(data[3]), 
										Integer.parseInt(data[4]), 
										Integer.parseInt(data[5]), 
										Double.parseDouble(data[6]) );
				list.add(human);		
			}
			else { // 타자
				human = new Batter(	Integer.parseInt(data[0]), 
										data[1], 
										Integer.parseInt(data[2]), 
										Double.parseDouble(data[3]), 
										Integer.parseInt(data[4]), 
										Integer.parseInt(data[5]), 
										Double.parseDouble(data[6]) );				
				list.add(human);
				// 로드한 데이터를 리스트에 저장.
			}							
		}		
	}
}