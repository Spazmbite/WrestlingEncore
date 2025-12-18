;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING ENCORE: TEXTS ---------------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////
;------------------- KEY NAMES ------------------------
;//////////////////////////////////////////////////////
Dim Key$(255)
For count=0 To 255
 Key$(count)="?" 
Next
Key$(2)="1" : Key$(3)="2" : Key$(4)="3" : Key$(5)="4" : Key$(6)="5" : Key$(7)="6" : Key$(8)="7" : Key$(9)="8" : Key$(10)="9"
Key$(16)="Q" : Key$(17)="W" : Key$(18)="E" : Key$(19)="R" : Key$(20)="T" : Key$(21)="Y"
Key$(22)="U" : Key$(23)="I" : Key$(24)="O" : Key$(25)="P" : Key$(26)="[" : Key$(27)="]"
Key$(29)="Left Ctrl" : Key$(30)="A" : Key$(31)="S" : Key$(32)="D" : Key$(33)="F" : Key$(34)="G"
Key$(35)="H" : Key$(36)="J" : Key$(37)="K" : Key$(38)="L" : Key$(39)=";" : Key$(40)="'"
Key$(42)="Left Shift" : Key$(43)="/" : Key$(44)="Z" : Key$(45)="X" : Key$(46)="C" : Key$(47)="V"
Key$(48)="B" : Key$(49)="N" : Key$(50)="M" : Key$(51)="," : Key$(52)="." : Key$(53)="/"
Key$(54)="Right Shift" : Key$(56)="Left Alt" : Key$(157)="Right Ctrl" : Key$(184)="Right Alt"
Key$(57)="Space" : Key$(200)="Cursor Up" : Key$(208)="Cursor Down" 
Key$(203)="Cursor Left" : Key$(205)="Cursor Right"

;//////////////////////////////////////////////////////
;-------------------- STATUS --------------------------
;//////////////////////////////////////////////////////
;weeks
Dim textWeek$(4)
textWeek$(1)="1st week"
textWeek$(2)="2nd week"
textWeek$(3)="3rd week"
textWeek$(4)="4th week"
;months
Dim textMonth$(12)
textMonth$(1)="January"
textMonth$(2)="February"
textMonth$(3)="March"
textMonth$(4)="April"
textMonth$(5)="May"
textMonth$(6)="June"
textMonth$(7)="July"
textMonth$(8)="August"
textMonth$(9)="September"
textMonth$(10)="October"
textMonth$(11)="November"
textMonth$(12)="December"
;contract clauses
Dim textClause$(4,2)
textClause$(1,0)="No control"
textClause$(1,1)="Changes must be paid for"
textClause$(1,2)="Full control at no cost!"
textClause$(2,0)="Losses go unpaid"
textClause$(2,1)="Losses pay 50% less"
textClause$(2,2)="Unconditional pay!"
textClause$(3,0)="No cover"
textClause$(3,1)="50% cover"
textClause$(3,2)="100% cover!"
textClause$(4,0)="No assistance"
textClause$(4,1)="50% paid for"
textClause$(4,2)="100% paid for!"
;traing courses
Dim textTrainCourse$(6)
textTrainCourse$(0)="Rest"
textTrainCourse$(1)="Strength"
textTrainCourse$(2)="Skill"
textTrainCourse$(3)="Agility"
textTrainCourse$(4)="Stamina"
textTrainCourse$(5)="Toughness"
textTrainCourse$(6)="Everything"
;traing levels
Dim textTrainLevel$(3)
textTrainLevel$(0)="None"
textTrainLevel$(1)="Casual"
textTrainLevel$(2)="Average"
textTrainLevel$(3)="Intense"
;gender issues
Global g
Dim He$(1)
He$(0)="He"
He$(1)="She"
Dim Him$(1)
Him$(0)="him"
Him$(1)="her"
Dim His$(1)
His$(0)="His"
His$(1)="Her"
Dim Guy$(1)
Guy$(0)="guy"
Guy$(1)="gal"

