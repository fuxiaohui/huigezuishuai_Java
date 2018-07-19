package com.huigezuishuai;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 如果你把每一天都当作生命中最后一天去生活的话，那么有一天你会发现你是正确的!
 *
 * @author fuxiaohui
 * @create 2017/12/21
 */
public class AddressBook {

    // 用于存储联系人
    private HashMap<String, ArrayList<Contact>> hashMap;
    // 注: 集合框架中类作为属性, 必须要手动初始化

    public AddressBook() {
        hashMap = new HashMap<>();

        /*
        String[] keys = new String[27];
        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            keys[i] = c + "";
        }
        keys[26] = "#";

        for (String key : keys) {
            ArrayList<Contact> arrayList = new ArrayList<>();
            hashMap.put(key, arrayList);
        }
        */
    }

    // 添加联系人
    public void addContact(String name, String phone) {
        // 创建联系人对象
        Contact contact = new Contact(name, phone);

        // 获取联系人姓名的首字母
        String firstLetter = toFirstChar(name).substring(0, 1).toUpperCase();

        // 判断分组名是否是字母
        char c = firstLetter.charAt(0);
        if (!(c >= 'A' && c <= 'Z')) {
            firstLetter = "#";
        }

        // 判断分组是否存
        if (hashMap.containsKey(firstLetter)) {
            // 分组已存在
            ArrayList<Contact> arrayList = hashMap.get(firstLetter);
            arrayList.add(contact);
        } else {
            // 分组不存在
            ArrayList<Contact> arrayList = new ArrayList<>();
            arrayList.add(contact);
            hashMap.put(firstLetter, arrayList);
        }
    }

    // 删除联系人
    public void removeContact(String group, int index) {
        if (hashMap.containsKey(group)) {
            ArrayList<Contact> contacts = hashMap.get(group);

            if (index >= 0 && index < contacts.size()) {
                if (contacts.size() == 1) {
                    hashMap.remove(group);
                } else {
                    contacts.remove(index);
                }
                System.out.println("删除成功!");
            } else {
                System.out.println("输入的下标有误!");
            }
        } else {
            System.out.println("输入的分组有误!");
        }
    }

    // 查找联系人
    public void findContact(String content) {

        boolean flag = true;

        // 遍历联系人
        for (String key : hashMap.keySet()) {
            for (Contact contact : hashMap.get(key)) {
                String name = contact.getName();
                if (name.contains(content)) {
                    System.out.println(contact);
                    flag = false;
                }
            }
        }

        if (flag) {
            System.out.println("查无此人!");
        }
    }

    // 修改联系人
    public void updateContact(String group, int index, String name, String phone) {
        removeContact(group, index);
        addContact(name, phone);
    }

    // 查看所有联系人
    public void showAllContacts() {
        /*
        for (String key : hashMap.keySet()) {
            System.out.println(key + "分组中联系人:");
            for (Contact contact : hashMap.get(key)) {
                System.out.println(contact);
            }
        }
        */

        // 按照A-Z#打印联系人
        String[] keys = new String[27];
        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            keys[i] = c + "";
        }
        keys[26] = "#";

        for (String key : keys) {
            if (hashMap.containsKey(key)) {
                System.out.println(key + "分组中联系人:");
                System.out.println("编号\t联系人信息");
                int i = 0;
                for (Contact contact : hashMap.get(key)) {
                    System.out.println(i + "\t" + contact);
                    i++;
                }
            }
        }
    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public String toFirstChar(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
}
