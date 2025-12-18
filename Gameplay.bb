;-------------------------------------------------------------------------
;/////////////////////////// WRESTLING ENCORE ////////////////////////////
;-------------------------------------------------------------------------
;~~~~~~~~~~~~~~~~~~~~~ Game made by Mat Dickie 2006 ~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~ This program can be re-released under any other ~~~~~~~~~~
;~~~~ identity but can't sold commercially without express permission. ~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

;FILE STRUCTURE
AppTitle "Wrestling Encore"
Include "Values.bb"
Include "Texts.bb"
Include "Data.bb"
Include "Functions.bb"
Include "Menus.bb"
Include "Credits.bb"
Include "Career.bb"
Include "Charts.bb"
Include "News.bb"
Include "Meetings.bb"
Include "Negotiations.bb"
Include "Scenes.bb"
Include "World.bb"
Include "Players.bb"
Include "Editor.bb"
Include "AI.bb"
Include "Anims.bb"
Include "Attacks.bb"
Include "Moves.bb"
Include "Ground.bb"
Include "Buckles.bb"
Include "Items.bb"
Include "Particles.bb"
Include "Promos.bb"

;INITIATE ENGINE
LoadOptions()
ChangeResolution(optMenuRes,0)
SetBuffer BackBuffer()
AutoMidHandle True
EnableDirectInput True
SeedRnd(MilliSecs())
MoveMouse 500,500

;LOADING PROCESS
;restore data
LoadWorld()
LoadChars()
LoadProgress()
;intro
Intro()
;initiation process
LoadImages()
LoadTextures()
LoadWeaponData()
Loader("Please Wait","Loading Statistics")
LoadPhotos()
;initiate values
LoadMatch(2)
arenaPreset=Rnd(1,no_arenas-2)
arenaApron=1

;SCREEN MANAGEMENT
SeedRnd(MilliSecs())
screen=1
Repeat
 ;main menus
 If screen=1 Then MainMenu()
 If screen=2 Then Options()
 If screen=3 Then DisplayOptions()
 If screen=4 Then RedefineKeys() 
 If screen=5 Then RedefineGamepad()
 If screen=6 Then Credits()
 If screen=7 Then Outro()
 ;game screens
 If screen=10 Then MatchSetup()
 If screen=11 Then GimmickSetup()
 If screen=12 Then ArenaSetup()
 If screen=13 Then SelectCharacter()
 If screen=14 Then SelectRoster()
 If screen=15 Then Calendar()
 If screen=16 Then NewWeek()
 If screen=17 Then Aftermath()
 If screen=18 Then NewsReports()
 If screen=19 Then FinanceReport()
 If screen=20 Then WorldLeaders()
 If screen=21 Then SelectSlot()
 If screen=22 Then EndCareer()
 If screen=23 Then HallOfFame()
 ;scenes
 If screen=50 Then PlayMatch()
 If screen=51 Then EditCharacter()
 If screen=52 Then Negotiations()
 If screen=53 Then Meeting() 
 If screen=54 Then Training() 
 If screen=55 Then PlasticSurgery()  
 ;get-out clause
 If KeyDown(56) And KeyDown(45) Then End
Until screen=0
End 

;--------------------------------------------------------------------------
;////////////////////////// 50. PLAY MATCH ////////////////////////////////
;--------------------------------------------------------------------------
Function PlayMatch()
;adjust resolution
ChangeResolution(optGameRes,1)
;initiate crowd
LoopSound sCrowd(1)
chCrowd=PlaySound(sCrowd(1))
If arenaType=2 And arenaCrowd>1 Then arenaCrowd=1
If arenaCrowd=0 Then crowdVol#=0 Else crowdVol#=0.2
crowdVolTarget#=crowdVol#
crowdPitch#=44100 : crowdPitchTarget#=crowdPitch# 
ChannelVolume chCrowd,crowdVol#
chThemeVol#=1.0
;load location
MatchLoader("Please Wait","Loading Arena",0)
LoadArena()
;load atmosphere
MatchLoader("Please Wait","Loading Atmosphere",0)
LoadAtmos()
;prepare cast
PreparePlayers()
;load players
LoadPlayers()
;force dual control
If matchMulti=0 And matchPlayer>0 Then pControl(matchPlayer)=3
;force focus in brawls
If matchOfficial=0
 For cyc=1 To no_wrestlers
  GetNewFoc(cyc)
 Next
EndIf
;prepare promo
PreparePromo()
;load items
MatchLoader("Please Wait","Loading Items",99)
If no_items>0 Then LoadItems()
;load weapons
MatchLoader("Please Wait","Loading Weapons",99)
PrepareWeapons()
If no_weaps>0 Then LoadWeapons()
AssignWeapons()
;load effects
If optFX>0
 MatchLoader("Please Wait","Loading Effects",99)
 LoadParticles()
 If optFX=>2 Then LoadPools()
EndIf
;rope ambience
If matchRopes=2 ;electricity
 LoopSound sPower
 ropeChannel(0)=PlaySound(sPower)
 ChannelVolume ropeChannel(0),0.3
EndIf
If matchRopes=3 ;fire
 LoopSound sFire
 ropeChannel(0)=PlaySound(sFire)
 ChannelVolume ropeChannel(0),0.7
EndIf
;reset match status
If matchOfficial=0 Then matchRules=0
matchState=0 : matchEnter=no_wrestlers
matchMins=0 : matchSecs=0 : matchLastMin=0
matchLeader=0 : matchWinner=0 : matchLoser=0
matchInvader=0 : matchWeapon=0 : matchDamage=0
matchBlasted=0 : matchPause=0 : matchQuit=0
marScript$=""
;store title status
fedOldChampWorld(fed)=fedChampWorld(fed)
fedOldChampInter(fed)=fedChampInter(fed)
For count=1 To 2
 fedOldChampTag(fed,count)=fedChampTag(fed,count)
