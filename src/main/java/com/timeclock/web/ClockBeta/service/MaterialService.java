package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.logistics.MaterialCostLogic;
import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    MaterialCostLogic mcl;

    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }

    public double calculateTotalPrice(int quantity, double price) {
        return mcl.storeTotalPrice(quantity, price);
    }
}
