;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING ENCORE: NEWS & REPORTS ---------------------
;//////////////////////////////////////////////////////////////////////////////

;------------------------------------------------------------------------
;///////////////////// 16. INTRODUCE NEW WEEK ///////////////////////////
;------------------------------------------------------------------------
Function NewWeek()
;advance date
AdvanceDate()
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
x1=0 : x2=800
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
	If k=ticks Then CaptureWorld
	
    ;PORTAL
    gotim=gotim+1
	If gotim>20
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Then go=1
	EndIf
	If gotim>500 Then go=1 
	;swing in boxes
	x1=x1+16
	If x1>400 Then x1=400
	x2=x2-16
	If x2<400 Then x2=400
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(1),rX#(400),rY#(270)
 ;date
 SetFont font(1)
 DrawImage gMenu(1),rX#(x1)-87,rY#(465)
 Outline("New Week",rX#(x1)-87,rY#(465),190,190,190,200,0,0)
 DrawImage gMenu(5),rX#(x2)+87,rY#(465)
 Outline(textWeek$(gamWeek(slot))+" of "+textMonth$(gamMonth(slot)),rX#(x2)+87,rY#(465),190,190,190,75,75,75)
 ;cursor
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;proceed to news
SaveProgress()
SaveWorld()
SaveChars() 
fed=1 : screen=18
End Function

;------------------------------------------------------------------
;///////////////////// ADVANCE DATE PROCESS ///////////////////////
;------------------------------------------------------------------
Function AdvanceDate()
 ;inspire new destinies
 SeedRnd(MilliSecs())
 ;advance date
 gamExperience(slot,charFed(gamChar(slot)))=gamExperience(slot,charFed(gamChar(slot)))+1
 gamWeek(slot)=gamWeek(slot)+1
 If gamWeek(slot)>4 Then gamMonth(slot)=gamMonth(slot)+1 : gamWeek(slot)=1
 If gamMonth(slot)>12 
  gamYear(slot)=gamYear(slot)+1 : gamMonth(slot)=1
  For char=1 To no_chars
   If charFed(char)=<8 And (CharBusy(char)=0 Or CharBusy(char)=slot) Then charAge(char)=charAge(char)+1
  Next
  ResetSchedule(1,0)
 EndIf
 ;CHARACTER DEVELOPMENTS
 For char=1 To no_chars
  If CharBusy(char)=0 Or CharBusy(char)=slot
   fed=charFed(char)
   ;health status
   charOldInjured(char)=charInjured(char)
   charInjured(char)=charInjured(char)-1
   If charInjured(char)<0 Then charInjured(char)=0
   If charInjured(char)>0 Then charHealth(char)=0 : charTrainCourse(char)=0
   If charInjured(char)=0 Then charHealth(char)=charHealth(char)+Rnd(charStam(char)/4,charStam(char))
   If charHealth(char)>100 Then charHealth(char)=100
   ;training limits
   If CharBusy(char)=0 Then charTrainCourse(char)=0
   If charTrainCourse(char)>0
    If charTrainLevel(char)=1 And charHealth(char)>75 Then charHealth(char)=75
    If charTrainLevel(char)=2 And charHealth(char)>50 Then charHealth(char)=50
    If charTrainLevel(char)=3 And charHealth(char)>25 Then charHealth(char)=25
   EndIf
   ;availability status
   charOldVacant(char)=charVacant(char)
   charVacant(char)=charVacant(char)-1
   If charVacant(char)<0 Then charVacant(char)=0
   charContract(char)=charContract(char)-1
   If charContract(char)<0 Then charContract(char)=0
   If (fed=7 Or fed=9) And char<>fedBooker(fed) 
    charContract(char)=0 : charSalary(char)=0
    If char=gamChar(slot) Then gamClause(slot,1)=1 : gamClause(slot,2)=0 : gamClause(slot,3)=0
   EndIf
   ;simulate CPU activity
   ResetOldValues(char)
   randy=Rnd(0,1)
   If DesignatedRef(char) Then randy=Rnd(0,4)
   If char=charFriend(gamChar(slot)) And gamFormat(slot)=2 Then randy=99
   If randy=0 And fed=<7 And charHealth(char)>50 And charInjured(char)=0 And char<>gamChar(slot) And char<>gamOpponent(slot,GetDate())
    ;exhaustion
    charHealth(char)=charHealth(char)-Rnd(10,100)
    randy=Rnd(0,charTough(char))
    If randy=<1 And fed<>charFed(gamChar(slot))
     charInjured(char)=Rnd(1,4) : charHealth(char)=0
     charStr(char)=charStr(char)+PursueValue(charStr(char),statLevel(1),1)
     charSkl(char)=charSkl(char)+PursueValue(charSkl(char),statLevel(1),1)
     charAgl(char)=charAgl(char)+PursueValue(charAgl(char),statLevel(1),1)
     charStam(char)=charStam(char)+PursueValue(charStam(char),statLevel(1),1)
     charTough(char)=charTough(char)+PursueValue(charTough(char),statLevel(5),1)
     charHap(char)=charHap(char)+PursueValue(charHap(char),statLevel(1),1)
    EndIf
    ;popularity fluctuation
    If fed=7 Then level=Rnd(1,4) Else level=Rnd(2,6)
    If TitleHolder(char,0)>0 Then level=Rnd(4,6)    
    charPop(char)=charPop(char)+PursueValue(charPop(char),statLevel(level),1)
    If charPop(char)>fedPop(fed)+5 And charPop(char)>charOldPop(char) Then charPop(char)=charOldPop(char)
    charHap(char)=charHap(char)+PursueValue(charHap(char),statLevel(level-1),1)
    charAtt(char)=charAtt(char)+Rnd(-1,1)
    ;strength fluctuation
    randy=Rnd(0,3) : level=Rnd(1,6)
    If randy=0 Then charStr(char)=charStr(char)+PursueValue(charStr(char),statLevel(level),1)
    ;skill fluctuation
    randy=Rnd(0,3) : level=Rnd(1,6)
    If randy=0 Then charSkl(char)=charSkl(char)+PursueValue(charSkl(char),statLevel(level),1)
    ;agility fluctuation
    randy=Rnd(0,3) : level=Rnd(1,6)
    If randy=0 Then charAgl(char)=charAgl(char)+PursueValue(charAgl(char),statLevel(level),1)
    ;stamina fluctuation
    randy=Rnd(0,3) : level=Rnd(1,6)
    If randy=0 Then charStam(char)=charStam(char)+PursueValue(charStam(char),statLevel(level),1)
    ;toughness fluctuation
    randy=Rnd(0,3) : level=Rnd(1,6)
    If randy=0 Then charTough(char)=charTough(char)+PursueValue(charTough(char),statLevel(level),1)
    ;limit changes if old
    If charAge(char)=>optOldAge
     If charStr(char)>charOldStr(char) Then charStr(char)=charOldStr(char)
     If charSkl(char)>charOldSkl(char) Then charSkl(char)=charOldSkl(char)
     If charAgl(char)>charOldAgl(char) Then charAgl(char)=charOldAgl(char)
     If charStam(char)>charOldStam(char) Then charStam(char)=charOldStam(char)
     If charTough(char)>charOldTough(char) Then charTough(char)=charOldTough(char)
    EndIf
   EndIf
   ;training effects
   If charTrainCourse(char)>0 And charInjured(char)=0 And charAge(char)<optOldAge
    randy=Rnd(-1,3-charTrainLevel(char)) 
    If randy=<0 ;dedicated training
     If charTrainCourse(char)=1 Then charStr(char)=charStr(char)+PursueValue(charStr(char),100,Rnd(1,charTrainLevel(char)))
     If charTrainCourse(char)=2 Then charSkl(char)=charSkl(char)+PursueValue(charSkl(char),100,Rnd(1,charTrainLevel(char)))
     If charTrainCourse(char)=3 Then charAgl(char)=charAgl(char)+PursueValue(charAgl(char),100,Rnd(1,charTrainLevel(char)))
     If charTrainCourse(char)=4 Then charStam(char)=charStam(char)+PursueValue(charStam(char),100,Rnd(1,charTrainLevel(char)))
     If charTrainCourse(char)=5 Then charTough(char)=charTough(char)+PursueValue(charTough(char),100,Rnd(1,charTrainLevel(char)))
    EndIf
    If charTrainCourse(char)=6 ;universal training 
     For count=1 To charTrainLevel(char)
      randy=Rnd(0,5)
      If randy=1 Then charStr(char)=charStr(char)+PursueValue(charStr(char),100,1)
      If randy=2 Then charSkl(char)=charSkl(char)+PursueValue(charSkl(char),100,1)
      If randy=3 Then charAgl(char)=charAgl(char)+PursueValue(charAgl(char),100,1)
      If randy=4 Then charStam(char)=charStam(char)+PursueValue(charStam(char),100,1)
      If randy=5 Then charTough(char)=charTough(char)+PursueValue(charTough(char),100,1)
     Next
    EndIf
   EndIf
   ;check changes
   CheckLimits(char)
  EndIf
 Next
 ;PROMOTION DEVELOPMENTS
 fedLeader=FindTopFed()
 For fed=1 To 9
  ;store old values
  fedOldPop(fed)=fedPop(fed)
  fedOldRep(fed)=fedRep(fed)
  ;fluctuate values
  If fed=<6
   randy=Rnd(0,2) : level=Rnd(1,6)
   If randy=0 Then fedPop(fed)=fedPop(fed)+PursueValue(fedPop(fed),statLevel(level),1)
   randy=Rnd(0,2) : level=Rnd(1,6)
   If randy=0 Then fedRep(fed)=fedRep(fed)+PursueValue(fedRep(fed),statLevel(level),1)
   ;finances
   factor=Rnd(10,20)
   casher=fedBank(fed)/factor
   casher=RoundOff(casher,1000)
   If casher<1000 Then casher=1000
   randy=Rnd(-2,2)
   If randy<0 And fedBank(fed)>0 Then fedBank(fed)=fedBank(fed)-casher
   If randy>0 Then fedBank(fed)=fedBank(fed)+casher
   If fedBank(fed)<0 Then fedBank(fed)=0
  EndIf
  ;champion logic
  If fed=>7 Then fedChampWorld(fed)=0 : fedChampInter(fed)=0 : fedChampTag(fed,1)=0 : fedChampTag(fed,1)=0
  If charFed(fedChampWorld(fed))<>fed Then fedChampWorld(fed)=0
  If charFed(fedChampInter(fed))<>fed Then fedChampInter(fed)=0
  If charFed(fedChampTag(fed,1))<>fed Or charFed(fedChampTag(fed,2))<>fed Then fedChampTag(fed,1)=0 : fedChampTag(fed,2)=0
  ;check limits
  CheckFedLimits(fed)
 Next
End Function

;--------------------------------------------------------------------
;////////////////////// FIND EVENTS FOR NEWS ////////////////////////
;--------------------------------------------------------------------
Function FindEvents()
 ;reset events
 no_events=0
 For count=1 To 50
  eEvent(count)=0 : eChar(count)=0 : eFoc(count)=9
  eVariable(count)=0 : eCharged(count)=0
 Next
 ;SCAN ROSTER FOR EVENTS
 For cyc=1 To fedSize(fed)
  char=fedRoster(fed,cyc)
  charEvent(char)=0
  ResetNewValues(char)
  ;-------------------- CONTRACT ISSUES (1-10) --------------------
  If charContract(char)=0 And CharBusy(char)=0
   ;assess chances of leaving school
   chance=(100-GetValue(char))*2
   If fedSize(7)=>32 Or GetValue(char)=>70 Then chance=chance/2
   If chance<10 Then chance=10
   If fed=7 And char<>fedBooker(fed) Then randy=Rnd(0,chance) Else randy=Rnd(0,20)
   ;force renewal for team-mates
   If char=charFriend(gamChar(slot)) And gamFormat(slot)=2 Then randy=0
   If DesignatedRef(char) And char<>fedBooker(fed) Then randy=0
   ;renew contract
   If randy=<3 And fedSize(fed)<optFedLim And charHap(char)>50 And (fed<>7 Or char=fedBooker(fed)) Then AddEvent(char,1)
   ;move elsewhere
   If randy=4 And fedSize(fed)>15 Then AddEvent(char,2)
   ;released 
   If randy=5 And fedSize(fed)>24 And fed<>7 And fedSize(7)=<36 And char<>fedBooker(fed) And GetValue(char)=<60 And charPop(char)<fedPop(fed)-5 And TitleHolder(char,0)=0
    AddEvent(char,3) 
   EndIf
   ;retired
   rando=Rnd(0,65-charAge(char))
   If randy=6 And rando=0 And fedSize(fed)>24 And fedSize(8)=<16 And charAge(char)>optOldAge Then AddEvent(char,4) 
  EndIf
  ;status changes
  If fed=charFed(gamChar(slot))
   If charInjured(char)=0 And charOldInjured(char)>0 Then AddEvent(char,6) ;back from injury 
   If charVacant(char)=0 And charOldVacant(char)>0 Then AddEvent(char,7) ;back from vacancy
  EndIf
  ;------------------- CAREER SPECIFIC (10-50) -----------------------
  If char=gamChar(slot)
   ;URGENT EVENTS (10-20)
   If gamMonth(slot)=1 And gamWeek(slot)=1 Then AddEvent(char,10) ;new year
   If fed<>7 And charContract(char)=1 Then AddEvent(char,11) ;contract about to expire
   If fed<>7 And charContract(char)=0 Then AddEvent(char,12) ;contract expired
   ;RANDOM EVENTS (20-50) 
   ;health issues
   randy=Rnd(0,charTough(char)*2)
   If randy=<charTrainLevel(char) And charTrainCourse(char)>0 Then AddEvent(char,20) ;training injury
   randy=Rnd(0,20)
   If randy=0 And charInjured(char)>0 Then AddEvent(char,21) ;injury gets worse
   If randy=1 And charInjured(char)>1 Then AddEvent(char,22) ;injury gets better
   randy=Rnd(0,50)
   If randy=0 And charHealth(char)>25 And charInjured(char)=0 Then AddEvent(char,23) ;feeling ill
   If randy=1 And charHealth(char)<75 And charInjured(char)=0 Then AddEvent(char,24) ;feeling good
   If charInjured(char)=0
    randy=Rnd(0,30)
    If randy=0 And (gamVariable(slot)=1 Or gamOldVariable(slot)=1) Then AddEvent(char,25) ;reaction to painkillers
    If randy=1 And (gamVariable(slot)=2 Or gamOldVariable(slot)=2) Then AddEvent(char,26) ;reaction to steroids
   EndIf
   ;gain weight
   chance=50
   If charTrainCourse(char)=1 Or charTrainCourse(char)=5 Then chance=chance/2
   If gamVariable(slot)=2 Or gamOldVariable(slot)=2 Then chance=10 
   randy=Rnd(0,chance)
   If randy=0 And ModelType(char,1)<>7 And ModelType(char,1)<>9 Then AddEvent(char,27) 
   ;lose weight
   chance=50
   If charTrainCourse(char)=>2 And charTrainCourse(char)=<4 Then chance=chance/2
   If gamVariable(slot)=2 Or gamOldVariable(slot)=2 Then chance=9999999
   randy=Rnd(0,chance)
   If randy=0 And ModelType(char,1)<>1 And ModelType(char,1)<>8 Then AddEvent(char,28)
   ;image changes
   If gamClause(slot,1)=0
    If gamExperience(slot,fed)=<1 Then chance=5 Else chance=30
    randy=Rnd(0,chance)
    If randy=<1 Then AddEvent(char,29) ;new name
    randy=Rnd(0,chance)
    If randy=<1 Or charTheme(char)=0 Then AddEvent(char,30) ;new gimmick
    randy=Rnd(0,chance)
    If randy=<1 Then AddEvent(char,31) ;new costume
   EndIf
   ;lack of exposure
   randy=Rnd(0,3)
   If randy=0 And GetDate()>1 And gamSchedule(slot,GetDate()-1)=<0 And charPop(char)>50 Then AddEvent(char,32) 
   ;gain height
   chance=50
   If gamVariable(slot)=2 Or gamOldVariable(slot)=2 Then chance=10
   randy=Rnd(0,chance)
   If randy=0 And charHeight(char)<32 And (charAge(char)=<21 Or chance=10) Then AddEvent(char,33)
   ;abuse accusation
   female=0
   For count=1 To fedSize(fed)
    If GetGender(fedRoster(fed,count))=1 Then female=1
   Next
   randy=Rnd(0,charAtt(char)*2)
   If randy=0 And female=1 And GetGender(char)<>1 Then AddEvent(char,34)
   ;fans determine allegiance
   If fed=<6
    randy=Rnd(0,40)
    If randy=0 And charHeel(char)=0 Then AddEvent(char,35) ;refuse to accept face
    If randy=1 And charHeel(char)=1 Then AddEvent(char,36) ;refuse to accept heel
   EndIf
  EndIf
  ;----------------- UNIVERSAL EVENTS (50-90) ---------------
  ;stripped of titles
  If charInjured(char)=>4 Or charVacant(char)=>4
   If TitleHolder(char,1) Then AddEvent(char,50) ;stripped of World
   If TitleHolder(char,2) Then AddEvent(char,51) ;stripped of Inter
   If TitleHolder(char,3) Then AddEvent(char,52) ;stripped of Tags
  EndIf
  If char=gamChar(slot) And TitleHolder(char,3)
   If gamFormat(slot)=1 Or charFriend(char)=0 Then AddEvent(char,52) ;stripped of Tags due to no team-mate
  EndIf
  ;age issues
  If fed=charFed(gamChar(slot))
   If gamMonth(slot)=1 And gamWeek(slot)=1 And charAge(char)=optOldAge Then AddEvent(char,53) ;reached peak
   If charTrainCourse(char)>0 And charAge(char)=>optOldAge Then AddEvent(char,54) ;training is futile
  EndIf
  ;relationships
  If CharBusy(char)=0 And fed=charFed(gamChar(slot))
   randy=Rnd(0,180)
   If randy=0 And charHeel(char)=1 And GetRatio(fed,0)<GetRatio(fed,1) Then AddEvent(char,55) ;turn face
   If randy=1 And charHeel(char)=0 And GetRatio(fed,1)<GetRatio(fed,0) Then AddEvent(char,56) ;turn heel
   If randy=2 And charFriend(char)=0 Then AddEvent(char,57) ;make friends
   If randy=3 And charEnemy(char)=0 Then AddEvent(char,58) ;become enemies
   If randy=4 And charManager(char)=0 And DesignatedRef(char)=0 Then AddEvent(char,59) ;acquire manager
  EndIf
  ;spontaneous stat improvements
  If fed=charFed(gamChar(slot))
   chance=(110-charAtt(char))*20
   If char=gamChar(slot) Then chance=chance/2
   randy=Rnd(0,chance)
   If randy=0 And charPop(char)=<80 Then AddEvent(char,60) ;popularity
   If charAge(char)<optOldAge
    If randy=1 And charStr(char)=<80 Then AddEvent(char,61) ;strength
    If randy=2 And charSkl(char)=<80 Then AddEvent(char,62) ;skill
    If randy=3 And charAgl(char)=<80 Then AddEvent(char,63) ;agility
    If randy=4 And charStam(char)=<80 Then AddEvent(char,64) ;stamina
    If randy=5 And charTough(char)=<80 Then AddEvent(char,65) ;toughness
   EndIf
   If randy=6 And charAtt(char)=<80 And CharBusy(char)=0 Then AddEvent(char,66) ;attitude
   If randy=7 And charHap(char)=<80 And CharBusy(char)=0 Then AddEvent(char,67) ;happiness
   ;spontaneous stat deteriorations
   chance=charAtt(char)*10
   If char=gamChar(slot) Then chance=chance/2
   randy=Rnd(0,chance)
   If randy=0 And charPop(char)=>60 Then AddEvent(char,68) ;popularity
   If randy=1 And charStr(char)=>60 Then AddEvent(char,69) ;strength
   If randy=2 And charSkl(char)=>60 Then AddEvent(char,70) ;skill
   If randy=3 And charAgl(char)=>60 Then AddEvent(char,71) ;agility
   If randy=4 And charStam(char)=>60 Then AddEvent(char,72) ;stamina
   If randy=5 And charTough(char)=>60 Then AddEvent(char,73) ;toughness
   If randy=6 And charAtt(char)=>60 And CharBusy(char)=0 Then AddEvent(char,74) ;attitude
   If randy=7 And charHap(char)=>60 And CharBusy(char)=0 Then AddEvent(char,75) ;happiness
  EndIf
  ;relationship affects status
  If fed=charFed(gamChar(slot)) And charFriend(char)>0
   randy=Rnd(0,100)
   If char=gamChar(slot) Then randy=Rnd(0,50)
   If randy=0 And charPop(char)<charPop(charFriend(char)) Then AddEvent(char,76) ;popularity improves
   If randy=1 And charPop(char)>charPop(charFriend(char)) Then AddEvent(char,77) ;popularity suffers
   If randy=2 And char<>gamChar(slot) And charFriend(char)<>gamChar(slot) Then AddEvent(char,78) ;fall out with friend
  EndIf
  ;injuries & fatalities (90-100)
  If CharBusy(char)=0 And charEvent(char)=0
   If fed=charFed(gamChar(slot)) 
    randy=Rnd(0,charTough(char)*10)
    If randy=<1 And charInjured(char)=0 Then AddEvent(char,90) ;small injury
    If randy=2 And charInjured(char)=0 Then AddEvent(char,91) ;serious injury
   EndIf
   If fedSize(fed)>10 And fedSize(9)=<16
    randy=Rnd(0,15000)
    If randy=0 And charInjured(char)=0 Then AddEvent(char,92) ;crippling injury
    If randy=1 And charInjured(char)=0 Then AddEvent(char,93) ;fatal injury
    If randy=2 And charAge(char)>optOldAge Then AddEvent(char,94) ;natural death
    If randy=3 And charAtt(char)<60 Then AddEvent(char,95) ;suspicious death
    If randy=4 And charHap(char)<60 Then AddEvent(char,96) ;suicide
   EndIf
  EndIf
  ;CHECK NEW VALUES
  CheckNewValues(char)
 Next
 ;-------------------- CORPORATE ISSUES --------------------
 fedNewPop(fed)=fedPop(fed)
 fedNewRep(fed)=fedRep(fed)
 ;buy talent
 randy=Rnd(0,fedSize(fed))
 If randy=0 And fedSize(fed)=<32 And fed=<6 Then AddEvent(0,5) 
 ;new owner
 randy=Rnd(0,500)
 If randy=0 Or fedBooker(fed)=0 Or charFed(fedBooker(fed))<>fed Then AddEvent(0,100) 
 ;championship issues
 If fed=<6 And fedSize(fed)=>5
  ;new world champ
  randy=Rnd(0,GetValue(fedChampWorld(fed)))
  If CharBusy(fedChampWorld(fed))>0 Then randy=99
  If randy=<1 Or fedChampWorld(fed)=0 Or charFed(fedChampWorld(fed))<>fed Then AddEvent(0,101)
  ;new inter champ
  randy=Rnd(0,GetValue(fedChampInter(fed)))
  If CharBusy(fedChampInter(fed))>0 Then randy=99
  If randy=<1 Or fedChampInter(fed)=0 Or charFed(fedChampInter(fed))<>fed Then AddEvent(0,102)
  ;new tag champs
  If fed<>5
   chance=(GetValue(fedChampTag(fed,1))+GetValue(fedChampTag(fed,2)))/2
   randy=Rnd(0,chance)
   If CharBusy(fedChampTag(fed,1))>0 Or CharBusy(fedChampTag(fed,2))>0 Then randy=99
   If randy=<1 Or fedChampTag(fed,1)=0 Or fedChampTag(fed,2)=0 Or charFed(fedChampTag(fed,1))<>fed Or charFed(fedChampTag(fed,2))<>fed Then AddEvent(0,103)
  EndIf
 EndIf
 ;status fluctuations
 If fed=<6
  randy=Rnd(0,10)
  If randy=<1 And fedChampWorld(fed)>0 And charPop(fedChampWorld(fed))<fedPop(fed) Then AddEvent(0,104) ;champ taints popularity
  If randy=2 And fedChampWorld(fed)>0 And charPop(fedChampWorld(fed))>fedPop(fed)+5 Then AddEvent(0,105) ;champ boosts popularity
  randy=Rnd(0,100)
  If randy=1 And fedPop(fed)<90 Then AddEvent(0,106) ;major popularity boost
  If randy=2 And fedPop(fed)>60 Then AddEvent(0,107) ;major popularity decrease
  If randy=3 And fedRep(fed)<90 Then AddEvent(0,108) ;major reputation boost
  If randy=4 And fedRep(fed)>60 Then AddEvent(0,109) ;major reputation decrease
 EndIf
 ;acknowledge significant changes
 If fed=<6
  If fed<>fedLeader And fedNewPop(fed)>fedPop(fedLeader) Then AddEvent(0,110) ;top fed!
  ;If fedNewRep(fed)<60 And fedOldRep(fed)=>60 Then AddEvent(0,111) ;dipped into hardcore
  ;If fedNewRep(fed)=>60 And fedOldRep(fed)<60 Then AddEvent(0,112) ;climbed out of hardcore
 EndIf
 ;schedule issues
 If fed=charFed(gamChar(slot))
  randy=Rnd(0,50)
  If randy=0 And gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<2 Then AddEvent(0,150) ;last minute cancellation
  If randy=1 And GetDate()=<47 And gamSchedule(slot,GetDate()+1)=>1 And gamSchedule(slot,GetDate()+1)=<2 Then AddEvent(0,151) ;next day cancellation
  If randy=>2 And randy=<3 And GetDate()=<44 And gamSchedule(slot,GetDate()+4)=>1 And gamSchedule(slot,GetDate()+4)=<2 Then AddEvent(0,152) ;planned rest
  If randy=>4 And randy=<5 And fed=7 And GetDate()=<47 Then AddEvent(0,153) ;champion visits school
  charity=0 : inter=0
  For count=GetDate() To GetDate()+4
   If gamSchedule(slot,count)=4 Then charity=1
   If gamSchedule(slot,count)=5 Then inter=1
  Next
  If fed=<6
   randy=Rnd(0,50)
   If KeyDown(23) Then randy=2
   If randy=0 And GetDate()=<46 And gamSchedule(slot,GetDate()+1)=<2 And gamSchedule(slot,GetDate()+2)=<2 Then AddEvent(0,154) ;PPV tour
   If randy=1 And charity=0 And GetDate()=<44 And gamSchedule(slot,GetDate()+4)=<2 Then AddEvent(0,155) ;charity event
   If randy=2 And inter=0 And GetDate()=<44 And gamSchedule(slot,GetDate()+4)=<2 Then AddEvent(0,156) ;inter-promotional event
  EndIf
  If gamSchedule(slot,GetDate()+1)=>2 And charInjured(gamChar(slot))=<1 Then AddEvent(gamChar(slot),157) ;big show soon reminder
  If gamSchedule(slot,GetDate())=>2 And charInjured(gamChar(slot))=0 Then AddEvent(gamChar(slot),158) ;big show tonight reminder
 EndIf
 ;mission reminders
 If fed=charFed(gamChar(slot)) And gamMission(slot)>0 Then AddEvent(gamChar(slot),200+gamMission(slot))
 ;check fed changes
 CheckFedLimits(fed)
 ;nothing to report!
 If no_events=0 Then AddEvent(0,-1) 
End Function

;--------------------------------------------------------------------
;//////////////////// ADD AN EVENT TO THE LIST //////////////////////
;--------------------------------------------------------------------
Function AddEvent(char,event)
 If no_events<50
  ;increment list
  no_events=no_events+1
  eChar(no_events)=char
  eEvent(no_events)=event
  If char>0 Then charEvent(char)=event
  If char>0 And char<>gamChar(slot) Then ResetOldValues(char)
  eFoc(no_events)=10
  its=0
  ;CONTRACT ISSUES
  ;renew contract
  If event=1 
   GetNewContract(char)
   charNewAtt(char)=charNewAtt(char)+Rnd(-1,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=6 
  EndIf
  ;move elsewhere
  If event=2 
   Repeat
    newbie=Rnd(1,6)
   Until newbie<>fed And fedSize(newbie)<optFedLim
   Trade(char,newbie)
   GetNewContract(char)
   charNewAtt(char)=charNewAtt(char)+Rnd(-1,1)
   If charNewHap(char)<50 Then charNewHap(char)=50
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=6 
  EndIf
  ;released
  If event=3
   Trade(char,7)
   GetNewContract(char)
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),0,0)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),0,0)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,0)
   eFoc(no_events)=1 
  EndIf
  ;retired
  If event=4
   Trade(char,8)
   GetNewContract(char)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,0)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,0)
   eFoc(no_events)=6
  EndIf
  ;buy talent
  If event=5 
   Repeat
    its=its+1 : satisfied=1 : char=Rnd(1,no_chars)
    If CharBusy(char)>0 Or char=fedBooker(charFed(char)) Or DesignatedRef(char) Then satisfied=0
    If charFed(char)=fed Or charFed(char)=9 Then satisfied=0
    If fedSize(charFed(char))<15 Then satisfied=0
    If charContract(char)=0 And fed<>8 And its<100 Then satisfied=0
    If charContract(char)>30 And its<100 Then satisfied=0
    If charEvent(char)>0 Then satisfied=0
   Until satisfied=1 Or its>1000
   eVariable(no_events)=charFed(char)
   Trade(char,fed)
   GetNewContract(char)
   charSalary(char)=charSalary(char)+(charSalary(char)/2)
   ResetNewValues(char)
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,1)
   charNewAtt(char)=charNewAtt(char)+Rnd(-1,1)
   If charNewHap(char)<50 Then charNewHap(char)=50
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,0)
   CheckNewValues(char)
   eChar(no_events)=char
   eFoc(no_events)=1 
  EndIf
  ;return from injury
  If event=6
   If char=gamChar(slot) And gamPromo(slot,GetDate())=0 Then gamPromo(slot,GetDate())=27
  EndIf
  ;CAREER ISSUES
  ;injury gets worse 
  If event=21
   charInjured(char)=charInjured(char)+1 
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,0)
   eFoc(no_events)=6
  EndIf
  ;injury gets better
  If event=22
   charInjured(char)=charInjured(char)/2 
   For count=GetDate() To 48
    If gamSchedule(slot,count)=-1 Then gamSchedule(slot,count)=Rnd(0,1)
   Next
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,0)
   eFoc(no_events)=6
  EndIf
  ;feeling ill
  If event=23
   charNewHealth(char)=0
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,1)
   eFoc(no_events)=1
  EndIf
  ;feeling good
  If event=24
   charNewHealth(char)=100
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=1
  EndIf
  ;gain weight
  If event=27
   model=ModelType(char,1)
   If model=1 Then charModel(char,1)=4
   If model=2 Or model=3 Then charModel(char,1)=9
   If model=4 Then charModel(char,1)=11
   If model=5 Then charModel(char,1)=12
   If model=6 Then charModel(char,1)=13
   If model=8 Then charModel(char,1)=17
   charModel(char,2)=charModel(char,1)
   charModel(char,3)=charModel(char,1)
   charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),100,0)
   charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),30,1)
   charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),30,0)
   charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),30,1)
   charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),100,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),30,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,1)  
   eFoc(no_events)=1 
  EndIf
  ;lose weight
  If event=28
   model=ModelType(char,1)
   If model=2 Or model=3 Then charModel(char,1)=1
   If model=4 Then charModel(char,1)=4
   If model=5 Then charModel(char,1)=9
   If model=6 Then charModel(char,1)=11
   If model=7 Then charModel(char,1)=12
   If model=9 Then charModel(char,1)=14
   charModel(char,2)=charModel(char,1)
   charModel(char,3)=charModel(char,1)
   charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),30,0)
   charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),100,1)
   charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),100,0)
   charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),100,1)
   charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),30,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1) 
   eFoc(no_events)=1 
  EndIf
  ;new name
  If event=29 
   charName$(char)=GetNewName$() 
   If gamPromo(slot,GetDate())=0 And gamExperience(slot,fed)>1
    gamPromo(slot,GetDate())=9 
    If charEnemy(char)>0 Then gamOpponent(slot,GetDate())=charEnemy(char)
   EndIf
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0) 
   eFoc(no_events)=6
  EndIf
  ;new gimmick
  If event=30 
   charTheme(char)=Rnd(1,no_themes)
   charThemePitch(char)=DefaultPitch(charTheme(char))
   charLight(char)=Rnd(5,no_lights)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0) 
   eFoc(no_events)=6
  EndIf
  ;new costume
  If event=31
   ChangeCostume(char)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0) 
   eFoc(no_events)=6
  EndIf
  ;lack of exposure affects status
  If event=32
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),30,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,1)
   eFoc(no_events)=1
  EndIf
  ;gain height
  If event=33
   charHeight(char)=charHeight(char)+1
   charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),100,1)
   charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),30,1)
   charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),30,1)
   charNewStam(char)=charNewAgl(char)+PursueValue(charStam(char),30,1)
   charNewTough(char)=charNewAgl(char)+PursueValue(charTough(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)  
   eFoc(no_events)=1 
  EndIf
  ;abuse accusation
  If event=34
   Repeat
    eVariable(no_events)=fedRoster(fed,Rnd(1,fedSize(fed)))
   Until GetGender(eVariable(no_events))=1
   BecomeEnemies(char,eVariable(no_events),1)
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),30,0)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),30,0)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0)
   gamSchedule(slot,GetDate())=0
   gamSchedule(slot,GetDate()+1)=0
   eFoc(no_events)=1
  EndIf
  ;not accepted as face
  If event=35
   charHeel(char)=1
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),30,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),30,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,1)
   eFoc(no_events)=1
  EndIf
  ;not accepted as heel
  If event=36
   charHeel(char)=0
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=1
  EndIf
  ;UNIVERSAL EVENTS
  ;stripped of title
  If event=>50 And event=<52
   If event=50 Then fedChampWorld(fed)=0
   If event=51 Then fedChampInter(fed)=0
   If event=52 Then fedChampTag(fed,1)=0 : fedChampTag(fed,2)=0
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),0,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),0,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,0)
   eFoc(no_events)=1 
  EndIf
  ;reached peak
  If event=53
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,0)
   eFoc(no_events)=6 
  EndIf
  ;training is futile
  If event=54 
   charTrainCourse(char)=0
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,1)
   eFoc(no_events)=6 
  EndIf
  ;turn face
  If event=55
   charHeel(char)=0
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)  
   eFoc(no_events)=1 
  EndIf
  ;turn heel
  If event=56
   charHeel(char)=1
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),0,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,1)  
   eFoc(no_events)=1 
  EndIf
  ;become friends
  If event=57
   Repeat
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    its=its+1
   Until newbie<>char And CharBusy(newbie)=0 And newbie<>charFriend(char) And (charHeel(newbie)=charHeel(char) Or its>100)
   BecomeFriends(char,newbie,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=1
  EndIf
  ;become enemies
  If event=58
   Repeat
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    its=its+1
   Until newbie<>char And CharBusy(newbie)=0 And newbie<>charEnemy(char) And (charHeel(newbie)<>charHeel(char) Or its>100)
   BecomeEnemies(char,newbie,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),0,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,1)
   eFoc(no_events)=1
  EndIf
  ;acquire manager
  If event=59
   Repeat
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    its=its+1
   Until newbie<>char And CharBusy(newbie)=0 And TitleHolder(newbie,0)=0 And DesignatedRef(newbie)=0 And (charHeel(newbie)=charHeel(char) Or its>100)
   BecomeFriends(char,newbie,0) : charManager(char)=newbie
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=1
  EndIf
  ;spontaneous stat improvements >>>>
  If event=>60 And event=<67
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   eFoc(no_events)=1
  EndIf
  If event=60 Then charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,0) ;popularity
  If event=61 Then charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),100,0) ;strength
  If event=62 Then charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),100,0) ;skill
  If event=63 Then charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),100,0) ;agility
  If event=64 Then charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),100,0) ;stamina
  If event=65 Then charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),100,0) ;toughness
  If event=66 Then charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,0) ;attitude
  If event=67 Then charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,0) ;happiness
  ;spontaneous stat deterioration <<<<
  If event=>68 And event=<75
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),30,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),30,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,1)
   eFoc(no_events)=1
  EndIf
  If event=68 Then charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),30,0) ;popularity
  If event=69 Then charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),30,0) ;strength
  If event=70 Then charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),30,0) ;skill
  If event=71 Then charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),30,0) ;agility
  If event=72 Then charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),30,0) ;stamina
  If event=73 Then charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),30,0) ;toughness
  If event=74 Then charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),30,0) ;attitude
  If event=75 Then charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0) ;happiness
  ;relationship affects status
  If event=76 Or event=77
   If event=76 Then target=100 Else target=30
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),target,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),target,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),target,1)
   eFoc(no_events)=1
  EndIf
  ;fall out with friend
  If event=78
   BecomeEnemies(char,charFriend(char),1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),0,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,1)
   eFoc(no_events)=1
  EndIf
  ;HEALTH ISSUES
  ;minor injury
  If event=90 Or event=20 Or event=25 Or event=26
   charInjured(char)=Rnd(2,5) : charNewHealth(char)=0
   charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),30,0)
   charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),30,0)
   charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),30,0)
   charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),30,0)
   charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),30,0)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0)
   charTrainCourse(char)=0
   eFoc(no_events)=1 
  EndIf
  ;serious injury
  If event=91
   charInjured(char)=Rnd(4,10) : charNewHealth(char)=0
   charNewStr(char)=charNewStr(char)+(PursueValue(charStr(char),30,0)*2)
   charNewSkl(char)=charNewSkl(char)+(PursueValue(charSkl(char),30,0)*2)
   charNewAgl(char)=charNewAgl(char)+(PursueValue(charAgl(char),30,0)*2)
   charNewStam(char)=charNewStam(char)+(PursueValue(charStam(char),30,0)*2)
   charNewTough(char)=charNewTough(char)+(PursueValue(charTough(char),30,0)*2)
   charNewHap(char)=charNewHap(char)+(PursueValue(charHap(char),30,0)*2)
   charTrainCourse(char)=0
   eFoc(no_events)=1 
  EndIf
  ;crippled
  If event=92 
   charInjured(char)=Rnd(6,16) : charNewHealth(char)=0
   charNewStr(char)=30 : charNewSkl(char)=30 : charNewAgl(char)=30
   charNewStam(char)=30 : charNewTough(char)=30
   charNewHap(char)=charHap(char)/2
   charTrainCourse(char)=0
   eFoc(no_events)=1 
  EndIf
  ;dead
  If event=>93 And event=<96 
   Trade(char,9)
   If fed=<6 And charFed(gamChar(slot))=<6 And gamSchedule(slot,GetDate())=<2 Then gamSchedule(slot,GetDate())=3 
  EndIf
  ;CORPORATE ISSUES
  ;new booker
  If event=100 
   Repeat
    char=fedRoster(fed,Rnd(1,fedSize(fed)))
   Until CharBusy(char)=0 And DesignatedRef(char)=0 And char<>fedBooker(fed) And TitleHolder(char,0)=0
   fedBooker(fed)=char : eChar(no_events)=char
   GetNewContract(char)
   If fed=charFed(gamChar(slot)) Then gamMission(slot)=0
  EndIf
  ;new world champ
  If event=101 
   level=90
   Repeat
    its=its+1 : satisfied=1
    char=fedRoster(fed,Rnd(1,fedSize(fed)))
    If charPop(char)<level Then satisfied=0 
    If TitleHolder(char,0)>0 Or charInjured(char)>0 Then satisfied=0
    If CharBusy(char)>0 Or DesignatedRef(char) Then satisfied=0
    If its>100 Then level=level-10 : its=0 
   Until satisfied=1 
   fedChampWorld(fed)=char : eChar(no_events)=char
   ResetNewValues(char)
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)  
   CheckNewValues(char)
   eFoc(no_events)=1 
  EndIf
  ;new inter champ
  If event=102 
   Repeat
    char=fedRoster(fed,Rnd(1,fedSize(fed)))
   Until CharBusy(char)=0 And DesignatedRef(char)=0 And TitleHolder(char,0)=0 And charInjured(char)=0
   fedChampInter(fed)=char : eChar(no_events)=char
   ResetNewValues(char)
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   CheckNewValues(char) 
   eFoc(no_events)=1 
  EndIf
  ;new tag champs
  If event=103 
   Repeat
    char=fedRoster(fed,Rnd(1,fedSize(fed)))
    partner=fedRoster(fed,Rnd(1,fedSize(fed)))
    If charFriend(char)>0 Then partner=charFriend(char) 
   Until char<>partner And CharBusy(char)=0 And CharBusy(partner)=0 And DesignatedRef(char)=0 And DesignatedRef(partner)=0 And TitleHolder(char,0)=0 And TitleHolder(partner,0)=0 And charInjured(char)=0 And charInjured(partner)=0
   fedChampTag(fed,1)=char : fedChampTag(fed,2)=partner
   BecomeTeam(char,partner) 
   eChar(no_events)=char 
   ResetNewValues(char)
   charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),100,1)
   charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),100,1)
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),100,1)
   CheckNewValues(char)
   eFoc(no_events)=1
  EndIf
  ;status changes
  If event=104 Then fedNewPop(fed)=fedNewPop(fed)-1 : eFoc(no_events)=1 ;champ taints popularity
  If event=105 Then fedNewPop(fed)=fedNewPop(fed)+1 : eFoc(no_events)=1 ;champ boosts popularity
  If event=106 Then fedNewPop(fed)=fedNewPop(fed)+PursueValue(fedPop(fed),100,0) : eFoc(no_events)=1 ;popularity increase
  If event=107 Then fedNewPop(fed)=fedNewPop(fed)+PursueValue(fedPop(fed),50,0) : eFoc(no_events)=1 ;popularity decrease
  If event=108 Then fedNewRep(fed)=fedNewRep(fed)+PursueValue(fedRep(fed),100,0) : eFoc(no_events)=1 ;reputation increase
  If event=109 Then fedNewRep(fed)=fedNewRep(fed)+PursueValue(fedRep(fed),50,0) : eFoc(no_events)=1 ;reputation decrease
  ;take top slot
  If event=110
   fedNewPop(fed)=fedNewPop(fed)+1 
   fedNewRep(fed)=fedNewRep(fed)+1 
   eVariable(no_events)=fedLeader
   fedLeader=fed
   eFoc(no_events)=1
  EndIf
  ;schedule issues
  If event=150 Then gamSchedule(slot,GetDate())=0 ;last minute cancellation
  If event=151 Then gamSchedule(slot,GetDate()+1)=0 ;tomorrow cancellation
  If event=152 Then gamSchedule(slot,GetDate()+4)=0 ;planned break
  If event=154 Then gamSchedule(slot,GetDate()+1)=2 : gamSchedule(slot,GetDate()+2)=2 ;PPV tour
  If event=155 Then gamSchedule(slot,GetDate()+4)=4 ;charity event
  ;royal visit
  If event=153 
   If gamSchedule(slot,GetDate()+1)=0 Then gamSchedule(slot,GetDate()+1)=1
   gamMatch(slot,GetDate()+1)=14
   gamPromo(slot,GetDate()+1)=32
   Repeat
    gamOpponent(slot,GetDate()+1)=fedChampWorld(Rnd(1,6))
   Until gamOpponent(slot,GetDate()+1)>0
   eChar(no_events)=gamOpponent(slot,GetDate()+1)
  EndIf
  ;arrange inter-promotional
  If event=156
   Repeat
    rival=Rnd(1,6)
   Until rival<>fed
   eVariable(no_events)=rival
   gamInterProm(slot,GetDate()+4)=rival
   gamSchedule(slot,GetDate()+4)=5 : gamMatch(slot,GetDate()+4)=3 
   gamGimmick(slot,GetDate()+4)=0 : gamOpponent(slot,GetDate()+4)=0 
  EndIf
 EndIf
