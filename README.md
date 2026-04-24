# StackCheck

A Minecraft Forge 1.20.1 mod that fixes the following crash when opening the creative mode inventory:

```
java.lang.IllegalArgumentException: Stack size must be exactly 1
    at net.minecraft.world.item.CreativeModeTab$ItemDisplayBuilder.m_246267_(CreativeModeTab.java:371)
    at net.minecraftforge.common.ForgeHooks.onCreativeModeTabBuildContents(ForgeHooks.java:1637)
```

## What it does

Some mods incorrectly add `ItemStack` entries with a stack count other than 1 to creative mode tabs.
Vanilla Minecraft enforces that all creative tab entries must have a count of exactly 1, and throws
an `IllegalArgumentException` if this condition is not met.

StackCheck uses Mixin to intercept the creative tab building process. Before items are passed to the
vanilla validation, it automatically corrects any `ItemStack` with `count != 1` to `count = 1` and
logs a warning with the offending item's registry name, so you can identify which mod is causing the issue.

## Installation

1. Requires **Minecraft 1.20.1** with **Forge 47.x**
2. Drop `stackcheck-1.0.0.jar` into your `mods/` folder
3. Launch the game

## Identifying the problematic mod

Check your game log for lines like:

```
[StackCheck] ItemStack with count 64 detected! Item: examplemod:some_item. Auto-fixing to 1.
```

This tells you which item (and therefore which mod) is adding entries with incorrect stack counts.
