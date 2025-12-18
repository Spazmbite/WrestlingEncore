;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: MEETINGS ------------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------
;///////////////// RISK NEGOTIATIONS /////////////////////
;---------------------------------------------------------
Function RiskNegotiations()
 ;contract renewal
 fed=0
 If charContract(gamChar(slot))=0 And charFed(gamChar(slot))=<6 And fedSize(7)<optFedLim Then fed=charFed(gamChar(slot))
 ;outside offers
 If fed=0
  ;assess chances
  chance=(90-GetValue(gamChar(slot)))/4
  If charContract(gamChar(slot))>0 And chance<charContract(gamChar(slot))*5 Then chance=charContract(gamChar(slot))*4
  If gamResult(slot,GetDate()-1)=1 Or charInjured(gamChar(slot))>0 Then chance=chance*2
  If chance<5 Then chance=5
  ;find matches
  randy=Rnd(0,chance)
  If randy=<1 
   its=0
   Repeat
    fed=Rnd(1,6) : satisfied=1
    randy=Rnd(0,3)
    If randy=0 And charPop(gamChar(slot))<fedPop(fed) Then satisfied=0
    If randy=1 And charAtt(gamChar(slot))<fedRep(fed) Then satisfied=0
    If randy=2 And gamExperience(slot,fed)>0 Then satisfied=0
    If fedSize(fed)>36 Then satisfied=0
    If fed=charFed(gamChar(slot)) Then satisfied=0
    its=its+1
    If its>100 Then fed=0 : satisfied=1
   Until satisfied=1
  EndIf
 EndIf
 ;proceed
 If fed>0 And fedBooker(fed)>0 Then negChar=fedBooker(fed) : screen=52 : Negotiations()
End Function

;------------------------------------------------------------------------------
;////////////////////// 52. CONTRACT NEGOTIATIONS /////////////////////////////
;------------------------------------------------------------------------------
Function Negotiations()
;get setting
Loader("Please Wait","Meeting "+charName$(negChar))
ChannelVolume chTheme,0.5
negSetting=1 : camFoc=2
fed=charFed(negChar)
PrepareMeeting()
;determine worth
worther=GetValue(gamChar(slot))-20
negWorth=worther*worther
If charFed(gamChar(slot))=7 Then negWorth=negWorth/2 ;half for unemployed
If charInjured(gamChar(slot))=>1 And charInjured(gamChar(slot))=<4 Then negWorth=negWorth-(negWorth/4) ;injury concerns
If charInjured(gamChar(slot))>4 Then negWorth=negWorth/2 ;major injury concerns
If gamChar(slot)=charEnemy(negChar) Then negWorth=negWorth-(negWorth/3) ;boss vendetta
If gamWorth(slot)<1000 And negWorth>gamWorth(slot)*2 Then negWorth=gamWorth(slot)*2 ;gradual increase (minor)
If gamWorth(slot)=>1000 And negWorth>gamWorth(slot)+(gamWorth(slot)/3) Then negWorth=gamWorth(slot)+(gamWorth(slot)/3) ;gradual increase (major)
If fed=2 Then negWorth=negWorth+(negWorth/5) ;All American bonus
If TitleHolder(gamChar(slot),1)>0 Then negWorth=negWorth+(negWorth/4) ;bonus for world champions
If TitleHolder(gamChar(slot),2)>0 Or TitleHolder(gamChar(slot),3)>0 Then negWorth=negWorth+(negWorth/6) ;bonus for other champions
negWorth=RoundOff(negWorth,100)
;initial contract
negChances=Rnd(1,8)
If gamChar(slot)=charEnemy(negChar) Then negChances=negChances/2
negPayOff=0 : negContract=20
negSalary=negWorth+((negWorth/50)*(negContract/2))
For count=1 To 3
 negClause(count)=0
