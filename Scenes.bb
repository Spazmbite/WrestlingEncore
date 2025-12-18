;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING ENCORE: CUTSCENES ------------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------
;///////////////// LOAD TRAINING SETTING ///////////////////////
;---------------------------------------------------------------
Function PrepareWorkout()
 ;ATMOSPHERE
 ;location
 negSetting=2
 LoadSet()
 ;camera
 cam=CreateCamera()
 CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
 camX#=135 : camY#=27 : camZ#=715
 PositionEntity cam,camX#,camY#,camZ#
 camType=1 : camFoc=1
 camOverride=0 : camTempTim=0
 ;assistants
 camPivot=CreatePivot()
 dummy=CreatePivot()
 ;lighting
 AmbientLight 200,200,200
 light=CreateLight(1) 
 LightColor light,220,200,180
 PositionEntity light,-20,70,-20
 ;CHARACTER
 negChar=gamChar(slot)
 cyc=1 : no_plays=1
 ;model
 p(cyc)=LoadAnimMesh("Models/Bodies/"+textModel$(charModel(negChar,2))+".3ds") 
 pSeq(cyc,600)=LoadAnimSeq(p(cyc),"Models/Sequences/Gestures.3ds")
 pSeq(cyc,601)=LoadAnimSeq(p(cyc),"Models/Sequences/Movement.3ds") 
 pSeq(cyc,0)=ExtractAnimSeq(p(cyc),3360,3390,pSeq(cyc,600)) ;rest
 pSeq(cyc,1)=ExtractAnimSeq(p(cyc),3400,3440,pSeq(cyc,600)) ;strength exercise
 pSeq(cyc,2)=ExtractAnimSeq(p(cyc),3450,3490,pSeq(cyc,600)) ;skill exercise
 pSeq(cyc,3)=ExtractAnimSeq(p(cyc),  40, 100,pSeq(cyc,601)) ;agility exercise
 pSeq(cyc,4)=ExtractAnimSeq(p(cyc),3500,3540,pSeq(cyc,600)) ;stamina exercise
 pSeq(cyc,5)=ExtractAnimSeq(p(cyc),3900,3960,pSeq(cyc,600)) ;toughness exercise
 pSeq(cyc,6)=ExtractAnimSeq(p(cyc),3400,3540,pSeq(cyc,600)) ;everything exercise
 ;appearance
 scaler#=charHeight(negChar)*0.0025
 ScaleEntity p(cyc),0.35+scaler#,0.35+scaler#,0.35+scaler#
 ApplyCostume(cyc,negChar,2)
 pEyes(cyc)=charEyes(negChar)
 pOldEyes(cyc)=-1
 ;hide weapons
 For v=1 To weapList
  HideEntity FindChild(p(cyc),weapFile$(v))
 Next
 ;initiation
 pX#(cyc)=170 : pY#(cyc)=2 : pZ#(cyc)=750 : pA#(1)=160
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(cyc),0,pA#(cyc),0
 Animate p(cyc),1,0.2,pSeq(cyc,charTrainCourse(negChar)),0
 ;shadow
 pShadow(cyc)=LoadSprite("World/Sprites/Shadow.bmp",12)
 ScaleSprite pShadow(cyc),10,10
 RotateEntity pShadow(cyc),90,0,0
 SpriteViewMode pShadow(cyc),2
 EntityAlpha pShadow(cyc),0.1
 PositionEntity pShadow(cyc),pX#(cyc),pY#(cyc)+0.4,pZ#(cyc)
End Function

