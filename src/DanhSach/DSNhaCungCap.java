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

        // Tìm vị trí NCC trong mảng
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(key)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Khong tim thay NCC phu hop!");
            return;
        }

        NhaCungCap ncc = ds[idx];
        int chon;

        do {
            System.out.println("\n--- THONG TIN NCC DANG SUA ---");
            ncc.xuat();

            System.out.println("Ban muon sua gi?");
            System.out.println("1. Sua MA NCC");
            System.out.println("2. Sua TEN NCC");
            System.out.println("3. Sua SO DIEN THOAI");
            System.out.println("4. Sua DIA CHI");
            System.out.println("0. Thoat sua");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1:
                    System.out.print("Nhap MA NCC moi: ");
                    String maMoi = sc.nextLine().trim();
                    ncc.setMaNCC(maMoi);
                    System.out.println(">> Da cap nhat MA NCC!");
                    break;

                case 2:
                    System.out.print("Nhap TEN NCC moi: ");
                    String tenMoi = sc.nextLine().trim();
                    ncc.setTenNCC(tenMoi);
                    System.out.println(">> Da cap nhat TEN NCC!");
                    break;

                case 3:
                    System.out.print("Nhap SO DIEN THOAI moi: ");
                    String sdtMoi = sc.nextLine().trim();
                    ncc.setSoDienThoai(sdtMoi);
                    System.out.println(">> Da cap nhat SO DIEN THOAI!");
                    break;

                case 4:
                    System.out.print("Nhap DIA CHI moi: ");
                    String diaChiMoi = sc.nextLine().trim();
                    ncc.setDiaChi(diaChiMoi);
                    System.out.println(">> Da cap nhat DIA CHI!");
                    break;

                case 0:
                    System.out.println("Thoat sua NHA CUNG CAP.");
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
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

    // ================= DOC FILE (DataInputStream) =================
    public void docFile(String filename) {
        n = 0;
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {

            while (true) {
                String ma  = in.readUTF();
                String ten = in.readUTF();
                String sdt = in.readUTF();
                String dia = in.readUTF();

                ds[n++] = new NhaCungCap(ma, ten, sdt, dia);
            }

        } catch (EOFException e) {
            // Đọc hết file
            System.out.println("Doc file NCC thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file NCC (bat dau tu danh sach rong).");
        } catch (IOException e) {
            System.out.println("Loi khi doc file NCC: " + e.getMessage());
        }
    }

    // ================= GHI FILE (DataOutputStream) =================
    public void ghiFile(String filename) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {

            for (int i = 0; i < n; i++) {
                out.writeUTF(ds[i].getMaNCC());
                out.writeUTF(ds[i].getTenNCC());
                out.writeUTF(ds[i].getSoDienThoai());
                out.writeUTF(ds[i].getDiaChi());
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
