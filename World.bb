;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: WORLD ---------------------------
;//////////////////////////////////////////////////////////////////////////////

;--------------------------------------------------------------------
;////////////////////// PREPARE ARENA DETAILS ///////////////////////
;--------------------------------------------------------------------
Function PrepareArena()
 ;random selection
 If arenaPreset=0 Then arenaPreset=Rnd(1,no_arenas-2)
 ;white hall
 If arenaPreset=1
  arenaWall=tWall(7) : arenaBackWall=tWall(9) : arenaCeiling=arenaWall
  arenaGround=tGround(9) : arenaBackGround=tGround(4) : arenaType=2
 EndIf
 ;wooden hall
 If arenaPreset=2
  arenaWall=tWall(6) : arenaBackWall=tWall(8) : arenaCeiling=arenaWall
  arenaGround=tGround(8) : arenaBackGround=tGround(4) : arenaType=2
 EndIf
 ;sandy hall
 If arenaPreset=3
  arenaWall=tWall(2) : arenaBackWall=tWall(7) : arenaCeiling=arenaWall
  arenaGround=tGround(6) : arenaBackGround=tGround(8) : arenaType=2
 EndIf
 ;plush hall
 If arenaPreset=4
  arenaWall=tWall(8) : arenaBackWall=tWall(9) : arenaCeiling=arenaWall
  arenaGround=tGround(3) : arenaBackGround=tGround(4) : arenaType=2
 EndIf
 ;cream hall
 If arenaPreset=5
  arenaWall=tWall(5) : arenaBackWall=tWall(6) : arenaCeiling=arenaWall
  arenaGround=tGround(2) : arenaBackGround=tGround(9) : arenaType=2
 EndIf
 ;red brick hall
 If arenaPreset=6
  arenaWall=tWall(1) : arenaBackWall=tWall(4) : arenaCeiling=arenaWall
  arenaGround=tGround(1) : arenaBackGround=tGround(2) : arenaType=2
 EndIf
 ;stone hall
 If arenaPreset=7
  arenaWall=tWall(3) : arenaBackWall=tWall(4) : arenaCeiling=arenaWall
  arenaGround=tGround(2) : arenaBackGround=tGround(1) : arenaType=2
 EndIf
 ;dark hall
 If arenaPreset=8
  arenaWall=tWall(4) : arenaBackWall=tWall(10) : arenaCeiling=arenaWall
  arenaGround=tGround(1) : arenaBackGround=tGround(5) : arenaType=2
 EndIf
 ;metal hall
 If arenaPreset=9
  arenaWall=tWall(10) : arenaBackWall=tWall(3) : arenaCeiling=arenaWall
  arenaGround=tGround(5) : arenaBackGround=tGround(1) : arenaType=2
 EndIf
 ;large hall
 If arenaPreset=10
  arenaWall=tBigWall(7) : arenaBackWall=tWall(9) : arenaCeiling=tCeiling(3)
  arenaGround=tGround(2) : arenaBackGround=tGround(3) : arenaType=1
 EndIf
 ;starry stadium
 If arenaPreset=11
  arenaWall=tBigWall(1) : arenaBackWall=tWall(10) : arenaCeiling=tCeiling(1)
  arenaGround=tGround(5) : arenaBackGround=tGround(1) : arenaType=1
 EndIf
 ;firey stadium
 If arenaPreset=12 
  arenaWall=tBigWall(2) : arenaBackWall=tWall(8) : arenaCeiling=tCeiling(1)
  arenaGround=tGround(9) : arenaBackGround=tGround(3) : arenaType=1
 EndIf
 ;standard stadium
 If arenaPreset=13
  arenaWall=tBigWall(3) : arenaBackWall=tWall(1) : arenaCeiling=tCeiling(1)
  arenaGround=tGround(1) : arenaBackGround=tGround(2) : arenaType=1
 EndIf
 ;spectacular stadium
 If arenaPreset=14 
  arenaWall=tBigWall(4) : arenaBackWall=tWall(5) : arenaCeiling=tCeiling(1)
  arenaGround=tGround(1) : arenaBackGround=tGround(2) : arenaType=1
 EndIf
 ;outdoor (concrete)
 If arenaPreset=15 
  arenaWall=tBigWall(5) : arenaBackWall=tWall(10) : arenaCeiling=tCeiling(2)
  arenaGround=tGround(1) : arenaBackGround=tGround(2) : arenaType=1
  If arenaCrowd=>3 Then arenaWall=tBigWall(6) 
 EndIf
 ;outdoor (beach)
 If arenaPreset=16 
  arenaWall=tBigWall(5) : arenaBackWall=tWall(7) : arenaCeiling=tCeiling(2)
  arenaGround=tGround(6) : arenaBackGround=tGround(8) : arenaType=1
  If arenaCrowd=>3 Then arenaWall=tBigWall(6) 
 EndIf
 ;outdoor (grass)
 If arenaPreset=17 
  arenaWall=tBigWall(5) : arenaBackWall=tWall(6) : arenaCeiling=tCeiling(2)
  arenaGround=tGround(7) : arenaBackGround=tGround(4) : arenaType=1
  If arenaCrowd=>3 Then arenaWall=tBigWall(6) 
 EndIf
 ;random hall
 If arenaPreset=18
  arenaWall=tWall(Rnd(1,10)) : arenaBackWall=tWall(Rnd(1,10)) : arenaCeiling=arenaWall
  arenaGround=tGround(Rnd(1,9)) : arenaBackGround=tGround(Rnd(1,9)) : arenaType=2
 EndIf
 ;random stadium
 If arenaPreset=19
  arenaWall=tBigWall(Rnd(1,4)) : arenaBackWall=tWall(Rnd(1,10)) : arenaCeiling=tCeiling(1)
  arenaGround=tGround(Rnd(1,9)) : arenaBackGround=tGround(Rnd(1,9)) : arenaType=1
 EndIf
End Function

