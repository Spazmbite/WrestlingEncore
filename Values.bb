;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING ENCORE: VARIABLES -------------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////
;------------------ STRUCTURE -------------------------
;//////////////////////////////////////////////////////
Global screen,screenCall,callX,callY
Global screenAgenda ;0=editing, 1=playing, 2=study, 3=initiation, ...  10=sparring
Global go,gotim,tester
Global foc,subfoc,keytim
Global clockStart,clockUpdate
Global filein,fileout
Global saver,loader
Global legendTim,speedTim
;frame rating
Const FPS=30
Dim frameRate(3)
frameRate(0)=1000
frameRate(1)=900
frameRate(2)=800
frameRate(3)=600

;//////////////////////////////////////////////////////
;------------------- PROGRESS -------------------------
;//////////////////////////////////////////////////////
.Progress
;handles
Global game=0,slot=1
;status
Dim gamChar(3)
Dim gamFormat(3) ;1=singles wrestler, 2=tag wrestler, 3=referee!
Dim gamBank(3)
Dim gamWorth(3)
Dim gamClause(3,3) ;1=image rights, 2=performance clause, 3=health policy
Dim gamTrainCourse(3)
Dim gamTrainLevel(3) ;1=mild, 2=average, 3=intense
Dim gamExpenses(3)
;1=drug use, 2=steroid use, 3=second match, 4=weapons banned, 5=items banned, 6=bribed opponent, 7=paid to lose
;8=favourable ref, 9=unfavourable ref, 10=ignore injury, 11=sold attacks, 12=sold moves, 13=changed costume
Dim gamVariable(3)
Dim gamOldVariable(3)
;schedule
Dim gamWeek(3)
Dim gamMonth(3)
Dim gamYear(3)
Dim gamSchedule(3,52) ;-1=injured, 0=nothing, 1=TV, 2=PPV, 3=tribute, 4=charity, 5=interpromotional, 6=court case
Dim gamInterProm(3,52)
Dim gamOpponent(3,52)
Dim gamMatch(3,52)
Dim gamGimmick(3,52) ;1=hardcore, 2=steel cage, 3=barbed, 4=electric, 5=inferno, 6=blast, 7=hair vs hair, 8=loser leaves
Dim gamPromo(3,52)
Dim gamResult(3,52) ;-1=loss, 0=null, 1=win
;missions
Dim gamMission(3)
Dim gamTarget(3)
Dim gamDeadline(3)
;record
Dim gamExperience(3,7)
Dim gamMatches(3,7)
Dim gamWins(3,7)
Dim gamWorldTitles(3,7)
Dim gamInterTitles(3,7)
Dim gamTagTitles(3,7)
;venue
Dim gamArenaPreset(3)
Dim gamArenaCrowd(3)
Dim gamArenaAtmos(3)
Dim gamArenaLight(3)
Dim gamArenaRopes(3)
Dim gamArenaApron(3)
Dim gamArenaCanvas(3)
Dim gamArenaMat(3)
;finances ;0=working value, 1=target value
Dim gamFinance(7,2) ;1=income, 2=lifestyle, 3=gimmick, 4=management, 5=training, 6=profit, 7=balance
Global gamBonus,gamPoints
Global gamManaged
;hi scores
Global hiInduct
Dim hiChar(10)
Dim hiName$(10)
Dim hiFed(10)
Dim hiTalent(10)
Dim hiBank(10)
Dim hiExperience(10)
Dim hiSuccess(10)
Dim hiTitles(10)

;//////////////////////////////////////////////////////
;------------------- OPTIONS --------------------------
;//////////////////////////////////////////////////////
.Options
;constants
Global optSound=1,optSubtitles=1,optMenu=0 ;0=MPire, 1=Encore
Global optOldAge=40,optTired=15,optRopeRange=7
Global optPlayLim=30,optCharLim=204,optFedLim=40
;preferences
Global optLevel,optHealth=1,optSpeed=1
Global optRef=2,optManagers=1,optExtras=1
Global optHideElim=1,optTagControl=1 
;display options
Global optGameRes=2,optMenuRes=2
Global optMeters=1,optTags=1
Global optFX=2,optGore=1,optLimbs=1
Global optCrowdAnim=1,optVideo=1
Global optGhost=2,optAtmos=1
;keys & buttons
Global keyAttack=30,keyGrab=31,keySwitch=29
Global keyRun=44,keyPick=45,keyTaunt=57
Global buttAttack=4,buttGrab=5,buttSwitch=3
Global buttRun=1,buttPick=2,buttTaunt=6

;//////////////////////////////////////////////////////
;------------------- MATCHES --------------------------
;//////////////////////////////////////////////////////
.Matches
;match options
Global no_plays=4,no_wrestlers=2,no_refs=1
Global matchPreset=2,no_matches=17
Global matchTim,matchEnter,matchState ;0=delay, 1=entrances, 2=promo, 3=playing, 4=over
Global matchSecs,matchMins,matchTimeLim=10,matchLastMin
Global matchRules=0 ;0=hardcore, 1=in ring, 2=in ring strict
Global matchType=1 ;0=none, 1=first fall wins, 2=best of 3, 3=ironman, 4=last fall, 5=elimination
Global matchPins=1,matchSubs=1,matchKOs=0,matchBlood=0,matchOTT=0
Global matchTeams=0 ;-1=sequential, 0=individuals, 1=teams, 2=tag teams
Global matchWinner,matchLoser,matchWinStyle ;0=DQ, 1=pin, 2=submit, 3=KO, 4=blood, 5=OTT
Global matchRemaining,matchLeader,matchSurvivor
Global matchPlayer,matchMulti,matchInvader
Global matchWeapon,matchDamage
Global matchOfficial,matchQuit
;gimmick options
Global matchCage=0 ;1=steel bars, 2=blue bars, 3=black bars, 4=wire mesh
Global matchRopes=0 ;0=normal, 1=barbed wire, 2=electrified
Global matchBlastTim,matchBlasted
Global matchChamps=1,matchReward ;0=ignore, 1=acknowledge, 2-4=crown new, 5=hair, 6=leave town
Global matchPause
;promos
Global matchPromo,no_promos=79
Global speaker,promoTim,promoLogic
Dim promoActor(3)
Dim promoReact(10)
Dim promoTitle$(100)
Dim promoExplain$(100)
Dim promoLength(100)
Dim promoRole(100,3) ;-1=ref, 0=none, 1=next from team one, 2=next from team two
Dim promoLocked(100)
;marques
Global marScript$
Global marX#,marY#
Global marColour

;//////////////////////////////////////////////////////
;----------------- FEDERATIONS ------------------------
;//////////////////////////////////////////////////////
.Promotions
Global no_chars
Global fed=1,fedLeader
Dim fedName$(9)
Dim fedShortName$(9)
Dim fedBank(9)
Dim fedPop(9)
Dim fedRep(9)
Dim fedOldPop(9)
Dim fedOldRep(9)
Dim fedNewPop(9)
Dim fedNewRep(9)
Dim fedSize(9)
Dim fedRoster(9,50)
Dim fedBooker(9)
Dim fedChampWorld(9)
Dim fedChampInter(9)
Dim fedChampTag(9,2)
Dim fedOldChampWorld(9)
Dim fedOldChampInter(9)
Dim fedOldChampTag(9,2)
Dim fedLocked(9)
;negotiations
Global negChar,negSetting ;1=boardroom, 2=locker room, 3=hospital, 4=courtroom, 5=award ceremony
Global negTopic,negTim,negStage,negSubStage
Global negWorth,negChances,negVerdict
Global negPayOff,negSalary,negContract
Dim negClause(3)
;news
Global no_events
Dim eEvent(50),eChar(50),eFoc(50)
Dim eVariable(50),eCharged(50)

;//////////////////////////////////////////////////////
;------------------- EDITING --------------------------
;//////////////////////////////////////////////////////
.Editing
Global no_slots,page,no_pages
Global cloner,sourceX,sourceY,targetX,targetY
Global no_hstyles=33,no_hats=13,no_specs=5
Global no_models=17,no_props=13
Global warning$,warnX,warnY
Global manipR,manipG,manipB
Global blockData,blockImage
Global blockAttacks,blockMoves
;page limits
Dim pageList(6)
pageList(1)=23
For count=2 To 4
 pageList(count)=16
Next
pageList(5)=16
pageList(6)=24
;COUNT COSTUME PARTS
;count hair textures
Global no_hairs=0
folder=ReadDir(CurrentDir$()+"Costumes/Hair/")
 Repeat
  file$=NextFile$(folder)
  namer$=Upper$(Left$(file$,4))
  If FileType(CurrentDir$()+"Costumes/Hair/"+file$)=1 And namer$="HAIR" Then no_hairs=no_hairs+1
 Until file$=""
CloseDir(folder)
;count face textures
Global no_faces=0
folder=ReadDir(CurrentDir$()+"Costumes/Faces/")
 Repeat
  file$=NextFile$(folder)
  namer$=Upper$(Left$(file$,4))
  If FileType(CurrentDir$()+"Costumes/Faces/"+file$)=1 And namer$="FACE" Then no_faces=no_faces+1
 Until file$=""
CloseDir(folder)
;count body textures
Global no_bodies=0
folder=ReadDir(CurrentDir$()+"Costumes/Bodies/")
 Repeat
  file$=NextFile$(folder)
  namer$=Upper$(Left$(file$,4))
  If FileType(CurrentDir$()+"Costumes/Bodies/"+file$)=1 And namer$="BODY" Then no_bodies=no_bodies+1
 Until file$=""
CloseDir(folder)
;count arm textures
Global no_arms=0
folder=ReadDir(CurrentDir$()+"Costumes/Arms/")
 Repeat
  file$=NextFile$(folder)
  namer$=Upper$(Left$(file$,3))
  If FileType(CurrentDir$()+"Costumes/Arms/"+file$)=1 And namer$="ARM" Then no_arms=no_arms+1
 Until file$=""
CloseDir(folder)
;count leg textures
Global no_legs=0
folder=ReadDir(CurrentDir$()+"Costumes/Legs/")
 Repeat
  file$=NextFile$(folder)
  namer$=Upper$(Left$(file$,4))
  If FileType(CurrentDir$()+"Costumes/Legs/"+file$)=1 And namer$="LEGS" Then no_legs=no_legs+1
 Until file$=""
CloseDir(folder)

;///////////////////////////////////////////////////////
;----------------------- SOUND -------------------------
;///////////////////////////////////////////////////////
.Sound
;menu effects
Global sJingle=LoadSound("Sound/Jingle.wav")
Global sMenuBrowse=LoadSound("Sound/Menu_Browse.wav")
Global sMenuSelect=LoadSound("Sound/Menu_Select.wav")
Global sMenuGo=LoadSound("Sound/Menu_Go.wav")
Global sMenuBack=LoadSound("Sound/Menu_Back.wav")
Global sVoid=LoadSound("Sound/Void.wav")
Global sCash=LoadSound("Sound/Cash.wav")
Global sCamera=LoadSound("Sound/Camera.wav")
Global sBuzzer=LoadSound("Sound/Buzzer.wav")
Global sBell=LoadSound("Sound/Bell.wav")
Global sPaper=LoadSound("Sound/Paper.wav")
;movements
Dim sShuffle(3)
For count=1 To 3
 sShuffle(count)=Load3DSound("Sound/Movement/Shuffle0"+count+".wav")
Next
Dim sStep(6)
For count=1 To 6
 sStep(count)=Load3DSound("Sound/Movement/Step0"+count+".wav")
Next
Global sFall=Load3DSound("Sound/Movement/Fall.wav")
Global sThud=Load3DSound("Sound/Movement/Thud.wav")
Global sRopes=Load3DSound("Sound/Movement/Ropes.wav")
;impacts
Global sSwing=Load3DSound("Sound/Movement/Swing.wav")
Dim sImpact(6)
For count=1 To 6
 sImpact(count)=Load3DSound("Sound/Movement/Impact0"+count+".wav")
Next
Global sBleed=Load3DSound("Sound/Movement/Bleed.wav")
;weapon impacts
Global sImpactBell=Load3DSound("Sound/Props/Impact_Bell.wav")
Global sImpactBlade=Load3DSound("Sound/Props/Impact_Blade.wav")
Global sImpactBottle=Load3DSound("Sound/Props/Impact_Bottle.wav")
Global sImpactCane=Load3DSound("Sound/Props/Impact_Cane.wav")
Global sImpactGun=Load3DSound("Sound/Props/Impact_Gun.wav")
Global sImpactHammer=Load3DSound("Sound/Props/Impact_Hammer.wav")
Global sImpactMetal=Load3DSound("Sound/Props/Impact_Metal.wav")
Global sImpactMic=Load3DSound("Sound/Props/Impact_Mic.wav")
Global sImpactWood=Load3DSound("Sound/Props/Impact_Wood.wav")
Global sImpactTacks=Load3DSound("Sound/Props/Impact_Tacks.wav")
;item smashes
Global sItem=Load3DSound("Sound/Props/Item.wav")
Global sSmashCard=Load3DSound("Sound/Props/Smash_Card.wav")
Global sSmashElectric=Load3DSound("Sound/Props/Smash_Electric.wav")
Global sSmashGlass=Load3DSound("Sound/Props/Smash_Glass.wav")
Global sSmashMetal=Load3DSound("Sound/Props/Smash_Metal.wav")
Global sSmashPlastic=Load3DSound("Sound/Props/Smash_Plastic.wav")
Global sSmashWire=Load3DSound("Sound/Props/Smash_Wire.wav")
Global sSmashWood=Load3DSound("Sound/Props/Smash_Wood.wav")
;pain
Global sChoke=Load3DSound("Sound/Speech/Choke.wav")
Dim sPain(6)
For count=1 To 6
 sPain(count)=Load3DSound("Sound/Speech/Pain0"+count+".wav")
