package main.com.maryzh555.photo_studio.interfaces;

import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.CustomerManager;
import main.com.maryzh555.photo_studio.models.users.Director;
import main.com.maryzh555.photo_studio.models.users.HRManager;
import main.com.maryzh555.photo_studio.models.users.SupplyManager;

/**
 * @author by Zhang M. on 23.04.2023.
 */
public interface ICallWorkers {

    Director callDirector(PhotoStudio photoStudio);

    HRManager callHRManager(PhotoStudio photoStudio);

    SupplyManager callSupplyManager(PhotoStudio photoStudio);

    CustomerManager callCustomerManager(PhotoStudio photoStudio);

}
