package astratech.prg7_m6_p1_027.repository;

import astratech.prg7_m6_p1_027.model.Helm;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HelmRepository extends CrudRepository<Helm, Integer> {
    @Query("SELECT h FROM Helm h WHERE h.idHelm =:idhelm")
    public Helm getHelmByIdHelm(@Param("idhelm") Integer idhelm);

    List<Helm> findAll();
}
