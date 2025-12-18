;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING ENCORE: DATA -----------------------------
;//////////////////////////////////////////////////////////////////////////////

;------------------------------------------------------------------------
;///////////////////////////// OPTIONS //////////////////////////////////
;------------------------------------------------------------------------

;SAVE OPTIONS
Function SaveOptions()
 fileout=WriteFile("Data/Options.dat")
  ;preferences
  WriteInt fileout,optLevel
  WriteInt fileout,optHealth
  WriteInt fileout,optSpeed 
  WriteInt fileout,optRef
  WriteInt fileout,optManagers
  WriteInt fileout,optExtras
  WriteInt fileout,optHideElim
  WriteInt fileout,optTagControl
  ;display
  WriteInt fileout,optGameRes
  WriteInt fileout,optMenuRes
  WriteInt fileout,optMeters
  WriteInt fileout,optTags
  WriteInt fileout,optFX
  WriteInt fileout,optGore
  WriteInt fileout,optLimbs
  WriteInt fileout,optCrowdAnim	
  WriteInt fileout,optVideo
  WriteInt fileout,optGhost
  WriteInt fileout,optAtmos
  ;key layout
  WriteInt fileout,keyAttack
  WriteInt fileout,keyGrab
  WriteInt fileout,keySwitch
  WriteInt fileout,keyRun
  WriteInt fileout,keyPick
  WriteInt fileout,keyTaunt
  ;button layout
  WriteInt fileout,buttAttack
  WriteInt fileout,buttGrab
  WriteInt fileout,buttSwitch
  WriteInt fileout,buttRun
  WriteInt fileout,buttPick
  WriteInt fileout,buttTaunt
 CloseFile(fileout)
End Function

;LOAD OPTIONS
Function LoadOptions()
 filein=ReadFile("Data/Options.dat")
  ;preferences
  optLevel=ReadInt(filein)
  optHealth=ReadInt(filein)
  optSpeed=ReadInt(filein)
  optRef=ReadInt(filein)
  optManagers=ReadInt(filein)
  optExtras=ReadInt(filein)
  optHideElim=ReadInt(filein)
  optTagControl=ReadInt(filein)
  ;display
  optGameRes=ReadInt(filein)
  optMenuRes=ReadInt(filein)
  optMeters=ReadInt(filein)
  optTags=ReadInt(filein)
  optFX=ReadInt(filein)
  optGore=ReadInt(filein)
  optLimbs=ReadInt(filein)
  optCrowdAnim=ReadInt(filein)
  optVideo=ReadInt(filein)
  optGhost=ReadInt(filein)
  optAtmos=ReadInt(filein)
  ;key layout
  keyAttack=ReadInt(filein)
  keyGrab=ReadInt(filein)
  keySwitch=ReadInt(filein)
  keyRun=ReadInt(filein)
  keyPick=ReadInt(filein)
  keyTaunt=ReadInt(filein)
  ;button layout
  buttAttack=ReadInt(filein)
  buttGrab=ReadInt(filein)
  buttSwitch=ReadInt(filein)
  buttRun=ReadInt(filein)
  buttPick=ReadInt(filein)
  buttTaunt=ReadInt(filein)
 CloseFile(filein)
End Function

;------------------------------------------------------------------------
;///////////////////////////// PROGRESS /////////////////////////////////
;------------------------------------------------------------------------

