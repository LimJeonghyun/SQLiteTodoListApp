package com.todo.menu;
public class Menu {

    public static void prompt()
    {
        System.out.println();
        System.out.println("---<명령어 설명서>---");
        System.out.println("add - 목록 추가");
        System.out.println("del - 목록 삭제");
        System.out.println("edit - 목록 수정");
        System.out.println("ls - 목록 모두 보이기");
        System.out.println("ls_name_asc - 이름 순으로 정렬");
        System.out.println("ls_name_desc - 이름 역순으로 정렬");
        System.out.println("ls_date - 날짜 순으로 정렬");
        System.out.println("help - 명령어 도우미");
        System.out.println("exit - 종료");
        System.out.print("메뉴를 선택해주세요 > ");
    }

    public static void displaymenu(){
        System.out.println();
        System.out.println("---<아래의 명령어를 입력해주세요>---");
        System.out.println("add");
        System.out.println("del");
        System.out.println("edit");
        System.out.println("ls");
        System.out.println("ls_name_asc");
        System.out.println("ls_name_desc");
        System.out.println("ls_date");
        System.out.println("help");
        System.out.println("exit");
        System.out.print("메뉴를 선택해주세요 > ");
    }
}
