;//////////////////////////////////////////////////////////////////////////////
;---------------- WRESTLING ENCORE: ARTIFICIAL INTELLIGENCE -------------------
;//////////////////////////////////////////////////////////////////////////////

;----------------------------------------------------------------
;///////////////////// GET INPUT ////////////////////////////////
;----------------------------------------------------------------
Function GetInput(cyc)
 ;reset commands
 cUp(cyc)=0 : cDown(cyc)=0 : cLeft(cyc)=0 : cRight(cyc)=0
 cAttack(cyc)=0 : cGrab(cyc)=0 : cTaunt(cyc)=0
 cRun(cyc)=0 : cPick(cyc)=0 : cSwitch(cyc)=0
 ;get keyboard input
 If pControl(cyc)=1 Or pControl(cyc)=3
  If KeyDown(200) Then cUp(cyc)=1
  If KeyDown(208) Then cDown(cyc)=1
  If KeyDown(203) Then cLeft(cyc)=1
  If KeyDown(205) Then cRight(cyc)=1
  If KeyDown(keyAttack) Then cAttack(cyc)=1
  If KeyDown(keyRun) Then cRun(cyc)=1
  If KeyDown(keyGrab) Then cGrab(cyc)=1
  If KeyDown(keyPick) Then cPick(cyc)=1
  If KeyDown(keySwitch) Then cSwitch(cyc)=1
  If KeyDown(keyTaunt) Then cTaunt(cyc)=1
 EndIf
 ;get gamepad input
 If pControl(cyc)=2 Or pControl(cyc)=3
  If JoyYDir()=-1 Then cUp(cyc)=1
  If JoyYDir()=1 Then cDown(cyc)=1
  If JoyXDir()=-1 Then cLeft(cyc)=1
  If JoyXDir()=1 Then cRight(cyc)=1
  If JoyDown(buttAttack) Then cAttack(cyc)=1 
  If JoyDown(buttRun) Then cRun(cyc)=1
  If JoyDown(buttGrab) Then cGrab(cyc)=1
  If JoyDown(buttPick) Then cPick(cyc)=1
  If JoyDown(buttSwitch) Then cSwitch(cyc)=1
  If JoyDown(buttTaunt) Then cTaunt(cyc)=1
 EndIf
 ;forced entry
 If pOutTim(cyc)=1 And pZ#(cyc)>440 And matchState=<3
  cUp(cyc)=0 : cDown(cyc)=1 : cLeft(cyc)=0 : cRight(cyc)=0
  cAttack(cyc)=0 : cGrab(cyc)=0 : cTaunt(cyc)=0
  cRun(cyc)=1 : cPick(cyc)=0 : cSwitch(cyc)=0
  If pRole(cyc)=2 Then cRun(cyc)=0
 EndIf
 ;get CPU input
 If pControl(cyc)=0 Then AI(cyc)
End Function

