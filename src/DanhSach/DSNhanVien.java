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

        // Tìm vị trí nhân viên trong mảng
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNhanVien().equalsIgnoreCase(key)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Khong tim thay nhan vien!");
            return;
        }

        NhanVien nv = ds[idx];
        int chon;

        do {
            System.out.println("\n--- THONG TIN NHAN VIEN DANG SUA ---");
            nv.xuat();

            System.out.println("Ban muon sua gi?");
            System.out.println("1. Sua MA NHAN VIEN");
            System.out.println("2. Sua HO TEN");
            System.out.println("3. Sua NGAY SINH");
            System.out.println("4. Sua LUONG THANG");
            System.out.println("5. Sua SO DIEN THOAI");
            System.out.println("0. Thoat sua");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1:
                    System.out.print("Nhap MA NHAN VIEN moi: ");
                    String maMoi = sc.nextLine().trim();
                    nv.setMaNhanVien(maMoi);
                    System.out.println(">> Da cap nhat MA NHAN VIEN!");
                    break;

                case 2:
                    System.out.print("Nhap HO TEN moi: ");
                    String tenMoi = sc.nextLine().trim();
                    nv.setHoTen(tenMoi);
                    System.out.println(">> Da cap nhat HO TEN!");
                    break;

                case 3:
                    System.out.print("Nhap NGAY SINH moi: ");
                    String nsMoi = sc.nextLine().trim();
                    nv.setNgaySinh(nsMoi);
                    System.out.println(">> Da cap nhat NGAY SINH!");
                    break;

                case 4:
                    System.out.print("Nhap LUONG THANG moi: ");
                    String luongStr = sc.nextLine().trim();
                    try {
                        double luongMoi = Double.parseDouble(luongStr);
                        nv.setLuongThang(luongMoi);
                        System.out.println(">> Da cap nhat LUONG THANG!");
                    } catch (NumberFormatException e) {
                        System.out.println(">>> LUONG khong hop le, giu nguyen gia tri cu!");
                    }
                    break;

                case 5:
                    System.out.print("Nhap SO DIEN THOAI moi: ");
                    String sdtMoi = sc.nextLine().trim();
                    nv.setSoDienThoai(sdtMoi);
                    System.out.println(">> Da cap nhat SO DIEN THOAI!");
                    break;

                case 0:
                    System.out.println("Thoat sua NHAN VIEN.");
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
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

    // ================= DOC FILE (DataInputStream) =================
    public void docFile(String filename) {
        n = 0;
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {

            while (true) {
                String ma  = in.readUTF();
                String ten = in.readUTF();
                String ns  = in.readUTF();
                double luong = in.readDouble();
                String sdt = in.readUTF();

                ds[n++] = new NhanVien(ma, ten, ns, luong, sdt);
            }

        } catch (EOFException e) {
            // đọc hết file
            System.out.println("Doc file NhanVien thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file NhanVien (bat dau tu danh sach rong).");
        } catch (IOException e) {
            System.out.println("Loi khi doc file NhanVien: " + e.getMessage());
        }
    }


    // ================= GHI FILE (DataOutputStream) =================
    public void ghiFile(String filename) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {

            for (int i = 0; i < n; i++) {
                NhanVien nv = ds[i];
                out.writeUTF(nv.getMaNhanVien());
                out.writeUTF(nv.getHoTen());
                out.writeUTF(nv.getNgaySinh());
                out.writeDouble(nv.getLuongThang());
                out.writeUTF(nv.getSoDienThoai());
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