;SAVE PROGRESS
Function SaveProgress()
 fileout=WriteFile("Data/Progress.dat")
  For cyc=1 To 3
   ;status
   WriteInt fileout,gamChar(cyc)
   WriteInt fileout,gamFormat(cyc)
   WriteInt fileout,gamBank(cyc)
   WriteInt fileout,gamWorth(cyc)
   For count=1 To 3
    WriteInt fileout,gamClause(cyc,count)
   Next
   WriteInt fileout,gamExpenses(cyc)
   WriteInt fileout,gamVariable(cyc)
   WriteInt fileout,gamOldVariable(cyc)
   ;schedule
   WriteInt fileout,gamWeek(cyc)
   WriteInt fileout,gamMonth(cyc)
   WriteInt fileout,gamYear(cyc)
   For count=1 To 48
    WriteInt fileout,gamSchedule(cyc,count)
    WriteInt fileout,gamInterProm(cyc,count)
    WriteInt fileout,gamOpponent(cyc,count)
    WriteInt fileout,gamMatch(cyc,count)
    WriteInt fileout,gamGimmick(cyc,count)
    WriteInt fileout,gamPromo(cyc,count)
    WriteInt fileout,gamResult(cyc,count)
   Next
   ;missions
   WriteInt fileout,gamMission(cyc)
   WriteInt fileout,gamTarget(cyc)
   WriteInt fileout,gamDeadline(cyc)
   ;record
   For count=1 To 7
    WriteInt fileout,gamExperience(cyc,count)
    WriteInt fileout,gamMatches(cyc,count)
    WriteInt fileout,gamWins(cyc,count)
    WriteInt fileout,gamWorldTitles(cyc,count)
    WriteInt fileout,gamInterTitles(cyc,count)
    WriteInt fileout,gamTagTitles(cyc,count)
   Next
   ;venue
   WriteInt fileout,gamArenaPreset(cyc)
   WriteInt fileout,gamArenaCrowd(cyc)
   WriteInt fileout,gamArenaAtmos(cyc)
   WriteInt fileout,gamArenaLight(cyc)
   WriteInt fileout,gamArenaRopes(cyc)
   WriteInt fileout,gamArenaApron(cyc)
   WriteInt fileout,gamArenaCanvas(cyc)
   WriteInt fileout,gamArenaMat(cyc)
  Next
  ;hall of fame
  For count=1 To 10
   WriteInt fileout,hiChar(count)
   WriteString fileout,hiName$(count)
   WriteInt fileout,hiFed(count)
   WriteInt fileout,hiTalent(count)
   WriteInt fileout,hiBank(count)
   WriteInt fileout,hiExperience(count)
   WriteInt fileout,hiSuccess(count)
   WriteInt fileout,hiTitles(count)
  Next
  ;promo access
  For count=1 To 100
   WriteInt fileout,promoLocked(count)
  Next
 CloseFile(fileout)
End Function

;LOAD PROGRESS
Function LoadProgress()
 filein=ReadFile("Data/Progress.dat")
  For cyc=1 To 3
   ;status
   gamChar(cyc)=ReadInt(filein)
   gamFormat(cyc)=ReadInt(filein)
   gamBank(cyc)=ReadInt(filein)
   gamWorth(cyc)=ReadInt(filein)
   For count=1 To 3
    gamClause(cyc,count)=ReadInt(filein)
   Next
   gamExpenses(cyc)=ReadInt(filein)
   gamVariable(cyc)=ReadInt(filein)
   gamOldVariable(cyc)=ReadInt(filein)
   ;schedule
   gamWeek(cyc)=ReadInt(filein)
   gamMonth(cyc)=ReadInt(filein)
   gamYear(cyc)=ReadInt(filein)
   For count=1 To 48
    gamSchedule(cyc,count)=ReadInt(filein)
    gamInterProm(cyc,count)=ReadInt(filein)
    gamOpponent(cyc,count)=ReadInt(filein)
    gamMatch(cyc,count)=ReadInt(filein)
    gamGimmick(cyc,count)=ReadInt(filein)
    gamPromo(cyc,count)=ReadInt(filein)
    gamResult(cyc,count)=ReadInt(filein)
   Next
   ;missions
   gamMission(cyc)=ReadInt(filein)
   gamTarget(cyc)=ReadInt(filein)
   gamDeadline(cyc)=ReadInt(filein)
   ;record
   For count=1 To 7
    gamExperience(cyc,count)=ReadInt(filein)
    gamMatches(cyc,count)=ReadInt(filein)
    gamWins(cyc,count)=ReadInt(filein)
    gamWorldTitles(cyc,count)=ReadInt(filein)
    gamInterTitles(cyc,count)=ReadInt(filein)
    gamTagTitles(cyc,count)=ReadInt(filein)
   Next
   ;venue
   gamArenaPreset(cyc)=ReadInt(filein)
   gamArenaCrowd(cyc)=ReadInt(filein)
   gamArenaAtmos(cyc)=ReadInt(filein)
   gamArenaLight(cyc)=ReadInt(filein)
   gamArenaRopes(cyc)=ReadInt(filein)
   gamArenaApron(cyc)=ReadInt(filein)
   gamArenaCanvas(cyc)=ReadInt(filein)
   gamArenaMat(cyc)=ReadInt(filein)
  Next
  ;hall of fame
  For count=1 To 10
   hiChar(count)=ReadInt(filein)
   hiName$(count)=ReadString(filein)
   hiFed(count)=ReadInt(filein)
   hiTalent(count)=ReadInt(filein)
   hiBank(count)=ReadInt(filein)
   hiExperience(count)=ReadInt(filein)
   hiSuccess(count)=ReadInt(filein)
   hiTitles(count)=ReadInt(filein)
  Next
  ;promo access
  For count=1 To 100
   promoLocked(count)=ReadInt(filein)
  Next
 CloseFile(filein)
