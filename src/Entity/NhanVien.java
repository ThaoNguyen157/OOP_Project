package Entity;

import java.util.Scanner;

public class NhanVien {
    private String manhanvien;
    private String hoten;
    private String ngaysinh;
    private double luongthang;
    private String sodienthoai;

    public NhanVien() {}

    public NhanVien(String maNhanVien, String hoTen, String ngaySinh,
                    double luongThang, String soDienThoai) {
        this.manhanvien = maNhanVien;
        this.hoten = hoTen;
        this.ngaysinh = ngaySinh;
        this.luongthang = luongThang;
        this.sodienthoai = soDienThoai;
    }

    // ===== Getter / Setter =====
    public String getMaNhanVien() {
        return manhanvien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.manhanvien = maNhanVien;
    }

    public String getHoTen() {
        return hoten;
    }

    public void setHoTen(String hoTen) {
        this.hoten = hoTen;
    }

    public String getNgaySinh() {
        return ngaysinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaysinh = ngaySinh;
    }

    public double getLuongThang() {
        return luongthang;
    }

    public void setLuongThang(double luongThang) {
        this.luongthang = luongThang;
    }

    public String getSoDienThoai() {
        return sodienthoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.sodienthoai = soDienThoai;
    }

    // ===== Nhập / Xuất =====
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma nhan vien: ");
        manhanvien = sc.nextLine().trim();

        System.out.print("Nhap ho ten: ");
        hoten = sc.nextLine().trim();

        System.out.print("Nhap ngay sinh: ");
        ngaysinh = sc.nextLine().trim();

        System.out.print("Nhap luong thang: ");
        luongthang = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Nhap so dien thoai: ");
        sodienthoai = sc.nextLine().trim();
    }

    public void xuat() {
        System.out.println("Ma NV: " + manhanvien
                + " | Ho ten: " + hoten
                + " | Ngay sinh: " + ngaysinh
                + " | Luong: " + luongthang
                + " | SDT: " + sodienthoai);
    }
}
