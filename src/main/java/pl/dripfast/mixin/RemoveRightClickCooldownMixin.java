package pl.dripfast.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class RemoveRightClickCooldownMixin {

    /**
     * Zeruje licznik cooldownu po kliknięciu gdy:
     * - gracz trzyma pointed_dripstone w ręce
     * - patrzy na blok będący trapdoorem
     */
    @Inject(method = "doItemUse", at = @At("RETURN"))
    private void onItemUse(CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null) return;

        // Sprawdź czy gracz trzyma dripstone
        boolean holdingDripstone =
            client.player.getMainHandStack().isOf(Items.POINTED_DRIPSTONE) ||
            client.player.getOffHandStack().isOf(Items.POINTED_DRIPSTONE);

        if (!holdingDripstone) return;

        // Sprawdź czy gracz patrzy na trapdoor
        HitResult hit = client.crosshairTarget;
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hit;
        Block targetBlock = client.world.getBlockState(blockHit.getBlockPos()).getBlock();

        if (!(targetBlock instanceof TrapdoorBlock)) return;

        // Wyzeruj cooldown kliknięcia prawym przyciskiem
        ClientPlayerInteractionManager interactionManager = client.interactionManager;
        if (interactionManager != null) {
            // Ustawiamy itemUseCooldown na 0 przez dostęp do pola (wymaga access widener)
            DripFastAccessor.resetCooldown(interactionManager);
        }
    }
}
