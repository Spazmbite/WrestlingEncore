;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: MEETINGS ------------------------
;//////////////////////////////////////////////////////////////////////////////

;----------------------------------------------------------------
;/////////////////// RISK FORMAL MEETINGS ///////////////////////
;----------------------------------------------------------------
Function RiskFormalMeetings()
 ;nothing by default
 fed=charFed(gamChar(slot))
 negTopic=0 : negChar=0
 ;STANDARD OPPORTUNITIES
 randy=Rnd(0,40)
 ;allegiance suggestions 
 If randy=<1 And fed=<6 And charHeel(gamChar(slot))=0 Then negTopic=31 : negChar=fedBooker(fed) ;turn heel
 If randy=<1 And fed=<6 And charHeel(gamChar(slot))=1 Then negTopic=32 : negChar=fedBooker(fed) ;turn face
 ;team suggestions
 If charFriend(gamChar(slot))>0 And TitleHolder(gamChar(slot),0)=0 And fed<>5
  If randy=>2 And randy=<3 And fed=<6 And gamFormat(slot)=1 Then negTopic=33 : negChar=fedBooker(fed) ;form team
  If randy=>2 And randy=<3 And fed=<6 And gamFormat(slot)=2 Then negTopic=34 : negChar=fedBooker(fed) ;break team
 EndIf
 ;health issues
 If (randy=4 And charHealth(gamChar(slot))=<60) Or (randy=5 And charHealth(gamChar(slot))=<40) 
  If charInjured(gamChar(slot))=0 Then negTopic=36 : negChar=fedBooker(fed) ;take night off to recover?
 EndIf
 If gamVariable(slot)=1 Or gamOldVariable(slot)=1 Or gamVariable(slot)=2 Or gamOldVariable(slot)=2
  If randy=>6 And randy=<7 And charInjured(gamChar(slot))=0 Then negTopic=39 : negChar=fedBooker(fed) ;drug scandals!
 EndIf
 ;contract issues
 If randy=8 And charContract(gamChar(slot))>0 And fedBank(fed)<1000000 Then negTopic=40 : negChar=fedBooker(fed) ;release offer
 If randy=9 And charContract(gamChar(slot))>0 And charSalary(gamChar(slot))>100 And fedBank(fed)<1000000 
  negTopic=41 : negChar=fedBooker(fed) ;pay-cut suggestion 
 EndIf
 If fed=<6 And charPop(gamChar(slot))>fedPop(fed)-10
  If randy=10 And fedSize(fed)>10 Then negTopic=42 : negChar=fedBooker(fed) ;opinion on internal
  If randy=11 And fedSize(fed)<optFedLim Then negTopic=43 : negChar=fedBooker(fed) ;opinion on external
 EndIf
 ;opportunities
 If fed=<6 And charPop(gamChar(slot))=>60 And gamResult(slot,GetDate()-1)=3 And gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<2
  If randy=12 Then negTopic=44 : negChar=fedBooker(8) ;endorsement
  If TitleHolder(gamChar(slot),0)=0 And TitleHolder(charEnemy(gamChar(slot)),0)=0
   If randy=13 And gamFormat(slot)=1 And gamWorldTitles(slot,fed)=0
    If charPop(gamChar(slot))>charPop(fedChampWorld(fed))-5 Or charPop(gamChar(slot))=>fedPop(fed) 
     negTopic=45 : negChar=fedBooker(fed) ;world title push
    EndIf
   EndIf
   If randy=14 And gamFormat(slot)=1 And gamInterTitles(slot,fed)=0 Then negTopic=46 : negChar=fedBooker(fed) ;inter title push
   If randy=15 And gamFormat(slot)=2 And gamTagTitles(slot,fed)=0 Then negTopic=47 : negChar=fedBooker(fed) ;tag title push
  EndIf
  If randy=16 And charPop(gamChar(slot))=>75 And charContract(gamChar(slot))>4 And GetDate()=<42
   negTopic=54 : negChar=fedBooker(8) ;movie offer
  EndIf
  If randy=17 Then negTopic=55 : negChar=fedBooker(8) ;magazine offer
 EndIf 
 ;teamed with enemy!
 If randy=18 And fed=<6 And fed<>5 And gamFormat(slot)=1 And gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<2
  If charEnemy(gamChar(slot))>0 And gamOpponent(slot,GetDate()-1)=charEnemy(gamChar(slot)) And TitleHolder(charEnemy(gamChar(slot)),3)=0
   negTopic=48 : negChar=fedBooker(fed)
  EndIf
 EndIf
 ;arena issyes
 If gamSchedule(slot,GetDate())>0 And gamPromo(slot,GetDate())<>74
  If randy=19 And gamArenaPreset(slot)=<14 Then negTopic=50 : negChar=fedBooker(fed) ;power failure!
  If randy=20 And fed=<6 And fedRep(fed)<90 Then negTopic=51 : negChar=fedBooker(fed) ;weapon ban
  If randy=21 Then negTopic=52 : negChar=fedBooker(fed) ;no items
 EndIf
 ;favourable ref offer
 If randy=20 And fed=<6 And gamSchedule(slot,GetDate())>0 And charInjured(gamChar(slot))=0 And gamBank(slot)>0
  negTopic=53 : negChar=fedBooker(fed)
 EndIf
 ;injury issues
 If charInjured(gamChar(slot))>0 And fed=<6
  If randy=21 Or (randy=22 And gamClause(slot,3)>0) Then negTopic=56 : negChar=fedBooker(fed) ;accused of faking 
  If randy=23 Or (randy=24 And charPop(gamChar(slot))=>70) Then negTopic=57 : negChar=fedBooker(fed) ;paid to work through
 EndIf 
 ;asked to change gimmick
 If fed=<6
  If randy=25 And gamClause(slot,1)>0 Then negTopic=59 : negChar=fedBooker(fed) ;costume
  If randy=26 Then negTopic=60 : negChar=fedBooker(fed) ;attacks
  If randy=27 Then negTopic=61 : negChar=fedBooker(fed) ;moves
 EndIf
 ;MISSIONS & URGENT ISSUES
 ;mission assignments
 If gamMission(slot)=0 And GetDate()=<42 And gamExperience(slot,fed)>4 And charInjured(gamChar(slot))=0 And (charContract(gamChar(slot))>6 Or fed=7)
  randy=Rnd(0,40)
  If randy=1 And charPop(gamChar(slot))<fedPop(fed)-5 Then negTopic=81 : negChar=fedBooker(fed) ;improve popularity
  If charAge(gamChar(slot))<optOldAge
   If randy=2 And charStr(gamChar(slot))<80 Then negTopic=82 : negChar=fedBooker(fed) ;improve strength
   If randy=3 And charSkl(gamChar(slot))<80 Then negTopic=83 : negChar=fedBooker(fed) ;improve skill
   If randy=4 And charAgl(gamChar(slot))<80 Then negTopic=84 : negChar=fedBooker(fed) ;improve agility
   If randy=5 And charStam(gamChar(slot))<80 Then negTopic=85 : negChar=fedBooker(fed) ;improve stamina
   If randy=6 And charTough(gamChar(slot))<80 Then negTopic=86 : negChar=fedBooker(fed) ;improve toughness
  EndIf
  If randy=7 And charAtt(gamChar(slot))<70 Then negTopic=87 : negChar=fedBooker(fed) ;improve attitude
  If randy=8 And fed=<6 And gamWorldTitles(slot,fed)=0 And gamInterTitles(slot,fed)=0 And gamTagTitles(slot,fed)=0
   negTopic=88 : negChar=fedBooker(fed) ;become champion
  EndIf
  If randy=9 Then negTopic=91 : negChar=fedBooker(fed) ;killer instinct!
  If gamBank(slot)=<0 Then negTopic=89 : negChar=fedBooker(fed) ;get out of debt!
  If randy=>10 And randy=<20 And fed=7 And gamExperience(slot,7)=>12 Then negTopic=90 : negChar=fedBooker(fed) ;get a deal!
 EndIf
 ;mission completions!
 If gamMission(slot)=1 And charPop(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved popularity
 If gamMission(slot)=2 And charStr(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved strength
 If gamMission(slot)=3 And charSkl(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved skill
 If gamMission(slot)=4 And charAgl(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved agility
 If gamMission(slot)=5 And charStam(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved stamina
 If gamMission(slot)=6 And charTough(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved toughness
 If gamMission(slot)=7 And charAtt(gamChar(slot))=>gamTarget(slot) Then negTopic=100 : negChar=fedBooker(fed) ;improved attitude 
 If gamMission(slot)=8 And TitleHolder(gamChar(slot),0)>0 Then negTopic=100 : negChar=fedBooker(fed) ;gained title
 If gamMission(slot)=9 And gamBank(slot)>0 Then negTopic=100 : negChar=fedBooker(fed) ;got out of debt
 If gamMission(slot)=11 And charInjured(gamOpponent(slot,GetDate()-1))>0 Then negTopic=100 : negChar=fedBooker(fed) ;injured somebody! 
 ;mission failures!
 If gamMission(slot)>0 And GetDate()=>gamDeadline(slot) And negTopic<>100
  If fed=7 Then negTopic=102 Else negTopic=101 
  negChar=fedBooker(fed) 
 EndIf
 ;event participation
 If charInjured(gamChar(slot))=0 And negTopic<100
  If gamSchedule(slot,GetDate())=3 Then negTopic=37 : negChar=fedBooker(fed)
  If gamSchedule(slot,GetDate())=4 Then negTopic=38 : negChar=fedBooker(fed)
 EndIf
 ;proceed
 If negTopic>0 And negChar>0 Then screen=53 : Meeting() 
End Function

;----------------------------------------------------------------
;/////////////////// RISK CASUAL MEETINGS ///////////////////////
;----------------------------------------------------------------
Function RiskCasualMeetings()
 ;nothing by default
 fed=charFed(gamChar(slot))
 negTopic=0 : negChar=0
 randy=Rnd(-1,40)
 ;allegiance suggestions
 If randy=<0 And charHeel(gamChar(slot))=0 Then negTopic=1 : negChar=RandomVisitor(-1) ;turn heel
 If randy=<0 And charHeel(gamChar(slot))=1 Then negTopic=2 : negChar=RandomVisitor(-1) ;turn face
 ;team suggestions 
 If charFriend(gamChar(slot))>0 And TitleHolder(gamChar(slot),0)=0 And fed<>5
  If randy=>1 And randy=<2 And gamFormat(slot)=1 Then negTopic=3 : negChar=charFriend(gamChar(slot)) ;form team
  If randy=>1 And randy=<2 And gamFormat(slot)=2 Then negTopic=4 : negChar=charFriend(gamChar(slot)) ;break team
 EndIf
 ;management issues
 If randy=>3 And randy=<4 And charManager(gamChar(slot))=0 And gamBank(slot)>0 ;management offer
  its=0
  Repeat
   negChar=fedRoster(fed,Rnd(1,fedSize(fed)))
   its=its+1
   If its>100 Then negChar=0
  Until SuitableManager(negChar) Or negChar=0
  negTopic=5
 EndIf
 If charManager(gamChar(slot))>0 And charManager(gamChar(slot))<>charFriend(gamChar(slot))
  If randy=5 Or (randy=6 And (charSalary(gamChar(slot))<1000 Or SuitableManager(charManager(gamChar(slot)))=0))
   negTopic=6 : negChar=charManager(gamChar(slot)) ;lose manager
  EndIf
 EndIf
 ;health issues
 If charInjured(gamChar(slot))=0 And gamBank(slot)>0 
  If (randy=7 And charHealth(gamChar(slot))=<60) Or (randy=8 And charHealth(gamChar(slot))=<40) 
   negTopic=8 : negChar=RandomVisitor(1) ;offered painkillers
  EndIf
  If randy=9 Or (randy=10 And charTrainCourse(gamChar(slot))>0) 
   negTopic=9 : negChar=RandomVisitor(1) ;offered steroids
  EndIf
 EndIf
 ;backstage politics
 If randy=11 And charFriend(gamChar(slot))=0 And gamBank(slot)>0 Then negTopic=10 : negChar=RandomVisitor(1) ;social offer
 If charHealth(gamChar(slot))>25 And charInjured(gamChar(slot))=0
  If randy=12 And gamSchedule(slot,GetDate())>0 And gamBank(slot)>0 Then negTopic=11 : negChar=RandomVisitor(-1) ;beating threat
  If randy=13 And charFriend(gamChar(slot))>0 Then negTopic=12 : negChar=RandomVisitor(-1) ;sacrifice friend threat
  If randy=14 And charEnemy(gamChar(slot))>0 And gamOpponent(slot,GetDate()-1)=charEnemy(gamChar(slot))
   negTopic=13 : negChar=charEnemy(gamChar(slot)) ;enemy wants to brawl
  EndIf
  If randy=15 ;hired to assault bully
   its=0
   Repeat
    negChar=fedRoster(fed,Rnd(1,fedSize(fed)))
    If its>100 Then negChar=0 Else its=its+1
   Until negChar=0 Or (charEnemy(negChar)>0 And CharBusy(charEnemy(negChar))=0 And CharBusy(negChar)=0)
   If negChar>0 Then negTopic=14 
  EndIf
  If randy=16 And fed=<6 And gamSchedule(slot,GetDate())>0 And gamInterProm(slot,GetDate())=0 ;invasion!
   Repeat
    gamInterProm(slot,GetDate())=Rnd(1,6)
   Until gamInterProm(slot,GetDate())<>fed
   negChar=fedBooker(fed) : negTopic=15
  EndIf
  If randy=17 And charSalary(gamChar(slot))>0 And charSalary(gamChar(slot))<5000 
   negTopic=16 : negChar=RandomVisitor(0) ;riot for better pay!
  EndIf
 EndIf
 ;shoot suggestion
 If randy=18 And fed=<6 And gamSchedule(slot,GetDate())>0 Then negTopic=17 : negChar=RandomVisitor(0)
 ;enemy suggestions
 If gamFormat(slot)=1 And charEnemy(gamChar(slot))>0 And gamOpponent(slot,GetDate()-1)=charEnemy(gamChar(slot)) And charInjured(charEnemy(gamChar(slot)))=0 And charInjured(gamChar(slot))=0
  If randy=19 Then negTopic=18 : negChar=charEnemy(gamChar(slot)) ;enemy calls a truce
  If randy=20 And fed<>5 And TitleHolder(charEnemy(gamChar(slot)),0)=0 And TitleHolder(gamChar(slot),0)=0 
   negTopic=19 : negChar=charEnemy(gamChar(slot)) ;enemy suggests a team!
  EndIf
  If randy=21 And fed=<6 And GetDate()=<47 And charHairStyle(charEnemy(gamChar(slot)),1)>1 And charHairStyle(gamChar(slot),1)>1
   negTopic=20 : negChar=charEnemy(gamChar(slot)) ;enemy wants hair!
  EndIf
  If randy=22 And fed=<6 And GetDate()=<47 And charContract(charEnemy(gamChar(slot)))>1 And charContract(gamChar(slot))>1
   negTopic=21 : negChar=charEnemy(gamChar(slot)) ;enemy wants career!
  EndIf
 EndIf
 ;corruption
 If gamOpponent(slot,GetDate())=0 And charInjured(gamChar(slot))=0 And gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<2 
  If randy=23 And gamBank(slot)>0 Then negTopic=22 : negChar=RandomVisitor(-1) ;opponent offers to lay down
  If randy=24 Then negTopic=23 : negChar=RandomVisitor(-1) ;opponent asks you to lay down
 EndIf
 ;friend turns on you
 If randy=25 And charFriend(gamChar(slot))>0 And gamOpponent(slot,GetDate())=0 And gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<2
  If charPop(charFriend(gamChar(slot)))=<charPop(gamChar(slot)) Then negTopic=24 : negChar=charFriend(gamChar(slot)) ;inferior friend
  If charPop(charFriend(gamChar(slot)))>charPop(gamChar(slot)) Then negTopic=25 : negChar=charFriend(gamChar(slot)) ;superior friend
 EndIf 
 ;team-mate wants to synchronize
 If randy=26 And gamFormat(slot)=2 And GetRace(gamChar(slot))=GetRace(charFriend(gamChar(slot)))
  negTopic=26 : negChar=charFriend(gamChar(slot))
 EndIf
 ;tutorials
 If randy=27 And optFX=>2 Then negTopic=70 ;fire use
 If randy=28 Then negTopic=71 ;weapon use
 If randy=29 Then negTopic=72 ;buckle use 
 If randy=30 And charManager(gamChar(slot))=0 Then negTopic=73 ;managers
 If randy=31 And charContract(gamChar(slot))>0 Then negTopic=74 ;pay-off's
 If negTopic=>70 And negTopic=<79 
  negChar=RandomVisitor(-1)
  If charFriend(gamChar(slot))>0 Then negChar=charFriend(gamChar(slot))
 EndIf
 ;proceed
 If negTopic>0 And negChar>0 Then screen=53 : Meeting() 
End Function

;----------------------------------------------------------------
;///////////////// RISK POST-MATCH MEETINGS /////////////////////
;----------------------------------------------------------------
Function RiskLateMeetings()
 ;nothing by default
 fed=charFed(gamChar(slot))
 negTopic=0 : negChar=0
 ;find opportunities
 randy=Rnd(0,10)
 If fed=7 And gamSchedule(slot,GetDate())=<1 Then randy=Rnd(0,20)
 If randy=0 Then negTopic=7 : negChar=RandomVisitor(1) ;manage someone
 If randy=1 Then negTopic=35 : negChar=fedBooker(fed) ;referee a match
 If randy=2 Then negTopic=49 : negChar=fedBooker(fed) ;fight another match
 ;pay for damages
 If matchDamage=>250 And gamBank(slot)>0 And GetDate()=<47
  If randy=>3 And randy=<4 Then negTopic=58 : negChar=fedBooker(fed) 
 EndIf
 ;risk team break up
 If gamFormat(slot)=2 And charFriend(gamChar(slot))>0 And TitleHolder(gamChar(slot),0)=0 And gamResult(slot,GetDate())<3
  If randy=>5 And randy=<6 Then negTopic=4 : negChar=charFriend(gamChar(slot))
 EndIf
 ;proceed
 If negTopic>0 And negChar>0 Then screen=53
End Function

;GET RANDOM VISITOR
Function RandomVisitor(task) ;-1=potential enemy, 0=100% random, 1=potential friend
 fed=charFed(gamChar(slot))
 char=0 : its=0
 Repeat
  satisfied=1
  char=fedRoster(fed,Rnd(1,fedSize(fed)))
  If task=-1 And charHeel(char)=charHeel(gamChar(slot)) Then satisfied=0 
  If task=1 And charHeel(char)<>charHeel(gamChar(slot)) Then satisfied=0
  If char=fedBooker(fed) Or DesignatedRef(char) Then satisfied=0 
  If CharBusy(char)>0 Then satisfied=0 
  its=its+1
  If its>1000 Then char=fedBooker(fed) : satisfied=1
 Until satisfied=1
 Return char
End Function

;---------------------------------------------------------------
;///////////////// LOAD MEETING SETTING ////////////////////////
;---------------------------------------------------------------
Function PrepareMeeting()
 ;ATMOSPHERE
 ;location
 LoadSet()
 ;camera
 cam=CreateCamera()
 CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
 camX#=-90 : camY#=45 : camZ#=-150
 If camFoc=2 Then camX#=20 : camY#=40 : camZ#=25
 If negSetting=2 Then camX#=230 : camY#=20 : camZ#=600 ;locker room offset
 If negSetting=3 Then camX#=-190 : camY#=20 : camZ#=650 ;backstage offset 
 If negSetting=4 Then camX#=-55 : camY#=40 : camZ#=-25 ;hospital offset
 PositionEntity cam,camX#,camY#,camZ#
 camType=1 : camOverride=0 : camTempTim=0
 ;assistants
 camPivot=CreatePivot()
 dummy=CreatePivot()
 ;lighting
 AmbientLight 200,200,200
 light=CreateLight(1) 
 LightColor light,220,200,180
 PositionEntity light,-20,70,-20
 ;power cut!
 If negTopic=50
  AmbientLight 30,30,30
  LightColor light,100,90,80
 EndIf
 ;CHARACTERS
 ;calculate cast
 For count=1 To optPlayLim
  pChar(count)=0
 Next
 no_plays=2
 pChar(1)=gamChar(slot)
 pChar(2)=negChar
 ;add 2nd boss in negotiations
 If screen=>52 And screen=<53 And negSetting=1 And fedBooker(charFed(gamChar(slot)))>0 And negChar<>fedBooker(charFed(gamChar(slot)))
  no_plays=no_plays+1
  pChar(no_plays)=fedBooker(charFed(gamChar(slot)))
 EndIf
 ;positioning
 If negSetting=1 ;boardroom
  pX#(1)=-33 : pY#(1)=4 : pZ#(1)=-85 : pA#(1)=0 : pAnim(1)=1
  pX#(2)=-34 : pY#(2)=4 : pZ#(2)=-3 : pA#(2)=180 : pAnim(2)=1
  pX#(3)=-50 : pY#(3)=2 : pZ#(3)=-100 : pA#(3)=340 : pAnim(3)=10
 EndIf
 If negSetting=2 Or negSetting=3 ;locker room
  If negSetting=3 Then pX#(1)=-150 Else pX#(1)=170
  pY#(1)=2 : pZ#(1)=749 : pA#(1)=215 : pAnim(1)=10
  pX#(2)=pX#(1)+20 : pY#(2)=2 : pZ#(2)=736 : pA#(2)=80 : pAnim(2)=11
 EndIf
 If negSetting=4 ;hospital
  pX#(1)=31 : pY#(1)=18 : pZ#(1)=116 : pA#(1)=180 : pAnim(1)=0
  pX#(2)=10 : pY#(2)=2 : pZ#(2)=130 : pA#(2)=240 : pAnim(2)=10
 EndIf
 ;load models
 For cyc=1 To no_plays
  LoadMeeter(cyc,pChar(cyc))
 Next
 ;point light
 PointEntity light,p(1)
End Function

;---------------------------------------------------------------
;////////////////// LOAD MEETING SCENE /////////////////////////
;---------------------------------------------------------------
Function LoadSet()
 ;boardroom
 If negSetting=1 
  world=LoadAnimMesh("World/Boardroom/Boardroom.3ds")
  tPicture=LoadTexture("Graphics/Promotions/Fed0"+fed+".bmp")
  If tPicture>0 Then EntityTexture FindChild(world,"Picture02"),tPicture,0,1
 EndIf
 ;locker room
 If negSetting=2 Or negSetting=3
  PrepareArena()
  If arenaType=1 Then world=LoadAnimMesh("World/Arena/Arena.3ds")
  If arenaType=2 Then world=LoadAnimMesh("World/Arena/Hall.3ds")
  DecorateArena()
  tPicture=LoadTexture("Graphics/Promotions/Fed0"+fed+".bmp")
  If tPicture>0 Then EntityTexture FindChild(world,"Screen02"),tPicture,0,1
  If screen=54
   HideEntity FindChild(world,"Screen02")
   HideEntity FindChild(world,"Monitor")
   HideEntity FindChild(world,"Cable") 
  EndIf
 EndIf
 ;hospital
 If negSetting=4 
  world=LoadAnimMesh("World/Hospital/Hospital.3ds")
 EndIf
End Function

;---------------------------------------------------------------
;//////////////// LOAD MEETING CHARACTER ///////////////////////
;---------------------------------------------------------------
Function LoadMeeter(cyc,char)
 ;model
 p(cyc)=LoadAnimMesh("Models/Bodies/"+textModel$(charModel(char,2))+".3ds")
 pSeq(cyc,600)=LoadAnimSeq(p(cyc),"Models/Sequences/Gestures.3ds")
 pSeq(cyc,601)=LoadAnimSeq(p(cyc),"Models/Sequences/Movement.3ds")
 ;seated gestures
 If pAnim(cyc)=0 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1520,1550,pSeq(cyc,600)) ;lying down
 If pAnim(cyc)=1 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),110,140,pSeq(cyc,600)) ;sitting
 If pAnim(cyc)=2 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),340,370,pSeq(cyc,600)) ;dead slouch!
 ;standing gestures
 If pAnim(cyc)=>10
  randy=Rnd(0,3) 
  If pAnim(cyc)=11 Then randy=Rnd(-1,6) 
  If charInjured(char)>0 Or charHealth(char)=<optTired Then randy=99
  If randy=<0 Then LoadStanceSequence(cyc,1)
  If randy=1 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),0,30,pSeq(cyc,600)) ;standing (still)
  If randy=2 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1860,1890,pSeq(cyc,600)) ;standing (hands on hips)
  If randy=3 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1820,1850,pSeq(cyc,600)) ;standing (arms folded)
  If randy=4 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1900,1930,pSeq(cyc,600)) ;standing (clasped hands)
  If randy=5 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1780,1810,pSeq(cyc,600)) ;standing (exasperated)
  If randy=6 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1710,1770,pSeq(cyc,600)) ;standing (explaining)
  If randy=99 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),3110,3140,pSeq(cyc,601)) ;injured stance
 EndIf
 ;appearance
 If pAnim(cyc)=>10 Then scaler#=charHeight(char)*0.0025 Else scaler#=0.03
 ScaleEntity p(cyc),0.35+scaler#,0.35+scaler#,0.35+scaler#
 ApplyCostume(cyc,char,2)
 pEyes(cyc)=charEyes(char) : pOldEyes(cyc)=-1
 pHidden(cyc)=0
 ;hide weapons
 For v=1 To weapList
  HideEntity FindChild(p(cyc),weapFile$(v))
 Next
 ;initiation
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(cyc),0,pA#(cyc),0
 speeder#=Rnd(0.1,0.3)
 If pAnim(cyc)=2 Then speeder#=0.01 
 If pAnim(cyc)=>10 Then speeder#=Rnd(0.05,0.2)
 Animate p(cyc),1,speeder#,pSeq(cyc,1),0
 ;shadow
 pShadow(cyc)=LoadSprite("World/Sprites/Shadow.bmp",12)
 ScaleSprite pShadow(cyc),10,10
 RotateEntity pShadow(cyc),90,0,0
 SpriteViewMode pShadow(cyc),2
 EntityAlpha pShadow(cyc),0.1
 PositionEntity pShadow(cyc),pX#(cyc),pY#(cyc)+0.4,pZ#(cyc)
