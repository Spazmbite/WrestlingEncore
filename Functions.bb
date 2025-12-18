;//////////////////////////////////////////////////////////////////////////////
;--------------------------- UNIVERSAL FUNCTIONS ------------------------------
;//////////////////////////////////////////////////////////////////////////////

;PRODUCE VARIANT SOUND
Function ProduceSound(entity,sound,pitch,vol#)
 ;fluctuate pitch
 range1=pitch-(pitch/8)
 range2=pitch+(pitch/24)
 pitcher=Rnd(range1,range2)
 ;override pitch changes
 If pitch=0 Or sound=sCount(1) Or sound=sCount(2) Or sound=sCount(3) Or sound=sBreak Or sound=sDQ
  pitcher=pitch
 EndIf
 ;fluctuate volume
 If vol#=0 Then vol#=Rnd(0.4,1.2)
 ;deliver sound
 If sound>0
  If pitch>0 Then SoundPitch sound,pitcher
  SoundVolume sound,vol#
  If optSound>0 And entity>0 Then EmitSound sound,entity 
  If optSound>0 And entity=0 Then EmitSound sound,cam
  If optSound=0 Then PlaySound sound
  ;reset
  If pitch>0 Then SoundPitch sound,pitch
  SoundVolume sound,1
 EndIf
End Function

;---------------------------------------------------------------------------
;///////////////////////////// GRAPHICAL ///////////////////////////////////
;---------------------------------------------------------------------------

;OUTLINE
Function Outline(script$,x,y,r1,g1,b1,r2,g2,b2)
 ;outline
 If r1<>r2 Or g1<>g2 Or b1<>b2
  Color r1,g1,b1
  Text x+1,y,script$,1,1
  Text x-1,y,script$,1,1
  Text x,y+1,script$,1,1
  Text x,y-1,script$,1,1
 EndIf
 ;core
 Color r2,g2,b2
 Text x,y,script$,1,1
End Function

;OUTLINE STRAIGHT
Function OutlineStraight(script$,x,y,r1,g1,b1,r2,g2,b2)
 ;outline
 If r1<>r2 Or g1<>g2 Or b1<>b2
  Color r1,g1,b1
  Text x+1,y,script$,0,1
  Text x-1,y,script$,0,1
  Text x,y+1,script$,0,1
  Text x,y-1,script$,0,1
 EndIf
 ;core
 Color r2,g2,b2
 Text x,y,script$,0,1
End Function

;PLOT BOLD LINE
Function DrawLine(startX,startY,endX,endY)
 Color 0,0,0
 Line startX-1,startY,endX-1,endY
 Line startX+1,startY,endX+1,endY
 Line startX,startY-1,endX,endY-1 
 Line startX,startY+1,endX,endY+1
 Color 255,180,0
 Line startX,startY,endX,endY
End Function

;RESOLUTION X FIX
Function rX#(x#)
 factor#=800.0/Float(GraphicsWidth())
 newX#=x#/factor#
 Return newX#
End Function

;RESOLUTION Y FIX
Function rY#(y#)
 factor#=600.0/Float(GraphicsHeight())
 newY#=y#/factor#
 Return newY#
End Function

;---------------------------------------------------------------------------
;//////////////////////////// MATHEMATICAL /////////////////////////////////
;---------------------------------------------------------------------------

;CALCULATE HEIGHT IN FEET & INCHES
Function GetHeight$(value)
 feet=value/12
 inches=value-(feet*12)
 ft$=(feet+5)+"'"
 in$=inches+"''"
 figure$=ft$+in$
 Return figure$
End Function

;CALCULATE 1'000'000 FIGURE
Function GetFigure$(value)
  minus=0
  If value<0 Then minus=1 : value=value-(value*2)
  ;get segments
  hundreds=0 : thousands=0 : millions=0
  millions=value/1000000
  If millions<0 Then millions=0
  thousands=(value-(millions*1000000))/1000
  If thousands<0 Then thousands=0
  hundreds=value-((millions*1000000)+(thousands*1000))
  If hundreds<0 Then hundreds=0
  ;piece together
  hun$=hundreds
  If thousands>0 Then tho$=thousands Else tho$=""
  If millions>0 Then mil$=millions Else mil$=""
  If (thousands>0 Or millions>0) Then hun$="'"+hundreds
  If (thousands>0 Or millions>0) And hundreds<100 Then hun$="'0"+hundreds
  If (thousands>0 Or millions>0) And hundreds<10 Then hun$="'00"+hundreds
  If millions>0 Then tho$="'"+thousands
  If millions>0 And thousands<100 Then tho$="'0"+thousands
  If millions>0 And thousands<10 Then tho$="'00"+thousands
  ;return
  If minus=0 Then figure$=mil$+tho$+hun$ 
  If minus=1 Then figure$="-"+mil$+tho$+hun$
  Return figure$
End Function

;PURSUE FIGURE
Function PursueFigure(current,target)
 ;working values
 differ=target-current
 value=0
 ;negative values
 If differ<0 Then value=-1
 If differ=<10 Then value=-10
 If differ=<100 Then value=-100
 If differ=<-500 Then value=-100
 If differ=<-5000 Then value=-1000
 If differ=<-50000 Then value=-10000
 If differ=<-500000 Then value=-100000
 If differ=<-5000000 Then value=-1000000
 ;positive values
 If differ>0 Then value=1
 If differ=>10 Then value=10
 If differ=>100 Then value=100
 If differ=>500 Then value=100
 If differ=>5000 Then value=1000
 If differ=>50000 Then value=10000
 If differ=>500000 Then value=100000
 If differ=>5000000 Then value=1000000
 Return value
End Function

;TRANSLATE NUMBER INTO DIGITS
Function Dig$(value,degree)
 digits$=value
 If value<degree Then digits$="0"+digits$
 If value<degree/10 Then digits$="0"+digits$
 If value=0 And degree=10 Then digits$="00"
 If value=0 And degree=100 Then digits$="000"
 Return digits$
End Function

;GET FIGURE TO NEAREST ???
Function RoundOff(value,degree)
 floater#=value/degree
 inty=Int(floater#)
 returner=inty*degree
 Return returner
End Function 

;CLEAN GIVEN ANGLE
Function CleanAngle#(angle#)
 its=0
 Repeat
  If angle#<0 Then angle#=angle#+360
  If angle#>360 Then angle#=angle#-360
  its=its+1
 Until (angle#=>0 And angle#=<360) Or its>100
 Return angle#
End Function

;FIND BEST ANGLE ROUTE
Function ReachAngle#(sA#,tA#,speed#)
 ;pre-empt 360 conflict
 If tA#=0 And sA#>180 Then tA#=360
 If tA#=360 And sA#<180 Then tA#=0
 ;standard logic
 If sA#<tA# Then value#=speed# Else value#=-speed#
 If sA#>270 And tA#<90 Then value#=speed#
 If sA#<90 And tA#>270 Then value#=-speed#
 ;post 360 conflict
 If sA#=0 And tA#>180 Then value#=-speed#
 If sA#=360 And tA#<180 Then value#=speed#
 Return value#
End Function

;SATISFIED TARGET ANGLE?
Function SatisfiedAngle(sA#,tA#,range)
 value=0
 ;standard logic
 If sA#>tA#-range And sA#<tA#+range Then value=1
 ;360 variations
 If tA#=360 And sA#>0-range And sA#<0+range Then value=1
 If tA#=0 And sA#>360-range And sA#<360+range Then value=1
 Return value
End Function

;CALCULATE DIFFERENCE (between any 2 numbers)
Function GetDiff#(source#,dest#)
 diff#=dest#-source#
 If diff#<0 Then diff#=MakePositive#(diff#)
 Return diff#
End Function

;FORCE MINUS INTO POSITIVE
Function MakePositive#(value#)
 If value#<0
  positive#=value#-(value#*2)
 Else
  positive#=value#
 EndIf
 Return positive#
End Function

;CALCULATE MIDDLE VALUE
Function GetCentre#(valueA#,valueB#)
 centre#=valueA#+((valueB#-valueA#)/2)
 Return centre#
End Function