Next
;elements
Global sFire=Load3DSound("Sound/Props/Fire.wav")
Global sIgnite=Load3DSound("Sound/Props/Ignite.wav")
Global sExpire=Load3DSound("Sound/Props/Expire.wav") 
Global sExplosion=Load3DSound("Sound/Props/Explosion.wav")
Global sElectric=Load3DSound("Sound/Props/Electricity.wav")
Global sPower=Load3DSound("Sound/Props/Power.wav")
Global sSplash=Load3DSound("Sound/Props/Splash.wav")
;refereeing
Dim sCount(3)
For count=1 To 3
 sCount(count)=Load3DSound("Sound/Speech/Count0"+count+".wav")
Next
Global sBreak=Load3DSound("Sound/Speech/Break.wav")
Global sDQ=Load3DSound("Sound/Speech/DQ.wav")
;crowd
Global chCrowd
Global crowdVolMin#,crowdVolMax#
Global crowdVol#,crowdVolTarget#
Global crowdPitch#,crowdPitchTarget#
Dim sCrowd(12)
sCrowd(0)=LoadSound("Sound/Crowd/Empty.wav")
sCrowd(1)=LoadSound("Sound/Crowd/Loop.wav")
sCrowd(2)=LoadSound("Sound/Crowd/Cheer.wav")
sCrowd(3)=LoadSound("Sound/Crowd/Boo.wav")
sCrowd(4)=LoadSound("Sound/Crowd/Yay.wav")
sCrowd(5)=LoadSound("Sound/Crowd/Groan.wav")
sCrowd(6)=LoadSound("Sound/Crowd/Excitement.wav")
sCrowd(7)=LoadSound("Sound/Crowd/Murmur.wav")
sCrowd(8)=LoadSound("Sound/Crowd/Laughter.wav")
sCrowd(9)=LoadSound("Sound/Crowd/Applause.wav")
sCrowd(10)=LoadSound("Sound/Crowd/Chant.wav")
sCrowd(11)=LoadSound("Sound/Crowd/Bored.wav")

;///////////////////////////////////////////////////////
;----------------------- MUSIC -------------------------
;///////////////////////////////////////////////////////
.Music
;variables
Global chTheme,chThemeVol#
Global no_themes
Dim sTheme(99)
;find files
For count=0 To 99
 sTheme(count)=LoadSound("Music/Theme"+Dig$(count,10)+".mp3")
 If sTheme(count)=0 Then sTheme(count)=LoadSound("Music/Theme"+Dig$(count,10)+".wav")
 If sTheme(count)>0 Then no_themes=count
Next

;///////////////////////////////////////////////////////
;--------------------- GRAPHICS ------------------------
;///////////////////////////////////////////////////////
.Images
;variables
Dim font(10)
Dim fontNews(5)
Dim gLogo(5)
Dim gMenu(10)
Dim gCharMenu(10)
Dim gFedMenu(10)
Dim gFed(9)
Dim gRole(3)
Dim gHeel(1)
Dim gBelt(6)
Dim gArena(20)
Dim gDate(20)
Dim gResult(3)
Global gTile,gMDickie,gOutro,gIdigicon
Global gColour,gCursor,gSmallprint,gSpeeder
Global gCamera,gTape,gVersus,gInjury
Global gReport,gMagazine,gNewspaper,gPhoto
;LOADING PROCESS
Function LoadImages()
 ;regular fonts
 font(0)=LoadFont("Verdana",12,0,0,0)
 font(1)=LoadFont("Verdana",15,1,0,0)
 font(2)=LoadFont("Verdana",20,0,0,0)
 font(3)=LoadFont("Verdana",28,0,0,0)
 font(4)=LoadFont("Verdana",36,0,0,0)
 font(5)=LoadFont("Verdana",36,0,1,0)
 font(6)=LoadFont("Verdana",48,0,0,0)
 ;news fonts
 fontNews(0)=LoadFont("Times New Roman",12,1,0,0)
 fontNews(1)=LoadFont("Times New Roman",15,1,0,0)
 fontNews(2)=LoadFont("Times New Roman",20,1,0,0)
 fontNews(3)=LoadFont("Times New Roman",45,1,0,0)
 ;game logos
 gTile=LoadImage("Graphics/Tile.bmp")
 For count=1 To 3
  gLogo(count)=LoadImage("Graphics/Logo0"+count+".png")
  MaskImage gLogo(count),0,0,0
 Next
 gMDickie=LoadImage("Graphics/MDickie.png")
 MaskImage gMDickie,255,0,255
 ;gIdigicon=LoadImage("Graphics/Idigicon.png")
 ;MaskImage gIdigicon,255,0,255
 gOutro=LoadImage("Graphics/Outro.JPG")
 MaskImage gOutro,255,0,255
 ;menu boxes
 For count=0 To 8
  gMenu(count)=LoadImage("Graphics/Menu Boxes/Menu0"+count+".bmp")
  MaskImage gMenu(count),255,0,255
 Next
 For count=1 To 2
  gCharMenu(count)=LoadImage("Graphics/Menu Boxes/CharMenu0"+count+".bmp")
  MaskImage gCharMenu(count),255,0,255
  gFedMenu(count)=LoadImage("Graphics/Menu Boxes/FedMenu0"+count+".bmp")
  MaskImage gFedMenu(count),255,0,255
 Next
 ;loader
 Loader("Please Wait","Loading Graphics")
 ;fed logos
 gFed(0)=LoadImage("Graphics/Promotions/Fed00.png")
 MaskImage gFed(0),255,0,255
 For count=1 To 9
  gFed(count)=LoadImage("Graphics/Promotions/Fed0"+count+".bmp")
  MaskImage gFed(count),0,0,0
 Next
 ;misc icons
 gCursor=LoadImage("Graphics/Cursor.bmp")
 MaskImage gCursor,255,0,255
 gSmallprint=LoadImage("Graphics/Smallprint.bmp")
 MaskImage gSmallprint,255,0,255
 gSpeeder=LoadImage("Graphics/Speeder.bmp")
 MaskImage gSpeeder,255,0,255
 gColour=LoadImage("Graphics/Colour.png") 
 MaskImage gColour,255,0,255
 gVersus=LoadImage("Graphics/Versus.bmp") 
 MaskImage gVersus,255,0,255
 gCamera=LoadImage("Graphics/Camera.bmp") 
 MaskImage gCamera,255,0,255
 gTape=LoadImage("Graphics/Tape.bmp") 
 MaskImage gTape,255,0,255 
 gInjury=LoadImage("Graphics/Injury.bmp") 
 MaskImage gInjury,255,0,255
 ;demeanour icons
 gHeel(0)=LoadImage("Graphics/Face.bmp")
 gHeel(1)=LoadImage("Graphics/Heel.bmp") 
 For count=0 To 1
  MaskImage gHeel(count),255,0,255
 Next
 ;role icons
 For count=1 To 3
  gRole(count)=LoadImage("Graphics/Role0"+count+".bmp")
  MaskImage gRole(count),255,0,255
 Next
 ;belt icons
 For count=0 To 6
  gBelt(count)=LoadImage("Graphics/Belt0"+count+".png")
  MaskImage gBelt(count),255,0,255
 Next
 ;arena previews
 For count=0 To 17
  gArena(count)=LoadImage("Graphics/Arenas/Arena"+Dig$(count,10)+".JPG")
  MaskImage gArena(count),255,0,255
 Next
 ;schedule icons
 For count=0 To 5
  gDate(count)=LoadImage("Graphics/Schedule/Date0"+count+".bmp")
  MaskImage gDate(count),0,0,0
 Next
 gDate(10)=LoadImage("Graphics/Schedule/History.bmp")
 MaskImage gDate(10),255,0,255
 gDate(11)=LoadImage("Graphics/Schedule/Highlighter.bmp")
 MaskImage gDate(11),0,0,0
 ;result icons
 For count=1 To 3
  gResult(count)=LoadImage("Graphics/Schedule/Result0"+count+".bmp")
  MaskImage gResult(count),0,0,0
 Next
 ;articles
 gMagazine=LoadImage("Graphics/Magazine.png")
 MaskImage gMagazine,255,0,255
 gNewspaper=LoadImage("Graphics/Newspaper.png")
 MaskImage gNewspaper,255,0,255
 gReport=LoadImage("Graphics/Report.png")
 MaskImage gReport,255,0,255
End Function

;///////////////////////////////////////////////////////
;--------------------- TEXTURES ------------------------
;///////////////////////////////////////////////////////
.Textures
;tag variables
Dim tagCount(3)
Global tagSpecial,tagEliminated,tagWarning
Global tagOut,tagInjured,tagLegal,tagControl
;world variables
Dim tBigWall(10)
Dim tCeiling(10)
Dim tWall(10)
Dim tGround(10)
Dim tCrowd(3)
Dim tVideo(10)
Dim tRopes(3)
Dim tCage(3)
Dim tWood(5)
Dim tMetal(5)
Dim tGlass(2)
Dim tBottle(2)
Dim tGame(5)
Dim tBelt(3)
Global tApron,tCanvas,tMat,tCurtain
Global tChair,tPicture,tTacks
;character variables
Dim tEyes(3)
Dim tMouth(5)
Dim tFaceScar(5)
Dim tBodyScar(5)
Dim tArmScar(5)
Dim tLegScar(5)
Dim tHair(no_hairs)
Dim tFace(no_faces)
Dim tBody(no_bodies)
Dim tArms(no_arms)
Dim tLegs(no_legs)
Global tVoid,tShaved
Global tHatBlack,tHatWhite
Global tCapBlack,tCapWhite
Global tSeverArms,tSeverLegs,tCharred
;LOADING PROCESS
Function LoadTextures()
 ;tags
 Loader("Please Wait","Loading Textures")
 For count=1 To 3
  tagCount(count)=LoadTexture("World/Sprites/Tag_Count0"+count+".bmp",12)
 Next
 tagSpecial=LoadTexture("World/Sprites/Tag_Special.bmp",12)
 tagWarning=LoadTexture("World/Sprites/Tag_Warning.bmp",12)
 tagInjured=LoadTexture("World/Sprites/Tag_Injured.bmp",12)
 tagEliminated=LoadTexture("World/Sprites/Tag_Eliminated.bmp",12)
 tagLegal=LoadTexture("World/Sprites/Tag_Legal.bmp",12)
 tagOut=LoadTexture("World/Sprites/Tag_Out.bmp",12)
 tagControl=LoadTexture("World/Sprites/Tag_Control.bmp",12)
 ;arena variations
 For count=1 To 7
  tBigWall(count)=LoadTexture("World/Variations/BigWall"+Dig$(count,10)+".JPG")
 Next 
 For count=1 To 3
  tCeiling(count)=LoadTexture("World/Variations/Ceiling"+Dig$(count,10)+".JPG")
 Next 
 For count=1 To 10
  tWall(count)=LoadTexture("World/Variations/Wall"+Dig$(count,10)+".JPG")
 Next 
 For count=1 To 9
  tGround(count)=LoadTexture("World/Variations/Ground"+Dig$(count,10)+".JPG")
 Next 
 ;crowds
 tCrowd(0)=LoadAnimTexture("World/Sprites/Crowders.png",4,512,128,0,4)
 For count=1 To 3
  tCrowd(count)=LoadTexture("World/Variations/Seating0"+count+".JPG")
 Next
 ;videos
 tVideo(0)=LoadTexture("Items/Screen.JPG")
 tVideo(1)=LoadTexture("World/Videos/Video01.JPG")
 tVideo(2)=LoadTexture("World/Videos/Video02.JPG")
 ;tVideo(3) ;fed logo ?
 ;tVideo(4) ;arena ?
 tVideo(5)=CreateTexture(512,256) ;live footage 
 tVideo(6)=CreateTexture(512,256) ;static shot
 tVideo(7)=LoadTexture("World/Videos/Noise.JPG") 
 ;ropes & cages
 tRopes(1)=LoadTexture("World/Ropes/Barbed.png",4)
 tRopes(2)=LoadTexture("World/Ropes/Electric.png",4)
 tRopes(3)=LoadTexture("World/Ropes/Flames.png",4) 
 For count=1 To 3
  tCage(count)=LoadTexture("World/Cage/Cage0"+count+".png",4)
 Next 
 ;item materials
 tChair=LoadTexture("Items/Textures/Chair.png",4)
 For count=1 To 2
  tMetal(count)=LoadTexture("Items/Textures/Metal0"+count+".JPG")
 Next
 For count=1 To 2
  tWood(count)=LoadTexture("Items/Textures/Wood0"+count+".JPG")
 Next
 tGlass(1)=LoadTexture("Items/Textures/Glass01.png")
 tGlass(2)=LoadTexture("Items/Textures/Glass02.png",4)
 ;weapon materials
 For count=1 To 2
  tBottle(count)=LoadTexture("Items/Textures/Bottle0"+count+".JPG")
 Next
 tGame(0)=LoadTexture("Items/Textures/Tape.JPG")
 tGame(1)=LoadTexture("Items/Textures/Game01.JPG")
 For count=1 To 3
  tBelt(count)=LoadTexture("Items/Textures/Belt0"+count+".JPG")
 Next 
 tTacks=LoadTexture("Items/Textures/Tacks.png",4)
 ;editor
 tVoid=LoadTexture("Costumes/Void.JPG")
 tShaved=LoadTexture("Costumes/Hair/Shaved.JPG")
 tHatBlack=LoadTexture("Costumes/Hats/Hat_Black.JPG")
 tHatWhite=LoadTexture("Costumes/Hats/Hat_White.JPG")
 tCapBlack=LoadTexture("Costumes/Hats/Cap_Black.JPG")
 tCapWhite=LoadTexture("Costumes/Hats/Cap_White.JPG")
 ;eye expressions
 Loader("Please Wait","Loading Expressions")
 For count=1 To 3
  tEyes(count)=LoadTexture("Costumes/Expressions/Eyes0"+count+".JPG")
 Next
 ;mouth expressions
 For count=0 To 5
  tMouth(count)=LoadTexture("Costumes/Expressions/Mouth0"+count+".JPG")
 Next
 ;facial scarring
 For count=0 To 5
  tFaceScar(count)=LoadTexture("Costumes/Scarring/Face"+Dig$(count,10)+".JPG") 
 Next
 ;body scarring
 For count=0 To 4
  tBodyScar(count)=LoadTexture("Costumes/Scarring/Body"+Dig$(count,10)+".JPG") 
 Next
 ;arm scarring
 For count=0 To 4
  tArmScar(count)=LoadTexture("Costumes/Scarring/Arm"+Dig$(count,10)+".JPG") 
 Next
 ;leg scarring
 For count=0 To 4
  tLegScar(count)=LoadTexture("Costumes/Scarring/Legs"+Dig$(count,10)+".JPG") 
 Next 
 ;additional damage
 tSeverArms=LoadTexture("Costumes/Scarring/Sever_Arms.JPG")
 tSeverLegs=LoadTexture("Costumes/Scarring/Sever_Legs.JPG") 
 tCharred=LoadTexture("Costumes/Scarring/Charred.JPG")  
 ;Hair Textures
 Loader("Please Wait","Loading Hair")
 For count=1 To no_hairs
  tHair(count)=LoadTexture("Costumes/Hair/Hair"+Dig$(count,10)+".JPG")
 Next
 ;Face Textures
 Loader("Please Wait","Loading Faces")
 For count=1 To no_faces
  tFace(count)=LoadTexture("Costumes/Faces/Face"+Dig$(count,10)+".JPG")
 Next
 ;Body Textures
 Loader("Please Wait","Loading Bodies")
 For count=1 To no_bodies
  tBody(count)=LoadTexture("Costumes/Bodies/Body"+Dig$(count,10)+".JPG")
 Next
 ;Arm Textures
 Loader("Please Wait","Loading Arms")
 For count=1 To no_arms
  tArms(count)=LoadTexture("Costumes/Arms/Arm"+Dig$(count,10)+".JPG")
 Next
 ;Leg Textures
 Loader("Please Wait","Loading Legs")
 For count=1 To no_legs
  tLegs(count)=LoadTexture("Costumes/Legs/Legs"+Dig$(count,10)+".JPG")
 Next  
