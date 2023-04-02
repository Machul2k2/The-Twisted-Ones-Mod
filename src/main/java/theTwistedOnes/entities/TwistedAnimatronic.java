package theTwistedOnes.entities;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import theTwistedOnes.init.Sounds;

public class TwistedAnimatronic extends Monster implements IAnimatable {

	protected TwistedAnimatronic(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}
	
	@SuppressWarnings("removal")
	private AnimationFactory factory = new AnimationFactory(this);
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
	
	protected<E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
	{
		return null;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<TwistedAnimatronic>(this, "controller", 0, this::predicate));
		
	}
	
	@Override
	public int getExperienceReward() {
		this.xpReward = 200 + (int)new Random().nextInt(100) + 1;
		return super.getExperienceReward();
	}
	
	@Override
	protected void onInsideBlock(BlockState state) {
		Level level = this.level;
		ArrayList<BlockPos> bp = new ArrayList<BlockPos>();
		for(int y = 0; y < 4; y++)
		{
			bp.add(new BlockPos(this.getBlockX(), this.getBlockY() + y, this.getBlockZ()));
			bp.add(new BlockPos(this.getBlockX(), this.getBlockY() + y, this.getBlockZ() + 1));
			bp.add(new BlockPos(this.getBlockX(), this.getBlockY() + y, this.getBlockZ() - 1));
			bp.add(new BlockPos(this.getBlockX() + 1, this.getBlockY() + y, this.getBlockZ()));
			bp.add(new BlockPos(this.getBlockX() - 1, this.getBlockY() + y, this.getBlockZ()));
			
			if(y < 3)
			{
				bp.add(new BlockPos(this.getBlockX() + 1, this.getBlockY() + y, this.getBlockZ() + 1));
				bp.add(new BlockPos(this.getBlockX() + 1, this.getBlockY() + y, this.getBlockZ() - 1));
				bp.add(new BlockPos(this.getBlockX() - 1, this.getBlockY() + y, this.getBlockZ() + 1));
				bp.add(new BlockPos(this.getBlockX() - 1, this.getBlockY() + y, this.getBlockZ() - 1));
			}
		}
		
		ArrayList<BlockState> bs = new ArrayList<BlockState>();
		for(int i = 0; i < 32; i++)
		{
			bs.add(level.getBlockState(bp.get(i)));
		}
		
		for(BlockState b : bs)
		{
			if (b.getMaterial() == Material.LEAVES)
			{
				level.destroyBlock(bp.get(bs.indexOf(b)), true);
			}
		}
		super.onInsideBlock(state);
	}
	
	
	public void spawnAtLoc(ItemLike item, int amount) {
		for(int i = amount; i > 0; i--)
		{
			this.spawnAtLocation(item);
		}
	}
	
	@Override
	protected void dropCustomDeathLoot(DamageSource source, int i, boolean b) {
		Entity entity = source.getEntity();
		if(entity instanceof Player)
		{
			this.spawnAtLoc(Items.IRON_INGOT, new Random().nextInt(10) + 9);
			this.spawnAtLoc(Items.IRON_BLOCK, new Random().nextInt(2) + 1);
			this.spawnAtLoc(Items.NETHERITE_SCRAP, new Random().nextInt(4));
		}
		super.dropCustomDeathLoot(source, i, b);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.IRON_GOLEM_HURT;
	}
    
	@Override
	protected SoundEvent getDeathSound() {
		this.playSound(Sounds.TWISTED_ANIMATRONIC_DEATH.get(), 1.0F, 1.0F);
		return null;
	}
	
//    @Override
//    protected void playStepSound(BlockPos position, BlockState state) {
//        this.playSound(Sounds.TWISTED_ANIMATRONIC_STEP.get(), 0.10F, 0.65F);
//     }
    
    protected class TwistedAnimatronicMeleeAttackGoal extends MeleeAttackGoal
    {
    	public TwistedAnimatronicMeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
    		super(mob, speedModifier, followingTargetEvenIfNotSeen);
    	}
    	
    	@Override
    	protected double getAttackReachSqr(LivingEntity entity) {
    		return (double)(this.mob.getBbWidth() * 2.75F * this.mob.getBbHeight() * 1.6F);
    	}
    	
		@Override
		protected void resetAttackCooldown()
		{ 
			super.adjustedTickDelay(10);
		}
    }
}
