;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING ENCORE: BUCKLE MOVES -----------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------------
;//////////////////// BUCKLE MOVE SEQUENCES //////////////////////////
;---------------------------------------------------------------------
Function LoadBuckleSequences(cyc)
 ;buckle issues
 pSeq(cyc,500)=ExtractAnimSeq(p(cyc),50,110,pSeq(cyc,615)) ;buckle turn
 pSeq(cyc,501)=ExtractAnimSeq(p(cyc),120,150,pSeq(cyc,615)) ;buckle slump
 pSeq(cyc,502)=ExtractAnimSeq(p(cyc),3285,3350,pSeq(cyc,600)) ;buckle fiddle
 pSeq(cyc,503)=ExtractAnimSeq(p(cyc),1230,1295,pSeq(cyc,602)) ;tag slap
 ;superplex
 pSeq(cyc,510)=ExtractAnimSeq(p(cyc),160,445,pSeq(cyc,614)) ;execute
 pSeq(cyc,511)=ExtractAnimSeq(p(cyc),160,445,pSeq(cyc,615)) ;receive
 ;mounted punches
 pSeq(cyc,512)=ExtractAnimSeq(p(cyc),455,770,pSeq(cyc,614)) ;execute
 pSeq(cyc,513)=ExtractAnimSeq(p(cyc),455,770,pSeq(cyc,615)) ;receive
 ;drag down
 pSeq(cyc,514)=ExtractAnimSeq(p(cyc),770,855,pSeq(cyc,614)) ;execute
 pSeq(cyc,515)=ExtractAnimSeq(p(cyc),770,855,pSeq(cyc,615)) ;receive
 ;head smashes
 pSeq(cyc,516)=ExtractAnimSeq(p(cyc),865,1085,pSeq(cyc,614)) ;execute
 pSeq(cyc,517)=ExtractAnimSeq(p(cyc),865,1085,pSeq(cyc,615)) ;receive
 ;hurricanranna
 pSeq(cyc,518)=ExtractAnimSeq(p(cyc),1095,1415,pSeq(cyc,614)) ;execute
 pSeq(cyc,519)=ExtractAnimSeq(p(cyc),1095,1415,pSeq(cyc,615)) ;receive
 ;shoulder barges
 pSeq(cyc,520)=ExtractAnimSeq(p(cyc),1425,1655,pSeq(cyc,614)) ;execute
 pSeq(cyc,521)=ExtractAnimSeq(p(cyc),1425,1655,pSeq(cyc,615)) ;receive
End Function

