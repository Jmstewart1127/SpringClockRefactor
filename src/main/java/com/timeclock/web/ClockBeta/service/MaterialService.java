package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.logistics.MaterialCostLogic;
import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    MaterialCostLogic mcl;

    public Iterable<Material> findByJobId(int jobId) {
        return materialRepository.findByJobId(jobId);
    }

    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }

    public double calculateTotalPrice(int quantity, double price) {
        return mcl.storeTotalPrice(quantity, price);
    }

    public double totalPriceOfAllJobMaterials(int jobId) {
        Iterable<Material> materials = materialRepository.findByJobId(jobId);
        double totalCost = 0;
        for (Material material : materials) {
           totalCost += material.getTotalPrice();
        }
        return Math.round(totalCost * 100d) / 100d;
    }
}
