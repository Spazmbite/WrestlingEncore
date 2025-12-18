;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: PLAYERS -------------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;/////////////////////// PREPARE PLAYERS /////////////////////////
;-----------------------------------------------------------------
Function PreparePlayers()
 ;reset team stats
 matchPlayer=0 : matchMulti=0
 teamFalls(1)=0 : teamFalls(2)=0
 teamLegal(1)=0 : teamLegal(2)=0
 ;find managers
 manage=0
 If matchTeams=>0
  If optManagers=1 And no_wrestlers<4 Then manage=1
  If optManagers=2 And no_wrestlers<10 Then manage=1
  If game=1 And optManagers>0 And gamSchedule(slot,GetDate())=5 Then manage=1
 EndIf
 If manage=1
  For cyc=1 To no_wrestlers
   char=charManager(pChar(cyc))
   If game=1 And gamSchedule(slot,GetDate())=5 Then char=fedBooker(charFed(pChar(cyc)))
   If char>0 And FindCharacter(char)=0 And charInjured(char)=0 And no_plays<optPlayLim 
    If game=1 And pChar(cyc)=gamChar(slot) And gamSchedule(slot,GetDate())<>5 Then gamManaged=1
    no_plays=no_plays+1
    pChar(no_plays)=char
   EndIf
  Next
 EndIf
 ;make room for intruders
 If matchTeams=>0 And screenAgenda<>10
  If optExtras=>1 And no_wrestlers<4 And no_plays<optPlayLim Then AddIntruder()
  If optExtras=2 And no_wrestlers<10 And no_plays<optPlayLim Then AddIntruder()
 EndIf
End Function

;CHARACTER PRESENT?
Function FindCharacter(char)
 value=0
 For v=1 To optPlayLim
  If char>0 And pChar(v)=char Then value=1
 Next
 Return value
End Function

;FIND MANAGER
Function FindManager(cyc)
 value=0
 For v=1 To no_plays
  If pRole(v)=2 
   If pChar(v)=charManager(pChar(cyc)) Then value=v
   If game=1 And gamSchedule(slot,GetDate())=5 And pChar(v)=fedBooker(charFed(pChar(cyc))) Then value=v
  EndIf 
 Next
 Return value
End Function

;FIND CLIENT
Function FindClient(cyc)
 value=0
 For v=1 To no_wrestlers
  If optManagers>0 And charManager(pChar(v))=pChar(cyc) Then value=v
  If game=1 And gamSchedule(slot,GetDate())=5 And pChar(cyc)=fedBooker(charFed(pChar(v))) Then value=v
 Next
 Return value
End Function

;ADD INTRUDER
Function AddIntruder()
 no_plays=no_plays+1
 Repeat
  char=fedRoster(fed,Rnd(1,fedSize(fed)))
  ;assess range
  randy=Rnd(1,8)
  If game=1 
   If gamSchedule(slot,GetDate())=>3 Or gamInterProm(slot,GetDate())>0 Then randy=Rnd(-5,8)
  EndIf
  ;universal wrestler
  If randy=<1 Then char=Rnd(1,no_chars) 
  ;local wrestler
  If randy=>2 And randy=<4 Then char=fedRoster(fed,Rnd(1,fedSize(fed))) 
  ;consider relationships
  v=Rnd(1,no_wrestlers)
  If randy=>5 And randy=<6 And charFriend(pChar(v))>0 Then char=charFriend(pChar(v))
  If randy=>7 And randy=<8 And charEnemy(pChar(v))>0 Then char=charEnemy(pChar(v)) 
 Until char>0 And FindCharacter(char)=0 And CharBusy(char)=0 And charFed(char)=<8 And charInjured(char)=0
 pChar(no_plays)=char
End Function

