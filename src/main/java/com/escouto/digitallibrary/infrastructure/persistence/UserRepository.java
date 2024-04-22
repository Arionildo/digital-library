package com.escouto.digitallibrary.infrastructure.persistence;

import com.escouto.digitallibrary.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
