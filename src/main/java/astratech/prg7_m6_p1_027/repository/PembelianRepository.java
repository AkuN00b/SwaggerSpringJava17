package astratech.prg7_m6_p1_027.repository;

import astratech.prg7_m6_p1_027.model.Pembelian;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PembelianRepository extends CrudRepository<Pembelian, Integer> {
    @Query("SELECT p FROM Pembelian p WHERE p.idPembelian =:idpembelian")
    public Pembelian getPembelianByIdPembelian(@Param("idpembelian") Integer idpembelian);

    List<Pembelian> findAll();
}