End Function

;----------------------------------------------------------------------
;/////////////////////// 18. NEWS PROCESS /////////////////////////////
;----------------------------------------------------------------------
Function NewsReports()
;prepare events
FindEvents()
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
foc=eFoc(1) : cyc=1
go=0 : gotim=-10 : keytim=0
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
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PROCESS
    gotim=gotim+1
	If gotim>30 
	 ;alter states
	 If eChar(cyc)>0 Then AlterStats(eChar(cyc)) Else AlterFedStats(fed)
	 ;browse events
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	  If keytim=0
	   If foc=10 Then cyc=cyc+1 : foc=eFoc(cyc) : keytim=10 : PlaySound sMenuBrowse : gotim=-10
	   If cyc>no_events Then cyc=no_events : foc=10 : go=1
	  Else
	   If foc<10 And keytim>1 Then keytim=1
	  EndIf
	 EndIf 
	EndIf 
	;paper impact
	If gotim=0 Then PlaySound sPaper
	
 UpdateWorld
 Next 
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)  
 ;show profiles
 char=eChar(cyc)
 If char>0 
  If gotim>25 Then HighlightStats()
  If gotim>10 Then DrawProfile(char,-1,-1)
 EndIf
 If char=0 
  If gotim>25 Then HighlightFedStats()
  If gotim>10 Then DrawFedProfile(fed,-1,-1)
 EndIf
 ;get context
 g=GetGender(char)
 ;CONSTRUCT NEWSPAPER
 If gotim>0
  ;mock-up
  x=rX#(400) : y=rY#(370)
  DrawImage gNewspaper,x,y
  SetFont font(0) : Color 110,110,110
  Text x-268,y-107,"The Nation's #1 Newspaper",0,1
  Text x+40,y-107,textWeek$(gamWeek(slot))+" of "+textMonth$(gamMonth(slot))+" "+gamYear(slot),1,1
  Text x+235,y-107,"50 Cents",0,1
  ;contract issues
  SetFont fontNews(2) : Color 0,0,0
  If eEvent(cyc)=-1
   headline$="CALM BEFORE THE STORM"
   Text x+5,y+150,fedName$(fed)+" have nothing to report...",1,1
  EndIf
  If eEvent(cyc)=1
   headline$="THE SAGA CONTINUES"
   Text x+5,y+138,charName$(char)+" has renewed "+Lower$(His$(g))+" contract with "+fedName$(fed)+".",1,1
   Text x+5,y+166,He$(g)+"'ll remain on the roster for the next "+charContract(char)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=2
   headline$="MOVING ON"
   Text x+5,y+138,charName$(char)+" has left "+fedName$(fed),1,1
   Text x+5,y+166,"to pursue a career at "+fedName$(charFed(char))+"...",1,1
  EndIf
  If eEvent(cyc)=3
   headline$="BACK TO BASICS"
   Text x+5,y+138,charName$(char)+" has been released from "+fedName$(fed)+".",1,1
   Text x+5,y+166,He$(g)+"'ll have to find permanent work elsewhere...",1,1
  EndIf
  If eEvent(cyc)=4
   headline$="END OF THE ROAD"
   Text x+5,y+138,"At the age of "+charAge(char)+", "+charName$(char)+" has retired from",1,1
   Text x+5,y+166,"the wrestling business to pursue other ambitions...",1,1
  EndIf
  If eEvent(cyc)=5
   headline$="HOT PROPERTY"
   Text x+5,y+138,fedName$(fed)+" have bought "+charName$(char)+" from",1,1
   Text x+5,y+166,fedName$(eVariable(cyc))+" and signed "+Him$(g)+" to a major deal...",1,1
  EndIf
  If eEvent(cyc)=6
   headline$="ROAD TO RECOVERY"
   Text x+5,y+138,charName$(char)+" has recovered from "+Lower$(His$(g))+" injury",1,1
   Text x+5,y+166,"and should now return to active competition...",1,1
  EndIf
  If eEvent(cyc)=7
   headline$="BACK TO BUSINESS"
   Text x+5,y+138,charName$(char)+" has returned from "+Lower$(His$(g))+" absence",1,1
   Text x+5,y+166,"and should now return to active competition...",1,1
  EndIf
  ;urgent career issues
  If eEvent(cyc)=10
   headline$="HAPPY NEW YEAR!"
   If eCharged(cyc)=0 Then PlaySound sCrowd(4) : eCharged(cyc)=1
   Text x+5,y+138,"A new year is upon us! You are now "+charAge(char)+" years old,",1,1
   Text x+5,y+166,"and have been in the business for "+CountExperience(slot)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=11
   headline$="THE END IS NEAR"
   Text x+5,y+138,"Your contract with "+fedName$(fed)+" expires soon!",1,1
   Text x+5,y+166,"Consider your future with the promotion...",1,1
  EndIf
  If eEvent(cyc)=12
   headline$="JUDGMENT DAY"
   Text x+5,y+138,"Your contract with "+fedName$(fed)+" has expired!",1,1
   Text x+5,y+166,"There'll be a meeting later to discuss your future...",1,1
  EndIf
  ;random career issues
  If eEvent(cyc)=20
   headline$="NO PAIN, NO GAIN"
   ChannelPitch chTheme,40000
   Text x+5,y+138,charName$(char)+" incurred an injury while training,",1,1
   Text x+5,y+166,"and will be out of action until "+Lower$(He$(g))+" recovers...",1,1
  EndIf
  If eEvent(cyc)=21
   headline$="HEALTH COMPLICATIONS"
   Text x+5,y+138,charName$(char)+"'s injury seems to have worsened",1,1
   Text x+5,y+166,"and will now take "+charInjured(char)+" weeks to treat...",1,1
  EndIf
  If eEvent(cyc)=22
   headline$="MIRACLE RECOVERY!"
   Text x+5,y+138,charName$(char)+"'s injury isn't as bad as first",1,1
   Text x+5,y+166,"thought and should heal in just "+charInjured(char)+" weeks!",1,1
  EndIf
  If eEvent(cyc)=23
   headline$="HEALTH SCARE"
   Text x+5,y+138,charName$(char)+"'s health has been sapped",1,1
   Text x+5,y+166,"by the symptoms of a mild illness...",1,1
  EndIf
  If eEvent(cyc)=24
   headline$="FIGHTING FIT"
   Text x+5,y+138,charName$(char)+" seems to be feeling great",1,1
   Text x+5,y+166,"today and is in perfect health!",1,1
  EndIf
  If eEvent(cyc)=25
   headline$="DRUG ABUSE"
   Text x+5,y+138,charName$(char)+" has developed an addiction",1,1
   Text x+5,y+166,"to painkillers and will need time to recover!",1,1
  EndIf
  If eEvent(cyc)=26
   headline$="STEROID ABUSE"
   Text x+5,y+138,charName$(char)+" has suffered an adverse reaction",1,1
   Text x+5,y+166,"to steroids and will need time to recover!",1,1
  EndIf
  If eEvent(cyc)=27
   headline$="LARGER THAN LIFE"
   Text x+5,y+138,charName$(char)+" seems to have gained weight!",1,1
   Text x+5,y+166,"The changes may affect "+Lower$(His$(g))+" wrestling style...",1,1
  EndIf
  If eEvent(cyc)=28
   headline$="SHEDDING THE POUNDS"
   Text x+5,y+138,charName$(char)+" seems to have lost weight!",1,1
   Text x+5,y+166,"The changes may affect "+Lower$(His$(g))+" wrestling style...",1,1
  EndIf
  If eEvent(cyc)=29
   headline$="IDENTITY CRISIS"
   Text x+5,y+138,fedName$(fed)+" have used their image rights",1,1
   Text x+5,y+166,"to change your name to '"+charName$(char)+"'!",1,1
  EndIf
  If eEvent(cyc)=30
   headline$="FACE THE MUSIC"
   Text x+5,y+138,fedName$(fed)+" have used their image rights",1,1
   Text x+5,y+166,"to assign you a new entrance gimmick!",1,1
  EndIf
  If eEvent(cyc)=31
   headline$="FASHION VICTIM"
   Text x+5,y+138,fedName$(fed)+" have used their image rights",1,1
   Text x+5,y+166,"to assign you a new wrestling costume!",1,1
  EndIf
  If eEvent(cyc)=32
   headline$="KEEPING UP APPEARANCES"
   Text x+5,y+138,charName$(char)+"'s profile has suffered due",1,1
   Text x+5,y+166,"to a lack of exposure in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=33
   headline$="GROWING PAINS"
   Text x+5,y+138,charName$(char)+" has grown an inch!",1,1
   Text x+5,y+166,"The "+charAge(char)+" year old now stands at "+GetHeight$(charHeight(char))+"...",1,1
  EndIf
  If eEvent(cyc)=34
   headline$="SEX ABUSE SCANDAL"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" has been accused of sexually",1,1
   Text x+5,y+150,"abusing a female employee of "+fedName$(fed)+"!",1,1
   Text x+5,y+175,He$(g)+"'ll be suspended until the matter is resolved...",1,1
  EndIf
  If eEvent(cyc)=35
   headline$="IDENTITY CRISIS"
   Text x+5,y+138,"The fans insist on treating "+charName$(char),1,1
   Text x+5,y+166,"as a Heel despite "+Lower$(His$(g))+" attempts to play Face!",1,1
  EndIf
  If eEvent(cyc)=36
   headline$="IDENTITY CRISIS"
   Text x+5,y+138,"The fans insist on treating "+charName$(char),1,1
   Text x+5,y+166,"as a Face despite "+Lower$(His$(g))+" attempts to play Heel!",1,1
  EndIf
  ;universal events
  If eEvent(cyc)=50
   headline$="WORLD CHAMP STRIPPED"
   Text x+5,y+138,charName$(char)+" has been stripped of the World",1,1
   Text x+5,y+166,"Championship due to being unable to defend it...",1,1
  EndIf
  If eEvent(cyc)=51
   headline$="INTER CHAMP STRIPPED"
   Text x+5,y+138,charName$(char)+" has been stripped of the Inter",1,1
   Text x+5,y+166,"Championship due to being unable to defend it...",1,1
  EndIf
  If eEvent(cyc)=52
   headline$="TAG CHAMPS STRIPPED"
   Text x+5,y+138,charName$(char)+"'s team have been stripped of the Tag",1,1
   Text x+5,y+166,"Championships due to being unable to defend them...",1,1
   DrawImage charPhoto(charFriend(char)),x+90,y+28
  EndIf
  If eEvent(cyc)=53
   headline$="OVER THE HILL"
   Text x+5,y+138,"At the age of "+charAge(char)+", "+charName$(char)+" has reached",1,1
   Text x+5,y+166,Lower$(His$(g))+" physical peak. "+He$(g)+"'s as good as "+Lower$(He$(g))+"'ll ever be...",1,1
  EndIf
  If eEvent(cyc)=54
   headline$="OVER THE HILL"
   Text x+5,y+138,"At the age of "+charAge(char)+", "+charName$(char)+"'s training",1,1
   Text x+5,y+166,"regime is futile. "+He$(g)+"'s as good as "+Lower$(He$(g))+"'ll ever be...",1,1
  EndIf
  If eEvent(cyc)=55
   headline$="A NEW LEAF"
   Text x+5,y+138,charName$(char)+" has decided to turn Face,",1,1
   Text x+5,y+166,"and will now be portrayed in a positive light...",1,1
  EndIf
  If eEvent(cyc)=56
   headline$="THE DARK SIDE"
   Text x+5,y+138,charName$(char)+" has decided to turn Heel,",1,1
   Text x+5,y+166,"and will now be portrayed in a negative light...",1,1
  EndIf
  If eEvent(cyc)=57
   headline$="MUTUAL ADMIRATION"
   Text x+5,y+138,charName$(char)+" has become good friends",1,1
   Text x+5,y+166,"with "+charName$(charFriend(char))+"...",1,1
   DrawImage charPhoto(charFriend(char)),x+90,y+28
  EndIf
  If eEvent(cyc)=58
   headline$="BOILING POINT"
   Text x+5,y+138,charName$(char)+" has developed a heated",1,1
   Text x+5,y+166,"rivalry with "+charName$(charEnemy(char))+"!",1,1
   DrawImage charPhoto(charEnemy(char)),x+90,y+28
  EndIf
  If eEvent(cyc)=59
   headline$="UNDER NEW MANAGEMENT"
   Text x+5,y+138,charName$(char)+" has hired "+charName$(charManager(char)),1,1
   Text x+5,y+166,"to be "+Lower$(His$(g))+" manager at ringside...",1,1
   DrawImage charPhoto(charManager(char)),x+90,y+28
  EndIf 
  If eEvent(cyc)=60
   headline$="RISING STAR"
   Text x+5,y+138,charName$(char)+"'s act has been getting",1,1
   Text x+5,y+166,"over with the fans in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=61
   headline$="MOUNTAIN OF MUSCLE"
   Text x+5,y+138,charName$(char)+"'s strength seems to",1,1
   Text x+5,y+166,"have improved in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=62
   headline$="MAN OF 1'000 HOLDS"
   Text x+5,y+138,charName$(char)+"'s wrestling skills seem",1,1
   Text x+5,y+166,"to have improved in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=63
   headline$="FANCY FOOTWORK"
   Text x+5,y+138,charName$(char)+"'s agility seems to",1,1
   Text x+5,y+166,"have improved in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=64
   headline$="MARATHON MAN"
   Text x+5,y+138,charName$(char)+"'s stamina seems to",1,1
   Text x+5,y+166,"have improved in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=65
   headline$="IRON MAN"
   Text x+5,y+138,charName$(char)+"'s toughness seems to",1,1
   Text x+5,y+166,"have improved in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=66
   headline$="EMPLOYEE OF THE MONTH"
   Text x+5,y+138,charName$(char)+" has vowed to put more",1,1
   Text x+5,y+166,"effort into "+Lower$(His$(g))+" wrestling career!",1,1
  EndIf
  If eEvent(cyc)=67
   headline$="JOB SATISFACTION"
   Text x+5,y+138,charName$(char)+" has been enjoying",1,1
   Text x+5,y+166,"working for "+fedName$(fed)+"!",1,1
  EndIf
  If eEvent(cyc)=68
   headline$="FADING STAR"
   Text x+5,y+138,charName$(char)+"'s act has started to",1,1
   Text x+5,y+166,"lose its appeal in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=69
   headline$="PENCIL NECK GEEK"
   Text x+5,y+138,charName$(char)+"'s strength seems to",1,1
   Text x+5,y+166,"have deteriorated in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=70
   headline$="SLIPPERY FINGERS"
   Text x+5,y+138,charName$(char)+"'s wrestling skills seem",1,1
   Text x+5,y+166,"to have deteriorated in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=71
   headline$="TWO LEFT FEET"
   Text x+5,y+138,charName$(char)+"'s agility seems to",1,1
   Text x+5,y+166,"have deteriorated in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=72
   headline$="COACH POTATO"
   Text x+5,y+138,charName$(char)+"'s stamina seems to",1,1
   Text x+5,y+166,"have deteriorated in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=73
   headline$="HANDLE WITH CARE"
   Text x+5,y+138,charName$(char)+"'s toughness seems to",1,1
   Text x+5,y+166,"have deteriorated in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=74
   headline$="PROBLEM CHILD"
   Text x+5,y+138,charName$(char)+"'s attitude seems to",1,1
   Text x+5,y+166,"have deteriorated in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=75
   headline$="MISERY GUTS"
   Text x+5,y+138,charName$(char)+" has grown tired of",1,1
   Text x+5,y+166,"working for "+fedName$(fed)+"...",1,1
  EndIf
  If eEvent(cyc)=76
   headline$="GOOD COMPANY"
   Text x+5,y+138,charName$(char)+"'s profile has benefited from",1,1
   Text x+5,y+166,"being associated with "+charName$(charFriend(char))+"!",1,1
   DrawImage charPhoto(charFriend(char)),x+90,y+28
  EndIf
  If eEvent(cyc)=77
   headline$="BAD COMPANY"
   Text x+5,y+138,charName$(char)+"'s popularity has suffered from",1,1
   Text x+5,y+166,"being associated with "+charName$(charFriend(char))+"...",1,1
   DrawImage charPhoto(charFriend(char)),x+90,y+28
  EndIf
  If eEvent(cyc)=78
   headline$="BREAK UP"
   Text x+5,y+138,charName$(char)+" has fallen out with "+charName$(charEnemy(char))+"!",1,1
   Text x+5,y+166,"The former friends are now bitter rivals...",1,1
   DrawImage charPhoto(charEnemy(char)),x+90,y+28
  EndIf
  ;health issues
  If eEvent(cyc)=90
   headline$="RISKY BUSINESS"
   Text x+5,y+138,charName$(char)+" incurred a slight injury in a recent",1,1
   Text x+5,y+166,"match, and will be out of action for "+charInjured(char)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=91
   headline$="RISKY BUSINESS"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" sustained a serious injury in a recent",1,1
   Text x+5,y+150,"match, and will be out of action for the next "+charInjured(char)+" weeks.",1,1
   Text x+5,y+175,"Even then, "+Lower$(He$(g))+" may be a shadow of "+Lower$(His$(g))+" former self...",1,1
  EndIf
  If eEvent(cyc)=92
   headline$="CASUALTY OF WAR"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" sustained a serious injury in a recent match.",1,1
   Text x+5,y+150,He$(g)+" was rushed to hospital, where it's feared "+Lower$(He$(g))+" may be paralysed.",1,1
   Text x+5,y+175,His$(g)+" wrestling career is almost certainly over...",1,1
  EndIf
  If eEvent(cyc)=93
   headline$="RING OF DEATH"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" sustained a serious injury in a recent match.",1,1
   Text x+5,y+150,"Despite their best efforts, medics were not able to resuscitate "+Lower$(Him$(g))+".",1,1
   Text x+5,y+175,charName$(char)+" has died as a result of "+Lower$(His$(g))+" injuries. "+He$(g)+" was "+charAge(char)+"...",1,1
  EndIf
  If eEvent(cyc)=94
   headline$="LEGEND PASSES AWAY"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,"At the age of "+charAge(char)+", "+Lower$(He$(g))+" reportedly died of natural causes.",1,1
   Text x+5,y+175,His$(g)+" contribution to wrestling will not be forgotten...",1,1
  EndIf
  If eEvent(cyc)=95
   headline$="WRESTLER FOUND DEAD"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,"The hedonistic "+charAge(char)+" year old was swimming in a lethal cocktail",1,1
   Text x+5,y+175,"of drink and drugs - which eventually claimed "+Lower$(His$(g))+" life...",1,1
  EndIf
  If eEvent(cyc)=96
   headline$="SUICIDE SHOCK"
   ChannelPitch chTheme,40000
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,"The troubled "+charAge(char)+" year old is thought to have committed suicide.",1,1
   Text x+5,y+175,His$(g)+" fleeting contribution to wrestling will be cherished...",1,1
  EndIf
  ;corporate issues
  If eEvent(cyc)=100
   headline$="NEW KING IN TOWN"
   Text x+5,y+138,charName$(fedBooker(fed))+" is the new booker of",1,1
   Text x+5,y+166,fedName$(fed)+"...",1,1
  EndIf
  If eEvent(cyc)=101
   If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
   headline$="NEW WORLD CHAMP!"
   Text x+5,y+138,charName$(fedChampWorld(fed))+" is the new World Champion",1,1
   Text x+5,y+166,"of "+fedName$(fed)+"...",1,1
  EndIf
  If eEvent(cyc)=102
   If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
   headline$="NEW INTER CHAMP!"
   Text x+5,y+138,charName$(fedChampInter(fed))+" is the new Inter Champion",1,1
   Text x+5,y+166,"of "+fedName$(fed)+"...",1,1
  EndIf
  If eEvent(cyc)=103
   If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
   headline$="NEW TAG CHAMPS!"
   Text x+5,y+138,charName$(fedChampTag(fed,1))+" & "+charName$(fedChampTag(fed,2))+" are the new",1,1
   Text x+5,y+166,"Tag Champions of "+fedName$(fed)+"...",1,1
   DrawImage charPhoto(charFriend(char)),x+90,y+28
  EndIf 
  If eEvent(cyc)=104
   headline$="BAD DRAW"
   Text x+5,y+138,fedName$(fed)+"'s popularity has suffered since",1,1
   Text x+5,y+166,charName$(fedChampWorld(fed))+" became their World champion...",1,1
   DrawImage charPhoto(fedChampWorld(fed)),x+90,y+28 
  EndIf
  If eEvent(cyc)=105
   headline$="MOST VALUABLE PLAYER"
   Text x+5,y+138,fedName$(fed)+"'s popularity has improved since",1,1
   Text x+5,y+166,charName$(fedChampWorld(fed))+" became their World champion!",1,1
   DrawImage charPhoto(fedChampWorld(fed)),x+90,y+28 
  EndIf
  If eEvent(cyc)=106
   headline$="THE NEXT BIG THING"
   Text x+5,y+138,fedName$(fed)+"'s product has attracted",1,1
   Text x+5,y+166,"a stronger following in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=107
   headline$="OLD NEWS"
   Text x+5,y+138,fedName$(fed)+"'s product has suffered",1,1
   Text x+5,y+166,"a dip in popularity in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=108
   headline$="KEEP IT CLEAN"
   Text x+5,y+138,fedName$(fed)+" have been striving",1,1
   Text x+5,y+166,"to deliver a more respectable product...",1,1
  EndIf
  If eEvent(cyc)=109
   headline$="DIRTY MOVES"
   Text x+5,y+138,fedName$(fed)+" seem to be intent on",1,1
   Text x+5,y+166,"delivering a more controversial product!",1,1
  EndIf
  If eEvent(cyc)=110
   headline$="KINGS OF THE RING"
   If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
   Text x+5,y+138,fedName$(fed)+" have overtaken "+fedName$(eVariable(cyc)),1,1
   Text x+5,y+166,"as the world's leading wrestling show!",1,1
  EndIf
  If eEvent(cyc)=111
   headline$="CROSSING THE LINE"
   Text x+5,y+125,fedName$(fed)+" has acquired a hardcore",1,1
   Text x+5,y+150,"reputation! The show now has a more relaxed",1,1
   Text x+5,y+175,"attitude towards the use of weapons...",1,1
  EndIf
  If eEvent(cyc)=112
   headline$="RAISING THE STANDARD"
   Text x+5,y+125,fedName$(fed)+" has emerged as a more",1,1
   Text x+5,y+150,"respectable product. The show no longer condones",1,1
   Text x+5,y+175,"the use of weapons in regular matches...",1,1
  EndIf
  ;scheduling issues
  If eEvent(cyc)=150
   headline$="NO SHOW"
   ChannelPitch chTheme,40000
   Text x+5,y+138,fedName$(fed)+" will not stage a",1,1
   Text x+5,y+166,"show tonight due to technical problems...",1,1
  EndIf
  If eEvent(cyc)=151
   headline$="NO SHOW"
   Text x+5,y+138,fedName$(fed)+" will not stage a show",1,1
   Text x+5,y+166,"next week due to a scheduling mix-up...",1,1
  EndIf
  If eEvent(cyc)=152
   headline$="DAY OF REST"
   Text x+5,y+138,fedName$(fed)+" have scheduled a day off",1,1
   Text x+5,y+166,"next month to give wrestlers time to recuperate...",1,1
  EndIf
  If eEvent(cyc)=153
   headline$="ROYAL VISIT"
   Text x+5,y+138,charName$(char)+" of "+fedName$(charFed(char))+" will",1,1
   Text x+5,y+166,"be hosting a training session next week!",1,1
  EndIf
  If eEvent(cyc)=154
   headline$="TOUR ANNOUNCED"
   Text x+5,y+138,fedName$(fed)+" have scheduled a series",1,1
   Text x+5,y+166,"of PPV events over the forthcoming weeks!",1,1
  EndIf
  If eEvent(cyc)=155
   headline$="TOUGH LOVE"
   Text x+5,y+138,"The wrestling industry will stage a special",1,1
   Text x+5,y+166,"event next month to raise money for charity!",1,1
  EndIf
  If eEvent(cyc)=156
   headline$="WAR OF THE WORLDS"
   If eCharged(cyc)=0 Then PlaySound sCrowd(6) : eCharged(cyc)=1
   Text x+5,y+138,fedName$(eVariable(cyc))+" have challenged "+fedName$(fed),1,1
   Text x+5,y+166,"to an inter-promotional showdown next month!",1,1
   DrawImage charPhoto(fedBooker(eVariable(cyc))),x+90,y+28
  EndIf
  If eEvent(cyc)=157
   headline$="TENSION MOUNTS"
   Text x+5,y+138,"Remember to prepare for the big event that",1,1
   Text x+5,y+166,fedName$(fed)+" have planned for next week!",1,1
  EndIf
  If eEvent(cyc)=158
   headline$="THE BIG DAY"
   Text x+5,y+138,"Tonight is a big night for "+fedName$(fed)+"!",1,1
   Text x+5,y+166,"Make sure you do the company proud...",1,1
  EndIf
  ;missions reminder
  If eEvent(cyc)=201
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a popularity rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=202
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a strength rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=203
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a skill rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=204
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain an agility rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=205
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a stamina rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=206
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a toughness rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=207
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain an attitude rating of "+gamTarget(slot)+"%...",1,1
  EndIf
  If eEvent(cyc)=208
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"become a title holder!",1,1
  EndIf
  If eEvent(cyc)=209
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission",1,1
   Text x+5,y+166,"to get out of debt!",1,1
  EndIf
  If eEvent(cyc)=210
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"get signed by a major promotion!",1,1
  EndIf
  If eEvent(cyc)=211
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"injure one of your opponents!",1,1
  EndIf
  ;display headline
  SetFont fontNews(3) : Color 0,0,0
  Text x+5,y-65,headline$,1,1
  ;photo display
  If char>0 Then DrawImage charPhoto(char),x+125,y+30
  If char=0 And fedBooker(fed)>0 Then DrawImage charPhoto(fedBooker(fed)),x+125,y+30
  ;prompt
  SetFont font(1)
  If foc=10 Then Outline(">>> CLICK or ESCAPE to proceed >>>",x,y+210,130,130,130,255,255,255)
 EndIf
 ;cursor
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
fed=fed+1
If fed=<7
 ;next promotion
 PlaySound sMenuSelect
 screen=18
