package com.example.fbase;

public class MainModel {

    String ten, tenThuong,dacTinh, mauLa, anh;

    MainModel(){

    }

    public MainModel(String ten, String tenThuong, String dacTinh, String mauLa, String anh) {
        this.ten = ten;
        this.tenThuong = tenThuong;
        this.dacTinh = dacTinh;
        this.mauLa = mauLa;
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenThuong() {
        return tenThuong;
    }

    public void setTenThuong(String tenThuong) {
        this.tenThuong = tenThuong;
    }

    public String getDacTinh() {
        return dacTinh;
    }

    public void setDacTinh(String dacTinh) {
        this.dacTinh = dacTinh;
    }

    public String getMauLa() {
        return mauLa;
    }

    public void setMauLa(String mauLa) {
        this.mauLa = mauLa;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
