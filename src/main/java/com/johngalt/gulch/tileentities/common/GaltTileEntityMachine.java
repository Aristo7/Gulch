package com.johngalt.gulch.tileentities.common;

import com.johngalt.gulch.blocks.common.GaltMachineBlock;
import com.johngalt.gulch.gui.GaltContainerMachine;
import com.johngalt.gulch.gui.GaltGuiMachine;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 6/27/2014.
 */
public abstract class GaltTileEntityMachine extends GaltTileEntity implements ISidedInventory
{
    private GaltMachineBlock _ActiveBlock;
    private GaltMachineBlock _InactiveBlock;

    private static final int[] _SlotsTopDestination = new int[]{0};
    private static final int[] _SlotsBottomDestination = new int[]{2, 1};
    private static final int[] _SlotsSideDestination = new int[]{1};

    private boolean _UseRegisteredFuel = false;

    public List<MachineSlot> Slots = new ArrayList<MachineSlot>();

    public MachineRecipeList RecipeList;

    private String _GuiName;

    private int _FurnaceSpeed = 150;
    public int BurnTime;
    public int CurrentItemBurnTime;
    public int CookTime;

    public GaltTileEntityMachine()
    {
        super();

        Slots = new ArrayList<MachineSlot>();
        RecipeList = new MachineRecipeList();
    }

    public GaltTileEntityMachine(GaltMachineBlock activeBlock, GaltMachineBlock inactiveBlock)
    {
        this();

        setActiveInactiveBlocks(activeBlock, inactiveBlock);

    }


    public GaltTileEntityMachine(List<MachineSlot> slots, MachineRecipeList recipeList)
    {
        super();

        List<Integer> ids = new ArrayList<Integer>();
        for (MachineSlot slot : slots)
        {
            if (!ids.contains(slot.ID))
            {
                ids.add(slot.ID);
            }
            else
            {
                throw new ExceptionInInitializerError("Machine slots must have unique IDs.");
            }
        }

        Slots = slots;
        RecipeList = recipeList;
    }

    public GaltTileEntityMachine(List<MachineSlot> slots, MachineRecipeList recipeList, GaltMachineBlock activeBlock, GaltMachineBlock inactiveBlock)
    {
        this(slots, recipeList);

        setActiveInactiveBlocks(activeBlock, inactiveBlock);
    }


    protected void setActiveInactiveBlocks(GaltMachineBlock activeBlock, GaltMachineBlock inactiveBlock)
    {
        _ActiveBlock = activeBlock;
        _InactiveBlock = inactiveBlock;
    }

    public void SetGuiName(String guiNameFileNameWithExt)
    {
        _GuiName = guiNameFileNameWithExt;
    }

    public GaltGuiMachine CreateGUIInstance(InventoryPlayer inv)
    {
        if (_GuiName != null)
        {
            return new GaltGuiMachine(inv, this, _GuiName);
        }
        else
        {
            return new GaltGuiMachine(inv, this, this.getClass().getSimpleName().replace("TileEntity", "Gui") + ".png");
        }
    }

    public GaltContainerMachine GetContainer(InventoryPlayer inv)
    {
        return new GaltContainerMachine(inv, this);
    }


    /**
     * This allows the machine to use fuels registered with minecraft.
     */
    public void AllowRegisteredFuel()
    {
        _UseRegisteredFuel = true;
    }

    public String getInventoryName()
    {
        if (hasCustomInventoryName())
        {
            return _LocalizedName;
        }
        else
        {
            return "container.Machine";
        }

    }

