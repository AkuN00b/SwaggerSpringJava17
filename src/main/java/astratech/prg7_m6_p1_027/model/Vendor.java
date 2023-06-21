package astratech.prg7_m6_p1_027.model;
import javax.persistence.*;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idVendor;

    @Column(name = "nama")
    private String namaVendor;

    public Vendor() {
    }

    public Vendor(Integer idVendor, String namaVendor) {
        this.idVendor = idVendor;
        this.namaVendor = namaVendor;
    }

    public Integer getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(Integer idVendor) {
        this.idVendor = idVendor;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }
}