;--------------------------------------------------------------------
;/////////////////////////// LOAD ARENA /////////////////////////////
;--------------------------------------------------------------------
Function LoadArena()
 ;prepare materials
 PrepareArena()
 ;load model
 If arenaType=1 Then world=LoadAnimMesh("World/Arena/Arena.3ds")
 If arenaType=2 Then world=LoadAnimMesh("World/Arena/Hall.3ds")
 ;video screen
 If screen>0
  wVideo=2 : wOldVideo=-1 : wVideoCaught=0
  For count=1 To 2
   If tVideo(wVideo)>0 Then EntityTexture FindChild(world,"Screen0"+count),tVideo(wVideo)
   If fed<>7 Or count=2 Then EntityFX FindChild(world,"Screen0"+count),1
  Next
  If fed=7 And tWood(2)>0 Then EntityTexture FindChild(world,"Screen01"),tWood(2)
  ;novelty screens
  tVideo(3)=LoadTexture("Graphics/Promotions/Fed"+Dig$(fed,10)+".bmp")
  If arenaApron=3
   tVideo(4)=LoadTexture("Data/Portraits/Photo"+Dig$(fedRoster(9,fedSize(9)),100)+".bmp")
   ScaleTexture tVideo(4),1,2
   PositionTexture tVideo(4),1,0.85 
  Else
   If arenaPreset=<0 Or arenaPreset=>18 Then tVideo(4)=LoadTexture("Graphics/Arenas/Arena00.JPG")
   If arenaPreset=>1 And arenaPreset=<17 Then tVideo(4)=LoadTexture("Graphics/Arenas/Arena"+Dig$(arenaPreset,10)+".JPG")
  EndIf
 EndIf
 ;intro video
 If screen=0 And tVideo(1)>0 Then EntityTexture FindChild(world,"Screen01"),tVideo(2)
 ;standing crowd
 For count=1 To 21
  If tCrowd(0)>0 Then EntityTexture FindChild(world,"Crowd"+Dig$(count,10)),tCrowd(0),Rnd(0,3)
  crowdSource#(count)=EntityY(FindChild(world,"Crowd"+Dig$(count,10)))
  crowdY#(count)=crowdSource#(count)
  crowdTY#(count)=crowdY#(count)
  If arenaCrowd=0 Then HideEntity FindChild(world,"Crowd"+Dig$(count,10))
 Next
 ;hide seating divides
 If arenaType=1
  For count=1 To 4
   HideEntity FindChild(world,"Divide0"+count)
  Next
 EndIf
 ;shine buckles
 For count=1 To 4
  EntityShininess FindChild(world,"Ring"+Dig$(padID(count),10)),1
  padExposed(count)=0
 Next
 ;decorate arena
 DecorateArena()
 ;add ropes
 LoadRopes()
 ;add cage
 If matchCage>0 Then LoadCage()
End Function

;--------------------------------------------------------------------
;///////////////////////// DECORATE ARENA ///////////////////////////
;--------------------------------------------------------------------
Function DecorateArena()
 ;paint stadium walls
 If arenaType=1 And arenaWall>0
  For count=1 To 4
   EntityTexture FindChild(world,"Wall0"+count),arenaWall
   If arenaWall=tBigWall(5) Or arenaWall=tBigWall(6) Then EntityFX FindChild(world,"Wall0"+count),1
  Next
 EndIf
 ;paint hall walls
 If arenaType=2 And arenaWall>0 
  EntityTexture FindChild(world,"Wall01"),arenaWall
  EntityTexture FindChild(world,"Hall"),arenaWall
 EndIf
 ;backstage walls
 For count=5 To 8
  If arenaBackWall>0 Then EntityTexture FindChild(world,"Wall0"+count),arenaBackWall
 Next
 ;ceilings
 If arenaCeiling>0 Then EntityTexture FindChild(world,"Ceiling"),arenaCeiling
 If arenaCeiling=tCeiling(2) Then EntityFX  FindChild(world,"Ceiling"),1
 If arenaBackWall>0 Then EntityTexture FindChild(world,"BackRoof"),arenaBackWall
 ;grounds
 If arenaGround>0 Then EntityTexture FindChild(world,"Ground01"),arenaGround
 If arenaBackGround>0 Then EntityTexture FindChild(world,"Ground02"),arenaBackGround
 ;seated crowd
 If arenaType=1 
  If arenaCrowd=<1 And tCrowd(1)>0 Then EntityTexture FindChild(world,"Seating"),tCrowd(1)
  If arenaCrowd=>2 And tCrowd(arenaCrowd)>0 Then EntityTexture FindChild(world,"Seating"),tCrowd(arenaCrowd)
 EndIf
 ;apron
 tApron=0
 If arenaApron=1 And fed=>7 Then arenaApron=0
 If arenaApron=2 And fed=>8 Then arenaApron=0 
 If arenaApron=0 Then tApron=LoadTexture("World/Aprons/Default.JPG")
 If arenaApron=1 Then tApron=LoadTexture("World/Aprons/TV/Fed0"+fed+".JPG")
 If arenaApron=2 Then tApron=LoadTexture("World/Aprons/PPV/Fed0"+fed+".JPG")
 If arenaApron=3 Then tApron=LoadTexture("World/Aprons/Tribute.JPG")
 If arenaApron=4 Then tApron=LoadTexture("World/Aprons/Charity.JPG")
 If arenaApron=5 Then tApron=LoadTexture("World/Aprons/International.JPG")
 If tApron>0 Then EntityTexture FindChild(world,"Apron"),tApron
 ;canvas
 tCanvas=0
 If arenaCanvas=4 And fed=>7 Then arenaCanvas=1
 If arenaCanvas=<3 Then tCanvas=LoadTexture("World/Aprons/Canvases/Canvas0"+arenaCanvas+".JPG")
 If arenaCanvas=4 Then tCanvas=LoadTexture("World/Aprons/Canvases/Fed0"+fed+".JPG")
 If arenaCanvas=5 Then tCanvas=LoadTexture("World/Aprons/Canvases/MPire.JPG")
 If tCanvas>0 Then EntityTexture FindChild(world,"Canvas"),tCanvas
 ;matting
 If arenaMat=0 Then HideEntity FindChild(world,"Matting")
 If arenaMat=0 Then tMat=LoadTexture("World/Aprons/Mats/Mat01.JPG")
 If arenaMat>0 
  tMat=LoadTexture("World/Aprons/Mats/Mat0"+arenaMat+".JPG")
  If tMat>0 Then EntityTexture FindChild(world,"Matting"),tMat
 EndIf
 ;curtain
 tCurtain=LoadTexture("World/Aprons/Curtains/Curtain00.JPG")
 If fed=>1 And fed=<6 
  If gamSchedule(slot,GetDate())=<2 Or game=0
   tCurtain=LoadTexture("World/Aprons/Curtains/Curtain0"+fed+".JPG")
  EndIf
 EndIf
 If tCurtain>0 Then EntityTexture FindChild(world,"Curtain"),tCurtain
 EntityAlpha FindChild(world,"Curtain"),0.9 
