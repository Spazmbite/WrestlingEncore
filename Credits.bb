;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING ENCORE: CREDITS --------------------------
;//////////////////////////////////////////////////////////////////////////////

;--------------------------------------------------------------------------
;////////////////////////////// INTRO /////////////////////////////////////
;--------------------------------------------------------------------------
Function Intro()
;initiate crowd
LoopSound sCrowd(1)
chCrowd=PlaySound(sCrowd(1))
;intro material
ChannelVolume chCrowd,0.1
font(0)=LoadFont("Verdana",12,0,0,0)
font(1)=LoadFont("Verdana",15,1,0,0) 
For count=1 To 8
 gMenu(count)=LoadImage("Graphics/Menu Boxes/Menu0"+count+".bmp")
 MaskImage gMenu(count),255,0,255
Next
;load arena
ChannelVolume chCrowd,0.15
tBigWall(3)=LoadTexture("World/Variations/BigWall03.JPG")
tCeiling(1)=LoadTexture("World/Variations/Ceiling01.JPG")
tWall(5)=LoadTexture("World/Variations/Wall05.JPG")
tGround(1)=LoadTexture("World/Variations/Ground01.JPG")
tGround(2)=LoadTexture("World/Variations/Ground02.JPG")
tCrowd(0)=LoadAnimTexture("World/Sprites/Crowders.png",4,512,128,0,4)
tCrowd(1)=LoadTexture("World/Variations/Seating01.JPG")
tVideo(1)=LoadTexture("World/Videos/Video01.JPG")
tVideo(2)=LoadTexture("World/Videos/Video02.JPG")
matchCage=0 : matchRopes=0
arenaPreset=13 : arenaCrowd=1 : arenaAtmos=0 : arenaLight=0
arenaRopes=1 : arenaApron=1 : arenaCanvas=1 : arenaMat=1
LoadArena()
;load atmosphere
ChannelVolume chCrowd,0.2
LoadAtmos()
AmbientLight 200,200,200
camType=6
;load character
ChannelVolume chCrowd,0.25
cyc=1 : char=5
p(cyc)=LoadAnimMesh("Models/Bodies/"+textModel$(charModel(char,2))+".3ds")
pSeq(cyc,600)=LoadAnimSeq(p(cyc),"Models/Sequences/Gestures.3ds")
pSeq(cyc,601)=LoadAnimSeq(p(cyc),"Models/Sequences/Movement.3ds")
pSeq(cyc,1)=ExtractAnimSeq(p(cyc),110,170,pSeq(cyc,601)) ;stroll
pSeq(cyc,2)=ExtractAnimSeq(p(cyc),5090,5330,pSeq(cyc,600)) ;contemplation
scaler#=charHeight(char)*0.0025
ScaleEntity p(cyc),0.35+scaler#,0.35+scaler#,0.35+scaler#
pX#(cyc)=-2 : pZ#(cyc)=250
pY#(cyc)=wGround# : pA#(cyc)=0
Animate p(cyc),1,1.6,pSeq(cyc,1),0
introState=0
;dress character
ChannelVolume chCrowd,0.3
tVoid=LoadTexture("Costumes/Void.JPG")
tShaved=LoadTexture("Costumes/Hair/Shaved.JPG")
tHatBlack=LoadTexture("Costumes/Hats/Hat_Black.JPG")
tHatWhite=LoadTexture("Costumes/Hats/Hat_White.JPG")
tCapBlack=LoadTexture("Costumes/Hats/Cap_Black.JPG")
tCapWhite=LoadTexture("Costumes/Hats/Cap_White.JPG")
tEyes(2)=LoadTexture("Costumes/Expressions/Eyes02.JPG")
tHair(charHair(char,2))=LoadTexture("Costumes/Hair/Hair"+Dig$(charHair(char,2),10)+".JPG")
tFace(charFace(char,2))=LoadTexture("Costumes/Faces/Face"+Dig$(charFace(char,2),10)+".JPG")
tBody(charBody(char,2))=LoadTexture("Costumes/Bodies/Body"+Dig$(charBody(char,2),10)+".JPG")
tArms(charLeftArm(char,2))=LoadTexture("Costumes/Arms/Arm"+Dig$(charLeftArm(char,2),10)+".JPG")
tArms(charRightArm(char,2))=LoadTexture("Costumes/Arms/Arm"+Dig$(charRightArm(char,2),10)+".JPG")
tLegs(charLegs(char,2))=LoadTexture("Costumes/Legs/Legs"+Dig$(charLegs(char,2),10)+".JPG")
ApplyCostume(cyc,char,2)
EntityTexture pLimb(cyc,1),tEyes(2),0,3
LoadWeaponData()
For v=1 To weapList
 HideEntity FindChild(p(cyc),weapFile$(v))
