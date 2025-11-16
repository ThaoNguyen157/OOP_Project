package DanhSach;

import Entity.LoaiSP;
import java.io.*;
import java.util.*;

public class DSLoaiSP {

    private LoaiSP[] ds = new LoaiSP[1000];  // mảng đủ lớn
    private int n = 0;
    private final Scanner sc = new Scanner(System.in);

    // ================= THEM =================
    public void them() {
        System.out.print("Nhap so luong loai san pham can them: ");
         int sl = sc.nextInt();
         sc.nextLine();

        for (int i = 0; i < sl; i++) {
            System.out.println("---- Loai SP thu " + (i + 1) + " ----");
            LoaiSP loai = new LoaiSP();
            loai.nhap(sc);
            ds[n++] = loai;
        }
    }

    // ================= XUAT =================
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach loai san pham rong!");
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            ds[i].xuat();
        }
    }

    // ================= TIM =================
    private int timTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaLoai().equalsIgnoreCase(ma)) return i;
        }
        return -1;
    }

    public void timKiemTheoMa() {
        System.out.print("Nhap ma loai can tim: ");
        int idx = timTheoMa(sc.nextLine());
        if (idx == -1) System.out.println("Khong tim thay!");
        else ds[idx].xuat();
    }

    // ================= SUA =================
    public void suaTheoMa() {
        System.out.print("Nhap ma loai can sua: ");
        String ma = sc.nextLine();
        int idx = timTheoMa(ma);

        if (idx == -1) {
            System.out.println("Khong tim thay!");
            return;
        }

        LoaiSP loai = ds[idx];
        int chon;

        do {
            System.out.println("\n--- THONG TIN LOAI DANG SUA ---");
            loai.xuat();

            System.out.println("Ban muon sua gi?");
            System.out.println("1. Sua MA LOAI");
            System.out.println("2. Sua TEN LOAI");
            System.out.println("0. Thoat sua");
            System.out.print("Nhap lua chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1:
                    System.out.print("Nhap MA LOAI moi: ");
                    String maMoi = sc.nextLine();
                    loai.setMaLoai(maMoi);
                    System.out.println(">> Da cap nhat MA LOAI!");
                    break;

                case 2:
                    System.out.print("Nhap TEN LOAI moi: ");
                    String tenMoi = sc.nextLine();
                    loai.setTenLoai(tenMoi);
                    System.out.println(">> Da cap nhat TEN LOAI!");
                    break;

                case 0:
                    System.out.println("Thoat sua loai san pham.");
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
    }


    // ================= XOA =================
    public void xoaTheoMa() {
        System.out.print("Nhap ma loai can xoa: ");
        int idx = timTheoMa(sc.nextLine());
        if (idx == -1) {
            System.out.println("Khong tim thay!");
            return;
        }
        System.arraycopy(ds, idx + 1, ds, idx, n - idx - 1);
        n--;
        System.out.println("Da xoa!");
    }

    // ================= DOC FILE =================
    public void docFile(String filename) {
        try {
            try (DataInputStream in = new DataInputStream(
                new FileInputStream(filename)
            )) {
                n = 0;

                while (true) {
                    String ma = in.readUTF();
                    String ten = in.readUTF();
                    ds[n++] = new LoaiSP(ma, ten);
                }
            }

        } catch (EOFException e) {
            System.out.println("Doc file LOAISP thanh cong!");

        } catch (IOException e) {
            System.out.println("Khong the doc file!");
        }
    }


    // ================= GHI FILE =================
    public void ghiFile(String filename) {
        try {
            DataOutputStream out = new DataOutputStream(
                new FileOutputStream(filename, true) // ghi thêm vào cuối file
            );

            for (int i = 0; i < n; i++) {
                out.writeUTF(ds[i].getMaLoai());
                out.writeUTF(ds[i].getTenLoai());
            }

            out.close();
            System.out.println("Ghi file thanh cong!");

        } catch (IOException e) {
            System.out.println("Ghi file that bai!");
        }
    }

}
