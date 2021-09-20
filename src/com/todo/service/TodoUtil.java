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
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String time = st.nextToken();

				TodoItem t = new TodoItem(title, desc, time);
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
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 목록 생성 중 ==========\n"
				+ "제목 입력 : ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목 중복!!!");
			return;
		}
		//빈칸 체크도 나중에 해보기
		
		System.out.print("설명 입력 : ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 목록 삭제 중 ==========\n"
				+ "삭제할 제목 입력 : ");
				// + "\n");
		String title = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== 목록 수정 중 ==========\n"
				+ "수정할 제목 입력 : ");
				// + "\n");
		String title = sc.nextLine();
		if (!l.isDuplicate(title)) {
			System.out.println("입력하신 제목이 존재하지 않습니다 :(");
			return;
		}

		System.out.print("새로운 제목 입력 : ");
		String new_title = sc.nextLine();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목 중복!!!");
			return;
		}
		
		System.out.print("새로운 설명 추가 : ");
		String new_description = sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				// System.out.println("item updated");
			}
		}

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
