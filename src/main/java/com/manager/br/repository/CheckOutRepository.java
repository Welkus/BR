package com.manager.br.repository;

import com.manager.br.model.CheckOutModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutRepository extends JpaRepository<CheckOutModel, Long> {
}
