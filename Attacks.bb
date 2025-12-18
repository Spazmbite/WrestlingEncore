;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING ENCORE: ATTACKS --------------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;////////////////// LOAD ATTACK SEQUENCES ////////////////////////
;-----------------------------------------------------------------
Function LoadAttackSequences(cyc)
 ;upper attack
 move=charAttack(pChar(cyc),1)
 If move=1 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),0,65,pSeq(cyc,602)) ;punch
 If move=2 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),6165,6250,pSeq(cyc,602)) ;uppercut
 If move=3 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),6260,6315,pSeq(cyc,602)) ;jab
 If move=4 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),1230,1295,pSeq(cyc,602)) ;open hand
 If move=5 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),6325,6415,pSeq(cyc,602)) ;slap
 If move=6 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),3345,3445,pSeq(cyc,602)) ;chop
 If move=7 Then pSeq(cyc,40)=ExtractAnimSeq(p(cyc),6055,6155,pSeq(cyc,602)) ;high kick
 ;lower attack
 move=charAttack(pChar(cyc),2)
 If move=1 Then pSeq(cyc,41)=ExtractAnimSeq(p(cyc),360,430,pSeq(cyc,602)) ;kick
 If move=2 Then pSeq(cyc,41)=ExtractAnimSeq(p(cyc),2925,3000,pSeq(cyc,602)) ;side kick
 If move=3 Then pSeq(cyc,41)=ExtractAnimSeq(p(cyc),3455,3550,pSeq(cyc,602)) ;body blow
 ;big attack
 move=charAttack(pChar(cyc),3)
 If move=1 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),695,770,pSeq(cyc,602)) ;double axe-handle
 If move=2 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),5320,5465,pSeq(cyc,602)) ;spinning punch
 If move=3 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),5105,5170,pSeq(cyc,602)) ;forearm
 If move=4 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),620,685,pSeq(cyc,602)) ;standing clothesline
 If move=5 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),1810,1940,pSeq(cyc,602)) ;flying clothesline
 If move=6 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),5180,5310,pSeq(cyc,602)) ;flying body tackle
 If move=7 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),1475,1645,pSeq(cyc,602)) ;dropkick
 If move=8 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),3205,3335,pSeq(cyc,602)) ;flying kick
 If move=9 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),5475,5615,pSeq(cyc,602)) ;spinning kick 
 If move=10 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),2090,2270,pSeq(cyc,602)) ;cartwheel kick
 If move=11 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),2805,2915,pSeq(cyc,602)) ;shuffle kick
 If move=12 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),5790,5925,pSeq(cyc,602)) ;big boot
 If move=13 Or move=14 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),4140,4270,pSeq(cyc,602)) ;spit mist
 If move=15 Then pSeq(cyc,42)=ExtractAnimSeq(p(cyc),4280,4390,pSeq(cyc,602)) ;dust throw
 ;running attack
 move=charAttack(pChar(cyc),4)
 If move=1 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),5105,5170,pSeq(cyc,602)) ;forearm
 If move=2 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),620,685,pSeq(cyc,602)) ;clothesline
 If move=3 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),1810,1940,pSeq(cyc,602)) ;flying clothesline 
 If move=4 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),5180,5310,pSeq(cyc,602)) ;flying body tackle
 If move=5 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),1475,1645,pSeq(cyc,602)) ;dropkick
 If move=6 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),3205,3335,pSeq(cyc,602)) ;flying kick
 If move=7 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),5475,5615,pSeq(cyc,602)) ;spinning kick
 If move=8 Then pSeq(cyc,43)=ExtractAnimSeq(p(cyc),2090,2270,pSeq(cyc,602)) ;cartwheel kick 
 ;flying attack
 move=charAttack(pChar(cyc),5)
 If move=1 Then pSeq(cyc,44)=ExtractAnimSeq(p(cyc),510,610,pSeq(cyc,602)) ;double axe-handle
 If move=2 Then pSeq(cyc,44)=ExtractAnimSeq(p(cyc),1950,2080,pSeq(cyc,602)) ;clothesline
 If move=3 Then pSeq(cyc,44)=ExtractAnimSeq(p(cyc),3080,3195,pSeq(cyc,602)) ;missile dropkick
 If move=4 Then pSeq(cyc,44)=ExtractAnimSeq(p(cyc),2280,2405,pSeq(cyc,602)) ;spinning heel kick
 ;universal
 pSeq(cyc,45)=ExtractAnimSeq(p(cyc),1370,1465,pSeq(cyc,602)) ;rising punch
 pSeq(cyc,46)=ExtractAnimSeq(p(cyc),550,630,pSeq(cyc,603)) ;broad weapon attack
 pSeq(cyc,47)=ExtractAnimSeq(p(cyc),695,770,pSeq(cyc,602)) ;handheld weapon attack
 pSeq(cyc,48)=ExtractAnimSeq(p(cyc),4140,4270,pSeq(cyc,602)) ;guaranteed spitting 
End Function

