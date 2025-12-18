;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING ENCORE: MENU SCREENS -----------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;------------------------------- 2. OPTIONS -----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Options()
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
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  If foc<9 And MouseDown(1)=0 Then foc=12 : keytim=10 
	  If foc=>9 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	oldMatch=matchPreset 
	If gotim>20 And keytim=0
	 ;highlight options
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>12 Then foc=1
	 If foc<1 Then foc=12
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then optLevel=optLevel-1
	  If foc=2 Then optHealth=optHealth-1
	  If foc=3 Then optSpeed=optSpeed-1
	  If foc=4 Then optRef=optRef-1
	  If foc=5 Then optManagers=optManagers-1
	  If foc=6 Then optExtras=optExtras-1
      If foc=7 Then optHideElim=optHideElim-1
      If foc=8 Then optTagControl=optTagControl-1
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then optLevel=optLevel+1
	  If foc=2 Then optHealth=optHealth+1
	  If foc=3 Then optSpeed=optSpeed+1
	  If foc=4 Then optRef=optRef+1
	  If foc=5 Then optManagers=optManagers+1
	  If foc=6 Then optExtras=optExtras+1
      If foc=7 Then optHideElim=optHideElim+1
      If foc=8 Then optTagControl=optTagControl+1
	 EndIf
	EndIf
	;check limits
	If optLevel<0 Then optLevel=0 
	If optLevel>5 Then optLevel=5
	If optHealth<0 Then optHealth=0
	If optHealth>2 Then optHealth=2
	If optSpeed<0 Then optSpeed=0
	If optSpeed>3 Then optSpeed=3 
	If optRef<0 Then optRef=0
	If optRef>2 Then optRef=2
	If optManagers<0 Then optManagers=0
	If optManagers>2 Then optManagers=2 
	If optExtras<0 Then optExtras=0
	If optExtras>2 Then optExtras=2
	If optHideElim<0 Then optHideElim=0
	If optHideElim>2 Then optHideElim=2 
	If optTagControl<0 Then optTagControl=2
	If optTagControl>2 Then optTagControl=0
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;options
 SetFont font(1)
 x=400 : y=140
 DrawOption(1,400,y,"CPU Level",textLevel$(optLevel),1,0)
 DrawOption(2,400,y+30,"Match Length",textLength$(optHealth),1,0)
 DrawOption(3,400,y+60,"Game Speed",textSpeed$(optSpeed),1,0)
 DrawOption(4,400,y+95,"Referees",textExtras$(optRef),1,0)
 DrawOption(5,400,y+125,"Managers",textExtras$(optManagers),1,0)
 DrawOption(6,400,y+155,"Interferences",textExtras$(optExtras),1,0)
 DrawOption(7,400,y+190,"Eliminated Wrestlers",textHide$(optHideElim),1,0)
 DrawOption(8,400,y+220,"Tag Control",textTagControl$(optTagControl),1,0)
 DrawOption(9,400,y+260,"DISPLAY OPTIONS","",0,0)
 DrawOption(10,400,y+295,"REDEFINE KEYS","",0,0)
 DrawOption(11,400,y+325,"REDEFINE GAMEPAD","",0,0) 
 DrawOption(12,400,550,"<<< SAVE & EXIT <<<","",0,0)
 ;warnings
 SetFont font(1)
 If foc=1 And optLevel=<1 Then Outline("(Not applicable in Career mode!)",rX#(400),rY#(152),0,0,0,200,100,100)
 ;inst
 Outline("UP/DOWN=Highlight option, LEFT/RIGHT=Change value, ENTER=Proceed, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=-1 Then screen=1
If go=1 
 If foc=9 Then screen=3
 If foc=10 Then screen=4
 If foc=11 Then screen=5 
 If foc=12 Then SaveOptions() : screen=1 
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 3. DISPLAY OPTIONS -------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function DisplayOptions()
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
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  If foc<12 And MouseDown(1)=0 Then foc=12 : keytim=10 
	  If foc=12 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	oldRes=optGameRes
	If gotim>20 And keytim=0
	 ;highlight options
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>12 Then foc=1
	 If foc<1 Then foc=12
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then optGameRes=optGameRes-1
	  If foc=2 Then optMenuRes=optMenuRes-1
	  If foc=3 Then optMeters=optMeters-1
	  If foc=4 Then optTags=optTags-1
	  If foc=5 Then optFX=optFX-1
	  If foc=6 Then optGore=optGore-1
      If foc=7 Then optLimbs=optLimbs-1
      If foc=8 Then optCrowdAnim=optCrowdAnim-1
      If foc=9 Then optVideo=optVideo-1
      If foc=10 Then optGhost=optGhost-1
      If foc=11 Then optAtmos=optAtmos-1
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then optGameRes=optGameRes+1
	  If foc=2 Then optMenuRes=optMenuRes+1
	  If foc=3 Then optMeters=optMeters+1
	  If foc=4 Then optTags=optTags+1
	  If foc=5 Then optFX=optFX+1
	  If foc=6 Then optGore=optGore+1
      If foc=7 Then optLimbs=optLimbs+1
      If foc=8 Then optCrowdAnim=optCrowdAnim+1
      If foc=9 Then optVideo=optVideo+1
      If foc=10 Then optGhost=optGhost+1
      If foc=11 Then optAtmos=optAtmos+1
	 EndIf
	EndIf
	;check limits
	If optGameRes<2 Then optGameRes=2 
	If optGameRes>5 Then optGameRes=5
	If optGameRes<>oldRes Then optMenuRes=optGameRes
	If optMenuRes<2 Then optMenuRes=2 
	If optMenuRes>5 Then optMenuRes=5
	If optMeters<0 Then optMeters=1
	If optMeters>1 Then optMeters=0 
	If optTags<0 Then optTags=0
	If optTags>2 Then optTags=2
	If optFX<0 Then optFX=0
	If optFX>3 Then optFX=3 
	If optGore<0 Then optGore=1
	If optGore>1 Then optGore=0
	If optLimbs<0 Then optLimbs=0
	If optLimbs>2 Then optLimbs=2 
	If optCrowdAnim<0 Then optCrowdAnim=1
	If optCrowdAnim>1 Then optCrowdAnim=0	
	If optVideo<0 Then optVideo=1
	If optVideo>1 Then optVideo=0
	If optGhost<0 Then optGhost=0
	If optGhost>2 Then optGhost=2 
	If optAtmos<0 Then optAtmos=1
	If optAtmos>1 Then optAtmos=0 
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;options
 SetFont font(1)
 x=400 : y=140
 DrawOption(1,400,y,"Game Resolution",textResX$(optGameRes)+" x "+textResY$(optGameRes),1,0)
 DrawOption(2,400,y+30,"Menu Resolution",textResX$(optMenuRes)+" x "+textResY$(optMenuRes),1,0)
 DrawOption(3,400,y+65,"Health Meters",textOnOff$(optMeters),1,0)
 DrawOption(4,400,y+95,"Overhead Labels",textTags$(optTags),1,0)
 DrawOption(5,400,y+130,"Particle Effects",textFX$(optFX),1,0)
 DrawOption(6,400,y+160,"Gore",textOnOff$(optGore),1,0)
 DrawOption(7,400,y+190,"Limb Loss",textLimbs$(optLimbs),1,0)
 DrawOption(8,400,y+225,"Crowd Movement",textAnim$(optCrowdAnim),1,0)
 DrawOption(9,400,y+255,"Video Screens",textAnim$(optVideo),1,0)
 DrawOption(10,400,y+285,"Scenery Ghosting",textGhost$(optGhost),1,0)
 DrawOption(11,400,y+315,"Fog Effect",textOnOff$(optAtmos),1,0)
 DrawOption(12,400,550,"<<< SAVE & EXIT <<<","",0,0)
 ;inst
 SetFont font(1)
 Outline("UP/DOWN=Highlight option, LEFT/RIGHT=Change value, ENTER=Proceed, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=1 Then SaveOptions()
screen=2
End Function

;//////////////////////////////////////////////////////////////////////////////
;---------------------------- 4. REDEFINE KEYS --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function RedefineKeys()
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
foc=1 : oldfoc=foc : screenCall=0
go=0 : gotim=0 : keytim=10
While go=0

	If screenCall=0 Then Cls
	
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
	If gotim>20 And keytim=0 And screenCall=0 
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc=8 And keytim=0 Then go=1 
	 EndIf
	EndIf
	
	;CONFIGURATION
	If gotim>20 And keytim=0 And screenCall=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>8 Then foc=1
	 If foc<1 Then foc=8
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  ;enter new command
	  If foc=<6
	   PlaySound sMenuBrowse : keytim=10
	   callX=MouseX() : callY=MouseY()
	   screenCall=foc
	  EndIf
	  ;restore defaults
	  If foc=7
	   PlaySound sSmashWire : keytim=10
	   keyAttack=30 : keyGrab=31 : keySwitch=29
       keyRun=44 : keyPick=45 : keyTaunt=57
	  EndIf
	 EndIf
	EndIf
	
	;INPUT DELAYS
    If screenCall>0 And keytim=0
     If screenCall=1 Then keyAttack=AssignKey(keyAttack) ;attack
     If screenCall=2 Then keyGrab=AssignKey(keyGrab) ;grab
     If screenCall=3 Then keySwitch=AssignKey(keySwitch) ;switch
     If screenCall=4 Then keyRun=AssignKey(keyRun) ;run
     If screenCall=5 Then keyPick=AssignKey(keyPick) ;pick-up
     If screenCall=6 Then keyTaunt=AssignKey(keyTaunt) ;taunt
    EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;lock mouse
 If screenCall>0 Then MoveMouse callX,callY
 ;options
 SetFont font(1)
 y=140
 DrawOption(1,400,y,"Attack",Key$(keyAttack),1,0)
 DrawOption(2,400,y+35,"Grapple",Key$(keyGrab),1,0)
 DrawOption(3,400,y+70,"Switch Focus",Key$(keySwitch),1,0)
 DrawOption(4,400,y+110,"Run",Key$(keyRun),1,0)
 DrawOption(5,400,y+145,"Pick Up",Key$(keyPick),1,0)
 DrawOption(6,400,y+180,"Taunt/Pin",Key$(keyTaunt),1,0)
 DrawOption(7,400,y+225,"RESTORE DEFAULTS","",0,0)
 DrawOption(8,400,550,"SAVE & EXIT","",0,0)
 ;input overrides
 If screenCall=1 Then DrawOption(-1,400,y,"Press New Key",">>> <<<",1,0)
 If screenCall=2 Then DrawOption(-1,400,y+35,"Press New Key",">>> <<<",1,0)
 If screenCall=3 Then DrawOption(-1,400,y+70,"Press New Key",">>> <<<",1,0)
 If screenCall=4 Then DrawOption(-1,400,y+110,"Press New Key",">>> <<<",1,0)
 If screenCall=5 Then DrawOption(-1,400,y+145,"Press New Key",">>> <<<",1,0)
 If screenCall=6 Then DrawOption(-1,400,y+180,"Press New Key",">>> <<<",1,0)
 ;inst
 SetFont font(1)
 Outline("UP/DOWN=Select action, ENTER=Change command, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=1 Then SaveOptions()
screen=2
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 5. REDEFINE GAMEPAD ------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function RedefineGamepad()
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
foc=1 : oldfoc=foc : screenCall=0
go=0 : gotim=0 : keytim=10
While go=0

	If screenCall=0 Then Cls
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
	If gotim>20 And keytim=0 And screenCall=0 
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc=8 And keytim=0 Then go=1 
	 EndIf
	EndIf
	
	;CONFIGURATION
	If gotim>20 And keytim=0 And screenCall=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>8 Then foc=1
	 If foc<1 Then foc=8
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  ;enter new command
	  If foc=<6
	   PlaySound sMenuBrowse : keytim=10
	   callX=MouseX() : callY=MouseY()
	   screenCall=foc
	  EndIf
	  ;restore defaults
	  If foc=7
	   PlaySound sSmashWire : keytim=10
	   buttAttack=4 : buttGrab=5 : buttSwitch=3
       buttRun=1 : buttPick=2 : buttTaunt=6
	  EndIf
	 EndIf
	 ;forfeit button
	 If (KeyDown(14) Or KeyDown(211)) And foc=<6
	  PlaySound sVoid : keytim=10 
	  If foc=1 Then buttAttack=0
	  If foc=2 Then buttGrab=0
	  If foc=3 Then buttSwitch=0
	  If foc=4 Then buttRun=0
	  If foc=5 Then buttPick=0
	  If foc=6 Then buttTaunt=0
	 EndIf 
	EndIf
	
	;INPUT DELAYS
    If screenCall>0 And keytim=0
     If screenCall=1 Then buttAttack=AssignButton(buttAttack) ;attack
     If screenCall=2 Then buttGrab=AssignButton(buttGrab) ;grab
     If screenCall=3 Then buttSwitch=AssignButton(buttSwitch) ;switch
     If screenCall=4 Then buttRun=AssignButton(buttRun) ;run
     If screenCall=5 Then buttPick=AssignButton(buttPick) ;pick-up
     If screenCall=6 Then buttTaunt=AssignButton(buttTaunt) ;taunt
    EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;lock mouse
 If screenCall>0 Then MoveMouse callX,callY
 ;options
 SetFont font(1)
 y=140
 DrawOption(1,400,y,"Attack","Button "+buttAttack,1,0)
 DrawOption(2,400,y+35,"Grapple","Button "+buttGrab,1,0)
 DrawOption(3,400,y+70,"Switch Focus","Button "+buttSwitch,1,0)
 DrawOption(4,400,y+110,"Run","Button "+buttRun,1,0)
 DrawOption(5,400,y+145,"Pick Up","Button "+buttPick,1,0)
 DrawOption(6,400,y+180,"Taunt/Pin","Button "+buttTaunt,1,0)
 DrawOption(7,400,y+225,"RESTORE DEFAULTS","",0,0)
 DrawOption(8,400,550,"SAVE & EXIT","",0,0)
 ;input overrides
 If screenCall=1 Then DrawOption(-1,400,y,"Press New Button",">>> <<<",1,0)
 If screenCall=2 Then DrawOption(-1,400,y+35,"Press New Button",">>> <<<",1,0)
 If screenCall=3 Then DrawOption(-1,400,y+70,"Press New Button",">>> <<<",1,0)
 If screenCall=4 Then DrawOption(-1,400,y+110,"Press New Button",">>> <<<",1,0)
 If screenCall=5 Then DrawOption(-1,400,y+145,"Press New Button",">>> <<<",1,0)
 If screenCall=6 Then DrawOption(-1,400,y+180,"Press New Button",">>> <<<",1,0)
 ;inst
 SetFont font(1)
 Outline("UP/DOWN=Select action, ENTER=Change command, BACKSPACE=Forfeit command, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=1 Then SaveOptions()
screen=2
End Function

;//////////////////////////////////////////////////////////////////////////////
;----------------------------- 10. MATCH SETUP --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function MatchSetup()
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
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  If foc<13 And MouseDown(1)=0 Then foc=13 : keytim=10 
	  If foc=13 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	oldMatch=matchPreset 
	If keytim=0
	 ;highlight options
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>13 Then foc=1
	 If foc<1 Then foc=13
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then matchPreset=matchPreset-1
	  If foc=2 Then no_wrestlers=no_wrestlers-1 : keytim=4
	  If foc=3 Then no_refs=no_refs-1 : keytim=4
	  If foc=4 Then matchType=matchType-1
	  If foc=5 Then matchRules=matchRules-1
	  If foc=6 Then matchTeams=matchTeams-1
      If foc=7 Then matchPins=matchPins-1
      If foc=8 Then matchSubs=matchSubs-1
      If foc=9 Then matchKOs=matchKOs-1
      If foc=10 Then matchBlood=matchBlood-1
      If foc=11 Then matchOTT=matchOTT-1
      If foc=12 Then matchTimeLim=matchTimeLim-1 : keytim=4
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then matchPreset=matchPreset+1
	  If foc=2 And (no_wrestlers+no_refs)<optPlayLim And (no_wrestlers+no_refs)<fedSize(fed) Then no_wrestlers=no_wrestlers+1 : keytim=4
	  If foc=3 And (no_wrestlers+no_refs)<optPlayLim And (no_wrestlers+no_refs)<fedSize(fed) Then no_refs=no_refs+1 : keytim=4
	  If foc=4 Then matchType=matchType+1
	  If foc=5 Then matchRules=matchRules+1
	  If foc=6 Then matchTeams=matchTeams+1
      If foc=7 Then matchPins=matchPins+1
      If foc=8 Then matchSubs=matchSubs+1
      If foc=9 Then matchKOs=matchKOs+1
      If foc=10 Then matchBlood=matchBlood+1
      If foc=11 Then matchOTT=matchOTT+1
      If foc=12 Then matchTimeLim=matchTimeLim+1 : keytim=4
	 EndIf
	EndIf
	;check limits
	If matchPreset>no_matches Then matchPreset=0
	If matchPreset<0 Then matchPreset=no_matches
	If matchPreset<>oldMatch Then LoadMatch(matchPreset) 
	If no_wrestlers>fedSize(fed) Then no_wrestlers=fedSize(fed) 
	If no_wrestlers>optPlayLim Then no_wrestlers=optPlayLim  
	If no_wrestlers<2 Then no_wrestlers=2
	If matchTeams=2 And no_wrestlers>10 Then no_wrestlers=10
	If no_refs>5 Then no_refs=5
	If no_refs>fedSize(fed)-2 Then no_refs=fedSize(fed)-2 
	If no_refs<0 Then no_refs=0
	If matchType<0 Then matchType=5
	If matchType>5 Then matchType=0
	If matchRules<0 Then matchRules=0
	If matchRules>2 Then matchRules=2
	If matchTeams<-1 Then matchTeams=-1
	If matchTeams>2 Then matchTeams=2
	If matchTeams>1 And matchOfficial=0 Then matchTeams=1
	If no_wrestlers=<2 And matchTeams>0 Then matchTeams=0
	If matchPins<0 Then matchPins=1
	If matchPins>1 Then matchPins=0
	If matchSubs<0 Then matchSubs=1
    If matchSubs>1 Then matchSubs=0	
	If matchKOs<0 Then matchKOs=1
	If matchKOs>1 Then matchKOs=0	
	If matchBlood<0 Then matchBlood=1
	If matchBlood>1 Then matchBlood=0
	If matchOTT<0 Then matchOTT=1
	If matchOTT>1 Then matchOTT=0
	If matchType<>5 Then matchKOs=0 : matchBlood=0 : matchOTT=0	
	If matchTimeLim<0 Then matchTimeLim=0
	If matchTimeLim>60 Then matchTimeLim=60	
	If (matchType=3 Or matchType=4) And matchTimeLim<5 Then matchTimeLim=5
	If matchTeams=-1 Then matchTimeLim=0
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)
 ;options
 SetFont font(1)
 x=400 : y=140
 DrawOption(1,400,y,"Preset",textMatch$(matchPreset),1,0)
 DrawOption(2,400,y+40,"No. Wrestlers",no_wrestlers,1,0)
 DrawOption(3,400,y+70,"No. Referees",no_refs,1,0)
 DrawOption(4,400,y+100,"Aim",textAim$(matchType),1,0)
 DrawOption(5,400,y+130,"Rules",textRules$(matchRules),1,0)
 If matchTeams=-1 Then namer$="Sequential" Else namer$=textTeams$(matchTeams)
 DrawOption(6,400,y+160,"Format",namer$,1,0)
 DrawOption(7,400,y+195,"Pins",textCount$(matchPins),1,0)
 DrawOption(8,400,y+225,"Submissions",textCount$(matchSubs),1,0)
 DrawOption(9,400,y+255,"Knock-Out's",textCount$(matchKOs),1,0)
 DrawOption(10,400,y+285,"Bleeding",textCount$(matchBlood),1,0)
 DrawOption(11,400,y+315,"Ring-Out's",textCount$(matchOTT),1,0)
 If matchTimeLim=0 Then namer$="None" Else namer$=matchTimeLim+" mins"
 DrawOption(12,400,y+350,"Time Limit",namer$,1,0)
 DrawOption(13,400,550,">>> PROCEED >>>","",0,0)
 ;warnings
 If foc=9 And matchType<>5 Then Outline("(Only applicable in 'Elimination' conditions)",rX#(400),rY#(y+268),0,0,0,200,100,100)
 If foc=10 And matchType<>5 Then Outline("(Only applicable in 'Elimination' conditions)",rX#(400),rY#(y+298),0,0,0,200,100,100)
 If foc=11 And matchType<>5 Then Outline("(Only applicable in 'Elimination' conditions)",rX#(400),rY#(y+328),0,0,0,200,100,100)
 ;inst
 SetFont font(1)
 Outline("UP/DOWN=Highlight option, LEFT/RIGHT=Change value, ENTER=Proceed, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=-1 Then screen=14
If go=1 Then screen=11
End Function

;//////////////////////////////////////////////////////////////////////////////
;---------------------------- 11. GIMMICK SETUP -------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function GimmickSetup()
;predict cage
If matchPreset=17 And matchCage=0 Then matchCage=1
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
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc<12 And MouseDown(1)=0 Then foc=12 : keytim=10
	  If foc=12 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	If keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>12 Then foc=1
	 If foc<1 Then foc=12
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then matchCage=matchCage-1
	  If foc=2 Then matchRopes=matchRopes-1
	  If foc=3 Then matchBlastTim=matchBlastTim-1 : keytim=4
	  If foc=4 Then no_items=no_items-1 : keytim=3
	  If foc=5 Then itemSelection=itemSelection-1
	  If foc=6 Then itemLayout=itemLayout-1
	  If foc=7 Then no_weaps=no_weaps-1 : keytim=3
	  If foc=8 Then weapSelection=weapSelection-1
	  If foc=9 Then weapLayout=weapLayout-1
	  If foc=10
	   Repeat  
	    matchChamps=matchChamps-1
	    If matchChamps<0 Then matchChamps=6
	   Until ChampLogic()=1
      EndIf
	  If foc=11
	   Repeat  
	    matchPromo=matchPromo-1
	    If matchPromo<0 Then matchPromo=no_promos
	   Until promoLocked(matchPromo)=0
	  EndIf
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then matchCage=matchCage+1
	  If foc=2 Then matchRopes=matchRopes+1
	  If foc=3 Then matchBlastTim=matchBlastTim+1 : keytim=4
	  If foc=4 Then no_items=no_items+1 : keytim=3
	  If foc=5 Then itemSelection=itemSelection+1
	  If foc=6 Then itemLayout=itemLayout+1
	  If foc=7 Then no_weaps=no_weaps+1 : keytim=3
	  If foc=8 Then weapSelection=weapSelection+1
	  If foc=9 Then weapLayout=weapLayout+1
	  If foc=10 
	   Repeat  
	    matchChamps=matchChamps+1
	    If matchChamps>6 Then matchChamps=0
	   Until ChampLogic()=1
      EndIf
	  If foc=11
	   Repeat  
	    matchPromo=matchPromo+1
	    If matchPromo>no_promos Then matchPromo=0
	   Until promoLocked(matchPromo)=0
	  EndIf
	 EndIf 
	 ;promo shortcuts
	 If foc=11 
	  If KeyDown(57) 
	   PlaySound sMenuBrowse : keytim=10
	   Repeat  
	    matchPromo=Rnd(1,no_promos)
	   Until promoLocked(matchPromo)=0
	  EndIf
	  If KeyDown(14) Then matchPromo=0 : PlaySound sMenuBrowse : keytim=10
	 EndIf
	 ;lock promos
	 If KeyDown(29) And KeyDown(19)
	  PlaySound sSmashWire : keytim=10
	  For count=1 To no_promos
	   promoLocked(count)=1
	  Next
	  promoLocked(16)=0
	 EndIf
	 ;unlock promos
	 If KeyDown(56) And KeyDown(25)
	  PlaySound sCrowd(8) : keytim=10
	  For count=1 To no_promos
	   If count<>48 And count<>49 Then promoLocked(count)=0
	  Next
	 EndIf 
	EndIf
	;check limits
	If matchCage<0 Then matchCage=4
	If matchCage>4 Then matchCage=0
	If matchRopes<0 Then matchRopes=3
	If matchRopes>3 Then matchRopes=0
	If matchBlastTim<0 Then matchBlastTim=0
	If matchTimeLim>0 And matchBlastTim>matchTimeLim Then matchBlastTim=matchTimeLim
	If matchBlastTim>60 Then matchBlastTim=60
	If no_items<0 Then no_items=0
	If no_items>itemLimit Then no_items=itemLimit
	If itemSelection<0 Then itemSelection=itemList+2 
	If itemSelection>itemList+2 Then itemSelection=0
	If itemLayout<0 Then itemLayout=4
	If itemLayout>4 Then itemLayout=0 
	If no_weaps<0 Then no_weaps=0
	If no_weaps>weapLimit Then no_weaps=weapLimit
	If weapSelection<0 Then weapSelection=weapList+2
	If weapSelection>weapList+2  Then weapSelection=0 
	If weapLayout<0 Then weapLayout=4
	If weapLayout>4 Then weapLayout=0 
	If matchChamps<0 Then matchChamps=6
	If matchChamps>6 Then matchChamps=0
	If fedLocked(fed) Then matchChamps=0 
	If matchPromo<0 Then matchPromo=no_promos
	If matchPromo>no_promos Then matchPromo=0
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)
 ;options
 SetFont font(1)
 y=140
 DrawOption(1,400,y,"Cage",textCage$(matchCage),1,0)
 DrawOption(2,400,y+30,"Rope Type",textRopeType$(matchRopes),1,0)
 If matchBlastTim=0 Then namer$="None" Else namer$=matchBlastTim+" mins"
 DrawOption(3,400,y+60,"Blast Time",namer$,1,0)
 DrawOption(4,400,y+100,"No. Items",no_items,1,0)
 If itemSelection>2 Then namer$=iName$(itemSelection-2)+"'s" Else namer$=textSelection$(itemSelection)
 DrawOption(5,400,y+130,"Item Selection",namer$,1,0)
 DrawOption(6,400,y+160,"Item Layout",textLayout$(itemLayout),1,0) 
 DrawOption(7,400,y+200,"No. Weapons",no_weaps,1,0)
 If weapSelection>2 Then namer$=weapName$(weapSelection-2)+"'s" Else namer$=textSelection$(weapSelection)
 DrawOption(8,400,y+230,"Weapon Selection",namer$,1,0)
 DrawOption(9,400,y+260,"Weapon Layout",textLayout$(weapLayout),1,0)
 DrawOption(10,400,y+300,"Championships",textChamps$(matchChamps),1,0)
 If matchPromo>0 Then namer$=matchPromo+". "+promoTitle$(matchPromo) Else namer$="None"
 DrawOption(11,400,y+330,"Promo",namer$,1,0)
 DrawOption(12,400,550,">>> PROCEED >>>","",0,0)
 ;promo description
 SetFont font(1)
 If foc=11 And matchPromo>0 Then Outline(promoExplain$(matchPromo),rX#(400),rY#(y+342),50,30,10,255,200,150)
 ;advice
 If foc=10 And fedLocked(fed) Then Outline("(Unlock promotion to access titles)",rX#(400),rY#(y+312),0,0,0,200,100,100)
 counter=-2
 For count=1 To no_promos
  If promoLocked(count) Then counter=counter+1
 Next
 If foc=11 And matchPromo=0 And counter>0 Then Outline("("+counter+" promos left to unlock!)",rX#(400),rY#(y+342),0,0,0,200,100,100) 
 ;inst
 Outline("UP/DOWN=Highlight option, LEFT/RIGHT=Change value, ENTER=Proceed, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=-1 Then screen=10
If go=1 Then screen=12
End Function

;//////////////////////////////////////////////////////////////////////////////
;----------------------------- 12. ARENA SETUP --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function ArenaSetup()
;consider atmos preference
If optAtmos=0 Then arenaAtmos=0
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
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc<9 And MouseDown(1)=0 Then foc=9 : keytim=10
	  If foc=9 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	If keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>9 Then foc=1
	 If foc<1 Then foc=9
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then arenaPreset=arenaPreset-1
	  If foc=2 Then arenaCrowd=arenaCrowd-1 
	  If foc=3 Then arenaAtmos=arenaAtmos-1
	  If foc=4 Then arenaLight=arenaLight-1
	  If foc=5 Then arenaRopes=arenaRopes-1
	  If foc=6 Then arenaApron=arenaApron-1
	  If foc=7 Then arenaCanvas=arenaCanvas-1
	  If foc=8 Then arenaMat=arenaMat-1
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  keytim=6 : PlaySound sMenuBrowse
	  If foc=1 Then arenaPreset=arenaPreset+1
	  If foc=2 Then arenaCrowd=arenaCrowd+1 
	  If foc=3 Then arenaAtmos=arenaAtmos+1
	  If foc=4 Then arenaLight=arenaLight+1
	  If foc=5 Then arenaRopes=arenaRopes+1
	  If foc=6 Then arenaApron=arenaApron+1
	  If foc=7 Then arenaCanvas=arenaCanvas+1
	  If foc=8 Then arenaMat=arenaMat+1
	 EndIf
	EndIf
	;check limits
	If arenaPreset<0 Then arenaPreset=no_arenas
	If arenaPreset>no_arenas Then arenaPreset=0 
	If arenaCrowd<0 Then arenaCrowd=0
	If arenaCrowd>3 Then arenaCrowd=3 
	If arenaAtmos<0 Then arenaAtmos=9
	If arenaAtmos>9 Then arenaAtmos=0 
	If arenaLight<0 Then arenaLight=4
	If arenaLight>4 Then arenaLight=0    
	If arenaRopes<1 Then arenaRopes=9
	If arenaRopes>9 Then arenaRopes=1
	If matchRopes>0 Then arenaRopes=0
	If arenaApron<0 Then arenaApron=5
	If arenaApron>5 Then arenaApron=0 
	If arenaCanvas<1 Then arenaCanvas=5
	If arenaCanvas>5 Then arenaCanvas=1
	If arenaMat<0 Then arenaMat=3
	If arenaMat>3 Then arenaMat=0    
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)
 ;options
 SetFont font(1) 
 y=140
 DrawOption(1,400,y,"Arena",textArena$(arenaPreset),1,0)
 DrawOption(2,400,y+30,"Attendance",textCrowd$(arenaCrowd),1,0)
 DrawOption(3,400,y+65,"Atmosphere",textAtmos$(arenaAtmos),1,0)
 DrawOption(4,400,y+95,"Lighting",textLight$(arenaLight),1,0)
 If arenaPreset=>1 And arenaPreset=<17 Then DrawImage gArena(arenaPreset),rX#(400),rY#(y+175) Else DrawImage gArena(0),rX#(400),rY#(y+175)
 DrawOption(5,400,y+255,"Ropes",textRopes$(arenaRopes),1,0)
 DrawOption(6,400,y+285,"Apron",textApron$(arenaApron),1,0)
 DrawOption(7,400,y+315,"Canvas",textCanvas$(arenaCanvas),1,0)
 DrawOption(8,400,y+345,"Matting",textMat$(arenaMat),1,0)
 DrawOption(9,400,550,">>> PROCEED >>>","",0,0)
 ;inst
 SetFont font(1)
 Outline("UP/DOWN=Highlight option, LEFT/RIGHT=Change value, ENTER=Proceed, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=-1 Then screen=11
If go=1 Then screen=13
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 14. ROSTER SELECTION -----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function SelectRoster()
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
no_plays=1 : no_slots=9
foc=1 : oldfoc=foc : confirmer=0
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
	flashTim=flashTim+1
	If flashTim>70 Then flashTim=0  
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0 And screenCall=0
	 ;exit
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc=0 Then go=-1 
	  If foc=>1 And foc=<9 Then go=1
	 EndIf
	 ;block editing access
	 If go=1 And screenAgenda=0 
	  If fedLocked(foc)
	   go=0
	   If keytim=0 Then PlaySound sVoid : keytim=10
	  EndIf
	 EndIf
	 ;block exhibition access
	 If go=1 And screenAgenda=1 
	  If foc=8 Or foc=9
	   go=0
	   If keytim=0 Then PlaySound sVoid : keytim=10
	  EndIf
	 EndIf 
	EndIf
	
	;CONFIGURATION
	screenCall=0
	If gotim>20 And keytim=0
	 ;explore grid
	 If (KeyDown(203) Or JoyXDir()=-1) And FocComplies(1,3,3) Then foc=foc-1 : keytim=6 
	 If (KeyDown(205) Or JoyXDir()=1) And FocComplies(2,3,3) Then foc=foc+1 : keytim=6 
	 If (KeyDown(200) Or JoyYDir()=-1) And FocComplies(3,3,3) Then foc=foc-3 : keytim=6 
	 If (KeyDown(208) Or JoyYDir()=1) And FocComplies(4,3,3)
	  If screenAgenda=0 And foc=>7 And foc=<9 Then foc=10 Else foc=foc+3
	  keytim=6 
	 EndIf
	 If screenAgenda=0 Then limit=10 Else limit=9
     If foc<0 Then foc=limit	
     If foc>limit Then foc=0	
     ;edit properties
     If screenAgenda=0 And foc=>1 And foc=<9 
      If fedLocked(foc)=0
       If KeyDown(16) Then fedBank(foc)=fedBank(foc)-10000 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(17) Then fedBank(foc)=fedBank(foc)+10000 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(30) Then fedPop(foc)=fedPop(foc)-1 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(31) Then fedPop(foc)=fedPop(foc)+1 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(44) Then fedRep(foc)=fedRep(foc)-1 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(45) Then fedRep(foc)=fedRep(foc)+1 : PlaySound sMenuBrowse : keytim=3
	   CheckFedLimits(foc)
	   ;name calls
	   If KeyDown(14) Then screenCall=1 : PlaySound sMenuSelect : keytim=10
	   If KeyDown(211) Then screenCall=2 : PlaySound sMenuSelect : keytim=10
	  EndIf
	  ;unlock cheat
	  If KeyDown(56) And KeyDown(33) And fedLocked(foc) 
	   PlaySound sCrowd(2) : keytim=10 
	   fedLocked(foc)=0
	  EndIf
	 EndIf
	 ;reset locks cheat
	 If screenAgenda=0 And KeyDown(29) And KeyDown(19)
	  PlaySound sSmashWire : keytim=10
	  For count=1 To 9
	   fedLocked(count)=1
	  Next
	 EndIf 
	 ;reset contracts cheat
	 If screenAgenda=0 And KeyDown(56) And KeyDown(46)
	  PlaySound sPaper : keytim=10
      For char=1 To no_chars
       GetNewContract(char)
       charHealth(char)=100 : charInjured(char)=0
	   charVacant(char)=0 : charTrainCourse(char)=0
	   For limb=1 To 40
        charLimb(char,limb)=1
       Next 
      Next
	 EndIf
	 ;jumble rosters cheat
	 If screenAgenda=0 And KeyDown(56) And KeyDown(36)
	  PlaySound sCrowd(8) : keytim=10
	  For fed=1 To 9
       fedSize(fed)=0
       For count=1 To 40
        fedRoster(fed,count)=0
       Next
      Next
      For char=1 To no_chars
       Repeat
        charFed(char)=Rnd(1,9)
        If charFed(char)=8 And fedSize(8)=>8 Then charFed(char)=Rnd(1,7)
        If charFed(char)=9 And fedSize(9)=>4 Then charFed(char)=Rnd(1,7)
       Until fedSize(charFed(char))<optFedLim
       fedSize(charFed(char))=fedSize(charFed(char))+1
       fedRoster(charFed(char),fedSize(charFed(char)))=char
       If fedSize(charFed(char))=1 Then fedBooker(charFed(char))=char
       If fedSize(charFed(char))=2 And charFed(char)=<6 Then fedChampWorld(charFed(char))=char
       If fedSize(charFed(char))=3 And charFed(char)=<6 Then fedChampInter(charFed(char))=char
       If fedSize(charFed(char))=4 And charFed(char)=<6 Then fedChampTag(charFed(char),1)=char
       If fedSize(charFed(char))=5 And charFed(char)=<6 Then fedChampTag(charFed(char),2)=char
       GetNewContract(char)
      Next
	 EndIf
	EndIf 
	
	;RESETTING PROCESS
	If screenAgenda=0 And foc=10 And gotim>20 And keytim=0
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  ;confirm?
	  If confirmer=0 Then confirmer=1 : PlaySound sMenuGo : keytim=10
	  ;execute
	  If confirmer=1 And keytim=0
	   Loader("Please Wait","Resetting World")
	   PlaySound sMenuBrowse : keytim=10
	   ;preserve careers
	   For count=1 To 3
	    gamFormat(count)=charFed(gamChar(count))
	   Next
	   ;restore default data
	   LoadWorld()
	   LoadChars()
	   ;restore careers
	   For count=1 To 3  
	    If gamChar(count)>0 And charFed(gamChar(count))<>gamFormat(count) 
	     Trade(gamChar(count),gamFormat(count))
	     gamFormat(count)=1
	    EndIf
	   Next
	   ;save changes
	   SaveWorld()
	   SaveChars()
	   PlaySound sSmashWire : confirmer=0
	  EndIf
	 EndIf  
	EndIf
	;reset confirmer
	If foc<>10 Then confirmer=0
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;profile
 If foc=>1 And foc=<9 
  DrawFedProfile(foc,-1,-1)
 EndIf
 ;fed boxes
 x=200 : y=200
 If screenAgenda=0 Then y=y-8
 For cyc=1 To 9
  SetFont font(1)
  If MouseX()>rX#(x)-90 And MouseX()<rX#(x)+90 And MouseY()=>rY#(y)-55 And MouseY()<rY#(y)+55 Then foc=cyc
  DrawImage gFed(cyc),rX#(x),rY#(y)-11
  If fedLocked(cyc) And screenAgenda=0 
   DrawImage gFed(0),rX#(x),rY#(y)-11
   If foc=cyc And flashTim>20
    DrawImage gBelt(1),rX#(x),rY#(y)-11
    If cyc=<6 Then Outline("UNLOCK BY WINNING",rX#(x),rY#(y)-26,50,25,50,220,150,250)
    If cyc=<6 Then Outline("THE WORLD TITLE!",rX#(x),rY#(y)+4,50,25,50,220,150,250)
    If cyc=>7 Then Outline("UNLOCK BY WINNING",rX#(x),rY#(y)-26,50,25,50,220,150,250)
    If cyc=>7 Then Outline("ALL WORLD TITLES!",rX#(x),rY#(y)+4,50,25,50,220,150,250)
   EndIf
  EndIf
  If screenAgenda=1 And cyc=>8 Then DrawImage gFed(0),rX#(x),rY#(y)-11
  If foc=cyc Then DrawImage gFedMenu(1),rX#(x),rY#(y) Else DrawImage gFedMenu(2),rX#(x),rY#(y)
  r=160 : g=160 : b=160
  If foc=cyc Then r=255 : g=Rnd(175,225) : b=100
  If screenAgenda=0 Then namer$=fedName$(cyc)+" ("+fedShortName$(cyc)+")" Else namer$=fedName$(cyc)
  If StringWidth(namer$)>180 Then SetFont font(0)
  Outline(namer$,rX#(x),rY#(y)+40,0,0,0,r,g,b)
  If fedLocked(cyc) And screenAgenda=0 Then DrawImage gMenu(0),rX#(x),rY#(y)+40
  If screenAgenda=1 And cyc=>8 Then DrawImage gMenu(0),rX#(x),rY#(y)+40
  If screenAgenda=2 And charFed(gamChar(slot))=cyc Then DrawImage charPhoto(gamChar(slot)),rX#(x)-70,rY#(y)-30
  If foc=cyc Then showX=x : showY=y
  x=x+200
  If x>700 Then y=y+120 : x=200
 Next
 ;name changes
 If screenCall>0
  x=rX#(showX) : y=rY#(showY)
  DrawImage gFedMenu(1),x,y : Flip
  Locate x-75,y+33 : Color 50,50,50
  If screenCall=1 ;long
   oldTitle$=fedName$(foc)
   fedName$(foc)=Left$(Input$(""),30)
   If fedName$(foc)="" Then fedName$(foc)=oldTitle$
  EndIf 
  If screenCall=2 ;short
   oldTitle$=fedShortName$(foc)
   fedShortName$(foc)=Left$(Input$(""),30)
   If fedShortName$(foc)="" Then fedShortName$(foc)=oldTitle$
  EndIf
 EndIf
 ;roster summary
 If KeyDown(57) Or JoyDown(2) Or MouseDown(2)
  If foc=>1 And foc=<9
   x=125 : ranger=fedSize(foc)
   If ranger>29 Then ranger=29
   For count=1 To ranger
    DrawImage charPhoto(fedRoster(foc,count)),rX#(x),rY#(500)
    x=x+20 
   Next
  EndIf
 EndIf
 ;options
 If confirmer=1 Then namer$="ARE YOU SURE?" Else namer$="RESET WORLD"
 If screenAgenda=0 Then DrawOption(10,400,515,namer$,"",0,0)
 DrawOption(0,400,550,"<<< EXIT <<<","",0,0)
 ;inst
 SetFont font(1)
 If screenAgenda<>0 Then Outline("CURSORS=Highlight promotion, SPACE=Preview roster, ENTER=Access roster, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 If screenAgenda=0
  Outline("Q/W=Adjust bank, A/S=Adjust popularity, Z/X=Adjust reputation,",rX#(400),rY#(573),0,0,0,85,85,85)
  Outline("BACKSPACE=Change long name, DELETE=Change abbreviation",rX#(400),rY#(587),0,0,0,85,85,85)
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
If go=1 Then fed=foc
If screenAgenda=0 ;editing
 If go=-1 Then screen=1
 If go=1 Then screen=13
EndIf
If screenAgenda=1 ;playing
 If go=-1 Then screen=1
 If go=1 Then screen=10
EndIf
If screenAgenda=2 ;studying
 If go=-1 Then screen=15
 If go=1 Then screen=13
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------- 13. CHARACTER SELECTION ----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function SelectCharacter()
;assess player count
no_plays=no_wrestlers+no_refs
If screenAgenda<>1 Then no_plays=1
;reset selection status
For cyc=1 To optPlayLim
 pChar(cyc)=0
 pControl(cyc)=0
Next
For char=1 To no_chars
 charSelected(char)=-1
Next
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
cloner=0 : legendTim=0
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
	legendTim=legendTim-1
	If legendTim<0 Then legendTim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;leave (break cloning)
	 If KeyDown(1) And cloner=0 Then go=-1 
	 If (KeyDown(1) Or MouseDown(1)) And cloner>0 Then cloner=0 : PlaySound sMenuBack : keytim=10
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  If (foc=0 And CountSelected()=no_plays) Or (no_plays=1 And screenAgenda<>2) Then go=1
	  If foc=0 And screenAgenda<>1 Then go=-1
	  ;block access
	  If go=1 And screenAgenda=3 And foc>0 And (CharBusy(fedRoster(fed,foc))>0 Or fedRoster(fed,foc)=fedBooker(fed))
	   go=0
	   If keytim=0 Then PlaySound sVoid : keytim=10
	  EndIf
	 EndIf
	EndIf
	
	;assess roster
    no_slots=fedSize(fed)
    If screenAgenda=0 And fedSize(fed)<optFedLim And no_chars<optCharLim
     no_slots=fedSize(fed)+1
     charName$(no_chars+1)="~~~ NEW ~~~"
     fedRoster(fed,fedSize(fed)+1)=no_chars+1
    EndIf
    ;available controls
    keysSelected=0 : padSelected=0
    For cyc=1 To fedSize(fed)
     If charSelected(fedRoster(fed,cyc))=1 Then keysSelected=1
     If charSelected(fedRoster(fed,cyc))=2 Then padSelected=1
    Next
	
	;CONFIGURATION
	If gotim>20 And keytim=0
	 ;FIDDLES
	 If screenAgenda=0 And foc<fedSize(fed)+1
	  ;move talent
	  If fedSize(fed)>3 Or (fed=>8 And fedSize(fed)>1)
	   For count=1 To 9
	    If KeyDown(count+1) And fedSize(count)<optFedLim And fed<>count 
	     PlaySound sSwing : keytim=20
	     Trade(fedRoster(fed,foc),count)
	    EndIf
	   Next 
	  EndIf
	  ;change titles
	  If KeyDown(48) And CharBusy(fedRoster(fed,foc))=0 Then fedBooker(fed)=fedRoster(fed,foc) : PlaySound sPaper : keytim=10
	  If KeyDown(17) And fed=<6 Then fedChampWorld(fed)=fedRoster(fed,foc) : PlaySound sPaper : keytim=10
	  If KeyDown(23) And fed=<6 Then fedChampInter(fed)=fedRoster(fed,foc) : PlaySound sPaper : keytim=10
	  If KeyDown(20) And fed=<6 And fed<>5 Then fedChampTag(fed,1)=fedRoster(fed,foc) : PlaySound sPaper : keytim=10
	  If KeyDown(21) And fed=<6 And fed<>5 Then fedChampTag(fed,2)=fedRoster(fed,foc) : PlaySound sPaper : keytim=10
	  ;reset status
	  If KeyDown(14) 
	   PlaySound sSmashWire : keytim=10
	   charHealth(fedRoster(fed,foc))=100
	   charInjured(fedRoster(fed,foc))=0
	   charVacant(fedRoster(fed,foc))=0
	   For limb=1 To 40
        charLimb(fedRoster(fed,foc),limb)=1
       Next 
	  EndIf
	 EndIf 
	 ;SELECTION
	 ;browse slots
	 If (KeyDown(203) Or JoyXDir()=-1) And FocComplies(1,4,10) Then foc=foc-1 : keytim=6
	 If (KeyDown(205) Or JoyXDir()=1) And FocComplies(2,4,10) Then foc=foc+1 : keytim=6
	 If (KeyDown(200) Or JoyYDir()=-1) And FocComplies(3,4,10) Then foc=foc-4 : keytim=6
	 If (KeyDown(208) Or JoyYDir()=1) And FocComplies(4,4,10) Then foc=foc+4 : keytim=6
     If foc<0 Then foc=no_slots	
     If foc>no_slots Then foc=0
     If screenAgenda=1 And foc=0 And CountSelected()<no_plays Then foc=1	
	 ;select
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  If foc>0 And screenAgenda=1
	   For cyc=1 To no_plays
	    If pChar(cyc)=0 And charSelected(fedRoster(fed,foc))=-1 Then pChar(cyc)=fedRoster(fed,foc) : Exit
	   Next
	   If CountSelected()<no_plays Or charSelected(fedRoster(fed,foc))=>0 ;cycle options
	    PlaySound sMenuBrowse : keytim=10 : legendTim=50
	    charSelected(fedRoster(fed,foc))=charSelected(fedRoster(fed,foc))+1
	    If charSelected(fedRoster(fed,foc))=1 And keysSelected=1 Then charSelected(fedRoster(fed,foc))=2
	    If charSelected(fedRoster(fed,foc))=2 And padSelected=1 Then charSelected(fedRoster(fed,foc))=-1
	    If charSelected(fedRoster(fed,foc))>2 Then charSelected(fedRoster(fed,foc))=-1
	   EndIf
	  EndIf
	 EndIf
	 ;CLONING
	 If KeyDown(57) Or JoyDown(2) Or MouseDown(2) 
	  ;initiate
	  If foc=<fedSize(fed) And cloner=0 Then cloner=foc : PlaySound sMenuGo : keytim=6
	  ;cancel
	  If cloner>0 And cloner=foc And keytim=0 Then cloner=0 : PlaySound sMenuBack : keytim=6
	  ;execute
	  If cloner>0 And cloner<>foc And keytim=0
	   PlaySound sMenuGo : keytim=6
	   If screenAgenda=0 ;cloning
        CopyChar(fedRoster(fed,cloner),fedRoster(fed,foc))
        charPhoto(fedRoster(fed,foc))=CopyImage(charPhoto(fedRoster(fed,cloner))) 
        SaveImage(charPhoto(fedRoster(fed,foc)),"Data/Portraits/Photo"+Dig$(fedRoster(fed,foc),100)+".bmp")
       Else ;switching
        temp=fedRoster(fed,foc)
        fedRoster(fed,foc)=fedRoster(fed,cloner)
        fedRoster(fed,cloner)=temp
       EndIf
       cloner=0 
       ;increment roster?
       If foc=fedSize(fed)+1
 	    fedSize(fed)=foc : no_chars=no_chars+1
        If no_chars>optCharLim Then no_chars=optCharLim
        fedRoster(fed,foc)=no_chars
        charFed(no_chars)=fed
	    SaveWorld()
       EndIf
       If screenAgenda=0 Then SaveChars()
	  EndIf
	 EndIf
	EndIf
	
	;bind controls to player (or remove)
	For cyc=1 To no_plays
	 If charSelected(pChar(cyc))=-1 Then pChar(cyc)=0 
	 pControl(cyc)=charSelected(pChar(cyc))
	Next
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)
 ;stats
 If foc>0 And foc=<fedSize(fed)
  DrawProfile(fedRoster(fed,foc),-1,-1)
 EndIf
 If foc=0 And screenAgenda=2 Then DrawFedProfile(fed,-1,-1)
 ;CHARACTER BOXES
 SetFont font(1)
 x=120 : y=150
 sourceX=0 : sourceY=0
 targetX=0 : targetY=0
 For cyc=1 To no_slots
  ;mouse hotspot
  char=fedRoster(fed,cyc)
  If MouseX()>rX#(x)-87 And MouseX()<rX#(x)+87 And MouseY()=>rY#(y)-10 And MouseY()<rY#(y)+10 Then foc=cyc
  ;status indicate
  If foc=cyc Then Color 85,85,85 Else Color 55,55,55
  If game=1
   If charInjured(char)>0 Then Color 65,0,0
   If charVacant(char)>0 Then Color 0,65,0
   If charTrainCourse(char)>0 Then Color 0,0,65
  EndIf
  If charSelected(char)=0 And no_plays>1 Then Color 200,0,0
  If charSelected(char)=1 And no_plays>1 Then Color 0,0,200
  If charSelected(char)=2 And no_plays>1 Then Color 180,0,180
  If cloner=cyc Then Color 255,255,0
  Rect rX#(x)-85,rY#(y)-10,170,20,1
  ;boxing
  If foc=cyc Then DrawImage gCharMenu(1),rX#(x),rY#(y) Else DrawImage gCharMenu(2),rX#(x),rY#(y)
  ;titles
  If TitleHolder(char,3) Then DrawImage gBelt(3),rX#(x)+4,rY#(y)+8
  If TitleHolder(char,2) Then DrawImage gBelt(2),rX#(x)-4,rY#(y)+4
  If TitleHolder(char,1) Then DrawImage gBelt(1),rX#(x),rY#(y)+6
  If char=fedBooker(fed) Then DrawImage gBelt(0),rX#(x),rY#(y)-14
  ;injury indicator
  If game=1 And charInjured(char)>0
   SetFont font(0)
   OutlineStraight(charInjured(char),rX#(x)-75,rY#(y),0,0,0,255,150,150)
  EndIf
  ;file preview
  If KeyDown(32) And screenAgenda=0
   SetFont font(0)
   OutlineStraight(char+".",rX#(x)-78,rY#(y),0,0,0,0,0,0)
  EndIf
  ;name tag
  SetFont font(1)
  r=160 : g=160 : b=160
  If foc=cyc Then r=255 : g=Rnd(175,225) : b=100
  Outline(charName$(char),rX#(x),rY#(y)-1,0,0,0,r,g,b)
  ;black out
  If screenAgenda=3 And (CharBusy(char)>0 Or char=fedBooker(fed))
   DrawImage gMenu(0),rX#(x),rY#(y)
  EndIf
  ;cloning data
  If cloner=cyc Then sourceX=x : sourceY=y 
  If foc=cyc 
   targetX=x : targetY=y 
   If cloner>0 And cloner<>cyc And screenAgenda=0 Then Outline("RIGHT CLICK TO CLONE",rX#(x),rY#(y)+12,50,30,10,255,200,150)
   If cloner>0 And cloner<>cyc And screenAgenda>0 Then Outline("RIGHT CLICK TO SWITCH",rX#(x),rY#(y)+12,50,30,10,255,200,150)
   If cloner>0 And cloner=cyc Then Outline("RIGHT CLICK TO CANCEL",rX#(x),rY#(y)+12,50,30,10,255,200,150)
  EndIf
  ;increment
  x=x+190
  If x>700 Then y=y+38 : x=120
 Next
 ;plot clone line
 If cloner>0 And foc<>cloner And targetY>0 
  DrawLine(rX#(sourceX),rY#(sourceY),rX#(targetX),rY#(targetY))
 EndIf
 ;legend
 If legendTim>0 And screenAgenda=1
  x=640 : y=52
  Color 255,0,0 : Rect rX#(x)-80,rY#(y)-8,160,16,1
  Color 135,0,0 : Rect rX#(x)-80,rY#(y)-8,160,16,0
  Outline("CPU",rX#(x),rY#(y),145,0,0,165,150,150)
  y=y+20
  Color 0,0,255 : Rect rX#(x)-80,rY#(y)-8,160,16,1
  Color 0,0,135 : Rect rX#(x)-80,rY#(y)-8,160,16,0
  Outline("Keyboard",rX#(x),rY#(y),0,0,145,150,150,165)
  y=y+20
  Color 155,0,155 : Rect rX#(x)-80,rY#(y)-8,160,16,1
  Color 75,0,75 : Rect rX#(x)-80,rY#(y)-8,160,16,0
  Outline("Gamepad",rX#(x),rY#(y),100,0,100,165,150,165)
 EndIf
 ;options
 SetFont font(1)
 If no_plays>1
  chosen=CountSelected()
  If chosen<no_wrestlers
   DrawImage gRole(1),rX#(328),rY#(545)
   Outline(chosen+"/"+no_wrestlers+" Wrestlers",rX#(400),rY#(550),0,0,0,200,200,200)
  EndIf
  If chosen=>no_wrestlers And chosen<no_plays And no_refs>0
   DrawImage gRole(3),rX#(328),rY#(545)
   Outline((chosen-no_wrestlers)+"/"+no_refs+" Referees",rX#(400),rY#(550),0,0,0,200,200,200)
  EndIf
  If chosen=no_plays Then DrawOption(0,400,550,">>> PROCEED >>>","",0,0)
 EndIf
 If screenAgenda<>1 Then DrawOption(0,400,550,"<<< EXIT <<<","",0,0)
 ;trading legend
 If screenAgenda=0 And no_slots=<36
  x=50
  For count=1 To 9
   If count<>fed And fedSize(count)<optFedLim 
    Outline(count+". "+fedShortName$(count),rX#(x),rY#(510),60,0,0,130,130,130)
   EndIf
   x=x+87
  Next
 EndIf 
 ;inst
 If screenAgenda=0 Or screenAgenda=3 Then Outline("CURSORS=Highlight character, SPACE=Clone, ENTER=Select, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 If screenAgenda=1 Then Outline("CURSORS=Highlight character, ENTER=Assign Control, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 If screenAgenda=2 Then Outline("CURSORS=Highlight character, SPACE=Re-arrange, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If screenAgenda=0 ;editing
 If go=-1 Then screen=14
 If go=1 Then saver=fedRoster(fed,foc) : screen=51
EndIf
If screenAgenda=1 ;playing
 If go=-1 Then screen=12
 If go=1 Then screen=50
EndIf
If screenAgenda=2 ;studying
 If go=-1 Then screen=14
 If go=1 Then screen=14
EndIf
If screenAgenda=3 ;initiation
 If go=-1 Then screen=21
 If go=1 Then saver=fedRoster(fed,foc) : screen=51
EndIf
End Function

;////////////////////////////////////////////////////////////////
;---------------------- RELATED FUNCTIONS -----------------------
;////////////////////////////////////////////////////////////////

;DRAW MENU ITEM
Function DrawOption(box,x,y,scriptA$,scriptB$,style,size) ;0=solo,1=duel / 0=normal,2=reduced
 ;assess hotspots
 If size=0 Then ranger=87 Else ranger=62
 If style=0 Then width=ranger Else width=ranger*2
 If box=>0 And box<>99 And foc<>99
  If MouseX()=>rX#(x)-width And MouseX()<rX#(x)+width And MouseY()=>rY#(y)-12 And MouseY()<rY#(y)+12 Then foc=box
 EndIf
 If style=0 Then ranger=0
 ;draw boxes
 If foc=box Or box=-1
  ;active
  DrawImage gMenu(1+size),rX#(x)-ranger,rY#(y)
  SqueezeFont(scriptA$,175-(25*size))
  If optMenu=0 Then Outline(scriptA$,rX#(x)-ranger,rY#(y),190,190,190,200,0,0)
  If optMenu=1 Then Outline(scriptA$,rX#(x)-ranger,rY#(y),100,0,0,255,255,130) 
  If style=1
   DrawImage gMenu(5+size),rX#(x)+ranger,rY#(y)
   SqueezeFont(scriptB$,175-(25*size))
   If optMenu=0 Then Outline(scriptB$,rX#(x)+ranger,rY#(y),190,190,190,75,75,75)
   If optMenu=1 Then Outline(scriptB$,rX#(x)+ranger,rY#(y),0,0,120,255,255,255) 
  EndIf
 Else
  ;inactive
  DrawImage gMenu(2+size),rX#(x)-ranger,rY#(y)
  SqueezeFont(scriptA$,175-(25*size))
  If optMenu=0 Then Outline(scriptA$,rX#(x)-ranger,rY#(y),120,120,120,100,0,0)
  If optMenu=1 Then Outline(scriptA$,rX#(x)-ranger,rY#(y),20,0,0,150,120,80)
  If style=1
   DrawImage gMenu(6+size),rX#(x)+ranger,rY#(y)
   SqueezeFont(scriptB$,175-(25*size))
   If optMenu=0 Then Outline(scriptB$,rX#(x)+ranger,rY#(y),120,120,120,0,0,0)
   If optMenu=1 Then Outline(scriptB$,rX#(x)+ranger,rY#(y),0,0,20,140,140,140)
  EndIf
 EndIf
 ;EDITOR WARNINGS
 If foc=box And screen=51
  ;profile blocks
  If page=1
   If (foc=>2 And foc=<12) Or (foc=>14 And foc=<15)
    If blockData=1 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
   EndIf
   If foc=1 Or foc=17 Or foc=19
    If blockImage=1 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
   EndIf
   If fed=7
    If foc=>17 And foc=<19 Then warnX=x : warnY=y+12 : warning$="(Not available at school)"
   EndIf
   If screenAgenda=3
    If foc=4 Or foc=10 Or foc=11 Or foc=14 Or foc=15 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
   EndIf
  EndIf
  ;costume blocks
  If page=>2 And page=<4
   If foc=>2 And foc=<10 
    If blockImage=1 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
    If blockData=1 And foc=6 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
   EndIf
  EndIf
  ;attack blocks
  If page=5
   If foc=>1 And foc=<13 
    If blockAttacks=1 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
   EndIf
  EndIf
  ;move blocks
  If page=6
   If foc=>1 And foc=<21
    If blockMoves=1 Then warnX=x : warnY=y+12 : warning$="(This value is beyond your control)"
   EndIf
  EndIf
 EndIf
End Function

;SQUEEZE FONT INTO BOX
Function SqueezeFont(script$,width)
 SetFont font(1)
 If StringWidth(script$)>width Then SetFont font(0)
End Function

;DRAW CHARACTER PROFILE
Function DrawProfile(char,x,y)
 ;default location
 If x<0 Then x=170
 If y<0 Then y=25
 x=rX#(x) : y=rY#(y) 
 ;colour scheme
 r=240 : g=220 : b=90
 ;<<<<<<<<< LEFT HAND SIDE <<<<<<<<<
 ;health meter
 Color 130,0,0 : Rect x-40,y+5,100,5,1
 Color 200,0,0 : Rect x-39,y+6,98,3,1
 If game=1 Then liner=charHealth(char) Else liner=100
 Color 0,130,0 : Rect x-40,y+5,liner,5,1
 Color 0,200,0 : Rect x-39,y+6,liner-2,3,1
 ;injury indication
 If game=1 And charInjured(char)>0
  DrawImage gInjury,x-45,y+7
  SetFont font(1)
  If screen<>17 And screen<>18 Then Outline(charInjured(char),x-45,y+7,0,0,0,255,150,150)
 EndIf
 ;allegiance indicator
 SetFont font(2)
 placer=(StringWidth(charName$(char))/2)+5
 If placer<45 Then placer=45
 If placer>90 Then placer=90
 If game=1 And charInjured(char)>0 And placer<60 Then placer=60
 DrawImage gHeel(charHeel(char)),x-placer,y
 ;photos & name line
 If charManager(char)>0 Then DrawImage charPhoto(charManager(char)),x-132,y+33
 If game=1 And screen=15 And gamSchedule(slot,GetDate())>0 And matchTeams>0 And charFriend(char)>0 And charFriend(char)<>charManager(char) 
  DrawImage charPhoto(charFriend(char)),x-142,y+33
 EndIf
 If char>0 Then DrawImage charPhoto(char),x-112,y+35
 If TitleHolder(char,3) Then DrawImage gBelt(3),x-110,y+49
 If TitleHolder(char,2) Then DrawImage gBelt(2),x-112,y+47
 If TitleHolder(char,1) Then DrawImage gBelt(1),x-111,y+45
 If KeyDown(47)=0 Then Outline(charName$(char),x+10,y-1,0,0,0,r,g,b)
 If KeyDown(47) Then Outline(charName$(char)+"("+GetValue(char)+")",x+10,y-1,0,0,0,r,g,b)
 ;headers A
 SetFont font(1)
 OutlineStraight("Popularity:",x-90,y+21,0,0,0,r,g,b)
 OutlineStraight("Strength:",x-80,y+36,0,0,0,r,g,b)
 OutlineStraight("Skill:",x-52,y+51,0,0,0,r,g,b)
 OutlineStraight("Agility:",x-65,y+66,0,0,0,r,g,b)
 OutlineStraight("Finisher:",x-76,y+84,0,0,0,r,g,b)
 ;data A
 GetStatColour(charPop(char),charOldPop(char))
 Outline(charPop(char),x-7,y+21,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charStr(char),charOldStr(char))
 Outline(charStr(char),x-7,y+36,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charSkl(char),charOldSkl(char))
 Outline(charSkl(char),x-7,y+51,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charAgl(char),charOldAgl(char))
 Outline(charAgl(char),x-7,y+66,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 OutlineStraight(movName$(charMove(char,15)),x-15,y+84,0,0,0,255,255,255)
 ;headers B
 OutlineStraight("Stamina:",x+27,y+21,0,0,0,r,g,b)
 OutlineStraight("Toughness:",x+11,y+36,0,0,0,r,g,b)
 OutlineStraight("Attitude:",x+29,y+51,0,0,0,r,g,b)
 OutlineStraight("Happiness:",x+13,y+66,0,0,0,r,g,b)
 ;data B
 GetStatColour(charStam(char),charOldStam(char))
 Outline(charStam(char),x+97,y+21,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charTough(char),charOldTough(char)) 
 Outline(charTough(char),x+97,y+36,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charAtt(char),charOldAtt(char))
 Outline(charAtt(char),x+97,y+51,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charHap(char),charOldHap(char))
 Outline(charHap(char),x+97,y+66,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 ;>>>>>>>>>> RIGHT HAND SIDE >>>>>>>>>
 If screen<>13 Or legendTim=0
  ;headers
  x=rX#(600)
  OutlineStraight("Experience:",x-79,y+21,0,0,0,r,g,b)
  OutlineStraight("Contract:",x-62,y+36,0,0,0,r,g,b)
  OutlineStraight("Salary:",x-49,y+51,0,0,0,r,g,b) 
  ;data
  OutlineStraight(charAge(char)+" years old",x,y+21,0,0,0,255,255,255)
  OutlineStraight(charContract(char)+" weeks",x,y+36,0,0,0,255,255,255) 
  OutlineStraight("$"+GetFigure$(charSalary(char))+" per week",x,y+51,0,0,0,255,255,255)
  ;smallprint
  SetFont font(0)
  If game=1 And char=gamChar(slot) Then OutlineStraight("("+CountMatches(slot)+" matches)",x+86,y+21,0,0,0,255,255,255)
  OutlineStraight("Image Rights:",x-72,y+65,0,0,0,r,g,b)
  OutlineStraight("Perform Clause:",x-83,y+76,0,0,0,r,g,b)
  OutlineStraight("Health Policy:",x-72,y+87,0,0,0,r,g,b)
  For count=1 To 3
   namer$="Undisclosed" : Color 255,255,255
   If game=1 And char=gamChar(slot) Then namer$=textClause$(count,gamClause(slot,count)) : GetStatColour(gamClause(slot,count),1)
   OutlineStraight(namer$,x,y+(54+(count*11)),0,0,0,ColorRed(),ColorGreen(),ColorBlue())
  Next
  ;bank details
  If game=1 And char=gamChar(slot)
   Color 190,150,40 : Line x-50,y+8,x+70,y+8
   Color 160,120,10 : Line x-54,y+10,x+74,y+10
   SetFont font(2)
   If gamBank(slot)=<0 Then Outline("$"+GetFigure$(gamBank(slot)),x+10,y,0,0,0,200,0,0)
   If gamBank(slot)>0 Then Outline("$"+GetFigure$(gamBank(slot)),x+10,y,0,0,0,250,250,130) 
  EndIf
 EndIf
End Function

;DRAW FED STATS
Function DrawFedProfile(cyc,x,y)
 ;default location
 If x<0 Then x=180
 If y<0 Then y=35
 x=rX#(x) : y=rY#(y) 
 ;colour scheme
 r=240 : g=220 : b=90
 ;<<<<<<<<< LEFT HAND SIDE <<<<<<<<<
 ;name
 Color 190,150,40 : Line x-50,y+8,x+70,y+8
 Color 160,120,10 : Line x-54,y+10,x+74,y+10
 SetFont font(2)
 Outline(fedName$(cyc),x+10,y-1,0,0,0,r,g,b)
 ;portraits
 If fedChampWorld(cyc)>0 Then DrawImage charPhoto(fedChampWorld(cyc)),x-127,y+24
 If fedBooker(cyc)>0 Then DrawImage charPhoto(fedBooker(cyc)),x-107,y+25 
 ;headers
 SetFont font(1)
 OutlineStraight("Popularity:",x-75,y+21,0,0,0,r,g,b)
 OutlineStraight("Reputation:",x-78,y+36,0,0,0,r,g,b)
 OutlineStraight("Booker:",x-54,y+51,0,0,0,r,g,b)
 OutlineStraight("Champion:",x-72,y+66,0,0,0,r,g,b)
 ;popularity data
 GetStatColour(fedPop(cyc),fedOldPop(cyc))
 Outline(fedPop(cyc),x+8,y+21,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 r2=ColorRed()-(ColorRed()/4) : g2=ColorGreen()-(ColorGreen()/4) : b2=ColorBlue()-(ColorBlue()/4)
 Outline("/100",x+35,y+21,0,0,0,r2,g2,b2)
 ;reputation data
 GetStatColour(fedRep(cyc),fedOldRep(cyc))
 Outline(fedRep(cyc),x+8,y+36,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 r2=ColorRed()-(ColorRed()/4) : g2=ColorGreen()-(ColorGreen()/4) : b2=ColorBlue()-(ColorBlue()/4)
 Outline("/100",x+35,y+36,0,0,0,r2,g2,b2)
 ;key players
 If fedBooker(cyc)>0 Then namer$=charName$(fedBooker(cyc)) Else namer$="Vacant"
 OutlineStraight(namer$,x,y+51,0,0,0,255,255,255)
 If fedChampWorld(cyc)>0 Then namer$=charName$(fedChampWorld(cyc)) Else namer$="Vacant"
 OutlineStraight(namer$,x,y+66,0,0,0,255,255,255)
 ;>>>>>>>>>> RIGHT HAND SIDE >>>>>>>>>
 x=rX#(600)
 ;bank details
 Color 190,150,40 : Line x-50,y+8,x+70,y+8
 Color 160,120,10 : Line x-54,y+10,x+74,y+10
 SetFont font(2)
 If fedBank(cyc)=<0 Then Outline("$"+GetFigure$(fedBank(cyc)),x+10,y,0,0,0,200,0,0)
 If fedBank(cyc)>0 Then Outline("$"+GetFigure$(fedBank(cyc)),x+10,y,0,0,0,250,250,130) 
 ;headers
 SetFont font(1)
 OutlineStraight("Roster:",x-49,y+21,0,0,0,r,g,b)
 OutlineStraight("Ratio:",x-40,y+36,0,0,0,r,g,b)
 OutlineStraight("Salaries:",x-60,y+51,0,0,0,r,g,b)
 OutlineStraight("Production:",x-77,y+66,0,0,0,r,g,b) 
 ;data
 OutlineStraight(fedSize(cyc)+" wrestlers",x,y+21,0,0,0,255,255,255)
 OutlineStraight(GetRatio(cyc,0)+" Faces vs "+GetRatio(cyc,1)+" Heels",x,y+36,0,0,0,255,255,255) 
 OutlineStraight("$"+GetFigure$(CountSalaries(cyc))+" per week",x,y+51,0,0,0,255,255,255)
 OutlineStraight("Undisclosed",x,y+66,0,0,0,255,255,255)
End Function

;GET STAT COLOUR
Function GetStatColour(current,old)
 ;default colours
 Color 255,255,255
 ;higher or lower
 If game=1
  If current>old Then Color 100,220,100
  If current<old Then Color 220,100,100
 EndIf
End Function

;COUNT FACES/HEELS IN FED
Function GetRatio(cyc,style)
 value=0
 For count=1 To fedSize(cyc)
  char=fedRoster(cyc,count)
  If DesignatedRef(char)=0 And charHeel(char)=style Then value=value+1
 Next 
 Return value
End Function

;CALCULATE FED SALARIES
Function CountSalaries(cyc)
 value=0
 For count=1 To fedSize(cyc)
  value=value+charSalary(fedRoster(cyc,count))
 Next
 Return value
End Function

;ASSIGN KEY PROCESS
Function AssignKey(current)
 value=0
 While value=0
  For v=0 To 255
   If KeyDown(v)=1 And keytim=0 
    If v<>0 And v<>1 And v<>28 And v<>25 Then value=v : screenCall=0 : PlaySound sMenuSelect : gotim=0 : keytim=10
   EndIf
  Next
  If KeyDown(1) And keytim=0 Then value=current : screenCall=0 : PlaySound sMenuBack : go=0 : gotim=0 : keytim=15
 Wend
 Return value
End Function

;ASSIGN BUTTON PROCESS
Function AssignButton(current)
 value=28
 While value=28
  For v=1 To 8
   If JoyDown(v)=1 And keytim=0 Then value=v : screenCall=0 : PlaySound sMenuSelect : gotim=0 : keytim=10
  Next
  If KeyDown(1) And keytim=0 Then value=current : screenCall=0 : PlaySound sMenuBack : go=0 : gotim=0 : keytim=15
 Wend
 Return value
End Function

;COUNT SELECTED CHARACTERS
Function CountSelected()
 value=0
 For char=1 To no_chars
  If charSelected(char)=>0 Then value=value+1
 Next
 Return value
End Function

;DOES FOC COMPLY IN TABLE BROWSING?
Function FocComplies(dir,cols,rows)
 value=1
 ;left browsing
 If dir=1
  If foc=1 Then value=0
  For count=1 To rows-1
   If foc=1+(cols*count) Then value=0
  Next
 EndIf
 ;right browsing
 If dir=2
  For count=1 To rows
   If foc=cols*count Then value=0
  Next
 EndIf
 ;up browsing
 If dir=3
  If foc>0 And foc=<cols Then value=0
 EndIf
 ;down browsing
 If dir=4
  ;If foc=>(cols*(rows-1))+1 And chosen<needed Then value=0
  If foc+cols>no_slots And screenAgenda=1 And CountSelected()<no_plays Then value=0
 EndIf
 Return value
End Function

;CHAMP SETTING LOGICAL?
Function ChampLogic()
 logic=1
 If matchChamps=>2 And matchChamps=<3 And matchTeams>0 Then logic=0
 If matchChamps=4 And matchTeams=<0 Then logic=0
 If matchChamps=4 And (no_wrestlers<3 Or no_wrestlers>4) Then logic=0
 If matchChamps=6 
  If fed=7 Or fedSize(7)=>optFedLim Or fedSize(fed)=<3 Then logic=0
 EndIf
 Return logic
End Function

;STANDARD LOADING DISPLAY
Function Loader(scriptA$,scriptB$)
 ;in-game
 If screen>0
  Cls
  TileImage gTile
  DrawImage gLogo(1),rX#(400),rY#(300)
  SetFont font(1)
  DrawOption(-1,400,465,scriptA$,scriptB$,1,0)
  Flip
 Else
  ;intro
  SetFont font(1)
  DrawOption(-1,400,500,scriptA$,scriptB$,1,0)
  Flip
 EndIf
End Function

;QUICK LOADING DISPLAY
Function QuickLoader(x,y,scriptA$,scriptB$)
 SetFont font(1)
 DrawOption(-1,x,y,scriptA$,scriptB$,1,0)
 Flip
End Function

;MATCH LOADING DISPLAY
Function MatchLoader(scriptA$,scriptB$,level)
 ;background
 Cls
 TileImage gTile
 DrawImage gLogo(1),rX#(400),rY#(225)
 DrawImage gVersus,rX#(400),rY#(420)
 ;portraits
 If level>0
  If no_wrestlers>6 Then spacing=50 Else spacing=100
  If matchTeams=<0 Then x=(400+(spacing/2))-(no_wrestlers*(spacing/2))
  For cyc=1 To level
   If matchTeams>0
    If cyc=<no_wrestlers/2 Then x=390-(45*cyc) 
    If cyc>no_wrestlers/2 Then x=410+(45*(cyc-(no_wrestlers/2)))
   EndIf
   If cyc=<no_wrestlers Then DrawImage charPhoto(pChar(cyc)),rX#(x),rY#(400)
   x=x+spacing
  Next
 EndIf
 ;message
 SetFont font(1)
 DrawOption(-1,400,500,scriptA$,scriptB$,1,0)
 Flip
End Function

;CHANGE RESOLUTION
Function ChangeResolution(resolution,task) ;0=pre-game, 1=during game
 ;assess preferences
 width=Int(textResX$(resolution))
 height=Int(textResY$(resolution))
 If GfxMode3DExists(width,height,16)=0 Then width=800 : height=600 : optGameRes=2 : optMenuRes=2
 ;make transition?
 If width<>GraphicsWidth() Or height<>GraphicsHeight()
  If task>0 Then Loader("Please Wait","Adjusting Resolution")
  Graphics3D width,height,16,2
  If task>0 ;restore media
   LoadImages()
   Loader("Please Wait","Restoring Media")
   LoadTextures()
   LoadWeaponData()
   LoadPhotos()
  EndIf
 EndIf
End Function

;GET SCREENSHOT
Function Screenshot()   
 ;obtain image
 PlaySound sCamera
 screenshot=CreateImage(GraphicsWidth(),GraphicsHeight())
 GrabImage screenshot,GraphicsWidth()/2,GraphicsHeight()/2
 ;title & save
 temp=MilliSecs()/10
 If game=0 Then namer$="Exhibition - "+temp+".bmp"
 If game=1 Then namer$=charName$(gamChar(slot))+" ("+Left$(textMonth$(gamMonth(slot)),3)+" "+gamYear(slot)+") - "+temp+".bmp"
 SaveImage(screenshot,"Photo Album/"+namer$)
End Function