Else 
 ;reset variables 
 PlaySound sMenuGo 
 gamOldVariable(slot)=gamVariable(slot)
 gamVariable(slot)=0 
 For char=1 To no_chars
  charOldVariable(char,slot)=charVariable(char,slot)
  charVariable(char,slot)=0
 Next  
 ;prepare new venue
 GenerateArena()
 gamArenaPreset(slot)=arenaPreset : gamArenaCrowd(slot)=arenaCrowd
 gamArenaAtmos(slot)=arenaAtmos : gamArenaLight(slot)=arenaLight
 gamArenaRopes(slot)=arenaRopes : gamArenaApron(slot)=arenaApron
 gamArenaCanvas(slot)=arenaCanvas : gamArenaMat(slot)=arenaMat
 ;proceed to meetings
 RiskNegotiations()
 RiskFormalMeetings() 
 If screen<>22 Then RiskCasualMeetings()
 ;save and exit 
 SaveProgress()
 SaveWorld()
 SaveChars()
 If screen<>22 And screen<>50 Then screen=15 
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------------ 17. AFTERMATH ---------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Aftermath()
;restore photo
gPhoto=LoadImage("Photo Album/Photo.bmp")
MaskImage gPhoto,255,0,255
;translate health (for all)
For cyc=1 To no_plays
 char=pChar(cyc)
 charHealth(char)=pHealth(cyc)
 If optHealth=0 Then charHealth(char)=pHealth(cyc)*2
 If optHealth=2 Then charHealth(char)=pHealth(cyc)-(pHealth(cyc)/3)
 If cyc>no_wrestlers And pInjured(cyc)>0 And charInjured(char)=0 Then charInjured(char)=Rnd(1,4) : charHealth(char)=0 
 For limb=1 To 40 ;record limb loss!
  If pScar(cyc,limb)=5 Then charLimb(char,limb)=0
 Next 
