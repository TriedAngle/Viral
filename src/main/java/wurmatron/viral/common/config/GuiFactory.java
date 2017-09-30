package wurmatron.viral.common.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import wurmatron.viral.client.gui.ConfigGui;

import java.util.Set;

public class GuiFactory implements IModGuiFactory {

	@Override
	public void initialize (Minecraft minecraftInstance) {
	}

	@Override
	public Set <RuntimeOptionCategoryElement> runtimeGuiCategories () {
		return null;
	}

	@Override
	public boolean hasConfigGui () {
		return true;
	}

	@Override
	public GuiScreen createConfigGui (GuiScreen parentScreen) {
		return new ConfigGui (parentScreen);
	}
}
