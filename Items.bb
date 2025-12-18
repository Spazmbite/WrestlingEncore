;//////////////////////////////////////////////////////////////////////////////
;--------------------- WRESTLING ENCORE: ITEMS & WEAPONS ----------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;//////////////////////// LOAD ITEMS /////////////////////////////
;-----------------------------------------------------------------
Function LoadItems()
 For cyc=1 To no_items
  ;selection
  If itemSelection=<1 Then iType(cyc)=Rnd(1,itemList) ;random
  If itemSelection=1 ;standard override
   If cyc=1 Or cyc=3 Then iType(cyc)=3
   If cyc=2 Or cyc=4 Or cyc=8 Then iType(cyc)=Rnd(1,2)
   If cyc=7 Or cyc=11 Then iType(cyc)=7
   If cyc=5 Or cyc=6 Or cyc=9 Then iType(cyc)=9
   If cyc=10
    Repeat
     iType(cyc)=Rnd(1,itemList)
    Until iType(cyc)<>3 And iType(cyc)<>7 And iType(cyc)<>9 And iType(cyc)<>14
   EndIf
   If cyc>3 And iType(cyc)=3 Then iType(cyc)=1
   If game=1 And gamGimmick(slot,GetDate())=7 And cyc=>8 Then iType(cyc)=14
  EndIf
  If itemSelection=2 ;one of each
   If cyc=1 Then itemRotor=Rnd(1,itemList) 
   itemRotor=itemRotor+1
   If itemRotor>itemList Then itemRotor=1
   iType(cyc)=itemRotor
  EndIf
  If itemSelection=>3 Then iType(cyc)=itemSelection-2 ;specific
  ;generate
  i(cyc)=LoadAnimMesh(iFile$(iType(cyc)))
  ExtractAnimSeq(i(cyc),0,60,0) ;1. break animation
  ExtractAnimSeq(i(cyc),70,100,0) ;2. broken animation
  ScaleEntity i(cyc),0.4,0.4,0.4 
  ;texture variations
  If iType(cyc)=2 
   For count=1 To 3
    If tMetal(1)>0 Then EntityTexture FindChild(i(cyc),"Table0"+count),tMetal(1) ;metal table
    EntityShininess FindChild(i(cyc),"Table0"+count),0.5
   Next
  EndIf
  If iType(cyc)=>4 And iType(cyc)=<6 
   For count=1 To 6
    If iType(cyc)=5 And tWood(2)>0 Then EntityTexture FindChild(i(cyc),"Side0"+count),tWood(2) ;wooden crate
    If iType(cyc)=6 And tCage(3)>0 Then EntityTexture FindChild(i(cyc),"Side0"+count),tCage(3) ;metal crate
    If iType(cyc)=6 Then EntityShininess FindChild(i(cyc),"Side0"+count),0.5
   Next
  EndIf
  If iType(cyc)=8 ;shiny trashcan
   EntityShininess FindChild(i(cyc),"Lid"),0.5
   For count=1 To 8
    EntityShininess FindChild(i(cyc),"Object0"+count),0.5
   Next
  EndIf
  If iType(cyc)=9 ;shiny chair
   EntityShininess FindChild(i(cyc),"Back"),0.5
   EntityShininess FindChild(i(cyc),"Legs01"),0.5
   EntityShininess FindChild(i(cyc),"Legs02"),0.5
  EndIf
  If iType(cyc)=10 Or iType(cyc)=11
   EntityColor FindChild(i(cyc),"Top"),150,150,150
   EntityColor FindChild(i(cyc),"Support01"),150,150,150
   EntityColor FindChild(i(cyc),"Support02"),150,150,150
   For count=1 To CountChildren(i(cyc))
    If iType(cyc)=10 And tMetal(2)>0 Then EntityTexture GetChild(i(cyc),count),tMetal(2) ;metal ladder
    If iType(cyc)=10 Then EntityShininess GetChild(i(cyc),count),0.5
    If iType(cyc)=11 And tWood(1)>0 Then EntityTexture GetChild(i(cyc),count),tWood(1) ;wooden ladder
   Next
  EndIf
  If iType(cyc)=12 ;shiny railing
   For count=1 To 4
    EntityShininess FindChild(i(cyc),"Leg0"+count),0.5
    EntityShininess FindChild(i(cyc),"Sign0"+count),0.5
   Next
  EndIf
  If iType(cyc)=13 ;shiny statue
   For count=1 To CountChildren(i(cyc))
    EntityShininess GetChild(i(cyc),count),0.5
   Next
  EndIf
  If iType(cyc)=14 ;glass pane
   For count=1 To CountChildren(i(cyc))
    EntityShininess FindChild(i(cyc),"Glass"),0.5
    EntityAlpha FindChild(i(cyc),"Glass"),0.6
   Next
  EndIf
  ;location
  its=0
  Repeat 
   conflict=0 : its=its+1
   randy=Rnd(2,5)
   If itemLayout=0 And randy=5 Then randy=Rnd(2,4)
   If itemLayout=1 And randy=2 Then randy=4
   If game=1 And gamGimmick(slot,GetDate())=7 And iType(cyc)=14 Then randy=2
   If itemLayout=2 Or (randy=2 And itemLayout=<1) Then iX#(cyc)=Rnd(-70,70) : iZ#(cyc)=Rnd(-70,70) ;inside ring
   If itemLayout=3 Or (randy=3 And itemLayout=<1)
    Repeat
     iX#(cyc)=Rnd(-130,130) : iZ#(cyc)=Rnd(-130,130) ;surrounding ring
    Until InsideRing(iX#(cyc),iZ#(cyc),10)=0 
   EndIf
   If itemLayout=4 Or (randy=>4 And itemLayout=<1) Then iX#(cyc)=Rnd(-180,220) : iZ#(cyc)=Rnd(580,830) ;backstage
   For v=1 To no_items
    If v<cyc And iX#(cyc)>iX#(v)-20 And iX#(cyc)<iX#(v)+20 And iZ#(cyc)>iZ#(v)-20 And iZ#(cyc)<iZ#(v)+20
     conflict=1
    EndIf
   Next
  Until conflict=0 Or its>100
  iA#(cyc)=Rnd(0,360)
  ;standard override
  If itemLayout=1
   If cyc=1 Then iX#(cyc)=-118 : iZ#(cyc)=135 : iA#(cyc)=180 ;upper left railing
   If cyc=2 Then iX#(cyc)=119 : iZ#(cyc)=135 : iA#(cyc)=180 ;upper right railing
   If cyc=3 Then iX#(cyc)=119 : iZ#(cyc)=-134 : iA#(cyc)=0 ;lower right railing
   If cyc=4 Then iX#(cyc)=-118 : iZ#(cyc)=-134 : iA#(cyc)=0 ;lower left railing
   If cyc=5 And iType(cyc)=9 Then iX#(cyc)=-123 : iZ#(cyc)=145 : iA#(cyc)=0 ;upper left chair
   If cyc=6 And iType(cyc)=9 Then iX#(cyc)=124 : iZ#(cyc)=145 : iA#(cyc)=0 ;upper right chair
   If cyc=7 And iType(cyc)=7 Then iX#(cyc)=-130 : iZ#(cyc)=115 : iA#(cyc)=220 ;TV at left table
   If cyc=8 Then iX#(cyc)=160 : iZ#(cyc)=690 : iA#(cyc)=180 ;table in locker area
   If cyc=9 And iType(cyc)=9 Then iX#(cyc)=160 : iZ#(cyc)=715 : iA#(cyc)=335 ;chair in locker area
   If cyc=10 Then iX#(cyc)=-170 : iZ#(cyc)=813 : iA#(cyc)=180 ;upper left of backstage
   If cyc=11 And iType(cyc)=7 Then iX#(cyc)=130 : iZ#(cyc)=115 : iA#(cyc)=140 ;TV at right table
   ;If cyc=5 Then iX#(cyc)=0 : iZ#(cyc)=-134 : iA#(cyc)=0 ;south of ring
   ;If cyc=12 Then iX#(cyc)=-133 : iZ#(cyc)=0 : iA#(cyc)=270 ;left of ring
   ;If cyc=13 Then iX#(cyc)=134 : iZ#(cyc)=0 : iA#(cyc)=90 ;right of ring
   ;If cyc=9 Then iX#(cyc)=34 : iZ#(cyc)=819 : iA#(cyc)=180 ;upper door of backstage
   ;If cyc=12 Then iX#(cyc)=-176 : iZ#(cyc)=652 : iA#(cyc)=270 ;left door of backstage 
   ;If cyc=13 Then iX#(cyc)=60 : iZ#(cyc)=584 : iA#(cyc)=0 ;lower right of backstage
   ;If cyc=14 Then iX#(cyc)=-150 : iZ#(cyc)=584 : iA#(cyc)=0 ;lower left of backstage
   ;If cyc=15 Then iX#(cyc)=-65 : iZ#(cyc)=584 : iA#(cyc)=0 ;left of backstage entrance 
  EndIf
  iY#(cyc)=wGround#
  If InsideRing(iX#(cyc),iZ#(cyc),0) Then iY#(cyc)=wStage#
  ;reset data
  iState(cyc)=0 : iOldState(cyc)=-1 : iCarrier(cyc)=0 
  iScar(cyc)=0 : iBurning(cyc)=0
 Next
End Function

;-----------------------------------------------------------------
;/////////////////////// ITEM CYCLE //////////////////////////////
;-----------------------------------------------------------------
Function ItemCycle()
 For cyc=1 To no_items
  ;determine ground
  If InsideRing(iX#(cyc),iZ#(cyc),-5) Then iGround#(cyc)=wStage# Else iGround#(cyc)=wGround#
  ;trigger fall to floor
  If InsideRing(iX#(cyc),iZ#(cyc),-5)=0 And iY#(cyc)>wGround# And iCarrier(cyc)=0 And iAnim(cyc)=0
   iAnim(cyc)=10 : iAnimTim(cyc)=0
  EndIf
  ;falling/dropping process
  If iAnim(cyc)=10
   iY#(cyc)=iY#(cyc)-1.4 
   iA#(cyc)=iA#(cyc)+0.5
   PositionEntity dummy,iX#(cyc),iY#(cyc),iZ#(cyc)
   RotateEntity dummy,0,iFallA#(cyc),0
   MoveEntity dummy,0,0,0.15
   iX#(cyc)=EntityX(dummy) : iZ#(cyc)=EntityZ(dummy)
   If iY#(cyc)<iGround#(cyc)
    ProduceSound(i(cyc),sItem,22050,0)
    ProduceSound(i(cyc),sThud,22050,0.3)
    iAnim(cyc)=11 : iAnimTim(cyc)=0
    If iState(cyc)=0 Then iAnimTim(cyc)=10
    iY#(cyc)=iGround#(cyc)
   EndIf
  EndIf
  ;bounce process
  If iAnim(cyc)=11
   If iAnimTim(cyc)=<4 Then tY#=iGround#(cyc)+2
   If iAnimTim(cyc)=>5 And iAnimTim(cyc)=<9 Then tY#=iGround#(cyc)
   If iAnimTim(cyc)=>10 And iAnimTim(cyc)=<13 Then tY#=iGround#(cyc)+1
   If iAnimTim(cyc)=>14 Then tY#=iGround#(cyc)
   If iY#(cyc)<tY# Then iY#(cyc)=iY#(cyc)+0.25
   If iY#(cyc)>tY# Then iY#(cyc)=iY#(cyc)-0.25
   If iAnimTim(cyc)=8 Then ProduceSound(i(cyc),sItem,22050,0.4)
   If iAnimTim(cyc)>18 Then iY#(cyc)=iGround#(cyc) : iAnim(cyc)=0 
  EndIf
  ;burning
  If iBurning(cyc)>0
   If optFX=<2 Then density=2 Else density=10
   For count=1 To density
    CreateParticle(iX#(cyc)+Rnd(-10,10),iY#(cyc),iZ#(cyc)+Rnd(-10,10),1)
   Next
   If optFX=>3
    For count=1 To 5
     CreateParticle(iX#(cyc)+Rnd(-5,5),iY#(cyc),iZ#(cyc)+Rnd(-5,5),1)
    Next
   EndIf
   CreateParticle(iX#(cyc)+Rnd(-10,10),iY#(cyc),iZ#(cyc)+Rnd(-10,10),2)
   iBurning(cyc)=iBurning(cyc)-1
   If iBurning(cyc)=<0 Or iState(cyc)=0 Then ExtinguishItem(cyc)
  EndIf
  ;screen
  If optVideo>0 And iType(cyc)=7
   randy=Rnd(0,100)
   If randy=0 Then iScreen(cyc)=Rnd(1,8)
   If iScreen(cyc)>7 Then iScreen(cyc)=6 
   If iScreen(cyc)=6 And wVideoCaught=0 Then iScreen(cyc)=5
   If iState(cyc)=1 Then iScreen(cyc)=0
   If iScreen(cyc)<>iOldScreen(cyc) And tVideo(iScreen(cyc))>0
    EntityTexture FindChild(i(cyc),"Screen"),tVideo(iScreen(cyc))
    iOldScreen(cyc)=iScreen(cyc)
   EndIf
   randy=Rnd(0,2)
   If randy=0 And iScreen(cyc)=7 Then PositionTexture tVideo(7),1,Rnd(0.0,2.0)
  EndIf
  ;glass breakage
  If iType(cyc)=14 And iState(cyc)<>iOldState(cyc) And tGlass(iState(cyc)+1)>0 
   EntityTexture FindChild(i(cyc),"Glass"),tGlass(iState(cyc)+1)
   iOldState(cyc)=iState(cyc)
  EndIf
  ;display
  ManageItemScars(cyc)
  PositionEntity i(cyc),iX#(cyc),iY#(cyc),iZ#(cyc)
  RotateEntity i(cyc),0,iA#(cyc),0
  ;increment animations
  iAnimTim(cyc)=iAnimTim(cyc)+1
 Next
End Function

;-----------------------------------------------------------------
;////////////////////// LOAD WEAPONS /////////////////////////////
;-----------------------------------------------------------------
Function LoadWeapons()
 For cyc=1 To no_weaps
  ;selection
  If weapType(cyc)=0
   If weapSelection=<1 ;random
    Repeat
     weapType(cyc)=Rnd(1,weapList) 
    Until weapStandard(weapType(cyc))=>0
   EndIf
   If weapSelection=1 ;standard override
    randy=Rnd(0,4)
    If (randy=0 And weapStandard(weapType(cyc))=<0) Or weapStandard(weapType(cyc))=-1
     Repeat
      weapType(cyc)=Rnd(1,weapList)
     Until weapStandard(weapType(cyc))=1
    EndIf
    If game=1 And gamGimmick(slot,GetDate())=6 And cyc=>5 Then weapType(cyc)=19
   EndIf
   If weapSelection=2 ;one of each
    If cyc=1 Then weapRotor=Rnd(1,weapList) 
    weapRotor=weapRotor+1
    If weapRotor>weapList Then weapRotor=1
    weapType(cyc)=weapRotor
   EndIf
   If weapSelection=>3 Then weapType(cyc)=weapSelection-2 ;specific
  EndIf
  ;generate
  weap(cyc)=LoadAnimMesh("Items/Weapons/"+weapFile$(weapType(cyc))+".3DS")
  ScaleEntity weap(cyc),0.4,0.4,0.4
  If weapTex(weapType(cyc))>0
   For count=1 To CountChildren(weap(cyc))
    EntityTexture GetChild(weap(cyc),count),weapTex(weapType(cyc))
    If weapType(cyc)=16 Or weapType(cyc)=17 Then EntityAlpha GetChild(weap(cyc),count),0.75
   Next
  EndIf
  If weapShiny(weapType(cyc))>0
   For count=1 To CountChildren(weap(cyc))
    EntityShininess GetChild(weap(cyc),count),1.0
   Next
  EndIf
  If weapType(cyc)=28 Then EntityTexture FindChild(weap(cyc),"Tacks"),tTacks
  ;location
  its=0
  Repeat 
   conflict=0 : its=its+1
   randy=Rnd(1,4)
   If weapLayout=1 And randy=<2 ;standard tidy
    If weapStandard(weapType(cyc))=<0 Then randy=4 Else randy=3
   EndIf
   If game=1 And gamGimmick(slot,GetDate())=6 And weapType(cyc)=19 Then randy=2
   If randy=1 And weapLayout=<1 Then weapX#(cyc)=Rnd(-25,20) : weapZ#(cyc)=Rnd(130,440) ;aisle way
   If weapLayout=2 Or (randy=2 And weapLayout=<1) Then weapX#(cyc)=Rnd(-70,70) : weapZ#(cyc)=Rnd(-70,70) ;inside ring
   If weapLayout=3 Or (randy=3 And weapLayout=<1)
    Repeat
     weapX#(cyc)=Rnd(-130,130) : weapZ#(cyc)=Rnd(-130,130) ;surrounding ring
    Until InsideRing(weapX#(cyc),weapZ#(cyc),0)=0 
   EndIf
   If weapLayout=4 Or (randy=>4 And weapLayout=<1) Then weapX#(cyc)=Rnd(-180,220) : weapZ#(cyc)=Rnd(580,830) ;backstage
   For v=1 To no_weaps
    If v<cyc And weapX#(cyc)>weapX#(v)-10 And weapX#(cyc)<weapX#(v)+10 And weapZ#(cyc)>weapZ#(v)-10 And weapZ#(cyc)<weapZ#(v)+10
     conflict=1
    EndIf
   Next
  Until conflict=0 Or its>100
  weapA#(cyc)=Rnd(0,360) : weapY#(cyc)=wGround# 
  If InsideRing(weapX#(cyc),weapZ#(cyc),-5) Then weapY#(cyc)=wStage#
  ;vacant title positions
  If matchChamps=2 And weapType(cyc)=21 Then weapX#(cyc)=0 : weapZ#(cyc)=-100 : weapA#(cyc)=270 : weapY#(cyc)=wGround#
  If matchChamps=3 And weapType(cyc)=22 Then weapX#(cyc)=0 : weapZ#(cyc)=-100 : weapA#(cyc)=270 : weapY#(cyc)=wGround#
  If matchChamps=4 And weapType(cyc)=23 Then weapX#(cyc)=Rnd(-10,10) : weapZ#(cyc)=Rnd(-105,-95) : weapA#(cyc)=270 : weapY#(cyc)=wGround#
  ;reset data
  weapCarrier(cyc)=0 : weapAnim(cyc)=0
  weapBurning(cyc)=0 : weapScar(cyc)=0
 Next
End Function

;-----------------------------------------------------------------
;////////////////////// WEAPON CYCLE /////////////////////////////
;-----------------------------------------------------------------
Function WeaponCycle()
 For cyc=1 To no_weaps
  ;find ground
  weapGround#(cyc)=wGround#
  If InsideRing(weapX#(cyc),weapZ#(cyc),-10) Then weapGround#(cyc)=wStage#
  ;preserve old positions
  weapOldX#(cyc)=weapX#(cyc) : weapOldZ#(cyc)=weapZ#(cyc)
  ;fall
  If weapAnim(cyc)=10
   If weapAnimTim(cyc)=0 Then Animate weap(cyc),3,Rnd(2.0,3.0)
   If weapGravity#(cyc)>-3 Then weapGravity#(cyc)=weapGravity#(cyc)-weapWeight#(weapType(cyc))
   weapY#(cyc)=weapY#(cyc)+weapGravity#(cyc)
   weapA#(cyc)=weapA#(cyc)+1
   If weapY#(cyc)=<weapGround#(cyc)
    RiskExplosion(cyc,10)
    ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,0)
    CreateSpurt(weapX#(cyc),weapY#(cyc)+3,weapZ#(cyc),10,5)
    weapAnim(cyc)=30 : weapAnimTim(cyc)=0
	weapY#(cyc)=weapGround#(cyc)
   EndIf
  EndIf
  ;fly
  If weapAnim(cyc)=20
   If weapAnimTim(cyc)=0 Then Animate weap(cyc),3,Rnd(1.0,2.0)
   If weapGravity#(cyc)>-3 Then weapGravity#(cyc)=weapGravity#(cyc)-weapWeight#(weapType(cyc))
   weapY#(cyc)=weapY#(cyc)+weapGravity#(cyc)
   weapA#(cyc)=weapA#(cyc)+5
   PositionEntity weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc)
   RotateEntity weap(cyc),0,weapFlightA#(cyc),0
   MoveEntity weap(cyc),0,0,weapFlight#(cyc)
   weapX#(cyc)=EntityX(weap(cyc)) : weapZ#(cyc)=EntityZ(weap(cyc))
   FlyingWeaponImpacts(cyc)
   If weapY#(cyc)=<weapGround#(cyc)
    RiskExplosion(cyc,5) 
	ProduceSound(weap(cyc),weapSound(weapType(cyc)),22000,0) 
	CreateSpurt(weapX#(cyc),weapY#(cyc)+3,weapZ#(cyc),10,5)
	weapFlightA#(cyc)=weapFlightA#(cyc)+Rnd(-30,30)
	weapA#(cyc)=weapA#(cyc)+Rnd(-30,30)
	weapAnim(cyc)=30 : weapAnimTim(cyc)=0 
	weapY#(cyc)=weapGround#(cyc)
   EndIf
  EndIf
  ;bounce
  If weapAnim(cyc)=30
   If weapAnimTim(cyc)=0 
	Animate weap(cyc),3,Rnd(1.0,3.0)
	weapBounce#(cyc)=Rnd(2.0,4.0)
	If weapType(cyc)=28 Then weapBounce#(cyc)=Rnd(0.25,1.0)  
	For count=1 To no_plays
	 weapSting(cyc,count)=0
	Next
   EndIf
   If weapGravity#(cyc)>-3 Then weapGravity#(cyc)=weapGravity#(cyc)-weapWeight#(weapType(cyc))
   weapY#(cyc)=weapY#(cyc)+weapGravity#(cyc)
   PositionEntity weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc)
   RotateEntity weap(cyc),0,weapFlightA#(cyc),0
   MoveEntity weap(cyc),0,0,weapFlight#(cyc)
   weapX#(cyc)=EntityX(weap(cyc)) : weapZ#(cyc)=EntityZ(weap(cyc))
   FlyingWeaponImpacts(cyc)
   If weapY#(cyc)=<weapGround#(cyc)
	If weapBounce#(cyc)=>0.8
	 ProduceSound(weap(cyc),weapSound(weapType(cyc)),22000,0.5)
	 CreateSpurt(weapX#(cyc),weapY#(cyc)+2,weapZ#(cyc),10,5) 
	EndIf
	weapBounce#(cyc)=weapBounce#(cyc)/2
    If weapBounce#(cyc)<0.2 Then weapBounce#(cyc)=0
    weapGravity#(cyc)=weapBounce#(cyc) 
    weapFlight#(cyc)=weapFlight#(cyc)/2
    If weapFlight#(cyc)<0.2 Then weapFlight#(cyc)=0 
	weapY#(cyc)=weapGround#(cyc)
	If weapBounce#(cyc)=0 Then weapAnim(cyc)=0
   EndIf
  EndIf
  ;explode relocation
  If weapAnim(cyc)=50
   weapX#(cyc)=999 : weapZ#(cyc)=999 : weapY#(cyc)=wGround#-50
   If weapAnimTim(cyc)>200 
    weapX#(cyc)=Rnd(-200,200) : weapZ#(cyc)=Rnd(-200,200) : weapY#(cyc)=wGround#
    If InsideRing(weapX#(cyc),weapZ#(cyc),-10) Then weapY#(cyc)=wStage#
    weapAnim(cyc)=0 : weapAnimTim(cyc)=0
   EndIf
  EndIf
  ;follow carrier
  If weapCarrier(cyc)>0
   v=weapCarrier(cyc)
   weapX#(cyc)=pX#(v) : weapZ#(cyc)=pZ#(v) : weapY#(cyc)=pY#(v)+15
  EndIf
  ;bag state
  If weapType(cyc)=28 And weapCarrier(cyc)=0
   If weapY#(cyc)>weapGround#(cyc)+3
    ShowEntity FindChild(weap(cyc),"Bag")
    HideEntity FindChild(weap(cyc),"Empty")
    HideEntity FindChild(weap(cyc),"Tacks")
   EndIf
   If weapY#(cyc)=<weapGround#(cyc)+3
    HideEntity FindChild(weap(cyc),"Bag")
    ShowEntity FindChild(weap(cyc),"Empty")
    ShowEntity FindChild(weap(cyc),"Tacks")
   EndIf
  EndIf
  ;burning
  If weapBurning(cyc)>0 
   If weapCarrier(cyc)>0
    limb=FindChild(p(weapCarrier(cyc)),weapFile$(weapType(cyc)))
    limbX#=EntityX(limb,1) : limbY#=EntityY(limb,1) : limbZ#=EntityZ(limb,1)
   Else
    limbX#=weapX#(cyc) : limbY#=weapY#(cyc) : limbZ#=weapZ#(cyc)
   EndIf
   If weapType(cyc)=>30 Then flame=1 Else flame=7
   CreateSpurt(limbX#,limbY#-1,limbZ#,3,flame)
   CreateParticle(limbX#,limbY#-1,limbZ#,5)
   randy=Rnd(0,2)
   If randy=0 Then CreateParticle(limbX#,limbY#-1,limbZ#,2)
   weapBurning(cyc)=weapBurning(cyc)-1
   If weapBurning(cyc)=<0 Then ExtinguishWeapon(cyc)
  EndIf
  ;burning explosion!
  If weapBurning(cyc)>0 And weapExplodable(weapType(cyc))>0 And weapCarrier(cyc)=0 And weapAnim(cyc)=0
   randy=Rnd(0,100)
   If randy=0
    CreateExplosion(weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc),weapExplodable(weapType(cyc))) 
    If weapExplodable(weapType(cyc))<>10 Then CreateExplosion(weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc),10)
    ExtinguishWeapon(cyc)
    weapAnim(cyc)=50 : weapAnimTim(cyc)=0
    weapX#(cyc)=999 : weapZ#(cyc)=999 : weapY#(cyc)=wGround#-50
   EndIf
  EndIf
  ;scarring
  ManageWeaponScars(cyc)
  If weapAnim(cyc)>0 And weapScar(cyc)>1 
   randy=Rnd(0,6)
   If randy=<1 Then CreateParticle(weapX#(cyc),weapY#(cyc),weapZ#(cyc),3)
   If randy=0 Then CreatePool(weapX#(cyc),weapZ#(cyc),Rnd(1.0,2.0),1,1)
  EndIf
  ;display
  PositionEntity weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc)
  RotateEntity weap(cyc),0,weapA#(cyc),0
  ;increment animation
  weapAnimTim(cyc)=weapAnimTim(cyc)+1
 Next
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;PREPARE WEAPONS FOR PLAYERS
Function PrepareWeapons()
 ;reset selection
 weapCount=no_weaps
 For cyc=1 To no_weaps
  weapType(cyc)=0
 Next
 ;prepare for titles
 matchReward=matchChamps
 If matchChamps=2 Then AddWeapon(21)
 If matchChamps=3 Then AddWeapon(22)
 If matchChamps=4 Then AddWeapon(23) : AddWeapon(23)
 ;find personal items
 For cyc=1 To no_plays
  weapBlock=0
  If game=1 And gamVariable(slot)=>4 And gamVariable(slot)=<5 Then weapBlock=1
  ;default weapon
  pWeapon(cyc)=0
  If weapBlock=0 And charWeapon(pChar(cyc))>0 And pRole(cyc)<>1 Then pWeapon(cyc)=charWeapon(pChar(cyc))
  ;promo mic
  If weapBlock=0 And matchPromo>0 And matchPromo<>74 And matchOfficial=1 And fed<>7
   If cyc=promoActor(1) Or cyc=promoActor(2) Or cyc=promoActor(3) Then pWeapon(cyc)=5
  EndIf
  ;tag championship
  If TitleHolder(pChar(cyc),3) And matchChamps<>4 And pRole(cyc)<>1
   pWeapon(cyc)=23
   If matchChamps=1 And matchTeams>0 And pRole(cyc)=0 And no_wrestlers=>3 And no_wrestlers=<4 Then matchReward=4
  EndIf
  ;inter championship
  If TitleHolder(pChar(cyc),2) And matchChamps<>3 And pRole(cyc)<>1
   pWeapon(cyc)=22
   If matchChamps=1 And matchReward=1 And matchTeams=<0 And pRole(cyc)=0 Then matchReward=3
  EndIf
  ;world championship
  If TitleHolder(pChar(cyc),1) And matchChamps<>2 And pRole(cyc)<>1
   pWeapon(cyc)=21
   If matchChamps=1 And matchTeams=<0 And pRole(cyc)=0 Then matchReward=2
  EndIf
  ;add to list
  If pWeapon(cyc)>0 Then AddWeapon(pWeapon(cyc))
 Next
End Function

;ADD WEAPON TO LIST
Function AddWeapon(weapon)
 no_weaps=no_weaps+1
 If no_weaps>weapLimit Then no_weaps=weapLimit
 weapType(no_weaps)=weapon
End Function

;ASSIGN WEAPONS TO PLAYERS
Function AssignWeapons()
 For cyc=1 To no_plays
  For v=1 To no_weaps
   If weapType(v)=pWeapon(cyc) And pHolding(cyc)=0 And weapCarrier(v)=0 Then AttainWeapon(cyc,v)
  Next
 Next
End Function

;ITEM IN PROXIMITY?
Function ItemProximity(cyc,v,range)
 value=0
 height#=iClimb#(iType(v))
 If RealX#(cyc)>iX#(v)-range And RealX#(cyc)<iX#(v)+range And RealZ#(cyc)>iZ#(v)-range And RealZ#(cyc)<iZ#(v)+range And RealY#(cyc)>iY#(v)-height# And RealY#(cyc)<iY#(v)+height#
  value=1
 EndIf
 Return value
End Function

;ITEM IN SIGHT?
Function ItemVisible(cyc,v,range)
 value=0
 If RealX#(cyc)>iX#(v)-range And RealX#(cyc)<iX#(v)+range And RealZ#(cyc)>iZ#(v)-range And RealZ#(cyc)<iZ#(v)+range
  value=1
 EndIf
 Return value
End Function

;ITEM IN RANGE?
Function ItemRange(cyc,v,range)
 ;position probe
 ResetDummy(cyc)
 MoveEntity dummy,0,0,range
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find matches
 value=0
 If checkX#>iX#(v)-range And checkX#<iX#(v)+range And checkZ#>iZ#(v)-range And checkZ#<iZ#(v)+range And pY#(cyc)=iY#(v)
  value=1
 EndIf
 Return value
End Function

;WEAPON IN PROXIMITY?
Function WeaponProximity(cyc,v,range)
 value=0
 If range<0 Then range=weapSize#(weapType(v))
 If RealX#(cyc)>weapX#(v)-range And RealX#(cyc)<weapX#(v)+range And RealZ#(cyc)>weapZ#(v)-range And RealZ#(cyc)<weapZ#(v)+range And RealY#(cyc)>weapY#(v)-10 And RealY#(cyc)<weapY#(v)+15
  value=1
 EndIf
 Return value
End Function

;WEAPON IN SIGHT?
Function WeaponVisible(cyc,v,range)
 value=0
 If range<0 Then range=weapSize#(weapType(v))
 If RealX#(cyc)>weapX#(v)-range And RealX#(cyc)<weapX#(v)+range And RealZ#(cyc)>weapZ#(v)-range And RealZ#(cyc)<weapZ#(v)+range
  value=1
 EndIf
 Return value
End Function

;SCAN ITEM DIMENSIONS
Function ScanItem(cyc,offset#)
 ;reset dummy
 PositionEntity dummy,iX#(cyc),iY#(cyc),iZ#(cyc)
 RotateEntity dummy,0,iA#(cyc),0
 rangeX=(iSizeX#(iType(cyc))+offset#)*2
 rangeZ=(iSizeZ#(iType(cyc))+offset#)*2
 MoveEntity dummy,-rangeX,0,-rangeZ
 ;explore dimensions
 iDensity(cyc)=0
 For countX=1 To rangeX+1
  For countZ=1 To rangeZ+1
   iDensity(cyc)=iDensity(cyc)+1
   iDimX#(cyc,iDensity(cyc))=EntityX(dummy)
   iDimZ#(cyc,iDensity(cyc))=EntityZ(dummy)
   MoveEntity dummy,0,0,2
  Next
  MoveEntity dummy,0,0,-((rangeZ+1)*2)
  MoveEntity dummy,2,0,0
 Next
End Function

;ITEM COLLISION?
Function ItemCollide(cyc,v,x#,z#,offset#)
 value=0
 For count=1 To iDensity(v)
  range#=3+offset# : height#=iClimb#(iType(v))
  If x#>iDimX#(v,count)-range# And x#<iDimX#(v,count)+range# And z#>iDimZ#(v,count)-range# And z#<iDimZ#(v,count)+range# And RealY#(cyc)>iY#(v)-height# And RealY#(cyc)<iY#(v)+height#
   value=1 
  EndIf
 Next
 Return value
End Function

;FIND ITEM CLIMBING
Function FindItemClimbing(cyc,v)
 If DirPressed(cyc) And pAnim(cyc)=>0 And pAnim(cyc)=<3 And pPlatform(cyc)=0 And pDazed(cyc)=0
  If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
  If pFriction(cyc)=>20 And iClimb#(iType(v))>0 And iCarrier(v)=0 And ItemClear(v) And iType(v)<>14
   PointEntity p(cyc),i(v)
   pA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
   pPlatform(cyc)=v+10 : pPlatY#(cyc)=iClimb#(iType(v))
   pPlatX#(cyc)=iX#(v) : pPlatZ#(cyc)=iZ#(v)
   ChangeAnim(cyc,28) : pFriction(cyc)=0
  EndIf
 EndIf
End Function

;ITEM CLEAR FOR CLIMBING?
Function ItemClear(item)
 clear=1
 For v=1 To no_plays
  If pPlatform(v)=item+10 Then clear=0
 Next
 Return clear
End Function

;FIND ITEM IMPACTS
Function FindSmashes(cyc,level) ;0=low contact, 1=high contact
 ;ITEM CONTACTS
 For v=1 To no_items
  viable=0
  If iState(v)=0 And level=<1 Then viable=1
  If iState(v)=1 And (level=0 Or iCarrier(v)>0) Then viable=1
  If level=1 And v=pPlatform(cyc)-10 Then viable=0
  If iCarrier(v)>0 And (pAnim(iCarrier(v))=34 Or pAnim(iCarrier(v))=39) Then viable=0
  If viable=1 And ItemProximity(cyc,v,30)
   ScanItem(v,0) 
   If ItemCollide(cyc,v,RealX#(cyc),RealZ#(cyc),0)
    Pop(0,Rnd(2,7),0)
    ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1)
    ProduceSound(i(v),iSound(iType(v)),22050,1)  
    If iExplodable(iType(v))>0 And iState(v)=0
     randy=Rnd(0,2)
     If randy=0 Then CreateExplosion(i(v),iX#(v),iY#(v),iZ#(v),iExplodable(iType(v)))
    EndIf
    If iState(v)=0 And level=1 Then impactY#=iY#(v)+iClimb#(iType(v)) Else impactY#=iY#(v)+5
    CreateSpurt(RealX#(cyc),impactY#,RealZ#(cyc),15,99)
    ScarArea(cyc,0,0,0,8-iDamage(iType(v)))
    If iType(v)=14 Then ScarArea(cyc,0,0,0,8-iDamage(iType(v)))
    If CountScars(cyc)=>4 
     ScarItem(v,3)
     CreatePool(RealX#(cyc),RealZ#(cyc),Rnd(5.0,10.0),3,1)
     If iType(v)=14 Then CreatePool(RealX#(cyc),RealZ#(cyc),Rnd(5.0,10.0),3,1)
    EndIf
    If pChar(cyc)=gamChar(slot) Or cyc=pFoc(matchPlayer)
     If iState(v)=0 Then matchDamage=matchDamage+200 Else matchDamage=matchDamage+50
    EndIf
    If iState(v)=0 Then Animate i(v),3,2.5,1,0 Else Animate i(v),3,2.0,2,5
    iState(v)=1 : iA#(v)=iA#(v)+Rnd(-20,20)
    iX#(v)=iX#(v)+Rnd(-2,2) : iZ#(v)=iZ#(v)+Rnd(-2,2)
    pHealth(cyc)=pHealth(cyc)-iDamage(iType(v)) : pHP(cyc)=0
    If pDT(cyc)>0 Then pDT(cyc)=pDT(cyc)+(10*iDamage(iType(v)))
    RiskInjury(cyc,1)
    If iCarrier(v)>0 Then ChangeAnim(iCarrier(v),61) : pStagger#(iCarrier(v))=0
    If iBurning(v)>0
     Pop(0,Rnd(4,7),1)
     ProduceSound(p(cyc),sPain(3),GetVoice(cyc),1)
     ProduceSound(i(v),sIgnite,22050,0)
     CreateSpurt(RealX#(cyc),iY#(v)+5,RealZ#(cyc),5,2)
     ScarArea(cyc,0,0,0,1) : RiskInjury(cyc,1)
     pHealth(cyc)=pHealth(cyc)-iDamage(iType(v))
     If pDT(cyc)>0 Then pDT(cyc)=pDT(cyc)+(10*iDamage(iType(v)))
    EndIf
   EndIf
  EndIf
 Next
 ;WEAPON CONTACTS
 If level=0 And pAnim(cyc)<>13
  For v=1 To no_weaps
   If WeaponProximity(cyc,v,-4) And weapCarrier(v)=0
    Pop(0,Rnd(2,7),0)
    ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1)
    ProduceSound(weap(v),weapSound(weapType(v)),22050,0)
    CreateSpurt(weapX#(v),weapY#(v)+2,weapZ#(v),15,99)
    ScarArea(cyc,weapX#(v),pY#(cyc),weapZ#(v),5)
    If CountScars(cyc)=>4 Then ScarWeapon(v,3) : CreatePool(RealX#(cyc),RealZ#(cyc),Rnd(2.0,8.0),2,1)
    Animate weap(v),3,2.0,0,5
    weapA#(v)=weapA#(v)+Rnd(-30,30)
    weapX#(v)=weapX#(v)+Rnd(-2,2) : weapZ#(v)=weapZ#(v)+Rnd(-2,2)
    pHealth(cyc)=pHealth(cyc)-Rnd(1,weapDamage(weapType(v))) : pHP(cyc)=pHP(cyc)-1
    If pDT(cyc)>0 Then pDT(cyc)=pDT(cyc)+(10*weapDamage(weapType(v)))
    RiskInjury(cyc,0) : RiskExplosion(v,3)
    ClockWeapon(0,cyc,weapType(v))
    If weapBurning(v)>0
     Pop(0,Rnd(4,7),1)
     ProduceSound(p(cyc),sPain(3),GetVoice(cyc),1)
     ProduceSound(weap(v),sIgnite,22050,0)
     CreateSpurt(RealX#(cyc),weapY#(v)+2,RealZ#(cyc),5,1)
     ScarArea(cyc,weapX#(v),pY#(cyc),weapZ#(v),1)
     pHealth(cyc)=pHealth(cyc)-1 : pHP(cyc)=0
     If pDT(cyc)>0 Then pDT(cyc)=pDT(cyc)+(10*weapDamage(weapType(v)))
     RiskInjury(cyc,1) : RiskExplosion(v,1)
    EndIf
    If weapType(v)=28 
     Pop(0,Rnd(4,7),1) : ProduceSound(p(cyc),sPain(3),GetVoice(cyc),1)
     ScarArea(cyc,0,0,0,5) : CreatePool(RealX#(cyc),RealZ#(cyc),Rnd(2.0,8.0),2,1)
     pHealth(cyc)=pHealth(cyc)-Rnd(1,weapDamage(weapType(v)))
     If pDT(cyc)>0 Then pDT(cyc)=pDT(cyc)+(5*weapDamage(weapType(v)))
     RiskInjury(cyc,1)
    EndIf
   EndIf
  Next
 EndIf
 ;HUMAN CONTACT
 For v=1 To no_plays
  ;standing victims
  If cyc<>v And AttackViable(v)=1 And level=<1 And pAnim(v)<>51 And pAnim(v)<>52
   If InProximity(cyc,v,6) And RealY#(cyc)>pY#(v)-5 And RealY#(cyc)<pY#(v)+10
    Pop(0,Rnd(2,7),0)
	ProduceSound(p(v),sImpact(3),22050,0)
    ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1)
    height#=EntityY(pLimb(cyc,30),1)
    CreateSpurt(RealX#(v),height#,RealZ#(v),15,99)
    ScarArea(v,pX#(v),height#,pZ#(v),20) : ScarArea(cyc,0,0,0,10) 
 	ImpactChecks(v) : ChangeAnim(v,Rnd(60,61))
	pHealth(v)=pHealth(v)-1 : pHP(v)=0
	pHP(cyc)=0 : RiskInjury(v,0) 
   EndIf
  EndIf
  ;grounded victims
  If cyc<>v And AttackViable(v)=2 And level=0
   If InProximity(cyc,v,10) And RealY#(cyc)>pY#(v)-5 And RealY#(cyc)<pY#(v)+10
    Pop(0,Rnd(2,7),0)
	ProduceSound(p(v),sImpact(6),22050,0)
    ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1)
    CreateSpurt(RealX#(cyc),pY#(v)+3,RealZ#(cyc),15,99)
    ScarArea(v,pX#(cyc),pY#(v),pZ#(cyc),10) : ScarArea(cyc,0,0,0,10) 
	GroundImpactChecks(v) : RiskInjury(v,0)
	pHealth(v)=pHealth(v)-1
    If pDT(v)>0 Then pDT(v)=pDT(v)+50
   EndIf
  EndIf
 Next
 ;RING CONTACT
 ;ropes
 roper=NearRopes(cyc,2)
 If roper>0 
  ShakeRopes(roper,1,2)
  RopeSound(p(cyc),0)
  RopeBurn(cyc,3)
 EndIf
 ;cage walls
 cager=NearestCage(cyc)
 If cager>0 And pY#(cyc)=wStage#
  Pop(0,Rnd(4,7),0)
  ProduceSound(p(cyc),sSmashWire,22050,0)
  ProduceSound(p(cyc),sImpact(6),22050,0)
  ProduceSound(p(cyc),sPain(Rnd(2,3)),GetVoice(cyc),1)
  ScarArea(cyc,0,0,0,5) : RiskInjury(cyc,1)
  pHealth(cyc)=pHealth(cyc)-1 : pHP(cyc)=pHP(cyc)-Rnd(1,5)
  If pDT(cyc)>0 Then pDT(cyc)=pDT(cyc)+50
  cageTim(cager)=0
 EndIf
End Function

;INJURE PLAYER
Function RiskInjury(cyc,chance)
 randy=Rnd(0,charTough(pChar(cyc))*3)
 If randy=<chance And pInjured(cyc)=<1
  Pop(0,7,1)
  ProduceSound(p(cyc),sPain(Rnd(5,6)),GetVoice(cyc),1)
  pHealth(cyc)=pHealth(cyc)/2 : pHP(cyc)=0
  pDT(cyc)=pDT(cyc)+100
  If pInjured(cyc)=1 Then pInjured(cyc)=2
  If pInjured(cyc)=0 Then pInjured(cyc)=Rnd(-1,2)
  If pInjured(cyc)<1 Then pInjured(cyc)=1 
 EndIf
End Function

;BIND ITEM TO CARRIER
Function BindItem(cyc)
 ;projected location
 v=pCarrying(cyc)
 PositionEntity i(v),RealX#(cyc),pY#(cyc),RealZ#(cyc)
 RotateEntity i(v),0,pA#(cyc),0
 mover#=iCarryDepth#(iType(v),iState(v))
 If pAnim(cyc)=10 Or pAnim(cyc)=12 Then mover#=mover#+5
 MoveEntity i(v),0,0,mover#
 iX#(v)=EntityX(i(v)) : iZ#(v)=EntityZ(i(v))
 ;bind to hands
 tY#=EntityY(FindChild(p(cyc),"R_Palm"),1)+iCarryY#(iType(v),iState(v))
 If iY#(v)<tY#-1.5 Then iY#(v)=iY#(v)+1.5
 If iY#(v)<tY# Then iY#(v)=iY#(v)+0.1
 If iY#(v)>tY# Then iY#(v)=iY#(v)-0.1
 If iY#(v)>tY#+1.5 Then iY#(v)=iY#(v)-1.5
 If pAnim(cyc)=10 And iY#(v)<wStage# Then iY#(v)=wStage#
 If iY#(v)<pY#(cyc) Then iY#(v)=pY#(cyc)
 ;offset angle
 iA#(v)=pA#(cyc)+iCarryA#(iType(v),iState(v))
End Function

;DROP ITEM
Function DropItem(cyc)
 v=pCarrying(cyc)
 If v>0
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  pCarrying(cyc)=0 : iCarrier(v)=0
  iAnim(v)=10 : iAnimTim(v)=0
  iFallA#(v)=pA#(cyc) : iGround#(v)=pY#(cyc)
  If iState(v)=0
   pusher#=9.0-(iCarryDepth#(iType(v),0)-iSizeZ#(iType(v)))
   If pusher#<2.0 Then pusher#=2.0
   PositionEntity dummy,iX#(v),iY#(v),iZ#(v)
   RotateEntity dummy,0,iFallA#(v),0
   MoveEntity dummy,0,0,pusher#
   iX#(v)=EntityX(dummy) : iZ#(v)=EntityZ(dummy)
  EndIf
 EndIf
End Function

;DROP WEAPON
Function DropWeapon(cyc,chance)
 v=pHolding(cyc)
 randy=Rnd(0,chance)
 If v>0 And (randy=0 Or weapBroad(weapType(v)))
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  limb=FindChild(p(cyc),"R_Palm")
  weapX#(v)=EntityX(limb,1) : weapY#(v)=EntityY(limb,1) : weapZ#(v)=EntityZ(limb,1)
  weapA#(v)=EntityYaw(FindChild(p(cyc),weapFile$(weapType(v))),1)
  weapGravity#(v)=1.0 : weapFlight#(v)=0.2
  weapAnim(v)=10 : weapAnimTim(v)=0
  pHolding(cyc)=0 : weapCarrier(v)=0  
  HideEntity FindChild(p(cyc),weapFile$(weapType(v)))
  ShowEntity weap(v) : weapOldScar(v)=-1
 EndIf
End Function

;THROW WEAPON
Function ThrowWeapon(cyc)
 v=pHolding(cyc)
 If v>0
  ;position for release
  limb=pLimb(cyc,19)
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1)
  weapA#(v)=EntityYaw(FindChild(p(cyc),weapFile$(weapType(v))),1)
  RotateEntity dummy,0,weapA#(v),0
  MoveEntity dummy,-2,0,0
  weapX#(v)=EntityX(dummy) : weapY#(v)=EntityY(dummy) : weapZ#(v)=EntityZ(dummy)
  ;reset state
  weapFlightA#(v)=pA#(cyc) : weapFlight#(v)=2.0+(weapGravity#(v)/2)
  weapAnim(v)=20 : weapAnimTim(v)=0
  pHolding(cyc)=0 : weapCarrier(v)=0
  HideEntity FindChild(p(cyc),weapFile$(weapType(v)))
  ShowEntity weap(v) : weapOldScar(v)=-1
  ;make potent
  For count=1 To no_plays
   weapSting(v,count)=1
  Next
  For count=1 To no_items
   weapItemSting(v,count)=1
  Next
 EndIf
End Function

;ATTAIN WEAPON
Function AttainWeapon(cyc,v)
 ;bind to player
 pHolding(cyc)=v : weapCarrier(v)=cyc
 ;texturing issues
 If weapTex(weapType(v))>0
  EntityTexture FindChild(p(cyc),weapFile$(weapType(v))),weapTex(weapType(v))
 EndIf
 EntityShininess FindChild(p(cyc),weapFile$(weapType(v))),weapShiny(weapType(v))
 weapOldScar(v)=-1  
 ;switch display
 ShowEntity FindChild(p(cyc),weapFile$(weapType(v))) 
 HideEntity weap(v)
End Function

;FIND WEAPON IMPACT
Function WeaponImpact(cyc,v)
 w=pHolding(cyc)
 If w>0
  ;effect
  Pop(cyc,Rnd(2,7),0)
  ProduceSound(p(v),weapSound(weapType(w)),22050,1)
  If pAnim(cyc)=>40 And pAnim(cyc)=<49
   ScarLimb(v,1,5)
  Else
   limb=FindChild(p(cyc),weapFile$(weapType(w)))
   ScarArea(v,RealX#(v),EntityY(limb,1),RealZ#(v),5)
  EndIf
  If CountScars(v)=>4 Then ScarWeapon(w,3)
  ;damage
  pHealth(v)=pHealth(v)-Rnd(1,weapDamage(weapType(w)))
  pHP(v)=pHP(v)-weapDamage(weapType(w))
  pDT(v)=pDT(v)+(weapDamage(weapType(w))*10)
  pDazed(v)=pDazed(v)+50
  ;fire bonus
  If weapBurning(w)>0
   Pop(0,Rnd(4,7),0)
   ProduceSound(p(v),sIgnite,22050,0)
   limb=FindChild(p(cyc),weapFile$(weapType(w)))
   CreateSpurt(RealX#(v),EntityY(limb,1),RealZ#(v),5,1)
   ScarArea(v,RealX#(v),EntityY(limb,1),RealZ#(v),5)
   pHealth(v)=pHealth(v)-1 : pHP(v)=pHP(v)-1
   pDT(v)=pDT(v)+50 : pDazed(v)=pDazed(v)+50
  EndIf
  ;clock use
  ClockWeapon(cyc,v,weapType(w))
  ;risk drop if mid-move
  If pAnim(cyc)>100 Then DropWeapon(cyc,5)
  DQ(cyc,v)
 EndIf
End Function

;FLYING WEAPON COLLISIONS
Function FlyingWeaponImpacts(cyc)
 ;players
 For v=1 To no_plays
  size#=weapSize#(weapType(cyc))
  range=size#-(size#/3) : height#=30
  If AttackViable(v)=2 Then range=size#+(size#/3) : height#=6 
  If weapSting(cyc,v)=1 And AttackViable(v)>0
   If weapX#(cyc)<RealX#(v)+range And weapX#(cyc)>RealX#(v)-range And weapZ#(cyc)<RealZ#(v)+range And weapZ#(cyc)>RealZ#(v)-range And weapY#(cyc)=>pY#(v) And weapY#(cyc)=<pY#(v)+height#
    Pop(0,Rnd(4,8),0)
    ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,0)
    ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
    CreateSpurt(RealX#(v),weapY#(cyc)-4,RealZ#(v),15,99) 
    ScarArea(v,weapX#(cyc),weapY#(cyc),weapZ#(cyc),5)
    If CountScars(v)=>4 Then ScarWeapon(cyc,3)
    pHealth(v)=pHealth(v)-Rnd(1,weapDamage(weapType(cyc)))
    pHP(v)=pHP(v)-Rnd(1,weapDamage(weapType(cyc)))
    RiskExplosion(cyc,8)
    If weapBurning(cyc)>0
     Pop(0,Rnd(4,8),0)
     ProduceSound(weap(cyc),sIgnite,22050,0)
     CreateSpurt(RealX#(v),weapY#(cyc)-4,RealZ#(v),5,1)
     ScarArea(v,weapX#(cyc),weapY#(cyc),weapZ#(cyc),5)
     pHealth(v)=pHealth(v)-1 : pHP(v)=pHP(v)-1
     If weapY#(cyc)=>pY#(v)+20 Then pDazed(v)=pDazed(v)+50
    EndIf
    pHurtA#(v)=weapFlightA#(cyc)
    weapFlightA#(cyc)=weapFlightA#(cyc)+180
    weapA#(cyc)=weapA#(cyc)+Rnd(-50,50)
    weapGravity#(cyc)=1.0 : weapFlight#(cyc)=0.5
    If AttackViable(v)=1
     ImpactChecks(v) : pRefAnger(v)=0
     If weapY#(cyc)=>pY#(v)+20 Then ChangeAnim(v,60)
     If weapY#(cyc)<pY#(v)+20 Then ChangeAnim(v,61)
    EndIf
    If AttackViable(v)=2 Then GroundImpactChecks(v)
    randy=Rnd(0,1)
    If randy=0 And pAnim(v)=60 Then pDazed(v)=Rnd(50,250)
    weapSting(cyc,v)=0
   EndIf
  EndIf
 Next
 ;items
 For v=1 To no_items
  If iState(v)=0 Then height#=iClimb#(iType(v)) Else height#=5
  If weapItemSting(cyc,v)=1 And weapX#(cyc)>iX#(v)-20 And weapX#(cyc)<iX#(v)+20 And weapZ#(cyc)>iZ#(v)-20 And weapZ#(cyc)<iZ#(v)+20 And weapY#(cyc)=>iY#(v) And weapY#(cyc)=<iY#(v)+height#
   weapX#(cyc)=weapOldX#(cyc) : weapZ#(cyc)=weapOldZ#(cyc)
   ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,0.5)
   CreateSpurt(weapX#(cyc),weapY#(cyc)-4,weapZ#(cyc),10,5)
   If weapBurning(cyc)>0 And iBurning(v)=0 And iState(v)=1 Then IgniteItem(v)
   If weapBurning(cyc)=0 And iBurning(v)>0 Then IgniteWeapon(cyc)
   RiskExplosion(cyc,3)
   weapFlightA#(cyc)=weapFlightA#(cyc)+180
   weapA#(cyc)=weapA#(cyc)+Rnd(-50,50)
   weapGravity#(cyc)=1.0 : weapFlight#(cyc)=0.5
   ProduceSound(i(v),sItem,22050,0.5)
   iAnim(v)=11 : iAnimTim(v)=0
   weapItemSting(cyc,v)=0
  EndIf
 Next
 ;world blocks
 For v=1 To no_blocks
  If weapOldX#(cyc)>wBlockX1#(v) And weapOldX#(cyc)<wBlockX2#(v) And weapOldZ#(cyc)>wBlockZ1#(v) And weapOldZ#(cyc)<wBlockZ2#(v)
   trapped=1
  Else
   excused=0 : randy=Rnd(0,2)
   If randy>0 And v=>1 And v=<4 Then excused=1
   If excused=0 And weapX#(cyc)>wBlockX1#(v) And weapX#(cyc)<wBlockX2#(v) And weapZ#(cyc)>wBlockZ1#(v) And weapZ#(cyc)<wBlockZ2#(v) And weapY#(cyc)>wBlockY1#(v) And weapY#(cyc)<wBlockY2#(v)+4
    If weapOldX#(cyc)>wBlockX1#(v) And weapOldX#(cyc)<wBlockX2#(v) Then weapZ#(cyc)=weapOldZ#(cyc)
	If weapOldZ#(cyc)>wBlockZ1#(v) And weapOldZ#(cyc)<wBlockZ2#(v) Then weapX#(cyc)=weapOldX#(cyc) 
	ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,0)
	CreateSpurt(weapX#(cyc),weapY#(cyc)-4,weapZ#(cyc),10,5)  
	If v=>1 And v=<4 
	 ProduceSound(weap(cyc),sRopes,44100,0) : ShakeRopes(v,1,2)
	 roper=v
	 If weapY#(cyc)=>37 And weapY#(cyc)=<43 Then roper=v+4
	 If weapY#(cyc)>wStage# And weapY#(cyc)<37 Then roper=v+8
	 If weapBurning(cyc)>0 And ropeBurning(roper)=0 Then IgniteRope(roper)
	EndIf  
	RiskExplosion(cyc,3)  
	weapFlightA#(cyc)=weapFlightA#(cyc)+180
    weapA#(cyc)=weapA#(cyc)+Rnd(-50,50)
    weapGravity#(cyc)=1.0 : weapFlight#(cyc)=0.5
   EndIf
  EndIf
 Next
 ;cage blocks
 cager=0
 If matchCage>0 And weapY#(cyc)>wBlockY2#(0) And weapY#(cyc)<wBlockY2#(0)+50
  If cageTim(1)=-1 And weapX#(cyc)>wBlockX1#(0) And weapX#(cyc)<wBlockX2#(0) And weapZ#(cyc)>wBlockZ2#(0)-10 And weapZ#(cyc)<wBlockZ2#(0)+10 Then cager=1
  If cageTim(2)=-1 And weapX#(cyc)>wBlockX2#(0)-10 And weapX#(cyc)<wBlockX2#(0)+10 And weapZ#(cyc)>wBlockZ1#(0) And weapZ#(cyc)<wBlockZ2#(0) Then cager=2
  If cageTim(3)=-1 And weapX#(cyc)>wBlockX1#(0) And weapX#(cyc)<wBlockX2#(0) And weapZ#(cyc)>wBlockZ1#(0)-10 And weapZ#(cyc)<wBlockZ1#(0)+10 Then cager=3
  If cageTim(4)=-1 And weapX#(cyc)>wBlockX1#(0)-10 And weapX#(cyc)<wBlockX1#(0)+10 And weapZ#(cyc)>wBlockZ1#(0) And weapZ#(cyc)<wBlockZ2#(0) Then cager=4
 EndIf
 If cager>0
  ProduceSound(weap(cyc),sSmashWire,22050,0.5) 
  ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,0)
  CreateSpurt(weapX#(cyc),weapY#(cyc)-4,weapZ#(cyc),10,5)  
  RiskExplosion(cyc,3) 
  weapX#(cyc)=weapOldX#(cyc) : weapZ#(cyc)=weapOldZ#(cyc)   
  weapFlightA#(cyc)=weapFlightA#(cyc)+180
  weapA#(cyc)=weapA#(cyc)+Rnd(-50,50)
  weapGravity#(cyc)=1.0 : weapFlight#(cyc)=0.5
  cageTim(cager)=5
 EndIf
End Function

;RISK EXPLOSION
Function RiskExplosion(cyc,chance)
 If weapType(cyc)=12 Or weapType(cyc)=20 Then chance=chance*2
 randy=Rnd(0,chance)
 If (randy=<1 Or weapBurning(cyc)>0) And weapExplodable(weapType(cyc))>0
  CreateExplosion(weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc),weapExplodable(weapType(cyc)))
  If weapBurning(cyc)>0
   CreateExplosion(weap(cyc),weapX#(cyc)+Rnd(-5,5),weapY#(cyc),weapZ#(cyc)+Rnd(-5,5),10)
   ExtinguishWeapon(cyc)
  EndIf
  weapAnim(cyc)=50 : weapAnimTim(cyc)=0
  weapX#(cyc)=999 : weapZ#(cyc)=999 : weapY#(cyc)=wGround#-50
 EndIf
End Function