package Entity;

import java.util.Scanner;

public class LoaiSP {
    private String maloai;
    private String tenloai;

    public LoaiSP() {}

    public LoaiSP(String maLoai, String tenLoai) {
        this.maloai = maLoai;
        this.tenloai = tenLoai;
    }

    public String getMaLoai() {
        return maloai;
    }

    public void setMaLoai(String maLoai) {
        this.maloai = maLoai;
    }

    public String getTenLoai() {
        return tenloai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenloai = tenLoai;
    }

    // ===== NHAP / XUAT =====
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma loai: ");
        maloai = sc.nextLine();
        System.out.print("Nhap ten loai: ");
        tenloai = sc.nextLine();
    }

    public void xuat() {
        System.out.println("Ma loai: " + maloai + " | Ten loai: " + tenloai);
    }
}
