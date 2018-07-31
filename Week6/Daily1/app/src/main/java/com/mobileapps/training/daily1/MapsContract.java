package com.mobileapps.training.daily1;

public interface MapsContract {

    //From presenter to View
    interface PublishToView{

        //location is valid
        void showResults(String results);

        //error message
        void showToastMessage(String message);
    }

    // Passes click events from view to presenter
    interface ForwardInteractionToPresenter{

        void getLocationAsAddress();
    }

}
