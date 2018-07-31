package com.mobileapps.training.daily1;

import android.location.Address;

public interface SearchContract {

    interface PublishToView{

        void showLocation(double latitude, double longitude, float zoom);

        void searchLocation(Address address, float zoom);

        void showToastMessage(String message);
    }

    // Passes click events from view to presenter
    interface ForwardInteractionToPresenter{

        void getLocationDevice();

        void getAddressFromText(String text);
    }
}
