;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: CHARTS --------------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 19. FINANCIAL REPORT -----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function FinanceReport()
;reset working figures
For count=1 To 6
 gamFinance(count,0)=0
Next
oldBank=gamBank(slot)
gamFinance(6,0)=gamBank(slot)
;calculate income
gamBonus=0
gamFinance(1,1)=charSalary(gamChar(slot))
If gamSchedule(slot,GetDate())=2 Then gamFinance(1,1)=charSalary(gamChar(slot))+(charSalary(gamChar(slot))/3) : gamBonus=1 ;PPV bonus
If gamSchedule(slot,GetDate())=3 Or gamSchedule(slot,GetDate())=4 Then gamFinance(1,1)=0 : gamBonus=2 ;charitable donation
If gamSchedule(slot,GetDate())=5 And matchWinner=1 Then gamFinance(1,1)=charSalary(gamChar(slot))*2 : gamBonus=3 ;inter-promotional bonus
;assignment bonuses
If matchPlayer>0 And matchQuit=0 
 If pRole(matchPlayer)=1 And matchWinner>0 Then gamFinance(1,1)=gamFinance(1,1)+negPayOff : gamBonus=6 ;ref duty
 If pRole(matchPlayer)=2 And matchWinner=1 Then gamFinance(1,1)=gamFinance(1,1)+negPayOff : gamBonus=7 ;management duty
 If gamVariable(slot)=3 Then gamFinance(1,1)=gamFinance(1,1)+negPayOff : gamBonus=8 ;2nd match
 If gamVariable(slot)=7 And matchWinner<>matchPlayer Then gamFinance(1,1)=gamFinance(1,1)+negPayOff : gamBonus=9 ;lie down bonus
EndIf
matchPlayer=0
;penalties
If gamFinance(1,1)>0 And gamBonus=<3 
 If gamResult(slot,GetDate())=<1 Or gamSchedule(slot,GetDate())=0 ;performance filter
  If gamClause(slot,2)=0 Then gamFinance(1,1)=0 : gamBonus=4
  If gamClause(slot,2)=1 Then gamFinance(1,1)=gamFinance(1,1)/2 : gamBonus=4
 EndIf
 If gamSchedule(slot,GetDate())=-1 ;injury filter
  If gamClause(slot,3)=0 Then gamFinance(1,1)=0
  If gamClause(slot,3)=1 Then gamFinance(1,1)=charSalary(gamChar(slot))/2
  If gamClause(slot,3)>0 Then gamBonus=5
 EndIf
EndIf
;calculate lifestyle costs
gamFinance(2,1)=gamWorth(slot)/4
If (gamBank(slot)/100)>gamFinance(2,1) Then gamFinance(2,1)=gamBank(slot)/100
If gamFinance(2,1)>gamWorth(slot)/2 Then gamFinance(2,1)=gamWorth(slot)/2
If gamFinance(2,1)<100 Then gamFinance(2,1)=100
;If charAge(gamChar(slot))<18 And charContract(gamChar(slot))=0 Then gamFinance(2,1)=0
;calculate gimmick costs
gamFinance(3,1)=gamExpenses(slot)
If charWeapon(gamChar(slot))>0 And gamVariable(slot)<>4 And gamVariable(slot)<>5 And gamSchedule(slot,GetDate())>0
 gamFinance(3,1)=gamFinance(3,1)+weapCost(charWeapon(gamChar(slot)))
EndIf
;calculate management costs
gamFinance(4,1)=0
If charManager(gamChar(slot))>0 And gamManaged=1
 If charManager(gamChar(slot))<>charFriend(gamChar(slot)) Or gamFormat(slot)=1
  gamFinance(4,1)=charSalary(gamChar(slot))/5
  If gamFinance(4,1)<100 Then gamFinance(4,1)=100
 EndIf
EndIf
;calculate profit
gamFinance(5,1)=gamFinance(1,1)
gamFinance(5,1)=gamFinance(5,1)-(gamFinance(2,1)+gamFinance(3,1)+gamFinance(4,1))
gamFinance(6,1)=gamBank(slot)+gamFinance(5,1)
;round off values
For count=1 To 6
 gamFinance(count,1)=RoundOff(gamFinance(count,1),10)