    public boolean hasCustomInventoryName()
    {
        return _LocalizedName != null;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
        {
            return false;
        }
        else
        {
            double distance = player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D);
            return distance <= 64.0D;
        }
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack item)
    {
        MachineSlot slot = GetSlotByID(index);

        return slot.CanBeSlotted(item);

    }

    public boolean IsItemFuel(ItemStack item)
    {
        return getItemBurnTime(item) > 0;
    }

    private int getItemBurnTime(ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return 0;
        }
        else
        {
            int amount = RecipeList.GetFuelBurnAmount(itemstack);

            if (_UseRegisteredFuel && amount == 0)
            {
                return TileEntityFurnace.getItemBurnTime(itemstack);
            }
            else
            {
                return amount;
            }
        }
    }

    @Override
    public void updateEntity()
    {
        boolean burning = BurnTime > 0;
        boolean stateChanged = false;

        if (isBurning())
        {
            BurnTime--;
        }

        if (!this.worldObj.isRemote)
        {
            if (BurnTime == 0 && canSmelt())
            {
                for (Object nextItem : GetItemsInSlotWithType(ComponentType.Fuel))
                {
                    if (nextItem != null)
                    {

                        ItemStack item = (ItemStack) nextItem;
                        CurrentItemBurnTime = BurnTime = getItemBurnTime(item);

                        if (isBurning())
                        {
                            stateChanged = true;

                            item.stackSize--;

                            if (item.stackSize == 0)
                            {
                                item = item.getItem().getContainerItem(item); // keep buckets
                            }

                            break; //if valid fuel is found, no need to look at other fuel
                        }
                    }
                }
            }

            if (isBurning() && canSmelt())
            {
                CookTime++;

                if (CookTime == _FurnaceSpeed)
                {
                    CookTime = 0;
                    smeltItem();

                    stateChanged = true;
                }
            }
            else
            {
                CookTime = 0;
            }

            if (burning != isBurning())
            {
                stateChanged = true;

                if (_InactiveBlock != null && _ActiveBlock != null)
                    updateMachineBlockState(BurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (stateChanged)
        {
            this.markDirty();
        }
    }

    private void updateMachineBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        GaltMachineBlock.SkipBreakEvent = true;

        if (_ActiveBlock != null && _InactiveBlock != null)
        {
            worldObj.setBlock(xCoord, yCoord, zCoord, active ? _ActiveBlock : _InactiveBlock);
        }

        GaltMachineBlock.SkipBreakEvent = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);


        this.validate(); // force update
        worldObj.setTileEntity(xCoord, yCoord, zCoord, this);
    }


    private List<ItemStack> GetItemsInSlotWithType(ComponentType slotType)
    {
        List<ItemStack> items = new ArrayList<ItemStack>();

        for (MachineSlot slot : Slots)
        {
            if (slot.SlotType == slotType)
            {
                items.add(slot.SlotItem);
            }
        }

        return items;
    }

    private List<MachineSlot> GetSlotsWithType(ComponentType slotType)
    {
        List<MachineSlot> items = new ArrayList<MachineSlot>();

        for (MachineSlot slot : Slots)
        {
            if (slot.SlotType == slotType)
            {
                items.add(slot);
            }
        }

        return items;
    }

    private void smeltItem()
    {
        MachineRecipeList.MachineRecipe recipe = RecipeList.GetSmeltingRecipe(Slots);

        if (recipe != null)
        {
            consumeRecipe(recipe);
        }
    }

    private void consumeRecipe(MachineRecipeList.MachineRecipe recipe)
    {
        removeItemsFromSlots(recipe.GetAllItemsOfType(ComponentType.Input), ComponentType.Input);
        removeItemsFromSlots(recipe.GetAllItemsOfType(ComponentType.Fuel), ComponentType.Input);
        removeItemsFromSlots(recipe.GetAllItemsOfType(ComponentType.RequiredItems), ComponentType.Input);
        addItemsToSlots(recipe.GetAllItemsOfType(ComponentType.Output), ComponentType.Output);
    }

    private void addItemsToSlots(List<ItemStack> itemstacks, ComponentType slotType)
    {
        List<ItemStack> slotItems = GetItemsInSlotWithType(slotType);
        List<ItemStack> items = new ArrayList<ItemStack>(itemstacks.size());
        for (ItemStack item : itemstacks)
            items.add(item.copy());

        // Iterate through the items to see if they are already in output lots. Add to existing stacks if they are.
        Iterator<ItemStack> itr = items.iterator();
        while (itr.hasNext())
        {
            ItemStack item = itr.next();
            for (ItemStack slottedItem : slotItems)
            {
                // if found already in output and there is room, dump some of the stack to the existing stack in slot
                if (slottedItem != null && item.isItemEqual(slottedItem) && slottedItem.stackSize < slottedItem.getMaxStackSize())
                {
                    if (item.stackSize + slottedItem.stackSize > slottedItem.getMaxStackSize())
                    {
                        int amountToMove = slottedItem.getMaxStackSize() - slottedItem.stackSize;
                        slottedItem.stackSize = slottedItem.getMaxStackSize();

                        item.stackSize -= amountToMove;
                    }
                    else
                    {
                        slottedItem.stackSize += item.stackSize;
                        itr.remove();
                        break;
                    }
                }
            }
        }

        // Put the remaining into empty slots
        List<MachineSlot> slots = GetSlotsWithType(slotType);
        for (ItemStack item : items)
        {
            for (MachineSlot slot : slots)
            {
                if (slot.SlotItem == null)
                {
                    slot.SlotItem = item;
                    break;
                }
            }
        }
    }

    private void removeItemsFromSlots(List<ItemStack> items, ComponentType slotType)
    {
        List<MachineSlot> slotItems = GetSlotsWithType(slotType);

        // Iterate through the items and remove from slots
        Iterator<ItemStack> itr = items.iterator();
        while (itr.hasNext())
        {
            ItemStack item = itr.next();
            for (MachineSlot slot : slotItems)
            {
                // if found check size. Either remove entire stack or partial
                if (slot.SlotItem != null && item.isItemEqual(slot.SlotItem))
                {
                    if (item.stackSize >= slot.SlotItem.stackSize)
                    {
                        int amountToMove = slot.SlotItem.stackSize;
                        slot.SlotItem = null;
                        item.stackSize -= amountToMove;

                        if (item.stackSize == 0)
                        {
                            itr.remove();
                            break;
                        }
                    }
                    else
                    {
                        slot.SlotItem.stackSize -= item.stackSize;
                        itr.remove();
                        break;
                    }
                }
            }
        }

    }


    private boolean canSmelt()
    {
        return RecipeList.CanSmelt(Slots);
    }

    public boolean isBurning()
    {
        return BurnTime > 0;
    }

    public int getSizeInventory()
    {
        return Slots.size();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        MachineSlot slot = GetSlotByID(index);
        if (slot != null && slot.SlotItem != null)
        {
            return slot.SlotItem;
        }

        return null;
    }

    public MachineSlot GetSlotByID(int index)
    {
        for (MachineSlot slot : Slots)
        {
            if (slot.ID == index)
            {
                return slot;
            }
        }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int amount)
    {
        // find slot
        MachineSlot slot = GetSlotByID(index);

        if (slot != null && slot.SlotItem != null)
        {
            ItemStack itemstack;

            if (slot.SlotItem.stackSize <= amount)
            {
                itemstack = slot.SlotItem;
                slot.SlotItem = null;
            }
            else
            {
                itemstack = slot.SlotItem.splitStack(amount);

                if (slot.SlotItem.stackSize <= 0)
                {
                    slot.SlotItem = null;
                }
            }

            return itemstack;
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        MachineSlot slot = GetSlotByID(index);

        if (slot.SlotItem != null && slot.SlotItem.stackSize > 0)
        {
            ItemStack itemstack = slot.SlotItem;
            slot.SlotItem = null;
            return itemstack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemstack)
    {
        MachineSlot slot = GetSlotByID(index);
        slot.SlotItem = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return _SlotsBottomDestination;
            case 1:
                return _SlotsTopDestination;
            default: //sides
                return _SlotsSideDestination;
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side)
    {
        return isItemValidForSlot(slot, item);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        return side != 0 || slot != 1 || item.getItem() == Items.bucket;
    }

    public int GetBurnTimeRemainingScaled(int scale)
    {
        if (CurrentItemBurnTime == 0)
        {
            CurrentItemBurnTime = _FurnaceSpeed;
        }

        return BurnTime * scale / CurrentItemBurnTime;
    }

    public int GetCookProgressScaled(int scale)
    {
        return CookTime * scale / _FurnaceSpeed;
    }

    public void SetStatuses(int status, int newValue)
    {
        switch (status)
        {
            case 0:
                CookTime = newValue;
                break;
            case 1:
                BurnTime = newValue;
                break;
            case 2:
                CurrentItemBurnTime = newValue;
                break;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);

        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (GetSlotByID(b) != null)
            {
                setInventorySlotContents(b, ItemStack.loadItemStackFromNBT(compound));
            }
        }

        BurnTime = (int) nbt.getShort("BurnTime");
        CookTime = (int) nbt.getShort("CookTime");
        CurrentItemBurnTime = (int) nbt.getShort("CurrentItemBurnTime");

        if (nbt.hasKey("CustomName"))
        {
            _LocalizedName = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) BurnTime);
        nbt.setShort("CookTime", (short) CookTime);
        nbt.setShort("CurrentItemBurnTime", (short) CurrentItemBurnTime);

        NBTTagList list = new NBTTagList();

        for (MachineSlot slot : Slots)
        {
            if (slot.SlotItem != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) slot.ID);
                slot.SlotItem.writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

        if (hasCustomInventoryName())
        {
            nbt.setString("CustomName", _LocalizedName);
        }
    }

    /**
     * Get the GUI cordinates for this TE. Puts 4 numbers: slotID, x, y, and furnace out put indicator (1=furnace, 0 otherwise)
     *
     * @return Returns the for int array: slotID, x, y, and furnace out put indicator (1=furnace, 0 otherwise)
     */
    public List<int[]> GetSlotsForContainer()
    {
        List<int[]> slotCoord = new ArrayList<int[]>();
        for (MachineSlot slot : Slots)
        {
            slotCoord.add(new int[]{
                    slot.ID, slot._GUIPosX, slot._GUIPosY,
                    (slot.SlotType == ComponentType.Output ? 1 : 0)
            });
        }
        return slotCoord;
    }

    public int[] GetSlotIDsForType(ComponentType componentType)
    {
        List<MachineSlot> slots = GetSlotsWithType(componentType);
        int[] slotIDs = new int[slots.size()];

        int counter = 0;
        for (MachineSlot slot : slots)
        {
            slotIDs[counter] = slot.ID;
            counter++;
        }

        return slotIDs;
    }

    public List<ItemStack> GetItemsstacksInSlots()
    {
        List<ItemStack> items = new ArrayList<ItemStack>();
        for (MachineSlot slot : Slots)
        {
            if (slot.SlotItem != null)
            {
                items.add(slot.SlotItem);
            }
        }

        return items;
    }


    public enum ComponentType
    {
        Input,
        Output,
        RequiredItems,
        Fuel
    }


    /**
     * Class used to store information about the tile slots
     */
    public class MachineSlot
    {
        public int ID;
        private boolean _Filtered = false;
        private List<ItemStack> _FilteredItems;
        public ComponentType SlotType;
        public ItemStack SlotItem;
        private int _GUIPosX;
        private int _GUIPosY;

        public MachineSlot(int id, ComponentType slotType, int guiPosX, int guiPosY)
        {
            ID = id + 100; //to not conflict with other slots later
            SlotType = slotType;

            _GUIPosX = guiPosX;
            _GUIPosY = guiPosY;
        }

        public MachineSlot(int id, ComponentType slotType, int guiPosX, int guiPosY, ItemStack[] filteredItems)
        {
            this(id, slotType, guiPosX, guiPosY);

            if (filteredItems != null && filteredItems.length > 0)
            {
                _Filtered = true;
                _FilteredItems = Arrays.asList(filteredItems);
            }
        }

        public boolean CanBeSlotted(ItemStack itemStack)
        {
            if (SlotType == ComponentType.Output) return false;

            if (!_Filtered) return true;

            for (ItemStack item : _FilteredItems)
            {
                if (itemStack.isItemEqual(item))
                {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * This stores the entire list of recipes a galt machine can use, as well as a list of fuels if the recipe burns fuel constantly.
     */
    protected class MachineRecipeList
    {
        private List<MachineRecipe> _Recipes;
        private List<MachineFuel> _Fuel;

        public MachineRecipeList()
        {
            _Recipes = new ArrayList<MachineRecipe>();
            _Fuel = new ArrayList<MachineFuel>();
        }

        public void AddRecipe(MachineRecipe recipe)
        {
            _Recipes.add(recipe);
        }

        public boolean CanSmelt(List<MachineSlot> slots)
        {

            // loop through the recipes and see if one matches the slots' contents.
            for (MachineRecipe recipe : _Recipes)
            {
                if (recipe.CanSmelt(slots))
                {
                    return true;
                }
            }

            return false;
        }

        public MachineRecipe GetSmeltingRecipe(List<MachineSlot> slots)
        {
            for (MachineRecipe recipe : _Recipes)
            {
                if (recipe.CanSmelt(slots))
                {
                    return recipe;
                }
            }

            return null;
        }

        public void CalculateAndAddOreDictionaryEntries()
        {
            /// TODO
        }

        public void AddRecipe(ItemStack[] inputs, ItemStack[] fuel, ItemStack[] required, ItemStack[] output)
        {
            _Recipes.add(new MachineRecipe(inputs, fuel, required, output));
        }

        public boolean IsFuel(ItemStack item)
        {
            for (MachineFuel fuels : _Fuel)
            {
                if (fuels.FuelItem.isItemEqual(item))
                {
                    return true;
                }
            }

            return false;
        }

        public void AddFuel(ItemStack item, int burnValue)
        {
            _Fuel.add(new MachineFuel(item, burnValue));
        }

        public int GetFuelBurnAmount(ItemStack item)
        {
            for (MachineFuel fuel : _Fuel)
            {
                if (fuel.FuelItem.isItemEqual(item))
                {
                    return fuel.BurnValue;
                }
            }

            return 0;
        }


        /**
         * This is a basic container to store fuels and their burn values
         */
        protected class MachineFuel
        {
            public ItemStack FuelItem;
            public int BurnValue;

            public MachineFuel(ItemStack fuelItem, int burnValue)
            {
                FuelItem = fuelItem;
                BurnValue = burnValue;
            }
        }

        /**
         * This stores a complete recipe that a galt machine can use.
         */
        protected class MachineRecipe
        {
            private List<RecipeComponent> _RecipeComponents;

            public MachineRecipe()
            {
                _RecipeComponents = new ArrayList<RecipeComponent>();
            }

            public MachineRecipe(ItemStack[] inputs, ItemStack[] fuel, ItemStack[] required, ItemStack[] output)
            {
                _RecipeComponents = new ArrayList<RecipeComponent>();

                AddComponentRange(inputs, ComponentType.Input);
                AddComponentRange(fuel, ComponentType.Fuel);
                AddComponentRange(required, ComponentType.RequiredItems);
                AddComponentRange(output, ComponentType.Output);
            }

            public void AddComponent(ItemStack itemStack, ComponentType type)
            {
                _RecipeComponents.add(new RecipeComponent(itemStack, type));
            }

            public void AddComponentRange(ItemStack[] items, ComponentType type)
            {
                if (items != null)
                    for (ItemStack item : items)
                    {
                        AddComponent(item, type);
                    }
            }

            public boolean CanSmelt(List<MachineSlot> slots)
            {
                // Seperate out recipe comp types
                List<ItemStack> recipeinputs = new ArrayList<ItemStack>();
                List<ItemStack> recipefuel = new ArrayList<ItemStack>();
                List<ItemStack> reciperequired = new ArrayList<ItemStack>();
                List<ItemStack> recipeoutput = new ArrayList<ItemStack>(); // used later when checking for space to put output

                for (RecipeComponent component : _RecipeComponents)
                {
                    if (component.Type == ComponentType.Input)
                    {
                        recipeinputs.add(component.Component);
                    }
                    else if (component.Type == ComponentType.Fuel)
                    {
                        recipefuel.add(component.Component);
                    }
                    else if (component.Type == ComponentType.RequiredItems)
                    {
                        reciperequired.add(component.Component);
                    }
                    else if (component.Type == ComponentType.Output)
                    {
                        recipeoutput.add(component.Component);
                    }
                }

                // separate out slot types
                List<ItemStack> slotinputs = new ArrayList<ItemStack>();
                List<ItemStack> slotfuel = new ArrayList<ItemStack>();
                List<ItemStack> slotrequired = new ArrayList<ItemStack>();

                for (MachineSlot slot : slots)
                {
                    if (slot.SlotItem != null) //only pull filled slots
                    {
                        if (slot.SlotType == ComponentType.Input)
                        {
                            slotinputs.add(slot.SlotItem);
                        }
                        else if (slot.SlotType == ComponentType.Fuel)
                        {
                            slotfuel.add(slot.SlotItem);
                        }
                        else if (slot.SlotType == ComponentType.RequiredItems)
                        {
                            slotrequired.add(slot.SlotItem);
                        }
                    }
                }

                // quick check to see if recipes match
                if (slotinputs.size() != recipeinputs.size()) return false;
                if (slotfuel.size() < recipefuel.size()) return false;
                if (slotrequired.size() != reciperequired.size()) return false;

                // in depth check
                if (compareItemStacks(recipeinputs, slotinputs)) return false;
                if (compareItemStacks(recipefuel, slotfuel)) return false;
                if (compareItemStacks(reciperequired, slotrequired)) return false;

                // obtain the output slots for analysis
                List<MachineSlot> outputSlots = new ArrayList<MachineSlot>();
                for (MachineSlot slot : slots)
                {
                    if (slot.SlotType == ComponentType.Output)
                    {
                        outputSlots.add(slot);
                    }
                }

                // check to see if items in output are same type and have room
                Iterator<ItemStack> itr = recipeoutput.iterator();
                while (itr.hasNext())
                {
                    ItemStack item = itr.next();
                    for (MachineSlot slot : outputSlots)
                    {
                        if (slot.SlotItem != null)
                        {
                            if (item.isItemEqual(slot.SlotItem) && item.stackSize + slot.SlotItem.stackSize < item.getMaxStackSize())
                            {
                                itr.remove();
                            }
                        }
                    }
                }

                // if more slots for output are needed, check to see how many empty slots are left.
                int neededOutput = recipeoutput.size();
                if (neededOutput > 0)
                {
                    for (MachineSlot slot : outputSlots)
                    {
                        if (slot.SlotItem == null)
                        {
                            neededOutput--;
                        }
                    }

                    if (neededOutput > 0) return false;
                }

                return true;
            }

            private boolean compareItemStacks(List<ItemStack> recipeinputs, List<ItemStack> slotinputs)
            {
                for (ItemStack recipeItem : recipeinputs)
                {
                    boolean found = false;

                    for (ItemStack slotItem : slotinputs)
                    {
                        if (recipeItem.isItemEqual(slotItem) && slotItem.stackSize >= recipeItem.stackSize)
                        {
                            found = true;
                            break;
                        }
                    }

                    if (!found) return true;
                }
                return false;
            }

            public List<ItemStack> GetAllItemsOfType(ComponentType componentType)
            {
                List<ItemStack> items = new ArrayList<ItemStack>();
                for (RecipeComponent component : _RecipeComponents)
                {
                    if (component.Type == componentType)
                    {
                        items.add(component.Component);
                    }
                }

                return items;
            }

            /**
             * it is one component of a recipe, whether it's an input, output, fuel or required item.
             */
            protected class RecipeComponent
            {
                public ItemStack Component;
                public ComponentType Type;

                public RecipeComponent(ItemStack component, ComponentType type)
                {
                    Component = component;
                    Type = type;
                }
            }
        }
    }
}




