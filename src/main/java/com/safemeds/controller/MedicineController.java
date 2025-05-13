package com.safemeds.controller;

import com.safemeds.model.Medicine;
import com.safemeds.service.MedicineService;
import com.safemeds.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    
    @Autowired
    private MedicineRepository medicineRepository;
    
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.addMedicine(medicine));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Medicine>> getUserMedicines(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineService.getUserMedicines(userId));
    }
    
    @GetMapping("/expired/{userId}")
    public ResponseEntity<List<Medicine>> getExpiredMedicines(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineService.getExpiredMedicines(userId));
    }
    
    @GetMapping("/expiring-soon/{userId}")
    public ResponseEntity<List<Medicine>> getExpiringSoonMedicines(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineService.getExpiringSoonMedicines(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        return medicineRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine) {
        medicine.setId(id);
        return ResponseEntity.ok(medicineService.updateMedicine(medicine));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }
} 