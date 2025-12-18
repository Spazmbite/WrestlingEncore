;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING ENCORE: ANIMATIONS ------------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;//////////////// LOAD ANIMATION SEQUENCES ///////////////////////
;-----------------------------------------------------------------
Function LoadSequences(cyc)
 ;source files
 pSeq(cyc,600)=LoadAnimSeq(p(cyc),"Models/Sequences/Gestures.3ds")
 pSeq(cyc,601)=LoadAnimSeq(p(cyc),"Models/Sequences/Movement.3ds")
 pSeq(cyc,602)=LoadAnimSeq(p(cyc),"Models/Sequences/Attacks.3ds")
 pSeq(cyc,603)=LoadAnimSeq(p(cyc),"Models/Sequences/Weapons.3ds")
 pSeq(cyc,610)=LoadAnimSeq(p(cyc),"Models/Sequences/Move_Execute01.3ds")
 pSeq(cyc,611)=LoadAnimSeq(p(cyc),"Models/Sequences/Move_Receive01.3ds")
 pSeq(cyc,612)=LoadAnimSeq(p(cyc),"Models/Sequences/Ground_Execute.3ds")
 pSeq(cyc,613)=LoadAnimSeq(p(cyc),"Models/Sequences/Ground_Receive.3ds")
 pSeq(cyc,614)=LoadAnimSeq(p(cyc),"Models/Sequences/Buckle_Execute.3ds")
 pSeq(cyc,615)=LoadAnimSeq(p(cyc),"Models/Sequences/Buckle_Receive.3ds")
 pSeq(cyc,616)=LoadAnimSeq(p(cyc),"Models/Sequences/Move_Execute02.3ds")
 pSeq(cyc,617)=LoadAnimSeq(p(cyc),"Models/Sequences/Move_Receive02.3ds")
 ;0. movement sequences 
 pSeq(cyc,1)=ExtractAnimSeq(p(cyc),260,290,pSeq(cyc,601)) ;normalizer
 LoadStanceSequence(cyc,2) ;stance
 LoadWalkSequence(cyc) ;movement
 pSeq(cyc,4)=ExtractAnimSeq(p(cyc),110,170,pSeq(cyc,601)) ;stroll
 pSeq(cyc,5)=ExtractAnimSeq(p(cyc),40,100,pSeq(cyc,601)) ;running
 pSeq(cyc,6)=ExtractAnimSeq(p(cyc),1100,1145,pSeq(cyc,601)) ;rope turn
 LoadTauntSequence(cyc,7)
 LoadTauntSequence(cyc,8)
 ;10. standard climbing
 pSeq(cyc,10)=ExtractAnimSeq(p(cyc),495,595,pSeq(cyc,601)) ;climb up to apron
 pSeq(cyc,11)=ExtractAnimSeq(p(cyc),370,485,pSeq(cyc,601)) ;climb through ropes
 pSeq(cyc,12)=ExtractAnimSeq(p(cyc),605,670,pSeq(cyc,601)) ;climb down from apron
 pSeq(cyc,13)=ExtractAnimSeq(p(cyc),3330,3360,pSeq(cyc,601)) ;climb onto wall
 pSeq(cyc,14)=ExtractAnimSeq(p(cyc),3370,3400,pSeq(cyc,601)) ;wall hang from wall
 pSeq(cyc,15)=ExtractAnimSeq(p(cyc),3410,3470,pSeq(cyc,601)) ;climb wall
 pSeq(cyc,16)=ExtractAnimSeq(p(cyc),3480,3630,pSeq(cyc,601)) ;climb over cage
 ;20. turnbuckle climbing
 pSeq(cyc,20)=ExtractAnimSeq(p(cyc),680,770,pSeq(cyc,601)) ;climb turnbuckle from apron (right)
 pSeq(cyc,21)=ExtractAnimSeq(p(cyc),890,980,pSeq(cyc,601)) ;climb turnbuckle from apron (left)
 pSeq(cyc,22)=ExtractAnimSeq(p(cyc),990,1090,pSeq(cyc,601)) ;climb turnbuckle from inside
 pSeq(cyc,23)=ExtractAnimSeq(p(cyc),780,810,pSeq(cyc,601)) ;turnbuckle stance
 pSeq(cyc,24)=ExtractAnimSeq(p(cyc),820,880,pSeq(cyc,601)) ;turnbuckle movement
 pSeq(cyc,25)=ExtractAnimSeq(p(cyc),2880,2990,pSeq(cyc,601)) ;climb onto item/railing
 pSeq(cyc,26)=ExtractAnimSeq(p(cyc),3000,3100,pSeq(cyc,601)) ;climb off item/railing
 ;30. item interaction
 pSeq(cyc,30)=ExtractAnimSeq(p(cyc),2770,2800,pSeq(cyc,601)) ;carry stance
 pSeq(cyc,31)=ExtractAnimSeq(p(cyc),2810,2870,pSeq(cyc,601)) ;carry movement
 pSeq(cyc,32)=ExtractAnimSeq(p(cyc),1230,1295,pSeq(cyc,602)) ;throw weapon
 pSeq(cyc,33)=ExtractAnimSeq(p(cyc),1305,1360,pSeq(cyc,602)) ;lying pick up
 ;40. standing attacks
 LoadAttackSequences(cyc)
 ;50. ground attacks
 LoadCrushSequences(cyc)
 ;60. hurting sequences 
 pSeq(cyc,60)=ExtractAnimSeq(p(cyc),220,280,pSeq(cyc,602)) ;upper hurt
 pSeq(cyc,61)=ExtractAnimSeq(p(cyc),290,350,pSeq(cyc,602)) ;lower hurt
 pSeq(cyc,62)=ExtractAnimSeq(p(cyc),1765,1820,pSeq(cyc,601)) ;lying hurt (on back)
 pSeq(cyc,63)=ExtractAnimSeq(p(cyc),1835,1900,pSeq(cyc,601)) ;lying hurt (on front)
 pSeq(cyc,64)=ExtractAnimSeq(p(cyc),3110,3140,pSeq(cyc,601)) ;tired stance
 pSeq(cyc,65)=ExtractAnimSeq(p(cyc),3150,3210,pSeq(cyc,601)) ;tired movement
 pSeq(cyc,66)=ExtractAnimSeq(p(cyc),3220,3250,pSeq(cyc,601)) ;dazed stance
 pSeq(cyc,67)=ExtractAnimSeq(p(cyc),3260,3320,pSeq(cyc,601)) ;dazed movement
 pSeq(cyc,68)=ExtractAnimSeq(p(cyc),4180,4275,pSeq(cyc,601)) ;stumble onto back
 pSeq(cyc,69)=ExtractAnimSeq(p(cyc),4285,4390,pSeq(cyc,601)) ;stumble onto front
 ;70. falling sequences 
 pSeq(cyc,70)=ExtractAnimSeq(p(cyc),1385,1465,pSeq(cyc,601)) ;fall onto back
 pSeq(cyc,71)=ExtractAnimSeq(p(cyc),2680,2760,pSeq(cyc,601)) ;fall onto front
 pSeq(cyc,72)=ExtractAnimSeq(p(cyc),1910,2040,pSeq(cyc,601)) ;fall out of ring
 pSeq(cyc,73)=ExtractAnimSeq(p(cyc),2050,2180,pSeq(cyc,601)) ;fall out onto apron
 pSeq(cyc,74)=ExtractAnimSeq(p(cyc),2460,2500,pSeq(cyc,601)) ;thrown out onto back
 pSeq(cyc,75)=ExtractAnimSeq(p(cyc),2570,2610,pSeq(cyc,601)) ;thrown out onto front
 pSeq(cyc,76)=ExtractAnimSeq(p(cyc),2510,2560,pSeq(cyc,601)) ;land on back
 pSeq(cyc,77)=ExtractAnimSeq(p(cyc),2620,2670,pSeq(cyc,601)) ;land on front
 ;80. lying sequences  
 pSeq(cyc,80)=ExtractAnimSeq(p(cyc),1475,1505,pSeq(cyc,601)) ;lying on back
 pSeq(cyc,81)=ExtractAnimSeq(p(cyc),1515,1605,pSeq(cyc,601)) ;get up off back
 pSeq(cyc,82)=ExtractAnimSeq(p(cyc),1685,1750,pSeq(cyc,601)) ;turn onto front
 pSeq(cyc,83)=ExtractAnimSeq(p(cyc),1270,1300,pSeq(cyc,601)) ;lying on front
 pSeq(cyc,84)=ExtractAnimSeq(p(cyc),1310,1375,pSeq(cyc,601)) ;get up off front
 pSeq(cyc,85)=ExtractAnimSeq(p(cyc),1615,1675,pSeq(cyc,601)) ;crawling
 ;90. pinning & refereeing
 pSeq(cyc,90)=ExtractAnimSeq(p(cyc),2460,2500,pSeq(cyc,600)) ;pin initiate
 pSeq(cyc,91)=ExtractAnimSeq(p(cyc),2510,2540,pSeq(cyc,600)) ;pin loop
 pSeq(cyc,92)=ExtractAnimSeq(p(cyc),2550,2580,pSeq(cyc,600)) ;hook pin loop
 pSeq(cyc,93)=ExtractAnimSeq(p(cyc),2630,2660,pSeq(cyc,600)) ;reverse hook pin loop
 pSeq(cyc,94)=ExtractAnimSeq(p(cyc),2590,2620,pSeq(cyc,600)) ;hook pinned loop
 pSeq(cyc,95)=ExtractAnimSeq(p(cyc),2670,2900,pSeq(cyc,600)) ;exasperated pin loss
 pSeq(cyc,96)=ExtractAnimSeq(p(cyc),2910,2950,pSeq(cyc,600)) ;ref kneel down
 pSeq(cyc,97)=ExtractAnimSeq(p(cyc),2950,3070,pSeq(cyc,600)) ;ref examination
 pSeq(cyc,98)=ExtractAnimSeq(p(cyc),3080,3130,pSeq(cyc,600)) ;ref count
 pSeq(cyc,99)=ExtractAnimSeq(p(cyc),3140,3275,pSeq(cyc,600)) ;ref declare
 ;100. standing grapples
 LoadMoveSequences(cyc)
 ;300. head grappling
 LoadHeadSequences(cyc)
 ;400. leg grappling
 LoadLegSequences(cyc)
 ;500. buckle grappling
 LoadBuckleSequences(cyc)
 ;550+ misc
 pSeq(cyc,550)=ExtractAnimSeq(p(cyc),370,400,pSeq(cyc,603)) ;weapon stance
 pSeq(cyc,551)=ExtractAnimSeq(p(cyc),410,470,pSeq(cyc,603)) ;weapon movement
 pSeq(cyc,552)=ExtractAnimSeq(p(cyc),480,540,pSeq(cyc,603)) ;weapon run
 pSeq(cyc,560)=ExtractAnimSeq(p(cyc),1710,1770,pSeq(cyc,600)) ;hands-free promo
 pSeq(cyc,561)=ExtractAnimSeq(p(cyc),2170,2230,pSeq(cyc,600)) ;microphone speech
 pSeq(cyc,562)=ExtractAnimSeq(p(cyc),4200,4320,pSeq(cyc,600)) ;high ref examination
End Function

;-----------------------------------------------------------------
;//////////////////// LOAD STANCE SEQUENCE ////////////////////////
;-----------------------------------------------------------------
Function LoadStanceSequence(cyc,seq)
 move=charStance(pChar(cyc),1)
 If (pRole(cyc)=>1 And pRole(cyc)=<2) Or seq=1
  If move=5 Then move=1
  If move=6 Then move=3
  If move=7 Then move=2
 EndIf
 If move=1 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),3840,3880,pSeq(cyc,601)) ;tall
 If move=2 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),0,30,pSeq(cyc,600)) ;straight
 If move=3 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),260,290,pSeq(cyc,601)) ;normal
 If move=4 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),3890,3930,pSeq(cyc,601)) ;pensive
 If move=5 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),530,560,pSeq(cyc,600)) ;boxing
 If move=6 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),710,740,pSeq(cyc,600)) ;martial arts
 If move=7 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),3790,3830,pSeq(cyc,601)) ;technical
 If move=8 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),4010,4050,pSeq(cyc,601)) ;relaxed
 If move=9 Then pSeq(cyc,seq)=ExtractAnimSeq(p(cyc),4060,4100,pSeq(cyc,601)) ;feminine
End Function

;-----------------------------------------------------------------
;/////////////////// LOAD MOVEMENT SEQUENCE //////////////////////
;-----------------------------------------------------------------
Function LoadWalkSequence(cyc)
 move=charStance(pChar(cyc),2)
 If pRole(cyc)=>1 And pRole(cyc)=<2 And move=>4 And move=<5 Then move=2
 If move=1 Then pSeq(cyc,3)=ExtractAnimSeq(p(cyc),110,170,pSeq(cyc,601)) ;stroll
 If move=2 Then pSeq(cyc,3)=ExtractAnimSeq(p(cyc),300,360,pSeq(cyc,601)) ;normal
 If move=3 Then pSeq(cyc,3)=ExtractAnimSeq(p(cyc),3940,4000,pSeq(cyc,601)) ;pensive 
 If move=4 Then pSeq(cyc,3)=ExtractAnimSeq(p(cyc),3640,3710,pSeq(cyc,601)) ;boxing
 If move=5 Then pSeq(cyc,3)=ExtractAnimSeq(p(cyc),3720,3780,pSeq(cyc,601)) ;martial arts
 If move=6 Then pSeq(cyc,3)=ExtractAnimSeq(p(cyc),4110,4170,pSeq(cyc,601)) ;feminine