End Function

;--------------------------------------------------------------------
;/////////////////////////// LOAD ROPES /////////////////////////////
;--------------------------------------------------------------------
Function LoadRopes()
 ;get rope positions
 y#=47.5 : s=0
 For count=1 To 3
  ropeX#(s+1)=1 : ropeY#(s+1)=y# : ropeZ#(s+1)=72.5 : ropeA#(s+1)=0 ;north
  ropeX#(s+2)=74.5 : ropeY#(s+2)=y# : ropeZ#(s+2)=-2 : ropeA#(s+2)=270 ;east
  ropeX#(s+3)=0.5 : ropeY#(s+3)=y# : ropeZ#(s+3)=-76.5 : ropeA#(s+3)=180 ;south
  ropeX#(s+4)=-73.5 : ropeY#(s+4)=y# : ropeZ#(s+4)=-1.5 : ropeA#(s+4)=90 ;west
  y#=y#-7.5 : s=s+4
 Next
 ;construct ropes
 For cyc=1 To 12
  If matchRopes=0 Then rope(cyc)=LoadAnimMesh("World/Ropes/Rope.3ds")
  If matchRopes>0 Then rope(cyc)=LoadAnimMesh("World/Ropes/Wire.3ds")
  ExtractAnimSeq(rope(cyc),0,30,0) ;1. static
  ExtractAnimSeq(rope(cyc),40,130,0) ;2. mild shake
  ExtractAnimSeq(rope(cyc),40,130,0) ;3. big shake
  ExtractAnimSeq(rope(cyc),300,450,0) ;4. whip segment A
  ExtractAnimSeq(rope(cyc),140,290,0) ;5. whip segment B
  ExtractAnimSeq(rope(cyc),460,610,0) ;6. whip segment C
  PositionEntity rope(cyc),ropeX#(cyc),ropeY#(cyc),ropeZ#(cyc)
  RotateEntity rope(cyc),0,ropeA#(cyc),0
  ;get colour variations
  r=255 : g=255 : b=255
  If matchRopes=0
   If arenaRopes=1 ;red,white,and blue
    If cyc=<4 Then r=160 : g=0 : b=0
    If cyc=>5 And cyc=<8 Then r=255 : g=255 : b=255
    If cyc=>9 Then r=0 : g=0 : b=160
   EndIf
   If arenaRopes=2 ;black,white,yellow
    If cyc=<4 Then r=40 : g=40 : b=40
    If cyc=>5 And cyc=<8 Then r=255 : g=255 : b=255
    If cyc=>9 Then r=255 : g=200 : b=0
   EndIf
   If arenaRopes=3 ;mdickie
    If cyc=<4 Then r=255 : g=200 : b=0
    If cyc=>5 And cyc=<8 Then r=220 : g=90 : b=0
    If cyc=>9 Then r=120 : g=0 : b=0
   EndIf
   If arenaRopes=4 ;green,white,brown
    If cyc=<4 Then r=0 : g=120 : b=0
    If cyc=>5 And cyc=<8 Then r=220 : g=220 : b=220
    If cyc=>9 Then r=110 : g=50 : b=0
   EndIf
   If arenaRopes=5 ;mexican
    If cyc=<4 Then r=180 : g=50 : b=0
    If cyc=>5 And cyc=<8 Then r=255 : g=255 : b=255
    If cyc=>9 Then r=0 : g=150 : b=0
   EndIf
   If arenaRopes=6 Then r=160 : g=0 : b=0 ;all red
   If arenaRopes=7 Then r=0 : g=130 : b=250 ;all blue
   If arenaRopes=8 Then r=40 : g=40 : b=40 ;all black
   If arenaRopes=9 Then r=255 : g=255 : b=255 ;all white
  EndIf
  ;apply variations
  For count=1 To 4
   If matchRopes=0 Then EntityColor FindChild(rope(cyc),"Rope0"+count),r,g,b
   If matchRopes>0 And tRopes(matchRopes)>0 Then EntityTexture FindChild(rope(cyc),"Rope0"+count),tRopes(matchRopes) 
   If matchRopes=>1 Then EntityShininess FindChild(rope(cyc),"Rope0"+count),1.0
   If matchRopes=>2 Then EntityFX FindChild(rope(cyc),"Rope0"+count),9
  Next
 Next
End Function

;--------------------------------------------------------------------
;/////////////////////////// LOAD CAGE //////////////////////////////
;--------------------------------------------------------------------
Function LoadCage()
 cage=LoadAnimMesh("World/Cage/Cage.3ds")
 For cyc=1 To 4
  ;store original location
  limb=FindChild(cage,"Frame0"+cyc)
  cageOrigX#(cyc)=EntityX(limb,1) : cageOrigZ#(cyc)=EntityZ(limb,1)
  cageX#(cyc)=cageOrigX#(cyc) : cageZ#(cyc)=cageOrigZ#(cyc)
  cageTX#(cyc)=cageX#(cyc) : cageTZ#(cyc)=cageZ#(cyc) 
  ;bar variations
  If matchCage=<3 
   If tCage(1)>0 Then EntityTexture FindChild(cage,"Wall0"+cyc),tCage(1) ;steel bars
   If matchCage=2 
    EntityColor FindChild(cage,"Wall0"+cyc),80,120,180 ;blue bars
    EntityColor FindChild(cage,"Frame0"+cyc),80,120,180
   EndIf
   If matchCage=3 
    EntityColor FindChild(cage,"Wall0"+cyc),60,60,60 ;black bars
    EntityColor FindChild(cage,"Frame0"+cyc),60,60,60
   EndIf
  EndIf
  ;wire mesh
  If matchCage=4 And tCage(2)>0 Then EntityTexture FindChild(cage,"Wall0"+cyc),tCage(2)
 Next
End Function

