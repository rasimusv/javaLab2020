package ru.itis.rasimusv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.rasimusv.models.MethodCall;

@Repository
public interface MethodCallLogRepository extends JpaRepository<MethodCall, Long> {
}
