package net.aconamos.autodc.mixin;

import net.aconamos.autodc.AutoDCScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    public TitleScreenMixin() {
        super(new TranslatableText("narrator.screen.title"));
    }

    @Inject(at = @At("HEAD"), method = "init()V")
    protected void init(CallbackInfo info) {
        int j = this.height / 4 + 48;
        ButtonWidget configButton = new ButtonWidget(this.width / 2 - 100, j + 72 + 12 + 20 + 1, 98, 20, Text.of("AutoDC Config"), (button) -> {
            this.client.setScreen(new AutoDCScreen(this));
        });
        this.addDrawableChild(configButton);
    }
}