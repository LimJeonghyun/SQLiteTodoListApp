package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolist.txt");
		
		boolean quit = false;
		Menu.displaymenu();

		do {
			String choice = sc.next();

			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				 TodoUtil.listAll(l);
				break;

			case "ls_name":
				TodoUtil.listAll(l,"title", 1);
				System.out.println("제목순으로 정렬하였습니다!");
				break;
//
			case "ls_name_desc":
				TodoUtil.listAll(l,"title", 0);
				System.out.println("제목역순으로 정렬하였습니다!");
				break;
//				
			case "ls_date":
				TodoUtil.listAll(l, "due_date", 1);
				System.out.println("날짜순으로 정렬하였습니다!");
				break;
//			
			case "ls_date_desc":
				TodoUtil.listAll(l, "due_date", 0);
				System.out.println("날짜역순으로 정렬하였습니다!");
				break;
//
			case "exit":
				System.out.println("종료합니다!");
//				TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;
//			
			case "help":
				Menu.prompt();
				break;

			case "find":
				TodoUtil.find(l, sc.nextLine().trim());
				break;
			
			case "find_cate":
				TodoUtil.find_cate(l, sc.nextLine().trim());
				break;

			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			default:
				System.out.println("\n메뉴에 나와있는 명령어를 입력해주세요:( (help 입력해주세요~)");
				break;
			}	
		} while (!quit);
		sc.close();
	}
}
