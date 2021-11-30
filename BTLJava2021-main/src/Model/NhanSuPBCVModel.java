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
public class NhanSuPBCVModel {
    String maNS, tenNS, maPB, maCV;

    public NhanSuPBCVModel() {
    }

    public NhanSuPBCVModel(String maNS, String tenNS, String maPB, String maCV) {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.maPB = maPB;
        this.maCV = maCV;
    }

    public String getMaNS() {
        return maNS;
    }

    public String getTenNS() {
        return tenNS;
    }

    public void setMaNS(String maNS) {
        this.maNS = maNS;
    }

    public void setTenNS(String tenNS) {
        this.tenNS = tenNS;
    }

    public String getMaPB() {
        return maPB;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }
    
    
    
}
