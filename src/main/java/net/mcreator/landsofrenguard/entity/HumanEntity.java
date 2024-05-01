
package net.mcreator.landsofrenguard.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;

import java.util.Random;

import net.mcreator.landsofrenguard.init.LandsOfRenguardModEntities;

public class HumanEntity extends Monster {
	public static final EntityDataAccessor<Integer> DATA_GivenNameId = SynchedEntityData.defineId(HumanEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_Gender = SynchedEntityData.defineId(HumanEntity.class, EntityDataSerializers.INT);

	public int GetGender() {
		return getEntityData().get(DATA_Gender);
	}

	public void SetGender(int g) {
		getEntityData().set(DATA_Gender, g);
	}

	public int GetNameId() {
		return getEntityData().get(DATA_GivenNameId);
	}

	public void SetNameId(int n) {
		getEntityData().set(DATA_GivenNameId, n);
	}

	public HumanEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(LandsOfRenguardModEntities.HUMAN.get(), world);
	}

	public HumanEntity(EntityType<HumanEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);

		Random rand = new Random();
		
		if (GetGender() == -1) {
			SetGender(rand.nextInt(2));
		}

		if (GetNameId() == -1) {
			SetNameId(rand.nextInt(5));
		}

		String name = "UNDEFINED";

		switch (GetNameId()) {
			case 0:
				if (GetGender() == 0) {
					name = "Billy";
				} else {
					name = "Poppy";
				}
				break;
			case 1:
				if (GetGender() == 0) {
					name = "Derek";
				} else {
					name = "Eliza";
				}
				break;
			case 2:
				if (GetGender() == 0) {
					name = "Henry";
				} else {
					name = "Harriet";
				}
				break;
			case 3:
				if (GetGender() == 0) {
					name = "Sam";
				} else {
					name = "Lola";
				}
				break;
			case 4:
				if (GetGender() == 0) {
					name = "Robin";
				} else {
					name = "Gertrude";
				}
				break;
		}
		
		setCustomName(Component.literal(name));
		setCustomNameVisible(true);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_GivenNameId, -1);
		this.entityData.define(DATA_Gender, -1);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.getNavigation().getNodeEvaluator().setCanOpenDoors(true);
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new FloatGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, (float) 6));
		this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(6, new OpenDoorGoal(this, false));
		this.goalSelector.addGoal(7, new AvoidEntityGoal<>(this, Monster.class, (float) 6, 1, 1.2));
		this.targetSelector.addGoal(8, new HurtByTargetGoal(this).setAlertOthers());
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public double getMyRidingOffset() {
		return -0.35D;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.death"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DataGivenNameId", this.entityData.get(DATA_GivenNameId));
		compound.putInt("DataGender", this.entityData.get(DATA_Gender));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataGivenNameId"))
			this.entityData.set(DATA_GivenNameId, compound.getInt("DataGivenNameId"));
		if (compound.contains("DataGender"))
			this.entityData.set(DATA_Gender, compound.getInt("DataGender"));
	}

	public static void init() {
		SpawnPlacements.register(LandsOfRenguardModEntities.HUMAN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}
}
