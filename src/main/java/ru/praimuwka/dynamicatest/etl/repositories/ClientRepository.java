package ru.praimuwka.dynamicatest.etl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.praimuwka.dynamicatest.etl.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}