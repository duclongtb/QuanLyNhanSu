package Model;

/**
 *
 * @author Iroha
 */
public class BacLuongModel {
    private int bacLuong;
    private double heSoLuong;

    public BacLuongModel() {
    }

    public BacLuongModel(int bacLuong, double heSoLuong) {
        this.bacLuong = bacLuong;
        this.heSoLuong = heSoLuong;
    }

    public int getBacLuong() {
        return bacLuong;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setBacLuong(int bacLuong) {
        this.bacLuong = bacLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }
    
}
