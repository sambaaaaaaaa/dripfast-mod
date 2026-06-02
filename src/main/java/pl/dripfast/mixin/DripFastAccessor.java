package pl.dripfast.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;

public class DripFastAccessor {
    public static void resetCooldown(ClientPlayerInteractionManager manager) {
        ((InteractionManagerAccessor) manager).setItemUseCooldown(0);
    }
}
