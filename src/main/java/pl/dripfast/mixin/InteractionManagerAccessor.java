package pl.dripfast.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientPlayerInteractionManager.class)
public interface InteractionManagerAccessor {

    @Accessor("itemUseCooldown")
    void setItemUseCooldown(int cooldown);
}
