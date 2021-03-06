package com.todo.menu;
public class Menu {

    public static void prompt()
    {
        System.out.println();
        System.out.println("---<명령어 설명서>---");
        System.out.println("add - 목록 추가");
        System.out.println("del - 목록 삭제");
        System.out.println("delA - 모든 목록 삭제");
        System.out.println("edit - 목록 수정");
        System.out.println("alarmOnOff - 중요한 할일 정렬 기능 키기/끄");
        System.out.println("update - 완료체크하");
        System.out.println("ls - 목록 모두 보이기");
        System.out.println("ls_name - 이름 순으로 정렬");
        System.out.println("ls_name_desc - 이름 역순으로 정렬");
        System.out.println("ls_date - 날짜 순으로 정렬");
        System.out.println("ls_date_desc - 날짜 역순으로 정렬");
        System.out.println("help - 명령어 도우미");
        System.out.println("find <keyWord> - 해당 키워드 찾기 (제목 및 내용 비교)");
        System.out.println("find_cate <keyWord> - 해당 키워드 찾기 (카테고리 비교) ");
        System.out.println("ls_cate - 등록되어 있는 카테고리 출력");
        System.out.println("exit - 종료");
    }

    public static void displaymenu(){
        System.out.println();
        System.out.println("---<아래의 명령어를 입력해주세요>---");
        System.out.println("add");
        System.out.println("del");
        System.out.println("delA");
        System.out.println("edit");
        System.out.println("alarmOnOff");
        System.out.println("update");
        System.out.println("ls");
        System.out.println("ls_name");
        System.out.println("ls_name_desc");
        System.out.println("ls_date");
        System.out.println("ls_date_desc");
        System.out.println("help");
        System.out.println("find <keyWord>");
        System.out.println("find_cate <keyWord>");
        System.out.println("ls_cate");
        System.out.println("exit");
        System.out.print("메뉴를 선택해주세요 > ");
    }
}