;-----------------------------------------------------------------
;////////////////// STANDING ATTACK ANIMATIONS ///////////////////
;-----------------------------------------------------------------
Function AttackAnims(cyc)
 ;upper attack
 If pAnim(cyc)=40
  move=charAttack(pChar(cyc),1)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,attackSpeed#(1,move),pSeq(cyc,40),5
   InstantTurn(cyc) : pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.1,0.3))
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<attackMomentum(1,move) And attackTravel#(1,move)>0
   Advance(cyc,pA#(cyc),attackTravel#(1,move))
   pStepTim(cyc)=pStepTim(cyc)+Rnd(0,1)
  EndIf
  If pAnimTim(cyc)=>attackImpact(1,move)-attackRange(1,move) And pAnimTim(cyc)=<attackImpact(1,move)+4 And pSting(cyc)=1
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And pY#(cyc)<pY#(v)+5 And AttackViable(v)=1 And pSting(cyc)=1
     range=pAnimTim(cyc)-(attackImpact(1,move)-attackRange(1,move)) 
     If range>attackRange(1,move) Then range=attackRange(1,move)
     contact=InRange(cyc,v,range)
     If contact>0 And range>2
      randy=Rnd(0,3)
	  If randy=0 Then Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(Rnd(1,2)),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,99)
      ScarLimb(v,1,20)
      ImpactChecks(v) : ChangeAnim(v,60)
      pHurtA#(v)=pA#(cyc) : pStagger#(v)=(6-contact)*0.2
      If pStagger#(v)<0.2 Then pStagger#(v)=0.2
      damage=charStr(pChar(cyc))/25
      pHealth(v)=pHealth(v)-(damage/2) : pHP(v)=pHP(v)-damage
      pDT(v)=(200-pHealth(v))+(damage*10)
      pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-1
      If attackWeapon(1,move)>0 Then WeaponImpact(cyc,v)
      ClockInvader(cyc,v) 
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  If attackWeapon(1,move)>0 And pAnimTim(cyc)=attackImpact(1,move)+5 And pSting(cyc)=0 Then DropWeapon(cyc,5)
  randy=Rnd(0,50)
  If randy=0 And pAnimTim(cyc)>attackImpact(1,move)+5 Then ChangeAnim(cyc,0)
  If pAnimTim(cyc)>attackLength(1,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,46)
 EndIf
 ;lower attack
 If pAnim(cyc)=41
  move=charAttack(pChar(cyc),2)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,attackSpeed#(2,move),pSeq(cyc,41),5
   InstantTurn(cyc) : pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=7 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.1,0.3))
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<attackMomentum(2,move) And attackTravel#(2,move)>0
   Advance(cyc,pA#(cyc),attackTravel#(2,move))
   pStepTim(cyc)=pStepTim(cyc)+Rnd(0,1)
  EndIf
  If pAnimTim(cyc)=>attackImpact(2,move)-attackRange(2,move) And pAnimTim(cyc)=<attackImpact(2,move)+4 And pSting(cyc)=1
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And pY#(cyc)>pY#(v)-10 And pY#(cyc)<pY#(v)+5 And AttackViable(v)=1 And pSting(cyc)=1
     range=pAnimTim(cyc)-(attackImpact(2,move)-attackRange(2,move)) 
     If range>attackRange(2,move) Then range=attackRange(2,move)
     contact=InRange(cyc,v,range)
     If contact>0 And range>2
      randy=Rnd(0,3)
	  If randy=0 Then Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(cyc)+16,pZ#(v),15,99)
      ScarArea(v,pX#(v),pY#(cyc)+16,pZ#(v),20)
      ImpactChecks(v) : ChangeAnim(v,61)
      pHurtA#(v)=pA#(cyc) : pStagger#(v)=(6-contact)*0.25
      If pStagger#(v)<0.2 Then pStagger#(v)=0.2
      damage=charStr(pChar(cyc))/20
      pHealth(v)=pHealth(v)-(damage/2) : pHP(v)=pHP(v)-damage
      pDT(v)=(200-pHealth(v))+(damage*10)
      pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-1
      If attackWeapon(2,move)>0 Then WeaponImpact(cyc,v)
      ClockInvader(cyc,v)
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  If attackWeapon(2,move)>0 And pAnimTim(cyc)=attackImpact(2,move)+5 And pSting(cyc)=0 Then DropWeapon(cyc,5)
  randy=Rnd(0,50)
  If randy=0 And pAnimTim(cyc)>attackImpact(2,move)+5 Then ChangeAnim(cyc,0)
  If pAnimTim(cyc)>attackLength(2,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,46)
 EndIf 
 ;big attack
 If pAnim(cyc)=42
  move=charAttack(pChar(cyc),3) : anim=42
  If pHolding(cyc)>0 ;weapon offsets
   move=1 : anim=47
   If weapType(pHolding(cyc))=>16 And weapType(pHolding(cyc))=<17 Then move=14 : anim=48 
  EndIf
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,attackSpeed#(3,move),pSeq(cyc,anim),5
   InstantTurn(cyc) : pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sSwing,20000,Rnd(0.3,0.5))
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<attackMomentum(3,move) And attackTravel#(3,move)>0
   Advance(cyc,pA#(cyc),attackTravel#(3,move))
   pStepTim(cyc)=pStepTim(cyc)+Rnd(0,1)
  EndIf
  If attackWeapon(3,move)=<1 ;impacts
   If pAnimTim(cyc)=>attackImpact(3,move)-attackRange(3,move) And pAnimTim(cyc)=<attackImpact(3,move)+4 And pSting(cyc)=1
    For v=1 To no_plays
     If cyc<>v And InProximity(cyc,v,20) And pY#(cyc)<pY#(v)+5 And AttackViable(v)=1 And pSting(cyc)=1
      range=pAnimTim(cyc)-(attackImpact(3,move)-attackRange(3,move)) 
      If range>attackRange(3,move) Then range=attackRange(3,move)
      contact=InRange(cyc,v,range)
      If contact>0 And range>2
       Pop(cyc,Rnd(2,7),0)
       ProduceSound(p(v),sImpact(3),22050,0)
       ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
       CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,99)
       ScarLimb(v,1,10)
       ImpactChecks(v) : ChangeAnim(v,64)
       pHurtA#(v)=pA#(cyc) : pStagger#(v)=(attackRange(3,move)-contact)*0.3
       If pStagger#(v)<0.3 Then pStagger#(v)=0.3
       If pSpecial(cyc)>0 Then damage=charStr(pChar(cyc))/9 Else damage=charStr(pChar(cyc))/12
       pHealth(v)=pHealth(v)-(damage/2) : pHP(v)=pHP(v)-damage
       pDT(v)=(225-pHealth(v))+(damage*10)
       pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
       If attackWeapon(3,move)=1 Then WeaponImpact(cyc,v)
       ClockInvader(cyc,v)
       pFoc(v)=cyc : pSting(cyc)=0
      EndIf
     EndIf
    Next
   EndIf
  EndIf
  If attackWeapon(3,move)>1 ;spit effects
   spurt=attackWeapon(3,move)
   If pHolding(cyc)>0 And attackWeapon(3,move)=>7 And weapType(pHolding(cyc))=16 Then spurt=6
   If pHolding(cyc)>0 And attackWeapon(3,move)=>7 And weapType(pHolding(cyc))=17 Then spurt=14
   If pAnimTim(cyc)=18 
    pHeat(cyc)=pHeat(cyc)-1
    If spurt=7 Then ProduceSound(p(cyc),sIgnite,22050,1)
    If spurt<>7 Then ProduceSound(p(cyc),sIgnite,22050,0.3)
    If spurt=6 Or spurt=14 Then ProduceSound(p(cyc),sSplash,22050,0.3)
   EndIf
   If pAnimTim(cyc)=>18 And pAnimTim(cyc)=<32 And spurt=>6 Then CreateSpit(cyc,spurt)
   If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<28 And spurt=2
    limb=pLimb(cyc,19)
    CreateSpurt(EntityX(limb,1)+Rnd(-3,3),EntityY(limb,1)+Rnd(-3,3),EntityZ(limb,1)+Rnd(-3,3),8,4)
    CreateSpurt(EntityX(limb,1)+Rnd(-3,3),EntityY(limb,1)+Rnd(-3,3),EntityZ(limb,1)+Rnd(-3,3),4,2)
   EndIf
   If pAnimTim(cyc)=>attackImpact(3,move)-4 And pAnimTim(cyc)=<attackImpact(3,move)+4 And pSting(cyc)=1
    For v=1 To no_plays
     If cyc<>v And InProximity(cyc,v,20) And AttackViable(v)=1
      If InRange(cyc,v,attackRange(3,move))
       Pop(cyc,Rnd(2,7),1)
       ProduceSound(p(v),sBleed,22050,0)
       ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1)
       ProduceSound(p(v),sChoke,GetVoice(v),0)
       If spurt=7
        ProduceSound(p(v),sIgnite,22050,0)
        CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,7)
        CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,2)
        pHealth(v)=pHealth(v)-1 : pHP(v)=pHP(v)-1
        ScarLimb(v,1,5)
       EndIf
       If spurt=6 Or spurt=9 Or spurt=14 Then CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,spurt)
       If spurt=9 And tFaceScar(5)>0 Then EntityTexture pLimb(v,1),tFaceScar(5),0,6
       ImpactChecks(v) : ChangeAnim(v,60)
       pHurtA#(v)=pA#(cyc) : pStagger#(v)=0.3
       pHealth(v)=pHealth(v)-1 : pHP(v)=pHP(v)-1
       pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)-5
       pDazed(v)=Rnd(50,200) : pSting(cyc)=0
      EndIf
     EndIf
    Next
   EndIf
   randy=Rnd(0,30)
   If randy=0 And pAnimTim(cyc)>attackImpact(3,move)+6 And (spurt=6 Or spurt=14) Then DropWeapon(cyc,0) : ChangeAnim(cyc,0)
  EndIf
  If attackWeapon(3,move)=1 And pAnimTim(cyc)=attackImpact(3,move)+10 And pSting(cyc)=0 Then DropWeapon(cyc,5)
  If attackFall(3,move)>0 And pAnimTim(cyc)=attackFall(3,move)
   ProduceSound(p(cyc),sThud,22050,1)
   If attackName$(3,move)<>"Flying Kick" And attackName$(3,move)<>"Spinning Kick" 
    ProduceSound(p(cyc),sFall,22050,1) : FindSmashes(cyc,0)
    If pSting(cyc)=1 Then ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1) : pHP(cyc)=pHP(cyc)-1
   EndIf
  EndIf
  If pAnimTim(cyc)>attackLength(3,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,46)
 EndIf
 ;running attack
 If pAnim(cyc)=43
  move=charAttack(pChar(cyc),4)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,attackSpeed#(4,move),pSeq(cyc,43),5
   pFlight#(cyc)=pSpeed#(cyc)*2 : pSting(cyc)=1
  EndIf 
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,20000,Rnd(0.3,0.5))
  If pAnimTim(cyc)<attackMomentum(4,move)
   If pFlight#(cyc)>pSpeed#(cyc)/2 Then pFlight#(cyc)=pFlight#(cyc)-attackTravel#(4,move)
   Advance(cyc,pA#(cyc),pFlight#(cyc))
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=>attackImpact(4,move)-attackRange(4,move) And pAnimTim(cyc)=<attackImpact(4,move)+4 And pSting(cyc)=1
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And pY#(cyc)<pY#(v)+5 And AttackViable(v)=1
     range=pAnimTim(cyc)-(attackImpact(4,move)-attackRange(4,move)) 
     If range>attackRange(4,move) Then range=attackRange(4,move)
     contact=InRange(cyc,v,range)
     If contact>0 And range>2
      Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(3),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,99)
      ScarLimb(v,1,10)
      ImpactChecks(v) : ChangeAnim(v,64)
      pHurtA#(v)=pA#(cyc) : pStagger#(v)=(attackRange(4,move)-contact)*0.3
      If pStagger#(v)<0.3 Then pStagger#(v)=0.3
      If pSpecial(cyc)>0 Then damage=charStr(pChar(cyc))/9 Else damage=charStr(pChar(cyc))/12
      pHealth(v)=pHealth(v)-(damage/2) : pHP(v)=pHP(v)-damage
      pDT(v)=(225-pHealth(v))+(damage*10)
      pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
      If attackWeapon(4,move)>0 Then WeaponImpact(cyc,v)
      ClockInvader(cyc,v)
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  If attackWeapon(4,move)>0 And pAnimTim(cyc)=attackImpact(4,move)+10 And pSting(cyc)=0 Then DropWeapon(cyc,5)
  If attackFall(4,move)>0 And pAnimTim(cyc)=attackFall(4,move)
   ProduceSound(p(cyc),sThud,22050,1)
   If attackName$(4,move)<>"Flying Kick" And attackName$(4,move)<>"Spinning Kick"
    ProduceSound(p(cyc),sFall,22050,1) : FindSmashes(cyc,0)
    If pSting(cyc)=1 Then ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1) : pHP(cyc)=pHP(cyc)-1
   EndIf
  EndIf
  If pAnimTim(cyc)>attackLength(4,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,46)
 EndIf
 ;flying attack
 If pAnim(cyc)=44
  move=charAttack(pChar(cyc),5)
  FlyingAttack(cyc,44,attackSpeed#(5,move),attackMomentum(5,move),attackLength(5,move),1,attackWeapon(5,move))
 EndIf
 ;rising punch
 If pAnim(cyc)=45
  If pOldAnim(cyc)=81 Then transition=5 Else transition=0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,45),transition : pSting(cyc)=1
  If pAnimTim(cyc)=6 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.2,0.4))
  If pAnimTim(cyc)=>11 And pAnimTim(cyc)=<17 And pSting(cyc)=1
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And pY#(cyc)>pY#(v)-10 And AttackViable(v)=1 And pSting(cyc)=1
     range=pAnimTim(cyc)-10
     If range>5 Then range=5
     contact=InRange(cyc,v,range)
     If contact>0 And range>2
      Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(cyc)+13,pZ#(v),15,99)
      ScarArea(v,pX#(v),pY#(cyc)+13,pZ#(v),20)
      ImpactChecks(v) : ChangeAnim(v,61)
      pHurtA#(v)=pA#(cyc) : pStagger#(v)=(6-contact)*0.2
      If pStagger#(v)<0.2 Then pStagger#(v)=0.2
      pHealth(v)=pHealth(v)-2 : pHP(v)=pHP(v)-Rnd(1,3) : pDT(v)=200-pHealth(v)
      pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
      WeaponImpact(cyc,v) : ClockInvader(cyc,v)
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)=20 And pSting(cyc)=0 Then DropWeapon(cyc,5)
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)>47 Then ChangeAnim(cyc,0) : pHP(cyc)=20
 EndIf
 ;weapon attack
 If pAnim(cyc)=46
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,46),5 : pSting(cyc)=1
  If pAnimTim(cyc)=12 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>5 And pAnimTim(cyc)=<30
   Advance(cyc,pA#(cyc),0.1)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=>17 And pAnimTim(cyc)=<21
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,30) And pY#(cyc)<pY#(v)+5 And AttackViable(v)=1 And pSting(cyc)=1
     contact=InRange(cyc,v,pAnimTim(cyc)-12)
     If contact>0
      Pop(cyc,Rnd(2,7),1)
      ProduceSound(p(v),weapSound(weapType(pHolding(cyc))),22050,0)
      ProduceSound(p(v),sImpact(3),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(cyc)+23,pZ#(v),15,99)
      ScarArea(v,pX#(v),pY#(cyc)+23,pZ#(v),10) : ScarLimb(v,1,5)
      If CountScars(v)=>4 Then ScarWeapon(pHolding(cyc),3)
      ImpactChecks(v) : ChangeAnim(v,64)
      pHurtA#(v)=pA#(cyc) : pStagger#(v)=(8-contact)*0.3
      If pStagger#(v)<0.3 Then pStagger#(v)=0.3
      damage=weapDamage(weapType(pHolding(cyc)))+(charStr(pChar(cyc))/20)
      pHealth(v)=pHealth(v)-(damage/2) : pHP(v)=pHP(v)-damage
      pDT(v)=(250-pHealth(v))+(damage*10)
      pDazed(v)=pDazed(v)+50 : pHeat(v)=pHeat(v)-1
      ClockInvader(cyc,v) : DQ(cyc,v)
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  randy=Rnd(0,3)
  If randy=0 And pAnimTim(cyc)=30 And pSting(cyc)=0 Then DropWeapon(cyc,0)
  If pAnimTim(cyc)>35 Then ChangeAnim(cyc,0)
 EndIf
End Function

;-----------------------------------------------------------------
;/////////////// LOAD GROUND ATTACK SEQUENCES ////////////////////
;-----------------------------------------------------------------
Function LoadCrushSequences(cyc)
 ;stomp
 move=charCrush(pChar(cyc),2)
 If move=1 Then pSeq(cyc,50)=ExtractAnimSeq(p(cyc),440,500,pSeq(cyc,602)) ;stomp
 If move=2 Then pSeq(cyc,50)=ExtractAnimSeq(p(cyc),3010,3070,pSeq(cyc,602)) ;kick
 ;big/running crush
 For count=3 To 4
  move=charCrush(pChar(cyc),count)
  If move=1 Then pSeq(cyc,48+count)=ExtractAnimSeq(p(cyc),3550,3755,pSeq(cyc,602)) ;elbow drop
  If move=2 Then pSeq(cyc,48+count)=ExtractAnimSeq(p(cyc),780,895,pSeq(cyc,602)) ;knee drop
  If move=3 Then pSeq(cyc,48+count)=ExtractAnimSeq(p(cyc),2415,2605,pSeq(cyc,602)) ;leg drop
  If move=4 Then pSeq(cyc,48+count)=ExtractAnimSeq(p(cyc),4770,4935,pSeq(cyc,602)) ;splash
  If move=5 Then pSeq(cyc,48+count)=ExtractAnimSeq(p(cyc),4945,5095,pSeq(cyc,602)) ;moonsault
  If move=6 Then pSeq(cyc,48+count)=ExtractAnimSeq(p(cyc),3920,4130,pSeq(cyc,602)) ;senton bomb
 Next
 ;flying crush
 move=charCrush(pChar(cyc),5)
 If move=1 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),3765,3915,pSeq(cyc,602)) ;elbow drop
 If move=2 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),5625,5780,pSeq(cyc,602)) ;knee drop
 If move=3 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),2615,2780,pSeq(cyc,602)) ;leg drop
 If move=4 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),905,1015,pSeq(cyc,602)) ;splash
 If move=5 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),4400,4510,pSeq(cyc,602)) ;frog splash 
 If move=6 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),4520,4635,pSeq(cyc,602)) ;moonsault
 If move=7 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),4645,4760,pSeq(cyc,602)) ;shooting star
 If move=8 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),1655,1790,pSeq(cyc,602)) ;senton bomb
 If move=9 Then pSeq(cyc,53)=ExtractAnimSeq(p(cyc),5935,6045,pSeq(cyc,602)) ;450 splash
 ;universal
 pSeq(cyc,54)=ExtractAnimSeq(p(cyc),1025,1035,pSeq(cyc,602)) ;land on front normalizer 
 pSeq(cyc,55)=ExtractAnimSeq(p(cyc),1795,1800,pSeq(cyc,602)) ;land on back normalizer 
 pSeq(cyc,56)=ExtractAnimSeq(p(cyc),640,720,pSeq(cyc,603)) ;weapon crush