;--------------------------------------------------------------------
;//////////////////////// LOAD ATMOSPHERE ///////////////////////////
;--------------------------------------------------------------------
Function LoadAtmos()
 ;CAMERA
 cam=CreateCamera()
 CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
 CameraRange cam,1,1500
 EntityType cam,3,0
 EntityRadius cam,2
 camX#=0 : camZ#=-150 : camY#=80 
 If matchOfficial=0 Then camX#=0 : camZ#=800 : camY#=10 
 If screen=0 Then camX#=60 : camZ#=320 : camY#=40 
 PositionEntity cam,camX#,camY#,camZ#
 camType=3 : camFoc=1 : camTarget=1
 ;assistants
 If screen>0 Then camListener=CreateListener(cam,0.005,0.005,0.005)
 camPivot=CreatePivot()
 dummy=CreatePivot() 
 ;ATMOSPHERICS
 CameraFogMode cam,0 
 If arenaAtmos>0
  ;settings
  CameraFogMode cam,1
  CameraFogRange cam,200,800
  ;variations
  If arenaAtmos=1 Then CameraFogColor cam,0,0,0 ;black
  If arenaAtmos=2 Then CameraFogColor cam,200,200,200 ;white
  If arenaAtmos=3 Then CameraFogColor cam,160,130,100 ;cream
  If arenaAtmos=4 Then CameraFogColor cam,200,200,240 ;daylight 
  If arenaAtmos=5 Then CameraFogColor cam,100,90,120 ;purple
  If arenaAtmos=6 Then CameraFogColor cam,100,0,0 ;red
  If arenaAtmos=7 Then CameraFogColor cam,0,100,0 ;green
  If arenaAtmos=8 Then CameraFogColor cam,0,0,100 ;blue
  If arenaAtmos=9 Then CameraFogColor cam,Rnd(0,255),Rnd(0,255),Rnd(0,255) ;random
 EndIf
 ;LIGHTING
 lightPreset=arenaLight
 ;ambient light
 light=CreateLight(1) 
 lightR#=100 : lightG#=100 : lightB#=100
 ambR#=100 : ambG#=100 : ambB#=100 
 ;location
 lightPivot=CreatePivot()
 PositionEntity light,-200,200,-200
 PositionEntity lightPivot,0,20,0 
 PointEntity light,lightPivot
End Function

;--------------------------------------------------------------------
;////////////////////// MANAGE ATMOSPHERE ///////////////////////////
;--------------------------------------------------------------------
Function ManageAtmos()
 ;force logical setting
 If (arenaPreset=>15 And arenaPreset=<17) Or fed=7 Then lightPreset=0 ;daylight
 If game=1 And gamArenaLight(slot)=3 Then lightPreset=3 ;power failure
 ;light settings
 If lightPreset=0 ;normal
  ambTR#=200 : ambTG#=200 : ambTB#=200
  lightTR#=200 : lightTG#=200 : lightTB#=200
 EndIf
 If lightPreset=1 ;bright
  ambTR#=240 : ambTG#=240 : ambTB#=240
  lightTR#=250 : lightTG#=250 : lightTB#=250
 EndIf
 If lightPreset=2 ;dim
  ambTR#=100 : ambTG#=100 : ambTB#=100
  lightTR#=100 : lightTG#=100 : lightTB#=100
 EndIf
 If lightPreset=3 ;dark
  ambTR#=30 : ambTG#=30 : ambTB#=30
  lightTR#=30 : lightTG#=30 : lightTB#=30
 EndIf
 If lightPreset=4 ;dark lit
  ambTR#=50 : ambTG#=50 : ambTB#=50
  lightTR#=200 : lightTG#=180 : lightTB#=160
 EndIf
 If lightPreset=5 ;smooth multi (dull)
  randy=Rnd(0,100)
  If randy=1 Then ambTR#=Rnd(0,100)
  If randy=2 Then ambTG#=Rnd(0,100)
  If randy=3 Then ambTB#=Rnd(0,100)
  If randy=4 Then lightTR#=Rnd(0,100)
  If randy=5 Then lightTG#=Rnd(0,100)
  If randy=6 Then lightTB#=Rnd(0,100)
 EndIf
 If lightPreset=6 ;fast multi (dull)
  ambR#=Rnd(0,100) : ambG#=Rnd(0,100) : ambB#=Rnd(0,100)
  lightR#=Rnd(0,100) : lightG#=Rnd(0,100) : lightB#=Rnd(0,100)
 EndIf
 If lightPreset=7 ;smooth multi (bright)
  randy=Rnd(0,100)
  If randy=1 Then ambTR#=Rnd(0,255)
  If randy=2 Then ambTG#=Rnd(0,255)
  If randy=3 Then ambTB#=Rnd(0,255)
  If randy=4 Then lightTR#=Rnd(0,255)
  If randy=5 Then lightTG#=Rnd(0,255)
  If randy=6 Then lightTB#=Rnd(0,255)
 EndIf
 If lightPreset=8 ;fast multi (bright)
  ambR#=Rnd(0,255) : ambG#=Rnd(0,255) : ambB#=Rnd(0,255)
  lightR#=Rnd(0,255) : lightG#=Rnd(0,255) : lightB#=Rnd(0,255)
 EndIf
 If lightPreset=9 ;reds
  If ambTG#>50 Then ambTG#=50
  If ambTB#>50 Then ambTB#=50
  randy=Rnd(0,50)
  If randy=0 Then ambTR#=Rnd(50,255)
  lightTR#=200 : lightTG#=100 : lightTB#=100
 EndIf
 If lightPreset=10 ;greens
  If ambTR#>50 Then ambTR#=50
  If ambTB#>50 Then ambTB#=50
  randy=Rnd(0,50)
  If randy=0 Then ambTG#=Rnd(50,255)
  lightTR#=100 : lightTG#=200 : lightTB#=100
 EndIf
 If lightPreset=11 ;blues
  If ambTR#>50 Then ambTR#=50
  If ambTG#>50 Then ambTG#=50
  randy=Rnd(0,50)
  If randy=0 Then ambTB#=Rnd(50,255)
  lightTR#=100 : lightTG#=100 : lightTB#=200
 EndIf
 If lightPreset=12 ;yellows
  ambR#=255 : ambG#=Rnd(150,250) : ambB#=Rnd(0,150)
  lightTR#=255 : lightTG#=Rnd(150,250) : lightTB#=Rnd(0,150)
 EndIf
 If lightPreset=13 ;pink
  ambR#=255 : ambG#=Rnd(50,250) : ambB#=255
  lightTR#=255 : lightTG#=Rnd(50,250) : lightTB#=255
 EndIf
 ;apply ambience
 changer#=5.0
 If ambR#<ambTR# Then ambR#=ambR#+changer#
 If ambR#>ambTR# Then ambR#=ambR#-changer#
 If ambG#<ambTG# Then ambG#=ambG#+changer#
 If ambG#>ambTG# Then ambG#=ambG#-changer#
 If ambB#<ambTB# Then ambB#=ambB#+changer#
 If ambB#>ambTB# Then ambB#=ambB#-changer# 
 AmbientLight ambR#,ambG#,ambB#
 ;apply lighting
 If lightR#<lightTR# Then lightR#=lightR#+changer#
 If lightR#>lightTR# Then lightR#=lightR#-changer#
 If lightG#<lightTG# Then lightG#=lightG#+changer#
 If lightG#>lightTG# Then lightG#=lightG#-changer#
 If lightB#<lightTB# Then lightB#=lightB#+changer#
 If lightB#>lightTB# Then lightB#=lightB#-changer# 
 LightColor light,lightR#,lightG#,lightB#