Next
;shadow
ChannelVolume chCrowd,0.35
pShadow(cyc)=LoadSprite("World/Sprites/Shadow.bmp",12)
ScaleSprite pShadow(cyc),10,10
RotateEntity pShadow(cyc),90,0,0
SpriteViewMode pShadow(cyc),2
EntityAlpha pShadow(cyc),0.1
;frame rating
ChannelVolume chCrowd,0.4
period=frameRate(optSpeed)/FPS
time=MilliSecs()-period
SeedRnd(MilliSecs())
;MAIN LOOP
go=0 : gotim=-50 : keytim=10
While go=0

 Cls
 Repeat
  elapsed=MilliSecs()-time 
 Until elapsed
 ticks=elapsed/period
 tween#=Float(elapsed Mod period)/Float(period)
	 
 For k=1 To ticks
	time=time+period
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>100 And keytim=0
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Or pZ#(1)>520 Then go=1
    EndIf 

    ;CHARACTER
    ;manage sequence
    cyc=1
    pAnimTim(cyc)=pAnimTim(cyc)+1
    If (introState=0 Or introState=3) And go=0 Then pZ#(cyc)=pZ#(cyc)+0.5 : pStepTim(cyc)=pStepTim(cyc)+1
    If introState=0 And pZ#(cyc)>450 Then introState=1
    If introState=1 And pA#(cyc)>135
     PlaySound sCrowd(6)
     Animate p(cyc),3,1.0,pSeq(cyc,2),15
     introState=2 : pAnimTim(cyc)=0
    EndIf
    If introState=2 And pAnimTim(cyc)>255 
     PlaySound sCrowd(3)
     Animate p(cyc),1,1.6,pSeq(cyc,1),10
     introState=3
    EndIf
    If introState=>1 And introState=<2 And pA#(cyc)<180 Then pA#(cyc)=pA#(cyc)+10 : pStepTim(cyc)=pStepTim(cyc)+1
    If introState=3 And pA#(cyc)<360 Then pA#(cyc)=pA#(cyc)+10 : pStepTim(cyc)=pStepTim(cyc)+1
    If pStepTim(cyc)>20 And gotim>0 Then PlaySound sStep(Rnd(5,6)) : pStepTim(cyc)=0
    If go=1 Then Animate p(cyc),1,1.6,pSeq(cyc,1),100
    ;update display
    PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
    RotateEntity p(cyc),0,pA#(cyc),0
    PositionEntity pShadow(cyc),pX#(cyc),pY#(cyc)+0.4,pZ#(cyc)
    ;reactions
    If gotim=0 Then PlaySound sJingle ;sCrowd(2)
    If gotim=150 Then PlaySound sCrowd(10)
    If introState=2 And pAnimTim(cyc)=115 Then PlaySound sCrowd(7)

    ;CAMERA
    If go=0 Then Camera()
    ;animate crowd
    If go=0 And optCrowdAnim=1
     For cyc=1 To 21
      randy=Rnd(0,5)
      If randy=0 Then crowdTY#(cyc)=crowdSource#(cyc)+Rnd(-1.0,1.0)
      If crowdY#(cyc)<crowdTY#(cyc) Then crowdY#(cyc)=crowdY#(cyc)+0.25
      If crowdY#(cyc)>crowdTY#(cyc) Then crowdY#(cyc)=crowdY#(cyc)-0.25
      If crowdY#(cyc)<crowdTY#(cyc)-0.15 Or crowdY#(cyc)>crowdTY#(cyc)+0.15
       limb=FindChild(world,"Crowd"+Dig$(cyc,10))
       PositionEntity limb,EntityX(limb),crowdY#(cyc),EntityZ(limb)
      EndIf
     Next
    EndIf
 	
 UpdateWorld
 Next
 RenderWorld 1
 
 ;DISPLAY
 ;mask shaky start!
 If gotim=<0 Then Color 0,0,0 : Rect 0,0,GraphicsWidth(),GraphicsHeight()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;trigger theme
StopChannel chCrowd
LoopSound sTheme(1)
chTheme=PlaySound(sTheme(1))
;remove atmospherics
Loader("Please Wait","Loading Game")
FreeEntity world
FreeEntity cam
FreeEntity camPivot
FreeEntity light
FreeEntity lightPivot
FreeEntity dummy
;remove entities
FreeEntity p(cyc)
FreeEntity pShadow(cyc)
For cyc=1 To 12
 FreeEntity rope(cyc)
