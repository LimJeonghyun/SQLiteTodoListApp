package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		boolean call = false;
		boolean find = false;
		String key;
		TodoUtil.loadList(l, "todolist.txt");
		do {
			if(call == false) Menu.displaymenu();
			isList = false;
			String choice = sc.nextLine().trim();
			call = false;

			if (choice.indexOf("find")!=-1) {
				if (choice.indexOf("find_cate")!=-1) {
					key = choice.replace("find_cate ", "");
					choice = "find_cate";
					TodoUtil.findCategory(l, key);
				}
				else {
					key=choice.replace("find ", "");
					choice = "find";
					TodoUtil.findAll(l, key);
				}
				
			}
				
			// else if (choice.indexOf("find")!=-1) {
			// 	// find =true;
			// 	String key = choice.replace("find ", "");
			// 	TodoUtil.findAll(l, key);
			// 	choice = "find";
			// 	}
			// }
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				System.out.println("내용 추가를 완료했습니다!");
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				System.out.println("내용 수정을 완료했습니다!");
				break;
				
			case "ls":
				// l.listAll(l);
				isList=true;
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("이름 순으로 정렬하였습니다!");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("이름 역순으로 정렬하였습니다!");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜 순으로 정렬하였습니다!");
				isList = true;
				break;
			
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜 역순으로 정렬하였습니다!");
				isList = true;
				break;

			case "exit":
				System.out.println("종료합니다!");
				TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;
			
			case "help":
				Menu.prompt();
				call = true;
				break;

			case "find":
				break;
			
			case "find_cate":
				break;

			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			default:
				System.out.println("\n메뉴에 나와있는 명령어를 입력해주세요:( (help 입력해주세요~)");
				break;
			}
			
			if(isList) l.listAll();			
		} while (!quit);
	}
}
