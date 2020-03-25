#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Mar 25 02:38:23 2020

@author: abhijithneilabraham
"""

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
        name=db.child(i).child(j).child("Name").get().val()
        print(name)
    
        
        