;---------------------------------------------------------------------
;/////////////////// BUCKLE MOVE ANIMATIONS //////////////////////////
;---------------------------------------------------------------------
Function BuckleMoves(cyc)
 ;buckle turn
 If pAnim(cyc)=400
  pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,500),5
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.2,0.5))
  If pPlatform(cyc)=-1 Then CruiseToLocation(cyc,wBlockX2#(4),wBlockZ1#(1),1) : pTA#(cyc)=45 ;NW
  If pPlatform(cyc)=-2 Then CruiseToLocation(cyc,wBlockX1#(2),wBlockZ1#(1),1) : pTA#(cyc)=315 ;NE
  If pPlatform(cyc)=-3 Then CruiseToLocation(cyc,wBlockX1#(2),wBlockZ2#(3),1) : pTA#(cyc)=225 ;SE
  If pPlatform(cyc)=-4 Then CruiseToLocation(cyc,wBlockX2#(4),wBlockZ2#(3),1) : pTA#(cyc)=135 ;SW
  pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pTA#(cyc),1)
  If pAnimTim(cyc)=15 
   If pPlatform(cyc)=-1 Then ShakeRopes(1,1,2) : ShakeRopes(4,1,2)
   If pPlatform(cyc)=-2 Then ShakeRopes(1,1,2) : ShakeRopes(2,1,2)
   If pPlatform(cyc)=-3 Then ShakeRopes(3,1,2) : ShakeRopes(2,1,2)
   If pPlatform(cyc)=-4 Then ShakeRopes(3,1,2) : ShakeRopes(4,1,2)
   Pop(0,Rnd(4,7),0)
   ProduceSound(p(cyc),sThud,22050,0)
   ProduceSound(p(cyc),sImpact(6),22050,0)
   ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1)
   CreateSpurt(EntityX(pLimb(cyc,30),1),45,EntityZ(pLimb(cyc,30),1),15,99)
   ScarArea(cyc,EntityX(pLimb(cyc,30),1),45,EntityZ(pLimb(cyc,30),1),10)
   pHealth(cyc)=pHealth(cyc)-1
   randy=Rnd(0,2)
   If randy=0 And pMomentum(cyc)>0 Then pHP(cyc)=0
   BuckleImpact(cyc)
   For v=1 To no_plays
    If InProximity(cyc,v,30) And pPlatform(v)=>5 And pPlatform(v)=<8 And TurnViable(v)>0 Then pHP(v)=0
   Next
  EndIf
  If pAnimTim(cyc)>35 
   ChangeAnim(cyc,401) : pA#(cyc)=pTA#(cyc)+180
   Animate p(cyc),1,1,pSeq(cyc,501),0
   pDT(cyc)=Rnd(20,200-pHealth(cyc))
   If pDT(cyc)<20 Then pDT(cyc)=20
   pExcusedPlays(cyc)=0
   pMomentum(cyc)=0
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;buckle slump
 If pAnim(cyc)=401
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(0.2,0.4),pSeq(cyc,501),5
  If pDT(cyc)=<0 
   If DirPressed(cyc) Or ActionPressed(cyc) Then ChangeAnim(cyc,402) : pPlatform(cyc)=0
  EndIf
 EndIf
 ;leave buckle delay
 If pAnim(cyc)=402
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,1.0,pSeq(cyc,1),15
  If pAnimTim(cyc)>10 Then ChangeAnim(cyc,0) : pTA#(cyc)=pA#(cyc)
 EndIf
 ;unfasten pad
 If pAnim(cyc)=403
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,502),5
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,1)
  If pAnimTim(cyc)=25
   For v=1 To 4
    If BuckleProximity(cyc,v,10) And padExposed(v)=0
     Pop(cyc,Rnd(2,8),1)
     ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,1)
     ProduceSound(p(cyc),sImpact(6),20000,0.4)
     ProduceSound(p(cyc),sImpactBlade,20000,0.2)
     limb=FindChild(world,"Ring"+Dig$(padID(v),10))
     CreateSpurt(EntityX(limb,1),45,EntityZ(limb,1),10,4)
     HideEntity FindChild(world,"Pad"+Dig$(padID(v),10))
     matchDamage=matchDamage+50
     padExposed(v)=1
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)>32 Then ChangeAnim(cyc,0)
 EndIf
 ;tag execute
 If pAnim(cyc)=404
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,3.0,pSeq(cyc,503),5 : pSting(cyc)=1
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=11
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And pAnim(v)=405 And pSting(cyc)=1
     Pop(v,Rnd(2,7),0)
     ProduceSound(p(cyc),sImpact(2),22050,0)
     teamLegal(pTeam(cyc))=v
     If pTeam(v)=1 Then pFoc(v)=teamLegal(2)
     If pTeam(v)=2 Then pFoc(v)=teamLegal(1)
     pChaosTim(cyc)=200 : pChaosTim(v)=200
     pAgenda(cyc)=2 : pSting(cyc)=0
     If optTagControl=2 Or (optTagControl=1 And game=0) Then SwapControls(cyc,v,0)
     If pControl(cyc)=0 Then GetNewFoc(cyc)
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)>20 Then ChangeAnim(cyc,0)
 EndIf
 ;tag receive
 If pAnim(cyc)=405
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,3.0,pSeq(cyc,503),5
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)>20 Then ChangeAnim(cyc,0) : pAgenda(cyc)=1
 EndIf
 ;SUPERPLEX
 If pAnim(cyc)=410
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.8,pSeq(cyc,510),0 
   Animate p(v),3,1.8,pSeq(v,511),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=40 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=60
   pStepTim(cyc)=99
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=70 Or pAnimTim(cyc)=80
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=30 Or pAnimTim(cyc)=90 Then Pop(cyc,Rnd(2,7),0)
  If pAnimTim(cyc)=100 Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=116 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=125
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,0)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0
   pDT(cyc)=200-pHealth(cyc) : pDT(v)=400-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>158
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,180)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v) : pPlatform(v)=0
  EndIf
 EndIf
 ;MOUNTED PUNCHES
 If pAnim(cyc)=411
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,512),0 
   Animate p(v),3,2.0,pSeq(v,513),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=137 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=145 Or pAnimTim(cyc)=155 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=27 Or pAnimTim(cyc)=55 Or pAnimTim(cyc)=82 Or pAnimTim(cyc)=115 
   ProduceSound(p(cyc),sSwing,22050,0.5)
  EndIf
  If pAnimTim(cyc)=32 Or pAnimTim(cyc)=60 Or pAnimTim(cyc)=87 Or pAnimTim(cyc)=120
   Pop(cyc,Rnd(4,5),0)
   If pAnimTim(cyc)=120 Then Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sImpact(Rnd(1,2)),22050,0)
   ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)+1,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-1 : ScarLimb(v,1,10) : WeaponImpact(cyc,v)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
  EndIf
  If pAnimTim(cyc)>157 Then ChangeAnim(cyc,412)
 EndIf
 ;DRAG DOWN
 If pAnim(cyc)=412
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,514),0 
   Animate p(v),3,2.0,pSeq(v,515),0
  EndIf
  If pAnimTim(cyc)=7 Or pAnimTim(cyc)=137 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=40 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=22 Then FindSmashes(v,1)
  If pAnimTim(cyc)=30
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-3 : pHP(v)=0 : pDT(v)=200-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10) : DropWeapon(v,0)
  EndIf
  If pAnimTim(cyc)>42
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,180)
   EndMove(cyc,v) : pPlatform(v)=0
  EndIf
 EndIf
 ;HEAD SMASHES
 If pAnim(cyc)=413
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.8,pSeq(cyc,516),0 
   Animate p(v),3,1.8,pSeq(v,517),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=31 Or pAnimTim(cyc)=55 Or pAnimTim(cyc)=77 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=18 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=22 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=67 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=27 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=72
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sImpact(6),22050,0)
   ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)-2,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-1 : ScarLimb(v,1,10) : WeaponImpact(cyc,v)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   BuckleImpact(v)
  EndIf
  If pAnimTim(cyc)=94 Then FindSmashes(v,1)
  If pAnimTim(cyc)=102
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-2 : pHP(v)=0 : pDT(v)=100-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   FindSmashes(v,0) : ScarArea(v,0,0,0,10) : DropWeapon(v,0)
  EndIf
  If pAnimTim(cyc)>122
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,90)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v) : pPlatform(v)=0
  EndIf
 EndIf
 ;HURRICANRANNA
 If pAnim(cyc)=414
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,518),0 
   Animate p(v),3,2.0,pSeq(v,519),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=52 Or pAnimTim(cyc)=150
   pStepTim(cyc)=99
  EndIf
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=62 Or pAnimTim(cyc)=102 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=92 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.5)
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=57 Or pAnimTim(cyc)=90 Then Pop(cyc,Rnd(2,7),0)
  If pAnimTim(cyc)=77 Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=117 Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=115 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=127
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
   FindSmashes(cyc,0) : pHP(cyc)=pHP(cyc)-1
  EndIf 
  If pAnimTim(cyc)=127 Then FindSmashes(v,1)
  If pAnimTim(cyc)=140
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,0)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>160
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v) : pPlatform(v)=0
  EndIf
 EndIf
 ;SHOULDER BARGES
 If pAnim(cyc)=415
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,1.7,pSeq(cyc,520),0 
   Animate p(v),3,1.7,pSeq(v,521),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=125 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=57 Or pAnimTim(cyc)=86 Or pAnimTim(cyc)=115 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=42 Or pAnimTim(cyc)=71 Or pAnimTim(cyc)=100 
   ProduceSound(p(cyc),sSwing,22050,0.5)
  EndIf
  If pAnimTim(cyc)=47 Or pAnimTim(cyc)=76 Or pAnimTim(cyc)=105
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sImpact(3),22050,0)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1)
   CreateSpurt(EntityX(pLimb(v,30),1),EntityY(pLimb(v,30),1)+1,EntityZ(pLimb(v,30),1),15,99)
   pHealth(v)=pHealth(v)-2 : ScarLimb(v,3,10)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-2
   BuckleImpact(v)
  EndIf
  If pAnimTim(cyc)>135 Then ChangeAnim(cyc,412)
 EndIf