End Function

;//////////////////////////////////////////////////////
;-------------------- PLAYERS -------------------------
;//////////////////////////////////////////////////////
.Players
Dim p(optPlayLim)
Dim pX#(optPlayLim)
Dim pY#(optPlayLim)
Dim pZ#(optPlayLim)
Dim pOldX#(optPlayLim)
Dim pOldZ#(optPlayLim)
Dim pOldProjX#(optPlayLim)
Dim pOldProjZ#(optPlayLim)
Dim pFoc(optPlayLim)
Dim pFocTim(optPlayLim)
Dim pA#(optPlayLim)
Dim pOldA#(optPlayLim)
Dim pTA#(optPlayLim)
Dim pHurtA#(optPlayLim)
Dim pStagger#(optPlayLim)
Dim pDefSpeed#(optPlayLim)
Dim pSpeed#(optPlayLim)
Dim pMomentum(optPlayLim)
;physics
Dim pExcusedPlays(optPlayLim)
Dim pExcusedItems(optPlayLim)
Dim pExcusedWorld(optPlayLim)
Dim pExcusedEdges(optPlayLim)
Dim pFriction(optPlayLim)
Dim pPlatform(optPlayLim) ;1-4=aprons, 5-8=turnbuckles, 9=railing, 10+=item
Dim pPlatX#(optPlayLim)
Dim pPlatY#(optPlayLim)
Dim pPlatZ#(optPlayLim)
Dim pGravity#(optPlayLim)
Dim pFlight#(optPlayLim)
Dim pFlightA#(optPlayLim)
Dim pPeak#(optPlayLim)
Dim pPeaked(optPlayLim)
Dim pGround#(optPlayLim)
Dim pElevation#(optPlayLim)
Dim pElevator#(optPlayLim)
;status
Dim pState(optPlayLim)
Dim pSting(optPlayLim)
Dim pHeat(optPlayLim)
Dim pSpecial(optPlayLim)
Dim pHealth(optPlayLim)
Dim pHealthLimit(optPlayLim)
Dim pHP(optPlayLim)
Dim pDT(optPlayLim)
Dim pDazed(optPlayLim)
Dim pInjured(optPlayLim)
Dim pCarrying(optPlayLim)
Dim pHolding(optPlayLim)
Dim pGrappling(optPlayLim)
Dim pGrappler(optPlayLim)
;refereeing
Dim pRole(optPlayLim) ;0=wrestler, 1=ref, 2=manager, 3=intruder
Dim pPinStyle(optPlayLim)
Dim pPinning(optPlayLim)
Dim pPinner(optPlayLim)
Dim pStretched(optPlayLim)
Dim pRefAnger(optPlayLim)
Dim pRefAward(optPlayLim)
Dim pRefVictim(optPlayLim)
Dim pRefCount(optPlayLim)
Dim pRefSpeed(optPlayLim)
Dim pChecked(optPlayLim)
Dim pFalls(optPlayLim)
Dim pAutoTim(optPlayLim)
Dim pSubmitDelay(optPlayLim)
Dim pDQDelay(optPlayLim)
Dim pRefGesture(optPlayLim)
;teams
Dim pTeam(optPlayLim)
Dim pChaosTim(optPlayLim)
Dim pEliminated(optPlayLim)
Dim teamLegal(optPlayLim) 
Dim teamFalls(optPlayLim) 
Dim teamMen(optPlayLim) 
Dim teamX#(optPlayLim)
Dim teamZ#(optPlayLim)
teamX#(1)=-100 : teamZ#(1)=-100
teamX#(2)=100 : teamZ#(2)=100
;tags
Dim pTag(optPlayLim)
Dim pOldTag(optPlayLim)
Dim pTagX#(optPlayLim)
Dim pTagY#(optPlayLim)
Dim pTagZ#(optPlayLim)
;triggers
Global controlTim
Dim pControl(optPlayLim)
Dim cUp(optPlayLim)
Dim cDown(optPlayLim)
Dim cLeft(optPlayLim)
Dim cRight(optPlayLim)
Dim cAttack(optPlayLim)
Dim cRun(optPlayLim)
Dim cGrab(optPlayLim)
Dim cPick(optPlayLim)
Dim cSwitch(optPlayLim)
Dim cTaunt(optPlayLim)
;AI
Dim pOutTim(optPlayLim)
Dim pAgenda(optPlayLim)
Dim pRunAgenda(optPlayLim)
Dim pTarget(optPlayLim)
Dim pTX#(optPlayLim)
Dim pTZ#(optPlayLim)
Dim pSubX#(optPlayLim)
Dim pSubZ#(optPlayLim)
Dim pActX#(optPlayLim)
Dim pActZ#(optPlayLim)
Dim pLeaveX#(optPlayLim)
Dim pLeaveZ#(optPlayLim)
Dim pFollow(optPlayLim)
Dim pNowhere(optPlayLim)
Dim pSatisfied(optPlayLim)
;animation
Dim pSeq(optPlayLim,620)
Dim pAnim(optPlayLim)
Dim pOldAnim(optPlayLim)
Dim pAnimTim(optPlayLim)
Dim pRevAnim(optPlayLim)
Dim pRevTim#(optPlayLim)
Dim pRevSpeed#(optPlayLim)
Dim pStepTim(optPlayLim)
Dim pAnimSpeed#(optPlayLim)
;appearance
Dim pName$(optPlayLim)
Dim pChar(optPlayLim)
Dim pCostume(optPlayLim)
Dim pWeapon(optPlayLim)
Dim pHeel(optPlayLim)
Dim pEyes(optPlayLim)
Dim pOldEyes(optPlayLim)
Dim pMouth(optPlayLim)
Dim pSpeaking(optPlayLim)
Dim pShadow(optPlayLim)
Dim pHidden(optPlayLim)

;/////////////////////////////////////////////////////////
;------------------ HEALTH DISPLAYS ----------------------
;/////////////////////////////////////////////////////////
Dim healthPosX(30),healthPosY(30)
;TEAM ONE SLOTS (1-15)
x=300 : y=60
;row 1
healthPosX(1)=x : healthPosY(1)=y
healthPosX(2)=x-110 : healthPosY(2)=y
healthPosX(3)=x-220 : healthPosY(3)=y
;row 2
healthPosX(4)=x-165 : healthPosY(4)=y+60
healthPosX(5)=x-55 : healthPosY(5)=y+60
;row 3
healthPosX(6)=x-220 : healthPosY(6)=y+120
healthPosX(7)=x-110 : healthPosY(7)=y+120
healthPosX(8)=x : healthPosY(8)=y+120
;row 4
healthPosX(9)=x-165 : healthPosY(9)=y+180
healthPosX(10)=x-55 : healthPosY(10)=y+180
;row 5
healthPosX(11)=x-220 : healthPosY(11)=y+240
healthPosX(12)=x-110 : healthPosY(12)=y+240
healthPosX(13)=x : healthPosY(13)=y+240
;row 6
healthPosX(14)=x-165 : healthPosY(14)=y+320
healthPosX(15)=x-55 : healthPosY(15)=y+320
;TEAM TWO SLOTS (16-30)
x=500 : y=60
;row 1
healthPosX(16)=x : healthPosY(16)=y
healthPosX(17)=x+110 : healthPosY(17)=y
healthPosX(18)=x+220 : healthPosY(18)=y
;row 2
healthPosX(19)=x+165 : healthPosY(19)=y+60
healthPosX(20)=x+55 : healthPosY(20)=y+60
;row 3
healthPosX(21)=x+220 : healthPosY(21)=y+120
healthPosX(22)=x+110 : healthPosY(22)=y+120
healthPosX(23)=x : healthPosY(23)=y+120
;row 4
healthPosX(24)=x+165 : healthPosY(24)=y+180
healthPosX(25)=x+55 : healthPosY(25)=y+180
;row 5
healthPosX(26)=x+220 : healthPosY(26)=y+240
healthPosX(27)=x+110 : healthPosY(27)=y+240
healthPosX(28)=x : healthPosY(28)=y+240
;row 6
healthPosX(29)=x+165 : healthPosY(29)=y+320
healthPosX(30)=x+55 : healthPosY(30)=y+320

;/////////////////////////////////////////////////////////
;-------------------- CHARACTERS -------------------------
;/////////////////////////////////////////////////////////
.Characters
;profile
Dim charPhoto(optCharLim)
Dim charName$(optCharLim)
Dim charAge(optCharLim)
Dim charHeight(optCharLim)
Dim charPop(optCharLim)
Dim charStr(optCharLim)
Dim charSkl(optCharLim)
Dim charAgl(optCharLim)
Dim charStam(optCharLim)
Dim charTough(optCharLim)
Dim charAtt(optCharLim)
Dim charHap(optCharLim)
Dim charHeel(optCharLim) 
Dim charEyes(optCharLim) 
Dim charFriend(optCharLim)
Dim charEnemy(optCharLim)
Dim charRealFriend(optCharLim)
Dim charRealEnemy(optCharLim)
Dim charManager(optCharLim)
Dim charTheme(optCharLim)
Dim charThemePitch(optCharLim)
Dim charLight(optCharLim)
Dim charWeapon(optCharLim)
;old values
Dim charOldPop(optCharLim)
Dim charOldStr(optCharLim)
Dim charOldSkl(optCharLim)
Dim charOldAgl(optCharLim)
Dim charOldStam(optCharLim)
Dim charOldTough(optCharLim)
Dim charOldAtt(optCharLim)
Dim charOldHap(optCharLim)
;new values
Dim charNewPop(optCharLim)
Dim charNewStr(optCharLim)
Dim charNewSkl(optCharLim)
Dim charNewAgl(optCharLim)
Dim charNewStam(optCharLim)
Dim charNewTough(optCharLim)
Dim charNewAtt(optCharLim)
Dim charNewHap(optCharLim)
;appearance
Dim charLimb(optCharLim,40)
Dim charModel(optCharLim,3)
Dim charHairStyle(optCharLim,3)
Dim charHat(optCharLim,3)
Dim charHatCol(optCharLim,3,3)
Dim charSpecs(optCharLim,3)
Dim charSpecsCol(optCharLim,3,3)
Dim charHair(optCharLim,3)
Dim charHairCol(optCharLim,3,3)
Dim charFace(optCharLim,3)
Dim charFaceCol(optCharLim,3,3)
Dim charBody(optCharLim,3)
Dim charBodyCol(optCharLim,3,3)
Dim charLeftArm(optCharLim,3)
Dim charLeftArmCol(optCharLim,3,3)
Dim charRightArm(optCharLim,3)
Dim charRightArmCol(optCharLim,3,3)
Dim charLegs(optCharLim,3)
Dim charLegsCol(optCharLim,3,3)
;move set
Dim charAttack(optCharLim,5) ;1=punch, 2=kick, 3=big, 4=running, 5=flying
Dim charCrush(optCharLim,5) ;1/2=stomp, 3=big, 4=running, 5=flying
Dim charStance(optCharLim,2) ;1=stance, 2=movement
Dim charTaunt(optCharLim,2) ;1=taunt, 2=special
Dim charGroundMove(optCharLim,6) ;1-3=head, 4-6=legs
Dim charMove(optCharLim,15) ;1-12=standard, 13=momentum, 14=turnbuckle, 15=special
;exporters
Dim charMoveName$(optCharLim,15)
Dim charGroundMoveName$(optCharLim,6)
Dim charAttackName$(optCharLim,5)
Dim charCrushName$(optCharLim,5)
;status
Dim charFed(optCharLim)
Dim charHealth(optCharLim)
Dim charNewHealth(optCharLim)
Dim charInjured(optCharLim)
Dim charOldInjured(optCharLim)
Dim charVacant(optCharLim)
Dim charOldVacant(optCharLim)
Dim charTrainCourse(optCharLim)
Dim charTrainLevel(optCharLim) ;1=mild, 2=average, 3=intense
Dim charSalary(optCharLim)
Dim charContract(optCharLim)
;handles
Dim charEvent(optCharLim)
Dim charSelected(optCharLim)
Dim charVariable(optCharLim,3)
Dim charOldVariable(optCharLim,3)
;rankings
Dim charRanked(optCharLim)
Dim chartChar(10)
;stat pursuits
Dim statLevel(6)
statLevel(1)=30
statLevel(2)=50
statLevel(3)=70
statLevel(4)=85
statLevel(5)=100
statLevel(6)=100