;-----------------------------------------------------------------
;//////////////////////// LOAD PLAYERS ///////////////////////////
;-----------------------------------------------------------------
Function LoadPlayers()
 For cyc=1 To no_plays
  ;assign role
  pRole(cyc)=0
  If no_refs>0 And cyc>no_wrestlers Then pRole(cyc)=1
  If cyc>no_wrestlers+no_refs 
   If FindClient(cyc)>0 Then pRole(cyc)=2 Else pRole(cyc)=3
  EndIf
  ;assign costume
  pCostume(cyc)=1
  If pRole(cyc)=1 Then pCostume(cyc)=3
  If pRole(cyc)=>2 Or matchPreset=<1 Or screenAgenda=10 Then pCostume(cyc)=2
  randy=Rnd(0,4)
  If (randy=0 Or DesignatedRef(pChar(cyc))) And pRole(cyc)=3 And no_refs>0 Then pCostume(cyc)=3
  ;assign team
  pTeam(cyc)=cyc
  If matchTeams>0 And pRole(cyc)=0
   If cyc=<no_wrestlers/2 Then pTeam(cyc)=1 Else pTeam(cyc)=2
  EndIf
  If teamLegal(pTeam(cyc))=0 Then teamLegal(pTeam(cyc))=cyc
  ;clock controls
  If game=1 And pChar(cyc)=gamChar(slot) Then pControl(cyc)=3
  If pControl(cyc)>0 And matchPlayer>0 Then matchMulti=1
  If pControl(cyc)>0 And matchPlayer=0 Then matchPlayer=cyc
  ;loading message
  namer$=charName$(pChar(cyc))
  If pRole(cyc)=1 Then namer$="Referees"
  If pRole(cyc)=2 Then namer$="Managers"
  If pRole(cyc)=3 Then namer$="Extras"
  MatchLoader("Please Wait","Loading "+namer$,cyc)
  ;generate model
  p(cyc)=LoadAnimMesh("Models/Bodies/"+textModel$(charModel(pChar(cyc),pCostume(cyc)))+".3ds")
  LoadSequences(cyc)
  ApplyCostume(cyc,pChar(cyc),pCostume(cyc))
  ;apply eyes
  pEyes(cyc)=charEyes(pChar(cyc)) : pOldEyes(cyc)=-1
  If tEyes(pEyes(cyc))>0 Then EntityTexture pLimb(cyc,1),tEyes(pEyes(cyc)),0,3
  ;no scars by default
  For limb=1 To 40
   If charLimb(pChar(cyc),limb)=0 Then pScar(cyc,limb)=5 Else pScar(cyc,limb)=0
  Next
  ;hide weapons by default
  For v=1 To weapList
   If v=16 Or v=17 Then EntityAlpha FindChild(p(cyc),weapFile$(v)),0.75
   HideEntity FindChild(p(cyc),weapFile$(v))
  Next
  ;location
  pOutTim(cyc)=0
  pX#(cyc)=Rnd(-50,50) : pZ#(cyc)=Rnd(-50,50)
  pY#(cyc)=wStage# : pA#(cyc)=Rnd(0,360)
  If pRole(cyc)=0 ;wrestler line-up
   pX#(cyc)=Rnd(-6,3) : pZ#(cyc)=490+(10*(no_wrestlers-cyc))
   pY#(cyc)=wGround# : pA#(cyc)=180
   If matchOfficial=0 ;backstage variations
    pX#(cyc)=Rnd(-180,220) : pZ#(cyc)=Rnd(600,810)
    pA#(cyc)=Rnd(0,360) : pOutTim(cyc)=1000
    If game=1
     If negSetting=2 Then pX#(1)=170 : pZ#(1)=749
     If negSetting=3 Then pX#(1)=-150 : pZ#(1)=749
     pX#(2)=pX#(1)+20 : pZ#(2)=736
     If negTopic=15 And cyc=3 Then pX#(cyc)=-50 : pZ#(cyc)=840 : pA#(cyc)=180
     If negTopic=15 And cyc=4 Then pX#(cyc)=-30 : pZ#(cyc)=840 : pA#(cyc)=180
    EndIf
   EndIf
  EndIf
  If pRole(cyc)=2 ;manage client
   v=FindClient(cyc) : pTeam(cyc)=pTeam(v)
   pX#(cyc)=pX#(v)-5 : pZ#(cyc)=pZ#(v)+10
   pY#(cyc)=wGround# : pA#(cyc)=180
   If matchOfficial=0 Then pX#(cyc)=pX#(v)-10 : pZ#(cyc)=pZ#(v)-10 : pA#(cyc)=315 : pOutTim(cyc)=1000
  EndIf
  If pRole(cyc)=3 ;random intrusion
   randy=Rnd(-6,7)
   If matchOfficial=0 Then randy=0
   If randy=<0 Then pX#(cyc)=Rnd(-180,220) : pZ#(cyc)=Rnd(580,830) ;backstage
   If randy=1 Then pX#(cyc)=-60 : pZ#(cyc)=Rnd(180,450) ;left aisle
   If randy=2 Then pX#(cyc)=55 : pZ#(cyc)=Rnd(180,450) ;right aisle
   If randy=3 Then pX#(cyc)=-170 : pZ#(cyc)=Rnd(-150,150) ;left of ring
   If randy=4 Then pX#(cyc)=170 : pZ#(cyc)=Rnd(-150,150) ;right of ring
   If randy=5 Then pX#(cyc)=Rnd(-170,-60) : pZ#(cyc)=180 ;upper left of ring
   If randy=6 Then pX#(cyc)=Rnd(55,170) : pZ#(cyc)=180 ;upper right of ring
   If randy=7 Then pX#(cyc)=Rnd(-170,170) : pZ#(cyc)=-180 ;bottom of ring
   pY#(cyc)=wGround#
  EndIf
  pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
  pTX#(cyc)=pX#(cyc) : pTZ#(cyc)=pZ#(cyc) : pTA#(cyc)=pA#(cyc)
  pSubX#(cyc)=999 : pSubZ#(cyc)=999
  PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity p(cyc),0,pA#(cyc),0
  scaler#=charHeight(pChar(cyc))*0.0025
  ScaleEntity p(cyc),0.34+scaler#,0.34+scaler#,0.34+scaler#
  Animate p(cyc),1,0.3,pSeq(cyc,1),0
  ;reset values
  pFoc(cyc)=0
  pAnim(cyc)=0 : pAnimTim(cyc)=0
  pPlatform(cyc)=0 : pMomentum(cyc)=0
  pExcusedWorld(cyc)=0 : pExcusedEdges(cyc)=0
  pExcusedPlays(cyc)=0 : pExcusedItems(cyc)=0
  pCarrying(cyc)=0 : pHolding(cyc)=0
  pGrappling(cyc)=0 : pGrappler(cyc)=0 : pStretched(cyc)=0
  pPinning(cyc)=0 : pPinner(cyc)=0
  pFalls(cyc)=0 : pEliminated(cyc)=0
  pRefCount(cyc)=0 : pAutoTim(cyc)=0
  pSubmitDelay(cyc)=0 : pDQDelay(cyc)=0
  ;health issues
  pHealth(cyc)=100 
  If game=1 Then pHealth(cyc)=charHealth(pChar(cyc))
  If optHealth=0 Then pHealth(cyc)=pHealth(cyc)/2
  If optHealth=2 Then pHealth(cyc)=pHealth(cyc)+(pHealth(cyc)/2)
  pHealthLimit(cyc)=pHealth(cyc)
  pHP(cyc)=10 : pHeat(cyc)=30
  pInjured(cyc)=0 : pDazed(cyc)=0
  If game=1 And charInjured(pChar(cyc))>0 Then pInjured(cyc)=1
  ;status
  pName$(cyc)=charName$(pChar(cyc))
  pHeel(cyc)=charHeel(pChar(cyc))
  pDefSpeed#(cyc)=0.2+(Float(charAgl(pChar(cyc)))/100)
  ;shadow
  pShadow(cyc)=LoadSprite("World/Sprites/Shadow.bmp",12)
  ScaleSprite pShadow(cyc),10,10
  RotateEntity pShadow(cyc),90,0,0
  SpriteViewMode pShadow(cyc),2
  EntityAlpha pShadow(cyc),0.1
  ;tags
  If optTags>0
   pTag(cyc)=CreateSprite()
   SpriteViewMode pTag(cyc),1
   EntityFX pTag(cyc),1
   If optTags=1 Then EntityAlpha pTag(cyc),0.7
   HideEntity pTag(cyc)
  EndIf
  ;visibility
  pHidden(cyc)=0
  If (matchTeams=-1 And pRole(cyc)=0) Or pRole(cyc)=3
   If optHideElim=1 And no_wrestlers>4 Then pHidden(cyc)=1
   If optHideElim=2 Then pHidden(cyc)=1
  EndIf
 Next
End Function

