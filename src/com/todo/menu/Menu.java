package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList ���� ��ɾ� ����>");
        System.out.println("add - �׸� �߰�");
        System.out.println("del - �׸� ����");
        System.out.println("edit - �׸� ����");
        System.out.println("ls - ��ü ���");
        System.out.println("find - ����� ���� Ű���� �˻�");
        System.out.println("find_cate - ī�װ� Ű���� �˻�");
        System.out.println("ls_name - ����� ����");
        System.out.println("ls_name_desc - ���񿪼� ����");
        System.out.println("ls_date - ��¥�� ����");
        System.out.println("ls_date_desc - ��¥���� ����");
        System.out.println("exit - ����");
    }
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
