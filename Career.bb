;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: CAREER --------------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;---------------------------- INITIATE CAREER ---------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function BeginCareer(char)
 ;status
 gamChar(slot)=char
 gamFormat(slot)=1
 gamBank(slot)=2000 
 gamWorth(slot)=GetWorth(char)
 gamClause(slot,1)=1
 gamClause(slot,2)=0
 gamClause(slot,3)=0
 gamExpenses(slot)=0
 gamVariable(slot)=0
 gamOldVariable(slot)=0
 ;schedule
 GetRealDate()
 ResetSchedule(1,0)
 For count=1 To GetDate()
  gamSchedule(slot,count)=0
 Next
 gamSchedule(slot,GetDate())=1
 gamMatch(slot,GetDate())=2
 gamPromo(slot,GetDate())=4
 randy=Rnd(0,1)
 If (randy=0 Or promoLocked(78)) And optRef>0 Then gamPromo(slot,GetDate())=78
 If promoLocked(4) Then gamPromo(slot,GetDate())=4
 ;missions
 gamMission(slot)=0
 gamTarget(slot)=0
 gamDeadline(slot)=0
 ;record
 For count=1 To 7
  gamExperience(slot,count)=0
  gamMatches(slot,count)=0
  gamWins(slot,count)=0
  gamWorldTitles(slot,count)=0
  gamInterTitles(slot,count)=0
  gamTagTitles(slot,count)=0
 Next
 ;reset character
 charHealth(char)=100
 charInjured(char)=0
 charOldInjured(char)=0
 charTrainCourse(char)=0
 charTrainLevel(char)=0
 ResetOldValues(char)
 ;prepare initial venue
 GenerateArena()
 gamArenaPreset(slot)=arenaPreset : gamArenaCrowd(slot)=arenaCrowd
 gamArenaAtmos(slot)=arenaAtmos : gamArenaLight(slot)=arenaLight
 gamArenaRopes(slot)=arenaRopes : gamArenaApron(slot)=arenaApron
 gamArenaCanvas(slot)=arenaCanvas : gamArenaMat(slot)=arenaMat
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 21. SLOT SELECTION -------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function SelectSlot()
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
If slot>0 Then foc=slot Else foc=1
keytim=0 : go=0 : gotim=0
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
	If gotim>20 And keytim=0 
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	  If foc=5 Then go=-1 Else go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	If gotim>20 And keytim=0
	 ;browse slots
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>5 Then foc=1
	 If foc<1 Then foc=5
	 ;reset
	 If KeyDown(14) And foc=<3
	  PlaySound sSmashWire : keytim=10 
	  gamChar(foc)=0 : SaveProgress()
	 EndIf
	EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(1),rX#(400),rY#(200)
 ;slots
 SetFont font(1)
 y=360
 For cyc=1 To 3
  If gamChar(cyc)>0 Then namer$=charName$(gamChar(cyc)) Else namer$="EMPTY"
  DrawOption(cyc,400,y,cyc+". "+namer$,"",0,0)
  If gamChar(cyc)>0 Then DrawImage gTape,rX#(400)-80,rY#(y)-15
  If foc=cyc And gamChar(cyc)>0 And gotim>5 Then DrawProfile(gamChar(cyc),-1,y-40)
  y=y+45
 Next
 ;options
 DrawOption(4,400,510,"HALL OF FAME","",0,0)
 DrawImage gBelt(0),rX#(400),rY#(510)-14
 DrawOption(5,400,550,"<<< EXIT <<<","",0,0)
 ;inst
 Outline("UP/DOWN=Select slot, BACKSPACE=Clear slot, ENTER=Proceed, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
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
 If foc=<3 
  If gamChar(foc)=0 Then slot=foc : fed=7 : screenAgenda=3 : screen=13
  If gamChar(foc)>0 Then slot=foc : screen=15
 EndIf
 If foc=4 Then screen=23
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;---------------------------- 15. CALENDAR HUB --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Calendar()
;restore arena
arenaPreset=gamArenaPreset(slot) : arenaCrowd=gamArenaCrowd(slot)
arenaAtmos=gamArenaAtmos(slot) : arenaLight=gamArenaLight(slot)
arenaRopes=gamArenaRopes(slot) : arenaApron=gamArenaApron(slot)
arenaCanvas=gamArenaCanvas(slot) : arenaMat=gamArenaMat(slot)
;set injured dates
If charInjured(gamChar(slot))>0
 dater=GetDate()
 For count=1 To charInjured(gamChar(slot))
  gamSchedule(slot,dater)=-1
  dater=dater+1
  If dater>48 Then dater=1
 Next
 If gamVariable(slot)=10 Then gamSchedule(slot,GetDate())=1
EndIf
;check format is logical
If TitleHolder(gamChar(slot),3) Then gamFormat(slot)=2
If TitleHolder(gamChar(slot),1) Or TitleHolder(gamChar(slot),2) Then gamFormat(slot)=1
If charFriend(gamChar(slot))=0 Or charInjured(charFriend(gamChar(slot)))>4 Then gamFormat(slot)=1
;inter-promotional equivalents
If gamSchedule(slot,GetDate())=5
 rival=gamInterProm(slot,GetDate())
 If TitleHolder(gamChar(slot),3) And fedChampTag(rival,1)>0 Then gamOpponent(slot,GetDate())=fedChampTag(rival,1)
 If TitleHolder(gamChar(slot),2) And fedChampInter(rival)>0 Then gamOpponent(slot,GetDate())=fedChampInter(rival) 
 If TitleHolder(gamChar(slot),1) And fedChampWorld(rival)>0 Then gamOpponent(slot,GetDate())=fedChampWorld(rival) 
EndIf
;prepare match
gamManaged=0
fed=charFed(gamChar(slot))
If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5
 pChar(1)=gamChar(slot) 
 If gamOpponent(slot,GetDate())=0 Then gamOpponent(slot,GetDate())=GetOpponent()
 If gamMatch(slot,GetDate())=0 Then GenerateMatch()
 matchPreset=gamMatch(slot,GetDate())
 LoadMatch(matchPreset)
 If matchPreset=>15 And matchPreset=<16 And gamGimmick(slot,GetDate())=2 Then gamGimmick(slot,GetDate())=0 ;prevent cage in rumbles
 If matchPreset=17 Then gamGimmick(slot,GetDate())=2 ;guarantee cage in escape challenge
 AddGimmick(gamGimmick(slot,GetDate()))
 ConstructMatch()
 AssignPromo()
EndIf
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
scroller=700-(gamMonth(slot)*230)
foc=1 : oldfoc=foc : screenAgenda=0
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
	If gotim>20 And keytim=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or JoyDown(1) Or (MouseDown(1) And MouseY()>rY#(250) And foc<>99)
	  If foc=7 Then go=-1 Else go=1
	 EndIf
	EndIf
	
	;CONFIGURATION
	If gotim>20 And keytim=0
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	EndIf
	If foc>7 Then foc=1
	If foc<1 Then foc=7
	;prepare to examine schedule
	If oldfoc=99 Then foc=1
	If MouseY()>rY#(135) And MouseY()<rY#(235) 
	 If MouseDown(1) Then foc=99
	 If foc=99 And oldfoc<>99 Then void=MouseXSpeed()
	EndIf
	If MouseDown(1) And oldfoc=99 Then foc=99 
	
	;SCROLLING
	;browse with keys
	If KeyDown(203) Or JoyXDir()=-1 Then scroller=scroller+15 : scrollTim=50
	If KeyDown(205) Or JoyXDir()=1 Then scroller=scroller-15 : scrollTim=50
	;browse with mouse
	If foc=99 And MouseDown(1) Then scroller=scroller+MouseXSpeed() : scrollTim=50
	;auto scrolling
	scrollTim=scrollTim-1
	If scrollTim<1 Then scrollTim=0
	If scrollTim=0 
	 scrollT=rX#(470)-(gamMonth(slot)*230) 
	 If scroller<scrollT Then scroller=scroller+10
	 If scroller>scrollT Then scroller=scroller-10
	EndIf
	If scroller>240 Then scroller=240
	If scroller<-2290 Then scroller=-2290
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)
 ;player profile
 char=gamChar(slot)
 If foc=4 Then char=0
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5 And gamOpponent(slot,GetDate())>0 
  If KeyDown(57) Or JoyDown(2) Or MouseDown(2) Then char=gamOpponent(slot,GetDate())
 EndIf
 If char=0 Then DrawFedProfile(fed,-1,-1)
 If char>0 Then DrawProfile(char,-1,-1)
 If char=gamChar(slot) And foc=3 Then ShowStatProgress(char)
 ;DATE LINE
 SetFont font(1)
 x=0 : y=rY#(184)
 dater=0 : monther=1
 For cyc=1 To 48
  dater=dater+1
  If dater>4 Then dater=1 : monther=monther+1
  If dater=1 Then x=x+80 Else x=x+50
  ;highlighter
  If cyc=GetDate()
   DrawImage gDate(11),x+scroller,y
   DrawImageRect charPhoto(gamChar(slot)),x+scroller,y-15,0,0,70,55
  EndIf
  ;schedule icons
  If gamSchedule(slot,cyc)=-1 Then DrawImage gInjury,x+scroller,y ;injured
  If gamSchedule(slot,cyc)=>0 And gamSchedule(slot,cyc)=<5 Then DrawImage gDate(gamSchedule(slot,cyc)),x+scroller,y ;event
  If cyc<GetDate() Then DrawImage gDate(10),x+scroller,y ;history
  ;result
  If cyc<GetDate() And gamSchedule(slot,cyc)=>1 And gamSchedule(slot,cyc)=<5 And gamResult(slot,cyc)>0 
   DrawImage gResult(gamResult(slot,cyc)),x+scroller,y
  EndIf
  ;month boxes
  If dater=1 
   If gamMonth(slot)=monther
    DrawImage gMenu(5),(x+75)+scroller,y+46
    Outline(textMonth$(monther),(x+75)+scroller,y+45,190,190,190,75,75,75)
    Outline(textWeek$(gamWeek(slot))+" of "+textMonth$(gamMonth(slot))+" "+gamYear(slot),(x+75)+scroller,y+70,70,60,50,255,240,220)
   Else
    DrawImage gMenu(6),(x+75)+scroller,y+46
    Outline(textMonth$(monther),(x+75)+scroller,y+45,120,120,120,0,0,0)
   EndIf
  EndIf
  ;mission reminder
  If gamMission(slot)>0 And cyc=gamDeadline(slot) Then DrawImage charPhoto(fedBooker(fed)),x+scroller,y-15 ;deadline
 Next
 ;indicate title matches
 y=290
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5 And gamOpponent(slot,GetDate())>0 
  If TitleHolder(gamChar(slot),3) Or TitleHolder(gamOpponent(slot,GetDate()),3) 
   If matchTeams>0 And no_wrestlers=4 Then DrawImage gBelt(3),rX#(400),rY#(y+7)
  EndIf
  If TitleHolder(gamChar(slot),2) Or TitleHolder(gamOpponent(slot,GetDate()),2) 
   If matchTeams=0 Then DrawImage gBelt(2),rX#(400),rY#(y+7)
  EndIf
  If TitleHolder(gamChar(slot),1) Or TitleHolder(gamOpponent(slot,GetDate()),1) 
   If matchTeams=0 Then DrawImage gBelt(1),rX#(400),rY#(y+7)
  EndIf
 EndIf
 ;event summary
 SetFont font(1) 
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5 And gamGimmick(slot,GetDate())>0 
  Outline(textGimmick$(gamGimmick(slot,GetDate())),rX#(400),rY#(y-10),50,0,0,200,Rnd(0,100),0)
 EndIf
 SetFont font(2)
 If gamSchedule(slot,GetDate())=-1 Then namer$="Injured"
 If gamSchedule(slot,GetDate())=0 Then namer$="Empty" 
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<4 Then namer$=textMatch$(matchPreset)
 If gamSchedule(slot,GetDate())=5 Then namer$="Inter-Promotional Match"
 Outline(namer$,rX#(400),rY#(y),50,0,0,200,0,0)
 ;opponent line
 SetFont font(1) 
 If gamSchedule(slot,GetDate())-1 Then namer$="Recovering from injury..."
 If gamSchedule(slot,GetDate())=0 Then namer$="Nothing scheduled..."
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5 And gamOpponent(slot,GetDate())>0 
  namer$="Vs "+charName$(gamOpponent(slot,GetDate()))
  If matchTeams>0 Then namer$="Vs "+charName$(gamOpponent(slot,GetDate()))+"'s Team"
  If no_wrestlers=>3 And matchTeams=0
   For v=3 To no_wrestlers
    namer$=namer$+" Vs "+charName$(pChar(v))
   Next
  EndIf
  If matchTeams=-1 Then namer$="Vs Multiple Opponents"
  If gamSchedule(slot,GetDate())=5 Then namer$="Vs "+fedName$(gamInterProm(slot,GetDate())) 
 EndIf
 Outline(namer$,rX#(400),rY#(y+18),30,30,30,150,150,150)
 ;options
 SetFont font(1)
 y=350
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<10 Then namer$=">>> PLAY MATCH >>>" Else namer$=">>> NEXT WEEK >>>"
 DrawOption(1,400,y,namer$,"",0,0)
 DrawOption(2,400,y+30,"EDIT CHARACTER","",0,0)
 DrawOption(3,400,y+60,"TRAINING","",0,0)
 DrawOption(4,400,y+95,"STUDY ROSTERS","",0,0)
 DrawOption(5,400,y+125,"STUDY DATABASE","",0,0)
 DrawOption(6,400,510,"RETIRE!","",0,0)
 DrawOption(7,400,550,"<<< SAVE & EXIT <<<","",0,0) 
 ;inst
 SetFont font(1)
 Outline("LEFT/RIGHT=Browse schedule, SPACE=View Opponent, ENTER=Proceed, ESC=Quit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
If go=-1 Then screen=21
If go=1
 If foc=1 
  If gamSchedule(slot,GetDate())=<0 Then screen=19 ;next week
  If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5 Then screen=50 ;match
 EndIf
 If foc=2 Then saver=gamChar(slot) : screen=51 ;edit
 If foc=3 Then screen=54 ;training
 If foc=4 Then screenAgenda=2 : screen=14 ;study rosters
 If foc=5 Then screen=20 ;database
 If foc=6 Then negTopic=30 : negChar=fedBooker(fed) : screen=53 ;retirement talks
EndIf
End Function

;/////////////////////////////////////////////////////////////////////////////////////
;------------------------------ RELATED FUNCTIONS ------------------------------------
;/////////////////////////////////////////////////////////////////////////////////////

;IDENTIFY CAREER CHARACTERS
Function CharBusy(char)
 value=0
 For count=1 To 3
  If char=gamChar(count) Then value=count
 Next
 Return value
End Function

;CALCULATE DATE FROM WEEK & MONTH
Function GetDate()
 value=0
 If gamWeek(slot)>0 And gamMonth(slot)>0
  value=((gamMonth(slot)-1)*4)+gamWeek(slot)
 EndIf
 Return value
End Function

;CALCULATE REAL DATE!
Function GetRealDate()
 gamWeek(slot)=1 : gamMonth(slot)=1
 weeker=Int(Left$(CurrentDate$(),2))
 If weeker=<7 Then gamWeek(slot)=1
 If weeker=>8 And weeker=<15 Then gamWeek(slot)=2
 If weeker=>16 And weeker=<23 Then gamWeek(slot)=3
 If weeker>23 Then gamWeek(slot)=4
 For count=1 To 12
  monther$=Mid$(CurrentDate$(),4,3)
  If monther$=Left$(textMonth$(count),3) Then gamMonth(slot)=count
 Next
 gamYear(slot)=Int(Right$(CurrentDate$(),4))
End Function

;RESET CAREER SCHEDULE
Function ResetSchedule(start,task) ;0=total, 1=shows only
 For count=start To 48
  ;reset contents
  If gamSchedule(slot,count)=<5 Or task=0 
   gamSchedule(slot,count)=0
   gamInterProm(slot,count)=0
   gamOpponent(slot,count)=0
   gamMatch(slot,count)=0
   gamGimmick(slot,count)=0
   gamPromo(slot,count)=0
   gamResult(slot,count)=0
  EndIf
  ;TV schedule
  gamSchedule(slot,count)=1
  For month=1 To 12
   If count=month*4 Then gamSchedule(slot,count)=2
  Next
 Next
 ;remove missions
 gamMission(slot)=0
End Function

;HOLDING ANY TITLES?
Function TitleHolder(char,title) ;0=return most prestigious, 1=find world, 2=find inter, 3=find tags 
 value=0
 ;find matches
 If title=1 And fedChampWorld(charFed(char))=char Then value=1
 If title=2 And fedChampInter(charFed(char))=char Then value=1
 If title=3 
  If fedChampTag(charFed(char),1)=char Or fedChampTag(charFed(char),2)=char Then value=1
 EndIf
 ;return most prestigious
 If title=0
  If fedChampTag(charFed(char),1)=char Or fedChampTag(charFed(char),2)=char Then value=3
  If fedChampInter(charFed(char))=char Then value=2
  If fedChampWorld(charFed(char))=char Then value=1
 EndIf
 Return value
End Function

;GENERATE MATCH
Function GenerateMatch()
 ;match type
 match=2
 randy=Rnd(0,30)
 If gamSchedule(slot,GetDate())=>2 Then randy=Rnd(0,15)
 If randy=<3 Then match=Rnd(3,9) ;rule variations
 If gamFormat(slot)=1
  If randy=4 Then match=10 ;triple threat
  If charFriend(gamChar(slot))>0 And charInjured(charFriend(gamChar(slot)))=0 And charHealth(charFriend(gamChar(slot)))=>50
   If randy=>5 And randy=<6 Then match=12 ;tag-team
   If randy=7 Then match=13 ;team battle
  EndIf
  If charFriend(gamChar(slot))=0 And gamOpponent(slot,GetDate())=charEnemy(gamChar(slot))
   If randy=>5 And randy=<7 Then match=11 ;handicap variation
  EndIf
  If randy=8 Then match=Rnd(14,16) ;4-way variations
  If randy=9 And fed=<6 Then match=17 ;escape challenge
  If fed=5 ;favour shoot fights at MLG
   If randy=10 Then match=6
   If randy=11 Then match=7
  EndIf
 EndIf
 gamMatch(slot,GetDate())=match
 ;gimmicks
 If fed=<6 And gamGimmick(slot,GetDate())=0 And gamSchedule(slot,GetDate())<>5
  gimmick=0
  randy=Rnd(0,60)
  If gamSchedule(slot,GetDate())=>2 Or gamOpponent(slot,GetDate())=charEnemy(gamChar(slot)) Then randy=Rnd(0,30)
  If randy=<3 Then gimmick=1 ;hardcore rules
  If randy=>4 And randy=<5 Then gimmick=2 ;steel cage
  If randy=6 Then gimmick=3 ;barbed wire
  If randy=7 Then gimmick=4 ;electrified
  If randy=8 Then gimmick=5 ;inferno
  If randy=9 And optFX=>2 Then gimmick=6 ;blast
  If randy=10 Then gimmick=6 ;glass
  If gamOpponent(slot,GetDate())=charEnemy(gamChar(slot)) 
   If randy=11 Or (randy=12 And promoLocked(71)) Then gimmick=10 ;empty arena
  EndIf
  If fed=5 And gamMatch(slot,GetDate())=>6 And gamMatch(slot,GetDate())=<7
   If randy=>13 And randy=<15 Then gimmick=2 ;shoot cage
  EndIf
  If gamMatch(slot,GetDate())=17 Then gimmick=2 ;force cage
  gamGimmick(slot,GetDate())=gimmick
 EndIf
End Function

;LOAD MATCH PROPERTIES
Function LoadMatch(match)
 ;backstage brawl
 If match=0
  no_wrestlers=2 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=1 : matchBlood=0 : matchOTT=0
  matchTimeLim=0 : matchOfficial=0
 EndIf
 ;confrontation
 If match=1
  no_wrestlers=2 : no_refs=0 : matchType=0 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;normal
 If match=2
  no_wrestlers=2 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf
 ;best of three
 If match=3
  no_wrestlers=2 : no_refs=1 : matchType=2 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=15 : matchOfficial=1
 EndIf
 ;iron man
 If match=4
  no_wrestlers=2 : no_refs=1 : matchType=3 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf
 ;24/7 challenge
 If match=5
  no_wrestlers=2 : no_refs=1 : matchType=4 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf
 ;submission
 If match=6
  no_wrestlers=2 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=0
  matchPins=0 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf
 ;shoot fight
 If match=7
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=1 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf 
 ;last man standing
 If match=8
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=1 : matchBlood=0 : matchOTT=0
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;first blood
 If match=9
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=1 : matchOTT=0
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;triple threat
 If match=10
  no_wrestlers=3 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf
 ;handicap
 If match=11
  no_wrestlers=3 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=1
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=10 : matchOfficial=1
 EndIf
 ;tag team
 If match=12
  no_wrestlers=4 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=2
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=15 : matchOfficial=1
 EndIf
 ;team battle
 If match=13
  no_wrestlers=4 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=1
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;royal brawl
 If match=14
  no_wrestlers=4 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchOTT=0
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;battle royal
 If match=15
  no_wrestlers=4 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchOTT=1
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;timed battle royal
 If match=16
  no_wrestlers=10 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=-1
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchOTT=1
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;cage escape
 If match=17
  no_wrestlers=2 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchOTT=1
  matchTimeLim=0 : matchOfficial=1
 EndIf
 ;time limit filter
 If matchTimeLim>0 And optHealth=2 Then matchTimeLim=matchTimeLim+5
 ;reputation filter
 ;If game=1 
  ;If fedRep(fed)<60 And matchRules>1 Then matchRules=1
 ;EndIf
 ;referee filter
 If optRef=0 Then no_refs=0
 If optRef=1 And no_wrestlers=>4 Then no_refs=0
End Function

;ADD GIMMICK TO CAREER MATCH
Function AddGimmick(gimmick)
 ;reset by default
 matchCage=0 : matchRopes=0 : matchBlastTim=0
 no_items=15 : itemSelection=1 : itemLayout=1 
 no_weaps=15 : weapSelection=1 : weapLayout=1 
 matchChamps=1 : matchPromo=0
 ;hardcore rules
 If gimmick>0
  If gimmick=1 Then matchRules=0 : weapSelection=0 : weapLayout=0  
  If matchRules>1 Then matchRules=1
 EndIf
 ;cages
 If gimmick=2 
  matchCage=Rnd(1,4)
  If matchPreset=6 Or matchPreset=7 Then matchRules=2 
 EndIf
 ;novelty ropes
 If gimmick=3 Then matchRopes=1 ;barbed wire
 If gimmick=4 Then matchRopes=2 ;electrified
 If gimmick=5 Then matchRopes=3 ;inferno 
 ;weapon gimmicks
 If gimmick=6 Then matchBlastTim=2+optHealth ;blast times
 If gimmick=7 Then no_items=no_items+5 ;extra glass
 ;stipulations
 If gimmick=8 Then matchChamps=5 ;hair vs hair
 If gimmick=9 Then matchChamps=6 ;loser leaves
 If gimmick=10 Then arenaCrowd=0 : gamArenaCrowd(slot)=0 : matchRules=0 ;empty arena
 ;item blocks
 If gamVariable(slot)=4 Then no_weaps=0
 If gamVariable(slot)=5 Then no_items=0 : no_weaps=0 
 ;tag variations
 If game=1 And gamFormat(slot)=2 And matchTeams=0
  If no_wrestlers<4 Then no_wrestlers=4
  If matchRules>0 Then matchTeams=2 Else matchTeams=1
 EndIf
 ;negate title matches
 If matchTeams=-1 Or gamSchedule(slot,GetDate())=5 Or fed=7 Then matchChamps=0
End Function

;GET OPPONENT
Function GetOpponent()
 char=0 : its=0
 Repeat
  satisfied=1
  ;random by default
  char=fedRoster(fed,Rnd(1,fedSize(fed)))
  If gamSchedule(slot,GetDate())=>3 And gamSchedule(slot,GetDate())=<4 Then char=Rnd(1,no_chars)
  If gamSchedule(slot,GetDate())=5 
   rival=gamInterProm(slot,GetDate())
   char=fedRoster(rival,Rnd(1,fedSize(rival)))
  EndIf
  ;career logic
  If pChar(1)=gamChar(slot)
   randy=Rnd(0,2)
   If randy=0 And charEnemy(gamChar(slot))>0 Then char=charEnemy(gamChar(slot)) ;favour enemy
   ;push title shots
   If TitleHolder(gamChar(slot),0)=0 And charPop(gamChar(slot))=>60 And gamSchedule(slot,GetDate())=<2
    If gamFormat(slot)=1 And matchTeams=0 And pChar(2)=0
     randy=Rnd(0,20)
     If randy=0 And gamInterTitles(slot,fed)=0 Then char=fedChampInter(fed) ;inter title shot
     If randy=1 And gamWorldTitles(slot,fed)=0 And (charPop(gamChar(slot))=>charPop(fedChampWorld(fed))-5 Or charPop(gamChar(slot))=>fedPop(fed))
      char=fedChampWorld(fed) ;world title shot
     EndIf
    EndIf
    If gamFormat(slot)=2 And matchTeams>0
     randy=Rnd(0,15)
     If randy=0 Then char=fedChampTag(fed,1) ;tag title shot
    EndIf
   EndIf
   ;link lucrative opponents to success
   dater=GetDate()-1
   If dater<1 Then dater=48
   randy=Rnd(0,3)
   If randy>0 And gamResult(slot,dater)<3 And charPop(char)>charPop(gamChar(slot)) Then satisfied=0 ;not worthy of superior 
   If TitleHolder(char,0)>0 And char<>charEnemy(gamChar(slot)) 
    If gamResult(slot,dater)<3 Then satisfied=0 ;not worthy of title shot
    If TitleHolder(char,1) And charPop(gamChar(slot))<charPop(char)-5 Then satisfied=0 ;not worthy of world title shot
   EndIf
   ;prevent champions as extras
   If TitleHolder(char,1) Or TitleHolder(char,2) 
    If pChar(2)>0 And matchTeams=<0 Then satisfied=0
   EndIf
   ;prevent tag champs if singles wrestler
   If TitleHolder(char,3) And gamFormat(slot)=1 Then satisfied=0
   ;prevent champs if campaigning
   If TitleHolder(char,0)>0 And gamPromo(slot,GetDate())=>57 And gamPromo(slot,GetDate())=<59 Then satisfied=0
  EndIf
  ;universal logic
  randy=Rnd(0,5)
  If randy>0 And charHeel(char)=charHeel(pChar(1)) And TitleHolder(char,0)=0 
   If no_wrestlers=2 Or matchTeams>0 Then satisfied=0 ;rarely same allegiance in opposition
  EndIf
  randy=Rnd(0,5)
  If randy>0 And char=fedBooker(fed) And char<>charEnemy(gamChar(slot)) And TitleHolder(char,0)=0 And fed<>7 
   satisfied=0 ;avoid booker
  EndIf
  randy=Rnd(0,3)
  If randy>0 And charHealth(char)<60 Then satisfied=0 ;favour healthy
  If charInjured(char)>0 Or charVacant(char)>0 Then satisfied=0 ;avoid injured/vacant
  If DesignatedRef(char) And char<>fedBooker(fed) Then satisfied=0 ;avoid officials
  If char=charFriend(gamChar(slot)) And gamVariable(slot)=8 Then satisfied=0 ;friend reserved to ref
  If char=charEnemy(gamChar(slot)) And gamVariable(slot)=9 Then satisfied=0 ;enemy reserved to ref
  If char=charEnemy(gamChar(slot)) And gamPromo(slot,GetDate())=31 Then satisfied=0 ;enemy reserved to team 
  If gamSchedule(slot,GetDate())=>3 And charFed(char)=fed Then satisfied=0 ;avoid colleagues at special events
  If charFed(char)=>8 Or (charFed(char)=7 And fed<>7) Then satisfied=0 ;avoid inactive wrestlers
  If CharBusy(char)>0 Or FindCharacter(char)>0 Then satisfied=0 ;avoid already booked
  If (charLimb(char,31)=0 Or charLimb(char,32)=0 Or charLimb(char,33)=0) And (charLimb(char,34)=0 Or charLimb(char,35)=0 Or charLimb(char,36)=0)
   satisfied=0 ;avoid cripples!
  EndIf
  ;get-out clause
  its=its+1
  If its>1000 Then satisfied=1
 Until satisfied=1
 Return char
End Function

;GET REFEREE
Function GetReferee()
 char=0 : its=0
 Repeat
  satisfied=1
  ;random by default
  char=fedRoster(fed,Rnd(1,fedSize(fed)))
  If gamSchedule(slot,GetDate())=>3 Then char=Rnd(1,no_chars)
  If gamSchedule(slot,GetDate())=5 ;avoid bias at inter-promotionals
   If charFed(char)=charFed(gamChar(slot)) Or charFed(char)=gamInterProm(slot,GetDate()) Then satisfied=0 
  EndIf
  ;favour designated
  randy=Rnd(0,5)
  If randy>0 And gamSchedule(slot,GetDate())=<2 And DesignatedRef(char)=0 Then satisfied=0 
  ;push significant's
  randy=Rnd(0,30)
  If randy=<1 And charEnemy(gamChar(slot))>0 Then char=charEnemy(gamChar(slot)) ;enemy
  If TitleHolder(gamChar(slot),0)=0
   If randy=2 And gamWorldTitles(slot,fed)=0 
    If charPop(gamChar(slot))>charPop(fedChampWorld(fed))-5 Or charPop(gamChar(slot))=>fedPop(fed) Then char=fedChampWorld(fed) ;world champ
   EndIf
   If randy=3 And gamInterTitles(slot,fed)=0 And charPop(gamChar(slot))<charPop(fedChampInter(fed)) Then char=fedChampInter(fed) ;inter champ
  EndIf
  ;check suitability
  randy=Rnd(0,3)
  If randy>0 And charHealth(char)<50 Then satisfied=0 ;favour healthy
  If charInjured(char)>0 Then satisfied=0 ;avoid injured
  If charFed(char)=9 Then satisfied=0 ;avoid dead wrestlers (interpromotional events)
  If CharBusy(char)>0 Or FindCharacter(char)>0 Then satisfied=0 ;avoid already booked
  If (charLimb(char,31)=0 Or charLimb(char,32)=0 Or charLimb(char,33)=0) And (charLimb(char,34)=0 Or charLimb(char,35)=0 Or charLimb(char,36)=0)
   satisfied=0 ;avoid cripples!
  EndIf
  its=its+1
  If its>1000 Then satisfied=1
 Until satisfied=1
 ;force coach at beginning
 If gamPromo(slot,GetDate())=4 Then char=fedBooker(fed)
 ;planned referees
 If gamVariable(slot)=8 Then char=charFriend(gamChar(slot))
 If gamVariable(slot)=9 Then char=charEnemy(gamChar(slot))
 Return char
End Function

;CONSTRUCT MATCH
Function ConstructMatch()
 ;reset characters
 For cyc=1 To optPlayLim
  pChar(cyc)=0
  pControl(cyc)=0
 Next
 If no_wrestlers>fedSize(fed) Then no_wrestlers=fedSize(fed)
 ;complusaries
 pChar(1)=gamChar(slot)
 If matchTeams=<0 Then pChar(2)=gamOpponent(slot,GetDate())
 ;team-mate logic
 If matchTeams>0 And no_wrestlers=4 
  If gamPromo(slot,GetDate())=31 Then pChar(2)=charEnemy(gamChar(slot)) 
  pChar(3)=gamOpponent(slot,GetDate())  
  If charFriend(pChar(1))>0 And FindCharacter(charFriend(pChar(1)))=0 Then pChar(2)=charFriend(pChar(1))
  If charFriend(pChar(3))>0 And FindCharacter(charFriend(pChar(3)))=0 Then pChar(4)=charFriend(pChar(3))
  If pChar(3)=fedChampTag(fed,1) And FindCharacter(fedChampTag(fed,2))=0 Then pChar(4)=fedChampTag(fed,2) 
  If pChar(3)=fedChampTag(fed,2) And FindCharacter(fedChampTag(fed,1))=0 Then pChar(4)=fedChampTag(fed,1)
  If pChar(1)=fedChampTag(fed,1) And FindCharacter(fedChampTag(fed,2))=0 Then pChar(2)=fedChampTag(fed,2) 
  If pChar(1)=fedChampTag(fed,2) And FindCharacter(fedChampTag(fed,1))=0 Then pChar(2)=fedChampTag(fed,1)
  While TitleHolder(pChar(4),3) And TitleHolder(pChar(3),3)=0
   pChar(4)=GetOpponent()
  Wend
  If gamPromo(slot,GetDate())=31 Then pChar(2)=charEnemy(gamChar(slot))
 EndIf
 ;handicap variation
 If matchTeams>0 And no_wrestlers=3 
  pChar(2)=gamOpponent(slot,GetDate())
  If charFriend(pChar(2))>0 And FindCharacter(charFriend(pChar(2)))=0 Then pChar(3)=charFriend(pChar(2))
 EndIf
 ;random extras
 For v=1 To no_wrestlers
  If pChar(v)=0 Then pChar(v)=GetOpponent()
 Next
 ;make player last in rumbles!
 If matchTeams=-1 
  pChar(1)=pChar(no_wrestlers)
  pChar(no_wrestlers)=gamChar(slot) 
 EndIf
 ;add referees
 no_plays=no_wrestlers+no_refs
 If no_refs>0
  For v=no_wrestlers+1 To no_wrestlers+no_refs
   pChar(v)=GetReferee()
  Next
 EndIf
End Function

;GENERATE ARENA
Function GenerateArena()
 ;venue
 fed=charFed(gamChar(slot))
 arenaPreset=Rnd(1,no_arenas-2)
 If gamSchedule(slot,GetDate())=>2 Then arenaPreset=Rnd(10,no_arenas-2)
 randy=Rnd(0,30)
 If gamSchedule(slot,GetDate())=>2 Then randy=Rnd(0,15)
 If randy=0 Then arenaPreset=10
 If randy=1 Then arenaPreset=13
 If randy=2 Then arenaPreset=14 
 If fed=7 Then arenaPreset=1
 ;attendance
 arenaCrowd=Rnd(1,2)
 If fed=7 Then arenaCrowd=0
 If gamSchedule(slot,GetDate())=>2 Then arenaCrowd=Rnd(2,3)
 If arenaCrowd>1 And fedPop(fed)=<60 Then arenaCrowd=1
 If arenaCrowd>2 And fedPop(fed)=<75 Then arenaCrowd=2 
 If gamGimmick(slot,GetDate())=10 Then arenaCrowd=0
 ;atmosphere
 arenaAtmos=Rnd(0,9) 
 If arenaPreset=1 Then arenaAtmos=3
 If arenaPreset=>15 Then arenaAtmos=4
 If optAtmos=0 Then arenaAtmos=0
 arenaLight=0
 ;apron
 arenaApron=0
 If gamSchedule(slot,GetDate())=>1 And gamSchedule(slot,GetDate())=<5 Then arenaApron=gamSchedule(slot,GetDate())
 ;canvas
 arenaCanvas=Rnd(1,4)
 If gamSchedule(slot,GetDate())=>3 And gamSchedule(slot,GetDate())=<5 Then arenaCanvas=5
 ;ropes
 If fed=1
  randy=Rnd(0,3)
  If randy=<1 Then arenaRopes=1 
  If randy=2 Then arenaRopes=6
  If randy=3 Then arenaRopes=8
 EndIf
 If fed=2
  randy=Rnd(1,2)
  If randy=1 Then arenaRopes=1 
  If randy=2 Then arenaRopes=Rnd(6,8)
 EndIf
 If fed=3
  randy=Rnd(1,2)
  If randy=1 Then arenaRopes=2 
  If randy=2 Then arenaRopes=Rnd(8,9)
 EndIf
 If fed=4
  randy=Rnd(1,2)
  If randy=1 Then arenaRopes=3 
  If randy=2 Then arenaRopes=Rnd(8,9)
 EndIf
 If fed=5
  randy=Rnd(1,2)
  If randy=1 Then arenaRopes=4 
  If randy=2 Then arenaRopes=Rnd(8,9)
 EndIf
 If fed=6
  randy=Rnd(0,5)
  If randy=<2 Then arenaRopes=5
  If randy=3 Then arenaRopes=3
  If randy=4 Then arenaRopes=6
  If randy=5 Then arenaRopes=Rnd(8,9)
 EndIf
 If fed=>7 Then arenaRopes=Rnd(8,9)
 If gamSchedule(slot,GetDate())=>3 And gamSchedule(slot,GetDate())=<5 Then arenaRopes=1
 ;matting
 randy=Rnd(0,5)
 If randy=0 Then arenaMat=0
 If randy=>1 And randy=<2 Then arenaMat=1
 If randy=3 Then arenaMat=2
 If randy=>4 And randy=<5 Then arenaMat=3
End Function

;FIND SUITABLE PROMO FOR MATCH
Function AssignPromo()
 matchPromo=gamPromo(slot,GetDate())
 If matchPromo=0 And fed=<6
  ;title shot
  randy=Rnd(0,4)
  If (randy=0 Or promoLocked(21)) And matchTeams=0 And gamOpponent(slot,GetDate())=fedChampInter(fed) Then matchPromo=21
  If (randy=1 Or promoLocked(20)) And matchTeams=0 And gamOpponent(slot,GetDate())=fedChampWorld(fed) Then matchPromo=20
  If (randy=2 Or promoLocked(22)) And matchTeams>0 And TitleHolder(gamOpponent(slot,GetDate()),3) Then matchPromo=22
  If (randy=>3 Or promoLocked(76)) And matchTeams=0 
   If gamOpponent(slot,GetDate())=fedChampWorld(fed) Or gamOpponent(slot,GetDate())=fedChampInter(fed) Then matchPromo=76
  EndIf
  ;title defence
  randy=Rnd(0,3)
  If (randy=0 Or promoLocked(18)) And matchTeams=0 And gamChar(slot)=fedChampInter(fed) Then matchPromo=18
  If (randy=1 Or promoLocked(17)) And matchTeams=0 And gamChar(slot)=fedChampWorld(fed) Then matchPromo=17
  If (randy=2 Or promoLocked(19)) And matchTeams>0 And TitleHolder(gamChar(slot),3) Then matchPromo=19  
  ;heated words
  If gamOpponent(slot,GetDate())=charEnemy(gamChar(slot)) Or gamChar(slot)=charEnemy(gamOpponent(slot,GetDate()))
   randy=Rnd(0,8)
   If randy=0 And charSalary(gamChar(slot))>0 Then matchPromo=7
   If randy=1 Then matchPromo=8
   If randy=2 Then matchPromo=9
   If randy=3 Then matchPromo=38
   If randy=4 Then matchPromo=54
   If randy=5 Or promoLocked(37) 
    If charPop(gamChar(slot))<charPop(gamOpponent(slot,GetDate())) Then matchPromo=37 ;superior opponent
   EndIf
   If randy=6 Or promoLocked(60) 
    If charAge(gamChar(slot))>charAge(gamOpponent(slot,GetDate())) Then matchPromo=60 ;younger opponent
   EndIf
   If randy=7 Or promoLocked(61) 
    If charTough(gamChar(slot))<charTough(gamOpponent(slot,GetDate())) Then matchPromo=61 ;tougher opponent
   EndIf
   If randy=8 Or promoLocked(72) 
    If gamOpponent(slot,GetDate())=fedBooker(fed) Then matchPromo=72 ;facing the boss!
   EndIf
   If matchRules=2 And matchPreset=>3 And matchPreset=<7 Then matchPromo=34 ;technical reference
   If matchRules=0 Or (gamGimmick(slot,GetDate())=>1 And gamGimmick(slot,GetDate())=<7) Then matchPromo=35 ;hardcore reference
   If matchCage>0 Then matchPromo=36 ;cage reference
   If arenaCrowd=0 Then matchPromo=71 ;empty arena
   If matchPreset=11 Then matchPromo=77 ;handicap reference
   If gamFormat(slot)=1 And matchTeams>0 And no_wrestlers=4 ;team reference
    randy=Rnd(0,3)
    If randy=0 Or promoLocked(63) Then matchPromo=63
   EndIf 
  EndIf
  ;facing friend
  If gamOpponent(slot,GetDate())=charFriend(gamChar(slot)) Or gamOpponent(slot,GetDate())=charManager(gamChar(slot)) ; Or gamChar(slot)=charFriend(gamOpponent(slot,GetDate()))
   matchPromo=23
  EndIf
  ;referee issues
  If no_refs>0 
   If DesignatedRef(pChar(no_wrestlers+1))=0 And matchPromo=0 
    randy=Rnd(0,5)
    If randy=0 Or promoLocked(28) Then matchPromo=28 ;guest referee
   EndIf
   If pChar(no_wrestlers+1)=charEnemy(gamChar(slot)) Then matchPromo=15 ;corrupt referee   
   If matchTeams=0 And GetDate()=<47 
    If pChar(no_wrestlers+1)=fedChampInter(fed) And TitleHolder(gamChar(slot),0)=0 Then matchPromo=41 ;inter contendership
    If pChar(no_wrestlers+1)=fedChampWorld(fed) And fedChampWorld(fed)<>gamChar(slot) Then matchPromo=33 ;world contendership
   EndIf
  EndIf
  ;injury return
  If charInjured(gamChar(slot))=0 And charOldInjured(gamChar(slot))>0 Then matchPromo=27
  ;farewell
  If charContract(gamChar(slot))=1 
   If charHeel(gamChar(slot))=1 Then matchPromo=42 Else matchPromo=13
  EndIf
  ;welcome to fed
  If fed=<6 And gamMatches(slot,fed)=0
   If charHeel(gamOpponent(slot,GetDate()))=1 Then matchPromo=5 Else matchPromo=6
  EndIf
  ;special event
  If gamSchedule(slot,GetDate())=3 Then matchPromo=10 ;tribute
  If gamSchedule(slot,GetDate())=4 Then matchPromo=11 ;charity
  If gamSchedule(slot,GetDate())=5 
   If matchTeams>0 Then matchPromo=53 Else matchPromo=12 ;inter-promotional
  EndIf
 EndIf
 ;match summary if nothing else
 If no_refs>0 And (matchPromo=0 Or matchPromo=28)
  If matchPreset<>2 And matchPreset<>12 And matchOfficial=1 Then matchPromo=16
 EndIf
End Function

;CREATE ANGLES BASED ON MATCH RESULT
Function FindAngles()
 ;blow-off current feud
 randy=Rnd(0,10)
 If randy=0 And gamOpponent(slot,GetDate())=charEnemy(gamChar(slot))
  charEnemy(gamChar(slot))=0
  charEnemy(gamOpponent(slot,GetDate()))=0
 EndIf
 ;consider new feud
 dater=GetDate()+1
 If dater>48 Then dater=1
 ;---- CASUAL ASSIGNMENTS
 If gamOpponent(slot,dater)=0 And gamMatch(slot,dater)=0 And gamPromo(slot,dater)=0 And gamSchedule(slot,GetDate())=<2
  ;annoyed at victory
  If pChar(matchWinner)=gamChar(slot) And matchLoser>0 And pTeam(matchLoser)<>pTeam(matchWinner)
   randy=Rnd(0,6)
   If randy=0 Then BecomeEnemies(gamChar(slot),pChar(matchLoser),0)
   If randy=1 And pHealth(matchWinner)=<optTired And pHealth(matchLoser)=<optTired ;close call
    gamOpponent(slot,dater)=pChar(matchLoser) : gamPromo(slot,dater)=39 
    BecomeEnemies(gamChar(slot),pChar(matchLoser),1)
   EndIf
   If randy=2 And pHealthLimit(matchWinner)>pHealthLimit(matchLoser) ;fitness claim
    gamOpponent(slot,dater)=pChar(matchLoser) : gamPromo(slot,dater)=67
    charHealth(pChar(matchLoser))=charHealth(pChar(matchLoser))+50
    BecomeEnemies(gamChar(slot),pChar(matchLoser),1)
   EndIf
   If randy=3 And matchWeapon>0 ;weapon claim
    gamOpponent(slot,dater)=pChar(matchLoser) : gamPromo(slot,dater)=74
    BecomeEnemies(gamChar(slot),pChar(matchLoser),1)
    charWeapon(pChar(matchLoser))=matchWeapon
   EndIf
   If (randy=4 Or charInjured(pChar(matchLoser))>0) And charFriend(pChar(matchLoser))>0 And charFriend(pChar(matchLoser))<>gamChar(slot) 
    gamOpponent(slot,dater)=charFriend(pChar(matchLoser)) : gamPromo(slot,dater)=24 ;friend avenges
    BecomeEnemies(gamChar(slot),pChar(matchLoser),1)
    BecomeFriends(pChar(matchLoser),charFriend(pChar(matchLoser)),1)
   EndIf
  EndIf
  ;time out leads to rematch
  If matchPlayer=<no_wrestlers And matchMins=matchTimeLim And matchTimeLim=>10 And matchWinner=0
   randy=Rnd(0,2)
   If randy=0
    gamOpponent(slot,dater)=gamOpponent(slot,GetDate()) : gamPromo(slot,dater)=79 
    BecomeEnemies(gamChar(slot),gamOpponent(slot,GetDate()),1)
   EndIf
  EndIf
  ;world title win leads to feud
  If fedOldChampWorld(fed)<>gamChar(slot) And fedChampWorld(fed)=gamChar(slot)
   randy=Rnd(0,3)
   If randy=<1 Then BecomeEnemies(gamChar(slot),fedOldChampWorld(fed),randy)
   If randy=1 Then gamOpponent(slot,dater)=fedOldChampWorld(fed) : gamPromo(slot,dater)=46
  EndIf
  ;world title loss leads to rematch
  If fedOldChampWorld(fed)=gamChar(slot) And fedChampWorld(fed)<>gamChar(slot)
   randy=Rnd(0,3)
   If randy=<1 Then BecomeEnemies(gamChar(slot),fedChampWorld(fed),randy)
   If randy=1 Then gamOpponent(slot,dater)=fedChampWorld(fed) : gamPromo(slot,dater)=45
  EndIf 
  ;inter title win leads to feud
  If fedOldChampInter(fed)<>gamChar(slot) And fedChampInter(fed)=gamChar(slot)
   randy=Rnd(0,3)
   If randy=<1 Then BecomeEnemies(gamChar(slot),fedOldChampInter(fed),randy)
   If randy=1 Then gamOpponent(slot,dater)=fedOldChampInter(fed) : gamPromo(slot,dater)=46
  EndIf
  ;inter title loss leads to rematch
  If fedOldChampInter(fed)=gamChar(slot) And fedChampInter(fed)<>gamChar(slot)
   randy=Rnd(0,3)
   If randy=<1 Then BecomeEnemies(gamChar(slot),fedChampInter(fed),randy)
   If randy=1 Then gamOpponent(slot,dater)=fedChampInter(fed) : gamPromo(slot,dater)=45
  EndIf 
  ;novelty ref leads to feud
  reffer=pChar(no_wrestlers+1)
  If no_refs>0 And DesignatedRef(reffer)=0 And TitleHolder(reffer,0)=0 And matchWinner>0 And pTeam(matchWinner)<>pTeam(matchPlayer)
   randy=Rnd(0,4)
   If randy=0
    BecomeEnemies(gamChar(slot),reffer,1) 
    gamOpponent(slot,dater)=reffer : gamPromo(slot,dater)=75 
   EndIf
  EndIf
  ;interference leads to feud
  If matchInvader>0
   If pChar(1)=gamChar(slot) And pTeam(matchInvader)<>1 And matchOfficial=1
    If pChar(matchInvader)=charFriend(gamChar(slot)) Or pChar(matchInvader)=charManager(gamChar(slot))
     BecomeEnemies(gamChar(slot),pChar(matchInvader),1) 
     gamOpponent(slot,dater)=pChar(matchInvader) : gamPromo(slot,dater)=73
    Else
     If pChar(matchWinner)=gamChar(slot) Then randy=Rnd(0,3) Else randy=Rnd(0,1)
     If randy=<1 Then BecomeEnemies(gamChar(slot),pChar(matchInvader),randy) 
     If randy=1 Then gamOpponent(slot,dater)=pChar(matchInvader) : gamPromo(slot,dater)=2
    EndIf
   EndIf
  EndIf
  ;your interference (management) leads to feud
  If matchPlayer>0 And pRole(matchPlayer)=2 And matchWinner=1
   randy=Rnd(0,3)
   If randy=<1 Then BecomeEnemies(gamChar(slot),pChar(2),randy) 
   If randy=1 Then gamOpponent(slot,dater)=pChar(2) : gamPromo(slot,dater)=1
  EndIf
  ;your refereeing leads to feud
  If matchPlayer>0 And pRole(matchPlayer)=1 And matchWinner>0
   For cyc=1 To no_wrestlers
    randy=Rnd(0,3)
    If randy=<1 And matchWinner<>cyc Then BecomeEnemies(gamChar(slot),pChar(cyc),randy) 
    If randy=1 And matchWinner<>cyc Then gamOpponent(slot,dater)=pChar(cyc) : gamPromo(slot,dater)=14
   Next
  EndIf 
  ;loss threatens to break team
  If matchTeams>0 And gamFormat(slot)=2 And matchWinner>0 And pTeam(matchWinner)<>1
   randy=Rnd(0,5)
   If randy=0 
    gamOpponent(slot,dater)=charFriend(gamChar(slot)) : gamPromo(slot,dater)=55
    BecomeEnemies(gamChar(slot),charFriend(gamChar(slot)),0) : gamFormat(slot)=1
   EndIf
  EndIf
  ;win threatens to break team
  If matchTeams>0 And gamFormat(slot)=2 And matchWinner=matchPlayer And TitleHolder(gamChar(slot),3)=0
   randy=Rnd(0,15)
   If randy=0 
    gamOpponent(slot,dater)=charFriend(gamChar(slot)) : gamPromo(slot,dater)=70
    BecomeEnemies(gamChar(slot),charFriend(gamChar(slot)),0) : gamFormat(slot)=1
   EndIf
  EndIf
  ;negate promos in school
  If fed=7 Then gamPromo(slot,dater)=0
  ;negate if injured
  If charInjured(gamOpponent(slot,dater))>0 Then gamOpponent(slot,dater)=0 : gamPromo(slot,dater)=0
 EndIf
 ;------- URGENT ASSIGNMENTS
 ;inter contender
 If matchPromo=41 And pChar(matchWinner)=gamChar(slot) And fedChampInter(fed)<>gamChar(slot)
  gamOpponent(slot,dater)=fedChampInter(fed)
  gamMatch(slot,dater)=2 : gamPromo(slot,dater)=0
 EndIf
 ;world contender
 If matchPromo=33 And pChar(matchWinner)=gamChar(slot) And fedChampWorld(fed)<>gamChar(slot)
  gamOpponent(slot,dater)=fedChampWorld(fed)
  gamMatch(slot,dater)=2 : gamPromo(slot,dater)=0
 EndIf
 ;rumble contender
 If matchTeams=-1 And pChar(matchWinner)=gamChar(slot) And TitleHolder(gamChar(slot),0)=0
  gamOpponent(slot,dater)=fedChampInter(fed)
  If gamInterTitles(slot,fed)>0 Or gamWorldTitles(slot,fed)>0 Then gamOpponent(slot,dater)=fedChampWorld(fed) 
  gamMatch(slot,dater)=2 : gamPromo(slot,dater)=0
 EndIf
 ;positive intruder becomes friend
 If matchInvader<0 And pChar(matchWinner)=gamChar(slot)
  BecomeFriends(gamChar(slot),pChar(MakePositive#(matchInvader)),0)
 EndIf
 ;negative intruder becomes enemy
 If matchInvader>0
  BecomeEnemies(gamChar(slot),pChar(matchInvader),0)
 EndIf
End Function