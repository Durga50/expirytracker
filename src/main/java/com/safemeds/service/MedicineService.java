package com.safemeds.service;

import com.safemeds.model.Medicine;
import com.safemeds.model.User;
import com.safemeds.repository.MedicineRepository;
import com.safemeds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Medicine addMedicine(Medicine medicine) {
        // Fetch and set the managed User entity
        Long userId = medicine.getUser().getId();
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        medicine.setUser(user);
        return medicineRepository.save(medicine);
    }
    
    public List<Medicine> getUserMedicines(Long userId) {
        return medicineRepository.findByUserId(userId);
    }
    
    public List<Medicine> getExpiredMedicines(Long userId) {
        return medicineRepository.findExpiredMedicines(userId, LocalDate.now());
    }
    
    public List<Medicine> getExpiringSoonMedicines(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(7);
        return medicineRepository.findExpiringSoonMedicines(userId, today, sevenDaysAgo);
    }
    
    public Medicine updateMedicine(Medicine medicine) {
        // Fetch and set the managed User entity
        Long userId = medicine.getUser().getId();
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        medicine.setUser(user);
        return medicineRepository.save(medicine);
    }
    
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
} 