End Function

;--------------------------------------------------------------------
;//////////////////////// ROPE CYCLE ////////////////////////////////
;--------------------------------------------------------------------
Function RopeCycle()
 For cyc=1 To 12
  ;stop
  If ropeAnim(cyc)=0
   If ropeAnimTim(cyc)=0 Then Animate rope(cyc),3,0,1,20
  EndIf
  ;shakes
  If ropeAnim(cyc)=>1 And ropeAnim(cyc)=<3
   If ropeAnimTim(cyc)=0 And ropeAnim(cyc)=1 Then Animate rope(cyc),3,Rnd(1.0,1.5),2,5 ;mild
   If ropeAnimTim(cyc)=0 And ropeAnim(cyc)=2 Then Animate rope(cyc),3,Rnd(1.0,1.5),3,5 ;big (immediate)
   If ropeAnimTim(cyc)=10 And ropeAnim(cyc)=3 Then Animate rope(cyc),3,Rnd(1.0,1.5),3,5 ;big (delayed)
   randy=Rnd(0,200)
   If (randy=0 And ropeAnimTim(cyc)>10) Or ropeAnimTim(cyc)>100
    ropeAnim(cyc)=0 : ropeAnimTim(cyc)=-1
   EndIf
  EndIf
  ;whips
  If ropeAnim(cyc)=>4 And ropeAnim(cyc)=<6
   If ropeAnimTim(cyc)=0 Then Animate rope(cyc),3,Rnd(1.5,1.8),ropeAnim(cyc),5
   randy=Rnd(0,200)
   If (randy=0 And ropeAnimTim(cyc)>60) Or ropeAnimTim(cyc)>150
    ropeAnim(cyc)=0 : ropeAnimTim(cyc)=-1
   EndIf
  EndIf
  ;burning
  If ropeBurning(cyc)>0 And optFX=>3
   If optFX=<2 Then density=5 Else density=20
   For count=1 To density
    CreateParticle(FindRopeX#(cyc,Rnd(-70,70)),ropeY#(cyc)-3,FindRopeZ#(cyc,Rnd(-70,70)),7)
   Next
   CreateParticle(FindRopeX#(cyc,Rnd(-70,70)),ropeY#(cyc)-3,FindRopeZ#(cyc,Rnd(-70,70)),2)
   ropeBurning(cyc)=ropeBurning(cyc)-1
   If ropeBurning(cyc)=<0 Then ExtinguishRope(cyc)
  EndIf
  ;hotwire effect
  If matchRopes=3 And optFX=>3
   randy=Rnd(0,15)
   If randy=<1 And matchRopes=3 Then CreateParticle(FindRopeX#(cyc,Rnd(-70,70)),ropeY#(cyc)-3,FindRopeZ#(cyc,Rnd(-70,70)),7)
   If randy=2 And matchRopes=3 Then CreateParticle(FindRopeX#(cyc,Rnd(-70,70)),ropeY#(cyc)-3,FindRopeZ#(cyc,Rnd(-70,70)),2)
  EndIf
  ;increment
  ropeAnimTim(cyc)=ropeAnimTim(cyc)+1
 Next
 ;electric flow
 If matchRopes=>2
  ropeFlow#=ropeFlow#+0.25
  PositionTexture tRopes(matchRopes),1,ropeFlow#
 EndIf
End Function

;-----------------------------------------------------------------
;////////////////////// CAGE CYCLE /////////////////////////////
;-----------------------------------------------------------------
Function CageCycle()
 If matchCage>0
  For cyc=1 To 4
   ;shaking process
   If cageTim(cyc)=>0 Then cageTim(cyc)=cageTim(cyc)+1
   If cageTim(cyc)=1 Or cageTim(cyc)=6
    cageTX#(cyc)=cageOrigX#(cyc)+Rnd(-5,5)
    cageTZ#(cyc)=cageOrigZ#(cyc)+Rnd(-5,5)
   EndIf
   If cageTim(cyc)=11 Or cageTim(cyc)=16
    cageTX#(cyc)=cageOrigX#(cyc)+Rnd(-1,1)
    cageTZ#(cyc)=cageOrigZ#(cyc)+Rnd(-1,1)
   EndIf
   If cageTim(cyc)>20 
    cageTX#(cyc)=cageOrigX#(cyc)
    cageTZ#(cyc)=cageOrigZ#(cyc)
    cageTim(cyc)=-1
   EndIf
   ;pursue position
   shaker#=0.2
   If cageTim(cyc)>10 Then shaker#=0.1
   If cageTim(cyc)=-1 Then shaker#=0.05
   If cageX#(cyc)<cageTX#(cyc) Then cageX#(cyc)=cageX#(cyc)+shaker#
   If cageX#(cyc)>cageTX#(cyc) Then cageX#(cyc)=cageX#(cyc)-shaker#
   If cageZ#(cyc)<cageTZ#(cyc) Then cageZ#(cyc)=cageZ#(cyc)+shaker#
   If cageZ#(cyc)>cageTZ#(cyc) Then cageZ#(cyc)=cageZ#(cyc)-shaker#
   ;display position
   limb=FindChild(cage,"Frame0"+cyc)
   PositionEntity limb,cageX#(cyc),EntityY(limb,1),cageZ#(cyc)
  Next
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;FIND ROPE BOUNCES
Function RopeBounce(cyc)
 If pY#(cyc)=wStage# And pPlatform(cyc)=0
  ;position checker
  ResetDummy(cyc) : MoveEntity dummy,0,0,5
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  ;find rope contact
  If checkZ#>wBlockZ1#(1) And checkX#>wBlockX1#(0)+35 And checkX#<wBlockX2#(0)-35 ;north side
   segment=5
   If pX#(cyc)<-25 Then segment=4
   If pX#(cyc)>25 Then segment=6
   pA#(cyc)=0 : ChangeAnim(cyc,4) : ShakeRopes(1,1,segment)
  EndIf
  If checkX#>wBlockX1#(2) And checkZ#>wBlockZ1#(0)+35 And checkZ#<wBlockZ2#(0)-35 ;east side
   segment=5
   If pZ#(cyc)>25 Then segment=4
   If pZ#(cyc)<-25 Then segment=6
   pA#(cyc)=270 : ChangeAnim(cyc,4) : ShakeRopes(2,1,segment)
  EndIf
  If checkZ#<wBlockZ2#(3) And checkX#>wBlockX1#(0)+35 And checkX#<wBlockX2#(0)-35 ;south side
   segment=5
   If pX#(cyc)<-25 Then segment=6
   If pX#(cyc)>25 Then segment=4
   pA#(cyc)=180 : ChangeAnim(cyc,4) : ShakeRopes(3,1,segment)
  EndIf
  If checkX#<wBlockX2#(4) And checkZ#>wBlockZ1#(0)+35 And checkZ#<wBlockZ2#(0)-35 ;west side
   segment=5
   If pZ#(cyc)>25 Then segment=6
   If pZ#(cyc)<-25 Then segment=4
   pA#(cyc)=90 : ChangeAnim(cyc,4) : ShakeRopes(4,1,segment)
  EndIf
  ;find turnbuckle contact
  If pAnim(cyc)<>4 And pMomentum(cyc)>0 Then FindBuckleSlumps(cyc,checkX#,checkZ#,10) 
EndIf
End Function

;SHAKE ROPES
Function ShakeRopes(cyc,style,anim) ;0=specific, 1-whole side
 If cyc>0
  ropeAnim(cyc)=anim : ropeAnimTim(cyc)=0
  If style=1 And cyc=<4
   ropeAnim(cyc+4)=anim : ropeAnimTim(cyc+4)=0 
   ropeAnim(cyc+8)=anim : ropeAnimTim(cyc+8)=0 
  EndIf
 EndIf
End Function

;EMIT ROPE SOUND
Function RopeSound(entity,volume) ;0=quiet, 1=loud
 ;standard sound
 If volume=1 Then vol#=1.0 Else vol#=0
 ProduceSound(entity,sRopes,44100,vol#)
 ;add novelties
 If volume=1 Then vol#=0 Else vol#=0.3
 If matchRopes>0 Then ProduceSound(entity,sSmashWire,22050,vol#)
 If matchRopes=2 Then ProduceSound(entity,sElectric,22050,vol#)
End Function

;FIND ROPE'S X AXIS
Function FindRopeX#(cyc,variable#)
 If cyc=1 Or cyc=5 Or cyc=9 Or cyc=3 Or cyc=7 Or cyc=11 Then x#=variable#
 If cyc=2 Or cyc=6 Or cyc=10 Or cyc=4 Or cyc=8 Or cyc=12 Then x#=ropeX#(cyc)
 Return x#
End Function

;FIND ROPE'S Z AXIS
Function FindRopeZ#(cyc,variable#)
 If cyc=1 Or cyc=3 Or cyc=5 Or cyc=7 Or cyc=9 Or cyc=11 Then z#=ropeZ#(cyc)
 If cyc=2 Or cyc=4 Or cyc=6 Or cyc=8 Or cyc=10 Or cyc=12 Then z#=variable#
 Return z#
End Function

;NEAR ROPES?
Function NearRopes(cyc,offset#)
 value=0
 If pY#(cyc)=>wStage# Or PointViable(cyc)=1 Or (pAnim(cyc)=109 And pAnim(pGrappler(cyc))=112)
  For v=1 To 4
   If RealX#(cyc)>wBlockX1#(v)-offset# And RealX#(cyc)<wBlockX2#(v)+offset# And RealZ#(cyc)>wBlockZ1#(v)-offset# And RealZ#(cyc)<wBlockZ2#(v)+offset#
    value=v
   EndIf
  Next
 EndIf
 Return value
End Function

;FIND ROPE IMPACTS
Function FindRopeImpacts(cyc)
 ;human impacts
 If pAnimTim(cyc)=>8 And pAnimTim(cyc)=<12
  For v=1 To no_plays 
   If cyc<>v And AttackViable(v)=1 And pPlatform(v)=>1 And pPlatform(v)=<4 
    If RealX#(cyc)>pX#(v)-15 And RealX#(cyc)<pX#(v)+15 And RealZ#(cyc)>pZ#(v)-15 And RealZ#(cyc)<pZ#(v)+15
     Pop(cyc,Rnd(2,7),0)
     ProduceSound(p(v),sImpact(6),22050,0)
     ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
     CreateSpurt(pX#(v),pY#(cyc)+18,pZ#(v),15,3)
     CreateSpurt(pX#(v),pY#(cyc)+18,pZ#(v),15,4)
     ImpactChecks(v) : ChangeAnim(v,60)
     pHealth(v)=pHealth(v)-1 : pHP(v)=0 : pDT(v)=200-pHealth(v)
     pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-1
     RopeBurn(v,1) 
     pFoc(v)=cyc : pSting(cyc)=0
    EndIf
   EndIf
  Next
 EndIf
 ;rope burn
 If pAnimTim(cyc)=8 Then RopeBurn(cyc,0) 
 ;cage impacts
 cager=NearestCage(cyc)
 If pAnimTim(cyc)=12 And cager>0 
  Pop(0,Rnd(4,7),0)
  ProduceSound(p(cyc),sSmashWire,22050,0)
  ProduceSound(p(cyc),sImpact(6),22050,0)
  ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1)
  ScarArea(cyc,0,0,0,5)
  pHealth(cyc)=pHealth(cyc)-1 : pHP(cyc)=pHP(cyc)-Rnd(1,5)
  cageTim(cager)=0
 EndIf
 ;topple over
 If pMomentum(cyc)>0 Or pHP(cyc)=<0 Then randy=Rnd(0,10) Else randy=Rnd(0,30)
 If randy=0 And pAnimTim(cyc)=10
  If matchCage>0 Then anim=75 Else anim=Rnd(74,75)
  ChangeAnim(cyc,anim) : SharpTransition(cyc,pAnim(cyc)-2,180)
  If pAnim(cyc)=75 And SatisfiedAngle(pA#(cyc),180,20) Then pPlatform(cyc)=1
  If pAnim(cyc)=75 And SatisfiedAngle(pA#(cyc),90,20) Then pPlatform(cyc)=2
  If pAnim(cyc)=75 And SatisfiedAngle(pA#(cyc),0,20) Then pPlatform(cyc)=3
  If pAnim(cyc)=75 And SatisfiedAngle(pA#(cyc),270,20) Then pPlatform(cyc)=4
 EndIf
End Function

;FIND BUCKLE SLUMPS
Function FindBuckleSlumps(cyc,x#,z#,range)
 range=10
 ;north west
 If z#>wBlockZ1#(1)-range And x#<wBlockX2#(4)+range And BuckleClear(-1) 
  ChangeAnim(cyc,400) : pPlatform(cyc)=-1  
 EndIf
 ;north east
 If z#>wBlockZ1#(1)-range And x#>wBlockX1#(2)-range And BuckleClear(-2) 
  ChangeAnim(cyc,400) : pPlatform(cyc)=-2 
 EndIf
 ;south east
 If z#<wBlockZ2#(3)+range And x#>wBlockX1#(2)-range And BuckleClear(-3)
  ChangeAnim(cyc,400) : pPlatform(cyc)=-3 
 EndIf
 ;south west
 If z#<wBlockZ2#(3)+range And x#<wBlockX2#(4)+range And BuckleClear(-4)
  ChangeAnim(cyc,400) : pPlatform(cyc)=-4 
 EndIf
End Function

;TURNBUCKLE CLEAR?
Function BuckleClear(buckle)
 clear=1
 For v=1 To no_plays
  If pPlatform(v)=buckle Then clear=0
 Next
 Return clear
End Function

;BUCKLE PROXIMITY
Function BuckleProximity(cyc,v,range)
 value=0
 If pY#(cyc)=wStage# And pPlatform(cyc)=<0
  limb=FindChild(world,"Ring"+Dig$(padID(v),10))
  If pX#(cyc)>EntityX(limb,1)-range And pX#(cyc)<EntityX(limb,1)+range And pZ#(cyc)>EntityZ(limb,1)-range And pZ#(cyc)<EntityZ(limb,1)+range
   value=1
  EndIf
 EndIf
 Return value
End Function

;FIND BARE BUCKLE IMPACTS
Function BuckleImpact(cyc)
 For v=1 To 4
  If BuckleProximity(cyc,v,10) And padExposed(v)=1
   Pop(0,Rnd(4,7),1)
   ProduceSound(p(cyc),sImpact(3),22050,0)
   ProduceSound(p(cyc),sImpactBlade,22050,0)
   limb=FindChild(world,"Ring"+Dig$(padID(v),10))
   CreateSpurt(EntityX(limb,1),45,EntityZ(limb,1),15,99)
   ScarArea(cyc,EntityX(pLimb(cyc,30),1),45,EntityZ(pLimb(cyc,30),1),5)
   If CountScars(cyc)=>4 Then EntityTexture limb,tBodyScar(Rnd(1,4)),0,2
   pHealth(cyc)=pHealth(cyc)-1
  EndIf
 Next
End Function

;NEAREST CAGE
Function NearestCage(cyc)
 value=0 : range=25
 If matchCage>0
  ;check north
  If RealX#(cyc)>wBlockX1#(0) And RealX#(cyc)<wBlockX2#(0) And RealZ#(cyc)>wBlockZ2#(0)-range And RealZ#(cyc)<wBlockZ2#(0)+range Then value=1
  ;check east
  If RealX#(cyc)>wBlockX2#(0)-range And RealX#(cyc)<wBlockX2#(0)+range And RealZ#(cyc)>wBlockZ1#(0) And RealZ#(cyc)<wBlockZ2#(0) Then value=2
  ;check south
  If RealX#(cyc)>wBlockX1#(0) And RealX#(cyc)<wBlockX2#(0) And RealZ#(cyc)>wBlockZ1#(0)-range And RealZ#(cyc)<wBlockZ1#(0)+range Then value=3
  ;check west
  If RealX#(cyc)>wBlockX1#(0)-range And RealX#(cyc)<wBlockX1#(0)+range And RealZ#(cyc)>wBlockZ1#(0) And RealZ#(cyc)<wBlockZ2#(0) Then value=4
 EndIf
 Return value
End Function