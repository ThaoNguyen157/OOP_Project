package DanhSach;

import Entity.NhaCungCap;
import java.io.*;
import java.util.Scanner;

public class DSNhaCungCap {

    private NhaCungCap[] ds = new NhaCungCap[1000]; // mảng đủ lớn
    private int n = 0;
    private final Scanner sc = new Scanner(System.in);

    // ================= THEM =================
    public void them() {
        System.out.print("Nhap so luong NCC can them: ");
         int sl = sc.nextInt();
         sc.nextLine();

        for (int i = 0; i < sl; i++) {
            System.out.println("---- Nhap NCC thu " + (i + 1) + " ----");
            NhaCungCap ncc = new NhaCungCap();
            ncc.nhap(sc);
            ds[n++] = ncc;
        }
    }

    // ================= XUAT =================
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach NCC rong!");
            return;
        }
        System.out.println("==== DANH SACH NHA CUNG CAP ====");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
        }
    }

    // ================= HAM SO KHOP CHUOI =================
    private boolean match(String src, String key) {
        return src.toLowerCase().contains(key.toLowerCase());
    }

    // ================= SUA THEO MA =================
    public void suaTheoMa() {
        System.out.print("Nhap ma NCC can sua: ");
        String key = sc.nextLine().trim();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(key)) {
                System.out.println("Nhap lai thong tin NCC:");
                ds[i].nhap(sc);
                return;
            }
        }
        System.out.println("Khong tim thay NCC phu hop!");
    }

    // ================= XOA THEO MA =================
    public void xoaTheoMa() {
        System.out.print("Nhap ma NCC can xoa: ");
        String key = sc.nextLine().trim();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(key)) {
                for (int j = i; j < n - 1; j++) {
                ds[j] = ds[j + 1];
            }
            ds[n - 1] = null; // Xóa tham chiếu cuối cùng
            n--; 
                System.out.println("Da xoa NCC!");
                return;
            }
        }
        System.out.println("Khong tim thay NCC phu hop!");
    }

    // ================= TIM KIEM NÂNG CAO =================
    public void timKiem() {
        System.out.print("Nhap tu khoa (ma, ten, dia chi): ");
        String key = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (match(ds[i].getMaNCC(), key) ||
                match(ds[i].getTenNCC(), key) ||
                match(ds[i].getDiaChi(), key)) {

                ds[i].xuat();
                found = true;
            }
        }
        if (!found) System.out.println("Khong tim thay NCC nao phu hop!");
    }

    // ================= DOC FILE =================
    public void docFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            n = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length >= 4) {
                    ds[n++] = new NhaCungCap(p[0], p[1], p[2], p[3]);
                }
            }
            System.out.println("Doc file NCC thanh cong!");
        } catch (IOException e) {
            System.out.println("Khong tim thay file NCC (bat dau tu danh sach rong).");
        }
    }

    // ================= GHI FILE =================
    public void ghiFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < n; i++) {
                bw.write(ds[i].getMaNCC() + ";" 
                        + ds[i].getTenNCC() + ";" 
                        + ds[i].getSoDienThoai() + ";" 
                        + ds[i].getDiaChi());
                bw.newLine();
            }
            System.out.println("Ghi file NCC thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file NCC: " + e.getMessage());
        }
    }

    // ================= THONG KE =================
    public void thongKeSoLuongNCC() {
        if (n == 0) {
            System.out.println("Danh sach NCC rong!");
            return;
        }
        System.out.println("=== DANH SACH TAT CA NHA CUNG CAP ===");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
        }
        System.out.println("--------------------------------");
        System.out.println("Tong so NHA CUNG CAP: " + n);
    }

    public void thongKeNCCTheoDiaChi() {
        if (n == 0) {
            System.out.println("Danh sach NHA CUNG CAP rong!");
            return;
        }

        System.out.print("Nhap tu khoa dia chi (VD: 'Ha Noi', 'HCM', 'Binh Thanh'): ");
        String key = sc.nextLine().trim();

        System.out.println("=== DANH SACH NCC CO DIA CHI CHUA '" + key + "' ===");
        int dem = 0;
        for (int i = 0; i < n; i++) {
            if (match(ds[i].getDiaChi(), key)) {
                System.out.print((dem + 1) + ". ");
                ds[i].xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("Khong co NCC nao o khu vuc nay.");
            return;
        }
        System.out.println("--------------------------------");
        System.out.println("Tong NCC trong khu vuc '" + key + "': " + dem);
        
    }
}
