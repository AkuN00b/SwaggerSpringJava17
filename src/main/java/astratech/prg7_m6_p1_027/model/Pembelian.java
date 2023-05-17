package astratech.prg7_m6_p1_027.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pembelian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_pembelian")
    private Integer idPembelian;

    @Column(name = "nama")
    private String nama;

    @Column(name = "tanggal")
    private Date tanggal;

    @Column(name = "total")
    private Integer total;

    public Pembelian(Integer idPembelian, String nama, Date tanggal, Integer total) {
        this.idPembelian = idPembelian;
        this.nama = nama;
        this.tanggal = tanggal;
        this.total = total;
    }

    public Pembelian() {

    }

    public Integer getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(Integer idPembelian) {
        this.idPembelian = idPembelian;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
