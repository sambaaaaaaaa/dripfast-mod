package pl.dripfast;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DripFastMod implements ClientModInitializer {
    public static final String MOD_ID = "dripfast";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("DripFast zaladowany!");
    }
}
