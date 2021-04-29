package ru.nata.diploma.cookies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nata.diploma.cookies.models.HardwareParameters;

@Repository
public interface HardwareParametersRepository extends CrudRepository<HardwareParameters, Long> {
}