Next
;find stat changes (for wrestlers)
For cyc=1 To no_wrestlers
 ;reset values
 char=pChar(cyc)
 charEvent(char)=0
 eCharged(cyc)=0
 If screenAgenda<>10 Then ResetOldValues(char)
 ResetNewValues(char)
 ;spontaneous developments
 If matchMins=>optHealth+1 And pOutTim(cyc)>0
  ;boost health
  If pInjured(cyc)=0 Then charNewHealth(char)=charNewHealth(char)+Rnd(charStam(char)/6,charStam(char)/3)
  ;strength fluctuation
  randy=Rnd(0,2) : level=Rnd(1,6)
  If screenAgenda=10 Then level=5
  If randy=0 Then charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),statLevel(level),1)
  ;skill fluctuation
  randy=Rnd(0,2) : level=Rnd(1,6)
  If screenAgenda=10 Then level=5
  If randy=0 Then charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),statLevel(level),1)
  ;agility fluctuation
  randy=Rnd(0,2) : level=Rnd(1,6)
  If screenAgenda=10 Then level=5
  If randy=0 Then charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),statLevel(level),1)
  ;stamina fluctuation
  randy=Rnd(0,2) : level=Rnd(1,6)
  If screenAgenda=10 Then level=5
  If randy=0 Then charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),statLevel(level),1)
  ;toughness fluctuation
  randy=Rnd(0,2) : level=Rnd(1,6)
  If screenAgenda=10 Then level=5
  If randy=0 Then charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),statLevel(level),1)
  ;limit changes if old
  If charAge(char)=>optOldAge
   If charNewStr(char)>charOldStr(char) Then charNewStr(char)=charOldStr(char)
   If charNewSkl(char)>charOldSkl(char) Then charNewSkl(char)=charOldSkl(char)
   If charNewAgl(char)>charOldAgl(char) Then charNewAgl(char)=charOldAgl(char)
   If charNewStam(char)>charOldStam(char) Then charNewStam(char)=charOldStam(char)
   If charNewTough(char)>charOldTough(char) Then charNewTough(char)=charOldTough(char)
  EndIf
 EndIf
 ;winning effects
 If matchWinner>0 And pTeam(cyc)=pTeam(matchWinner) And pEliminated(cyc)=0
  level=1   
  If matchWinStyle<>0
   If matchWinner=cyc And gamSchedule(slot,GetDate())=>2 Then level=level+Rnd(0,1) : charEvent(char)=1 ;high profile bonus 
   If matchWinner=cyc And no_wrestlers>2 Then level=level+Rnd(0,1) : charEvent(char)=2 ;big match bonus 
   If matchReward=2 And TitleHolder(char,1) Then level=level+1 : charEvent(char)=3 ;world title bonus
   If matchReward=3 And TitleHolder(char,2) Then level=level+Rnd(0,1) : charEvent(char)=4 ;inter title bonus
   If matchReward=4 And TitleHolder(char,3) Then level=level+Rnd(0,1) : charEvent(char)=5 ;tag title bonus
   If matchWinner=cyc And (charPop(char)<charPop(pChar(matchLoser))-5 Or pInjured(cyc)>0) 
    level=level+Rnd(0,1) : charEvent(char)=6 ;underdog bonus
   EndIf
   If matchWinner=1 And cyc=1 And matchTeams>0 And no_wrestlers=3 Then level=level+Rnd(0,1) : charEvent(char)=6 ;handicap bonus
   If matchWinner=cyc And FindSquash()=1 Then level=level+Rnd(0,1) : charEvent(char)=7 ;squash bonus
   If matchWinner=cyc And gamSchedule(slot,GetDate())=5 ;inter-promotional win
    level=level+1 : charEvent(char)=8 
    fedPop(charFed(char))=fedPop(charFed(char))+1
   EndIf
  EndIf
  If matchWinner=cyc And matchTeams=-1 Then level=level+1 ;rumble bonus
  If matchOfficial=1 And screenAgenda<>10 Then charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),statLevel(5),level) 
  charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),statLevel(Rnd(0,6)),1)
  charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),statLevel(5),level) 
 EndIf
 ;fed limitations
 If gamSchedule(slot,GetDate())=<2 And charPop(char)=>fedPop(fed) And charNewPop(char)>charPop(char)
  charNewPop(char)=charPop(char)+Rnd(0,1) : charEvent(char)=-1
  If charNewPop(char)>fedPop(fed)+5 Then charNewPop(char)=charPop(char) : charEvent(char)=-2
 EndIf
 ;quitting punishment
 If char=gamChar(slot) And matchQuit=1 And screenAgenda<>10
  charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),30,0)
  charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),30,0) 
  charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0) 
  charEvent(char)=10
 EndIf
 ;losing effects
 If (matchWinner>0 And pTeam(cyc)<>pTeam(matchWinner)) Or pEliminated(cyc)
  level=1
  If matchLoser=cyc And matchWinStyle<>0
   If gamSchedule(slot,GetDate())=>2 Then level=level+Rnd(0,1) : charEvent(char)=11 ;high profile humiliation 
   If no_wrestlers>2 Then level=level+Rnd(0,1) : charEvent(char)=12 ;singled out
   If charPop(char)>charPop(pChar(matchWinner))+5 Then level=level+Rnd(0,1) : charEvent(char)=13 ;underdog humiliation
   If matchWinner=1 And cyc>1 And matchTeams>0 And no_wrestlers=3 Then level=level+Rnd(0,1) : charEvent(char)=6 ;handicap humiliation
   If FindSquash()=1 Then level=level+Rnd(0,1) : charEvent(char)=14 ;squash humiliation
   If gamSchedule(slot,GetDate())=5 ;inter-promotional effects
    level=level+1 : charEvent(char)=15 
    fedPop(charFed(char))=fedPop(charFed(char))-1
   EndIf
  EndIf
  If matchOfficial=1 And screenAgenda<>10 Then charNewPop(char)=charNewPop(char)+PursueValue(charPop(char),statLevel(1),level)
  charNewAtt(char)=charNewAtt(char)+PursueValue(charAtt(char),statLevel(Rnd(0,6)),1) 
  charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),statLevel(1),level) 
 EndIf
 ;charity bonuses
 If gamSchedule(slot,GetDate())=>3 And gamSchedule(slot,GetDate())=<4
  charNewAtt(char)=charAtt(char)+PursueValue(charAtt(char),100,0) 
 EndIf
 ;brawling implications
 If matchOfficial=0 Then charEvent(char)=-3 : charNewAtt(char)=charAtt(char)-1
 ;find injuries (20-30)
 If charInjured(char)=0
  If pScar(cyc,1)=>2
   randy=Rnd(0,20/pScar(cyc,1))
   If randy=0 Then charEvent(char)=20 ;stitches
  EndIf
  If pInjured(cyc)=1 Then charEvent(char)=21 ;standard injury
  randy=Rnd(0,6)
  If randy=<3 And pInjured(cyc)=2 Then charEvent(char)=22 ;bad injury
  If randy=>4 And randy=<5 And pInjured(cyc)=2 Then charEvent(char)=23 ;crippled!
  If randy=6 And pInjured(cyc)=2 Then charEvent(char)=24 ;dead!
  randy=Rnd(0,10)
  If randy=0 And charEvent(char)=21 Then charEvent(char)=25 ;false alarm
 EndIf
 ;apply injuries
 If charEvent(char)=20 ;stitches
  charInjured(char)=1 : charNewHealth(char)=0
  charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),100,1)
  charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),0,1)
 EndIf
 If charEvent(char)=21 ;regular injury
  charInjured(char)=Rnd(2,5) : charNewHealth(char)=0
  charNewStr(char)=charNewStr(char)+PursueValue(charStr(char),30,0)
  charNewSkl(char)=charNewSkl(char)+PursueValue(charSkl(char),30,0)
  charNewAgl(char)=charNewAgl(char)+PursueValue(charAgl(char),30,0)
  charNewStam(char)=charNewStam(char)+PursueValue(charStam(char),30,0)
  charNewTough(char)=charNewTough(char)+PursueValue(charTough(char),30,0)
  charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0)
 EndIf
 If charEvent(char)=22 ;bad injury
  charInjured(char)=Rnd(4,10) : charNewHealth(char)=0
  charNewStr(char)=charNewStr(char)+(PursueValue(charStr(char),30,0)*2)
  charNewSkl(char)=charNewSkl(char)+(PursueValue(charSkl(char),30,0)*2)
  charNewAgl(char)=charNewAgl(char)+(PursueValue(charAgl(char),30,0)*2)
  charNewStam(char)=charNewStam(char)+(PursueValue(charStam(char),30,0)*2)
  charNewTough(char)=charNewTough(char)+(PursueValue(charTough(char),30,0)*2)
  charNewHap(char)=charNewHap(char)+(PursueValue(charHap(char),30,0)*2)
 EndIf
 If charEvent(char)=23 ;crippled
  charInjured(char)=Rnd(6,16) : charNewHealth(char)=0
  charNewStr(char)=30 : charNewSkl(char)=30 : charNewAgl(char)=30
  charNewStam(char)=30 : charNewTough(char)=30
  charNewHap(char)=charHap(char)/2
 EndIf
 If charEvent(char)=24 ;dead
  charInjured(char)=99 : charNewHealth(char)=0
  Trade(char,9)
  If fed=<6 Then gamSchedule(slot,GetDate()+1)=3 
 EndIf
 ;other acknowledgments (30+) 
 If charEvent(char)<>24
  If matchChamps>0
   If TitleHolder(char,1) And char<>fedOldChampWorld(fed) ;new world champ 
    If char=gamChar(slot) Then fedLocked(fed)=0 : gamWorldTitles(slot,fed)=gamWorldTitles(slot,fed)+1
    charEvent(char)=30 
   EndIf
   If TitleHolder(char,2) And char<>fedOldChampInter(fed) ;new inter champ
    If char=gamChar(slot) Then gamInterTitles(slot,fed)=gamInterTitles(slot,fed)+1
    charEvent(char)=31 
   EndIf
   If TitleHolder(char,3) And char<>fedOldChampTag(fed,1) And char<>fedOldChampTag(fed,2) ;new tag champ
    If char=gamChar(slot) Then gamTagTitles(slot,fed)=gamTagTitles(slot,fed)+1 
    charEvent(char)=32 
   EndIf
  EndIf
  If matchWinner>0 And pTeam(cyc)<>pTeam(matchWinner) And matchReward=5 ;lost hair!
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0)
   charEvent(char)=33 
  EndIf
  If matchWinner>0 And pTeam(cyc)<>pTeam(matchWinner) And matchReward=6 ;leave town!
   charNewHap(char)=charNewHap(char)+PursueValue(charHap(char),30,0)
   charEvent(char)=34 
  EndIf
  If matchPromo=33 And cyc=matchWinner Then charEvent(char)=35 ;world contender
  If matchPromo=41 And cyc=matchWinner Then charEvent(char)=36 ;inter contender
  If matchOfficial=0
   If negTopic=15 And pTeam(matchWinner)=1 And cyc=1 Then charEvent(char)=37 ;successful invasion defence
   If negTopic=15 And pTeam(matchWinner)=2 And cyc=3 Then charEvent(char)=38 : gamSchedule(slot,GetDate())=0 ;failed invasion defence
   If negTopic=16 And matchWinner=cyc Then charEvent(char)=39 : charSalary(char)=charSalary(char)+(charSalary(char)/4) ;riot success
   If negTopic=16 And matchWinner<>cyc Then charEvent(char)=40 ;riot failure
  EndIf
  If charEvent(char)<30
   If matchPromo=47 And cyc=1 ;shoot effects
    charNewPop(char)=charPop(char)+PursueValue(charPop(char),100,0) 
    charNewAtt(char)=charAtt(char)+PursueValue(charAtt(char),30,0)
    charNewHap(char)=charHap(char)+PursueValue(charHap(char),100,0) 
    charEvent(char)=41
   EndIf
   If matchPromo=29 And cyc=1 ;endorsement effects
    charNewPop(char)=charPop(char)+PursueValue(charPop(char),30,0) 
    charNewAtt(char)=charAtt(char)+PursueValue(charAtt(char),30,0)
    charNewHap(char)=charHap(char)+PursueValue(charHap(char),30,0) 
    charHeel(char)=1
    charEvent(char)=42
   EndIf 
   If matchTeams=-1 And cyc=matchWinner And pChar(matchWinner)<>fedChampWorld(fed) Then charEvent(char)=43 ;rumbles makes contender
  EndIf
 EndIf
 ;limit changes
 CheckNewValues(char)
