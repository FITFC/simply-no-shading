package com.github.startsmercury.simplynoshading.mixin.minecraft;

import static net.fabricmc.api.EnvType.CLIENT;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.startsmercury.simplynoshading.client.option.SimplyNoShadingGameOptions;
import com.github.startsmercury.simplynoshading.client.option.SimplyNoShadingKeyBindings;

import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;

/**
 * Contains implementation of the extensions to the class {@link GameOptions}.
 * <p>
 * <table border=1 style="margin:0;padding:0;">
 * <tr>
 * <td align=center>keyCycleShadeAll</td>
 * <td align=center colspan=2>{@link #keyCycleShadeAll() get}</td>
 * </tr>
 * <tr>
 * <td align=center>keyCycleShadeBlocks</td>
 * <td align=center colspan=2>{@link #keyCycleShadeBlocks() get}</td>
 * </tr>
 * <tr>
 * <td align=center>keyCycleShadeClouds</td>
 * <td align=center colspan=2>{@link #keyCycleShadeClouds() get}</td>
 * </tr>
 * <tr>
 * <td align=center>keyCycleShadeFluids</td>
 * <td align=center colspan=2>{@link #keyCycleShadeFluids() get}</td>
 * </tr>
 * <tr>
 * <td align=center>shadeAll</td>
 * <td align=center>{@link #isShadeAll() get}</td>
 * <td align=center>{@link #setShadeAll(boolean) set</td>
 * </tr>
 * <tr>
 * <td align=center>shadeBlocks</td>
 * <td align=center>{@link #isShadeBlocks() get}</td>
 * <td align=center>{@link #setShadeBlocks(boolean) set</td>
 * </tr>
 * <tr>
 * <td align=center>shadeClouds</td>
 * <td align=center>{@link #isShadeClouds() get}</td>
 * <td align=center>{@link #setShadeClouds(boolean) set</td>
 * </tr>
 * <tr>
 * <td align=center>shadeFluids</td>
 * <td align=center>{@link #isShadeFluids() get}</td>
 * <td align=center>{@link #setShadeFluids(boolean) set</td>
 * </tr>
 * </table>
 */
@Environment(CLIENT)
@Mixin(GameOptions.class)
public class GameOptionsMixin implements SimplyNoShadingGameOptions {
	/**
	 * Complementary flag to other shadeXxxs properties.
	 * <p>
	 * Default value is {@code false}.
	 *
	 * @see #isShadeAll()
	 * @see #setShadeAll(boolean)
	 * @see #shadeBlocks
	 * @see #shadeFluids
	 */
	private boolean shadeAll;

	/**
	 * Complementary flag to the shadeAll property.
	 * <p>
	 * Default value is {@code false}.
	 *
	 * @see #isShadeBlocks()
	 * @see #setShadeBlocks(boolean)
	 * @see #shadeAll
	 */
	private boolean shadeBlocks;

	/**
	 * Complementary flag to the shadeAll property.
	 * <p>
	 * Default value is {@code false}.
	 *
	 * @see #isShadeClouds()
	 * @see #setShadeClouds(boolean)
	 * @see #shadeAll
	 */
	private boolean shadeClouds;

	/**
	 * Complementary flag to the shadeAll property.
	 * <p>
	 * Default value is {@code false}.
	 *
	 * @see #isShadeFluids()
	 * @see #setShadeFluids(boolean)
	 * @see #shadeAll
	 */
	private boolean shadeFluids;

	/**
	 * @see #shadeAll
	 * @see #setShadeAll(boolean)
	 */
	@Override
	public boolean isShadeAll() {
		return this.shadeAll;
	}

	/**
	 * @see #shadeBlocks
	 * @see #setShadeBlocks(boolean)
	 */
	@Override
	public boolean isShadeBlocks() {
		return this.shadeBlocks;
	}

	/**
	 * @see #shadeClouds
	 * @see #setShadeClouds(boolean)
	 */
	@Override
	public boolean isShadeClouds() {
		return this.shadeClouds;
	}

	/**
	 * @see #shadeFluids
	 * @see #setShadeFluids(boolean)
	 */
	@Override
	public boolean isShadeFluids() {
		return this.shadeFluids;
	}

	/**
	 * @see SimplyNoShadingKeyBindings#KEY_CYCLE_SHADE_ALL
	 */
	@Override
	public final KeyBinding keyCycleShadeAll() {
		return SimplyNoShadingKeyBindings.KEY_CYCLE_SHADE_ALL;
	}

	/**
	 * @see SimplyNoShadingKeyBindings#KEY_CYCLE_SHADE_BLOCKS
	 */
	@Override
	public KeyBinding keyCycleShadeBlocks() {
		return SimplyNoShadingKeyBindings.KEY_CYCLE_SHADE_BLOCKS;
	}

	/**
	 * @see SimplyNoShadingKeyBindings#KEY_CYCLE_SHADE_CLOUDS
	 */
	@Override
	public KeyBinding keyCycleShadeClouds() {
		return SimplyNoShadingKeyBindings.KEY_CYCLE_SHADE_CLOUDS;
	}

	/**
	 * @see SimplyNoShadingKeyBindings#KEY_CYCLE_SHADE_FLUIDS
	 */
	@Override
	public KeyBinding keyCycleShadeFluids() {
		return SimplyNoShadingKeyBindings.KEY_CYCLE_SHADE_FLUIDS;
	}

	/**
	 * Allows the extensions to be incorporated even in the serialization process.
	 *
	 * @param visitor  the serialization api
	 * @param callback the method callback
	 */
	@Inject(method = "accept", at = @At("HEAD"))
	private final void onAcceptHead(final GameOptions.Visitor visitor, final CallbackInfo callback) {
		this.shadeAll = visitor.visitBoolean("shadeAll", this.shadeAll);
		this.shadeBlocks = visitor.visitBoolean("shadeBlocks", this.shadeBlocks);
		this.shadeFluids = visitor.visitBoolean("shadeFluids", this.shadeFluids);
	}

	/**
	 * @see #shadeAll
	 * @see #isShadeAll()
	 */
	@Override
	public void setShadeAll(final boolean shadeAll) {
		this.shadeAll = shadeAll;
	}

	/**
	 * @see #shadeBlocks
	 * @see #isShadeBlocks()
	 */
	@Override
	public void setShadeBlocks(final boolean shadeBlocks) {
		this.shadeBlocks = shadeBlocks;
	}

	/**
	 * @see #shadeClouds
	 * @see #isShadeClouds()
	 */
	@Override
	public void setShadeClouds(final boolean shadeClouds) {
		this.shadeClouds = shadeClouds;
	}

	/**
	 * @see #shadeFluids
	 * @see #isShadeFluids()
	 */
	@Override
	public void setShadeFluids(final boolean shadeFluids) {
		this.shadeFluids = shadeFluids;
	}
}