End Function

;------------------------------------------------------------------------
;////////////////////////////// WORLD ///////////////////////////////////
;------------------------------------------------------------------------

;SAVE WORLD
Function SaveWorld()
 fileout=WriteFile("Data/World.dat")
  WriteInt fileout,no_chars
  For cyc=1 To 9
   WriteString fileout,fedName$(cyc)
   WriteString fileout,fedShortName$(cyc)
   WriteInt fileout,fedBank(cyc)
   WriteInt fileout,fedPop(cyc)
   WriteInt fileout,fedRep(cyc)
   WriteInt fileout,fedSize(cyc)
   For count=1 To 50
    WriteInt fileout,fedRoster(cyc,count)
   Next
   WriteInt fileout,fedBooker(cyc)
   WriteInt fileout,fedChampWorld(cyc)
   WriteInt fileout,fedChampInter(cyc)
   For count=1 To 2
    WriteInt fileout,fedChampTag(cyc,count)
   Next
   WriteInt fileout,fedLocked(cyc)
  Next
 CloseFile(fileout)
End Function

;LOAD WORLD
Function LoadWorld()
 If screen=14 Then filer$="Data/Default/" Else filer$="Data/"
 filein=ReadFile(filer$+"World.dat")
  no_chars=ReadInt(filein)
  For cyc=1 To 9
   fedName$(cyc)=ReadString(filein)
   fedShortName$(cyc)=ReadString(filein)
   fedBank(cyc)=ReadInt(filein)
   fedPop(cyc)=ReadInt(filein)
   fedRep(cyc)=ReadInt(filein)
   fedSize(cyc)=ReadInt(filein)
   For count=1 To 50
    fedRoster(cyc,count)=ReadInt(filein)
   Next
   fedBooker(cyc)=ReadInt(filein)
   fedChampWorld(cyc)=ReadInt(filein)
   fedChampInter(cyc)=ReadInt(filein)
   For count=1 To 2
    fedChampTag(cyc,count)=ReadInt(filein)
   Next
   If screen<>14 Then fedLocked(cyc)=ReadInt(filein)
   If screen=14 Then void=ReadInt(filein)
   ;reset old values
   fedOldPop(cyc)=fedPop(cyc)
   fedOldRep(cyc)=fedRep(cyc)
  Next
 CloseFile(filein)
End Function

