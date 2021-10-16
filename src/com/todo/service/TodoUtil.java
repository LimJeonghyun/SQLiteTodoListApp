package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;

public class TodoUtil {

	public static void createItem(TodoList l) {

		String title, desc, category, due_date, completeness, priority, member;
		Scanner sc = new Scanner(System.in);

		System.out.print("\n" + "[항목 추가]\n" + "제목 > ");
		title = sc.nextLine().trim();
		if (l.isDuplicate(title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		System.out.print("중요도 추가 (*/no 으로 구분) > " );
		priority=sc.next();
		sc.nextLine();
		System.out.print("공유하는 인원 추가 (&으로 구분) > ");
		member = sc.nextLine().trim();
		completeness = "미완료";
		TodoItem t = new TodoItem(title, desc, category,due_date, completeness, priority, member);
//		l.addItem(t);
		if (l.addItem(t) > 0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[항목 삭제]\n" + "삭제할 항목의 번호 (동시 삭제 가능 / &로 구분) >  ");
		String indexL = sc.nextLine();
		String[] index = indexL.split(" & ");
		for(int i=0; i<index.length; i++) {
			l.deleteItem(Integer.parseInt(index[i]));
		}
		System.out.println("삭제되었습니다.");
	}
	
	public static void deletAllItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n"+"[모든 항목 삭제]\n" + "모든 항목을 삭제하시겠습니까? (y/n) > ");
		String answer = sc.next();
		int count = 0;
		if (answer.equals("y")) {
			l.deleteAll();
			System.out.println("모두 삭제되었습니다!");
		}
			
	}
	
	
	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date, new_completeness, new_priority, new_member;
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[항목 수정]\n" + "수정할 항목의 번호 > ");
		int index = sc.nextInt();
		
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 제목 > ");
		new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("중요도 추가 (*/no 으로 구분) > ");
		new_priority = sc.next();
		sc.nextLine();
		System.out.print("새 공유멤버 지정 > ");
		new_member = sc.nextLine();
		new_completeness = "미완료";
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_completeness, new_priority, new_member);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
	}

