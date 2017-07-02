package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }
}