End Function

;----------------------------------------------------------------------------
;//////////////////////// 53. MEETING PROCESS ///////////////////////////////
;----------------------------------------------------------------------------
Function Meeting()
;loader
Loader("Please Wait","Meeting "+charName$(negChar))
ChannelVolume chTheme,0.5
;load setting
fed=charFed(negChar)
negSetting=Rnd(2,3)
If negTopic=>30 Then negSetting=1
If negTopic=>70 And negTopic=<79 Then negSetting=Rnd(2,3)
camFoc=2
PrepareMeeting()
;get pay-off's
negPayOff=0
If negTopic=6 Then negPayOff=gamWorth(slot)*4 ;management demand
If negTopic=7 ;management earnings
 negPayOff=charSalary(negChar) 
 If negPayOff<100 Then negPayOff=100
EndIf
If negTopic=8 Then negPayOff=1000 ;painkiller costs
If negTopic=9 Then negPayOff=1000 ;steroid costs
If negTopic=10 Then negPayOff=100 ;social costs
If negTopic=11 ;bully demand
 negPayOff=gamBank(slot)/10
 If negPayOff<100 Then negPayOff=100
 If negPayOff>10000 Then negPayOff=10000
EndIf
If negTopic=14 ;assault pay-off
 negPayOff=charSalary(negChar)*2
 If negPayOff<500 Then negPayOff=500