;///////////////////////////////////////////////////////
;----------------------- LIMBS -------------------------
;///////////////////////////////////////////////////////
.Limbs
;status
Dim pLimb(optPlayLim,40)
Dim pScar(optPlayLim,40)
Dim pOldScar(optPlayLim,40)
;heirarchy
Dim limbPrecede(40)
Dim limbSource(40)
;left arm
limbPrecede(4)=5 : limbSource(4)=3 ;left bicep
limbPrecede(5)=6 : limbSource(5)=4 ;left arm
limbPrecede(6)=0 : limbSource(6)=5 ;left hand
limbPrecede(7)=8 : limbSource(7)=6 ;left thumb01
limbPrecede(8)=0 : limbSource(8)=7 ;left thumb02
limbPrecede(9)=10 : limbSource(9)=6 ;left finger01
limbPrecede(10)=0 : limbSource(10)=9 ;left finger02
limbPrecede(11)=12 : limbSource(11)=6 ;left finger03
limbPrecede(12)=0 : limbSource(12)=11 ;left finger04
limbPrecede(13)=14 : limbSource(13)=6 ;left finger05
limbPrecede(14)=0 : limbSource(14)=13 ;left finger06
limbPrecede(15)=16 : limbSource(15)=6 ;left finger07
limbPrecede(16)=0 : limbSource(16)=15 ;left finger08
;right arm
limbPrecede(17)=18 : limbSource(17)=3 ;right bicep
limbPrecede(18)=19 : limbSource(18)=17 ;right arm
limbPrecede(19)=0 : limbSource(19)=18 ;right hand
limbPrecede(20)=21 : limbSource(20)=19 ;right thumb01
limbPrecede(21)=0 : limbSource(21)=20 ;right thumb02
limbPrecede(22)=23 : limbSource(22)=19 ;right finger01
limbPrecede(23)=0 : limbSource(23)=22 ;right finger02
limbPrecede(24)=25 : limbSource(24)=19 ;right finger03
limbPrecede(25)=0 : limbSource(25)=24 ;right finger04
limbPrecede(26)=27 : limbSource(26)=19 ;right finger05
limbPrecede(27)=0 : limbSource(27)=26 ;right finger06
limbPrecede(28)=29 : limbSource(28)=19 ;right finger07
limbPrecede(29)=0 : limbSource(29)=28 ;right finger08
;legs
limbPrecede(31)=32 : limbSource(31)=30 ;left thigh
limbPrecede(32)=33 : limbSource(32)=31 ;left leg
limbPrecede(33)=0 : limbSource(33)=32 ;left foot
limbPrecede(34)=35 : limbSource(34)=30 ;right thigh
limbPrecede(35)=36 : limbSource(35)=34 ;right leg
limbPrecede(36)=0 : limbSource(36)=35 ;right foot

;///////////////////////////////////////////////////////
;---------------------- ATTACKS ------------------------
;///////////////////////////////////////////////////////
.Attacks
;properties
Dim no_attacks(5) ;1=punch, 2=kick, 3=big, 4=running, 5=flying
Dim attackName$(5,20)
Dim attackSpeed#(5,20)
Dim attackLength(5,20)
Dim attackTravel#(5,20)
Dim attackMomentum(5,20)
Dim attackFall(5,20)
Dim attackImpact(5,20)
Dim attackRange(5,20)
Dim attackWeapon(5,20)
;upper attacks
no_attacks(1)=7
n=1 : attackName$(1,n)="Stiff Punch"
attackSpeed#(1,n)=3.0 : attackLength(1,n)=20
attackTravel#(1,n)=0.15 : attackMomentum(1,n)=14 : attackFall(1,n)=0
attackImpact(1,n)=12 : attackRange(1,n)=6 : attackWeapon(1,n)=1
n=2 : attackName$(1,n)="Uppercut"
attackSpeed#(1,n)=3.0 : attackLength(1,n)=28
attackTravel#(1,n)=0.2 : attackMomentum(1,n)=21 : attackFall(1,n)=0
attackImpact(1,n)=15 : attackRange(1,n)=6 : attackWeapon(1,n)=1
n=3 : attackName$(1,n)="Jab"
attackSpeed#(1,n)=2.7 : attackLength(1,n)=20
attackTravel#(1,n)=0.2 : attackMomentum(1,n)=17 : attackFall(1,n)=0
attackImpact(1,n)=11 : attackRange(1,n)=6 : attackWeapon(1,n)=1
n=4 : attackName$(1,n)="Open Hand Punch"
attackSpeed#(1,n)=2.5 : attackLength(1,n)=22
attackTravel#(1,n)=0.15 : attackMomentum(1,n)=15 : attackFall(1,n)=0
attackImpact(1,n)=13 : attackRange(1,n)=5 : attackWeapon(1,n)=1
n=5 : attackName$(1,n)="Slap"
attackSpeed#(1,n)=3.0 : attackLength(1,n)=30
attackTravel#(1,n)=0.15 : attackMomentum(1,n)=25 : attackFall(1,n)=0
attackImpact(1,n)=13 : attackRange(1,n)=6 : attackWeapon(1,n)=1
n=6 : attackName$(1,n)="Chest Chop"
attackSpeed#(1,n)=3.0 : attackLength(1,n)=32
attackTravel#(1,n)=0.2 : attackMomentum(1,n)=22 : attackFall(1,n)=0
attackImpact(1,n)=12 : attackRange(1,n)=5 : attackWeapon(1,n)=1
n=7 : attackName$(1,n)="High Kick"
attackSpeed#(1,n)=3.75 : attackLength(1,n)=26
attackTravel#(1,n)=0.15 : attackMomentum(1,n)=18 : attackFall(1,n)=0
attackImpact(1,n)=14 : attackRange(1,n)=8 : attackWeapon(1,n)=0
;lower attacks
no_attacks(2)=3
n=1 : attackName$(2,n)="Kick"
attackSpeed#(2,n)=2.5 : attackLength(2,n)=28
attackTravel#(2,n)=0.15 : attackMomentum(2,n)=15 : attackFall(2,n)=0
attackImpact(2,n)=14 : attackRange(2,n)=6 : attackWeapon(2,n)=0
n=2 : attackName$(2,n)="Side Kick"
attackSpeed#(2,n)=3.0 : attackLength(2,n)=25
attackTravel#(2,n)=0.2 : attackMomentum(2,n)=16 : attackFall(2,n)=0
attackImpact(2,n)=9 : attackRange(2,n)=7 : attackWeapon(2,n)=0
n=3 : attackName$(2,n)="Body Blow"
attackSpeed#(2,n)=3.0 : attackLength(2,n)=28
attackTravel#(2,n)=0.2 : attackMomentum(2,n)=21 : attackFall(2,n)=0
attackImpact(2,n)=14 : attackRange(2,n)=5 : attackWeapon(2,n)=1
;big attacks
no_attacks(3)=15
n=1 : attackName$(3,n)="Axe-Handle"
attackSpeed#(3,n)=2.0 : attackLength(3,n)=37
attackTravel#(3,n)=0.15 : attackMomentum(3,n)=22 : attackFall(3,n)=0
attackImpact(3,n)=17 : attackRange(3,n)=5 : attackWeapon(3,n)=1
n=2 : attackName$(3,n)="Spinning Punch"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=48
attackTravel#(3,n)=0.3 : attackMomentum(3,n)=36 : attackFall(3,n)=0
attackImpact(3,n)=28 : attackRange(3,n)=6 : attackWeapon(3,n)=1
n=3 : attackName$(3,n)="Forearm Strike"
attackSpeed#(3,n)=2.0 : attackLength(3,n)=32
attackTravel#(3,n)=0.3 : attackMomentum(3,n)=28 : attackFall(3,n)=0
attackImpact(3,n)=16 : attackRange(3,n)=5 : attackWeapon(3,n)=1
n=4 : attackName$(3,n)="Clothesline"
attackSpeed#(3,n)=2.0 : attackLength(3,n)=32
attackTravel#(3,n)=0.25 : attackMomentum(3,n)=28 : attackFall(3,n)=0
attackImpact(3,n)=16 : attackRange(3,n)=5 : attackWeapon(3,n)=1
n=5 : attackName$(3,n)="Diving Clothesline"
attackSpeed#(3,n)=2.2 : attackLength(3,n)=59
attackTravel#(3,n)=0.35 : attackMomentum(3,n)=40 : attackFall(3,n)=27
attackImpact(3,n)=17 : attackRange(3,n)=6 : attackWeapon(3,n)=1
n=6 : attackName$(3,n)="Body Tackle"
attackSpeed#(3,n)=2.2 : attackLength(3,n)=59
attackTravel#(3,n)=0.35 : attackMomentum(3,n)=40 : attackFall(3,n)=27
attackImpact(3,n)=17 : attackRange(3,n)=6 : attackWeapon(3,n)=1
n=7 : attackName$(3,n)="Dropkick"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=56
attackTravel#(3,n)=0.25 : attackMomentum(3,n)=40 : attackFall(3,n)=34
attackImpact(3,n)=21 : attackRange(3,n)=7 : attackWeapon(3,n)=0
n=8 : attackName$(3,n)="Flying Kick"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=43
attackTravel#(3,n)=0.4 : attackMomentum(3,n)=38 : attackFall(3,n)=33
attackImpact(3,n)=20 : attackRange(3,n)=8 : attackWeapon(3,n)=0
n=9 : attackName$(3,n)="Spinning Kick"
attackSpeed#(3,n)=3.2 : attackLength(3,n)=43
attackTravel#(3,n)=0.4 : attackMomentum(3,n)=33 : attackFall(3,n)=33
attackImpact(3,n)=24 : attackRange(3,n)=7 : attackWeapon(3,n)=0
n=10 : attackName$(3,n)="Cartwheel Kick"
attackSpeed#(3,n)=2.8 : attackLength(3,n)=64
attackTravel#(3,n)=0.4 : attackMomentum(3,n)=42 : attackFall(3,n)=32
attackImpact(3,n)=26 : attackRange(3,n)=5 : attackWeapon(3,n)=0
n=11 : attackName$(3,n)="Shuffle Kick"
attackSpeed#(3,n)=3.2 : attackLength(3,n)=32
attackTravel#(3,n)=0.3 : attackMomentum(3,n)=26 : attackFall(3,n)=0
attackImpact(3,n)=15 : attackRange(3,n)=7 : attackWeapon(3,n)=0
n=12 : attackName$(3,n)="Big Boot"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=45
attackTravel#(3,n)=0.2 : attackMomentum(3,n)=39 : attackFall(3,n)=0
attackImpact(3,n)=15 : attackRange(3,n)=6 : attackWeapon(3,n)=0
n=13 : attackName$(3,n)="Fire Breath"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=43
attackTravel#(3,n)=0.15 : attackMomentum(3,n)=30 : attackFall(3,n)=0
attackImpact(3,n)=22 : attackRange(3,n)=9 : attackWeapon(3,n)=7
n=14 : attackName$(3,n)="Green Mist"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=43
attackTravel#(3,n)=0.15 : attackMomentum(3,n)=30 : attackFall(3,n)=0
attackImpact(3,n)=22 : attackRange(3,n)=9 : attackWeapon(3,n)=9
n=15 : attackName$(3,n)="Dust Attack"
attackSpeed#(3,n)=3.0 : attackLength(3,n)=36
attackTravel#(3,n)=0.15 : attackMomentum(3,n)=26 : attackFall(3,n)=0
attackImpact(3,n)=22 : attackRange(3,n)=7 : attackWeapon(3,n)=2
;running attacks
no_attacks(4)=8
n=1 : attackName$(4,n)="Forearm Strike"
attackSpeed#(4,n)=2.0 : attackLength(4,n)=32
attackTravel#(4,n)=0.08 : attackMomentum(4,n)=28 : attackFall(4,n)=0
attackImpact(4,n)=16 : attackRange(4,n)=5 : attackWeapon(4,n)=1
n=2 : attackName$(4,n)="Clothesline"
attackSpeed#(4,n)=2.0 : attackLength(4,n)=32
attackTravel#(4,n)=0.1 : attackMomentum(4,n)=28 : attackFall(4,n)=0
attackImpact(4,n)=16 : attackRange(4,n)=5 : attackWeapon(4,n)=1
n=3 : attackName$(4,n)="Diving Clothesline"
attackSpeed#(4,n)=2.2 : attackLength(4,n)=59
attackTravel#(4,n)=0.07 : attackMomentum(4,n)=40 : attackFall(4,n)=27
attackImpact(4,n)=17 : attackRange(4,n)=6 : attackWeapon(4,n)=1
n=4 : attackName$(4,n)="Body Tackle"
attackSpeed#(4,n)=2.2 : attackLength(4,n)=59
attackTravel#(4,n)=0.07 : attackMomentum(4,n)=40 : attackFall(4,n)=27
attackImpact(4,n)=17 : attackRange(4,n)=6 : attackWeapon(4,n)=1
n=5 : attackName$(4,n)="Dropkick"
attackSpeed#(4,n)=3.0 : attackLength(4,n)=56
attackTravel#(4,n)=0.1 : attackMomentum(4,n)=45 : attackFall(4,n)=34
attackImpact(4,n)=21 : attackRange(4,n)=7 : attackWeapon(4,n)=0
n=6 : attackName$(4,n)="Flying Kick"
attackSpeed#(4,n)=3.0 : attackLength(4,n)=43
attackTravel#(4,n)=0.08 : attackMomentum(4,n)=38 : attackFall(4,n)=33
attackImpact(4,n)=20 : attackRange(4,n)=8 : attackWeapon(4,n)=0
n=7 : attackName$(4,n)="Spinning Kick"
attackSpeed#(4,n)=3.2 : attackLength(4,n)=43
attackTravel#(4,n)=0.08 : attackMomentum(4,n)=33 : attackFall(4,n)=33
attackImpact(4,n)=24 : attackRange(4,n)=7 : attackWeapon(4,n)=0
n=8 : attackName$(4,n)="Cartwheel Kick"
attackSpeed#(4,n)=2.8 : attackLength(4,n)=64
attackTravel#(4,n)=0.05 : attackMomentum(4,n)=42 : attackFall(4,n)=32
attackImpact(4,n)=26 : attackRange(4,n)=5 : attackWeapon(4,n)=0
;flying attacks
no_attacks(5)=4
n=1 : attackName$(5,n)="Axe-Handle"
attackSpeed#(5,n)=1.7 : attackLength(5,n)=58
attackTravel#(5,n)=0 : attackMomentum(5,n)=48 : attackFall(5,n)=0
attackImpact(5,n)=10 : attackRange(5,n)=5 : attackWeapon(5,n)=1
n=2 : attackName$(5,n)="Clothesline"
attackSpeed#(5,n)=1.7 : attackLength(5,n)=76
attackTravel#(5,n)=1 : attackMomentum(5,n)=58 : attackFall(5,n)=1
attackImpact(5,n)=10 : attackRange(5,n)=6 : attackWeapon(5,n)=1
n=3 : attackName$(5,n)="Missile Dropkick"
attackSpeed#(5,n)=1.7 : attackLength(5,n)=67
attackTravel#(5,n)=3 : attackMomentum(5,n)=50 : attackFall(5,n)=1
attackImpact(5,n)=12 : attackRange(5,n)=7 : attackWeapon(5,n)=0
n=4 : attackName$(5,n)="Cartwheel Kick"
attackSpeed#(5,n)=1.7 : attackLength(5,n)=73
attackTravel#(5,n)=2 : attackMomentum(5,n)=47 : attackFall(5,n)=1
attackImpact(5,n)=18 : attackRange(5,n)=5 : attackWeapon(5,n)=0

