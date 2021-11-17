package net.aconamos.autodc;

import net.aconamos.autodc.config.Config;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class AutoDCScreen extends Screen {
    private final Screen parent;
    private ButtonWidget enableButton;
    private ButtonWidget menuButton;
    protected TextFieldWidget ticksBox;

    public AutoDCScreen(Screen parent) {
        super(Text.of("AutoDC Config"));
        this.parent = parent;
    }

    public final void init() {
        this.enableButton = new ButtonWidget(this.width / 2 - 100, this.height / 2, 98, 20, Text.of("AutoDC: " + (Config.AutoDCEnabled ? "Enabled" : "Disabled")), (button) -> {
            Config.AutoDCEnabled = !Config.AutoDCEnabled;
            button.setMessage(Text.of("AutoDC: " + (Config.AutoDCEnabled ? "Enabled" : "Disabled")));
        });
        this.addDrawableChild(this.enableButton);
        this.menuButton = new ButtonWidget(this.width / 2 - 100, this.height / 2 + 22, 200, 20, ScreenTexts.DONE, (button -> this.client.setScreen(this.parent)));
        this.addDrawableChild(this.menuButton);
        this.ticksBox = new TextFieldWidget(this.textRenderer, this.width / 2 + 3, this.height / 2 + 2, 95, 16, this.ticksBox, Text.of("Ticks required for DC"));
        this.ticksBox.setChangedListener((number) -> Config.ticksRequired = number);
        this.ticksBox.setText(Config.ticksRequired);
        this.addSelectableChild(this.ticksBox);
        this.setInitialFocus(this.ticksBox);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.ticksBox.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void tick() {
        this.ticksBox.tick();
    }
}
