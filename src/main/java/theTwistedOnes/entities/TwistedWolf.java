package theTwistedOnes.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class TwistedWolf extends TwistedAnimatronic {

	public TwistedWolf(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	public void registerGoals() 
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
	    this.goalSelector.addGoal(2, new TwistedAnimatronicMeleeAttackGoal(this, 1.0D, true));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
	    this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return TwistedAnimatronic.createMobAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.4D)
				.add(Attributes.MAX_HEALTH, 125.0D)
				.add(Attributes.FOLLOW_RANGE, 37.0D)
				.add(Attributes.ATTACK_DAMAGE, 8.0D)
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
				event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedwolf.chase", true));
				event.getController().animationSpeed = 1.7D;
			}
			else
			{
				event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedwolf.walk", true));
				event.getController().animationSpeed = 1.7D;
			}
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("twistedwolf.idle", true));
		return PlayState.CONTINUE;
	}

}