;///////////////////////////////////////////////////////
;---------------------- CRUSHES ------------------------
;///////////////////////////////////////////////////////
.Crushes
;properties
Dim no_crushes(5) ;1/2=stomp, 3=big, 4=running, 5=flying
Dim crushName$(5,20)
Dim crushSpeed#(5,20)
Dim crushLength(5,20)
Dim crushTravel#(5,20)
Dim crushMomentum(5,20)
Dim crushFall(5,20) ;2=splash, 3=moonsault, 4=senton bomb, 5=elbow drop, 6=standing
Dim crushImpact(5,20)
Dim crushRange(5,20)
Dim crushWeapon(5,20)
;stomps
no_crushes(2)=2
n=1 : crushName$(2,n)="Stomp"
crushSpeed#(2,n)=2.5 : crushLength(2,n)=24
crushTravel#(2,n)=0.1 : crushMomentum(2,n)=12 : crushFall(2,n)=0
crushImpact(2,n)=12 : crushRange(2,n)=4 : crushWeapon(2,n)=0
n=2 : crushName$(2,n)="Kick"
crushSpeed#(2,n)=2.5 : crushLength(2,n)=24
crushTravel#(2,n)=0.1 : crushMomentum(2,n)=12 : crushFall(2,n)=0
crushImpact(2,n)=14 : crushRange(2,n)=4 : crushWeapon(2,n)=0
;big crushes
no_crushes(3)=6
n=1 : crushName$(3,n)="Elbow Drop"
crushSpeed#(3,n)=3.0 : crushLength(3,n)=68
crushTravel#(3,n)=0.1 : crushMomentum(3,n)=45 : crushFall(3,n)=58
crushImpact(3,n)=28 : crushRange(3,n)=6 : crushWeapon(3,n)=1
n=2 : crushName$(3,n)="Knee Drop"
crushSpeed#(3,n)=2.2 : crushLength(3,n)=52
crushTravel#(3,n)=0 : crushMomentum(3,n)=0 : crushFall(3,n)=38
crushImpact(3,n)=25 : crushRange(3,n)=5 : crushWeapon(3,n)=0
n=3 : crushName$(3,n)="Leg Drop"
crushSpeed#(3,n)=2.5 : crushLength(3,n)=76
crushTravel#(3,n)=0.1 : crushMomentum(3,n)=40 : crushFall(3,n)=65
crushImpact(3,n)=28 : crushRange(3,n)=6 : crushWeapon(3,n)=1
n=4 : crushName$(3,n)="Splash"
crushSpeed#(3,n)=2.7 : crushLength(3,n)=61
crushTravel#(3,n)=0.1 : crushMomentum(3,n)=42 : crushFall(3,n)=52
crushImpact(3,n)=29 : crushRange(3,n)=6 : crushWeapon(3,n)=1
n=5 : crushName$(3,n)="Moonsault"
crushSpeed#(3,n)=2.2 : crushLength(3,n)=68
crushTravel#(3,n)=0.2 : crushMomentum(3,n)=46 : crushFall(3,n)=58
crushImpact(3,n)=34 : crushRange(3,n)=6 : crushWeapon(3,n)=1
n=6 : crushName$(3,n)="Senton Bomb"
crushSpeed#(3,n)=2.5 : crushLength(3,n)=84
crushTravel#(3,n)=0.2 : crushMomentum(3,n)=43 : crushFall(3,n)=72
crushImpact(3,n)=30 : crushRange(3,n)=5 : crushWeapon(3,n)=1
;running crushes
no_crushes(4)=6
n=1 : crushName$(4,n)="Elbow Drop"
crushSpeed#(4,n)=3.0 : crushLength(4,n)=68
crushTravel#(4,n)=0.1 : crushMomentum(4,n)=45 : crushFall(4,n)=58
crushImpact(4,n)=28 : crushRange(4,n)=6 : crushWeapon(4,n)=1
n=2 : crushName$(4,n)="Knee Drop"
crushSpeed#(4,n)=2.2 : crushLength(4,n)=52
crushTravel#(4,n)=0.1 : crushMomentum(4,n)=28 : crushFall(4,n)=38
crushImpact(4,n)=24 : crushRange(4,n)=5 : crushWeapon(4,n)=0
n=3 : crushName$(4,n)="Leg Drop"
crushSpeed#(4,n)=2.5 : crushLength(4,n)=76
crushTravel#(4,n)=0.1 : crushMomentum(4,n)=40 : crushFall(4,n)=65
crushImpact(4,n)=28 : crushRange(4,n)=6 : crushWeapon(4,n)=1
n=4 : crushName$(4,n)="Splash"
crushSpeed#(4,n)=2.7 : crushLength(4,n)=61
crushTravel#(4,n)=0.09 : crushMomentum(4,n)=42 : crushFall(4,n)=52
crushImpact(4,n)=29 : crushRange(4,n)=6 : crushWeapon(4,n)=1
n=5 : crushName$(4,n)="Moonsault"
crushSpeed#(4,n)=2.2 : crushLength(4,n)=68
crushTravel#(4,n)=0.08 : crushMomentum(4,n)=45 : crushFall(4,n)=58
crushImpact(4,n)=34 : crushRange(4,n)=6 : crushWeapon(4,n)=1
n=6 : crushName$(4,n)="Senton Bomb"
crushSpeed#(4,n)=2.5 : crushLength(4,n)=84
crushTravel#(4,n)=0.08 : crushMomentum(4,n)=43 : crushFall(4,n)=72
crushImpact(4,n)=30 : crushRange(4,n)=5 : crushWeapon(4,n)=1
;flying crushes
no_crushes(5)=9
n=1 : crushName$(5,n)="Elbow Drop"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=93
crushTravel#(5,n)=3 : crushMomentum(5,n)=58 : crushFall(5,n)=6
crushImpact(5,n)=0 : crushRange(5,n)=7 : crushWeapon(5,n)=1
n=2 : crushName$(5,n)="Knee Drop"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=91
crushTravel#(5,n)=0 : crushMomentum(5,n)=79 : crushFall(5,n)=6
crushImpact(5,n)=0 : crushRange(5,n)=4 : crushWeapon(5,n)=1
n=3 : crushName$(5,n)="Leg Drop"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=93
crushTravel#(5,n)=2 : crushMomentum(5,n)=55 : crushFall(5,n)=6
crushImpact(5,n)=0 : crushRange(5,n)=7 : crushWeapon(5,n)=1
n=4 : crushName$(5,n)="Splash"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=70
crushTravel#(5,n)=0 : crushMomentum(5,n)=50 : crushFall(5,n)=2
crushImpact(5,n)=0 : crushRange(5,n)=4 : crushWeapon(5,n)=1
n=5 : crushName$(5,n)="Frog Splash"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=70
crushTravel#(5,n)=0 : crushMomentum(5,n)=50 : crushFall(5,n)=2
crushImpact(5,n)=0 : crushRange(5,n)=4 : crushWeapon(5,n)=1
n=6 : crushName$(5,n)="Moonsault"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=70
crushTravel#(5,n)=0 : crushMomentum(5,n)=52 : crushFall(5,n)=3
crushImpact(5,n)=0 : crushRange(5,n)=4 : crushWeapon(5,n)=1
n=7 : crushName$(5,n)="Shooting Star"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=70
crushTravel#(5,n)=0 : crushMomentum(5,n)=52 : crushFall(5,n)=2
crushImpact(5,n)=0 : crushRange(5,n)=4 : crushWeapon(5,n)=1
n=8 : crushName$(5,n)="Senton Bomb"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=79
crushTravel#(5,n)=0 : crushMomentum(5,n)=60 : crushFall(5,n)=4
crushImpact(5,n)=0 : crushRange(5,n)=0 : crushWeapon(5,n)=1
n=9 : crushName$(5,n)="450 Splash"
crushSpeed#(5,n)=1.7 : crushLength(5,n)=67
crushTravel#(5,n)=0 : crushMomentum(5,n)=45 : crushFall(5,n)=2
crushImpact(5,n)=0 : crushRange(5,n)=4 : crushWeapon(5,n)=1

;///////////////////////////////////////////////////////
;---------------------- GESTURES -----------------------
;///////////////////////////////////////////////////////
.Gestures
;stances
Global no_stances=9
Dim stanceName$(no_stances)
Dim stanceSpeed#(no_stances)
stanceName$(1)="Powerful" : stanceSpeed#(1)=0.15
stanceName$(2)="Straight" : stanceSpeed#(2)=0.15
stanceName$(3)="Normal" : stanceSpeed#(3)=0.15
stanceName$(4)="Pensive" : stanceSpeed#(4)=0.25
stanceName$(5)="Boxing" : stanceSpeed#(5)=0.2
stanceName$(6)="Martial Arts" : stanceSpeed#(6)=0.25
stanceName$(7)="Technical" : stanceSpeed#(7)=0.25
stanceName$(8)="Relaxed" : stanceSpeed#(8)=0.15
stanceName$(9)="Feminine" : stanceSpeed#(9)=0.15
;movements
Global no_walks=6
Dim walkName$(no_walks)
walkName$(1)="Stroll"
walkName$(2)="Normal"
walkName$(3)="Pensive"
walkName$(4)="Boxing"
walkName$(5)="Martial Arts"
walkName$(6)="Feminine"
;taunts
Global no_taunts=50
Dim tauntName$(no_taunts)
Dim tauntSpeed#(no_taunts)
Dim tauntLength(no_taunts)
Dim tauntLoop(no_taunts)
n=1 : tauntName$(n)="Tall Stance" : tauntSpeed#(n)=0.25 : tauntLength(n)=160 : tauntLoop(n)=1
n=2 : tauntName$(n)="Pensive Stance" : tauntSpeed#(n)=0.5 : tauntLength(n)=80 : tauntLoop(n)=1
n=3 : tauntName$(n)="Boxing Stance" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=4 : tauntName$(n)="Martial Arts Stance" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=5 : tauntName$(n)="Technical Stance" : tauntSpeed#(n)=0.5 : tauntLength(n)=80 : tauntLoop(n)=1
n=6 : tauntName$(n)="Relaxed Stance" : tauntSpeed#(n)=0.5 : tauntLength(n)=80 : tauntLoop(n)=1
n=7 : tauntName$(n)="Feminine Stance" : tauntSpeed#(n)=0.5 : tauntLength(n)=80 : tauntLoop(n)=1
n=8 : tauntName$(n)="Zombie Walk" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=9 : tauntName$(n)="Searching" : tauntSpeed#(n)=0.5 : tauntLength(n)=120 : tauntLoop(n)=1
n=10 : tauntName$(n)="Exasperated" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=11 : tauntName$(n)="Point To Side" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=12 : tauntName$(n)="Explaining" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=13 : tauntName$(n)="Shocked" : tauntSpeed#(n)=0.5 : tauntLength(n)=120 : tauntLoop(n)=1
n=14 : tauntName$(n)="Surrender" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=15 : tauntName$(n)="Waving" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=16 : tauntName$(n)="Hands On Hips" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=17 : tauntName$(n)="Clasped Hands" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=18 : tauntName$(n)="Hands Behind Back" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=19 : tauntName$(n)="Folded Arms" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=20 : tauntName$(n)="Prayer" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=21 : tauntName$(n)="Salute" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=22 : tauntName$(n)="Celebration" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=23 : tauntName$(n)="Disco Dance" : tauntSpeed#(n)=1.5 : tauntLength(n)=80 : tauntLoop(n)=1
n=24 : tauntName$(n)="Handshake" : tauntSpeed#(n)=0.5 : tauntLength(n)=60 : tauntLoop(n)=1
n=25 : tauntName$(n)="Rule Out" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=26 : tauntName$(n)="Point At Ground" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=27 : tauntName$(n)="Raised Arms" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=28 : tauntName$(n)="Outstretched Arms" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=29 : tauntName$(n)="One Raised Arm" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=30 : tauntName$(n)="Side Arm Pump" : tauntSpeed#(n)=1.5 : tauntLength(n)=40 : tauntLoop(n)=1
n=31 : tauntName$(n)="Pat Chest" : tauntSpeed#(n)=2.0 : tauntLength(n)=30 : tauntLoop(n)=1
n=32 : tauntName$(n)="Beat Chest" : tauntSpeed#(n)=2.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=33 : tauntName$(n)="Two Birds!" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=1
n=34 : tauntName$(n)="V Signs!" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=1
n=35 : tauntName$(n)="Cupped Ear" : tauntSpeed#(n)=1.8 : tauntLength(n)=61 : tauntLoop(n)=1
n=36 : tauntName$(n)="Bicep Flex" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=37 : tauntName$(n)="Come On" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=1
n=38 : tauntName$(n)="Come Here" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=1
n=39 : tauntName$(n)="Point Forward" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=1
n=40 : tauntName$(n)="Shake Finger" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=1
n=41 : tauntName$(n)="Overhead Arm Pump" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=42 : tauntName$(n)="Overhead Press" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=43 : tauntName$(n)="Crotch Chop" : tauntSpeed#(n)=2.0 : tauntLength(n)=40 : tauntLoop(n)=0
n=44 : tauntName$(n)="X Sign" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=45 : tauntName$(n)="Diamond Sign" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=46 : tauntName$(n)="Throat Slice" : tauntSpeed#(n)=1.0 : tauntLength(n)=40 : tauntLoop(n)=0
n=47 : tauntName$(n)="Wrist Fiddle" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1
n=48 : tauntName$(n)="Contemplation" : tauntSpeed#(n)=1.0 : tauntLength(n)=240 : tauntLoop(n)=0
n=49 : tauntName$(n)="Shake Fist" : tauntSpeed#(n)=1.5 : tauntLength(n)=40 : tauntLoop(n)=1
n=50 : tauntName$(n)="Calm Down" : tauntSpeed#(n)=1.0 : tauntLength(n)=60 : tauntLoop(n)=1

