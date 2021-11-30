/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.BacLuongModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public interface IBacLuong {
    public List<BacLuongModel> getBacLuong();
    public double getHeSoLuong(int bacLuong);
    public boolean CheckBacLuong(int bacLuong);
    public boolean ThemBacLuong(int bacLuong, double heSoLuong);
    public boolean XoaBacLuong(int bacLuong);
    public boolean SuaBacLuong(int bacLuong, double heSoLuong);
}
