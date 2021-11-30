/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CustomTable;

import Model.AccountModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iroha
 */
public class TaiKhoanTableModel extends AbstractTableModel{
    private String name[] = {"Tên tài khoản", "Họ và tên"};
    private Class classes[] = {String.class, String.class};
    ArrayList<AccountModel> ds;

    public TaiKhoanTableModel() {
    }
    
    public TaiKhoanTableModel(List<AccountModel> ls) {
        ds = new ArrayList<>(ls);
    }
    
    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return classes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return String.valueOf(ds.get(rowIndex).getUsername());
            case 1: return String.valueOf(ds.get(rowIndex).getTen());
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }
    
}
