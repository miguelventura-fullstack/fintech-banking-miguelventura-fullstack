package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class Address {

    @NotBlank
    @Size(max = 200)
    @Column(name = "line1", nullable = false, length = 200)
    private String line1;

    @Size(max = 200)
    @Column(name = "line2", length = 200)
    private String line2;

    @NotBlank
    @Size(max = 100)
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotBlank
    @Size(max = 100)
    @Column(name = "state_province", nullable = false, length = 100)
    private String stateProvince;

    @NotBlank
    @Size(max = 20)
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @NotBlank
    @Pattern(regexp = "[A-Z]{2}")
    @Column(name = "country_code", nullable = false, length = 2)
    private String countryCode;

    protected Address() {
    }

    public Address(
            String line1,
            String line2,
            String city,
            String stateProvince,
            String postalCode,
            String countryCode
    ) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address address)) {
            return false;
        }
        return Objects.equals(line1, address.line1)
                && Objects.equals(line2, address.line2)
                && Objects.equals(city, address.city)
                && Objects.equals(stateProvince, address.stateProvince)
                && Objects.equals(postalCode, address.postalCode)
                && Objects.equals(countryCode, address.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line1, line2, city, stateProvince, postalCode, countryCode);
    }
}
