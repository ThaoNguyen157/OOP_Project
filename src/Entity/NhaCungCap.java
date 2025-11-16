package Entity;

import java.util.Scanner;

public class NhaCungCap {
    private String mancc;
    private String tenncc;
    private String sodienthoai;
    private String diachi;

    public NhaCungCap() {}

    public NhaCungCap(String maNCC, String tenNCC,
                      String soDienThoai, String diaChi) {
        this.mancc = maNCC;
        this.tenncc = tenNCC;
        this.sodienthoai = soDienThoai;
        this.diachi = diaChi;
    }

    // Getter / Setter (giữ nguyên tên phương thức)
    public String getMaNCC() {
        return mancc;
    }

    public void setMaNCC(String maNCC) {
        this.mancc = maNCC;
    }

    public String getTenNCC() {
        return tenncc;
    }

    public void setTenNCC(String tenNCC) {
        this.tenncc = tenNCC;
    }

    public String getSoDienThoai() {
        return sodienthoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.sodienthoai = soDienThoai;
    }

    public String getDiaChi() {
        return diachi;
    }

    public void setDiaChi(String diaChi) {
        this.diachi = diaChi;
    }

    // Nhập / xuất
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma NCC: ");
        mancc = sc.nextLine().trim();

        System.out.print("Nhap ten NCC: ");
        tenncc = sc.nextLine().trim();

        System.out.print("Nhap so dien thoai: ");
        sodienthoai = sc.nextLine().trim();

        System.out.print("Nhap dia chi: ");
        diachi = sc.nextLine().trim();
    }

    public void xuat() {
        System.out.println(
                "Ma NCC: " + mancc
                + " | Ten: " + tenncc
                + " | SDT: " + sodienthoai
                + " | Dia chi: " + diachi
        );
    }
}
