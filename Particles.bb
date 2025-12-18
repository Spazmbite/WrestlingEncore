;//////////////////////////////////////////////////////////////////////////////
;--------------------- WRESTLING ENCORE: PARTICLE EFFECTS ---------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;/////////////////////// PARTICLE EFFECTS ////////////////////////
;-----------------------------------------------------------------

;LOAD PARTICLE EFFECTS
Function LoadParticles()
 For cyc=1 To no_particles
  part(cyc)=LoadSprite("World/Sprites/Particle.bmp")
  EntityFX part(cyc),9
  partState(cyc)=0
  HideEntity part(cyc)
 Next
End Function

;CREATE PARTICLE EFFECT
Function CreateParticle(x#,y#,z#,style)
If optFX>0
 ;find empty spot
 cyc=0
 For count=1 To no_particles
  If partState(count)=0 Then cyc=count
 Next
 ;force spot!
 If cyc=0 Then cyc=Rnd(1,no_particles)
 ;activate new particle
 If cyc>0
  partX#(cyc)=x#
  partY#(cyc)=y#
  partZ#(cyc)=z#
  partA#(cyc)=Rnd(0,360)
  partGravity#(cyc)=Rnd(1.0,2.0)
  partFlight#(cyc)=0.3 
  partSize#(cyc)=Rnd(1.0,5.0)
  partAlpha#(cyc)=Rnd(0.5,0.9)
  partFade#(cyc)=0.02 
  ;unique traits
  partType(cyc)=style
  If partType(cyc)=1 ;fire
   EntityColor part(cyc),220,Rnd(0,100),0 
  EndIf
  If partType(cyc)=2 ;smoke
   randy=Rnd(0,100)
   EntityColor part(cyc),randy,randy,randy 
   partFlight#(cyc)=0.1
   partFade#(cyc)=0.0075
  EndIf
  If partType(cyc)=3 ;blood
   EntityColor part(cyc),Rnd(50,200),0,0
   partFlight#(cyc)=0.15 : partSize#(cyc)=Rnd(1.0,4.0)
   partGravity#(cyc)=Rnd(1.0,1.5)
   partAlpha#(cyc)=Rnd(0.8,1.0)
   partFade#(cyc)=0.03
  EndIf
  If partType(cyc)=4 ;impact
   EntityColor part(cyc),Rnd(90,110),Rnd(70,90),Rnd(40,60);250,Rnd(100,200),0
   partFlight#(cyc)=0.15 : partSize#(cyc)=Rnd(1.0,4.0)
   partGravity#(cyc)=Rnd(1.0,1.5)
   partFade#(cyc)=0.03
  EndIf
  If partType(cyc)=5 ;dust
   EntityColor part(cyc),100,80,50 
   partAlpha#(cyc)=Rnd(0.2,0.5)
   ;partSize#(cyc)=Rnd(1.0,4.0)
   partGravity#(cyc)=0.5
  EndIf
  If partType(cyc)=6 ;water
   EntityColor part(cyc),40,80,120
   partGravity#(cyc)=Rnd(0.75,1.25)
   partFlight#(cyc)=0.3 
   partFade#(cyc)=0.015
  EndIf
  If partType(cyc)=7 ;small fire
   EntityColor part(cyc),220,Rnd(0,100),0
   partSize#(cyc)=Rnd(1.0,4.0)
   partGravity#(cyc)=Rnd(0.75,1.25)
   partFade#(cyc)=0.03
  EndIf
  If partType(cyc)=8 ;multi-coloured
   EntityColor part(cyc),Rnd(100,250),Rnd(100,250),Rnd(100,250) 
  EndIf
  If partType(cyc)=9 ;green mist
   EntityColor part(cyc),0,Rnd(100,180),0
   partGravity#(cyc)=Rnd(0.75,1.25)
   partFade#(cyc)=0.03
  EndIf
  If partType(cyc)=10 ;explosion (fire)
   EntityColor part(cyc),220,Rnd(0,100),0
   partSize#(cyc)=Rnd(5.0,10.0)
   partAlpha#(cyc)=Rnd(0.8,1.0)
  EndIf
  If partType(cyc)=11 ;explosion (foam)
   randy=Rnd(100,200)
   EntityColor part(cyc),randy,randy,randy
   partSize#(cyc)=Rnd(5.0,10.0)
   partAlpha#(cyc)=Rnd(0.6,0.8)
  EndIf
  If partType(cyc)=12 ;explosion (water)
   EntityColor part(cyc),40,80,120
   partSize#(cyc)=Rnd(5.0,10.0)
   partAlpha#(cyc)=Rnd(0.7,0.9)
  EndIf
  If partType(cyc)=13 ;explosion (beer)
   EntityColor part(cyc),Rnd(50,150),50,0
   partSize#(cyc)=Rnd(5.0,10.0)
   partAlpha#(cyc)=Rnd(0.7,0.9)
  EndIf
  If partType(cyc)=14 ;beer (small)
   EntityColor part(cyc),Rnd(50,150),50,0
   partGravity#(cyc)=Rnd(0.75,1.25)
   partFlight#(cyc)=0.3 
  EndIf
  ;reset & show
  partTim(cyc)=0
  partState(cyc)=1
  ShowEntity part(cyc)
  PositionEntity part(cyc),partX#(cyc),partY#(cyc),partZ#(cyc)
  RotateEntity part(cyc),0,partA#(cyc),0
  ScaleSprite part(cyc),partSize#(cyc),partSize#(cyc)
  EntityAlpha part(cyc),partAlpha#(cyc)
 EndIf
EndIf
End Function

;CREATE SPURT OF PARTICLES
Function CreateSpurt(x#,y#,z#,density,style)
 If optFX>0
  If (optFX=<2 And (style=1 Or style=7)) Or optFX=<1 Then density=density/2
  For count=1 To density 
   If style<99 Then CreateParticle(Rnd(x#-1,x#+1),Rnd(y#-1,y#+1),Rnd(z#-1,z#+1),style)
   If style=99 
    CreateParticle(Rnd(x#-1,x#+1),Rnd(y#-1,y#+1),Rnd(z#-1,z#+1),3) 
    CreateParticle(Rnd(x#-1,x#+1),Rnd(y#-1,y#+1),Rnd(z#-1,z#+1),4)
   EndIf
  Next
 EndIf
End Function

;CREATE SPIT MIST
Function CreateSpit(cyc,style)
 limb=pLimb(cyc,1)
 If optFX=<2 Then density=5 Else density=10
 For count=1 To density
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1)+1,EntityZ(limb,1)
  RotateEntity dummy,EntityPitch(limb,1),EntityYaw(limb,1),EntityRoll(limb,1)
  pusher=Rnd(3,15) : spray=pusher/5
  MoveEntity dummy,0,0,pusher
  CreateParticle(EntityX(dummy,1)+Rnd(-spray,spray),EntityY(dummy,1)+Rnd(-spray,spray),EntityZ(dummy,1)+Rnd(-spray,spray),style)
 Next
End Function

;PARTICLE CYCLE
Function ParticleCycle()
 For cyc=1 To no_particles
  If partState(cyc)>0
   ;gravity
   If partGravity#(cyc)>-3.0 Then partGravity#(cyc)=partGravity#(cyc)-0.1
   If partType(cyc)=2 And partGravity#(cyc)<0.1 Then partGravity#(cyc)=0.1
   partY#(cyc)=partY#(cyc)+partGravity#(cyc)
   ;flight
   MoveEntity part(cyc),0,0,partFlight#(cyc)
   partX#(cyc)=EntityX(part(cyc))
   partZ#(cyc)=EntityZ(part(cyc))
   ;update properties
   PositionEntity part(cyc),partX#(cyc),partY#(cyc),partZ#(cyc)
   RotateEntity part(cyc),0,partA#(cyc),0
   ScaleSprite part(cyc),partSize#(cyc),partSize#(cyc)
   ;transparency
   partAlpha#(cyc)=partAlpha#(cyc)-partFade#(cyc)
   EntityAlpha part(cyc),partAlpha#(cyc)
   ;clock
   partTim(cyc)=partTim(cyc)+1
   If partAlpha#(cyc)=<0 Or partTim(cyc)>1000 Then partState(cyc)=0
  EndIf
  ;remove
  If partState(cyc)=0 Then HideEntity part(cyc)
 Next
End Function

;-----------------------------------------------------------------
;///////////////////////// EXPLOSIONS ////////////////////////////
;-----------------------------------------------------------------

;TRIGGER EXPLOSION
Function CreateExplosion(trigger,x#,y#,z#,style)
 If optFX>0
  ;find empty slot
  cyc=0
  For count=1 To no_explodes
   If exTim(count)=0 Then cyc=count
  Next
  If cyc=0 Then cyc=Rnd(1,no_explodes)
  ;initiate explosion
  exType(cyc)=style : exTim(cyc)=20
  exX#(cyc)=x# : exY#(cyc)=y# : exZ#(cyc)=z#
  For v=1 To no_plays
   exHurt(cyc,v)=0
  Next
  ;sound effect
  Pop(0,Rnd(4,7),1)
  If exType(cyc)=10 Then ProduceSound(0,sExplosion,0,1)
  If exType(cyc)=>11 
   ProduceSound(0,sExplosion,0,0.6)
   ProduceSound(0,sSplash,22050,0)
  EndIf
 EndIf
End Function

;EXPLOSION CYCLE
Function ExplosionCycle()
 For cyc=1 To no_explodes
  If exTim(cyc)>0
   ;blaze
   If exTim(cyc)=20 Or exTim(cyc)=15 Or exTim(cyc)=10 Or exTim(cyc)=5
    If optFX=<2 Then density=12 Else density=25
    For count=1 To density
     CreateParticle(exX#(cyc)+Rnd(-15,15),Rnd(exY#(cyc)-5,exY#(cyc)+10),exZ#(cyc)+Rnd(-15,15),exType(cyc))
    Next 
    If optFX=<2 Then density=7 Else density=15
    For count=1 To density
     CreateParticle(exX#(cyc)+Rnd(-10,10),exY#(cyc)+Rnd(-5,5),exZ#(cyc)+Rnd(-10,10),exType(cyc))
    Next
    If optFX=<2 Then density=2 Else density=5
    For count=1 To density
     CreateParticle(exX#(cyc)+Rnd(-5,5),exY#(cyc),exZ#(cyc)+Rnd(-5,5),exType(cyc))
    Next
    If optFX=<2 Then density=5 Else density=10
    For count=1 To density
     CreateParticle(exX#(cyc)+Rnd(-10,10),Rnd(exY#(cyc),exY#(cyc)+5),exZ#(cyc)+Rnd(-10,10),2)
    Next
   EndIf
   ;mess
   If exTim(cyc)=10 And exType(cyc)=>11 
    ;ProduceSound(0,sBleed,22050,0.5)
    CreatePool(exX#(cyc),exZ#(cyc),Rnd(10.0,15.0),1,exType(cyc)-9)
   EndIf
   ;human damage
   If exTim(cyc)=>5 And exTim(cyc)=<15
    For v=1 To no_plays
     If BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),30) Then pDazed(v)=Rnd(100,300)
     If exHurt(cyc,v)=0 And BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),20) ;And AttackViable(v)>0
      Pop(0,Rnd(4,8),0)
      If exType(cyc)=10 Then ProduceSound(p(v),sIgnite,22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0.5)
      If exType(cyc)=10 
       If AttackViable(v)=2 Then impactY#=pY#(cyc)+3 Else impactY#=pY#(cyc)+23
       If AttackViable(v)>0 Then CreateSpurt(RealX#(v),impactY#,RealZ#(v),10,2)
       If CountScars(cyc)=>4 Then CreatePool(RealX#(v),RealZ#(v),Rnd(5.0,10.0),3,1)
       ScarArea(v,0,0,0,1) : ScarArea(v,0,0,0,1)
       pHealth(v)=pHealth(v)-10 : RiskInjury(v,1)
      EndIf
      pHP(v)=0
      If pAnim(v)=4 Then pA#(v)=pA#(v)+180
      If AttackViable(v)=1 Then ImpactChecks(v) : ChangeAnim(v,Rnd(70,71))
      If AttackViable(v)=2 Then GroundImpactChecks(v)
      If BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),10) Or AttackViable(v)=2
       If InsideRing(pX#(v),pZ#(v),-40) Or InsideRing(pX#(v),pZ#(v),0)=0 Then ChangeAnim(v,Rnd(72,73))
      EndIf
      exHurt(cyc,v)=1
     EndIf
    Next
   EndIf
   ;damage items
   If exTim(cyc)=15 And exType(cyc)=10
    For v=1 To no_items
     If BlastProximity(cyc,iX#(v),iY#(v),iZ#(v),30)
      ProduceSound(i(v),iSound(iType(v)),22050,1)
      If iState(v)=0 Then Animate i(v),3,2.0,1,0 Else Animate i(v),3,2.0,2,5
      iState(v)=1 : iA#(v)=iA#(v)+Rnd(-20,20)
      iX#(v)=iX#(v)+Rnd(-2,2) : iZ#(v)=iZ#(v)+Rnd(-2,2)
      If iBurning(v)=0 And iFlammable(iType(v)) Then IgniteItem(v)
     EndIf
    Next
    For v=1 To no_weaps
     If BlastProximity(cyc,weapX#(v),weapY#(v),weapZ#(v),15) And weapBurning(v)=0 And weapFlammable(weapType(v))>0
      IgniteWeapon(v)
     EndIf
    Next
   EndIf
   ;extinguish items
   If exTim(cyc)=15 And exType(cyc)>10
    For v=1 To no_items
     If BlastProximity(cyc,iX#(v),iY#(v),iZ#(v),30) And iBurning(v)>0 Then ExtinguishItem(v)
    Next
    For v=1 To no_weaps
     If BlastProximity(cyc,weapX#(v),weapY#(v),weapZ#(v),30) And weapBurning(v)>0 Then ExtinguishWeapon(v)
    Next
   EndIf
   ;affect ropes
   If exTim(cyc)=15 And exY#(cyc)=wStage#
    For v=1 To 4
     If exX#(cyc)>wBlockX1#(v)-10 And exX#(cyc)<wBlockX2#(v)+10 And exZ#(cyc)>wBlockZ1#(v)-10 And exZ#(cyc)<wBlockZ2#(v)+10
      randy=Rnd(1,3)
      If randy=1 And exType(cyc)=10 And ropeBurning(v)=0 Then IgniteRope(v)
      If randy=2 And exType(cyc)=10 And ropeBurning(v+4)=0 Then IgniteRope(v+4)
      If randy=3 And exType(cyc)=10 And ropeBurning(v+8)=0 Then IgniteRope(v+8)
      If exType(cyc)>10 And ropeBurning(v)>0 Then ExtinguishRope(v)
      If exType(cyc)>10 And ropeBurning(v+4)>0 Then ExtinguishRope(v+4)
      If exType(cyc)>10 And ropeBurning(v+8)>0 Then ExtinguishRope(v+8)
     EndIf
    Next
   EndIf
   ;expire
   exTim(cyc)=exTim(cyc)-1
  EndIf
 Next
 ;BLAST GIMMICK
 ;initial blast
 If matchBlastTim>0 And matchMins=matchBlastTim And matchBlasted=0
  PlaySound sBuzzer : matchBlasted=1
  CreateExplosion(0,-50,wStage,50,10) : CreateExplosion(0,50,wStage,50,10)
  CreateExplosion(0,-50,wStage,-50,10) : CreateExplosion(0,50,wStage,-50,10) 
  CreateExplosion(0,0,wStage,0,10)
 EndIf
 ;follow-up blasts
 randy=Rnd(0,80)
 If randy=0 And matchState=3 And matchBlastTim>0 And matchMins=>matchBlastTim And matchBlasted=1
  x#=Rnd(-130,130) : z#=Rnd(-130,130)
  If InsideRing(x#,z#,-5) Then y#=wStage# Else y#=wGround#
  CreateExplosion(0,x#,y#,z#,10)
 EndIf
End Function

;BLAST PROXIMITY
Function BlastProximity(cyc,x#,y#,z#,range)
 value=0
 If x#>exX#(cyc)-range And x#<exX#(cyc)+range And z#>exZ#(cyc)-range And z#<exZ#(cyc)+range And y#>exY#(cyc)-50 And y#<exY#(cyc)+50
  value=1
 EndIf
 Return value
End Function

;IGNITE ITEM
Function IgniteItem(cyc)
 If optFX=>3 And iFlammable(iType(cyc))
  ;effect
  Pop(0,Rnd(4,7),0)
  ProduceSound(i(cyc),sIgnite,22050,1)
  LoopSound sFire
  iChannel(cyc)=EmitSound(sFire,i(cyc))
  CreateSpurt(iX#(cyc),iY#(cyc),iZ#(cyc),10,1) 
  iBurning(cyc)=Rnd(100,800)
  ;char material
  If iScar(cyc)<1 Then iScar(cyc)=1
  For limb=1 To CountChildren(i(cyc))
   If tCharred>0 Then EntityTexture GetChild(i(cyc),limb),tCharred,0,3
  Next
 EndIf
End Function

;EXTINGUISH ITEM
Function ExtinguishItem(cyc)
 ProduceSound(i(cyc),sExpire,22050,1)
 If ChannelPlaying(iChannel(cyc)) Then StopChannel iChannel(cyc)
 CreateSpurt(iX#(cyc),iY#(cyc),iZ#(cyc),10,2)
 iBurning(cyc)=0
End Function

;IGNITE WEAPON
Function IgniteWeapon(cyc)
 If optFX>0 And weapFlammable(weapType(cyc))
  Pop(0,Rnd(4,7),0)
  ProduceSound(weap(cyc),sIgnite,22050,1)
  LoopSound sFire
  weapChannel(cyc)=EmitSound(sFire,weap(cyc))
  CreateSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),10,1)
  If weapScar(cyc)<1 Then weapScar(cyc)=1 
  weapBurning(cyc)=Rnd(100,800)
 EndIf
End Function

;EXTINGUISH WEAPON
Function ExtinguishWeapon(cyc)
 ProduceSound(weap(cyc),sExpire,22050,1)
 If ChannelPlaying(weapChannel(cyc)) Then StopChannel weapChannel(cyc)
 CreateSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),10,2)
 weapBurning(cyc)=0
End Function

;IGNITE ROPE
Function IgniteRope(cyc)
 If optFX=>3 And matchRopes=0
  ;effect
  Pop(0,Rnd(4,7),0)
  ProduceSound(rope(cyc),sIgnite,22050,1)
  LoopSound sFire
  ropeChannel(cyc)=EmitSound(sFire,rope(cyc)) 
  ropeBurning(cyc)=Rnd(100,800)
  ;char material
  For count=1 To 4
   If tCharred>0 Then EntityTexture FindChild(rope(cyc),"Rope0"+count),tCharred,0,3
  Next
 EndIf
End Function

;ROPE BURN
Function RopeBurn(cyc,style) ;0=whole side, 1-3=specific level
 ;find nearest ropes
 roper=NearRopes(cyc,5)
 ;find matches
 If roper>0
  effect=0
  For v=1 To 12
   contactX#=FindRopeX#(v,RealX#(cyc))
   contactZ#=FindRopeZ#(v,RealZ#(cyc))
   If v=roper*style Or (style=0 And (v=roper+4 Or v=roper+8))
    ;flaming impact
    If ropeBurning(v)>0 Or matchRopes=3 
     If effect=0
      Pop(0,Rnd(4,8),1)
      ProduceSound(p(cyc),sIgnite,22050,0)
      ProduceSound(p(cyc),sPain(Rnd(2,3)),GetVoice(cyc),1)
      ScarArea(cyc,contactX#,ropeY#(v),contactZ#,5)
      pHealth(cyc)=pHealth(cyc)-Rnd(1,3) : pHP(cyc)=pHP(cyc)-Rnd(1,5)
      effect=1
     EndIf
     CreateSpurt(contactX#,ropeY#(v)-2,contactZ#,5,1)
     CreateSpurt(contactX#,ropeY#(v)-2,contactZ#,5,2)
    EndIf
    ;barbed/electric impact
    If matchRopes=>1 And matchRopes=<2
     If effect=0
      Pop(0,Rnd(4,8),1)
      ProduceSound(p(cyc),sImpact(3),22050,0.3)
      ProduceSound(p(cyc),sImpactBlade,22050,0.3)
      ProduceSound(p(cyc),sPain(Rnd(2,3)),GetVoice(cyc),1)
      ScarArea(cyc,contactX#,ropeY#(v),contactZ#,5)
      pHealth(cyc)=pHealth(cyc)-Rnd(1,3) : pHP(cyc)=pHP(cyc)-Rnd(1,5)
      effect=1
     EndIf
     If matchRopes=1 Then CreateSpurt(contactX#,ropeY#(v)-2,contactZ#,15,99)
     If matchRopes=2
      CreateSpurt(contactX#,ropeY#(v)-2,contactZ#,10,8)
      CreateSpurt(contactX#,ropeY#(v)-2,contactZ#,5,2)
      randy=Rnd(0,3)
      If randy=0 Then CreateExplosion(p(cyc),contactX#,35,contactZ#,10)
     EndIf
    EndIf
   EndIf
  Next
 EndIf
End Function

;EXTINGUISH ROPE
Function ExtinguishRope(cyc)
 ProduceSound(rope(cyc),sExpire,22050,1)
 If ChannelPlaying(ropeChannel(cyc)) Then StopChannel ropeChannel(cyc)
 ropeBurning(cyc)=0
End Function

;-----------------------------------------------------------------
;/////////////////////// GORE & SCARRING /////////////////////////
;-----------------------------------------------------------------

;LOAD POOLS
Function LoadPools()
 If optFX=>2
  For cyc=1 To no_pools
   pool(cyc)=LoadSprite("World/Sprites/Pool.bmp",4)
   SpriteViewMode pool(cyc),2
   EntityFX pool(cyc),0
   HideEntity pool(cyc)
   poolState(cyc)=0
  Next
 EndIf
End Function

;PRODUCE POOL
Function CreatePool(x#,z#,size#,layers,style)
 If optFX=>2 And (optGore>0 Or style<>1)
  For count=1 To layers
   ;find empty spot
   cyc=0
   For count=1 To no_pools
    If poolState(count)=0 Then cyc=count
   Next
   ;force spot!
   If cyc=0 Then cyc=Rnd(1,no_pools)
   ;generate pool
   poolX#(cyc)=x# : poolZ#(cyc)=z#
   If count>1 Then poolX#(cyc)=x#+Rnd(-5,5) : poolZ#(cyc)=z#+Rnd(-5,5)
   poolA#(cyc)=Rnd(0,360) : poolY#(cyc)=wGround#+0.1
   If InsideRing(x#,z#,-10) Then poolY#(cyc)=wStage#+0.55
   poolSize#(cyc)=size# : poolAlpha#(cyc)=0.7 
   poolState(cyc)=1 : ShowEntity pool(cyc)
   ;colour variations
   poolType(cyc)=style
   If style=1 Then EntityColor pool(cyc),Rnd(150,220),0,0 ;blood
   If style=2 Then EntityColor pool(cyc),255,255,255 ;foam
   If style=3 Then EntityColor pool(cyc),100,200,255 ;water
   If style=4 Then EntityColor pool(cyc),150,50,0 ;beer
  Next
 EndIf
End Function

;POOL CYCLE
Function PoolCycle()
 For cyc=1 To no_pools
  If poolState(cyc)=1
   ;location
   PositionEntity pool(cyc),poolX#(cyc),poolY#(cyc),poolZ#(cyc)
   RotateEntity pool(cyc),90,poolA#(cyc),0
   ;fade away
   If poolType(cyc)=1 Then poolAlpha#(cyc)=poolAlpha#(cyc)-0.0001
   If poolType(cyc)=>2 Then poolAlpha#(cyc)=poolAlpha#(cyc)-0.001
   EntityAlpha pool(cyc),poolAlpha#(cyc)
   ;shrink away
   If poolType(cyc)=1 Then poolSize#(cyc)=poolSize#(cyc)-0.005
   If poolType(cyc)=>2 Then poolSize#(cyc)=poolSize#(cyc)-0.01
   ScaleSprite pool(cyc),poolSize#(cyc),poolSize#(cyc)
   ;remove
   If poolSize#(cyc)<0.5 Or poolAlpha#(cyc)<0.01 Then poolState(cyc)=0 : HideEntity pool(cyc)
  EndIf
 Next
End Function

;SCAR SPECIFIC LIMB
Function ScarLimb(cyc,limb,chance)
 If limb=0 Then chance=chance*2
 randy=Rnd(0,chance)
 If randy=0 And pScar(cyc,limb)=<4
  ;add scarring
  pScar(cyc,limb)=pScar(cyc,limb)+1
  If pScar(cyc,limb)=>2 And limb>0 And pLimb(cyc,limb)>0
   vol#=pScar(cyc,limb)*0.25
   ProduceSound(0,sBleed,22050,Rnd(0.25,vol#))
   limbX#=EntityX(pLimb(cyc,limb),1)
   limbY#=EntityY(pLimb(cyc,limb),1)
   limbZ#=EntityZ(pLimb(cyc,limb),1)
   CreateParticle(limbX#,limbY#,limbZ#,3)
   CreatePool(limbX#,limbZ#,Rnd(1.0,3.0),1,1) 
  EndIf
  ;lose accessories?
  If limb=1
   randy=Rnd(0,8) ;lose accessories!
   If randy=0 And charHat(pChar(cyc),pCostume(cyc))>0
    HideEntity FindChild(p(cyc),"BandA")
    HideEntity FindChild(p(cyc),"BandB")
    HideEntity FindChild(p(cyc),"CapA")
    HideEntity FindChild(p(cyc),"Hat")
    HideEntity FindChild(p(cyc),"Helmet")
    HideEntity FindChild(p(cyc),"Horns")
   EndIf
   If randy=1 And charSpecs(pChar(cyc),pCostume(cyc))>0 Then HideEntity FindChild(p(cyc),"Specs")
   If randy=2 And charSpecs(pChar(cyc),pCostume(cyc))>0 Then HideEntity FindChild(p(cyc),"LensA")
   If randy=3 And charSpecs(pChar(cyc),pCostume(cyc))>0 Then HideEntity FindChild(p(cyc),"LensB")
   pDazed(cyc)=Rnd(100,300)
  EndIf
  ;lose limb?
  If pScar(cyc,limb)>4 
   pScar(cyc,limb)=4
   If optLimbs=1 Then LoseLimb(cyc,limb,chance)
   If optLimbs=2 Then LoseLimb(cyc,limb,0)
  EndIf
 EndIf
End Function

;SCAR WHOLE BODY
Function ScarArea(cyc,x#,y#,z#,chance)
 For limb=0 To 40
  If pLimb(cyc,limb)>0
   limbX#=EntityX(pLimb(cyc,limb),1)
   limbY#=EntityY(pLimb(cyc,limb),1)
   limbZ#=EntityZ(pLimb(cyc,limb),1)
   If x#>limbX#-8 And x#<limbX#+8 And z#>limbZ#-8 And z#<limbZ#+8 And y#>limbY#-8 And y#<limbY#+8
    If limb=1 Then risk=chance*2 Else risk=chance
    ScarLimb(cyc,limb,risk)
   EndIf
  EndIf
  If limb=1 Then risk=chance*3 Else risk=chance*2
  If checkX#=0 And checkY#=0 And checkZ#=0 Then ScarLimb(cyc,limb,risk)
 Next
End Function

;BURN HANDS
Function BurnHands(cyc,style) ;-1=lying, 0=weapon, 1=item
 ;effect
 ProduceSound(p(cyc),sIgnite,22050,0)
 ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),1)
 ;scar hand(s)
 If style=1
  For limb=6 To 16
   ScarLimb(cyc,limb,5)
  Next
 EndIf
 For limb=19 To 29
  ScarLimb(cyc,limb,5)
 Next
 ;adjust state
 DropWeapon(cyc,0)
 If style=>0 Then ChangeAnim(cyc,61)
 If style=-1 Then ChangeAnim(cyc,63)
 ;scare CPU
 pAgenda(cyc)=0 : pNowhere(cyc)=99
End Function

;LOSE LIMB
Function LoseLimb(cyc,limb,chance)
 randy=Rnd(0,chance)
 If randy=0 And limbSource(limb)>0 And pScar(cyc,limbSource(limb))=<4
  If pScar(cyc,limbPrecede(limb))=5 Or limbPrecede(limb)=0
   ;pain and mess
   Pop(0,7,1)
   ProduceSound(p(cyc),sBleed,22050,1)
   ProduceSound(p(cyc),sPain(Rnd(5,6)),GetVoice(cyc),1)
   limbX#=EntityX(pLimb(cyc,limb),1)
   limbY#=EntityY(pLimb(cyc,limb),1)
   limbZ#=EntityZ(pLimb(cyc,limb),1)
   CreateSpurt(limbX#,limbY#,limbZ#,15,99)
   CreatePool(limbX#,limbZ#,Rnd(5.0,15.0),5,1)  
   ;remove limb
   DropWeapon(cyc,0)
   pScar(cyc,limbSource(limb))=4
   If limbSource(limb)=>4 And limbSource(limb)=<29 And tSeverArms>0 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverArms,0,6
   If limbSource(limb)=>30 And limbSource(limb)=<36 And tSeverLegs>0 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverLegs,0,6
   pScar(cyc,limb)=5
   HideEntity pLimb(cyc,limb)
   If (limb=>7 And limb=<16) Or (limb=>20 And limb=<29) Then finger=1 Else finger=0
   If finger=1 And optLimbs=<1 And pInjured(cyc)<1 Then pInjured(cyc)=1
   If finger=0 And optLimbs=2 And pInjured(cyc)=<1 Then pInjured(cyc)=pInjured(cyc)+1
   If finger=0 And optLimbs=<1 Then pInjured(cyc)=2
   pHealth(cyc)=pHealth(cyc)/2 : pHP(cyc)=0
  EndIf
 EndIf
End Function

;MANAGE SCARS
Function ManageScars(cyc)
 For limb=0 To 40
  If pScar(cyc,limb)=<4
   ;heal scars
   randy=Rnd(0,10000)
   If limb=1 And randy=<5 And tFaceScar(0)>0 Then EntityTexture pLimb(cyc,1),tFaceScar(0),0,6 ;remove green stain
   If randy=0 Or (randy=1 And limb=1) 
    If matchBlood=0 Or limb<>1 Then pScar(cyc,limb)=pScar(cyc,limb)-1
   EndIf
   If pScar(cyc,limb)<0 Then pScar(cyc,limb)=0
   ;prevent gore
   If (optGore=0 Or limb=0) And pScar(cyc,limb)>1 Then pScar(cyc,limb)=1
   ;apply scars (standard)
   If pScar(cyc,limb)<>pOldScar(cyc,limb) And pLimb(cyc,limb)>0
    If limb=1 And tFaceScar(pScar(cyc,limb))>0
     EntityTexture pLimb(cyc,limb),tFaceScar(pScar(cyc,limb)),0,5
     EntityTexture pLimb(cyc,37),tFaceScar(pScar(cyc,limb)),0,5
     hatter=pScar(cyc,limb)-1
     If hatter>0
      EntityTexture FindChild(p(cyc),"BandA"),tFaceScar(hatter),0,5
      EntityTexture FindChild(p(cyc),"BandB"),tFaceScar(hatter),0,5
      EntityTexture FindChild(p(cyc),"CapA"),tFaceScar(hatter),0,5
      EntityTexture FindChild(p(cyc),"Hat"),tFaceScar(hatter),0,5
      EntityTexture FindChild(p(cyc),"Helmet"),tFaceScar(hatter),0,5
      EntityTexture FindChild(p(cyc),"Horns"),tFaceScar(hatter),0,5
     EndIf
    EndIf
    If limb=>2 And limb=<3 And tBodyScar(pScar(cyc,limb))>0 Then EntityTexture pLimb(cyc,limb),tBodyScar(pScar(cyc,limb)),0,5
    If limb=>4 And limb=<29 And tArmScar(pScar(cyc,limb))>0 Then EntityTexture pLimb(cyc,limb),tArmScar(pScar(cyc,limb)),0,5
    If limb=>30 And limb=<36 And tLegScar(pScar(cyc,limb))>0 Then EntityTexture pLimb(cyc,limb),tLegScar(pScar(cyc,limb)),0,5
   EndIf
   ;apply scars (to hair)
   If pScar(cyc,limb)<>pOldScar(cyc,limb) And limb=0 And tBodyScar(pScar(cyc,limb))>0
    For count=1 To 15
     EntityTexture FindChild(p(cyc),hairFile$(count)),tBodyScar(pScar(cyc,limb)),0,5
    Next
   EndIf
   ;store status
   pOldScar(cyc,limb)=pScar(cyc,limb)
  EndIf
 Next 
End Function

;COUNT SCARS
Function CountScars(cyc)
 value=0
 For limb=1 To 40
  If pLimb(cyc,limb)>0 And pScar(cyc,limb)=>2 And limbSource(limb)<>6 And limbSource(limb)<>19
   If limb=3 Then value=value+2 Else value=value+1
  EndIf
 Next 
 Return value
End Function

;SCAR ITEM
Function ScarItem(cyc,chance)
 randy=Rnd(0,chance)
 If randy=0 Then iScar(cyc)=iScar(cyc)+1
 If iScar(cyc)>4 Then iScar(cyc)=4 
End Function

;MANAGE ITEM SCARS
Function ManageItemScars(cyc)
 ;heal scars
 randy=Rnd(0,6000)
 If randy=0 Then iScar(cyc)=iScar(cyc)-1
 If iScar(cyc)<0 Then iScar(cyc)=0
 ;prevent gore
 If optGore=0 And iScar(cyc)>1 Then iScar(cyc)=1
 ;apply scars
 If iScar(cyc)<>iOldScar(cyc) And tBodyScar(iScar(cyc))>0
  For limb=1 To CountChildren(i(cyc))
   EntityTexture GetChild(i(cyc),limb),tBodyScar(iScar(cyc)),0,2
  Next
 EndIf
 ;store status
 iOldScar(cyc)=iScar(cyc)
End Function

;SCAR WEAPON
Function ScarWeapon(cyc,chance)
 randy=Rnd(0,chance)
 If randy=0 Then weapScar(cyc)=weapScar(cyc)+1
 If weapScar(cyc)>4 Then weapScar(cyc)=4 
End Function

;MANAGE WEAPON SCARS
Function ManageWeaponScars(cyc)
 ;heal scars
 randy=Rnd(0,6000)
 If randy=0 Then weapScar(cyc)=weapScar(cyc)-1
 If weapScar(cyc)<0 Then weapScar(cyc)=0
 ;prevent gore
 If optGore=0 And weapScar(cyc)>1 Then weapScar(cyc)=1
 ;apply scars
 If weapScar(cyc)<>weapOldScar(cyc) And tBodyScar(weapScar(cyc))>0
  For limb=1 To CountChildren(weap(cyc))
   EntityTexture GetChild(weap(cyc),limb),tBodyScar(weapScar(cyc)),0,2
  Next
  If weapCarrier(cyc)>0
   limb=FindChild(p(weapCarrier(cyc)),weapFile$(weapType(cyc)))
   EntityTexture limb,tBodyScar(weapScar(cyc)),0,2 
  EndIf
 EndIf
 ;store status
 weapOldScar(cyc)=weapScar(cyc)
End Function