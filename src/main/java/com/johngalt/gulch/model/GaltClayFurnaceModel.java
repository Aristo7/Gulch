// Date: 7/4/2014 12:39:43 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package com.johngalt.gulch.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GaltClayFurnaceModel extends ModelBase
{
  //fields
    ModelRenderer Side1;
    ModelRenderer Corner1;
    ModelRenderer Side3;
    ModelRenderer Side4;
    ModelRenderer OpenSideB;
    ModelRenderer Corder2;
    ModelRenderer Corner3;
    ModelRenderer Corner4;
    ModelRenderer Bottom;
    ModelRenderer Top;
    ModelRenderer BowlBottom;
    ModelRenderer BowlTop;
    ModelRenderer BSide4;
    ModelRenderer BSide2;
    ModelRenderer BSide3;
    ModelRenderer BSide1;
    ModelRenderer OpenSideLeft;
    ModelRenderer OpneSideRight;
    ModelRenderer OPenSideUp;
    ModelRenderer Shape1;
  
  public GaltClayFurnaceModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Side1 = new ModelRenderer(this, 0, 0);
      Side1.addBox(0F, 0F, 0F, 12, 10, 1);
      Side1.setRotationPoint(-6F, 14F, 7F);
      Side1.setTextureSize(64, 32);
      Side1.mirror = true;
      setRotation(Side1, 0F, 0F, 0F);
      Corner1 = new ModelRenderer(this, 0, 0);
      Corner1.addBox(0F, 0F, 0F, 1, 10, 1);
      Corner1.setRotationPoint(6F, 14F, 6F);
      Corner1.setTextureSize(64, 32);
      Corner1.mirror = true;
      setRotation(Corner1, 0F, 0F, 0F);
      Side3 = new ModelRenderer(this, 0, 0);
      Side3.addBox(0F, 0F, 0F, 12, 10, 1);
      Side3.setRotationPoint(-6F, 14F, -8F);
      Side3.setTextureSize(64, 32);
      Side3.mirror = true;
      setRotation(Side3, 0F, 0F, 0F);
      Side4 = new ModelRenderer(this, 0, 0);
      Side4.addBox(0F, 0F, 0F, 1, 10, 12);
      Side4.setRotationPoint(-8F, 14F, -6F);
      Side4.setTextureSize(64, 32);
      Side4.mirror = true;
      setRotation(Side4, 0F, 0F, 0F);
      OpenSideB = new ModelRenderer(this, 0, 0);
      OpenSideB.addBox(0F, 0F, 0F, 1, 3, 6);
      OpenSideB.setRotationPoint(7F, 21F, -3F);
      OpenSideB.setTextureSize(64, 32);
      OpenSideB.mirror = true;
      setRotation(OpenSideB, 0F, 0F, 0F);
      Corder2 = new ModelRenderer(this, 0, 0);
      Corder2.addBox(0F, 0F, 0F, 1, 10, 1);
      Corder2.setRotationPoint(6F, 14F, -7F);
      Corder2.setTextureSize(64, 32);
      Corder2.mirror = true;
      setRotation(Corder2, 0F, 0F, 0F);
      Corner3 = new ModelRenderer(this, 0, 0);
      Corner3.addBox(0F, 0F, 0F, 1, 10, 1);
      Corner3.setRotationPoint(-7F, 14F, -7F);
      Corner3.setTextureSize(64, 32);
      Corner3.mirror = true;
      setRotation(Corner3, 0F, 0F, 0F);
      Corner4 = new ModelRenderer(this, 0, 0);
      Corner4.addBox(0F, 0F, 0F, 1, 10, 1);
      Corner4.setRotationPoint(-7F, 14F, 6F);
      Corner4.setTextureSize(64, 32);
      Corner4.mirror = true;
      setRotation(Corner4, 0F, 0F, 0F);
      Bottom = new ModelRenderer(this, 0, 0);
      Bottom.addBox(0F, 0F, 0F, 12, 1, 12);
      Bottom.setRotationPoint(-6F, 23F, -6F);
      Bottom.setTextureSize(64, 32);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 0);
      Top.addBox(0F, 0F, 0F, 12, 1, 12);
      Top.setRotationPoint(-6F, 15F, -6F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      BowlBottom = new ModelRenderer(this, 0, 0);
      BowlBottom.addBox(0F, 0F, 0F, 8, 1, 8);
      BowlBottom.setRotationPoint(-4F, 13F, -4F);
      BowlBottom.setTextureSize(64, 32);
      BowlBottom.mirror = true;
      setRotation(BowlBottom, 0F, 0F, 0F);
      BowlTop = new ModelRenderer(this, 0, 0);
      BowlTop.addBox(0F, 0F, 0F, 4, 1, 4);
      BowlTop.setRotationPoint(-2F, 14F, -2F);
      BowlTop.setTextureSize(64, 32);
      BowlTop.mirror = true;
      setRotation(BowlTop, 0F, 0F, 0F);
      BSide4 = new ModelRenderer(this, 0, 0);
      BSide4.addBox(0F, 0F, 0F, 8, 3, 1);
      BSide4.setRotationPoint(-4F, 10F, -5F);
      BSide4.setTextureSize(64, 32);
      BSide4.mirror = true;
      setRotation(BSide4, 0F, 0F, 0F);
      BSide2 = new ModelRenderer(this, 0, 0);
      BSide2.addBox(0F, 0F, 0F, 1, 3, 10);
      BSide2.setRotationPoint(-5F, 10F, -5F);
      BSide2.setTextureSize(64, 32);
      BSide2.mirror = true;
      setRotation(BSide2, 0F, 0F, 0F);
      BSide3 = new ModelRenderer(this, 0, 0);
      BSide3.addBox(0F, 0F, 0F, 8, 3, 1);
      BSide3.setRotationPoint(-4F, 10F, 4F);
      BSide3.setTextureSize(64, 32);
      BSide3.mirror = true;
      setRotation(BSide3, 0F, 0F, 0F);
      BSide1 = new ModelRenderer(this, 0, 0);
      BSide1.addBox(0F, 0F, 0F, 1, 3, 10);
      BSide1.setRotationPoint(4F, 10F, -5F);
      BSide1.setTextureSize(64, 32);
      BSide1.mirror = true;
      setRotation(BSide1, 0F, 0F, 0F);
      OpenSideLeft = new ModelRenderer(this, 0, 0);
      OpenSideLeft.addBox(0F, 0F, 0F, 1, 7, 3);
      OpenSideLeft.setRotationPoint(7F, 17F, -6F);
      OpenSideLeft.setTextureSize(64, 32);
      OpenSideLeft.mirror = true;
      setRotation(OpenSideLeft, 0F, 0F, 0F);
      OpneSideRight = new ModelRenderer(this, 0, 0);
      OpneSideRight.addBox(0F, 0F, 0F, 1, 7, 3);
      OpneSideRight.setRotationPoint(7F, 17F, 3F);
      OpneSideRight.setTextureSize(64, 32);
      OpneSideRight.mirror = true;
      setRotation(OpneSideRight, 0F, 0F, 0F);
      OPenSideUp = new ModelRenderer(this, 0, 0);
      OPenSideUp.addBox(0F, 0F, 0F, 1, 3, 12);
      OPenSideUp.setRotationPoint(7F, 14F, -6F);
      OPenSideUp.setTextureSize(64, 32);
      OPenSideUp.mirror = true;
      setRotation(OPenSideUp, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 28, 20);
      Shape1.addBox(0F, 0F, 0F, 8, 2, 10);
      Shape1.setRotationPoint(-4F, 21F, -5F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Side1.render(f5);
    Corner1.render(f5);
    Side3.render(f5);
    Side4.render(f5);
    OpenSideB.render(f5);
    Corder2.render(f5);
    Corner3.render(f5);
    Corner4.render(f5);
    Bottom.render(f5);
    Top.render(f5);
    BowlBottom.render(f5);
    BowlBottom.render(f5);
    BSide4.render(f5);
    BSide2.render(f5);
    BSide3.render(f5);
    BSide1.render(f5);
    OpenSideLeft.render(f5);
    OpneSideRight.render(f5);
    OPenSideUp.render(f5);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