;----------------------------------------------------------------
;/////////////// ARTIFICIAL INTELLIGENCE ////////////////////////
;----------------------------------------------------------------
Function AI(cyc)
 ;counters
 pNowhere(cyc)=pNowhere(cyc)-1
 If pNowhere(cyc)<0 Then pNowhere(cyc)=0
 pSatisfied(cyc)=pSatisfied(cyc)-1
 If pSatisfied(cyc)<0 Then pSatisfied(cyc)=0
 pRefAnger(cyc)=pRefAnger(cyc)-1
 If pRefAnger(cyc)<0 Then pRefAnger(cyc)=0
 ;DETERMINE AGENDA
 ;random intentions
 If pAgenda(cyc)<10
  randy=Rnd(0,2000)
  If (pEliminated(cyc) Or pRole(cyc)=2) And pChaosTim(cyc)=0 Then randy=Rnd(0,10000) 
  If randy=<2 Then pAgenda(cyc)=0
  If matchState=3
   If randy=>3 And randy=<5 Then pAgenda(cyc)=1
   If randy=>6 And randy=<7 Then pAgenda(cyc)=2
   If matchRules=<1 And matchOTT=0 And matchCage=0 And pScar(cyc,19)=<4 And pCarrying(cyc)=0 And pRole(cyc)<>1
    ;If randy=9 And no_items>0 Then pAgenda(cyc)=20 : pTarget(cyc)=0
    If randy=8 And pHolding(cyc)=0 And no_weaps>0 Then pAgenda(cyc)=21 : pTarget(cyc)=0
   EndIf
  EndIf
 EndIf 
 ;random enemy change
 If matchState=>3
  If (pAgenda(cyc)=>1 And pAgenda(cyc)=<2) Or (pAgenda(cyc)=>13 And pAgenda(cyc)=<16) Or pRole(cyc)=3
   randy=Rnd(0,500)
   If matchState=3 And pRole(cyc)=0 And LegalMan(pFoc(cyc))=0 And pChaosTim(cyc)=0 Then randy=Rnd(0,200)
   If pTeam(cyc)=pTeam(pFoc(cyc)) And (pRole(cyc)<>2 Or pChaosTim(cyc)>0) Then randy=Rnd(0,50)
   If randy=0 Then GetNewFoc(cyc)
  EndIf
 EndIf
 ;favour ring if rules demand
 If matchRules=>1 Or matchOTT=1 Or matchCage>0
  If matchState=<3 And InsideRing(pX#(cyc),pZ#(cyc),0)=0 And (LegalMan(cyc) Or pRole(cyc)=1)
   randy=Rnd(0,200) 
   If randy=0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
  EndIf
 EndIf
 ;enemy attraction
 If matchState=>3 And TurnViable(cyc)=1 And pPlatform(cyc)<100 And pAgenda(cyc)<10
  For v=1 To no_plays
   If InProximity(cyc,v,30) And cyc<>v And pFoc(cyc)<>v And pTeam(cyc)<>pTeam(v) And AttackViable(v)=1 And pHidden(v)=0
    randy=Rnd(0,200)
    If pFoc(v)=cyc And LegalMan(cyc) And LegalMan(v) Then randy=Rnd(0,5)
    If pRole(cyc)=1 And InsideRing(pX#(v),pZ#(v),-20)
     If pRole(v)=3 Or (LegalMan(v)=0 And pChaosTim(v)=0) Then randy=Rnd(0,100)
    EndIf
    If pChar(v)=charFriend(pChar(cyc)) Then randy=Rnd(0,1000)
    If LegalMan(cyc) And LegalMan(v)=0 Then randy=Rnd(0,1000)
    If matchRules=>1 And InsideRing(pX#(v),pZ#(v),0)=0 Then randy=Rnd(0,1000)
    If pAgenda(cyc)=0 And matchCage>0 And matchOTT=1 Then randy=Rnd(0,1000)
    If matchState<>3 Then randy=Rnd(0,1000)
    If pRole(v)=1 Then randy=99
    If randy=0 Then pFoc(cyc)=v : pAgenda(cyc)=1
   EndIf
  Next
 EndIf
 ;special confidence
 If matchState=3 And pSpecial(cyc)>0 And LegalMan(cyc) And pAgenda(cyc)=0 Then pAgenda(cyc)=1
 ;pin attraction
 If matchState=3 And matchPins=1 And LegalMan(cyc) And pAgenda(cyc)<>11
  For v=1 To no_plays
   If InProximity(cyc,v,50) And LegalMan(v) And FallsCount(v) And PinViable(v) And pPinner(v)=0 And pTeam(cyc)<>pTeam(v) And pDT(v)=>100
    chance=(300-pDT(v))/2
    If chance<10 Then chance=10
    randy=Rnd(0,chance)
    If randy=0 Then pAgenda(cyc)=11 : pFoc(cyc)=v
   EndIf
  Next
 EndIf
 ;find ref duties
 If matchState=3 And pRole(cyc)=1
  If pAgenda(cyc)=1 Then pAgenda(cyc)=2
  For v=1 To no_plays
   If RefViable(v)>0 And pChecked(v)=0 And pAnim(v)<>90 Then pFoc(cyc)=v : pAgenda(cyc)=10
  Next
 EndIf
 ;consider tagging
 If matchState=3 And matchTeams=2 And LegalMan(cyc)
  For v=1 To no_plays
   If cyc<>v And pTeam(v)=pTeam(cyc) And pEliminated(v)=0 And TagProximity(v,80) And InsideRing(pX#(cyc),pZ#(cyc),-20) And Isolated(cyc,10)
    chance=(25+pHealth(cyc))*2
    If pHealth(v)<pHealth(cyc) Then chance=chance*2
    If pAnim(v)=5 And pControl(v)>0 Then chance=20
    If pSpecial(v)>0 Then chance=20
    randy=Rnd(0,chance)
    If pInjured(v)>pInjured(cyc) Then randy=999
    If randy=0 Then pFoc(cyc)=v : pAgenda(cyc)=12
   EndIf
  Next
 EndIf
 ;item attraction
 If TurnViable(cyc)=1 And pPlatform(cyc)=0 And pCarrying(cyc)=0 And pScar(cyc,19)=<4 And pAgenda(cyc)<>20 And pTarget(cyc)<>v
  For v=1 To no_items
   If ItemProximity(cyc,v,30) And pY#(cyc)=iY#(v) And iCarrier(v)=0 And iCarryDepth#(iType(v),iState(v))>0
    randy=Rnd(0,500)
    If Isolated(cyc,30) Then randy=Rnd(0,250)
    If iState(v)=0 And pNowhere(cyc)>0 And pNowhere(cyc)<30 Then randy=Rnd(0,10)
    If randy=0 Then pTarget(cyc)=v : pAgenda(cyc)=20 : pNowhere(cyc)=0
   EndIf
  Next
 EndIf
 ;weapon attraction
 If TurnViable(cyc)=1 And pHolding(cyc)=0 And pCarrying(cyc)=0 And pScar(cyc,19)=<4 And pAgenda(cyc)<>10 And pAgenda(cyc)<>11
  For v=1 To no_weaps
   If WeaponProximity(cyc,v,20) And pY#(cyc)=weapY#(v)
    If Isolated(cyc,30) Then randy=Rnd(0,50) Else randy=Rnd(0,200)
    If matchRules=>2 And matchState=3 Then randy=Rnd(0,500)
    If randy=0 Then pTarget(cyc)=v : pAgenda(cyc)=21
   EndIf
  Next
 EndIf
 ;find relevant belt
 If matchReward=>2 And matchReward=<4 And matchState<>3 And pHolding(cyc)=0
  For v=1 To no_weaps
   If pChar(cyc)=fedChampTag(fed,1) Or pChar(cyc)=fedChampTag(fed,2)
    If weapType(v)=23 And weapCarrier(v)=0 Then pTarget(cyc)=v : pAgenda(cyc)=21
   EndIf
   If pChar(cyc)=fedChampInter(fed) And weapType(v)=22 Then pTarget(cyc)=v : pAgenda(cyc)=21
   If pChar(cyc)=fedChampWorld(fed) And weapType(v)=21 Then pTarget(cyc)=v : pAgenda(cyc)=21
  Next
 EndIf
 ;team-mate responsibilities
 If matchState=3 And matchTeams=2 And pRole(cyc)=0 And LegalMan(cyc)=0 And pEliminated(cyc)=0
  If pChaosTim(cyc)=0 And pPlatform(cyc)=<4 And (pAgenda(cyc)<13 Or pAgenda(cyc)>16) ;get out
   Repeat
    randy=Rnd(0,1)
    If randy=0 Then pAgenda(cyc)=Rnd(14,15) Else pAgenda(cyc)=Rnd(13,16) 
   Until FindTagConflict(cyc,pAgenda(cyc))=0
  EndIf
  randy=Rnd(0,100)
  If randy=0 And pChaosTim(cyc)>0 And pPlatform(cyc)>0 And pAgenda(cyc)=>13 And pAgenda(cyc)=<16 ;get in
   pAgenda(cyc)=1 
  EndIf
 EndIf
 ;leave after elimination
 If matchState=3 And pChaosTim(cyc)=0 And pAgenda(cyc)>0 And pAgenda(cyc)<20 ;And InsideRing(pX#(cyc),pZ#(cyc),0)
  If pEliminated(cyc) Or pRole(cyc)=2 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;avoid outside if OTT
 If matchState=<3 And matchOTT=1 And matchCage=0 And pRole(cyc)=0 And pEliminated(cyc)=0
  If InsideRing(pTX#(cyc),pTZ#(cyc),-5)=0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;pursue outside if caged
 If matchState=3 And matchOTT=1 And matchCage>0 And LegalMan(cyc) And pAgenda(cyc)=>1 And pAgenda(cyc)=<2 
  If InsideRing(pX#(cyc),pZ#(cyc),0) And pPlatform(cyc)<101 And pFoc(cyc)>0
   randy=Rnd(0,100)
   If randy=0 And AttackViable(pFoc(cyc))=2 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
  EndIf
  If pPlatform(cyc)=>101 Then pAgenda(cyc)=0
 EndIf
 ;find win threats
 If matchState=3 And pRole(cyc)<>1
  For v=1 To no_plays
   threat=0
   If cyc<>v And LegalMan(v) And pTeam(cyc)<>pTeam(v) And pTeam(cyc)=>1 And pTeam(cyc)=<pTeam(no_wrestlers) 
    If matchPins=1 And pPinning(v)>0 And FindPinCount(pPinning(v))>0 And LegalMan(pPinning(v)) ;pin threat
     If pTeam(pPinning(v))=pTeam(cyc) Or matchType<>5 Then threat=1
    EndIf
    If matchSubs=1 And pGrappling(v)>0 And pStretched(pGrappling(v))>0 And LegalMan(pGrappling(v)) ;submission threat
     If pTeam(pGrappling(v))=pTeam(cyc) Or matchType<>5 Then threat=1
    EndIf
    If matchCage>0 And matchOTT=1 And pPlatform(v)=>101 And pPlatform(cyc)<101 Then threat=1 ;cage escape threat
   EndIf
   randy=0
   If matchTeams=2 And pPlatform(cyc)>0 And LegalMan(cyc)=0 And pRole(cyc)=0 And pEliminated(cyc)=0 Then randy=Rnd(0,6*no_wrestlers)
   If threat>0 And randy=0
    pFoc(cyc)=v : pAgenda(cyc)=1
    If InProximity(cyc,pFoc(cyc),30)=0 Then pRunAgenda(cyc)=10
    If pNowhere(cyc)=20 Then cAttack(cyc)=1 : cRun(cyc)=0 : cGrab(cyc)=0
   EndIf
  Next
 EndIf
 ;crawl out of pins/holds
 If pPinner(cyc)>0 And pAgenda(cyc)>0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 If pStretched(cyc)>0 And pGrappler(cyc)>0 And InsideRing(pX#(cyc),pZ#(cyc),0) Then pAgenda(cyc)=17
 ;focus on entrance
 If (matchState=1 Or matchEnter=cyc) And pOutTim(cyc)=<2 And pAgenda(cyc)<20 
  If pRole(cyc)=2 And pTeam(cyc)=pTeam(matchEnter) And pOutTim(cyc)=>2 Then pAgenda(cyc)=2 Else pAgenda(cyc)=0
 EndIf
 ;focus on entrant
 If matchState=1 And matchEnter>0 And matchEnter<>cyc 
  If pTeam(matchEnter)<>pTeam(cyc) And pRole(cyc)<>2 Then pFoc(cyc)=matchEnter
  If pTeam(matchEnter)=pTeam(cyc) And pRole(cyc)=2 And pOutTim(cyc)=>2 Then pFoc(cyc)=matchEnter
 EndIf
 ;PURSUE AGENDA
 ;exploring agenda
 If pAgenda(cyc)=0
  randy=Rnd(0,500)
  If (pEliminated(cyc) Or pRole(cyc)=2) And pChaosTim(cyc)=0 And matchState=3 Then randy=Rnd(0,2000) 
  If randy=0 Or pNowhere(cyc)>20 
   pTX#(cyc)=Rnd(wBlockX2#(4)+5,wBlockX1#(2)-5)
   pTZ#(cyc)=Rnd(wBlockZ2#(3)+5,wBlockZ1#(1)-5)
   If matchState=3 And matchRules=0 And matchCage=0 And matchOTT=0 And pOutTim(cyc)>200 ;hardcore exploration
    Repeat
     pTX#(cyc)=pX#(cyc)+Rnd(-120,120)
     pTZ#(cyc)=pZ#(cyc)+Rnd(-120,120)
    Until FindRailings(pTX#(cyc),pTZ#(cyc))=0 Or FindRailings(pX#(cyc),pZ#(cyc))=1
   EndIf
   If matchState<3 And matchTeams>0 ;team sides
    If pTeam(cyc)=1 Then pTX#(cyc)=Rnd(wBlockX2#(4)+10,-20)
    If pTeam(cyc)=2 Then pTX#(cyc)=Rnd(20,wBlockX1#(2)-10)
   EndIf
   If matchState=3 And matchCage>0 And matchOTT=1 And LegalMan(cyc) ;cage climb out
    If pPlatform(cyc)=<100
     Repeat
      pTX#(cyc)=Rnd(-130,130) : pTZ#(cyc)=Rnd(-130,130)
     Until InsideRing(pTX#(cyc),pTZ#(cyc),10)=0
    EndIf
   EndIf
   If matchState=3 And matchRules=>1 And pRole(cyc)=1 And pCarrying(cyc)>0 ;refs carry items out
    If InsideRing(pX#(cyc),pZ#(cyc),0)
     Repeat
      pTX#(cyc)=pX#(cyc)+Rnd(-150,150) : pTZ#(cyc)=pZ#(cyc)+Rnd(-150,150)
     Until InsideRing(pTX#(cyc),pTZ#(cyc),10)=0
    EndIf
   EndIf
   If matchState=3 And (pRole(cyc)=2 Or pEliminated(cyc)) ;manager moat
    Repeat
     pTX#(cyc)=Rnd(-120,120) : pTZ#(cyc)=Rnd(-120,120)
     rando=Rnd(0,1)
     If rando=0 And matchTeams>0 And pTeam(cyc)=1 Then pTX#(cyc)=Rnd(-120,0) : pTZ#(cyc)=Rnd(-120,-105)
     If rando=1 And matchTeams>0 And pTeam(cyc)=1 Then pTX#(cyc)=Rnd(-120,-105) : pTZ#(cyc)=Rnd(-120,0)
     If rando=0 And matchTeams>0 And pTeam(cyc)=2 Then pTX#(cyc)=Rnd(0,120) : pTZ#(cyc)=Rnd(105,120)
     If rando=1 And matchTeams>0 And pTeam(cyc)=2 Then pTX#(cyc)=Rnd(105,120) : pTZ#(cyc)=Rnd(0,120)
    Until InsideRing(pTX#(cyc),pTZ#(cyc),10)=0
   EndIf
   If matchState=4 Or matchOfficial=0 ;go backstage 
    pTX#(cyc)=Rnd(wBlockX2#(20)+5,wBlockX1#(23)-5)
    pTZ#(cyc)=Rnd(550,wBlockZ1#(18)-5)
   EndIf
   pNowhere(cyc)=0
  EndIf
  pFollow(cyc)=5
  If (pRole(cyc)=2 Or pEliminated(cyc)) And matchState=3 Then range=300 Else range=100
  If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),range)=0 And Isolated(cyc,30) And pOutTim(cyc)>0
   pFoc(cyc)=0
  EndIf
 EndIf
 ;following agendas
 If pAgenda(cyc)=1 Or pAgenda(cyc)=2
  If pFoc(cyc)=0 Then GetNewFoc(cyc)
  If pFoc(cyc)>0
   pTX#(cyc)=RealX#(pFoc(cyc)) : pTZ#(cyc)=RealZ#(pFoc(cyc))
   If pAgenda(cyc)=1 Then pFollow(cyc)=10 ;close
   If pAgenda(cyc)=2 Then pFollow(cyc)=30 ;distant
   If pPlatform(pFoc(cyc))=>1 And pPlatform(pFoc(cyc))=<4 Then pFollow(cyc)=20 ;relax on apron victims
   If pRole(cyc)=0 And AttackViable(pFoc(cyc))=2 Then pFollow(cyc)=10 ;close on vulnerables
   If pRole(cyc)<>1 And (pPlatform(pFoc(cyc))<0 Or pPlatform(pFoc(cyc))=>5) Then pFollow(cyc)=5 ;close for platforms
   If pRefAnger(cyc)>0 Or pSpecial(cyc)>0 Then pFollow(cyc)=5 ;close for ref anger (or specials)
   If pRole(cyc)=1 And Isolated(cyc,10)=0 And InsideRing(pX#(cyc),pZ#(cyc),-20) Then pNowhere(cyc)=99 ;relocate ref if too close
   If InProximity(cyc,pFoc(cyc),30) And pSpecial(pFoc(cyc))>0 And pRole(cyc)<>1 And pTeam(cyc)<>pTeam(pFoc(cyc)) And TurnViable(pFoc(cyc))=1
    pNowhere(cyc)=99 ;run away from specials!
   EndIf
   If pRole(cyc)=0 And matchState=1 Then pNowhere(cyc)=99 ;focus on entrance
  Else
   pNowhere(cyc)=99
  EndIf
 EndIf
 ;follow for examination
 If pAgenda(cyc)=10
  pTX#(cyc)=RealX#(pFoc(cyc)) : pTZ#(cyc)=RealZ#(pFoc(cyc))
  pFollow(cyc)=5
  If InProximity(cyc,pFoc(cyc),25) And pY#(cyc)=pY#(pFoc(cyc)) And pPlatform(cyc)=0 And pAnim(pFoc(cyc))<>90
   cTaunt(cyc)=1
  EndIf
  If RefViable(pFoc(cyc))=0 Or pChecked(pFoc(cyc))=1 Then pAgenda(cyc)=2
 EndIf
 ;follow for pinning
 If pAgenda(cyc)=11
  pTX#(cyc)=RealX#(pFoc(cyc)) : pTZ#(cyc)=RealZ#(pFoc(cyc))
  pFollow(cyc)=5
  If InProximity(cyc,pFoc(cyc),15) And pY#(cyc)=pY#(pFoc(cyc)) And PinViable(pFoc(cyc)) And pPlatform(cyc)=0
   cTaunt(cyc)=1
  EndIf
  If PinViable(pFoc(cyc))=0 Or LegalMan(cyc)=0 Then pAgenda(cyc)=1
 EndIf
 ;follow for tag
 If pAgenda(cyc)=12
  pTX#(cyc)=RealX#(pFoc(cyc)) : pTZ#(cyc)=RealZ#(pFoc(cyc))
  pFollow(cyc)=5
  If pPlatform(pFoc(cyc))<1 Or pPlatform(pFoc(cyc))>4 Or LegalMan(cyc)=0 Or InsideRing(pX#(cyc),pZ#(cyc),-15)=0
   pNowhere(cyc)=99
  EndIf
 EndIf
 ;pursue apron position A (far left)
 If pAgenda(cyc)=13
  If pTeam(cyc)=1 Then pTX#(cyc)=wBlockX1#(0)+10 : pTZ#(cyc)=wBlockZ1#(0)+55
  If pTeam(cyc)=2 Then pTX#(cyc)=wBlockX2#(0)-55 : pTZ#(cyc)=wBlockZ2#(0)-10
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc))>0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;pursue apron position B (close left)
 If pAgenda(cyc)=14
  If pTeam(cyc)=1 Then pTX#(cyc)=wBlockX1#(0)+10 : pTZ#(cyc)=wBlockZ1#(0)+35
  If pTeam(cyc)=2 Then pTX#(cyc)=wBlockX2#(0)-35 : pTZ#(cyc)=wBlockZ2#(0)-10
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc))>0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;pursue apron position C (close right)
 If pAgenda(cyc)=15
  If pTeam(cyc)=1 Then pTX#(cyc)=wBlockX1#(0)+35 : pTZ#(cyc)=wBlockZ1#(0)+10
  If pTeam(cyc)=2 Then pTX#(cyc)=wBlockX2#(0)-10 : pTZ#(cyc)=wBlockZ2#(0)-35
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc))>0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;pursue apron position D (far right)
 If pAgenda(cyc)=16
  If pTeam(cyc)=1 Then pTX#(cyc)=wBlockX1#(0)+55 : pTZ#(cyc)=wBlockZ1#(0)+10
  If pTeam(cyc)=2 Then pTX#(cyc)=wBlockX2#(0)-10 : pTZ#(cyc)=wBlockZ2#(0)-55
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc))>0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;find the ropes
 If pAgenda(cyc)=17
  If pX#(cyc)<0 Then pTX#(cyc)=-100 Else pTX#(cyc)=100
  If pZ#(cyc)<0 Then pTZ#(cyc)=-100 Else pTZ#(cyc)=100
  pFollow(cyc)=5
  If pStretched(cyc)=0 Or pGrappler(cyc)=0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;follow item
 If pAgenda(cyc)=20 And no_items>0
  If pTarget(cyc)=0 
   Repeat
    pTarget(cyc)=Rnd(0,no_items)
   Until ItemVisible(cyc,pTarget(cyc),200) Or pTarget(cyc)=0
  EndIf
  If pTarget(cyc)>0
   pTX#(cyc)=iX#(pTarget(cyc)) : pTZ#(cyc)=iZ#(pTarget(cyc))
   pFollow(cyc)=5
   If pCarrying(cyc)>0 Or iCarrier(pTarget(cyc)=0)>0 Or ItemVisible(cyc,pTarget(cyc),200)=0
    pAgenda(cyc)=Rnd(0,2) : pNowhere(cyc)=99
   EndIf
   If pFoc(cyc)>0 And ItemVisible(cyc,pTarget(cyc),100)=0 Then pFoc(cyc)=0
  Else 
   pNowhere(cyc)=99
  EndIf
 EndIf
 ;follow weapon
 If pAgenda(cyc)=21 And no_weaps>0
  If pTarget(cyc)=0 
   Repeat
    pTarget(cyc)=Rnd(0,no_weaps)
   Until WeaponVisible(cyc,pTarget(cyc),200) Or pTarget(cyc)=0
  EndIf
  If pTarget(cyc)>0
   pTX#(cyc)=weapX#(pTarget(cyc)) : pTZ#(cyc)=weapZ#(pTarget(cyc))
   pFollow(cyc)=5
   If pHolding(cyc)>0 
    If pRole(cyc)=0 And matchState=3 Then pAgenda(cyc)=Rnd(1,2) Else pAgenda(cyc)=0 : pNowhere(cyc)=99
   EndIf
   If WeaponVisible(cyc,pTarget(cyc),200)=0 Then pAgenda(cyc)=Rnd(1,2) : pNowhere(cyc)=99
   If pFoc(cyc)>0 And WeaponVisible(cyc,pTarget(cyc),100)=0 Then pFoc(cyc)=0
  Else
   pAgenda(cyc)=Rnd(1,2)
  EndIf
 EndIf
 ;rethink if getting nowhere
 If pNowhere(cyc)>30 
  pAgenda(cyc)=0
  pTX#(cyc)=pX#(cyc) : pTZ#(cyc)=pZ#(cyc)
  pSubX#(cyc)=999 : pSubZ#(cyc)=999
  randy=Rnd(0,1)
  If randy=0 And pPlatform(cyc)=>5 Then pAgenda(cyc)=22 : FindExit(cyc) : pNowhere(cyc)=0 
 EndIf
 ;climb off item
 If pAgenda(cyc)=22
  pTX#(cyc)=pLeaveX#(cyc) : pTZ#(cyc)=pLeaveZ#(cyc)
  pFollow(cyc)=1
  If pPlatform(cyc)=0 Then pAgenda(cyc)=0 : pNowhere(cyc)=99
 EndIf
 ;cage assistance
 If matchState=3 And matchCage>0 And matchOTT=1 And LegalMan(cyc) And pPlatform(cyc)>100
  If pPlatform(cyc)=101 Or pPlatform(cyc)=111 Then pTZ#(cyc)=130
  If pPlatform(cyc)=102 Or pPlatform(cyc)=112 Then pTX#(cyc)=130
  If pPlatform(cyc)=103 Or pPlatform(cyc)=113 Then pTZ#(cyc)=-130
  If pPlatform(cyc)=104 Or pPlatform(cyc)=114 Then pTX#(cyc)=-130
 EndIf
 ;CONSIDER SUB-ROUTES
 ;reset satisified
 If Reached(pX#(cyc),pSubX#(cyc),2) Then pSubX#(cyc)=999
 If Reached(pZ#(cyc),pSubZ#(cyc),2) Then pSubZ#(cyc)=999 
 ;backstage entry/exit
 If (pZ#(cyc)<150 And pTZ#(cyc)>150) Or (pZ#(cyc)>150 And pTZ#(cyc)<150) Or (pZ#(cyc)<475 And pTZ#(cyc)>475) Or (pZ#(cyc)>475 And pTZ#(cyc)<475)
  If pX#(cyc)<-26 And pTX#(cyc)<-26 Then pSubX#(cyc)=-30
  If pX#(cyc)>20 And pTX#(cyc)>20 Then pSubX#(cyc)=24
  If pX#(cyc)<4 And pTX#(cyc)>28 Then pSubX#(cyc)=0
  If pX#(cyc)>-4 And pTX#(cyc)<-34 Then pSubX#(cyc)=0
 EndIf
 ;get through video legs
 If pZ#(cyc)>430 And pZ#(cyc)<455 And pTZ#(cyc)>455 Then pSubX#(cyc)=0 ;from arena to backstage
 If pZ#(cyc)>455 And pZ#(cyc)<500 And pTZ#(cyc)<455 Then pSubX#(cyc)=0 ;from backstage to arena
 ;away from sides and through video legs
 If pZ#(cyc)>455 And pZ#(cyc)<485 And pTZ#(cyc)>455 
  If pX#(cyc)<-34 Or pX#(cyc)>28 Then pSubZ#(cyc)=445
 EndIf
 ;stay in aisle during entrance
 If pOutTim(cyc)<10 And pZ#(cyc)>150 And pTZ#(cyc)<150 And pNowhere(cyc)=0
  pSubX#(cyc)=-2
  If matchTeams>0 And LegalMan(cyc)=0 And pOutTim(cyc)>1 
   If pTX#(cyc)<-30 Then pSubX#(cyc)=-30
   If pTX#(cyc)>24 Then pSubX#(cyc)=24
  EndIf
 EndIf
 ;getting around ring
 If pAgenda(cyc)=>13 And pAgenda(cyc)=<16 And pChaosTim(cyc)=0
  If pZ#(cyc)>wBlockZ1#(1) And pTZ#(cyc)<wBlockZ2#(3) Then pSubX#(cyc)=-110
  If pZ#(cyc)<wBlockZ2#(3) And pTZ#(cyc)>wBlockZ1#(1) Then pSubX#(cyc)=110
  If pX#(cyc)>wBlockX1#(2) And pTX#(cyc)<wBlockX2#(4) Then pSubZ#(cyc)=-110
  If pX#(cyc)<wBlockX2#(4) And pTX#(cyc)>wBlockX1#(2) Then pSubZ#(cyc)=110
 EndIf
 ;EXECUTE AGENDA
 ;determine values
 pActX#(cyc)=pTX#(cyc) : pActZ#(cyc)=pTZ#(cyc)
 If pSubX#(cyc)<>999 Then pActX#(cyc)=pSubX#(cyc) : pFollow(cyc)=2
 If pSubZ#(cyc)<>999 Then pActZ#(cyc)=pSubZ#(cyc) : pFollow(cyc)=2
 If pAgenda(cyc)=22 Then pActX#(cyc)=pLeaveX#(cyc) : pActZ#(cyc)=pLeaveZ#(cyc) : pFollow(cyc)=2 
 ;move to target location
 If Reached(pX#(cyc),pActX#(cyc),pFollow(cyc))=0 And pSatisfied(cyc)=0
  If pX#(cyc)>pActX#(cyc) Then cLeft(cyc)=1
  If pX#(cyc)<pActX#(cyc) Then cRight(cyc)=1
 EndIf
 If Reached(pZ#(cyc),pActZ#(cyc),pFollow(cyc))=0 And pSatisfied(cyc)=0
  If pZ#(cyc)>pActZ#(cyc) Then cDown(cyc)=1
  If pZ#(cyc)<pActZ#(cyc) Then cUp(cyc)=1
 EndIf
 ;consider satisfied?
 If Reached(pX#(cyc),pActX#(cyc),pFollow(cyc)) And Reached(pZ#(cyc),pActZ#(cyc),pFollow(cyc))
  pSubX#(cyc)=999 : pSubZ#(cyc)=999
  pSatisfied(cyc)=20 : pRunAgenda(cyc)=0
 EndIf
 ;additional running
 pRunAgenda(cyc)=pRunAgenda(cyc)-1
 If pRunAgenda(cyc)<0 Then pRunAgenda(cyc)=0
 randy=Rnd(0,300)
 If randy=0 And pRunAgenda(cyc)=0 Then pRunAgenda(cyc)=Rnd(50,200)
 If pAgenda(cyc)=1 And pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),200)=0 Then pRunAgenda(cyc)=10
 If pAgenda(cyc)=10 Or pAgenda(cyc)=11
  If InProximity(cyc,pFoc(cyc),40)=0 Then pRunAgenda(cyc)=5
 EndIf
 If matchState=3 And matchCage>0 And matchOTT=1 And LegalMan(cyc)
  If pPlatform(pFoc(cyc))>100 Then pRunAgenda(cyc)=10
  If pPlatform(cyc)=0 And InsideRing(pTX#(cyc),pTZ#(cyc),0)=0 Then pRunAgenda(cyc)=10
 EndIf
 If pAgenda(cyc)=20 
  If ItemProximity(cyc,pTarget(cyc),50)=0 Then pRunAgenda(cyc)=10
 EndIf
 If pAgenda(cyc)=21 
  If WeaponProximity(cyc,pTarget(cyc),50)=0 Then pRunAgenda(cyc)=10
 EndIf
 If pRunAgenda(cyc)>0 And DirPressed(cyc) Then cRun(cyc)=1
 If NearRopes(cyc,10)>0 And InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(pTX#(cyc),pTZ#(cyc),-30)=0 
  cRun(cyc)=0
 EndIf
 If cAttack(cyc)=1 And InProximity(cyc,pFoc(cyc),30)=0 Then cRun(cyc)=0
 ;static during promos
 If matchState=2 And pControl(cyc)=0
  cUp(cyc)=0 : cDown(cyc)=0 : cLeft(cyc)=0 : cRight(cyc)=0 : cRun(cyc)=0
 EndIf
 ;ATTACKING
 ;assess violence
 v=pFoc(cyc) : violent=0
 If pRole(cyc)<>1 Then violent=1
 If matchState=3 And matchCage>0 And matchOTT=1 And LegalMan(cyc)
  If pAgenda(cyc)=0 And AttackViable(v)=2 Then violent=0
 EndIf
 If pRole(cyc)=1 And LegalMan(v)=0 And InsideRing(pX#(v),pZ#(v),-20) And AttackViable(v)=1
  If pChaosTim(v)=0 Or pRole(v)=>2 Then violent=1
 EndIf
 If pRole(cyc)=1 And pRefAnger(cyc)>0 Then violent=1
 randy=Rnd(0,10)
 If randy=0 And pRole(cyc)=1 And pChar(v)=charEnemy(pChar(cyc)) Then violent=1
 ;find matches
 If violent=1 And v>0 And AttackSensible(cyc,v)
  If AttackViable(v)=2 Then range=15 Else range=30
  If (pPlatform(cyc)=>1 And pY#(v)=wGround#) Or pPlatform(cyc)=>5 Then range=80
  If AttackViable(v)>0 And InProximity(cyc,v,range)
   range=6
   If pAnim(v)=40 Or pAnim(v)=41 Or pAnim(v)=42 Or pAnim(v)=43 Or pAnim(v)=100 Then range=7
   If pAnim(v)=3 And pMomentum(v)>0 Then range=9
   randy=Rnd(0,20)
   If randy=<1 Then range=range+1
   If randy=2 Then range=range+2
   If (pPlatform(cyc)=>1 And pY#(v)=wGround#) Or pPlatform(cyc)=>5 Then range=25
   turner=0
   If optLevel=>4 And InProximity(cyc,v,15) And pControl(cyc)=0 And pControl(v)>0 And AttackViable(v)=1 And pAnim(cyc)<>3 Then turner=1
   If InRange(cyc,v,range)>0 Or turner=1
    ;assess intensity
    If optLevel=0 Then intensity=500
    If optLevel=1 Then intensity=250
    If optLevel=2 Then intensity=80
    If optLevel=3 Then intensity=20
    If optLevel=>4 Then intensity=15
    If game=1 And intensity>100 Then intensity=100
    If game=1 And matchReward=>2 And matchReward=<4 And intensity>20 Then intensity=20
    If game=1 And gamSchedule(slot,GetDate())=5 And intensity>20 Then intensity=20
    If game=1 And pTeam(cyc)=pTeam(matchPlayer) And intensity>20 Then intensity=20
    ;dilute intensity?
    relax=0
    If matchState=4 Or pRole(v)=1 Or pRole(cyc)=1 Or pRole(cyc)=2 Or pAgenda(cyc)=11 Or cTaunt(cyc)=1 Or pPlatform(cyc)=>5 
     relax=1
    EndIf
    If matchState=3 And pAgenda(cyc)=0 And matchCage>0 And matchOTT=1 Then relax=1
    If relax=1 And intensity<200 Then intensity=200
    If matchState=3 And pChaosTim(cyc)=0 And (pRole(cyc)=2 Or pEliminated(cyc)) Then intensity=500
    If matchState=3 And pTeam(cyc)=pTeam(v) Then intensity=500
    If game=1 And pChar(cyc)=gamOpponent(slot,GetDate()) And gamVariable(slot)=6 Then intensity=500 
    randy=Rnd(0,intensity)
    ;assess urgency
    urgent=0 
    If pSpecial(cyc)=0 And pMomentum(v)=0
     If TurnViable(v)=1 Then urgent=1
     If pAnim(v)=>40 And pAnim(v)<60 Then urgent=1
    EndIf 
    ;corner move offset
    If randy<10 And pPlatform(v)<0 And InsideRing(pX#(cyc),pZ#(cyc),-20) Then randy=10
    ;be quick against potent enemies
    If urgent=1
     If attackWeapon(3,charAttack(pChar(cyc),3))>1 And NearGrounded(cyc,20)=0
      If randy=9 And pDazed(v)>0 Then randy=10 ;limit spit
     EndIf
     If randy=<8 Then cAttack(cyc)=1 : cGrab(cyc)=0
     If randy=9 Then cAttack(cyc)=1 : cGrab(cyc)=1
     If randy=10 And GrabViable(v)>0 And pY#(cyc)=pY#(v) Then cAttack(cyc)=0 : cGrab(cyc)=1
    EndIf
    ;be strong against weakened enemies
    If urgent=0
     If attackWeapon(3,charAttack(pChar(cyc),3))>1 And NearGrounded(cyc,20)=0
      If (randy=>6 And randy=<7) Or (randy=>5 And randy=<7 And pDazed(v)>0) Then randy=8 ;limit spit
     EndIf
     If randy=<4 Then cAttack(cyc)=1 : cGrab(cyc)=0
     If randy=>5 And randy=<7 Then cAttack(cyc)=1 : cGrab(cyc)=1
     If randy=>8 And randy=<10 And GrabViable(v)>0 And pY#(cyc)=pY#(v) Then cAttack(cyc)=0 : cGrab(cyc)=1
    EndIf
    ;avoid grappling in shoot fights
    If matchPreset=7 And cGrab(cyc)=1 Then cAttack(cyc)=1 : cGrab(cyc)=Rnd(0,1)
    ;drop if carrying
    If pCarrying(cyc)>0 Then cPick(cyc)=1 : cGrab(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
   EndIf
  EndIf
 EndIf
 ;ITEM INTERACTION
 ;pick up item
 If pAgenda(cyc)=20 And pTarget(cyc)>0 And pCarrying(cyc)=0 And pScar(cyc,19)=<4
  v=pTarget(cyc)
  If ItemProximity(cyc,v,20) And pY#(cyc)=iY#(v)
   ScanItem(v,2)
   If ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),0)
    cPick(cyc)=1 : cGrab(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
    randy=Rnd(0,5)
    If randy=0 And pRole(cyc)<>1 And pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0 And iFlammable(iType(v)) And iBurning(v)=0 Then cRun(cyc)=1
   EndIf
  EndIf
 EndIf
 ;pick up weapon
 If pAgenda(cyc)=21 And pTarget(cyc)>0 And pHolding(cyc)=0 And pScar(cyc,19)=<4
  v=pTarget(cyc)
  If WeaponProximity(cyc,v,weapSize#(weapType(v))) And pY#(cyc)=weapY#(v)
   cPick(cyc)=1 : cGrab(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
   randy=Rnd(0,10)
   If randy=0 And pRole(cyc)<>1 And weapFlammable(weapType(v)) And weapBurning(v)=0 Then cRun(cyc)=1
  EndIf
 EndIf
 ;item dropping
 randy=Rnd(0,100)
 If matchState=3 And matchRules=>1 And pRole(cyc)=1 And pCarrying(cyc)>0 And InsideRing(pX#(cyc),pZ#(cyc),0)=0 Then randy=0 ;ref tidy
 If randy=0 And pCarrying(cyc)>0 Then cPick(cyc)=1 : cGrab(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
 ;weapon dropping/throwing
 If pHolding(cyc)>0
  randy=Rnd(0,500)
  If matchRules=>2 And matchState=3 And LegalMan(cyc) And FindReferee(1)>0 Then randy=0 ;avoid DQ!
  If randy=0 Then cPick(cyc)=1 : cGrab(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
  title=0
  If matchState<>3 And weapType(pHolding(cyc))=>21 And weapType(pHolding(cyc))=<23 Then title=1
  If randy=>1 And randy=<3 And title=0 And Isolated(cyc,30) Then cGrab(cyc)=1 : cPick(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0 
 EndIf
 If matchState=<2 And matchPromo>0 And weapType(pHolding(cyc))=5 Then cPick(cyc)=0 ;promo prevention
 ;TAUNTING
 If Isolated(cyc,20) Or matchState<>3
  chance=300
  If matchState=1 Or matchState=4 Then chance=150
  If matchState=4 And pTeam(cyc)=pTeam(matchWinner) Then chance=50
  If DirPressed(cyc) Then chance=chance*2
  randy=Rnd(0,chance)
  If randy=0 Then cTaunt(cyc)=1
 EndIf
End Function

;----------------------------------------------------------------
;////////////////// TRANSLATE INPUT /////////////////////////////
;----------------------------------------------------------------
Function TranslateInput(cyc)
 ;declaw?
 declaw=0
 If matchState=<2 Then declaw=1 ;entrances
 If matchState=3 And pRole(cyc)<>1 And pRole(cyc)<>3 And LegalMan(cyc)=0 And pChaosTim(cyc)=0 
  If InsideRing(pX#(cyc),pZ#(cyc),-20) Then declaw=1 ;team-mates
  If InsideRing(pX#(cyc),pZ#(cyc),0) And pRole(cyc)=2 Then declaw=1 ;managers
  If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then declaw=1 ;avoid buckle loophole
 EndIf
 If declaw=1 Then cAttack(cyc)=0 : cGrab(cyc)=0
 ;switch focus
 If cSwitch(cyc)=1 And TurnViable(cyc)=1 And pFocTim(cyc)<20
  PlaySound sMenuSelect
  SwitchFocus(cyc)
 EndIf
 ;movement
 If pAnim(cyc)=0
  If DirPressed(cyc)=1 And (cRun(cyc)=0 Or pDazed(cyc)>0) Then ChangeAnim(cyc,2) ;walk
  If cRun(cyc)=1 And pDazed(cyc)=0 Then ChangeAnim(cyc,3) ;run
  If pMomentum(cyc)>0 Then ChangeAnim(cyc,3) ;forced run
 EndIf
 ;flying triggers
 If cAttack(cyc)=1 And pPlatform(cyc)>0 And pDazed(cyc)=0 
  randy=Rnd(0,charAgl(pChar(cyc))/4)
  If randy=0 And pPlatform(cyc)=>5 Then pHP(cyc)=0
  If pHP(cyc)>0 And (TurnViable(cyc)=1 Or (pPlatform(cyc)=>100 And pY#(cyc)>pGround#(cyc)+10))
   tA#=pA#(cyc)
   If pPlatform(cyc)=>100 And pFoc(cyc)>0
    PointEntity p(cyc),p(pFoc(cyc))
    tA#=EntityYaw(p(cyc))
   EndIf
   If pPlatform(cyc)=>1 And pPlatform(cyc)=<4 Then range=25 Else range=40
   If FlightClear(cyc,tA#,range) 
    If (pFoc(cyc)>0 And AttackViable(pFoc(cyc))=2) Or cRun(cyc)=1 Or cGrab(cyc)=1
     pA#(cyc)=tA# : ChangeAnim(cyc,53) ;flying crush
    Else
     pA#(cyc)=tA# : ChangeAnim(cyc,44) ;flying attack
    EndIf
   EndIf
  EndIf
 EndIf
 ;ACTIVE COMMANDS
 If pAnim(cyc)=>0 And pAnim(cyc)=<3 And pHP(cyc)>0 And pDazed(cyc)=0
  ;running attacks
  runup=0
  If (pAnim(cyc)=3 And pAnimTim(cyc)>5) Or pOldAnim(cyc)=4 Then runup=1
  If cAttack(cyc)=1 And pMomentum(cyc)<10 And runup=1 And pPlatform(cyc)=<4
   If NearGrounded(cyc,40) And pPlatform(cyc)=0
    ChangeAnim(cyc,52) ;running crush
   Else
    ChangeAnim(cyc,43) ;running attack
   EndIf
   ;translate to outside
   If charAgl(pChar(cyc))=>70 And pInjured(cyc)=0 And matchCage=0 And matchOTT=0 
    If pFoc(cyc)>0 And InsideRing(pX#(pFoc(cyc)),pZ#(pFoc(cyc)),0)=0 And InsideRing(pX#(cyc),pZ#(cyc),0) And pPlatform(cyc)=0
     ResetDummy(cyc)
     MoveEntity dummy,0,0,50
     If InsideRing(EntityX(dummy),EntityZ(dummy),0)=0 Then ChangeAnim(cyc,55)
    EndIf
   EndIf
  EndIf
  ;standing attacks
  If cAttack(cyc)=1 And pMomentum(cyc)=0 And runup=0 And pPlatform(cyc)=<4
   If NearGrounded(cyc,20)
    ChangeAnim(cyc,50) ;stomp
    If cGrab(cyc)=1 Or cRun(cyc)=1 Then ChangeAnim(cyc,51) ;big stomp
   Else
    If DirPressed(cyc) Or pPlatform(cyc)>0 Then ChangeAnim(cyc,40) ;upper attack
    If DirPressed(cyc)=0 And pPlatform(cyc)=0 Then ChangeAnim(cyc,41) ;lower attack
    If cGrab(cyc)=1 Or cRun(cyc)=1 Then ChangeAnim(cyc,42) ;big attack
   EndIf
  EndIf
  ;grapple lunges
  If cGrab(cyc)=1 And cAttack(cyc)=0 And pMomentum(cyc)=0 And pPlatform(cyc)=<4 And matchPreset<>7
   If NearGrounded(cyc,25)
    ChangeAnim(cyc,200)
   Else
    ChangeAnim(cyc,100)
   EndIf
  EndIf
  ;throw weapon
  If cGrab(cyc)=1 And pMomentum(cyc)=0 And pHolding(cyc)>0 
   If pFoc(cyc)=0 And Isolated(cyc,30) Then ChangeAnim(cyc,36)
   If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),30)=0 Then ChangeAnim(cyc,36)
  EndIf
  ;TAUNT COMMANDS
  If cTaunt(cyc)=1 And pMomentum(cyc)=0
   ;pin attempt?
   If pRole(cyc)=0 And pPlatform(cyc)=0 And matchState=3
    v=pFoc(cyc)
    If v>0 And pRole(v)=0 And InProximity(cyc,v,16) And PinViable(v) And pElevation#(cyc)=>pElevation#(v)
     pFoc(cyc)=v : pFoc(v)=cyc
     pPinning(cyc)=v : pPinner(v)=cyc
	 pPinStyle(cyc)=FindPin(cyc,v)
	 ChangeAnim(cyc,90)
    EndIf
   EndIf
   ;ref examination?
   If pRole(cyc)=1 And pPlatform(cyc)=0 And matchState=3
    v=pFoc(cyc)
    If RefViable(pFoc(cyc))=0 And pControl(cyc)>0 ;assistance for humans
     v=0
     For count=1 To no_wrestlers
      If RefViable(count)>0 And InProximity(cyc,count,25) And pChecked(count)=0 Then pFoc(cyc)=count : v=count
     Next
    EndIf
    If v>0 And RefViable(v)>0 And InProximity(cyc,v,25) And pChecked(v)=0
     pRefVictim(cyc)=v : pChecked(v)=1
     If RefViable(v)=1 Then pRefAward(cyc)=pPinner(v)
     If RefViable(v)=2 Then pRefAward(cyc)=pGrappler(v)
     pRefCount(cyc)=0
	 ChangeAnim(cyc,95)
    EndIf
   EndIf
   ;unfasten pad!
   If pY#(cyc)=wStage# And pPlatform(cyc)=0 And Isolated(cyc,15)
    For v=1 To 4
     If BuckleProximity(cyc,v,10) And padExposed(v)=0
      PointEntity p(cyc),FindChild(world,"Pad"+Dig$(padID(v),10))
      pA#(cyc)=EntityYaw(p(cyc))
      ChangeAnim(cyc,403)
     EndIf
    Next
   EndIf
   ;taunt
   If pAnim(cyc)<90 Then ChangeAnim(cyc,5)
  EndIf
  ;PICK UP LUNGES
  If cPick(cyc)=1 And pCarrying(cyc)=0 And pMomentum(cyc)=0
   ;items
   If pPlatform(cyc)=0
    For v=1 To no_items
     If ItemProximity(cyc,v,30) And iCarrier(v)=0 And iAnim(v)=0 And pAnim(cyc)<>30 And pAnim(cyc)<>38 And iCarryDepth#(iType(v),iState(v))>0
      ScanItem(v,2)
      If ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),0)
       PointEntity p(cyc),i(v)
       pA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
       ChangeAnim(cyc,30)
       If cRun(cyc)=1 And iState(v)=1 And iBurning(v)=0 And pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0
        ChangeAnim(cyc,38)
       EndIf
      EndIf
     EndIf
    Next
   EndIf
   ;weapons
   If pHolding(cyc)=0
    For v=1 To no_weaps
     range=weapSize#(weapType(v))+5
     If WeaponProximity(cyc,v,range) And weapCarrier(v)=0 And pAnim(cyc)<>35 And pAnim(cyc)<>38
      PointEntity p(cyc),weap(v)
      pA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
      ChangeAnim(cyc,35)
      If cRun(cyc)=1 And weapBurning(v)=0 Then ChangeAnim(cyc,38)
     EndIf
    Next
   EndIf
  EndIf
  ;drop weapon
  If cPick(cyc)=1 And pHolding(cyc)>0 And pAnim(cyc)<>30 And pAnim(cyc)<>38
   ChangeAnim(cyc,33)
  EndIf
 EndIf
 ;check for climbing
 If pDazed(cyc)=0 Then FindClimbing(cyc)
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;SWAP CONTROLS
Function SwapControls(cyc,v,style) ;0=tag, 1=forced
 ;prepare to swap
 temp=pControl(v)
 If style=1 And matchPlayer=0 Then pControl(cyc)=3 : matchPlayer=cyc
 ;make transition
 pControl(v)=pControl(cyc) : pControl(cyc)=temp
 If matchPlayer=cyc And pControl(v)>0 Then matchPlayer=v
 If matchPlayer=v And pControl(cyc)>0 Then matchPlayer=cyc
 ;update camfoc
 If camFoc=cyc Then camFoc=v
 If style=1 Then camTim=100
End Function

;DIRECTION PRESSED?
Function DirPressed(cyc)
 value=0
 If cUp(cyc)=1 Or cDown(cyc)=1 Or cLeft(cyc)=1 Or cRight(cyc)=1 Then value=1
 Return value
End Function

;ACTION PRESSED?
Function ActionPressed(cyc)
 value=0
 If cAttack(cyc)=1 Or cRun(cyc)=1 Or cGrab(cyc)=1 Or cPick(cyc)=1 Then value=1
 Return value
End Function

;CHANGE FOCUS MANUALLY
Function SwitchFocus(cyc)
 ;nearest enemy
 oldFoc=pFoc(cyc)
 If pFocTim(cyc)=0 
  pFoc(cyc)=0
  For v=1 To no_plays
   If pFoc(cyc)=0 And InProximity(cyc,v,50) And cyc<>v And v<>oldFoc And pTeam(cyc)<>pTeam(v) And pHidden(v)=0
    pFoc(cyc)=v
   EndIf
  Next
 EndIf
 ;browse all
 If pFocTim(cyc)>0 Or pFoc(cyc)=oldFoc 
  its=0
  Repeat
   satisfied=1
   pFoc(cyc)=pFoc(cyc)+1
   If pFoc(cyc)>no_plays 
    If Isolated(cyc,30) Then pFoc(cyc)=0 Else pFoc(cyc)=1
   EndIf
   If pFoc(cyc)=cyc Or pTeam(pFoc(cyc))=pTeam(cyc) Then satisfied=0
   If pOutTim(pFoc(cyc))=0 Or pHidden(pFoc(cyc))>0 Then satisfied=0
   its=its+1
   If its>100 Then satisfied=1
  Until satisfied=1
 EndIf
 pFocTim(cyc)=30
End Function

;GET NEW ENEMY
Function GetNewFoc(cyc)
 its=its+1
 Repeat
  satisfied=1
  pFoc(cyc)=Rnd(1,no_plays)
  ;favour leader
  randy=Rnd(0,1)
  If randy=0 And matchState=3 And pRole(cyc)=0 And matchLeader>0 Then pFoc(cyc)=matchLeader 
  ;favour enemies
  For v=1 To no_wrestlers
   If pChar(v)=charEnemy(pChar(cyc)) And pTeam(cyc)<>pTeam(v) And LegalMan(v) Then pFoc(cyc)=v
  Next
  ;avoid friends
  randy=Rnd(0,1)
  If randy=0 And pChar(pFoc(cyc))=charFriend(pChar(cyc)) Then satisfied=0
  ;managers favour client
  If pRole(cyc)=2 And pChaosTim(cyc)=0 Then pFoc(cyc)=FindClient(cyc) 
  ;avoid undesirables
  If matchState<4
   If pRole(cyc)=<1 And pRole(pFoc(cyc))<>0 Then satisfied=0 ;non-wrestlers
   If (LegalMan(cyc) Or pRole(cyc)=1) And LegalMan(pFoc(cyc))=0 Then satisfied=0 ;illegals
  EndIf
  ;avoid suicide!
  If pTeam(cyc)=pTeam(pFoc(cyc)) And pChar(pFoc(cyc))<>charEnemy(pChar(cyc)) Then satisfied=0 
  If pFoc(cyc)=cyc Then satisfied=0 
  ;avoid unavailable
  If pOutTim(pFoc(cyc))=0 Or pHidden(pFoc(cyc))>0 Then satisfied=0 
  its=its+1
  If its>100 Then pFoc(cyc)=0 : satisfied=1
 Until satisfied=1
End Function

;REACHED DESTINATION?
Function Reached(curr#,dest#,range)
 value=0
 If curr#>dest#-range And curr#<dest#+range Then value=1
 Return value
End Function

;LOCATION INSIDE RING?
Function InsideRing(x#,z#,offset#)
 value=0
 If x#>wBlockX1#(0)-offset# And x#<wBlockX2#(0)+offset# And z#>wBlockZ1#(0)-offset# And z#<wBlockZ2#(0)+offset# Then value=1
 Return value
End Function

;IS PLAYER ISOLATED?
Function Isolated(cyc,range)
 value=1
 For v=1 To no_plays
  If cyc<>v And InProximity(cyc,v,range) And pY#(cyc)>pY#(v)-15 And pY#(cyc)<pY#(v)+15 Then value=0
 Next
 Return value
End Function

;ENEMY IN ROUGH PROXIMITY?
Function InProximity(cyc,v,range)
 value=0
 If RealX#(cyc)>RealX#(v)-range And RealX#(cyc)<RealX#(v)+range And RealZ#(cyc)>RealZ#(v)-range And RealZ#(cyc)<RealZ#(v)+range ;And pY#(cyc)>pY#(v)-30 And pY#(cyc)<pY#(v)+15 
  value=1
 EndIf
 Return value
End Function

;ENEMY IN RANGE?
Function InRange(cyc,v,range)
 ;position probe
 ResetDummy(cyc)
 ;find matches
 value=0
 If (pY#(cyc)>pY#(v)-30 And pY#(cyc)<pY#(v)+15) Or (pPlatform(cyc)=>1 And pY#(v)=wGround#) Or pPlatform(cyc)=>5
  For depth=1 To range
   If value=0
    MoveEntity dummy,0,0,2
    checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
    If checkX#>RealX#(v)-8 And checkX#<RealX#(v)+8 And checkZ#>RealZ#(v)-8 And checkZ#<RealZ#(v)+8 
     If depth>1 Or pAnim(cyc)=200 Then value=depth
    EndIf
   EndIf
  Next
 EndIf
 Return value
End Function

;ENEMY IN DIRECT RANGE?
Function InDirectRange(cyc,v,range)
 ;position probe
 ResetDummy(cyc)
 MoveEntity dummy,0,0,range
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find matches
 value=0
 If checkX#>pX#(v)-10 And checkX#<pX#(v)+10 And checkZ#>pZ#(v)-10 And checkZ#<pZ#(v)+10 And pY#(cyc)>pY#(v)-30 And pY#(cyc)<pY#(v)+15 
  value=1
 EndIf
 Return value
End Function

;NEAR A GROUNDED ENEMY?
Function NearGrounded(cyc,range)
 ;find general victims
 value=0
 For v=1 To no_plays
  If InProximity(cyc,v,range) And AttackViable(v)=2 Then value=1
 Next
 ;cancel if target doesn't comply
 If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),range+10) And AttackViable(pFoc(cyc))<>2 Then value=0
 Return value
End Function

;FIND CLIMBING REQUESTS
Function FindClimbing(cyc)
 ;CLIMBING UP TO APRON
 locked=0
 If matchState=3 And (pChaosTim(cyc)=0 Or matchOTT=1)
  If pEliminated(cyc) Or pRole(cyc)=2 Then locked=1
 EndIf
 If matchCage>0 Then locked=1
 If locked=0 And pPlatform(cyc)=0 And pY#(cyc)=wGround# And (pAnim(cyc)=2 Or pAnim(cyc)=3 Or pAnim(cyc)=31 Or pAnim(cyc)=32)
  ;north side
  If cDown(cyc)=1 And pX#(cyc)>wBlockX1#(0)+26 And pX#(cyc)<wBlockX2#(0)-26 And pZ#(cyc)>wBlockZ2#(0)-4 And pZ#(cyc)<wBlockZ2#(0)+4
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,180,15) Then pPlatform(cyc)=1 : pA#(cyc)=180 : ChangeAnim(cyc,10)
  EndIf
  ;east side
  If cLeft(cyc)=1 And pX#(cyc)>wBlockX2#(0)-4 And pX#(cyc)<wBlockX2#(0)+4 And pZ#(cyc)>wBlockZ1#(0)+26 And pZ#(cyc)<wBlockZ2#(0)-26
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,90,15) Then pPlatform(cyc)=2 : pA#(cyc)=90 : ChangeAnim(cyc,10)
  EndIf
  ;south side
  If cUp(cyc)=1 And pX#(cyc)>wBlockX1#(0)+26 And pX#(cyc)<wBlockX2#(0)-26 And pZ#(cyc)>wBlockZ1#(0)-4 And pZ#(cyc)<wBlockZ1#(0)+4
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,0,15) Then pPlatform(cyc)=3 : pA#(cyc)=0 : ChangeAnim(cyc,10)
  EndIf
  ;west side
  If cRight(cyc)=1 And pX#(cyc)>wBlockX1#(0)-4 And pX#(cyc)<wBlockX1#(0)+4 And pZ#(cyc)>wBlockZ1#(0)+26 And pZ#(cyc)<wBlockZ2#(0)-26
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,270,15) Then pPlatform(cyc)=4 : pA#(cyc)=270 : ChangeAnim(cyc,10)
  EndIf
  ;carrying correction
  If pAnim(cyc)=10 And pCarrying(cyc)>0 Then pA#(cyc)=pOldA#(cyc) : ChangeAnim(cyc,39)
 EndIf
 ;CLIMBING IN FROM APRON
 locked=0
 If matchState=3 And pRole(cyc)<>1 And LegalMan(cyc)=0 And pChaosTim(cyc)=0 
  If (pRole(cyc)=0 And matchTeams=2) Or pRole(cyc)=2 Then locked=1
 EndIf
 If locked=0 And pPlatform(cyc)>0 And pY#(cyc)=wStage# And (pAnim(cyc)=2 Or pAnim(cyc)=3 Or pAnim(cyc)=31 Or pAnim(cyc)=32)
  ;north ropes
  If cDown(cyc)=1 And pPlatform(cyc)=1 And pX#(cyc)>wBlockX1#(0)+30 And pX#(cyc)<wBlockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 And PassageClear(cyc,180,15) Then pPlatform(cyc)=0 : pA#(cyc)=180 : ChangeAnim(cyc,11) : ShakeRopes(1,1,3)
  EndIf
  ;east ropes
  If cLeft(cyc)=1 And pPlatform(cyc)=2 And pZ#(cyc)>wBlockZ1#(0)+30 And pZ#(cyc)<wBlockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 And PassageClear(cyc,90,15) Then pPlatform(cyc)=0 : pA#(cyc)=90 : ChangeAnim(cyc,11) : ShakeRopes(2,1,3)
  EndIf
  ;south ropes
  If cUp(cyc)=1 And pPlatform(cyc)=3 And pX#(cyc)>wBlockX1#(0)+30 And pX#(cyc)<wBlockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 And PassageClear(cyc,0,15) Then pPlatform(cyc)=0 : pA#(cyc)=0 : ChangeAnim(cyc,11) : ShakeRopes(3,1,3)
  EndIf
  ;west ropes
  If cRight(cyc)=1 And pPlatform(cyc)=4 And pZ#(cyc)>wBlockZ1#(0)+30 And pZ#(cyc)<wBlockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 And PassageClear(cyc,270,15) Then pPlatform(cyc)=0 : pA#(cyc)=270 : ChangeAnim(cyc,11) : ShakeRopes(4,1,3)
  EndIf
 EndIf 
 ;CLIMBING OUT TO APRON
 If pPlatform(cyc)=0 And pY#(cyc)=wStage# And (pAnim(cyc)=2 Or pAnim(cyc)=3 Or pAnim(cyc)=31 Or pAnim(cyc)=32) And pAgenda(cyc)<>12
  ;north ropes
  If cUp(cyc)=1 And pX#(cyc)>wBlockX1#(1)+30 And pX#(cyc)<wBlockX2#(1)-30 And pZ#(cyc)>wBlockZ1#(1)-2 And pZ#(cyc)<wBlockZ1#(1)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,0,15) Then pPlatform(cyc)=1 : pA#(cyc)=0 : ChangeAnim(cyc,12) : ShakeRopes(1,1,3)
  EndIf
  ;east ropes
  If cRight(cyc)=1 And pX#(cyc)>wBlockX1#(2)-2 And pX#(cyc)<wBlockX1#(2)+2 And pZ#(cyc)>wBlockZ1#(2)+30 And pZ#(cyc)<wBlockZ2#(2)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,270,15) Then pPlatform(cyc)=2 : pA#(cyc)=270 : ChangeAnim(cyc,12) : ShakeRopes(2,1,3)
  EndIf
  ;south ropes
  If cDown(cyc)=1 And pX#(cyc)>wBlockX1#(3)+30 And pX#(cyc)<wBlockX2#(3)-30 And pZ#(cyc)>wBlockZ2#(3)-2 And pZ#(cyc)<wBlockZ2#(3)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,180,15) Then pPlatform(cyc)=3 : pA#(cyc)=180 : ChangeAnim(cyc,12) : ShakeRopes(3,1,3)
  EndIf
  ;west ropes
  If cLeft(cyc)=1 And pX#(cyc)>wBlockX2#(4)-2 And pX#(cyc)<wBlockX2#(4)+2 And pZ#(cyc)>wBlockZ1#(4)+30 And pZ#(cyc)<wBlockZ2#(4)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,90,15) Then pPlatform(cyc)=4 : pA#(cyc)=90 : ChangeAnim(cyc,12) : ShakeRopes(4,1,3)
  EndIf 
  ;carrying correction
  If pAnim(cyc)=12 And pCarrying(cyc)>0 Then pA#(cyc)=pOldA#(cyc) : ChangeAnim(cyc,39)
 EndIf
 ;CLIMBING DOWN FROM APRON
 locked=0
 If matchState=3 And LegalMan(cyc) And matchOTT=1 Then locked=1
 If matchCage>0 Then locked=1
 If locked=0 And pPlatform(cyc)>0 And pY#(cyc)=wStage# And (pAnim(cyc)=2 Or pAnim(cyc)=3 Or pAnim(cyc)=31 Or pAnim(cyc)=32)
  ;north side
  If cUp(cyc)=1 And pPlatform(cyc)=1
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=0 : pA#(cyc)=180 : ChangeAnim(cyc,13)
  EndIf
  ;east side
  If cRight(cyc)=1 And pPlatform(cyc)=2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=0 : pA#(cyc)=90 : ChangeAnim(cyc,13)
  EndIf
  ;south side
  If cDown(cyc)=1 And pPlatform(cyc)=3
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=0 : pA#(cyc)=0 : ChangeAnim(cyc,13)
  EndIf
  ;west side
  If cLeft(cyc)=1 And pPlatform(cyc)=4
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=0 : pA#(cyc)=270 : ChangeAnim(cyc,13)
  EndIf
 EndIf
 ;CLIMB UP TURNBUCKLE FROM APRON (RIGHT)
 If pPlatform(cyc)>0 And pY#(cyc)=wStage# And pAnim(cyc)=>2 And pAnim(cyc)=<3
  ;north side
  If cLeft(cyc)=1 And pPlatform(cyc)=1 And pX#(cyc)<wBlockX1#(0)+29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(5) Then pPlatform(cyc)=5 : pA#(cyc)=180 : ChangeAnim(cyc,20)
  EndIf
  ;east side
  If cUp(cyc)=1 And pPlatform(cyc)=2 And pZ#(cyc)>wBlockZ2#(0)-29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(6) Then pPlatform(cyc)=6 : pA#(cyc)=90 : ChangeAnim(cyc,20)
  EndIf
  ;south side
  If cRight(cyc)=1 And pPlatform(cyc)=3 And pX#(cyc)>wBlockX2#(0)-29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(7) Then pPlatform(cyc)=7 : pA#(cyc)=0 : ChangeAnim(cyc,20)
  EndIf
  ;west side
  If cDown(cyc)=1 And pPlatform(cyc)=4 And pZ#(cyc)<wBlockZ1#(0)+29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(8) Then pPlatform(cyc)=8 : pA#(cyc)=270 : ChangeAnim(cyc,20)
  EndIf
 EndIf
 ;CLIMB UP TURNBUCKLE FROM APRON (LEFT)
 If pPlatform(cyc)>0 And pY#(cyc)=wStage# And pAnim(cyc)=>2 And pAnim(cyc)=<3
  ;north side
  If cRight(cyc)=1 And pPlatform(cyc)=1 And pX#(cyc)>wBlockX2#(0)-29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(6) Then pPlatform(cyc)=6 : pA#(cyc)=180 : ChangeAnim(cyc,21)
  EndIf
  ;east side
  If cDown(cyc)=1 And pPlatform(cyc)=2 And pZ#(cyc)<wBlockZ1#(0)+29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(7) Then pPlatform(cyc)=7 : pA#(cyc)=90 : ChangeAnim(cyc,21)
  EndIf
  ;south side
  If cLeft(cyc)=1 And pPlatform(cyc)=3 And pX#(cyc)<wBlockX1#(0)+29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(8) Then pPlatform(cyc)=8 : pA#(cyc)=0 : ChangeAnim(cyc,21)
  EndIf
  ;west side
  If cUp(cyc)=1 And pPlatform(cyc)=4 And pZ#(cyc)>wBlockZ2#(0)-29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(5) Then pPlatform(cyc)=5 : pA#(cyc)=270 : ChangeAnim(cyc,21)
  EndIf
 EndIf
 ;CLIMB UP TURNBUCKLE FROM INSIDE
 If pPlatform(cyc)=0 And pY#(cyc)=wStage# And pAnim(cyc)=>2 And pAnim(cyc)=<3
  ;north-west post
  If cUp(cyc)=1 And cLeft(cyc)=1 And pX#(cyc)<wBlockX1#(0)+29 And pZ#(cyc)>wBlockZ2#(0)-29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(5) Then pPlatform(cyc)=5 : pA#(cyc)=45 : ChangeAnim(cyc,22)
  EndIf
  ;north-east post
  If cUp(cyc)=1 And cRight(cyc)=1 And pX#(cyc)>wBlockX2#(0)-29 And pZ#(cyc)>wBlockZ2#(0)-29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(6) Then pPlatform(cyc)=6 : pA#(cyc)=315 : ChangeAnim(cyc,22)
  EndIf
  ;south-east post
  If cDown(cyc)=1 And cRight(cyc)=1 And pX#(cyc)>wBlockX2#(0)-29 And pZ#(cyc)<wBlockZ1#(0)+29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(7) Then pPlatform(cyc)=7 : pA#(cyc)=225 : ChangeAnim(cyc,22)
  EndIf
  ;south-west post
  If cDown(cyc)=1 And cLeft(cyc)=1 And pX#(cyc)<wBlockX1#(0)+29 And pZ#(cyc)<wBlockZ1#(0)+29
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And BuckleClear(8) Then pPlatform(cyc)=8 : pA#(cyc)=135 : ChangeAnim(cyc,22)
  EndIf
 EndIf
 ;CLIMB DOWN FROM TURNBUCKLE
 If pPlatform(cyc)>0 And pY#(cyc)=wBuckle# And pAnim(cyc)=>23 And pAnim(cyc)=<24
  ;north-west post (right to north apron)
  If cRight(cyc)=1 And cDown(cyc)=0 And pPlatform(cyc)=5
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,270,15) Then pPlatform(cyc)=1 : pA#(cyc)=180 : ChangeAnim(cyc,25)
  EndIf
  ;north-west post (down to west apron)
  If cDown(cyc)=1 And cRight(cyc)=0 And pPlatform(cyc)=5
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,180,15) Then pPlatform(cyc)=4 : pA#(cyc)=270 : ChangeAnim(cyc,26)
  EndIf
  ;north-west post (diagonal to ring)
  If cRight(cyc)=1 And cDown(cyc)=1 And pPlatform(cyc)=5
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,225,15) Then pPlatform(cyc)=0 : pA#(cyc)=45 : ChangeAnim(cyc,27)
  EndIf
  ;north-east post (left to north apron)
  If cLeft(cyc)=1 And cDown(cyc)=0 And pPlatform(cyc)=6
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,90,15) Then pPlatform(cyc)=1 : pA#(cyc)=180 : ChangeAnim(cyc,26)
  EndIf
  ;north-east post (down to east apron)
  If cDown(cyc)=1 And cLeft(cyc)=0 And pPlatform(cyc)=6
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,180,15) Then pPlatform(cyc)=2 : pA#(cyc)=90 : ChangeAnim(cyc,25)
  EndIf
  ;north-east post (diagonal to ring)
  If cLeft(cyc)=1 And cDown(cyc)=1 And pPlatform(cyc)=6
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,135,15) Then pPlatform(cyc)=0 : pA#(cyc)=315 : ChangeAnim(cyc,27)
  EndIf
  ;south-east post (up to east apron)
  If cUp(cyc)=1 And cLeft(cyc)=0 And pPlatform(cyc)=7
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,0,15) Then pPlatform(cyc)=2 : pA#(cyc)=90 : ChangeAnim(cyc,26)
  EndIf
  ;south-east post (left to south apron)
  If cLeft(cyc)=1 And cUp(cyc)=0 And pPlatform(cyc)=7
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,90,15) Then pPlatform(cyc)=3 : pA#(cyc)=0 : ChangeAnim(cyc,25)
  EndIf
  ;south-east post (diagonal to ring)
  If cLeft(cyc)=1 And cUp(cyc)=1 And pPlatform(cyc)=7
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,45,15) Then pPlatform(cyc)=0 : pA#(cyc)=225 : ChangeAnim(cyc,27)
  EndIf
  ;south-west post (right to south apron)
  If cRight(cyc)=1 And cUp(cyc)=0 And pPlatform(cyc)=8
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,270,15) Then pPlatform(cyc)=3 : pA#(cyc)=0 : ChangeAnim(cyc,26)
  EndIf
  ;south-west post (up to west apron)
  If cUp(cyc)=1 And cRight(cyc)=0 And pPlatform(cyc)=8
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,0,15) Then pPlatform(cyc)=4 : pA#(cyc)=270 : ChangeAnim(cyc,25)
  EndIf
  ;south-west post (diagonal to ring)
  If cRight(cyc)=1 And cUp(cyc)=1 And pPlatform(cyc)=8
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 And PassageClear(cyc,315,15) Then pPlatform(cyc)=0 : pA#(cyc)=135 : ChangeAnim(cyc,27)
  EndIf
 EndIf
 ;CLIMB HORIZONTAL RAILINGS
 If pPlatform(cyc)=0 And pY#(cyc)=wGround# And pZ#(cyc)=<450 And pAnim(cyc)=>2 And pAnim(cyc)=<3
  For count=1 To 4
   b=3+(2*count)
   ;from right to left
   If cLeft(cyc)=1 And pX#(cyc)>wBlockX2#(b)-2 And pX#(cyc)<wBlockX2#(b)+2 And pZ#(cyc)>wBlockZ1#(b)+5 And pZ#(cyc)<wBlockZ2#(b)-5
    If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2 
    If pFriction(cyc)>20
     pPlatform(cyc)=9 : pPlatZ#(cyc)=pZ#(cyc) : pPlatY#(cyc)=wBlockY2#(b)
     pPlatX#(cyc)=GetCentre#(wBlockX1#(b),wBlockX2#(b))
     pA#(cyc)=90 : ChangeAnim(cyc,28) : pFriction(cyc)=0
    EndIf
   EndIf
   ;from left to right
   If cRight(cyc)=1 And pX#(cyc)>wBlockX1#(b)-2 And pX#(cyc)<wBlockX1#(b)+2 And pZ#(cyc)>wBlockZ1#(b)+5 And pZ#(cyc)<wBlockZ2#(b)-5
    If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2 
    If pFriction(cyc)>20
     pPlatform(cyc)=9 : pPlatZ#(cyc)=pZ#(cyc) : pPlatY#(cyc)=wBlockY2#(b)
     pPlatX#(cyc)=GetCentre#(wBlockX1#(b),wBlockX2#(b))
     pA#(cyc)=270 : ChangeAnim(cyc,28) : pFriction(cyc)=0
    EndIf
   EndIf
  Next
 EndIf
 ;CLIMB VERTICAL RAILINGS
 If pPlatform(cyc)=0 And pY#(cyc)=wGround# And pZ#(cyc)=<450 And pAnim(cyc)=>2 And pAnim(cyc)=<3
  For count=1 To 3
   b=4+(2*count)
   ;from south to north
   If cUp(cyc)=1 And pZ#(cyc)>wBlockZ1#(b)-2 And pZ#(cyc)<wBlockZ1#(b)+2 And pX#(cyc)>wBlockX1#(b)+5 And pX#(cyc)<wBlockX2#(b)-5
    If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2 
    If pFriction(cyc)>20
     pPlatform(cyc)=9 : pPlatX#(cyc)=pX#(cyc) : pPlatY#(cyc)=wBlockY2#(b)
     pPlatZ#(cyc)=GetCentre#(wBlockZ1#(b),wBlockZ2#(b))
     pA#(cyc)=0 : ChangeAnim(cyc,28) : pFriction(cyc)=0
    EndIf
   EndIf
   ;from north to south
   If cDown(cyc)=1 And pZ#(cyc)>wBlockZ2#(b)-2 And pZ#(cyc)<wBlockZ2#(b)+2 And pX#(cyc)>wBlockX1#(b)+5 And pX#(cyc)<wBlockX2#(b)-5
    If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2 
    If pFriction(cyc)>20
     pPlatform(cyc)=9 : pPlatX#(cyc)=pX#(cyc) : pPlatY#(cyc)=wBlockY2#(b)
     pPlatZ#(cyc)=GetCentre#(wBlockZ1#(b),wBlockZ2#(b))
     pA#(cyc)=180 : ChangeAnim(cyc,28) : pFriction(cyc)=0
    EndIf
   EndIf
  Next
 EndIf
 ;CLIMB OFF ITEM/RAILING
 If pPlatform(cyc)=>9 And pAnim(cyc)=>2 And pAnim(cyc)=<3
  If DirPressed(cyc)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pPlatform(cyc)=9 Then range=10 Else range=30
   If pFriction(cyc)>20 And FlightClear(cyc,pTA#(cyc),30) And StepClear(cyc,pTA#(cyc),range)
    pA#(cyc)=pTA#(cyc) : ChangeAnim(cyc,29)
   EndIf
  EndIf
 EndIf
 ;CLIMB ONTO INSIDE WALLS OF CAGE
 If matchCage>0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pY#(cyc)=wStage# And pAnim(cyc)=>2 And pAnim(cyc)=<3
  ;north side
  If cUp(cyc)=1 And pPlatform(cyc)=1
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=101 : pA#(cyc)=0 : ChangeAnim(cyc,14)
  EndIf
  ;east side
  If cRight(cyc)=1 And pPlatform(cyc)=2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=102 : pA#(cyc)=270 : ChangeAnim(cyc,14)
  EndIf
  ;south side
  If cDown(cyc)=1 And pPlatform(cyc)=3
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=103 : pA#(cyc)=180 : ChangeAnim(cyc,14)
  EndIf
  ;west side
  If cLeft(cyc)=1 And pPlatform(cyc)=4
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>7 Then pPlatform(cyc)=104 : pA#(cyc)=90 : ChangeAnim(cyc,14)
  EndIf
 EndIf
 ;CLIMB ONTO OUTSIDE WALLS
 locked=0
 If matchState=3 And pChaosTim(cyc)=0 
  If pEliminated(cyc) Or pRole(cyc)=2 Then locked=1
 EndIf
 If locked=0 And pPlatform(cyc)=0 And pY#(cyc)=wGround# And pAnim(cyc)=>2 And pAnim(cyc)=<3
  ;video wall (left leg)
  If cUp(cyc)=1 And pX#(cyc)>wBlockX1#(12) And pX#(cyc)<wBlockX2#(12) And pZ#(cyc)>wBlockZ1#(12)-2 And pZ#(cyc)<wBlockZ1#(12)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 Then pPlatform(cyc)=100 : pA#(cyc)=0 : ChangeAnim(cyc,14)
  EndIf
  ;video wall (right leg)
  If cUp(cyc)=1 And pX#(cyc)>wBlockX1#(13) And pX#(cyc)<wBlockX2#(13) And pZ#(cyc)>wBlockZ1#(13)-2 And pZ#(cyc)<wBlockZ1#(13)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>14 Then pPlatform(cyc)=100 : pA#(cyc)=0 : ChangeAnim(cyc,14)
  EndIf
  If matchCage>0 And locked=0
   ;north side
   If cDown(cyc)=1 And pX#(cyc)>wBlockX1#(0)+26 And pX#(cyc)<wBlockX2#(0)-26 And pZ#(cyc)>wBlockZ2#(0)-2 And pZ#(cyc)<wBlockZ2#(0)+2
    pFriction(cyc)=pFriction(cyc)+2
    If pFriction(cyc)>14 Then pPlatform(cyc)=111 : pA#(cyc)=180 : ChangeAnim(cyc,14)
   EndIf
   ;east side
   If cLeft(cyc)=1 And pX#(cyc)>wBlockX2#(0)-2 And pX#(cyc)<wBlockX2#(0)+2 And pZ#(cyc)>wBlockZ1#(0)+26 And pZ#(cyc)<wBlockZ2#(0)-26
    pFriction(cyc)=pFriction(cyc)+2
    If pFriction(cyc)>14 Then pPlatform(cyc)=112 : pA#(cyc)=90 : ChangeAnim(cyc,14)
   EndIf
   ;south side
   If cUp(cyc)=1 And pX#(cyc)>wBlockX1#(0)+26 And pX#(cyc)<wBlockX2#(0)-26 And pZ#(cyc)>wBlockZ1#(0)-2 And pZ#(cyc)<wBlockZ1#(0)+2
    pFriction(cyc)=pFriction(cyc)+2
    If pFriction(cyc)>14 Then pPlatform(cyc)=113 : pA#(cyc)=0 : ChangeAnim(cyc,14)
   EndIf
   ;west side
   If cRight(cyc)=1 And pX#(cyc)>wBlockX1#(0)-2 And pX#(cyc)<wBlockX1#(0)+2 And pZ#(cyc)>wBlockZ1#(0)+26 And pZ#(cyc)<wBlockZ2#(0)-26
    pFriction(cyc)=pFriction(cyc)+2
    If pFriction(cyc)>14 Then pPlatform(cyc)=114 : pA#(cyc)=270 : ChangeAnim(cyc,14)
   EndIf
  EndIf
 EndIf
 ;FIND TAGS
 If matchTeams=2 And LegalMan(cyc) And pPlatform(cyc)=0 And pY#(cyc)=wStage# And pAnim(cyc)=>2 And pAnim(cyc)=<3
  tagger=0
  ;north ropes
  If cUp(cyc)=1 And pX#(cyc)>wBlockX1#(1)+30 And pX#(cyc)<wBlockX2#(1)-30 And pZ#(cyc)>wBlockZ1#(1)-2 And pZ#(cyc)<wBlockZ1#(1)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Then tagger=1
  EndIf
  ;east ropes
  If cRight(cyc)=1 And pX#(cyc)>wBlockX1#(2)-2 And pX#(cyc)<wBlockX1#(2)+2 And pZ#(cyc)>wBlockZ1#(2)+30 And pZ#(cyc)<wBlockZ2#(2)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Then tagger=2
  EndIf
  ;south ropes
  If cDown(cyc)=1 And pX#(cyc)>wBlockX1#(3)+30 And pX#(cyc)<wBlockX2#(3)-30 And pZ#(cyc)>wBlockZ2#(3)-2 And pZ#(cyc)<wBlockZ2#(3)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Then tagger=3
  EndIf
  ;west ropes
  If cLeft(cyc)=1 And pX#(cyc)>wBlockX2#(4)-2 And pX#(cyc)<wBlockX2#(4)+2 And pZ#(cyc)>wBlockZ1#(4)+30 And pZ#(cyc)<wBlockZ2#(4)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Then tagger=4
  EndIf 
  ;translate into tag?
  If tagger>0
   PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
   If tagger=1 Then RotateEntity dummy,0,0,0
   If tagger=2 Then RotateEntity dummy,0,270,0
   If tagger=3 Then RotateEntity dummy,0,180,0
   If tagger=4 Then RotateEntity dummy,0,90,0
   MoveEntity dummy,0,0,10
   checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
   For v=1 To no_plays
    If cyc<>v And pTeam(cyc)=pTeam(v) And pRole(v)=0 And pPlatform(v)=tagger And pAnim(v)=>0 And pAnim(v)=<6 
     If TagProximity(v,80) And checkX#>pX#(v)-8 And checkX#<pX#(v)+8 And checkZ#>pZ#(v)-8 And checkZ#<pZ#(v)+8
      ChangeAnim(cyc,404) : ChangeAnim(v,405)
      PointEntity p(cyc),p(v) : pA#(cyc)=EntityYaw(p(cyc))
      PointEntity p(v),p(cyc) : pA#(v)=EntityYaw(p(v))
     EndIf
    EndIf
   Next
  EndIf
 EndIf
End Function

;FIND RAILING CONFLICTS
Function FindRailings(x#,z#)
 value=0
 If z#<475
  If z#>150 And (x#<-34 Or x#>28) Then value=1 ;aisle sides
  If z#=<150 And (x#<-144 Or x#>148) Then value=1 ;ring sides
  If z#<-147 Then value=1 ;bottom
 EndIf
 Return value
End Function

;PASSAGE CLEAR FOR CLIMBING?
Function PassageClear(cyc,angle#,range)
 ;prepare probe 
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 MoveEntity dummy,0,0,range
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find human blocks
 clear=1
 For v=1 To no_plays
  If cyc<>v And checkX#>pX#(v)-10 And checkX#<pX#(v)+10 And checkZ#>pZ#(v)-10 And checkZ#<pZ#(v)+10 And AttackViable(v)<>2
   clear=0
  EndIf
  If InProximity(cyc,v,15) And pAnim(v)=12 Then clear=0
 Next
 ;find item blocks
 For v=1 To no_items
  If iState(v)=0 And iCarrier(v)=0
   range=iSizeX#(iType(v))*2
   If iSizeZ#(iType(v))>iSizeX#(iType(v)) Then range=iSizeZ#(iType(v))*2
   If checkX#>iX#(v)-range And checkX#<iX#(v)+range And checkZ#>iZ#(v)-range And checkZ#<iZ#(v)+range And iY#(v)=>pY#(cyc)
    clear=0
   EndIf
  EndIf
 Next
 Return clear
End Function

;FLIGHT PATH CLEAR?
Function FlightClear(cyc,angle#,range)
 ;prepare probe 
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 MoveEntity dummy,0,0,range
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy) 
 ;find structures
 clear=1
 For v=0 To no_blocks
  excused=0
  If v=0 And InsideRing(pX#(cyc),pZ#(cyc),-20) And pY#(cyc)=>wStage# Then excused=1 
  If v=0 And pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then excused=1 
  If v=0 And matchCage>0 And InsideRing(pX#(cyc),pZ#(cyc),0) And pY#(cyc)=>wStage# 
   If pPlatform(cyc)<1 Or pPlatform(cyc)>4 Then excused=1 
  EndIf
  If excused=0 And checkX#>wBlockX1#(v) And checkX#<wBlockX2#(v) And checkZ#>wBlockZ1#(v) And checkZ#<wBlockZ2#(v) And pY#(cyc)>wBlockY1#(v)
   clear=0
  EndIf
 Next
 ;cage limits
 If matchCage>0
  If InsideRing(pX#(cyc),pZ#(cyc),0) And InsideRing(checkX#,checkZ#,-20)=0 Then clear=0
  If InsideRing(pX#(cyc),pZ#(cyc),0)=0 And InsideRing(checkX#,checkZ#,0) Then clear=0
 EndIf
 ;video logic
 If pPlatform(cyc)=100 And checkZ#>pZ#(cyc) Then clear=0
 Return clear
End Function 

;CLEAR TO STEP OFF ITEM?
Function StepClear(cyc,angle#,range)
 ;prepare probe 
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 MoveEntity dummy,0,0,range
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy) 
 ;leading outside?
 clear=1
 If InsideRing(pX#(cyc),pZ#(cyc),0) And InsideRing(checkX#,checkZ#,-10)=0 Then clear=0
 Return clear
End Function 

;FIND EXIT (off item)
Function FindExit(cyc)
 If pPlatform(cyc)=>9
  ;find clear angle
  tA#=0
  For count=1 To 36
   angle#=Float(count*10)
   If FlightClear(cyc,angle#,40) And StepClear(cyc,angle#,40) And SatisfiedAngle(pA#(cyc),angle#,20)=0 Then tA#=angle# : Exit
  Next
  ;assist during entrances
  If matchState=1 And pZ#(cyc)>90 Then tA#=360
  ;work towards target
  If tA#>0
   PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
   RotateEntity dummy,0,tA#,0
   MoveEntity dummy,0,0,40
   pLeaveX#(cyc)=EntityX(dummy) : pLeaveZ#(cyc)=EntityZ(dummy)
  EndIf
 EndIf
 ;turnbuckle variation
 If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then pLeaveX#(cyc)=0 : pLeaveZ#(cyc)=0
End Function

;FIND MOST SUITABLE PIN
Function FindPin(cyc,v)
 value=Rnd(2,3)
 ;head options
 If PinClear(cyc,v,0,-15,6) Then value=1
 ;left options
 If PinClear(cyc,v,-8,0,10) 
  randy=Rnd(0,1)
  If randy=0 Then value=4 Else value=2
 EndIf
 ;right options
 If PinClear(cyc,v,8,0,10)
  randy=Rnd(0,1)
  If randy=0 Then value=5 Else value=3
 EndIf
 Return value
End Function

;CHECK PIN OPTION IS CLEAR
Function PinClear(cyc,v,posX#,posZ#,range)
 clear=0
 ;probe specified area
 ResetDummy(v)
 MoveEntity dummy,posX#,0,posZ#
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 If pX#(cyc)>checkX#-range And pX#(cyc)<checkX#+range And pZ#(cyc)>checkZ#-range And pZ#(cyc)<checkZ#+range
  clear=1
 EndIf
 ;check for world blocks
 For b=1 To no_blocks
  If checkX#>wBlockX1#(b) And checkX#<wBlockX2#(b) And checkZ#>wBlockZ1#(b) And checkZ#<wBlockZ2#(b) And pY#(cyc)>wBlockY1#(b) And pY#(cyc)<wBlockY2#(b)
   clear=0
  EndIf
 Next
 ;check for item blocks
 If clear>0
  For b=1 To no_items
   If iState(b)=0 And checkX#>iX#(b)-30 And checkX#<iX#(b)+30 And checkZ#>iZ#(b)-30 And checkZ#<iZ#(b)+30 And pY#(cyc)>iY#(b)-5 And pY#(cyc)<iY#(b)+10
    ScanItem(b,0)
    If ItemCollide(cyc,b,checkX#,checkZ#,0.5)=1 Then clear=0
   EndIf
  Next
 EndIf
 Return clear
End Function

;CRUISE TO PIN POSITION
Function ApplyPin(cyc,v,posX#,posZ#,pointZ#)
 ;find & pursue target location
 ResetDummy(v)
 MoveEntity dummy,posX#,0,posZ#
 CruiseToLocation(cyc,EntityX(dummy),EntityZ(dummy),0.5)
 ;face correct area
 ResetDummy(v)
 MoveEntity dummy,0,0,pointZ#
 PointEntity p(cyc),dummy
 pA#(cyc)=CleanAngle#(EntityYaw(p(cyc))) 
End Function

;ESTABLISH WHETHER FALLS COUNT
Function FallsCount(cyc)
 value=0
 If matchRules=>1 And InsideRing(pX#(cyc),pZ#(cyc),0) Then value=1
 If matchRules=0 Then value=1
 Return value
End Function

;FIND AN ACTIVE REFEREE
Function FindReferee(task) ;0=for chaos, 1=for DQ's
 ;find real referees
 value=0
 For cyc=1 To no_plays
  If pRole(cyc)=1 And InsideRing(pX#(cyc),pZ#(cyc),0)
   If task=0 And AttackViable(cyc)<>2 Then value=cyc
   If task=1 And pAnim(cyc)=>0 And pAnim(cyc)=<6 And pAnim(cyc)<>4 And pDazed(cyc)=0 Then value=cyc
  EndIf
 Next
 ;guaranteed if automated
 If no_refs=0 Then value=1
 Return value
End Function

;RISK DISQUALIFICATION!
Function DQ(cyc,v)
 If matchState=3 And (matchSecs>1 Or matchMins>0) And matchRules=2 
  If InsideRing(pX#(cyc),pZ#(cyc),-10) And LegalMan(cyc) And (LegalMan(v) Or pRole(v)=1)
   ;find a witness
   witness=FindReferee(1)
   If pRole(v)=1 And witness=0 Then witness=v
   ;blind eye for friends
   If pChar(v)=charEnemy(pChar(witness)) Or pChar(cyc)=charFriend(pChar(witness)) Then witness=0
   ;generate victim if attacking ref
   pRefAward(0)=v
   If pRole(v)=1 And matchType=>1 And matchType=<4
    Repeat
     pRefAward(0)=Rnd(1,no_plays)
    Until pRefAward(0)<>cyc And pRole(pRefAward(0))=0 And pTeam(pRefAward(0))<>pTeam(cyc)
   EndIf
   ;declare it
   If witness>0 And no_refs>0
    pRefAward(witness)=pRefAward(0)
    pRefVictim(witness)=cyc
    pDQDelay(witness)=1
   EndIf
   ;automated declaration
   If witness>0 And no_refs=0
    ProduceSound(p(cyc),sDQ,22050,1)
    pRefAward(cyc)=pRefAward(0)
    pDQDelay(cyc)=1
   EndIf 
   ;thrill of cheating
   crowdVolTarget#=crowdVolMax#
   Pop(cyc,Rnd(2,8),0) : Pop(0,3,0)
  EndIf
 EndIf
End Function

;DECLARE FALL
Function DeclareFall(cyc,v)
 ;reaction
 PlaySound sBell
 crowdVolTarget#=crowdVolMax#
 ;clock falls
 If matchType=>1 And matchType=<4 And cyc>0
  Pop(cyc,Rnd(2,3),1) : Pop(cyc,9,0)
  pFalls(cyc)=pFalls(cyc)+1
  teamFalls(pTeam(cyc))=teamFalls(pTeam(cyc))+1
  If matchType=4 Then matchLeader=cyc
  matchLoser=v
  PostMessage(cyc,matchType,1)
 EndIf
 ;clock eliminations
 If matchType=5 And v>0
  Pop(0,3-pHeel(v),1)
  pEliminated(v)=1 : pOutTim(v)=300 : pDT(v)=pDT(v)+500
  GetNewLegal(v) : CauseChaos(100)
  If matchLoser=0 Then matchLoser=v
  PostMessage(v,matchType,0)
 EndIf
End Function

;FIND PIN VICTIM'S COUNT
Function FindPinCount(cyc)
 value=0
 For v=1 To no_plays
  ;referee source
  If no_refs>0 And pRole(v)=1 And pRefVictim(v)=cyc Then value=pRefCount(v)
  ;wrestler source
  If no_refs=0 And pPinning(v)=cyc Then value=pRefCount(v)
 Next
 Return value
End Function

;VIABLE TO BE EXAMINED?
Function RefViable(cyc) ;1=pin, 2=submission, 3=KO, 4=blood
 viable=0
 If LegalMan(cyc) And FallsCount(cyc)
  ;bloody 
  If matchBlood=1 And AttackViable(cyc)=2 And pScar(cyc,1)>0 And pDT(cyc)>50 Then viable=4
  ;knocked out
  If matchKOs=1 And AttackViable(cyc)=2 And pDT(cyc)>100 
   If pHealth(cyc)<50 Or pInjured(cyc)>0 Then viable=3
  EndIf
  ;hold victim
  If matchSubs=1 And pGrappler(cyc)>0 And LegalMan(pGrappler(cyc)) And pTeam(pGrappler(cyc))<>pTeam(cyc) 
   If pStretched(cyc)>0 Then viable=2 
  EndIf
  ;pin victim
  If matchPins=1 And pPinner(cyc)>0 And LegalMan(pPinner(cyc)) And pTeam(pPinner(cyc))<>pTeam(cyc) Then viable=1 
 EndIf
 Return viable 
End Function

;CAUSE CHAOS (to all players)
Function CauseChaos(chaos)
 For cyc=1 To no_plays
  pChaosTim(cyc)=chaos
 Next
End Function

;LEGAL MAN?
Function LegalMan(cyc)
 value=0
 If teamLegal(pTeam(cyc))=cyc Or matchTeams=<1 Then value=1
 If pRole(cyc)>0 Or pEliminated(cyc) Then value=0
 Return value
End Function

;ASSIGN NEW LEGAL MAN
Function GetNewLegal(cyc)
 If matchTeams=2 And matchState=3
  ;find replacement
  v=cyc : its=0
  Repeat
   v=v+1 : satisfied=1
   If v>no_plays Then v=1
   If cyc=v Or pTeam(v)<>pTeam(cyc) Then satisfied=0
   If pRole(v)>0 Or pEliminated(v) Then satisfied=0
   its=its+1 
   If its>100 Then v=0 : satisfied=1
  Until satisfied=1
  ;make transition
  teamLegal(pTeam(cyc))=v
  If pTeam(v)=1 Then pFoc(v)=teamLegal(2)
  If pTeam(v)=2 Then pFoc(v)=teamLegal(1)
  pAgenda(v)=2
  ;pass control too
  If cyc<>v
   If optTagControl=2 Or (optTagControl=1 And game=0)
    SwapControls(cyc,v,0)
    camTempTim=50 : camTempFoc=cyc : camOldFoc=camFoc
   EndIf
  EndIf
 EndIf
End Function

;CHARACTER IN TAG CORNER?
Function TagProximity(cyc,range)
 value=0
 If pY#(cyc)=wStage#
  If pX#(cyc)>teamX#(pTeam(cyc))-range And pX#(cyc)<teamX#(pTeam(cyc))+range And pZ#(cyc)>teamZ#(pTeam(cyc))-range And pZ#(cyc)<teamZ#(pTeam(cyc))+range 
   value=1
  EndIf
 EndIf
 Return value
End Function

;FIND TAG POSITION CONFLICTS
Function FindTagConflict(cyc,agenda)
 conflict=0
 For v=1 To no_wrestlers
  If cyc<>v And pTeam(cyc)=pTeam(v) And pAgenda(v)=agenda Then conflict=1
 Next
 Return conflict
End Function

;SENSIBLE TO ATTACK?
Function AttackSensible(cyc,v)
 sensible=1
 ;don't disturb team-mate activity
 If pTeam(pGrappler(v))=pTeam(cyc) Or pTeam(pPinner(v))=pTeam(cyc) Then sensible=0
 ;don't disturb enemy elimination
 If matchType=5 
  If pGrappling(v)>0 And pTeam(pGrappling(v))<>pTeam(cyc) Then sensible=0
  If pGrappler(v)>0 And pTeam(pGrappler(v))<>pTeam(cyc) Then sensible=0
  If pPinning(v)>0 And pTeam(pPinning(v))<>pTeam(cyc) Then sensible=0
  If pPinner(v)>0 And pTeam(pPinner(v))<>pTeam(cyc) Then sensible=0
 EndIf
 ;don't disturb referee examinations
 If matchType=5 And pTeam(v)<>pTeam(cyc) And RefViable(v)=>3
  For count=1 To no_plays
   If pRole(count)=1 And pRefVictim(count)=v Then sensible=0
  Next
 EndIf
 ;don't disturb beneficial refs
 If pRole(v)=1 
  If pRefAward(v)>0 And pTeam(pRefAward(v))=pTeam(cyc) Then sensible=0
  If matchType=5 And pRefVictim(v)>0 And pTeam(pRefVictim(v))<>pTeam(cyc) Then sensible=0
 EndIf
 ;wreckless if no team!
 If pTeam(cyc)=0 Or pTeam(v)=0 Then sensible=1
 ;avoid suicide!
 If pTeam(cyc)=pTeam(v) And pChar(v)<>charEnemy(pChar(cyc)) Then sensible=0
 If cyc=v Then sensible=0
 Return sensible
End Function

;MAKE CPU TURN INSTANTLY
Function InstantTurn(cyc)
 v=pFoc(cyc)
 If v>0 And pControl(v)>0 And pControl(cyc)=0 And optLevel=>4 ;And PointViable(v)=0
  PointEntity p(cyc),pLimb(v,30)
  pA#(cyc)=EntityYaw(p(cyc))
 EndIf
End Function