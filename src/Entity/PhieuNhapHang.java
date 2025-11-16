package Entity;

import java.util.Scanner;

public class PhieuNhapHang {
    private String maphieunhap;
    private String manhanvien;  
    private String mancc;       
    private String ngaynhap;
    private double tongtien;

    public PhieuNhapHang() {}

    public PhieuNhapHang(String maPhieuNhap, String maNhanVien,
                         String maNCC, String ngayNhap, double tongTien) {
        this.maphieunhap = maPhieuNhap;
        this.manhanvien = maNhanVien;
        this.mancc = maNCC;
        this.ngaynhap = ngayNhap;
        this.tongtien = tongTien;
    }

    // Getter / Setter 
    public String getMaPhieuNhap() {
        return maphieunhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maphieunhap = maPhieuNhap;
    }

    public String getMaNhanVien() {
        return manhanvien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.manhanvien = maNhanVien;
    }

    public String getMaNCC() {
        return mancc;
    }

    public void setMaNCC(String maNCC) {
        this.mancc = maNCC;
    }

    public String getNgayNhap() {
        return ngaynhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngaynhap = ngayNhap;
    }

    public double getTongTien() {
        return tongtien;
    }

    public void setTongTien(double tongTien) {
        this.tongtien = tongTien;
    }

    // Nhập / xuất
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        maphieunhap = sc.nextLine().trim();

        System.out.print("Nhap ma nhan vien: ");
        manhanvien = sc.nextLine().trim();

        System.out.print("Nhap ma nha cung cap: ");
        mancc = sc.nextLine().trim();

        System.out.print("Nhap ngay nhap: ");
        ngaynhap = sc.nextLine().trim();

        System.out.print("Nhap tong tien: ");
        tongtien = Double.parseDouble(sc.nextLine().trim());
    }

    public void xuat() {
        System.out.println("Ma PNH: " + maphieunhap
                + " | Ma NV: " + manhanvien
                + " | Ma NCC: " + mancc
                + " | Ngay nhap: " + ngaynhap
                + " | Tong tien: " + tongtien);
    }
}