Next
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
foc=10 : cyc=0
go=0 : gotim=-30 : keytim=10
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
	
	;PROCESS
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;find next subject
	 If cyc=0 Or KeyDown(1) Or JoyDown(1) Or MouseDown(1)
	  If foc=10 And cyc<no_wrestlers 
	   PlaySound sMenuSelect : keytim=30 : gotim=0
	   cyc=cyc+1 : foc=1
	  EndIf
	  If foc=10 And cyc=no_wrestlers Then go=1
	 EndIf
	 ;administer changes
	 If cyc=>1 And cyc=<no_wrestlers
	  AlterStats(pChar(cyc))
	  If keytim>2 And foc=<9
	   If KeyDown(1) Or JoyDown(1) Or MouseDown(1) Then keytim=2 ;speed up
	  EndIf
	 EndIf
	EndIf
	
	;reaction
	If gotim=-10 Then PlaySound sPaper
	If gotim=-5 Then PlaySound sCrowd(4) 
	
 UpdateWorld
 Next 
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 If gamSchedule(slot,GetDate())=>3 And cyc=>1 And cyc=<no_wrestlers 
  DrawImage gFed(charFed(pChar(cyc))),rX#(400),rY#(65) 
 Else
  DrawImage gFed(fed),rX#(400),rY#(65) 
 EndIf
 ;show profiles
 If cyc=>1 And cyc=<no_wrestlers
  If gotim>20 Then HighlightStats() 
  DrawProfile(pChar(cyc),-1,-1)
 EndIf
 ;MAGAZINE ARTICLE
 If gotim>-10
  x=rX#(400) : y=rY#(390)
  ;images
  DrawImage gMagazine,x,y
  DrawImage gPhoto,x-128,y-5
  Color 100,100,100
  Rect x-259,y-72,262,134,0
  Rect x-260,y-73,264,136,0
  Color 0,0,0
  Rect x-256,y-69,256,127,0
  ;smallprint
  SetFont fontNews(0) : Color 100,100,100
  Text x-270,y-205,"Wrestling Review Magazine",0,1
  dateline$=textWeek$(gamWeek(slot))+" of "+textMonth$(gamMonth(slot))+" "+gamYear(slot)
  Text x,y-205,dateline$,1,1 
  Text x+210,y-205,"news@wrestlingreview.com",1,1
  ;intro
  SetFont fontNews(1) : Color 70,70,70
  If screenAgenda=10
   Text x+130,y-50,"All the latest from the sparring",1,1
   Text x+130,y-33,"session that "+charName$(gamChar(slot))+" held",1,1
  EndIf
  If matchOfficial=0
   Text x+130,y-50,"All the latest from the",1,1
   Text x+130,y-33,"chaos that erupted backstage",1,1
  EndIf
  If matchOfficial=1 And screenAgenda<>10
   If gamSchedule(slot,GetDate())=1 And fed=7
    Text x+130,y-50,"All the latest from the training",1,1
    Text x+130,y-33,"session that "+fedName$(fed)+" held",1,1
   EndIf
   If gamSchedule(slot,GetDate())=1 And fed<>7
    Text x+130,y-50,"All the latest from the TV show",1,1
    Text x+130,y-33,"that "+fedName$(fed)+" promoted",1,1
   EndIf
   If gamSchedule(slot,GetDate())=2
    Text x+130,y-50,"All the latest from the PPV event",1,1
    Text x+130,y-33,"that "+fedName$(fed)+" promoted",1,1
   EndIf
   If gamSchedule(slot,GetDate())=3
    Text x+130,y-50,"All the latest from the tribute",1,1
    Text x+130,y-33,"to "+charName$(fedRoster(9,fedSize(9)))+", which took place",1,1
   EndIf
   If gamSchedule(slot,GetDate())=4
    Text x+130,y-50,"All the latest from the charity show",1,1
    Text x+130,y-33,"that various promotions took part in",1,1
   EndIf
   If gamSchedule(slot,GetDate())=5
    Text x+130,y-50,"All the latest from the inter-",1,1
    Text x+130,y-33,"promotional contest that took place",1,1
   EndIf
  EndIf
  Text x+130,y-16,"on the "+dateline$+":",1,1
  ;result
  SetFont fontNews(1) : Color 70,70,70
  If screenAgenda=10
   Text x+130,y+20,"The session was brought",1,1
   Text x+130,y+37,"to an end at "+matchMins+":"+Dig$(matchSecs,10)+"...",1,1
  EndIf 
  If matchOfficial=0
   If matchWinner>0
    If matchTeams>0 Then namer$=charName$(pChar(matchWinner))+"'s team" Else namer$=charName$(pChar(matchWinner))
    Text x+130,y+20,namer$+" emerged victorious",1,1
    Text x+130,y+37,"in a backstage brawl...",1,1
   EndIf
   If matchWinner=0
    Text x+130,y+20,"A backstage brawl was stopped",1,1
    Text x+130,y+37,"before it got out of hand...",1,1
   EndIf
  EndIf
  If matchOfficial=1 And screenAgenda<>10 And gamSchedule(slot,GetDate())<>5
   If matchWinner>0
    If matchTeams>0 Then namer$=charName$(pChar(matchWinner))+"'s team" Else namer$=charName$(pChar(matchWinner))
    Text x+130,y+20,namer$+" emerged victorious",1,1
    If gamGimmick(slot,GetDate())>0 Then namer$=textGimmick$(gamGimmick(slot,GetDate()))+" " Else namer$=""
    Text x+130,y+37,"in a "+namer$+"'"+textMatch$(matchPreset)+"'...",1,1
   EndIf
   If matchWinner=0
    If gamGimmick(slot,GetDate())>0 Then namer$=textGimmick$(gamGimmick(slot,GetDate()))+" " Else namer$=""
    Text x+130,y+20,"A "+namer$+"'"+textMatch$(matchPreset)+"'",1,1
    Text x+130,y+37,"failed to produce a winner...",1,1
   EndIf
  EndIf
  If screenAgenda<>10 And gamSchedule(slot,GetDate())=5
   If matchWinner>0
    If matchTeams>0 Then namer$=charName$(pChar(matchWinner))+"'s team" Else namer$=charName$(pChar(matchWinner))
    Text x+130,y+20,namer$+" notched up a victory",1,1
    Text x+130,y+37,"for the "+fedName$(charFed(pChar(matchWinner)))+" camp...",1,1
   EndIf
   If matchWinner=0
    Text x+130,y+20,"A match between "+fedName$(charFed(gamChar(slot)))+" and",1,1
    Text x+130,y+37,fedName$(gamInterProm(slot,GetDate()))+" ended in a tie...",1,1
   EndIf
  EndIf
  ;REPORTS
  If foc=10 And cyc=>1 And cyc=<no_wrestlers
   char=pChar(cyc)
   g=GetGender(char)
   SetFont fontNews(2) : Color 0,0,0
   ;misc explanations
   If charEvent(char)=-1
    Text x+20,y+105,charName$(char)+"'s progress is being hindered",1,1
    Text x+20,y+133,"by the limitations of "+fedName$(fed)+"...",1,1
   EndIf
   If charEvent(char)=-2
    Text x+20,y+105,charName$(char)+" has reached the limit",1,1
    Text x+20,y+133,"of "+fedName$(fed)+"'s popularity...",1,1
   EndIf
   If charEvent(char)=-3
    Text x+20,y+105,charName$(char)+"'s reputation has been tainted",1,1
    Text x+20,y+133,"by being involved in that altercation...",1,1
   EndIf
   ;standard result descriptions
   eliminator=0
   If pEliminated(cyc) And no_wrestlers>2 Then eliminator=1
   If charEvent(char)=0 And matchWinner=0 And eliminator=0
    Text x+20,y+105,charName$(char)+"'s status hasn't been affected",1,1
    Text x+20,y+133,"by the result of that match...",1,1
   EndIf
   If charEvent(char)=0 And matchWinner>0 And matchTeams=<0 And matchWinner=cyc And eliminator=0
    Text x+20,y+105,charName$(char)+"'s profile has improved by",1,1
    Text x+20,y+133,"securing a victory in that match...",1,1
   EndIf
   If charEvent(char)=0 And matchWinner>0 And matchTeams=<0 And matchWinner<>cyc And eliminator=0
    Text x+20,y+105,charName$(char)+"'s profile has suffered by",1,1
    Text x+20,y+133,"incurring a loss in that match...",1,1
   EndIf
   If charEvent(char)=0 And matchWinner>0 And matchTeams>0 And pTeam(matchWinner)=pTeam(cyc) And eliminator=0
    Text x+20,y+105,charName$(char)+"'s profile has improved by",1,1
    Text x+20,y+133,"being on the winning team in that match...",1,1
   EndIf
   If charEvent(char)=0 And matchWinner>0 And matchTeams>0 And pTeam(matchWinner)<>pTeam(cyc) And eliminator=0
    Text x+20,y+105,charName$(char)+"'s profile has suffered by",1,1
    Text x+20,y+133,"being on the losing team in that match...",1,1
   EndIf
   If charEvent(char)=0 And eliminator=1
    Text x+20,y+105,charName$(char)+"'s profile has suffered by",1,1
    Text x+20,y+133,"failing to survive until the end of that match...",1,1
   EndIf
   ;win descriptions
   If charEvent(char)=1
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by securing a victory at a high profile event!",1,1
   EndIf
   If charEvent(char)=2
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by being the winning participant in that match!",1,1
   EndIf
   If charEvent(char)=3
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by defending the World Championship in that match!",1,1
   EndIf
   If charEvent(char)=4
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by defending the Inter Championship in that match!",1,1
   EndIf
   If charEvent(char)=5
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by defending the Tag Championships in that match!",1,1
   EndIf 
   If charEvent(char)=6
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by defeating a superior opponent in that match!",1,1
   EndIf
   If charEvent(char)=7
    Text x+20,y+105,charName$(char)+"'s profile has improved significantly",1,1
    Text x+20,y+133,"by winning so convincingly in that match!",1,1
   EndIf
   If charEvent(char)=8
    Text x+20,y+105,charName$(char)+" has raised the profile of",1,1
    Text x+20,y+133,fedName$(charFed(char))+" by winning that match!",1,1
   EndIf
   ;loss descriptions
   If charEvent(char)=10
    Text x+20,y+105,charName$(char)+"'s profile has suffered considerably",1,1
    Text x+20,y+133,"by bailing out of that match before it had finished!",1,1
   EndIf
   If charEvent(char)=11
    Text x+20,y+105,charName$(char)+"'s profile has suffered considerably",1,1
    Text x+20,y+133,"by incurring a loss at a high profile event...",1,1
   EndIf
   If charEvent(char)=12
    Text x+20,y+105,charName$(char)+"'s profile has suffered considerably",1,1
    Text x+20,y+133,"by being the losing participant in that match...",1,1
   EndIf
   If charEvent(char)=13
    Text x+20,y+105,charName$(char)+"'s profile has suffered considerably",1,1
    Text x+20,y+133,"by losing to a lesser opponent in that match...",1,1
   EndIf
   If charEvent(char)=14
    Text x+20,y+105,charName$(char)+"'s profile has suffered considerably",1,1
    Text x+20,y+133,"by being defeated so easily in that match...",1,1
   EndIf
   If charEvent(char)=15
    Text x+20,y+105,charName$(char)+" has damaged the profile of",1,1
    Text x+20,y+133,fedName$(charFed(char))+" by losing that match...",1,1
   EndIf
   ;injury events
   If charEvent(char)=20
    Text x+20,y+105,charName$(char)+" incurred some serious facial damage",1,1
    Text x+20,y+133,"and will be nursing the wounds until next week...",1,1
   EndIf
   If charEvent(char)=21
    Text x+20,y+105,charName$(char)+" incurred a minor injury in that match",1,1
    Text x+20,y+133,"and will be out of action for the next "+charInjured(char)+" weeks...",1,1
   EndIf
   If charEvent(char)=22
    ChannelPitch chTheme,40000
    Text x+20,y+95,charName$(char)+" sustained a serious injury in that match",1,1
    Text x+20,y+120,"and will be out of action for the next "+charInjured(char)+" weeks.",1,1
    Text x+20,y+145,"Even then, "+Lower$(He$(g))+" may be a shadow of "+Lower$(His$(g))+" former self...",1,1
   EndIf
   If charEvent(char)=23
    ChannelPitch chTheme,40000
    Text x+20,y+95,charName$(char)+" sustained an extremely serious injury in that match.",1,1
    Text x+20,y+120,He$(g)+" was rushed to hospital, where it's feared "+Lower$(He$(g))+" may be paralysed.",1,1
    Text x+20,y+145,His$(g)+" wrestling career is almost certainly over...",1,1
   EndIf
   If charEvent(char)=24
    ChannelPitch chTheme,40000
    Text x+20,y+95,charName$(char)+" sustained an extremely serious injury in that match.",1,1
    Text x+20,y+120,"Despite their best efforts, medics were not able to resuscitate "+Lower$(Him$(g))+".",1,1
    Text x+20,y+145,charName$(char)+" has died as a result of "+Lower$(His$(g))+" injuries. "+He$(g)+" was "+charAge(char)+"...",1,1
   EndIf
   If charEvent(char)=25
    Text x+20,y+105,"It seems "+charName$(char)+"'s injury was a false alarm!",1,1
    Text x+20,y+133,"On closer inspection, there was nothing to worry about...",1,1
   EndIf
   ;stipulation acknowledgements
   If charEvent(char)=30
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,"Congratulations to "+charName$(char)+" for becoming",1,1
    Text x+20,y+133,"the World Champion of "+fedName$(fed)+"!",1,1
   EndIf
   If charEvent(char)=31
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,"Congratulations to "+charName$(char)+" for becoming",1,1
    Text x+20,y+133,"the Inter Champion of "+fedName$(fed)+"!",1,1
   EndIf
   If charEvent(char)=32
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,"Congratulations to "+charName$(char)+" for becoming one",1,1
    Text x+20,y+133,"of the Tag Champions of "+fedName$(fed)+"!",1,1
   EndIf
   If charEvent(char)=33
    If eCharged(cyc)=0 Then PlaySound sCrowd(8) : eCharged(cyc)=1
    Text x+20,y+105,"In accordance with the stipulations of that match, ",1,1
    Text x+20,y+133,charName$(char)+" must now lose all of "+Lower$(His$(g))+" hair!",1,1
   EndIf
   If charEvent(char)=34
    If eCharged(cyc)=0 Then PlaySound sCrowd(6) : eCharged(cyc)=1
    Text x+20,y+105,"In accordance with the stipulations of that match, ",1,1
    Text x+20,y+133,charName$(char)+" must leave "+fedName$(fed)+"!",1,1
   EndIf
   If charEvent(char)=35
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,"In accordance with the stipulations of that match, ",1,1
    Text x+20,y+133,charName$(char)+" will receive a World title shot!",1,1
   EndIf
   If charEvent(char)=36
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,"In accordance with the stipulations of that match, ",1,1
    Text x+20,y+133,charName$(char)+" will receive an Inter title shot!",1,1
   EndIf
   If charEvent(char)=37
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,fedName$(charFed(char))+" thwarted the",1,1
    Text x+20,y+133,"attempt to sabotage tonight's show!",1,1
   EndIf
   If charEvent(char)=38
    If eCharged(cyc)=0 Then PlaySound sCrowd(3) : eCharged(cyc)=1
    Text x+20,y+105,fedName$(charFed(char))+" proceeded to",1,1
    Text x+20,y+133,"ruin the venue for tonight's show...",1,1
    ChannelPitch chTheme,40000
   EndIf
   If charEvent(char)=39
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,charName$(char)+" successfully negotiated",1,1
    Text x+20,y+133,"a pay rise because of that protest!",1,1
   EndIf
   If charEvent(char)=40
    Text x+20,y+105,charName$(char)+" failed to negotiate",1,1
    Text x+20,y+133,"a pay rise after that performance...",1,1
   EndIf
   If charEvent(char)=41
    If eCharged(cyc)=0 Then PlaySound sCrowd(7) : eCharged(cyc)=1
    Text x+20,y+105,charName$(char)+"'s controversial comments",1,1
    Text x+20,y+133,"have made "+Him$(g)+" the talk of the industry!",1,1
   EndIf
   If charEvent(char)=42
    If eCharged(cyc)=0 Then PlaySound sCrowd(8) : eCharged(cyc)=1
    Text x+20,y+105,charName$(char)+"'s advertising campaign",1,1
    Text x+20,y+133,"has made "+Him$(g)+" a laughing stock!",1,1
   EndIf
   If charEvent(char)=43
    If eCharged(cyc)=0 Then PlaySound sCrowd(9) : eCharged(cyc)=1
    Text x+20,y+105,charName$(char)+" has earned a title shot",1,1
    Text x+20,y+133,"by winning such a prestigious contest!",1,1
   EndIf
  EndIf
  ;prompt
  If foc=10 And gotim>50
   SetFont font(1)
   Outline(">>> CLICK or ESCAPE to proceed >>>",x,y+190,120,120,120,250,250,250)
  EndIf
 EndIf
 ;cursor
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
;remove photo
FreeImage gPhoto
;final character checks
For cyc=1 To no_wrestlers
 If charInjured(pChar(cyc))>0 And charOldInjured(pChar(cyc))=0 Then charInjured(pChar(cyc))=charInjured(pChar(cyc))+1 ;offset injuries
 If charEvent(pChar(cyc))=34 Then ReleaseCharacter(pChar(cyc)) ;honour releases 
