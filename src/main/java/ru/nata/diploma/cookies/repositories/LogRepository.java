package ru.nata.diploma.cookies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nata.diploma.cookies.models.Log;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
    List<Log> findAllByProcessId(String processId);
}
