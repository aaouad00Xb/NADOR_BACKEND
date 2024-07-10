package com.example.demo.Service;


import com.example.demo.Dao.PrestationAcquisitionRepository;
import com.example.demo.Dao.Province_Repo;
import com.example.demo.Dtos.PrestationAcquisitionDto;
import com.example.demo.Entities.PrestationAcquisition;
import com.example.demo.Entities.Province;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrestationAquiService {

    private final PrestationAcquisitionRepository prestationAcquisitionRepository;
    private final Province_Repo provinceRepo;
    @Autowired
    ObjectMapper objectMapper;



    @Transactional
    public PrestationAcquisition addPrestationAcquisition(PrestationAcquisitionDto prestationAcquisition) {

        PrestationAcquisition prestationAcquisition1=  objectMapper.convertValue(prestationAcquisition, PrestationAcquisition.class);

        Optional<Province> province = Optional.ofNullable(provinceRepo.findById(prestationAcquisition.getProvinceId()).orElseThrow(() -> new UsernameNotFoundException("provinceNot found")));

        if (province.isPresent()) prestationAcquisition1.setProvince(province.get());

        return prestationAcquisitionRepository.save(prestationAcquisition1);
    }

    @Transactional
    public PrestationAcquisition editPrestationAcquisition(Long id, PrestationAcquisition updatedPrestationAcquisition) {
        Optional<PrestationAcquisition> existingPrestationAcquisitionOptional = prestationAcquisitionRepository.findById(id);
        if (existingPrestationAcquisitionOptional.isPresent()) {
            PrestationAcquisition existingPrestationAcquisition = existingPrestationAcquisitionOptional.get();
            existingPrestationAcquisition.setNombre(updatedPrestationAcquisition.getNombre());
            existingPrestationAcquisition.setType(updatedPrestationAcquisition.getType());
            existingPrestationAcquisition.setCommentaire(updatedPrestationAcquisition.getCommentaire());
            return prestationAcquisitionRepository.save(existingPrestationAcquisition);
        } else {
            // Handle the case when the prestation acquisition with the provided id is not found
            // For example, you may throw an exception or return null indicating failure.
            return null;
        }
    }

    @Transactional
    public void deletePrestationAcquisition(Long id) {
        prestationAcquisitionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PrestationAcquisition> getAllPrestationAcquisitions() {
        return prestationAcquisitionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PrestationAcquisition> getPrestationAcquisitionById(Long id) {
        return prestationAcquisitionRepository.findById(id);
    }

}
