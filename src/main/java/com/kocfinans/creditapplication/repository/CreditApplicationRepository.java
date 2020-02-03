package com.kocfinans.creditapplication.repository;

import com.kocfinans.creditapplication.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditApplicationRepository extends JpaRepository<Applicant, Long> {
}
