package main.com.maryzh555.photo_studio.interfaces;

import main.com.maryzh555.photo_studio.models.PhotoStudio;
import main.com.maryzh555.photo_studio.models.users.Worker;

/**
 * @author by Zhang M. on 25.04.2023.
 */
public interface ICallWorkers {

    <T extends Worker> T callWorker(PhotoStudio photoStudio, Class<T> clazz);
}