;//////////////////////////////////////////////////////
;------------------- OPTIONS --------------------------
;//////////////////////////////////////////////////////
;on/off
Dim textOnOff$(1)
textOnOff$(0)="Off"
textOnOff$(1)="On"
;difficulty
Dim textLevel$(5)
textLevel$(0)="Very Easy"
textLevel$(1)="Easy"
textLevel$(2)="Average"
textLevel$(3)="Hard"
textLevel$(4)="Very Hard"
textLevel$(5)="Extremely Hard"
;match length
Dim textLength$(2)
textLength$(0)="Short"
textLength$(1)="Normal"
textLength$(2)="Long"
;game speed
Dim textSpeed$(3)
textSpeed$(0)="Normal"
textSpeed$(1)="Swift"
textSpeed$(2)="Fast"
textSpeed$(3)="Turbo!"
;secondary characters
Dim textExtras$(2)
textExtras$(0)="Never"
textExtras$(1)="Only In Small Matches"
textExtras$(2)="Always"
;eliminated wrestlers
Dim textHide$(2)
textHide$(0)="Remain At Ringside"
textHide$(1)="Remove In Big Matches"
textHide$(2)="Always Remove"
;tag control
Dim textTagControl$(2)
textTagControl$(0)="Specific Wrestler"
textTagControl$(1)="Specific In Career"
textTagControl$(2)="Whole Team"
;resolution
Dim textResX$(5),textResY$(5)
textResX$(0)="320" : textResY$(0)="240"
textResX$(1)="640" : textResY$(1)="480"
textResX$(2)="800" : textResY$(2)="600"
textResX$(3)="1024" : textResY$(3)="768"
textResX$(4)="1280" : textResY$(4)="1024"
textResX$(5)="1280" : textResY$(5)="800"
;overhead labels
Dim textTags$(2)
textTags$(0)="None"
textTags$(1)="Transparent"
textTags$(2)="Solid"
;particle effects
Dim textFX$(3)
textFX$(0)="None"
textFX$(1)="Minimal"
textFX$(2)="Moderate"
textFX$(3)="Maximum"
;limb loss
Dim textLimbs$(2)
textLimbs$(0)="Impossible"
textLimbs$(1)="Rare"
textLimbs$(2)="Common"
;crowd/video animation
Dim textAnim$(2)
textAnim$(0)="Static"
textAnim$(1)="Animated"
;scenery ghosting
Dim textGhost$(2)
textGhost$(0)="Never"
textGhost$(1)="Only In Cage Matches"
textGhost$(2)="Wherever Necessary"

;//////////////////////////////////////////////////////
;------------------- MATCHES --------------------------
;//////////////////////////////////////////////////////
;match types
Dim textMatch$(20)
textMatch$(0)="Backstage Brawl"
textMatch$(1)="Confrontation"
textMatch$(2)="Traditional Match"
textMatch$(3)="Best Of Three Match"
textMatch$(4)="Iron Man Match"
textMatch$(5)="24/7 Challenge"
textMatch$(6)="Submission Match"
textMatch$(7)="Shoot Fight"
textMatch$(8)="Last Man Standing"
textMatch$(9)="First Blood Match"
textMatch$(10)="Triple Threat Match"
textMatch$(11)="Handicap Match"
textMatch$(12)="Tag Team Match"
textMatch$(13)="Team Battle"
textMatch$(14)="Royal Brawl"
textMatch$(15)="Battle Royal"
textMatch$(16)="Timed Battle Royal"
textMatch$(17)="Escape Challenge"
;match gimmicks
Dim textGimmick$(10)
textGimmick$(0)=""
textGimmick$(1)="Hardcore"
textGimmick$(2)="Steel Cage"
textGimmick$(3)="Barbed Wire"
textGimmick$(4)="Electrified"
textGimmick$(5)="Inferno"
textGimmick$(6)="Exploding"
textGimmick$(7)="Hall Of Mirrors"
textGimmick$(8)="Hair Vs Hair"
textGimmick$(9)="Loser Leaves"
textGimmick$(10)="Empty Arena"
;count/ignore
Dim textCount$(1)
textCount$(0)="Ignore"
textCount$(1)="Count"
;match type
Dim textAim$(5)
textAim$(0)="Aimless"
textAim$(1)="First Fall Wins"
textAim$(2)="Best Of Three"
textAim$(3)="Most Falls Wins"
textAim$(4)="Last Fall Wins"
textAim$(5)="Elimination"
;match rules
Dim textRules$(5)
textRules$(0)="Hardcore"
textRules$(1)="Lenient"
textRules$(2)="Strict"
;teams
Dim textTeams$(2)
textTeams$(0)="Individuals"
textTeams$(1)="Teams"
textTeams$(2)="Tag Teams"
;championships
Dim textChamps$(6)
textChamps$(0)="Ignore"
textChamps$(1)="Acknowledge"
textChamps$(2)="Crown New World"
textChamps$(3)="Crown New Inter"
textChamps$(4)="Crown New Tags"
textChamps$(5)="Hair vs Hair!"
textChamps$(6)="Loser Leaves Town!"
;cages
Dim textCage$(5)
textCage$(0)="None"
textCage$(1)="Steel Bars"
textCage$(2)="Blue Bars"
textCage$(3)="Black Bars"
textCage$(4)="Wire Mesh"
;rope types
Dim textRopeType$(5)
textRopeType$(0)="Normal"
textRopeType$(1)="Barbed Wire"
textRopeType$(2)="Electrified"
textRopeType$(3)="Flaming"
;item selections
Dim textSelection$(5)
textSelection$(0)="Random"
textSelection$(1)="Standard"
textSelection$(2)="One Of Each"
;item lay-out's
Dim textLayout$(5)
textLayout$(0)="Random"
textLayout$(1)="Standard"
textLayout$(2)="Inside Ring" 
textLayout$(3)="Surrounding Ring" 
textLayout$(4)="Backstage" 

