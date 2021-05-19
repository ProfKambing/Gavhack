package me.gavin.gavhack;

import me.gavin.gavhack.clickgui.impl.GUI;
import me.gavin.gavhack.clickgui.impl.HUD;
import me.gavin.gavhack.manager.ColorManager;
import me.gavin.gavhack.manager.DiscordManager;
import me.gavin.gavhack.manager.ModuleManager;
import me.gavin.gavhack.manager.ProjectionManager;
import me.gavin.gavhack.util.ForgeEventTranslator;
import me.gavin.gavhack.util.font.SalFontRenderer;
import me.gavin.quasar.EventSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = Gavhack.MOD_ID,
        name = Gavhack.MOD_NAME,
        version = Gavhack.VERSION,
        clientSideOnly = true
)
public class Gavhack {

    public static final String MOD_ID = "gavhack";
    public static final String MOD_NAME = "Gavhack";
    public static final String VERSION = "v1.0";

    @Mod.Instance(MOD_ID)
    public static Gavhack INSTANCE;

    public Logger logger;
    public EventSystem eventSys;
    public SalFontRenderer fontRenderer;
    public ModuleManager moduleManager;
    public ColorManager colorManager;
    public DiscordManager discordManager;
    public ProjectionManager projectionManager;
    public GUI clickGui;
    public HUD hudEditor;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger = LogManager.getLogger(MOD_ID);

        logger.info("Starting " + MOD_NAME + " " + VERSION + "...");

        eventSys = new EventSystem();
        logger.info("Event system initialized");

        fontRenderer = new SalFontRenderer("muli-semibold", 18);
        logger.info("Font renderer initialized");

        discordManager = new DiscordManager();
        logger.info("Discord manager initialized");

        moduleManager = new ModuleManager();
        logger.info("Module manager initialized");

        colorManager = new ColorManager();
        logger.info("Color manager initialized");

        projectionManager = new ProjectionManager();
        logger.info("Projection manager initialized");

        clickGui = new GUI();
        logger.info("Click GUI initialized");

        hudEditor = new HUD();
        logger.info("HUD editor initialized");

        new ForgeEventTranslator();
        logger.info("Forge event translator initialized");

        logger.info(Gavhack.MOD_NAME + " finished initialization");

        discordManager.startRPC();
    }
}