;-----------------------------------------------------------------
;////////////////////// 54. TRAINING /////////////////////////////
;-----------------------------------------------------------------
Function Training()
;load setting
Loader("Please Wait","Preparing To Train")
ChannelVolume chTheme,0.5
PrepareWorkout()
;frame rating
period=1000/FPS  
time=MilliSecs()-period
;MAIN LOOP
oldCourse=-1 : oldLevel=-1
go=0 : gotim=0 : keytim=10 : foc=1
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
	If gotim>20 
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;activations
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  ;trigger course
	  If foc=<6 And charInjured(negChar)>0 And keytim=0 Then PlaySound sVoid : keytim=10
	  If foc=<6 And charInjured(negChar)=0 And keytim=0 
	   charTrainCourse(negChar)=foc : PlaySound sStep(1) : keytim=10
	   If foc>0 Then PlaySound sPain(2)
	  EndIf
	  ;proceed
	  If foc=8 And charInjured(negChar)>0 And keytim=0 Then PlaySound sVoid : keytim=10
	  If foc=8 And charInjured(negChar)=0 And keytim=0 Then go=2
	  If foc=9 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;CONFIGURE
	If keytim=0 And gotim>20 
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>9 Then foc=0
	 If foc<0 Then foc=9
	 ;adjust intensity
	 If foc=7 
	  If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) 
	   PlaySound sMenuBrowse : keytim=6
	   charTrainLevel(negChar)=charTrainLevel(negChar)-1
	  EndIf
	  If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	   PlaySound sMenuBrowse : keytim=6
	   charTrainLevel(negChar)=charTrainLevel(negChar)+1
	  EndIf
	 EndIf
	EndIf 
	;check limits
	If charTrainLevel(negChar)<1 Then charTrainLevel(negChar)=1
	If charTrainLevel(negChar)>3 Then charTrainLevel(negChar)=3 
	If charInjured(negChar)>0 Then charTrainCourse(negChar)=0
	If charTrainCourse(negChar)=0 Then charTrainLevel(negChar)=0  
	
	;MANAGE ANIMATION   	
	If oldCourse<>charTrainCourse(negChar) Or oldLevel<>charTrainLevel(negChar)
	 ;get initial speed
	 speeder#=1.0
	 If charTrainCourse(negChar)=0 Then speeder#=0.5
	 If charTrainCourse(negChar)=3 Then speeder#=1.5
	 If charTrainCourse(negChar)=5 Then speeder#=1.5 
	 ;intensity variations
	 If charTrainLevel(negChar)=<1 Then speeder#=speeder#-(speeder#/3)
	 If charTrainLevel(negChar)=3 Then speeder#=speeder#+(speeder#/3)  
	 ;deliver animation
	 If charTrainCourse(negChar)=<5 Then Animate p(1),1,speeder#,pSeq(1,charTrainCourse(negChar)),5
	 If charTrainCourse(negChar)=6 Then Animate p(1),2,speeder#,pSeq(1,charTrainCourse(negChar)),5 
    EndIf   
    ;record changes
    oldCourse=charTrainCourse(negChar) 
	oldLevel=charTrainLevel(negChar)

	;SPEAKING
	If charTrainCourse(negChar)>0 Then pSpeaking(1)=1 Else pSpeaking(1)=0 
	FacialExpressions(1)
	
	;CAMERA
	Camera()
	
 UpdateWorld
 Next  
 RenderWorld 1

 ;DISPLAY
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;profile
 DrawProfile(negChar,-1,-1)
 ShowStatProgress(negChar)
 ;options
 x=580 : y=190
 SetFont font(1)
 For count=0 To 6
  DrawOption(count,x,y,Upper$(textTrainCourse$(count)),"",0,0)
  If count=charTrainCourse(negChar) Then DrawImageRect charPhoto(negChar),rX#(x)-70,rY#(y)+20,0,0,70,55
  y=y+30
 Next
 DrawOption(7,x,y+10,"Intensity",textTrainLevel$(charTrainLevel(negChar)),1,2)
 DrawOption(8,x,y+60,"SPARRING SESSION","",0,0) 
 DrawOption(9,x,y+100,"<<< SAVE & EXIT <<<","",0,0) 
 ;intensity preview
 If charTrainLevel(negChar)>0
  Color 130,0,0 : Rect rX#(x)-50,rY#(y+10)+12,100,5,1
  Color 200,0,0 : Rect rX#(x)-49,rY#(y+10)+13,98,3,1
  If charTrainLevel(negChar)=1 Then liner=75
  If charTrainLevel(negChar)=2 Then liner=50
  If charTrainLevel(negChar)=3 Then liner=25
  Color 0,130,0 : Rect rX#(x)-50,rY#(y+10)+12,liner,5,1
  Color 0,200,0 : Rect rX#(x)-49,rY#(y+10)+13,liner-2,3,1  
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
ChannelVolume chTheme,1	
;free entities
FreeEntity world
FreeEntity cam 
FreeEntity camPivot
FreeEntity dummy
FreeEntity light
;remove trainee
FreeEntity p(1)
FreeEntity pShadow(1)
;proceed
screen=15
;detour to sparring
If go=2
 For cyc=1 To optPlayLim
  pChar(cyc)=0
  pControl(cyc)=0
 Next
 LoadMatch(1) : AddGimmick(0)
 matchPromo=0 : matchChamps=0
 no_plays=no_wrestlers+no_refs
 pChar(1)=negChar : pChar(2)=GetOpponent()
 arenaPreset=1 : arenaCrowd=0 
 arenaAtmos=Rnd(0,9) : arenaLight=0
 If optAtmos=0 Then arenaAtmos=0
 arenaApron=0 : arenaCanvas=Rnd(1,3) 
 arenaRopes=Rnd(8,9) : arenaMat=Rnd(0,3)
 screenAgenda=10 : screen=50