End Function

;--------------------------------------------------------------------
;//////////////////// CAMERA OPERATIONS /////////////////////////////
;--------------------------------------------------------------------
Function Camera()
 ;store old position
 camOldX#=camX# : camOldZ#=camZ#
 ;CAMERA SELECTION
 ;timers
 camTim=camTim-1
 If camTim<0 Then camTim=0
 If screen=50
  ;browse all 
  If KeyDown(59) And keytim=0 Then camType=camType-1 : camTim=200 : camOverride=0 : keytim=6 : PlaySound sCamera
  If KeyDown(60) And keytim=0 Then camType=camType+1 : camTim=200 : camOverride=0 : keytim=6 : PlaySound sCamera 
  If camType<0 Then camType=no_cams
  If camType>no_cams Then camType=0 
  ;shortcuts
  For count=1 To 10
   If KeyDown(count+1) And keytim=0 
    PlaySound sCamera : camTim=200 : camOverride=0 : keytim=10
    If camType<>camShortcut(count) Then camType=camShortcut(count) Else ZoomCamera()
   EndIf
  Next
  ;zoom to target
  If KeyDown(61) And keytim=0 Then ZoomCamera() : camTim=200 : camOverride=0 : keytim=6 : PlaySound sCamera
 EndIf
 ;trigger manual
 If KeyDown(21) And CamConflict(21)=0 Then camType=0 : camTim=100 : camOverride=0
 If KeyDown(23) And CamConflict(23)=0 Then camType=0 : camTim=100 : camOverride=0
 For count=35 To 38
  If KeyDown(count) And CamConflict(count)=0 Then camType=0 : camTim=100
 Next
 ;CAMERA FOCUS
 ;browse left
 If KeyDown(12) And keytim=0
  camTim=200 : keytim=8 : PlaySound sCamera
  Repeat 
   camFoc=camFoc-1 : satisfied=1
   If camFoc<1 Then camFoc=no_plays
   If pRole(camFoc)=3 And pOutTim(camFoc)=0 Then satisfied=0
   If pHidden(camFoc)>0 Then satisfied=0
  Until satisfied=1
 EndIf
 ;browse right
 If KeyDown(13) And keytim=0
  camTim=200 : keytim=8 : PlaySound sCamera
  Repeat 
   camFoc=camFoc+1 : satisfied=1
   If camFoc>no_plays Then camFoc=1
   If pRole(camFoc)=3 And pOutTim(camFoc)=0 Then satisfied=0 
   If pHidden(camFoc)>0 Then satisfied=0
  Until satisfied=1
 EndIf
 ;limits
 If camFoc<1 Then camFoc=no_plays
 If camFoc>no_plays Then camFoc=1
 ;temporary override
 If matchPause=0 Then camTempTim=camTempTim-1 
 If camTempTim>5 And camTempFoc>0 Then camFoc=camTempFoc
 If camTempTim=>1 And camTempTim=<5 Then camFoc=camOldFoc
 If camTempTim<1 Then camTempTim=0 : camTempFoc=0
 ;CAMERA TYPES
 ;manual control
 If camType=0
  If KeyDown(36) And CamConflict(36)=0 Then camX#=camX#-2 : camTX#=camX#
  If KeyDown(38) And CamConflict(38)=0 Then camX#=camX#+2 : camTX#=camX#
  If KeyDown(35) And CamConflict(35)=0 Then camY#=camY#-2 : camTY#=camY#
  If KeyDown(21) And CamConflict(21)=0 Then camY#=camY#+2 : camTY#=camY#
  If KeyDown(23) And CamConflict(23)=0 Then camZ#=camZ#+2 : camTZ#=camZ#
  If KeyDown(37) And CamConflict(37)=0 Then camZ#=camZ#-2 : camTZ#=camZ#
  camOverride=0
 EndIf
 ;head-shot
 If camType=1 
  ResetDummy(camFoc)
  MoveEntity dummy,9,0,22
  camTX#=EntityX(dummy) : camTZ#=EntityZ(dummy)
  camTY#=RealY#(camFoc)+30
  ;camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+25 : camTZ#=RealZ#(camFoc)-20
  If screen=>52 
   If negSetting=1 And camFoc=1 Then camTX#=pX#(camFoc)-9 : camTY#=pY#(camFoc)+23 : camTZ#=pZ#(camFoc)+22 ;negotiation subject
   If negSetting=1 And camFoc=2 Then camTX#=pX#(camFoc)+9 : camTY#=pY#(camFoc)+23 : camTZ#=pZ#(camFoc)-22 ;negotiation host
   If negSetting=>2 And negSetting=<3 And camFoc=1 Then camTX#=pX#(camFoc)-6 : camTY#=RealY#(camFoc)+28 : camTZ#=pZ#(camFoc)-27 ;locker room player
   If negSetting=>2 And negSetting=<3 And camFoc=2 Then camTX#=pX#(camFoc)-22 : camTY#=RealY#(camFoc)+28 : camTZ#=pZ#(camFoc)-16 ;locker room visitor
   If screen=54 Then camTX#=135 : camTY#=27 : camTZ#=715 ;trainee
   If negSetting=4 And camFoc=1 Then camTX#=pX#(camFoc)+18 : camTY#=RealY#(camFoc)+28 : camTZ#=pZ#(camFoc)-15 ;hospital patient
   If negSetting=4 And camFoc=2 Then camTX#=pX#(camFoc)+8 : camTY#=RealY#(camFoc)+28 : camTZ#=pZ#(camFoc)-25 ;hospital doctor
  EndIf
 EndIf
 ;close follow
 If camType=2 Then camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+30 : camTZ#=RealZ#(camFoc)-40
 ;normal follow
 If camType=3 Then camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+40 : camTZ#=RealZ#(camFoc)-60
 ;distant follow
 If camType=4 Then camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+50 : camTZ#=RealZ#(camFoc)-80
 ;low
 If camType=5 Then camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+15 : camTZ#=RealZ#(camFoc)-50
 ;angled
 If camType=6 Then camTX#=RealX#(camFoc)-30 : camTY#=RealY#(camFoc)+40 : camTZ#=RealZ#(camFoc)-60
 ;sidways
 If camType=7 Then camTX#=RealX#(camFoc)-45 : camTY#=RealY#(camFoc)+30 : camTZ#=RealZ#(camFoc)
 ;behind
 If camType=8 Then camTX#=RealX#(camFoc)+10 : camTY#=RealY#(camFoc)+20 : camTZ#=RealZ#(camFoc)+40
 ;bird's eye
 If camType=9 
  camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+100 : camTZ#=RealZ#(camFoc)
  If RealZ#(camFoc)>460 Then camTY#=50
  If RealZ#(camFoc)>560 Then camTY#=80
  If camZ#<470 And camTZ#>460 Then camTY#=35
 EndIf
 ;distant
 If camType=10 Then camTX#=0 : camTY#=100 : camTZ#=-220
 ;fan view
 If camType=11 Then camTX#=-200 : camTY#=50 : camTZ#=-50
 ;announcer's view
 If camType=12 Then camTX#=-115 : camTY#=35 : camTZ#=150 
 ;ref's view
 If camType=13 And camFoc>0
  looker=2
  For count=1 To no_plays
   If count<>camFoc And pRole(count)=1 Then looker=count
  Next
  limb=pLimb(looker,1)
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1)
  PointEntity dummy,p(camFoc)
  MoveEntity dummy,0,3,8
  camX#=EntityX(dummy) : camY#=EntityY(dummy) : camZ#=EntityZ(dummy)
  camTX#=camX# : camTY#=camY# : camTZ#=camZ#
 EndIf
 ;first person
 If camType=14 And camFoc>0
  limb=pLimb(camFoc,1)
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1)
  RotateEntity dummy,EntityPitch(limb,1),EntityYaw(limb,1),EntityRoll(limb,1)
  MoveEntity dummy,0,3,8
  camX#=EntityX(dummy) : camY#=EntityY(dummy) : camZ#=EntityZ(dummy)
  camTX#=camX# : camTY#=camY# : camTZ#=camZ#
 EndIf
 ;spontaneous setting
 If camType=15 
  camTX#=RealX#(camFoc) : camTY#=RealY#(camFoc)+40 : camTZ#=RealZ#(camFoc)-60
  randy=Rnd(0,500)
  If randy=0 And camOverride=0 And matchPause=0
   camTempX#=camTX#+Rnd(-50,50) : camTempZ#=camTZ#+Rnd(-50,50)
   camTempY#=camTY#+Rnd(-30,30) : camOverride=Rnd(200,500)
  EndIf
  If randy=1 And camOverride=0 And matchPause=0 
   camTempX#=camTX#+Rnd(-20,20) : camTempZ#=camTZ#
   camTempY#=camTY# : camOverride=Rnd(200,500)
  EndIf
 EndIf
 ;entrance variations
 randy=Rnd(0,100)
 If randy=0 And matchState=1 And matchEnter>0 And pControl(camFoc)=0 And pZ#(camFoc)>120 And pZ#(camFoc)<450 And camOverride=0 And matchPause=0
  camOverride=400
  randy=Rnd(0,1)
  If randy=0 Then camTempX#=-30 Else camTempX#=30
  camTempZ#=camZ# : camTempY#=20
 EndIf
 ;spontaenous override
 If matchPause=0 Then camOverride=camOverride-1
 If camOverride<0 Then camOverride=0
 If camOverride>100 Then camTX#=camTempX# : camTY#=camTempY# : camTZ#=camTempZ#
 ;'CONTAIN ALL' PROCESS
 If camType=no_cams
  ;find values
  hiX1#=999 : guyX1=0 : hiX2#=-999 : guyX2=0
  hiZ#=999 : guyZ=0 : heighter#=0 : loSpeed#=5
  For count=1 To no_plays
   If (pRole(count)=0 And pEliminated(count)=0 And (LegalMan(count) Or pChaosTim(count)>0)) Or pControl(count)>0
    If pY#(count)>heighter# Then heighter#=pY#(count)
    If RealX#(count)<hiX1# Then guyX1=count : hiX1#=RealX#(count) ;leftmost guy
    If RealX#(count)>hiX2# Then guyX2=count : hiX2#=RealX#(count) ;rightmost guy
    If RealZ#(count)<hiZ# Then guyZ=count : hiZ#=RealZ#(count) ;lowermost guy
    If pSpeed#(count)-0.2<loSpeed# Then loSpeed#=pSpeed#(count)-0.2
    If TurnViable(count)<>1 Then loSpeed#=0.25
   EndIf
  Next
  ;calculate X
  differ#=(RealX#(guyX2)-RealX#(guyX1))/2
  camTX#=RealX#(guyX1)+differ#
  ;calculate Z
  backer#=differ#*1.5
  If backer#<50 Then backer#=50
  If RealZ#(guyX1)<RealZ#(guyX2) Then checker=guyX1 Else checker=guyX2
  camTZ#=RealZ#(checker)-backer#
  If camTZ#>RealZ#(guyZ)-50 Then camTZ#=RealZ#(guyZ)-50
  ;average Y#
  camTY#=(heighter#+18)+(backer#/2)
  If camTY#>100 Then camTY#=100 
 EndIf 
 ;PIVOT PLACEMENT
 ;standard targets
 camPivTX#=pX#(camFoc)
 camPivTY#=pY#(camFoc)+25
 camPivTZ#=pZ#(camFoc) 
 ;promo offset
 If camType=1 Then camPivTY#=camPivTY#+2
 ;projected targets
 If PointViable(camFoc)>0
  camPivTX#=RealX#(camFoc) : camPivTZ#=RealZ#(camFoc)
  If PointViable(camFoc)=1 Then camPivTY#=EntityY(pLimb(camFoc,30),1)+10
  If camPivTY#<25 Then camPivTY#=25
 EndIf
 ;turnbuckle offset
 If (camType=>2 And camType=<6) Or camType=15
  If pFoc(camFoc)>0 And pY#(camFoc)=wBuckle#
   inside=InsideRing(pX#(pFoc(camFoc)),pZ#(pFoc(camFoc)),0)
   If (pPlatform(camFoc)=>5 And pPlatform(camFoc)=<6 And inside=1) Or (pPlatform(camFoc)=>7 And pPlatform(camFoc)=<8 And inside=0 And pZ#(pFoc(camFoc))<pZ#(camFoc))
    camTZ#=camTZ#-50 : camPivTZ#=camPivTZ#-30
    If pPlatform(camFoc)=5 Or pPlatform(camFoc)=8 Then camPivTX#=camPivTX#+10
    If pPlatform(camFoc)=6 Or pPlatform(camFoc)=7 Then camPivTX#=camPivTX#-10
   EndIf
  EndIf
 EndIf
 ;CAMERA OPERATION
 ;backstage assistance
 If camPivZ#>470 Or camZ#>470 
  If camTY#>35 And camType>0 And camType<>9 Then camTY#=35
 EndIf
 ;camera tracking 
 If gotim>0
  camSpeed=15
  If matchState=0 Then camSpeed=150
  If matchState=2 Then camSpeed=60
  If matchState=3 And camType=no_cams And matchTeams=2 Then camSpeed=30
  If camType=2 And pAnim(camFoc)=3 Then camSpeed=10
  If camType=1 And pAnim(camFoc)=>2 And pAnim(camFoc)=<3 Then camSpeed=5
  If camTempTim>1 Or camOverride>0 Then camSpeed=60
  If screen=>52 And negSetting=1 Then camSpeed=30
  If screen=>52 And negSetting=>2 Then camSpeed=60
  GetCamSpeeds(camSpeed)
  If camX#<camTX# Then camX#=camX#+camSpeedX#
  If camX#>camTX# Then camX#=camX#-camSpeedX#
  If camY#<camTY# Then camY#=camY#+camSpeedY#
  If camY#>camTY# Then camY#=camY#-camSpeedY#
  If camZ#<camTZ# Then camZ#=camZ#+camSpeedZ#
  If camZ#>camTZ# Then camZ#=camZ#-camSpeedZ#
 EndIf
 ;enforce blocks
 If gotim>0 And (camType<11 Or camType>14)
  For v=0 To no_blocks
   If camOldX#<wBlockX2#(v) And camOldX#>wBlockX1#(v) And camOldZ#<wBlockZ2#(v) And camOldZ#>wBlockZ1#(v)
    trapped=1
   Else
    If camX#<wBlockX2#(v) And camX#>wBlockX1#(v) And camZ#<wBlockZ2#(v) And camZ#>wBlockZ1#(v) And camY#>wBlockY1#(v) And camY#<wBlockY2#(v) And wBlockCam(v)=1
     If camOldX#<wBlockX2#(v) And camOldX#>wBlockX1#(v) Then camZ#=camOldZ#
     If camOldZ#<wBlockZ2#(v) And camOldZ#>wBlockZ1#(v) Then camX#=camOldX#
    EndIf
   EndIf
  Next
 EndIf
 ;world limitations
 If camY#<wGround# Then camY#=wGround#
 If camZ#>567 And camY#>84 Then camY#=84
 If camZ#=>483 And camZ#=<567 And camY#>50 Then camY#=50
 If camZ#<483 
  If arenaType=1 And camY#>200 Then camY#=200
  If arenaType=2 And camY#>130 Then camY#=130
 EndIf
 ;pivot tracking 
 If PointViable(camFoc)>0 Then camSpeed=10 Else camSpeed=5
 GetPivotSpeeds(camSpeed)
 If camPivX#<camPivTX# Then camPivX#=camPivX#+camSpeedX#
 If camPivX#>camPivTX# Then camPivX#=camPivX#-camSpeedX#
 If camPivY#<camPivTY# Then camPivY#=camPivY#+camSpeedY#
 If camPivY#>camPivTY# Then camPivY#=camPivY#-camSpeedY#
 If camPivZ#<camPivTZ# Then camPivZ#=camPivZ#+camSpeedZ#
 If camPivZ#>camPivTZ# Then camPivZ#=camPivZ#-camSpeedZ#
 ;bird's eye offset
 If camType=9 Then camZ#=camZ#-2 : camPivZ#=camPivZ#+2
 ;first person override
 If camType=14
  MoveEntity dummy,0,0,30
  camPivX#=EntityX(dummy) : camPivY#=EntityY(dummy) : camPivZ#=EntityZ(dummy)
 EndIf
 ;position pivot
 PositionEntity cam,camX#,camY#,camZ# 
 PositionEntity camPivot,camPivX#,camPivY#,camPivZ#
 ;meeting offsets
 If screen=>52 And pAnim(camFoc)<10 Then PositionEntity camPivot,pX#(camFoc),pY#(camFoc)+25,pZ#(camFoc)
 If screen=>52 And pAnim(camFoc)=>10 Then PositionEntity camPivot,pX#(camFoc),pY#(camFoc)+28,pZ#(camFoc)
 If screen=54 Then PositionEntity camPivot,185,23,740
 ;point at pivot
 PointEntity cam,camPivot
 ;contain all override
 If camType=no_cams Then RotateEntity cam,20,0,0
 ;hospital override
 If screen=55 And camFoc=1 Then PointEntity cam,FindChild(p(camFoc),"Head")
End Function

;ZOOM CAMERA TO TARGET
Function ZoomCamera()
 camX#=camTX#
 camY#=camTY#
 camZ#=camTZ#
End Function

;CALCULATE SMOOTH CAMERA SPEEDS
Function GetCamSpeeds(factor)
 ;calculate differences & identify leader
 camDiffX#=GetDiff#(camX#,camTX#)
 camLead#=camDiffX# : lead=1
 camDiffY#=GetDiff#(camY#,camTY#) 
 If camDiffY#>camLead# Then camLead#=camDiffY# : lead=2
 camDiffZ#=GetDiff#(camZ#,camTZ#)
 If camDiffZ#>camLead# Then camLead#=camDiffZ# : lead=3
 ;make anchor speed from leading difference
 camAnchor#=camLead#/factor
 ;calculate respective speeds
 If lead=1 Then camSpeedX#=camAnchor# Else camSpeedX#=camAnchor#*(camDiffX#/camLead#)
 If lead=2 Then camSpeedY#=camAnchor# Else camSpeedY#=camAnchor#*(camDiffY#/camLead#)
 If lead=3 Then camSpeedZ#=camAnchor# Else camSpeedZ#=camAnchor#*(camDiffZ#/camLead#)
End Function

;CALCULATE SMOOTH PIVOT SPEEDS
Function GetPivotSpeeds(factor)
 ;calculate differences & identify leader
 camDiffX#=GetDiff#(camPivX#,camPivTX#)
 camLead#=camDiffX# : lead=1
 camDiffY#=GetDiff#(camPivY#,camPivTY#) 
 If camDiffY#>camLead# Then camLead#=camDiffY# : lead=2
 camDiffZ#=GetDiff#(camPivZ#,camPivTZ#)
 If camDiffZ#>camLead# Then camLead#=camDiffZ# : lead=3
 ;make anchor speed from leading difference
 camAnchor#=camLead#/factor
 ;calculate respective speeds
 If lead=1 Then camSpeedX#=camAnchor# Else camSpeedX#=camAnchor#*(camDiffX#/camLead#)
 If lead=2 Then camSpeedY#=camAnchor# Else camSpeedY#=camAnchor#*(camDiffY#/camLead#)
 If lead=3 Then camSpeedZ#=camAnchor# Else camSpeedZ#=camAnchor#*(camDiffZ#/camLead#)
End Function

;RESET DUMMY (in terms of target)
Function ResetDummy(cyc)
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,pA#(cyc),0
End Function

;CAMERA vs CONTROL CONFLICTS?
Function CamConflict(command)
 conflict=0
 If command=keyAttack Or command=keyGrab Or command=keySwitch Then conflict=1
 If command=keyRun Or command=keyPick Or command=keyTaunt Then conflict=1 
 Return conflict
End Function

;GHOST SCENERY
Function GhostScenery()
If matchState=>3 And optGhost>0
 ;reset status
 For count=1 To 20
  wOldGhost(count)=wGhost(count) 
  wGhost(count)=0
 Next
 ;arena issues
 If optGhost=2
  ;behind ring
  If RealX#(camFoc)>wBlockX1#(0)+5 And RealX#(camFoc)<wBlockX2#(0)-5 And RealZ#(camFoc)>wBlockZ2#(0) And RealZ#(camFoc)<wBlockZ2#(0)+15 And camZ#<wBlockZ2#(0) And camY#<70 
   wGhost(1)=1
  EndIf
  If wGhost(1)<>wOldGhost(1)
   If wGhost(1)=1 Then alpha#=0.8 Else alpha#=1.0
   EntityAlpha FindChild(world,"Apron"),alpha#
   EntityAlpha FindChild(world,"Canvas"),alpha#
   EntityAlpha FindChild(world,"Post01"),alpha#
   EntityAlpha FindChild(world,"Post02"),alpha#
   If matchCage>0
    EntityAlpha FindChild(cage,"Wall01"),alpha#
    EntityAlpha FindChild(cage,"Frame01"),alpha#
   EndIf
  EndIf
  ;video wall
  If RealX#(camFoc)<wBlockX2#(12) Or RealX#(camFoc)>wBlockX1#(13)
   If RealZ#(camFoc)>wBlockZ2#(12) And camZ#<wBlockZ2#(12) Then wGhost(2)=1
   If RealZ#(camFoc)<wBlockZ1#(12) And camZ#>wBlockZ1#(12) Then wGhost(2)=1
  EndIf
  If wGhost(2)<>wOldGhost(2)
   If wGhost(2)=1 Then alpha#=0.6 Else alpha#=1.0
   EntityAlpha FindChild(world,"Entrance"),alpha#
   EntityAlpha FindChild(world,"Screen01"),alpha#
  EndIf
  ;gorilla wall
  If RealX#(camFoc)<wBlockX2#(15)-10 Or RealX#(camFoc)>wBlockX1#(16)+10
   If RealZ#(camFoc)>wBlockZ2#(15) And camZ#<wBlockZ2#(15) Then wGhost(3)=1
   If RealZ#(camFoc)<wBlockZ1#(15) And camZ#>wBlockZ2#(15) Then wGhost(3)=1
  EndIf
  If wGhost(3)<>wOldGhost(3)
   If wGhost(3)=1 Then alpha#=0.6 Else alpha#=1.0
   EntityAlpha FindChild(world,"Wall01"),alpha#
   EntityAlpha FindChild(world,"Wall07"),alpha#
   EntityAlpha FindChild(world,"Gorilla"),alpha#
  EndIf
  ;backstage ceiling
  If RealZ#(camFoc)>560 And camZ#>500 And camY#>80 Then wGhost(4)=1
  If wGhost(4)<>wOldGhost(4)
   If wGhost(4)=1 Then alpha#=0.6 Else alpha#=1.0
   EntityAlpha FindChild(world,"BackRoof"),alpha#
  EndIf
  ;curtain
  If RealZ#(camFoc)>465 And camZ#<465 Then wGhost(5)=1
  If RealZ#(camFoc)<465 And camZ#>465 Then wGhost(5)=1
  If wGhost(5)<>wOldGhost(5)
   If wGhost(5)=1 Then alpha#=0.7 Else alpha#=0.8
   EntityAlpha FindChild(world,"Curtain"),alpha#
  EndIf
 EndIf
 ;cage walls
 If optGhost=>1 And matchCage>0
  ;south wall
  If InsideRing(RealX#(camFoc),RealZ#(camFoc),0) And RealZ#(camFoc)>wBlockZ1#(0) And camZ#<wBlockZ1#(0)+10 Then wGhost(10)=1
  If wGhost(10)<>wOldGhost(10)
   If wGhost(10)=1 Then alpha#=0.6 Else alpha#=1.0
   EntityAlpha FindChild(cage,"Wall03"),alpha#
   EntityAlpha FindChild(cage,"Frame03"),alpha#
  EndIf
  ;north wall
  If RealZ#(camFoc)>wBlockZ2#(0) And camZ#<wBlockZ2#(0)+10 
   If RealX#(camFoc)>wBlockX1#(0) And RealX#(camFoc)<wBlockX2#(0) Then wGhost(11)=1
  EndIf
  If wGhost(11)<>wOldGhost(11)
   If wGhost(11)=1 Then alpha#=0.6 Else alpha#=1.0
   EntityAlpha FindChild(cage,"Wall01"),alpha#
   EntityAlpha FindChild(cage,"Frame01"),alpha#
  EndIf
 EndIf
EndIf
End Function