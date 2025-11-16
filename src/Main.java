import DanhSach.DSNhaCungCap;
import DanhSach.DSNhanVien;
import DanhSach.DSPhieuNhapHang;
import DanhSach.DSLoaiSP;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DSNhanVien dsNV = new DSNhanVien();
        DSNhaCungCap dsNCC = new DSNhaCungCap();
        DSPhieuNhapHang dsPNH = new DSPhieuNhapHang();
        DSLoaiSP dsLoai = new DSLoaiSP();

        // Load file nếu có sẵn
        dsNV.docFile("../NhanVien.txt");
        dsNCC.docFile("../NhaCungCap.txt");
        dsPNH.docFile("../PhieuNhapHang.txt");
        dsLoai.docFile("../LoaiSP.txt");

        int chon;
        do {
            System.out.println("\n===== MENU CHINH =====");
            System.out.println("1. Quan ly NHAN VIEN");
            System.out.println("2. Quan ly NHA CUNG CAP");
            System.out.println("3. Quan ly PHIEU NHAP HANG");
            System.out.println("4. Quan ly LOAI SAN PHAM");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    menuNhanVien(dsNV, sc); break;
                case 2:
                    menuNCC(dsNCC, sc); break;
                case 3:
                    menuPNH(dsPNH, sc); break;
                case 4:
                    menuLoaiSP(dsLoai, sc); break;
                case 0:
                    System.out.println("Dang thoat va ghi file...");
                    dsNV.ghiFile("../NhanVien.txt");
                    dsNCC.ghiFile("../NhaCungCap.txt");
                    dsPNH.ghiFile("../PhieuNhapHang.txt");
                    dsLoai.ghiFile("../LoaiSP.txt");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);

        sc.close();
    }

    // ------------------ MENU NHAN VIEN ------------------
    private static void menuNhanVien(DSNhanVien ds, Scanner sc) {
        int chon;
        do {
            System.out.println("\n--- MENU NHAN VIEN ---");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Sua theo ma");
            System.out.println("4. Xoa theo ma");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke SO LUONG");
            System.out.println("7. Thong ke TONG LUONG");
            System.out.println("8. Thong ke LUONG TRUNG BINH");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    ds.them();
                    ds.ghiFile("../NhanVien.txt");   // AUTO SAVE
                    break;
                case 2: ds.xuat(); break;
                case 3:
                    ds.suaTheoMa();
                    ds.ghiFile("../NhanVien.txt");
                    break;
                case 4:
                    ds.xoaTheoMa();
                    ds.ghiFile("../NhanVien.txt");
                    break;
                case 5: ds.timKiem(); break;
                case 6: ds.thongKeSoLuongNhanVien(); break;
                case 7: ds.thongKeTongLuong(); break;
                case 8: ds.thongKeLuongTrungBinh(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
    }

    // ------------------ MENU NHA CUNG CAP ------------------
    private static void menuNCC(DSNhaCungCap ds, Scanner sc) {
        int chon;
        do {
            System.out.println("\n--- MENU NHA CUNG CAP ---");
            System.out.println("1. Them NCC");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Sua theo ma");
            System.out.println("4. Xoa theo ma");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke SO LUONG NCC");
            System.out.println("7. Thong ke THEO DIA CHI");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    ds.them();
                    ds.ghiFile("../NhaCungCap.txt");
                    break;
                case 2: ds.xuat(); break;
                case 3:
                    ds.suaTheoMa();
                    ds.ghiFile("../NhaCungCap.txt");
                    break;
                case 4:
                    ds.xoaTheoMa();
                    ds.ghiFile("../NhaCungCap.txt");
                    break;
                case 5: ds.timKiem(); break;
                case 6: ds.thongKeSoLuongNCC(); break;
                case 7: ds.thongKeNCCTheoDiaChi(); break;
                case 0: break;
                default: System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
    }

    // ------------------ MENU PHIEU NHAP HANG ------------------
    private static void menuPNH(DSPhieuNhapHang ds, Scanner sc) {
        int chon;
        do {
            System.out.println("\n--- MENU PHIEU NHAP HANG ---");
            System.out.println("1. Them phieu nhap");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Sua theo ma");
            System.out.println("4. Xoa theo ma");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke TONG TIEN tat ca");
            System.out.println("7. Thong ke THEO NHA CUNG CAP");
            System.out.println("8. Thong ke THEO NHAN VIEN");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    ds.them();
                    ds.ghiFile("../PhieuNhapHang.txt");
                    break;
                case 2: ds.xuat(); break;
                case 3:
                    ds.suaTheoMa();
                    ds.ghiFile("../PhieuNhapHang.txt");
                    break;
                case 4:
                    ds.xoaTheoMa();
                    ds.ghiFile("../PhieuNhapHang.txt");
                    break;
                case 5: ds.timKiem(); break;
                case 6: ds.thongKeTongTienTatCa(); break;
                case 7: ds.thongKeTheoNhaCungCap(); break;
                case 8: ds.thongKeTheoNhanVien(); break;
                case 0: break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (chon != 0);
    }

    // ------------------ MENU LOAI SAN PHAM ------------------
    private static void menuLoaiSP(DSLoaiSP ds, Scanner sc) {
        int chon;
        do {
            System.out.print("\n--- MENU LOAI SAN PHAM ---\n" +
                    "1. Them loai\n" +
                    "2. Xem danh sach\n" +
                    "3. Sua theo ma\n" +
                    "4. Xoa theo ma\n" +
                    "5. Tim kiem\n" +
                    "0. Quay lai\n" +
                    "Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    ds.them();
                    ds.ghiFile("../LoaiSP.txt");
                    break;
                case 2: ds.xuat(); break;
                case 3:
                    ds.suaTheoMa();
                    ds.ghiFile("../LoaiSP.txt");
                    break;
                case 4:
                    ds.xoaTheoMa();
                    ds.ghiFile("../LoaiSP.txt");
                    break;
                case 5: ds.timKiemTheoMa(); break;
                case 0: break;
                default: System.out.println("Nhap sai!");
            }

        } while (chon != 0);
    }
}