End Function

;-----------------------------------------------------------------
;//////////////////// LOAD TAUNT SEQUENCE ////////////////////////
;-----------------------------------------------------------------
Function LoadTauntSequence(cyc,anim)
 ;ascertain gesture
 move=charTaunt(pChar(cyc),anim-6)
 If pRole(cyc)=1
  randy=Rnd(1,8) 
  If randy=1 Then pRefGesture(cyc)=11
  If randy=2 Then pRefGesture(cyc)=10
  If randy=3 Then pRefGesture(cyc)=12
  If randy=4 Then pRefGesture(cyc)=17
  If randy=5 Then pRefGesture(cyc)=26
  If randy=6 Then pRefGesture(cyc)=39
  If randy=7 Then pRefGesture(cyc)=40
  If randy=8 Then pRefGesture(cyc)=50
  move=pRefGesture(cyc)
 EndIf
 ;load sequence
 If move=1 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3840,3880,pSeq(cyc,601)) ;tall stance
 If move=2 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3890,3930,pSeq(cyc,601)) ;pensive stance
 If move=3 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),530,560,pSeq(cyc,600)) ;boxing stance
 If move=4 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),710,740,pSeq(cyc,600)) ;martial arts stance
 If move=5 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3790,3830,pSeq(cyc,601)) ;technical stance
 If move=6 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4010,4050,pSeq(cyc,601)) ;relaxed stance
 If move=7 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4060,4100,pSeq(cyc,601)) ;feminine stance
 If move=8 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1640,1700,pSeq(cyc,600)) ;zombie walk
 If move=9 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2390,2450,pSeq(cyc,600)) ;searching
 If move=10 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1780,1810,pSeq(cyc,600)) ;exasperated
 If move=11 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2240,2270,pSeq(cyc,600)) ;point to side
 If move=12 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1710,1770,pSeq(cyc,600)) ;explaining
 If move=13 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1980,2040,pSeq(cyc,600)) ;shocked
 If move=14 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2050,2080,pSeq(cyc,600)) ;hands up
 If move=15 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2090,2120,pSeq(cyc,600)) ;waving
 If move=16 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1860,1890,pSeq(cyc,600)) ;hands on hips
 If move=17 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1900,1930,pSeq(cyc,600)) ;clasped hands
 If move=18 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1940,1970,pSeq(cyc,600)) ;hands behind back
 If move=19 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1820,1850,pSeq(cyc,600)) ;arms folded
 If move=20 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2280,2310,pSeq(cyc,600)) ;praying
 If move=21 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2130,2160,pSeq(cyc,600)) ;salute
 If move=22 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),2320,2380,pSeq(cyc,600)) ;celebrating
 If move=23 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),830,890,pSeq(cyc,600)) ;dancing 
 If move=24 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),1140,1170,pSeq(cyc,600)) ;shake hands
 If move=25 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3140,3200,pSeq(cyc,600)) ;rule out
 If move=26 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3215,3275,pSeq(cyc,600)) ;point at ground 
 If move=27 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3550,3610,pSeq(cyc,600)) ;raised arms
 If move=28 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3620,3680,pSeq(cyc,600)) ;outstretched arms
 If move=29 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3690,3750,pSeq(cyc,600)) ;one raised arm
 If move=30 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3760,3820,pSeq(cyc,600)) ;side arm pump
 If move=31 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3830,3890,pSeq(cyc,600)) ;pad chest
 If move=32 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3900,3960,pSeq(cyc,600)) ;beat chest 
 If move=33 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),3970,4010,pSeq(cyc,600)) ;two birds!
 If move=34 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4020,4060,pSeq(cyc,600)) ;V signs!
 If move=35 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4080,4190,pSeq(cyc,600)) ;cupped ear
 If move=36 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4330,4390,pSeq(cyc,600)) ;bicep flex
 If move=37 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4400,4440,pSeq(cyc,600)) ;come on
 If move=38 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4550,4590,pSeq(cyc,600)) ;come here
 If move=39 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4450,4490,pSeq(cyc,600)) ;point forward
 If move=40 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4500,4540,pSeq(cyc,600)) ;shake finger
 If move=41 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4600,4660,pSeq(cyc,600)) ;overhead arm pump
 If move=42 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4670,4730,pSeq(cyc,600)) ;overhead press
 If move=43 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4740,4820,pSeq(cyc,600)) ;crotch chop
 If move=44 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4830,4890,pSeq(cyc,600)) ;X sign
 If move=45 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4900,4960,pSeq(cyc,600)) ;diamond sign
 If move=46 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),4970,5010,pSeq(cyc,600)) ;throat slice
 If move=47 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),5020,5080,pSeq(cyc,600)) ;wrist fiddle
 If move=48 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),5090,5330,pSeq(cyc,600)) ;contemplation
 If move=49 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),5340,5400,pSeq(cyc,600)) ;shake fist
 If move=50 Then pSeq(cyc,anim)=ExtractAnimSeq(p(cyc),5410,5470,pSeq(cyc,600)) ;calm down
End Function

