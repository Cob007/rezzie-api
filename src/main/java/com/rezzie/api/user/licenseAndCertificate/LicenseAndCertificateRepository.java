package com.rezzie.api.user.licenseAndCertificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseAndCertificateRepository extends JpaRepository<LicenseAndCertificate, Integer> {
}
