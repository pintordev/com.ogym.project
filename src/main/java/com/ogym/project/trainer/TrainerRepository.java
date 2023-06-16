package com.ogym.project.trainer;

import com.ogym.project.trainerField.TrainerField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<TrainerField> findByOrderByIdAsc();
    TrainerField findByName(String name);
}