;------------------------------------------------------------------------
;//////////////////////////// CHARACTERS ////////////////////////////////
;------------------------------------------------------------------------

;LOAD PHOTOS
Function LoadPhotos()
 Loader("Please Wait","Loading Portraits")
 For loader=0 To no_chars
  charPhoto(loader)=LoadImage("Data/Portraits/Photo"+Dig$(loader,100)+".bmp")
  MaskImage charPhoto(loader),25,5,5 
 Next 
End Function

;SAVE CHARACTERS
Function SaveChars()
 For saver=1 To no_chars
  CheckLimits(saver)
  fileout=WriteFile("Data/Character"+Dig$(saver,100)+".dat")
   ;profile
   WriteString fileout,charName$(saver)
   WriteInt fileout,charAge(saver)
   WriteInt fileout,charHeight(saver)
   WriteInt fileout,charPop(saver)
   WriteInt fileout,charStr(saver)
   WriteInt fileout,charSkl(saver)
   WriteInt fileout,charAgl(saver)
   WriteInt fileout,charStam(saver)
   WriteInt fileout,charTough(saver)
   WriteInt fileout,charAtt(saver)
   WriteInt fileout,charHap(saver)
   WriteInt fileout,charHeel(saver)
   WriteInt fileout,charEyes(saver)
   WriteInt fileout,charFriend(saver)
   WriteInt fileout,charEnemy(saver)
   WriteInt fileout,charRealFriend(saver)
   WriteInt fileout,charRealEnemy(saver)
   WriteInt fileout,charManager(saver)
   WriteInt fileout,charTheme(saver)
   WriteInt fileout,charThemePitch(saver)
   WriteInt fileout,charLight(saver)
   WriteInt fileout,charWeapon(saver)
   ;costumes
   For coz=1 To 3
    WriteInt fileout,charModel(saver,coz)
    WriteInt fileout,charHairStyle(saver,coz)
    WriteInt fileout,charHat(saver,coz)
    WriteInt fileout,charSpecs(saver,coz)
    WriteInt fileout,charHair(saver,coz)
    WriteInt fileout,charFace(saver,coz)
    WriteInt fileout,charBody(saver,coz)
    WriteInt fileout,charLeftArm(saver,coz)
    WriteInt fileout,charRightArm(saver,coz)
    WriteInt fileout,charLegs(saver,coz)
    For col=1 To 3
     WriteInt fileout,charHatCol(saver,col,coz)
     WriteInt fileout,charSpecsCol(saver,col,coz)
     WriteInt fileout,charHairCol(saver,col,coz)
     WriteInt fileout,charFaceCol(saver,col,coz)
     WriteInt fileout,charBodyCol(saver,col,coz)
     WriteInt fileout,charLeftArmCol(saver,col,coz)
     WriteInt fileout,charRightArmCol(saver,col,coz)
     WriteInt fileout,charLegsCol(saver,col,coz)
    Next
   Next
   ;limb status
   For limb=1 To 40
    WriteInt fileout,charLimb(saver,limb)
   Next
   ;move set
   For count=1 To 5
    WriteInt fileout,charAttack(saver,count)
    WriteInt fileout,charCrush(saver,count)
   Next
   For count=1 To 2
    WriteInt fileout,charStance(saver,count)
    WriteInt fileout,charTaunt(saver,count)
   Next
   For count=1 To 6
    WriteInt fileout,charGroundMove(saver,count)
   Next
   For count=1 To 15
    WriteInt fileout,charMove(saver,count)
   Next
   ;status
   WriteInt fileout,charFed(saver)
   WriteInt fileout,charHealth(saver)
   WriteInt fileout,charInjured(saver)
   WriteInt fileout,charVacant(saver)
   WriteInt fileout,charTrainCourse(saver)
   WriteInt fileout,charTrainLevel(saver)
   WriteInt fileout,charSalary(saver)
   WriteInt fileout,charContract(saver)
   For count=1 To 3
    WriteInt fileout,charVariable(saver,count)
    WriteInt fileout,charOldVariable(saver,count)
   Next
  CloseFile(fileout)
 Next
