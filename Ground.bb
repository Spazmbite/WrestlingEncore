;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING ENCORE: GROUND MOVES -----------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------------
;////////////////////// HEAD MOVE SEQUENCES //////////////////////////
;---------------------------------------------------------------------
Function LoadHeadSequences(cyc)
 ;head grappling
 pSeq(cyc,300)=ExtractAnimSeq(p(cyc),1155,1220,pSeq(cyc,602)) ;ground lunge
 pSeq(cyc,301)=ExtractAnimSeq(p(cyc),50,85,pSeq(cyc,612)) ;grasp head (on back)
 pSeq(cyc,302)=ExtractAnimSeq(p(cyc),50,85,pSeq(cyc,613)) ;head grasped (on back)
 pSeq(cyc,303)=ExtractAnimSeq(p(cyc),175,250,pSeq(cyc,612)) ;grasp head (from front)
 pSeq(cyc,304)=ExtractAnimSeq(p(cyc),175,250,pSeq(cyc,613)) ;head grasped (from front) 
 pSeq(cyc,305)=ExtractAnimSeq(p(cyc),95,125,pSeq(cyc,612)) ;holding head
 pSeq(cyc,306)=ExtractAnimSeq(p(cyc),95,125,pSeq(cyc,613)) ;head being held
 pSeq(cyc,307)=ExtractAnimSeq(p(cyc),260,320,pSeq(cyc,612)) ;dragging by head
 pSeq(cyc,308)=ExtractAnimSeq(p(cyc),260,320,pSeq(cyc,613)) ;dragged by head
 pSeq(cyc,309)=ExtractAnimSeq(p(cyc),135,165,pSeq(cyc,612)) ;release head
 pSeq(cyc,310)=ExtractAnimSeq(p(cyc),135,165,pSeq(cyc,613)) ;head released
 pSeq(cyc,311)=ExtractAnimSeq(p(cyc),330,405,pSeq(cyc,612)) ;drag up by head
 pSeq(cyc,312)=ExtractAnimSeq(p(cyc),330,405,pSeq(cyc,613)) ;dragged up by head
 ;sleeper hold
 If GroundRequired(cyc,211,1) Then pSeq(cyc,313)=ExtractAnimSeq(p(cyc),415,445,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,211,-1) Then pSeq(cyc,314)=ExtractAnimSeq(p(cyc),415,445,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,211,1) Then pSeq(cyc,315)=ExtractAnimSeq(p(cyc),455,485,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,211,-1) Then pSeq(cyc,316)=ExtractAnimSeq(p(cyc),455,485,pSeq(cyc,613)) ;wrench receive
 ;mounted punches
 If GroundRequired(cyc,212,1) Then pSeq(cyc,317)=ExtractAnimSeq(p(cyc),1095,1340,pSeq(cyc,612)) ;execute
 If GroundRequired(cyc,212,-1) Then pSeq(cyc,318)=ExtractAnimSeq(p(cyc),1095,1340,pSeq(cyc,613)) ;receive
 ;camel clutch
 If GroundRequired(cyc,213,1) Then pSeq(cyc,319)=ExtractAnimSeq(p(cyc),1620,1755,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,213,-1) Then pSeq(cyc,320)=ExtractAnimSeq(p(cyc),1620,1755,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,213,1) Then pSeq(cyc,321)=ExtractAnimSeq(p(cyc),1755,1815,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,213,-1) Then pSeq(cyc,322)=ExtractAnimSeq(p(cyc),1755,1815,pSeq(cyc,613)) ;wrench receive
 ;choke hold
 If GroundRequired(cyc,214,1) Then pSeq(cyc,323)=ExtractAnimSeq(p(cyc),2115,2165,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,214,-1) Then pSeq(cyc,324)=ExtractAnimSeq(p(cyc),2115,2165,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,214,1) Then pSeq(cyc,325)=ExtractAnimSeq(p(cyc),2165,2195,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,214,-1) Then pSeq(cyc,326)=ExtractAnimSeq(p(cyc),2165,2195,pSeq(cyc,613)) ;wrench receive
 ;head smashes
 If GroundRequired(cyc,215,1) Then pSeq(cyc,327)=ExtractAnimSeq(p(cyc),2205,2430,pSeq(cyc,612)) ;execute
 If GroundRequired(cyc,215,-1) Then pSeq(cyc,328)=ExtractAnimSeq(p(cyc),2205,2430,pSeq(cyc,613)) ;receive
 ;crossface
 If GroundRequired(cyc,216,1) Then pSeq(cyc,329)=ExtractAnimSeq(p(cyc),2885,2985,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,216,-1) Then pSeq(cyc,330)=ExtractAnimSeq(p(cyc),2885,2985,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,216,1) Then pSeq(cyc,331)=ExtractAnimSeq(p(cyc),2985,3045,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,216,-1) Then pSeq(cyc,332)=ExtractAnimSeq(p(cyc),2985,3045,pSeq(cyc,613)) ;wrench receive
 ;dragon sleeper
 If GroundRequired(cyc,217,1) Then pSeq(cyc,333)=ExtractAnimSeq(p(cyc),3055,3095,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,217,-1) Then pSeq(cyc,334)=ExtractAnimSeq(p(cyc),3055,3095,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,217,1) Then pSeq(cyc,335)=ExtractAnimSeq(p(cyc),3095,3135,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,217,-1) Then pSeq(cyc,336)=ExtractAnimSeq(p(cyc),3095,3135,pSeq(cyc,613)) ;wrench receive
 ;rolling head snap
 If GroundRequired(cyc,218,1) Then pSeq(cyc,337)=ExtractAnimSeq(p(cyc),3275,3450,pSeq(cyc,612)) ;execute
 If GroundRequired(cyc,218,-1) Then pSeq(cyc,338)=ExtractAnimSeq(p(cyc),3275,3450,pSeq(cyc,613)) ;receive
 ;arm bar
 If GroundRequired(cyc,219,1) Then pSeq(cyc,339)=ExtractAnimSeq(p(cyc),3585,3680,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,219,-1) Then pSeq(cyc,340)=ExtractAnimSeq(p(cyc),3585,3680,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,219,1) Then pSeq(cyc,341)=ExtractAnimSeq(p(cyc),3680,3720,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,219,-1) Then pSeq(cyc,342)=ExtractAnimSeq(p(cyc),3680,3720,pSeq(cyc,613)) ;wrench receive
 ;bow and arrow
 If GroundRequired(cyc,220,1) Then pSeq(cyc,343)=ExtractAnimSeq(p(cyc),3875,3915,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,220,-1) Then pSeq(cyc,344)=ExtractAnimSeq(p(cyc),3875,3915,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,220,1) Then pSeq(cyc,345)=ExtractAnimSeq(p(cyc),3915,3955,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,220,-1) Then pSeq(cyc,346)=ExtractAnimSeq(p(cyc),3915,3955,pSeq(cyc,613)) ;wrench receive
