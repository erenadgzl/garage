package com.kafein.garage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kafein.garage.data.VehicleType;

import javax.validation.constraints.NotNull;

/**
 * @author erenadiguzel
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkRequestModel {

    @NotNull(message = "Type can not be null!")
    private VehicleType type;

    @NotNull(message = "Color can not be null!")
    private String color;

    @NotNull(message = "Licence plate can not be null!")
    private String licencePlate;

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
}