;----------------------------------------------------------------
;////////////////// MANAGE ANIMATIONS ///////////////////////////
;----------------------------------------------------------------
Function Animations(cyc)
 ;---------- 0. MOVEMENT STATES ----------
 ;resting
 If pAnim(cyc)=0
  move=charStance(pChar(cyc),1)
  anim=2 : speeder#=Rnd(stanceSpeed#(move),stanceSpeed#(move)*2)
  If (pHealth(cyc)=<optTired Or pInjured(cyc)>0) And pSpecial(cyc)=0 Then anim=64 : speeder#=Rnd(0.3,0.5)
  If pDazed(cyc)>0 Then anim=66 : speeder#=Rnd(0.3,0.5)
  If weapBroad(weapType(pHolding(cyc))) Then anim=550 : speeder#=Rnd(0.1,0.4)
  If matchState=2 And speaker=cyc 
   anim=560 : speeder#=Rnd(0.1,0.4)
   If weapType(pHolding(cyc))=5 Then anim=561 : speeder#=Rnd(0.2,0.5) 
  EndIf
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   Animate p(cyc),1,speeder#,pSeq(cyc,anim),10
   pState(cyc)=anim
  EndIf
  EndMove(cyc,v)
 EndIf
 ;turning
 If pAnim(cyc)=1
  anim=3
  If (pHealth(cyc)=<optTired Or pInjured(cyc)>0) And pSpecial(cyc)=0 Then anim=65
  If pDazed(cyc)>0 Then anim=67
  If weapBroad(weapType(pHolding(cyc))) Then anim=551
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   Animate p(cyc),1,2.0,pSeq(cyc,anim),5
   pState(cyc)=anim
  EndIf
  If pAnimTim(cyc)>5 
   If pA#(cyc)=pTA#(cyc) Or DirPressed(cyc)=1 Or (cRun(cyc)=1 And pDazed(cyc)=0) Or (DirPressed(cyc)=0 And pDazed(cyc)>0)  
    ChangeAnim(cyc,0)
   EndIf
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;walking
 If pAnim(cyc)=2
  anim=4
  If pFoc(cyc)>0 Then anim=3
  If matchState=1 And pRole(cyc)=2 Then anim=4
  If (pHealth(cyc)=<optTired Or pInjured(cyc)>0) And pSpecial(cyc)=0 Then anim=65
  If pDazed(cyc)>0 Then anim=67
  If weapBroad(weapType(pHolding(cyc))) Then anim=551
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)   
   Animate p(cyc),1,pDefSpeed#(cyc)*2,pSeq(cyc,anim),5
   pState(cyc)=anim
  EndIf
  ApplyMovement(cyc,pSpeed#(cyc))
  If pAnimTim(cyc)>5
   If DirPressed(cyc)=0 Or (cRun(cyc)=1 And pDazed(cyc)=0) Then ChangeAnim(cyc,0)
  EndIf 
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;running
 If pAnim(cyc)=3
  If pOldAnim(cyc)=4 Then transition=0 Else transition=5
  If weapBroad(weapType(pHolding(cyc))) Then anim=552 Else anim=5
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,pDefSpeed#(cyc)*2,pSeq(cyc,anim),transition
  If pMomentum(cyc)>0 Then speeder#=pDefSpeed#(cyc)*2 Else speeder#=pSpeed#(cyc)*2
  If DirPressed(cyc) And pMomentum(cyc)=0
   ApplyMovement(cyc,pSpeed#(cyc)*2)
  Else
   If pMomentum(cyc)>0 Then angle#=pA#(cyc) Else angle#=pTA#(cyc)
   Advance(cyc,angle#,pDefSpeed#(cyc)*2)
  EndIf
  randy=Rnd(0,500)
  If Isolated(cyc,15)=0 Then randy=Rnd(0,50)
  If randy=0 And pMomentum(cyc)>10 Then pMomentum(cyc)=10
  If pAnimTim(cyc)>5 
   If (cRun(cyc)=0 Or pDazed(cyc)>0) And pMomentum(cyc)=0 Then ChangeAnim(cyc,0)
   RopeBounce(cyc)
   If pMomentum(cyc)>0 And pAnim(cyc)<>400 Then FindRunningBlocks(cyc)
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;rope turn
 If pAnim(cyc)=4
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,6),3
  If pAnimTim(cyc)=1 Then RopeSound(p(cyc),1)
  FindRopeImpacts(cyc)
  If pAnimTim(cyc)>21 
   ChangeAnim(cyc,3) : SharpTransition(cyc,5,180) 
   pTA#(cyc)=pA#(cyc) : pExcusedWorld(cyc)=0
   If pMomentum(cyc)<10 Then pMomentum(cyc)=10
   If pHP(cyc)=<0 Then ChangeAnim(cyc,Rnd(70,71)) : pA#(cyc)=pA#(cyc)+180
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;taunt
 If pAnim(cyc)=5
  move=charTaunt(pChar(cyc),1) : anim=7
  If pRole(cyc)=1 Then move=pRefGesture(cyc)
  If pSpecial(cyc)>0 Then move=charTaunt(pChar(cyc),2) : anim=8
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,tauntSpeed#(move),pSeq(cyc,anim),10 
  If pAnimTim(cyc)=10 And pRole(cyc)=0 And matchState<>2
   Pop(cyc,Rnd(1,9),0)
   If LegalMan(cyc) Then pHeat(cyc)=pHeat(cyc)+Rnd(0,3)
  EndIf
  abort=0
  If pAnimTim(cyc)>20 Or pAnimTim(cyc)>tauntLength(move)/2
   If pControl(cyc)>0 And cTaunt(cyc)=0 And (DirPressed(cyc)=1 Or ActionPressed(cyc)=1) Then abort=1
   If pControl(cyc)=0 And (Isolated(cyc,15)=0 Or pAgenda(cyc)=10) Then abort=1 
  EndIf
  If (pAnimTim(cyc)>tauntLength(move) And (cTaunt(cyc)=0 Or tauntLoop(move)=0)) Or abort=1 
   If pOldAnim(cyc)=23 Or pOldAnim(cyc)=24 Then anim=23 Else anim=0
   ChangeAnim(cyc,anim)
  EndIf
  pSpeaking(cyc)=1
 EndIf
 ;special ignition
 If pAnim(cyc)=6
  move=charTaunt(pChar(cyc),2)
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,tauntSpeed#(move),pSeq(cyc,8),10
  If pAnimTim(cyc)=10
   Pop(cyc,2,1) : Pop(cyc,10,1)
   If arenaCrowd=0 Then ProduceSound(p(cyc),sCrowd(0),0,0) 
   pSpecial(cyc)=800
  EndIf
  If pAnimTim(cyc)>tauntLength(move)
   If pOldAnim(cyc)=23 Or pOldAnim(cyc)=24 Then anim=23 Else anim=0
   ChangeAnim(cyc,anim)
  EndIf
  pSpeaking(cyc)=1
 EndIf
 ;---------- 10. STANDARD CLIMBING ----------
 ;climb up to apron
 If pAnim(cyc)=10
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,10),5
  If pAnimTim(cyc)=7 Or pAnimTim(cyc)=23 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=17 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=40 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=23 Then ProduceSound(p(cyc),sFall,22050,0.4)
  If pAnimTim(cyc)=40 Then DropItem(cyc)
  If pAnimTim(cyc)>51 
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0) : pY#(cyc)=wStage# 
   pExcusedWorld(cyc)=0 : pFriction(cyc)=20 : pStepTim(cyc)=99
  EndIf
  If pCarrying(cyc)>0 Then BindItem(cyc)
 EndIf
 ;climb in/out through ropes
 If pAnim(cyc)=11 Or pAnim(cyc)=12
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.7,pSeq(cyc,11),5
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sRopes,44100,0)
  If pAnimTim(cyc)=8 Then RopeSound(p(cyc),0)
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=16 Or pAnimTim(cyc)=37 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=25 And pAnim(cyc)=12 And pCarrying(cyc)>0 Then DropItem(cyc) : pNowhere(cyc)=99
  If pAnimTim(cyc)>42 
   If pAnim(cyc)=12 Then pFriction(cyc)=20
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   ConfineToRing(cyc) : pExcusedWorld(cyc)=0
  EndIf
  If pCarrying(cyc)>0 Then BindItem(cyc)
 EndIf
 ;climb down from apron
 If pAnim(cyc)=13
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.5,pSeq(cyc,12),5
  offset#=Float(charHeight(pChar(cyc)))/10
  If pY#(cyc)<wStage#+offset# And offset#=>1.0 Then pY#(cyc)=pY#(cyc)+0.2
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=16 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=23 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=25 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=33 Then ProduceSound(p(cyc),sThud,22050,0.6) : FindSmashes(cyc,0)
  If pAnimTim(cyc)>44
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   pY#(cyc)=wGround# : pExcusedWorld(cyc)=0
  EndIf
  If pCarrying(cyc)>0 Then BindItem(cyc)
 EndIf
 ;climb onto wall
 If pAnim(cyc)=14
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,13),5
  If pAnimTim(cyc)>5 Then pY#(cyc)=pY#(cyc)+0.3
  If pAnimTim(cyc)=7 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=15
   If pPlatform(cyc)=100 Then ProduceSound(p(cyc),sItem,22050,0)
   If pPlatform(cyc)=>101 And pPlatform(cyc)=<114 Then ProduceSound(p(cyc),sImpactMetal,20000,Rnd(0.2,0.8))
  EndIf
  If pAnimTim(cyc)>15 Then ChangeAnim(cyc,15)
 EndIf
 ;hanging on wall
 If pAnim(cyc)=15
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(0.2,0.4),pSeq(cyc,14),5
  If pAnimTim(cyc)>5 And DirPressed(cyc) Then ChangeAnim(cyc,16)
  RiskWallFall(cyc,8)
 EndIf
 ;climbing wall
 If pAnim(cyc)=16
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,1.5,pSeq(cyc,15),5
  speeder#=0.1
  If matchState=3 And pRole(cyc)=0 And matchCage>0 And matchOTT=1 And pPlatform(cyc)=>101 And pPlatform(cyc)=<104 Then speeder#=0.06
  If pAnimTim(cyc)=>3 And pAnimTim(cyc)=<17 Then speeder#=speeder#*3 
  If pAnimTim(cyc)=>24 And pAnimTim(cyc)=<37 Then speeder#=speeder#*3
  ClimbingMovement(cyc,speeder#)
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 
   If pPlatform(cyc)=100 Then ProduceSound(p(cyc),sItem,22050,0)
   If pPlatform(cyc)=>101 And pPlatform(cyc)=<114  
    ProduceSound(p(cyc),sImpactMetal,20000,Rnd(0.1,0.5))
    cageTim(NearestCage(cyc))=9
   EndIf
  EndIf
  If DirPressed(cyc)=0 Then ChangeAnim(cyc,15)
  If pY#(cyc)>50 And pPlatform(cyc)=>101 And pPlatform(cyc)=<114 Then ChangeAnim(cyc,17)
  If pY#(cyc)<pGround#(cyc)+3 Then ChangeAnim(cyc,18)
  If pAnimTim(cyc)>40 Then pAnimTim(cyc)=0
  RiskWallFall(cyc,4)
 EndIf
 ;climb over wall
 If pAnim(cyc)=17
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,16),5
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=42 Or pAnimTim(cyc)=52 Or pAnimTim(cyc)=65 
   ProduceSound(p(cyc),sImpactMetal,20000,Rnd(0.1,0.4))
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=60 Or pAnimTim(cyc)=75
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  EndIf
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=40 Or pAnimTim(cyc)=55 Then cageTim(NearestCage(cyc))=9
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=50 
   If matchState=3 And matchCage>0 And matchOTT=1 And LegalMan(cyc) Then Pop(cyc,Rnd(2,7),1)
  EndIf
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)>65 Then pY#(cyc)=pY#(cyc)-0.1
  If pAnimTim(cyc)>80 
   ChangeAnim(cyc,15) : SharpTransition(cyc,14,180)
   If pPlatform(cyc)=>101 And pPlatform(cyc)=<104 Then pPlatform(cyc)=pPlatform(cyc)+10 Else pPlatform(cyc)=pPlatform(cyc)-10 
  EndIf
 EndIf
 ;climb off wall
 If pAnim(cyc)=18
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,-2.0,pSeq(cyc,13),5
  If pY#(cyc)>pGround#(cyc) Then pY#(cyc)=pY#(cyc)-0.25
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sThud,22050,0) : pStepTim(cyc)=99
  If pAnimTim(cyc)>15 
   ChangeAnim(cyc,0)
   If pPlatform(cyc)=>101 And pPlatform(cyc)=<104 Then pPlatform(cyc)=pPlatform(cyc)-100 Else pPlatform(cyc)=0
   pY#(cyc)=pGround#(cyc)
   pExcusedWorld(cyc)=0
  EndIf
 EndIf
 ;---------- 20. TURNBUCKLE CLIMBING ----------
 ;climb up turnbuckle from apron (right)
 If pAnim(cyc)=20
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.2,pSeq(cyc,20),5
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=27 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>43 
   ChangeAnim(cyc,23) : SharpTransition(cyc,23,0)
   pY#(cyc)=wBuckle# : pExcusedWorld(cyc)=0 : pStepTim(cyc)=99
  EndIf
 EndIf
 ;climb up turnbuckle from apron (left)
 If pAnim(cyc)=21
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.2,pSeq(cyc,21),5
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=27 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>43
   ChangeAnim(cyc,23) : SharpTransition(cyc,23,0)
   pY#(cyc)=wBuckle# : pExcusedWorld(cyc)=0 : pStepTim(cyc)=99
  EndIf
 EndIf 
 ;climb up turnbuckle from inside
 If pAnim(cyc)=22
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0
   Animate p(cyc),3,2.2,pSeq(cyc,22),5
   RotateEntity p(cyc),0,pA#(cyc),0
   MoveEntity p(cyc),0,0,-4
   pX#(cyc)=EntityX(p(cyc),1) : pZ#(cyc)=EntityZ(p(cyc),1)
  EndIf
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=27 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>45
   ChangeAnim(cyc,23) : SharpTransition(cyc,23,0)
   pY#(cyc)=wBuckle# : pExcusedWorld(cyc)=0 : pStepTim(cyc)=99
  EndIf
 EndIf  
 ;turnbuckle stance
 If pAnim(cyc)=23
  pExcusedWorld(cyc)=1
  If pOldAnim(cyc)=5 Or pOldAnim(cyc)=6 Then transition=10 Else transition=5
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(0.1,0.4),pSeq(cyc,23),transition
  If DirPressed(cyc) Then ChangeAnim(cyc,24)
  If cTaunt(cyc)=1 And pAnimTim(cyc)>5 
   If pSpecial(cyc)=0 Then ChangeAnim(cyc,5)
   If pSpecial(cyc)>0 Then ChangeAnim(cyc,6)
  EndIf
 EndIf
 ;turnbuckle movement
 If pAnim(cyc)=24
  If pOldAnim(cyc)=5 Or pOldAnim(cyc)=6 Then transition=10 Else transition=5
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,2.0,pSeq(cyc,24),transition
  ApplyMovement(cyc,0)
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 And (pA#(cyc)=pTA#(cyc) Or pFoc(cyc)=0) 
   ChangeAnim(cyc,23)
  EndIf
  If cTaunt(cyc)=1
   If pSpecial(cyc)=0 Then ChangeAnim(cyc,5)
   If pSpecial(cyc)>0 Then ChangeAnim(cyc,6)
  EndIf 
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;climb down from turnbuckle to apron (right)
 If pAnim(cyc)=25
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 
   ReverseTransition(cyc,20,2.2,-19,-13)
   pY#(cyc)=wStage#
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=27 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sThud,22050,0.2)
  If pAnimTim(cyc)>41
   Animate p(cyc),1,1,pSeq(cyc,1),0
   pExcusedWorld(cyc)=0 : ChangeAnim(cyc,0) ;: pStepTim(cyc)=99
  EndIf
 EndIf
 ;climb down from turnbuckle to apron (left)
 If pAnim(cyc)=26
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 
   ReverseTransition(cyc,21,2.2,19,-13)
   pY#(cyc)=wStage#
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=27 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sThud,22050,0.2)
  If pAnimTim(cyc)>41
   Animate p(cyc),1,1,pSeq(cyc,1),0
   pExcusedWorld(cyc)=0 : ChangeAnim(cyc,0) ;: pStepTim(cyc)=99
  EndIf
 EndIf
 ;climb down from turnbuckle to inside
 If pAnim(cyc)=27
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 
   ReverseTransition(cyc,22,2.2,0,-30)
   pY#(cyc)=wStage#
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=27 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sThud,22050,0.2)
  If pAnimTim(cyc)>45 
   Animate p(cyc),1,1,pSeq(cyc,1),0
   pExcusedWorld(cyc)=0 : ChangeAnim(cyc,0) ;: pStepTim(cyc)=99
  EndIf
 EndIf 
 ;climb onto item
 If pAnim(cyc)=28
  pExcusedWorld(cyc)=1 : pExcusedItems(cyc)=1 : pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.5,pSeq(cyc,25),5
  If pAnimTim(cyc)>8 
   If pY#(cyc)<pGround#(cyc)+pPlatY#(cyc) Then pY#(cyc)=pY#(cyc)+(pPlatY#(cyc)/25)
  EndIf
  If pAnimTim(cyc)>15
   If pX#(cyc)<pPlatX#(cyc) Then pX#(cyc)=pX#(cyc)+0.45
   If pX#(cyc)>pPlatX#(cyc) Then pX#(cyc)=pX#(cyc)-0.45
   If pZ#(cyc)<pPlatZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+0.45
   If pZ#(cyc)>pPlatZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-0.45
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=36 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=11 Or pAnimTim(cyc)=31 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=21 
   ProduceSound(p(cyc),sThud,22050,0.5) : pStepTim(cyc)=99
   If pPlatform(cyc)=9 Then ProduceSound(p(cyc),sItem,22050,0.5)
   If pPlatform(cyc)=>10 Then ProduceSound(p(cyc),sItem,22050,0)
  EndIf
  If pAnimTim(cyc)>44
   If pPlatform(cyc)=9 Then pX#(cyc)=pPlatX#(cyc) : pZ#(cyc)=pPlatZ#(cyc)
   ChangeAnim(cyc,0)
  EndIf
  If pPlatform(cyc)=>10 And pAnimTim(cyc)>10
   v=pPlatform(cyc)-10
   If iState(v)=1 Or iCarrier(v)>0 Then pHP(cyc)=0 ;immediate fall down
  EndIf
 EndIf
 ;climb off item
 If pAnim(cyc)=29
  pExcusedWorld(cyc)=0 : pExcusedItems(cyc)=1 : pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,2.2,pSeq(cyc,26),5 : pGravity#(cyc)=1.5
  If pAnimTim(cyc)=4 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=6 Then ProduceSound(p(cyc),sSwing,22050,0.3)
  If pAnimTim(cyc)=>8 And pAnimTim(cyc)=<100
   If pPlatform(cyc)=9 Then mover#=0.5 Else mover#=0.9
   If pAnimTim(cyc)=>90 Then mover#=mover#/2
   Advance(cyc,pA#(cyc),mover#)
  EndIf
  If pAnimTim(cyc)=>10
   If pGravity#(cyc)>-3.0 Then pGravity#(cyc)=pGravity#(cyc)-0.2 
   If pY#(cyc)>pGround#(cyc) Then pY#(cyc)=pY#(cyc)+pGravity#(cyc)
  EndIf
  If pY#(cyc)=>pGround#(cyc)+6 And pY#(cyc)=<pGround#(cyc)+10 Then FindSmashes(cyc,1)
  If pY#(cyc)=<pGround#(cyc) And pAnimTim(cyc)<90
   ProduceSound(p(cyc),sThud,22050,0) : pStepTim(cyc)=99
   pY#(cyc)=pGround#(cyc) : pAnimTim(cyc)=90
   FindSmashes(cyc,0) : DropWeapon(cyc,5)
   pHP(cyc)=pHP(cyc)-Rnd(0,1)
  EndIf
  If pAnimTim(cyc)>105 
   ChangeAnim(cyc,0) : pPlatform(cyc)=0
   pExcusedItems(cyc)=0 : pExcusedPlays(cyc)=0
  EndIf
 EndIf
 ;---------- 30. ITEM INTERACTION ----------
 ;pick up item
 If pAnim(cyc)=30
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.5,pSeq(cyc,300),5 : pSting(cyc)=1
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=18 
   If pScar(cyc,6)=<4 Or pScar(cyc,19)=<4
    For v=1 To no_items
     viable=0
     If iState(v)=0 And iClimb#(iType(v))=>15 And pAnimTim(cyc)=<10 Then viable=1
     If pAnimTim(cyc)>10 Then viable=1
     If viable=1 And ItemProximity(cyc,v,30) And iCarrier(v)=0 And iAnim(v)=0 And iCarryDepth#(iType(v),iState(v))>0 And pSting(cyc)=1
      ScanItem(v,2)
      If ItemRange(cyc,v,20) And ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),0)
	   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
	   ProduceSound(p(cyc),sItem,22050,0)
	   pCarrying(cyc)=v : iCarrier(v)=cyc 
	   ChangeAnim(cyc,31) : pSting(cyc)=0 
	  EndIf
	 EndIf
    Next
   EndIf
  EndIf
  If pCarrying(cyc)>0 And iBurning(pCarrying(cyc))>0 Then BurnHands(cyc,1)
  If pAnimTim(cyc)>26 Then ChangeAnim(cyc,0)
 EndIf
 ;holding item
 If pAnim(cyc)=31
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0
   transition=5
   If pOldAnim(cyc)=30 And (iState(v)=1 Or iClimb#(iType(v))<15) Then transition=10
   Animate p(cyc),1,0.5,pSeq(cyc,30),transition
  EndIf
  BindItem(cyc)
  If pAnimTim(cyc)>5 
   If DirPressed(cyc) Then ChangeAnim(cyc,32)
   If cAttack(cyc)=1 Or cGrab(cyc)=1 Or cPick(cyc)=1 Or cTaunt(cyc)=1 Then ChangeAnim(cyc,33)
   If iState(v)=1 
    randy=Rnd(0,100)
    If (randy=0 And pControl(cyc)=0) Or (cRun(cyc)=1 And cPick(cyc)=1) Then ChangeAnim(cyc,34)
   EndIf
  EndIf
 EndIf
 ;dragging item
 If pAnim(cyc)=32
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,1.5,pSeq(cyc,31),5
  ApplyMovement(cyc,pSpeed#(cyc)/2)
  BindItem(cyc)
  If pAnimTim(cyc)>5 
   If DirPressed(cyc)=0 Then ChangeAnim(cyc,31)
   If cAttack(cyc)=1 Or cGrab(cyc)=1 Or cPick(cyc)=1 Or cTaunt(cyc)=1 Then ChangeAnim(cyc,33)
   If iState(v)=1 
    randy=Rnd(0,100)
    If (randy=0 And pControl(cyc)=0) Or (cRun(cyc)=1 And cPick(cyc)=1) Then ChangeAnim(cyc,34)
   EndIf
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;drop item
 If pAnim(cyc)=33
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.0,pSeq(cyc,300),5 : pSting(cyc)=1
  If pAnimTim(cyc)=1 And pCarrying(cyc)>0 Then DropItem(cyc) : pSting(cyc)=0
  If pAnimTim(cyc)=5 And pSting(cyc)=1 Then DropWeapon(cyc,0) : pSting(cyc)=0
  If pAnimTim(cyc)>6 Then ChangeAnim(cyc,0)
 EndIf
 ;fix item
 If pAnim(cyc)=34
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,300),5
   Animate i(v),3,-3.0,1,0
  EndIf
  If v>0 
   PositionEntity dummy,iX#(v),iY#(v),iZ#(v)
   RotateEntity dummy,0,pA#(cyc),0
   MoveEntity dummy,0,0,0.25
   If iType(v)=>4 And iType(v)=<6 Then MoveEntity dummy,0,0,0.1 
   iX#(v)=EntityX(dummy) : iZ#(v)=EntityZ(dummy)
  EndIf
  If iY#(v)>pY#(cyc) Then iY#(v)=iY#(v)-0.5
  If iY#(v)<pY#(cyc) Then iY#(v)=pY#(cyc)
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Then ProduceSound(i(v),iSound(iType(v)),22050,0)
  If pAnimTim(cyc)=15 Then iState(v)=0 : DropItem(cyc)
  If pAnimTim(cyc)>32 Then ChangeAnim(cyc,0)
 EndIf
 ;pick up weapon
 If pAnim(cyc)=35
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,3.0,pSeq(cyc,300),5 : pSting(cyc)=1
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=14 And pScar(cyc,19)=<4
   For v=1 To no_weaps
    range=weapSize#(weapType(v))+5
    If WeaponProximity(cyc,v,range) And weapCarrier(v)=0 And weapAnim(v)=0 And pSting(cyc)=1
	 ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
	 ProduceSound(weap(v),weapSound(weapType(v)),22050,0.5)
	 CreateSpurt(weapX#(v),weapY#(v)+1,weapZ#(v),10,5) 
	 AttainWeapon(cyc,v)
	 pSting(cyc)=0 
	EndIf
   Next
  EndIf
  randy=Rnd(0,2)
  If randy=0 And pAnimTim(cyc)=20 
   If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0 Then BurnHands(cyc,0)
  EndIf 
  If pAnimTim(cyc)>21 Then ChangeAnim(cyc,0)
 EndIf
 ;throw weapon
 If pAnim(cyc)=36
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,32),5
   weapGravity#(pHolding(cyc))=1.0
   If pRole(cyc)=1 And pControl(cyc)=0 And pFoc(cyc)>0 Then pA#(cyc)=pA#(cyc)+90
  EndIf
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=>3 And pAnimTim(cyc)=<15
   If cGrab(cyc)=1 Then weapGravity#(pHolding(cyc))=weapGravity#(pHolding(cyc))+0.1 
  EndIf
  If pAnimTim(cyc)=18 Then ProduceSound(p(cyc),sSwing,20000,0.5) : ThrowWeapon(cyc)
  If pAnimTim(cyc)>30 Then ChangeAnim(cyc,0)
 EndIf
 ;lying pick-up
 If pAnim(cyc)=37
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,33),5 : pSting(cyc)=1
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=15 And pScar(cyc,19)=<4
   ResetDummy(cyc)
   MoveEntity dummy,0,0,-15
   checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
   For v=1 To no_weaps
    If weapBroad(weapType(v))=0 And weapCarrier(v)=0 And weapAnim(v)=0 And pSting(cyc)=1
     range=weapSize#(weapType(v))
     If checkX#>weapX#(v)-range And checkX#<weapX#(v)+range And checkZ#>weapZ#(v)-range And checkZ#<weapZ#(v)+range And pY#(cyc)=weapY#(v)
	  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
	  ProduceSound(weap(v),weapSound(weapType(v)),22050,0.5)
	  CreateSpurt(weapX#(v),weapY#(v)+1,weapZ#(v),10,5) 
	  AttainWeapon(cyc,v)
	  pSting(cyc)=0
	 EndIf 
	EndIf
   Next
  EndIf
  randy=Rnd(0,2)
  If randy=0 And pAnimTim(cyc)=21 
   If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0 Then BurnHands(cyc,-1)
  EndIf 
  If pAnimTim(cyc)>26 Then ChangeAnim(cyc,83)
 EndIf
 ;set fire to weapon/item
 If pAnim(cyc)=38
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,3.0,pSeq(cyc,300),5 : pSting(cyc)=1
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=>5 And pAnimTim(cyc)=<15
   limb=pLimb(cyc,19)
   CreateParticle(EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1),7)
   randy=Rnd(0,2)
   If randy=0 Then CreateParticle(EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1),2)
  EndIf
  If pAnimTim(cyc)=14 And pScar(cyc,19)=<4 And optFX>0
   If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0
    For v=1 To no_items
     range=iSizeX#(iType(v))+8
     If iSizeZ#(iType(v))<iSizeX#(iType(v)) Then range=iSizeZ#(iType(v))+8
     If ItemProximity(cyc,v,range) And iState(v)=1 And iBurning(v)=0 And iFlammable(iType(v)) And iCarrier(v)=0 And pSting(cyc)=1
	  IgniteItem(v)
	  pSting(cyc)=0 
	 EndIf
    Next
   EndIf
   For v=1 To no_weaps
    range=weapSize#(weapType(v))+5
    If WeaponProximity(cyc,v,range) And weapBurning(v)=0 And weapFlammable(weapType(v)) And weapCarrier(v)=0 And weapAnim(v)=0 And pSting(cyc)=1
	 IgniteWeapon(v)
	 pSting(cyc)=0 
	EndIf
   Next
  EndIf
  If pAnimTim(cyc)=15 And pSting(cyc)=1 Then ProduceSound(p(cyc),sExpire,22050,0)
  If pAnimTim(cyc)>21 Then ChangeAnim(cyc,0)
 EndIf
 ;smooth item entry
 If pAnim(cyc)=39
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,1.5,pSeq(cyc,31),5
  If (pY#(cyc)=wGround# And pPlatform(cyc)=1) Or (pY#(cyc)=wStage# And pPlatform(cyc)=3) Then tA#=180
  If (pY#(cyc)=wGround# And pPlatform(cyc)=2) Or (pY#(cyc)=wStage# And pPlatform(cyc)=4) Then tA#=90
  If (pY#(cyc)=wGround# And pPlatform(cyc)=3) Or (pY#(cyc)=wStage# And pPlatform(cyc)=1) Then tA#=0
  If (pY#(cyc)=wGround# And pPlatform(cyc)=4) Or (pY#(cyc)=wStage# And pPlatform(cyc)=2) Then tA#=270
  pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),tA#,10)
  If SatisfiedAngle(pA#(cyc),tA#,15) 
   pA#(cyc)=tA#
   If pY#(cyc)=wGround# Then ChangeAnim(cyc,10)
   If pY#(cyc)=wStage# Then ChangeAnim(cyc,12)
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
  BindItem(cyc)
  If SatisfiedAngle(pA#(cyc),tA#,90) Then iY#(pCarrying(cyc))=wStage#
 EndIf
 ;---------- 40. STANDING ATTACKS ----------
 AttackAnims(cyc)
 ;---------- 50. GROUND ATTACKS ----------
 GroundAttackAnims(cyc)
 ;---------- 60. HURTING STATES ----------
 ;standing hurt
 If pAnim(cyc)=60 Or pAnim(cyc)=61 Or pAnim(cyc)=64
  If pAnimTim(cyc)=0 
   If pAnim(cyc)=<61 Then Animate p(cyc),3,Rnd(1.0,1.5),pSeq(cyc,pAnim(cyc)),5
   If pAnim(cyc)=64 Then Animate p(cyc),3,Rnd(1.0,1.5),pSeq(cyc,60),5
  EndIf
  If pAnimTim(cyc)=>1 And pAnimTim(cyc)=<10
   Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  chance=150-charTough(pChar(cyc))
  If pSpecial(cyc)>0 Then chance=chance/2
  If optLevel=>4 And pControl(cyc)=0 And pControl(pFoc(cyc))>0 Then chance=chance/2
  randy=Rnd(0,chance)
  If pAnim(cyc)=64 Then waker=6 Else waker=3
  If randy=<5 And pAnimTim(cyc)>waker
   If ActionPressed(cyc) Or pSpecial(cyc)>0 Then ChangeAnim(cyc,0)
  EndIf
  If randy=6 And pAnimTim(cyc)>10 And DirPressed(cyc) Then ChangeAnim(cyc,0)
  If pAnimTim(cyc)>10 And pSpecial(cyc)>0 Then ChangeAnim(cyc,0)
  If pAnimTim(cyc)>40 Then ChangeAnim(cyc,0)
  If pPlatform(cyc)=>5 Then pHP(cyc)=0
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
 EndIf
 ;lying hurt (on back)
 If pAnim(cyc)=62
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,Rnd(1.0,1.5),pSeq(cyc,pAnim(cyc)),5
  randy=Rnd(0,200)
  If (randy=0 And pAnimTim(cyc)=>5) Or pAnimTim(cyc)>37 Then ChangeAnim(cyc,80)
  If pDT(cyc)=<0 And (ActionPressed(cyc) Or pControl(cyc)=0) Then ChangeAnim(cyc,81)
  DropWeapon(cyc,10)
 EndIf
 ;lying hurt (on front)
 If pAnim(cyc)=63
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,Rnd(1.0,1.5),pSeq(cyc,pAnim(cyc)),5
  randy=Rnd(0,200)
  If (randy=0 And pAnimTim(cyc)=>5) Or pAnimTim(cyc)>43 Then ChangeAnim(cyc,83)
  If pDT(cyc)=<0 And (ActionPressed(cyc) Or pControl(cyc)=0) Then ChangeAnim(cyc,84)
  DropWeapon(cyc,10)
 EndIf
 ;stumble onto back
 If pAnim(cyc)=68
  If pAnimTim(cyc)=<25 Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=<20 Then FindFalls(cyc)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.8,pSeq(cyc,68),5
  If pAnimTim(cyc)=>1 And pAnimTim(cyc)=<35
   Advance(cyc,pA#(cyc),-0.3)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=15 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=27 Then ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,0)
  If pAnimTim(cyc)=38 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)>52 Then ChangeAnim(cyc,0) : pHP(cyc)=1 : pExcusedItems(cyc)=0
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
  If pPlatform(cyc)<0 Then pPlatform(cyc)=0
 EndIf
 ;stumble onto front
 If pAnim(cyc)=69
  If pAnimTim(cyc)=<25 Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=<15 Then FindFalls(cyc)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.8,pSeq(cyc,69),5
  If pAnimTim(cyc)=>1 And pAnimTim(cyc)=<30
   Advance(cyc,pA#(cyc),-0.4)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=17 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sFall,22050,1)
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sThud,22050,0)
  If pAnimTim(cyc)=38 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=47 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>58 Then ChangeAnim(cyc,0) : pHP(cyc)=1 : pExcusedItems(cyc)=0
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
  If pPlatform(cyc)<0 Then pPlatform(cyc)=0
 EndIf
 ;---------- 70. FALLING STATES ----------
 ;fall onto back
 If pAnim(cyc)=70
  If pAnimTim(cyc)=<25 Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=<20 Then FindFalls(cyc)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.8,pSeq(cyc,70),5
  If pAnimTim(cyc)=>1 And pAnimTim(cyc)=<35
   Advance(cyc,pA#(cyc),-0.5)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=15 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=25 
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,0)
   FindSmashes(cyc,0) : ScarArea(cyc,0,0,0,20)
  EndIf
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)>44 Then ChangeAnim(cyc,80) : pExcusedItems(cyc)=0
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
 EndIf
 ;fall onto front
 If pAnim(cyc)=71
  If pAnimTim(cyc)=<27 Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=<15 Then FindFalls(cyc)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.8,pSeq(cyc,71),5
  If pAnimTim(cyc)=>1 And pAnimTim(cyc)=<35
   Advance(cyc,pA#(cyc),-0.5)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=17 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=27 
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,0)
   FindSmashes(cyc,0) : ScarArea(cyc,0,0,0,20)
  EndIf
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)>44 Then ChangeAnim(cyc,83) : pExcusedItems(cyc)=0
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
 EndIf
 ;fall from a platform
 If pAnim(cyc)=72 Or pAnim(cyc)=73
  If pAnimTim(cyc)=0 
   Pop(0,Rnd(4,8),0)
   ProduceSound(p(cyc),sPain(4),GetVoice(cyc),1)
   If pAnim(cyc)=72 Then Animate p(cyc),3,1.5,pSeq(cyc,70),5
   If pAnim(cyc)=73 Then Animate p(cyc),3,1.5,pSeq(cyc,71),5
   FindClearFall(cyc)
   pGravity#(cyc)=0.2 : pPeaked(cyc)=0
   pPeak#(cyc)=1.2 : pFlight#(cyc)=-0.8
   If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then pPeak#(cyc)=1.7 : pFlight#(cyc)=-1.3
   pPlatform(cyc)=0
   pExcusedWorld(cyc)=1 : pExcusedEdges(cyc)=1
   pExcusedPlays(cyc)=1 : pExcusedItems(cyc)=1
   If pY#(cyc)=<pGround#(cyc) Then pY#(cyc)=pGround#(cyc)+1.0
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=>2 And pY#(cyc)>pGround#(cyc)
   If pGravity#(cyc)<pPeak#(cyc) And pPeaked(cyc)=0 Then pGravity#(cyc)=pGravity#(cyc)+(pPeak#(cyc)/10)
   If pGravity#(cyc)=>pPeak#(cyc) Then pPeaked(cyc)=1
   If pPeaked(cyc)=1 Then pGravity#(cyc)=pGravity#(cyc)-((pPeak#(cyc)/10)+0.1)
   If FindBlocks(pX#(cyc),pZ#(cyc),0)>0 And pGravity#(cyc)<-1.0 Then pGravity#(cyc)=-1.0 
   pY#(cyc)=pY#(cyc)+pGravity#(cyc)
   Advance(cyc,pA#(cyc),pFlight#(cyc))
  EndIf
  If pPeaked(cyc)=1
   pExcusedWorld(cyc)=0 : pExcusedEdges(cyc)=0
   If pY#(cyc)=>pGround#(cyc)+10 And pY#(cyc)=<pGround#(cyc)+15 Then FindSmashes(cyc,1)
   If pY#(cyc)<pGround#(cyc) 
    Pop(0,Rnd(4,8),0) : RiskApplause(cyc,5)  
    ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
    ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1)
    If pGround#(cyc)=wStage# Then pHealth(cyc)=pHealth(cyc)-5 : pHP(cyc)=0 : pDT(cyc)=200-pHealth(cyc)
    If pGround#(cyc)<wStage# Then pHealth(cyc)=pHealth(cyc)-10 : pHP(cyc)=0 : pDT(cyc)=300-pHealth(cyc)
    pHeat(cyc)=pHeat(cyc)-5 : RiskInjury(cyc,0)
    FindSmashes(cyc,0) : ScarArea(cyc,0,0,0,20)
    pY#(cyc)=pGround#(cyc)
   EndIf
   If pY#(cyc)=pGround#(cyc) And pAnimTim(cyc)<50 Then Advance(cyc,pA#(cyc),pFlight#(cyc)/2)
   If pAnimTim(cyc)>53 And pY#(cyc)=<pGround#(cyc)
    pExcusedPlays(cyc)=0 : pExcusedItems(cyc)=0 
    If pAnim(cyc)=72 Then ChangeAnim(cyc,80)
    If pAnim(cyc)=73 Then ChangeAnim(cyc,83)
    ForceOut(cyc,-1)
   EndIf
  EndIf
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
 EndIf
 ;fall out of ring
 If pAnim(cyc)=74
  pExcusedWorld(cyc)=1 : pExcusedItems(cyc)=1
  If pAnimTim(cyc)=0 
   Pop(0,Rnd(4,8),0)
   ProduceSound(p(cyc),sPain(4),GetVoice(cyc),1) 
   Animate p(cyc),3,2.0,pSeq(cyc,72),2
   If SatisfiedAngle(pA#(cyc),180,20) Then pZ#(cyc)=wBlockZ1#(1)
   If SatisfiedAngle(pA#(cyc),90,20) Then pX#(cyc)=wBlockX1#(2)
   If SatisfiedAngle(pA#(cyc),0,20) Then pZ#(cyc)=wBlockZ2#(3)
   If SatisfiedAngle(pA#(cyc),270,20) Then pX#(cyc)=wBlockX2#(4)
  EndIf  
  If pAnimTim(cyc)=5  
   ShakeRopes(NearRopes(cyc,5),1,2)
   RopeSound(p(cyc),0) : RopeBurn(cyc,1) 
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=35 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=35 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=52 
   Pop(0,Rnd(4,8),0) : RiskApplause(cyc,5)
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
   ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1) 
   pHealth(cyc)=pHealth(cyc)-10 : pHP(cyc)=0 : pDT(cyc)=300-pHealth(cyc)
   pHeat(cyc)=pHeat(cyc)-5 : RiskInjury(cyc,1)
   FindSmashes(cyc,0) : ScarArea(cyc,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>50 And EntityY(FindChild(p(cyc),"Hips"),1)>wGround#+5 Then pY#(cyc)=pY#(cyc)-0.2
  If pAnimTim(cyc)>66 
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,270) : pY#(cyc)=wGround#
   pExcusedWorld(cyc)=0 : pExcusedItems(cyc)=0
  EndIf
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
 EndIf
 ;fall out onto apron
 If pAnim(cyc)=75
  pExcusedWorld(cyc)=1 : pExcusedItems(cyc)=1
  If pAnimTim(cyc)=0 
   Pop(0,Rnd(4,8),0)
   ProduceSound(p(cyc),sPain(4),GetVoice(cyc),0) 
   Animate p(cyc),3,2.0,pSeq(cyc,73),2
   If SatisfiedAngle(pA#(cyc),180,20) Then pZ#(cyc)=wBlockZ1#(1)
   If SatisfiedAngle(pA#(cyc),90,20) Then pX#(cyc)=wBlockX1#(2)
   If SatisfiedAngle(pA#(cyc),0,20) Then pZ#(cyc)=wBlockZ2#(3)
   If SatisfiedAngle(pA#(cyc),270,20) Then pX#(cyc)=wBlockX2#(4)
  EndIf
  If pAnimTim(cyc)=5  
   ShakeRopes(NearRopes(cyc,5),1,2)
   RopeSound(p(cyc),0) : RopeBurn(cyc,1) 
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=55 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=25 Then FindSmashes(cyc,1)
  If pAnimTim(cyc)=35
   Pop(cyc,Rnd(4,7),0) : RiskApplause(cyc,1) 
   ProduceSound(p(cyc),sFall,22050,1)
   ProduceSound(p(cyc),sThud,22050,1) 
   pHealth(cyc)=pHealth(cyc)-1 : pHP(cyc)=1
   pHeat(cyc)=pHeat(cyc)+5
   FindSmashes(cyc,0)
  EndIf
  If pAnimTim(cyc)>67 
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   pExcusedWorld(cyc)=0 : pExcusedItems(cyc)=0
   If pHP(cyc)<1 Then pHP(cyc)=1
  EndIf
  LoseGrapple(cyc,0) : DropItem(cyc) : DropWeapon(cyc,20)
 EndIf
 ;thrown out into fall
 If pAnim(cyc)=76 Or pAnim(cyc)=77
  pExcusedWorld(cyc)=1 : pExcusedEdges(cyc)=1
  pExcusedPlays(cyc)=1 : pExcusedItems(cyc)=1
  If pAnimTim(cyc)=0 
   Pop(0,Rnd(4,8),0)
   ProduceSound(p(cyc),sSwing,21000,0.5)
   ProduceSound(p(cyc),sPain(4),GetVoice(cyc),1)
   If pAnim(cyc)=76 Then Animate p(cyc),1,1.5,pSeq(cyc,74),0
   If pAnim(cyc)=77 Then Animate p(cyc),1,1.5,pSeq(cyc,75),0
   pGravity#(cyc)=0.2 : pPeaked(cyc)=0
   pPeak#(cyc)=1.2 : pFlight#(cyc)=1.0
   pPlatform(cyc)=0 : pExcusedWorld(cyc)=0
  EndIf
  If pAnimTim(cyc)=1 Then ShakeRopes(NearRopes(cyc,5),1,2)
  If pY#(cyc)>wGround#
   If pGravity#(cyc)<pPeak#(cyc) And pPeaked(cyc)=0 Then pGravity#(cyc)=pGravity#(cyc)+(pPeak#(cyc)/10)
   If pGravity#(cyc)=>pPeak#(cyc) Then pPeaked(cyc)=1
   If pPeaked(cyc)=1 Then pGravity#(cyc)=pGravity#(cyc)-((pPeak#(cyc)/10)+0.1)
   If InsideRing(pX#(cyc),pZ#(cyc),-10) And pGravity#(cyc)<0 Then pGravity#(cyc)=0
   pY#(cyc)=pY#(cyc)+pGravity#(cyc)
   Advance(cyc,pFlightA#(cyc),pFlight#(cyc))
  EndIf
  If pPeaked(cyc)=1 
   pExcusedWorld(cyc)=0 : pExcusedEdges(cyc)=0
   If pY#(cyc)=>pGround#(cyc)+10 And pY#(cyc)=<pGround#(cyc)+15 Then FindSmashes(cyc,1)
   If pY#(cyc)=<wGround#
    Pop(0,Rnd(4,8),0) : RiskApplause(cyc,5)
    ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
    ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1) 
    pHealth(cyc)=pHealth(cyc)-15 : pHP(cyc)=0 : pDT(cyc)=350-pHealth(cyc)
    pHeat(cyc)=pHeat(cyc)-10 : RiskInjury(cyc,1)
    FindSmashes(cyc,0) : ScarArea(cyc,0,0,0,10)
    ChangeAnim(cyc,pAnim(cyc)+2) : pY#(cyc)=wGround#
    pExcusedPlays(cyc)=0 : pExcusedItems(cyc)=0
   EndIf
  EndIf
  DropWeapon(cyc,20)
 EndIf
 ;land after throw out
 If pAnim(cyc)=78 Or pAnim(cyc)=79
  If pAnimTim(cyc)=0 
   If pAnim(cyc)=78 Then Animate p(cyc),3,2.0,pSeq(cyc,76),5
   If pAnim(cyc)=79 Then Animate p(cyc),3,2.0,pSeq(cyc,77),5
  EndIf
  If pAnimTim(cyc)=<20 Then Advance(cyc,pFlightA#(cyc),pFlight#(cyc)/2)
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)>25
   If pAnim(cyc)=78 Then ChangeAnim(cyc,80) : ForceOut(cyc,1)
   If pAnim(cyc)=79 Then ChangeAnim(cyc,83) : ForceOut(cyc,1)
  EndIf
  DropWeapon(cyc,10)
 EndIf
 ;---------- 80. LYING STATES ----------
 ;lying on back
 If pAnim(cyc)=80
  pinny=0
  If pPinning(cyc)>0 And matchState=3 And LegalMan(cyc) And matchPins=1 Then pinny=1
  If pPinner(cyc)>0 Then transition=15 Else transition=5
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(0.1,0.3),pSeq(cyc,80),transition
  If pAnimTim(cyc)>10 And pDT(cyc)=<0 
   getup=0
   If pControl(cyc)>0 And ActionPressed(cyc) Then getup=1
   If pControl(cyc)=0 And pinny=0 Then getup=1
   If getup=1 Then ChangeAnim(cyc,81)
  EndIf
  If pAnimTim(cyc)>5 And pDT(cyc)<100 
   If DirPressed(cyc) And (pinny=0 Or pControl(cyc)>0) Then ChangeAnim(cyc,82)
  EndIf
  If pPlatform(cyc)<0 Then pPlatform(cyc)=0
 EndIf
 ;get up off back
 If pAnim(cyc)=81
  If pOldAnim(cyc)=92 Then transition=1 Else transition=5
  If pAnimTim(cyc)=0 
   riser#=Float(charAgl(pChar(cyc)))/80
   If pInjured(cyc)>0 Then riser#=0.2
   pAnimSpeed#(cyc)=Rnd(1.7,1.7+riser#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,81),transition
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   pStepTim(cyc)=99 
  EndIf
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(80/pAnimSpeed#(cyc))
   randy=Rnd(0,1)
   If randy=0 And cAttack(cyc)=1 Then ChangeAnim(cyc,45)
  EndIf
  If pAnimTim(cyc)>10 And pAnimTim(cyc)>Int(88/pAnimSpeed#(cyc))+transition
   ChangeAnim(cyc,0)
   pStepTim(cyc)=99 : pHP(cyc)=20
  EndIf
 EndIf
 ;turn onto front
 If pAnim(cyc)=82
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,2.0,pSeq(cyc,82),5
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)>32 Then ChangeAnim(cyc,83)
 EndIf
 ;lying on front
 If pAnim(cyc)=83
  pinny=0
  If pPinning(cyc)>0 And matchState=3 And LegalMan(cyc) And matchPins=1 Then pinny=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(0.1,0.3),pSeq(cyc,83),10
  If pAnimTim(cyc)>5 And pDT(cyc)<100 
   If DirPressed(cyc) And (pinny=0 Or pControl(cyc)>0) Then ChangeAnim(cyc,85) 
   If cPick(cyc)=1 And pHolding(cyc)=0 Then ChangeAnim(cyc,37) 
   getup=0
   If pControl(cyc)>0 And (cAttack(cyc)=1 Or cGrab(cyc)=1 Or cRun(cyc)=1) Then getup=1
   If pControl(cyc)=0 And pinny=0 Then getup=1
   If pDT(cyc)=<0 And getup=1
    ChangeAnim(cyc,84)
   EndIf
  EndIf
  If pPlatform(cyc)<0 Then pPlatform(cyc)=0
 EndIf
 ;get up off front
 If pAnim(cyc)=84
  If pAnimTim(cyc)=0 
   riser#=Float(charAgl(pChar(cyc)))/80
   If pInjured(cyc)>0 Then riser#=0.2
   pAnimSpeed#(cyc)=Rnd(1.7,1.7+riser#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,84),5
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(32/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc))
   pStepTim(cyc)=99 
  EndIf
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc))
   randy=Rnd(0,3)
   If pControl(cyc)=0 And pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),10) And AttackViable(pFoc(cyc))
    cAttack(cyc)=1
   EndIf
   If randy=0 And cAttack(cyc)=1 Then ChangeAnim(cyc,45) : SharpTransition(cyc,45,180)
  EndIf
  If pAnimTim(cyc)>10 And pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : Animate p(cyc),1,1,pSeq(cyc,1),0
   pA#(cyc)=pA#(cyc)+180 : pTA#(cyc)=pA#(cyc) : pHP(cyc)=20
  EndIf
 EndIf
 ;crawling
 If pAnim(cyc)=85
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,1.2,pSeq(cyc,85),5
  ApplyMovement(cyc,0.2)
  If pAnimTim(cyc)>5 
   If DirPressed(cyc)=0 Then ChangeAnim(cyc,83)
   If cPick(cyc)=1 And pHolding(cyc)=0 Then ChangeAnim(cyc,37) 
  EndIf
  getup=0
  If pControl(cyc)>0 And (cAttack(cyc)=1 Or cGrab(cyc)=1 Or cRun(cyc)=1) Then getup=1
  If pControl(cyc)=0 And (pPinning(cyc)=0 Or matchState<>3 Or LegalMan(cyc)=0) Then getup=1
  If pDT(cyc)=<0 And getup=1
   ChangeAnim(cyc,84) : pAnimTim(cyc)=-1
  EndIf
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;---------- 90. PINNING & REFEREEING ----------
 ;initiate pin
 If pAnim(cyc)=90
  v=pPinning(cyc) : pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,2.0,pSeq(cyc,90),2
   If pAnim(v)<>62 And pAnim(v)<>80 Then ChangeAnim(v,80) ;turn onto back
   If pAnimTim(v)>0 Then pAnimTim(v)=1
   pDT(v)=pDT(v)+Rnd(10,60)
  EndIf
  If pPinStyle(cyc)=1 Then ApplyPin(cyc,v,0,-13,-3) ;over head
  If pPinStyle(cyc)=2 Then ApplyPin(cyc,v,-5,-3,-3) ;from left (flat)
  If pPinStyle(cyc)=3 Then ApplyPin(cyc,v,5,-3,-3) ;from right (flat)
  If pPinStyle(cyc)=4 Then ApplyPin(cyc,v,-6,-6,-6) ;from left (hooked) 
  If pPinStyle(cyc)=5 Then ApplyPin(cyc,v,7,-6,-5) ;from right (hooked)
  If pAnimTim(cyc)=>10 And pAnim(v)=>81 And pAnim(v)=<85
   ChangeAnim(cyc,Rnd(68,71)) : pHP(cyc)=0
   pPinning(cyc)=0 : pPinner(v)=0
  EndIf
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sFall,22050,0.5) : Pop(cyc,Rnd(2,7),0)
  If pAnimTim(v)>12 Then ChangeAnim(v,92)
  If pAnimTim(cyc)>15 And pPinStyle(cyc)=4 Then Animate p(cyc),1,1.0,pSeq(cyc,93),5
  If pAnimTim(cyc)>15 And pPinStyle(cyc)=5 Then Animate p(cyc),1,1.0,pSeq(cyc,92),5
  If pAnimTim(cyc)>20 Then ChangeAnim(cyc,91)
  If pRefCount(cyc)<3 Then pRefCount(cyc)=0
  pAutoTim(cyc)=0
  DropWeapon(cyc,10)
 EndIf
 ;pin loop
 If pAnim(cyc)=91
  v=pPinning(cyc)
  If pAnimTim(cyc)=0
   If pPinStyle(cyc)=<3 Then Animate p(cyc),1,Rnd(0.2,0.5),pSeq(cyc,91),5
   If pPinStyle(cyc)=4 Then Animate p(cyc),1,Rnd(0.2,0.5),pSeq(cyc,93),5
   If pPinStyle(cyc)=5 Then Animate p(cyc),1,Rnd(0.2,0.5),pSeq(cyc,92),5
  EndIf
  break=0
  If pControl(cyc)=0 And pAnimTim(cyc)>100 And matchRules=>1 And LegalMan(cyc) And LegalMan(v) And FindPinCount(v)=0
   If NearRopes(cyc,optRopeRange/2)>0 Or NearRopes(v,optRopeRange)>0 Then break=1
  EndIf
  If pControl(cyc)=0 And (matchState<>3 Or pEliminated(v) Or pAnimTim(cyc)>400) Then break=1
  If pControl(cyc)>0 And cGrab(cyc)=1 Then break=1
  If break=1 Or pAnim(v)<>92
   If break=0
    If FindPinCount(v)>0 Then Pop(v,Rnd(2,7),1)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
    ProduceSound(p(cyc),sPain(1),GetVoice(cyc),1)
    ProduceSound(p(cyc),sSwing,22050,0.3)
    pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
   EndIf
   pPinning(cyc)=0 : pPinner(v)=0
   ChangeAnim(cyc,93) : pExcusedPlays(cyc)=0
   randy=99
   If FindPinCount(v)>0 Then randy=Rnd(0,3)
   If FindPinCount(v)=>2 Then randy=Rnd(0,1)
   If randy=0 And (pAnim(v)=62 Or pAnim(v)=82) Then ChangeAnim(cyc,94) ;exasperated
   If pAnim(v)=81 Then ChangeAnim(cyc,Rnd(68,71)) : pHP(cyc)=0 ;fly off!
  EndIf
 EndIf
 ;pin victim
 If pAnim(cyc)=92
  v=pPinner(cyc)
  If pAnimTim(cyc)=0 
   If pPinStyle(v)=<3 Then Animate p(cyc),1,Rnd(0.1,0.3),pSeq(cyc,80),10
   If pPinStyle(v)=>4 Then Animate p(cyc),1,Rnd(0.1,0.3),pSeq(cyc,94),10
  EndIf
  If pDT(cyc)<100 
   If pControl(cyc)>0 And ActionPressed(cyc) Then ChangeAnim(cyc,81) : pAnimTim(cyc)=-1
   If pControl(cyc)>0 And DirPressed(cyc) Then ChangeAnim(cyc,82) : pAnimTim(cyc)=-1
   If pControl(cyc)=0 Then ChangeAnim(cyc,Rnd(81,82))
  EndIf
  If v=0 Then ChangeAnim(cyc,80)
 EndIf
 ;release pin
 If pAnim(cyc)=93
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,-2.0,pSeq(cyc,90),5
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>20 Then ChangeAnim(cyc,0)
 EndIf
 ;exasperated pin loss
 If pAnim(cyc)=94
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,95),2
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=85 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=100 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>115 Then ChangeAnim(cyc,0)
 EndIf
 ;ref kneel down
 If pAnim(cyc)=95
  v=pRefVictim(cyc)
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,2.0,pSeq(cyc,96),5
  PointEntity p(cyc),pLimb(v,1) 
  pA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sFall,22050,0.3)
  If pAnimTim(cyc)>20 Then ChangeAnim(cyc,96)
  DropWeapon(cyc,10)
 EndIf
 ;ref examination
 If pAnim(cyc)=96
  v=pRefVictim(cyc) : pSpeaking(cyc)=1
  If pAnimTim(cyc)=0 
   If pAnim(v)=109 Then Animate p(cyc),1,Rnd(0.5,1.0),pSeq(cyc,562),10
   If pAnim(v)<>109 Then Animate p(cyc),1,Rnd(0.5,1.0),pSeq(cyc,97),5
   pRefSpeed(cyc)=Rnd(30,50)
  EndIf
  PointEntity p(cyc),pLimb(v,1) 
  pA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
  If pAnimTim(cyc)=>10
   If RefViable(v)=1 ;pin sequences
    If pChar(v)=charEnemy(pChar(cyc)) Or pChar(pRefAward(cyc))=charFriend(pChar(cyc)) Then pRefSpeed(cyc)=20
    If pChar(v)=charFriend(pChar(cyc)) Or pChar(pRefAward(cyc))=charEnemy(pChar(cyc)) Then pRefSpeed(cyc)=80
    If pControl(cyc)=0 And pRefCount(cyc)=0 Then ChangeAnim(cyc,97)
    If pControl(cyc)=0 And pRefCount(cyc)>0 And pAnimTim(cyc)>pRefSpeed(cyc) Then ChangeAnim(cyc,97)
    If pControl(cyc)>0 And cTaunt(cyc)=1 Then ChangeAnim(cyc,97)
    If pAnimTim(cyc)=55 Then Pop(v,2,1)
   EndIf
   If RefViable(v)=2 And pStretched(v)=>2 Then ChangeAnim(cyc,98) : matchWinStyle=2 ;declare submission
   If RefViable(v)=>2 And matchKOs=1 
    If pHealth(v)=<1 Or pInjured(v)>0 Then ChangeAnim(cyc,98) : matchWinStyle=3 ;declare KO
   EndIf
   If RefViable(v)=>2 And matchBlood=1
    If pScar(v,1)=>3 Or (optGore=0 And pScar(v,1)>0) Then ChangeAnim(cyc,98) : matchWinStyle=4 ;declare blood
   EndIf
   If (pControl(cyc)>0 And cGrab(cyc)=1) Or RefViable(v)=0 ;cancel
    pRefAward(cyc)=0 : pRefVictim(cyc)=0
    ChangeAnim(cyc,98)
   EndIf
   If InProximity(cyc,v,35)=0 ;lose track
    pRefAward(cyc)=0 : pRefVictim(cyc)=0
    ChangeAnim(cyc,98) : pChecked(v)=0
   EndIf
   ignore=0
   If pChar(v)=charEnemy(pChar(cyc)) Or pChar(pRefAward(cyc))=charFriend(pChar(cyc)) Then ignore=1
   If ignore=0 And pControl(cyc)=0 And matchRules=>1 And RefViable(v)>0 And LegalMan(pRefAward(cyc)) And LegalMan(v) 
    If NearRopes(pRefAward(cyc),optRopeRange/2)>0 Or NearRopes(v,optRopeRange)>0 ;rope break
     Pop(v,Rnd(2,7),0)
     ProduceSound(p(cyc),sBreak,GetVoice(cyc),1)
     pFoc(cyc)=pRefAward(cyc) : pRefAnger(cyc)=60
     pRefAward(cyc)=0 : pRefVictim(cyc)=0
     ChangeAnim(cyc,98)
    EndIf
   EndIf
  EndIf
 EndIf
 ;administer count
 If pAnim(cyc)=97
  v=pRefVictim(cyc) : pSpeaking(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.5,pSeq(cyc,98),5
  If pAnimTim(cyc)=11 Then ProduceSound(p(cyc),sSwing,22050,0.3)
  If pAnimTim(cyc)=20
   ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.5)
   If pHolding(cyc)>0 Then ProduceSound(p(cyc),weapSound(weapType(pHolding(cyc))),22050,0.6)
   pRefCount(cyc)=pRefCount(cyc)+1
   ProduceSound(p(cyc),sCount(pRefCount(cyc)),GetVoice(cyc),1)
   If pRefCount(cyc)=2 Then Pop(pRefAward(cyc),Rnd(4,5),0)
   If pRefCount(cyc)=3 Then Pop(pRefAward(cyc),Rnd(2,7),1)
  EndIf
  If pAnimTim(cyc)>33 Or (pAnimTim(cyc)=<19 And RefViable(v)=0) 
   ChangeAnim(cyc,96)
   If pRefCount(cyc)=>3 Then ChangeAnim(cyc,98) : matchWinStyle=1
  EndIf
 EndIf
 ;ref get up
 If pAnim(cyc)=98
  If pRefAward(cyc)>0 Or pRefVictim(cyc)>0 Then speeder#=2.0 Else speeder#=1.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,-speeder#,pSeq(cyc,96),5
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0.5)
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>(40/speeder#) 
   If Isolated(cyc,20)=0 And InsideRing(pX#(cyc),pZ#(cyc),-20) And pRefAnger(cyc)=0 
    pAgenda(cyc)=0 : pNowhere(cyc)=99 ;relocate
   EndIf
   If pRefAward(cyc)=0 And pRefVictim(cyc)=0 Then ChangeAnim(cyc,0)
   If pRefAward(cyc)>0 Or pRefVictim(cyc)>0 Then ChangeAnim(cyc,99)
  EndIf
 EndIf
 ;ref declare
 If pAnim(cyc)=99
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.5,pSeq(cyc,99),5
  If pAnimTim(cyc)=0 And matchWinStyle=0 Then ProduceSound(p(cyc),sDQ,GetVoice(cyc),1)
  If matchWinStyle=0 Then declareTim=20 Else declareTim=10
  If pAnimTim(cyc)=declareTim Then DeclareFall(pRefAward(cyc),pRefVictim(cyc))
  If pAnimTim(cyc)>90 Then ChangeAnim(cyc,0)
 EndIf
 ;---------- 100. STANDING GRAPPLES ----------
 MoveAnims(cyc)
 ;---------- 200. GROUND GRAPPLING ----------
 HeadMoves(cyc)
 LegMoves(cyc)
 ;---------- 400. BUCKLE GRAPPLING ----------
 BuckleMoves(cyc)
 ;INCREMENTATION
 pAnimTim(cyc)=pAnimTim(cyc)+1
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;CHANGE ANIMATION
Function ChangeAnim(cyc,anim)
 pOldAnim(cyc)=pAnim(cyc)
 pAnim(cyc)=anim
 If pOldAnim(cyc)>anim Then pAnimTim(cyc)=-1 Else pAnimTim(cyc)=0
End Function

;IMMEDIATE TRANSITION
Function SharpTransition(cyc,anim,offset#)
 ;honour current co-ords
 pX#(cyc)=EntityX(pLimb(cyc,30),1)
 pZ#(cyc)=EntityZ(pLimb(cyc,30),1)
 If offset#=0 Then pA#(cyc)=EntityYaw(pLimb(cyc,30),1)
 If offset#>0 Then pA#(cyc)=pA#(cyc)+offset# 
 pA#(cyc)=CleanAngle#(pA#(cyc)) 
 pTA#(cyc)=pA#(cyc)
 ;offset for lying on back
 If pAnim(cyc)=80 Or pAnim(cyc)=83
  PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity p(cyc),0,pA#(cyc),0
  MoveEntity p(cyc),0,0,-1
  ;If pOldAnim(cyc)=74 Then MoveEntity p(cyc),0,0,-1
  pX#(cyc)=EntityX(p(cyc)) : pZ#(cyc)=EntityZ(p(cyc))
 EndIf
 ;update old values
 If pExcusedWorld(cyc) Then pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
 ;immediate transition 
 Animate p(cyc),1,1,pSeq(cyc,anim),0
End Function

;REVERSE TRANSITION
Function ReverseTransition(cyc,anim,speed#,offX#,offZ#)
 ;trigger new sequence
 Animate p(cyc),3,-speed#,pSeq(cyc,anim),0
 ;forecast new location
 RotateEntity p(cyc),0,pA#(cyc),0
 MoveEntity p(cyc),offX#,0,offZ#
 pX#(cyc)=EntityX(FindChild(p(cyc),"Hips"),1)
 pZ#(cyc)=EntityZ(FindChild(p(cyc),"Hips"),1)
 pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
End Function

;POINT HEAD
Function PointHead(cyc,entity)
 ;identify limbs involved
 limb=FindChild(p(cyc),"Head")
 source=FindChild(p(cyc),"Body")
 ;stabilize and point
 RotateEntity limb,EntityPitch(source),EntityYaw(source),EntityRoll(source) 
 PointEntity limb,entity
 ;X limitations
 If EntityPitch(limb)<EntityPitch(source)-60 Then RotateEntity limb,EntityPitch(source)-60,EntityYaw(limb),EntityRoll(limb)
 If EntityPitch(limb)>EntityPitch(source)+10 Then RotateEntity limb,EntityPitch(source)+10,EntityYaw(limb),EntityRoll(limb)
 ;Y limitations
 If EntityYaw(limb)<EntityYaw(source)-45 Then RotateEntity limb,EntityPitch(limb),EntityYaw(source)-45,EntityRoll(limb)
 If EntityYaw(limb)>EntityYaw(source)+45 Then RotateEntity limb,EntityPitch(limb),EntityYaw(source)+45,EntityRoll(limb) 
 ;Z limitations
 If EntityRoll(limb)<EntityRoll(source)-1 Then RotateEntity limb,EntityPitch(limb),EntityYaw(limb),EntityRoll(source)-1
 If EntityRoll(limb)>EntityRoll(source)+1 Then RotateEntity limb,EntityPitch(limb),EntityYaw(limb),EntityRoll(source)+1
 ;preserve long hair
 RotateEntity FindChild(p(cyc),"Hair_Long"),-EntityPitch(limb),-(EntityYaw(limb)-(EntityYaw(limb)/3)),-(EntityRoll(limb)/2)
 If EntityPitch(FindChild(p(cyc),"Hair_Long"))<EntityPitch(source)-10
  RotateEntity FindChild(p(cyc),"Hair_Long"),EntityPitch(source)-10,EntityYaw(FindChild(p(cyc),"Hair_Long")),EntityRoll(FindChild(p(cyc),"Hair_Long"))
 EndIf
End Function

;VIABLE FOR PIVOT POINTING?
Function PointViable(cyc) ;1=Y# inclusive, 2=Y# excluded
 viable=0
 If pAnim(cyc)=4 Then viable=2 ;rope turn
 If pAnim(cyc)=>10 And pAnim(cyc)=<13 Then viable=1 ;ring climbing
 If pAnim(cyc)=17 Then viable=1 ;climb over wall
 If pAnim(cyc)=>20 And pAnim(cyc)=<22 Then viable=1 ;buckle climbing (up)
 If pAnim(cyc)=>25 And pAnim(cyc)=<27 Then viable=1 ;buckle climbing (down)
 If pAnim(cyc)=74 Then viable=1 ;fall out of ring
 If pAnim(cyc)=75 Then viable=2 ;fall out to apron
 If pAnim(cyc)=>109 And pAnim(cyc)<200 Then viable=2 ;standing moves
 If pAnim(cyc)=109 
  If pAnim(pGrappler(cyc))=111 Then viable=1 ;thrown out through ropes
  If pAnim(pGrappler(cyc))=113 Then viable=1 ;thrown out from apron
 EndIf
 If pAnim(cyc)=202 Then viable=2 ;ground victim
 If pAnim(cyc)=>203 And pAnim(cyc)<300 Then viable=2 ;head ground moves
 If pAnim(cyc)=>303 And pAnim(cyc)<400 Then viable=2 ;leg ground moves
 If pAnim(cyc)=>410 And pAnim(cyc)<500 Then viable=2 ;buckle moves
 Return viable 
End Function

;VIABLE TO TURN?
Function TurnViable(cyc) ;1=active & focused, 2=inactive but focused, 3=distracted
 viable=0
 If pAnim(cyc)=>0 And pAnim(cyc)=<2 Then viable=1 ;standing/moving
 If pAnim(cyc)=3 And pMomentum(cyc)=0 Then viable=1 ;running
 If pAnim(cyc)=23 Or pAnim(cyc)=24 Then viable=1 ;turnbuckle stances
 If pAnim(cyc)=32 Then viable=3 ;carrying item
 If pAnim(cyc)=85 Then viable=3 ;crawling
 If pAnim(cyc)=100 Or pAnim(cyc)=200 
  If pAnimTim(cyc)<20 And pFoc(cyc)>0 Then viable=2 ;grapple lunging 
 EndIf
 If pAnim(cyc)=102 And pPlatform(cyc)=0 And pPlatform(pGrappling(cyc))=0 
  viable=3 ;grapple movement
 EndIf
 If pAnim(cyc)=201 Then viable=2 ;cruising to head
 If pAnim(cyc)=301 Then viable=2 ;cruising to legs
 Return viable 
End Function

;VIABLE TO BE ATTACKED?
Function AttackViable(cyc) ;1=standing, 2=grounded
 ;standing vulnerable
 viable=0
 If pAnim(cyc)=>0 And pAnim(cyc)=<4 Then viable=1 ;standing/moving
 If pAnim(cyc)=5 Or pAnim(cyc)=6 Then viable=1 ;taunting
 If pAnim(cyc)=15 Or pAnim(cyc)=16 Then viable=1 ;climbing wall
 If pAnim(cyc)=23 Or pAnim(cyc)=24 Then viable=1 ;on turnbuckles
 If pAnim(cyc)=>30 And pAnim(cyc)=<38 And pAnim(cyc)<>34 And pAnim(cyc)<>37 
  viable=1 ;item interaction
 EndIf
 If pAnim(cyc)=>40 And pAnim(cyc)=<43 Then viable=1 ;standing attacks
 If pAnim(cyc)=46 Then viable=1 ;weapon attack 
 If pAnim(cyc)=>50 And pAnim(cyc)=<51 Then viable=1 ;ground attacks 
 If pAnim(cyc)=60 Or pAnim(cyc)=61 Or pAnim(cyc)=64 Then viable=1 ;standing hurts
 If (pAnim(cyc)=68 Or pAnim(cyc)=69) And pAnimTim(cyc)>45 Then viable=1 ;stumbling 
 If pAnim(cyc)=93 Or pAnim(cyc)=94 Then viable=1 ;pin getup 
 If pAnim(cyc)=>95 And pAnim(cyc)=<99 Then viable=1 ;refereeing
 If pAnim(cyc)=>100 And pAnim(cyc)=<103 Then viable=1 ;standing grapples
 If pAnim(cyc)=>400 And pAnim(cyc)=<403 Then viable=1 ;buckle slump
 If pAnim(cyc)=>404 And pAnim(cyc)=<405 Then viable=1 ;tagging
 ;standing holds
 If pAnim(cyc)=>110 And pAnim(cyc)<199
  If pGrappling(cyc)>0 And pStretched(pGrappling(cyc))>0 Then viable=1
 EndIf
 If pAnim(cyc)=109 And pGrappler(cyc)>0 And pStretched(cyc)>0 Then viable=1
 ;ground grappling issues
 If pAnim(cyc)=200 Or pAnim(cyc)=201 Or pAnim(cyc)=301 Then viable=1 ;ground lunging
 If pAnim(cyc)=205 Or pAnim(cyc)=206 Then viable=1 ;head grappling
 If pAnim(cyc)=305 Or pAnim(cyc)=306 Then viable=1 ;leg grappling
 If pAnim(cyc)=>211 And pAnim(cyc)=<299 Then viable=1 ;head moves
 If pAnim(cyc)=>311 And pAnim(cyc)=<399 And pAnim(cyc)<>317 Then viable=1 ;leg moves
 ;ground vulnerable
 If pAnim(cyc)=37 Then viable=2 ;lying pick-up
 If pAnim(cyc)=62 Or pAnim(cyc)=63 Then viable=2 ;lying hurts
 If pAnim(cyc)=80 Or pAnim(cyc)=82 Or pAnim(cyc)=83 Or pAnim(cyc)=85 Then viable=2 ;lying states
 If pAnim(cyc)=91 Or pAnim(cyc)=92 Then viable=2 ;pinning
 ;ground victim issues
 If pAnim(cyc)=202 
  v=pGrappler(cyc)
  If pAnim(v)<>210 And pAnim(v)<>310 And pAnim(v)<>218 And pAnim(v)<>317 Then viable=2 ;ground victim
 EndIf
 ;mid-flight immunity
 If pAnim(cyc)=42 ;standing attack
  move=charAttack(pChar(cyc),3)
  If pHolding(cyc)>0 And attackWeapon(3,move)=0 Then move=1
  If attackFall(3,move)>0 And pAnimTim(cyc)>10 And pAnimTim(cyc)<attackLength(3,move)-10 Then viable=0
 EndIf
 If pAnim(cyc)=43 ;running attack
  move=charAttack(pChar(cyc),4)
  If attackFall(4,move)>0 And pAnimTim(cyc)>10 And pAnimTim(cyc)<attackLength(4,move)-10 Then viable=0
 EndIf
 If pAnim(cyc)=51 ;standing crush
  If pAnimTim(cyc)>10 And pAnimTim(cyc)<crushLength(3,charCrush(pChar(cyc),3))-10 Then viable=0
 EndIf
 If pAnim(cyc)=52 ;running crush
  If pAnimTim(cyc)>10 And pAnimTim(cyc)<crushLength(4,charCrush(pChar(cyc),4))-10 Then viable=0
 EndIf
 ;standard logic
 If pHidden(cyc)>0 Then viable=0
 Return viable 
End Function

;VIABLE TO BE GRABBED?
Function GrabViable(cyc)
 viable=AttackViable(cyc)
 If pAnim(cyc)=>90 And pAnim(cyc)=<92 Then viable=0
 If pGrappling(cyc)>0 Or pGrappler(cyc)>0 Then viable=0
 If pPlatform(cyc)=>5 Then viable=0
 If pHidden(cyc)>0 Then viable=0 
 Return viable
End Function

;GROUND HURT RESPONSE
Function GroundResponse(cyc)
 ;back responses
 anim=62 
 If pAnim(cyc)=62 Or pAnim(cyc)=80 Then anim=62 
 If pAnim(cyc)=82 And pAnimTim(cyc)=<15 Then anim=62 ;mid-turn
 ;front response 
 If pAnim(cyc)=37 Or pAnim(cyc)=63 Or pAnim(cyc)=83 Or pAnim(cyc)=85 Then anim=63 
 If pAnim(cyc)=82 And pAnimTim(cyc)>15 Then anim=63 ;mid-turn
 ;ground holds
 If pAnim(cyc)=202
  v=pGrappler(cyc)
  If pAnim(v)=311 Then anim=63 ;held on front
 EndIf
 Return anim
End Function

;VIABLE TO COLLPASE?
Function CollapseViable(cyc)
 viable=0
 If pAnim(cyc)=>0 And pAnim(cyc)=<3 Then viable=1 ;standing/moving
 If pAnim(cyc)=5 Or pAnim(cyc)=6 Then viable=1 ;taunting
 If pAnim(cyc)=15 Or pAnim(cyc)=16 Then viable=1 ;climbing walls
 If pAnim(cyc)=23 Or pAnim(cyc)=24 Then viable=1 ;turnbuckle
 If pAnim(cyc)=28 Then viable=1 ;climbing item 
 If pAnim(cyc)=31 Or pAnim(cyc)=32 Then viable=1 ;carrying item 
 If pAnim(cyc)=60 Or pAnim(cyc)=61 Or pAnim(cyc)=64 Then viable=1 ;hurting
 If pAnim(cyc)=>400 And pAnim(cyc)=<402 Then viable=1 ;buckle slump 
 Return viable 
End Function

;VIABLE TO BE PINNED?
Function PinViable(cyc)
 viable=0
 If pAnim(cyc)=37 Then viable=1 ;lying pick-up
 If pAnim(cyc)=62 Or pAnim(cyc)=63 Then viable=1 ;lying hurts
 If pAnim(cyc)=80 Or pAnim(cyc)=82 Or pAnim(cyc)=83 Or pAnim(cyc)=85 Then viable=1 ;lying states
 If pPinner(cyc)>0 Then viable=0
 Return viable 
End Function

;VIABLE TO GRIMACE?
Function GrimaceViable(cyc)
 viable=0
 If pAnim(cyc)=>40 And pAnim(cyc)=<59 Then viable=1 ;attacking
 If pAnim(cyc)=>60 And pAnim(cyc)=<79 Then viable=1 ;hurting/falling
 If pAnim(cyc)=94 Then viable=1 ;pin exasperated
 If pAnim(cyc)=>109 And pAnim(cyc)=<199 Then viable=1 ;move execute/receive
 If pAnim(cyc)=>210 And pAnim(cyc)=<299 Then viable=1 ;head moves
 If pAnim(cyc)=>310 And pAnim(cyc)=<399 Then viable=1 ;leg moves 
 If pAnim(cyc)=202 Then viable=1 ;ground victim
 Return viable 
End Function

;FIND FALLS
Function FindFalls(cyc)
 ;fall off platform
 If pPlatform(cyc)>0 Then ChangeAnim(cyc,Rnd(72,73))
 ;fall out of ring
 If matchOTT=1 And matchCage=0 Then randy=Rnd(0,2) Else randy=Rnd(-1,1)
 If randy=<0 And pPlatform(cyc)=0 And pY#(cyc)=wStage#
  If pZ#(cyc)>wBlockZ1#(1)-2 And pX#(cyc)>wBlockX1#(0) And pX#(cyc)<wBlockX2#(0) And pA#(cyc)=>150 And pA#(cyc)=<210
   If pX#(cyc)>wBlockX1#(0)+35 And pX#(cyc)<wBlockX2#(0)-35 Then anim=Rnd(74,75) Else anim=74
   If matchCage>0 Then anim=75 
   pA#(cyc)=180 : ChangeAnim(cyc,anim) : ShakeRopes(1,1,2) ;north side
   If pAnim(cyc)=75 Then pPlatform(cyc)=1
  EndIf
  If pX#(cyc)>wBlockX1#(2)-2 And pZ#(cyc)>wBlockZ1#(0) And pZ#(cyc)<wBlockZ2#(0) And pA#(cyc)=>60 And pA#(cyc)=<120
   If pZ#(cyc)>wBlockZ1#(0)+35 And pZ#(cyc)<wBlockZ2#(0)-35 Then anim=Rnd(74,75) Else anim=74
   If matchCage>0 Then anim=75
   pA#(cyc)=90 : ChangeAnim(cyc,anim) : ShakeRopes(2,1,2) ;east side
   If pAnim(cyc)=75 Then pPlatform(cyc)=2
  EndIf
  If pZ#(cyc)<wBlockZ2#(3)+2 And pX#(cyc)>wBlockX1#(0) And pX#(cyc)<wBlockX2#(0) And (pA#(cyc)=<30 Or pA#(cyc)=>330)
   If pX#(cyc)>wBlockX1#(0)+35 And pX#(cyc)<wBlockX2#(0)-35 Then anim=Rnd(74,75) Else anim=74
   If matchCage>0 Then anim=75
   pA#(cyc)=0 : ChangeAnim(cyc,anim) : ShakeRopes(3,1,2) ;south side
   If pAnim(cyc)=75 Then pPlatform(cyc)=3
  EndIf
  If pX#(cyc)<wBlockX2#(4)+2 And pZ#(cyc)>wBlockZ1#(0) And pZ#(cyc)<wBlockZ2#(0) And pA#(cyc)=>240 And pA#(cyc)=<300
   If pZ#(cyc)>wBlockZ1#(0)+35 And pZ#(cyc)<wBlockZ2#(0)-35 Then anim=Rnd(74,75) Else anim=74
   If matchCage>0 Then anim=75
   pA#(cyc)=270 : ChangeAnim(cyc,anim) : ShakeRopes(4,1,2) ;west side
   If pAnim(cyc)=75 Then pPlatform(cyc)=4
  EndIf
 EndIf
 ;find buckle slumps
 If pAnim(cyc)=69 Or pAnim(cyc)=71
  If pPlatform(cyc)=0 And pY#(cyc)=wStage# And pAnimTim(cyc)>5
   ResetDummy(cyc) : MoveEntity dummy,0,0,-3
   checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
   FindBuckleSlumps(cyc,checkX#,checkZ#,2)
   If pAnim(cyc)=400
    If pPlatform(cyc)=-1 Then pA#(cyc)=45
    If pPlatform(cyc)=-2 Then pA#(cyc)=315
    If pPlatform(cyc)=-3 Then pA#(cyc)=225
    If pPlatform(cyc)=-4 Then pA#(cyc)=135
    pHP(cyc)=1
   EndIf
  EndIf
 EndIf
End Function

;FIND CLEAR FALL
Function FindClearFall(cyc)
 its=0
 checkA#=CleanAngle#(pA#(cyc)+180)
 While FlightClear(cyc,checkA#,20)=0 And pPlatform(cyc)<100
  tA#=-1 : its=its+1
  If its<100 And pPlatform(cyc)=1 Then tA#=180
  If its<100 And pPlatform(cyc)=2 Then tA#=90
  If its<100 And pPlatform(cyc)=3 Then tA#=0
  If its<100 And pPlatform(cyc)=4 Then tA#=270
  If tA#=>0 Then pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),tA#,5)
  If tA#=-1 Then pA#(cyc)=pA#(cyc)+5
  checkA#=CleanAngle#(pA#(cyc)+180)
 Wend
End Function

;FIND RUNNING OBSTRUCTIONS
Function FindRunningBlocks(cyc)
 contact=0
 ;human contacts
 For v=1 To no_plays
  If cyc<>v And pAnim(v)<>100 And pAnim(v)<>110
   width#=12 : height#=35
   If pX#(cyc)>RealX#(v)-width# And pX#(cyc)<RealX#(v)+width# And pZ#(cyc)>RealZ#(v)-width# And pZ#(cyc)<RealZ#(v)+width# And pY#(cyc)>pY#(v)-height# And pY#(cyc)<pY#(v)+height#
    doit=1
    If AttackViable(v)=2 And (pAnim(v)=62 Or pAnim(v)=63) Then doit=0
    If doit=1
     If InRange(cyc,v,2)
      Pop(cyc,Rnd(2,8),0)
      If AttackViable(v)=2 Then impact=Rnd(4,5) Else impact=Rnd(1,2)
      ProduceSound(p(v),sImpact(impact),22050,0)
      ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
      If AttackViable(v)=2 Then impactY#=pY#(cyc)+3 Else impactY#=pY#(cyc)+23
      CreateSpurt(pX#(v),impactY#,pZ#(v),15,99)
      ScarArea(v,pX#(v),impactY#,pZ#(v),10)
      pHealth(v)=pHealth(v)-1 : pHP(v)=pHP(v)-Rnd(1,5) : pDT(v)=200-pHealth(v)
      If AttackViable(v)=1 
       ImpactChecks(v) : ChangeAnim(v,60)
       pHurtA#(v)=pA#(cyc) : pStagger#(v)=0.2
      EndIf
      If AttackViable(v)=2 Then GroundImpactChecks(v)
      If AttackViable(v)<>2 Then contact=1
     EndIf
    EndIf
   EndIf
  EndIf
 Next
 ;world blocks
 If contact=0 And pAnim(cyc)<>4
  For v=0 To no_blocks
   If pX#(cyc)>wBlockX1#(v) And pX#(cyc)<wBlockX2#(v) And pZ#(cyc)>wBlockZ1#(v) And pZ#(cyc)<wBlockZ2#(v) And pY#(cyc)>wBlockY1#(v) And pY#(cyc)<wBlockY2#(v)
    contact=1
   EndIf
  Next
 EndIf
 ;item contacts
 If contact=0
  For v=1 To no_items
   If ItemProximity(cyc,v,30) And iState(v)=0
    ScanItem(v,0)
    If ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),0) 
     ProduceSound(i(v),sItem,22050,0)
     CreateSpurt(pX#(cyc),pY#(cyc)+iClimb#(iType(v)),pZ#(cyc),15,99)
     iAnim(v)=11 : iAnimTim(v)=0
     contact=2
    EndIf
   EndIf
  Next
 EndIf
 ;act on contact
 If contact>0
  Pop(cyc,Rnd(4,8),0)
  ProduceSound(p(cyc),sImpact(Rnd(4,6)),22050,0)
  ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(cyc),1)
  If contact<>2 Then CreateSpurt(pX#(cyc),pY#(cyc)+18,pZ#(cyc),15,99)
  ScarArea(cyc,pX#(cyc),pY#(cyc)+18,pZ#(cyc),10)
  pHealth(cyc)=pHealth(cyc)-1 : pHP(cyc)=0 : pDT(cyc)=200-pHealth(cyc)
  randy=Rnd(0,2)
  If randy>0 And contact=2 Then pA#(cyc)=pA#(cyc)+180
 EndIf
End Function

;RISK WALL FALL
Function RiskWallFall(cyc,risk)
 ;establish risk
 chance=charAgl(pChar(cyc))*risk
 If matchState=3 And pRole(cyc)=0 And matchCage>0 And matchOTT=1 
  If pPlatform(cyc)=>101 And pPlatform(cyc)=<104 Then chance=chance/2
 EndIf
 ;execute risk
 randy=Rnd(0,chance)
 If randy=0 And matchState=>3 And pY#(cyc)>pGround#(cyc)+10
  ProduceSound(p(cyc),sPain(Rnd(2,3)),GetVoice(cyc),1)
  pHP(cyc)=0
 EndIf
End Function