End Function

;FIND GROUND MOVE REQUIREMENT
Function GroundRequired(cyc,move,task) ;-1=receive, 1=execute
 needed=0
 For v=1 To no_plays
  For count=1 To 3
   If headAnim(charGroundMove(pChar(v),count))=move Or legAnim(charGroundMove(pChar(v),count+3))=move 
    If task=1 And cyc=v Then needed=1 ;executor
    If task=-1 Then needed=1 ;receiver
    needed=1
   EndIf
  Next
 Next
 Return needed
End Function

;---------------------------------------------------------------------
;///////////////////// HEAD MOVE ANIMATIONS //////////////////////////
;---------------------------------------------------------------------
Function HeadMoves(cyc)
 ;GROUND LUNGE
 If pAnim(cyc)=200
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.5,pSeq(cyc,300),5 : pSting(cyc)=1
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<20
   Advance(cyc,pA#(cyc),0.2)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=20
   For v=1 To no_plays
    If cyc<>v And GrabViable(v)=2 And InProximity(cyc,v,35) And pY#(cyc)=pY#(v) And pPlatform(cyc)=0 And pPlatform(v)=0 And pGrappling(cyc)=0 And pGrappler(v)=0 And pPinning(v)=0 And pPinner(v)=0 And pSting(cyc)=1
     If InRange(cyc,v,7)
      ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.3)
      ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
      If ClosestLimb(cyc,v)=1 Then anim=201 Else anim=301
      ChangeAnim(cyc,anim) : ChangeAnim(v,202)
      pGrappling(cyc)=v : pGrappler(v)=cyc
      pFoc(cyc)=v : pFoc(v)=cyc
      pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)>26 Then ChangeAnim(cyc,0)
 EndIf 
 ;CRUISE TO HEAD
 If pAnim(cyc)=201
  v=pGrappling(cyc) : pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,2.0,pSeq(cyc,24),5
  CruiseToLimb(cyc,v,-20)
  If pAnimTim(cyc)>50 Or (pX#(cyc)>pTX#(cyc)-2 And pX#(cyc)<pTX#(cyc)+2 And pZ#(cyc)>pTZ#(cyc)-2 And pZ#(cyc)<pTZ#(cyc)+2)
   state=0
   If pOldAnim(v)=63 Or pOldAnim(v)=82 Or pOldAnim(v)=83 Or pOldAnim(v)=85 Then state=1
   If state=0 Then ChangeAnim(cyc,203) : SharpTransition(cyc,301,0) ;back 
   If state=1 Then ChangeAnim(cyc,204) : SharpTransition(cyc,303,0) ;front
   pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
   pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
   pExcusedPlays(cyc)=1 : pExcusedPlays(v)=1
  EndIf
 EndIf
 ;GROUND VICTIM
 If pAnim(cyc)=202
  If pGrappler(cyc)=0 Or pAnim(pGrappler(cyc))<100 Then ChangeAnim(cyc,80)
 EndIf
 ;PICK-UP HEAD (normally)
 If pAnim(cyc)=203
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,301),0
   Animate p(v),3,2.0,pSeq(v,302),0
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>22 Then ChangeAnim(cyc,205)
  GroundPositioning(cyc,v)
 EndIf
 ;PICK-UP HEAD (from front)
 If pAnim(cyc)=204
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,303),0
   Animate p(v),3,2.0,pSeq(v,304),0
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=17 Then ProduceSound(p(cyc),sFall,22050,0.4)
  If pAnimTim(cyc)=30 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>37 Then ChangeAnim(cyc,205)
  GroundPositioning(cyc,v)
 EndIf
 ;HOLD HEAD
 If pAnim(cyc)=205
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),1,0.5,pSeq(cyc,305),5
   Animate p(v),1,0.5,pSeq(v,306),5
  EndIf
  GroundPositioning(cyc,v)
  If DirPressed(cyc) Then ChangeAnim(cyc,206)
  FindGroundMoves(cyc,0)
 EndIf
 ;DRAG BY HEAD
 If pAnim(cyc)=206
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),1,1.5,pSeq(cyc,307),5
   Animate p(v),1,1.5,pSeq(v,308),5
  EndIf
  ;movement
  If cLeft(cyc)=1 Then pX#(v)=pX#(v)-0.2 : pTA#(cyc)=270
  If cRight(cyc)=1 Then pX#(v)=pX#(v)+0.2 : pTA#(cyc)=90 
  If cUp(cyc)=1 Then pZ#(v)=pZ#(v)+0.2 : pTA#(cyc)=180
  If cDown(cyc)=1 Then pZ#(v)=pZ#(v)-0.2 : pTA#(cyc)=0
  If cLeft(cyc)=1 And cUp(cyc)=1 Then pTA#(cyc)=225
  If cRight(cyc)=1 And cUp(cyc)=1 Then pTA#(cyc)=135 
  If cLeft(cyc)=1 And cDown(cyc)=1 Then pTA#(cyc)=315
  If cRight(cyc)=1 And cDown(cyc)=1 Then pTA#(cyc)=45
  If DirPressed(cyc)=1
   pA#(cyc)=pA#(cyc)+ReachAngle(pA#(cyc),pTA#(cyc),1)
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),2) Then pA#(cyc)=pTA#(cyc)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  ;bind to victim
  EnforceBlocks(v)
  GroundPositioning(cyc,v)
  ;monitor commands
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,205)
  FindGroundMoves(cyc,0) 
 EndIf
 ;RELEASE HEAD
 If pAnim(cyc)=207
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   Animate p(cyc),3,1.5,pSeq(cyc,309),0
   Animate p(v),3,1.5,pSeq(v,310),0
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=5 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sFall,22050,0.4)
  If pAnimTim(cyc)>20 
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   ChangeAnim(v,80) : EndMove(cyc,v)
  EndIf
 EndIf
 ;///////////////////////////// HEAD HOLDS //////////////////////////////////
 ;DRAG UP BY HEAD
 If pAnim(cyc)=210
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,311),5
   Animate p(v),3,2.0,pSeq(v,312),5
  EndIf 
  If pAnimTim(cyc)<20 Then DropWeapon(cyc,40)
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=25 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Then pStepTim(v)=99
  If pAnimTim(cyc)=25 Then BreakMove(cyc,v,charSkl(pChar(cyc))/4)
  If pAnimTim(cyc)>42 
   ChangeAnim(cyc,101) : SharpTransition(cyc,102,0)
   ChangeAnim(v,103) : SharpTransition(v,102,180)
   If pHP(v)<1 Then pHP(v)=1
  EndIf
 EndIf
 ;SLEEPER HOLD
 If pAnim(cyc)=211
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.5,pSeq(cyc,313),2
   Animate p(v),3,1.5,pSeq(v,314),2
  EndIf 
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.4)
  ;wrench
  start=23
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,315),5
   Animate p(v),1,speeder#,pSeq(v,316),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,30,0.05,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-1.5,pSeq(cyc,313),5
   Animate p(v),3,-1.5,pSeq(v,314),5
  EndIf
  If pAnimTim(cyc)=1005 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1010 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1020 Then ChangeAnim(cyc,207)
 EndIf
 ;MOUNTED PUNCHES
 If pAnim(cyc)=212
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.5,pSeq(cyc,317),2
   Animate p(v),3,1.5,pSeq(v,318),2
  EndIf 
  If pAnimTim(cyc)<20 Then DropWeapon(cyc,40)
  If pAnimTim(cyc)=7 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=105 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=130 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=41 Or pAnimTim(cyc)=65 Or pAnimTim(cyc)=89 Or pAnimTim(cyc)=116 
   ProduceSound(p(cyc),sSwing,22050,0.5)
  EndIf
  If pAnimTim(cyc)=46 Or pAnimTim(cyc)=70 Or pAnimTim(cyc)=94 Or pAnimTim(cyc)=123
   Pop(cyc,Rnd(4,5),0)
   If pAnimTim(cyc)=123 Then Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sImpact(Rnd(1,2)),22050,0)
   ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)-3,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-1 : ScarLimb(v,1,10)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v) : DropWeapon(cyc,5)
  EndIf
  If pAnimTim(cyc)>163
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,90)
   ChangeAnim(v,81) : SharpTransition(v,80,0.1)
   EndMove(cyc,v) : pDT(v)=0
  EndIf
 EndIf
 ;CAMEL CLUTCH
 If pAnim(cyc)=213
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,319),2
   Animate p(v),3,2.0,pSeq(v,320),2
  EndIf 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=40 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=60 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=70
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(1.1,1.5) Else speeder#=Rnd(0.7,1.1)
   Animate p(cyc),1,speeder#,pSeq(cyc,321),2
   Animate p(v),1,speeder#,pSeq(v,322),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,15,0.025,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,319),5
   Animate p(v),3,-2.5,pSeq(v,320),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1030 Or pAnimTim(cyc)=1040 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1016 Or pAnimTim(cyc)=1022 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1054 Then ChangeAnim(cyc,207)
 EndIf
 ;CHOKE HOLD
 If pAnim(cyc)=214
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.5,pSeq(cyc,323),2
   Animate p(v),3,1.5,pSeq(v,324),2
  EndIf 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=24 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=18 Then pStepTim(cyc)=99
  ;wrench
  start=33
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sChoke,GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,325),2
   Animate p(v),1,speeder#,pSeq(v,326),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,20,0.025,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.0,pSeq(cyc,323),5
   Animate p(v),3,-2.0,pSeq(v,324),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1025 Then ChangeAnim(cyc,207)
 EndIf
 ;HEAD SMASHES
 If pAnim(cyc)=215
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.8,pSeq(cyc,327),2
   Animate p(v),3,1.8,pSeq(v,328),2
  EndIf 
  If pAnimTim(cyc)<27 Then DropWeapon(cyc,40)
  If pAnimTim(cyc)=11 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=17 Or pAnimTim(cyc)=113 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=44 Or pAnimTim(cyc)=66 Or pAnimTim(cyc)=88 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=50 Or pAnimTim(cyc)=72 Or pAnimTim(cyc)=94
   If pAnimTim(cyc)=50 Then popper=Rnd(2,7) Else popper=Rnd(4,5)
   Pop(cyc,popper,0)
   ProduceSound(p(v),sImpact(3),22050,0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1)
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)-3,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-1 : ScarLimb(v,1,10)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v) : DropWeapon(cyc,5)
  EndIf
  If pAnimTim(cyc)>125
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,90)
   ChangeAnim(v,81) : SharpTransition(v,80,0.1)
   EndMove(cyc,v) : pDT(v)=0
  EndIf
 EndIf
 ;CROSSFACE CLUTCH
 If pAnim(cyc)=216
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,329),2
   Animate p(v),3,2.0,pSeq(v,330),2
  EndIf 
  If pAnimTim(cyc)=17 Or pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=40 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=28 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=50
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(1.1,1.5) Else speeder#=Rnd(0.7,1.1)
   Animate p(cyc),1,speeder#,pSeq(cyc,331),2
   Animate p(v),1,speeder#,pSeq(v,332),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,25,10,0.025,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,329),5
   Animate p(v),3,-2.5,pSeq(v,330),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1025 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1015 Or pAnimTim(cyc)=1035 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1040 Then ChangeAnim(cyc,207)
 EndIf
 ;DRAGON SLEEPER
 If pAnim(cyc)=217
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.5,pSeq(cyc,333),2
   Animate p(v),3,1.5,pSeq(v,334),2
  EndIf 
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.3)
  ;wrench
  start=25
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,335),5
   Animate p(v),1,speeder#,pSeq(v,336),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,20,0.05,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.0,pSeq(cyc,333),5
   Animate p(v),3,-2.0,pSeq(v,334),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1015 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1010 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1020 Then ChangeAnim(cyc,207)
 EndIf
 ;ROLLING HEAD SNAP
 If pAnim(cyc)=218
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,337),2
   Animate p(v),3,2.0,pSeq(v,338),2
  EndIf 
  If pAnimTim(cyc)=57 Or pAnimTim(cyc)=70 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=80 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=7 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=17 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=27
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : DropWeapon(cyc,5)
  EndIf
  If pAnimTim(cyc)=47 Then ProduceSound(p(v),sFall,22050,0) : FindSmashes(v,0)
  If pAnimTim(cyc)>87
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,81) : SharpTransition(v,80,0.1)
   EndMove(cyc,v) : pDT(v)=0
  EndIf
 EndIf
 ;ARM BAR
 If pAnim(cyc)=219
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,339),2
   Animate p(v),3,2.0,pSeq(v,340),2
  EndIf 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=25 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=47
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,341),3
   Animate p(v),1,speeder#,pSeq(v,342),3
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,20,0.025,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,339),5
   Animate p(v),3,-2.5,pSeq(v,340),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Or pAnimTim(cyc)=1030 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Or pAnimTim(cyc)=1036 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1038 Then ChangeAnim(cyc,207)
 EndIf
 ;BOW AND ARROW
 If pAnim(cyc)=220
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,343),2
   Animate p(v),3,2.0,pSeq(v,344),2
  EndIf 
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Then pStepTim(cyc)=99
  ;wrench
  start=20
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.9,1.3) Else speeder#=Rnd(0.5,0.9)
   Animate p(cyc),1,speeder#,pSeq(cyc,345),5
   Animate p(v),1,speeder#,pSeq(v,346),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,20,0.025,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,343),5
   Animate p(v),3,-2.5,pSeq(v,344),5
  EndIf
  If pAnimTim(cyc)=1006 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1016 Then ChangeAnim(cyc,207)
 EndIf