;//////////////////////////////////////////////////////
;-------------------- ARENAS --------------------------
;//////////////////////////////////////////////////////
;presets
Dim textArena$(no_arenas)
textArena$(0)="Random Preset"
textArena$(1)="White Hall"
textArena$(2)="Wooden Hall"
textArena$(3)="Sandy Hall"
textArena$(4)="Plush Hall"
textArena$(5)="Pebble Hall"
textArena$(6)="Red Brick Hall"
textArena$(7)="Stone Hall"
textArena$(8)="Dark Hall"
textArena$(9)="Metal Hall"
textArena$(10)="Large Hall"
textArena$(11)="Star Stadium"
textArena$(12)="Fire Stadium"
textArena$(13)="Classic Stadium"
textArena$(14)="Spectacular Stadium"
textArena$(15)="Outdoor Concrete"
textArena$(16)="Outdoor Beach"
textArena$(17)="Outdoor Yard"
textArena$(18)="Random Hall"
textArena$(19)="Random Stadium"
;attendance
Dim textCrowd$(3)
textCrowd$(0)="Empty"
textCrowd$(1)="Small"
textCrowd$(2)="Half Full"
textCrowd$(3)="Sold Out"
;atmospherics
Dim textAtmos$(10)
textAtmos$(0)="None"
textAtmos$(1)="Black"
textAtmos$(2)="White"
textAtmos$(3)="Cream"
textAtmos$(4)="Daylight"
textAtmos$(5)="Purple"
textAtmos$(6)="Red"
textAtmos$(7)="Green"
textAtmos$(8)="Blue"
textAtmos$(9)="Random"
;lighting
Dim textLight$(no_lights)
textLight$(0)="Normal"
textLight$(1)="Bright"
textLight$(2)="Dim"
textLight$(3)="Dark"
textLight$(4)="Dark Lit"
textLight$(5)="Smooth Murkiness"
textLight$(6)="Fast Murkiness"
textLight$(7)="Smooth Disco"
textLight$(8)="Fast Disco"
textLight$(9)="Reds"
textLight$(10)="Greens"
textLight$(11)="Blues"
textLight$(12)="Golden"
textLight$(13)="Pink"
;ropes
Dim textRopes$(9)
textRopes$(0)="Novelty"
textRopes$(1)="Red, White, Blue"
textRopes$(2)="Black, White, Yellow"
textRopes$(3)="Yellow, Orange, Red"
textRopes$(4)="Green, White, Brown"
textRopes$(5)="Orange, White, Green"
textRopes$(6)="Red"
textRopes$(7)="Blue"
textRopes$(8)="Black"
textRopes$(9)="White"
;aprons
Dim textApron$(5)
textApron$(0)="Blank"
textApron$(1)="TV Show"
textApron$(2)="PPV Show"
textApron$(3)="Tribute Show"
textApron$(4)="Charity Show"
textApron$(5)="Inter-Promotional"
;canvases
Dim textCanvas$(5)
textCanvas$(1)="Grey"
textCanvas$(2)="Blue"
textCanvas$(3)="Cream"
textCanvas$(4)="Promotion Specific"
textCanvas$(5)="Wrestling MPire"
;mats
Dim textMat$(4)
textMat$(0)="None"
textMat$(1)="Blue"
textMat$(2)="Brown"
textMat$(3)="Black"
;cameras
Dim textCam$(20)
textCam$(0)="Manual (Y,H,I,J,K,L)"
textCam$(1)="Head Shot"
textCam$(2)="Close Follow"
textCam$(3)="Medium Follow"
textCam$(4)="Distant Follow"
textCam$(5)="Low Follow"
textCam$(6)="Angled Follow"
textCam$(7)="Sideways Follow"
textCam$(8)="Rear Follow"
textCam$(9)="Bird's Eye View"
textCam$(10)="Arena Extents"
textCam$(11)="Spectator's View"
textCam$(12)="Announcer's View"
textCam$(13)="Referee's View"
textCam$(14)="First Person"
textCam$(15)="Spontaneous"
textCam$(16)="Contain All"

