package net.minecraft.client.gui;

import de.florianmichael.viamcp.ViaMCP;
import de.florianmichael.viamcp.gui.GuiProtocolSelector;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;
import net.sssssssthedev.SmartClient.Main;
import net.sssssssthedev.SmartClient.ui.login.GuiAltLogin;

import java.io.IOException;
import java.util.List;

public class GuiDisconnected extends GuiScreen
{
    private String reason;
    private IChatComponent message;
    private List<String> multilineMessage;
    private final GuiScreen parentScreen;
    private long reconnectTime;
    private int field_175353_i;

    public GuiDisconnected(GuiScreen screen, String reasonLocalizationKey, IChatComponent chatComp)
    {
        this.parentScreen = screen;
        this.reason = I18n.format(reasonLocalizationKey, new Object[0]);
        this.message = chatComp;
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.buttonList.clear();
        this.multilineMessage = this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
        this.field_175353_i = this.multilineMessage.size() * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(1337, 5, 6, 98, 20, "Version Switcher"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + 33,  "\u00a76Reconnect"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + 57, "\u00a7eAuto Reconnect"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT, I18n.format("gui.toMenu", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            this.mc.displayGuiScreen(this.parentScreen);
        } else if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(new GuiMainMenu()), this.mc, new ServerData(ViaMCP.INSTANCE.getLastServer(), ViaMCP.INSTANCE.getLastServer(), false)));
        } else if (button.id == 2) {
            Main.setAutoReconnect(!Main.isAutoReconnect());
            reconnectTime = System.currentTimeMillis() + 1500;
        } else if (button.id == 1337) {
            this.mc.displayGuiScreen(new GuiProtocolSelector(this));
        }


    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.reason, this.width / 2, this.height / 2 - this.field_175353_i / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
        int i = this.height / 2 - this.field_175353_i / 2;
        this.mc.fontRendererObj.drawStringWithShadow("<-- Current Version", 104.0F, 13.0F, -1);

        if (this.multilineMessage != null) {
            for (String s : this.multilineMessage) {
                this.drawCenteredString(this.fontRendererObj, s, this.width / 2, i, 16777215);
                i += this.fontRendererObj.FONT_HEIGHT;
            }
        }

        if (Main.isAutoReconnect()) {
            drawCenteredString(fontRendererObj, "Relog Time: " + (Math.max(reconnectTime - System.currentTimeMillis(), 0)) + "ms", width / 2, GuiAltLogin.username != null ? 62 : 48, -1);
            if (System.currentTimeMillis() >= reconnectTime) {
                this.mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(new GuiMainMenu()), this.mc, new ServerData(ViaMCP.INSTANCE.getLastServer(), ViaMCP.INSTANCE.getLastServer(), false)));
                return;
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
