package com.huigezuishuai;

/**
 * 如果你把每一天都当作生命中最后一天去生活的话，那么有一天你会发现你是正确的!
 *
 * @author fuxiaohui
 * @create 2017/12/21
 */
public class Contact {

    private String name;
    private String phone;

    public Contact() {
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