;//////////////////////////////////////////////////////
;-------------------- EDITING -------------------------
;//////////////////////////////////////////////////////
;allegiance
Dim textHeel$(1)
textHeel$(0)="Face"
textHeel$(1)="Heel"
;eyes
Dim textEyes$(3)
textEyes$(1)="Angry"
textEyes$(2)="Neutral"
textEyes$(3)="Docile"
;model references
Dim textModel$(no_models)
textModel$(1)="Male_Slim"
textModel$(2)="Male_Slim_Pants"
textModel$(3)="Male_Slim_Baggy"
textModel$(4)="Male_Normal"
textModel$(5)="Male_Normal_Pants"
textModel$(6)="Male_Normal_Baggy"
textModel$(7)="Male_Jaw"
textModel$(8)="Male_Jaw_Baggy"
textModel$(9)="Male_Muscular"
textModel$(10)="Male_Muscular_Baggy"
textModel$(11)="Male_Chubby"
textModel$(12)="Male_Fat"
textModel$(13)="Male_Obese"
textModel$(14)="Female_Slim"
textModel$(15)="Female_Baggy"
textModel$(16)="Female_Skirt"
textModel$(17)="Female_Plump"
;build descriptions
Dim textBuild$(no_models)
textBuild$(1)="Slim (Tight)"
textBuild$(2)="Slim (Baggy Pants)"
textBuild$(3)="Slim (Baggy Top)"
textBuild$(4)="Normal (Tight)"
textBuild$(5)="Normal (Baggy Pants)"
textBuild$(6)="Normal (Baggy Top)"
textBuild$(7)="Big Jaw (Tight)"
textBuild$(8)="Big Jaw (Baggy)"
textBuild$(9)="Muscular (Tight)"
textBuild$(10)="Muscular (Baggy)"
textBuild$(11)="Chubby"
textBuild$(12)="Fat"
textBuild$(13)="Obese"
textBuild$(14)="Female (Tight)"
textBuild$(15)="Female (Baggy)"
textBuild$(16)="Female (Skirt)"
textBuild$(17)="Female (Plump)"
;hair styles
Dim textHair$(no_hstyles)
textHair$(0)="Bald"
textHair$(1)="Shaved"
textHair$(2)="Balding"
textHair$(3)="Receding"
textHair$(4)="Short"
textHair$(5)="Raised"
textHair$(6)="Quiff"
textHair$(7)="Fringe"
textHair$(8)="Thick"
textHair$(9)="Full"
textHair$(10)="Small Afro"
textHair$(11)="Big Afro"
textHair$(12)="Spikey"
textHair$(13)="Mohican"
textHair$(14)="Corn Rows"
textHair$(15)="Balding w/Ponytail"
textHair$(16)="Receding w/Ponytail"
textHair$(17)="Short w/Ponytail"
textHair$(18)="Raised w/Ponytail"
textHair$(19)="Quiff w/Ponytail"
textHair$(20)="Fringe w/Ponytail"
textHair$(21)="Thick w/Ponytail"
textHair$(22)="Afro w/Ponytail"
textHair$(23)="Mohican w/Ponytail"
textHair$(24)="Rows w/Ponytail"
textHair$(25)="Balding w/Length"
textHair$(26)="Receding w/Length"
textHair$(27)="Short w/Length"
textHair$(28)="Raised w/Length"
textHair$(29)="Quiff w/Length"
textHair$(30)="Fringe w/Length"
textHair$(31)="Thick w/Length"
textHair$(32)="Afro w/Length"
textHair$(33)="Mohican w/Length"
;hair references
Dim hairFile$(15)
hairFile$(1)="Hair_Bald"
hairFile$(2)="Hair_Thin"
hairFile$(3)="Hair_Short"
hairFile$(4)="Hair_Raise"
hairFile$(5)="Hair_Quiff"
hairFile$(6)="Hair_Mop"
hairFile$(7)="Hair_Thick"
hairFile$(8)="Hair_Full"
hairFile$(9)="Hair_Curl"
hairFile$(10)="Hair_Afro"
hairFile$(11)="Hair_Spike"
hairFile$(12)="Hair_Punk"
hairFile$(13)="Hair_Rolls"
hairFile$(14)="Hair_Pony"
hairFile$(15)="Hair_Long"
;hats
Dim textHat$(no_hats)
textHat$(0)="None"
textHat$(1)="Headband"
textHat$(2)="Black Cap A"
textHat$(3)="Black Cap B"
textHat$(4)="Black Cap C"
textHat$(5)="Generic Cap A"
textHat$(6)="Generic Cap B"
textHat$(7)="Generic Cap C"
textHat$(8)="Black Hat A"
textHat$(9)="Black Hat B"
textHat$(10)="Generic Hat A"
textHat$(11)="Generic Hat B"
textHat$(12)="Helmet"
textHat$(13)="Horns"
;specs
Dim textSpecs$(no_specs)
textSpecs$(0)="None"
textSpecs$(1)="Spectacles"
textSpecs$(2)="Coloured Specs"
textSpecs$(3)="Coloured Shades"
textSpecs$(4)="Solid Shades"
textSpecs$(5)="Dark Shades"