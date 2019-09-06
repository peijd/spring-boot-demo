package com.ripjava.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ripjava.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
