package com.example.application;

import io.swagger.v3.oas.models.links.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