;///////////////////////////////////////////////////////
;----------------------- MOVES -------------------------
;///////////////////////////////////////////////////////
.Moves
;standing moves
Global no_moves=71
Dim movName$(no_moves)
Dim movAnim(no_moves)
movName$(1)="Weak Attack" : movAnim(1)=1
movName$(2)="Strong Attack" : movAnim(2)=2
movName$(3)="Headlock Punch" : movAnim(3)=129
movName$(4)="Side Headlock" : movAnim(4)=133
movName$(5)="Sleeper Hold" : movAnim(5)=166
movName$(6)="Rear Arm Bar" : movAnim(6)=173
movName$(7)="Bear Hug" : movAnim(7)=170
movName$(8)="Standing Clothesline" : movAnim(8)=169
movName$(9)="Spear" : movAnim(9)=167
movName$(10)="Headlock Takedown" : movAnim(10)=158
movName$(11)="Snapmare" : movAnim(11)=125
movName$(12)="Hip Toss" : movAnim(12)=159
movName$(13)="Russian Legsweep" : movAnim(13)=168
movName$(14)="Drop Toe Hold" : movAnim(14)=160
movName$(15)="Flying Head Scissors" : movAnim(15)=165
movName$(16)="Leaping Plancha" : movAnim(16)=140
movName$(17)="Hurricanranna" : movAnim(17)=139
movName$(18)="Snap Suplex" : movAnim(18)=118
movName$(19)="Suplex" : movAnim(19)=117
movName$(20)="Stalling Suplex" : movAnim(20)=116
movName$(21)="Brainbuster" : movAnim(21)=120
movName$(22)="Stalling Brainbuster" : movAnim(22)=119
movName$(23)="Suplex Drop" : movAnim(23)=145
movName$(24)="Back Suplex" : movAnim(24)=148
movName$(25)="German Suplex" : movAnim(25)=147
movName$(26)="Northern Lights Suplex" : movAnim(26)=164
movName$(27)="Gutwrench Suplex" : movAnim(27)=172
movName$(28)="Belly-To-Belly Suplex" : movAnim(28)=121
movName$(29)="Belly-To-Belly Slam" : movAnim(29)=122
movName$(30)="Samoan Drop" : movAnim(30)=162
movName$(31)="Back Body Drop" : movAnim(31)=161
movName$(32)="Bodyslam" : movAnim(32)=115
movName$(33)="Powerslam" : movAnim(33)=124
movName$(34)="Shoulder Powerslam" : movAnim(34)=135
movName$(35)="Shoulder Breaker" : movAnim(35)=136
movName$(36)="Backbreaker" : movAnim(36)=126
movName$(37)="Side Backbreaker" : movAnim(37)=128
movName$(38)="Side Slam" : movAnim(38)=127
movName$(39)="Spinebuster" : movAnim(39)=156
movName$(40)="Inverted Atomic Drop" : movAnim(40)=155
movName$(41)="Atomic Drop" : movAnim(41)=149
movName$(42)="Neckbreaker" : movAnim(42)=157
movName$(43)="Bulldog" : movAnim(43)=130
movName$(44)="DDT" : movAnim(44)=138
movName$(45)="Piledriver" : movAnim(45)=143
movName$(46)="Sitting Powerbomb" : movAnim(46)=142
movName$(47)="Powerbomb" : movAnim(47)=141
movName$(48)="Choke Slam" : movAnim(48)=150
movName$(49)="Throat Toss" : movAnim(49)=152
movName$(50)="Press Slam" : movAnim(50)=132
movName$(51)="Pumping Press Slam" : movAnim(51)=131
movName$(52)="Gorilla Press Slam" : movAnim(52)=134
movName$(53)="Suplex Slam" : movAnim(53)=146
movName$(54)="Samoan Slam" : movAnim(54)=153
movName$(55)="MDKO" : movAnim(55)=154
movName$(56)="Facebreaker" : movAnim(56)=123
movName$(57)="Underhook Facebuster" : movAnim(57)=144
movName$(58)="Jumping Facebuster" : movAnim(58)=171
movName$(59)="Tombstone Piledriver" : movAnim(59)=137
movName$(60)="Death Valley Driver" : movAnim(60)=163
movName$(61)="Reverse DDT" : movAnim(61)=151
movName$(62)="Inverted Piledriver" : movAnim(62)=174
movName$(63)="Underhook Suplex" : movAnim(63)=178
movName$(64)="Full Nelson Suplex" : movAnim(64)=177
movName$(65)="Full Nelson" : movAnim(65)=175
movName$(66)="Cobra Clutch" : movAnim(66)=176
movName$(67)="Flipping Piledriver" : movAnim(67)=179
movName$(68)="Pump Handle Slam" : movAnim(68)=180
movName$(69)="Sitting Bodyslam" : movAnim(69)=181
movName$(70)="Tornado DDT" : movAnim(70)=182
movName$(71)="Reverse Suplex" : movAnim(71)=183
;corner moves
Global no_cornmoves=6
Dim cornName$(no_cornmoves)
Dim cornAnim(no_cornmoves)
cornName$(1)="Drag Down" : cornAnim(1)=412
cornName$(2)="Head Smashes" : cornAnim(2)=413
cornName$(3)="Shoulder Barges" : cornAnim(3)=415
cornName$(4)="Mounted Punches" : cornAnim(4)=411
cornName$(5)="Superplex" : cornAnim(5)=410
cornName$(6)="Hurricanranna" : cornAnim(6)=414
;head moves
Global no_headmoves=10
Dim headName$(no_headmoves)
Dim headAnim(no_headmoves)
headName$(1)="Rolling Head Snap" : headAnim(1)=218
headName$(2)="Mounted Punches" : headAnim(2)=212
headName$(3)="Head Smashes" : headAnim(3)=215
headName$(4)="Choke Hold" : headAnim(4)=214
headName$(5)="Sleeper Hold" : headAnim(5)=211
headName$(6)="Dragon Sleeper" : headAnim(6)=217
headName$(7)="Bow & Arrow" : headAnim(7)=220
headName$(8)="Arm Bar" : headAnim(8)=219
headName$(9)="Camel Clutch" : headAnim(9)=213
headName$(10)="Crossface Clutch" : headAnim(10)=216
;leg moves
Global no_legmoves=10
Dim legName$(no_legmoves)
Dim legAnim(no_legmoves)
legName$(1)="Slingshot" : legAnim(1)=317
legName$(2)="Groin Kick" : legAnim(2)=314
legName$(3)="Spinning Toe Hold" : legAnim(3)=313
legName$(4)="Leg Wrench" : legAnim(4)=320
legName$(5)="Ankle Lock" : legAnim(5)=319
legName$(6)="Figure 4 Leglock" : legAnim(6)=312
legName$(7)="Scorpion Lock" : legAnim(7)=311
legName$(8)="Boston Crab" : legAnim(8)=315
legName$(9)="Half Crab" : legAnim(9)=316
legName$(10)="Elevated Crab" : legAnim(10)=318

