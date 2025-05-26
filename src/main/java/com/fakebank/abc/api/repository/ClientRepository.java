package com.fakebank.abc.api.repository;

import com.fakebank.abc.api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}