End Function

;---------------------------------------------------------------------
;/////////////////////// LEG MOVE SEQUENCES //////////////////////////
;---------------------------------------------------------------------
Function LoadLegSequences(cyc)
 ;leg grappling
 pSeq(cyc,401)=ExtractAnimSeq(p(cyc),495,540,pSeq(cyc,612)) ;grasp legs (on back)
 pSeq(cyc,402)=ExtractAnimSeq(p(cyc),495,540,pSeq(cyc,613)) ;head legs (on back)
 pSeq(cyc,403)=ExtractAnimSeq(p(cyc),700,790,pSeq(cyc,612)) ;grasp legs (from front)
 pSeq(cyc,404)=ExtractAnimSeq(p(cyc),700,790,pSeq(cyc,613)) ;legs grasped (from front) 
 pSeq(cyc,405)=ExtractAnimSeq(p(cyc),550,580,pSeq(cyc,612)) ;holding legs
 pSeq(cyc,406)=ExtractAnimSeq(p(cyc),550,580,pSeq(cyc,613)) ;legs being held
 pSeq(cyc,407)=ExtractAnimSeq(p(cyc),590,650,pSeq(cyc,612)) ;dragging by legs
 pSeq(cyc,408)=ExtractAnimSeq(p(cyc),590,650,pSeq(cyc,613)) ;dragged by legs
 pSeq(cyc,409)=ExtractAnimSeq(p(cyc),660,690,pSeq(cyc,612)) ;release legs
 pSeq(cyc,410)=ExtractAnimSeq(p(cyc),660,690,pSeq(cyc,613)) ;legs released
 pSeq(cyc,411)=ExtractAnimSeq(p(cyc),800,880,pSeq(cyc,612)) ;drag up by legs
 pSeq(cyc,412)=ExtractAnimSeq(p(cyc),800,880,pSeq(cyc,613)) ;dragged up by legs
 ;sharpshooter
 If GroundRequired(cyc,311,1) Then pSeq(cyc,413)=ExtractAnimSeq(p(cyc),890,1045,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,311,-1) Then pSeq(cyc,414)=ExtractAnimSeq(p(cyc),890,1045,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,311,1) Then pSeq(cyc,415)=ExtractAnimSeq(p(cyc),1055,1085,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,311,-1) Then pSeq(cyc,416)=ExtractAnimSeq(p(cyc),1055,1085,pSeq(cyc,613)) ;wrench receive
 ;figure 4 leglock
 If GroundRequired(cyc,312,1) Then pSeq(cyc,417)=ExtractAnimSeq(p(cyc),1350,1470,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,312,-1) Then pSeq(cyc,418)=ExtractAnimSeq(p(cyc),1350,1470,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,312,1) Then pSeq(cyc,419)=ExtractAnimSeq(p(cyc),1480,1520,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,312,-1) Then pSeq(cyc,420)=ExtractAnimSeq(p(cyc),1480,1520,pSeq(cyc,613)) ;wrench receive
 ;spinning toe hold
 If GroundRequired(cyc,313,1) Then pSeq(cyc,421)=ExtractAnimSeq(p(cyc),1530,1570,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,313,-1) Then pSeq(cyc,422)=ExtractAnimSeq(p(cyc),1530,1570,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,313,1) Then pSeq(cyc,423)=ExtractAnimSeq(p(cyc),1570,1610,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,313,-1) Then pSeq(cyc,424)=ExtractAnimSeq(p(cyc),1570,1610,pSeq(cyc,613)) ;wrench receive
 ;groin stomp
 If GroundRequired(cyc,314,1) Then pSeq(cyc,425)=ExtractAnimSeq(p(cyc),1825,2105,pSeq(cyc,612)) ;execute
 If GroundRequired(cyc,314,-1) Then pSeq(cyc,426)=ExtractAnimSeq(p(cyc),1825,2105,pSeq(cyc,613)) ;receive
 ;boston crab
 If GroundRequired(cyc,315,1) Then pSeq(cyc,427)=ExtractAnimSeq(p(cyc),2440,2520,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,315,-1) Then pSeq(cyc,428)=ExtractAnimSeq(p(cyc),2440,2520,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,315,1) Then pSeq(cyc,429)=ExtractAnimSeq(p(cyc),2520,2560,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,315,-1) Then pSeq(cyc,430)=ExtractAnimSeq(p(cyc),2520,2560,pSeq(cyc,613)) ;wrench receive
 ;half crab
 If GroundRequired(cyc,316,1) Then pSeq(cyc,431)=ExtractAnimSeq(p(cyc),2570,2650,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,316,-1) Then pSeq(cyc,432)=ExtractAnimSeq(p(cyc),2570,2650,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,316,1) Then pSeq(cyc,433)=ExtractAnimSeq(p(cyc),2650,2690,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,316,-1) Then pSeq(cyc,434)=ExtractAnimSeq(p(cyc),2650,2690,pSeq(cyc,613)) ;wrench receive
 ;slingshot
 If GroundRequired(cyc,317,1) Then pSeq(cyc,435)=ExtractAnimSeq(p(cyc),2700,2875,pSeq(cyc,612)) ;execute
 If GroundRequired(cyc,317,-1) Then pSeq(cyc,436)=ExtractAnimSeq(p(cyc),2700,2875,pSeq(cyc,613)) ;receive
 ;elevated crab
 If GroundRequired(cyc,318,1) Then pSeq(cyc,437)=ExtractAnimSeq(p(cyc),3145,3225,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,318,-1) Then pSeq(cyc,438)=ExtractAnimSeq(p(cyc),3145,3225,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,318,1) Then pSeq(cyc,439)=ExtractAnimSeq(p(cyc),3225,3265,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,318,-1) Then pSeq(cyc,440)=ExtractAnimSeq(p(cyc),3225,3265,pSeq(cyc,613)) ;wrench receive
 ;ankle lock
 If GroundRequired(cyc,319,1) Then pSeq(cyc,441)=ExtractAnimSeq(p(cyc),3460,3530,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,319,-1) Then pSeq(cyc,442)=ExtractAnimSeq(p(cyc),3460,3530,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,319,1) Then pSeq(cyc,443)=ExtractAnimSeq(p(cyc),3530,3570,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,319,-1) Then pSeq(cyc,444)=ExtractAnimSeq(p(cyc),3530,3570,pSeq(cyc,613)) ;wrench receive
 ;leg wrench
 If GroundRequired(cyc,320,1) Then pSeq(cyc,445)=ExtractAnimSeq(p(cyc),3730,3825,pSeq(cyc,612)) ;apply execute
 If GroundRequired(cyc,320,-1) Then pSeq(cyc,446)=ExtractAnimSeq(p(cyc),3730,3825,pSeq(cyc,613)) ;apply receive
 If GroundRequired(cyc,320,1) Then pSeq(cyc,447)=ExtractAnimSeq(p(cyc),3825,3865,pSeq(cyc,612)) ;wrench execute
 If GroundRequired(cyc,320,-1) Then pSeq(cyc,448)=ExtractAnimSeq(p(cyc),3825,3865,pSeq(cyc,613)) ;wrench receive
