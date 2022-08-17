package com.kafein.garage.data;

import com.kafein.garage.model.ParkRequestModel;

/**
 * @author erenadiguzel
 */

public class VehicleFactory {
    private VehicleFactory() {
    }

    public static Vehicle createVehicle(ParkRequestModel parkRequestModel){
        switch (parkRequestModel.getType()){
            case CAR:
                return new Car(parkRequestModel.getColor(), parkRequestModel.getLicencePlate());
            case JEEP:
                return new Jeep(parkRequestModel.getColor(), parkRequestModel.getLicencePlate());
            case TRUCK:
                return new Truck(parkRequestModel.getColor(), parkRequestModel.getLicencePlate());
            default:
                throw new IllegalArgumentException();
        }
    }
}