Next
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
foc=0 : fed=charFed(gamChar(slot))
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
	;paper thud
	If gotim=-10 Then PlaySound sPaper
	;initial subject
	If gotim>10 And foc=0 Then foc=1
	
	;PROCESS
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;develop figures
	 For cyc=1 To 6
	  If foc=cyc And keytim=0
	   If gamFinance(cyc,0)<>gamFinance(cyc,1) 
	    PlaySound sMenuBrowse : keytim=4
	    gamFinance(cyc,0)=gamFinance(cyc,0)+PursueFigure(gamFinance(cyc,0),gamFinance(cyc,1))
	   EndIf
	   If gamFinance(cyc,0)=gamFinance(cyc,1) 
	    foc=foc+1 : keytim=20
	    If gamFinance(cyc,1)=0 Then PlaySound sMenuSelect Else PlaySound sCash
	    If foc=>7 Then gamBank(slot)=gamFinance(6,1)
	   EndIf
	  EndIf
	 Next
	 ;leave
	 If foc=>7 And keytim=0
	  If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Then go=1
	 EndIf
	EndIf
	;speed up
	If foc=>1 And foc=<6 And keytim>0
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Then keytim=keytim/2
	EndIf
	
 UpdateWorld
 Next 
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65) 
 If gotim>0 Then DrawProfile(gamChar(slot),-1,-1)
 ;REPORT
 If gotim>-10
  x=rX#(400) : y=rY#(390)
  DrawImage gReport,x,y
  ;dateline
  SetFont fontNews(0) : Color 50,50,50
  Text x+116,y-142,"("+textWeek$(gamWeek(slot))+" of "+textMonth$(gamMonth(slot))+" "+gamYear(slot)+")",1,1 
  ;headers
  SetFont fontNews(1)
  Outline("EMPLOYEE:",x-30,y-90,225,220,215,50,50,50)
  OutlineStraight(charName$(gamChar(slot)),x+10,y-90,225,220,215,50,50,50)
  Outline("EMPLOYER:",x-31,y-73,225,220,215,50,50,50)
  OutlineStraight(charName$(fedBooker(fed)),x+10,y-73,225,220,215,50,50,50)
  Outline("PROMOTION:",x-37,y-54,225,220,215,50,50,50)
  OutlineStraight(fedName$(fed),x+10,y-54,225,220,215,50,50,50)
  ;income
  spacer=StringWidth(GetFigure$(gamFinance(1,0)))+20
  If foc=1 Then Color 150,150,150 : Rect x-55,y-32,55+spacer,15,0
  Outline("INCOME:",x-23,y-25,225,220,215,50,50,50)
  If gamFinance(1,0)<>0 Then r=0 : g=150 : b=0 Else r=50 : g=50 : b=50
  OutlineStraight("$"+GetFigure$(gamFinance(1,0)),x+10,y-25,225,220,215,r,g,b)
  ;living costs
  spacer=StringWidth(GetFigure$(gamFinance(2,0)))+20
  If foc=2 Then Color 150,150,150 : Rect x-92,y-1,92+spacer,15,0
  Outline("LIVING COSTS:",x-42,y+6,225,220,215,50,50,50)
  If gamFinance(2,0)<>0 Then r=150 : g=0 : b=0 Else r=50 : g=50 : b=50
  OutlineStraight("$"+GetFigure$(gamFinance(2,0)),x+10,y+6,225,220,215,r,g,b)
  ;gimmick changes
  spacer=StringWidth(GetFigure$(gamFinance(3,0)))+20
  If foc=3 Then Color 150,150,150 : Rect x-122,y+16,122+spacer,15,0
  Outline("GIMMICK CHANGES:",x-58,y+23,225,220,215,50,50,50)
  If gamFinance(3,0)<>0 Then r=150 : g=0 : b=0 Else r=50 : g=50 : b=50
  OutlineStraight("$"+GetFigure$(gamFinance(3,0)),x+10,y+23,225,220,215,r,g,b)
  ;management
  spacer=StringWidth(GetFigure$(gamFinance(4,0)))+20
  If foc=4 Then Color 150,150,150 : Rect x-124,y+33,124+spacer,15,0 
  Outline("MANAGEMENT FEES:",x-58,y+40,225,220,215,50,50,50)
  If gamFinance(4,0)<>0 Then r=150 : g=0 : b=0 Else r=50 : g=50 : b=50
  OutlineStraight("$"+GetFigure$(gamFinance(4,0)),x+10,y+40,225,220,215,r,g,b)
  ;profit
  spacer=StringWidth(GetFigure$(gamFinance(5,0)))+20
  If foc=5 Then Color 150,150,150 : Rect x-48,y+68,48+spacer,15,0
  Outline("PROFIT:",x-20,y+75,225,220,215,50,50,50)
  r=50 : g=50 : b=50
  If gamFinance(5,0)>0 Then r=0 : g=150 : b=0
  If gamFinance(5,0)<0 Then r=150 : g=0 : b=0
  OutlineStraight("$"+GetFigure$(gamFinance(5,0)),x+10,y+75,225,220,215,r,g,b)
  ;balance
  spacer=StringWidth(GetFigure$(gamFinance(6,0)))+20
  If foc=6 Then Color 150,150,150 : Rect x-92,y+98,92+spacer,15,0
  Outline("NEW BALANCE:",x-42,y+105,225,220,215,50,50,50)
  r=50 : g=50 : b=50
  If gamFinance(6,0)>oldBank Then r=0 : g=150 : b=0
  If gamFinance(6,0)<oldBank Then r=150 : g=0 : b=0
  OutlineStraight("$"+GetFigure$(gamFinance(6,0)),x+10,y+105,225,220,215,r,g,b)
  ;bonus explanations
  If foc>1
   SetFont font(0)
   spacer=StringWidth(GetFigure$(gamFinance(1,1)))+25
   If gamBonus=1 And charSalary(gamChar(slot))>0 Then OutlineStraight("(PPV Bonus!)",x+spacer,y-25,225,220,215,130,0,250)
   If gamBonus=2 Then OutlineStraight("(Donated)",x+spacer,y-25,225,220,215,250,120,180)
   If gamBonus=3 And charSalary(gamChar(slot))>0 Then OutlineStraight("(Victory Bonus!)",x+spacer,y-25,225,220,215,130,0,250)
   If gamBonus=4 And charSalary(gamChar(slot))>0 Then OutlineStraight("(Performance Penalty)",x+spacer,y-25,225,220,215,130,130,130)
   If gamBonus=5 And gamFinance(1,1)>0 Then OutlineStraight("(Health Cover)",x+spacer,y-25,225,220,215,230,0,0)
   If gamBonus=6 Then OutlineStraight("(Referee Bonus!)",x+spacer,y-25,225,220,215,130,0,250)
   If gamBonus=7 Then OutlineStraight("(Management Bonus!)",x+spacer,y-25,225,220,215,130,0,250)
   If gamBonus=8 Then OutlineStraight("(Overtime Bonus!)",x+spacer,y-25,225,220,215,130,0,250)
   If gamBonus=9 Then OutlineStraight("(Corruption Bonus!)",x+spacer,y-25,225,220,215,130,0,250)
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
gamExpenses(slot)=0
screen=16
End Function