EndIf
End Function

;-----------------------------------------------------------------
;//////////////////// 55. PLASTIC SURGERY ////////////////////////
;-----------------------------------------------------------------
Function PlasticSurgery()
;get setting
Loader("Please Wait","Consulting Surgeon")
ChannelVolume chTheme,0.5
negChar=fedBooker(9) : fed=charFed(gamChar(slot))
negSetting=4 : camFoc=2
PrepareMeeting()
;reset situation
negTim=0 : negStage=0 : negVerdict=0
;frame ratings
period=1000/FPS  
time=MilliSecs()-period
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
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
	;If k=ticks Then CaptureWorld
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL 
	screenCall=0
    gotim=gotim+1 : negTim=negTim+1 
    ;speed-up's
	If KeyDown(1) Or MouseDown(1)
	 If negStage<>1 And negStage<>3 And negTim<350 And keytim=0 Then negTim=negTim+50 : keytim=5 ;: PlaySound sMenuBrowse
	EndIf 
	;leave
	If negStage=>4 And negTim>350 Then go=1
	
	;CONFIGURE
	If gotim>20 And keytim=0 And (negStage=1 Or negStage=3)
	 ;browse left
	 If MouseDown(2) And foc=<3 And negStage=1
	  PlaySound sMenuBrowse
	  If foc=1 Then charHeight(0)=charHeight(0)-1 : keytim=3
	  If foc=2 Then charModel(0,2)=charModel(0,2)-1 : keytim=6
	  If foc=3 Then charFace(0,2)=charFace(0,2)-1 : keytim=5
	 EndIf
	 ;browse right
	 If MouseDown(1) And foc=<3 And negStage=1
	  PlaySound sMenuBrowse
	  If foc=1 Then charHeight(0)=charHeight(0)+1 : keytim=3
	  If foc=2 Then charModel(0,2)=charModel(0,2)+1 : keytim=6
	  If foc=3 Then charFace(0,2)=charFace(0,2)+1 : keytim=5
	 EndIf
	 ;refresh model
	 If KeyDown(28)
	  screenCall=1 : PlaySound sMenuGo : keytim=10
	 EndIf
	 ;submit proposal
	 If MouseDown(1) And foc=4
	  If negStage=1 And negPayOff=0 Then negStage=5 : negTim=0 : PlaySound sMenuBack : keytim=10
	  If negStage=1 And negPayOff>0 Then negStage=2 : negTim=0 : PlaySound sMenuGo : keytim=10
	  If negStage=3 Then negStage=4 : negTim=0 : PlaySound sMenuGo : keytim=10
	 EndIf
	 ;quit
	 If KeyDown(1) Or (MouseDown(1) And foc=5) 
	  PlaySound sMenuBack : negStage=5 : negTim=0
	 EndIf
	EndIf
	;check limits
	CheckLimits(0)
	
	;UPDATE CHANGES
	;scale model
	scaler#=charHeight(0)*0.0025
	ScaleEntity p(1),0.35+scaler#,0.35+scaler#,0.35+scaler#
	;apply costume
	ApplyCostume(1,0,2)  
	;restore limbs
	If ModelType(0,2)<>ModelType(saver,2)
	 For limb=1 To 40
      If pLimb(1,limb)>0 
       ShowEntity pLimb(1,limb) 
      EndIf
     Next
	EndIf
	;cost gauntlet
	If negStage=<3
	 negPayOff=0
	 If charHeight(0)<>charHeight(saver) Then negPayOff=negPayOff+5000 
	 If ModelType(0,2)<>ModelType(saver,2) Then negPayOff=negPayOff+5000 
	 If charFace(0,2)<>charFace(saver,2) Then negPayOff=negPayOff+5000   
	EndIf
	
	;SPEAKING
	For cyc=1 To no_plays
	 FacialExpressions(cyc)
	Next
	
	;CAMERA
	Camera()
	
 UpdateWorld
 Next  
 RenderWorld 1

 ;DISPLAY
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;reset expressions
 For cyc=1 To no_plays
  pSpeaking(cyc)=0
 Next
 ;initiate font
 SetFont font(3)
 If GraphicsWidth()=>1024 Then SetFont font(4)
 x=400 : y=520
 ;INTRO
 If negStage=0
  If negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Welcome to the surgery, "+charName$(pChar(1))+". Just tell me",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("what you're looking for and i'll see what i can do...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;CONFIGURE
 If negStage=1 Or negStage=3
  SetFont font(1)
  x=400 : y=510
  If foc=<3 Then Speak(1,0,2)
  If foc=4 Then Speak(1,0,3)
  If foc=5 Then Speak(1,0,1)
  DrawOption(1,x,y,"Height",GetHeight$(charHeight(0)),1,2)
  DrawOption(2,x,y+30,"Build",textBuild$(charModel(0,2)),1,2)
  DrawOption(3,x,y+60,"Face",charFace(0,2),1,2)
  If foc=2 Then Outline("(Press ENTER to apply)",rX#(400),rY#(y+43),50,30,10,255,200,150)
  ;proceeders
  If negStage=3 Then namer$=">>> CONFIRM >>>" Else namer$=">>> PROPOSE >>>"
  DrawOption(4,x+250,y+30,namer$,"",0,0)
  DrawOption(5,x-250,y+30,"<<< WITHDRAW <<<","",0,0)
 EndIf
 ;RESPONSE
 If negStage=2
  If negTim>5 Then camFoc=2
  If negTim>25 And negTim<325 And gamClause(slot,1)=<1
   Speak(2,0,3)
   Outline("OK, i'd need $"+GetFigure$(negPayOff)+" to perform that operation?",rX#(400),rY#(520),0,50,0,150,250,150)
   Outline("Just confirm the details and i can get to work...",rX#(400),rY#(555),0,50,0,150,250,150)
  EndIf
  If negTim>25 And negTim<325 And gamClause(slot,1)=2
   Speak(2,0,3)
   Outline("OK, "+fedName$(fed)+" are picking up the bill",rX#(400),rY#(520),0,50,0,150,250,150)
   Outline("for this so i just need you to confirm the details...",rX#(400),rY#(555),0,50,0,150,250,150)
  EndIf
  If negTim>350 Then negStage=3 : negTim=0 : keytim=20
 EndIf
 ;ENDINGS
 If negStage=>4 And negTim>5 Then camFoc=2
 If negStage=4 And negTim>25 And negTim<325 And gamBank(slot)=>negPayOff And gamClause(slot,1)=>1
  Speak(2,0,3)
  If charged=0 
   If gamClause(slot,1)=<1 Then gamBank(slot)=gamBank(slot)-negPayOff : negPayOff=0
   PlaySound sCash : charged=1
  EndIf
  Outline("OK, just relax while i finalize the work we've done.",rX#(400),rY#(520),50,50,50,250,250,250)
  Outline("When you wake up you'll feel like a different person!",rX#(400),rY#(555),50,50,50,250,250,250)
 EndIf
 ;short changed!
 If negStage=4 And negTim>25 And negTim<325 And gamBank(slot)<negPayOff And gamClause(slot,1)=>1
  Speak(2,0,1)
  Outline("Hang on, you haven't got enough money for this surgery!",rX#(400),rY#(520),50,0,0,250,150,150)
  Outline("Come back when you've learnt to manage your finances...",rX#(400),rY#(555),50,0,0,250,150,150)
 EndIf
 ;short changed!
 If negStage=4 And negTim>25 And negTim<325 And gamClause(slot,1)=0
  Speak(2,0,1)
  Outline("Hang on, you're not authorized to change your image!",rX#(400),rY#(520),50,0,0,250,150,150)
  Outline("Come back when you've negotiated a better contract...",rX#(400),rY#(555),50,0,0,250,150,150)
 EndIf
 ;breakdown
 If negStage=5 And negTim>25 And negTim<325
  Speak(2,0,1)
  Outline("Alright, you're obviously not comfortable about this.",rX#(400),rY#(520),50,0,0,250,150,150)
  Outline("Give it some thought and come back when you're ready...",rX#(400),rY#(555),50,0,0,250,150,150)
 EndIf
 ;loading calls
 If screenCall=1
  QuickLoader(400,300,"Please Wait","Reconstructing Character")
  FreeEntity p(1)
  LoadMeeter(1,0)
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore theme
;PlaySound sMenuGo
ChannelVolume chTheme,1.0
;free entities	
FreeEntity world
FreeEntity cam 
FreeEntity camPivot
FreeEntity dummy
FreeEntity light
For cyc=1 To no_plays
 FreeEntity p(cyc)
 FreeEntity pShadow(cyc)
Next
;save changes
If negStage=4 And charged=1
 If ModelType(0,2)<>ModelType(saver,2) 
  For limb=1 To 40
   charLimb(0,limb)=1
  Next  
 EndIf
 For count=1 To 3
  If ModelType(0,count)<>ModelType(0,2) Then charModel(0,count)=charModel(0,2)
  charFace(0,count)=charFace(0,2)
 Next
 CopyChar(0,saver)
EndIf
;return to editor
screen=51
End Function