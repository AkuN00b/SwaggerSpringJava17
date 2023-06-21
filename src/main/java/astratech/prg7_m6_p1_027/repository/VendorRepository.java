package astratech.prg7_m6_p1_027.repository;

import astratech.prg7_m6_p1_027.model.Vendor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
    @Query("SELECT v FROM Vendor v WHERE v.idVendor =:idvendor")
    public Vendor getVendorByIdVendor(@Param("idvendor") Integer idvendor);

    List<Vendor> findAll();
}
