package DanhSach;

import Entity.NhanVien;
import java.io.*;
import java.util.Scanner;

public class DSNhanVien {

    private NhanVien[] ds = new NhanVien[1000]; // mảng đủ lớn
    private int n = 0;
    private final Scanner sc = new Scanner(System.in);

    // ================= THEM =================
    public void them() {
        System.out.print("Nhap so luong nhan vien can them: ");
        int sl = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < sl; i++) {
            System.out.println("---- Nhap nhan vien thu " + (i + 1) + " ----");
            NhanVien nv = new NhanVien();
            nv.nhap(sc);
            ds[n++] = nv;
        }
        
    }

    // ================= XUAT =================
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }
        System.out.println("==== DANH SACH NHAN VIEN ====");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
        }
    }

    // ================= HAM KIEM TRA "CHUA" CHUOI =================
    private boolean match(String src, String key) {
        return src.toLowerCase().contains(key.toLowerCase());
    }

    // ================= TIM KIEM GAN DUNG =================
    public void timKiem() {
        System.out.print("Nhap tu khoa tim kiem (ma hoac ten): ");
        String key = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (match(ds[i].getMaNhanVien(), key) ||
                match(ds[i].getHoTen(), key)) {

                ds[i].xuat();
                found = true;
            }
        }

        if (!found) System.out.println("Khong tim thay nhan vien phu hop!");
    }

    // ================= SUA THEO MA =================
    public void suaTheoMa() {
        System.out.print("Nhap ma nhan vien can sua: ");
        String key = sc.nextLine().trim();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNhanVien().equalsIgnoreCase(key)) {
                System.out.println("Nhap lai thong tin nhan vien:");
                ds[i].nhap(sc);
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien!");
    }

    // ================= XOA THEO MA =================
    public void xoaTheoMa() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String key = sc.nextLine().trim();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNhanVien().equals(key)) {
                // Dời các phần tử phía sau sang trái
            for (int j = i; j < n - 1; j++) {
                ds[j] = ds[j + 1];
            }
            ds[n - 1] = null; // Xóa tham chiếu cuối cùng
            n--; // Giảm số lượng nhân viên
                System.out.println("Da xoa nhan vien!");
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien!");
    }

    // ================= DOC FILE =================
    public void docFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            n = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length < 5) continue;
                if (p[0].equalsIgnoreCase("maNhanVien")) continue;

                String ma = p[0].trim();
                String ten = p[1].trim();
                String ns;
                double luong;
                String sdt;

                try {
                    if (p.length >= 6) { // Format cũ
                        ns    = p[2].trim();
                        luong = Double.parseDouble(p[4].trim());
                        sdt   = p[5].trim();
                    } else {            // Format mới
                        ns    = p[2].trim();
                        luong = Double.parseDouble(p[3].trim());
                        sdt   = p[4].trim();
                    }
                } catch (NumberFormatException e) {
                    continue; // bỏ dòng lỗi
                }

                ds[n++] = new NhanVien(ma, ten, ns, luong, sdt);
            }
            System.out.println("Doc file NhanVien thanh cong!");
        } catch (IOException e) {
            System.out.println("Khong tim thay file NhanVien (bat dau tu danh sach rong).");
        }
    }

    // ================= GHI FILE =================
    public void ghiFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < n; i++) {
                NhanVien nv = ds[i];
                String line = nv.getMaNhanVien() + ";" +
                              nv.getHoTen()       + ";" +
                              nv.getNgaySinh()    + ";" +
                              nv.getLuongThang()  + ";" +
                              nv.getSoDienThoai();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Ghi file NhanVien thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file NhanVien: " + e.getMessage());
        }
    }

    // ================= THONG KE =================
    public void thongKeSoLuongNhanVien() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        System.out.println("=== DANH SACH NHAN VIEN ===");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
        }

        System.out.println("--------------------------------");
        System.out.println("Tong so nhan vien: " + n);
    }

    public void thongKeTongLuong() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        System.out.println("=== THONG KE LUONG NHAN VIEN ===");
        double tong = 0;
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
            tong += ds[i].getLuongThang();
        }
        System.out.println("--------------------------------");
        System.out.println("Tong so nhan vien: " + n);
        System.out.println("Tong luong thang: " + tong);
    }

    public void thongKeLuongTrungBinh() {
        if (n == 0) {
            System.out.println("Danh sach nhan vien rong!");
            return;
        }

        System.out.println("=== THONG KE LUONG TRUNG BINH ===");
        double tong = 0;
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
            tong += ds[i].getLuongThang();
        }
        double tb = tong / n;
        System.out.println("--------------------------------");
        System.out.println("Tong luong thang: " + tong);
        System.out.println("Luong trung binh: " + tb);
    }
}
