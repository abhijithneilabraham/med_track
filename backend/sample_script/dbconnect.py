#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Mar 25 02:38:23 2020

@author: abhijithneilabraham
"""
from algo import Tracker
import pyrebase
config = {
  "apiKey": "AIzaSyDkcfN2VKcfJbreSOwTGaX7CqS6MjZ0t8o",
  "authDomain": "https://myapplication2-f5537.firebaseapp.com",
  "databaseURL": "https://myapplication2-f5537.firebaseio.com",
  "storageBucket": "myapplication2-f5537.appspot.com",
 
}
firebase = pyrebase.initialize_app(config)
db = firebase.database()
suppliers=db.shallow().get().val()
uid=suppliers
print(uid)
for i in uid:
    print(i)
    customerpath=db.child(i)
    customers=customerpath.shallow().get().val()
    print(customers)
    for j in customers:
        commname=db.child(i).child(j).child("Commodity Names").get().val()
        print(commname)
        for k in commname:
            dat=commname[k]
            starttime,stock=dat["Date"],dat["Stock"]
            duration,days=list(dat["Period"].keys())[0],list(dat["Period"].values())[0]
            print(starttime)
            print(stock)
            print(days)
            print(duration)
            track=Tracker(days,duration,stock,starttime)
            remtime,flag=track.calctime()
            print("Remaining days",remtime,"with flag",flag)
            
            


    
        
        
