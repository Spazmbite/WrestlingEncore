;//////////////////////////////////////////////////////////////////////////////
;---------------------- WRESTLING ENCORE: STANDING MOVES ----------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------------
;/////////////////// STANDING MOVE SEQUENCES /////////////////////////
;---------------------------------------------------------------------
Function LoadMoveSequences(cyc)
 ;grappling
 pSeq(cyc,100)=ExtractAnimSeq(p(cyc),1045,1145,pSeq(cyc,602)) ;standing grapple lunge
 pSeq(cyc,101)=ExtractAnimSeq(p(cyc),2190,2270,pSeq(cyc,601)) ;standing grappler
 pSeq(cyc,102)=ExtractAnimSeq(p(cyc),2280,2360,pSeq(cyc,601)) ;standing grappled
 pSeq(cyc,103)=ExtractAnimSeq(p(cyc),2370,2450,pSeq(cyc,601)) ;standing grapple movement
 ;irish whip
 pSeq(cyc,110)=ExtractAnimSeq(p(cyc),200,290,pSeq(cyc,610)) ;execute
 pSeq(cyc,111)=ExtractAnimSeq(p(cyc),200,260,pSeq(cyc,611)) ;receive
 pSeq(cyc,112)=ExtractAnimSeq(p(cyc),275,280,pSeq(cyc,611)) ;run normalizer
 ;throw out
 pSeq(cyc,113)=ExtractAnimSeq(p(cyc),300,440,pSeq(cyc,610)) ;execute
 pSeq(cyc,114)=ExtractAnimSeq(p(cyc),300,440,pSeq(cyc,611)) ;receive
 ;throw in
 pSeq(cyc,115)=ExtractAnimSeq(p(cyc),450,550,pSeq(cyc,610)) ;execute
 pSeq(cyc,116)=ExtractAnimSeq(p(cyc),450,550,pSeq(cyc,611)) ;receive
 ;drag out from apron
 pSeq(cyc,117)=ExtractAnimSeq(p(cyc),560,730,pSeq(cyc,610)) ;execute
 pSeq(cyc,118)=ExtractAnimSeq(p(cyc),560,730,pSeq(cyc,611)) ;receive
 ;drag in from apron
 pSeq(cyc,119)=ExtractAnimSeq(p(cyc),740,880,pSeq(cyc,610)) ;execute
 pSeq(cyc,120)=ExtractAnimSeq(p(cyc),740,880,pSeq(cyc,611)) ;receive 
 ;bodyslam
 pSeq(cyc,121)=ExtractAnimSeq(p(cyc),40,190,pSeq(cyc,610)) ;execute
 pSeq(cyc,122)=ExtractAnimSeq(p(cyc),40,190,pSeq(cyc,611)) ;receive
 ;stalling suplex
 If MoveRequired(cyc,116,1) Then pSeq(cyc,123)=ExtractAnimSeq(p(cyc),890,1245,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,116,-1) Then pSeq(cyc,124)=ExtractAnimSeq(p(cyc),890,1245,pSeq(cyc,611)) ;receive
 ;regular suplex
 If MoveRequired(cyc,117,1) Then pSeq(cyc,125)=ExtractAnimSeq(p(cyc),1255,1525,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,117,-1) Then pSeq(cyc,126)=ExtractAnimSeq(p(cyc),1255,1525,pSeq(cyc,611)) ;receive
 ;snap suplex
 If MoveRequired(cyc,118,1) Then pSeq(cyc,127)=ExtractAnimSeq(p(cyc),1535,1715,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,118,-1) Then pSeq(cyc,128)=ExtractAnimSeq(p(cyc),1535,1715,pSeq(cyc,611)) ;receive
 ;stalling brainbuster
 If MoveRequired(cyc,119,1) Then pSeq(cyc,129)=ExtractAnimSeq(p(cyc),1725,2080,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,119,-1) Then pSeq(cyc,130)=ExtractAnimSeq(p(cyc),1725,2080,pSeq(cyc,611)) ;receive
 ;regular brainbuster
 If MoveRequired(cyc,120,1) Then pSeq(cyc,131)=ExtractAnimSeq(p(cyc),2090,2370,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,120,-1) Then pSeq(cyc,132)=ExtractAnimSeq(p(cyc),2090,2370,pSeq(cyc,611)) ;receive
 ;belly-to-belly suplex
 pSeq(cyc,133)=ExtractAnimSeq(p(cyc),2380,2515,pSeq(cyc,610)) ;execute
 pSeq(cyc,134)=ExtractAnimSeq(p(cyc),2380,2515,pSeq(cyc,611)) ;receive
 ;belly-to-belly slam
 If MoveRequired(cyc,122,1) Then pSeq(cyc,135)=ExtractAnimSeq(p(cyc),2525,2680,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,122,-1) Then pSeq(cyc,136)=ExtractAnimSeq(p(cyc),2525,2680,pSeq(cyc,611)) ;receive
 ;stunner
 If MoveRequired(cyc,123,1) Then pSeq(cyc,137)=ExtractAnimSeq(p(cyc),2690,2870,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,123,-1) Then pSeq(cyc,138)=ExtractAnimSeq(p(cyc),2690,2870,pSeq(cyc,611)) ;receive
 ;powerslam
 If MoveRequired(cyc,124,1) Then pSeq(cyc,139)=ExtractAnimSeq(p(cyc),2880,3025,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,124,-1) Then pSeq(cyc,140)=ExtractAnimSeq(p(cyc),2880,3025,pSeq(cyc,611)) ;receive
 ;snapmare
 If MoveRequired(cyc,125,1) Then pSeq(cyc,141)=ExtractAnimSeq(p(cyc),3040,3165,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,125,-1) Then pSeq(cyc,142)=ExtractAnimSeq(p(cyc),3040,3165,pSeq(cyc,611)) ;receive
 ;backbreaker
 If MoveRequired(cyc,126,1) Then pSeq(cyc,143)=ExtractAnimSeq(p(cyc),3175,3340,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,126,-1) Then pSeq(cyc,144)=ExtractAnimSeq(p(cyc),3175,3340,pSeq(cyc,611)) ;receive
 ;side slam
 If MoveRequired(cyc,127,1) Then pSeq(cyc,145)=ExtractAnimSeq(p(cyc),3350,3555,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,127,-1) Then pSeq(cyc,146)=ExtractAnimSeq(p(cyc),3350,3555,pSeq(cyc,611)) ;receive
 ;side backbreaker
 If MoveRequired(cyc,128,1) Then pSeq(cyc,147)=ExtractAnimSeq(p(cyc),3565,3755,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,128,-1) Then pSeq(cyc,148)=ExtractAnimSeq(p(cyc),3565,3755,pSeq(cyc,611)) ;receive
 ;headlock punch
 If MoveRequired(cyc,129,1) Then pSeq(cyc,149)=ExtractAnimSeq(p(cyc),3765,3965,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,129,-1) Then pSeq(cyc,150)=ExtractAnimSeq(p(cyc),3765,3965,pSeq(cyc,611)) ;receive
 ;bulldog
 If MoveRequired(cyc,130,1) Then pSeq(cyc,151)=ExtractAnimSeq(p(cyc),3975,4235,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,130,-1) Then pSeq(cyc,152)=ExtractAnimSeq(p(cyc),3975,4235,pSeq(cyc,611)) ;receive
 ;pumping press slam
 If MoveRequired(cyc,131,1) Then pSeq(cyc,153)=ExtractAnimSeq(p(cyc),4245,4555,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,131,-1) Then pSeq(cyc,154)=ExtractAnimSeq(p(cyc),4245,4555,pSeq(cyc,611)) ;receive
 ;press slam
 If MoveRequired(cyc,132,1) Then pSeq(cyc,155)=ExtractAnimSeq(p(cyc),4565,4755,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,132,-1) Then pSeq(cyc,156)=ExtractAnimSeq(p(cyc),4565,4755,pSeq(cyc,611)) ;receive
 ;headlock
 If MoveRequired(cyc,133,1) Then pSeq(cyc,157)=ExtractAnimSeq(p(cyc),4765,4795,pSeq(cyc,610)) ;apply execute
 If MoveRequired(cyc,133,-1) Then pSeq(cyc,158)=ExtractAnimSeq(p(cyc),4765,4795,pSeq(cyc,611)) ;apply receive
 If MoveRequired(cyc,133,1) Then pSeq(cyc,159)=ExtractAnimSeq(p(cyc),4795,4870,pSeq(cyc,610)) ;wrench execute
 If MoveRequired(cyc,133,-1) Then pSeq(cyc,160)=ExtractAnimSeq(p(cyc),4795,4870,pSeq(cyc,611)) ;wrench receive
 ;gorilla press slam
 If MoveRequired(cyc,134,1) Then pSeq(cyc,161)=ExtractAnimSeq(p(cyc),4880,5135,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,134,-1) Then pSeq(cyc,162)=ExtractAnimSeq(p(cyc),4880,5135,pSeq(cyc,611)) ;receive
 ;shoulder powerslam
 If MoveRequired(cyc,135,1) Then pSeq(cyc,163)=ExtractAnimSeq(p(cyc),5145,5390,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,135,-1) Then pSeq(cyc,164)=ExtractAnimSeq(p(cyc),5145,5390,pSeq(cyc,611)) ;receive
 ;shoulder breaker
 If MoveRequired(cyc,136,1) Then pSeq(cyc,165)=ExtractAnimSeq(p(cyc),5400,5660,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,136,-1) Then pSeq(cyc,166)=ExtractAnimSeq(p(cyc),5400,5660,pSeq(cyc,611)) ;receive
 ;tombstone piledriver
 If MoveRequired(cyc,137,1) Then pSeq(cyc,167)=ExtractAnimSeq(p(cyc),5670,5930,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,137,-1) Then pSeq(cyc,168)=ExtractAnimSeq(p(cyc),5670,5930,pSeq(cyc,611)) ;receive
 ;DDT
 If MoveRequired(cyc,138,1) Then pSeq(cyc,169)=ExtractAnimSeq(p(cyc),40,235,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,138,-1) Then pSeq(cyc,170)=ExtractAnimSeq(p(cyc),40,235,pSeq(cyc,617)) ;receive
 ;hurricanranna
 If MoveRequired(cyc,139,1) Then pSeq(cyc,171)=ExtractAnimSeq(p(cyc),245,485,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,139,-1) Then pSeq(cyc,172)=ExtractAnimSeq(p(cyc),245,485,pSeq(cyc,617)) ;receive
 ;hurrican plancha
 If MoveRequired(cyc,140,1) Then pSeq(cyc,173)=ExtractAnimSeq(p(cyc),495,685,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,140,-1) Then pSeq(cyc,174)=ExtractAnimSeq(p(cyc),495,685,pSeq(cyc,617)) ;receive
 ;powerbomb
 If MoveRequired(cyc,141,1) Then pSeq(cyc,175)=ExtractAnimSeq(p(cyc),695,930,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,141,-1) Then pSeq(cyc,176)=ExtractAnimSeq(p(cyc),695,930,pSeq(cyc,617)) ;receive
 ;sitting powerbomb
 If MoveRequired(cyc,142,1) Then pSeq(cyc,177)=ExtractAnimSeq(p(cyc),940,1260,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,142,-1) Then pSeq(cyc,178)=ExtractAnimSeq(p(cyc),940,1260,pSeq(cyc,617)) ;receive
 ;piledriver
 If MoveRequired(cyc,143,1) Then pSeq(cyc,179)=ExtractAnimSeq(p(cyc),1275,1610,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,143,-1) Then pSeq(cyc,180)=ExtractAnimSeq(p(cyc),1275,1610,pSeq(cyc,617)) ;receive
 ;pedigree
 If MoveRequired(cyc,144,1) Then pSeq(cyc,181)=ExtractAnimSeq(p(cyc),1620,1835,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,144,-1) Then pSeq(cyc,182)=ExtractAnimSeq(p(cyc),1620,1835,pSeq(cyc,617)) ;receive
 ;suplex drop
 If MoveRequired(cyc,145,1) Then pSeq(cyc,183)=ExtractAnimSeq(p(cyc),5940,6255,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,145,-1) Then pSeq(cyc,184)=ExtractAnimSeq(p(cyc),5940,6255,pSeq(cyc,611)) ;receive
 ;jackhammer
 If MoveRequired(cyc,146,1) Then pSeq(cyc,185)=ExtractAnimSeq(p(cyc),6275,6600,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,146,-1) Then pSeq(cyc,186)=ExtractAnimSeq(p(cyc),6275,6600,pSeq(cyc,611)) ;receive
 ;german suplex
 If MoveRequired(cyc,147,1) Then pSeq(cyc,187)=ExtractAnimSeq(p(cyc),1845,2105,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,147,-1) Then pSeq(cyc,188)=ExtractAnimSeq(p(cyc),1845,2105,pSeq(cyc,617)) ;receive
 ;back suplex
 If MoveRequired(cyc,148,1) Then pSeq(cyc,189)=ExtractAnimSeq(p(cyc),2115,2385,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,148,-1) Then pSeq(cyc,190)=ExtractAnimSeq(p(cyc),2115,2385,pSeq(cyc,617)) ;receive
 ;atomic drop
 If MoveRequired(cyc,149,1) Then pSeq(cyc,191)=ExtractAnimSeq(p(cyc),2395,2640,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,149,-1) Then pSeq(cyc,192)=ExtractAnimSeq(p(cyc),2395,2640,pSeq(cyc,617)) ;receive
 ;choke slam
 If MoveRequired(cyc,150,1) Then pSeq(cyc,193)=ExtractAnimSeq(p(cyc),2650,2790,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,150,-1) Then pSeq(cyc,194)=ExtractAnimSeq(p(cyc),2650,2790,pSeq(cyc,617)) ;receive
 ;reverse DDT
 If MoveRequired(cyc,151,1) Then pSeq(cyc,195)=ExtractAnimSeq(p(cyc),2800,3025,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,151,-1) Then pSeq(cyc,196)=ExtractAnimSeq(p(cyc),2800,3025,pSeq(cyc,617)) ;receive
 ;throat toss
 If MoveRequired(cyc,152,1) Then pSeq(cyc,197)=ExtractAnimSeq(p(cyc),3035,3195,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,152,-1) Then pSeq(cyc,198)=ExtractAnimSeq(p(cyc),3035,3195,pSeq(cyc,617)) ;receive
 ;rock bottom
 If MoveRequired(cyc,153,1) Then pSeq(cyc,199)=ExtractAnimSeq(p(cyc),3205,3410,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,153,-1) Then pSeq(cyc,200)=ExtractAnimSeq(p(cyc),3205,3410,pSeq(cyc,617)) ;receive
 ;MDKO
 If MoveRequired(cyc,154,1) Then pSeq(cyc,201)=ExtractAnimSeq(p(cyc),3420,3650,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,154,-1) Then pSeq(cyc,202)=ExtractAnimSeq(p(cyc),3420,3650,pSeq(cyc,617)) ;receive
 ;inverted atomic drop
 If MoveRequired(cyc,155,1) Then pSeq(cyc,203)=ExtractAnimSeq(p(cyc),3660,3835,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,155,-1) Then pSeq(cyc,204)=ExtractAnimSeq(p(cyc),3660,3835,pSeq(cyc,617)) ;receive
 ;spinebuster
 If MoveRequired(cyc,156,1) Then pSeq(cyc,205)=ExtractAnimSeq(p(cyc),3845,4030,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,156,-1) Then pSeq(cyc,206)=ExtractAnimSeq(p(cyc),3845,4030,pSeq(cyc,617)) ;receive
 ;neckbreaker
 If MoveRequired(cyc,157,1) Then pSeq(cyc,207)=ExtractAnimSeq(p(cyc),4040,4310,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,157,-1) Then pSeq(cyc,208)=ExtractAnimSeq(p(cyc),4040,4310,pSeq(cyc,617)) ;receive
 ;headlock takedown
 If MoveRequired(cyc,158,1) Then pSeq(cyc,209)=ExtractAnimSeq(p(cyc),6610,6790,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,158,-1) Then pSeq(cyc,210)=ExtractAnimSeq(p(cyc),6610,6790,pSeq(cyc,611)) ;receive
 ;hip toss
 If MoveRequired(cyc,159,1) Then pSeq(cyc,211)=ExtractAnimSeq(p(cyc),4320,4455,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,159,-1) Then pSeq(cyc,212)=ExtractAnimSeq(p(cyc),4320,4455,pSeq(cyc,617)) ;receive
 ;drop toe hold
 If MoveRequired(cyc,160,1) Then pSeq(cyc,213)=ExtractAnimSeq(p(cyc),4465,4610,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,160,-1) Then pSeq(cyc,214)=ExtractAnimSeq(p(cyc),4465,4610,pSeq(cyc,617)) ;receive
 ;back body drop
 If MoveRequired(cyc,161,1) Then pSeq(cyc,215)=ExtractAnimSeq(p(cyc),4620,4775,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,161,-1) Then pSeq(cyc,216)=ExtractAnimSeq(p(cyc),4620,4775,pSeq(cyc,617)) ;receive
 ;samoan drop
 If MoveRequired(cyc,162,1) Then pSeq(cyc,217)=ExtractAnimSeq(p(cyc),4785,5030,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,162,-1) Then pSeq(cyc,218)=ExtractAnimSeq(p(cyc),4785,5030,pSeq(cyc,617)) ;receive
 ;death valley driver
 If MoveRequired(cyc,163,1) Then pSeq(cyc,219)=ExtractAnimSeq(p(cyc),5040,5290,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,163,-1) Then pSeq(cyc,220)=ExtractAnimSeq(p(cyc),5040,5290,pSeq(cyc,617)) ;receive 
 ;northern lights suplex
 If MoveRequired(cyc,164,1) Then pSeq(cyc,221)=ExtractAnimSeq(p(cyc),5300,5500,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,164,-1) Then pSeq(cyc,222)=ExtractAnimSeq(p(cyc),5300,5500,pSeq(cyc,617)) ;receive
 ;flying head scissors
 If MoveRequired(cyc,165,1) Then pSeq(cyc,223)=ExtractAnimSeq(p(cyc),5510,5740,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,165,-1) Then pSeq(cyc,224)=ExtractAnimSeq(p(cyc),5510,5740,pSeq(cyc,617)) ;receive
 ;sleeper hold
 If MoveRequired(cyc,166,1) Then pSeq(cyc,225)=ExtractAnimSeq(p(cyc),5750,5820,pSeq(cyc,616)) ;apply execute
 If MoveRequired(cyc,166,-1) Then pSeq(cyc,226)=ExtractAnimSeq(p(cyc),5750,5820,pSeq(cyc,617)) ;apply receive
 If MoveRequired(cyc,166,1) Then pSeq(cyc,227)=ExtractAnimSeq(p(cyc),5820,5860,pSeq(cyc,616)) ;wrench execute
 If MoveRequired(cyc,166,-1) Then pSeq(cyc,228)=ExtractAnimSeq(p(cyc),5820,5860,pSeq(cyc,617)) ;wrench receive
 ;spear
 If MoveRequired(cyc,167,1) Then pSeq(cyc,229)=ExtractAnimSeq(p(cyc),5870,6050,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,167,-1) Then pSeq(cyc,230)=ExtractAnimSeq(p(cyc),5870,6050,pSeq(cyc,617)) ;receive
 ;russian legsweep
 If MoveRequired(cyc,168,1) Then pSeq(cyc,231)=ExtractAnimSeq(p(cyc),6060,6285,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,168,-1) Then pSeq(cyc,232)=ExtractAnimSeq(p(cyc),6060,6285,pSeq(cyc,617)) ;receive
 ;standing clothesline
 If MoveRequired(cyc,169,1) Then pSeq(cyc,233)=ExtractAnimSeq(p(cyc),6295,6450,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,169,-1) Then pSeq(cyc,234)=ExtractAnimSeq(p(cyc),6295,6450,pSeq(cyc,617)) ;receive
 ;bear hug
 If MoveRequired(cyc,170,1) Then pSeq(cyc,235)=ExtractAnimSeq(p(cyc),6460,6520,pSeq(cyc,616)) ;apply execute
 If MoveRequired(cyc,170,-1) Then pSeq(cyc,236)=ExtractAnimSeq(p(cyc),6460,6520,pSeq(cyc,617)) ;apply receive
 If MoveRequired(cyc,170,1) Then pSeq(cyc,237)=ExtractAnimSeq(p(cyc),6520,6580,pSeq(cyc,616)) ;wrench execute
 If MoveRequired(cyc,170,-1) Then pSeq(cyc,238)=ExtractAnimSeq(p(cyc),6520,6580,pSeq(cyc,617)) ;wrench receive
 ;x-factor
 If MoveRequired(cyc,171,1) Then pSeq(cyc,239)=ExtractAnimSeq(p(cyc),6590,6815,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,171,-1) Then pSeq(cyc,240)=ExtractAnimSeq(p(cyc),6590,6815,pSeq(cyc,617)) ;receive
 ;gutwrench suplex
 If MoveRequired(cyc,172,1) Then pSeq(cyc,241)=ExtractAnimSeq(p(cyc),6825,7000,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,172,-1) Then pSeq(cyc,242)=ExtractAnimSeq(p(cyc),6825,7000,pSeq(cyc,617)) ;receive
 ;rear arm bar
 If MoveRequired(cyc,173,1) Then pSeq(cyc,243)=ExtractAnimSeq(p(cyc),7010,7080,pSeq(cyc,616)) ;apply execute
 If MoveRequired(cyc,173,-1) Then pSeq(cyc,244)=ExtractAnimSeq(p(cyc),7010,7080,pSeq(cyc,617)) ;apply receive
 If MoveRequired(cyc,173,1) Then pSeq(cyc,245)=ExtractAnimSeq(p(cyc),7080,7120,pSeq(cyc,616)) ;wrench execute
 If MoveRequired(cyc,173,-1) Then pSeq(cyc,246)=ExtractAnimSeq(p(cyc),7080,7120,pSeq(cyc,617)) ;wrench receive
 ;inverted piledriver
 If MoveRequired(cyc,174,1) Then pSeq(cyc,247)=ExtractAnimSeq(p(cyc),6800,7070,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,174,-1) Then pSeq(cyc,248)=ExtractAnimSeq(p(cyc),6800,7070,pSeq(cyc,611)) ;receive
 ;full nelson
 If MoveRequired(cyc,175,1) Then pSeq(cyc,249)=ExtractAnimSeq(p(cyc),7080,7170,pSeq(cyc,610)) ;apply execute
 If MoveRequired(cyc,175,-1) Then pSeq(cyc,250)=ExtractAnimSeq(p(cyc),7080,7170,pSeq(cyc,611)) ;apply receive
 If MoveRequired(cyc,175,1) Then pSeq(cyc,251)=ExtractAnimSeq(p(cyc),7170,7210,pSeq(cyc,610)) ;wrench execute
 If MoveRequired(cyc,175,-1) Then pSeq(cyc,252)=ExtractAnimSeq(p(cyc),7170,7210,pSeq(cyc,611)) ;wrench receive
 ;cobra clutch
 If MoveRequired(cyc,176,1) Then pSeq(cyc,253)=ExtractAnimSeq(p(cyc),7220,7310,pSeq(cyc,610)) ;apply execute
 If MoveRequired(cyc,176,-1) Then pSeq(cyc,254)=ExtractAnimSeq(p(cyc),7220,7310,pSeq(cyc,611)) ;apply receive
 If MoveRequired(cyc,176,1) Then pSeq(cyc,255)=ExtractAnimSeq(p(cyc),7310,7350,pSeq(cyc,610)) ;wrench execute
 If MoveRequired(cyc,176,-1) Then pSeq(cyc,256)=ExtractAnimSeq(p(cyc),7310,7350,pSeq(cyc,611)) ;wrench receive
 ;full nelson suplex
 If MoveRequired(cyc,177,1) Then pSeq(cyc,257)=ExtractAnimSeq(p(cyc),7360,7640,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,177,-1) Then pSeq(cyc,258)=ExtractAnimSeq(p(cyc),7360,7640,pSeq(cyc,611)) ;receive
 ;underhook suplex
 If MoveRequired(cyc,178,1) Then pSeq(cyc,259)=ExtractAnimSeq(p(cyc),7650,7820,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,178,-1) Then pSeq(cyc,260)=ExtractAnimSeq(p(cyc),7650,7820,pSeq(cyc,611)) ;receive
 ;flipping piledriver
 If MoveRequired(cyc,179,1) Then pSeq(cyc,261)=ExtractAnimSeq(p(cyc),7130,7450,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,179,-1) Then pSeq(cyc,262)=ExtractAnimSeq(p(cyc),7130,7450,pSeq(cyc,617)) ;receive
 ;pump handle slam
 If MoveRequired(cyc,180,1) Then pSeq(cyc,263)=ExtractAnimSeq(p(cyc),7830,8035,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,180,-1) Then pSeq(cyc,264)=ExtractAnimSeq(p(cyc),7830,8035,pSeq(cyc,611)) ;receive
 ;michinoku driver
 If MoveRequired(cyc,181,1) Then pSeq(cyc,265)=ExtractAnimSeq(p(cyc),8045,8290,pSeq(cyc,610)) ;execute
 If MoveRequired(cyc,181,-1) Then pSeq(cyc,266)=ExtractAnimSeq(p(cyc),8045,8290,pSeq(cyc,611)) ;receive
 ;tornado DDT
 If MoveRequired(cyc,182,1) Then pSeq(cyc,267)=ExtractAnimSeq(p(cyc),7460,7665,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,182,-1) Then pSeq(cyc,268)=ExtractAnimSeq(p(cyc),7460,7665,pSeq(cyc,617)) ;receive
 ;reverse suplex
 If MoveRequired(cyc,183,1) Then pSeq(cyc,269)=ExtractAnimSeq(p(cyc),7675,7845,pSeq(cyc,616)) ;execute
 If MoveRequired(cyc,183,-1) Then pSeq(cyc,270)=ExtractAnimSeq(p(cyc),7675,7845,pSeq(cyc,617)) ;receive 
