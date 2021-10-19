package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		int id = 0;
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목추가]\n"
				+ "제목 > ");
		
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(id, title, desc, category, due_date);
		if(l.addItem(t)>0) {
			System.out.println("추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if (l.deleteItem(index)>0)
			System.out.println("삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		
		int new_int=0;
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_int, new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
		}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개)\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("모든 데이터가 저장되었습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int count = 0;
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				int id = 0;
				String title = st.nextToken();
				String description = st.nextToken();
				String current_date = st.nextToken();
				String due_date = st.nextToken();
				TodoItem item = new TodoItem(id, title, description, current_date, due_date);
				item.setCurrent_date(current_date);
				l.addItem(item);
				count++;
			}
			br.close();
			System.out.println(count+"개의 항목을 읽었습니다.");
		} catch (FileNotFoundException e) {
			//TODO Auto-generated catch block
			System.out.println(filename+" 파일이 없습니다.");
			//e.printStackTrace();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void findList(TodoList l, String keyword) {
		int count=0;
		for (TodoItem item : l.getList(keyword)) {
				System.out.println(item.toString());
				count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void listCateAll(TodoList l) {
		int count=0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}

	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
}
