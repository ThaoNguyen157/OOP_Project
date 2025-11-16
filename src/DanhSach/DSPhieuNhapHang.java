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
    private boolean match(String src, String key) {
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

        // Tìm vị trí phiếu trong mảng
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaPhieuNhap().equalsIgnoreCase(key)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Khong tim thay phieu nhap!");
            return;
        }

        PhieuNhapHang p = ds[idx];
        int chon;

        do {
            System.out.println("\n--- THONG TIN PHIEU NHAP DANG SUA ---");
            p.xuat();

            System.out.println("Ban muon sua gi?");
            System.out.println("1. Sua MA PHIEU NHAP");
            System.out.println("2. Sua MA NHAN VIEN");
            System.out.println("3. Sua MA NHA CUNG CAP");
            System.out.println("4. Sua NGAY NHAP");
            System.out.println("5. Sua TONG TIEN");
            System.out.println("0. Thoat sua");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1:
                    System.out.print("Nhap MA PHIEU NHAP moi: ");
                    String maMoi = sc.nextLine().trim();
                    p.setMaPhieuNhap(maMoi);
                    System.out.println(">> Da cap nhat MA PHIEU NHAP!");
                    break;

                case 2:
                    System.out.print("Nhap MA NHAN VIEN moi: ");
                    String maNVMoi = sc.nextLine().trim();
                    p.setMaNhanVien(maNVMoi);
                    System.out.println(">> Da cap nhat MA NHAN VIEN!");
                    break;

                case 3:
                    System.out.print("Nhap MA NHA CUNG CAP moi: ");
                    String maNCCMoi = sc.nextLine().trim();
                    p.setMaNCC(maNCCMoi);
                    System.out.println(">> Da cap nhat MA NHA CUNG CAP!");
                    break;

                case 4:
                    System.out.print("Nhap NGAY NHAP moi: ");
                    String ngayMoi = sc.nextLine().trim();
                    p.setNgayNhap(ngayMoi);
                    System.out.println(">> Da cap nhat NGAY NHAP!");
                    break;

                case 5:
                    System.out.print("Nhap TONG TIEN moi: ");
                    String tienStr = sc.nextLine().trim();
                    try {
                        double tienMoi = Double.parseDouble(tienStr);
                        p.setTongTien(tienMoi);
                        System.out.println(">> Da cap nhat TONG TIEN!");
                    } catch (NumberFormatException e) {
                        System.out.println(">>> TONG TIEN khong hop le, giu nguyen gia tri cu!");
                    }
                    break;

                case 0:
                    System.out.println("Thoat sua PHIEU NHAP HANG.");
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
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

    // ================== FILE (DataInputStream / DataOutputStream) ==================
    public void docFile(String filename) {
        n = 0;
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {

            while (true) {
                String maPnh = in.readUTF();
                String maNv  = in.readUTF();
                String maNcc = in.readUTF();
                String ngay  = in.readUTF();
                double tong  = in.readDouble();

                ds[n++] = new PhieuNhapHang(maPnh, maNv, maNcc, ngay, tong);
            }

        } catch (EOFException e) {
            // đọc hết file
            System.out.println("Doc file PNH thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file PNH (bat dau danh sach rong).");
        } catch (IOException e) {
            System.out.println("Loi khi doc file PNH: " + e.getMessage());
        }
    }

    public void ghiFile(String filename) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {

            for (int i = 0; i < n; i++) {
                PhieuNhapHang p = ds[i];
                out.writeUTF(p.getMaPhieuNhap());
                out.writeUTF(p.getMaNhanVien());
                out.writeUTF(p.getMaNCC());
                out.writeUTF(p.getNgayNhap());
                out.writeDouble(p.getTongTien());
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