End Function

;FIND MOVE REQUIREMENT
Function MoveRequired(cyc,move,task) ;-1=receive, 1=execute
 needed=0
 For v=1 To no_plays
  For count=1 To 13
   If movAnim(charMove(pChar(v),count))=move 
    If task=1 And cyc=v Then needed=1 ;executor
    If task=-1 Then needed=1 ;receiver
    needed=1
   EndIf
  Next
  If movAnim(charMove(pChar(v),15))=move Then needed=1
 Next
 Return needed
End Function

;---------------------------------------------------------------------
;////////////////// STANDING MOVE ANIMATIONS /////////////////////////
;---------------------------------------------------------------------
Function MoveAnims(cyc)
 ;standing grapple lunge
 If pAnim(cyc)=100
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,3.0,pSeq(cyc,100),5 : pSting(cyc)=1 : InstantTurn(cyc)
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.2,0.4))
  If pAnimTim(cyc)=>2 And pAnimTim(cyc)=<22
   Advance(cyc,pA#(cyc),0.25)
   pStepTim(cyc)=pStepTim(cyc)+1
  EndIf
  If pAnimTim(cyc)=>19 And pAnimTim(cyc)=<23
   For v=1 To no_plays
    If cyc<>v And GrabViable(v)=1 And InProximity(cyc,v,30) And pY#(cyc)=pY#(v) And pPlatform(v)=<4 And pGrappling(cyc)=0 And pGrappler(v)=0 And pGrappling(v)=0 And pSting(cyc)=1
     If pPlatform(v)<0 Then range=3 Else range=5
     If InRange(cyc,v,range)
      ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
      CreateSpurt(pX#(v),pY#(v)+20,pZ#(v),15,4)
      buckle=0
      If pPlatform(v)<0 Then buckle=1
      ImpactChecks(v)
      ChangeAnim(cyc,101) : ChangeAnim(v,103)
      pGrappling(cyc)=v : pGrappler(v)=cyc
      DropItem(v) : DropWeapon(v,5)
      pFoc(cyc)=v : pFoc(v)=cyc
      pSting(cyc)=0
      If buckle=1 Then TriggerMove(cyc,cornAnim(charMove(pChar(cyc),14)))
     EndIf
    EndIf
   Next
  EndIf
  randy=Rnd(0,50)
  If (randy=0 And pAnimTim(cyc)>25) Or pAnimTim(cyc)>33 Then ChangeAnim(cyc,0)
 EndIf
 ;standing grappler
 If pAnim(cyc)=101
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   speeder#=Rnd(0.4,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,101),10
   Animate p(v),1,speeder#,pSeq(v,102),10
  EndIf
  FixGrapple(cyc)
  If pAnimTim(cyc)>5 And DirPressed(cyc) Then ChangeAnim(cyc,102)
  ManageGrapple(cyc)
 EndIf
 ;standing grapple movement
 If pAnim(cyc)=102
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   Animate p(cyc),1,2.0,pSeq(cyc,103),10
   Animate p(v),1,2.0,pSeq(v,103),10
  EndIf
  FixGrapple(cyc)
  ApplyMovement(cyc,0.4)
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,101)
  ManageGrapple(cyc)
  pStepTim(cyc)=pStepTim(cyc)+1
 EndIf
 ;grapple victim
 If pAnim(cyc)=103
  If pGrappler(cyc)=0 Or pAnim(pGrappler(cyc))<100 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.2
  EndIf
 EndIf
 ;move victim
 If pAnim(cyc)=109
  DropWeapon(cyc,25)
 EndIf
 ;IRISH WHIP
 If pAnim(cyc)=110
  v=pGrappling(cyc)
  animA=110 : animB=111 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=37 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then pStepTim(v)=99
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=25 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0) 
  If pAnimTim(cyc)=18 Then ProduceSound(p(cyc),sSwing,22050,Rnd(0.2,0.5))
  If pAnimTim(cyc)=22 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/6)
  EndIf
  If pAnimTim(cyc)>28 And v>0
   ChangeAnim(v,3) : SharpTransition(v,112,180)
   pGrappling(cyc)=0 : pGrappler(v)=0
   pExcusedPlays(v)=0 : pExcusedItems(v)=0
   pHeat(v)=pHeat(v)-1 : pMomentum(v)=Rnd(50,200)
  EndIf
  If pAnimTim(cyc)>45
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   pExcusedPlays(cyc)=0 : pExcusedItems(cyc)=0
   pHeat(cyc)=pHeat(cyc)+1
  EndIf
 EndIf
 ;FORCE OUT OF RING
 If pAnim(cyc)=111
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0
   Animate p(cyc),3,2.0,pSeq(cyc,113),0
   Animate p(v),3,2.0,pSeq(v,114),0 
  EndIf
  If pAnimTim(cyc)=25 Then ShakeRopes(NearRopes(v,5),1,2) : RopeSound(p(v),0)
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=40 Or pAnimTim(cyc)=55 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=20 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.2,0.5))
  If pAnimTim(cyc)=30 Then Pop(cyc,Rnd(2,7),0)
  If pAnimTim(cyc)=41 Then FindSmashes(v,1)
  If pAnimTim(cyc)=51
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-5 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>40 And EntityY(FindChild(p(v),"Hips"),1)>wGround#+5 Then pY#(v)=pY#(v)-0.1 
  If pAnimTim(cyc)>70
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   ChangeAnim(v,83) : SharpTransition(v,83,90) : pY#(v)=wGround#
   EndMove(cyc,v)
  EndIf
 EndIf
 ;FORCE INTO RING
 If pAnim(cyc)=112
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0
   Animate p(cyc),3,1.5,pSeq(cyc,115),0
   Animate p(v),3,1.5,pSeq(v,116),0
  EndIf
  If pAnimTim(cyc)=40 Then ShakeRopes(NearRopes(v,5),1,2) : RopeSound(p(v),0)
  If pAnimTim(cyc)=7 Or pAnimTim(cyc)=26 Or pAnimTim(cyc)=40 Or pAnimTim(cyc)=55 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=13 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=46 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=20 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
  If pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.2,0.5))
  If pAnimTim(cyc)=40 Then ProduceSound(p(v),sFall,22050,0.5) : Pop(0,Rnd(3,5),0)
  If pAnimTim(cyc)>40 And EntityY(FindChild(p(v),"Hips"),1)<wStage#+5 Then pY#(v)=pY#(v)+0.1
  If pAnimTim(cyc)>66
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,83) : SharpTransition(v,83,90) : pY#(v)=wStage#
   EndMove(cyc,v) : pDT(v)=pDT(v)+50
  EndIf
 EndIf
 ;DRAG OUT FROM APRON
 If pAnim(cyc)=113
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0
   Animate p(cyc),3,2.5,pSeq(cyc,117),0
   Animate p(v),3,2.5,pSeq(v,118),0
  EndIf
  If pAnimTim(cyc)=16 Then ShakeRopes(NearRopes(v,5),1,2) : RopeSound(p(v),0)
  If pAnimTim(cyc)=24 Or pAnimTim(cyc)=36 Or pAnimTim(cyc)=48 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=8 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
  If pAnimTim(cyc)=25 Then ProduceSound(p(v),sPain(4),GetVoice(v),1) 
  If pAnimTim(cyc)=30 Then Pop(cyc,Rnd(2,7),0)
  If pAnimTim(cyc)=32 Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.2,0.5))
  If pAnimTim(cyc)=42 Then FindSmashes(v,1)
  If pAnimTim(cyc)=52
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-10 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>40 And EntityY(FindChild(p(v),"Hips"),1)>wGround#+5 Then pY#(v)=pY#(v)-0.1 
  If pAnimTim(cyc)>68
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,90) : pY#(v)=wGround#
   EndMove(cyc,v)
  EndIf
 EndIf
 ;DRAG IN FROM APRON
 If pAnim(cyc)=114
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  If pAnimTim(cyc)=0
   Animate p(cyc),3,2.5,pSeq(cyc,119),0
   Animate p(v),3,2.5,pSeq(v,120),0 
  EndIf
  If pAnimTim(cyc)=16 Then ShakeRopes(NearRopes(v,5),1,2) : RopeSound(p(v),0)
  If pAnimTim(cyc)=24 Or pAnimTim(cyc)=40 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=8 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=8 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),1)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.2,0.5))
  If pAnimTim(cyc)=30 Then FindSmashes(v,1)
  If pAnimTim(cyc)=36 
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-3 : pHP(v)=0 : pDT(v)=250-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,20)
  EndIf
  If pAnimTim(cyc)>56
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,90)
   ConfineToRing(v) : pPlatform(v)=0
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BODYSLAM
 If pAnim(cyc)=115
  v=pGrappling(cyc)
  animA=121 : animB=122 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<50 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0) 
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If v>0 And pAnimTim(cyc)=50 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=60
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=275-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>75
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,45,55,1)
 EndIf
 ;STALLING SUPLEX
 If pAnim(cyc)=116
  v=pGrappling(cyc)
  animA=123 : animB=124 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=80 Or pAnimTim(cyc)=105
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=130 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=90 Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<130 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=60 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=70 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If v>0 And pAnimTim(cyc)=135 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=145
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,0)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>177
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,135,140,3)
 EndIf
 ;REGULAR SUPLEX
 If pAnim(cyc)=117
  v=pGrappling(cyc)
  animA=125 : animB=126 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=80
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<95 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=60 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=70 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If v>0 And pAnimTim(cyc)=95 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=102
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>135
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,90,95,3)
 EndIf
 ;SNAP SUPLEX
 If pAnim(cyc)=118
  v=pGrappling(cyc)
  animA=127 : animB=128 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>30 And pAnimTim(cyc)=<55 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9) 
  If pAnimTim(cyc)=45 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=55 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=60
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>90
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;STALLING BRAINBUSTER
 If pAnim(cyc)=119
  v=pGrappling(cyc)
  animA=129 : animB=130 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=80 Or pAnimTim(cyc)=105
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=130 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=90 Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<130 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=60 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=70 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If v>0 And pAnimTim(cyc)=135 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=145
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0)
   ScarLimb(v,1,5) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>177
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,135,140,3)
 EndIf
 ;REGULAR BRAINBUSTER
 If pAnim(cyc)=120
  v=pGrappling(cyc)
  animA=131 : animB=132 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=80
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<95 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=60 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=70 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If v>0 And pAnimTim(cyc)=100 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=107
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0)
   ScarLimb(v,1,5) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>140
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,92,97,3)
 EndIf
 ;BELLY-TO-BELLY SUPLEX
 If pAnim(cyc)=121
  v=pGrappling(cyc)
  animA=133 : animB=134 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.3)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=23 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>17 And pAnimTim(cyc)=<38 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=23 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=38 Then FindSmashes(cyc,1)
  If v>0 And pAnimTim(cyc)=43 Then FindSmashes(v,1)
  If pAnimTim(cyc)=49 Then FindSmashes(cyc,0)
  If v>0 And pAnimTim(cyc)=53
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>67
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,37,45,3)
 EndIf
 ;BELLY-TO-BELLY SLAM
 If pAnim(cyc)=122
  v=pGrappling(cyc)
  animA=135 : animB=136 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=60 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.3)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<40 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=25 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=35 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=45
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>77
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;STUNNER
 If pAnim(cyc)=123
  v=pGrappling(cyc)
  animA=137 : animB=138 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=65 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=55 Or pAnimTim(cyc)=75 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=22 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>13 And pAnimTim(cyc)=<32 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=25 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=35
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)-2,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,3)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=57 Then FindSmashes(v,1)
  If pAnimTim(cyc)=75 
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>90
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;POWERSLAM
 If pAnim(cyc)=124
  v=pGrappling(cyc)
  animA=139 : animB=140 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Or pAnimTim(cyc)=62 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=66 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  ;If pAnimTim(cyc)=>12 And pAnimTim(cyc)=<35 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=20 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=30 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=40
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>72
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,270)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SNAPMARE
 If pAnim(cyc)=125
  v=pGrappling(cyc)
  animA=141 : animB=142 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=15 Or pAnimTim(cyc)=62 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<40 Then MoveTurn(cyc,v,5) 
  If pAnimTim(cyc)=30 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/6)
  EndIf
  If pAnimTim(cyc)=40 Then FindSmashes(v,1)
  If pAnimTim(cyc)=48
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=250-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>62
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BACKBREAKER
 If pAnim(cyc)=126
  v=pGrappling(cyc)
  animA=143 : animB=144 : speeder#=1.7
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=7 Or pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=57 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<40 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=35
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=47
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,1) : ScarLimb(v,3,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=76
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1) 
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>97
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SIDE SLAM
 If pAnim(cyc)=127
  v=pGrappling(cyc)
  animA=145 : animB=146 : speeder#=1.8
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=16 Or pAnimTim(cyc)=44 Or pAnimTim(cyc)=91 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=8 Or pAnimTim(cyc)=33 Or pAnimTim(cyc)=102 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=27 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>22 And pAnimTim(cyc)=<58 Then MoveTurn(cyc,v,5) 
  If pAnimTim(cyc)=50 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=58 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=66
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>113
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,270)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SIDE BACKBREAKER
 If pAnim(cyc)=128
  v=pGrappling(cyc)
  animA=147 : animB=148 : speeder#=1.8
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=16 Or pAnimTim(cyc)=44 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=8 Or pAnimTim(cyc)=33 Or pAnimTim(cyc)=94 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=27 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=83 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>22 And pAnimTim(cyc)=<60 Then MoveTurn(cyc,v,5) 
  If pAnimTim(cyc)=47 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=61
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,1) : ScarLimb(v,3,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=91
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1) 
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>105
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,270)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HEADLOCK PUNCH
 If pAnim(cyc)=129
  v=pGrappling(cyc)
  animA=149 : animB=150 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=77 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=22 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  ;If pAnimTim(cyc)=>10 And pAnimTim(cyc)=<65 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=30 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/6)
  EndIf
  If pAnimTim(cyc)=50
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)-2,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-3 : pHP(v)=0 : pDT(v)=200-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   ScarLimb(v,1,3) : WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=77 Then FindSmashes(v,1)
  If pAnimTim(cyc)=87 
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>100
   ChangeAnim(cyc,0)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BULLDOG
 If pAnim(cyc)=130
  v=pGrappling(cyc)
  animA=151 : animB=152 : speeder#=2.2
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=27 Or pAnimTim(cyc)=86 Or pAnimTim(cyc)=100 
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  EndIf
  If pAnimTim(cyc)=11 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=34 Or pAnimTim(cyc)=79 Or pAnimTim(cyc)=90 Or pAnimTim(cyc)=106 
   pStepTim(cyc)=99
  EndIf
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sSwing,20000,1)
  If pAnimTim(cyc)=>18 And pAnimTim(cyc)=<45 Then MoveTurn(cyc,v,5) 
  If pAnimTim(cyc)=34 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=43 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=54
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0)
   ScarArea(v,0,0,0,10) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>118
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;PUMPING PRESS SLAM
 If pAnim(cyc)=131
  v=pGrappling(cyc)
  animA=153 : animB=154 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<125 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=65 Or pAnimTim(cyc)=92
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=80 Or pAnimTim(cyc)=110 
   ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
   If pAnimTim(cyc)=>50 Then Pop(cyc,Rnd(2,9),0)
  EndIf
  If pAnimTim(cyc)=45 Or pAnimTim(cyc)=72 Or pAnimTim(cyc)=102 Then ProduceSound(p(cyc),sSwing,22050,0.3)
  If pAnimTim(cyc)=127 Then ProduceSound(p(cyc),sSwing,22050,0)
  If pAnimTim(cyc)=37 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/12)
  EndIf
  If v>0 And pAnimTim(cyc)=132 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=140
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,1)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>155
   ChangeAnim(cyc,0)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,130,135,5)
 EndIf
 ;PRESS SLAM
 If pAnim(cyc)=132
  v=pGrappling(cyc)
  animA=155 : animB=156 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<70 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=50 Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,22050,0.3)
  If pAnimTim(cyc)=127 Then ProduceSound(p(cyc),sSwing,22050,0)
  If pAnimTim(cyc)=37 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/12)
  EndIf
  If v>0 And pAnimTim(cyc)=72 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=80
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,1)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>95
   ChangeAnim(cyc,0)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,70,75,5)
 EndIf
 ;SIDE HEADLOCK
 If pAnim(cyc)=133
  ;apply
  v=pGrappling(cyc)
  animA=157 : animB=158 : speeder#=1.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf 
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  ;wrench
  start=20
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(1.1,1.5) Else speeder#=Rnd(0.7,1.1)
   Animate p(cyc),1,speeder#,pSeq(cyc,159),1
   Animate p(v),1,speeder#,pSeq(v,160),1
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,40,0,10)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-1.5,pSeq(cyc,157),5
   Animate p(v),3,-1.5,pSeq(cyc,158),5
  EndIf
  If pAnimTim(cyc)=1005 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1010 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1020 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc) : pStagger#(cyc)=0.1
   ChangeAnim(v,Rnd(70,71)) : SharpTransition(v,pAnim(cyc),180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;GORILLA PRESS SLAM
 If pAnim(cyc)=134
  v=pGrappling(cyc)
  animA=161 : animB=162 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<90 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=60 
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  EndIf
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=70 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=55 Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,22050,0.3)
  If pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sSwing,22050,0)
  If pAnimTim(cyc)=37 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/12)
  EndIf
  If v>0 And pAnimTim(cyc)=99 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=106
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,1)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>127
   ChangeAnim(cyc,0)
   If v>0 Then ChangeAnim(v,83) : SharpTransition(v,83,270)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,90,100,6)
 EndIf
 ;SHOULDER POWERSLAM
 If pAnim(cyc)=135
  v=pGrappling(cyc)
  animA=163 : animB=164 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=37 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=105 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=45 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.3)
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  ;If pAnimTim(cyc)=>30 And pAnimTim(cyc)=<65 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=70 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=82
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>122
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,270)
   ChangeAnim(v,80) : SharpTransition(v,80,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SOULDER BREAKER
 If pAnim(cyc)=136
  v=pGrappling(cyc)
  animA=165 : animB=166 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=37 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=120 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=45 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.3)
  If pAnimTim(cyc)=55 Or pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>30 And pAnimTim(cyc)=<75 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=75
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,1) : ScarLimb(v,3,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=105
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1) 
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>130
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;TOMBSTONE
 If pAnim(cyc)=137
  v=pGrappling(cyc)
  animA=167 : animB=168 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=22 Or pAnimTim(cyc)=37 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=115 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=45 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.3)
  If pAnimTim(cyc)=55 Or pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>30 And pAnimTim(cyc)=<77 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=77
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=105
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),1) 
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
  EndIf
  If pAnimTim(cyc)>130
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;DDT
 If pAnim(cyc)=138
  v=pGrappling(cyc)
  animA=169 : animB=170 : speeder#=2.2
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=11 Or pAnimTim(cyc)=22 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=34 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>18 And pAnimTim(cyc)=<45 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=34 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=43 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=50
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=72 Then ProduceSound(p(v),sFall,22050,1) : FindSmashes(v,0)
  If pAnimTim(cyc)>88
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HURRICANRANNA
 If pAnim(cyc)=139
  v=pGrappling(cyc)
  animA=171 : animB=172 : speeder#=2.2
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=52 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=100 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=35 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.4)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=60 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>22 And pAnimTim(cyc)=<75 Then MoveTurn(cyc,v,4) 
  If pAnimTim(cyc)=18 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=25 Then BreakMove(cyc,v,charAgl(pChar(cyc))/12)
  If v>0 And pAnimTim(cyc)=68 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=77
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>109
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,68,73,3)
 EndIf
 ;HURRICAN-PLANCHA
 If pAnim(cyc)=140
  v=pGrappling(cyc)
  animA=173 : animB=174 : speeder#=2.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=7 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=60 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=68 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=32 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.4)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=38 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<45 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=16 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=22 Then BreakMove(cyc,v,charAgl(pChar(cyc))/9)
  If pAnimTim(cyc)=40 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=48
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>76
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;POWERBOMB
 If pAnim(cyc)=141
  v=pGrappling(cyc)
  animA=175 : animB=176 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=62 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=25 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=47 Or pAnimTim(cyc)=75 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>30 And pAnimTim(cyc)=<80 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=45
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/12)
  EndIf
  If v>0 And pAnimTim(cyc)=77 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=87
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>117
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,73,78,2)
 EndIf
 ;SITTING POWERBOMB
 If pAnim(cyc)=142
  v=pGrappling(cyc)
  animA=177 : animB=178 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=62 Or pAnimTim(cyc)=118 Or pAnimTim(cyc)=133 
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  EndIf
  If pAnimTim(cyc)=30 Or pAnimTim(cyc)=150 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=47 Or pAnimTim(cyc)=73 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>25 And pAnimTim(cyc)=<90 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=45
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=80 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=90
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>160
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;PILEDRIVER
 If pAnim(cyc)=143
  v=pGrappling(cyc)
  animA=179 : animB=180 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=35  Or pAnimTim(cyc)=125 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=140 Or pAnimTim(cyc)=160 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=65 Or pAnimTim(cyc)=110 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<80 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=50
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=70 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=80
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=120 Then ProduceSound(p(v),sFall,22050,1) : FindSmashes(v,0)
  If pAnimTim(cyc)>167
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;PEDIGREE
 If pAnim(cyc)=144
  v=pGrappling(cyc)
  animA=181 : animB=182 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=30  Or pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=95 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=48 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=53 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>35 And pAnimTim(cyc)=<67 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=50
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=57 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=67
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>107
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SUPLEX DROP
 If pAnim(cyc)=145
  v=pGrappling(cyc)
  animA=183 : animB=184 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=80 Or pAnimTim(cyc)=134 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=147 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=85 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<104 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=60 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=70 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If v>0 And pAnimTim(cyc)=95 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=104
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>157
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   If v>0 Then ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,90,95,8)
 EndIf
 ;JACKHAMMER
 If pAnim(cyc)=146
  v=pGrappling(cyc)
  animA=185 : animB=186 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=147 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=100 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=80 Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<120 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=60 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=70 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=110 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=120
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>162
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;GERMAN SUPLEX
 If pAnim(cyc)=147
  v=pGrappling(cyc)
  animA=187 : animB=188 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=87 Or pAnimTim(cyc)=105 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=117 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<55 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=35 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=47 Then FindSmashes(cyc,1)
  If v>0 And pAnimTim(cyc)=47 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=55
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=75 Then ProduceSound(p(cyc),sFall,22050,0.3)
  If pAnimTim(cyc)=90 Then ProduceSound(p(v),sFall,22050,0.3)
  If pAnimTim(cyc)>130
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,45,50,4)
 EndIf
 ;BACK SUPLEX
 If pAnim(cyc)=148
  v=pGrappling(cyc)
  animA=189 : animB=190 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=92 Or pAnimTim(cyc)=110 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=122 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<60 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=52 Then FindSmashes(cyc,1)
  If v>0 And pAnimTim(cyc)=52 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=60
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=80 Then ProduceSound(p(cyc),sFall,22050,0.3)
  If pAnimTim(cyc)=95 Then ProduceSound(p(v),sFall,22050,0.3)
  If pAnimTim(cyc)>135
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,50,55,4)
 EndIf
 ;ATOMIC DROP
 If pAnim(cyc)=149
  v=pGrappling(cyc)
  animA=191 : animB=192 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=58 Or pAnimTim(cyc)=94 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<67 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=67
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,1) : ScarLimb(v,30,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=104 
   ProduceSound(p(v),sFall,22050,0) : ProduceSound(p(v),sThud,22050,0)
   FindSmashes(v,0)
  EndIf
  If pAnimTim(cyc)>122
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;CHOKE SLAM
 If pAnim(cyc)=150
  v=pGrappling(cyc)
  animA=193 : animB=194 : speeder#=1.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=36 Or pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=22 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.5) 
  If pAnimTim(cyc)=22 Then ProduceSound(p(v),sChoke,GetVoice(v),0.3) 
  If pAnimTim(cyc)=43 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=60 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>30 And pAnimTim(cyc)=<73 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/12)
  EndIf
  If v>0 And pAnimTim(cyc)=63 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=73
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>93
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,58,65,2)
 EndIf
 ;REVERSE DDT
 If pAnim(cyc)=151
  v=pGrappling(cyc)
  animA=195 : animB=196 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=90 Or pAnimTim(cyc)=102 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>25 And pAnimTim(cyc)=<45 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=30 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=42 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=50
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=80 Then ProduceSound(p(v),sFall,22050,0.5)
  If pAnimTim(cyc)>112
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,270)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;THROAT TOSS
 If pAnim(cyc)=152
  v=pGrappling(cyc)
  animA=197 : animB=198 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=37 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=10 Then ProduceSound(p(v),sChoke,GetVoice(v),0.3) 
  If pAnimTim(cyc)=30 Then ProduceSound(p(v),sPain(Rnd(1,2)),GetVoice(v),0.5) 
  If pAnimTim(cyc)=17 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<52 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=17
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If v>0 And pAnimTim(cyc)=57 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=65
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>80
   ChangeAnim(cyc,0)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,52,57,2)
 EndIf
 ;ROCK BOTTOM
 If pAnim(cyc)=153
  v=pGrappling(cyc)
  animA=199 : animB=200 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=32 Or pAnimTim(cyc)=80 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=92 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<57 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=22
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=47 Then FindSmashes(v,1)
  If pAnimTim(cyc)=57
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>97
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;MDKO
 If pAnim(cyc)=154
  v=pGrappling(cyc)
  animA=201 : animB=202 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Or pAnimTim(cyc)=92 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=80 Or pAnimTim(cyc)=105 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then Pop(cyc,Rnd(2,7),0) : ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.3) 
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<50 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=30
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=40 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=50
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=80 Then ProduceSound(p(v),sFall,22050,0.5) : FindSmashes(v,0)
  If pAnimTim(cyc)>115
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,270)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;INVERTED ATOMIC DROP
 If pAnim(cyc)=155
  v=pGrappling(cyc)
  animA=203 : animB=204 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<42 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=24
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=42
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,1) : ScarLimb(v,30,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=67
   ProduceSound(p(v),sFall,22050,0) : ProduceSound(p(v),sThud,22050,0)
   FindSmashes(v,0)
  EndIf
  If pAnimTim(cyc)>87
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SPINEBUSTER
 If pAnim(cyc)=156
  v=pGrappling(cyc)
  animA=205 : animB=206 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=82 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=25
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=42 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=52
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>93
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;NECKBREAKER
 If pAnim(cyc)=157
  v=pGrappling(cyc)
  animA=207 : animB=208 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=50 Or pAnimTim(cyc)=100 Or pAnimTim(cyc)=115 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=35 Or pAnimTim(cyc)=125 Then pStepTim(cyc)=99  
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<70 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=50
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=60 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=70
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   CreateSpurt(EntityX(pLimb(v,1),1),EntityY(pLimb(v,1),1)-2,EntityZ(pLimb(v,1),1),15,99)
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,3)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=110 Then ProduceSound(p(v),sFall,22050,0.3)
  If pAnimTim(cyc)>135
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,90)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HEADLOCK TAKEDOWN
 If pAnim(cyc)=158
  v=pGrappling(cyc)
  animA=209 : animB=210 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=70 Or pAnimTim(cyc)=80 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=23 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>12 And pAnimTim(cyc)=<40 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=24 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/6)
  EndIf
  If pAnimTim(cyc)=30 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=40
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=275-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>90
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HIP TOSS
 If pAnim(cyc)=159
  v=pGrappling(cyc)
  animA=211 : animB=212 : speeder#=2.2
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=9 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>13 And pAnimTim(cyc)=<36 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=14
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/6)
  EndIf
  If v>0 And pAnimTim(cyc)=34 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=43
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=275-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>61
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,31,36,3)
 EndIf
 ;DROP TOE HOLD
 If pAnim(cyc)=160
  v=pGrappling(cyc)
  animA=213 : animB=214 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=62 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>8 And pAnimTim(cyc)=<37 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=11
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/6)
  EndIf
  If pAnimTim(cyc)=29 Then FindSmashes(v,1)
  If pAnimTim(cyc)=37
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=275-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>72
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BACK BODY DROP
 If pAnim(cyc)=161
  v=pGrappling(cyc)
  animA=215 : animB=216 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=60 Or pAnimTim(cyc)=70 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=32 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>10 And pAnimTim(cyc)=<37 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=25
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If v>0 And pAnimTim(cyc)=45 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=55
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>77
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,43,48,3)
 EndIf
 ;SAMOAN DROP
 If pAnim(cyc)=162
  v=pGrappling(cyc)
  animA=217 : animB=218 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=32 Or pAnimTim(cyc)=90 Or pAnimTim(cyc)=102 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=115 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<57 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=20 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=30 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=57 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=57
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>122
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;DEATH VALLEY DRIVER
 If pAnim(cyc)=163
  v=pGrappling(cyc)
  animA=219 : animB=220 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=32 Or pAnimTim(cyc)=100 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=117 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0.5) 
  If pAnimTim(cyc)=40 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<57 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=20 Then BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  If pAnimTim(cyc)=30 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=47 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=57
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=82 Then ProduceSound(p(v),sFall,22050,0)
  If pAnimTim(cyc)>125
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;NORTHERN LIGHTS SUPLEX
 If pAnim(cyc)=164
  v=pGrappling(cyc)
  animA=221 : animB=222 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=65 Or pAnimTim(cyc)=80 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=90 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=22 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>10 And pAnimTim(cyc)=<37 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=25
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charStr(pChar(cyc))/9)
  EndIf
  If pAnimTim(cyc)=30 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=37
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=50 Then ProduceSound(p(v),sFall,22050,0.5)
  If pAnimTim(cyc)>100
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;FLYING HEAD SCISSORS
 If pAnim(cyc)=165
  v=pGrappling(cyc)
  animA=223 : animB=224 : speeder#=2.2
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=86 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=95 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=35 Then ProduceSound(p(v),sImpact(Rnd(4,5)),22050,0.4)
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>22 And pAnimTim(cyc)=<68 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=18 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=25 Then BreakMove(cyc,v,charAgl(pChar(cyc))/12)
  If pAnimTim(cyc)=58 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=68
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>104
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SLEEPER HOLD
 If pAnim(cyc)=166
  ;apply
  v=pGrappling(cyc)
  animA=225 : animB=226 : speeder#=2.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf 
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  ;wrench
  start=28
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.9,1.3) Else speeder#=Rnd(0.5,0.9)
   Animate p(cyc),1,speeder#,pSeq(cyc,227),3
   Animate p(v),1,speeder#,pSeq(v,228),3
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,30,0,11)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-3.0,pSeq(cyc,225),5
   Animate p(v),3,-3.0,pSeq(cyc,226),5
  EndIf
  If pAnimTim(cyc)=1005 Or pAnimTim(cyc)=1015 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1010 Or pAnimTim(cyc)=1020 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1023 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc) : pStagger#(cyc)=0.1
   ChangeAnim(v,Rnd(70,71)) : SharpTransition(v,pAnim(cyc),180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SPEAR
 If pAnim(cyc)=167
  v=pGrappling(cyc)
  animA=229 : animB=230 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=70 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=80 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>23 And pAnimTim(cyc)=<45 Then MoveTurn(cyc,v,2) 
  If pAnimTim(cyc)=27
   Pop(cyc,Rnd(2,7),1)
   ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0)
   ProduceSound(p(v),sPain(3),GetVoice(v),1) 
   CreateSpurt(EntityX(pLimb(v,3),1),EntityY(pLimb(v,3),1)-2,EntityZ(pLimb(v,3),1),15,99)
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   WeaponImpact(cyc,v)
  EndIf
  If pAnimTim(cyc)=32 Then BreakMove(cyc,v,charStr(pChar(cyc))/6) 
  If pAnimTim(cyc)=35 Then FindSmashes(v,1)
  If pAnimTim(cyc)=45
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>90
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;RUSSIAN LEGSWEEP
 If pAnim(cyc)=168
  v=pGrappling(cyc)
  animA=231 : animB=232 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=35 Or pAnimTim(cyc)=92 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=102 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=45 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<60 Then MoveTurn(cyc,v,2) 
  If pAnimTim(cyc)=35 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=45 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=50 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=60
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=80 Then ProduceSound(p(cyc),sFall,22050,0)
  If pAnimTim(cyc)>112
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;STANDING CLOTHESLINE
 If pAnim(cyc)=169
  v=pGrappling(cyc)
  animA=233 : animB=234 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=54 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=65 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<38 Then MoveTurn(cyc,v,2)   
  If pAnimTim(cyc)=25
   Pop(cyc,Rnd(2,7),1)
   ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-6 : pHP(v)=0 : pDT(v)=275-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=32 Then BreakMove(cyc,v,charStr(pChar(cyc))/6)
  If pAnimTim(cyc)=38 Then FindSmashes(v,1)
  If pAnimTim(cyc)=47
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,5)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   FindSmashes(v,0) : ScarArea(v,0,0,0,10) 
  EndIf
  If pAnimTim(cyc)>77
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BEAR HUG
 If pAnim(cyc)=170
  ;apply
  v=pGrappling(cyc)
  animA=235 : animB=236 : speeder#=1.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=30 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  ;wrench
  start=40
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(1.1,1.5) Else speeder#=Rnd(0.7,1.1)
   Animate p(cyc),1,speeder#,pSeq(cyc,237),3
   Animate p(v),1,speeder#,pSeq(v,238),3
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,20,20,0,12)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.0,pSeq(cyc,235),5
   Animate p(v),3,-2.0,pSeq(cyc,236),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1030 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc) : pStagger#(cyc)=0.1
   ChangeAnim(v,Rnd(70,71)) : SharpTransition(v,pAnim(cyc),180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;X-FACTOR
 If pAnim(cyc)=171
  v=pGrappling(cyc)
  animA=239 : animB=240 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=18 Or pAnimTim(cyc)=80 Or pAnimTim(cyc)=95 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=9 Or pAnimTim(cyc)=105 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=27 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<50 Then MoveTurn(cyc,v,2) 
  If pAnimTim(cyc)=27
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charAgl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=40 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=50
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>112
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;GUTWRENCH SUPLEX
 If pAnim(cyc)=172
  v=pGrappling(cyc)
  animA=241 : animB=242 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=67 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=77 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<52 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=32
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9) 
  EndIf
  If pAnimTim(cyc)=42 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=52
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-9 : pHP(v)=0 : pDT(v)=300-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-2
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>87
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;REAR ARM BAR
 If pAnim(cyc)=173
  ;apply
  v=pGrappling(cyc)
  animA=243 : animB=244 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf 
  If pAnimTim(cyc)=5 Or pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=15 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  ;wrench
  start=35
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.9,1.3) Else speeder#=Rnd(0.5,0.9)
   Animate p(cyc),1,speeder#,pSeq(cyc,245),2
   Animate p(v),1,speeder#,pSeq(v,246),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,40,0,11)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-2.5,pSeq(cyc,243),5
   Animate p(v),3,-2.5,pSeq(cyc,244),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1028 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc) : pStagger#(cyc)=0.1
   ChangeAnim(v,Rnd(70,71)) : SharpTransition(v,pAnim(cyc),180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;INVERTED PILEDRIVER
 If pAnim(cyc)=174
  v=pGrappling(cyc)
  animA=247 : animB=248 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=45 Or pAnimTim(cyc)=105Or pAnimTim(cyc)=115 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=25 Or pAnimTim(cyc)=125 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>27 And pAnimTim(cyc)=<75 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=55
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=65 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=75
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=100 Then ProduceSound(p(v),sFall,22050,0)
  If pAnimTim(cyc)>135
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;FULL NELSON
 If pAnim(cyc)=175
  ;apply
  v=pGrappling(cyc)
  animA=249 : animB=250 : speeder#=2.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf 
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=24 Or pAnimTim(cyc)=36 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  ;wrench
  start=36
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.3,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,251),2
   Animate p(v),1,speeder#,pSeq(v,252),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,30,30,0,11)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-3.0,pSeq(cyc,249),5
   Animate p(v),3,-3.0,pSeq(cyc,250),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1030 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc) : pStagger#(cyc)=0.1
   ChangeAnim(v,Rnd(70,71)) : SharpTransition(v,pAnim(cyc),180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;COBRA CLUTCH
 If pAnim(cyc)=176
  ;apply
  v=pGrappling(cyc)
  animA=253 : animB=254 : speeder#=2.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf 
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=24 Or pAnimTim(cyc)=36 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=20 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  ;wrench
  start=36
  If pAnimTim(cyc)<start Then DropWeapon(cyc,start*2)
  If pAnimTim(cyc)=start
   Pop(cyc,Rnd(2,7),0)
   ProduceSound(p(v),sPain(Rnd(1,3)),GetVoice(v),0)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
   If pSpecial(cyc)>0 Then speeder#=Rnd(0.7,1.1) Else speeder#=Rnd(0.3,0.7)
   Animate p(cyc),1,speeder#,pSeq(cyc,255),2
   Animate p(v),1,speeder#,pSeq(v,256),2
  EndIf
  If pAnimTim(cyc)=>start And pAnimTim(cyc)<1000 Then HoldEffects(cyc,v,start,20,20,0,11)
  ;leave
  If pAnimTim(cyc)=1000 
   Animate p(cyc),3,-3.0,pSeq(cyc,253),5
   Animate p(v),3,-3.0,pSeq(cyc,254),5
  EndIf
  If pAnimTim(cyc)=1006 Or pAnimTim(cyc)=1018 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=1012 Or pAnimTim(cyc)=1024 Then pStepTim(cyc)=99
  If pAnimTim(cyc)>1030 
   ChangeAnim(cyc,61) : pHurtA#(cyc)=pA#(cyc) : pStagger#(cyc)=0.1
   ChangeAnim(v,Rnd(70,71)) : SharpTransition(v,pAnim(cyc),180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;FULL NELSON SUPLEX
 If pAnim(cyc)=177
  v=pGrappling(cyc)
  animA=257 : animB=258 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=6 Or pAnimTim(cyc)=18 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=100 Or pAnimTim(cyc)=115
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  EndIf
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=24 Or pAnimTim(cyc)=36 Or pAnimTim(cyc)=130 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=55 Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>45 And pAnimTim(cyc)=<75 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=25 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=55 Then BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  If pAnimTim(cyc)=65 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=75
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=90 Then ProduceSound(p(cyc),sFall,22050,1) : FindSmashes(v,0)
  If pAnimTim(cyc)>140
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;UNDERHOOK SUPLEX
 If pAnim(cyc)=178
  v=pGrappling(cyc)
  animA=259 : animB=260 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=67 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=77 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=27 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>20 And pAnimTim(cyc)=<52 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=27
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/9) 
  EndIf
  If v>0 And pAnimTim(cyc)=46 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=52
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>85
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,180)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,40,45,3)
 EndIf
 ;FLIPPING PILEDRIVER
 If pAnim(cyc)=179
  v=pGrappling(cyc)
  animA=261 : animB=262 : speeder#=2.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=10 Or pAnimTim(cyc)=28 Or pAnimTim(cyc)=92 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=20 Or pAnimTim(cyc)=108 Or pAnimTim(cyc)=120 Then pStepTim(cyc)=99
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=42 Or pAnimTim(cyc)=56 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>40 And pAnimTim(cyc)=<70 Then MoveTurn(cyc,v,1) 
  If pAnimTim(cyc)=40 Then BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  If pAnimTim(cyc)=48 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc)) : Pop(cyc,Rnd(2,7),0)
  If pAnimTim(cyc)=60 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=70
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,0)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,1) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=90 Then ProduceSound(p(v),sFall,22050,1) : FindSmashes(v,0)
  If pAnimTim(cyc)>128
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0.1)
   ChangeAnim(v,83) : SharpTransition(v,83,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;PUMP HANDLE SLAM
 If pAnim(cyc)=180
  v=pGrappling(cyc)
  animA=263 : animB=264 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0) 
  If pAnimTim(cyc)=56 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<67 Then MoveTurn(cyc,v,3)
  If pAnimTim(cyc)=25 Then BreakMove(cyc,v,charStr(pChar(cyc))/9)
  If pAnimTim(cyc)=35 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If v>0 And pAnimTim(cyc)=62 Then FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=72
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0 : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>102
   ChangeAnim(cyc,0) ;: SharpTransition(cyc,1,0)
   If v>0 Then ChangeAnim(v,80) : SharpTransition(v,80,90)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,58,63,9)
 EndIf
 ;MICHINOKU DRIVER
 If pAnim(cyc)=181
  v=pGrappling(cyc)
  animA=265 : animB=266 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=15 Or pAnimTim(cyc)=30 Or pAnimTim(cyc)=92 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  If pAnimTim(cyc)=102 Or pAnimTim(cyc)=112 Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=15 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0) 
  If pAnimTim(cyc)=50 Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=>15 And pAnimTim(cyc)=<67 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=40 
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If pAnimTim(cyc)=57 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=67
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,2)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(6),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0 : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarArea(v,0,0,0,10)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>122
   ChangeAnim(cyc,0) : SharpTransition(cyc,1,0)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;TORNDAO DDT
 If pAnim(cyc)=182
  v=pGrappling(cyc)
  animA=267 : animB=268 : speeder#=2.5
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=8 Or pAnimTim(cyc)=20 Or pAnimTim(cyc)=30 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=14 Or pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=16 Then ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
  If pAnimTim(cyc)=32 Then BreakMove(cyc,v,charSkl(pChar(cyc))/9)
  If pAnimTim(cyc)=38 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If pAnimTim(cyc)=48
   Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-15 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=350-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)=66 Then ProduceSound(p(v),sFall,22050,1) : FindSmashes(v,0)
  If pAnimTim(cyc)>82
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,180)
   ChangeAnim(v,80) : SharpTransition(v,80,0.1)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;REVERSE SUPLEX
 If pAnim(cyc)=183
  v=pGrappling(cyc)
  animA=269 : animB=270 : speeder#=2.0
  If pAnimTim(cyc)=0 
   Animate p(cyc),3,speeder#,pSeq(cyc,animA),0 
   Animate p(v),3,speeder#,pSeq(v,animB),0
  EndIf
  If pAnimTim(cyc)=12 Or pAnimTim(cyc)=25 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0) 
  If pAnimTim(cyc)=35 Then ProduceSound(p(cyc),sPain(Rnd(1,2)),GetVoice(cyc),0)
  If pAnimTim(cyc)=42 Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>25 And pAnimTim(cyc)=<55 Then MoveTurn(cyc,v,3) 
  If pAnimTim(cyc)=32
   ReverseMove(cyc,v,animA,animB,speeder#,pAnimTim(cyc))
   BreakMove(cyc,v,charSkl(pChar(cyc))/12)
  EndIf
  If v>0 And pAnimTim(cyc)=45 Then FindSmashes(cyc,1) : FindSmashes(v,1)
  If v>0 And pAnimTim(cyc)=55
   Pop(cyc,Rnd(2,7),0) : RiskApplause(cyc,3)
   ProduceSound(p(v),sFall,22050,1) : ProduceSound(p(v),sThud,22050,1)
   ProduceSound(p(v),sImpact(3),22050,0) : ProduceSound(p(v),sPain(Rnd(2,3)),GetVoice(v),1) 
   pHealth(v)=pHealth(v)-12 : pHP(v)=0
   pDT(cyc)=50-pHealth(cyc) : pDT(v)=325-pHealth(v)
   pHeat(cyc)=pHeat(cyc)+10 : pHeat(v)=pHeat(v)-5
   FindSmashes(cyc,0) : FindSmashes(v,0) : ScarLimb(v,1,5)
   WeaponImpact(cyc,v) : SpecialEffects(cyc,v)
  EndIf
  If pAnimTim(cyc)>85
   ChangeAnim(cyc,80) : SharpTransition(cyc,80,0.1)
   If v>0 Then ChangeAnim(v,83) : SharpTransition(v,83,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveFallCheck(cyc,v,45,50,10)
 EndIf

 ;REVERSAL PROCESS
 If pAnim(cyc)=199
  ;rewind animation
  v=pGrappling(cyc)
  pRevTim#(cyc)=pRevTim#(cyc)-1.5
  If pRevTim#(cyc)=>0 Then SetAnimTime p(cyc),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(cyc,pRevAnim(cyc))
  If pRevTim#(cyc)=>0 Then SetAnimTime p(v),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(v,pRevAnim(v))
  ;switch control
  If pRevTim#(cyc)=<0
   ChangeAnim(cyc,103)
   ChangeAnim(v,101) : SharpTransition(v,101,180)
   pGrappling(cyc)=0 : pGrappler(v)=0
   pGrappling(v)=cyc : pGrappler(cyc)=v
   randy=Rnd(0,3)
   If randy=0 Then move=110
   If randy>0 Then move=movAnim(charMove(pChar(v),Rnd(1,12)))
   If pSpecial(v)>0 Then move=movAnim(charMove(pChar(v),15))
   If move<110 Or pRole(v)=1 Then move=110
   TriggerMove(v,move)
   Animate p(v),3,1,pSeq(cyc,121),0
   Animate p(cyc),3,1,pSeq(cyc,122),0 
   PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
   PositionEntity p(v),pX#(v),pY#(v),pZ#(v)
   pOldX#(cyc)=EntityX(pLimb(cyc,30),1) : pOldZ#(cyc)=EntityZ(pLimb(cyc,30),1)
   pOldX#(v)=EntityX(pLimb(v,30),1) : pOldZ#(v)=EntityZ(pLimb(v,30),1)
  EndIf
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;FIX GRAPPLE POSITIONS
Function FixGrapple(cyc)
 ;project victim from grappler
 v=pGrappling(cyc)
 PositionEntity p(v),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(v),0,pA#(cyc),0
 MoveEntity p(v),0,0,15
 ;update co-ordinates
 pX#(v)=EntityX(p(v))
 pZ#(v)=EntityZ(p(v))
 pY#(v)=pY#(cyc)
 pA#(v)=CleanAngle#(pA#(cyc)+180)
 ;pre-empt collisions
 EnforceBlocks(v)
 ;push grappler back?
 If pPlatform(cyc)=0 And pPlatform(v)=0
  PositionEntity dummy,pX#(v),pY#(v),pZ#(v)
  RotateEntity dummy,0,pA#(v),0
  MoveEntity dummy,0,0,15
  If EntityX(dummy)>pX#(cyc)-1 And EntityX(dummy)<pX#(cyc)+1 And EntityZ(dummy)>pZ#(cyc)-1 And EntityZ(dummy)<pZ#(cyc)+1
   satisfied=1
  Else
   MoveEntity p(cyc),0,0,-1
   pX#(cyc)=EntityX(p(cyc)) : pZ#(cyc)=EntityZ(p(cyc))
  EndIf
 EndIf
End Function

;MANAGE GRAPPLE
Function ManageGrapple(cyc)
 v=pGrappling(cyc)
 ;break if interrupted
 If pAnim(cyc)<100 Or pAnim(v)<100 Then LoseGrapple(cyc,0) 
 ;break on command
 If pAnimTim(cyc)>5
  If pControl(cyc)>0 And cGrab(cyc)=1 And DirPressed(cyc)=0 Then LoseGrapple(cyc,0)
 EndIf
 ;break if out of touch
 If InProximity(cyc,v,18)=0 Or InDirectRange(cyc,v,15)=0 Then LoseGrapple(cyc,0)
 ;break over time
 randy=Rnd(0,200)
 If randy=0
  If pControl(v)=0 Or DirPressed(v) Or ActionPressed(v) Then LoseGrapple(cyc,-1)
 EndIf
 ;find moves
 If pAnimTim(cyc)>5 Or pMomentum(v)>0 Then FindMoves(cyc)
End Function

;LOSE GRAPPLE
Function LoseGrapple(cyc,violent) ;-1=victim attack, 1=perpetrator attack, 2=big attack
 v=pGrappling(cyc)
 If v>0
  ;break into hurt
  If violent=0 Then ProduceSound(p(cyc),sImpact(Rnd(4,5)),22050,0.5) 
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,1) 
  pHurtA#(cyc)=pA#(v) : pHurtA#(v)=pA#(cyc)
  pStagger#(cyc)=0.2 : pStagger#(v)=0.2
  ChangeAnim(cyc,61) : ChangeAnim(v,61)
  If violent=1 Then ChangeAnim(cyc,Rnd(40,41))
  If violent=2 Then ChangeAnim(cyc,42)
  If violent=-1 Then ChangeAnim(v,Rnd(40,41))
  ;reset grapple status
  EndMove(cyc,v)
 EndIf
End Function

;FIND MOVES
Function FindMoves(cyc)
 ;standard options
 v=pGrappling(cyc) : move=0
 If pPlatform(cyc)=0 And pPlatform(v)=0 
  ;CPU commands
  If pControl(cyc)=0 ;CPU
   randy=Rnd(0,50)
   If matchCage>0 Or matchRopes>0 Then randy=Rnd(-3,50)
   If randy=<3 And pSpecial(cyc)=0 Then move=110 ;whip
   If randy=>4 And randy=<15 Then move=movAnim(charMove(pChar(cyc),randy-3)) ;random move
   If matchState=3 And matchOTT=1 And matchCage=0 ;rumble assistance
    If randy=>16 And randy=<18 Then move=110
    If randy=>19 And randy=<20 Then move=115
    If randy=21 Then move=121 
   EndIf
   If move>110 And (pRole(cyc)=1 Or pTeam(cyc)=pTeam(v)) Then move=110 ;safe move for team-mates
  EndIf
  ;HUMAN commands
  If pControl(cyc)>0 
   If cGrab(cyc)=1 And DirPressed(cyc) Then move=110 ;whip
   If cAttack(cyc)=1 And DirPressed(cyc)=0 Then move=movAnim(charMove(pChar(cyc),1)) ;move A centre
   If cAttack(cyc)=1 And cUp(cyc) Then move=movAnim(charMove(pChar(cyc),2)) ;move A up
   If cAttack(cyc)=1 And cDown(cyc) Then move=movAnim(charMove(pChar(cyc),3)) ;move A down
   If cAttack(cyc)=1 And (cLeft(cyc) Or cRight(cyc)) Then move=movAnim(charMove(pChar(cyc),4)) ;move A side
   If cRun(cyc)=1 And DirPressed(cyc)=0 Then move=movAnim(charMove(pChar(cyc),5)) ;move B centre
   If cRun(cyc)=1 And cUp(cyc) Then move=movAnim(charMove(pChar(cyc),6)) ;move B up
   If cRun(cyc)=1 And cDown(cyc) Then move=movAnim(charMove(pChar(cyc),7)) ;move B down
   If cRun(cyc)=1 And (cLeft(cyc) Or cRight(cyc)) Then move=movAnim(charMove(pChar(cyc),8)) ;move B side
   If cPick(cyc)=1 And DirPressed(cyc)=0 Then move=movAnim(charMove(pChar(cyc),9)) ;move C centre
   If cPick(cyc)=1 And cUp(cyc) Then move=movAnim(charMove(pChar(cyc),10)) ;move C up
   If cPick(cyc)=1 And cDown(cyc) Then move=movAnim(charMove(pChar(cyc),11)) ;move C down
   If cPick(cyc)=1 And (cLeft(cyc) Or cRight(cyc)) Then move=movAnim(charMove(pChar(cyc),12)) ;move C side
  EndIf
  ;novelties
  If pMomentum(v)>0 Then move=movAnim(charMove(pChar(cyc),13)) ;momentum override
  If pSpecial(cyc)>0 And move>110 ;special override
   If cAttack(cyc) And cRun(cyc) Then move=movAnim(charMove(pChar(v),15)) Else move=movAnim(charMove(pChar(cyc),15)) 
  EndIf
 EndIf
 ;perpetrator on apron
 If pPlatform(cyc)>0 And pPlatform(v)=0
  randy=Rnd(0,10)
  If pControl(cyc)=0 And randy=0 Then move=113
  If pControl(cyc)>0 And cGrab(cyc)=1 And DirPressed(cyc) Then move=113
  If pControl(cyc)>0 And (cAttack(cyc)=1 Or cRun(cyc)=1 Or cPick(cyc)=1) Then move=113
  If move=113 And matchCage>0 Then move=1
 EndIf
 ;victim on apron
 If pPlatform(cyc)=0 And pPlatform(v)>0
  randy=Rnd(0,10)
  If pControl(cyc)=0 And randy=0 Then move=114
  If pControl(cyc)>0 And cGrab(cyc)=1 And DirPressed(cyc) Then move=114
  If pControl(cyc)>0 And (cAttack(cyc)=1 Or cRun(cyc)=1 Or cPick(cyc)=1) Then move=114
 EndIf
 ;both on apron
 If pPlatform(cyc)>0 And pPlatform(v)>0
  randy=Rnd(0,5)
  If pControl(cyc)=0 And randy=0 Then move=1
  If pControl(cyc)>0 And cGrab(cyc)=1 And DirPressed(cyc) Then move=1
  If pControl(cyc)>0 And (cAttack(cyc)=1 Or cRun(cyc)=1 Or cPick(cyc)=1) Then move=1
 EndIf
 ;convert whip into throw out?
 If move=110 And pY#(cyc)=wStage# And matchOTT=0 And matchCage=0
  If pZ#(cyc)>wBlockZ1#(1)-2 And pX#(cyc)>wBlockX1#(0)+35 And pX#(cyc)<wBlockX2#(0)-35 ;north side
   If pA#(cyc)=>140 And pA#(cyc)=<220 Then pA#(cyc)=180 : move=111
  EndIf
  If pX#(cyc)>wBlockX1#(2)-2 And pZ#(cyc)>wBlockZ1#(0)+35 And pZ#(cyc)<wBlockZ2#(0)-35 ;east side
   If pA#(cyc)=>50 And pA#(cyc)=<130 Then pA#(cyc)=90 : move=111
  EndIf
  If pZ#(cyc)<wBlockZ2#(3)+2 And pX#(cyc)>wBlockX1#(0)+35 And pX#(cyc)<wBlockX2#(0)-35 ;south side
   If pA#(cyc)=<40 Or pA#(cyc)=>320 Then pA#(cyc)=0 : move=111
  EndIf
  If pX#(cyc)<wBlockX2#(4)+2 And pZ#(cyc)>wBlockZ1#(0)+35 And pZ#(cyc)<wBlockZ2#(0)-35 ;west side
   If pA#(cyc)=>230 And pA#(cyc)=<310 Then pA#(cyc)=270 : move=111
  EndIf
 EndIf
 ;convert whip into throw in?
 If move=110 And pY#(cyc)=wGround# And matchCage=0
  If pZ#(cyc)<wBlockZ2#(0)+2 And pZ#(cyc)>wBlockZ1#(0) And pX#(cyc)>wBlockX1#(0)+35 And pX#(cyc)<wBlockX2#(0)-35 ;north side
   If pA#(cyc)=<40 Or pA#(cyc)=>320 Then pA#(cyc)=0 : move=112
  EndIf
  If pX#(cyc)<wBlockX2#(0)+2 And pX#(cyc)>wBlockX1#(0) And pZ#(cyc)>wBlockZ1#(0)+35 And pZ#(cyc)<wBlockZ2#(0)-35 ;east side
   If pA#(cyc)=>230 And pA#(cyc)=<310 Then pA#(cyc)=270 : move=112
  EndIf
  If pZ#(cyc)>wBlockZ1#(0)-2 And pZ#(cyc)<wBlockZ2#(0) And pX#(cyc)>wBlockX1#(0)+35 And pX#(cyc)<wBlockX2#(0)-35 ;south side
   If pA#(cyc)=>140 And pA#(cyc)=<220 Then pA#(cyc)=180 : move=112
  EndIf
  If pX#(cyc)>wBlockX1#(0)-2 And pX#(cyc)<wBlockX2#(0) And pZ#(cyc)>wBlockZ1#(0)+35 And pZ#(cyc)<wBlockZ2#(0)-35 ;west side
   If pA#(cyc)=>50 And pA#(cyc)=<130 Then pA#(cyc)=90 : move=112
  EndIf
 EndIf
 ;last minute break
 randy=Rnd(0,10)
 If optLevel=>4 And pControl(cyc)>0 And pControl(v)=0 Then randy=Rnd(0,5)
 If randy=0 And move>0 And pMomentum(v)=0
  If pControl(v)=0 Or DirPressed(v) Or ActionPressed(v) Then LoseGrapple(cyc,-1) : move=0
 EndIf
 ;deliver chosen move 
 If move>100 Then TriggerMove(cyc,move) 
 ;resort to punch
 If move=1 Or move=2 Then LoseGrapple(cyc,move)
End Function

;TRIGGER MOVE
Function TriggerMove(cyc,move)
 ;make victim copy grappler
 v=pGrappling(cyc)
 If move>400
  pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
 Else
  pX#(v)=pX#(cyc) : pZ#(v)=pZ#(cyc) : pA#(v)=pA#(cyc)
 EndIf
 pExcusedPlays(cyc)=1 : pExcusedPlays(v)=1
 pExcusedItems(cyc)=1 : pExcusedItems(v)=1
 ;trigger animations
 ChangeAnim(cyc,move) : ChangeAnim(v,109)
 ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
End Function

;MOVE TURNING
Function MoveTurn(cyc,v,degree#)
 If cLeft(cyc)=1 Then pA#(cyc)=pA#(cyc)+degree#
 If cRight(cyc)=1 Then pA#(cyc)=pA#(cyc)-degree#
 pA#(v)=pA#(cyc)
End Function

;MOVE FALL OUT CHECKING
Function MoveFallCheck(cyc,v,range1,range2,style)
 ;excuse edges
 pExcusedEdges(v)=0
 If pAnimTim(cyc)=>range1-10 And pAnimTim(cyc)=<range2 And matchCage=0 Then pExcusedEdges(v)=1
 ;find falls
 If pAnimTim(cyc)=>range1 And pAnimTim(cyc)=<range2 And pY#(cyc)=wStage# And matchCage=0
  FindMoveFalls(cyc,v,style)
 EndIf
End Function

;FIND MOVE THROW OUT'S
Function FindMoveFalls(cyc,v,style)
 If InsideRing(RealX#(v),RealZ#(v),-25)=0
  ;reset status
  pGrappling(cyc)=0 : pGrappler(v)=0
  pY#(v)=EntityY(FindChild(p(v),"Hips"),1)-8
  If pY#(v)<42 Then pY#(v)=42
  ;transitions (onto back)
  If style=1 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,76) ;thrown forward, feet first onto back (slam)
  If style=2 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,76) ;thrown forward, head first onto back (chokeslam)
  If style=3 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,76) ;thrown behind, feet first onto back (suplex)
  If style=4 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,76) ;thrown behind, head first onto back (back suplex)
  ;transitions (onto front)
  If style=5 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,77) ;thrown forward, head first onto front (slam)
  If style=6 Then pA#(v)=pA#(cyc)+270 : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,77) ;thrown backward, sideways onto front (gorilla press)
  If style=7 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,77) ;thrown backward, headfirst onto front (slingshot)
  If style=8 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,77) ;thrown forward, feet first onto front (suplex drop)
  ;novelties
  If style=9 Then pA#(v)=pA#(cyc)+90 : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,76) ;thrown forward, sideways onto back (pump handle)
  If style=10 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,77) ;thrown backward, feet first onto front (rev suplex)
  ;immediate animation
  If pAnim(v)=76 Then SharpTransition(v,74,0.1)
  If pAnim(v)=77 Then SharpTransition(v,75,0.1)
  ;ensure clear flight
  While FlightClear(v,pFlightA#(v),40)=0
   tA#=-1
   If pZ#(v)=>wBlockZ1#(1) Then tA#=0
   If pX#(v)=>wBlockX1#(2) Then tA#=270
   If pZ#(v)=<wBlockZ2#(3) Then tA#=180
   If pX#(v)=<wBlockX2#(4) Then tA#=90
   If tA#=>0 Then pFlightA#(v)=pFlightA#(v)+ReachAngle#(pFlightA#(v),tA#,5)
   If tA#=-1 Then pFlightA#(v)=pFlightA#(v)+5
  Wend
 EndIf
End Function

;MESS UP MOVE
Function BreakMove(cyc,v,chance)
 ;asssess chances
 If matchState=3 And matchOTT=1 And matchCage=0 Then chance=chance/2
 If optLevel=4 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/2
 If optLevel=5 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/3
 If DirPressed(v) Or ActionPressed(v) Or pControl(v)=0 Then struggle=1 Else struggle=0
 ;execute risk
 If chance<2 Then chance=2
 randy=Rnd(0,chance)
 If randy=0 And v>0 And pAnim(cyc)<>199 And pSpecial(cyc)=0 And struggle=1
  Pop(cyc,Rnd(4,9),0) : Pop(v,Rnd(4,9),0)
  ProduceSound(p(cyc),sPain(Rnd(2,3)),GetVoice(cyc),1)
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  ProduceSound(p(cyc),sSwing,20000,0.3)
  BreakPlayer(cyc) : BreakPlayer(v)
  RiskInjury(cyc,0) : RiskInjury(v,0)
  pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
  If optLevel=>4 And pControl(cyc)>0 And pControl(v)=0 Then pHeat(cyc)=pHeat(cyc)+5
  Repeat
   pA#(v)=Rnd(0,360)
  Until SatisfiedAngle(pA#(v),pA#(cyc),45)=0
  EndMove(cyc,v)
 EndIf
End Function

;BREAK PLAYER OUT OF GRAPPLE
Function BreakPlayer(cyc)
 pHP(cyc)=0 : pDT(cyc)=200-pHealth(cyc)
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc) 
 RotateEntity p(cyc),0,pA#(cyc),0
 ChangeAnim(cyc,Rnd(68,71))
 SharpTransition(cyc,pAnim(cyc),0) 
 If InsideRing(pX#(cyc),pZ#(cyc),0) And pY#(cyc)=>wStage# Then ConfineToRing(cyc)
 pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc) 
 Animate p(cyc),3,1.8,pSeq(cyc,pAnim(cyc)),0
 pAnimTim(cyc)=1
End Function

;REVERSE MOVE
Function ReverseMove(cyc,v,animA,animB,speed#,length#)
 ;assess chances
 chance=(120-charSkl(pChar(v)))/6
 If matchState=3 And matchOTT=1 And matchCage=0 Then chance=chance/2
 If optLevel=4 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/2
 If optLevel=5 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/3
 If DirPressed(v) Or ActionPressed(v) Or pControl(v)=0 Then struggle=1 Else struggle=0
 If game=1 And pChar(v)=gamOpponent(slot,GetDate()) And gamVariable(slot)=6 Then struggle=0
 ;execute risk
 If chance<2 Then chance=2
 randy=Rnd(0,chance)
 If (randy=0 Or pSpecial(v)>0) And v>0 And pSpecial(cyc)=0 And struggle=1
  ;effects
  Pop(cyc,Rnd(2,9),0) : Pop(v,Rnd(2,9),0)
  ProduceSound(p(cyc),sPain(Rnd(1,3)),GetVoice(v),0)
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  ProduceSound(p(cyc),sSwing,20000,0.3)
  pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
  If optLevel=>4 And pControl(cyc)>0 And pControl(v)=0 Then pHeat(cyc)=pHeat(cyc)+5
  ;reverse animation
  pRevAnim(cyc)=animA : pRevAnim(v)=animB
  pRevTim#(cyc)=length# : pRevSpeed#(cyc)=speed#
  SetAnimTime p(cyc),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(cyc,pRevAnim(cyc))
  SetAnimTime p(v),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(cyc,pRevAnim(v))
  ChangeAnim(cyc,199)
 EndIf
End Function

;END MOVE (COMMON CODE)
Function EndMove(cyc,v)
 pGrappling(cyc)=0 : pGrappler(v)=0
 pExcusedPlays(cyc)=0 : pExcusedPlays(v)=0
 pExcusedItems(cyc)=0 : pExcusedItems(v)=0
 pExcusedWorld(cyc)=0 : pExcusedWorld(v)=0
 pExcusedEdges(cyc)=0 : pExcusedEdges(v)=0
 pStretched(v)=0
End Function

;SPECIAL EFFECTS
Function SpecialEffects(cyc,v)
 ;special move bonus
 If pSpecial(cyc)>0
  Pop(cyc,2,1) : RiskApplause(cyc,0)
  pHealth(v)=pHealth(v)-5 : pDT(v)=pDT(v)+100
  pHeat(v)=pHeat(v)-5 : pSpecial(cyc)=pSpecial(cyc)/2
 EndIf
 ;momentum bonus
 If pMomentum(v)>0
  Pop(cyc,Rnd(2,7),1) : RiskApplause(cyc,3)
  pHealth(v)=pHealth(v)-1 : pDT(v)=pDT(v)+25
  pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
 EndIf
 If pDT(v)>400 Then pDT(v)=400
End Function