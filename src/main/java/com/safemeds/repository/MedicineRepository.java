package com.safemeds.repository;

import com.safemeds.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByUserId(Long userId);
    
    @Query("SELECT m FROM Medicine m WHERE m.user.id = ?1 AND m.expiryDate <= ?2")
    List<Medicine> findExpiredMedicines(Long userId, LocalDate date);
    
    @Query("SELECT m FROM Medicine m WHERE m.user.id = ?1 AND m.expiryDate <= ?2 AND m.expiryDate > ?3")
    List<Medicine> findExpiringSoonMedicines(Long userId, LocalDate date, LocalDate sevenDaysAgo);
} 