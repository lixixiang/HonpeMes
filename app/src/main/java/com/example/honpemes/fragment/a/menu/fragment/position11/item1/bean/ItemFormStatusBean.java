package com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean;

/**
 * Created by Lixixiang on 2023/2/8 14:31
 */
public class ItemFormStatusBean {
    String _id;
    String name;
    String num;
    String sum;

    public ItemFormStatusBean(String _id, String name, String num, String sum) {
        this._id = _id;
        this.name = name;
        this.num = num;
        this.sum = sum;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
