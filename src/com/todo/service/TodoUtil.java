package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class TodoUtil {
	public static void loadList(TodoList l, String filename){
		//buffereader
		int count = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename)); 
			String oneline;
			while((oneline = br.readLine()) != null){
				count++;
				StringTokenizer st = new StringTokenizer(oneline, "-");
				String category = st.nextToken().trim();
				String title = st.nextToken().trim();
				String desc = st.nextToken().trim();
				String due_date = st.nextToken().trim();
				String time = st.nextToken();

				TodoItem t = new TodoItem(title, desc, time, category, due_date);
				l.addItem(t);
			}
			br.close();
			if (count == 0) System.out.println("목록이 비워져있음 :(");
			else System.out.println("목록 로딩 완료!!!");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void saveList(TodoList l, String filename){
		//filewriter
        try{
            File file = new File(filename) ;
            FileWriter fw = new FileWriter(file, false);
			for (TodoItem item : l.getList()) {
				fw.write(item.toSaveString()+"\n");
				// fw.write("\n");
			}	
            fw.flush();
            fw.close();
			System.out.println("파일 저장 완료!");
             
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		String category, due_date;
		// int index = list.getList().size();
		Scanner sc = new Scanner(System.in);
		// sc.nextLine();
		System.out.print("\n"
				+ "[항목 추가]\n"
				+ "제목 입력 : ");
		
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("제목 중복!!!");
			return;
		}
		//빈칸 체크도 나중에 해보기
		
		System.out.print("설명 입력 : ");
		desc = sc.nextLine().trim();

		System.out.print("카테고리 입력 : ");
		category = sc.nextLine().trim();

		System.out.print("마감 날짜 : ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String answer;
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		int num = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			if (num == l.indexOf(item)+1) {
				System.out.print((l.indexOf(item)+1) + ". " + item.toPrintString() + " 위 항목을 삭제하시겠습니다까? (y/n)");
				answer = sc.next().trim();
				
				if(answer.equals("y")) {
					l.deleteItem(item);
					System.out.println("내용 삭제를 완료했습니다!");
				}
				else System.out.print("삭체를 취소했습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String new_title, new_description, new_due_date, new_category;
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
				// + "\n");
		int num = sc.nextInt();
		sc.nextLine(); //버퍼비워주기
		// if (num > l.getList().size()) {
		// 	System.out.println("입력하신 인덱스가 존재하지 않습니다. :(");
		// 	return;
		// }

		for (TodoItem item : l.getList()) {
			if (num == l.indexOf(item)+1) {
				System.out.print((l.indexOf(item)+1) + ". " + item.toPrintString()+"\n");
				System.out.print("새로운 제목 입력 > ");
				new_title = sc.nextLine().trim();

				if (l.isDuplicate(new_title)) {
					System.out.println("제목 중복!!!");
					return;
				}

				System.out.print("새 카테고리 > ");
				new_category = sc.nextLine().trim();

				System.out.print("새로운 설명 추가 > ");
				new_description = sc.nextLine().trim();

				System.out.print("새 마감일자 > ");
				new_due_date = sc.nextLine().trim();

				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				break;
			}
		}
	}

	public static void findAll(TodoList l, String key){
		// System.out.println(key.trim());
		int count = 0;
		for(TodoItem item : l.getList()){
			if(item.getTitle().contains(key) || item.getDesc().contains(key)){
				count++;
				System.out.println(l.indexOf(item)+1 + "." + item.toPrintString());
			}
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");

	}

	public static void findCategory(TodoList l, String key){
		int count = 0;
		System.out.println(key.trim());
		for(TodoItem item : l.getList()){
			if(item.getCategory().contains(key)){
				count++;
				System.out.println(l.indexOf(item)+1 + "." + item.toPrintString());
			}
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");
	}

	public static void listCate(TodoList l){
		HashSet<String> set = new HashSet<String>();
		for(TodoItem item : l.getList()){
			set.add(item.getCategory());
		}

		Iterator<String> iter = set.iterator();
		int count = 1;
		while(iter.hasNext()){
			System.out.print(iter.next());
			if (count != set.size()) System.out.print(" / ");
			count++;
		}

		System.out.println("\n총 " + set.size() + "개의 카테고리가 등록되어 있습니다.");
	}

	// public static void listAll(TodoList l) {
	// 	if (l.getList().size() ==0) System.out.println("목록 내용이 없음! ");
	// 	else{
	// 		System.out.println("[목록]");
	// 		for (TodoItem item : l.getList()) {
	// 			System.out.println(item.toSaveString());
	// 		}	
	// 	}
	// }
}