;///////////////////////////////////////////////////////
;----------------------- ITEMS -------------------------
;///////////////////////////////////////////////////////
.Items
Global no_items=15,itemLimit=30,itemList=14
Global itemSelection=1,itemLayout=1,itemRotor
;state
Dim i(itemLimit)
Dim iType(itemLimit)
Dim iX#(itemLimit)
Dim iY#(itemLimit)
Dim iZ#(itemLimit)
Dim iA#(itemLimit)
Dim iState(itemLimit) ;0=standing, 1=broken
Dim iOldState(itemLimit)
Dim iCarrier(itemLimit)
Dim iFallA#(itemLimit)
Dim iGround#(itemLimit)
Dim iAnim(itemLimit)
Dim iAnimTim(itemLimit)
Dim iScar(itemLimit)
Dim iOldScar(itemLimit)
Dim iBurning(itemLimit)
Dim iChannel(itemLimit)
Dim iScreen(itemLimit)
Dim iOldScreen(itemLimit)
;dimensions
Dim iDensity#(itemLimit)
Dim iDimX#(itemLimit,1000)
Dim iDimZ#(itemLimit,1000)
;type
Dim iName$(itemList)
Dim iFile$(itemList)
Dim iSound(itemList)
Dim iSizeX#(itemList)
Dim iSizeZ#(itemList)
Dim iElevate#(itemList)
Dim iCarryY#(itemList,2)
Dim iCarryA#(itemList,2)
Dim iCarryDepth#(itemList,2)
Dim iClimb#(itemList)
Dim iDamage(itemList)
Dim iFlammable(itemList)
Dim iExplodable(itemList)
Dim iStandard(itemList)
;PROPERTIES
;wooden table
n=1
iName$(n)="Wooden Table" : iFile$(n)="Items/Table.3DS"
iSound(n)=sSmashWood : iDamage(n)=3
iSizeX#(n)=11 : iSizeZ#(n)=7
iCarryY#(n,0)=-17 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=0 
iCarryDepth#(n,0)=15.5 : iCarryDepth#(n,1)=14
iElevate#(n)=5.0 : iClimb#(n)=16
iFlammable(n)=1 : iExplodable(n)=0
;metal table
n=2
iName$(n)="Metal Table" : iFile$(n)="Items/Table.3DS"
iSound(n)=sSmashMetal : iDamage(n)=5
iSizeX#(n)=11 : iSizeZ#(n)=7
iCarryY#(n,0)=-17 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=0 
iCarryDepth#(n,0)=15.5 : iCarryDepth#(n,1)=14
iElevate#(n)=5.0 : iClimb#(n)=16
iFlammable(n)=0 : iExplodable(n)=0
;announcer's desk
n=3
iName$(n)="Announce Desk" : iFile$(n)="Items/Desk.3DS"
iSound(n)=sSmashWood : iDamage(n)=4
iSizeX#(n)=11 : iSizeZ#(n)=7
iCarryY#(n,0)=-17.5 : iCarryY#(n,1)=-5
iCarryA#(n,0)=0 : iCarryA#(n,1)=0 
iCarryDepth#(n,0)=14.5 : iCarryDepth#(n,1)=17
iElevate#(n)=6.5 : iClimb#(n)=18
iFlammable(n)=1 : iExplodable(n)=0
;cardboard box
n=4
iName$(n)="Cardboard Box" : iFile$(n)="Items/Crate.3DS"
iSound(n)=sSmashCard : iDamage(n)=1
iSizeX#(n)=7 : iSizeZ#(n)=7
iCarryY#(n,0)=-15 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=45 
iCarryDepth#(n,0)=14.75 : iCarryDepth#(n,1)=15.0
iElevate#(n)=5.0 : iClimb#(n)=19.5
iFlammable(n)=1 : iExplodable(n)=0
;wooden crate
n=5
iName$(n)="Wooden Crate" : iFile$(n)="Items/Crate.3DS"
iSound(n)=sSmashWood : iDamage(n)=3
iSizeX#(n)=7 : iSizeZ#(n)=7
iCarryY#(n,0)=-15 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=45 
iCarryDepth#(n,0)=14.75 : iCarryDepth#(n,1)=15.0
iElevate#(n)=5.0 : iClimb#(n)=19.5
iFlammable(n)=1 : iExplodable(n)=0
;metal crate
n=6
iName$(n)="Metal Crate" : iFile$(n)="Items/Crate.3DS"
iSound(n)=sSmashWire : iDamage(n)=5
iSizeX#(n)=7 : iSizeZ#(n)=7
iCarryY#(n,0)=-15 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=45
iCarryDepth#(n,0)=14.75 : iCarryDepth#(n,1)=15.0
iElevate#(n)=5.0 : iClimb#(n)=19.5
iFlammable(n)=0 : iExplodable(n)=0
;monitor
n=7
iName$(n)="Monitor" : iFile$(n)="Items/Monitor.3DS"
iSound(n)=sSmashElectric : iDamage(n)=6
iSizeX#(n)=5 : iSizeZ#(n)=5
iCarryY#(n,0)=-5 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=10.0 : iCarryDepth#(n,1)=10.0
iElevate#(n)=5.0 : iClimb#(n)=11
iFlammable(n)=1 : iExplodable(n)=10
;trashcan
n=8
iName$(n)="Trashcan" : iFile$(n)="Items/Trashcan.3DS"
iSound(n)=sSmashWire : iDamage(n)=4
iSizeX#(n)=5 : iSizeZ#(n)=5
iCarryY#(n,0)=-18 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=12.0 : iCarryDepth#(n,1)=12.0
iElevate#(n)=5.0 : iClimb#(n)=22
iFlammable(n)=0 : iExplodable(n)=0
;chair
n=9
iName$(n)="Chair" : iFile$(n)="Items/Chair.3DS"
iSound(n)=sSmashMetal : iDamage(n)=4
iSizeX#(n)=5 : iSizeZ#(n)=6
iCarryY#(n,0)=-11 : iCarryY#(n,1)=-3
iCarryA#(n,0)=90 : iCarryA#(n,1)=90
iCarryDepth#(n,0)=12.0 : iCarryDepth#(n,1)=12.0
iElevate#(n)=4.5 : iClimb#(n)=11.5
iFlammable(n)=1 : iExplodable(n)=0
;metal ladder
n=10
iName$(n)="Metal Ladder" : iFile$(n)="Items/Ladder.3DS"
iSound(n)=sSmashMetal : iDamage(n)=5
iSizeX#(n)=6 : iSizeZ#(n)=4
iCarryY#(n,0)=-15 : iCarryY#(n,1)=-3
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=11.0 : iCarryDepth#(n,1)=10.0
iElevate#(n)=5.0 : iClimb#(n)=33
iFlammable(n)=0 : iExplodable(n)=0
;wooden ladder
n=11
iName$(n)="Wooden Ladder" : iFile$(n)="Items/Ladder.3DS"
iSound(n)=sSmashWood : iDamage(n)=4
iSizeX#(n)=6 : iSizeZ#(n)=4
iCarryY#(n,0)=-15 : iCarryY#(n,1)=-3
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=11.0 : iCarryDepth#(n,1)=10.0
iElevate#(n)=5.0 : iClimb#(n)=33
iFlammable(n)=1 : iExplodable(n)=0
;barrier
n=12
iName$(n)="Barrier" : iFile$(n)="Items/Railing.3DS"
iSound(n)=sSmashMetal : iDamage(n)=4
iSizeX#(n)=8 : iSizeZ#(n)=4
iCarryY#(n,0)=-14 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=10.0 : iCarryDepth#(n,1)=11.0
iElevate#(n)=5.0 : iClimb#(n)=18
iFlammable(n)=1 : iExplodable(n)=0
;mdickie logo
n=13
iName$(n)="MDickie Logo" : iFile$(n)="Items/Logo.3DS"
iSound(n)=sSmashMetal : iDamage(n)=4
iSizeX#(n)=10 : iSizeZ#(n)=3
iCarryY#(n,0)=-16 : iCarryY#(n,1)=-4
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=7.0 : iCarryDepth#(n,1)=11.0
iElevate#(n)=5.0 : iClimb#(n)=19
iFlammable(n)=1 : iExplodable(n)=0
;glass pane
n=14
iName$(n)="Glass Pane" : iFile$(n)="Items/Glass.3DS"
iSound(n)=sSmashGlass : iDamage(n)=6
iSizeX#(n)=6 : iSizeZ#(n)=2
iCarryY#(n,0)=-16 : iCarryY#(n,1)=0
iCarryA#(n,0)=0 : iCarryA#(n,1)=0
iCarryDepth#(n,0)=7.0 : iCarryDepth#(n,1)=0
iElevate#(n)=2.0 : iClimb#(n)=25
iFlammable(n)=0 : iExplodable(n)=0

