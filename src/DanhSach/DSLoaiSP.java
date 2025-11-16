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
        String ma = sc.nextLine().trim();
        int idx = timTheoMa(ma);

        if (idx == -1) {
            System.out.println("Khong tim thay!");
            return;
        }

        LoaiSP loai = ds[idx];

        System.out.println("=== THONG TIN HIEN TAI ===");
        loai.xuat();

        System.out.println("\nNhap thong tin moi (Enter de giu nguyen):");

        // ----- Sửa MA LOAI -----
        System.out.print("Ma loai moi (" + loai.getMaLoai() + "): ");
        String maMoi = sc.nextLine().trim();
        if (!maMoi.isEmpty()) loai.setMaLoai(maMoi);

        // ----- Sửa TEN LOAI -----
        System.out.print("Ten loai moi (" + loai.getTenLoai() + "): ");
        String tenMoi = sc.nextLine().trim();
        if (!tenMoi.isEmpty()) loai.setTenLoai(tenMoi);

        System.out.println(">> Da cap nhat loai san pham!");
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
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            n = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length >= 2) {
                    ds[n++] = new LoaiSP(p[0], p[1]);
                }
            }
            System.out.println("Doc file thanh cong!");
        } catch (Exception e) {
            System.out.println("Khong the doc file!");
        }
    }

    // ================= GHI FILE =================
    public void ghiFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < n; i++) {
                bw.write(ds[i].getMaLoai() + ";" + ds[i].getTenLoai());
                bw.newLine();
            }
            System.out.println("Ghi file thanh cong!");
        } catch (Exception e) {
            System.out.println("Ghi file that bai!");
        }
    }
}