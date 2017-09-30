package wurmatron.viral.common.blocks;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import wurmatron.viral.common.capabilities.IViral;
import wurmatron.viral.common.capabilities.ViralProvider;
import wurmatron.viral.common.config.Settings;

import java.util.List;
import java.util.Random;

public class ViralInterdictionTorchInverted extends BlockTorch {

	public ViralInterdictionTorchInverted () {
		setCreativeTab (CreativeTabs.DECORATIONS);
		setUnlocalizedName ("torchInterdictionInverted");
		setHardness (1);
		setResistance (5);
		setLightLevel (12);
	}

	@Override
	public void updateTick (World world,BlockPos pos,IBlockState state,Random random) {
		super.updateTick (world,pos,state,random);
		world.scheduleBlockUpdate (pos,this,1,1);
		if (!world.isRemote) {
			world.setBlockState (pos.north (Settings.radius).east (Settings.radius),Blocks.BEACON.getDefaultState ());
			world.setBlockState (pos.south (Settings.radius).west (Settings.radius),Blocks.BEACON.getDefaultState ());
			List <Entity> entities = world.getEntitiesWithinAABB (Entity.class,new AxisAlignedBB (pos.getX () - Settings.radius,pos.getY () - Settings.radius,pos.getZ () - Settings.radius,pos.getX () + Settings.radius,pos.getY () + Settings.radius,pos.getZ () + Settings.radius));
			for (Entity e : entities) {
				if (!(e instanceof EntityPlayer)) {
					IViral status = e.getCapability (ViralProvider.VIRAL,null);
					if (status != null && status.status () != -1 && status.status () == 1) {
						double distance = e.getDistanceSqToCenter (pos);
						double knockbackSpeed = 1 + (1 / distance);
						Vec3d angle = new Vec3d (e.posX - (pos.getX () + 0.5),e.posY - pos.getY (),e.posZ - (pos.getZ () + 0.5));
						e.motionX -= angle.x * knockbackSpeed;
						e.motionY -= angle.y * knockbackSpeed;
						e.motionZ -= angle.z * knockbackSpeed;
					}
				}
			}
		}
	}

	public int tickRate (World worldIn) {
		return 5;
	}
}