End Function

;---------------------------------------------------------------------
;////////////////////// LEG MOVE ANIMATIONS //////////////////////////
;---------------------------------------------------------------------
Function LegMoves(cyc)
 ;CRUISE TO LEGS
 If pAnim(cyc)=301
  v=pGrappling(cyc) : pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,2.0,pSeq(cyc,24),5
  CruiseToLimb(cyc,v,20)
  If pAnimTim(cyc)>50 Or (pX#(cyc)>pTX#(cyc)-2 And pX#(cyc)<pTX#(cyc)+2 And pZ#(cyc)>pTZ#(cyc)-2 And pZ#(cyc)<pTZ#(cyc)+2)
   state=0
   If pOldAnim(v)=63 Or pOldAnim(v)=82 Or pOldAnim(v)=83 Or pOldAnim(v)=85 Then state=1
   If state=0 Then ChangeAnim(cyc,303) : SharpTransition(cyc,401,0) ;back 
   If state=1 Then ChangeAnim(cyc,304) : SharpTransition(cyc,403,0) ;front
   pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
   pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
   pExcusedPlays(cyc)=1 : pExcusedPlays(v)=1
  EndIf
 EndIf
 ;PICK-UP LEGS (normally)
 If pAnim(cyc)=303
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,401),0
   Animate p(v),3,2.0,pSeq(v,402),0
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>22 Then ChangeAnim(cyc,305)
  GroundPositioning(cyc,v)
 EndIf
 ;PICK-UP LEGS (from front)
 If pAnim(cyc)=304
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,403),0
   Animate p(v),3,2.0,pSeq(v,404),0
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sFall,22050,0.4)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=30 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>45 Then ChangeAnim(cyc,305)
  GroundPositioning(cyc,v)
 EndIf
 ;HOLD LEGS
 If pAnim(cyc)=305
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),1,0.5,pSeq(cyc,405),5
   Animate p(v),1,0.5,pSeq(v,406),5
  EndIf
  GroundPositioning(cyc,v)
  If DirPressed(cyc) Then ChangeAnim(cyc,306)
  FindGroundMoves(cyc,1)
 EndIf
 ;DRAG BY LEGS
 If pAnim(cyc)=306
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),1,1.5,pSeq(cyc,407),5
   Animate p(v),1,1.5,pSeq(v,408),5
  EndIf
  ;movement
  If cLeft(cyc)=1 Then pX#(v)=pX#(v)-0.25 : pTA#(cyc)=90
  If cRight(cyc)=1 Then pX#(v)=pX#(v)+0.25 : pTA#(cyc)=270 
  If cUp(cyc)=1 Then pZ#(v)=pZ#(v)+0.25 : pTA#(cyc)=0
  If cDown(cyc)=1 Then pZ#(v)=pZ#(v)-0.25 : pTA#(cyc)=180
  If cLeft(cyc)=1 And cUp(cyc)=1 Then pTA#(cyc)=45
  If cRight(cyc)=1 And cUp(cyc)=1 Then pTA#(cyc)=315 
  If cLeft(cyc)=1 And cDown(cyc)=1 Then pTA#(cyc)=135
  If cRight(cyc)=1 And cDown(cyc)=1 Then pTA#(cyc)=225
  If DirPressed(cyc)=1
   pA#(cyc)=pA#(cyc)+ReachAngle(pA#(cyc),pTA#(cyc),1.5)
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),3) Then pA#(cyc)=pTA#(cyc)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  ;bind to victim
  EnforceBlocks(v)
  GroundPositioning(cyc,v)
  ;monitor commands
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,305)
  FindGroundMoves(cyc,1)
 EndIf
 ;RELEASE LEGS
 If pAnim(cyc)=307
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   Animate p(cyc),3,1.5,pSeq(cyc,409),0
   Animate p(v),3,1.5,pSeq(v,410),0
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,Rnd(0.2,0.5))
  If pAnimTim(cyc)=15 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sFall,22050,0.4)
  If pAnimTim(cyc)>20 
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   ChangeAnim(v,80) : EndMove(cyc,v)
  EndIf
 EndIf
 ;///////////////////////////// LEG HOLDS //////////////////////////////////
 ;DRAG UP TO FEET
 If pAnim(cyc)=310
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.5,pSeq(cyc,411),5
   Animate p(v),3,1.5,pSeq(v,412),5
  EndIf 
  If pAnimTim(cyc)<20 Then DropWeapon(cyc,40)
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sSwing,20000,0.2)
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=36 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=13 Or pAnimTim(cyc)=46 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=26 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=35 Then BreakMove(cyc,v,charSkl(pChar(cyc))/4)
  If pAnimTim(cyc)>56 
   ChangeAnim(cyc,101) : SharpTransition(cyc,102,180)
   ChangeAnim(v,103)
   If pHP(v)<1 Then pHP(v)=1
  EndIf
 EndIf
 ;SCORPION LOCK
 If pAnim(cyc)=311
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,413),2
   Animate p(v),3,2.0,pSeq(v,414),2
  EndIf 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=40 Or pAnimTim(cyc)=65 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=35 Or pAnimTim(cyc)=70 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=50 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=65 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=80
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,415),2
   Animate p(v),1,speeder#,pSeq(v,416),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,25,10,0.05,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,413),5
   Animate p(v),3,-2.5,pSeq(v,414),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1040 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1024 Or pAnimTim(cyc)=1052 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1065 Then ChangeAnim(cyc,307)
 EndIf
 ;FIGURE 4 LEGLOCK
 If pAnim(cyc)=312
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.8,pSeq(cyc,417),2
   Animate p(v),3,1.8,pSeq(v,418),2
  EndIf 
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=38 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=16 Or pAnimTim(cyc)=27 Or pAnimTim(cyc)=43 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=38 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=57 Then ProduceSound(p(cyc),sFall,22050,0)
  ;wrench
  start=68
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,419),5
   Animate p(v),1,speeder#,pSeq(v,420),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,25,10,0.025,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,417),5
   Animate p(v),3,-2.5,pSeq(v,418),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1025 Or pAnimTim(cyc)=1035 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1016 Or pAnimTim(cyc)=1028 Or pAnimTim(cyc)=1040 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1048 Then ChangeAnim(cyc,307)
 EndIf
 ;SPINNING TOE HOLD
 If pAnim(cyc)=313
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,421),2
   Animate p(v),3,2.0,pSeq(v,422),2
  EndIf 
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Then pStepTim(cyc)=99
  ;wrench
  start=21
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(1.1,1.5) Else speeder#=Rnd(0.7,1.1)
   Animate p(cyc),2,speeder#,pSeq(cyc,423),2
   Animate p(v),2,speeder#,pSeq(v,424),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,25,0.025,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,421),5
   Animate p(v),3,-2.5,pSeq(v,422),5
  EndIf
  If pAnimTim(cyc)=1006 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1016 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1020 Then ChangeAnim(cyc,307)
 EndIf
 ;GROIN KICK
 If pAnim(cyc)=314
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.2,pSeq(cyc,425),2
   Animate p(v),3,2.2,pSeq(v,426),2
  EndIf 
  If pAnimTim(cyc)<20 Then DropWeapon(cyc,40)
  If pAnimTim(cyc)=13 Or pAnimTim(cyc)=43 Or pAnimTim(cyc)=61 Or pAnimTim(cyc)=90 Or pAnimTim(cyc)=104 
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  EndIf
  If pAnimTim(cyc)=104 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=36 Then Pop(cyc,Rnd(2,8),0)
  If pAnimTim(cyc)=59 Then Pop(0,7,0) 
  If pAnimTim(cyc)=71 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=79
   Pop(cyc,Rnd(2,7),0) : Pop(0,8,0)
   ProduceSound(p(v),sImpact(6),22050,1)
   ProduceSound(p(v),sBleed,22050,0)
   ProduceSound(p(v),sPain(3),GetVoice(v),1)
   CreateSpurt(EntityX(pLimb(v,30),1),EntityY(pLimb(v,30),1),EntityZ(pLimb(v,30),1),15,99)
   pHealth(v)=pHealth(v)-5 : ScarLimb(v,30,5)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
  EndIf
  If pAnimTim(cyc)=79 Or pAnimTim(cyc)=102 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)>127
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,81) : SharpTransition(v,80,0.1)
   EndMove(cyc,v) : pDT(v)=0
  EndIf
 EndIf
 ;BOSTON CRAB
 If pAnim(cyc)=315
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,427),2
   Animate p(v),3,2.0,pSeq(v,428),2
  EndIf 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=65 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=40 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=40
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,429),5
   Animate p(v),1,speeder#,pSeq(v,430),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,15,0.05,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,427),5
   Animate p(v),3,-2.5,pSeq(v,428),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1017 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1011 Or pAnimTim(cyc)=1025 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1032 Then ChangeAnim(cyc,307)
 EndIf
 ;HALF CRAB
 If pAnim(cyc)=316
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,431),2
   Animate p(v),3,2.0,pSeq(v,432),2
  EndIf 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=65 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=40 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=40
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,433),5
   Animate p(v),1,speeder#,pSeq(v,434),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,20,0.05,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,431),5
   Animate p(v),3,-2.5,pSeq(v,432),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1017 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1011 Or pAnimTim(cyc)=1025 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1032 Then ChangeAnim(cyc,307)
 EndIf
 ;SLINGSHOT
 If pAnim(cyc)=317
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,435),2
   Animate p(v),3,2.0,pSeq(v,436),2
  EndIf 
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=30 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=32 Or pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sSwing,20000,0)
  ;If pAnimTim(cyc)=>10 And pAnimTim(cyc)=<45 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=30 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=47 Then ProduceSound(p(cyc),sFall,22050,0) : FindSmashes(cyc,0)
  If v>0 And pAnimTim(cyc)=62 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=72
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=100
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>87
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,180)
   If v>0 Then ChangeAnim(v,83) : SharpTransition(v,83,180)
   EndMove(cyc,v)
  EndIf
  ;If v>0 Then MoveFallCheck(cyc,v,60,65,7)
 EndIf
 ;ELEVATED CRAB
 If pAnim(cyc)=318
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,437),2
   Animate p(v),3,2.0,pSeq(v,438),2
  EndIf 
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sFall,22050,0.3)
  ;wrench
  start=40
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),1)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,439),5
   Animate p(v),1,speeder#,pSeq(v,440),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,25,10,0.025,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,437),5
   Animate p(v),3,-2.5,pSeq(v,438),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1026 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1032 Then ChangeAnim(cyc,307)
 EndIf
 ;ANKLE LOCK
 If pAnim(cyc)=319
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,441),2
   Animate p(v),3,2.0,pSeq(v,442),2
  EndIf 
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=30 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sFall,22050,0.3)
  ;wrench
  start=35
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,443),5
   Animate p(v),1,speeder#,pSeq(v,444),5
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,15,0.05,1)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,441),5
   Animate p(v),3,-2.5,pSeq(v,442),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1028 Then ChangeAnim(cyc,307)
 EndIf
 ;LEG WRENCH
 If pAnim(cyc)=320
  ;apply
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,445),2
   Animate p(v),3,2.0,pSeq(v,446),2
  EndIf 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=37 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=28 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sFall,22050,0.5)
  ;wrench
  start=47
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(1.1,1.5) Else speeder#=Rnd(0.7,1.1)
   Animate p(cyc),2,speeder#,pSeq(cyc,447),2
   Animate p(v),2,speeder#,pSeq(v,448),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,20,0.025,0)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,445),5
   Animate p(v),3,-2.5,pSeq(v,446),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Or pAnimTim(cyc)=1030 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Or pAnimTim(cyc)=1036 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1038 Then ChangeAnim(cyc,307)
 EndIf
