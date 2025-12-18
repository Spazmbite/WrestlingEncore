;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING ENCORE: EDITOR ----------------------------
;//////////////////////////////////////////////////////////////////////////////

;-------------------------------------------------------------------
;///////////////////// 51. EDIT CHARACTER //////////////////////////
;-------------------------------------------------------------------
Function EditCharacter()
;loading
Loader("Please Wait","Loading Character")
;camera
cam=CreateCamera()
CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
PositionEntity cam,0,22,-36
If GraphicsWidth()>1024 Then PositionEntity cam,0,22,-42
RotateEntity cam,4,0,0
CameraClsMode cam,0,1
MoveMouse 400,600
;load existing costume
If saver=<no_chars Then CopyChar(saver,0)
If saver=no_chars+1 Then CopyChar(1,0)
charFed(0)=fed
;translate relationships
charFriend(0)=0 : charEnemy(0)=0 : charManager(0)=0
For count=0 To fedSize(fed)
 If fedRoster(fed,count)=charFriend(saver) Then charFriend(0)=count
 If fedRoster(fed,count)=charEnemy(saver) Then charEnemy(0)=count 
 If fedRoster(fed,count)=charManager(saver) Then charManager(0)=count
Next
;crop skills at initiation
If screenAgenda=3 
 charStr(0)=50 : charSkl(0)=50 : charAgl(0)=50
 charStam(0)=50 : charTough(0)=50
EndIf
;load model
InitModel(1)
;light
light=CreateLight(1)
PositionEntity light,50,30,-80
LightColor light,150,150,150
AmbientLight 200,200,200
;thumbnail
pic=CreateSprite()
ScaleSprite pic,5,5
PositionEntity pic,22.9,29,0
If GraphicsWidth()=1280 And GraphicsHeight()=800 Then PositionEntity pic,27,29,0
;re-organize pages
For count=2 To 4
 If game=1 Then pageList(count)=17 Else pageList(count)=16
Next 
;assess blocks
blockData=0 : blockImage=0
blockAttacks=0 : blockMoves=0
If game=1 And screenAgenda<>3
 blockData=1
 If gamClause(slot,1)=0 Or gamPromo(slot,GetDate())=29 Then blockImage=1
 If gamVariable(slot)=13 Or gamOldVariable(slot)=13 Then blockImage=1
 If gamVariable(slot)=11 Or gamOldVariable(slot)=11 Then blockAttacks=1
 If gamVariable(slot)=12 Or gamOldVariable(slot)=12 Then blockMoves=1
