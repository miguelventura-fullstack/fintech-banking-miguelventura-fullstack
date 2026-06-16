package org.example.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_customers_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_customers_customer_number", columnNames = "customer_number")
        }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "customer_number", nullable = false, length = 20, updatable = false)
    private String customerNumber;

    @NotBlank
    @Size(max = 100)
    @Column(name = "legal_first_name", nullable = false, length = 100)
    private String legalFirstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "legal_last_name", nullable = false, length = 100)
    private String legalLastName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$")
    @Column(name = "phone_e164", nullable = false, length = 20)
    private String phoneE164;

    @NotNull
    @Past
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank
    @Column(name = "tax_id_token", nullable = false, length = 255)
    private String taxIdToken;

    @NotBlank
    @Pattern(regexp = "\\d{4}")
    @Column(name = "tax_id_last_four", nullable = false, length = 4)
    private String taxIdLastFour;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "line1", column = @Column(name = "address_line1", nullable = false, length = 200)),
            @AttributeOverride(name = "line2", column = @Column(name = "address_line2", length = 200)),
            @AttributeOverride(name = "city", column = @Column(name = "address_city", nullable = false, length = 100)),
            @AttributeOverride(name = "stateProvince", column = @Column(name = "address_state_province", nullable = false, length = 100)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code", nullable = false, length = 20)),
            @AttributeOverride(name = "countryCode", column = @Column(name = "address_country_code", nullable = false, length = 2))
    })
    private Address primaryAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CustomerStatus status = CustomerStatus.PROSPECT;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_status", nullable = false, length = 20)
    private KycStatus kycStatus = KycStatus.NOT_STARTED;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    protected Customer() {
    }

    public Customer(
            String legalFirstName,
            String legalLastName,
            String email,
            String phoneE164,
            LocalDate dateOfBirth,
            String taxIdToken,
            String taxIdLastFour,
            Address primaryAddress
    ) {
        this.legalFirstName = legalFirstName;
        this.legalLastName = legalLastName;
        this.email = email;
        this.phoneE164 = phoneE164;
        this.dateOfBirth = dateOfBirth;
        this.taxIdToken = taxIdToken;
        this.taxIdLastFour = taxIdLastFour;
        this.primaryAddress = primaryAddress;
    }

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getLegalFirstName() {
        return legalFirstName;
    }

    public void setLegalFirstName(String legalFirstName) {
        this.legalFirstName = legalFirstName;
    }

    public String getLegalLastName() {
        return legalLastName;
    }

    public void setLegalLastName(String legalLastName) {
        this.legalLastName = legalLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneE164() {
        return phoneE164;
    }

    public void setPhoneE164(String phoneE164) {
        this.phoneE164 = phoneE164;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTaxIdToken() {
        return taxIdToken;
    }

    public void setTaxIdToken(String taxIdToken) {
        this.taxIdToken = taxIdToken;
    }

    public String getTaxIdLastFour() {
        return taxIdLastFour;
    }

    public void setTaxIdLastFour(String taxIdLastFour) {
        this.taxIdLastFour = taxIdLastFour;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public KycStatus getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(KycStatus kycStatus) {
        this.kycStatus = kycStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer customer)) {
            return false;
        }
        return id != null && id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
