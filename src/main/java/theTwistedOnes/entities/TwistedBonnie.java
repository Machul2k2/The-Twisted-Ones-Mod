package theTwistedOnes.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import theTwistedOnes.init.Sounds;

public class TwistedBonnie extends TwistedAnimatronic {

	public TwistedBonnie(EntityType<? extends TwistedAnimatronic> type, Level level) {
		super(type, level);
	}
	
	@Override
	public void registerGoals() 
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
	    this.goalSelector.addGoal(2, new TwistedAnimatronicMeleeAttackGoal(this, 1.0D, true));
	    this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
	    this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}
	
	public static AttributeSupplier.Builder registerAttributes() {
		return TwistedAnimatronic.createMobAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.4D)
				.add(Attributes.MAX_HEALTH, 125.0D)
				.add(Attributes.FOLLOW_RANGE, 37.0D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
	}

	@SuppressWarnings("removal")
	@Override
	protected<E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
	{
		if(event.isMoving())
		{
			if(this.isAggressive())
			{
				event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedbonnie.chase", true));
				event.getController().animationSpeed = 1.7D;
			}
			else
			{
				event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedbonnie.walk", true));
				event.getController().animationSpeed = 1.7D;
			}
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedbonnie.idle", true));
		return PlayState.CONTINUE;
	}
	
	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return super.canDrownInFluidType(type);
	}

	protected SoundEvent getAmbientSound() {
    	if(!(this.getTarget() == null))
    	{
    		return Sounds.TWISTED_BONNIE_AMBIENT.get();
    	}
	    return null;
   }
	

}
