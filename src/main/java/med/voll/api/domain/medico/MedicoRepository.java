package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository <Medico, Long>{
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
        select m from Medico m  -- Selecciona un médico (entidad Medico) desde la base de datos.
        where
        m.activo = 1           -- Filtra únicamente los médicos que están marcados como activos.
        and
        m.especialidad = :especialidad  -- Filtra los médicos que tienen la especialidad indicada como parámetro.
        and m.id not in (       -- Excluye médicos cuyo ID esté en la lista de:
            select c.medico.id from Consulta c  -- Selecciona los IDs de médicos que tienen consultas programadas.
            where
            c.fecha = :fecha    -- Filtra las consultas que coincidan con la fecha proporcionada como parámetro.
        )
        order by rand()         -- Ordena aleatoriamente los resultados para obtener uno de manera aleatoria.
        limit 1                 -- Limita el resultado a solo un médico.
        """)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);
