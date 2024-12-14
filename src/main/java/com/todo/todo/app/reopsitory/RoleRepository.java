package com.todo.todo.app.reopsitory;

import com.todo.todo.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role ,Long> {

    Set<Role> findByName(String user);
}
