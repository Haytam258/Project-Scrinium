package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.CertificatMedicale;
import com.mbc.clickclinic.service.CertificatMedicaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CertificatMedicaleRestController {

    private final CertificatMedicaleService certificatMedicaleService;

    @Autowired
    public CertificatMedicaleRestController(CertificatMedicaleService certificatMedicaleService){
        this.certificatMedicaleService = certificatMedicaleService;
    }

    @PostMapping("/createCertificat")
    public CertificatMedicale createCertificat(@RequestBody CertificatMedicale certificatMedicale){
        return certificatMedicaleService.saveCertificatMedical(certificatMedicale);
    }

    @GetMapping("/certificats")
    public List<CertificatMedicale> getCertificats(){
        return certificatMedicaleService.CertificatMedicals();
    }

    @GetMapping("/certificats/{id}")
    public CertificatMedicale getCertificat(@PathVariable Long id){
        return certificatMedicaleService.CertificatMedicalById(id);
    }

    @PostMapping("/deleteCertificat/{id}")
    public void deleteCertificat(@PathVariable Long id){
        certificatMedicaleService.deleteCertificatMedical(certificatMedicaleService.CertificatMedicalById(id));
    }

    @PostMapping("/updateCertificat")
    public CertificatMedicale updateCertificat(@RequestBody CertificatMedicale certificatMedicale){
        return certificatMedicaleService.updateCertificatMedical(certificatMedicale);
    }
}