EndIf
If negTopic=22 Or negTopic=23 Or negTopic=53 Or negTopic=55 Or negTopic=57 ;bribe to you
 negPayOff=gamWorth(slot)*2
 If negPayOff<500 Then negPayOff=500
EndIf
If negTopic=35 Or negTopic=49 ;referee / 2nd match earnings 
 negPayOff=charSalary(gamChar(slot))  
 If negPayOff<100 Then negPayOff=100
EndIf
If negTopic=39 Then negPayOff=10000 ;drug fines
If negTopic=44 Then negPayOff=10000 ;endorsement pay-off
If negTopic=54 Then negPayOff=50000 ;movie earnings
If negTopic=58 Then negPayOff=matchDamage ;damage estimate
If negTopic=59 Or negTopic=60 Or negTopic=61 ;paid to change gimmick
 negPayOff=gamWorth(slot)*2
 If negPayOff<500 Then negPayOff=500
EndIf
If negTopic=100 ;mission reward
 negPayOff=gamWorth(slot)*5
 If negPayOff<1000 Then negPayOff=1000
EndIf
If negPayOff>0 Then negPayOff=RoundOff(negPayOff,100)
;third party
If negTopic=42 ;internal wrestler
 Repeat
  pChar(9)=fedRoster(fed,Rnd(1,fedSize(fed)))
 Until CharBusy(pChar(9))=0 And pChar(9)<>negChar
EndIf
If negTopic=43 ;external wrestler
 Repeat
  pChar(9)=Rnd(1,no_chars)
 Until CharBusy(pChar(9))=0 And charFed(pChar(9))<>fed And charFed(pChar(9))=<8