End Function

;LOAD CHARACTERS
Function LoadChars()
 For loader=1 To no_chars
  If screen=14 Then filer$="Data/Default/" Else filer$="Data/"
  filein=ReadFile(filer$+"Character"+Dig$(loader,100)+".dat")
   ;profile
   charName$(loader)=ReadString(filein)
   charAge(loader)=ReadInt(filein) 
   charHeight(loader)=ReadInt(filein) 
   charPop(loader)=ReadInt(filein) 
   charStr(loader)=ReadInt(filein) 
   charSkl(loader)=ReadInt(filein) 
   charAgl(loader)=ReadInt(filein) 
   charStam(loader)=ReadInt(filein) 
   charTough(loader)=ReadInt(filein) 
   charAtt(loader)=ReadInt(filein) 
   charHap(loader)=ReadInt(filein) 
   charHeel(loader)=ReadInt(filein) 
   charEyes(loader)=ReadInt(filein) 
   charFriend(loader)=ReadInt(filein) 
   charEnemy(loader)=ReadInt(filein) 
   charRealFriend(loader)=ReadInt(filein) 
   charRealEnemy(loader)=ReadInt(filein) 
   charManager(loader)=ReadInt(filein) 
   charTheme(loader)=ReadInt(filein) 
   charThemePitch(loader)=ReadInt(filein) 
   charLight(loader)=ReadInt(filein) 
   charWeapon(loader)=ReadInt(filein) 
   ;costumes
   For coz=1 To 3
    charModel(loader,coz)=ReadInt(filein) 
    charHairStyle(loader,coz)=ReadInt(filein) 
    charHat(loader,coz)=ReadInt(filein) 
    charSpecs(loader,coz)=ReadInt(filein) 
    charHair(loader,coz)=ReadInt(filein) 
    charFace(loader,coz)=ReadInt(filein) 
    charBody(loader,coz)=ReadInt(filein) 
    charLeftArm(loader,coz)=ReadInt(filein) 
    charRightArm(loader,coz)=ReadInt(filein) 
    charLegs(loader,coz)=ReadInt(filein) 
    For col=1 To 3
     charHatCol(loader,col,coz)=ReadInt(filein) 
     charSpecsCol(loader,col,coz)=ReadInt(filein) 
     charHairCol(loader,col,coz)=ReadInt(filein) 
     charFaceCol(loader,col,coz)=ReadInt(filein) 
     charBodyCol(loader,col,coz)=ReadInt(filein) 
     charLeftArmCol(loader,col,coz)=ReadInt(filein) 
     charRightArmCol(loader,col,coz)=ReadInt(filein) 
     charLegsCol(loader,col,coz)=ReadInt(filein) 
    Next
   Next
   ;limb status
   For limb=1 To 40
    charLimb(loader,limb)=ReadInt(filein) 
   Next
   ;move set
   For count=1 To 5
    charAttack(loader,count)=ReadInt(filein) 
    charCrush(loader,count)=ReadInt(filein) 
   Next
   For count=1 To 2
    charStance(loader,count)=ReadInt(filein) 
    charTaunt(loader,count)=ReadInt(filein) 
   Next
   For count=1 To 6
    charGroundMove(loader,count)=ReadInt(filein) 
   Next
   For count=1 To 15
    charMove(loader,count)=ReadInt(filein) 
   Next
   ;status
   charFed(loader)=ReadInt(filein) 
   charHealth(loader)=ReadInt(filein) 
   charInjured(loader)=ReadInt(filein) 
   charVacant(loader)=ReadInt(filein) 
   charTrainCourse(loader)=ReadInt(filein) 
   charTrainLevel(loader)=ReadInt(filein) 
   charSalary(loader)=ReadInt(filein) 
   charContract(loader)=ReadInt(filein) 
   For count=1 To 3
    charVariable(loader,count)=ReadInt(filein) 
    charOldVariable(loader,count)=ReadInt(filein) 
   Next
  CloseFile(filein)
  ;check data
  CheckLimits(loader)
  ResetOldValues(loader)
  ;relationship logic
  If charFriend(loader)=loader Or charFriend(loader)=charEnemy(loader) Then charFriend(loader)=0
  If charEnemy(loader)=loader Then charEnemy(loader)=0
  If charManager(loader)=loader Or charManager(loader)=charEnemy(loader) Then charManager(loader)=0
  ;confirm fed
  For fed=1 To 9
   For count=1 To fedSize(fed)
    If fedRoster(fed,count)=loader Then charFed(loader)=fed
   Next
  Next
 Next
