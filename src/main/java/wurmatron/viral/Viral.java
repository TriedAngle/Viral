package wurmatron.viral;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wurmatron.viral.common.capabilities.IViral;
import wurmatron.viral.common.capabilities.ViralData;
import wurmatron.viral.common.capabilities.ViralStorage;
import wurmatron.viral.common.config.ConfigHandler;
import wurmatron.viral.common.event.CapabilityHandler;
import wurmatron.viral.common.event.ViralEventHandler;
import wurmatron.viral.common.proxy.CommonProxy;
import wurmatron.viral.common.reference.Global;

@Mod(modid = Global.MODID, name = Global.NAME, version = Global.VERSION, guiFactory = Global.GUIFACTORY)
public class Viral {

    @Mod.Instance(Global.MODID)
    public static Viral instance;

    @SidedProxy(serverSide = Global.COMMONPROXY, clientSide = Global.CLIENTPROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        ConfigHandler.preInit(e);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e) {
        CapabilityManager.INSTANCE.register(IViral.class, new ViralStorage(), ViralData.class);

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new ViralEventHandler());
        proxy.register();
    }
}