Next
;find consequent angles
If matchOfficial=1 And screenAgenda<>10 Then FindAngles()
;proceed
screen=19
If screenAgenda=10 Then screen=54
If matchOfficial=0 Then screen=15
If charFed(gamChar(slot))=9 Then screen=22 ;death forces retirement!
;detour to last minute meetings
If screen=19 And matchPlayer=<no_wrestlers And charInjured(gamChar(slot))=0 And gamSchedule(slot,GetDate())=<2 And gamVariable(slot)<>3
 RiskLateMeetings()
EndIf
End Function

;/////////////////////////////////////////////////////////////////////////////////////
;------------------------------ RELATED FUNCTIONS ------------------------------------
;/////////////////////////////////////////////////////////////////////////////////////

;RESET OLD VALUES 
Function ResetOldValues(char)
 charOldPop(char)=charPop(char)
 charOldStr(char)=charStr(char)
 charOldSkl(char)=charSkl(char)
 charOldAgl(char)=charAgl(char)
 charOldStam(char)=charStam(char)
 charOldTough(char)=charTough(char)
 charOldAtt(char)=charAtt(char)
 charOldHap(char)=charHap(char)
End Function

;RESET NEW VALUES
Function ResetNewValues(char)
 charNewHealth(char)=charHealth(char)
 charNewPop(char)=charPop(char)
 charNewStr(char)=charStr(char)
 charNewSkl(char)=charSkl(char)
 charNewAgl(char)=charAgl(char)
 charNewStam(char)=charStam(char)
 charNewTough(char)=charTough(char)
 charNewAtt(char)=charAtt(char)
 charNewHap(char)=charHap(char)
