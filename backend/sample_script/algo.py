#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 24 15:53:01 2020

@author: abhijithneilabraham
"""
from datetime import date,timedelta
class tracker:
    def __init__(self,d,dr,st):
        self.d=d
        self.dr=dr
        self.st=st
    def exptime(self,dosage,dur,stock):
        expiry=0
        if dur=="Day":
            expiry=stock//dosage
        elif dur=="Week":
            expiry=stock//(dosage*7)
        elif dur=="Month":
            expiry=stock//(dosage*30)
        return expiry
    
 
    #String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()); this is for java
    def updatetime(self,starttime):
        d=self.d
        dr=self.dr
        st=self.st
        deadline=starttime+timedelta(self.exptime(d,dr,st))
        return deadline
track=tracker(20,30,200)
print(track.updatetime(date.today()))