End Function

;-----------------------------------------------------------------
;////////////////// GROUND ATTACK ANIMATIONS /////////////////////
;-----------------------------------------------------------------
Function GroundAttackAnims(cyc)
 ;stomp
 If pAnim(cyc)=50
  move=charCrush(pChar(cyc),2)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,crushSpeed#(2,move),pSeq(cyc,50),5 : pSting(cyc)=1
  If pAnimTim(cyc)=7 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.1,0.3))
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<crushMomentum(2,move) And crushTravel#(2,move)>0
   Advance(cyc,pA#(cyc),crushTravel#(2,move))
   pStepTim(cyc)=pStepTim(cyc)+Rnd(0,1)
  EndIf
  If pAnimTim(cyc)=crushImpact(2,move)
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And AttackViable(v)=2 And pSting(cyc)=1 
     If InRange(cyc,v,crushRange(2,move))
      randy=Rnd(0,5)
	  If randy=0 Then Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(4),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
      limb=FindChild(p(cyc),"R_Foot")
      PositionEntity dummy,EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1)
      RotateEntity dummy,0,EntityYaw(limb,1),0
      MoveEntity dummy,0,0,3*move
      CreateSpurt(EntityX(dummy),pY#(v)+2,EntityZ(dummy),15,99)
      ScarArea(v,EntityX(limb,1),pY#(v),EntityZ(limb,1),20)
      GroundImpactChecks(v) : pHealth(v)=pHealth(v)-1
      pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1 
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  randy=Rnd(0,50)
  If randy=0 And pAnimTim(cyc)>crushImpact(2,move)+5 Then ChangeAnim(cyc,0)
  If pAnimTim(cyc)>crushLength(2,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,54)
 EndIf
 ;big crush
 If pAnim(cyc)=51
  move=charCrush(pChar(cyc),3)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,crushSpeed#(3,move),pSeq(cyc,51),5 : pSting(cyc)=1
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,20000,Rnd(0.3,0.5))
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<crushMomentum(3,move) And crushTravel#(3,move)>0
   Advance(cyc,pA#(cyc),crushTravel#(3,move))
   pStepTim(cyc)=pStepTim(cyc)+Rnd(0,1)
  EndIf
  If pAnimTim(cyc)=crushImpact(3,move)
   ProduceSound(p(cyc),sFall,22050,0)
   ProduceSound(p(cyc),sThud,22050,0)
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And AttackViable(v)=2 And pSting(cyc)=1 
     If InRange(cyc,v,crushRange(3,move)) Or InProximity(cyc,v,5)
      Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(6),22050,0)
      ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1)
      limb=FindChild(p(cyc),"Hips")
      If crushName$(3,move)="Elbow Drop" Then limb=FindChild(p(cyc),"R_Arm")
      CreateSpurt(EntityX(limb,1),pY#(v)+3,EntityZ(limb,1),15,99)
      ScarArea(v,EntityX(limb,1),pY#(v),EntityZ(limb,1),10)
      GroundImpactChecks(v) : FindSmashes(v,0)
      pHealth(v)=pHealth(v)-(charStr(pChar(cyc))/20)
      pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
      If crushWeapon(3,move)>0 Then WeaponImpact(cyc,v)
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
   If pSting(cyc)=1 Then ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1) : FindSmashes(cyc,0) : pHP(cyc)=0
  EndIf
  If pHP(cyc)=<0 And pAnimTim(cyc)>crushFall(3,move) Then ChangeAnim(cyc,0) 
  If pAnimTim(cyc)>crushLength(3,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,54)
 EndIf 
 ;running crush
 If pAnim(cyc)=52
  move=charCrush(pChar(cyc),4)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,crushSpeed#(4,move),pSeq(cyc,52),5 
   pFlight#(cyc)=pSpeed#(cyc)*2 : pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,20000,Rnd(0.3,0.5))
  If pAnimTim(cyc)<crushMomentum(4,move)
   If pFlight#(cyc)>pSpeed#(cyc)/2 Then pFlight#(cyc)=pFlight#(cyc)-crushTravel#(4,move)
   If InProximity(cyc,pFoc(cyc),5)=0 Then Advance(cyc,pA#(cyc),pFlight#(cyc))
  EndIf
  If pAnimTim(cyc)=crushImpact(4,move)
   ProduceSound(p(cyc),sFall,22050,0)
   ProduceSound(p(cyc),sThud,22050,0)
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And AttackViable(v)=2 And pSting(cyc)=1 
     If InRange(cyc,v,crushRange(4,move)) Or InProximity(cyc,v,5)
      Pop(cyc,Rnd(2,7),0)
      ProduceSound(p(v),sImpact(6),22050,0)
      ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1)
      limb=FindChild(p(cyc),"Hips")
      If crushName$(4,move)="Elbow Drop" Then limb=FindChild(p(cyc),"R_Arm")
      CreateSpurt(EntityX(limb,1),pY#(v)+3,EntityZ(limb,1),15,99)
      ScarArea(v,EntityX(limb,1),pY#(v),EntityZ(limb,1),10)
      GroundImpactChecks(v) : FindSmashes(v,0)
      pHealth(v)=pHealth(v)-(charStr(pChar(cyc))/20)
      pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
      pFoc(v)=cyc : pSting(cyc)=0
     EndIf
    EndIf
   Next
   If pSting(cyc)=1 Then ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1) : FindSmashes(cyc,0) : pHP(cyc)=0
  EndIf
  If pHP(cyc)=<0 And pAnimTim(cyc)>crushFall(4,move) Then ChangeAnim(cyc,0) 
  If pAnimTim(cyc)>crushLength(4,move) Then ChangeAnim(cyc,0)
  If weapBroad(weapType(pHolding(cyc))) Then ChangeAnim(cyc,54)
 EndIf 
 ;flying crush
 If pAnim(cyc)=53 Or pAnim(cyc)=55
  move=charCrush(pChar(cyc),5)
  FlyingAttack(cyc,53,crushSpeed#(5,move),crushMomentum(5,move),crushLength(5,move),crushFall(5,move),crushWeapon(5,move))
 EndIf
 ;weapon crush
 If pAnim(cyc)=54
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,56),5 : pSting(cyc)=1
  If pAnimTim(cyc)=12 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=20
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,30) And AttackViable(v)=2 And pSting(cyc)=1
     If InRange(cyc,v,8)
      Pop(cyc,Rnd(2,7),1)
      ProduceSound(p(v),weapSound(weapType(pHolding(cyc))),22050,0)
      ProduceSound(p(v),sImpact(6),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(v)+3,pZ#(v),15,99)
      ScarArea(v,0,0,0,10)
      If CountScars(v)=>4 Then ScarWeapon(pHolding(cyc),3)
      GroundImpactChecks(v) : FindSmashes(v,0)
      damage=weapDamage(weapType(pHolding(cyc)))+(charStr(pChar(cyc))/20)
      pHealth(v)=pHealth(v)-(damage/2)
      pHeat(v)=pHeat(v)-1 : pDT(v)=pDT(v)-50
      pFoc(v)=cyc : pSting(cyc)=0
      DQ(cyc,v)
     EndIf
    EndIf
   Next
  EndIf
  randy=Rnd(0,2)
  If randy=0 And pAnimTim(cyc)=30 And pSting(cyc)=0 Then DropWeapon(cyc,0)
  If pAnimTim(cyc)>35 Then ChangeAnim(cyc,0)
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;FLYING ATTACK PROCESS
Function FlyingAttack(cyc,anim,speed#,travel,length,style,weapon)
 ;initiation
 If pAnimTim(cyc)=0 
  Animate p(cyc),3,speed#,pSeq(cyc,anim),10
  agile#=Float(charAgl(pChar(cyc)))/100
  pPeak#(cyc)=1.4+agile#
  If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 And InProximity(cyc,pFoc(cyc),25) Then pPeak#(cyc)=pPeak#(cyc)-(pPeak#(cyc)/7)
  If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 And InProximity(cyc,pFoc(cyc),50)=0 Then pPeak#(cyc)=pPeak#(cyc)+(pPeak#(cyc)/7)
  If pPlatform(cyc)=0 Then pPeak#(cyc)=3.0
  pSting(cyc)=1 : pGravity#(cyc)=0.2 : pPeaked(cyc)=0
  pPlatform(cyc)=0 : pFlight#(cyc)=0 : pFlightA#(cyc)=pA#(cyc) 
  pExcusedWorld(cyc)=1 : pExcusedEdges(cyc)=1
  pExcusedPlays(cyc)=1 : pExcusedItems(cyc)=1
  If pY#(cyc)=pGround#(cyc) Then pY#(cyc)=pY#(cyc)+0.1
 EndIf
 If pAnimTim(cyc)=7 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.5,0.7))
 ;flight physics
 If pAnim(cyc)=55 Then leaper=4 Else leaper=7
 If pAnimTim(cyc)=>leaper And pY#(cyc)>pGround#(cyc)
  blocked=FindBlocks(pX#(cyc),pZ#(cyc),0)
  If pGravity#(cyc)<pPeak#(cyc) And pPeaked(cyc)=0 Then pGravity#(cyc)=pGravity#(cyc)+(pPeak#(cyc)/10)
  If pGravity#(cyc)=>pPeak#(cyc) Then pPeaked(cyc)=1
  If pPeaked(cyc)=1 Then pGravity#(cyc)=pGravity#(cyc)-((pPeak#(cyc)/10)+0.1)
  If blocked=1 And pGravity#(cyc)<-1.0 Then pGravity#(cyc)=-1.0 
  pY#(cyc)=pY#(cyc)+pGravity#(cyc)
  ;accuracy
  If style=1 Then range=25+(attackTravel#(5,charAttack(pChar(cyc),5))*2)
  If style=>2 Then range=15+(crushTravel#(5,charCrush(pChar(cyc),5))*2)
  If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),range) And blocked=0 And pFlight#(cyc)>0.3
   If style=1 Then decel=30-attackTravel#(5,charAttack(pChar(cyc),5))
   If style=>2 Then decel=30-crushTravel#(5,charCrush(pChar(cyc),5))
   pFlight#(cyc)=pFlight#(cyc)-(pPeak#(cyc)/decel)
  Else
   flyer#=pPeak#(cyc)-(pPeak#(cyc)/3)
   If pInjured(cyc)>0 Then flyer#=flyer#/2
   If pAnim(cyc)=55 Then flyer#=1.5
   If pFlight#(cyc)<flyer# Then pFlight#(cyc)=pFlight#(cyc)+(pPeak#(cyc)/10)
  EndIf 
  Advance(cyc,pFlightA#(cyc),pFlight#(cyc))
 EndIf
 ;tope momentum
 If pAnim(cyc)=55 And pAnimTim(cyc)=15 Then Pop(cyc,Rnd(2,7),0)
 If pAnim(cyc)=55 And pAnimTim(cyc)=<leaper And InsideRing(pX#(cyc),pZ#(cyc),-25) 
  Advance(cyc,pFlightA#(cyc),1.0)
 EndIf
 ;mid-air turning
 If pAnimTim(cyc)>5 And pAnimTim(cyc)<travel And pY#(cyc)>pGround#(cyc) And style=>2
  If cLeft(cyc)=1 Then pA#(cyc)=pA#(cyc)+5
  If cRight(cyc)=1 Then pA#(cyc)=pA#(cyc)-5
 EndIf
 ;find impacts
 If style=1 Then height#=attackImpact(5,charAttack(pChar(cyc),5)) Else height#=30
 If pY#(cyc)>pGround#(cyc) And pY#(cyc)<pGround#(cyc)+height# And pSting(cyc)=1
  For v=1 To no_plays
   ;versus standing
   If cyc<>v And InProximity(cyc,v,30) And AttackViable(v)=1
    If style=1 Then range=attackRange(5,charAttack(pChar(cyc),5)) Else range=8
    If InRange(cyc,v,range)
     Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,1)
     ProduceSound(p(v),sImpact(3),22050,0)
     ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
     CreateSpurt(pX#(v),pY#(v)+23,pZ#(v),15,99)
     If style=1 Then ScarLimb(v,1,10)
     ImpactChecks(v) : ChangeAnim(v,60)
     damage=charStr(pChar(cyc))/15
     pHealth(v)=pHealth(v)-damage : pHP(v)=0
     If style=1 Then pDT(v)=(275-pHealth(v))+(damage*10)
     If pSpecial(cyc)>0 And style=1 Then pHealth(v)=pHealth(v)-5 : pDT(v)=pDT(v)+50
     pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
     If style=1 And weapon>0 Then WeaponImpact(cyc,v)
     If style=1 Then pGravity#(cyc)=0 : pSting(cyc)=0
     If style=>2 Then pGravity#(cyc)=1.0 : pFlightA#(cyc)=pFlightA#(cyc)+Rnd(-10,10)
     pFlight#(cyc)=0.2 : pFoc(v)=cyc
    EndIf
   EndIf
   ;versus grounded
   If style=>2 Then range=15 Else range=10
   If cyc<>v And InProximity(cyc,v,range) And pY#(cyc)<pGround#(cyc)+7 And AttackViable(v)=2
    If InRange(cyc,v,crushRange(5,charCrush(pChar(cyc),5))) Or crushRange(5,charCrush(pChar(cyc),5))=0
     Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,1)
     ProduceSound(p(v),sImpact(6),22050,0)
     ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1)
     CreateSpurt(pX#(cyc),pY#(v)+3,pZ#(cyc),15,99)
     ScarArea(v,pX#(cyc),pY#(v),pZ#(cyc),10)
     damage=charStr(pChar(cyc))/10
     pHealth(v)=pHealth(v)-damage
     If style=>2 Then pDT(v)=(250-pHealth(v))+(damage*10)
     If pSpecial(cyc)>0 And style=>2 Then pHealth(v)=pHealth(v)-5 : pDT(v)=pDT(v)+50
     pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
     If style=>2 And weapon>0 Then WeaponImpact(cyc,v)
     GroundImpactChecks(v) : FindSmashes(v,0)
     If style=>2 Then pSting(cyc)=0
     pFoc(v)=cyc
    EndIf
   EndIf
  Next
 EndIf
 ;aftermath
 If pPeaked(cyc)=1
  pExcusedWorld(cyc)=0 : pExcusedEdges(cyc)=0
  If pY#(cyc)=>pGround#(cyc)+6 And pY#(cyc)=<pGround#(cyc)+10 Then FindSmashes(cyc,1)
  If pY#(cyc)<pGround#(cyc) 
   ProduceSound(p(cyc),sThud,22050,1)
   If style=>2 Then ProduceSound(p(cyc),sFall,22050,1)
   RiskInjury(cyc,0) : pHP(cyc)=pHP(cyc)-1
   FindSmashes(cyc,0) : DropWeapon(cyc,5)
   pY#(cyc)=pGround#(cyc)
  EndIf
  If pY#(cyc)=pGround#(cyc) And pAnimTim(cyc)<travel
   Advance(cyc,pFlightA#(cyc),pFlight#(cyc)/2)
  EndIf
  If pY#(cyc)=pGround#(cyc) 
   If pSting(cyc)=1 
    ProduceSound(p(cyc),sPain(3),GetVoice(cyc),1)
    pHealth(cyc)=pHealth(cyc)-5 : pHP(cyc)=0
    pDT(cyc)=200-pHealth(cyc) : pSting(cyc)=0
    RiskInjury(cyc,0) : Pop(0,Rnd(7,8),0)
   EndIf
   If (style=1 Or style=6) And pHP(cyc)=0
    If style=1 And attackFall(5,charAttack(pChar(cyc),5))=0 Then ChangeAnim(cyc,0) ;instant collapse 
    If (attackFall(5,charAttack(pChar(cyc),5))=1 Or style=6) And pAnimTim(cyc)>length-10 Then ChangeAnim(cyc,0) ;delayed collapse 
   EndIf
   If pAnimTim(cyc)>length 
    If style=1 Or style=6 Then ChangeAnim(cyc,0) ;standing
    If style=2 Then ChangeAnim(cyc,83) : SharpTransition(cyc,54,180) ;lying on front (forward)
    If style=3 Then ChangeAnim(cyc,83) ;lying on front (backward)
    If style=4 Then ChangeAnim(cyc,80) ;lying on back (normal)
    If style=5 Then ChangeAnim(cyc,80) : SharpTransition(cyc,54,180) ;lying on back (reversed) 
   EndIf
   pExcusedPlays(cyc)=0 : pExcusedItems(cyc)=0
   ForceOut(cyc,-1)
  EndIf
 EndIf
End Function

;STANDING IMPACT CHECKS
Function ImpactChecks(cyc)
 ;whip correction
 If pAnim(cyc)=4 Or (pAnim(cyc)=400 And pAnimTim(cyc)=>10) 
  Animate p(cyc),3,1,pSeq(cyc,61),0
  pA#(cyc)=pA#(cyc)+180
 EndIf
 ;momentum vulnerability 
 If pMomentum(cyc)>0 Or pAnim(cyc)=4 Then pHP(cyc)=0
 ;remove buckle slump
 If pPlatform(cyc)<0 Then pPlatform(cyc)=0
 ;apron vulnerability
 If matchCage>0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 Then pHP(cyc)=pHP(cyc)-1
 ;break standing grappler
 If pGrappling(cyc)>0 And pAnim(cyc)<200
  v=pGrappling(cyc)
  ChangeAnim(v,61) : pHurtA#(v)=pA#(v)
  If pStretched(v)>0 Then SharpTransition(cyc,61,0) : SharpTransition(v,61,0) : pHP(v)=0
  EndMove(cyc,v)
 EndIf
 ;break standing grapple victim
 If pGrappler(cyc)>0 And pAnim(cyc)<200 ;And pAnim(cyc)=103
  v=pGrappler(cyc)
  ChangeAnim(v,61) : pHurtA#(v)=pA#(v)
  If pStretched(cyc)>0 Then SharpTransition(cyc,61,0) : SharpTransition(v,61,0) : pHP(cyc)=0
  EndMove(v,cyc)
 EndIf 
 ;break ground grappler
 If pGrappling(cyc)>0 And pAnim(cyc)=>200
  v=pGrappling(cyc)
  ChangeAnim(v,GroundResponse(v))
  SharpTransition(cyc,61,0)
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.2
  EndMove(cyc,v)
 EndIf
 ;scare referee
 If pRole(cyc)=1 Then pAgenda(cyc)=0 : pNowhere(cyc)=99 : pRefAnger(cyc)=60
 ;activate bystander
 If pRole(cyc)=3 Then pOutTim(cyc)=2
End Function

;GROUND IMPACT CHECKS
Function GroundImpactChecks(cyc)
 ;break pinner
 If pPinning(cyc)>0 
  v=pPinning(cyc)
  ChangeAnim(v,80)
  If pAnim(cyc)=91 Then ChangeAnim(cyc,Rnd(68,71)) : pHP(cyc)=0
  If FindPinCount(v)>0 Then Pop(v,Rnd(2,7),1) 
  pPinner(v)=0 : pPinning(cyc)=0
 EndIf
 ;break pin victim
 If pPinner(cyc)>0 
  v=pPinner(cyc)
  If pAnim(v)=90 Or pAnim(v)=91 Then ChangeAnim(v,93)
  ChangeAnim(cyc,80)
  If FindPinCount(cyc)>0 Then Pop(cyc,Rnd(2,7),1)
  pPinner(v)=0 : pPinning(cyc)=0
 EndIf
 ;break ground grapple victim
 If pAnim(cyc)=202
  v=pGrappler(cyc)
  ChangeAnim(cyc,GroundResponse(cyc))
  EnforceBlocks(cyc)
  ChangeAnim(v,61) : SharpTransition(v,61,0)
  pHurtA#(v)=pA#(v)+180 : pStagger#(v)=0.2
  pDT(cyc)=pDT(cyc)+50
  EndMove(v,cyc)
 EndIf
 ;universal response
 If pAnim(cyc)<>68 And pAnim(cyc)<>69 And pAnim(cyc)<>70 And pAnim(cyc)<>71
  If (pAnim(cyc)<>62 And pAnim(cyc)<>63) Or pAnimTim(cyc)>8 
   ChangeAnim(cyc,GroundResponse(cyc))
  EndIf
 EndIf
End Function

;CLOCK INVADER
Function ClockInvader(cyc,v)
 ;preserve betrayal
 betrayed=0
 If matchInvader>0 
  If pChar(matchInvader)=charFriend(gamChar(slot)) Or pChar(matchInvader)=charManager(gamChar(slot)) Then betrayed=1
 EndIf
 ;find new intrusions
 If betrayed=0 And game=1 And matchState=3 And matchPlayer>0 And pRole(v)=0 And pEliminated(v)=0
  manage=0
  If pRole(cyc)=2 And pChar(cyc)=gamChar(slot) Then manage=1
  If pRole(cyc)=2 And pChar(cyc)=charManager(gamChar(slot)) And pChar(v)=gamChar(slot) Then manage=1
  If pRole(cyc)=3 Or manage=1
   If pTeam(v)=pTeam(matchPlayer) Then matchInvader=cyc ;hinder player
   If pTeam(v)<>pTeam(matchPlayer) Then matchInvader=-cyc ;benefit player
  EndIf
 EndIf
End Function

;CLOCK WEAPON USE
Function ClockWeapon(cyc,v,weapon)
 If game=1 And matchState=3 And matchRules>0 And pTeam(v)=2 And pRole(v)=0 And pHealth(v)<pHealthLimit(v)/2
  If pChar(cyc)=gamChar(slot) Or (cyc=0 And pChar(pFoc(v))=gamChar(slot)) Then matchWeapon=weapon
 EndIf
End Function