EndIf
;reset situation
negChances=Rnd(0,5)
negTim=0 : negVerdict=0
negStage=0 : negSubStage=0
;frame ratings
period=1000/FPS  
time=MilliSecs()-period
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
go=0 : gotim=0 : keytim=0
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
    gotim=gotim+1 : negTim=negTim+1 
    ;speed-up's
	If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	 If negStage<>1 And keytim=0 Then negTim=negTim+50 : keytim=5 ;: PlaySound sMenuBrowse
	EndIf 
	;leave
	If negStage=2 And negTim>350 Then go=1
	
	;CHOICE CONFIRMATION
	If keytim=0 And negStage=1
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>2 Then foc=2
	 If foc<1 Then foc=1 
	 ;proceed 
     If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  PlaySound sMenuGo : negStage=2
	  negTim=0 : keytim=10
	  If foc=1 Then negVerdict=1
	  If foc=2 Or KeyDown(1) Then negVerdict=-1
	  ;sales detour
	  ;If negVerdict=-1 And negTopic=99
	   ;randy=Rnd(3,5)
	   ;negPayOff=negPayOff+(negPayOff/randy)
	   ;negChances=negChances-1
	   ;If negChances>0 Then negTim=325 : negStage=0 : negSubStage=1 : negVerdict=0
	  ;EndIf
	 EndIf
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
 ;determine font
 SetFont font(3)
 If GraphicsWidth()=>1024 Then SetFont font(4)
 ;------------------------ INFORMAL TOPICS (1-30) ----------------------------
 ;1. WRESTLER SUGGESTS HEEL TURN
 If negTopic=1
  optionA$="Yes, turn Heel!" : optionB$="No, remain Face..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", don't you ever get",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("tired of doing everything by the book?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("This isn't an office job - it's a fight for survival!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Nice guys really do finish last in this business...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("But if you wanna grow a set of balls and be a man,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("come join my crew and live life on the wild side!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    charHeel(gamChar(slot))=1 : charEyes(gamChar(slot))=1 : BecomeFriends(gamChar(slot),negChar,1)
    If gamPromo(slot,GetDate())=0 And fed=<6 
     randy=Rnd(0,1)
     If randy=0 Then gamPromo(slot,GetDate())=26
     If randy=1 Then gamMatch(slot,GetDate())=12 : gamPromo(slot,GetDate())=64
    EndIf
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("Haha! I knew there was a bad-ass in there somewhere!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You're gonna love the way we operate, brother...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then BecomeEnemies(gamChar(slot),negChar,0) : AdjustAttitude(gamChar(slot),100,1) : charged=1
   Outline("Yeah, they told me you were a loser - and now i know!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You better watch your back, cos we certainly won't be...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;2. WRESTLER SUGGESTS FACE TURN
 If negTopic=2
  optionA$="Yes, turn Face!" : optionB$="No, remain Heel..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hey, "+charName$(gamChar(slot))+", why do you go through",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("your career with a chip on your shoulder?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Wrestling isn't a prison sentence - it's a SPORT!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("If you acted like an athlete, you'd appreciate that...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("And so would the fans. They'd love to get behind you!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Why don't you join us and see for yourself?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    charHeel(gamChar(slot))=0 : charEyes(gamChar(slot))=3 : BecomeFriends(gamChar(slot),negChar,1)
    If gamPromo(slot,GetDate())=0 And fed=<6 
     randy=Rnd(0,1)
     If randy=0 Then gamPromo(slot,GetDate())=25
     If randy=1 Then gamMatch(slot,GetDate())=12 : gamPromo(slot,GetDate())=64
    EndIf
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Great! I knew there was a heart in there somewhere!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Your life is about to change for the better...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Well, that's a shame. I thought you had potential,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but i guess you'll waste your life like the others...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;3. FRIEND SUGGESTS TEAM FORMATION
 If negTopic=3
  optionA$="Yes, form a team!" : optionB$="No, remain single..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", we get on well right?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("So listen, i've got a proposal for you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Why don't the two of us form a proper team?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We'd be tearing this company up in no time!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamFormat(slot)=2 : BecomeTeam(gamChar(slot),negChar) 
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=3
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Excellent! You won't regret this, i promise!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Together, we'll both become better than ever...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then BecomeEnemies(gamChar(slot),negChar,1) : AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Oh, i get it - you think you're better than me?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Fine! Let's see how far you get without my help...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;4. TEAM-MATE SUGGESTS BREAK-UP
 If negTopic=4
  optionA$="Yes, break up..." : optionB$="No, remain a team!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", you must have noticed",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("that this team isn't working out too well?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("No offence, but i don't feel it's helping my career.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Do you mind if we split up and try going solo?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then gamFormat(slot)=1 : AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Good, i'm glad we both feel the same way!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("It'll be great to get back to singles competition...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("You really see a future in this? If you say so,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but things better pick up for us damn quickly!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;5. WANT A MANAGER?
 If negTopic=5
  optionA$="Yes, hire manager!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", what do you hope",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to achieve without a manager by your side?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("A helping hand at ringside can often be the",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("difference between success and failure!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("I'd be happy to help you out myself if you'd like?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("All it would cost is 20% of the winnings...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    BecomeFriends(gamChar(slot),negChar,0) : charManager(gamChar(slot))=negChar
    If gamPromo(slot,GetDate())=0 Then gamPromo(slot,GetDate())=40  
    PlaySound sCash : AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Excellent! I'm glad we could come to an arrangement.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You'll never have to lose another match again...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Alright, i was only trying to help you out!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Don't blame me the next time you get outnumbered...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;6. MANAGER THREATENS TO LEAVE
 If negTopic=6
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No, release manager..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Listen, "+charName$(gamChar(slot))+", i'm really not happy",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("about being your manager at the moment...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I've got plenty of other things that need my attention,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and managing you has become the least of my concerns...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("If you want to keep me on, i'm going to have",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to ask you for a $"+GetFigure$(negPayOff)+" bonus upfront?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("OK, this money should make the job a little easier!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'd be happy to manage you for a few more matches...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,0) : charManager(gamChar(slot))=0
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("So it turns out i'm not worth much to you anyway?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Next time you see me, i'll be working against you!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;7. WRESTLER ASKS YOU TO ASSIST
 If negTopic=7
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", would you mind doing",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("me a favour before you leave for the night?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("I've got the biggest match of my life in a minute,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but i'm not sure that i can make it on my own!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Would you consider assisting me at ringside?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("There's $"+GetFigure$(negPayOff)+" in it for you if you do!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    BecomeFriends(gamChar(slot),negChar,0) : charManager(negChar)=gamChar(slot)
    PlaySound sCash : AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Great! With you by my side i can't lose!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Now let's get out there and chalk up a win...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Damn, you were my only hope! I haven't got time",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to ask anybody else! I guess my fate is sealed...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;8. WRESTLER OFFERS PAIN RELIEVE
 If negTopic=8
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", you look like crap!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I think you've been working too hard...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("This may be a tough business, but you don't",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("have to go through it in pain all the time...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("A few painkillers can make you feel great!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I can hook up right now for just $"+GetFigure$(negPayOff)+"?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0    
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff : gamVariable(slot)=1
    charHealth(gamChar(slot))=100 : BecomeFriends(gamChar(slot),negChar,0)
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Good for you! Just stick this needle in your",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("arm and you'll be wrestling all night...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Hey, it's your choice - but how do you intend",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to survive night after night in constant pain?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;9. WRESTLER OFFERS STEROIDS
 If negTopic=9
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hey, "+charName$(gamChar(slot))+", aren't you tired of",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("training all the time and getting nowhere?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("So was i until i discovered performance enhancing",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("drugs! That's what all the top athletes are using...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("I can hook you up with some steroids now if you want?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("For just $"+GetFigure$(negPayOff)+", you could become better than ever!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    BecomeFriends(gamChar(slot),negChar,0) : gamVariable(slot)=2
    charStr(gamChar(slot))=charStr(gamChar(slot))+PursueValue(charStr(gamChar(slot)),100,0)
    charSkl(gamChar(slot))=charSkl(gamChar(slot))+PursueValue(charSkl(gamChar(slot)),100,0)
    charAgl(gamChar(slot))=charAgl(gamChar(slot))+PursueValue(charAgl(gamChar(slot)),100,0)
    charStam(gamChar(slot))=charStam(gamChar(slot))+PursueValue(charStam(gamChar(slot)),100,0)
    charTough(gamChar(slot))=charTough(gamChar(slot))+PursueValue(charTough(gamChar(slot)),100,0)
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Good for you! You should feel the results in",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("no time... and so will your opponent!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Hey, it's your choice - but how do you intend",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to defeat wrestlers that ARE using this stuff?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;10. WANT TO SOCIALIZE?
 If negTopic=10
  optionA$="Yes, socialize!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", how are you doing?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You've been awful quiet with the guys backstage...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("What happens in the ring isn't all that matters.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("At the end of the day, we're all on the same team!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Why don't you come out to dinner tonight with",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("me and the guys? It would only cost $"+GetFigure$(negPayOff)+"...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    BecomeFriends(gamChar(slot),negChar,0)
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Alright, it'll be good to get to know the real you!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Meet us after the show and we'll trade war stories...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Hey, i was only trying to be friendly! Trust me,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("it doesn't happen very often in this business...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;11. BEATING THREAT
 If negTopic=11
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"..." : optionB$="No, do your worst!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hey, "+charName$(gamChar(slot))+", i just wanted to",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("wish you luck with your match tonight...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("Dangerous things happen back here, and it would",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("be a shame if you ended up CRAWLING to the ring!",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>450 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("But neither of us want that to happen, right?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Good, so hand over $"+GetFigure$(negPayOff)+" and it won't!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("A wise decision! Although i am disappointed",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("that i didn't get to kick somebody's ass...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then BecomeEnemies(gamChar(slot),negChar,1) : AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Well, you can't say i didn't warn you! The only",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("place you're going to is the emergency room...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;12. LEAVE FRIEND DEMAND
 If negTopic=12
  g=GetGender(charFriend(gamChar(slot)))
  optionA$="Yes, forsake friend..." : optionB$="No, do your worst!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", why are you hanging",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("out with that loser, "+charName$(charFriend(gamChar(slot)))+"?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("You better reconsider the company you keep!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("That asshole has enemies in high places...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("So what's it gonna be? Are you gonna ditch "+Lower$(Him$(g))+",",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("or do i have to KNOCK some sense into you?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    BecomeEnemies(gamChar(slot),charFriend(gamChar(slot)),1)
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Haha! Good for you! Being friends with that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("loser isn't good for anybody's career...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,1)
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("You're prepared to take a hit for THAT loser?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("A couple of idiots like you deserve each other...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;13. ENEMY WANTS TO BRAWL
 If negTopic=13
  g=GetGender(charFriend(gamChar(slot)))
  optionA$="Yes, bring it on!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", the beef between",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("us has gone far beyond the ring!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I don't care about 'scoring victories' anymore.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I just want to kick your ass for real!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Are you man enough to take me on right now,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("or are you a bitch that lives for the cameras?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Wow, i'm surprised you had the balls to say yes!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You haven't got what it takes to beat me though...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Like you have a choice! I'm swinging your way",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("no matter what! It's up to you if you fight back...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;14. HIRED GUN
 If negTopic=14
  g=GetGender(charEnemy(negChar))
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", you've got to help",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("me get "+charName$(charEnemy(negChar))+" off my back!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline(He$(g)+"'s busting me up in the ring AND back here!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("The asshole thinks "+Lower$(He$(g))+" rules this locker room...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("But surely you can take "+Him$(g)+" down, right?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'll pay you $"+GetFigure$(negPayOff)+" if you knock "+Him$(g)+" out!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    BecomeFriends(gamChar(slot),negChar,0)
    BecomeEnemies(gamChar(slot),charEnemy(negChar),0)
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Thank you! You saved my life! It'll be worth",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("every penny to see that thug taught a lesson...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Come on, you're going to leave me to suffer?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I guess the bad guys really do run this business...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;15. FOREIGN INVASION!
 If negTopic=15
  optionA$="Yes, fight them off!" : optionB$="No, stay away..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", have you heard that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline(fedName$(gamInterProm(slot,GetDate()))+" invaded the building?!",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>125 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("They forced their way into my office, and now",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("they're tearing through the locker rooms!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Come on, we've got to stop them before they",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("ruin tonight's show! Are you going to help?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    PlaySound sCrowd(6) : BecomeFriends(gamChar(slot),negChar,0)
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Thank God! Now get out there are show those",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("assholes what "+fedName$(fed)+" is made of!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0
    BecomeEnemies(gamChar(slot),negChar,0) : gamSchedule(slot,GetDate())=0
    PlaySound sCrowd(3) : AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("NO?! Well, that's tonight's show screwed then!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("They're going to tear this building to the ground...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;16. RIOT FOR RISE!
 If negTopic=16
  g=GetGender(fedBooker(fed))
  optionA$="Yes, cause a riot!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", aren't you sick of",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("earning nothing here at "+fedName$(fed)+"?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("While we risk our lives, "+charName$(fedBooker(fed)),rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("is getting rich by sitting on "+Lower$(His$(g))+" ass!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Well, some of us have had enough and we're going",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to riot for better pay! Can we count you in?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0 
    BecomeFriends(gamChar(slot),negChar,0)
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Excellent! Now let's tear this locker room up",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("until the suits upstairs give us what we want!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("So you're happy with your $"+GetFigure$(charSalary(gamChar(slot)))+" per week?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("If you're that naive, i guess it's what you deserve!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;17. SHOOT SUGGESTION
 If negTopic=17
  g=GetGender(fedBooker(fed))
  optionA$="Deliver a shoot promo!" : optionB$="Stick to the script..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", aren't you sick of",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("reading "+charName$(fedBooker(fed))+"'s pathetic scripts?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Remember the spotlight is YOURS once that microphone",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("is in your hands! You could say anything you want...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("Why don't you say what you really think tonight?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("The controversy will make you more popular than ever!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    charHeel(gamChar(slot))=1 : charEyes(gamChar(slot))=1 : gamPromo(slot,GetDate())=47
    BecomeFriends(gamChar(slot),negChar,0) : BecomeEnemies(gamChar(slot),fedBooker(fed),1)
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Cool! I look forward to hearing what you have to say!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("This could be the defining moment of your career...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("I knew you wouldn't have the balls to be yourself!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Enjoy spewing out those meaningless words tonight...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;18. ENEMY CALLS A TRUCE
 If negTopic=18
  optionA$="Yes, bury the hatchet..." : optionB$="No, keep feuding!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", i know we haven't",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("been seeing eye-to-eye in recent weeks?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("Well, i for one am getting sick of the",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("constant bickering - and so are the fans!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("Why don't we put it all behind us and get back",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to wrestling? I'm willing to do it if you are...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    If gamOpponent(slot,GetDate())=0 Then gamOpponent(slot,GetDate())=negChar : gamPromo(slot,GetDate())=54
    BecomeFriends(gamChar(slot),negChar,0)
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Thank God for that! I thought it would never end.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Who knows? Maybe we'll end up being friends!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then gamOpponent(slot,GetDate())=negChar : AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Well, you can't say i didn't give you a way out.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Instead of ending a battle, you've started a WAR!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;19. ENEMY WANTS TO FORM TEAM
 If negTopic=19
  optionA$="Yes, form a team!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", i know we've taken",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("each other to hell and back in recent weeks?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("Well, it occurs to me that we've been wasting",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("our careers by trying to outdo each other!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("We should be working TOGETHER! Why don't we",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("form a team and rule "+fedName$(fed)+"?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamFormat(slot)=2 : BecomeTeam(gamChar(slot),negChar) 
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=3
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Excellent! If we channel our efforts in the same",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("direction, we could both become better than ever!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("You idiot! You'll NEVER be able to defeat me!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("That was your only chance to save your career...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;20. ENEMY WANTS HAIR
 If negTopic=20
  optionA$="Yes, book the match!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", the beef between",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("us has gone far beyond the ring!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I don't just want to kick your ass anymore;",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i want to rip every hair out of your head!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("What would you say to a 'Hair vs Hair' match",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("next week? That would make things interesting!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0 
    gamOpponent(slot,GetDate()+1)=negChar : gamMatch(slot,GetDate()+1)=2 : gamGimmick(slot,GetDate()+1)=8
    If fed=<6 Then gamPromo(slot,GetDate()+1)=69
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Haha! Not only do i get to beat you, but i get to",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("humiliate you too! That'll be sweeter than any title...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Haha! I knew you were nothing but a coward!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I guess you know you wouldn't stand a chance...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;21. ENEMY WANTS CAREER
 If negTopic=21
  optionA$="Yes, book the match!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", the beef between",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("us has gone far beyond the ring!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I don't just want to kick your ass anymore;",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i want to bring an end to your sorry career!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("What do you say to a 'Loser Leaves Town' match",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("next week? Then we'll never see each other again!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0 
    gamOpponent(slot,GetDate()+1)=negChar : gamMatch(slot,GetDate()+1)=3 : gamGimmick(slot,GetDate()+1)=9
    If fed=<6 Then gamPromo(slot,GetDate()+1)=68
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("I guess you're not the coward i thought you were!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Shame you just made the biggest mistake of your life...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Hey, i just gave you a chance to leave properly!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Now you'll have to end your career in a wheelchair...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;22. OPPONENT OFFERS TO LAY DOWN
 If negTopic=22
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", do you know that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i'm booked to wrestle you tonight?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("Well, fortunately for you, i need money - and i",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("could be persuaded to lose if the price was right?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Just give me $"+GetFigure$(negPayOff)+" and the match is yours?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("It's a small price to pay for an easy night!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamOpponent(slot,GetDate())=negChar : gamVariable(slot)=6
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Excellent! This should be the easiest money i've",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("ever earned and the easiest match you've ever had!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,0) : gamOpponent(slot,GetDate())=negChar
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("You don't think a win over me is worth $"+GetFigure$(negPayOff)+"?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We'll see what happens when i give you my all!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;23. OPPONENT PAYS YOU TO LAY DOWN
 If negTopic=23
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", do you know that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i'm booked to wrestle you tonight?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("Well, i'm going through a bad patch right now",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and i could really use an easy victory!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("I'd be willing to pay $"+GetFigure$(negPayOff)+" if you lie down",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and make sure the match goes my way?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0 
    gamOpponent(slot,GetDate())=negChar : gamVariable(slot)=7
    PlaySound sCash : AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Excellent! This should be easy money for you.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Just make sure your pride doesn't screw things up!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,0) : gamOpponent(slot,GetDate())=negChar
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("That could've been the easiest money you ever earned!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Somebody that stupid can't be hard to beat anyway...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;24. INFERIOR FRIEND WANTS TO FIGHT
 If negTopic=24
  optionA$="Yes, fight friend!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", have you heard what",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("everybody has been saying about us?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("They're calling me your little pet dog that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("worships at your feet like some sort of God?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Well, maybe they're right. I'm sick of walking",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("in YOUR shadow! I need to make a name for myself...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 And negTim<1300
   Speak(2,0,1)
   Outline("Take me on tonight, you son of a bitch! Then",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("we'll see who's the laughing stock around here...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1325 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0    
    gamFormat(slot)=1 : BecomeEnemies(gamChar(slot),negChar,1)
    gamOpponent(slot,GetDate())=negChar : gamPromo(slot,GetDate())=70
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("That's right - get used to following MY demands!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Tonight, i'm gonna prove that i'm better than you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0    
    gamFormat(slot)=1 : BecomeEnemies(gamChar(slot),negChar,1)
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("You chicken! You know that you could never beat me!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You can't hide forever though. My time will come...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;25. SUPERIOR FRIEND WANTS TO FIGHT
 If negTopic=25
  optionA$="Yes, fight friend!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", have you heard what",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("everybody has been saying about us?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("They say i threw my career down the drain by",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("letting a loser like YOU into my life!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Well, maybe they're right. You've been nothing but",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("trouble for me! I need to get back to the top...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 And negTim<1300
   Speak(2,0,1)
   Outline("Since you ruined my career, the least you could do",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("is let me use your ass as a stepping stone tonight?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1325 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0    
    gamFormat(slot)=1 : BecomeEnemies(gamChar(slot),negChar,1)
    gamOpponent(slot,GetDate())=negChar : gamPromo(slot,GetDate())=37
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("That's right - get used to following MY demands!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("As of tonight, my name is going back on the map...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0    
    gamFormat(slot)=1 : BecomeEnemies(gamChar(slot),negChar,1)
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Hey, you OWE me the right to kick your ass!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Now i'll have to do it when you least suspect it...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;26. TEAM-MATE WANTS TO SYNCHRONIZE
 If negTopic=26
  optionA$="Yes, copy costume!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", we're supposed to be a",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("team - but you wouldn't know it to look at us!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("Why don't you copy my costume? If we look like a",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("team then people might take us seriously as one!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamVariable(slot)=13
    charBody(gamChar(slot),1)=charBody(negChar,1)
    charLeftArm(gamChar(slot),1)=charLeftArm(negChar,1)
    charRightArm(gamChar(slot),1)=charRightArm(negChar,1)
    charLegs(gamChar(slot),1)=charLegs(negChar,1)
    For col=1 To 3
     charBodyCol(gamChar(slot),col,1)=charBodyCol(negChar,col,1)
     charLeftArmCol(gamChar(slot),col,1)=charLeftArmCol(negChar,col,1)
     charRightArmCol(gamChar(slot),col,1)=charRightArmCol(negChar,col,1)
     charLegsCol(gamChar(slot),col,1)=charLegsCol(negChar,col,1)
    Next
    charTheme(gamChar(slot))=charTheme(negChar)
    charThemePitch(gamChar(slot))=charThemePitch(negChar)
    charLight(gamChar(slot))=charLight(negChar)
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Great! We're gonna look cooler than ever now!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We might as well use my entrance gimmick too...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0
    BecomeEnemies(gamChar(slot),negChar,1) : gamFormat(slot)=1
    If gamOpponent(slot,GetDate())=0 Then gamOpponent(slot,GetDate())=negChar : gamPromo(slot,GetDate())=8
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Well, if you insist on looking apart maybe we should",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("stand apart too! Then we'll see who has the best gimmick...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;------------------------ FORMAL TOPICS (30-60) ----------------------------
 ;30. RETIREMENT?!
 If negTopic=30
  optionA$="Yes, retire..." : optionB$="No, keep wrestling!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", what's all this i hear about",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("retirement?! Are you sure you want to leave us?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1 And CountExperience(slot)=<48
   Speak(2,0,1)
   Outline("If that's what you want, i guess there's nothing to say.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Some people just aren't built to go the distance...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1 And CountExperience(slot)>48
   Speak(2,0,2)
   If negTim>150 And charged=0 Then PlaySound sCrowd(6) : charged=1
   Outline("Well, i suppose all that remains is to wish you luck.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Maybe we'll see you behind this desk some time!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   Outline("Good! I hate to see people throwing away their careers.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Remember: you've only ever failed when you give up...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;31. BOOKER SUGGESTS HEEL TURN
 If negTopic=31
  optionA$="Yes, turn Heel!" : optionB$="No, remain Face..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I requested to see you today",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to discuss taking your career in a new direction...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("How would you feel about turning against the fans?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("It could give your career a whole new perspective!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    charHeel(gamChar(slot))=1 : charEyes(gamChar(slot))=1
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=26
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("OK, we'll start pushing you as a bad-ass right away!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("The fans will go nuts if you wind them up properly...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("OK, it was only a suggestion. I do advise you to mix",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("things up though. Even a hero gets boring after a while...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;32. BOOKER SUGGESTS FACE TURN
 If negTopic=32
  optionA$="Yes, turn Face!" : optionB$="No, remain Heel..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I requested to see you today",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to discuss taking your career in a new direction...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("How would you feel about becoming a fan favourite?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("It could help you become more popular than ever!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    charHeel(gamChar(slot))=0 : charEyes(gamChar(slot))=3
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=25
    AdjustAttitude(gamChar(slot),100,1) : charged=1 
   EndIf
   Outline("OK, we'll start portraying you in a more positive light!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("The fans should enjoy the chance to get behind you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("OK, it was only a suggestion. I do advise you to mix",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("things up though. Otherwise your act could become stale...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;33. BOOKER SUGGESTS TEAM WITH FRIEND
 If negTopic=33
  optionA$="Yes, form a team!" : optionB$="No, remain single..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I notice that you and",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline(charName$(charFriend(gamChar(slot)))+" have been getting on well lately?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Well, how would you feel about working as a team?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'm sure that chemistry would translate to the ring!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamFormat(slot)=2 : BecomeTeam(gamChar(slot),charFriend(gamChar(slot)))
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=3
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Alright, we'll start promoting you as a double-act",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("right away! This could be the start of something big...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("You don't see it? OK, it was only a suggestion.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Just make sure you shine as a singles wrestler!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;34. BOOKER SUGGESTS TEAM BREAK-UP
 If negTopic=34
  optionA$="Yes, break up..." : optionB$="No, remain a team!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Listen, "+charName$(gamChar(slot))+". I'm not sure that your",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("team with "+charName$(charFriend(gamChar(slot)))+" is doing you any favours...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("How would you feel about striking out on your own?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("With a little work, you could be bigger than any team!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then gamFormat(slot)=1 : AdjustAttitude(gamChar(slot),100,1) : charged=1
   Outline("OK, we'll look into pushing you as a solo star!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("It could be just the break your career needs...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),charFriend(gamChar(slot)),1)
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("You want to hide behind a team-mate forever?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I must say, that's not what champions are made of...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;35. BOOKER ASKS YOU TO REFEREE
 If negTopic=35
  optionA$="Yes, referee a match!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", would you mind doing",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("me a favour before you leave for the night?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("We're short of referees at the moment, and i",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("need somebody to take care of the next match...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Would you be able to handle the referee duties?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'll double your salary for the night if you do!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then PlaySound sCash : AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Great! Thanks for doing this. I know you've got",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("better things to do, but it shouldn't take long...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("I guess your matches are the only ones that matter?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Don't worry, i'll just have to take care of it myself...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;36. TAKE DAY OFF TO RECOUP?
 If negTopic=36
  optionA$="Yes, take a break..." : optionB$="No, keep working!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", you don't look well!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Are you sure you're fit to wrestle tonight?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("You're no good to anybody if you're tired.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Feel free to take the night off if you want?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then gamSchedule(slot,GetDate())=0 : AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Alright, i'll find somebody to take your place.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("All you have to worry about is getting better...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Are you sure?! I admire your commitment, but",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i do hope it doesn't come back to haunt you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;37. WANT TO PARTICIPATE IN TRIBUTE?
 If negTopic=37
  g=GetGender(fedRoster(9,fedSize(9)))
  optionA$="Yes, take part!" : optionB$="No, leave me out..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I'm sure you've",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("heard about the death of "+charName$(fedRoster(9,fedSize(9)))+"?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Well, tonight the whole wrestling industry is coming",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("together to stage a special memorial show!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("Do you want me to pencil you in for a match?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You wouldn't earn anything, but it's a good cause...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,1) : charged=1
   Outline("That's the attitude! Stepping into the ring is the",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("perfect way to pay tribute to any fallen soldier...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    PlaySound sCrowd(3) : gamSchedule(slot,GetDate())=0
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Why wouldn't you want to honour a fellow wrestler?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I guess some people are only in this for the money...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;38. WANT TO PARTICIPATE IN CHARITY?
 If negTopic=38
  optionA$="Yes, take part!" : optionB$="No, leave me out..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I'm sure you're aware",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("that there's a special charity event tonight?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Well, we've been asked to take part too -",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and i'd like to pencil you in for a match!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("You'd obviously have to donate your fee to charity,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but that's the least we can do, don't you think?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,1) : charged=1
   Outline("That's the attitude! The best thing about this",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("business is that we know how to give back...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    PlaySound sCrowd(3) : gamSchedule(slot,GetDate())=0
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Why wouldn't you want to raise money for charity?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Let's hope that YOU never need the help of others...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;39. DRUG SCANDAL!
 If negTopic=39
  optionA$="Pay $"+GetFigure$(negPayOff)+" fine!" : optionB$="Serve suspension..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Listen, "+charName$(gamChar(slot))+", it's been brought to",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("my attention that you've been using drugs?",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>150 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I know this life is hard, but our policy does",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("not tolerate the use of drugs of any kind!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Before this gets blown out of proportion, i",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("have to be seen to be doing something about it...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 And negTim<1300
   Speak(2,0,2)
   Outline("I'm supposed to suspend you for a month, but i",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("may be able to let you off with a $"+GetFigure$(negPayOff)+" fine?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1325 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   If charged=0
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    charPop(gamChar(slot))=charPop(gamChar(slot))-1
    AdjustAttitude(gamChar(slot),30,0) : charged=1 
   EndIf
   Outline("OK, get out your wallet and we'll make this problem",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("go away - but it won't buy you an awful lot of respect!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 
    For count=GetDate() To GetDate()+3
     gamSchedule(slot,count)=0
    Next
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Yeah, that's probably for the best. The fans will",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("forgive you if you're seen to be suffering...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;40. CONTRACT TERMINATION
 If negTopic=40
  optionA$="Yes, terminate contract..." : optionB$="No, serve contract!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Listen, "+charName$(gamChar(slot))+", i asked you in here",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("today to discuss terminating your contract...",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>150 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("It's not personal! It's just that business is",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("down and we desperately need to cut costs...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("I can't force you out - but if you'd like to",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("leave on your terms, now would be a good time?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1  
   Outline("Thanks for understanding the situation! Sorry it",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("had to end this way, but i'm sure you'll be fine...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,1)
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=7
    AdjustAttitude(gamChar(slot),30,0)  : charged=1
   EndIf
   Outline("I knew you wouldn't understand! All you",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("morons care about is milking me dry...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;41. TAKE A PAY-CUT
 If negTopic=41
  optionA$="Yes, take a pay cut..." : optionB$="No, honour contract!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Listen, "+charName$(gamChar(slot))+", i brought you in here",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("today to ask you to consider taking a pay cut?",rX#(400),rY#(555),50,50,50,250,250,250)  
   If negTim>150 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("It's not personal! It's just that business is",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("down and we desperately need to cut costs...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("I know it's a lot to ask, but we're going to need",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("everybody to pitch in to keep this company afloat?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    BecomeFriends(gamChar(slot),negChar,0)
    PlaySound sCash : charSalary(gamChar(slot))=charSalary(gamChar(slot))/2
    AdjustAttitude(gamChar(slot),100,0)  : charged=1  
   EndIf
   Outline("Thanks for being so understanding! You can rest",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("assured that your sacrifice won't be forgotten...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,1)
    If gamPromo(slot,GetDate())=0 And fed=<6 Then gamPromo(slot,GetDate())=7
    AdjustAttitude(gamChar(slot),30,0)  : charged=1
   EndIf
   Outline("I knew you wouldn't understand! All you",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("morons care about is milking me dry...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;42. OPINION ON INTERNAL EMPLOYEE
 If negTopic=42
  g=GetGender(pChar(9))
  optionA$="Yes, get rid of "+Him$(g)+"!" : optionB$="No, "+Lower$(He$(g))+"'s worth keeping..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I asked you in here",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("today to help me with a business decision...",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("What do you make of "+charName$(pChar(9))+"?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You must have come across "+Him$(g)+" backstage?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Well, we can't decide whether to keep "+Him$(g)+" or not.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("What's your professional opinion on the matter?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    ReleaseCharacter(pChar(9)) : BecomeFriends(gamChar(slot),negChar,0)
    AdjustAttitude(gamChar(slot),30,0)  : charged=1  
   EndIf
   Outline("Really? Well, you'd know better than any of us!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline(He$(g)+"'ll be gone by next week. Thanks for your input...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   If charged=0 
    BecomeFriends(gamChar(slot),pChar(9),0)
    charSalary(pChar(9))=charSalary(pChar(9))+(charSalary(pChar(9))/4)
    charContract(pChar(9))=charContract(pChar(9))+10
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Wow, "+Lower$(He$(g))+" must be good if a fellow wrestler says so!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Most guys will tread over anybody to get to the top...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;43. OPINION ON EXTERNAL WRESTLER
 If negTopic=43
  g=GetGender(pChar(9))
  optionA$="Yes, hire "+Him$(g)+"!" : optionB$="No, forget "+Him$(g)+"..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I asked you in here",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("today to help me with a business decision...",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Have you heard of the wrestler, "+charName$(pChar(9))+"?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We could be signing "+Him$(g)+" from "+fedName$(charFed(pChar(9)))+"!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("What's your professional opinion on the matter?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Would "+Lower$(He$(g))+" fit in at "+fedName$(fed)+"?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    Trade(pChar(9),fed) : GetNewContract(pChar(9))
    BecomeFriends(gamChar(slot),pChar(9),0)
    AdjustAttitude(gamChar(slot),100,0) : charged=1  
   EndIf
   Outline("Excellent! I knew "+charName$(pChar(9))+" belonged here!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We'll break the bank to sign "+Him$(g)+" as soon as possible...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Really?! Damn, i had a good feeling about that one!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I guess we'll look elsewhere for new talent...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;44. ASKED TO ENDORSE BEER
 If negTopic=44
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I represent a beverage",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("company that's interested in sponsoring you...",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Bullhorn Beer is an exciting beverage for exciting",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("people, and we think that YOU fit that description!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("We'd be willing to pay you $"+GetFigure$(negPayOff)+" if you wear",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("our shirt to the ring and help spread the word?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    For count=1 To 2
     charBody(gamChar(slot),count)=71
     If GetRace(gamChar(slot))=0 Then charLeftArm(gamChar(slot),count)=28 : charRightArm(gamChar(slot),count)=28
     If GetRace(gamChar(slot))=1 Then charLeftArm(gamChar(slot),count)=33 : charRightArm(gamChar(slot),count)=33
    Next
    charWeapon(gamChar(slot))=17 : gamPromo(slot,GetDate())=29
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),30,0)  : charged=1  
   EndIf
   Outline("Fantastic! Here's your money! Now try on the",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("shirt and we can start campaigning tonight...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("You won't see that kind of money anywhere else!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'm not entirely sure you were worth it anyway...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;45. WORLD TITLE PUSH
 If negTopic=45
  optionA$="Yes, pursue the title!" : optionB$="No, not yet..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hi, "+charName$(gamChar(slot))+". I've been following your",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("progress, and i think you've got a bright future...",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("How would you like to compete for the World title?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I think you could really liven up that scene!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("It'd be tough working against "+charName$(fedChampWorld(fed))+",",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but that's what champions are made of, right?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    BecomeEnemies(gamChar(slot),fedChampWorld(fed),1) : gamPromo(slot,GetDate())=57
    AdjustAttitude(gamChar(slot),100,1) : charged=1  
   EndIf
   Outline("Great! This could be the start of something big!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We'll work to get you over as a title contender...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("You DON'T want to be the World Champion?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Well, what the hell DO you want to achieve?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;46. INTER TITLE PUSH
 If negTopic=46
  optionA$="Yes, pursue the title!" : optionB$="No, not yet..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hi, "+charName$(gamChar(slot))+". I've been following your",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("progress, and i think you've got a bright future...",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("How would you like to compete for the Inter title?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You'd work well against "+charName$(fedChampInter(fed))+"!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("I know it's not the best title to compete for,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but surely ANY gold is better than nothing?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    BecomeEnemies(gamChar(slot),fedChampInter(fed),1) : gamPromo(slot,GetDate())=58
    AdjustAttitude(gamChar(slot),100,1) : charged=1  
   EndIf
   Outline("Great! This could be just what the division needs!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We'll put you on the road to the title tonight...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("You don't want to be a champion of ANY kind?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("What the hell do you bother fighting for?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;47. TAG TITLE PUSH
 If negTopic=47
  optionA$="Yes, pursue the titles!" : optionB$="No, not yet..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hi, "+charName$(gamChar(slot))+". I've been impressed with",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("progress that you and "+charName$(charFriend(gamChar(slot)))+" have made...",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("How would you like to compete for the Tag titles?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You two could be just what the division needs!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline(charName$(fedChampTag(fed,1))+" and "+charName$(fedChampTag(fed,2))+" will",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("work you hard, but surely the gold is worth it?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamPromo(slot,GetDate())=59
    BecomeEnemies(gamChar(slot),fedChampTag(fed,1),1)
    BecomeEnemies(charFriend(gamChar(slot)),fedChampTag(fed,2),1)
    AdjustAttitude(gamChar(slot),100,1) : charged=1  
   EndIf
   Outline("Great! This could be the making of you both!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We'll use tonight to get you in the hunt...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("You DON'T want to be the Tag Champions?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Well, what the hell did you form a team for?!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;48. FORCED TO TEAM WITH ENEMY
 If negTopic=48
  optionA$="Yes, team with enemy..." : optionB$="No chance!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", i'm getting sick",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("of your petty feud with "+charName$(charEnemy(gamChar(slot)))+"!",rX#(400),rY#(555),50,50,50,250,250,250)  
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("Since the two of you can't be trusted to face",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("each other, i've come up with the perfect solution...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Tonight, the two of you will work as a TEAM!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("That should help you settle your differences...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,2)
   If charged=0 
    gamMatch(slot,GetDate())=12 : gamPromo(slot,GetDate())=31
    If gamOpponent(slot,GetDate())=charEnemy(gamChar(slot)) Then gamOpponent(slot,GetDate())=0
    AdjustAttitude(gamChar(slot),100,1) : charged=1  
   EndIf
   Outline("Good, i'm glad you're receptive to a solution.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Who knows? You might even enjoy working together!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,0) : gamSchedule(slot,GetDate())=0
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("Who do you think you're talking to?! You either",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("do as i say or you don't wrestle at all...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;49. WORK A SECOND MATCH?
 If negTopic=49
  optionA$="Yes, wrestle again..." : optionB$="No chance!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", would you mind doing",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("me a favour before you leave for the night?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("The roster is such a shambles tonight that i'm",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("struggling to put together a full card of matches!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("Do you feel fit enough to work another match?",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'll double your salary for the night if you do!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then PlaySound sCash : gamVariable(slot)=3 : AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Thanks for doing this! I know it's not ideal,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but think of it as another chance to shine...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("OK, i suspected it would be too much to ask.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I guess i'll have to make up the numbers myself!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;50. WORK THROUGH POWER FAILURE?
 If negTopic=50
  optionA$="Yes, work anyway!" : optionB$="No, leave me out..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Look, "+charName$(gamChar(slot))+", it seems the venue",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("for tonight has suffered a power failure!",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>150 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("That obviously leaves the arena with hardly",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("any lighting, so things could get dangerous!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("It's still possible to stage some matches though.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Do you want to work through it or give it a miss?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then gamArenaLight(slot)=3 : AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Great, i'm glad you're up to the challenge!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Who knows? It could make things interesting...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then gamSchedule(slot,GetDate())=0 : AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Alright, just leave me here with a dark arena",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and an empty locker room! I'll figure it out...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;51. WORK THROUGH WEAPON BAN?
 If negTopic=51
  optionA$="Yes, work clean..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Hey, "+charName$(gamChar(slot))+", i'm getting tired of",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("the violence at "+fedName$(fed)+"!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("It comes to an ends tonight. I'm confiscating",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("all weapons until you animals learn to behave!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("So where does that leave you? Are you happy",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to work tonight under a no-weapons policy?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then gamVariable(slot)=4 : AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Good! It's for the benefit of all concerned.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("It should also make for a more creative match!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then gamSchedule(slot,GetDate())=0 : AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Fine, if you can't wrestle clean then you won't",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("work at all! Go home and break your own furniture...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;52. WORK THROUGH LACK OF ITEMS?
 If negTopic=52
  optionA$="Yes, work anyway..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Listen, "+charName$(gamChar(slot))+", it seems the props",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("for tonight's show have failed to turn up!",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>150 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("We've got a ring, but that's about it! No items",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to smash through, and no weapons to grab hold of...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("That'll put a crimp in any wrestler's night,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but do you think you can work through it?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then gamVariable(slot)=5 : AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Great! I know it makes us look pathetic,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but at least you'll have a safe night...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then gamSchedule(slot,GetDate())=0 : AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Fine, then you won't work at all! Go figure out",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("if you're a wrestler or an interior designer...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;53. WANT A FAVOURABLE REF?
 If negTopic=53
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Listen, "+charName$(gamChar(slot))+", you must understand",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("that i don't really care who wins or loses...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,2)
   Outline("All i care about is keeping the money pumping",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("through this company. Then we're ALL winners!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("For instance, you could slip me $"+GetFigure$(negPayOff),rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and find yourself with a favourable referee?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamVariable(slot)=8
    If charFriend(gamChar(slot))=0 Then BecomeFriends(gamChar(slot),negChar,1) 
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Thank you! That's a very generous donation!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'll make sure your referee returns the favour...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    gamVariable(slot)=9
    If charEnemy(gamChar(slot))=0 Then BecomeEnemies(gamChar(slot),negChar,1) 
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Fine, have it your way! If you won't appreciate",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("a favourable referee, perhaps your opponent will...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;54. WANT TO SHOOT A MOVIE?
 If negTopic=54
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hi, "+charName$(gamChar(slot))+". I'm shooting a movie",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("soon, and i think you'd be perfect for it!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("It's a great opportunity to raise your profile,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and you could earn as much as $"+GetFigure$(negPayOff)+"!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   Outline("Of course, we'd have to pull you out of the ring",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("until filming is complete - but that's fine, right?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    For count=GetDate() To GetDate()+6
     gamSchedule(slot,count)=0
    Next
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    charPop(char)=charPop(char)+PursueValue(charPop(char),100,0)
    charHap(char)=charHap(char)+PursueValue(charHap(char),100,0)
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Great! Forget about your wrestling commitments!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You'll soon be bigger than this business...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
   Outline("Actually, i'm not sure you were right for it anyway.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("We need somebody with ambitions beyond the ring...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;55. WANT TO PARTICIPATE IN ARTICLE?
 If negTopic=55
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hi, "+charName$(gamChar(slot))+". I represent a show biz",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("magazine, and we're interested in your story!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("Would you be willing to spend the day with us",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("if we paid you $"+GetFigure$(negPayOff)+" for an interview?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamSchedule(slot,GetDate())=0
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    charPop(char)=charPop(char)+PursueValue(charPop(char),100,1)
    charHap(char)=charHap(char)+PursueValue(charHap(char),100,0)
    AdjustAttitude(gamChar(slot),30,1) : charged=1
   EndIf
   Outline("Excellent! Sorry to pull you out of the ring,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but i'm sure your fans will enjoy the article...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,1) : charged=1
   Outline("Actually, your story isn't worth printing anyway.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Wrestlers are too stupid to express themselves...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;56. FORCED TO WORK THROUGH INJURY!
 If negTopic=56
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Poor, injured "+charName$(gamChar(slot))+" - do take a seat.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I think we need to talk about your health...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("Let's start with why you're pretending to be hurt?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I know when somebody is trying to get out of work!",rX#(400),rY#(555),50,50,50,250,250,250)
   If negTim>450 Then ChannelPitch chTheme,40000
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   If charged=0 
    gamSchedule(slot,GetDate())=1 : gamVariable(slot)=10
    charHap(char)=charHap(char)+PursueValue(charHap(char),30,0)
   EndIf
   Outline("Well, the charade ends tonight! You're working no",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("matter what! Then we'll see how 'injured' you are...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then go=1
 EndIf
 ;57. PAID TO WORK THROUGH INJURY
 If negTopic=57
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I know you're nursing",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("an injury, but i've got a proposal for you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("It would be really helpful if you could show your",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("face on tonight's show? Just one little match...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("I know it's the last thing on your mind, but",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i'd be willing to pay $"+GetFigure$(negPayOff)+" for your time?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamSchedule(slot,GetDate())=1 : gamVariable(slot)=10
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
   Outline("Fantastic! It'll be worth every penny when",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("the fans see you walk through that curtain...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,2)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Alright, i suspected it was too much to ask.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Take it easy and come back when you're ready...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;58. PAID FOR DAMAGES
 If negTopic=58
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("For the love of God, "+charName$(gamChar(slot))+"!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You made a hell of a mess out there tonight...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("We all like to see an entertaining match, but",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline(fedName$(fed)+" has to pay for that stuff!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,1)
   Outline("Before the venue sues us for damages, i figure",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("we can put this mess behind us for $"+GetFigure$(negPayOff)+"?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    PlaySound sCash : gamBank(slot)=gamBank(slot)-negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Thanks, i'll pass this onto the arena staff.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Try to be more responsible in future though...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 
    BecomeEnemies(gamChar(slot),negChar,0) : gamSchedule(slot,GetDate()+1)=0
    AdjustAttitude(gamChar(slot),30,0) : charged=1
   EndIf
   Outline("Fine! If you can't be trusted in the ring,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("i'll just have to keep you out of it...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;59. PAID TO CHANGE GIMMICK
 If negTopic=59
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I asked you in",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("here today to talk about your act...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("I know you own the rights to your image, but",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("there's a gimmick we'd love you to portray!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Would you be willing to give it a try if",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("we paid you $"+GetFigure$(negPayOff)+" for your trouble?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    gamClause(slot,1)=0
    ChangeCostume(gamChar(slot))
    charTheme(gamChar(slot))=Rnd(1,no_themes)
    charThemePitch(gamChar(slot))=DefaultPitch(charTheme(gamChar(slot)))
    charLight(gamChar(slot))=Rnd(5,no_lights)
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Great! This could be the start of something big!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Go try on your outfit and see what you think...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,1) : charged=1
   Outline("Damn, you would have been perfect for it!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I guess the opportunity will go to someone else...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;60. PAID TO CHANGE FIGHTING STYLE
 If negTopic=60
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I asked you in",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("here today to talk about your act...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("I know a wrestler's fighting style is very personal,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but there are some attacks we'd love you to try!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Would you be willing to change your fighting style",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("if we paid you $"+GetFigure$(negPayOff)+" for your trouble?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    Repeat 
     idol=Rnd(1,no_chars)
    Until idol<>gamChar(slot)
    For count=1 To 5
     charAttack(gamChar(slot),count)=charAttack(idol,count)
     charCrush(gamChar(slot),count)=charCrush(idol,count)
    Next
    For count=1 To 2
     charStance(gamChar(slot),count)=charStance(idol,count)
     charTaunt(gamChar(slot),count)=charTaunt(idol,count)
    Next
    gamVariable(slot)=11
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Great! This could change your career forever!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Check out our choices and see what you think...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Hey, this style could have changed your life!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("If you don't experiment, you'll never progress...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;61. PAID TO CHANGE MOVE SET
 If negTopic=61
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   Outline("Hi, "+charName$(gamChar(slot))+". I asked you in",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("here today to talk about your act...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,3)
   Outline("I know a wrestler's choice of moves is very personal,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("but there are some moves we'd love you to try!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,3)
   Outline("Would you be willing to spice up your arsenal",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("if we paid you $"+GetFigure$(negPayOff)+" for your trouble?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 
    Repeat 
     idol=Rnd(1,no_chars)
    Until idol<>gamChar(slot)
    For count=1 To 15
     charMove(gamChar(slot),count)=charMove(idol,count)
    Next
    For count=1 To 6
     charGroundMove(gamChar(slot),count)=charGroundMove(idol,count)
    Next
    gamVariable(slot)=12
    If gamPromo(slot,GetDate())=0 Then gamPromo(slot,GetDate())=38 
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),100,1) : charged=1
   EndIf
   Outline("Great! This could change your career forever!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Check out our choices and see what you think...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
   Outline("Hey, these moves could have changed your life!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("If you don't experiment, you'll never progress...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;-------------------------- TUTORIAL ---------------------------------
 ;70. FIRE USE
 If negTopic=70
  optionA$="Yes, of course..." : optionB$="No, tell me more!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", did you know that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("it's possible to use fire during matches?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   Outline("Well, remember you can set fire to items too!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Just use a flaming weapon on the broken debris...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   Outline("You just press RUN and PICK-UP near a flammable",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("weapon, and then it'll inflict even more damage!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;71. WEAPON USE
 If negTopic=71
  optionA$="Yes, of course..." : optionB$="No, tell me more!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", did you know that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("it's possible to use weapons during moves?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   Outline("Well, remember it works for submission holds too!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("You can get extra leverage with a weapon in hand...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   Outline("Grab an opponent with a weapon in hand and it'll",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("inflict bonus damage at the point of impact!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;72. BUCKLE USE
 If negTopic=72
  optionA$="Yes, of course..." : optionB$="No, tell me more!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", did you know that",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("it's possible to unfasten the corner buckles?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   Outline("Well, remember the exposed steel can score bonus",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("damage when you force your opponent into it!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   Outline("Simply press TAUNT when you're alone near a",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("corner, and you'll expose the steel underneath!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;73. MANAGERS
 If negTopic=73
  optionA$="Yes, of course..." : optionB$="No, tell me more!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", did you know that it's",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("possible to get a manager whenever you want?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   Outline("Well, remember they cost 20% of your earnings!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Not all of them are worth that investment...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   Outline("You just need to find somebody substantially less",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("popular than you and of the same allegiance...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;74. PAY-OFF's
 If negTopic=74
  optionA$="Yes, of course..." : optionB$="No, tell me more!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hey, "+charName$(gamChar(slot))+", did you know that it's",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("possible to receive your wages as a lump sum?",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   Outline("Well, remember it's only good for the short term.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Having no salary makes a mockery of your clauses...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,3)
   Outline("If you reduce your weekly salary, you're entitled",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("to make up the difference in a juicy advance!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf 
 ;-------------------------- MISSION ASSIGNMENTS ---------------------------------
 ;STANDARD INTRO
 If negTopic=>81 And negTopic=<99
  If negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Listen, "+charName$(gamChar(slot))+", i've been keeping a close eye",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("on your progress and i think changes need to be made...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;81. MISSION 1: IMPROVE POPULARITY
 If negTopic=81
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I'm not sure you're a big enough star for this show!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Raise your profile soon or i'll have to let you go...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=1 : gamTarget(slot)=charPop(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+6 : go=1
 EndIf
 ;82. MISSION 2: IMPROVE STRENGTH
 If negTopic=82
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("This isn't ballet! Wrestling is a powerful sport.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Improve your strength soon or you won't survive...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=2 : gamTarget(slot)=charStr(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+4 : go=1
 EndIf
 ;83. MISSION 3: IMPROVE SKILL
 If negTopic=83
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I'm not sure i trust you out there in the ring!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Improve your skills soon or i'll lose faith in you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=3 : gamTarget(slot)=charSkl(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+4 : go=1
 EndIf
 ;84. MISSION 4: IMPROVE AGILITY
 If negTopic=84
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("You're too slow to deliver an exciting match!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Gain some agility soon or i'll make you run home...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=4 : gamTarget(slot)=charAgl(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+4 : go=1
 EndIf
 ;85. MISSION 5: IMPROVE STAMINA
 If negTopic=85
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("What good is a wrestler that can't go the distance?!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Improve your stamina soon or i'll have to let you go...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=5 : gamTarget(slot)=charStam(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+4 : go=1
 EndIf
 ;86. MISSION 6: IMPROVE TOUGHNESS
 If negTopic=86
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I'm scared you'll break your neck whenever you wrestle!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Toughen up that body soon or you won't survive...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=6 : gamTarget(slot)=charTough(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+4 : go=1
 EndIf
 ;87. MISSION 7: IMPROVE ATTITUDE
 If negTopic=87
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I'm disappointed in the way you conduct yourself!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Improve your attitude soon or i'll have to let you go...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=7 : gamTarget(slot)=charAtt(gamChar(slot))+5 : gamDeadline(slot)=GetDate()+6 : go=1
 EndIf
 ;88. MISSION 8: GAIN A TITLE
 If negTopic=88
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("I'm not sure that you're championship material!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Win a title soon or you'll be a failure forever...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=8 : gamDeadline(slot)=GetDate()+6 : go=1
 EndIf
 ;89. MISSION 9: GET OUT OF DEBT
 If negTopic=89
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("Your financial status brings shame on the company!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Get out of debt soon or i'll have to disown you...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=9 : gamDeadline(slot)=GetDate()+6 : go=1
 EndIf
 ;90. MISSION 10: GET A DEAL
 If negTopic=90
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("You can't stay at wrestling school forever!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Get a deal soon or step aside for new talent...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=10 : gamDeadline(slot)=GetDate()+6 : go=1
 EndIf
 ;91. MISSION 11: KILLER INSTINCT
 If negTopic=91
  If negTim>350 And negTim<650
   Speak(2,0,1)
   Outline("You need a killer instinct to be a great wrestler!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Injure an opponent soon or i'll 'injure' your career...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=11 : gamDeadline(slot)=GetDate()+4 : go=1
 EndIf

 ;100. MISSION ACCOMPLISHED!
 If negTopic=100
  If negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Congratulations, "+charName$(gamChar(slot))+"! You followed my",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("advice and made your prospects better than ever...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 And negTim<650 And fed<>7
   Speak(2,0,3)
   Outline("As a reward for your struggle, here's a $"+GetFigure$(negPayOff)+" bonus!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I'll still be watching though, so don't let things slip...",rX#(400),rY#(555),50,50,50,250,250,250)
   If charged=0 
    PlaySound sCash : gamBank(slot)=gamBank(slot)+negPayOff
    AdjustAttitude(gamChar(slot),100,0) : charged=1
   EndIf
  EndIf
  If negTim>350 And negTim<650 And fed=7
   Speak(2,0,2)
   Outline("I wish i could offer you a reward - but at this",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("level, experience is the only reward there is!",rX#(400),rY#(555),50,50,50,250,250,250)
   If charged=0 Then AdjustAttitude(gamChar(slot),100,0) : charged=1
  EndIf
  If negTim>700 Then gamMission(slot)=0 : go=1
 EndIf
 ;101. MISSION FAILED! (MAJOR)
 If negTopic=101
  If negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Well, "+charName$(gamChar(slot))+", your time is up and",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("you blatantly failed to follow my advice!",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 And negTim<650
   Speak(2,0,1) : ChannelPitch chTheme,40000
   Outline("Unfortunately, i wasn't bluffing: YOU'RE FIRED!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Crawl back to school and learn some respect...",rX#(400),rY#(555),50,50,50,250,250,250)
   If charged=0 Then AdjustAttitude(gamChar(slot),30,0) : charged=1
  EndIf
  If negTim>700 Then gamMission(slot)=0 : go=1
 EndIf 
 ;102. MISSION FAILED! (SCHOOL)
 If negTopic=102
  If negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Well, "+charName$(gamChar(slot))+", your time is up and it",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("looks like you've failed to deliver the goods...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 And negTim<650
   Speak(2,0,1) : ChannelPitch chTheme,40000
   Outline("Unfortunately, this is the end of the road for you.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("There's nothing else i can do for your career...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>700 Then gamMission(slot)=0 : go=1
 EndIf
 ;---------- OPTION BOX ---------------
 If negStage=1 And negVerdict=0
  SetFont font(1)
  If foc=1 Then Speak(1,0,3)
  If foc=2 Then Speak(1,0,1)
  DrawOption(1,400,520,optionA$,"",0,0)
  DrawOption(2,400,555,optionB$,"",0,0)
  ;stat reminder
  char=gamChar(slot)
  If negTopic=3 Or negTopic=4 Or negTopic=33 Or negTopic=34 Then char=charFriend(gamChar(slot))
  If negTopic=5 Or negTopic=6 Or negTopic=10 Then char=negChar
  If negTopic=14 Then char=charEnemy(negChar) 
  If negTopic=42 Or negTopic=43 Then char=pChar(9)
  If negTopic=45 Then char=fedChampWorld(fed)
  If negTopic=46 Then char=fedChampInter(fed)
  If negTopic=47 Then char=fedChampTag(fed,1)
  If KeyDown(57) Or JoyDown(2) Or MouseDown(2) 
   If char<>gamChar(slot) Then char=gamChar(slot) Else char=negChar
  EndIf
  DrawProfile(char,-1,-1)
 EndIf
 ;offset camera
 If negStage<>1 And negTim>10 And negSubStage=0 Then camFoc=2
 If negStage<>1 And negTim>335 And negSubStage=1 Then camFoc=2
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;free entities
ChannelVolume chTheme,1.0
FreeEntity world
FreeEntity cam 
FreeEntity camPivot
FreeEntity dummy
FreeEntity light
For cyc=1 To no_plays
 FreeEntity p(cyc)
 FreeEntity pShadow(cyc)
Next
;released from company
If (negTopic=40 And negVerdict=1) Or negTopic=101 Then ReleaseCharacter(gamChar(slot))
;check changes
CheckLimits(gamChar(slot))
;proceed
screen=15
If gamResult(slot,GetDate())>0 Then screen=19 ;post-match ending
If (negTopic=30 And negVerdict=1) Or negTopic=102 Then screen=22 ;game over!
;prepare for impromptu matches
For cyc=1 To optPlayLim
 pChar(cyc)=0
 pControl(cyc)=0
Next
;CPU match detour
If negTopic=7 Or negTopic=35 Or negTopic=49
 If negVerdict=1
  matchPreset=2 : LoadMatch(matchPreset) : AddGimmick(0)
  no_wrestlers=2 : matchTeams=0 : matchPromo=0
  If negTopic=35 Then no_refs=1 : matchRules=1 : matchPromo=28
  no_plays=no_wrestlers+no_refs
  If negTopic=7 Then pChar(1)=negChar : pChar(2)=GetOpponent() : pChar(3)=GetReferee()
  If negTopic=35 Then pChar(1)=GetOpponent() : pChar(2)=GetOpponent() : pChar(3)=gamChar(slot) 
  If negTopic=49 Then pChar(1)=gamChar(slot) : pChar(2)=GetOpponent() : pChar(3)=GetReferee()
  screen=50
 EndIf
EndIf
;backstage brawl detour
If (negTopic=>11 And negTopic=<13 And negVerdict=-1) Or (negTopic=>13 And negTopic=<16 And negVerdict=1)
 matchPreset=0 : LoadMatch(matchPreset) : AddGimmick(0)
 no_wrestlers=2 : matchTeams=0 : matchChamps=0 : matchPromo=0 
 no_weaps=no_weaps/2 : weapLayout=4
 If negTopic=15 Then no_wrestlers=4 : matchTeams=1 : matchPromo=56
 If negTopic=16 Then no_wrestlers=4
 no_plays=no_wrestlers+no_refs
 If negTopic=>11 And negTopic=<13 Then pChar(1)=gamChar(slot) : pChar(2)=negChar
 If negTopic=14 Then pChar(1)=gamChar(slot) : pChar(2)=charEnemy(negChar)
 If negTopic=15
  pChar(1)=gamChar(slot) : pChar(2)=negChar 
  rival=gamInterProm(slot,GetDate())
  pChar(3)=fedRoster(rival,Rnd(1,fedSize(rival)/2))
  pChar(4)=fedRoster(rival,Rnd(fedSize(rival)/2,fedSize(rival)))
 EndIf
 If negTopic=16 Then pChar(1)=gamChar(slot) : pChar(2)=negChar : pChar(3)=GetOpponent() : pChar(4)=GetOpponent()
 screen=50
EndIf
End Function

;ADJUST ATTITUDE
Function AdjustAttitude(char,target,level)
 charAtt(char)=charAtt(char)+PursueValue(charAtt(char),target,level)
 charHap(char)=charHap(char)+PursueValue(charHap(char),target,1)
End Function