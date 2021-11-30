/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Iroha
 */
public class BangLuongModel {
    String maNS, hoTen, chucVu;
    double  namCongTac, heSoLuong, phuCap, thucLinh, thuongThamNien;

    public BangLuongModel() {
    }

    public BangLuongModel(String maNS, String hoTen, double namCongTac, String chucVu, double heSoLuong, double phuCap, double thucLinh, double thuongThamNien) {
        this.maNS = maNS;
        this.hoTen = hoTen;
        this.namCongTac = namCongTac;
        this.chucVu = chucVu;
        this.heSoLuong = heSoLuong;
        this.phuCap = phuCap;
        this.thucLinh = thucLinh;
        this.thuongThamNien = thuongThamNien;
    }

    public String getMaNS() {
        return maNS;
    }

    public void setMaNS(String maNS) {
        this.maNS = maNS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getNamCongTac() {
        return namCongTac;
    }

    public void setNamCongTac(double namCongTac) {
        this.namCongTac = namCongTac;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getThucLinh() {
        return thucLinh;
    }

    public void setThucLinh(double thucLinh) {
        this.thucLinh = thucLinh;
    }

    public double getThuongThamNien() {
        return thuongThamNien;
    }

    public void setThuongThamNien(double thuongThamNien) {
        this.thuongThamNien = thuongThamNien;
    }
    
}