End Function 

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;FIND CLOSEST LIMB
Function ClosestLimb(cyc,v) ;1=head, 2=legs
 ;find distance to legs
 ResetDummy(v)
 MoveEntity dummy,0,0,20
 legX#=EntityX(dummy) : legZ#=EntityZ(dummy)
 distX#=GetDiff#(pX#(cyc),legX#)
 distZ#=GetDiff#(pZ#(cyc),legZ#)
 legDist#=distX#+distZ#
 ;find distance to head
 ResetDummy(v)
 MoveEntity dummy,0,0,-20
 headX#=EntityX(dummy) : headZ#=EntityZ(dummy)
 distX#=GetDiff#(pX#(cyc),headX#)
 distZ#=GetDiff#(pZ#(cyc),headZ#)
 headDist#=distX#+distZ#
 ;return closest
 If headDist#<legDist# Then value=1 Else value=2
 Return value
End Function

;CRUISE TO HEAD/LEGS
Function CruiseToLimb(cyc,v,range)
 ;prepare probe
 ResetDummy(v)
 MoveEntity dummy,0,0,range
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;search for block conflicts
 conflict=0
 For b=0 To no_blocks
  If checkX#>wBlockX1#(b)-5 And checkX#<wBlockX2#(b)+5 And checkZ#>wBlockZ1#(b)-5 And checkZ#<wBlockZ2#(b)+5 And pY#(v)=>wBlockY1#(b) And pY#(v)<wBlockY2#(b)
   conflict=1
  EndIf
 Next
 ;extra check for ropes
 If pY#(v)=wStage#
  If checkZ#>wBlockZ1#(1) Then conflict=1
  If checkZ#<wBlockZ2#(3) Then conflict=1
  If checkX#>wBlockX1#(2) Then conflict=1
  If checkX#<wBlockX2#(4) Then conflict=1
 EndIf
 ;search for item conflicts
 If conflict=0
  For b=1 To no_items
   If checkX#>iX#(b)-30 And checkX#<iX#(b)+30 And checkZ#>iZ#(b)-30 And checkZ#<iZ#(b)+30 And pY#(v)>iY#(b)-5 And pY#(v)<iY#(b)+10 And iState(b)=0
    If ItemCollide(v,b,checkX#,checkZ#,0)=1 Then conflict=1
   EndIf
  Next
 EndIf
 ;act on conflict
 If conflict=1
  ResetDummy(v)
  If range>0 Then MoveEntity dummy,0,0,-1
  If range<0 Then MoveEntity dummy,0,0,1
  pX#(v)=EntityX(dummy) : pZ#(v)=EntityZ(dummy)
 EndIf
 ;cruise to location
 ResetDummy(v)
 MoveEntity dummy,0,0,range
 pTX#(cyc)=EntityX(dummy) : pTZ#(cyc)=EntityZ(dummy)
 CruiseToLocation(cyc,pTX#(cyc),pTZ#(cyc),0.5)
 pStepTim(cyc)=pStepTim(cyc)+1
End Function

;CRUISE TO GIVEN CO-ORDINATES
Function CruiseToLocation(cyc,tX#,tZ#,speed#)
 If pX#(cyc)>tX# Then pX#(cyc)=pX#(cyc)-speed#
 If pX#(cyc)<tX# Then pX#(cyc)=pX#(cyc)+speed#
 If pZ#(cyc)>tZ# Then pZ#(cyc)=pZ#(cyc)-speed#
 If pZ#(cyc)<tZ# Then pZ#(cyc)=pZ#(cyc)+speed#
End Function

;SET GROUND POSITIONING
Function GroundPositioning(cyc,v)
 pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
 pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(v)=pA#(cyc)
 EnforceBlocks(cyc)
End Function

;FIND GROUND MOVES
Function FindGroundMoves(cyc,limb) ;0=head, 1=legs
 v=pGrappling(cyc) 
 ;trigger moves
 move=0 : randy=Rnd(0,50)
 If (pControl(cyc)>0 And cAttack(cyc)=1) Or (pControl(cyc)=0 And randy=1)
  If limb=0 Then move=headAnim(charGroundMove(pChar(cyc),1)) ;head move A
  If limb=1 Then move=legAnim(charGroundMove(pChar(cyc),4)) ;leg move A
 EndIf
 If (pControl(cyc)>0 And cRun(cyc)=1) Or (pControl(cyc)=0 And randy=2) 
  If limb=0 Then move=headAnim(charGroundMove(pChar(cyc),2)) ;head move B
  If limb=1 Then move=legAnim(charGroundMove(pChar(cyc),5)) ;leg move B
 EndIf
 If (pControl(cyc)>0 And cPick(cyc)=1) Or (pControl(cyc)=0 And randy=3)
  If limb=0 Then move=headAnim(charGroundMove(pChar(cyc),3)) ;head move C
  If limb=1 Then move=legAnim(charGroundMove(pChar(cyc),6)) ;leg move C
 EndIf
 ;drag up
 randy=Rnd(0,20)
 If matchState=3 And matchOTT=1 And matchCage=0 Then randy=Rnd(0,5)
 If matchState=3 And matchSubs=1 And matchPins=0 Then randy=99
 If randy=0 And pControl(cyc)=0 Then move=210
 If pControl(cyc)>0 And cGrab(cyc)=1 And DirPressed(cyc) Then move=210
 ;release
 If pControl(cyc)>0 And cGrab(cyc)=1 And DirPressed(cyc)=0 Then move=207
 ;lose grip
 If LostGrip(cyc,v,2) Then move=207
 ;translate into safe move for team-mates
 If pControl(cyc)=0 And move>210
  If pTeam(cyc)=pTeam(v) Then move=207
  If pRole(cyc)=1 Or pRole(v)=1 Then move=210
 EndIf
 ;execute
 If limb=1 And move>0 And move<300 Then move=move+100
 If move>0 Then ChangeAnim(cyc,move)
 ;break out
 If pSpecial(cyc)=0 And pDT(v)=<100
  randy=Rnd(0,100)
  If optLevel=>4 And pControl(cyc)>0 And pControl(v)=0 Then randy=Rnd(0,50)
  If randy=0 Or (randy=<10 And move>0) Or pSpecial(v)>0
   If pControl(v)=0 Or DirPressed(v) Or ActionPressed(v) Then BreakHold(cyc,v,-1)
  EndIf
 EndIf
End Function

;SUBMISSION HOLD EFFECTS
Function HoldEffects(cyc,v,start,wrench,power,crawl#,dir)
 ;movement
 If pAnimTim(cyc)>start+5 ;And crawl#>0
  If cLeft(v)=1 Then pX#(v)=pX#(v)-crawl# : pTA#(v)=270
  If cRight(v)=1 Then pX#(v)=pX#(v)+crawl# : pTA#(v)=90 
  If cUp(v)=1 Then pZ#(v)=pZ#(v)+crawl# : pTA#(v)=180
  If cDown(v)=1 Then pZ#(v)=pZ#(v)-crawl# : pTA#(v)=0
  If cLeft(v)=1 And cUp(v)=1 Then pTA#(v)=225
  If cRight(v)=1 And cUp(v)=1 Then pTA#(v)=135 
  If cLeft(v)=1 And cDown(v)=1 Then pTA#(v)=315
  If cRight(v)=1 And cDown(v)=1 Then pTA#(v)=45
  If dir=0 Or dir=10 Then pTA#(v)=CleanAngle#(pTA#(v)+180) ;travel correction (0=on back, 1=on front)
  If DirPressed(v)=1 And crawl#>0
   pA#(v)=pA#(v)+ReachAngle(pA#(v),pTA#(v),crawl#*2)
   If SatisfiedAngle(pA#(v),pTA#(v),crawl#*2) Then pA#(v)=pTA#(v)
   randy=Rnd(0,3)
   If randy=0 Then pStepTim(v)=pStepTim(v)+1
  EndIf
  EnforceBlocks(v)
  pX#(cyc)=pX#(v) : pY#(cyc)=pY#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
  EnforceBlocks(cyc)
 EndIf
 ;rope break?
 break=0
 If no_refs=0 And matchRules=>1 And LegalMan(cyc) And LegalMan(v) And pAnimTim(cyc)>start+20
  If NearRopes(cyc,optRopeRange/2)>0 Or NearRopes(v,optRopeRange)>0
   Pop(v,Rnd(2,7),0)
   ProduceSound(p(cyc),sBreak,22050,1)
   break=1
  EndIf
 EndIf
 ;pain
 sting=0
 If pSpecial(cyc)>0 Then wrench=wrench-(wrench/3) 
 randy=Rnd(0,wrench)
 If randy=0 ;small wrench
  ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0)
  If pHolding(cyc)>0 
   ProduceSound(p(cyc),weapSound(weapType(pHolding(cyc))),22050,0.3) 
   pHealth(v)=pHealth(v)-Rnd(0,1) : DropWeapon(cyc,5)
  EndIf
  pHealth(v)=pHealth(v)-1 : pHeat(cyc)=pHeat(cyc)+1
  If pSpecial(cyc)>10 Then pSpecial(cyc)=pSpecial(cyc)-10
 EndIf
 If randy=1 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
 randy=Rnd(0,150)
 If randy=0 Then ProduceSound(p(v),sChoke,GetVoice(v),Rnd(0.2,0.5))
 randy=Rnd(0,wrench*3)
 If randy=0 ;big wrench
  Pop(cyc,Rnd(2,7),crowdVol#/2)
  ProduceSound(p(v),sPain(3),GetVoice(v),1)
  pHealth(v)=pHealth(v)-1
  pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
  If pHolding(cyc)>0 
   ProduceSound(p(cyc),weapSound(weapType(pHolding(cyc))),22050,0) 
   pHealth(v)=pHealth(v)-1 : DropWeapon(cyc,5)
  EndIf
  If pSpecial(cyc)>50 Then pSpecial(cyc)=pSpecial(cyc)-50
  sting=1
 EndIf
 ;tapping
 If pStretched(v)<1 Then pStretched(v)=1
 chance=((pHealth(v)/10)+1)*power
 If pSpecial(cyc)>0 Then chance=chance/2
 If pInjured(v)=1 Then chance=chance/2
 If pInjured(v)=>2 Then chance=0
 randy=Rnd(0,chance)
 If randy=<1 And pAnimTim(cyc)>start+20 And sting=1 And break=0
  Pop(cyc,Rnd(2,7),1)
  ProduceSound(p(v),sPain(Rnd(5,6)),GetVoice(v),1) 
  If pStretched(v)<2 Then pStretched(v)=2 : pDT(v)=pDT(v)+100
  RiskInjury(v,0)
 EndIf
 ;release
 If pAnimTim(cyc)>start+10
  If cGrab(cyc)=1 And pControl(cyc)>0 Then break=1
  If break=1 Then pAnimTim(cyc)=1000 : pStretched(v)=0
 EndIf
 ;break out
 If pAnimTim(cyc)>start+20 And break=0
  chance=charSkl(pChar(cyc))*2
  chance=chance+(200-pHealth(v))
  chance=chance+power
  If pSpecial(cyc)>0 Then chance=1000
  randy=Rnd(0,chance)
  If randy=0 And (DirPressed(v) Or ActionPressed(v) Or pControl(v)=0) Then BreakHold(cyc,v,dir)
  If LostGrip(cyc,v,2) Or pAnimTim(cyc)>start+400 Then BreakHold(cyc,v,dir)
 EndIf
 ;cause chaos
 If LegalMan(cyc) And LegalMan(v) And matchSubs=1 Then CauseChaos(100)
End Function

;LOST GRIP?
Function LostGrip(cyc,v,range)
 value=0
 If pX#(cyc)<pX#(v)-range Or pX#(cyc)>pX#(v)+range Or pZ#(cyc)<pZ#(v)-range Or pZ#(cyc)>pZ#(v)+range
  value=1
 EndIf
 Return value
End Function

;BREAK GROUND MOVE
Function BreakHold(cyc,v,style) ;-1=grapple turn, 0=move on back, 1=move on front, 10+=standing move
 ;effects
 Pop(v,Rnd(2,9),0) 
 ProduceSound(p(cyc),sImpact(Rnd(4,6)),22050,0)
 ProduceSound(p(cyc),sPain(Rnd(2,3)),GetVoice(cyc),0) 
 ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
 ProduceSound(p(cyc),sSwing,20000,0.3)
 ;ground transitions
 If style<10
  BreakPlayer(cyc)
  If style=-1 Then ChangeAnim(v,82)
  If style=0 Then ChangeAnim(v,62)
  If style=1 Then ChangeAnim(v,63)
  EnforceBlocks(v)
 EndIf
 ;standing transition (side headlock)
 If style=10
  ChangeAnim(cyc,61) : SharpTransition(cyc,61,180)
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.2
  pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc) 
  BreakPlayer(v) : pA#(v)=pA#(cyc)
 EndIf
 ;standing transition (sleeper hold)
 If style=11
  ChangeAnim(cyc,61) : SharpTransition(cyc,61,180)
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.2
  pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc) 
  BreakPlayer(v) : pA#(v)=pA#(cyc)+180
 EndIf
 ;standing transition (bear hug)
 If style=12
  ChangeAnim(cyc,61) : pAnimTim(cyc)=-1
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.2
  pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc) 
  BreakPlayer(v) : pA#(v)=pA#(cyc)+180
 EndIf
 EndMove(cyc,v)
End Function