;----------------------------------------------------------------------
;///////////////////// 22. RETIREMENT SUMMARY /////////////////////////
;----------------------------------------------------------------------
Function EndCareer()
;get working values
hiChar(0)=gamChar(slot)
hiName$(0)=charName$(gamChar(slot))
hiFed(0)=charFed(gamChar(slot))
hiTalent(0)=GetValue(gamChar(slot))
hiBank(0)=gamBank(slot)
hiExperience(0)=CountExperience(slot)
;calculate win %
hiSuccess(0)=0
If CountMatches(slot)>0
 percy#=Float#(CountWins(slot))/Float#(CountMatches(slot))
 percy#=percy#*100
 hiSuccess(0)=Int(percy#)
EndIf
;count title reigns
hiTitles(0)=0
For count=1 To 6
 If gamWorldTitles(slot,count)>0 Then hiTitles(0)=hiTitles(0)+1
 If gamInterTitles(slot,count)>0 Then hiTitles(0)=hiTitles(0)+1
 If gamTagTitles(slot,count)>0 Then hiTitles(0)=hiTitles(0)+1
Next
;frame rating
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
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
	
	;PORTAL
    gotim=gotim+1
	If gotim>100 
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Then go=1
	EndIf 
	
    ;REACTION
	If gotim=0 
	 PlaySound sPaper
	 If gamBank(slot)=<1000 Then PlaySound sCrowd(8)
	 If gamBank(slot)>1000 And gamBank(slot)=<10000 Then PlaySound sCrowd(7)
	 If gamBank(slot)>10000 And gamBank(slot)=<100000 Then PlaySound sCrowd(4)
	 If gamBank(slot)>100000 And gamBank(slot)=<250000 Then PlaySound sCrowd(2)
	 If gamBank(slot)>250000 Then PlaySound sCrowd(6) : PlaySound sCrowd(9)
	EndIf
	
 UpdateWorld
 Next 
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gFed(fed),rX#(400),rY#(65)  
 ;show profiles
 If gotim>10 Then DrawProfile(gamChar(slot),-1,-1)
 ;get context
 g=GetGender(char)
 ;CONSTRUCT NEWSPAPER
 If gotim>0
  ;images
  x=rX#(400) : y=rY#(370)
  DrawImage gNewspaper,x,y
  DrawImage charPhoto(gamChar(slot)),x+125,y+30
  ;small print
  SetFont font(0) : Color 110,110,110
  Text x-268,y-107,"The Nation's #1 Newspaper",0,1
  Text x+40,y-107,textWeek$(gamWeek(slot))+" of "+textMonth$(gamMonth(slot))+" "+gamYear(slot),1,1
  Text x+235,y-107,"50 Cents",0,1
  ;headline
  SetFont fontNews(3) : Color 0,0,0
  Text x+5,y-65,"GAME OVER",1,1
  ;career summary
  SetFont fontNews(2) : Color 0,0,0
  Text x+5,y+125,"After "+hiExperience(0)+" weeks, "+charName$(gamChar(slot))+" has retired from",1,1
  Text x+5,y+150,"the wrestling business with a fortune of $"+GetFigure$(gamBank(slot))+".",1,1
  Text x+5,y+175,He$(g)+" won "+hiSuccess(0)+"% of "+Lower$(His$(g))+" matches and held "+hiTitles(0)+" titles...",1,1
  ;prompt
  SetFont font(1)
  If gotim>100 Then Outline(">>> CLICK or ESCAPE to proceed >>>",x,y+210,130,130,130,255,255,255)
 EndIf
 ;cursor
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
PlaySound sMenuGo
;find hall of fame slot
hiInduct=0
For cyc=1 To 10
 If hiChar(cyc)=0 Then hiInduct=cyc
Next
If hiInduct=0
 hi=999999999 : loser=0
 For cyc=1 To 10
  If hiBank(cyc)<hi Then hi=hiBank(cyc) : loser=cyc
 Next
 hiInduct=loser
EndIf
;transfer details
If hiInduct>0
 hiChar(hiInduct)=hiChar(0)
 hiName$(hiInduct)=hiName$(0)
 hiFed(hiInduct)=hiFed(0)
 hiTalent(hiInduct)=hiTalent(0)
 hiBank(hiInduct)=hiBank(0)
 hiExperience(hiInduct)=hiExperience(0)
 hiSuccess(hiInduct)=hiSuccess(0)
 hiTitles(hiInduct)=hiTitles(0)
EndIf
;move to hollywood
If charFed(gamChar(slot))=<7 And fedSize(8)<optFedLim Then Trade(gamChar(slot),8)
gamChar(slot)=0
hiChar(0)=0
;proceed
screen=23
End Function

;----------------------------------------------------------------------
;////////////////////// 20. WORLD LEADERS /////////////////////////////
;----------------------------------------------------------------------
Function WorldLeaders()
;initiate list
fed=0
GetRankings(1)
;frame ratings
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
go=0 : gotim=0 : keytim=10 : page=1
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
	flashTim=flashTim+1
	If flashTim>70 Then flashTim=0 
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 If KeyDown(1) Or KeyDown(28) Then go=-1 
	 If MouseDown(1) And foc=3 Then go=-1
	EndIf
	
	;hotspots
	foc=0 
	If MouseX()>rX#(270) And MouseX()<rX#(530) And MouseY()>rY#(15) And MouseY()<rY#(105) Then foc=1
	If MouseX()>rX#(150) And MouseX()<rX#(650) And MouseY()>rY#(140) And MouseY()<rY#(535) Then foc=2
	
	;CONFIGURE 
	oldPage=page : oldFed=fed
	If gotim>20 And keytim=0
	 ;browse promotions
	 If (foc=1 And MouseDown(2)) Or KeyDown(200) Or JoyYDir()=-1 Then fed=fed-1 : PlaySound sMenuBrowse : keytim=6
	 If (foc=1 And MouseDown(1)) Or KeyDown(208) Or JoyYDir()=1 Then fed=fed+1 : PlaySound sMenuBrowse : keytim=6
	 ;change category
	 If (foc=2 And MouseDown(2)) Or KeyDown(203) Or JoyXDir()=-1 Then page=page-1 : PlaySound sMenuBrowse : keytim=6
	 If (foc=2 And MouseDown(1)) Or KeyDown(205) Or JoyXDir()=1 Then page=page+1 : PlaySound sMenuBrowse : keytim=6  
	EndIf
	;limits
	If fed<0 Then fed=7
	If fed>7 Then fed=0
	If page<1 Then page=13
	If page>13 Then page=1
	
	;UPDATE LIST
	If fed<>oldFed Or page<>oldPage 
	 GetRankings(page)
    EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 If fed=0 Then DrawImage gLogo(2),rX#(400),rY#(65)
 If fed>0 Then DrawImage gFed(fed),rX#(400),rY#(65)
 ;RANKINGS
 x=170 : y=170
 For count=1 To 10
  char=chartChar(count)
  ;photo
  If char>0
   If count=1 Then DrawImage charPhoto(char),rX#(x),rY#(y)-40 
   If count>1 Then DrawImageRect charPhoto(char),rX#(x),rY#(y)+24,0,0,70,52
  EndIf
  ;position
  If count=1 Then SetFont font(4) Else SetFont font(3)
  If char=gamChar(slot) Then r=255 : g=Rnd(130,220) : b=100 Else r=255 : g=180 : b=80
  Outline(count+".",rX#(x+35),rY#(y),0,0,0,r,g,b)
  ;character
  SetFont font(2)
  If char>0 Then namer$=charName$(char) Else namer$="-"
  If char=gamChar(slot) Then r=255 : g=Rnd(130,220) : b=100 Else r=245 : g=245 : b=255
  OutlineStraight(namer$,rX#(x+55),rY#(y),0,0,0,r,g,b)
  ;promotion
  SetFont font(1) 
  r=120 : g=120 : b=120 : outR=0 : outG=0 : outB=0
  If charFed(char)=1 Then outR=80 : outG=0 : outB=0
  If charFed(char)=2 Then outR=0 : outG=0 : outB=80
  If charFed(char)=3 Then outR=100 : outG=80 : outB=30
  If charFed(char)=4 Then outR=120 : outG=40 : outB=0
  If charFed(char)=5 Then outR=0 : outG=80 : outB=0
  If charFed(char)=6 Then outR=110 : outG=10 : outB=50
  If charFed(char)=7 Then outR=40 : outG=40 : outB=40
  If char=gamChar(slot) Then r=255 : g=Rnd(130,220) : b=100 : outR=0 : outG=0 : outB=0
  If char>0 Then namer$=fedName$(charFed(char)) Else namer$="-"
  Outline(namer$,rX#(x+308),rY#(y),outR,outG,outB,r,g,b)
  ;value
  SetFont font(2)
  If page=1 Then namer$=GetValue(char)+"%" : header$="Best Overall Wrestlers"
  If page=2 Then namer$=charPop(char)+"%" : header$="Most Popular Wrestlers"
  If page=3 Then namer$=charStr(char)+"%" : header$="Strongest Wrestlers"
  If page=4 Then namer$=charSkl(char)+"%" : header$="Most Skillful Wrestlers"
  If page=5 Then namer$=charAgl(char)+"%" : header$="Most Agile Wrestlers"
  If page=6 Then namer$=charStam(char)+"%" : header$="Fittest Wrestlers"
  If page=7 Then namer$=charTough(char)+"%" : header$="Toughest Wrestlers"
  If page=8 Then namer$=charAtt(char)+"%" : header$="Most Reputable Wrestlers"
  If page=9 Then namer$=charHap(char)+"%" : header$="Happiest Wrestlers"
  If page=10 Then namer$=charAge(char)+" years" : header$="Youngest Wrestlers"
  If page=11 Then namer$=charAge(char)+" years" : header$="Oldest Wrestlers"
  If page=12 Then namer$=charContract(char)+" weeks" : header$="Most Committed Wrestlers"
  If page=13 Then namer$="$"+GetFigure$(charSalary(char)) : header$="Highest Paid Wrestlers"
  If char=0 Then namer$=""
  If char=gamChar(slot) Then r=255 : g=Rnd(130,220) : b=100 Else r=220 : g=150 : b=255
  Outline(namer$,rX#(x+436),rY#(y),100,50,100,r,g,b)
  y=y+37
 Next 
 ;header
 SetFont font(2)
 Outline(header$,rX#(400),rY#(127),100,75,50,250,230,110)
 ;exit button
 SetFont font(1)
 DrawOption(3,400,550,"<<< EXIT <<<","",0,0)
 ;inst
 If foc=1 And flashTim>20
  Outline("CLICK TO BROWSE",rX#(240),rY#(50),0,0,0,85,85,85)
  Outline("ROSTERS",rX#(240),rY#(70),0,0,0,85,85,85) 
  Outline("CLICK TO BROWSE",rX#(560),rY#(50),0,0,0,85,85,85)
  Outline("ROSTERS",rX#(560),rY#(70),0,0,0,85,85,85) 
 EndIf
 If foc=2 And flashTim>20
  Outline("CLICK TO BROWSE",rX#(80),rY#(310),0,0,0,85,85,85)
  Outline("CATEGORIES",rX#(80),rY#(330),0,0,0,85,85,85) 
  Outline("CLICK TO BROWSE",rX#(720),rY#(310),0,0,0,85,85,85)
  Outline("CATEGORIES",rX#(720),rY#(330),0,0,0,85,85,85) 
 EndIf
 Outline("UP/DOWN=Browse rosters, LEFT/RIGHT=Browse categories, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor 
 If foc>0 And foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack
screen=15
End Function

;----------------------------------------------------------------------
;/////////////////////// 23. HALL OF FAME /////////////////////////////
;----------------------------------------------------------------------
Function HallOfFame()
;initiate list
If hiInduct>0 Then PlaySound sCrowd(9)
CareerRankings(1)
;frame ratings
period=1000/FPS
time=MilliSecs()-period
;MAIN LOOP
go=0 : gotim=0 : keytim=10 : page=1
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
	flashTim=flashTim+1
	If flashTim>70 Then flashTim=0 
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 If KeyDown(1) Or KeyDown(28) Then go=-1 
	 If MouseDown(1) And foc=3 Then go=-1
	EndIf
	
	;hotspots
	foc=0 
	If MouseX()>rX#(150) And MouseX()<rX#(650) And MouseY()>rY#(140) And MouseY()<rY#(535) Then foc=2
	
	;CONFIGURE 
	oldPage=page : oldFed=fed
	If gotim>20 And keytim=0
	 ;change category
	 If (foc=2 And MouseDown(2)) Or KeyDown(203) Or JoyXDir()=-1 Then page=page-1 : PlaySound sMenuBrowse : keytim=6
	 If (foc=2 And MouseDown(1)) Or KeyDown(205) Or JoyXDir()=1 Then page=page+1 : PlaySound sMenuBrowse : keytim=6  
	 ;reset entries
	 If KeyDown(29) And KeyDown(19)
	  PlaySound sSmashWire : keytim=10 
	  For count=1 To 10
       hiChar(count)=Rnd(1,no_chars)
       hiName$(count)=charName$(hiChar(count))
       hiFed(count)=charFed(hiChar(count))
       hiBank(count)=count*100
       hiExperience(count)=count
       hiSuccess(count)=count
       hiTitles(count)=0
       hiTalent(count)=count*5
      Next 
	 EndIf
	EndIf
	;limits
	If page<1 Then page=5
	If page>5 Then page=1
	
	;UPDATE LIST
	If fed<>oldFed Or page<>oldPage 
	 CareerRankings(page)
    EndIf
	
 UpdateWorld
 Next
 RenderWorld tween#

 ;DISPLAY
 TileImage gTile
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;RANKINGS
 x=165 : y=170
 For count=1 To 10
  cyc=chartChar(count)
  ;photo
  If hiChar(cyc)>0
   If count=1 Then DrawImage charPhoto(hiChar(cyc)),rX#(x),rY#(y)-40 
   If count>1 Then DrawImageRect charPhoto(hiChar(cyc)),rX#(x),rY#(y)+24,0,0,70,52
  EndIf
  ;position
  If count=1 Then SetFont font(4) Else SetFont font(3)
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(130,220) : b=100 Else r=255 : g=180 : b=80
  Outline(count+".",rX#(x+35),rY#(y),0,0,0,r,g,b)
  ;character
  SetFont font(2)
  If hiChar(cyc)>0 Then namer$=hiName$(cyc) Else namer$="-"
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(130,220) : b=100 Else r=245 : g=245 : b=255
  OutlineStraight(namer$,rX#(x+55),rY#(y),0,0,0,r,g,b)
  ;promotion
  SetFont font(1) 
  r=120 : g=120 : b=120 : outR=0 : outG=0 : outB=0
  If hiFed(cyc)=1 Then outR=80 : outG=0 : outB=0
  If hiFed(cyc)=2 Then outR=0 : outG=0 : outB=80
  If hiFed(cyc)=3 Then outR=100 : outG=80 : outB=30
  If hiFed(cyc)=4 Then outR=120 : outG=40 : outB=0
  If hiFed(cyc)=5 Then outR=0 : outG=80 : outB=0
  If hiFed(cyc)=6 Then outR=110 : outG=10 : outB=50
  If hiFed(cyc)=>7 Then outR=40 : outG=40 : outB=40
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(130,220) : b=100 : outR=0 : outG=0 : outB=0
  If hiChar(cyc)>0 Then namer$=fedName$(hiFed(cyc)) Else namer$="-"
  Outline(namer$,rX#(x+305),rY#(y),outR,outG,outB,r,g,b)
  ;value
  SetFont font(2)
  If page=1 Then namer$="$"+GetFigure$(hiBank(cyc)) : header$="Wealthiest Careers"
  If page=2 Then namer$=hiExperience(cyc)+" weeks" : header$="Longest Careers"
  If page=3 Then namer$=hiSuccess(cyc)+"% wins" : header$="Most Successful Careers"
  If page=4 Then namer$=hiTitles(cyc)+" titles" : header$="Most Decorated Careers"
  If page=5 Then namer$=hiTalent(cyc)+"%" : header$="Most Talented Careers"
  If hiChar(cyc)=0 Then namer$=""
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(130,220) : b=100 Else r=220 : g=150 : b=255
  Outline(namer$,rX#(x+430),rY#(y),100,50,100,r,g,b)
  y=y+37
 Next 
 ;header
 SetFont font(2)
 Outline(header$,rX#(400),rY#(127),100,75,50,250,230,110)
 ;exit button
 SetFont font(1)
 DrawOption(3,400,550,"<<< EXIT <<<","",0,0)
 ;inst
 If foc=2 And flashTim>20
  Outline("CLICK TO BROWSE",rX#(80),rY#(310),0,0,0,85,85,85)
  Outline("CATEGORIES",rX#(80),rY#(330),0,0,0,85,85,85) 
  Outline("CLICK TO BROWSE",rX#(720),rY#(310),0,0,0,85,85,85)
  Outline("CATEGORIES",rX#(720),rY#(330),0,0,0,85,85,85) 
 EndIf
 Outline("LEFT/RIGHT=Browse categories, ESC=Exit",rX#(400),rY#(580),0,0,0,85,85,85)
 ;cursor 
 If foc>0 And foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack
screen=21
If hiInduct>0 Then screen=6
hiInduct=0
End Function

;-------------------------------------------------------------------
;///////////////////////// RELATED FUNCTIONS ///////////////////////
;-------------------------------------------------------------------

;GET CHARACTER RANKINGS
Function GetRankings(category)
 ;reset checkers
 For count=1 To 10
  chartChar(count)=0
 Next
 For count=1 To no_chars
  charRanked(count)=0
 Next
 ;find top 10
 For finder=1 To 10
  hi=-1 : leader=0
  If category=10 Then hi=999
  If fed>0 Then range=fedSize(fed) Else range=no_chars
  For count=1 To range
   If fed>0 Then char=fedRoster(fed,count) Else char=count
   If fed=0 And charFed(char)=>7 Then included=0 Else included=1
   If charRanked(char)=0 And charFed(char)=<7 And included=1
    If category=1 And GetValue(char)>hi Then hi=GetValue(char) : leader=char
    If category=2 And charPop(char)>hi Then hi=charPop(char) : leader=char
    If category=3 And charStr(char)>hi Then hi=charStr(char) : leader=char
    If category=4 And charSkl(char)>hi Then hi=charSkl(char) : leader=char
    If category=5 And charAgl(char)>hi Then hi=charAgl(char) : leader=char
    If category=6 And charStam(char)>hi Then hi=charStam(char) : leader=char
    If category=7 And charTough(char)>hi Then hi=charTough(char) : leader=char
    If category=8 And charAtt(char)>hi Then hi=charAtt(char) : leader=char
    If category=9 And charHap(char)>hi Then hi=charHap(char) : leader=char
    If category=10 And charAge(char)<hi Then hi=charAge(char) : leader=char
    If category=11 And charAge(char)>hi Then hi=charAge(char) : leader=char
    If category=12 And charContract(char)>hi Then hi=charContract(char) : leader=char
    If category=13 And charSalary(char)>hi Then hi=charSalary(char) : leader=char
   EndIf
  Next
  chartChar(finder)=leader : charRanked(leader)=finder
 Next
End Function

;GET CAREER RANKINGS
Function CareerRankings(category)
 ;reset checkers
 For count=1 To 10
  chartChar(count)=0
 Next
 For count=1 To 10
  charRanked(count)=0
 Next
 ;find top 10
 For finder=1 To 10
  hi=-1 : leader=0
  For cyc=1 To 10
   If hiChar(cyc)>0 And charRanked(cyc)=0
    If category=1 And hiBank(cyc)>hi Then hi=hiBank(cyc) : leader=cyc
    If category=2 And hiExperience(cyc)>hi Then hi=hiExperience(cyc) : leader=cyc
    If category=3 And hiSuccess(cyc)>hi Then hi=hiSuccess(cyc) : leader=cyc
    If category=4 And hiTitles(cyc)>hi Then hi=hiTitles(cyc) : leader=cyc
    If category=5 And hiTalent(cyc)>hi Then hi=hiTalent(cyc) : leader=cyc
   EndIf
  Next
  chartChar(finder)=leader : charRanked(leader)=finder
 Next
End Function

;COUNT EXPERIENCE
Function CountExperience(cyc)
 value=0
 For count=1 To 7
  value=value+gamExperience(cyc,count)
 Next
 Return value
End Function

;COUNT MATCHES
Function CountMatches(cyc)
 value=0
 For count=1 To 7
  value=value+gamMatches(cyc,count)
 Next
 Return value
End Function

;COUNT WINS
Function CountWins(cyc)
 value=0
 For count=1 To 7
  value=value+gamWins(cyc,count)
 Next
 Return value
End Function