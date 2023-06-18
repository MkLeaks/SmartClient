package net.sssssssthedev.SmartClient;

// Copyright sssssss.dev (C) 2020-2021

import de.florianmichael.viamcp.ViaMCP;
import net.minecraft.util.Session;
import net.sssssssthedev.SmartClient.annotations.modules.Module;
import net.sssssssthedev.SmartClient.annotations.version.IVersion;
import net.sssssssthedev.SmartClient.annotations.version.VersionInfo;
import net.sssssssthedev.SmartClient.clickgui.ClickGUI;
import net.sssssssthedev.SmartClient.command.CommandManager;
import net.sssssssthedev.SmartClient.event.EventManager;
import net.sssssssthedev.SmartClient.event.EventTarget;
import net.sssssssthedev.SmartClient.event.impl.KeyEvent;
import net.sssssssthedev.SmartClient.module.ModuleManager;
import net.sssssssthedev.SmartClient.settings.SettingsManager;
import net.sssssssthedev.SmartClient.utils.ValueManager;
import org.lwjgl.opengl.Display;

import java.io.IOException;
import java.util.Date;

/***
 * Main class
 * @author sssssssthedev
 */
@IVersion(
        name = "SmartClient",
        version = "1.3.0",
        build = "Production"
)
public class Main extends VersionInfo {

    public static Main instance = new Main();
    public static Date date = new Date(System.currentTimeMillis());
    public static boolean BungeeHack;
    public static boolean PremiumUUID;
    public static boolean SessionPremium;
    public static boolean AutoReconnect;
    public static String IpBungeeHack = "127.0.0.1";
    public static String fakenick;
    public static Session session;
    public static String PreUUID;
    public static String IpToScan = "Put a DNS like example.com";
    public SettingsManager settingsManager = new SettingsManager();
    public ModuleManager moduleManager;
    public ClickGUI clickGUI;
    public EventManager eventManager = new EventManager();
    public CommandManager commandManager = new CommandManager();
    public ValueManager valueManager = new ValueManager();

    /***
     * loadClient
     */
    public void loadClient() throws IOException {
        fakenick = "";
        PreUUID = "";
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();
        ViaMCP.create();
        eventManager.register(this);
        Display.setTitle(String.format("%s %s | %s", getName(), getBuild(), getVersion()));
        
    }


    @EventTarget
    public void onKey(KeyEvent event) {
        moduleManager.getModules().stream().filter(module -> module.getKey() == event.getKey()).forEach(Module::toggle);
    }
    public static String getFakeNick() {
        return fakenick;
    }

    public static void setFakeNick(String name) {
        fakenick = name;
    }

    public static void setSession(Session copiedSession) {
        session = copiedSession;
    }

    public static boolean isAutoReconnect() {
        return AutoReconnect;
    }

    public static void setAutoReconnect(Boolean autoReconnect) {
        AutoReconnect = autoReconnect;
    }
}