End Function

;PURSUE NEW STATISTIC
Function PursueValue(current,target,limit)
 value=0
 ;climb level 1
 If current<target Then value=1
 If current>target Then value=-1
 ;climb level 2
 If current<target-10 Then value=Rnd(2,4)
 If current>target+10 Then value=-Rnd(2,4)
 ;climb level 3
 If current<target-20 Then value=Rnd(3,7)
 If current>target+20 Then value=-Rnd(3,7)
 ;climb level 4
 If current<target-40 Then value=Rnd(4,10)
 If current>target+40 Then value=-Rnd(4,10)
 ;limit values
 If limit>0
  If value>limit Then value=limit
  If value<-limit Then value=-limit
 EndIf
 Return value
End Function

;ALTER STATS PROCESS
Function AlterStats(char)
 ;health
 If foc=1 And keytim=0
  If charNewHealth(char)<charHealth(char)-20 Or charNewHealth(char)>charHealth(char)+20 Then range=5 Else range=1
  If charHealth(char)<charNewHealth(char) Then charHealth(char)=charHealth(char)+range : keytim=4
  If charHealth(char)>charNewHealth(char) Then charHealth(char)=charHealth(char)-range : keytim=4
  If charHealth(char)=charNewHealth(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;popularity
 If foc=2 And keytim=0
  If charNewPop(char)<charPop(char)-20 Or charNewPop(char)>charPop(char)+20 Then range=5 Else range=1
  If charPop(char)<charNewPop(char) Then charPop(char)=charPop(char)+range : PlaySound sMenuBrowse : keytim=8
  If charPop(char)>charNewPop(char) Then charPop(char)=charPop(char)-range : PlaySound sMenuBrowse : keytim=8
  If charPop(char)=charNewPop(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;strength
 If foc=3 And keytim=0
  If charNewStr(char)<charStr(char)-20 Or charNewStr(char)>charStr(char)+20 Then range=5 Else range=1
  If charStr(char)<charNewStr(char) Then charStr(char)=charStr(char)+range : PlaySound sMenuBrowse : keytim=8
  If charStr(char)>charNewStr(char) Then charStr(char)=charStr(char)-range : PlaySound sMenuBrowse : keytim=8
  If charStr(char)=charNewStr(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;skill
 If foc=4 And keytim=0
  If charNewSkl(char)<charSkl(char)-20 Or charNewSkl(char)>charSkl(char)+20 Then range=5 Else range=1
  If charSkl(char)<charNewSkl(char) Then charSkl(char)=charSkl(char)+range : PlaySound sMenuBrowse : keytim=8
  If charSkl(char)>charNewSkl(char) Then charSkl(char)=charSkl(char)-range : PlaySound sMenuBrowse : keytim=8
  If charSkl(char)=charNewSkl(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;agility
 If foc=5 And keytim=0
  If charNewAgl(char)<charAgl(char)-20 Or charNewAgl(char)>charAgl(char)+20 Then range=5 Else range=1
  If charAgl(char)<charNewAgl(char) Then charAgl(char)=charAgl(char)+range : PlaySound sMenuBrowse : keytim=8
  If charAgl(char)>charNewAgl(char) Then charAgl(char)=charAgl(char)-range : PlaySound sMenuBrowse : keytim=8
  If charAgl(char)=charNewAgl(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;stamina
 If foc=6 And keytim=0
  If charNewStam(char)<charStam(char)-20 Or charNewStam(char)>charStam(char)+20 Then range=5 Else range=1
  If charStam(char)<charNewStam(char) Then charStam(char)=charStam(char)+range : PlaySound sMenuBrowse : keytim=8
  If charStam(char)>charNewStam(char) Then charStam(char)=charStam(char)-range : PlaySound sMenuBrowse : keytim=8
  If charStam(char)=charNewStam(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;toughness
 If foc=7 And keytim=0
  If charNewTough(char)<charTough(char)-20 Or charNewTough(char)>charTough(char)+20 Then range=5 Else range=1
  If charTough(char)<charNewTough(char) Then charTough(char)=charTough(char)+range : PlaySound sMenuBrowse : keytim=8
  If charTough(char)>charNewTough(char) Then charTough(char)=charTough(char)-range : PlaySound sMenuBrowse : keytim=8
  If charTough(char)=charNewTough(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;attitude
 If foc=8 And keytim=0
  If charNewAtt(char)<charAtt(char)-20 Or charNewAtt(char)>charAtt(char)+20 Then range=5 Else range=1
  If charAtt(char)<charNewAtt(char) Then charAtt(char)=charAtt(char)+range : PlaySound sMenuBrowse : keytim=8
  If charAtt(char)>charNewAtt(char) Then charAtt(char)=charAtt(char)-range : PlaySound sMenuBrowse : keytim=8
  If charAtt(char)=charNewAtt(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;happiness
 If foc=9 And keytim=0
  If charNewHap(char)<charHap(char)-20 Or charNewHap(char)>charHap(char)+20 Then range=5 Else range=1
  If charHap(char)<charNewHap(char) Then charHap(char)=charHap(char)+range : PlaySound sMenuBrowse : keytim=8
  If charHap(char)>charNewHap(char) Then charHap(char)=charHap(char)-range : PlaySound sMenuBrowse : keytim=8
  If charHap(char)=charNewHap(char) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
End Function

;ALTER STATS PROCESS
Function AlterFedStats(cyc)
 ;popularity
 If foc=1 And keytim=0
  If fedNewPop(cyc)<fedPop(cyc)-20 Or fedNewPop(cyc)>fedPop(cyc)+20 Then range=5 Else range=1
  If fedPop(cyc)<fedNewPop(cyc) Then fedPop(cyc)=fedPop(cyc)+range : PlaySound sMenuBrowse : keytim=8
  If fedPop(cyc)>fedNewPop(cyc) Then fedPop(cyc)=fedPop(cyc)-range : PlaySound sMenuBrowse : keytim=8
  If fedPop(cyc)=fedNewPop(cyc) And keytim=0 Then foc=foc+1 : keytim=20 : PlaySound sMenuSelect
 EndIf
 ;reputation
 If foc=2 And keytim=0
  If fedNewRep(cyc)<fedRep(cyc)-20 Or fedNewRep(cyc)>fedRep(cyc)+20 Then range=5 Else range=1
  If fedRep(cyc)<fedNewRep(cyc) Then fedRep(cyc)=fedRep(cyc)+range : PlaySound sMenuBrowse : keytim=8
  If fedRep(cyc)>fedNewRep(cyc) Then fedRep(cyc)=fedRep(cyc)-range : PlaySound sMenuBrowse : keytim=8
  If fedRep(cyc)=fedNewRep(cyc) And keytim=0 Then foc=10 : keytim=20 : PlaySound sMenuSelect
 EndIf
End Function

;CHECK ALTERED VALUES
Function CheckNewValues(char)
 If charNewHealth(char)>100 Then charNewHealth(char)=100
 If charNewHealth(char)<0 Then charNewHealth(char)=0
 If charNewPop(char)>99 Then charNewPop(char)=99
 If charNewPop(char)<30 Then charNewPop(char)=30
 If charNewStr(char)>99 Then charNewStr(char)=99
 If charNewStr(char)<30 Then charNewStr(char)=30
 If charNewSkl(char)>99 Then charNewSkl(char)=99
 If charNewSkl(char)<30 Then charNewSkl(char)=30
 If charNewAgl(char)>99 Then charNewAgl(char)=99
 If charNewAgl(char)<30 Then charNewAgl(char)=30
 If charNewStam(char)>99 Then charNewStam(char)=99
 If charNewStam(char)<30 Then charNewStam(char)=30
 If charNewTough(char)>99 Then charNewTough(char)=99
 If charNewTough(char)<30 Then charNewTough(char)=30
 If charNewAtt(char)>99 Then charNewAtt(char)=99
 If charNewAtt(char)<30 Then charNewAtt(char)=30
 If charNewHap(char)>99 Then charNewHap(char)=99
 If charNewHap(char)<30 Then charNewHap(char)=30
End Function

;CHECK FED LIMITS
Function CheckFedLimits(cyc)
 ;standard limits
 If fedPop(cyc)<30 Then fedPop(cyc)=30 
 If fedPop(cyc)>99 Then fedPop(cyc)=99
 If fedRep(cyc)<30 Then fedRep(cyc)=30
 If fedRep(cyc)>99 Then fedRep(cyc)=99
 ;new limits
 If fedNewPop(cyc)>99 Then fedNewPop(cyc)=99
 If fedNewPop(cyc)<1 Then fedNewPop(cyc)=1
 If fedNewRep(cyc)>99 Then fedNewRep(cyc)=99
 If fedNewRep(cyc)<1 Then fedNewRep(cyc)=1
End Function

;FIND TOP FED
Function FindTopFed()
 hi=0 : leader=0
 For count=1 To 6
  If fedPop(count)>hi Then leader=count : hi=fedPop(count)
 Next
 Return leader
End Function

;HIGHLIGHT STAT CHANGES
Function HighlightStats()
 x=rX#(170) : y=rY#(25)
 Color 250,130,60
 If foc=1 Then Rect x-41,y+4,102,7,0
 If foc=2 Then Rect x-92,y+14,96,15,0
 If foc=3 Then Rect x-82,y+29,86,15,0
 If foc=4 Then Rect x-54,y+44,58,15,0
 If foc=5 Then Rect x-68,y+59,72,15,0
 If foc=6 Then Rect x+24,y+14,84,15,0
 If foc=7 Then Rect x+8,y+29,100,15,0
 If foc=8 Then Rect x+26,y+44,82,15,0
 If foc=9 Then Rect x+10,y+59,98,15,0
End Function

;HIGHLIGHT FED STAT CHANGES
Function HighlightFedStats()
 x=rX#(180) : y=rY#(35)
 Color 250,130,60
 If foc=1 Then Rect x-79,y+13,135,17,0
 If foc=2 Then Rect x-81,y+29,137,17,0
End Function

;SHOW STAT PROGRESS
Function ShowStatProgress(char)
 SetFont font(0)
 x=rX#(170) : y=rY#(25)
 StatProgress(x+3,y+21,charOldPop(char),charPop(char))
 StatProgress(x+3,y+36,charOldStr(char),charStr(char))
 StatProgress(x+3,y+51,charOldSkl(char),charSkl(char))
 StatProgress(x+3,y+66,charOldAgl(char),charAgl(char))
 StatProgress(x+107,y+21,charOldStam(char),charStam(char))
 StatProgress(x+107,y+36,charOldTough(char),charTough(char))
 StatProgress(x+107,y+51,charOldAtt(char),charAtt(char))
 StatProgress(x+107,y+66,charOldHap(char),charHap(char))
End Function

;STAT PROGRESS
Function StatProgress(x,y,oldValue,newValue)
 differ=newValue-oldValue
 If differ>0 Then OutlineStraight("+"+differ,x,y,0,0,0,0,225,0)
 If differ<0 Then OutlineStraight(differ,x,y,0,0,0,225,0,0) 
End Function

;FIND SQUASH RESULT
Function FindSquash()
 squash=0
 If no_wrestlers=2 And matchWinner>0 And matchLoser>0 And matchWinStyle>0 And charPop(pChar(matchLoser))>charPop(pChar(matchWinner))-10
  ;substantial health difference
  If matchMins=<3+optHealth
   If optHealth=0 And pHealth(matchWinner)>pHealth(matchLoser)+25 Then squash=1
   If optHealth=1 And pHealth(matchWinner)>pHealth(matchLoser)+50 Then squash=1
   If optHealth=2 And pHealth(matchWinner)>pHealth(matchLoser)+75 Then squash=1
  EndIf
  ;quick finish
  If matchMins=<optHealth Then squash=1
 EndIf
 Return squash
End Function

;GET NEW NAME
Function GetNewName$()
 name$=""
 randy=Rnd(0,30)
 If randy=<2 Then name$=movName$(Rnd(1,no_moves))
 If randy=3 Then name$="Moonwalker"
 If randy=4 Then name$="Piston Pecker"
 If randy=5 Then name$="Filthy Akira"
 If randy=6 Then name$="Sinistar"
 If randy=7 Then name$="Powder Puff"
 If randy=8 Then name$=fedShortName$(fed)+"'s Pride"
 If randy=9 Then name$="Fanny Insense"
 If randy=10 Then name$="Super Bobo"
 If randy=11 Then name$="Kama Sutra"
 If randy=12 Then name$="The Crazy Jew"
 If randy=13 Then name$="Bone Bender"  
 If randy=14 Then name$="Slick Mystery"
 If randy=15 Then name$="God's Son"
 If randy=16 Then name$="Hey Zeus"
 If randy=17 Then name$="Monster Mundane"
 If randy=18 Then name$="Othello" 
 If randy=19 Then name$="Centurian"
 If randy=20 Then name$="Rising Son" 
 If randy=21 Then name$="Mr Bigtime"
 If randy=22 Then name$="Showtime"
 If randy=23 Then name$="Rope Burn"
 If randy=24 Then name$="The Hustler"
 If randy=25 Then name$="The Democrat" 
 If randy=26 Then name$="The Republican"
 If randy=27 Then name$="Ding-a-Ling" 
 If randy=28 Then name$="Machismo" 
 If randy=29 Then name$="Contract Killer"
 If randy=30 Then name$="Peek-a-boo" 
 Return name$
End Function

;CHANGE COSTUME
Function ChangeCostume(char)
 ;get target
 Repeat
  idol=Rnd(1,no_chars)
 Until idol<>char And GetRace(idol)=GetRace(char) And DesignatedRef(idol)=0
 ;copy target
 hatter=Rnd(-2,1)
 If ModelType(char,1)=ModelType(idol,1) Then charModel(char,1)=charModel(idol,1)
 If hatter=1
  If charHairStyle(char,1)>0 Then charHairStyle(char,1)=charHairStyle(idol,1)
  charHat(char,1)=charHat(idol,1)
  charSpecs(char,1)=charSpecs(idol,1)
 EndIf
 charBody(char,1)=charBody(idol,1)
 charLeftArm(char,1)=charLeftArm(idol,1)
 charRightArm(char,1)=charRightArm(idol,1)
 charLegs(char,1)=charLegs(idol,1)
 For col=1 To 3
  If hatter=1
   charHatCol(char,col,1)=charHatCol(idol,col,1)
   charSpecsCol(char,col,1)=charSpecsCol(idol,col,1)
  EndIf
  charFaceCol(char,col,1)=charFaceCol(idol,col,1)
  charBodyCol(char,col,1)=charBodyCol(idol,col,1)
  charLeftArmCol(char,col,1)=charLeftArmCol(idol,col,1)
  charRightArmCol(char,col,1)=charRightArmCol(idol,col,1)
  charLegsCol(char,col,1)=charLegsCol(idol,col,1)
 Next
 If hatter=<0 Then charHat(char,1)=0 : charSpecs(char,1)=0
End Function