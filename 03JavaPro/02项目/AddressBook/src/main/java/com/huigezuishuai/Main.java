package com.huigezuishuai;

import java.util.Scanner;

/**
 * 如果你把每一天都当作生命中最后一天去生活的话，那么有一天你会发现你是正确的!
 *
 * @author fuxiaohui
 * @create 2017/12/21
 */
public class Main {
    public static void main(String[] args) {
        // 手机通讯录
        // 1.分组管理
        // 2.添加联系人, 放到对应分组中; 如果分组不存在, 生成对应分组
        // 3.删除联系人; 如果是分组中的最后一个人, 删除整个分组
        // 4.查找联系人
        // 5.编辑联系人

        System.out.println("欢迎进入辉哥最帅手机通讯录!");

        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        boolean isLoop = true;
        while (isLoop) {
            System.out.println("1.查看所有联系人");
            System.out.println("2.查找联系人");
            System.out.println("3.添加联系人");
            System.out.println("4.修改联系人");
            System.out.println("5.删除联系人");
            System.out.println("6.退出");
            System.out.println("请选择:");

            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    addressBook.showAllContacts();
                    break;
                case 2:
                    System.out.println("请输入查询的内容:");
                    String content = scanner.next();
                    addressBook.findContact(content);
                    break;
                case 3:
                    System.out.println("请输入联系人姓名:");
                    String name = scanner.next();

                    System.out.println("请输入联系人电话");
                    String phone = scanner.next();

                    addressBook.addContact(name, phone);
                    break;
                case 4:
                    System.out.println("请输入分组名:");
                    String group1 = scanner.next();
                    System.out.println("请输入联系人编号:");
                    int index1 = scanner.nextInt();
                    System.out.println("请输入联系人姓名:");
                    String name1 = scanner.next();

                    System.out.println("请输入联系人电话");
                    String phone1 = scanner.next();
                    addressBook.updateContact(group1, index1, name1, phone1);
                    break;
                case 5:
                    System.out.println("请输入分组名:");
                    String group = scanner.next();
                    System.out.println("请输入联系人编号:");
                    int index = scanner.nextInt();
                    addressBook.removeContact(group, index);
                    break;
                case 6:
                    isLoop = false;
                    System.out.println("已退出!");
                    break;
                default:
                    break;
            }
        }
    }
}