Next
;remove temporary textures
FreeTexture tBigWall(3)
FreeTexture tCeiling(1)
FreeTexture tWall(5)
FreeTexture tGround(1)
FreeTexture tGround(2)
FreeTexture tCrowd(0)
FreeTexture tCrowd(1)
FreeTexture tVideo(1)
FreeTexture tVideo(2)
If tApron>0 Then FreeTexture tApron
If tCanvas>0 Then FreeTexture tCanvas
If tMat>0 Then FreeTexture tMat
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------------ 1. MAIN MENU ----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function MainMenu()
;assess locks
If fedLocked(1)=0 And fedLocked(2)=0 And fedLocked(3)=0 And fedLocked(4)=0 And fedLocked(5)=0 And fedLocked(6)=0
 fedLocked(7)=0 : fedLocked(8)=0 : fedLocked(9)=0
EndIf
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
foc=1 : oldfoc=foc
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
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
    ;PORTAL 
    gotim=gotim+1
	If gotim>20 
	 ;quit
	 If KeyDown(1) And gotim>30 Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc=6 Then go=-1 Else go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	If gotim>20 And keytim=0
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	EndIf
	If foc>6 Then foc=1
	If foc<1 Then foc=6
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(1),rX#(400),rY#(200)
 DrawImage gMDickie,rX#(100),rY#(550)
 DrawImage gMDickie,rX#(700),rY#(550)
 ;DrawImage gIdigicon,rX#(700),rY#(550)
 ;options
 SetFont font(1)
 y=360
 DrawOption(1,400,y,"CAREER","",0,0)
 DrawOption(2,400,y+35,"EXHIBITION","",0,0)
 DrawOption(3,400,y+70,"EDITOR","",0,0)
 DrawOption(4,400,y+105,"OPTIONS","",0,0)
 DrawOption(5,400,y+140,"CREDITS","",0,0)
 DrawOption(6,400,550,"<<< QUIT <<<","",0,0)
 ;inst
 SetFont font(1)
 Outline("UP/DOWN=Highlight option, ENTER=Proceed, ESC=Quit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;version
 SetFont font(0)
 Outline("Version 1.6",rX#(400)+195,rY#(200)+85,0,0,0,10,20,40)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=-1 Then screen=7
If go=1 
 If foc=1 Then game=1 : screen=21
 If foc=2 Then game=0 : screenAgenda=1 : screen=14
 If foc=3 Then game=0 : screenAgenda=0 : screen=14
 If foc=4 Then screen=2
 If foc=5 Then screen=6
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------------- 6. CREDITS -----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Credits()
;frame rating
period=1000/FPS
time=MilliSecs()-period
PlaySound sCrowd(2)
;MAIN LOOP
go=0 : gotim=0 : keytim=10 : scroll#=0
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
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 If KeyDown(1) Then go=-1
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) Then go=1
	EndIf
	
	;scroller
	If gotim>50
	 scroll#=scroll#-1
	 If KeyDown(200) Or JoyY()=-1 Then scroll#=scroll#-4.5
	 If KeyDown(208) Or JoyY()=1 Then scroll#=scroll#+5
	 If scroll#<-1600 Then scroll#=500
	 If scroll#>500 Then scroll#=-1600;1425
	EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(1),rX#(400),rY#(200+scroll#)
 ;text
 SetFont font(2)
 Outline("CONCEPT",rX#(400),rY#(350+scroll#),100,50,0,255,255,100)
 Outline("© MDickie 2005-2006",rX#(400),rY#(375+scroll#),60,60,60,250,250,250)
 Outline("GAME DESIGN",rX#(400),rY#(425+scroll#),90,45,0,255,230,0)
 Outline("Mat Dickie",rX#(400),rY#(450+scroll#),60,60,60,250,250,250)
 Outline("PROGRAMMING",rX#(400),rY#(500+scroll#),80,40,0,255,210,0)
 Outline("Mat Dickie",rX#(400),rY#(525+scroll#),60,60,60,250,250,250)
 Outline("2D GRAPHICS",rX#(400),rY#(575+scroll#),70,35,0,255,190,0)
 Outline("Mat Dickie",rX#(400),rY#(600+scroll#),60,60,60,250,250,250)
 Outline("3D MODELLING",rX#(400),rY#(650+scroll#),60,30,0,255,170,0)
 Outline("Mat Dickie",rX#(400),rY#(675+scroll#),60,60,60,250,250,250)
 Outline("ANIMATION",rX#(400),rY#(725+scroll#),50,25,0,255,150,0)
 Outline("Mat Dickie",rX#(400),rY#(750+scroll#),60,60,60,250,250,250)
 Outline("TEXTURING",rX#(400),rY#(800+scroll#),40,20,0,240,130,0)
 Outline("Mat Dickie",rX#(400),rY#(825+scroll#),60,60,60,250,250,250)
 Outline("SOUND FX",rX#(400),rY#(875+scroll#),30,15,0,230,110,0)
 Outline("Mat Dickie",rX#(400),rY#(900+scroll#),60,60,60,250,250,250)
 Outline("MUSIC",rX#(400),rY#(950+scroll#),20,10,0,220,90,0)
 Outline("Mat Dickie",rX#(400),rY#(975+scroll#),60,60,60,250,250,250)
 Outline("SCRIPT WRITING",rX#(400),rY#(1025+scroll#),10,5,0,210,70,0)
 Outline("Mat Dickie",rX#(400),rY#(1050+scroll#),60,60,60,250,250,250)
 Outline("PUBLISHING",rX#(400),rY#(1100+scroll#),0,0,0,200,50,0)
 Outline("MDickie.com",rX#(400),rY#(1125+scroll#),60,60,60,250,250,250)
 DrawImage gMDickie,rX#(400),rY#(1225+scroll#)
 Outline("THANKS",rX#(400),rY#(1325+scroll#),0,0,0,200,0,0)
 Outline("To all fans of the sport, fans of the",rX#(400),rY#(1350+scroll#),60,60,60,250,250,250)
 Outline("games, and fans of the philosophy. When",rX#(400),rY#(1375+scroll#),60,60,60,250,250,250)
 Outline("you're reaching mind, body, and soul",rX#(400),rY#(1400+scroll#),60,60,60,250,250,250) 
 Outline("the triangle can't be broken!",rX#(400),rY#(1425+scroll#),60,60,60,250,250,250) 
 Outline("SPECIAL THANKS",rX#(400),rY#(1475+scroll#),0,0,0,180,0,0)
 Outline("To the fan communities that are helping",rX#(400),rY#(1500+scroll#),60,60,60,250,250,250)
 Outline("to nudge the game towards its true potential.",rX#(400),rY#(1525+scroll#),60,60,60,250,250,250)
 Outline("Keep enjoying what you're doing and the",rX#(400),rY#(1550+scroll#),60,60,60,250,250,250) 
 Outline("public will follow suit...",rX#(400),rY#(1575+scroll#),60,60,60,250,250,250) 
 ;Outline("PUBLISHING",rX#(400),rY#(1100+scroll#),0,0,0,200,50,0)
 ;Outline("Idigicon",rX#(400),rY#(1125+scroll#),60,60,60,250,250,250)
 ;DrawImage gIdigicon,rX#(400),rY#(1215+scroll#)
 ;DrawImage gMDickie,rX#(400),rY#(1325+scroll#) 
 ;cursor
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
screen=1
End Function

;-------------------------------------------------------------------
;////////////////////////// 7. OUTRO ///////////////////////////////
;-------------------------------------------------------------------
Function Outro()
;frame rating
period=1000/FPS
time=MilliSecs()-period
ChannelVolume chTheme,0.3
;MAIN LOOP
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
	
	;PORTAL
    gotim=gotim+1
	If gotim>50 
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Or gotim>300 Then go=1
	EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 y=370
 TileImage gTile
 DrawImage gOutro,rX#(400),rY#(y)
 DrawImage gLogo(1),rX#(400),rY#(145)
 DrawImage gMDickie,rX#(400),rY#(540)
 ;text
 SetFont font(3)
 ;Outline("Get the most out of the game by",rX#(400),rY#(y)-35,0,0,0,250,250,250)
 ;Outline("reading the instruction manuals",rX#(400),rY#(y),0,0,0,250,250,250)
 ;Outline("that were installed along with it!",rX#(400),rY#(y)+35,0,0,0,250,250,250) 

 Outline("Step out of the ring and into",rX#(400),rY#(y)-35,0,0,0,250,250,250)
 Outline("the office by checking out the",rX#(400),rY#(y),0,0,0,250,250,250)
 Outline("second game in the series!",rX#(400),rY#(y)+35,0,0,0,250,250,250)

 ;Outline("Keep your wrestling career",rX#(400),rY#(y)-45,0,0,0,250,250,250)
 ;Outline("up to date with free downloads",rX#(400),rY#(y)-15,0,0,0,250,250,250)
 ;Outline("and other behind-the-scenes",rX#(400),rY#(y)+15,0,0,0,250,250,250)
 ;Outline("info from MDickie.com!",rX#(400),rY#(y)+45,0,0,0,250,250,250)  

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;save & leave
SaveOptions()
SaveProgress()
SaveWorld()
SaveChars() 
screen=0
End Function