	public static void find(TodoList l, String key) {
		int count = 0;
		for (TodoItem item : l.getList(key)) {
				System.out.println(item.toString());
				count ++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void find_cate(TodoList l, String key) {
		int count = 0;
		for (TodoItem item : l.getListCategory(key)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void listAll(TodoList l) {
		System.out.println("");
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAllpriority(TodoList l) {
		System.out.println("");
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		System.out.printf("\n*** 중요한 할 일 ***\n");
		for (TodoItem item : l.getListpriority()) {
			System.out.println(item.toString());
		}
		System.out.printf("\n*** 할 일 ***\n");
		for (TodoItem item : l.getListpriority_none()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void listCate(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item+" ");
			count ++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void update(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[완료 표시]\n" + "완료로 바꿀 번호 (동시 완료 가능 / &로 구분) >  ");
		String indexL = sc.nextLine();
		String[] index = indexL.split(" & ");
		for(int i=0; i<index.length; i++) {
			l.updateCompleteness(Integer.parseInt(index[i]));
		}
		System.out.println("완료로 체크하였습니다.");
	}
}


//	public static void loadList(TodoList l, String filename){
//		int count = 0;
//		try{
//			BufferedReader br = new BufferedReader(new FileReader(filename)); 
//			String oneline;
//			while((oneline = br.readLine()) != null){
//				count++;
//				StringTokenizer st = new StringTokenizer(oneline, "-");
//				String category = st.nextToken().trim();
//				String title = st.nextToken().trim();
//				String desc = st.nextToken().trim();
//				String due_date = st.nextToken().trim();
//				String time = st.nextToken();
//
//				TodoItem t = new TodoItem(title, desc, time, category, due_date);
//				l.addItem(t);
//			}
//			br.close();
//			if (count == 0) System.out.println("목록이 비워져있음 :(");
//			else System.out.println("목록 로딩 완료!!!");
//		}
//		catch(FileNotFoundException e){
//			e.printStackTrace();
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//	}

//	public static void saveList(TodoList l, String filename){
//        try{
//            File file = new File(filename) ;
//            FileWriter fw = new FileWriter(file, false);
//			for (TodoItem item : l.getList()) {
//				fw.write(item.toSaveString()+"\n");
//				// fw.write("\n");
//			}	
//            fw.flush();
//            fw.close();
//			System.out.println("파일 저장 완료!");
//             
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//	}
//	
//	public static void createItem(TodoList l) {
//		
//		String title, desc;
//		String category, due_date;
//		// int index = list.getList().size();
//		Scanner sc = new Scanner(System.in);
//		// sc.nextLine();
//		System.out.print("\n"
//				+ "[항목 추가]\n"
//				+ "제목 입력 > ");
//		
//		title = sc.nextLine().trim();
//		if (l.isDuplicate(title)) {
//			System.out.printf("제목 중복!!!");
//			sc.close();
//			return;
//		}
//		
//		System.out.print("카테고리 > ");
//		category = sc.next().trim();
//		sc.nextLine();
//
//		System.out.print("내용 > ");
//		desc = sc.nextLine().trim();
//		sc.nextLine();
//
//		System.out.print("마감일자 > ");
//		due_date = sc.nextLine().trim();
//		
//		TodoItem t = new TodoItem(title, desc, category, due_date);
//		if(l.addItem(t)>0) System.out.println("추가되었습니다.");
//		sc.close();
//	}

//	
//	public static void deleteItem(TodoList l) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하시오 > ");
//		int index = sc.nextInt();
//		if(l.deleteItem(index) > 0 ) System.out.println("삭제되었습니다.");
//		sc.close();
//	}
//	
//	public static void findList(TodoList l, String keyword) {
//		int count = 0;
//		for (TodoItem item : l.getList(keyword)) {
//			System.out.println(item.toString());
//			count++;
//		}
//		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
//	}
//
//	public static void find_cate(TodoList l, String keyword) {
//		int count = 0;
//		for (TodoItem item : l.getListCategory(keyword)) {
//			System.out.println(item.toString());
//			count++;
//		}
//		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
//	}
//	
//	public static void listAll(TodoList l) {
//		System.out.printf("[전체 목록 , 총 %d개]\n", l.getCount());
//		for(TodoItem item : l.getList()) {
//			System.out.println(item.toString());
//		}
//	}
//	
//	public static void listAll(TodoList l, String orderby, int ordering) {
//		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
//		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
//			System.out.println(item.toString());
//		}
//	}
//	
//	public static void listCate(TodoList l) {
//		System.out.println("");
//		System.out.println("<카테고리 목록>");
//		int count = 0;
//		for (String item : l.getCategories()) {
//			System.out.print(item+" ");
//			count ++;
//		}
//		System.out.printf("\nSystem: 총 %d개의 카테고리가 등록되어 있습니다.\n", count);
//	}
//	public static void deleteItem(TodoList l) {
//		
//		Scanner sc = new Scanner(System.in);
//		String answer;
//		
//		System.out.print("\n"
//				+ "[항목 삭제]\n"
//				+ "삭제할 항목의 번호를 입력하시오 > ");
//		int num = sc.nextInt();
//		
//		for (TodoItem item : l.getList()) {
//			if (num == l.indexOf(item)+1) {
//				System.out.print((l.indexOf(item)+1) + ". " + item.toPrintString() + " 위 항목을 삭제하시겠습니다까? (y/n)");
//				answer = sc.next().trim();
//				
//				if(answer.equals("y")) {
//					l.deleteItem(item);
//					System.out.println("내용 삭제를 완료했습니다!");
//				}
//				else System.out.print("삭체를 취소했습니다.");
//				break;
//			}
//		}
//	}
//
//		Iterator<String> iter = set.iterator();
//		int count = 1;
//		while(iter.hasNext()){
//			System.out.print(iter.next());
//			if (count != set.size()) System.out.print(" / ");
//			count++;
//		}
//
//		System.out.println("\n총 " + set.size() + "개의 카테고리가 등록되어 있습니다.");
//	}
//}


