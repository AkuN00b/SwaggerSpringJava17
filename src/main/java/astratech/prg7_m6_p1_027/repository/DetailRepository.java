package astratech.prg7_m6_p1_027.repository;

import astratech.prg7_m6_p1_027.model.Detail;
import astratech.prg7_m6_p1_027.model.Pembelian;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetailRepository extends CrudRepository<Detail, Integer> {
    @Query("SELECT d FROM Detail d WHERE d.idPembelian =: idPembelian")
    List<Detail> getDetailByIdPembelian(Pembelian idPembelian);
}
