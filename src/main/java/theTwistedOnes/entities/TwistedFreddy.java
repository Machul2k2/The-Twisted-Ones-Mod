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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import theTwistedOnes.init.Sounds;

public class TwistedFreddy extends TwistedAnimatronic{
	
	public TwistedFreddy(EntityType<? extends TwistedAnimatronic> type, Level level) {
		super(type, level);
	}
	
	@Override
	public void registerGoals() 
	{
		this.goalSelector.addGoal(3, new FloatGoal(this));
	    this.goalSelector.addGoal(2, new TwistedAnimatronicMeleeAttackGoal(this, 1.0D, true));
	    this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
	}
	
	public static AttributeSupplier.Builder registerAttributes() {
		return TwistedAnimatronic.createMobAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.4D)
				.add(Attributes.MAX_HEALTH, 175.0D)
				.add(Attributes.FOLLOW_RANGE, 37.0D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.7D);
	}
	
	@SuppressWarnings("removal")
	@Override
	protected<E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
	{
		if(event.isMoving())
		{
			if(this.isAggressive())
			{
				event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedfreddy.chase", true));
				event.getController().animationSpeed = 1.7D;
			}
			else
			{
				event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedfreddy.walk", true));
				event.getController().animationSpeed = 1.7D;
			}
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedfreddy.idle", true));
		return PlayState.CONTINUE;
	}
	
    protected SoundEvent getAmbientSound() {
    	if(!(this.getTarget() == null))
    	{
    		return Sounds.TWISTED_FREDDY_LAUGH.get();
    	}
	    return null;
   }
}
