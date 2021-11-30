package Model.CustomTable;

import DAO.implement.PhongBan;
import DAO.implement.ChucVu;
import Model.NhanSuHoSoModel;
import Model.NhanSuModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Iroha
 */
public class HoSoTableModel extends AbstractTableModel{

    private String name[] = {"Mã", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Quê quán", "Dân tộc", "Trình độ", "Chuyên ngành", "Phòng ban", "Chức vụ"};
    private Class classes[] = {String.class, String.class, String.class, String.class, String.class, String.class ,String.class, String.class, String.class, String.class, String.class};
    
    ArrayList<NhanSuHoSoModel> dsHoSo = new ArrayList<>();
    
    public HoSoTableModel(List<NhanSuModel> list) {
        for(int i = 0; i< list.size();i++)
        {
            NhanSuHoSoModel a =  new NhanSuHoSoModel(list.get(i));
            this.dsHoSo.set(i, a);
        }
    }
    
    public HoSoTableModel(ArrayList<NhanSuHoSoModel> ls){
        this.dsHoSo = ls;
    }
    
    public HoSoTableModel() {
        
    }
    
    @Override
    public int getRowCount() {
        return dsHoSo.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return dsHoSo.get(rowIndex).getMaNS();
            case 1: return dsHoSo.get(rowIndex).getHoTen();
            case 2: return dsHoSo.get(rowIndex).getNgaySinh();
            case 3: return dsHoSo.get(rowIndex).getGioiTinh();
            case 4: return dsHoSo.get(rowIndex).getSoDienThoai();
            case 5: return dsHoSo.get(rowIndex).getQueQuan();
            case 6: return dsHoSo.get(rowIndex).getDanToc();
            case 7: return dsHoSo.get(rowIndex).getTrinhDoHocVan();
            case 8: return dsHoSo.get(rowIndex).getChuyenNganh();
            case 9: return dsHoSo.get(rowIndex).getPhongBan();
            case 10: return dsHoSo.get(rowIndex).getChucVu();
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
