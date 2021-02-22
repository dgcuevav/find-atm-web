package com.inteligo.application.data.service;

import com.inteligo.application.data.entity.SamplePerson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Integer> {

}