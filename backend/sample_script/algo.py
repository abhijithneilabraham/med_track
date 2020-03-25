#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 24 15:53:01 2020

@author: abhijithneilabraham
"""
from datetime import date,timedelta,datetime
class Tracker:
    def __init__(self,d,dr,st,starttime):
        self.d=d
        self.dr=dr
        self.st=st
        self.starttime=starttime
        self.flag=0
        self.maxdur=30
    def exptime(self,dosage,dur,stock):
        expiry=0
        if dur=="Day":
            expiry=stock//dosage
        elif dur=="Week":
            expiry=stock*7//(dosage)
        elif dur=="Month":
            expiry=stock*30//(dosage)
        return expiry
    
 
    #String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()); this is for java
    def updatetime(self,starttime):
        starttime = datetime.strptime(starttime, '%Y-%m-%d').date()

        deadline=starttime+timedelta(self.exptime(self.d,self.dr,self.st))
        
        return deadline
    def calctime(self):
        now=date.today()
        balance=(self.updatetime(self.starttime)-now).days
        if balance<self.maxdur:
            self.flag=1
        return balance,self.flag
    
        
        
#track=Tracker(2,"Day",200,"2020-01-01")
#remtime,flag=track.calctime()
#print("Remaining days",remtime,"with flag",flag)