End Function

;------------------------------------------------------------------------
;///////////////////////// RELATED FUNCTIONS ////////////////////////////
;------------------------------------------------------------------------

;COPY CHARACTER DATA
Function CopyChar(source,target)
 ;attributes
 charName$(target)=charName$(source)
 charAge(target)=charAge(source)
 charHeight(target)=charHeight(source) 
 charPop(target)=charPop(source)
 charStr(target)=charStr(source)
 charSkl(target)=charSkl(source)
 charAgl(target)=charAgl(source)
 charStam(target)=charStam(source)
 charTough(target)=charTough(source) 
 charAtt(target)=charAtt(source)
 charHap(target)=charHap(source)
 charHeel(target)=charHeel(source)
 charEyes(target)=charEyes(source)
 charFriend(target)=charFriend(source)
 charEnemy(target)=charEnemy(source)
 charManager(target)=charManager(source)
 charTheme(target)=charTheme(source)
 charThemePitch(target)=charThemePitch(source)
 charLight(target)=charLight(source)
 charWeapon(target)=charWeapon(source) 
 ;costumes
 For coz=1 To 3
  charModel(target,coz)=charModel(source,coz)
  charHairStyle(target,coz)=charHairStyle(source,coz)
  charHat(target,coz)=charHat(source,coz)
  charSpecs(target,coz)=charSpecs(source,coz)
  charHair(target,coz)=charHair(source,coz)
  charFace(target,coz)=charFace(source,coz)
  charBody(target,coz)=charBody(source,coz)
  charLeftArm(target,coz)=charLeftArm(source,coz)
  charRightArm(target,coz)=charRightArm(source,coz)
  charLegs(target,coz)=charLegs(source,coz)
  For col=1 To 3
   charHatCol(target,col,coz)=charHatCol(source,col,coz)
   charSpecsCol(target,col,coz)=charSpecsCol(source,col,coz)
   charHairCol(target,col,coz)=charHairCol(source,col,coz)
   charFaceCol(target,col,coz)=charFaceCol(source,col,coz)
   charBodyCol(target,col,coz)=charBodyCol(source,col,coz)
   charLeftArmCol(target,col,coz)=charLeftArmCol(source,col,coz)
   charRightArmCol(target,col,coz)=charRightArmCol(source,col,coz)
   charLegsCol(target,col,coz)=charLegsCol(source,col,coz)
  Next
 Next
 ;limb structure
 For limb=1 To 40
  charLimb(target,limb)=charLimb(source,limb)
 Next
 ;attacks
 For count=1 To 5
  charAttack(target,count)=charAttack(source,count)
  charCrush(target,count)=charCrush(source,count)
 Next
 ;stances
 For count=1 To 2
  charStance(target,count)=charStance(source,count)
  charTaunt(target,count)=charTaunt(source,count)
 Next
 ;move set
 For count=1 To 15
  charMove(target,count)=charMove(source,count)
 Next
 For count=1 To 6
  charGroundMove(target,count)=charGroundMove(source,count)
 Next
End Function

