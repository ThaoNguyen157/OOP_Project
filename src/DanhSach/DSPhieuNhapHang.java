package DanhSach;

import Entity.PhieuNhapHang;
import java.io.*;
import java.util.Scanner;

public class DSPhieuNhapHang {

    private PhieuNhapHang[] ds = new PhieuNhapHang[1000]; 
    private int n = 0; 
    private final Scanner sc = new Scanner(System.in); 
    // ================== THEM ==================
    public void them() { 
        System.out.print("Nhap so luong phieu nhap can them: "); 
        int sl = sc.nextInt(); 
        sc.nextLine(); 

        for (int i = 0; i < sl; i++) { 
            System.out.println("---- Nhap phieu nhap thu " + (i + 1) + " ----");
            PhieuNhapHang pnh = new PhieuNhapHang(); 
            pnh.nhap(sc); 
            ds[n++] = pnh;
        } 
    }

    // ================== XUAT ==================
    public void xuat() { 
        if (n == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }
        System.out.println("==== DANH SACH PHIEU NHAP HANG ====");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat(); 
        }
    }

    // ================== TIM KIEM GAN DUNG ==================
    private boolean match(String src, String key) { // trả về true hay false
        return src.toLowerCase().contains(key.toLowerCase());
    }

    public void timKiem() {
        System.out.print("Nhap tu khoa tim kiem (ma phieu / ma NCC / ma NV): ");
        String key = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (match(ds[i].getMaPhieuNhap(), key) ||
                match(ds[i].getMaNCC(), key) ||
                match(ds[i].getMaNhanVien(), key)) {

                ds[i].xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay phieu nhap phu hop!");
        }
    }

    // ================== SUA ==================
    public void suaTheoMa() {
            System.out.print("Nhap ma phieu nhap can sua: ");
            String key = sc.nextLine().trim();

            for (int i = 0; i < n; i++) {
                if (ds[i].getMaPhieuNhap().equalsIgnoreCase(key)) {
                    System.out.println("Nhap lai thong tin phieu nhap:");
                    ds[i].nhap(sc);
                    return;
                }
            }
            System.out.println("Khong tim thay phieu nhap!");
        }

    // ================== XOA ==================
    public void xoaTheoMa() {
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String key = sc.nextLine().trim();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaPhieuNhap().equalsIgnoreCase(key)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds[n - 1] = null; // Xóa tham chiếu cuối cùng
                n--; 
                System.out.println("Da xoa phieu nhap!");
                return;
            }
        }
        System.out.println("Khong tim thay phieu nhap!");
    }

    // ================== FILE ==================
    public void docFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            n = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length < 5) continue;

                String maPnh = p[0].trim();
                String maNv  = p[1].trim();
                String maNcc = p[2].trim();
                String ngay  = p[3].trim();
                double tong;

                try {
                    tong = Double.parseDouble(p[4].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                ds[n++] = new PhieuNhapHang(maPnh, maNv, maNcc, ngay, tong);
            }
            System.out.println("Doc file PNH thanh cong!");
        } catch (IOException e) {
            System.out.println("Khong tim thay file PNH (bat dau danh sach rong).");
        }
    }

    public void ghiFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < n; i++) {
                PhieuNhapHang p = ds[i];
                String line = p.getMaPhieuNhap() + ";" +
                              p.getMaNhanVien()   + ";" +
                              p.getMaNCC()        + ";" +
                              p.getNgayNhap()     + ";" +
                              p.getTongTien();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Ghi file PNH thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file PNH: " + e.getMessage());
        }
    }

    // ================== THONG KE ==================
    public void thongKeTongTienTatCa() {
        if (n == 0) {
            System.out.println("Danh sach phieu nhap rong!");
            return;
        }

        System.out.println("=== DANH SACH PHIEU NHAP (THONG KE TAT CA) ===");
        double tong = 0;

        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
            tong += ds[i].getTongTien();
        }

        System.out.println("--------------------------------------");
        System.out.println("Tong so phieu: " + n);
        System.out.println("Tong tien tat ca: " + tong);
        System.out.println("Tien trung binh moi phieu: " + (tong / n));
    }

    public void thongKeTheoNhaCungCap() {
        System.out.print("Nhap ma NHA CUNG CAP: ");
        String key = sc.nextLine().trim();

        double tong = 0;
        int dem = 0;

        System.out.println("=== DANH SACH PHIEU NHAP THEO NCC ===");

        for (int i = 0; i < n; i++) {
            if (match(ds[i].getMaNCC(), key)) {
                System.out.print((dem + 1) + ". ");
                ds[i].xuat();
                tong += ds[i].getTongTien();
                dem++;
            }
        }

        if (dem == 0) {
            System.out.println("Khong co phieu nhap nao cua NCC phu hop!");
            return;
        }

        System.out.println("--------------------------------------");
        System.out.println("So phieu nhap tim duoc: " + dem);
        System.out.println("Tong tien nhap tu NCC: " + tong);
        System.out.println("Trung binh moi phieu: " + (tong / dem));
    }

    public void thongKeTheoNhanVien() {
        System.out.print("Nhap ma NHAN VIEN: ");
        String key = sc.nextLine().trim();

        double tong = 0;
        int dem = 0;

        System.out.println("=== DANH SACH PHIEU NHAP THEO NHAN VIEN ===");

        for (int i = 0; i < n; i++) {
            if (match(ds[i].getMaNhanVien(), key)) {
                System.out.print((dem + 1) + ". ");
                ds[i].xuat();
                tong += ds[i].getTongTien();
                dem++;
            }
        }

        if (dem == 0) {
            System.out.println("Khong co phieu nhap nao do nhan vien nay lap!");
            return;
        }

        System.out.println("--------------------------------------");
        System.out.println("So phieu nhap tim duoc: " + dem);
        System.out.println("Tong tien nhap do NV lap: " + tong);
        System.out.println("Trung binh moi phieu: " + (tong / dem));
    }
}
