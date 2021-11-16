package net.aconamos.autodc.mixin;

import net.aconamos.autodc.config.Config;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
	@Shadow private int ticks;
	@Shadow public abstract void disconnect(Text disconnectReason);

	@Inject(at = @At("HEAD"), method = "tick()V")
	private void init(CallbackInfo info) {
		if (Config.AutoDCEnabled) {
			if (this.ticks > Integer.parseInt(Config.ticksRequired)) {
				this.disconnect(Text.of("Timer expired"));
			}
		}
	}
}