Next
If fed=1 Then negClause(1)=1 ;image benefits
If fed=3 Then negContract=16
If fed=5 Then negClause(2)=1 ;performance benefit
If fed=4 Then negClause(3)=1 ;health benefit
;reset progress
negTim=0 : negStage=0
negVerdict=0
;frame rating
period=1000/FPS  
time=MilliSecs()-period
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
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
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0  
	
	;PORTAL
    gotim=gotim+1 : negTim=negTim+1 
    ;speed-ups
	If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1)
	 If negStage<>1 And negStage<>3 And keytim=0 Then negTim=negTim+50 : keytim=5 ;: PlaySound sMenuBrowse
	EndIf
	;leave
	If negStage=>4 And negTim>350 Then go=1
	
	;CONFIGURATION
	If gotim>20 And keytim=0 And (negStage=1 Or negStage=3)
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6
	 If foc>7 Then foc=0
	 If foc<0 Then foc=7 
	 ;adjust contract
	 If negStage=1 And foc=>1 And foc=<6
	  ;browse left
	  If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	   PlaySound sMenuBrowse : keytim=4
	   range=GetRanger(negPayOff)
	   If range<1000 Then range=1000
	   If foc=1 Then negPayOff=negPayOff-range
	   range=GetRanger(negSalary)
	   If foc=2 Then negSalary=negSalary-range
	   If foc=3 Then negContract=negContract-1
	   For count=1 To 3
	    If foc=3+count Then negClause(count)=negClause(count)-1 : keytim=8
	   Next
	  EndIf
	  ;browse right
	  If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	   PlaySound sMenuBrowse : keytim=4
	   range=GetRanger(negPayOff)
	   If range<1000 Then range=1000
	   If foc=1 Then negPayOff=negPayOff+range
	   range=GetRanger(negSalary)
	   If foc=2 Then negSalary=negSalary+range
	   If foc=3 Then negContract=negContract+1
	   For count=1 To 3
	    If foc=3+count Then negClause(count)=negClause(count)+1 : keytim=8
	   Next
	  EndIf
	 EndIf
	 ;submit proposal
	 If foc=7 Or KeyDown(28)
	  If KeyDown(28) Or JoyDown(1) Or MouseDown(1) 
	   PlaySound sMenuGo : keytim=10
	   If negStage=1 
	    negStage=2 : negTim=0 : foc=7
	    GetVerdict(gamChar(slot))
	   EndIf
	   If negStage=3 Then negStage=4 : negTim=0
	  EndIf
	 EndIf
	 ;cancel
	 If foc=0 Or KeyDown(1)
	  If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Or MouseDown(1) Then PlaySound sMenuBack : negStage=5 : negTim=0
	 EndIf
	EndIf
	
	;CHECK LIMITS
	If negPayOff<0 Then negPayOff=0
	If negPayOff>1000000 Then negPayOff=1000000 
	If negSalary<0 Then negSalary=0
	If negSalary>100000 Then negSalary=100000 
	If negContract<1 Then negContract=1
	If negContract>48 Then negContract=48 
	For count=1 To 3
	 If negClause(count)<0 Then negClause(count)=0
	 If negClause(count)>2 Then negClause(count)=2 
	Next  
	
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
 ;reset speech
 For cyc=1 To no_plays
  pSpeaking(cyc)=0
 Next
 ;initiate font
 SetFont font(3)
 If GraphicsWidth()=>1024 Then SetFont font(4)
 ;------------- INTRODUCTIONS -----------------
 ;renewal
 If negStage=0 And charFed(gamChar(slot))=fed
  If negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Hi, "+charName$(gamChar(slot))+". Your contract with us has",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("expired, so we need to talk about your future...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 And negTim<650 And negWorth<gamWorth(slot)
   Speak(2,0,1)
   Outline("To be honest, things aren't working out too well.",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("If you were to stay on, it would be with this deal:",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 And negTim<650 And negWorth=>gamWorth(slot)
   Speak(2,0,3)
   Outline("I must admit that we're pleased with your progress,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("so we'd be happy to keep you on with this deal:",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>675 Then negStage=1 : negTim=0 : keytim=10
 EndIf
 ;buy out
 If negStage=0 And charFed(gamChar(slot))<>fed
  If negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("Nice to meet you, "+charName$(gamChar(slot))+". We invited you here",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("today to discuss working for "+fedName$(fed)+"...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>350 And negTim<650 And negWorth<gamWorth(slot)
   Speak(2,0,1)
   Outline("You're not a priority for the company right now, but",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("we'd consider bringing you on board with this deal:",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf 
  If negTim>350 And negTim<650 And negWorth=>gamWorth(slot)
   Speak(2,0,3)
   Outline("We feel you'd make a great addition to the roster,",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("and we're prepared to make it happen with this deal:",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
  If negTim>675 Then negStage=1 : negTim=0 : keytim=10
 EndIf
 ;--------------- CONTRACT SETUP --------------------
 If negStage=1 Or negStage=3
  ;initiate
  SetFont font(1)
  x=400 : y=485
  If foc=0 Then Speak(1,0,1)
  If foc=>1 And foc=<6 Then Speak(1,0,2)
  If foc=7 Then Speak(1,0,3)
  Color 0,0,0 : Rect rX#(0),rY#(y)-25,rX#(800),rY#(300),1
  ;options
  DrawOption(0,x-250,525,"<<< WITHDRAW <<<","",0,0)
  DrawOption(1,x,y,"Pay-Off","$"+GetFigure$(negPayOff),1,2)
  DrawOption(2,x,y+30,"Salary","$"+GetFigure$(negSalary)+" per week",1,2)
  DrawOption(3,x,y+60,"Contract",negContract+" weeks",1,2)
  If negStage=3 Then namer$=">>> CONFIRM >>>" Else namer$=">>> PROPOSE >>>"
  DrawOption(7,x+250,525,namer$,"",0,0)
  ;smallprint hotspots
  If negStage=1 And MouseX()>rX#(x)-90 And MouseX()<rX#(x)+110
   For count=1 To 3
    If MouseY()>rY#(y+(70+(count*11)))-4 And MouseY()<rY#(y+(70+(count*11)))+5 Then foc=count+3
   Next
  EndIf
  ;smallprint options
  SetFont font(0)
  r=150 : g=80 : b=80
  If foc=4 Then DrawImage gSmallPrint,rX#(x)-80,rY#(y+83) : r=230 : g=0 : b=0
  OutlineStraight("Image Rights:",rX#(x)-69,rY#(y+81),0,0,0,r,g,b)
  r=150 : g=80 : b=80
  If foc=5 Then DrawImage gSmallPrint,rX#(x)-88,rY#(y+93) : r=230 : g=0 : b=0
  OutlineStraight("Perform Clause:",rX#(x)-80,rY#(y+92),0,0,0,r,g,b)
  r=150 : g=80 : b=80
  If foc=6 Then DrawImage gSmallPrint,rX#(x)-80,rY#(y+103) : r=230 : g=0 : b=0
  OutlineStraight("Health Policy:",rX#(x)-69,rY#(y+103),0,0,0,r,g,b)
  For count=1 To 3
   GetStatColour(negClause(count),1)
   OutlineStraight(textClause$(count,negClause(count)),rX#(x)+5,rY#(y+(70+(count*11))),0,0,0,ColorRed(),ColorGreen(),ColorBlue())
  Next
  ;stat reminder
  DrawProfile(gamChar(slot),-1,-1)
 EndIf
 ;------------------ VERDICTS -----------------------------
 If negStage=2 And negTim>5 Then camFoc=2
 ;acceptances
 If negStage=2 And negVerdict=0
  If negTim>25 And negTim<325
   Speak(2,0,3)
   Outline("OK, i'm happy to draw up that contract for you!",rX#(400),rY#(520),0,50,0,150,250,150)
   Outline("Just confirm the details and it's a done deal...",rX#(400),rY#(555),0,50,0,150,250,150)
  EndIf
  If negTim>350 Then negStage=3 : negTim=0 : keytim=10
 EndIf
 ;objections
 If negStage=2 And negVerdict=1
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("You're not getting $"+GetFigure$(negPayOff)+" now AND $"+GetFigure$(negSalary)+" per week!",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("At least one of those figures will have to come down...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=2
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("That $"+GetFigure$(negPayOff)+" pay-off sounds like a bonus to me,",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("and i'm not entirely sure that you deserve one...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=3
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("Hey, i'm happy to grease the wheels - but $"+GetFigure$(negPayOff),rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("strikes me as being a little too much!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=4
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("I'm sorry, but we can't afford to pay $"+GetFigure$(negPayOff)+" upfront.",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("You'll have to negotiate that as a weekly salary...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=10
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("No, i don't see you earning $"+GetFigure$(negSalary)+" per week!",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("The figure we offered you was perfectly reasonable...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=11
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("No, we can't give you $"+GetFigure$(negSalary)+" over "+negContract+" weeks.",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("That salary would require a bigger commitment...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=12
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("Why would you want to sign up for just "+negContract+" weeks?!",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("We can't invest in someone that's not committed...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=20
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("We have a distinct visual style at "+fedName$(fed)+".",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("We can't allow you to jeopardize that with your own gimmicks!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=21
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("What do YOU know about gimmicks?! We only let wrestlers",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("have creative control once they've proven themselves...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=22
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("You want us to pay for YOUR wardrobe?! We're happy for",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("you to express yourself, but we're not paying for it...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=23
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("We'd normally consider giving some creative control,",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("but you haven't got a reputation for being responsible!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=30
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("No, we think it's important that you have a performance",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("incentive until you've proven yourself in the ring...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=31
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("No, we're not giving you unconditional pay!",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("Very few people are THAT important to the company...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=32
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("No, we only give performance benefits to wrestlers",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("that approach this business with a good attitude!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=33
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("You might be talented enough to receive unconditional",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("pay, but your reputation doesn't quite match up!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=40
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("No, i don't think your style of wrestling is worthy",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("of health cover! You need an incentive to play safe...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=41
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("We're happy to give you assistance with injuries,",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("but very few wrestlers are worthy of full cover!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=42
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("Your wrestling is good enough to warrant health cover,",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("but i'm afraid we can't trust you to use it properly!",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 If negStage=2 And negVerdict=43
  If negTim>25 And negTim<325
   Speak(2,0,1) 
   Outline("We'd normally consider giving you full health cover,",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("but your reputation isn't exactly flawless...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=10
 EndIf
 ;----------------- ENDINGS -----------------------
 If negStage>3 And negTim>5 Then camFoc=2
 ;successful renewal
 If negStage=4 And charFed(gamChar(slot))=fed
  If negTim>25 And negTim<325
   Speak(2,0,3)
   If charged=0 Then PlaySound sCash : charged=1
   Outline("Good, i'm glad we could come to an arrangement!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("Let's make the next "+negContract+" weeks your best yet...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;successful trade
 If negStage=4 And charFed(gamChar(slot))<>fed
  If negTim>25 And negTim<325
   Speak(2,0,3)
   If charged=0 Then PlaySound sCash : charged=1
   Outline("Great! Welcome to "+fedName$(fed)+"!",rX#(400),rY#(520),50,50,50,250,250,250)
   Outline("I look forward to seeing you on our show...",rX#(400),rY#(555),50,50,50,250,250,250)
  EndIf
 EndIf
 ;renewal breakdown
 If negStage=5 And charFed(gamChar(slot))=fed 
  If negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("Well, we're obviously not on the same page anymore.",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("We wish you the best of luck finding work elsewhere...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
 EndIf
 ;trade breakdown
 If negStage=5 And charFed(gamChar(slot))<>fed 
  If negTim>25 And negTim<325
   Speak(2,0,1)
   Outline("OK, this meeting isn't going anywhere.",rX#(400),rY#(520),50,0,0,250,150,150)
   Outline("Let's just forget about it for the time being...",rX#(400),rY#(555),50,0,0,250,150,150)
  EndIf
 EndIf
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
;register new contract
If negStage=4
 If charFed(gamChar(slot))=fed And gamPromo(slot,GetDate())=0
  If charPop(gamChar(slot))=>fedPop(fed) Or TitleHolder(gamChar(slot),1) Then gamPromo(slot,GetDate())=66 ;done it all promo
 EndIf
 If charFed(gamChar(slot))<>fed
  fedBank(fed)=fedBank(fed)-negPayOff
  Trade(gamChar(slot),fed)
  ResetSchedule(GetDate(),1)
  If gamSchedule(slot,GetDate())>0 Then gamSchedule(slot,GetDate())=0
  If gamMatches(slot,fed)>0 Then gamPromo(slot,GetDate()+1)=65 ;returning promo
  gamFormat(slot)=1
 EndIf
 gamBank(slot)=gamBank(slot)+negPayOff
 gamWorth(slot)=negWorth
 charSalary(gamChar(slot))=negSalary
 charContract(gamChar(slot))=negContract
 For count=1 To 3
  gamClause(slot,count)=negClause(count)
 Next
 charAtt(gamChar(slot))=charAtt(gamChar(slot))+PursueValue(charAtt(gamChar(slot)),100,1)
 charHap(gamChar(slot))=charHap(gamChar(slot))+PursueValue(charHap(gamChar(slot)),100,1)
 CheckLimits(gamChar(slot))
EndIf
;released to school
If negStage=5 And charFed(gamChar(slot))=fed And fed=<6 
 ReleaseCharacter(gamChar(slot)) 
EndIf
;proceed
fed=charFed(gamChar(slot))
screen=15
End Function

;-------------------------------------------------------------------
;////////////////// GET CONTRACT VERDICT ///////////////////////////
;-------------------------------------------------------------------
Function GetVerdict(char)
 negVerdict=0
 ;assess bonus claim
 bonus=0
 If TitleHolder(char,0)>0 Or charPop(char)>fedPop(fed) Then bonus=negWorth*5
 ;assess pay-off allowance (on bonus)
 allowance=(negWorth*negContract)-(negSalary*negContract)
 If allowance<0 Then allowance=0
 ;assess pay-off Vs salary balance
 balance=1 : payer=negPayOff-bonus
 If payer<0 Then payer=0
 If payer+(negSalary*negContract)>negWorth*negContract Then balance=0
 ;pay-off objections
 If negVerdict=0 And balance=0 And negPayOff>0 And negSalary>0 And negSalary<negWorth Then negVerdict=1 ;balance isn't right
 If negVerdict=0 And bonus=0 And negPayOff>allowance Then negVerdict=2 ;don't deserve bonus
 If negVerdict=0 And (bonus>0 Or negSalary=0) And negPayOff>bonus+allowance Then negVerdict=3 ;bonus is too excessive
 If negVerdict=0 And negPayOff>0 And negPayOff>fedBank(fed)/2 Then negVerdict=4 ;can't afford bonus
 ;salary objections
 factor=negWorth+((negWorth/50)*negContract)
 If fed=3 And factor<negWorth Then factor=negWorth
 If negVerdict=0 And negSalary>factor And negContract=>20 Then negVerdict=10 ;salary is too excessive by any means
 If negVerdict=0 And negSalary>factor And negContract<20 Then negVerdict=11 ;salary is out of kilter with short contract
 If negVerdict=0 And negSalary>negWorth/2 And negContract=<4 And fed<>3 Then negVerdict=12 ;contract is far too short
 ;creative control
 If negVerdict=0 And negClause(1)=>1 And charPop(char)<fedPop(fed) And fed=6 Then negVerdict=20 ;strict dress code
 If negVerdict=0 And negClause(1)=>1 And charPop(char)<fedPop(fed)-(fedPop(fed)/4) And fed<>1 Then negVerdict=21 ;opinion isn't respected
 If negVerdict=0 And negClause(1)=>2 And (negPayOff>0 Or negSalary>5000 Or fedBank(fed)=<250000) Then negVerdict=22 ;can't afford to pay
 If negVerdict=0 And negClause(1)=>1 And charAtt(char)=<50 And fed<>1 Then negVerdict=23 ;aren't trusted
 ;performance clauses
 If negVerdict=0 And negClause(2)=>1 And GetValue(char)<fedPop(fed)-(fedPop(fed)/4) And negSalary>negWorth/2 And fed<>5 
  negVerdict=30 ;not talented enough for assistance
 EndIf
 claim=0
 If (fed=5 Or negSalary<negWorth/2) And GetValue(char)>fedPop(fed)-(fedPop(fed)/4) Then claim=1
 If GetValue(char)=>fedPop(fed) Or charPop(char)=>fedPop(fed) Then claim=1
 If negVerdict=0 And negClause(2)=>2 And claim=0 Then negVerdict=31 ;not talented enough for unconditional
 If negVerdict=0 And negClause(2)=>1 And charAtt(char)=<50 And negSalary>negWorth/2 And fed<>5 Then negVerdict=32 ;not reputable enough for assistance
 If negVerdict=0 And negClause(2)=2 And charAtt(char)=<75 Then negVerdict=33 ;not reputable enough for unconditional
 ;health policy
 risk=(charPop(char)+charSkl(char)+charTough(char))/3
 If charInjured(char)>0 Then risk=0
 If negVerdict=0 And negClause(3)=>1 And risk<fedRep(fed)-(fedRep(fed)/4) And negSalary>negWorth/2 And fed<>4 
  negVerdict=40 ;too vulnerable for anything!
 EndIf
 claim=0
 If (fed=4 Or negSalary<negWorth/2) And risk>fedRep(fed)-(fedRep(fed)/4) Then claim=1
 If risk=>fedRep(fed) Then claim=1
 If negVerdict=0 And negClause(3)=>2 And claim=0 Then negVerdict=41 ;too vulnerable for unconditional
 If negVerdict=0 And negClause(3)=>1 And charAtt(char)=<50 And negSalary>negWorth/2 And fed<>4 Then negVerdict=42 ;not reputable enough for assistance
 If negVerdict=0 And negClause(3)=2 And charAtt(char)=<75 Then negVerdict=43 ;not reputable enough for unconditional
End Function

;////////////////////////////////////////////////////////////////////////////
;-------------------------- RELATED FUNCTIONS -------------------------------
;////////////////////////////////////////////////////////////////////////////

;GET BEST VALUE CHANGER
Function GetRanger(amount)
 ;negative values
 If amount<0 Then value=-10
 If amount=<-1000 Then value=-100
 If amount=<-10000 Then value=-1000
 If amount=<-100000 Then value=-10000
 If amount=<-1000000 Then value=-50000
 If amount=<-10000000 Then value=-100000
 ;positive values
 If amount=>0 Then value=10
 If amount=>1000 Then value=100
 If amount=>10000 Then value=1000
 If amount=>100000 Then value=10000
 If amount=>1000000 Then value=50000
 If amount=>10000000 Then value=100000
 Return value
End Function

;LOSE CHANCES
Function PushLuck(degree)
 negChances=negChances-degree
 If negChances>0 Then negStage=1 Else negStage=5
End Function

;FIRING PROCESS
Function ReleaseCharacter(char)
 If fedSize(7)<optFedLim And charFed(char)<>7 
  ;universal effects
  Trade(char,7)
  charSalary(char)=0
  charContract(char)=0
  charAtt(char)=charAtt(char)+PursueValue(charAtt(char),30,1)
  charHap(char)=charHap(char)+PursueValue(charHap(char),30,0)
  ;career issues
  If CharBusy(char)>0
   ResetSchedule(GetDate(),1)
   If gamSchedule(slot,GetDate())>0 Then gamSchedule(slot,GetDate())=0
   gamFormat(slot)=1 
   gamClause(slot,1)=1 : gamClause(slot,2)=0 : gamClause(slot,3)=0
  EndIf
  CheckLimits(char)
 EndIf
End Function

;TRADE CHARACTER
Function Trade(char,target)
 source=charFed(char)
 ;find spot in current roster
 spot=0
 For count=1 To fedSize(source)
  If spot=0 And fedRoster(source,count)=char Then spot=count
 Next
 ;remove relationships
 charFriend(char)=0
 charEnemy(char)=0
 charManager(char)=0
 For count=1 To fedSize(source)
  If charFriend(fedRoster(source,count))=char Then charFriend(fedRoster(source,count))=0
  If charEnemy(fedRoster(source,count))=char Then charEnemy(fedRoster(source,count))=0
  If charManager(fedRoster(source,count))=char Then charManager(fedRoster(source,count))=0
 Next
 ;delete from source roster
 For count=spot To fedSize(source)-1
  fedRoster(source,count)=fedRoster(source,count+1)
 Next
 fedSize(source)=fedSize(source)-1
 ;add to target roster
 fedSize(target)=fedSize(target)+1
 fedRoster(target,fedSize(target))=char
 charFed(char)=target
 ;reassign any title belts
 randy=Rnd(1,fedSize(source))
 If fedBooker(source)=char Then fedBooker(source)=0;fedRoster(source,randy)
 If fedChampWorld(source)=char Then fedChampWorld(source)=0;fedRoster(source,randy)
 If fedChampInter(source)=char Then fedChampInter(source)=0;fedRoster(source,randy)
 If fedChampTag(source,1)=char Then fedChampTag(source,1)=0;fedRoster(source,randy)
 If fedChampTag(source,2)=char Then fedChampTag(source,1)=0;fedRoster(source,randy)
 ;update shirts to match new fed
 UpdateShirt(char)
 ;assign gimmick to school leavers
 If source=7 And target<>7 And CharBusy(char)=0
  charTheme(char)=Rnd(1,no_themes)
  charThemePitch(char)=DefaultPitch(charTheme(char))
  charLight(char)=Rnd(5,no_lights)
 EndIf
 ;remove gimmick for unemployed
 If source<>7 And target=7 Then charTheme(char)=0 : charThemePitch(char)=0 : charLight(char)=0
End Function

;UPDATE SHIRT TO MATCH NEW FED
Function UpdateShirt(char)
 If charFed(char)=>1 And charFed(char)=<6
  For coz=1 To 3
   If charBody(char,coz)=>65 And charBody(char,coz)=<70 Then charBody(char,coz)=64+charFed(char)
  Next
 EndIf
End Function

;GENERATE NEW CONTRACT
Function GetNewContract(char)
 ;get worth (salary)
 negWorth=GetWorth(char)
 If TitleHolder(char,1) Or char=fedBooker(charFed(char)) Then negWorth=negWorth+(negWorth/4)
 If TitleHolder(char,2) Or TitleHolder(char,3) Then negWorth=negWorth+(negWorth/6)  
 charSalary(char)=Rnd(negWorth,negWorth+(negWorth/5))
 charSalary(char)=RoundOff(charSalary(char),100)
 ;generate contract
 charContract(char)=Rnd(10,50)
 If char=fedBooker(charFed(char)) Then charContract(char)=Rnd(30,50)
 If (charFed(char)=7 Or charFed(char)=9) And char<>fedBooker(charFed(char))
  charSalary(char)=0
  charContract(char)=0
 EndIf
End Function

;CALCULATE CHARACTER VALUE (out of 700)
Function GetValue(char)
 ;drawing power
 valPop=charPop(char)-25
 If valPop<0 Then valPop=0
 valPop=valPop*5
 ;strength
 valStr=charStr(char)-25
 If valStr<0 Then valStr=0
 ;skill
 valSkl=charSkl(char)-25
 If valSkl<0 Then valSkl=0
 ;agility
 valAgl=charAgl(char)-25
 If valAgl<0 Then valAgl=0
 ;stamina
 valStam=charStam(char)-25
 If valStam<0 Then valStam=0
 ;toughness
 valTough=charTough(char)-25
 If valTough<0 Then valTough=0
 ;summarize
 value=valPop+valStr+valSkl+valAgl+valStam+valTough
 value=value/7
 If value>99 Then value=99
 If value<25 Then value=25
 Return value
End Function

;CALCULATE CHARACTER WORTH
Function GetWorth(char)
 worther=GetValue(char)-20
 value=worther*worther
 If charFed(char)=7 Then value=value/2
 If charFed(char)=8 Then value=value+(value/2)
 Return value
End Function