;------------------------------------------------------------
;///////////////////// PLAYER CYCLE /////////////////////////
;------------------------------------------------------------
Function PlayerCycle()
 ;MONITOR NEW INPUT
 For cyc=1 To no_plays
  ;reset counters
  pFocTim(cyc)=pFocTim(cyc)-1
  If pFocTim(cyc)<0 Then pFocTim(cyc)=0
  pFriction(cyc)=pFriction(cyc)-1
  If pFriction(cyc)<0 Then pFriction(cyc)=0
  If pAnim(cyc)<>109 Then pMomentum(cyc)=pMomentum(cyc)-1
  If pMomentum(cyc)<0 Or (pAnim(cyc)=>60 And pAnim(cyc)=<90) Then pMomentum(cyc)=0
  pChaosTim(cyc)=pChaosTim(cyc)-1
  If pChaosTim(cyc)<0 Then pChaosTim(cyc)=0
  ;monitor status
  MonitorStatus(cyc)
  ;check for input
  GetInput(cyc)
  ;manage entrances
  ManageEntrances(cyc)
  ;translate input
  If gotim>0 And pOutTim(cyc)>0 And pHidden(cyc)=0
   TranslateInput(cyc)
  EndIf
  ;adjust angle
  AdjustAngle(cyc)
  ;manage animations
  Animations(cyc)
  ;footsteps
  speeder#=2.5-pSpeed#(cyc)
  If speeder#<0.5 Then speeder#=0.5
  If pStepTim(cyc)>speeder#*10 And pHidden(cyc)=0
   vol#=Rnd(0.4,0.6)
   If pAnim(cyc)=3 Or pAnim(cyc)=4 Then vol#=Rnd(0.6,0.8)
   If InsideRing(pX#(cyc),pZ#(cyc),0) Then stepper=Rnd(1,4) Else stepper=Rnd(5,6)
   ProduceSound(p(cyc),sStep(stepper),22050,vol#)
   pStepTim(cyc)=0
  EndIf
  ;enforce blocks
  If pHidden(cyc)=0 Then EnforceBlocks(cyc)
  ;automated refereeing
  If matchState=3 Then AutomatedRefs(cyc)
  ;trigger interferences
  randy=Rnd(0,10000)
  If optHealth=0 Then randy=Rnd(0,5000)
  If randy=0 And matchState=>3 And matchMins>1 And pRole(cyc)=3 And pOutTim(cyc)=0
   needed=0
   For v=1 To no_plays
    If pRole(v)=0 And (pHealth(v)<pHealthLimit(v)/2 Or pInjured(v)>0) Then needed=v
   Next
   If needed>0
	pOutTim(cyc)=1 : pAgenda(cyc)=1 : pHeat(cyc)=90
	randy=Rnd(-2,2)
	If randy<0 Then pTeam(cyc)=pTeam(needed) Else pTeam(cyc)=randy
	For v=1 To no_wrestlers
	 If game=1 And pChar(v)=gamChar(slot) And pChar(v)=charFriend(pChar(cyc)) 
	  betrayal=Rnd(0,5)
	  If betrayal=0 Then charFriend(pChar(cyc))=0 : charEnemy(pChar(cyc))=pChar(v) ;risk betrayal! 
	 EndIf
	 If randy>0 And charHeel(pChar(v))=charHeel(pChar(cyc)) Then pTeam(cyc)=pTeam(v) ;support like allegiance
	 If randy>0 And charHeel(pChar(v))<>charHeel(pChar(cyc)) And pTeam(cyc)=pTeam(v) Then pTeam(cyc)=pTeam(cyc)+1 ;desert enemy allegiance
	 If game=1 And gamSchedule(slot,GetDate())=>3
	  If charFed(pChar(v))=charFed(pChar(cyc)) Then pTeam(cyc)=pTeam(v) ;support same fed
	  If charFed(pChar(v))<>charFed(pChar(cyc)) And pTeam(cyc)=pTeam(v) Then pTeam(cyc)=pTeam(cyc)+1 ;desert enemy fed 
	 EndIf
	 If pChar(v)=charFriend(pChar(cyc)) Then pTeam(cyc)=pTeam(v) ;support friends
	 If pChar(v)=charEnemy(pChar(cyc)) And pTeam(cyc)=pTeam(v) Then pTeam(cyc)=pTeam(cyc)+1 ;desert enemies  
	Next 
	If pCostume(cyc)=3 Then pRole(cyc)=1 : pTeam(cyc)=0 ;referee interference!
	;If pCostume(cyc)=<2 And matchRules=0 And matchType<>5 Then pRole(cyc)=0 : pTeam(cyc)=cyc ;hardcore contender
	If camZ#>500 Then pOutTim(cyc)=2
	GetNewFoc(cyc)
   EndIf
  EndIf 
  ;trigger manager betrayal
  If game=1 And matchState=3 And pRole(cyc)=2 And pChar(cyc)=charManager(gamChar(slot))
   chance=15000+(optHealth*5000)
   randy=Rnd(0,chance)
   If randy=0
    charFriend(pChar(cyc))=0 : charEnemy(pChar(cyc))=gamChar(slot)
    pTeam(cyc)=pTeam(matchPlayer)+1
    pFoc(cyc)=matchPlayer
   EndIf
  EndIf
 Next
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;DELIVER REAL X POSITION
Function RealX#(cyc)
 x#=pX#(cyc)
 If PointViable(cyc)>0 Then x#=EntityX(pLimb(cyc,30),1)
 Return x#
End Function

;DELIVER REAL Y POSITION
Function RealY#(cyc)
 y#=pY#(cyc)
 If PointViable(cyc)=1 Then y#=EntityY(pLimb(cyc,30),1)-20
 Return y#
End Function

;DELIVER REAL Z POSITION
Function RealZ#(cyc)
 z#=pZ#(cyc)
 If PointViable(cyc)>0 Then z#=EntityZ(pLimb(cyc,30),1)
 Return z#
End Function

;PRESERVE OLD POSITIONS
Function PreserveOldPositions()
 For cyc=1 To no_plays
  ;standard
  If PointViable(cyc)=0 Then pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
  ;projected
  If PointViable(cyc)>0 And pExcusedWorld(cyc)=0
   If FindBlocks(RealX#(cyc),RealZ#(cyc),pY#(cyc))=0
    pOldX#(cyc)=RealX#(cyc)
    pOldZ#(cyc)=RealZ#(cyc)
   EndIf
  EndIf 
  ;angle 
  pOldA#(cyc)=pA#(cyc)
 Next
End Function

;DISPLAY PLAYERS
Function DisplayPlayers()
 For cyc=1 To no_plays
  If pHidden(cyc)=0
   ;paint scars
   ManageScars(cyc)
   ;facial expressions
   If GrimaceViable(cyc) 
    pSpeaking(cyc)=1
    pEyes(cyc)=charEyes(pChar(cyc))-1
    If pEyes(cyc)<1 Then pEyes(cyc)=1
   EndIf
   If matchState=2 And speaker=cyc Then pSpeaking(cyc)=1
   FacialExpressions(cyc) 
   ;update display
   pA#(cyc)=CleanAngle#(pA#(cyc))
   RotateEntity p(cyc),0,pA#(cyc),0
   PositionEntity p(cyc),pX#(cyc),pY#(cyc)+Elevation#(cyc),pZ#(cyc)
   ;shadow (and determine ground)
   limb=pLimb(cyc,30)
   If InsideRing(EntityX(limb,1),EntityZ(limb,1),-12) Then pGround#(cyc)=wStage# Else pGround#(cyc)=wGround#
   If pPlatform(cyc)=>101 And pPlatform(cyc)=<104 Then pGround#(cyc)=wStage#
   PositionEntity pShadow(cyc),EntityX(limb,1),pGround#(cyc)+0.4,EntityZ(limb,1)
   ;display tags
   DisplayTags(cyc)
  EndIf
  ;hiding process
  ;If KeyDown(87) And pHidden(cyc)=0 Then pHidden(cyc)=1
  If pHidden(cyc)=1
   HideEntity p(cyc)
   HideEntity pShadow(cyc)
   HideEntity pTag(cyc)
   pHidden(cyc)=2
  EndIf
 Next
End Function

;DISPLAY TAGS
Function DisplayTags(cyc)
 ;get tags
 tag=0
 If pInjured(cyc)>0 Then tag=tagInjured ;injured
 If pSpecial(cyc)>0 Then tag=tagSpecial ;special
 If matchState=3
  If matchTeams=2 And LegalMan(cyc) And pChaosTim(cyc)>0 Then tag=tagLegal ;legal man
  If matchRules=2 And LegalMan(cyc) And pHolding(cyc)>0 And InsideRing(RealX#(cyc),RealZ#(cyc),0) 
   If FindReferee(1)>0 Then tag=tagWarning ;DQ warning
  EndIf
  If pEliminated(cyc) And no_wrestlers>2 Then tag=tagEliminated ;eliminated
  If LegalMan(cyc)=0 And pRole(cyc)<>1 And pRole(cyc)<>3 And pChaosTim(cyc)=0 And InsideRing(RealX#(cyc),RealZ#(cyc),-15)
   tag=tagOut ;leave ring
  EndIf 
 EndIf
 If controlTim>0 And pControl(cyc)>0 Then tag=tagControl
 ;pinning tags
 If matchState=3 And no_refs=0 And pPinning(cyc)>0 ;wrestler
  count=FindPinCount(pPinning(cyc))
  If count>0 Then tag=tagCount(count)
 EndIf
 If matchState=3 And pRole(cyc)=1 And pPinner(pRefVictim(cyc))>0 ;referee
  If pRefCount(cyc)>0 And pAnim(cyc)=>96 And pAnim(cyc)=<99 Then tag=tagCount(pRefCount(cyc))
 EndIf
 ;show tags
 If tag>0 And optTags>0
  If tag<>pOldTag(cyc)
   ShowEntity pTag(cyc)
   EntityTexture pTag(cyc),tag 
   ScaleSprite pTag(cyc),12,5
   EntityColor pTag(cyc),255,255,255
   If tag=tagInjured And pInjured(cyc)=>2 Then EntityColor pTag(cyc),150,150,150 
   If tag=tagSpecial Then ScaleSprite pTag(cyc),12,3 
   If tag=tagOut Then ScaleSprite pTag(cyc),10,4 
   If tag=tagWarning Then ScaleSprite pTag(cyc),9,4 
   If tag=tagEliminated Then ScaleSprite pTag(cyc),10,4 
   If tag=tagControl Then ScaleSprite pTag(cyc),10,4 
   If tag=tagCount(1) Or tag=tagCount(2) Or tag=tagCount(3) Then ScaleSprite pTag(cyc),20,5 
  EndIf
  If tag=tagSpecial Or tag=tagWarning Or (tag=tagInjured And pInjured(cyc)=>2)
   randy=Rnd(0,1)
   If randy=0 Then pTagX#(cyc)=Rnd(-0.5,0.5) : pTagY#(cyc)=Rnd(-0.5,0.5) : pTagZ#(cyc)=Rnd(-0.5,0.5)
  EndIf
  x#=EntityX(pLimb(cyc,30),1)+pTagX#(cyc)
  y#=(EntityY(pLimb(cyc,30),1)+20)+pTagY#(cyc)
  z#=EntityZ(pLimb(cyc,30),1)+pTagZ#(cyc)
  PositionEntity pTag(cyc),x#,y#,z#
 EndIf
 If tag=0 And pOldTag(cyc)>0 And optTags>0 Then HideEntity pTag(cyc)
 pOldTag(cyc)=tag 
End Function

;ELEVATE LYING PLAYERS
Function Elevation#(cyc)
 ;get co-ordinates
 If AttackViable(cyc)=2 And pAnim(cyc)<>91
  ResetDummy(cyc)
  MoveEntity dummy,0,0,-5
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 Else
  checkX#=EntityX(pLimb(cyc,3),1)
  checkZ#=EntityZ(pLimb(cyc,3),1)
 EndIf
 ;elevated by items?
 debris#=0
 If pAnim(cyc)<>30
  For v=1 To no_items
   If ItemProximity(cyc,v,30) And pY#(cyc)=iY#(v) And iState(v)=1 And iCarrier(v)<>cyc
    ScanItem(v,-2)
    If ItemCollide(cyc,v,checkX#,checkZ#,0) And iElevate#(iType(v))>debris#
     debris#=iElevate#(iType(v))
    EndIf
   EndIf
  Next
 EndIf
 ;elevated by humans?
 humans#=0
 If pAnim(cyc)<90 Or pAnim(cyc)>94
  If AttackViable(cyc)=2 Or pAnim(cyc)=52 Or pAnim(cyc)=53 Or pAnim(cyc)=109 Then range=12 Else range=8
  For v=1 To no_plays
   ontop=0
   If pDT(cyc)<pDT(v) Or pAnim(cyc)=52 Or pAnim(cyc)=53 Then ontop=1
   If pDT(cyc)=pDT(v) And pAnimTim(cyc)<pAnimTim(v) Then ontop=1
   If cyc<>v And ontop=1 And AttackViable(v)=2 And v<>pGrappling(cyc) And v<>pGrappler(cyc)
    If checkX#>pX#(v)-range And checkX#<pX#(v)+range And checkZ#>pZ#(v)-range And checkZ#<pZ#(v)+range And pY#(cyc)>pY#(v)-5 And pY#(cyc)<pY#(v)+5
     humans#=3.6
     If (charModel(pChar(cyc),pCostume(cyc))=>11 And charModel(pChar(cyc),pCostume(cyc))=<13) Or charModel(pChar(cyc),pCostume(cyc))=17 Then humans#=humans#+0.5
     If (charModel(pChar(v),pCostume(v))=>11 And charModel(pChar(v),pCostume(v))=<13) Or charModel(pChar(v),pCostume(v))=17 Then humans#=humans#+0.5
     ;casual pinning! 
     If pAnim(cyc)=>80 And pAnim(cyc)=<85 And pAnim(v)=80 And pPinning(cyc)=0 And pPinning(v)=0 And pPinner(cyc)=0 And pPinner(v)=0 And pElevation#(cyc)=>pElevation#(v) 
      pPinning(cyc)=v : pPinner(v)=cyc 
      If pRefCount(cyc)<3 Then pRefCount(cyc)=0
      pAutoTim(cyc)=0
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;lose casual pin
 v=pPinning(cyc)
 If v>0 And pAnim(cyc)<>90 And pAnim(cyc)<>91
  If humans#=0 Or pAnim(v)<>80 Or pAnim(cyc)<80 Or pAnim(cyc)>91
   If FindPinCount(v)>0 Then Pop(v,Rnd(2,7),0)
   pPinner(v)=0 : pPinning(cyc)=0
   If pAnim(cyc)=80 Or pAnim(cyc)=82 Or pAnim(cyc)=83 Or pAnim(cyc)=85 Then ChangeAnim(cyc,GroundResponse(cyc))
  EndIf
 EndIf
 ;calculate
 pElevator#(cyc)=debris#+humans#
 For count=1 To 5
  If pElevation#(cyc)<pElevator#(cyc) Then pElevation#(cyc)=pElevation#(cyc)+0.1
  If pElevation#(cyc)>pElevator#(cyc) Then pElevation#(cyc)=pElevation#(cyc)-0.1
 Next
 If pElevation#(cyc)<0 Then pElevation#(cyc)=0
 Return pElevation#(cyc)
End Function

;APPLY MOVEMENT
Function ApplyMovement(cyc,speed#)
 ;directional movement
 If cLeft(cyc)=1 Then pX#(cyc)=pX#(cyc)-speed# : pTA#(cyc)=90
 If cRight(cyc)=1 Then pX#(cyc)=pX#(cyc)+speed# : pTA#(cyc)=270
 If cUp(cyc)=1 Then pZ#(cyc)=pZ#(cyc)+speed# : pTA#(cyc)=0
 If cDown(cyc)=1 Then pZ#(cyc)=pZ#(cyc)-speed# : pTA#(cyc)=180
 ;diagonal angles
 If cLeft(cyc)=1 And cUp(cyc)=1 Then pTA#(cyc)=45
 If cLeft(cyc)=1 And cDown(cyc)=1 Then pTA#(cyc)=135
 If cRight(cyc)=1 And cUp(cyc)=1 Then pTA#(cyc)=315
 If cRight(cyc)=1 And cDown(cyc)=1 Then pTA#(cyc)=225
 ;flip angle for backtracking
 If pAnim(cyc)=32 Or pAnim(cyc)=85 Or pAnim(cyc)=102
  pTA#(cyc)=CleanAngle#(pTA#(cyc)+180)
 EndIf
 ;prevent targets when static
 If speed#>0 And DirPressed(cyc)=0 Then pTA#(cyc)=pA#(cyc)
End Function

;CLIMBING MOVEMENT
Function ClimbingMovement(cyc,speed#)
 ;north orientation
 If pPlatform(cyc)=100 Or pPlatform(cyc)=101 Or pPlatform(cyc)=113 
  If cLeft(cyc)=1 Then pX#(cyc)=pX#(cyc)-speed#
  If cRight(cyc)=1 Then pX#(cyc)=pX#(cyc)+speed#
  If cUp(cyc)=1 Then pY#(cyc)=pY#(cyc)+(speed#-(speed#/3))
  If cDown(cyc)=1 Then pY#(cyc)=pY#(cyc)-(speed#+(speed#/3))
 EndIf
 ;east orientation
 If pPlatform(cyc)=102 Or pPlatform(cyc)=114 
  If cUp(cyc)=1 Then pZ#(cyc)=pZ#(cyc)+speed#
  If cDown(cyc)=1 Then pZ#(cyc)=pZ#(cyc)-speed#
  If cLeft(cyc)=1 Then pY#(cyc)=pY#(cyc)-(speed#+(speed#/3))
  If cRight(cyc)=1 Then pY#(cyc)=pY#(cyc)+(speed#-(speed#/3))
 EndIf
 ;south orientation
 If pPlatform(cyc)=103 Or pPlatform(cyc)=111 
  If cLeft(cyc)=1 Then pX#(cyc)=pX#(cyc)-speed#
  If cRight(cyc)=1 Then pX#(cyc)=pX#(cyc)+speed#
  If cUp(cyc)=1 Then pY#(cyc)=pY#(cyc)-(speed#+(speed#/3))
  If cDown(cyc)=1 Then pY#(cyc)=pY#(cyc)+(speed#-(speed#/3))
 EndIf
 ;west orientation
 If pPlatform(cyc)=104 Or pPlatform(cyc)=112 
  If cUp(cyc)=1 Then pZ#(cyc)=pZ#(cyc)+speed#
  If cDown(cyc)=1 Then pZ#(cyc)=pZ#(cyc)-speed#
  If cLeft(cyc)=1 Then pY#(cyc)=pY#(cyc)+(speed#-(speed#/3))
  If cRight(cyc)=1 Then pY#(cyc)=pY#(cyc)-(speed#+(speed#/3))
 EndIf
End Function

;ADVANCE ENTITY
Function Advance(cyc,angle#,distance#)
 ;position
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 ;advance
 MoveEntity dummy,0,0,distance#
 pX#(cyc)=EntityX(dummy) : pZ#(cyc)=EntityZ(dummy)
End Function

;ADJUST ANGLE
Function AdjustAngle(cyc)
 If TurnViable(cyc)>0 And (pOutTim(cyc)>0 Or pRole(cyc)=3)
  ;get target angle from focus
  override=0
  If DirPressed(cyc) And pPlatform(cyc)=>5 Then override=1
  If override=0 And pFoc(cyc)>0 And cRun(cyc)=0 And TurnViable(cyc)=<2 And pDazed(cyc)=0
   If PointViable(pFoc(cyc))=0 Then PointEntity p(cyc),p(pFoc(cyc))
   If PointViable(pFoc(cyc))>0 Then PointEntity p(cyc),pLimb(pFoc(cyc),30)
   pTA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
  EndIf
  ;pursue target angle
  excused=0
  If pFoc(cyc)=0 And DirPressed(cyc)=0 And pPlatform(cyc)=>5 Then excused=1
  If pDazed(cyc)>0 And DirPressed(cyc)=0 Then excused=1
  If pAnim(cyc)=32 And cRun(cyc)=1 Then excused=1
  If excused=0 And pA#(cyc)<>pTA#(cyc)
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),10)=0 
    If pAnim(cyc)=0 Then ChangeAnim(cyc,1)
    If pAnim(cyc)=23 Then ChangeAnim(cyc,24)
   EndIf
   turner#=pSpeed#(cyc)*6
   If cRun(cyc)=1 Or pFoc(cyc)>0 Then turner#=pSpeed#(cyc)*8
   If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),10) Then turner#=pSpeed#(cyc)*12
   If pDazed(cyc)>0 Then turner#=0.8
   If (pAnim(cyc)=23 Or pAnim(cyc)=24) And DirPressed(cyc)=0 And SatisfiedAngle(pA#(cyc),pTA#(cyc),30)
    turner#=pSpeed#(cyc)*3
   EndIf
   If pAnim(cyc)=32 Then turner#=pSpeed#(cyc) ;carrying
   If pAnim(cyc)=85 Then turner#=pSpeed#(cyc)*2 ;crawling
   If pAnim(cyc)=100 Or pAnim(cyc)=200 Then turner#=pSpeed#(cyc)*3 ;grapple lunge
   If pAnim(cyc)=102 Then turner#=pSpeed#(cyc)*2 ;grappling
   pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pTA#(cyc),turner#)
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),turner#+2) Then pA#(cyc)=pTA#(cyc)
  EndIf
 EndIf
End Function

;ENFORCE BLOCKS
Function EnforceBlocks(cyc)
 ;human blocks
 If pExcusedPlays(cyc)=0
  For v=1 To no_plays
   If cyc<>v And AttackViable(v)<>2 And pHidden(v)=0
    width#=10 : height#=35
    If pOldX#(cyc)>RealX#(v)-width# And pOldX#(cyc)<RealX#(v)+width# And pOldZ#(cyc)>RealZ#(v)-width# And pOldZ#(cyc)<RealZ#(v)+width#
     trapped=1
    Else
     If RealX#(cyc)>RealX#(v)-width# And RealX#(cyc)<RealX#(v)+width# And RealZ#(cyc)>RealZ#(v)-width# And RealZ#(cyc)<RealZ#(v)+width# And pY#(cyc)>pY#(v)-height# And pY#(cyc)<pY#(v)+height#
      If pOldX#(cyc)>RealX#(v)-width# And pOldX#(cyc)<RealX#(v)+width# Then pZ#(cyc)=pOldZ#(cyc)
      If pOldZ#(cyc)>RealZ#(v)-width# And pOldZ#(cyc)<RealZ#(v)+width# Then pX#(cyc)=pOldX#(cyc)
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;standard world blocks
 If pExcusedWorld(cyc)=0 And PointViable(cyc)=0
  For v=0 To no_blocks
   If pOldX#(cyc)>wBlockX1#(v) And pOldX#(cyc)<wBlockX2#(v) And pOldZ#(cyc)>wBlockZ1#(v) And pOldZ#(cyc)<wBlockZ2#(v)
    trapped=1
   Else
    If pX#(cyc)>wBlockX1#(v) And pX#(cyc)<wBlockX2#(v) And pZ#(cyc)>wBlockZ1#(v) And pZ#(cyc)<wBlockZ2#(v) And pY#(cyc)>wBlockY1#(v) And pY#(cyc)<wBlockY2#(v)
     If pOldX#(cyc)>wBlockX1#(v) And pOldX#(cyc)<wBlockX2#(v) Then pZ#(cyc)=pOldZ#(cyc)
	 If pOldZ#(cyc)>wBlockZ1#(v) And pOldZ#(cyc)<wBlockZ2#(v) Then pX#(cyc)=pOldX#(cyc)
    EndIf
   EndIf
  Next
 EndIf
 ;projected world blocks
 If pExcusedWorld(cyc)=0 And PointViable(cyc)>0 
  PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
  For v=0 To no_blocks
   excused=0
   If v=>1 And v=<4 And pExcusedEdges(cyc) Then excused=1
   If excused=0
    its=0
    Repeat
     conflict=0
     If pOldX#(cyc)>wBlockX1#(v) And pOldX#(cyc)<wBlockX2#(v) And pOldZ#(cyc)>wBlockZ1#(v) And pOldZ#(cyc)<wBlockZ2#(v)
      trapped=1
     Else
      If RealX#(cyc)>wBlockX1#(v) And RealX#(cyc)<wBlockX2#(v) And RealZ#(cyc)>wBlockZ1#(v) And RealZ#(cyc)<wBlockZ2#(v) And pY#(cyc)>wBlockY1#(v) And pY#(cyc)<wBlockY2#(v)
       ForceMoveOut(cyc,v,RealX#(cyc),RealZ#(cyc))
       If pGrappler(cyc)>0 Then ForceMoveOut(pGrappler(cyc),v,RealX#(cyc),RealZ#(cyc))
       PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
       conflict=1
      EndIf
     EndIf
     its=its+1
     If its>100 Then conflict=0
    Until conflict=0 
   EndIf
  Next
 EndIf
 ;standard item blocks
 If pExcusedItems(cyc)=0 And pOutTim(cyc)>1 And (pX#(cyc)<>pOldX#(cyc) Or pZ#(cyc)<>pOldZ#(cyc))
  For v=1 To no_items
   If ItemProximity(cyc,v,30) And iState(v)=0 And cyc<>iCarrier(v)
    ScanItem(v,0)
    If ItemCollide(cyc,v,pOldX#(cyc),pOldZ#(cyc),-1)=0 
     If ItemCollide(cyc,v,RealX#(cyc),RealZ#(cyc),0)
      If ItemCollide(cyc,v,pOldX#(cyc),RealZ#(cyc),0) Then pZ#(cyc)=pOldZ#(cyc)
      If ItemCollide(cyc,v,RealX#(cyc),pOldZ#(cyc),0) Then pX#(cyc)=pOldX#(cyc) 
      If pAnim(cyc)=206 Or pAnim(cyc)=306 Then pX#(cyc)=pOldX#(cyc) : pZ#(cyc)=pOldZ#(cyc) : pA#(cyc)=pOldA#(cyc)
      randy=Rnd(0,30)
      If randy=0 Then pNowhere(cyc)=99
      FindItemClimbing(cyc,v) 
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;confine to ring
 If pExcusedWorld(cyc)=0 And pPlatform(cyc)=0 And pY#(cyc)=wStage# And TurnViable(cyc)=1
  ConfineToRing(cyc)
 EndIf
 ;apron limits
 If pExcusedWorld(cyc)=0
  If pPlatform(cyc)=1 ;north
   If pX#(cyc)<wBlockX2#(4)+2 Then pX#(cyc)=wBlockX2#(4)+2
   If pX#(cyc)>wBlockX1#(2)-2 Then pX#(cyc)=wBlockX1#(2)-2
   pZ#(cyc)=81;pOldZ#(cyc)
  EndIf
  If pPlatform(cyc)=2 ;east
   If pZ#(cyc)<wBlockZ2#(3)+2 Then pZ#(cyc)=wBlockZ2#(3)+2
   If pZ#(cyc)>wBlockZ1#(1)-2 Then pZ#(cyc)=wBlockZ1#(1)-2
   pX#(cyc)=83;pOldX#(cyc)
  EndIf
  If pPlatform(cyc)=3 ;south
   If pX#(cyc)<wBlockX2#(4)+2 Then pX#(cyc)=wBlockX2#(4)+2
   If pX#(cyc)>wBlockX1#(2)-2 Then pX#(cyc)=wBlockX1#(2)-2
   pZ#(cyc)=-84;pOldZ#(cyc)
  EndIf
  If pPlatform(cyc)=4 ;west
   If pZ#(cyc)<wBlockZ2#(3)+2 Then pZ#(cyc)=wBlockZ2#(3)+2
   If pZ#(cyc)>wBlockZ1#(1)-2 Then pZ#(cyc)=wBlockZ1#(1)-2
   pX#(cyc)=-81;pOldX#(cyc)
  EndIf
 EndIf
 ;platform limits
 If pPlatform(cyc)=>9 And pPlatform(cyc)<100 And pAnim(cyc)<>28 And pAnim(cyc)<>29
  pX#(cyc)=pOldX#(cyc) : pZ#(cyc)=pOldZ#(cyc)
 EndIf
 ;cage climbing limits
 If pPlatform(cyc)=>101 And pPlatform(cyc)=<114
  If pPlatform(cyc)=101 Or pPlatform(cyc)=103 Or pPlatform(cyc)=111 Or pPlatform(cyc)=113
   If pX#(cyc)<wBlockX2#(4)+2 Then pX#(cyc)=wBlockX2#(4)+2
   If pX#(cyc)>wBlockX1#(2)-2 Then pX#(cyc)=wBlockX1#(2)-2
  EndIf
  If pPlatform(cyc)=102 Or pPlatform(cyc)=104 Or pPlatform(cyc)=112 Or pPlatform(cyc)=114
   If pZ#(cyc)<wBlockZ2#(3)+2 Then pZ#(cyc)=wBlockZ2#(3)+2
   If pZ#(cyc)>wBlockZ1#(1)-2 Then pZ#(cyc)=wBlockZ1#(1)-2
  EndIf
  If pY#(cyc)<pGround#(cyc) Then pY#(cyc)=pGround#(cyc)
  If pY#(cyc)>50 Then pY#(cyc)=50
 EndIf
 ;video climbing limits
 If pPlatform(cyc)=100
  If pX#(cyc)<wBlockX2#(11) Then pX#(cyc)=wBlockX2#(11) 
  If pX#(cyc)>wBlockX1#(5) Then pX#(cyc)=wBlockX1#(5)
  If pY#(cyc)<wGround# Then pY#(cyc)=wGround#
  If pY#(cyc)>68 Then pY#(cyc)=68
 EndIf
 ;world limits
 If InsideRing(pX#(cyc),pZ#(cyc),-20) And pY#(cyc)<wStage# Then pY#(cyc)=wStage#
 If pY#(cyc)<wGround# Then pY#(cyc)=wGround#
 ;getting nowhere?
 angled=1
 If pPlatform(cyc)>0 And SatisfiedAngle(pA#(cyc),pTA#(cyc),10)=0 Then angled=0
 If angled=1 And DirPressed(cyc) And TurnViable(cyc)>0 And pX#(cyc)=pOldX#(cyc) And pZ#(cyc)=pOldZ#(cyc)
  If pPlatform(cyc)>0 Or pFriction(cyc)>0 Then pNowhere(cyc)=pNowhere(cyc)+2 Else pNowhere(cyc)=pNowhere(cyc)+3
 EndIf
End Function

;FIND BLOCK CONFLICTS
Function FindBlocks(x#,z#,y#)
 trapped=0
 For v=0 To no_blocks
  If (y#>wBlockY1#(v) And y#<wBlockY2#(v)) Or y#=0
   excused=0
   If v=0 And InsideRing(x#,z#,-15) And y#=0 Then excused=1
   If v=17 Or v=21 Then excused=1
   If excused=0 And x#>wBlockX1#(v)-1 And x#<wBlockX2#(v)+1 And z#>wBlockZ1#(v)-1 And z#<wBlockZ2#(v)+1 Then trapped=1
  EndIf
 Next
 Return trapped
End Function

;FORCE OUT OF BLOCKS
Function ForceOut(cyc,dir)
 While FindBlocks(pX#(cyc),pZ#(cyc),pY#(cyc))>0
  MoveEntity p(cyc),0,0,dir
  pX#(cyc)=EntityX(p(cyc))
  pZ#(cyc)=EntityZ(p(cyc))
 Wend
End Function

;FORCE MOVE OUT OF BLOCKS
Function ForceMoveOut(cyc,block,checkX#,checkZ#)
 ;slide along X axis
 If pOldZ#(cyc)>wBlockZ1#(block) And pOldZ#(cyc)<wBlockZ2#(block)
  If checkX#>pOldX#(cyc) Then pX#(cyc)=pX#(cyc)-1
  If checkX#<pOldX#(cyc) Then pX#(cyc)=pX#(cyc)+1
 EndIf
 ;slide along Z axis
 If pOldX#(cyc)>wBlockX1#(block) And pOldX#(cyc)<wBlockX2#(block)
  If checkZ#>pOldZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-1
  If checkZ#<pOldZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+1
 EndIf
End Function

;CONFINE TO RING
Function ConfineToRing(cyc)
 If pZ#(cyc)>wBlockZ1#(1) Then pZ#(cyc)=wBlockZ1#(1) ;north limit
 If pX#(cyc)>wBlockX1#(2) Then pX#(cyc)=wBlockX1#(2) ;east limit
 If pZ#(cyc)<wBlockZ2#(3) Then pZ#(cyc)=wBlockZ2#(3) ;south limit 
 If pX#(cyc)<wBlockX2#(4) Then pX#(cyc)=wBlockX2#(4) ;west limit
End Function

;MONITOR HEALTH & STATUS
Function MonitorStatus(cyc)
 ;regain health
 If pInjured(cyc)=0
  chance=(120-charStam(pChar(cyc)))*6
  If optLevel=5 
   If pControl(cyc)=0 And pControl(pFoc(cyc))>0 And LegalMan(cyc) And pHealth(cyc)>0 And pHealth(cyc)<pHealth(pFoc(cyc)) Then chance=chance/2
   If pControl(cyc)>0 And pControl(pFoc(cyc))=0 And pHealth(cyc)>pHealth(pFoc(cyc)) Then chance=chance*2
  EndIf
  If matchState=3 And matchKOs=1 And pHealth(cyc)=<0 Then chance=chance*2
  If game=1 And matchState=4 Then chance=99
  randy=Rnd(0,chance)
  If (randy=0 Or (randy=1 And LegalMan(cyc))) And pStretched(cyc)=0 Then pHealth(cyc)=pHealth(cyc)+1
  randy=Rnd(0,chance)
  If randy=<5 And pHP(cyc)>0 Then pHP(cyc)=pHP(cyc)+1
 EndIf
 ;lose health
 apron=0
 If matchTeams=2 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 Then apron=1
 If pInjured(cyc)>0 And apron=0
  chance=charTough(pChar(cyc))*2
  If pInjured(cyc)=2 Then chance=chance/2
  randy=Rnd(0,chance) 
  If randy=0
   ProduceSound(p(cyc),sPain(1),GetVoice(cyc),0)
   pHealth(cyc)=pHealth(cyc)-1 : pHP(cyc)=pHP(cyc)-1
   If pInjured(cyc)=2 Then pHP(cyc)=0
  EndIf
 EndIf
 ;dazed state
 pDazed(cyc)=pDazed(cyc)-1
 If pDazed(cyc)<0 Then pDazed(cyc)=0
 If pDazed(cyc)>0 And pAnim(cyc)=>1 And pAnim(cyc)=<3
  randy=Rnd(0,30)
  If randy=0 Then ProduceSound(p(cyc),sPain(1),GetVoice(cyc),0) : pHP(cyc)=pHP(cyc)-1
 EndIf
 ;health limits
 If pHealth(cyc)<0 Then pHealth(cyc)=0
 If pHealth(cyc)>pHealthLimit(cyc) Then pHealth(cyc)=pHealthLimit(cyc)
 If pHP(cyc)<0 Then pHP(cyc)=0
 If pHealth(cyc)=>25 And pHP(cyc)>(pHealth(cyc)/5) Then pHP(cyc)=pHealth(cyc)/5
 If pHealth(cyc)<25 And pHP(cyc)>5 Then pHP(cyc)=5  
 ;legless!
 If pScar(cyc,33)=5 And pScar(cyc,36)=5
  pHP(cyc)=0
  If pDT(cyc)<10 Then pDT(cyc)=10
 EndIf
 ;fall off broken items
 If pPlatform(cyc)=>10 And pPlatform(cyc)<100
  v=pPlatform(cyc)-10
  If iState(v)=1 Or iCarrier(v)>0 Then pHP(cyc)=0
 EndIf
 ;shake off cage
 If matchCage>0
  If cageTim(1)=5 And (pPlatform(cyc)=101 Or pPlatform(cyc)=111) Then pHP(cyc)=0
  If cageTim(2)=5 And (pPlatform(cyc)=102 Or pPlatform(cyc)=112) Then pHP(cyc)=0
  If cageTim(3)=5 And (pPlatform(cyc)=103 Or pPlatform(cyc)=113) Then pHP(cyc)=0
  If cageTim(4)=5 And (pPlatform(cyc)=104 Or pPlatform(cyc)=114) Then pHP(cyc)=0
 EndIf
 ;prevent fall on cage apron
 If matchCage>0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4
  If pHP(cyc)<1 Then pHP(cyc)=1
 EndIf
 ;trigger collapse
 If pHP(cyc)=<0 And CollapseViable(cyc) 
  If pDT(cyc)=<0 Then pDT(cyc)=200-pHealth(cyc)
  ChangeAnim(cyc,Rnd(68,71))
 EndIf 
 ;decrease down time
 If pAnim(cyc)<>109 Then pDT(cyc)=pDT(cyc)-1
 randy=Rnd(0,6)
 If randy=0 And pPinner(cyc)>0 Then pDT(cyc)=pDT(cyc)+1 ;pin struggle
 If pHealth(cyc)=<0 Or pInjured(cyc)>0 Or pEliminated(cyc) 
  randy=Rnd(0,4)
  If randy=0 And pDT(cyc)>10 Then pDT(cyc)=pDT(cyc)+1 ;weariness delay
 EndIf
 If game=1 And pChar(cyc)=gamOpponent(slot,GetDate()) And gamVariable(slot)=6
  randy=Rnd(0,4)
  If randy=0 And pDT(cyc)>10 Then pDT(cyc)=pDT(cyc)+1 ;corruption delay
 EndIf
 If pDT(cyc)>100 And pEliminated(cyc)=0
  randy=Rnd(0,1000)
  If pHealth(cyc)=<0 Then randy=Rnd(0,1500)
  If randy=0 Then pDT(cyc)=pDT(cyc)-100 ;get-up assistance
  If randy=1 Then Pop(cyc,2,0) : pDT(cyc)=0 ;miracle get-up
 EndIf
 If pDT(cyc)<0 Then pDT(cyc)=0
 ;heat fluctuation
 randy=Rnd(0,150)
 If optLevel=>5 And pControl(cyc)=0 And pControl(pFoc(cyc))>0 Then randy=99
 If randy=0 And matchState=>2 Then pHeat(cyc)=pHeat(cyc)-1
 If pHeat(cyc)<0 Then pHeat(cyc)=0
 If pHeat(cyc)>100 Then pHeat(cyc)=100
 If pHeat(cyc)=100 And pSpecial(cyc)=0 And pAnim(cyc)=>0 And pAnim(cyc)=<3 Then ChangeAnim(cyc,6)
 If (pAnim(cyc)<110 Or pAnim(cyc)>199) And pAnim(cyc)<>6 Then pSpecial(cyc)=pSpecial(cyc)-1
 If pSpecial(cyc)<0 Then pSpecial(cyc)=0
 ;special effects
 If pSpecial(cyc)>0
  randy=Rnd(0,100)
  If randy=0 Then pHealth(cyc)=pHealth(cyc)+1
  If pHP(cyc)<20 Then pHP(cyc)=pHP(cyc)+1
  pDazed(cyc)=0 : pDT(cyc)=0
  If pMomentum(cyc)>10 Then pMomentum(cyc)=10
  pHeat(cyc)=100
  If pSpecial(cyc)=<2 Then pHeat(cyc)=30
 EndIf
 ;determine speed
 pSpeed#(cyc)=pDefSpeed#(cyc)
 If pHealth(cyc)=<optTired Then pSpeed#(cyc)=pDefSpeed#(cyc)-(pDefSpeed#(cyc)/3)
 If pInjured(cyc)=1 Then pSpeed#(cyc)=pDefSpeed#(cyc)/2
 If pInjured(cyc)=>2 Then pSpeed#(cyc)=pDefSpeed#(cyc)/3 
 If pSpecial(cyc)>0 Then pSpeed#(cyc)=pDefSpeed#(cyc)+(pDefSpeed#(cyc)/3)
 If pDazed(cyc)>0 Then pSpeed#(cyc)=-0.3
End Function

;AUTOMATED REFEREEING
Function AutomatedRefs(cyc)
 ;reset check status
 If pRole(cyc)=0
  If RefViable(cyc)=0 Then pChecked(cyc)=0
 EndIf
 ;automatic pinning
 pAutoTim(cyc)=pAutoTim(cyc)+1 
 v=pPinning(cyc)
 If no_refs=0 And v>0 And RefViable(v)=1 And pChecked(v)=0 And pRefCount(cyc)<3
  oldCount=pRefCount(cyc) : pRefVictim(cyc)=v
  If pAutoTim(cyc)>35 And pRefCount(cyc)=0 Then pRefCount(cyc)=1 : pAutoTim(cyc)=0
  If pAutoTim(cyc)>70 And pRefCount(cyc)>0 Then pRefCount(cyc)=pRefCount(cyc)+1 : pAutoTim(cyc)=0 
  If pRefCount(cyc)<>oldCount
   ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.5)
   ProduceSound(p(cyc),sCount(pRefCount(cyc)),22050,1)
  EndIf
  If pAutoTim(cyc)>20 And matchRules=>1 And LegalMan(cyc) And LegalMan(v) ;rope break
   If NearRopes(cyc,optRopeRange/2)>0 Or NearRopes(v,optRopeRange)>0
    Pop(v,Rnd(2,7),0)
    ProduceSound(p(cyc),sBreak,22050,1)
    pChecked(v)=1
   EndIf 
  EndIf
 EndIf
 ;delayed declaration
 If no_refs=0 And pRefCount(cyc)=3
  If pAutoTim(cyc)>20
   matchWinStyle=1
   DeclareFall(cyc,pRefVictim(cyc))
   pRefCount(cyc)=0 : pChecked(pRefVictim(cyc))=1
  EndIf
 EndIf
 ;pinning chaos
 If pPinning(cyc)>0 And LegalMan(cyc) And LegalMan(pPinning(cyc)) 
  If FindPinCount(pPinning(cyc))>0 Then CauseChaos(100)
 EndIf
 ;submission declaration
 If no_refs=0 And RefViable(cyc)=2 And pStretched(cyc)=>2 And pChecked(cyc)=0 And pSubmitDelay(cyc)=0 
  pRefAward(cyc)=pGrappler(cyc)
  pSubmitDelay(cyc)=1 : pChecked(cyc)=1
 EndIf
 If pSubmitDelay(cyc)=>1 Then pSubmitDelay(cyc)=pSubmitDelay(cyc)+1
 If pSubmitDelay(cyc)>20
  matchWinStyle=2 : pSubmitDelay(cyc)=0
  DeclareFall(pRefAward(cyc),cyc)
 EndIf
 ;incapacitated declarations
 If no_refs=0 And RefViable(cyc)=>3 
  If matchKOs=1 And (pHealth(cyc)=<1 Or pInjured(cyc)>0) Then matchWinStyle=3 : DeclareFall(0,cyc) ;KO
  If matchBlood=1 
   If pScar(cyc,1)=>3 Or (optGore=0 And pScar(cyc,1)>0) Then matchWinStyle=4 : DeclareFall(0,cyc) ;first blood
  EndIf
 EndIf
 ;over-the-top elimination
 If matchOTT=1 And matchCage=0 And pOutTim(cyc)>200 And LegalMan(cyc) And InsideRing(RealX#(cyc),RealZ#(cyc),0)=0 And AttackViable(cyc)>0
  matchWinStyle=5
  DeclareFall(0,cyc)
 EndIf
 ;over-the-top cage victory
 If matchOTT=1 And matchCage>0 And pOutTim(cyc)>200 And LegalMan(cyc) And InsideRing(RealX#(cyc),RealZ#(cyc),0)=0 And AttackViable(cyc)>0 And pPlatform(cyc)=0
  matchWinStyle=1
  EndMatch(cyc)
 EndIf
 ;delayed referee DQ
 If pDQDelay(cyc)=>1 Then pDQDelay(cyc)=pDQDelay(cyc)+1
 If pDQDelay(cyc)>0 And pRole(cyc)=1 And pAnim(cyc)=>0 And pAnim(cyc)=<3
  PointEntity p(cyc),p(pRefVictim(cyc))
  pA#(cyc)=EntityYaw(p(cyc))
  matchWinStyle=0 : pDQDelay(cyc)=0
  ChangeAnim(cyc,99)
 EndIf
 ;delayed automated DQ
 If pDQDelay(cyc)>20 And no_refs=0
  matchWinStyle=0 : pDQDelay(cyc)=0 
  DeclareFall(pRefAward(cyc),cyc)
 EndIf
End Function

;MANAGE ENTRANCES
Function ManageEntrances(cyc)
 If matchEnter=cyc And cyc=<no_wrestlers And matchState=>1
  ;activate player (and team-mates?)
  If pOutTim(cyc)=0
   If pHidden(cyc)>0 Then ShowEntity p(cyc) : ShowEntity pShadow(cyc) : pHidden(cyc)=0
   pOutTim(cyc)=1 : pFoc(cyc)=0
   pAgenda(cyc)=0 : pNowhere(cyc)=99
   If matchState=1 
    camType=2 : camFoc=cyc
    If pControl(cyc)>0 And optLevel=<3 Then camType=3
    If pControl(cyc)>0 And optLevel=>4 Then camType=15
    lightPreset=charLight(pChar(cyc))
   EndIf
   PlayTheme(pChar(cyc))
   For v=1 To no_plays
    If cyc<>v And pTeam(v)=pTeam(cyc) And pRole(v)<>1 And pRole(v)<>3
     pOutTim(v)=1 : pFoc(v)=0
     pAgenda(v)=0 : pNowhere(v)=99
     If pControl(v)>0 Then camFoc=v
    EndIf
   Next
  EndIf
  ;fade out theme & lighting
  If pOutTim(cyc)=>100 And ChannelPlaying(chTheme)>0
   chThemeVol#=chThemeVol#-0.01
   If chThemeVol#<0 Then chThemeVol#=0
   ChannelVolume chTheme,chThemeVol#
   If chThemeVol#=<0 Or pOutTim(cyc)>200 Then StopChannel chTheme
   If chThemeVol#=<0.3 Or pOutTim(cyc)>190 Then lightPreset=arenaLight
  EndIf
  ;satisfy and proceed
  If pOutTim(cyc)>210 And matchState=1
   Repeat  
    matchEnter=matchEnter-1
   Until matchEnter=<0 Or pOutTim(matchEnter)=0
   If matchTeams=-1 And cyc=<no_wrestlers-1 Then matchEnter=0 ;stop at first 2 in Rumbles
  EndIf
 EndIf
 ;appear through curtain
 If pZ#(cyc)<450 And pRole(cyc)<>1 And pOutTim(cyc)=1
  pOutTim(cyc)=2 : cTaunt(cyc)=1
  Pop(cyc,2,1) : crowdVolTarget#=Float(charPop(pChar(cyc)))/90
  If matchState=3 Then camTempTim=100 : camTempFoc=cyc : camOldFoc=camFoc
 EndIf
 ;clock out-time
 If InsideRing(pX#(cyc),pZ#(cyc),-10) Then pOutTim(cyc)=pOutTim(cyc)+1
 If InsideRing(pX#(cyc),pZ#(cyc),0) And matchState=3 And pOutTim(cyc)>1 And pRole(cyc)=0 And pEliminated(cyc)=0
  pOutTim(cyc)=pOutTim(cyc)+1 ;rumble booster
 EndIf
 If matchState=1 And matchCage>0 And pPlatform(cyc)=>101 And pPlatform(cyc)=<104 And pOutTim(cyc)>1 
  pOutTim(cyc)=pOutTim(cyc)+1 ;cage booster
 EndIf
 randy=Rnd(0,5)
 If randy=0 And pRole(cyc)=3 And pOutTim(cyc)>10 Then pOutTim(cyc)=pOutTim(cyc)+1 ;intrusion booster
 ;remove eliminated
 If matchState=3 And matchType=5 And pEliminated(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0)=0
  pOutTim(cyc)=pOutTim(cyc)+1
  If pOutTim(cyc)>1000 And pHidden(cyc)=0 And cyc<>camFoc And pControl(cyc)=0 And pGrappling(cyc)=0 And pGrappler(cyc)=0 And pPinning(cyc)=0 And pPinner(cyc)=0 And pPlatform(cyc)=0
   If optHideElim=2 Or (optHideElim=1 And CountWrestlers()>4) Then pHidden(cyc)=1
  EndIf
 EndIf 
 ;exhaust interferences
 If pRole(cyc)=3 And pOutTim(cyc)>600 Then pRole(cyc)=2
End Function

;COUNT VISIBLE WRESTLERS
Function CountWrestlers()
 value=0
 For cyc=1 To no_wrestlers
  If pHidden(cyc)=0 Then value=value+1
 Next
 Return value
End Function