;CHECK ATTRIBUTE/COSTUME LIMITS
Function CheckLimits(char)
 ;profile
 If charHealth(char)<0 Then charHealth(char)=0
 If charHealth(char)>100 Then charHealth(char)=100
 If charAge(char)<13 Then charAge(char)=13
 If charAge(char)>100 Then charAge(char)=100
 If charHeight(char)<0 Then charHeight(char)=0
 If charHeight(char)>32 Then charHeight(char)=32
 ;---------
 If charPop(char)<30 Then charPop(char)=30
 If charPop(char)>99 Then charPop(char)=99
 If charStr(char)<30 Then charStr(char)=30
 If charStr(char)>99 Then charStr(char)=99
 If charSkl(char)<30 Then charSkl(char)=30
 If charSkl(char)>99 Then charSkl(char)=99
 If charAgl(char)<30 Then charAgl(char)=30
 If charAgl(char)>99 Then charAgl(char)=99
 If charStam(char)<30 Then charStam(char)=30
 If charStam(char)>99 Then charStam(char)=99
 If charTough(char)<30 Then charTough(char)=30
 If charTough(char)>99 Then charTough(char)=99
 If charAtt(char)<30 Then charAtt(char)=30
 If charAtt(char)>99 Then charAtt(char)=99
 If charHap(char)<30 Then charHap(char)=30
 If charHap(char)>99 Then charHap(char)=99
 ;---------
 If charHeel(char)<0 Then charHeel(char)=1
 If charHeel(char)>1 Then charHeel(char)=0
 ;If charHeel(char)=0 Then min=2 : max=3
 ;If charHeel(char)=1 Then min=1 : max=2
 min=1 : max=3
 If charEyes(char)<min Then charEyes(char)=max
 If charEyes(char)>max Then charEyes(char)=min
 If charTheme(char)<0 Then charTheme(char)=no_themes
 If charTheme(char)>no_themes Then charTheme(char)=0
 pitcher=DefaultPitch(charTheme(char))
 If charThemePitch(char)<pitcher/2 Then charThemePitch(char)=pitcher/2
 If charThemePitch(char)>pitcher+(pitcher/3) Then charThemePitch(char)=pitcher+(pitcher/3)
 If charLight(char)<0 Then charLight(char)=no_lights
 If charLight(char)>no_lights Then charLight(char)=0
 If charWeapon(char)<0 Then charWeapon(char)=weapList
 If charWeapon(char)>weapList Then charWeapon(char)=0
 ;appearance
 For coz=1 To 3
  If charModel(char,coz)<1 Then charModel(char,coz)=no_models
  If charModel(char,coz)>no_models Then charModel(char,coz)=1
  If charHairStyle(char,coz)<0 Then charHairStyle(char,coz)=no_hstyles
  If charHairStyle(char,coz)>no_hstyles Then charHairStyle(char,coz)=0
  If charHat(char,coz)<0 Then charHat(char,coz)=no_hats
  If charHat(char,coz)>no_hats Then charHat(char,coz)=0
  If charSpecs(char,coz)<0 Then charSpecs(char,coz)=no_specs
  If charSpecs(char,coz)>no_specs Then charSpecs(char,coz)=0
  If charHair(char,coz)<1 Then charHair(char,coz)=no_hairs
  If charHair(char,coz)>no_hairs Then charHair(char,coz)=1
  If charFace(char,coz)<1 Then charFace(char,coz)=no_faces
  If charFace(char,coz)>no_faces Then charFace(char,coz)=1
  If charBody(char,coz)<1 Then charBody(char,coz)=no_bodies
  If charBody(char,coz)>no_bodies Then charBody(char,coz)=1
  If charLeftArm(char,coz)<1 Then charLeftArm(char,coz)=no_arms
  If charLeftArm(char,coz)>no_arms Then charLeftArm(char,coz)=1
  If charRightArm(char,coz)<1 Then charRightArm(char,coz)=no_arms
  If charRightArm(char,coz)>no_arms Then charRightArm(char,coz)=1
  If charLegs(char,coz)<1 Then charLegs(char,coz)=no_legs
  If charLegs(char,coz)>no_legs Then charLegs(char,coz)=1
  ;colour schemes
  For col=1 To 3
   If charHatCol(char,col,coz)<0 Then charHatCol(char,col,coz)=0
   If charHatCol(char,col,coz)>255 Then charHatCol(char,col,coz)=255
   If charSpecsCol(char,col,coz)<0 Then charSpecsCol(char,col,coz)=0
   If charSpecsCol(char,col,coz)>255 Then charSpecsCol(char,col,coz)=255
   If charHairCol(char,col,coz)<0 Then charHairCol(char,col,coz)=0
   If charHairCol(char,col,coz)>255 Then charHairCol(char,col,coz)=255
   If charFaceCol(char,col,coz)<0 Then charFaceCol(char,col,coz)=0
   If charFaceCol(char,col,coz)>255 Then charFaceCol(char,col,coz)=255
   If charBodyCol(char,col,coz)<0 Then charBodyCol(char,col,coz)=0
   If charBodyCol(char,col,coz)>255 Then charBodyCol(char,col,coz)=255
   If charLeftArmCol(char,col,coz)<0 Then charLeftArmCol(char,col,coz)=0
   If charLeftArmCol(char,col,coz)>255 Then charLeftArmCol(char,col,coz)=255
   If charRightArmCol(char,col,coz)<0 Then charRightArmCol(char,col,coz)=0
   If charRightArmCol(char,col,coz)>255 Then charRightArmCol(char,col,coz)=255
   If charLegsCol(char,col,coz)<0 Then charLegsCol(char,col,coz)=0
   If charLegsCol(char,col,coz)>255 Then charLegsCol(char,col,coz)=255
  Next
 Next
 ;attacks
 For count=1 To 5
  If charAttack(char,count)<1 Then charAttack(char,count)=no_attacks(count)
  If charAttack(char,count)>no_attacks(count) Then charAttack(char,count)=1
  If charCrush(char,count)<1 Then charCrush(char,count)=no_crushes(count)
  If charCrush(char,count)>no_crushes(count) Then charCrush(char,count)=1
 Next
 ;stances
 If charStance(char,1)<1 Then charStance(char,1)=no_stances
 If charStance(char,1)>no_stances Then charStance(char,1)=1
 If charStance(char,2)<1 Then charStance(char,2)=no_walks
 If charStance(char,2)>no_walks Then charStance(char,2)=1
 For count=1 To 2
  If charTaunt(char,count)<1 Then charTaunt(char,count)=no_taunts
  If charTaunt(char,count)>no_taunts Then charTaunt(char,count)=1
 Next
 ;move set
 For count=1 To 12
  If charMove(char,count)<1 Then charMove(char,count)=no_moves
  If charMove(char,count)>no_moves Then charMove(char,count)=1
 Next
 If charMove(char,13)<3 Then charMove(char,13)=no_moves
 If charMove(char,13)>no_moves Then charMove(char,13)=3
 If charMove(char,14)<1 Then charMove(char,14)=no_cornmoves
 If charMove(char,14)>no_cornmoves Then charMove(char,14)=1
 If charMove(char,15)<2 Then charMove(char,15)=no_moves
 If charMove(char,15)>no_moves Then charMove(char,15)=2
 For count=1 To 3
  If charGroundMove(char,count)<1 Then charGroundMove(char,count)=no_headmoves
  If charGroundMove(char,count)>no_headmoves Then charGroundMove(char,count)=1
  If charGroundMove(char,count+3)<1 Then charGroundMove(char,count+3)=no_legmoves
  If charGroundMove(char,count+3)>no_legmoves Then charGroundMove(char,count+3)=1
 Next
End Function