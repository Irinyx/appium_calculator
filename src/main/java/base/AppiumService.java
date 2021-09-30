package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.IOException;

public class AppiumService {
    private static AppiumDriverLocalService service;

    public static AppiumDriverLocalService getService() throws IOException {
        if (service == null) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1");

            String log_level = Config.getString("driver.log_level");
            if (log_level != null && !log_level.isEmpty()) {
                builder.withArgument(GeneralServerFlag.LOG_LEVEL, log_level);
            }

            service = AppiumDriverLocalService.buildService(builder);
        }

        if (service != null && !service.isRunning()) {
            service.start();
        }

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }

        return service;
    }

    public static void stopService() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