Next
;prepare press photo
x#=rX#(512)
If x#>700 Then x#=700
If game=1 Then gPhoto=CreateImage(x#,rY#(256))
;frame rating
MatchLoader("Please Wait","Finalizing World",99)
period=frameRate(2.5)/FPS;optSpeed
time=MilliSecs()-period
SeedRnd(MilliSecs())
;MAIN LOOP
go=0 : gotim=-200 : keytim=10
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
	
	;COUNTERS
	keytim=keytim-1
	If keytim<1 Then keytim=0
	speedTim=speedTim-1
	If speedTim<1 Then speedTim=0
	controlTim=controlTim-1
	If controlTim<1 Then controlTim=0     
	;marque scroll
	If matchPause=0 Then marX#=marX#-2 
	
	;PORTAL
    gotim=gotim+1
	If gotim>30 And keytim=0
	 If KeyDown(1) Or KeyDown(28)
	  ;leave match
	  If matchState=4 Then go=-1
	  ;skip promos
	  If matchState=2 And promoTim>120 Then promoTim=promoTim-100 : PlaySound sMenuBrowse : keytim=5
	  ;skip entrance  
	  If matchState=1 And matchEnter>0 And pOutTim(matchEnter)=>2
	   For v=1 To no_plays
	    If pTeam(v)=pTeam(matchEnter) And pOutTim(v)>0 And pOutTim(v)<190
	     PlaySound sSwing : keytim=10
	     pOutTim(v)=190 
	     If InsideRing(pX#(v),pZ#(v),0)=0
	      ;If matchOTT=1 Or matchCage>0 Or (PointViable(v)=0 And pPlatform(v)=0)
	       pX#(v)=Rnd(wBlockX2#(4)+5,wBlockX1#(2)-5)
           pZ#(v)=Rnd(wBlockZ2#(3)+5,wBlockZ1#(1)-5)
           If matchTeams>0 And pTeam(v)=1 Then pX#(v)=Rnd(wBlockX2#(4)+5,0)
           If matchTeams>0 And pTeam(v)=2 Then pX#(v)=Rnd(0,wBlockX1#(2)-5)
           pOldX#(v)=pX#(v) : pOldZ#(v)=pZ#(v)
	       pY#(v)=wStage# : pPlatform(v)=0
	       ChangeAnim(v,0)
	      ;EndIf
	     EndIf
	    EndIf
	   Next
	  EndIf
	  ;skip intro
	  If matchState=0 
	   PlaySound sSwing : keytim=10
	   If ChannelPlaying(chTheme)>0 Then StopChannel chTheme
	   matchState=1
	  EndIf
	 EndIf
	 ;terminate match
	 If KeyDown(1) And matchState=3 And (matchOfficial=1 Or game=0)
	  EndMatch(0) : keytim=10
	  If matchPlayer>0 And pEliminated(matchPlayer)=0 Then matchQuit=1
     EndIf
    EndIf

   ;ppppppppppppppppppppppp PAUSE LOOP pppppppppppppppppppppppppppppppp
   ;pause toggle
   If KeyDown(25) And gotim>20 And keytim=0
    PlaySound sMenuBack : keytim=15
    If matchPause=1 
     matchPause=0
     clockStart=MilliSecs() 
     clockStart=clockStart-(matchSecs*frameRate(2.5));optSpeed
    Else 
     matchPause=1
    EndIf
   EndIf
   ;pause loop
   If matchPause=0
    
    ;MATCH PROCESS
    ;fade out main theme
    If matchState=0 And ChannelPlaying(chTheme)>0
     chThemeVol#=chThemeVol#-0.004
     If chThemeVol#<0 Then chThemeVol#=0
     ChannelVolume chTheme,chThemeVol# 
     If chThemeVol#=<0 Then StopChannel chTheme
    EndIf
    ;trigger entrances
    If gotim>100 And matchState=0 And matchOfficial=0 Then matchState=1
    If gotim>200 And matchState=0 And matchOfficial=1 Then matchState=1
    If matchState=1 And matchOfficial=0 Then matchEnter=0
    ;trigger promo
    If matchState=1 And matchEnter=0 Then matchState=2
    If matchState=2 Then promoTim=promoTim-1
	If promoTim<0 Then promoTim=0 
    ;trigger match
    If matchState=2 And promoTim=0
     clockStart=MilliSecs()
     PlaySound sBell
     Pop(0,6,1) : Pop(0,Rnd(2,7),1) 
     crowdVolTarget#=crowdVolMax#
     For cyc=1 To no_plays
      If pRole(cyc)=0 
       If pFoc(cyc)=0 Or pRole(pFoc(cyc))>0 Then GetNewFoc(cyc) 
       pAgenda(cyc)=1
      EndIf
     Next
     CauseChaos(200)
     camType=3 : camOverride=0
     If optLevel=>4 Or matchPlayer=0 Then camType=15
     If matchPlayer>0 Then camFoc=matchPlayer
     If matchMulti>0 Or pRole(matchPlayer)=1 Or (matchCage>0 And matchOTT=1) 
      camType=no_cams
      If camZ#>pZ#(camFoc) Then camZ#=camTZ#
     EndIf
     lightPreset=arenaLight
     matchState=3 
    EndIf
    ;clock
	If matchstate=3
	 clockUpdate=MilliSecs()
	 matchSecs=(clockUpdate-clockStart)/frameRate(2.5);optSpeed
	 If matchSecs>59 
	  matchMins=matchMins+1 : clockStart=clockUpdate
	  If matchBlastTim>0 And matchMins=matchBlastTim-1 Then PostMessage(0,10,3) ;blast warning
	  If matchTimeLim>5 And matchMins=matchTimeLim-5 Then PostMessage(0,11,5) ;5 minute warning
	  If matchTimeLim>1 And matchMins=matchTimeLim-1 Then PostMessage(0,12,4) ;1 minute warning
	 EndIf
	EndIf
	;rumble participants
	If matchState=3 And matchTeams=-1 And matchMins<>matchLastMin 
	 matchLastMin=matchMins : matchEnter=no_plays
	 Repeat  
      matchEnter=matchEnter-1
     Until matchEnter=<0 Or (pRole(matchEnter)=0 And pOutTim(matchEnter)=0)
     If matchEnter>0
      PlaySound sBuzzer : Pop(0,6,1)
      crowdVolTarget#=crowdVolMax#
	  If ChannelPlaying(chTheme) Then StopChannel chTheme : lightPreset=0
	  If game=0
	   For v=1 To no_plays
	    If pControl(matchEnter)=0 And pControl(v)>0 And pEliminated(v)=1 
	     pControl(matchEnter)=pControl(v) : pControl(v)=0
	     If matchPlayer=v Then matchPlayer=matchEnter
	     If camFoc=v Then camFoc=matchEnter
	    EndIf
	   Next
	  EndIf
     EndIf
	EndIf 
    ;cause universal chaos
    If matchState=3
     If FindReferee(0)=0 Then CauseChaos(100) ;no referee
	 If matchTeams=2 And InsideRing(pX#(teamLegal(1)),pZ#(teamLegal(1)),0)=0 And InsideRing(pX#(teamLegal(2)),pZ#(teamLegal(2)),0)=0
	  CauseChaos(100) ;match spills outside
	 EndIf
	EndIf

    ;FIDDLES
    If gotim>0 And keytim=0
     ;widescreen offset
     If KeyDown(56) And KeyDown(17)
      PlaySound sMenuGo : keytim=10
      adjuster=GraphicsWidth()/10
      CameraViewport cam,adjuster,0,GraphicsWidth()-(adjuster*2),GraphicsHeight()
     EndIf
     ;toggle health displays
     If KeyDown(62)
      PlaySound sMenuSelect : keytim=15 
      If optMeters=1 Then optMeters=0 Else optMeters=1
     EndIf
     ;alter game speed
     For count=63 To 66
      If KeyDown(count) 
       PlaySound sMenuSelect : keytim=10 : speedTim=100
       optSpeed=2.5;count-63
       period=frameRate(2.5)/FPS;optSpeed
      EndIf 
     Next
     ;decrease health universally
     If KeyDown(29) And KeyDown(207)
      PlaySound sPain(4) : keytim=10
      For v=1 To no_plays
       If pRole(v)=0 And pHealth(v)>0 Then pHealth(v)=pHealth(v)/2
      Next
     EndIf
     ;random explosion
     If KeyDown(14) 
      x#=Rnd(-150,150) : z#=Rnd(-150,150)
      If InsideRing(x#,z#,-5) Then y#=wStage# Else y#=wGround#
      CreateExplosion(0,x#,y#,z#,10)
      keytim=20
     EndIf
     ;restart match!
     If game=0 And matchState=4 And matchOTT=0 And KeyDown(211)
      PlaySound sBell : Pop(0,6,1)
      matchState=3 : matchWinner=0
      For v=1 To no_plays
       pFalls(v)=0 : pEliminated(v)=0
      Next
      teamFalls(1)=0 : teamFalls(2)=0
      If matchTimeLim>0 And matchMins=>matchTimeLim Then matchTimeLim=matchTimeLim+5
      If ChannelPlaying(chTheme) Then StopChannel chTheme
     EndIf
     ;switch player
     If game=0 And KeyDown(15)
      PlaySound sSwing : keytim=10 : controlTim=100
      If camTim>0 And camFoc<>matchPlayer
       SwapControls(matchPlayer,camFoc,1)
      Else
       v=matchPlayer
       Repeat 
        v=v+1 : satisfied=1
        If v>no_plays Then v=1
        If pOutTim(v)=0 Then satisfied=0
        If pControl(v)>0 Then satisfied=0
        If pEliminated(v) And optHideElim>0 Then satisfied=0 
       Until satisfied=1 Or v=matchPlayer
       If v<>matchPlayer Then SwapControls(matchPlayer,v,1)
      EndIf
     EndIf
    EndIf
 
    ;PLAYER CYCLE
    ;save location
    PreserveOldPositions()
    ;get new input
    PlayerCycle()
    ;update display
    DisplayPlayers()

    ;MATCH ANALYSIS
    MatchAnalysis()

    ;PROP MANAGEMENT
    ;ropes
    RopeCycle()
    ;cages
    CageCycle()
    ;items
    ItemCycle()
    ;weapons
    WeaponCycle()
   
    ;PARTICLE EFFECTS
    If optFX>0
     ;particles
     ParticleCycle()  
     ;explosions
     ExplosionCycle()
     ;gore
     If optFX=2 Then PoolCycle()
    EndIf
 
    ;VIDEO DISPLAY
    If optVideo>0
     ;change image
	 randy=Rnd(0,100)
	 If randy=0 Then wVideo=Rnd(1,8)
	 If wVideo>7 And arenaApron=3 Then wVideo=4
	 If wVideo>7 Then wVideo=6
	 If wVideo=6 And wVideoCaught=0 Then wVideo=7
	 ;apply to screen
     If wVideo<>wOldVideo And tVideo(wVideo)>0
      If fed<>7 Then EntityTexture FindChild(world,"Screen01"),tVideo(wVideo)
      EntityTexture FindChild(world,"Screen02"),tVideo(wVideo)
      wOldVideo=wVideo
     EndIf
     ;shake noise
     randy=Rnd(0,2)
     If randy=0 And wVideo=7 Then PositionTexture tVideo(7),1,Rnd(0.0,2.0)
    EndIf
  
    ;ATMOSPHERICS
    ;fog & lighting
    ManageAtmos()
    ;crowd effects
    CrowdNoise()

   ;ppppppppppppppppppppp END OF PAUSE LOOP pppppppppppppppppppppppp
   EndIf

    ;CAMERA
    Camera()
    ;ghost scenery
    GhostScenery()
	
 If matchPause=0
  UpdateWorld
 EndIf

 ;override animation
 For cyc=1 To no_plays
  If pFoc(cyc)>0 And pMomentum(cyc)=0 And pDazed(cyc)=0
   If pAnim(cyc)=<3 Or (pAnim(cyc)=>15 And pAnim(cyc)=<16) Or (pAnim(cyc)=>23 And pAnim(cyc)=<24) Or (pAnim(cyc)=>31 And pAnim(cyc)=<32) 
    PointHead(cyc,FindChild(p(pFoc(cyc)),"Head"))
   EndIf
  EndIf
 Next
 
 Next
 RenderWorld 1
 ;save shots for video
 If gotim>0
  If wVideo=5 And optVideo>0 Then CopyRect rX#(400)-256,rY#(300)-128,512,256,0,0,0,TextureBuffer(tVideo(5)) ;live feed
  randy=Rnd(0,200)
  If (randy=0 Or wVideoCaught=0) And matchState=<3
   If optVideo>0 Then CopyRect rX#(400)-256,rY#(300)-128,512,256,0,0,0,TextureBuffer(tVideo(6)) ;sporadic feed
   If EntityY(pLimb(camFoc,30),1)<pGround#(camFoc)+15 Then y#=375 Else y#=325
   If game=1 Then GrabImage gPhoto,rX#(400),rY#(y#) ;press photo
   wVideoCaught=1
  EndIf
 EndIf
 
 ;DISPLAY
 ;corner logo
 If matchState<>2 Then DrawImage gLogo(3),rX#(56),rY#(571) 
 ;name lines
 naming=0
 If matchState=<3 And matchEnter>0 And matchEnter=<no_wrestlers And pOutTim(matchEnter)=>2 And pOutTim(matchEnter)<175
  DrawNameLine(matchEnter,111,573) : naming=1
 EndIf
 If matchState=4 Then DrawNameLine(matchWinner,111,573) : naming=1
 ;championship intros
 If matchReward=>2 And matchState=0 And gotim<150
  SetFont font(3)
  belter=0 : namer$=""
  If matchReward=2 Then belter=4 : namer$="World Championship on the line!"
  If matchReward=3 Then belter=5 : namer$="Inter Championship on the line!"
  If matchReward=4 Then belter=6 : namer$="Tag Championships on the line!"
  If matchReward=5 Then belter=0 : namer$="Loser must lose their HAIR!!!"
  If matchReward=6 Then belter=0 : namer$="Loser must LEAVE the company!!!"
  spacer=(StringWidth(namer$)/2)+20
  If belter>0 Then DrawImage gBelt(belter),rX#(400)-spacer,rY#(80)
  Outline(namer$,rX#(400),rY#(80),0,0,0,255,Rnd(130,230),100)
 EndIf

 ;championship outros
 If matchReward=>2 And matchState=4
  SetFont font(3)
  belter=0 : namer$=""
  If matchReward=2 And fedChampWorld(fed)<>fedOldChampWorld(fed) Then belter=4 : namer$="NEW World Champion!"
  If matchReward=3 And fedChampInter(fed)<>fedOldChampInter(fed) Then belter=5 : namer$="NEW Inter Champion!"
  If matchReward=4 And fedChampTag(fed,1)<>fedOldChampTag(fed,1) And fedChampTag(fed,1)<>fedOldChampTag(fed,2) And fedChampTag(fed,2)<>fedOldChampTag(fed,1) And fedChampTag(fed,2)<>fedOldChampTag(fed,2)
   belter=6 : namer$="NEW Tag Champions!"
  EndIf
  spacer=(StringWidth(namer$)/2)+20
  If belter>0 Then DrawImage gBelt(belter),rX#(400)-spacer,rY#(80)
  Outline(namer$,rX#(400),rY#(80),0,0,0,255,Rnd(130,230),100)
 EndIf
 ;clock
 If matchState=3 And naming=0
  If optGameRes<2 Then SetFont font(1) Else SetFont font(2)
  r=255 : g=255 : b=255
  If matchState=4 Then r=200 : g=200 : b=200
  Outline(matchMins+" : "+Dig$(matchSecs,10),rX#(400),rY#(580),0,0,0,r,g,b)
 EndIf
 ;match status
 If matchState=3 And naming=0
  SetFont font(1) : y#=560
  If matchType=3 And matchLeader>0 And matchTeams=<0 Then Outline(charName$(pChar(matchLeader))+" is leading with "+pFalls(matchLeader)+" falls...",rX#(400),rY#(y#),100,50,0,255,200,100)
  If matchType=4 And matchLeader>0
   If matchTeams=<0 Then Outline(charName$(pChar(matchLeader))+" is leading...",rX#(400),rY#(y#),100,50,0,255,200,100)
   If matchTeams>0 Then Outline(charName$(pChar(matchLeader))+"'s team are leading...",rX#(400),rY#(y#),100,50,0,255,200,100)
  EndIf
  If matchType=2 Or matchType=3
   If matchTeams>0 Then Outline(teamFalls(1)+" Vs "+teamFalls(2),rX#(400),rY#(y#),100,50,0,255,200,100)
  EndIf
  If matchType=5 
   If matchTeams=<0 And no_wrestlers>2 Then Outline(matchRemaining+" Wrestlers Remaining",rX#(400),rY#(y#),100,50,0,255,200,100)
   If matchTeams>0 Then Outline(teamMen(1)+" Vs "+teamMen(2),rX#(400),rY#(y#),100,50,0,255,200,100)
  EndIf
 EndIf
 ;marque messages
 If matchState=3
  SetFont font(1)
  If marColour=0 Then r=130 : g=130 : b=130 ;dark grey
  If marColour=1 Then r=100 : g=200 : b=100 ;green
  If marColour=2 Then r=100 : g=100 : b=200 ;blue
  If marColour=3 Then r=250 : g=Rnd(40,125) : b=0 ;orange
  If marColour=4 Then r=255 : g=Rnd(160,220) : b=100 ;yellow
  If marColour=5 Then r=200 : g=200 : b=200 ;light grey
  If marColour=6 Then r=Rnd(100,200) : g=0 : b=0 ;red
  If marX#>-200 Then Outline(marScript$,rX#(marX#),rY#(marY#),0,0,0,r,g,b)
 EndIf 
 ;camera
 If camtim>0
  DrawImage gCamera,rX#(55),rY#(40)
  SetFont font(2)
  OutlineStraight(textCam$(camType),rX#(100),rY#(40),0,0,150,195,195,230)
  SetFont font(1)
  namer$="Following "+charName$(pChar(camFoc))
  If camType=no_cams Then namer$="Following Active Wrestlers"
  OutlineStraight(namer$,rX#(100),rY#(55),20,50,20,150,180,120) 
  SetFont font(0)
  If game=0 And matchPlayer<>camFoc Then OutlineStraight("(Press TAB to assume control)",rX#(100),rY#(66),0,0,0,140,140,140) 
 EndIf
 ;speed icon
 If speedTim>0
  x=rX#(30) : y=rY#(525)
  For count=0 To optSpeed
   DrawImage gSpeeder,x,y
   x=x+14
  Next
 EndIf
 ;health displays
 If optMeters>0 And matchState=3 And matchTeams=>0 And no_wrestlers=<10
  DrawHealthMeters()
 EndIf
 ;promo subtitles
 For cyc=1 To no_plays
  ResetExpressions(cyc)
 Next

 If matchPromo<>0 Then DisplayPromo();matchState=2 And 


  If KeyDown(19)
r1=250 : g1=250 : b1=250
 x=rX#(400) : y=rY#(505) : s=rY#(36)
   Speak(1,2,1)
   Outline("Hey, you've got your rematch right here tonight!",rX#(400),rY#(520),0,0,0,r1,g1,b1)
   Outline("This time, you'll be praying to hear that bell...",rX#(400),rY#(555),0,0,0,r1,g1,b1)
  Else 
    camType=3
    camFoc = 0
  EndIf


 ;diagnostics
 If KeyDown(52)
  SetFont font(1)
  Outline("pX#: "+pX#(camFoc),100,50,0,0,0,255,255,255)
  Outline("pY#: "+pY#(camFoc),100,65,0,0,0,255,255,255)
  Outline("pZ#: "+pZ#(camFoc),100,80,0,0,0,255,255,255)
  Outline("pA#: "+pA#(camFoc),100,120,0,0,0,255,255,255)
  Outline("pMomentum: "+pMomentum(camFoc),100,140,0,0,0,255,255,255)
  Outline("Viable: "+AttackViable(camFoc),100,160,0,0,0,255,255,255)
  Outline("camTempTim: "+camTempTim,100,500,0,0,0,255,255,255)
  Outline("camTempFoc: "+camTempFoc,100,515,0,0,0,255,255,255)
  Outline("camOldFoc: "+camOldFoc,100,530,0,0,0,255,255,255)
  ;AI diagnostics
  Outline("pAgenda: "+pAgenda(camFoc)+" ("+pFoc(camFoc)+")",700,50,0,0,0,255,255,255)
  Outline("pTX#: "+pTX#(camFoc),700,70,0,0,0,255,255,255)
  Outline("pTZ#: "+pTZ#(camFoc),700,85,0,0,0,255,255,255)
  Outline("pSubX#: "+pSubX#(camFoc),700,105,0,0,0,255,255,255)
  Outline("pSubZ#: "+pSubZ#(camFoc),700,120,0,0,0,255,255,255)
  Outline("pActX#: "+pActX#(camFoc),700,140,0,0,0,255,255,255)
  Outline("pActZ#: "+pActZ#(camFoc),700,155,0,0,0,255,255,255)
  Outline("pLeaveX#: "+pLeaveX#(camFoc),700,170,0,0,0,255,255,255)
  Outline("pLeaveZ#: "+pLeaveZ#(camFoc),700,185,0,0,0,255,255,255)
  Outline("pNowhere: "+pNowhere(camFoc),700,215,0,0,0,255,255,255)
  Outline("pSatisfied: "+pSatisfied(camFoc),700,230,0,0,0,255,255,255)
  y=400
  For count=1 To no_plays
   Outline(count+". pControl: "+pControl(count),700,y,50,100,0,150,205,55)
   y=y+15
  Next
 EndIf
 ;paused
 If matchPause=1
  SetFont font(1)
  DrawOption(-1,400,300,"PAUSED","",0,0)
  Outline("Press 'P' to resume play...",rX#(400),rY#(325),50,50,50,250,250,250)
 EndIf
 ;mask shaky start!
 If gotim=<0 Then MatchLoader("Please Wait","Finalizing World",99)

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
Loader("Please Wait","Restoring Sound")
FreeEntity camListener
StopChannel chCrowd
RestoreTheme()
;remove atmospherics
Loader("Please Wait","Cleaning Arena")
FreeEntity world
FreeEntity cam
FreeEntity camPivot
FreeEntity light
FreeEntity lightPivot
FreeEntity dummy
;remove temporary textures
If tApron>0 Then FreeTexture tApron
If tCanvas>0 Then FreeTexture tCanvas
If tMat>0 Then FreeTexture tMat
If tCurtain>0 Then FreeTexture tCurtain
If tVideo(3)>0 Then FreeTexture tVideo(3)
If tVideo(4)>0 Then FreeTexture tVideo(4)
;remove ropes
For cyc=1 To 12
 FreeEntity rope(cyc)
 If ChannelPlaying(ropeChannel(cyc))>0 Then StopChannel ropeChannel(cyc)
Next
If matchRopes=>2 And ChannelPlaying(ropeChannel(0))>0 Then StopChannel ropeChannel(0)
;remove cage
If matchCage>0 Then FreeEntity cage
;remove players
For cyc=1 To no_plays
 Loader("Please Wait","Removing "+charName$(pChar(cyc))) 
 FreeEntity p(cyc)
 FreeEntity pShadow(cyc)
 If optTags>0 Then FreeEntity pTag(cyc)
Next
;remove items
Loader("Please Wait","Removing Items")
For cyc=1 To no_items
 FreeEntity i(cyc)
 If ChannelPlaying(iChannel(cyc))>0 Then StopChannel iChannel(cyc)
Next
;remove weapons
For cyc=1 To no_weaps
 FreeEntity weap(cyc)
 If ChannelPlaying(weapChannel(cyc))>0 Then StopChannel weapChannel(cyc)
Next
no_weaps=weapCount
;remove particles
If optFX>0
 Loader("Please Wait","Removing Effects")
 For cyc=1 To no_particles
  FreeEntity part(cyc)
 Next
EndIf
;remove pools
If optFX=>2
 For cyc=1 To no_pools
  FreeEntity pool(cyc)
 Next
EndIf
;process photo
If game=1
 Loader("Please Wait","Processing Photo")
 ResizeImage gPhoto,256,128
 SaveImage gPhoto,"Photo Album/Photo.bmp"
 FreeImage gPhoto
EndIf
;restore resolution
If optGameRes<>optMenuRes Then ChangeResolution(optMenuRes,1)
;record result
If game=1 And screenAgenda<>10 And matchPlayer=<no_wrestlers
 gamResult(slot,GetDate())=2
 If matchQuit=1 Then gamResult(slot,GetDate())=1
 If matchWinner>0 And pTeam(matchPlayer)<>pTeam(matchWinner) Then gamResult(slot,GetDate())=1
 If matchWinner>0 And pTeam(matchPlayer)=pTeam(matchWinner) Then gamResult(slot,GetDate())=3 : gamWins(slot,fed)=gamWins(slot,fed)+1
 gamMatches(slot,fed)=gamMatches(slot,fed)+1
EndIf
;proceed
If game=1 Then screen=17 Else screen=1
End Function

;-----------------------------------------------------------------
;////////////////////// RELATED FUNCTIONS ////////////////////////
;-----------------------------------------------------------------

;DRAW NAME LINE
Function DrawNameLine(cyc,x,y)
 ;coloured lines
 Color 100,0,0 : Line rX#(x),rY#(y)+2,rX#(610),rY#(y)+2
 Color 150,0,0 : Line rX#(x),rY#(y)+3,rX#(610),rY#(y)+3
 Color 180,180,180 : Line rX#(x),rY#(y)+5,rX#(610),rY#(y)+5
 Color 180,180,180 : Line rX#(x),rY#(y)+6,rX#(610),rY#(y)+6
 Color 0,0,150 : Line rX#(x),rY#(y)+8,rX#(610),rY#(y)+8
 Color 0,0,100 : Line rX#(x),rY#(y)+9,rX#(610),rY#(y)+9
 ;main text
 SetFont font(4)
 namer$=charName$(pChar(cyc))
 If matchTeams>0 Then affix$="'s Team" Else affix$=""
 If matchState=4 And cyc>0 Then affix$=affix$+" Wins!"
 If matchState=4 And cyc=0 Then namer$="Nobody won this contest..." : affix$=""
 OutlineStraight(namer$+affix$,rX#(x)+5,rY#(y)-16,0,0,0,255,255,255) 
 ;portrait
 If cyc>0 Then DrawImageRect charPhoto(pChar(cyc)),rX#(590),rY#(y)+8,0,0,70,55
 ;manager reference
 v=FindManager(cyc)
 If v>0 And matchState=<3 And matchTeams=<0
  adder=StringWidth(pName$(cyc))+7
  SetFont font(1)
  OutlineStraight("(w/ "+pName$(v)+")",(rX#(x)+5)+adder,rY#(y)-10,50,50,70,200,200,215)  
 EndIf 
 ;title references
 If cyc>0
  belter=0 : namer$=""
  If pChar(cyc)=fedChampTag(fed,1) Or pChar(cyc)=fedChampTag(fed,2)
   belter=3
   If matchTeams>0 Then namer$="Tag Champions" Else namer$="Tag Champion"
   If matchState=4 And pChar(cyc)<>fedOldChampTag(fed,1) And pChar(cyc)<>fedOldChampTag(fed,2) 
    namer$="NEW Tag Champions!"
   EndIf
  EndIf
  If pChar(cyc)=fedChampInter(fed)
   belter=2 : namer$="Inter Champion"
   If matchState=4 And pChar(cyc)<>fedOldChampInter(fed) Then namer$="NEW Inter Champion!"
  EndIf
  If pChar(cyc)=fedChampWorld(fed) 
   belter=1 : namer$="World Champion"
   If matchState=4 And pChar(cyc)<>fedOldChampWorld(fed) Then namer$="NEW World Champion!"
  EndIf
  If belter>0
   DrawImage gBelt(belter),rX#(x)+52,rY#(y)+15
   SetFont font(1)
   r=255 : g=200 : b=100
   If Left$(namer$,3)="NEW" Then g=Rnd(130,220)
   OutlineStraight(namer$,rX#(x)+92,rY#(y)+14,0,0,0,r,g,b)
  EndIf
 EndIf
End Function

;DRAW HEALTH METERS
Function DrawHealthMeters()
 ;assess heirarchy
 SetFont font(1)
 x=rX#(70) : y=rY#(60) : spacing=rX#(110)
 If no_wrestlers=2 Then x=rX#(300) : spacing=rX#(200)
 If no_wrestlers=3 Then x=rX#(250) : spacing=rX#(150)
 If no_wrestlers=4 Then x=rX#(175) : spacing=rX#(150)
 If no_wrestlers=5 Then x=rX#(160) : spacing=rX#(120)
 If no_wrestlers=6 Then x=rX#(125) : spacing=rX#(110)
 ;display meters
 For cyc=1 To no_wrestlers
  ;get co-ordinates
  If matchTeams>0
   If pTeam(cyc)=1 Then box=cyc
   If pTeam(cyc)=2 Then box=(cyc-(no_wrestlers/2))+15
   x=rX#(healthPosX(box)) : y=rY#(healthPosY(box))
  EndIf
  If camTim=0 Or x=>rX#(250) Or y>rY#(60)
   ;photo
   If optGameRes=>2 Then DrawImageRect charPhoto(pChar(cyc)),x,y+5,0,0,70,52
   ;health meters
   Color 130,0,0 : Rect x-50,y,100,5,1
   Color 200,0,0 : Rect x-49,y+1,98,3,1
   If optHealth=0 Then liner=pHealth(cyc)*2
   If optHealth=1 Then liner=pHealth(cyc)
   If optHealth=2 Then liner=pHealth(cyc)-(pHealth(cyc)/3)
   If pInjured(cyc)>0 Then Color 80,0,0 Else Color 0,130,0
   Rect x-50,y,liner,5,1
   If pInjured(cyc)>0 Then Color Rnd(130,220),0,0 Else Color 0,200,0
   Rect x-49,y+1,liner-2,3,1
   ;special meter
   Color 200,100,0 : Line x-50,y+5,x+49,y+5
   If pSpecial(cyc)>0 Then Color 255,Rnd(150,230),0 Else Color 250,230,0
   Line x-50,y+5,(x-50)+pHeat(cyc),y+5
   Color 250,150,0 : Line x-50,y+5,x-48,y+5
   ;injury indicator
   If pInjured(cyc)>0 Then DrawImage gInjury,x-55,y+2
   ;name line
   r=230 : g=215 : b=200
   If matchTeams=<1 And cyc=matchLeader And matchType=>3 And matchType=<4 Then r=250 : g=200 : b=150
   If matchTeams=2 And LegalMan(cyc) Then r=250 : g=200 : b=150
   If pEliminated(cyc)=1 Then r=100 : g=80 : b=80 
   If controlTim>0 And pControl(cyc)>0 Then r=150 : g=150 : b=220
   Outline(charName$(pChar(cyc)),x,y-5,0,0,0,r,g,b)
   If matchType=>2 And matchType=<3 Then Outline("Falls: "+pFalls(cyc),x,y+10,0,0,0,200,200,200)
  EndIf
  ;spacing
  x=x+spacing
  If x>rX#(730) Then x=rX#(70) : y=y+rY#(85)
 Next
End Function

;CROWD NOISE
Function CrowdNoise()
 ;determine volume limits
 If arenaCrowd=0 Then crowdVolMin#=0.0 : crowdVolMax#=0.05
 If arenaCrowd=1 Then crowdVolMin#=0.2 : crowdVolMax#=0.6
 If arenaCrowd=2 Then crowdVolMin#=0.5 : crowdVolMax#=0.8
 If arenaCrowd=3 Then crowdVolMin#=0.7 : crowdVolMax#=1.0
 ;fluctuate volume
 randy=Rnd(0,400)
 If randy=0 Then crowdVolTarget#=Rnd(crowdVolMin#,crowdVolMax#)
 If camZ#>485 Then crowdVolTarget#=crowdVolMax#/3
 If crowdVol#>crowdVolTarget# Then crowdVol#=crowdVol#-0.0015
 If crowdVol#<crowdVolTarget# Then crowdVol#=crowdVol#+0.0015
 If crowdVol#<crowdVolMin# Then crowdVol#=crowdVolMin# 
 If crowdVol#>crowdVolMax# Then crowdVol#=crowdVolMax# 
 ChannelVolume chCrowd,crowdVol#
 ;fluctuate pitch
 randy=Rnd(0,800)
 If randy=0 Then crowdPitchTarget#=Rnd(35000,48000)
 If randy=1 Then crowdPitchTarget#=Rnd(40000,45000) 
 If crowdPitch#>crowdPitchTarget# Then crowdPitch#=crowdPitch#-50
 If crowdPitch#<crowdPitchTarget# Then crowdPitch#=crowdPitch#+50
 ChannelPitch chCrowd,crowdPitch#
 ;random boring chant
 randy=Rnd(0,10000)
 If randy=0 And matchState=>2 And matchState=<3 Then Pop(0,11,0)
 ;animate cut-out's
 If arenaCrowd>0 And optCrowdAnim=1
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
End Function

;CROWD REACTION
Function Pop(cyc,sound,vol#)
 ;bind response to allegiance
 If cyc>0
  If sound=2 Or sound=3 Then sound=2+pHeel(cyc)
  If sound=4 Or sound=5 Then sound=4+pHeel(cyc) 
  If sound=6 Or sound=7 Then sound=6+pHeel(cyc)
 EndIf
 ;determine volume
 If vol#=0 Then vol#=Rnd(0.4,1.1) 
 randy=Rnd(0,2)
 If randy>0 And cyc>0
  popper#=(crowdVolMax#/90)*charPop(pChar(cyc))
  If vol#>popper# Then vol#=popper#
 EndIf
 If camZ#>485 Then vol#=crowdVolMax#/3
 If vol#>crowdVolMax# Then vol#=crowdVolMax#
 ;deliver sound
 If sound>1 And arenaCrowd>0
  SoundVolume sCrowd(sound),vol#
  PlaySound sCrowd(sound)
 EndIf
End Function

;RISK APPLAUSE
Function RiskApplause(cyc,chance)
 randy=Rnd(0,chance)
 If randy=0 Then Pop(cyc,9,0)
End Function

;MATCH ANALYSIS
Function MatchAnalysis()
 ;find wrestler with best health
 If matchType<>4
  matchLeader=0 : hi=0
  For cyc=1 To no_plays
   If pRole(cyc)=0 And pEliminated(cyc)=0 And pHealth(cyc)>hi Then matchLeader=cyc : hi=pHealth(cyc)
  Next
 EndIf
 ;find first fall victories
 If matchState=3 And matchType=1
  For cyc=1 To no_plays
   If pRole(cyc)=0 And pFalls(cyc)=>1 Then EndMatch(cyc)
  Next
 EndIf
 ;best of three
 If matchState=3 And matchType=2
  For cyc=1 To no_plays
   If pRole(cyc)=0 And matchTeams=<0 And pFalls(cyc)=>2 Then EndMatch(cyc)
   If pRole(cyc)=0 And matchTeams>0 And teamFalls(pTeam(cyc))=>2 Then EndMatch(cyc)
  Next
 EndIf
 ;ironman
 If matchState=3 And matchType=3
  matchLeader=0 : hi=0
  For cyc=1 To no_plays
   If pRole(cyc)=0 And matchTeams=<0 ;solo play
    If pFalls(cyc)>hi Then matchLeader=cyc : hi=pFalls(cyc)
    If matchLeader<>cyc And pFalls(cyc)=pFalls(matchLeader) Then matchLeader=0
   EndIf
   If pRole(cyc)=0 And matchTeams>0 ;team play
    If teamFalls(pTeam(cyc))>hi Then matchLeader=cyc : hi=teamFalls(pTeam(cyc))
    If pTeam(matchLeader)<>pTeam(cyc) And teamFalls(pTeam(cyc))=teamFalls(pTeam(matchLeader)) Then matchLeader=0
   EndIf
  Next
  If matchMins=matchTimeLim And matchLeader>0 Then EndMatch(matchLeader)
 EndIf
 ;last fall wins (24/7)
 If matchState=3 And matchType=4
  If matchMins=matchTimeLim And matchLeader>0 Then EndMatch(matchLeader)
 EndIf
 ;elimination
 If matchState=3 And matchType=5
  matchRemaining=0 : matchSurvivor=0
  teamMen(1)=0 : teamMen(2)=0
  For cyc=1 To no_plays
   If pRole(cyc)=0 And pEliminated(cyc)=0
    matchRemaining=matchRemaining+1 : matchSurvivor=cyc
    teamMen(pTeam(cyc))=teamMen(pTeam(cyc))+1
   EndIf
  Next
  If matchTeams=<0 And matchRemaining=1 Then EndMatch(matchSurvivor)
  If matchTeams>0 And teamMen(2)=0 Then EndMatch(teamLegal(1))
  If matchTeams>0 And teamMen(1)=0 Then EndMatch(teamLegal(2))
 EndIf
 ;time out
 If matchState=3 And matchTimeLim>0 And matchMins=matchTimeLim 
  matchWinStyle=0
  EndMatch(0)
  matchSecs=0
 EndIf
End Function

;END MATCH
Function EndMatch(cyc)
 ;reaction
 PlaySound sBell
 If cyc>0 Then Pop(cyc,2,1) : Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,0)
 If cyc=0 Then Pop(0,3,1) : Pop(0,7,1)
 crowdVolTarget#=crowdVolMax#
 If cyc>0 Then PlayTheme(pChar(cyc))
 ;update status
 matchState=4 : matchWinner=cyc
 If cyc>0 Then camFoc=cyc : camType=2
 ;affect fellow wrestlers
 If cyc>0
  For v=1 To no_plays
   ;excite team-mates
   If pTeam(v)=pTeam(cyc) And pAnim(v)=>0 And pAnim(v)=<3 Then ChangeAnim(v,5) : pAnimTim(v)=0 
   ;lose hair
   If matchReward=5 And pRole(v)=0 And pTeam(v)<>pTeam(cyc) 
    Pop(0,7,1) : Pop(0,8,1)
    RemoveHair(v) : pDazed(v)=500
    For coz=1 To 3
     If charHairStyle(pChar(v),coz)>1 Then charHairStyle(pChar(v),coz)=1
    Next
   EndIf
   ;leave promotion
   If matchReward=6 And pRole(v)=0 And pTeam(v)<>pTeam(cyc)
    If game=0 And CharBusy(pChar(v))=0 And fedSize(7)<optFedLim Then Trade(pChar(v),7)
   EndIf
  Next
 EndIf
 ;lose titles?
 If cyc>0 And matchWinStyle>0
  If matchReward=2 Then fedChampWorld(fed)=pChar(cyc)
  If matchReward=3 Then fedChampInter(fed)=pChar(cyc)
  If matchReward=4 
   fedChampTag(fed,1)=pChar(cyc)
   fedChampTag(fed,2)=pChar(cyc)
   For v=1 To no_wrestlers  
    If cyc<>v And pTeam(v)=pTeam(cyc) Then fedChampTag(fed,2)=pChar(v)
   Next
  EndIf
 EndIf
End Function

;PLAY THEME
Function PlayTheme(char)
 ;remove existing music
 If ChannelPlaying(chTheme)>0 Then StopChannel chTheme
 ;loop and emit file
 LoopSound sTheme(charTheme(char))
 If screen<>50 Then chTheme=PlaySound(sTheme(charTheme(char)))
 If screen=50 Then chTheme=EmitSound(sTheme(charTheme(char)),cam)
 ;adjust pitch & volume
 ChannelPitch chTheme,charThemePitch(char)
 chThemeVol#=1
 ChannelVolume chTheme,chThemeVol#
End Function

;RESTORE MAIN THEME
Function RestoreTheme()
 If ChannelPlaying(chTheme)>0 Then StopChannel chTheme
 LoopSound sTheme(1)
 chTheme=PlaySound(sTheme(1))
 ChannelPitch chTheme,44100
 ChannelVolume chTheme,1.0
End Function

;INITIATE MARQUE MESSAGE
Function PostMessage(cyc,topic,colour)
 ;reset status
 marX#=900 : marY#=30 : marColour=colour
 ;scored fall
 If topic=>1 And topic=<3
  g=GetGender(pChar(cyc))
  affix$=""
  If matchTeams>0 Then affix$="team's "
  If matchWinStyle=0 Then marScript$=pName$(cyc)+" has scored a DQ, bringing "+Lower$(His$(g))+" "+affix$+"total to "+pFalls(cyc)+" falls..."
  If matchWinStyle=1 Then marScript$=pName$(cyc)+" has scored a pinfall, bringing "+Lower$(His$(g))+""+affix$+"total to "+pFalls(cyc)+" falls..."
  If matchWinStyle=2 Then marScript$=pName$(cyc)+" has scored a submission, bringing "+Lower$(His$(g))+" "+affix$+"total to "+pFalls(cyc)+" falls..."
 EndIf
 ;taken lead
 If topic=4 
  If matchWinStyle=0 Then marScript$=pName$(cyc)+" has taken the lead by disqualification..." 
  If matchWinStyle=1 Then marScript$=pName$(cyc)+" has taken the lead with a pinfall..." 
  If matchWinStyle=2 Then marScript$=pName$(cyc)+" has taken the lead with a submission..." 
 EndIf
 ;eliminated
 If topic=5 
  If matchWinStyle=0 Then marScript$=pName$(cyc)+" has been ELIMINATED by disqualification..."
  If matchWinStyle=1 Then marScript$=pName$(cyc)+" has been ELIMINATED by pinfall..."
  If matchWinStyle=2 Then marScript$=pName$(cyc)+" has been ELIMINATED by submission..."
  If matchWinStyle=3 Then marScript$=pName$(cyc)+" has been ELIMINATED by knock-out..."
  If matchWinStyle=4 Then marScript$=pName$(cyc)+" has been ELIMINATED by bloodshed..."
  If matchWinStyle=5 Then marScript$=pName$(cyc)+" has been ELIMINATED by ring-out..."
 EndIf
 ;clock issues
 If topic=10 Then marScript$="1 minute until the BLAST!"
 If topic=11 Then marScript$="5 minutes remaining..."
 If topic=12 Then marScript$="1 minute remaining!"
End Function