package net.sssssssthedev.SmartClient.module.world;

import net.sssssssthedev.SmartClient.event.EventTarget;
import net.sssssssthedev.SmartClient.event.impl.UpdateEvent;
import net.sssssssthedev.SmartClient.module.Category;
import net.sssssssthedev.SmartClient.module.Module;
import org.lwjgl.input.Keyboard;

public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", Keyboard.KEY_0, Category.WORLD);
    }

    @EventTarget
    public void onUpdate(UpdateEvent e) {
        mc.rightClickDelayTimer = 0;
    }
}