EndIf
;frame rating
period=1000/FPS
time=MilliSecs()-period
UpdateWorld
RenderWorld 1
;MAIN LOOP
testing=0
page=1 : foc=1 : oldfoc=foc
go=0 : gotim=0 : keytim=10
While go=0
	
 Cls
 Repeat
  elapsed=MilliSecs()-time
 Until elapsed
 ticks=elapsed/period
 tween#=Float(elapsed Mod period)/Float(period)
	
 For k=1 To ticks
	time=time+period
	If k=ticks Then CaptureWorld
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
	screenCall=0 
	gotim=gotim+1
	If gotim>20 And keytim=0
	 ;quit
	 If KeyDown(1) And keytim=0 Then go=-1
	 ;activations
	 mouser=0
	 If MouseDown(1) And foc<>99 And (MouseX()<rX#(300) Or MouseX()>rX#(500)) Then mouser=1
	 If KeyDown(28) Or JoyDown(1) Or mouser=1
	  ;next page
	  If foc=pageList(page)-2 And keytim=0
	   PlaySound sMenuGo : gotim=0 : keytim=6 : page=page+1
	   If page>6 Then page=1
	   foc=pageList(page)-2
	  EndIf
	  ;previous page
	  If foc=pageList(page)-1 And keytim=0
	   PlaySound sMenuBack : gotim=0 : keytim=6 : page=page-1
	   If page<1 Then page=6
	   foc=pageList(page)-1
	  EndIf
	  ;leave
	  If foc=pageList(page) And keytim=0 Then go=1
	  ;detour to cosmetics
	  If game=1 And screenAgenda<>3 And page=>2 And page=<4 And foc=11 Then go=2
	 EndIf
	EndIf
	
	;CONFIGURATION
	;highlight options
	If gotim>20 And keytim=0
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
    EndIf
    If foc<1 Then foc=pageList(page)
	If foc>pageList(page) Then foc=1 
    ;prepare to examine model
	If oldfoc=99 Then foc=pageList(page)
	If MouseX()>rX#(270) And MouseX()<rX#(530) 
	 If MouseDown(1) Or MouseDown(2) Then foc=99
	 If foc=99 And oldfoc<>99 Then void=MouseXSpeed()
	EndIf
	If (MouseDown(1) Or MouseDown(2)) And oldfoc=99 Then foc=99 
	
	;calculate points system
	If screenAgenda=3 Then gamPoints=280 Else gamPoints=999
	gamPoints=gamPoints-charStr(0)
	gamPoints=gamPoints-charSkl(0)
	gamPoints=gamPoints-charAgl(0)
	gamPoints=gamPoints-charStam(0) 
	gamPoints=gamPoints-charTough(0)       
	If gamPoints<0 Then gamPoints=0
 	
    ;************************** 1. PROFILE **************************
    oldTheme=charTheme(0) : oldPitch=charThemePitch(0)
	If page=1 And foc>1 And foc<99 And keytim=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  If blockData=0
	   If foc=2 Then charAge(0)=charAge(0)-1 : PlaySound sMenuBrowse : keytim=3
	   If foc=3 Then charHeight(0)=charHeight(0)-1 : PlaySound sMenuBrowse : keytim=3
	   If foc=4 Then charPop(0)=charPop(0)-1 : PlaySound sMenuBrowse : keytim=3
       If foc=5 Then charStr(0)=charStr(0)-1 : PlaySound sMenuBrowse : keytim=3
       If foc=6 Then charSkl(0)=charSkl(0)-1 : PlaySound sMenuBrowse : keytim=3
       If foc=7 Then charAgl(0)=charAgl(0)-1 : PlaySound sMenuBrowse : keytim=3
       If foc=8 Then charStam(0)=charStam(0)-1 : PlaySound sMenuBrowse : keytim=3
	   If foc=9 Then charTough(0)=charTough(0)-1 : PlaySound sMenuBrowse : keytim=3
       If foc=10 Then charAtt(0)=charAtt(0)-1 : PlaySound sMenuBrowse : keytim=3
	   If foc=11 Then charHap(0)=charHap(0)-1 : PlaySound sMenuBrowse : keytim=3
	   If foc=12 Then charHeel(0)=charHeel(0)-1 : PlaySound sMenuBrowse : keytim=8
	   If foc=14 Then charFriend(0)=charFriend(0)-1 : PlaySound sMenuBrowse : keytim=5
	   If foc=15 Then charEnemy(0)=charEnemy(0)-1 : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If foc=16 
	   PlaySound sMenuBrowse : keytim=5
	   Repeat 
	    charManager(0)=charManager(0)-1
	    If charManager(0)<0 Then charManager(0)=fedSize(fed)
	   Until SuitableManager(fedRoster(fed,charManager(0))) Or charManager(0)=0 
	  EndIf
	  If foc=13 Then charEyes(0)=charEyes(0)-1 : PlaySound sMenuBrowse : keytim=8
	  If foc=17 And blockImage=0 Then charTheme(0)=charTheme(0)-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=18 Then charThemePitch(0)=charThemePitch(0)-100 : PlaySound sMenuBrowse : keytim=3
	  If foc=19 And blockImage=0 Then charLight(0)=charLight(0)-1 : PlaySound sMenuBrowse : keytim=5  
	  If foc=20 Then charWeapon(0)=charWeapon(0)-1 : PlaySound sMenuBrowse : keytim=5
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  If blockData=0
	   If foc=2 Then charAge(0)=charAge(0)+1 : PlaySound sMenuBrowse : keytim=3
	   If foc=3 Then charHeight(0)=charHeight(0)+1 : PlaySound sMenuBrowse : keytim=3
	   If foc=4 Then charPop(0)=charPop(0)+1 : PlaySound sMenuBrowse : keytim=3
       If foc=5 And gamPoints>0 Then charStr(0)=charStr(0)+1 : PlaySound sMenuBrowse : keytim=3
       If foc=6 And gamPoints>0 Then charSkl(0)=charSkl(0)+1 : PlaySound sMenuBrowse : keytim=3
       If foc=7 And gamPoints>0 Then charAgl(0)=charAgl(0)+1 : PlaySound sMenuBrowse : keytim=3
       If foc=8 And gamPoints>0 Then charStam(0)=charStam(0)+1 : PlaySound sMenuBrowse : keytim=3
	   If foc=9 And gamPoints>0 Then charTough(0)=charTough(0)+1 : PlaySound sMenuBrowse : keytim=3
       If foc=10 Then charAtt(0)=charAtt(0)+1 : PlaySound sMenuBrowse : keytim=3
	   If foc=11 Then charHap(0)=charHap(0)+1 : PlaySound sMenuBrowse : keytim=3
	   If foc=12 Then charHeel(0)=charHeel(0)+1 : PlaySound sMenuBrowse : keytim=8
	   If foc=14 Then charFriend(0)=charFriend(0)+1 : PlaySound sMenuBrowse : keytim=5
	   If foc=15 Then charEnemy(0)=charEnemy(0)+1 : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If foc=16 
	   PlaySound sMenuBrowse : keytim=5
	   Repeat 
	    charManager(0)=charManager(0)+1
	    If charManager(0)>fedSize(fed) Then charManager(0)=0
	   Until SuitableManager(fedRoster(fed,charManager(0))) Or charManager(0)=0 
	  EndIf
	  If foc=13 Then charEyes(0)=charEyes(0)+1 : PlaySound sMenuBrowse : keytim=8
	  If foc=17 And blockImage=0 Then charTheme(0)=charTheme(0)+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=18 Then charThemePitch(0)=charThemePitch(0)+100 : PlaySound sMenuBrowse : keytim=3
	  If foc=19 And blockImage=0 Then charLight(0)=charLight(0)+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=20 Then charWeapon(0)=charWeapon(0)+1 : PlaySound sMenuBrowse : keytim=5
	 EndIf
	 ;randomize
	 If KeyDown(57) Or JoyDown(2)
	  If blockData=0
	   If foc=2 Then charAge(0)=Rnd(18,50) : PlaySound sMenuBrowse : keytim=5
	   If foc=3 Then charHeight(0)=Rnd(0,32) : PlaySound sMenuBrowse : keytim=5
	   If foc=4 Then charPop(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
       If foc=5 Then charStr(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
       If foc=6 Then charSkl(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
       If foc=7 Then charAgl(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
       If foc=8 Then charStam(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
	   If foc=9 Then charTough(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
       If foc=10 Then charAtt(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
	   If foc=11 Then charHap(0)=Rnd(30,99) : PlaySound sMenuBrowse : keytim=5
	   If foc=12 Then charHeel(0)=Rnd(0,1) : PlaySound sMenuBrowse : keytim=5
	   If foc=14 Then charFriend(0)=Rnd(1,fedSize(fed)) : PlaySound sMenuBrowse : keytim=5
	   If foc=15 Then charEnemy(0)=Rnd(1,fedSize(fed)) : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If foc=13 Then charEyes(0)=Rnd(1,3) : PlaySound sMenuBrowse : keytim=5
	  If foc=16 
	   PlaySound sMenuBrowse : keytim=5
	   Repeat 
	    charManager(0)=Rnd(0,fedSize(fed))
	   Until SuitableManager(fedRoster(fed,charManager(0))) Or charManager(0)=0 
	  EndIf
	  If blockImage=0
	   If foc=17 
	    PlaySound sMenuBrowse : keytim=5
	    charTheme(0)=Rnd(1,no_themes)
	    charThemePitch(0)=DefaultPitch(charTheme(0))    
	   EndIf
	   If foc=19 Then charLight(0)=Rnd(0,no_lights) : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If foc=20 Then charWeapon(0)=Rnd(0,weapList) : PlaySound sMenuBrowse : keytim=5 
	 EndIf 
	 ;revert
	 If KeyDown(14)
	  If blockData=0
	   If foc=2 Then charAge(0)=21 : PlaySound sMenuBrowse : keytim=5
	   If foc=3 Then charHeight(0)=12 : PlaySound sMenuBrowse : keytim=5
	   If foc=4 Then charPop(0)=50 : PlaySound sMenuBrowse : keytim=5
       If foc=5 Then charStr(0)=50 : PlaySound sMenuBrowse : keytim=5
       If foc=6 Then charSkl(0)=50 : PlaySound sMenuBrowse : keytim=5
       If foc=7 Then charAgl(0)=50 : PlaySound sMenuBrowse : keytim=5
       If foc=8 Then charStam(0)=50 : PlaySound sMenuBrowse : keytim=5
	   If foc=9 Then charTough(0)=50 : PlaySound sMenuBrowse : keytim=5
       If foc=10 Then charAtt(0)=50 : PlaySound sMenuBrowse : keytim=5
	   If foc=11 Then charHap(0)=50 : PlaySound sMenuBrowse : keytim=5
	   If foc=12 Then charHeel(0)=0 : PlaySound sMenuBrowse : keytim=5
	   If foc=14 Then charFriend(0)=0 : PlaySound sMenuBrowse : keytim=5
	   If foc=15 Then charEnemy(0)=0 : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If foc=13 Then charEyes(0)=2 : PlaySound sMenuBrowse : keytim=5
	  If foc=16 Then charManager(0)=0 : PlaySound sMenuBrowse : keytim=5
	  If blockImage=0
	   If foc=17 Then charTheme(0)=0 : PlaySound sMenuBrowse : keytim=5
	   If foc=18 
	    PlaySound sMenuBrowse : keytim=5
	    charThemePitch(0)=DefaultPitch(charTheme(0))
	   EndIf
	   If foc=19 Then charLight(0)=0 : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If foc=20 Then charWeapon(0)=0 : PlaySound sMenuBrowse : keytim=5
	 EndIf
	 ;recipricate relationships
	 If KeyDown(15) And blockData=0
	  If foc=14 And charFriend(0)>0 Then charFriend(fedRoster(fed,charFriend(0)))=saver : PlaySound sSwing : keytim=10
	  If foc=15 And charEnemy(0)>0 Then charEnemy(fedRoster(fed,charEnemy(0)))=saver : PlaySound sSwing : keytim=10
	  If foc=16 And charManager(0)>0 Then charManager(fedRoster(fed,charManager(0)))=saver : PlaySound sSwing : keytim=10 
     EndIf
	EndIf
	
	;************************** 2-4. COSTUMES **************************
	If page=>2 And page=<4 Then coz=page-1 Else coz=1
	oldArm=charLeftArm(0,coz) 
	If page=>2 And page=<4 And foc<99 And keytim=0
	 ;body
	 If foc=1
	  If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) ;search left
	   PlaySound sMenuBrowse : keytim=6
	   Repeat 
	    charModel(0,coz)=charModel(0,coz)-1
	    If charModel(0,coz)<1 Then charModel(0,coz)=no_models
	   Until ModelType(0,coz)=ModelType(saver,1) Or (coz=>2 And ModelType(0,coz)=ModelType(0,1)) Or (blockData=0 And coz=1)
	  EndIf
	  If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) ;search right
	   PlaySound sMenuBrowse : keytim=6
	   Repeat 
	    charModel(0,coz)=charModel(0,coz)+1
	    If charModel(0,coz)>no_models Then charModel(0,coz)=1
	   Until ModelType(0,coz)=ModelType(saver,1) Or (coz=>2 And ModelType(0,coz)=ModelType(0,1)) Or (blockData=0 And coz=1)
	  EndIf
	 EndIf
	 ;refresh model
	 If foc=1
	  If KeyDown(28) Or JoyDown(1) Then screenCall=1 : PlaySound sMenuGo : keytim=10
	 EndIf
	 ;hairstyle
	 If foc=2 And blockImage=0
	  If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charHairStyle(0,coz)=charHairStyle(0,coz)-1 : PlaySound sMenuBrowse : keytim=6
	  If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charHairStyle(0,coz)=charHairStyle(0,coz)+1 : PlaySound sMenuBrowse : keytim=6 
	  If KeyDown(57) Then charHairStyle(0,coz)=Rnd(0,no_hstyles) : PlaySound sMenuBrowse : keytim=5
	  If KeyDown(14) Then charHairStyle(0,coz)=0 : PlaySound sMenuBack : keytim=10
	 EndIf
	 ;headwear
	 If foc=3
	  If blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charHat(0,coz)=charHat(0,coz)-1 : keytim=6 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charHat(0,coz)=charHat(0,coz)+1 : keytim=6 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charHat(0,coz)=Rnd(0,no_hats) : keytim=6 : PlaySound sMenuBrowse	  
	  EndIf
	  ManipulateColour(charHatCol(0,1,coz),charHatCol(0,2,coz),charHatCol(0,3,coz))
	  charHatCol(0,1,coz)=manipR : charHatCol(0,2,coz)=manipG : charHatCol(0,3,coz)=manipB   
	 EndIf
	 ;eyewear
	 If foc=4
	  If blockImage=0 
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charSpecs(0,coz)=charSpecs(0,coz)-1 : keytim=6 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charSpecs(0,coz)=charSpecs(0,coz)+1 : keytim=6 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charSpecs(0,coz)=Rnd(0,no_specs) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charSpecsCol(0,1,coz),charSpecsCol(0,2,coz),charSpecsCol(0,3,coz))
	  charSpecsCol(0,1,coz)=manipR : charSpecsCol(0,2,coz)=manipG : charSpecsCol(0,3,coz)=manipB     
	 EndIf
	 ;hair
	 If foc=5
	  If blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charHair(0,coz)=charHair(0,coz)-1 : keytim=6 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charHair(0,coz)=charHair(0,coz)+1 : keytim=6 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charHair(0,coz)=Rnd(1,no_hairs) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charHairCol(0,1,coz),charHairCol(0,2,coz),charHairCol(0,3,coz))
	  charHairCol(0,1,coz)=manipR : charHairCol(0,2,coz)=manipG : charHairCol(0,3,coz)=manipB    
	 EndIf
	 ;face 
	 If foc=6 
	  If blockData=0 And blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charFace(0,coz)=charFace(0,coz)-1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charFace(0,coz)=charFace(0,coz)+1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charFace(0,coz)=Rnd(1,no_faces) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charFaceCol(0,1,coz),charFaceCol(0,2,coz),charFaceCol(0,3,coz))
	  charFaceCol(0,1,coz)=manipR : charFaceCol(0,2,coz)=manipG : charFaceCol(0,3,coz)=manipB   
	 EndIf
	 ;body
	 If foc=7
	  If blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charBody(0,coz)=charBody(0,coz)-1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charBody(0,coz)=charBody(0,coz)+1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charBody(0,coz)=Rnd(1,no_bodies) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charBodyCol(0,1,coz),charBodyCol(0,2,coz),charBodyCol(0,3,coz))
	  charBodyCol(0,1,coz)=manipR : charBodyCol(0,2,coz)=manipG : charBodyCol(0,3,coz)=manipB    
	 EndIf
	 ;left arm
	 If foc=8
	  If blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charLeftArm(0,coz)=charLeftArm(0,coz)-1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charLeftArm(0,coz)=charLeftArm(0,coz)+1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charLeftArm(0,coz)=Rnd(1,no_arms) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charLeftArmCol(0,1,coz),charLeftArmCol(0,2,coz),charLeftArmCol(0,3,coz))
	  charLeftArmCol(0,1,coz)=manipR : charLeftArmCol(0,2,coz)=manipG : charLeftArmCol(0,3,coz)=manipB  
	 EndIf
	 ;right arm
	 If foc=9
	  If blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charRightArm(0,coz)=charRightArm(0,coz)-1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charRightArm(0,coz)=charRightArm(0,coz)+1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charRightArm(0,coz)=Rnd(1,no_arms) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charRightArmCol(0,1,coz),charRightArmCol(0,2,coz),charRightArmCol(0,3,coz))
	  charRightArmCol(0,1,coz)=manipR : charRightArmCol(0,2,coz)=manipG : charRightArmCol(0,3,coz)=manipB     
	 EndIf 
	 ;legs
	 If foc=10
	  If blockImage=0
	   If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) Then charLegs(0,coz)=charLegs(0,coz)-1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1) Then charLegs(0,coz)=charLegs(0,coz)+1 : keytim=5 : PlaySound sMenuBrowse
	   If KeyDown(57) Then charLegs(0,coz)=Rnd(1,no_legs) : keytim=6 : PlaySound sMenuBrowse 
	  EndIf
	  ManipulateColour(charLegsCol(0,1,coz),charLegsCol(0,2,coz),charLegsCol(0,3,coz))
	  charLegsCol(0,1,coz)=manipR : charLegsCol(0,2,coz)=manipG : charLegsCol(0,3,coz)=manipB     
	 EndIf	
	 ;tan shortcut
	 If KeyDown(20)
	  PlaySound sMenuGo : keytim=10
	  For col=1 To 3
	   charFaceCol(0,col,coz)=195-(col*10)
	   charBodyCol(0,col,coz)=195-(col*10)
	   charLeftArmCol(0,col,coz)=195-(col*10)
	   charRightArmCol(0,col,coz)=195-(col*10)
	   charLegsCol(0,col,coz)=195-(col*10)
	  Next
	 EndIf  
	 ;clone costumes
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	   If foc=pageList(page)-5 And page<>2 And blockImage=0 Then CloneCostume(0,1,coz) : PlaySound sMenuGo : keytim=10
	   If foc=pageList(page)-4 And page<>3 And blockImage=0 Then CloneCostume(0,2,coz) : PlaySound sMenuGo : keytim=10
	   If foc=pageList(page)-3 And page<>4 And blockImage=0 Then CloneCostume(0,3,coz) : PlaySound sMenuGo : keytim=10
	 EndIf
	EndIf 
	
	;************************** 5. ATTACKS **************************
	If page=5 And foc<99 And keytim=0 And blockAttacks=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) 
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=charAttack(0,count)-1
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=charCrush(0,count)-1
	  Next
      If foc=10 Then charStance(0,1)=charStance(0,1)-1
	  If foc=11 Then charStance(0,2)=charStance(0,2)-1
      If foc=12 Then charTaunt(0,1)=charTaunt(0,1)-1
      If foc=13 Then charTaunt(0,2)=charTaunt(0,2)-1
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=charAttack(0,count)+1
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=charCrush(0,count)+1
	  Next
      If foc=10 Then charStance(0,1)=charStance(0,1)+1
	  If foc=11 Then charStance(0,2)=charStance(0,2)+1
      If foc=12 Then charTaunt(0,1)=charTaunt(0,1)+1
      If foc=13 Then charTaunt(0,2)=charTaunt(0,2)+1
	 EndIf
	 ;randomize
	 If KeyDown(57) Or JoyDown(2)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=Rnd(1,no_attacks(count))
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=Rnd(1,no_crushes(count))
	  Next
      If foc=10 Then charStance(0,1)=1
	  If foc=11 Then charStance(0,2)=1
      If foc=12 Then charTaunt(0,1)=Rnd(1,no_taunts)
      If foc=13 Then charTaunt(0,2)=Rnd(1,no_taunts)
	 EndIf 
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=1
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=1
	  Next
      If foc=10 Then charStance(0,1)=1
	  If foc=11 Then charStance(0,2)=1
      If foc=12 Then charTaunt(0,1)=1
      If foc=13 Then charTaunt(0,2)=1
	 EndIf  
	 ;shortcuts
	 If KeyDown(2) Or KeyDown(3) Or KeyDown(4)
	  PlaySound sMenuGo : keytim=10
      If KeyDown(2) Then idol=128
      If KeyDown(3) Then idol=2
      If KeyDown(4) Then idol=55
      For count=1 To 5
       charAttack(0,count)=charAttack(idol,count)
       charCrush(0,count)=charCrush(idol,count)
      Next
     EndIf
	EndIf
	
	;************************** 6. MOVES **************************
	If page=6 And foc<99 And keytim=0 And blockMoves=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) 
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 15
	   If foc=count Then charMove(0,count)=charMove(0,count)-1
	  Next
	  For count=1 To 6
	   If foc=count+15 Then charGroundMove(0,count)=charGroundMove(0,count)-1
	  Next
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 15
	   If foc=count Then charMove(0,count)=charMove(0,count)+1
	  Next
	  For count=1 To 6
	   If foc=count+15 Then charGroundMove(0,count)=charGroundMove(0,count)+1
	  Next
	 EndIf
	 ;randomize
	 If KeyDown(57) Or JoyDown(2)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 12
	   If foc=count Then charMove(0,count)=Rnd(1,no_moves)
	  Next
	  If foc=13 Then charMove(0,13)=Rnd(3,no_moves)
	  If foc=14 Then charMove(0,14)=Rnd(1,no_cornmoves)
	  If foc=15 Then charMove(0,15)=Rnd(2,no_moves)
	  For count=1 To 3
	   If foc=count+15 Then charGroundMove(0,count)=Rnd(1,no_headmoves)
	  Next
	  For count=1 To 3
	   If foc=count+18 Then charGroundMove(0,count+3)=Rnd(1,no_legmoves)
	  Next
	 EndIf
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 13
	   If foc=count Then charMove(0,count)=3
	  Next
	  If foc=14 Then charMove(0,14)=1
	  If foc=15 Then charMove(0,15)=56
	  For count=1 To 6
	   If foc=count+15 Then charGroundMove(0,count)=1
	  Next
	 EndIf 
	 ;shortcuts
	 If KeyDown(2) Or KeyDown(3) Or KeyDown(4)
	  PlaySound sMenuGo : keytim=10
      If KeyDown(2) Then idol=128
      If KeyDown(3) Then idol=2
      If KeyDown(4) Then idol=55
      For count=1 To 15
       charMove(0,count)=charMove(idol,count)
      Next
      For count=1 To 6
       charGroundMove(0,count)=charGroundMove(idol,count)
      Next 
     EndIf 
	EndIf 
	
	;CHECK LIMITS
	CheckLimits(0)
	;initiation compulsaries
	If screenAgenda=3 
	 charPop(0)=50 : charAtt(0)=75 : charHap(0)=60
	 charFriend(0)=0 : charEnemy(0)=0 : charManager(0)=0
	EndIf
	;school limitations
	If fed=7 Then charTheme(0)=0 : charLight(0)=0
	;relationship references
	If charFriend(0)<0 Then charFriend(0)=fedSize(fed)
    If charFriend(0)>fedSize(fed) Then charFriend(0)=0
    If charEnemy(0)<0 Then charEnemy(0)=fedSize(fed)
    If charEnemy(0)>fedSize(fed) Then charEnemy(0)=0
    If charManager(0)<0 Then charManager(0)=fedSize(fed)
    If charManager(0)>fedSize(fed) Then charManager(0)=0
    ;appearance issues
	If page=>2 And page=<4 
     For count=1 To 3
      ;consistent model
      If ModelType(0,count)<>ModelType(0,1) Then charModel(0,count)=charModel(0,1) 
      ;consistent hairstyles
      charHairStyle(0,count)=charHairStyle(0,coz)
      charHair(0,count)=charHair(0,coz)
      For col=1 To 3
       charHairCol(0,col,count)=charHairCol(0,col,coz)
      Next
      ;consistent face
      charFace(0,count)=charFace(0,coz)
      For col=1 To 3
       charFaceCol(0,col,count)=charFaceCol(0,col,coz)
      Next
     Next
     ;make right arm follow left
	 If charLeftArm(0,coz)<>oldArm 
	  charRightArm(0,coz)=charLeftArm(0,coz)
	  For col=1 To 3
	   charRightArmCol(0,col,coz)=charLeftArmCol(0,col,coz) 
	  Next
	 EndIf 
    EndIf 
    ;calculate costume costs
    gamExpenses(0)=0
    If game=1 And screenAgenda<>3 And gamClause(slot,1)=<1
     gamExpenses(0)=CostumeChanges()
	EndIf
	 
	;MANAGE MUSIC
	If fed<>7
	 ;initiate preview
	 If page=1 And foc=>17 And foc=<18
	  If charTheme(0)<>oldTheme
	   charThemePitch(0)=DefaultPitch(charTheme(0))
       PlayTheme(0) : testing=1
      EndIf
      If charThemePitch(0)<>oldPitch 
       If testing=0 Then PlayTheme(0) : testing=1
       ChannelPitch chTheme,charThemePitch(0)
      EndIf
     Else
      ;restore main theme
      If testing=1 And charTheme(0)<>1 Then RestoreTheme() : testing=0
      ChannelPitch chTheme,44100
     EndIf
    EndIf
	 
	;UPDATE MODEL
	;costume
	ApplyCostume(0,0,coz)
	;demeanour
	If charEyes(0)>0 And tEyes(charEyes(0))>0 Then EntityTexture FindChild(p(0),"Head"),tEyes(charEyes(0)),0,3
	;orientation
	If foc=99 Then TurnEntity p(0),0,MouseXSpeed()+360,0 Else RotateEntity p(0),0,155+(MouseX()/(GraphicsWidth()/50)),0
	;scale
	scaler#=charHeight(0)*0.0025
	ScaleEntity p(0),0.35+scaler#,0.35+scaler#,0.35+scaler#
	;handle weapons
	For v=1 To weapList
     If v=16 Or v=17 Then EntityAlpha FindChild(p(0),weapFile$(v)),0.8
     HideEntity FindChild(p(0),weapFile$(v))
    Next
    For v=1 To weapList
     If charWeapon(0)=v And page=1 
      ShowEntity FindChild(p(0),weapFile$(v))
      If weapTex(v)>0
       EntityTexture FindChild(p(0),weapFile$(v)),weapTex(v)
      EndIf
      EntityShininess FindChild(p(0),weapFile$(v)),weapShiny(v)
     EndIf
    Next 
    ;adjust stances
    shower=charStance(0,1)
    If page=5 And foc=11 Then shower=charStance(0,2)+20
    If page=1 And weapBroad(charWeapon(0)) Then shower=99
    If shower<>oldStance
     If shower=>1 And shower=<20 Then Animate p(0),1,stanceSpeed#(charStance(0,1)),pSeq(0,shower),5
     If shower=>21 And shower=<40 Then Animate p(0),1,1.0,pSeq(0,shower),5
     If shower=99 Then Animate p(0),1,0.2,pSeq(0,99),5
    EndIf
    oldStance=shower
	
	;THUMBNAILS
	If page=>2 And page=<4
	 ;obtain texture
	 tex=tVoid : texR=255 : texG=255 : texB=255
	 If foc=3 Then tex=tMouth(0) : texR=charHatCol(0,1,coz) : texG=charHatCol(0,2,coz) : texB=charHatCol(0,3,coz)
     If foc=4 Then tex=tMouth(0) : texR=charSpecsCol(0,1,coz) : texG=charSpecsCol(0,2,coz) : texB=charSpecsCol(0,3,coz)
	 If foc=5 Then tex=tHair(charHair(0,coz)) : texR=charHairCol(0,1,coz) : texG=charHairCol(0,2,coz) : texB=charHairCol(0,3,coz)
	 If foc=6 Then tex=tFace(charFace(0,coz)) : texR=charFaceCol(0,1,coz) : texG=charFaceCol(0,2,coz) : texB=charFaceCol(0,3,coz)
     If foc=7 Then tex=tBody(charBody(0,coz)) : texR=charBodyCol(0,1,coz) : texG=charBodyCol(0,2,coz) : texB=charBodyCol(0,3,coz)
	 If foc=8 Then tex=tArms(charLeftArm(0,coz)) : texR=charLeftArmCol(0,1,coz) : texG=charLeftArmCol(0,2,coz) : texB=charLeftArmCol(0,3,coz)
	 If foc=9 Then tex=tArms(charRightArm(0,coz)) : texR=charRightArmCol(0,1,coz) : texG=charRightArmCol(0,2,coz) : texB=charRightArmCol(0,3,coz)
	 If foc=10 Then tex=tLegs(charLegs(0,coz)) : texR=charLegsCol(0,1,coz) : texG=charLegsCol(0,2,coz) : texB=charLegsCol(0,3,coz)
	 ;bind to thumbnail
	 ShowEntity pic
	 If tex>0 Then EntityTexture pic,tex,0,1
     EntityColor pic,texR,texG,texB
	Else
	 ;remove
	 HideEntity pic
	EndIf

	;CAMERA
	;PositionEntity light,-40+(MouseX()/10),10,-30 
	PointEntity light,p(0)
	If go=1 Then CameraClsColor cam,25,5,5 : CameraClsMode cam,1,1

 UpdateWorld
 Next
 If go<>1 Then TileImage gTile : DrawImage gFed(charFed(0)),rX#(400),rY#(55)
 RenderWorld 1

 ;DISPLAYS
 If go=0
  SetFont font(1)
  warning$="" : warnX=0 : warnY=0
  ;/////////////////// PROFILE OPTIONS ///////////////////
  If page=1
   ;left options
   x=140 : y=130
   DrawOption(1,x,y,charName$(0),"",0,0)
   DrawOption(2,x,y+45,"Age",charAge(0)+" yrs",1,2)
   DrawOption(3,x,y+80,"Height",GetHeight$(charHeight(0)),1,2) 
   DrawOption(4,x,y+120,"Popularity",charPop(0),1,2)
   DrawOption(5,x,y+152,"Strength",charStr(0),1,2)
   DrawOption(6,x,y+184,"Skill",charSkl(0),1,2)
   DrawOption(7,x,y+216,"Agility",charAgl(0),1,2)
   DrawOption(8,x,y+256,"Stamina",charStam(0),1,2)
   DrawOption(9,x,y+288,"Toughness",charTough(0),1,2)
   DrawOption(10,x,y+320,"Attitude",charAtt(0),1,2)
   DrawOption(11,x,y+352,"Happiness",charHap(0),1,2)
   ;right options
   x=660 : y=160
   DrawOption(12,x,y-30,"Allegiance",textHeel$(charHeel(0)),1,2)
   DrawOption(13,x,y,"Demeanour",textEyes$(charEyes(0)),1,2)
   If charFriend(0)>0 Then namer$=charName$(fedRoster(fed,charFriend(0))) Else namer$="None"
   DrawOption(14,x,y+40,"Friend",namer$,1,2)
   If charEnemy(0)>0 Then namer$=charName$(fedRoster(fed,charEnemy(0))) Else namer$="None"
   DrawOption(15,x,y+70,"Enemy",namer$,1,2)
   If charManager(0)>0 Then namer$=charName$(fedRoster(fed,charManager(0))) Else namer$="None"
   DrawOption(16,x,y+100,"Manager",namer$,1,2)
   If charTheme(0)>0 Then namer$="Theme"+Dig$(charTheme(0),10) Else namer$="None"
   DrawOption(17,x,y+140,"Theme Song",namer$,1,2)
   If charTheme(0)>0 Then namer$=charThemePitch(0)+" kHz" Else namer$="N/A"
   DrawOption(18,x,y+170,"Theme Pitch",namer$,1,2)
   DrawOption(19,x,y+200,"Lighting",textLight$(charLight(0)),1,2)
   DrawOption(20,x,y+230,"Prop",weapName$(charWeapon(0)),1,2)
   DrawOption(21,x,430,">>> COSTUMES >>>","",0,0)
   DrawOption(22,x,460,"<<< MOVES <<<","",0,0)
   DrawOption(23,x,500,"SAVE & EXIT","",0,0)
   ;relationship previews
   If foc=14 And charFriend(0)>0 Then DrawImage charPhoto(fedRoster(fed,charFriend(0))),rX#(x)-120,rY#(y+40)
   If foc=15 And charEnemy(0)>0 Then DrawImage charPhoto(fedRoster(fed,charEnemy(0))),rX#(x)-120,rY#(y+70)
   If foc=16 And charManager(0)>0 Then DrawImage charPhoto(fedRoster(fed,charManager(0))),rX#(x)-120,rY#(y+100)
   ;enter name
   If MouseDown(1) And foc=1 And blockImage=0 And keytim=0 And gotim>10
    keytim=10 : FlushKeys()
    x=rX#(140) : y=rY#(130)
    DrawImage gMenu(1),x,y : Flip
    Locate x-75,y-7 : Color 200,0,0
    oldTitle$=charName$(0)
    charName$(0)=Left$(Input$(""),30) 
    If charName$(0)="" Then charName$(0)=oldTitle$
   EndIf
   ;points warning
   If game=1 And screenAgenda=3 And foc=>5 And foc=<9
    If gamPoints=0 Then Outline("(No Points Remaining!)",rX#(140),rY#(515),30,0,0,Rnd(150,220),0,0)
    If gamPoints>0 Then Outline("("+gamPoints+" Points Remaining)",rX#(140),rY#(515),50,30,10,255,200,150)
   EndIf
   ;warnings & advice
   If warnY=0 And blockData=0
    If foc=14 And charFriend(0)>0 Then Outline("(Press TAB to reciprocate)",rX#(x),rY#(y+50),50,30,10,255,200,150)
    If foc=15 And charEnemy(0)>0 Then Outline("(Press TAB to reciprocate)",rX#(x),rY#(y+80),50,30,10,255,200,150)
    If foc=16 And charManager(0)>0 Then Outline("(Press TAB to reciprocate)",rX#(x),rY#(y+110),50,30,10,255,200,150)
   EndIf
   If warnX>0 And warnY>0 Then Outline(warning$,rX#(warnX),rY#(warnY),0,0,0,200,100,100)
  EndIf
  ;/////////////////// COSTUME OPTIONS ///////////////////
  If page=>2 And page=<4
   coz=page-1
   ;color display
   x=rX#(658) : y=rY#(275)
   colR$="???" : colG$="???" : colB$="???"
   If foc=3 Then colR$=charHatCol(0,1,coz) : colG$=charHatCol(0,2,coz) : colB$=charHatCol(0,3,coz)
   If foc=4 Then colR$=charSpecsCol(0,1,coz) : colG$=charSpecsCol(0,2,coz) : colB$=charSpecsCol(0,3,coz)
   If foc=5 Then colR$=charHairCol(0,1,coz) : colG$=charHairCol(0,2,coz) : colB$=charHairCol(0,3,coz)
   If foc=6 Then colR$=charFaceCol(0,1,coz) : colG$=charFaceCol(0,2,coz) : colB$=charFaceCol(0,3,coz)
   If foc=7 Then colR$=charBodyCol(0,1,coz) : colG$=charBodyCol(0,2,coz) : colB$=charBodyCol(0,3,coz)
   If foc=8 Then colR$=charLeftArmCol(0,1,coz) : colG$=charLeftArmCol(0,2,coz) : colB$=charLeftArmCol(0,3,coz)
   If foc=9 Then colR$=charRightArmCol(0,1,coz) : colG$=charRightArmCol(0,2,coz) : colB$=charRightArmCol(0,3,coz)
   If foc=10 Then colR$=charLegsCol(0,1,coz) : colG$=charLegsCol(0,2,coz) : colB$=charLegsCol(0,3,coz)
   DrawImage gColour,x+1,y+1
   Outline(colR$,x-40,y,50,0,0,200,0,0)
   Outline(colG$,x,y,0,50,0,0,200,0)
   Outline(colB$,x+40,y,0,0,50,0,0,200)
   ;left options
   x=140 : y=190
   If game=1 And screenAgenda<>3 Then y=180
   DrawImage gRole(coz),rX#(x),rY#(y-40)-40
   If page=2 Then namer$="Wrestling Costume"
   If page=3 Then namer$="Casual Costume"
   If page=4 Then namer$="Referee Costume"
   DrawOption(-1,x,y-40,namer$,"",0,0)
   DrawOption(1,x,y,"Build",textBuild$(charModel(0,coz)),1,2)
   DrawOption(2,x,y+40,"Hairstyle",textHair$(charHairStyle(0,coz)),1,2)
   DrawOption(3,x,y+70,"Headwear",textHat$(charHat(0,coz)),1,2)
   DrawOption(4,x,y+100,"Eyewear",textSpecs$(charSpecs(0,coz)),1,2)
   If foc=1 And warnY=0 Then Outline("(Press ENTER to apply)",rX#(x),rY#(y)+10,50,30,10,255,200,150)
   DrawOption(5,x,y+140,"Hair",charHair(0,coz),1,2)
   DrawOption(6,x,y+170,"Face",charFace(0,coz),1,2)
   DrawOption(7,x,y+200,"Body",charBody(0,coz),1,2)
   DrawOption(8,x,y+230,"Left Arm",charLeftArm(0,coz),1,2)
   DrawOption(9,x,y+260,"Right Arm",charRightArm(0,coz),1,2)
   DrawOption(10,x,y+290,"Legs",charLegs(0,coz),1,2)
   If game=1 And screenAgenda<>3 Then DrawOption(11,x,y+330,"PLASTIC SURGERY","",0,0)
   ;right options
   x=660 : y=325
   DrawOption(pageList(page)-5,x,y,"COPY WRESTLING","",0,0) 
   DrawOption(pageList(page)-4,x,y+30,"COPY CASUAL","",0,0) 
   DrawOption(pageList(page)-3,x,y+60,"COPY REFEREE","",0,0) 
   If page=2 Then namerA$=">>> CASUAL >>>" : namerB$="<<< PROFILE <<<"
   If page=3 Then namerA$=">>> REFEREE >>>" : namerB$="<<< WRESTLING <<<"
   If page=4 Then namerA$=">>> ATTACKS >>>" : namerB$="<<< CASUAL <<<"
   DrawOption(pageList(page)-2,x,430,namerA$,"",0,0)
   DrawOption(pageList(page)-1,x,460,namerB$,"",0,0)
   DrawOption(pageList(page),x,500,"SAVE & EXIT","",0,0)
   ;warnings & advice
   If warnX>0 And warnY>0 Then Outline(warning$,rX#(warnX),rY#(warnY),0,0,0,200,100,100)
  EndIf
  ;/////////////////// ATTACK OPTIONS ///////////////////
  If page=5
   ;left options
   x=140 : y=190
   DrawOption(1,x,y,"Upper Attack",attackName$(1,charAttack(0,1)),1,2)
   DrawOption(2,x,y+30,"Lower Attack",attackName$(2,charAttack(0,2)),1,2)
   DrawOption(3,x,y+60,"Big Attack",attackName$(3,charAttack(0,3)),1,2)
   DrawOption(4,x,y+90,"Running Attack",attackName$(4,charAttack(0,4)),1,2)
   DrawOption(5,x,y+120,"Flying Attack",attackName$(5,charAttack(0,5)),1,2)
   DrawOption(6,x,y+160,"Stomp Attack",crushName$(2,charCrush(0,2)),1,2)
   DrawOption(7,x,y+190,"Crush Attack",crushName$(3,charCrush(0,3)),1,2)
   DrawOption(8,x,y+220,"Running Crush",crushName$(4,charCrush(0,4)),1,2)
   DrawOption(9,x,y+250,"Flying Crush",crushName$(5,charCrush(0,5)),1,2)
   ;right options
   x=660 : y=190
   DrawOption(10,x,y,"Stance",stanceName$(charStance(0,1)),1,2)
   DrawOption(11,x,y+30,"Movement",walkName$(charStance(0,2)),1,2)
   DrawOption(12,x,y+70,"Taunt",tauntName$(charTaunt(0,1)),1,2)
   DrawOption(13,x,y+100,"Special Taunt",tauntName$(charTaunt(0,2)),1,2)
   DrawOption(14,x,430,">>> MOVES >>>","",0,0)
   DrawOption(15,x,460,"<<< COSTUMES <<<","",0,0)
   DrawOption(16,x,500,"SAVE & EXIT","",0,0)
   ;warnings
   If warnX>0 And warnY>0 Then Outline(warning$,rX#(warnX),rY#(warnY),0,0,0,200,100,100)
  EndIf
  ;/////////////////// MOVE OPTIONS ///////////////////
  If page=6
   ;left options
   x=140 : y=130
   DrawOption(1,x,y,"Move A Centre",movName$(charMove(0,1)),1,2)
   DrawOption(2,x,y+30,"Move A Up",movName$(charMove(0,2)),1,2)
   DrawOption(3,x,y+60,"Move A Down",movName$(charMove(0,3)),1,2)
   DrawOption(4,x,y+90,"Move A Side",movName$(charMove(0,4)),1,2)
   DrawOption(5,x,y+130,"Move B Centre",movName$(charMove(0,5)),1,2)
   DrawOption(6,x,y+160,"Move B Up",movName$(charMove(0,6)),1,2)
   DrawOption(7,x,y+190,"Move B Down",movName$(charMove(0,7)),1,2)
   DrawOption(8,x,y+220,"Move B Side",movName$(charMove(0,8)),1,2) 
   DrawOption(9,x,y+260,"Move C Centre",movName$(charMove(0,9)),1,2)
   DrawOption(10,x,y+290,"Move C Up",movName$(charMove(0,10)),1,2)
   DrawOption(11,x,y+320,"Move C Down",movName$(charMove(0,11)),1,2)
   DrawOption(12,x,y+350,"Move C Side",movName$(charMove(0,12)),1,2)
   ;right options
   x=660 : y=130
   DrawOption(13,x,y,"Momentum Move",movName$(charMove(0,13)),1,2)
   DrawOption(14,x,y+30,"Turnbuckle Move",cornName$(charMove(0,14)),1,2)
   DrawOption(15,x,y+60,"Special Move",movName$(charMove(0,15)),1,2)
   DrawOption(16,x,y+100,"Head Move A",headName$(charGroundMove(0,1)),1,2)
   DrawOption(17,x,y+130,"Head Move B",headName$(charGroundMove(0,2)),1,2)
   DrawOption(18,x,y+160,"Head Move C",headName$(charGroundMove(0,3)),1,2)
   DrawOption(19,x,y+200,"Leg Move A",legName$(charGroundMove(0,4)),1,2)
   DrawOption(20,x,y+230,"Leg Move B",legName$(charGroundMove(0,5)),1,2)
   DrawOption(21,x,y+260,"Leg Move C",legName$(charGroundMove(0,6)),1,2)
   DrawOption(22,x,430,">>> PROFILE >>>","",0,0)
   DrawOption(23,x,460,"<<< ATTACKS <<<","",0,0)
   DrawOption(24,x,500,"SAVE & EXIT","",0,0)
   ;warnings
   If warnX>0 And warnY>0 Then Outline(warning$,rX#(warnX),rY#(warnY),0,0,0,200,100,100)
  EndIf
  ;/////////////////// OTHERS ///////////////////
  ;costume expenses
  expenses=gamExpenses(slot)+gamExpenses(0)+weapCost(charWeapon(0))
  If game=1 And expenses>0
   x=rX#(660) : y=rY#(65)
   Color 190,150,40 : Line x-50,y+13,x+50,y+13
   Color 160,120,10 : Line x-54,y+15,x+54,y+15
   SetFont font(1)
   Outline("Gimmick Changes:",x,y-15,0,0,0,200,170,80)
   SetFont font(3)
   Outline("$"+GetFigure$(expenses),x,y,0,0,0,250,250,130)
  EndIf
  ;inst
  SetFont font(1)
  Outline("UP/DOWN=Highlight option, LEFT/RIGHT=Change Values, SPACE=Randomize, BACKSPACE=Revert, ESC=Cancel",rX#(400),rY#(580),0,0,0,85,85,85)
  If foc<>99 And MouseX()>rX#(300) And MouseX()<rX#(500) And MouseY()<rY#(540)
   Outline("(CLICK TO EXAMINE)",rX#(400),rY#(555),50,30,10,255,200,150)
  EndIf
  ;loading calls
  If screenCall=1
   QuickLoader(400,300,"Please Wait","Reconstructing Character")
   FreeEntity p(0)
   InitModel(page-1)
  EndIf
  ;cursor
  If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
  DrawImage gCursor,MouseX()+10,MouseY()+9
 EndIf ;taking photo?

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
If go=>1 Then PlaySound sMenuGo Else PlaySound sMenuBack
;save changes
If go=>1
 QuickLoader(400,300,"Please Wait","Saving Character")
 ;take photo
 If go=1
  If GraphicsWidth()=1280 And GraphicsHeight()=800 Then width#=393 Else width#=rX#(300)
  photo=CreateImage(width#,rY#(550))
  GrabImage photo,rX#(400),rY#(280)
  MaskImage photo,25,5,5
  ;register portrait
  ResizeImage photo,70,128
  SaveImage(photo,"Data/Portraits/Photo"+Dig$(saver,100)+".bmp")
  charPhoto(saver)=LoadImage("Data/Portraits/Photo"+Dig$(saver,100)+".bmp") 
  MaskImage charPhoto(saver),25,5,5
 EndIf
 ;confirm data
 charFriend(0)=fedRoster(fed,charFriend(0))
 charEnemy(0)=fedRoster(fed,charEnemy(0))
 charManager(0)=fedRoster(fed,charManager(0))
 CopyChar(0,saver)
 charFed(saver)=fed 
 If screenAgenda=3 Then BeginCareer(saver)
 ;increment roster
 If saver=no_chars+1
  no_chars=no_chars+1
  If no_chars>optCharLim Then no_chars=optCharLim
  fedSize(fed)=fedSize(fed)+1
  fedRoster(fed,fedSize(fed))=saver
  SaveWorld()
 EndIf
 ;update files
 If go=1 
  If gamExpenses(0)>0 And screenAgenda<>3 Then gamExpenses(slot)=gamExpenses(slot)+gamExpenses(0)
  SaveChars()
 EndIf
EndIf
;free entities
FreeEntity p(0)
FreeEntity light
FreeEntity cam
FreeEntity pic
;proceed
screen=13
If game=1 Then screen=15
If screenAgenda=3 And go=-1 Then screen=21
If go=2 Then screen=55
End Function 

;INITIALISE CREATION MODEL
Function InitModel(costume)
 ;sequences
 p(0)=LoadAnimMesh("Models/Bodies/"+textModel$(charModel(0,costume))+".3ds")
 pSeq(0,600)=LoadAnimSeq(p(0),"Models/Sequences/Gestures.3ds")
 pSeq(0,601)=LoadAnimSeq(p(0),"Models/Sequences/Movement.3ds")
 pSeq(0,603)=LoadAnimSeq(p(0),"Models/Sequences/Weapons.3ds")
 ;stances
 pSeq(0,1)=ExtractAnimSeq(p(0),3840,3880,pSeq(0,601)) ;powerful
 pSeq(0,2)=ExtractAnimSeq(p(0),0,30,pSeq(0,600)) ;straight
 pSeq(0,3)=ExtractAnimSeq(p(0),260,290,pSeq(0,601)) ;normal
 pSeq(0,4)=ExtractAnimSeq(p(0),3890,3930,pSeq(0,601)) ;pensive
 pSeq(0,5)=ExtractAnimSeq(p(0),530,560,pSeq(0,600)) ;boxing
 pSeq(0,6)=ExtractAnimSeq(p(0),710,740,pSeq(0,600)) ;martial arts
 pSeq(0,7)=ExtractAnimSeq(p(0),3790,3830,pSeq(0,601)) ;grappler
 pSeq(0,8)=ExtractAnimSeq(p(0),4010,4050,pSeq(0,601)) ;relaxed
 pSeq(0,9)=ExtractAnimSeq(p(0),4060,4100,pSeq(0,601)) ;feminine
 ;movements
 pSeq(0,21)=ExtractAnimSeq(p(0),110,170,pSeq(0,601)) ;stroll
 pSeq(0,22)=ExtractAnimSeq(p(0),300,360,pSeq(0,601)) ;normal
 pSeq(0,23)=ExtractAnimSeq(p(0),3940,4000,pSeq(0,601)) ;pensive 
 pSeq(0,24)=ExtractAnimSeq(p(0),3640,3710,pSeq(0,601)) ;boxing
 pSeq(0,25)=ExtractAnimSeq(p(0),3720,3780,pSeq(0,601)) ;martial arts
 pSeq(0,26)=ExtractAnimSeq(p(0),4110,4170,pSeq(0,601)) ;feminine
 ;weapon stance
 pSeq(cyc,99)=ExtractAnimSeq(p(0),370,400,pSeq(cyc,603)) ;weapon stance
 ;initiate
 Animate p(0),1,stanceSpeed#(charStance(0,1)),pSeq(0,charStance(0,1)),0
 PositionEntity p(0),0,-1,0
End Function

;MANIPULATE COLOUR DATA
Function ManipulateColour(sourceR,sourceG,sourceB)
 ;source by default
 manipR=sourceR : manipG=sourceG : manipB=sourceB
 ;adjust red
 If KeyDown(16) Or KeyDown(19) Then manipR=manipR+10 : keytim=2
 If KeyDown(30) Or KeyDown(33) Then manipR=manipR-10 : keytim=2
 ;adjust green
 If KeyDown(17) Or KeyDown(19) Then manipG=manipG+10 : keytim=2
 If KeyDown(31) Or KeyDown(33) Then manipG=manipG-10 : keytim=2
 ;adjust blue
 If KeyDown(18) Or KeyDown(19) Then manipB=manipB+10 : keytim=2
 If KeyDown(32) Or KeyDown(33) Then manipB=manipB-10 : keytim=2
 ;revert
 If KeyDown(14) 
  PlaySound sMenuBrowse : keytim=10
  manipR=255 : manipG=255 : manipB=255 
 EndIf
End Function

;CLONE COSTUME
Function CloneCostume(char,source,target)
 ;textures
 charModel(char,target)=charModel(char,source)
 charHairStyle(char,target)=charHairStyle(char,source)
 charHat(char,target)=charHat(char,source)
 charSpecs(char,target)=charSpecs(char,source)
 charHair(char,target)=charHair(char,source)
 charFace(char,target)=charFace(char,source)
 charBody(char,target)=charBody(char,source)
 charLeftArm(char,target)=charLeftArm(char,source)
 charRightArm(char,target)=charRightArm(char,source)
 charLegs(char,target)=charLegs(char,source)
 ;colours
 For col=1 To 3
  charHatCol(char,col,target)=charHatCol(char,col,source)
  charSpecsCol(char,col,target)=charSpecsCol(char,col,source)
  charHairCol(char,col,target)=charHairCol(char,col,source)
  charFaceCol(char,col,target)=charFaceCol(char,col,source)
  charBodyCol(char,col,target)=charBodyCol(char,col,source)
  charLeftArmCol(char,col,target)=charLeftArmCol(char,col,source)
  charRightArmCol(char,col,target)=charRightArmCol(char,col,source)
  charLegsCol(char,col,target)=charLegsCol(char,col,source)
 Next
End Function

;CALCULATE COSTUME CHANGES
Function CostumeChanges()
 value=0
 ;gimmicks
 If charTheme(0)>0 And charTheme(0)<>charTheme(gamChar(slot))
  If DefaultPitch(charTheme(0))=8000 Then value=value+250 
  If DefaultPitch(charTheme(0))>8000 Then value=value+500
 EndIf
 If charLight(0)>0 And charLight(0)<>charLight(gamChar(slot)) Then value=value+250 
 ;costumes
 If charHairStyle(0,1)<>charHairStyle(gamChar(slot),1) Then value=value+100
 For count=1 To 3
  If charHat(0,count)>0 And charHat(0,count)<>charHat(gamChar(slot),count) Then value=value+50
  If charSpecs(0,count)>0 And charSpecs(0,count)<>charSpecs(gamChar(slot),count) Then value=value+25
  If charHair(0,count)<>charHair(gamChar(slot),count) Then value=value+50
  If charBody(0,count)<>charBody(gamChar(slot),count) Then value=value+50
  If charLeftArm(0,count)<>charLeftArm(gamChar(slot),count) Then value=value+25
  If charRightArm(0,count)<>charRightArm(gamChar(slot),count) Then value=value+25
  If charLegs(0,count)<>charLegs(gamChar(slot),count) Then value=value+50
 Next
 Return value
End Function

;IDENTIFY LIMBS
Function GetLimbs(cyc)
 ;reset entries
 For count=1 To 40
  pLimb(cyc,count)=0 
 Next
 ;upper body
 pLimb(cyc,1)=FindChild(p(cyc),"Head")
 pLimb(cyc,2)=FindChild(p(cyc),"Throat")
 pLimb(cyc,3)=FindChild(p(cyc),"Body")
 ;left arm
 pLimb(cyc,4)=FindChild(p(cyc),"L_Bicep")
 pLimb(cyc,5)=FindChild(p(cyc),"L_Arm")
 pLimb(cyc,6)=FindChild(p(cyc),"L_Palm")
 pLimb(cyc,7)=FindChild(p(cyc),"L_Thumb01")
 pLimb(cyc,8)=FindChild(p(cyc),"L_Thumb02")
 For count=1 To 8
  pLimb(cyc,8+count)=FindChild(p(cyc),"L_Finger0"+count)
 Next
 ;right arm
 pLimb(cyc,17)=FindChild(p(cyc),"R_Bicep")
 pLimb(cyc,18)=FindChild(p(cyc),"R_Arm")
 pLimb(cyc,19)=FindChild(p(cyc),"R_Palm")
 pLimb(cyc,20)=FindChild(p(cyc),"R_Thumb01")
 pLimb(cyc,21)=FindChild(p(cyc),"R_Thumb02")
 For count=1 To 8
  pLimb(cyc,21+count)=FindChild(p(cyc),"R_Finger0"+count)
 Next
 ;lower body
 pLimb(cyc,30)=FindChild(p(cyc),"Hips")
 pLimb(cyc,31)=FindChild(p(cyc),"L_Thigh")
 pLimb(cyc,32)=FindChild(p(cyc),"L_Leg")
 pLimb(cyc,33)=FindChild(p(cyc),"L_Foot")
 pLimb(cyc,34)=FindChild(p(cyc),"R_Thigh") 
 pLimb(cyc,35)=FindChild(p(cyc),"R_Leg")
 pLimb(cyc,36)=FindChild(p(cyc),"R_Foot")
 ;additional
 pLimb(cyc,37)=FindChild(p(cyc),"Ears")
End Function

;APPLY ALL COSTUME CHOICES
Function ApplyCostume(cyc,char,coz)
 ;construct model
 GetLimbs(cyc)
 ApplyClothing(cyc,char,coz)
 ApplyHairstyles(cyc,char,coz)
 ApplyHeadwear(cyc,char,coz)
 ApplyEyewear(cyc,char,coz)
 ;remove limbs!
 For limb=1 To 40
  If charLimb(char,limb)=0 And pLimb(cyc,limb)>0
   HideEntity pLimb(cyc,limb) 
  EndIf
 Next
End Function

;APPLY CLOTHING
Function ApplyClothing(cyc,char,coz)
 ;head
 For limb=1 To 2
  If tFace(charFace(char,coz))>0 Then EntityTexture pLimb(cyc,limb),tFace(charFace(char,coz)),0,1
  EntityColor pLimb(cyc,limb),charFaceCol(char,1,coz),charFaceCol(char,2,coz),charFaceCol(char,3,coz)
 Next
 ;ears
 If pLimb(cyc,37)>0
  If tFace(charFace(char,coz))>0 Then EntityTexture pLimb(cyc,37),tFace(charFace(char,coz)),0,1
  EntityColor pLimb(cyc,37),charFaceCol(char,1,coz),charFaceCol(char,2,coz),charFaceCol(char,3,coz)
  If charHat(char,coz)=12 Then HideEntity pLimb(cyc,37) Else ShowEntity pLimb(cyc,37) 
 EndIf
 ;body
 limb=3
 If tBody(charBody(char,coz))>0 Then EntityTexture pLimb(cyc,limb),tBody(charBody(char,coz)),0,1 
 EntityColor pLimb(cyc,limb),charBodyCol(char,1,coz),charBodyCol(char,2,coz),charBodyCol(char,3,coz)
 ;left arm
 For limb=4 To 16
  If tArms(charLeftArm(char,coz))>0 Then EntityTexture pLimb(cyc,limb),tArms(charLeftArm(char,coz)),0,1
  EntityColor pLimb(cyc,limb),charLeftArmCol(char,1,coz),charLeftArmCol(char,2,coz),charLeftArmCol(char,3,coz)
  If limb=>5 ;t-shirt filter
   If charLeftArm(char,coz)=68 Or charLeftArm(char,coz)=69 Then EntityColor pLimb(cyc,limb),255,255,255
  EndIf
  If limb=>6 ;long sleeve filter
   If charLeftArm(char,coz)=27 Or charLeftArm(char,coz)=29 Or charLeftArm(char,coz)=30 Or charLeftArm(char,coz)=32 Or charLeftArm(char,coz)=34 Or charLeftArm(char,coz)=37 Or charLeftArm(char,coz)=39 Or (charLeftArm(char,coz)=>44 And charLeftArm(char,coz)=<48) Or charLeftArm(char,coz)=64 Or charLeftArm(char,coz)=66
    EntityColor pLimb(cyc,limb),255,255,255
   EndIf
  EndIf
  If limb=4 And charLeftArm(char,coz)=70 Then EntityColor pLimb(cyc,limb),255,255,255 ;long glove filter
  If limb=<5
   If charLeftArm(char,coz)=71 Or charLeftArm(char,coz)=72 Then EntityColor pLimb(cyc,limb),255,255,255 ;short glove filter
  EndIf
 Next
 ;right arm
 For limb=17 To 29
  If tArms(charRightArm(char,coz))>0 Then EntityTexture pLimb(cyc,limb),tArms(charRightArm(char,coz)),0,1
  EntityColor pLimb(cyc,limb),charRightArmCol(char,1,coz),charRightArmCol(char,2,coz),charRightArmCol(char,3,coz)
  If limb=>18 ;t-shirt filter
   If charRightArm(char,coz)=68 Or charRightArm(char,coz)=69 Then EntityColor pLimb(cyc,limb),255,255,255
  EndIf
  If limb=>19 ;long sleeve filter
   If charRightArm(char,coz)=27 Or charRightArm(char,coz)=29 Or charRightArm(char,coz)=30 Or charRightArm(char,coz)=32 Or charRightArm(char,coz)=34 Or charRightArm(char,coz)=37 Or charRightArm(char,coz)=39 Or (charRightArm(char,coz)=>44 And charRightArm(char,coz)=<48) Or charRightArm(char,coz)=64 Or charRightArm(char,coz)=66
    EntityColor pLimb(cyc,limb),255,255,255
   EndIf
  EndIf 
  If limb=17 And charRightArm(char,coz)=70 Then EntityColor pLimb(cyc,limb),255,255,255 ;glove filter
  If limb=<18 
   If charRightArm(char,coz)=71 Or charRightArm(char,coz)=72 Then EntityColor pLimb(cyc,limb),255,255,255 ;short glove filter
  EndIf
 Next
 ;legs
 For limb=30 To 36
  If tLegs(charLegs(char,coz))>0 Then EntityTexture pLimb(cyc,limb),tLegs(charLegs(char,coz)),0,1
  EntityColor pLimb(cyc,limb),charLegsCol(char,1,coz),charLegsCol(char,2,coz),charLegsCol(char,3,coz)
  If limb>30 ;trunk filter
   If charLegs(char,coz)=4 Or charLegs(char,coz)=7 Or charLegs(char,coz)=9 Then EntityColor pLimb(cyc,limb),255,255,255
  EndIf
  If limb=33 Or limb=36 ;length filter
   If (charLegs(char,coz)=>48 And charLegs(char,coz)=<49) Or charLegs(char,coz)=51 Or (charLegs(char,coz)=>53 And charLegs(char,coz)=<54) Or (charLegs(char,coz)=>56 And charLegs(char,coz)=<63) Or (charLegs(char,coz)=>84 And charLegs(char,coz)=<85)
    EntityColor pLimb(cyc,limb),255,255,255
   EndIf
  EndIf
  If limb<>33 And limb<>36 ;shoe filter
   If charLegs(char,coz)=52 Or charLegs(char,coz)=55 Then EntityColor pLimb(cyc,limb),255,255,255
  EndIf
 Next
End Function

;REMOVE ALL HAIR
Function RemoveHair(cyc)
 For limb=1 To 15
  EntityAlpha FindChild(p(cyc),hairFile$(limb)),0
 Next
End Function

;APPLY HAIRSTYLES
Function ApplyHairstyles(cyc,char,coz)
 ;hide all by default
 RemoveHair(cyc)
 ;determine style
 If charHairStyle(char,coz)=2 Then hairerA$="Hair_Bald" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=3 Then hairerA$="Hair_Thin" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=4 Then hairerA$="Hair_Short" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=5 Then hairerA$="Hair_Raise" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=6 Then hairerA$="Hair_Quiff" : hairerB$="" : showA=1 : showB=0 
 If charHairStyle(char,coz)=7 Then hairerA$="Hair_Mop" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=8 Then hairerA$="Hair_Thick" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=9 Then hairerA$="Hair_Full" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=10 Then hairerA$="Hair_Curl" : hairerB$="" : showA=1 : showB=0 
 If charHairStyle(char,coz)=11 Then hairerA$="Hair_Afro" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=12 Then hairerA$="Hair_Spike" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=13 Then hairerA$="Hair_Punk" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=14 Then hairerA$="Hair_Rolls" : hairerB$="" : showA=1 : showB=0
 If charHairStyle(char,coz)=15 Then hairerA$="Hair_Bald" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=16 Then hairerA$="Hair_Thin" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=17 Then hairerA$="Hair_Short" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=18 Then hairerA$="Hair_Raise" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=19 Then hairerA$="Hair_Quiff" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=20 Then hairerA$="Hair_Mop" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=21 Then hairerA$="Hair_Thick" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=22 Then hairerA$="Hair_Curl" : hairerB$="Hair_Pony" : showA=1 : showB=1 
 If charHairStyle(char,coz)=23 Then hairerA$="Hair_Punk" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=24 Then hairerA$="Hair_Rolls" : hairerB$="Hair_Pony" : showA=1 : showB=1
 If charHairStyle(char,coz)=25 Then hairerA$="Hair_Bald" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=26 Then hairerA$="Hair_Thin" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=27 Then hairerA$="Hair_Short" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=28 Then hairerA$="Hair_Raise" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=29 Then hairerA$="Hair_Quiff" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=30 Then hairerA$="Hair_Mop" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=31 Then hairerA$="Hair_Thick" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=32 Then hairerA$="Hair_Curl" : hairerB$="Hair_Long" : showA=1 : showB=1
 If charHairStyle(char,coz)=33 Then hairerA$="Hair_Punk" : hairerB$="Hair_Long" : showA=1 : showB=1
 ;headwear conflicts
 If charHat(char,coz)=>2 And charHat(char,coz)=<11
  h=charHairStyle(char,coz) 
  If (h=>5 And h=<13) Or (h=>18 And h=<23) Or (h=>28 And h=<32) Then hairerA$="Hair_Short"
 EndIf
 If charHat(char,coz)=12 Then showA=0 : showB=0
 ;compose hair
 If charHairStyle(char,coz)>1
  If showA=1
   EntityAlpha FindChild(p(cyc),hairerA$),1
   EntityColor FindChild(p(cyc),hairerA$),charHairCol(char,1,coz),charHairCol(char,2,coz),charHairCol(char,3,coz)
   If tHair(charHair(char,coz))>0 Then EntityTexture FindChild(p(cyc),hairerA$),tHair(charHair(char,coz)),0,1
  EndIf
  If showB=1
   EntityAlpha FindChild(p(cyc),hairerB$),1 
   EntityColor FindChild(p(cyc),hairerB$),charHairCol(char,1,coz),charHairCol(char,2,coz),charHairCol(char,3,coz)
   If tHair(charHair(char,coz))>0 Then EntityTexture FindChild(p(cyc),hairerB$),tHair(charHair(char,coz)),0,1
  EndIf
  ;offset thick hair
  PositionEntity FindChild(p(cyc),"Hair_Thick"),0.2,7.5,0.5
  PositionEntity FindChild(p(cyc),"Hair_Full"),0.2,7.5,0.5 
 EndIf
 ;shaved head
 If charHairStyle(char,coz)<>1 And screen=51 And tMouth(0)>0 Then EntityTexture FindChild(p(cyc),"Head"),tMouth(0),0,2
 If charHairStyle(char,coz)=1 And tShaved>0 Then EntityTexture FindChild(p(cyc),"Head"),tShaved,0,2
End Function

;APPLY HEADWEAR
Function ApplyHeadwear(cyc,char,coz)
 ;hide by default
 HideEntity FindChild(p(cyc),"BandA")
 HideEntity FindChild(p(cyc),"BandB")
 HideEntity FindChild(p(cyc),"CapA")
 HideEntity FindChild(p(cyc),"Hat")
 HideEntity FindChild(p(cyc),"Helmet")
 HideEntity FindChild(p(cyc),"Horns")
 ;headband
 If charHat(char,coz)=1
  ShowEntity FindChild(p(cyc),"BandA")
  If charHairStyle(char,coz)=<1 Or charHairStyle(char,coz)=13 Or charHairStyle(char,coz)=14 Or charHairStyle(char,coz)=23 Or charHairStyle(char,coz)=24 Or charHairStyle(char,coz)=33 
   ShowEntity FindChild(p(cyc),"BandB")
  EndIf
  EntityColor FindChild(p(cyc),"BandA"),charHatCol(char,1,coz),charHatCol(char,2,coz),charHatCol(char,3,coz)
  EntityColor FindChild(p(cyc),"BandB"),charHatCol(char,1,coz),charHatCol(char,2,coz),charHatCol(char,3,coz)
 EndIf
 ;construct caps
 If charHat(char,coz)=>2 And charHat(char,coz)=<7
  ShowEntity FindChild(p(cyc),"CapA")
  If charHat(char,coz)=>2 And charHat(char,coz)=<4 And tCapBlack>0 Then EntityTexture FindChild(p(cyc),"CapA"),tCapBlack ;black 
  If charHat(char,coz)=>5 And charHat(char,coz)=<7 And tCapWhite>0 Then EntityTexture FindChild(p(cyc),"CapA"),tCapWhite ;white
  EntityColor FindChild(p(cyc),"CapA"),charHatCol(char,1,coz),charHatCol(char,2,coz),charHatCol(char,3,coz)
  If charHat(char,coz)=2 Or charHat(char,coz)=5 ;normal positions
   ScaleEntity FindChild(p(cyc),"CapA"),1.1,1.1,1.1
   RotateEntity FindChild(p(cyc),"CapA"),0,0,0
   PositionEntity FindChild(p(cyc),"CapA"),0,9.25,0.8
  EndIf
  If charHat(char,coz)=3 Or charHat(char,coz)=6 ;dipped positions
   ScaleEntity FindChild(p(cyc),"CapA"),1.1,1.1,1.1
   RotateEntity FindChild(p(cyc),"CapA"),20,-20,0
   PositionEntity FindChild(p(cyc),"CapA"),0.25,10,1.25
  EndIf
  If charHat(char,coz)=4 Or charHat(char,coz)=7 ;reversed positions
   ScaleEntity FindChild(p(cyc),"CapA"),1.115,1.115,1.2
   RotateEntity FindChild(p(cyc),"CapA"),15,180,0
   PositionEntity FindChild(p(cyc),"CapA"),0,8.75,0.75
  EndIf
 EndIf
 ;construct hats
 If charHat(char,coz)=>8 And charHat(char,coz)=<11
  ShowEntity FindChild(p(cyc),"Hat")
  If charHat(char,coz)=8 Or charHat(char,coz)=9 And tHatBlack>0 Then EntityTexture FindChild(p(cyc),"Hat"),tHatBlack ;black 
  If charHat(char,coz)=10 Or charHat(char,coz)=11 And tHatWhite>0 Then EntityTexture FindChild(p(cyc),"Hat"),tHatWhite ;white
  EntityColor FindChild(p(cyc),"Hat"),charHatCol(char,1,coz),charHatCol(char,2,coz),charHatCol(char,3,coz)
  If charHat(char,coz)=8 Or charHat(char,coz)=10 ;normal positions
   RotateEntity FindChild(p(cyc),"Hat"),5,0,0
   PositionEntity FindChild(p(cyc),"Hat"),0,9.5,0.9
  EndIf
  If charHat(char,coz)=9 Or charHat(char,coz)=11 ;dipped positions
   RotateEntity FindChild(p(cyc),"Hat"),25,0,-10
   PositionEntity FindChild(p(cyc),"Hat"),0,9.5,1.5
  EndIf
 EndIf
 ;helmet
 If charHat(char,coz)=12
  ShowEntity FindChild(p(cyc),"Helmet")
  EntityColor FindChild(p(cyc),"Helmet"),charHatCol(char,1,coz),charHatCol(char,2,coz),charHatCol(char,3,coz)
 EndIf
 ;horns
 If charHat(char,coz)=13
  ShowEntity FindChild(p(cyc),"Horns")
  EntityColor FindChild(p(cyc),"Horns"),charHatCol(char,1,coz),charHatCol(char,2,coz),charHatCol(char,3,coz)
 EndIf
End Function

;APPLY EYEWEAR
Function ApplyEyewear(cyc,char,coz)
 ;hide by default
 HideEntity FindChild(p(cyc),"Specs")
 HideEntity FindChild(p(cyc),"LensA")
 HideEntity FindChild(p(cyc),"LensB")
 If charSpecs(char,coz)>0
  ;compose specs
  ShowEntity FindChild(p(cyc),"Specs")
  ShowEntity FindChild(p(cyc),"LensA")
  ShowEntity FindChild(p(cyc),"LensB")
  EntityShininess FindChild(p(cyc),"LensA"),1
  EntityShininess FindChild(p(cyc),"LensB"),1
  EntityColor FindChild(p(cyc),"Specs"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
  ;glasses
  If charSpecs(char,coz)=1 
   EntityColor FindChild(p(cyc),"LensA"),255,255,255
   EntityAlpha FindChild(p(cyc),"LensA"),0.35
   EntityColor FindChild(p(cyc),"LensB"),255,255,255
   EntityAlpha FindChild(p(cyc),"LensB"),0.35
  EndIf
  ;lightly tinted glasses
  If charSpecs(char,coz)=2 
   EntityColor FindChild(p(cyc),"LensA"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
   EntityAlpha FindChild(p(cyc),"LensA"),0.35
   EntityColor FindChild(p(cyc),"LensB"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
   EntityAlpha FindChild(p(cyc),"LensB"),0.35
  EndIf
  ;heavily tinted glasses
  If charSpecs(char,coz)=3 
   EntityColor FindChild(p(cyc),"Specs"),15,15,15
   EntityColor FindChild(p(cyc),"LensA"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
   EntityAlpha FindChild(p(cyc),"LensA"),0.75
   EntityColor FindChild(p(cyc),"LensB"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
   EntityAlpha FindChild(p(cyc),"LensB"),0.75
  EndIf
  ;solid glasses
  If charSpecs(char,coz)=4 
   EntityColor FindChild(p(cyc),"LensA"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
   EntityAlpha FindChild(p(cyc),"LensA"),1
   EntityColor FindChild(p(cyc),"LensB"),charSpecsCol(char,1,coz),charSpecsCol(char,2,coz),charSpecsCol(char,3,coz)
   EntityAlpha FindChild(p(cyc),"LensB"),1
  EndIf
  ;shades
  If charSpecs(char,coz)=5 
   EntityColor FindChild(p(cyc),"LensA"),0,0,0
   EntityAlpha FindChild(p(cyc),"LensA"),0.9
   EntityColor FindChild(p(cyc),"LensB"),0,0,0
   EntityAlpha FindChild(p(cyc),"LensB"),0.9
  EndIf
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;DETERMINE MODEL TYPE
Function ModelType(char,coz)
 value=0
 ;slim
 If charModel(char,coz)=>1 And charModel(char,coz)=<3  Then value=1 
 ;normal
 If charModel(char,coz)=>4 And charModel(char,coz)=<6  Then value=2
 ;jaw
 If charModel(char,coz)=>7 And charModel(char,coz)=<8  Then value=3 
 ;muscular
 If charModel(char,coz)=>9 And charModel(char,coz)=<10  Then value=4
 ;chubby
 If charModel(char,coz)=11 Then value=5 
 ;fat 
 If charModel(char,coz)=12 Then value=6 
 ;obese 
 If charModel(char,coz)=13 Then value=7 
 ;female (slim) 
 If charModel(char,coz)=>14 And charModel(char,coz)=<16 Then value=8 
 ;female (fat)
 If charModel(char,coz)=17 Then value=9 
 Return value
End Function

;DETERMINE GENDER
Function GetGender(char) ;0=male, 1=female
 value=0
 If charModel(char,1)=>14 Then value=1
 Return value
End Function

;DETERMINE RACE
Function GetRace(char) ;0=white, 1=black
 value=0
 If charFace(char,1)=>66 And charFace(char,1)=<82 Then value=1
 If charFace(char,1)=86 Then value=1
 Return value
End Function

;DETERMINE VOICE PITCH
Function GetVoice(cyc)
 value=22050 : char=pChar(cyc)
 ;masculine tones
 If (ModelType(char,pCostume(cyc))=>4 And ModelType(char,pCostume(cyc))=<5) Or charHeight(char)=>18 Then value=21000
 If (ModelType(char,pCostume(cyc))=>6 And ModelType(char,pCostume(cyc))=<7) Or charHeight(char)=>24 Then value=20000
 ;femenine tones
 If ModelType(char,pCostume(cyc))=1 Or (ModelType(char,pCostume(cyc))=<3 And charHeight(char)=<6) Then value=23000
 If GetGender(char)=1 Then value=28000
 Return value
End Function

;DEFAULT THEME PITCH
Function DefaultPitch(theme)
 pitch=22050 
 If theme=1 Then pitch=44100 
 If theme=>26 And theme=<42 Then pitch=8000
 Return pitch
End Function

;DESIGNATED REFEREE?
Function DesignatedRef(char)
 value=0
 ;referee costume
 If charBody(char,1)=75 Or charBody(char,1)=76
  If charLeftArm(char,1)=>65 And charLeftArm(char,1)=<67 And charRightArm(char,1)=>65 And charRightArm(char,1)=<67 Then value=1
 EndIf
 ;school booker
 If char=fedBooker(7) Then value=1
 Return value
End Function

;CHECK MANAGER IS SUITABLE
Function SuitableManager(char)
 viable=1
 If game=1
  If charPop(char)>charPop(gamChar(slot))-10 Then viable=0
  If charHeel(char)<>charHeel(gamChar(slot)) Then viable=0
  If char=charFriend(gamChar(slot)) Then viable=1
  If char=charEnemy(gamChar(slot)) Then viable=0
  If char=fedBooker(charFed(gamChar(slot))) Or DesignatedRef(char) Then viable=0
  If CharBusy(char)>0 Then viable=0
 EndIf
 Return viable
End Function