;///////////////////////////////////////////////////////
;--------------------- WEAPONS -------------------------
;///////////////////////////////////////////////////////
.Weapons
Global no_weaps=15,weapLimit=50,weapList=28
Global weapSelection=1,weapLayout=1,weapRotor
Global weapCount
;state
Dim weap(weapLimit)
Dim weapType(weapLimit)
Dim weapX#(weapLimit)
Dim weapY#(weapLimit)
Dim weapZ#(weapLimit)
Dim weapOldX#(weapLimit)
Dim weapOldZ#(weapLimit)
Dim weapA#(weapLimit)
Dim weapCarrier(weapLimit)
Dim weapAnim(weapLimit)
Dim weapAnimTim(weapLimit)
Dim weapGround#(weapLimit)
Dim weapBounce#(weapLimit)
Dim weapGravity#(weapLimit)
Dim weapFlight#(weapLimit)
Dim weapFlightA#(weapLimit)
Dim weapSting(weapLimit,optPlayLim)
Dim weapItemSting(weapLimit,itemLimit)
Dim weapScar(weapLimit)
Dim weapOldScar(weapLimit)
Dim weapBurning(weapLimit)
Dim weapChannel(weapLimit)
;type
Dim weapName$(weapList)
Dim weapFile$(weapList)
Dim weapSound(weapList)
Dim weapTex(weapList)
Dim weapSize#(weapList)
Dim weapWeight#(weapList)
Dim weapDamage(weapList)
Dim weapFlammable(weapList)
Dim weapExplodable(weapList)
Dim weapShiny(weapList)
Dim weapBroad(weapList)
Dim weapStandard(weapList)
Dim weapCost(weapList)
;DATA
Function LoadWeaponData()
 weapName$(0)="None"
 ;wooden plank
 n=1
 weapName$(n)="Wooden Plank" : weapFile$(n)="Plank"
 weapSound(n)=sImpactWood : weapTex(n)=tWood(1) : weapShiny(n)=0
 weapSize#(n)=10 : weapWeight#(n)=0.2 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=100 : weapStandard(n)=1 
 ;baseball bat
 n=2
 weapName$(n)="Baseball Bat" : weapFile$(n)="Bat"
 weapSound(n)=sImpactWood : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=10 : weapWeight#(n)=0.25 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=100 : weapStandard(n)=0
 ;wooden cane
 n=3
 weapName$(n)="Wooden Cane" : weapFile$(n)="Cane"
 weapSound(n)=sImpactCane : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=15 : weapWeight#(n)=0.18 : weapDamage(n)=2
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=100 : weapStandard(n)=0
 ;metal pipe
 n=4
 weapName$(n)="Metal Pipe" : weapFile$(n)="Pipe"
 weapSound(n)=sImpactMetal : weapTex(n)=tMetal(1) : weapShiny(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.25 : weapDamage(n)=4
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=200 : weapStandard(n)=1 
 ;microphone
 n=5
 weapName$(n)="Microphone" : weapFile$(n)="Mic"
 weapSound(n)=sImpactMic : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=6 : weapWeight#(n)=0.18 : weapDamage(n)=2
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=200 : weapStandard(n)=1 
 ;sword
 n=6
 weapName$(n)="Samurai Sword" : weapFile$(n)="Sword"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShiny(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.3 : weapDamage(n)=5
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=500 : weapStandard(n)=0
 ;dagger
 n=7
 weapName$(n)="Dagger" : weapFile$(n)="Knife"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShiny(n)=1
 weapSize#(n)=8 : weapWeight#(n)=0.25 : weapDamage(n)=4
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=300 : weapStandard(n)=0
 ;hammer
 n=8
 weapName$(n)="Hammer" : weapFile$(n)="Hammer"
 weapSound(n)=sImpactHammer : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.35 : weapDamage(n)=5
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=300 : weapStandard(n)=0
 ;bell
 n=9
 weapName$(n)="Ring Bell" : weapFile$(n)="Bell"
 weapSound(n)=sImpactBell : weapTex(n)=0 : weapShiny(n)=1
 weapSize#(n)=8 : weapWeight#(n)=0.35 : weapDamage(n)=4
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=200 : weapStandard(n)=1
 ;brass knucks
 n=10
 weapName$(n)="Brass Knucks" : weapFile$(n)="Knucks"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShiny(n)=1
 weapSize#(n)=5 : weapWeight#(n)=0.3 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0 
 weapCost(n)=200 : weapStandard(n)=0 
 ;award
 n=11
 weapName$(n)="Award Statue" : weapFile$(n)="Award"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShiny(n)=1
 weapSize#(n)=6 : weapWeight#(n)=0.3 : weapDamage(n)=4
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=1000 : weapStandard(n)=0
 ;camera
 n=12
 weapName$(n)="Video Camera" : weapFile$(n)="Camera"
 weapSound(n)=sImpactGun : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.35 : weapDamage(n)=4
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=10
 weapCost(n)=500 : weapStandard(n)=1 
 ;tape
 n=13
 weapName$(n)="Video Tape" : weapFile$(n)="Box"
 weapSound(n)=sImpactCane : weapTex(n)=tGame(0) : weapShiny(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.18 : weapDamage(n)=2
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=50 : weapStandard(n)=1 
 ;game
 n=14
 weapName$(n)="Game Box" : weapFile$(n)="Box"
 weapSound(n)=sImpact(5) : weapTex(n)=tGame(1) : weapShiny(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.18 : weapDamage(n)=1
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0 
 weapCost(n)=15 : weapStandard(n)=1 
 ;briefcase
 n=15
 weapName$(n)="Briefcase" : weapFile$(n)="Case"
 weapSound(n)=sImpact(6) : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=12 : weapWeight#(n)=0.25 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapCost(n)=100 : weapStandard(n)=1 
 ;water bottle
 n=16
 weapName$(n)="Water Bottle" : weapFile$(n)="Bottle"
 weapSound(n)=sImpactBottle : weapTex(n)=tBottle(1) : weapShiny(n)=0
 weapSize#(n)=6 : weapWeight#(n)=0.2 : weapDamage(n)=2
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=12
 weapCost(n)=50 : weapStandard(n)=1 
 ;beer bottle
 n=17
 weapName$(n)="Beer Bottle" : weapFile$(n)="Bottle"
 weapSound(n)=sImpactBottle : weapTex(n)=tBottle(2) : weapShiny(n)=0
 weapSize#(n)=6 : weapWeight#(n)=0.2 : weapDamage(n)=2
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=13
 weapCost(n)=50 : weapStandard(n)=1 
 ;fire extinguisher
 n=18
 weapName$(n)="Fire Extinguisher" : weapFile$(n)="Exting"
 weapSound(n)=sImpactMetal : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.25 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=11
 weapCost(n)=300 : weapStandard(n)=1
 ;TNT
 n=19
 weapName$(n)="TNT" : weapFile$(n)="TNT"
 weapSound(n)=sItem : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.2 : weapDamage(n)=2
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=10
 weapCost(n)=1000 : weapStandard(n)=0
 ;handgun
 n=20
 weapName$(n)="Handgun" : weapFile$(n)="Gun"
 weapSound(n)=sImpactGun : weapTex(n)=0 : weapShiny(n)=1
 weapSize#(n)=6 : weapWeight#(n)=0.35 : weapDamage(n)=4
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=10
 weapCost(n)=500 : weapStandard(n)=0
 ;world title
 n=21
 weapName$(n)="World Title" : weapFile$(n)="Belt"
 weapSound(n)=sImpact(6) : weapTex(n)=tBelt(1) : weapShiny(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.25 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0 
 weapCost(n)=1000 : weapStandard(n)=-1
 ;inter title
 n=22
 weapName$(n)="Inter Title" : weapFile$(n)="Belt"
 weapSound(n)=sImpact(6) : weapTex(n)=tBelt(2) : weapShiny(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.25 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0 
 weapCost(n)=1000 : weapStandard(n)=-1
 ;tag title
 n=23
 weapName$(n)="Tag Title" : weapFile$(n)="Belt"
 weapSound(n)=sImpact(6) : weapTex(n)=tBelt(3) : weapShiny(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.25 : weapDamage(n)=3
 weapBroad(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0  
 weapCost(n)=1000 : weapStandard(n)=-1
 ;wooden board
 n=24
 weapName$(n)="Wooden Board" : weapFile$(n)="Board"
 weapSound(n)=sSmashWood : weapTex(n)=tWood(2) : weapShiny(n)=0
 weapSize#(n)=15 : weapWeight#(n)=0.25 : weapDamage(n)=4
 weapBroad(n)=1 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=250 : weapStandard(n)=1 
 ;metal sheet
 n=25
 weapName$(n)="Metal Sheet" : weapFile$(n)="Board"
 weapSound(n)=sSmashMetal : weapTex(n)=tMetal(1) : weapShiny(n)=1
 weapSize#(n)=15 : weapWeight#(n)=0.3 : weapDamage(n)=5
 weapBroad(n)=1 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=500 : weapStandard(n)=1 
 ;steel chair
 n=26
 weapName$(n)="Steel Chair" : weapFile$(n)="Board"
 weapSound(n)=sSmashMetal : weapTex(n)=tChair : weapShiny(n)=1
 weapSize#(n)=15 : weapWeight#(n)=0.3 : weapDamage(n)=5
 weapBroad(n)=1 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=500 : weapStandard(n)=1 
 ;cage piece
 n=27
 weapName$(n)="Cage Piece" : weapFile$(n)="Board"
 weapSound(n)=sSmashWire : weapTex(n)=tCage(3) : weapShiny(n)=1
 weapSize#(n)=15 : weapWeight#(n)=0.25 : weapDamage(n)=5
 weapBroad(n)=1 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=500 : weapStandard(n)=0
 ;thumbtacks
 n=28
 weapName$(n)="Thumbtacks" : weapFile$(n)="Bag"
 weapSound(n)=sImpactTacks : weapTex(n)=0 : weapShiny(n)=0
 weapSize#(n)=10 : weapWeight#(n)=0.25 : weapDamage(n)=5
 weapBroad(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapCost(n)=500 : weapStandard(n)=0
End Function

;///////////////////////////////////////////////////////
;----------------------- WORLD -------------------------
;///////////////////////////////////////////////////////
.World
Global world
Global wGround#=3,wStage#=25.5,wBuckle#=48
Global wVideo,wOldVideo,wVideoCaught
Dim wGhost(20)
Dim wOldGhost(20)
;arena variations
Global no_arenas=19
Global arenaPreset,arenaType
Global arenaWall,arenaBackWall,arenaCeiling
Global arenaGround,arenaBackGround
Global arenaRopes=1,arenaApron=1,arenaCanvas=1,arenaMat=1
Global arenaLight=0,arenaAtmos=3,arenaCrowd=1
;camera
Global cam,camFoc,camTarget
Global camType,camTim,no_cams=16
Global camX#,camY#,camZ#
Global camTX#,camTY#,camTZ#
Global camTempTim,camTempFoc,camOldFoc
Global camOverride,camTempX#,camTempY#,camTempZ#
Global camSpeedX#,camSpeedY#,camSpeedZ#
Global camDiffX#,camDiffY#,camDiffZ#
Global camLead#,camAnchor#,camSpeed
;camera shortcuts
Dim camShortcut(10)
camShortcut(1)=15
camShortcut(2)=2
camShortcut(3)=3
camShortcut(4)=4
camShortcut(5)=6
camShortcut(6)=9
camShortcut(7)=10
camShortcut(8)=13
camShortcut(9)=14
camShortcut(10)=16
;pivots
Global camPivot,camListener,dummy
Global camPivX#,camPivY#,camPivZ#
Global camPivTX#,camPivTY#,camPivTZ#
;lighting
Global lightPreset,no_lights=13
Global light,lightPivot
Global lightR#=250,lightG#=250,lightB#=250
Global lightTR#,lightTG#,lightTB#
Global ambR#=250,ambG#=250,ambB#=250
Global ambTR#,ambTG#,ambTB#
;crowd
Dim crowdSource#(30)
Dim crowdY#(30)
Dim crowdTY#(30)
;ropes
Dim rope(12)
Dim ropeType(12)
Dim ropeX#(12)
Dim ropeY#(12)
Dim ropeZ#(12)
Dim ropeA#(12)
Dim ropeAnim(12)
Dim ropeAnimTim(12)
Dim ropeBurning(12)
Dim ropeChannel(12)
Global ropeFlow#
;pads
Dim padID(4)
Dim padExposed(4)
padID(1)=1 : padID(2)=6
padID(3)=12 : padID(4)=9
;cage
Global cage
Dim cageTim(4)
Dim cageOrigX#(4)
Dim cageOrigZ#(4)
Dim cageX#(4)
Dim cageZ#(4)
Dim cageTX#(4)
Dim cageTZ#(4)

;///////////////////////////////////////////////////////
;-------------------- WORLD BLOCKS ---------------------
;///////////////////////////////////////////////////////
.Blocks
Global no_blocks=32
Dim wBlockX1#(35)
Dim wBlockX2#(35)
Dim wBlockZ1#(35)
Dim wBlockZ2#(35)
Dim wBlockY1#(35)
Dim wBlockY2#(35)
Dim wBlockCam(35)
;ring base
wBlockX1#(0)=-92 : wBlockX2#(0)=93
wBlockZ1#(0)=-95 : wBlockZ2#(0)=91
wBlockY1#(0)=0 : wBlockY2#(0)=25
wBlockCam(0)=1
;ropes (north)
wBlockX1#(1)=wBlockX1#(0) : wBlockX2#(1)=wBlockX2#(0)
wBlockZ1#(1)=66 : wBlockZ2#(1)=81
wBlockY1#(1)=25 : wBlockY2#(1)=45
wBlockCam(1)=0
;ropes (east)
wBlockX1#(2)=68 : wBlockX2#(2)=83
wBlockZ1#(2)=wBlockZ1#(0) : wBlockZ2#(2)=wBlockZ2#(0)
wBlockY1#(2)=25 : wBlockY2#(2)=45
wBlockCam(2)=0
;ropes (south)
wBlockX1#(3)=wBlockX1#(0) : wBlockX2#(3)=wBlockX2#(0)
wBlockZ1#(3)=-85 : wBlockZ2#(3)=-69
wBlockY1#(3)=25 : wBlockY2#(3)=45
wBlockCam(3)=0
;ropes (west)
wBlockX1#(4)=-82 : wBlockX2#(4)=-66
wBlockZ1#(4)=wBlockZ1#(0) : wBlockZ2#(4)=wBlockZ2#(0)
wBlockY1#(4)=25 : wBlockY2#(4)=45
wBlockCam(4)=0
;railing (right aisle)
wBlockX1#(5)=28 : wBlockX2#(5)=45
wBlockZ1#(5)=150 : wBlockZ2#(5)=490
wBlockY1#(5)=0 : wBlockY2#(5)=19.5
wBlockCam(5)=1 
;railing (right top)
wBlockX1#(6)=28 : wBlockX2#(6)=164
wBlockZ1#(6)=150 : wBlockZ2#(6)=169
wBlockY1#(6)=0 : wBlockY2#(6)=19.5
wBlockCam(6)=1 
;railing (right)
wBlockX1#(7)=148 : wBlockX2#(7)=164
wBlockZ1#(7)=-164 : wBlockZ2#(7)=169
wBlockY1#(7)=0 : wBlockY2#(7)=19.5
wBlockCam(7)=1 
;railing (bottom)
wBlockX1#(8)=-160 : wBlockX2#(8)=164
wBlockZ1#(8)=-164 : wBlockZ2#(8)=-147
wBlockY1#(8)=0 : wBlockY2#(8)=19.5
wBlockCam(8)=1 
;railing (left)
wBlockX1#(9)=-160 : wBlockX2#(9)=-144
wBlockZ1#(9)=-164 : wBlockZ2#(9)=169
wBlockY1#(9)=0 : wBlockY2#(9)=19.5
wBlockCam(9)=1 
;railing (left top)
wBlockX1#(10)=-160 : wBlockX2#(10)=-34
wBlockZ1#(10)=150 : wBlockZ2#(10)=169
wBlockY1#(10)=0 : wBlockY2#(10)=19.5
wBlockCam(10)=1 
;railing (left aisle)
wBlockX1#(11)=-50 : wBlockX2#(11)=-34
wBlockZ1#(11)=150 : wBlockZ2#(11)=490
wBlockY1#(11)=0 : wBlockY2#(11)=19.5
wBlockCam(11)=1 
;video leg (left)
wBlockX1#(12)=wBlockX1#(11) : wBlockX2#(12)=-12
wBlockZ1#(12)=452 : wBlockZ2#(12)=478
wBlockY1#(12)=0 : wBlockY2#(12)=200
wBlockCam(12)=1 
;video leg (right)
wBlockX1#(13)=7 : wBlockX2#(13)=wBlockX2#(5)
wBlockZ1#(13)=452 : wBlockZ2#(13)=478
wBlockY1#(13)=0 : wBlockY2#(13)=200
wBlockCam(13)=1 
;video screen
wBlockX1#(14)=-60 : wBlockX2#(14)=55
wBlockZ1#(14)=445 : wBlockZ2#(14)=489
wBlockY1#(14)=45 : wBlockY2#(14)=110
wBlockCam(14)=1 
;gorilla wall (left)
wBlockX1#(15)=-500 : wBlockX2#(15)=-34
wBlockZ1#(15)=475 : wBlockZ2#(15)=573
wBlockY1#(15)=0 : wBlockY2#(15)=250
wBlockCam(15)=1
;gorilla wall (right)
wBlockX1#(16)=28 : wBlockX2#(16)=500
wBlockZ1#(16)=475 : wBlockZ2#(16)=573
wBlockY1#(16)=0 : wBlockY2#(16)=250
wBlockCam(16)=1 
;gorilla ceiling
wBlockX1#(17)=-500 : wBlockX2#(17)=500
wBlockZ1#(17)=475 : wBlockZ2#(17)=573
wBlockY1#(17)=50 : wBlockY2#(17)=250
wBlockCam(17)=1 
;backstage wall (north)
wBlockX1#(18)=-200 : wBlockX2#(18)=260
wBlockZ1#(18)=840 : wBlockZ2#(18)=900
wBlockY1#(18)=0 : wBlockY2#(18)=200
wBlockCam(18)=1 
;backstage wall (east)
wBlockX1#(19)=252 : wBlockX2#(19)=260
wBlockZ1#(19)=500 : wBlockZ2#(19)=900
wBlockY1#(19)=0 : wBlockY2#(19)=200
wBlockCam(19)=1 
;backstage wall (west)
wBlockX1#(20)=-200 : wBlockX2#(20)=-192
wBlockZ1#(20)=500 : wBlockZ2#(20)=900
wBlockY1#(20)=0 : wBlockY2#(20)=200
wBlockCam(20)=1 
;backstage ceiling
wBlockX1#(21)=-200 : wBlockX2#(21)=260
wBlockZ1#(21)=500 : wBlockZ2#(21)=900
wBlockY1#(21)=85 : wBlockY2#(21)=250
wBlockCam(21)=1 
;lockers (top)
wBlockX1#(22)=81 : wBlockX2#(22)=260
wBlockZ1#(22)=818 : wBlockZ2#(22)=900
wBlockY1#(22)=0 : wBlockY2#(22)=37
wBlockCam(22)=1 
;lockers (right)
wBlockX1#(23)=232 : wBlockX2#(23)=260
wBlockZ1#(23)=500 : wBlockZ2#(23)=900
wBlockY1#(23)=0 : wBlockY2#(23)=37
wBlockCam(23)=1 
;lockers (bottom)
wBlockX1#(24)=81 : wBlockX2#(24)=260
wBlockZ1#(24)=500 : wBlockZ2#(24)=592
wBlockY1#(24)=0 : wBlockY2#(24)=37
wBlockCam(24)=1 
;seating (north west)
wBlockX1#(25)=-500 : wBlockX2#(25)=-70
wBlockZ1#(25)=195 : wBlockZ2#(25)=485
wBlockY1#(25)=0 : wBlockY2#(25)=200
wBlockCam(25)=1
;seating (north east)
wBlockX1#(26)=64 : wBlockX2#(26)=500
wBlockZ1#(26)=199 : wBlockZ2#(26)=485
wBlockY1#(26)=0 : wBlockY2#(26)=200
wBlockCam(26)=1
;seating (east)
wBlockX1#(27)=198 : wBlockX2#(27)=500
wBlockZ1#(27)=-500 : wBlockZ2#(27)=485
wBlockY1#(27)=0 : wBlockY2#(27)=200
wBlockCam(27)=1
;seating (south)
wBlockX1#(28)=-500 : wBlockX2#(28)=500
wBlockZ1#(28)=-489 : wBlockZ2#(28)=-192
wBlockY1#(28)=0 : wBlockY2#(28)=200
wBlockCam(28)=1
;seating (west)
wBlockX1#(29)=-500 : wBlockX2#(29)=-194
wBlockZ1#(29)=-500 : wBlockZ2#(29)=485
wBlockY1#(29)=0 : wBlockY2#(29)=200
wBlockCam(29)=1
;arena wall (east)
wBlockX1#(30)=465 : wBlockX2#(30)=500
wBlockZ1#(30)=-500 : wBlockZ2#(30)=485
wBlockY1#(30)=0 : wBlockY2#(30)=200
wBlockCam(30)=1
;arena wall (south)
wBlockX1#(31)=-500 : wBlockX2#(31)=500
wBlockZ1#(31)=-500 : wBlockZ2#(31)=-489
wBlockY1#(31)=0 : wBlockY2#(31)=200
wBlockCam(31)=1
;arena wall (west)
wBlockX1#(32)=-500 : wBlockX2#(32)=-463
wBlockZ1#(32)=-500 : wBlockZ2#(32)=485
wBlockY1#(32)=0 : wBlockY2#(32)=200
wBlockCam(32)=1

;////////////////////////////////////////////////////////
;--------------- PARTICLE EFFECTS -----------------------
;////////////////////////////////////////////////////////
.Particles
;particles
Global no_particles=500
Dim part(1000)
Dim partType(1000)
Dim partX#(1000)
Dim partY#(1000)
Dim partZ#(1000)
Dim partA#(1000)
Dim partGravity#(1000)
Dim partFlight#(1000)
Dim partSize#(1000)
Dim partAlpha#(1000)
Dim partFade#(1000)
Dim partTim(1000)
Dim partState(1000)
;explosions
Global no_explodes=10
Dim exType(no_explodes)
Dim exX#(no_explodes)
Dim exY#(no_explodes)
Dim exZ#(no_explodes)
Dim exTim(no_explodes)
Dim exHurt(no_explodes,optPlayLim)
;pools
Global no_pools=30
Dim pool(no_pools)
Dim poolType(no_pools)
Dim poolX#(no_pools)
Dim poolY#(no_pools)
Dim poolZ#(no_pools)
Dim poolA#(no_pools)
Dim poolSize#(no_pools)
Dim poolAlpha#(no_pools)
Dim poolState(no_pools)